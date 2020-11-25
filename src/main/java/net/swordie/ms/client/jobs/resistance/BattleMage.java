package net.swordie.ms.client.jobs.resistance;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.runestones.RuneStone;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.Summoned;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.AssistType;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class BattleMage extends Citizen {

    public static final int SECRET_ASSEMBLY = 30001281;

    public static final int CONDEMNATION = 32001014; //Special Buff (ON/OFF)
    public static final int HASTY_AURA = 32001016; //Buff (Unlimited Duration)

    public static final int CONDEMNATION_I = 32100010; //Special Buff (ON/OFF)
    public static final int DRAINING_AURA = 32101009; //Buff (Unlimited Duration)
    public static final int STAFF_BOOST = 32101005; //Buff
    public static final int DARK_CHAIN = 32101001; //Special Attack (Stun Debuff)

    public static final int CONDEMNATION_II = 32110017; //Special Buff (ON/OFF)
    public static final int BLUE_AURA = 32111012; //Buff (Unlimited Duration
    public static final int DARK_SHOCK = 32111016; //Buff (ON/OFF)

    public static final int CONDEMNATION_III = 32120019; //Special Buff (ON/OFF)
    public static final int DARK_GENESIS = 32121004; //Special Attack (Stun Debuff) (Special Properties if on Cooldown)
    public static final int DARK_GENESIS_FA = 32121011; // Final Attack  attack if DarkGenesis is on CD
    public static final int DARK_AURA = 32121017; //Buff (Unlimited Duration)
    public static final int WEAKENING_AURA = 32121018; //Buff (Unlimited Duration)
    public static final int PARTY_SHIELD = 32121006;
    public static final int BATTLE_RAGE = 32121010; //Buff (ON/OFF)
    public static final int MAPLE_WARRIOR_BAM = 32121007; //Buff
    public static final int HEROS_WILL_BAM = 32121008;

    public static final int FOR_LIBERTY_BAM = 32121053;
    public static final int MASTER_OF_DEATH = 32121056;

    private int[] addedSkills = new int[]{
            SECRET_ASSEMBLY,
    };

    private int[] buffs = new int[]{
            CONDEMNATION,
            CONDEMNATION_I,
            CONDEMNATION_II,
            CONDEMNATION_III,
            HASTY_AURA,
            DRAINING_AURA,
            BLUE_AURA,
            DARK_AURA,
            DARK_SHOCK,
            WEAKENING_AURA,
            STAFF_BOOST,
            BATTLE_RAGE,
            MAPLE_WARRIOR_BAM,
            FOR_LIBERTY_BAM,
            MASTER_OF_DEATH,
    };

    private int[] auras = new int[] {
            HASTY_AURA,
            DRAINING_AURA,
            BLUE_AURA,
            DARK_AURA,
            WEAKENING_AURA,
    };

    private Summon death;
    private long drainAuraCD = Long.MIN_VALUE;
    private ScheduledFuture WeaknessAuraTimer;
    private long lastCondemnationAttack = Long.MIN_VALUE;
    private int hitCountBoss = 0;

    public BattleMage(Char chr) {
        super(chr);
        if (chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
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
        return JobConstants.isBattleMage(id);
    }



    // Buff related methods --------------------------------------------------------------------------------------------

    public void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        switch (skillID) {
            case CONDEMNATION:
            case CONDEMNATION_I:
            case CONDEMNATION_II:
            case CONDEMNATION_III:
                o1.nOption = 0;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(BMageDeath, o1);
                spawnDeath(skillID, slv);
                break;
            case STAFF_BOOST:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case HASTY_AURA:
                for(int aura : auras) {
                    tsm.removeStatsBySkill(aura);
                }

                o1.nReason = skillID;
                o1.nValue = si.getValue(indieSpeed, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = 0;
                tsm.putCharacterStatValue(IndieSpeed, o1);
                o2.nReason = skillID;
                o2.nValue = -1;//   si.getValue(indieBooster, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = 0;
                tsm.putCharacterStatValue(IndieBooster, o2);
                o3.nOption = 1;
                o3.rOption = skillID;
                o3.tOption = 0;
                tsm.putCharacterStatValue(BMageAura, o3);
                break;
            case DRAINING_AURA:
                for(int aura : auras) {
                    tsm.removeStatsBySkill(aura);
                }

                o3.nOption = 1;
                o3.rOption = skillID;
                o3.tOption = 0;
                tsm.putCharacterStatValue(BMageAura, o3);
                break;
            case BLUE_AURA:
                for(int aura : auras) {
                    tsm.removeStatsBySkill(aura);
                }

                o1.nOption = si.getValue(asrR, slv);
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(AsrR, o1);
                o2.nOption = si.getValue(terR, slv);
                o2.rOption = skillID;
                o2.tOption = 0;
                tsm.putCharacterStatValue(TerR, o2);
                o3.nOption = si.getValue(y, slv);
                o3.rOption = skillID;
                o3.tOption = 0;
                tsm.putCharacterStatValue(IgnoreMobDamR, o3);
                o3.nOption = 1;
                o3.rOption = skillID;
                o3.tOption = 0;
                tsm.putCharacterStatValue(BMageAura, o3);
                applyBlueAuraDispel(); //Hyper
                break;
            case DARK_AURA:
                for(int aura : auras) {
                    tsm.removeStatsBySkill(aura);
                }

                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o3.nOption = 1;
                o3.rOption = skillID;
                o3.tOption = 0;
                tsm.putCharacterStatValue(BMageAura, o3);
                break;
            case WEAKENING_AURA:
                for(int aura : auras) {
                    tsm.removeStatsBySkill(aura);
                }

                o3.nOption = 1;
                o3.rOption = skillID;
                o3.tOption = 0;
                tsm.putCharacterStatValue(BMageAura, o3);
                if (WeaknessAuraTimer != null && !WeaknessAuraTimer.isDone()) {
                    WeaknessAuraTimer.cancel(true);
                }
                applyWeakenAuraOnMob();
                break;
            case DARK_SHOCK:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(DarkLighting, o1);
                break;
            case BATTLE_RAGE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(Enrage, o1);
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = 0;
                tsm.putCharacterStatValue(IndieDamR, o2);
                o3.nOption = si.getValue(z, slv);
                o3.rOption = skillID;
                o3.tOption = 0;
                tsm.putCharacterStatValue(IndieCr, o3);
                o4.nOption = si.getValue(y, slv);
                o4.rOption = skillID;
                o4.tOption = 0;
                tsm.putCharacterStatValue(EnrageCrDam, o4);
                break;
            case MAPLE_WARRIOR_BAM:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case FOR_LIBERTY_BAM:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                /*o2.nReason = skillID;
                o2.nValue = si.getValue(indieMaxDamageOverR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMaxDamageOverR, o2);*/
                break;
            case MASTER_OF_DEATH:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AttackCountX, o1);
                break;
        }
        tsm.sendSetStatPacket();
    }

    public boolean isBuff(int skillID) {
        return super.isBuff(skillID) || Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    public void spawnDeath(int skillID, byte slv) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        Field field = c.getChr().getField();
        death = Summon.getSummonBy(c.getChr(), skillID, slv);
        death.setFlyMob(true);
        death.setSummonTerm(0);
        death.setMoveAbility(MoveAbility.Walk);
        death.setAssistType(AssistType.AttackManual);
        death.setAttackActive(false);
        death.setBeforeFirstAttack(false);
        field.spawnSummon(death);

        o1.nReason = skillID;
        o1.nValue = 1;
        o1.summon = death;
        o1.tStart = (int) System.currentTimeMillis();
        o1.tTerm = 0; // #time is used for something else
        tsm.putCharacterStatValue(IndieEmpty, o1);
        tsm.sendSetStatPacket();
    }

    public void removeCondemnationBuff(Summon summon) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(summon != null) {
            if (tsm.hasStat(BMageDeath)) {
                tsm.removeStatsBySkill(summon.getSkillID());
                tsm.sendResetStatPacket();
            }
        }
    }

    public void applyBlueAuraDispel() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(BLUE_AURA);
        if (chr.hasSkill(32120062)) { //Blue Aura - Dispel Magic
            if (tsm.getOptByCTSAndSkill(BMageAura, skill.getSkillId()) != null) {
                tsm.removeAllDebuffs();
                EventManager.addEvent(this::applyBlueAuraDispel, 5, TimeUnit.SECONDS);
            }
        }
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
        byte slv = 0;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skill.getSkillId());
            slv = (byte) skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }
        if (hasHitMobs) {
            if(attackInfo.skillId != CONDEMNATION
                    && attackInfo.skillId != CONDEMNATION_I
                    && attackInfo.skillId != CONDEMNATION_II
                    && attackInfo.skillId != CONDEMNATION_III
                    && attackInfo.skillId != RuneStone.LIBERATE_THE_RUNE_OF_THUNDER_2) {
                incrementCondemnation(attackInfo);
            }
            drainAuraActiveHPRecovery(attackInfo);
            drainAuraPassiveHPRecovery(attackInfo);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case DARK_CHAIN:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    //if (Util.succeedProp(si.getValue(hcProp, slv))) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    if(!mob.isBoss()) {
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                    //}
                }
                break;
            case DARK_GENESIS:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    if(!mob.isBoss()) {
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    private void incrementCondemnation(AttackInfo attackInfo) {
        Field field = chr.getField();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();

        if (!tsm.hasStat(BMageDeath)) {
            return;
        }
        int killCount = tsm.getOption(BMageDeath).nOption;
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) field.getLifeByObjectID(mai.mobId);
            long dmgOnMob = Arrays.stream(mai.damages).sum();
            if (mob == null) {
                continue;
            }
            if (mob.isBoss()) {
                if(hitCountBoss < 1) {
                    hitCountBoss++;
                } else {
                    hitCountBoss = 0;
                    if(killCount < getCondemnationKillReq()) {
                        killCount++;
                    } else {
                        killCount = doCondemnationAttack(killCount);
                    }
                }
            } else {
                if(mob.getHp() <= dmgOnMob) {
                    if(killCount < getCondemnationKillReq()) {
                        killCount++;
                    } else {
                        killCount = doCondemnationAttack(killCount);
                    }

                }
            }
        }
        setCondemnationCount(killCount);
    }

    private void setCondemnationCount(int killCount) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = killCount;
        o.rOption = getCondemnationSkill().getSkillId();
        tsm.putCharacterStatValue(BMageDeath, o);
        tsm.sendSetStatPacket();
    }

    private int doCondemnationAttack(int killCount) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(lastCondemnationAttack + (getCondemnationCooldown() * 1000) < System.currentTimeMillis()) {
            death = tsm.getOption(IndieEmpty).summon;
            chr.write(Summoned.summonedAssistAttackRequest(death));
            killCount = 0;

            lastCondemnationAttack = System.currentTimeMillis();
        }
        return killCount;
    }

    private int getCondemnationCooldown() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = getCondemnationSkill();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());

        // Master of Death Buff
        if(tsm.getOptByCTSAndSkill(AttackCountX, MASTER_OF_DEATH) != null) {
            return 0;
        }

        return si.getValue(time, 1);
    }

    private int getCondemnationKillReq() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = getCondemnationSkill();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());

        // Master of Death Buff
        if(tsm.getOptByCTSAndSkill(AttackCountX, MASTER_OF_DEATH) != null) {
            return 1;
        }

        return si.getValue(x, 1);
    }

    private Skill getCondemnationSkill() {
        Skill skill = null;

        if (chr.getJob() == JobConstants.JobEnum.BATTLE_MAGE_1.getJobId()) {
            skill = chr.getSkill(CONDEMNATION);
        }
        if (chr.getJob() == JobConstants.JobEnum.BATTLE_MAGE_2.getJobId()) {
            skill = chr.getSkill(CONDEMNATION_I);
        }
        if (chr.getJob() == JobConstants.JobEnum.BATTLE_MAGE_3.getJobId()) {
            skill = chr.getSkill(CONDEMNATION_II);
        }
        if (chr.getJob() == JobConstants.JobEnum.BATTLE_MAGE_4.getJobId()) {
            skill = chr.getSkill(CONDEMNATION_III);
        }
        return skill;
    }


    private void drainAuraActiveHPRecovery(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(DRAINING_AURA);
        if (skill == null) {
            return;
        }
        byte slv = (byte) skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int duration = 1000 * si.getValue(subTime, slv);
        if ((tsm.getOptByCTSAndSkill(BMageAura, DRAINING_AURA) != null) && (drainAuraCD + duration < System.currentTimeMillis())) {
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                drainAuraCD = System.currentTimeMillis();
                long totaldmg = Arrays.stream(mai.damages).sum();
                int healingrate = si.getValue(x, slv);
                int restoration = (int) (totaldmg / ((double) 100 / healingrate));
                chr.heal(restoration);
            }
        }
    }

    private void drainAuraPassiveHPRecovery(AttackInfo attackInfo) {
        Skill skill = chr.getSkill(DRAINING_AURA);
        if (skill == null) {
            return;
        }
        byte slv = (byte) skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
            if (mob == null) {
                continue;
            }
            long totaldmg = Arrays.stream(mai.damages).sum();
            if(mob == null) {
                return;
            }
            if (totaldmg >= mob.getHp()) {
                int maxHP = chr.getMaxHP();
                int healingrate = si.getValue(x, slv);
                int restoration = (int) (maxHP / ((double) 100 / healingrate));
                chr.heal(restoration);
            }
        }
    }

    public void applyWeakenAuraOnMob() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(WEAKENING_AURA);
        byte slv = (byte) skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        Option o = new Option();
        int delay = si.getValue(y, slv);
        if (tsm.getOptByCTSAndSkill(BMageAura, skill.getSkillId()) != null) {
            Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
            if(!chr.isLeft()) {
                rect = rect.moveRight();
            }
            Field field = chr.getField();
            List<Mob> mobs = field.getMobsInRect(rect);
            for (Mob mob : mobs) {
                MobTemporaryStat mts = mob.getTemporaryStat();
                o.nOption = -si.getValue(x, slv);
                o.rOption = skill.getSkillId();
                o.tOption = si.getValue(time, slv);
                mts.addStatOptionsAndBroadcast(MobStat.PDR, o);
            }
            WeaknessAuraTimer = EventManager.addEvent(this::applyWeakenAuraOnMob, delay, TimeUnit.SECONDS);
        }
    }

    @Override
    public int getFinalAttackSkill() {
        SkillInfo si = SkillData.getSkillInfoById(DARK_GENESIS_FA);
        if(chr.getSkill(DARK_GENESIS) != null) {
            byte slv = (byte) chr.getSkill(DARK_GENESIS).getCurrentLevel();
            if (Util.succeedProp(si.getValue(prop, slv))) {
                return DARK_GENESIS_FA;
            }
        }
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
        if (skill != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        chr.chatMessage(ChatType.Mob, "SkillID: " + skillID);
        if (isBuff(skillID)) {
            handleBuff(c, inPacket, skillID, slv);
        } else {
            Option o1 = new Option();
            Option o2 = new Option();
            Option o3 = new Option();
            switch (skillID) {
                case PARTY_SHIELD:
                    AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                    aa.setMobOrigin((byte) 0);
                    aa.setPosition(chr.getPosition());
                    aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                    aa.setDelay((short) 16);
                    chr.getField().spawnAffectedArea(aa);

                    break;
                case SECRET_ASSEMBLY:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = chr.getOrCreateFieldByCurrentInstanceType(o1.nValue);
                    chr.warp(toField);
                    break;
                case HEROS_WILL_BAM:
                    tsm.removeAllDebuffs();
                    break;
            }
        }
    }



    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        super.handleHit(c, inPacket, hitInfo);
    }
}