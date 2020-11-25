package net.swordie.ms.client.jobs.resistance;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.world.field.Field;

import java.util.Arrays;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Blaster extends Citizen {

    public static final int SECRET_ASSEMBLY = 30001281;

    public static final int HAMMER_SMASH = 37111000;
    public static final int HAMMER_SMASH_CHARGE = 37110001;
    public static final int ARM_CANNON_BOOST = 37101003;
    public static final int MAPLE_WARRIOR_BLASTER = 37121006;
    public static final int HEROS_WILL_BLASTER = 37121007;

    public static final int FOR_LIBERTY_BLASTER = 37121053;
    public static final int CANNON_OVERDRIVE = 37121054;
    public static final int HYPER_MAGNUM_PUNCH = 37121052;


    //Revolving Cannon
    public static final int REVOLVING_CANNON_RELOAD = 37000010;
    public static final int REVOLVING_CANNON = 37001001;
    public static final int REVOLVING_CANNON_2 = 37100008;
    public static final int REVOLVING_CANNON_3 = 37000009;

    public static final int REVOLVING_CANNON_PLUS = 37100007;
    public static final int REVOLVING_CANNON_PLUS_II = 37110007;
    public static final int REVOLVING_CANNON_PLUS_III = 37120008;

    public static final int BUNKER_BUSTER_EXPLOSION_3 = 37001002;
    public static final int BUNKER_BUSTER_EXPLOSION_4 = 37000011;
    public static final int BUNKER_BUSTER_EXPLOSION_5 = 37000012;
    public static final int BUNKER_BUSTER_EXPLOSION_6 = 37000013;


    //Blast Shield
    public static final int BLAST_SHIELD = 37000006;
    public static final int SHIELD_TRAINING = 37110008;
    public static final int SHIELD_TRAINING_II = 37120009;
    public static final int VITALITY_SHIELD = 37121005;


    //Combo Training
    public static final int COMBO_TRAINING = 37110009;
    public static final int COMBO_TRAINING_II = 37120012;



    private int[] addedSkills = new int[] {
            SECRET_ASSEMBLY,
    };

    private int[] buffs = new int[] {
            ARM_CANNON_BOOST,
            MAPLE_WARRIOR_BLASTER,
            FOR_LIBERTY_BLASTER,
            CANNON_OVERDRIVE,
    };

    private int gauge = 0;
    private int ammo = getMaxAmmo();
    private int lastAttack = 0;

    public Blaster(Char chr) {
        super(chr);
        if(chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isBlaster(id);
    }



    // Buff related methods --------------------------------------------------------------------------------------------

    public void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case ARM_CANNON_BOOST:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case MAPLE_WARRIOR_BLASTER:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case FOR_LIBERTY_BLASTER:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieMaxDamageOverR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMaxDamageOverR, o2);
                break;
            case CANNON_OVERDRIVE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(RWMaximizeCannon, o1);
                break;
        }
        tsm.sendSetStatPacket();
    }

    public boolean isBuff(int skillID) {
        return super.isBuff(skillID) || Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    public void resetBlastShield() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        tsm.removeStat(RWBarrier, false);
        tsm.sendResetStatPacket();
    }



    // Attack related methods ------------------------------------------------------------------------------------------

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {
        Char chr = c.getChr();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(attackInfo.skillId);
        int skillID = 0;
        SkillInfo si = null;
        boolean hasHitMobs = attackInfo.mobAttackInfo.size() > 0;
        int slv = 0;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skill.getSkillId());
            slv = skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }
        incrementComboTraining(skillID, tsm);
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case HAMMER_SMASH_CHARGE:
                SkillInfo hmc = SkillData.getSkillInfoById(HAMMER_SMASH);
                AffectedArea hmci = AffectedArea.getPassiveAA(chr, HAMMER_SMASH, (byte) slv);
                hmci.setMobOrigin((byte) 0);
                hmci.setPosition(chr.getPosition());
                hmci.setRect(hmci.getPosition().getRectAround(hmc.getRects().get(0)));
                hmci.setDelay((short) 5);
                chr.getField().spawnAffectedArea(hmci);
                break;
            case BUNKER_BUSTER_EXPLOSION_3:
            case BUNKER_BUSTER_EXPLOSION_4:
            case BUNKER_BUSTER_EXPLOSION_5:
            case BUNKER_BUSTER_EXPLOSION_6:

                break;

            case HYPER_MAGNUM_PUNCH:
                o1.nOption = 5;
                o1.rOption = skillID;
                o1.tOption = 10;
                tsm.putCharacterStatValue(RWMagnumBlow, o1);
                tsm.sendSetStatPacket();
                break;
        }
        super.handleAttack(c, attackInfo);
    }

    public int getGauge() {
        return gauge;
    }

    public void setGauge(int gauge) {
        this.gauge = gauge;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public void addAmmo() {
        addAmmo(1);
    }

    public void addAmmo(int amount) {
        ammoChange(amount);
    }

    public void removeAmmo() {
        removeAmmo(1);
    }

    public void removeAmmo(int amount) {
        ammoChange(-amount);
    }

    public void ammoChange(int amount) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        if(getAmmo() > 0) {
            setAmmo(getAmmo() + amount);
            o.nOption = 1;
            o.bOption = getAmmo();
            o.cOption = getGauge();
            tsm.putCharacterStatValue(RWCylinder, o);
            tsm.sendSetStatPacket();
        }
    }

    public void addGauge() {
        addGauge(1);
    }

    public void addGauge(int amount) {
        gaugeChange(amount);
    }

    public void removeGauge() {
        removeGauge(1);
    }

    public void removeGauge(int amount) {
        gaugeChange(-amount);
    }

    public void gaugeChange(int amount) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        if(getGauge() < getMaxAmmo()) {
            setGauge(getGauge() + amount);
            o.nOption = 1;
            o.bOption = getAmmo();
            o.cOption = getGauge();
            tsm.putCharacterStatValue(RWCylinder, o);
            tsm.sendSetStatPacket();
        }
    }

    public int getMaxAmmo() {
        int maxAmmo = 3;
        if(chr.hasSkill(REVOLVING_CANNON_PLUS)) {
            maxAmmo = 4;
        }
        if(chr.hasSkill(REVOLVING_CANNON_PLUS_II)) {
            maxAmmo = 5;
        }
        if(chr.hasSkill(REVOLVING_CANNON_PLUS_III)) {
            maxAmmo = 6;
        }
        return maxAmmo;
    }

    public void reloadCylinder() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = 1;
        o.bOption = getMaxAmmo(); //ammo
        o.cOption = getGauge(); //gauge
        tsm.putCharacterStatValue(RWCylinder, o);
        tsm.sendSetStatPacket();
    }

    private void incrementComboTraining(int skillId, TemporaryStatManager tsm) {
        Option o = new Option();
        SkillInfo chargeInfo = SkillData.getSkillInfoById(COMBO_TRAINING);
        int amount = 1;
        if(tsm.hasStat(RWCombination)) {
            amount = tsm.getOption(RWCombination).nOption;
            if (lastAttack == skillId) {
                return;
            }
            if(amount < chargeInfo.getValue(z, 1)) {
                amount++;
            }
        }
        lastAttack = skillId;
        o.nOption = amount;
        o.rOption = COMBO_TRAINING;
        o.tOption = 10;
        tsm.putCharacterStatValue(RWCombination, o);
        tsm.sendSetStatPacket();
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }



    // Skill related methods -------------------------------------------------------------------------------------------

    @Override
    public void handleSkill(Client c, int skillID, byte slv, InPacket inPacket) {
        super.handleSkill(c, skillID, slv, inPacket);
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Char chr = c.getChr();
        Skill skill = chr.getSkill(skillID);
        SkillInfo si = null;
        if(skill != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        chr.chatMessage(ChatType.Mob, "SkillID: " + skillID);
        if (isBuff(skillID)) {
            handleBuff(c, inPacket, skillID, slv);
        } else {
            Option o1 = new Option();
            switch(skillID) {
                case SECRET_ASSEMBLY:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = chr.getOrCreateFieldByCurrentInstanceType(o1.nValue);
                    chr.warp(toField);
                    break;
                case REVOLVING_CANNON_RELOAD:
                    reloadCylinder();
                    break;
                case REVOLVING_CANNON_3:
                case REVOLVING_CANNON_2:
                case REVOLVING_CANNON:
                    if(getAmmo() > 0) {
                        removeAmmo();
                    }
                    if(getGauge() < 6) {
                        addGauge();
                    }
                    //c.write(UserLocal.rwMultiChargeCancelRequest((byte)1, skillID));
                    break;
                case VITALITY_SHIELD:
                    resetBlastShield();
                    break;
                case HEROS_WILL_BLASTER:
                    tsm.removeAllDebuffs();
                    break;
            }
        }
    }



    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        Option o1 = new Option();
        if(chr.hasSkill(BLAST_SHIELD)) {
            SkillInfo si = SkillData.getSkillInfoById(BLAST_SHIELD);
            o.nOption = 1;
            o.rOption = BLAST_SHIELD;
            o.tOption = 3;
            tsm.putCharacterStatValue(RWBarrier, o);
            tsm.sendSetStatPacket();
        }
        super.handleHit(c, inPacket, hitInfo);
    }
}