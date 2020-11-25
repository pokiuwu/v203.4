package net.swordie.ms.client.jobs.cygnus;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.Arrays;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class DawnWarrior extends Noblesse {

    public static final int IMPERIAL_RECALL = 10001245;
    public static final int ELEMENTAL_EXPERT = 10000250;
    public static final int ELEMENTAL_SLASH = 10001244;
    public static final int NOBLE_MIND = 10000202;
    public static final int ELEMENTAL_SHIFT = 10001254;
    public static final int ELEMENTAL_SHIFT2 = 10000252;
    public static final int ELEMENTAL_HARMONY_STR = 10000246;

    public static final int SOUL_ELEMENT = 11001022; //Buff  (Immobility Debuff)

    public static final int SOUL_SPEED = 11101024; //Buff
    public static final int FALLING_MOON = 11101022; //Buff (Unlimited Duration)

    public static final int RISING_SUN = 11111022; //Buff (Unlimited Duration)
    public static final int TRUE_SIGHT = 11111023; //Buff (Mob Def Debuff & Final DmgUp Debuff)
    public static final int WILL_OF_STEEL = 11110025;

    public static final int EQUINOX_CYCLE = 11121005; //Buff
    public static final int EQUINOX_CYCLE_MOON = 11121011;
    public static final int EQUINOX_CYCLE_SUN = 11121012;
    public static final int IMPALING_RAYS = 11121004; //Special Attack (Incapacitate Debuff)
    public static final int IMPALING_RAYS_EXPLOSION = 11121013;
    public static final int CALL_OF_CYGNUS_DW = 11121000; //Buff
    public static final int MASTER_OF_THE_SWORD = 11120009;

    public static final int SOUL_FORGE = 11121054;
    public static final int GLORY_OF_THE_GUARDIANS_DW = 11121053;


    private int[] addedSkills = new int[] {
            ELEMENTAL_HARMONY_STR,
            IMPERIAL_RECALL,
            ELEMENTAL_EXPERT,
            ELEMENTAL_SLASH,
            NOBLE_MIND,
            ELEMENTAL_SHIFT,
            ELEMENTAL_SHIFT2
    };

    private int[] buffs = new int[] {
            SOUL_ELEMENT,
            SOUL_SPEED,
            FALLING_MOON,
            RISING_SUN,
            EQUINOX_CYCLE,
            CALL_OF_CYGNUS_DW,
            SOUL_FORGE,
            GLORY_OF_THE_GUARDIANS_DW,
    };

    private ScheduledFuture willOfSteelTimer;

    public DawnWarrior(Char chr) {
        super(chr);
        if(chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
            if(willOfSteelTimer != null && !willOfSteelTimer.isDone()) {
                willOfSteelTimer.cancel(true);
            }
            //willOfSteel();
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isDawnWarrior(id);
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
        Option o5 = new Option();
        Summon summon;
        Field field;
        switch (skillID) {
            case SOUL_ELEMENT:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(SoulMasterFinal, o1);
                break;
            case SOUL_SPEED:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case FALLING_MOON:
                SkillInfo mosSI = SkillData.getSkillInfoById(MASTER_OF_THE_SWORD);
                if(tsm.getOption(PoseType).nOption != 1) {
                    tsm.removeStatsBySkill(RISING_SUN);
                    tsm.sendResetStatPacket();
                }
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(PoseType, o1);
                o2.nOption = chr.hasSkill(MASTER_OF_THE_SWORD) ? mosSI.getValue(indieCr, slv) : si.getValue(indieCr, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(HayatoCr, o2);
                o3.nOption = 1;
                o3.rOption = skillID;
                o3.tOption = 0;
                tsm.putCharacterStatValue(BuckShot, o3);
                break;
            case RISING_SUN:
                mosSI = SkillData.getSkillInfoById(MASTER_OF_THE_SWORD);
                if(tsm.getOption(PoseType).nOption != 2) {
                    tsm.removeStatsBySkill(FALLING_MOON);
                    tsm.sendResetStatPacket();
                }
                o1.nOption = 2;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(PoseType, o1);
                o2.nReason = skillID;
                o2.nValue = chr.hasSkill(MASTER_OF_THE_SWORD) ? mosSI.getValue(v, slv) : si.getValue(indieDamR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = 0;
                tsm.putCharacterStatValue(IndieDamR, o2); //Indie
                o3.nOption = chr.hasSkill(MASTER_OF_THE_SWORD) ? -2 : si.getValue(indieBooster, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(HayatoBooster, o3);
                break;
            case EQUINOX_CYCLE:
                o5.nOption = 1;
                o5.rOption = skillID;
                o5.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(GlimmeringTime, o5);
                break;
            case CALL_OF_CYGNUS_DW:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;
            case SOUL_FORGE:    //IndieMaxDamageOver is still causing some problems
                o1.nReason = skillID;
                o1.nValue = si.getValue(indiePad, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1);
                o2.nReason = skillID;
                o2.nValue = 1;//   si.getValue(indieMaxDamageOver, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMaxDamageOverR, o2);
                o3.nOption = 1;
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(LightOfSpirit, o3);
                break;
            case GLORY_OF_THE_GUARDIANS_DW:
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
        }
        tsm.sendSetStatPacket();
    }

    public boolean isBuff(int skillID) {
        return super.isBuff(skillID) || Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    private void willOfSteel() { //TODO needs to be called
        if(chr.hasSkill(WILL_OF_STEEL)) {
            Skill skill = chr.getSkill(WILL_OF_STEEL);
            byte slv = (byte) skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int interval = si.getValue(w, slv);
            int heal = (int) (chr.getMaxHP() / ((double) 100 / si.getValue(y, slv)));

            if ((chr.getMaxHP() - chr.getHP()) == 0) {
                return;
            }
            chr.heal(heal);
        }
        willOfSteelTimer = EventManager.addEvent(() -> willOfSteel(), 4, TimeUnit.SECONDS);
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
        if(tsm.hasStat(SoulMasterFinal)) {
            applySoulElementOnMob(attackInfo, slv);
        }
        if(chr.hasSkill(RISING_SUN) && chr.hasSkill(FALLING_MOON))
        {
            equinox(tsm);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case IMPALING_RAYS:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Freeze, o1);
                        o2.nOption = 1;
                        o2.rOption = skill.getSkillId();
                        o2.wOption = chr.getId();
                        mts.addStatOptionsAndBroadcast(MobStat.SoulExplosion, o2);
                    }
                }
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    private void equinox(TemporaryStatManager tsm ) {
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        Option o5 = new Option();
        SkillInfo mosSI = SkillData.getSkillInfoById(MASTER_OF_THE_SWORD);
        //Rising Sun Skill Info
            Skill skillRS = chr.getSkill(RISING_SUN);
            byte slvRS = (byte) skillRS.getCurrentLevel();
            SkillInfo siRS = SkillData.getSkillInfoById(skillRS.getSkillId());
        //Falling Moon Skill Info
            Skill skillFM = chr.getSkill(FALLING_MOON);
            byte slvFM = (byte) skillFM.getCurrentLevel();
            SkillInfo siFM = SkillData.getSkillInfoById(skillFM.getSkillId());


        if(tsm.hasStat(GlimmeringTime)) {
            if(tsm.getOption(PoseType).nOption == 1) {
                //Switch to Rising Sun State
                o1.nOption = 2;
                o1.rOption = RISING_SUN;
                o1.tOption = 0;
                o1.bOption = 1;
                tsm.putCharacterStatValue(PoseType, o1);

                tsm.removeStatsBySkill(FALLING_MOON);
                tsm.removeStatsBySkill(EQUINOX_CYCLE_SUN);

                    o2.nReason = RISING_SUN;
                    o2.nValue = chr.hasSkill(MASTER_OF_THE_SWORD) ? mosSI.getValue(v, slvRS) : siRS.getValue(indieDamR, slvRS);
                    o2.tStart = (int) System.currentTimeMillis();
                    o2.tTerm = 0;
                    tsm.putCharacterStatValue(IndieDamR, o2); //Indie

                    o3.nOption = chr.hasSkill(MASTER_OF_THE_SWORD) ? -2 : siRS.getValue(indieBooster, slvRS);
                    o3.rOption = RISING_SUN;
                    o3.tOption = siRS.getValue(time, slvRS);
                    tsm.putCharacterStatValue(HayatoBooster, o3);

                //Invisible Moon Buffs
                o4.nOption = 1;
                o4.rOption = EQUINOX_CYCLE_MOON;
                tsm.putCharacterStatValue(BuckShot, o4);

                o5.nOption = chr.hasSkill(MASTER_OF_THE_SWORD) ? mosSI.getValue(indieCr, slvFM) : siFM.getValue(indieCr, slvFM);
                o5.rOption = EQUINOX_CYCLE_MOON;
                tsm.putCharacterStatValue(HayatoCr, o5);
            } else {
                //Switch to Falling Moon State
                o1.nOption = 1;
                o1.rOption = FALLING_MOON;
                o1.tOption = 0;
                o1.bOption = 1;
                tsm.putCharacterStatValue(PoseType, o1);

                tsm.removeStatsBySkill(RISING_SUN);
                tsm.removeStatsBySkill(EQUINOX_CYCLE_MOON);

                o2.nOption = chr.hasSkill(MASTER_OF_THE_SWORD) ? mosSI.getValue(indieCr, slvFM) : siFM.getValue(indieCr, slvFM);
                o2.rOption = FALLING_MOON;
                o2.tOption = siFM.getValue(time, slvFM);
                tsm.putCharacterStatValue(HayatoCr, o2);

                o3.nOption = 1;
                o3.rOption = FALLING_MOON;
                o3.tOption = 0;
                tsm.putCharacterStatValue(BuckShot, o3);

                //Invisible Sun Buffs
                o4.nReason = EQUINOX_CYCLE_SUN;
                o4.nValue = chr.hasSkill(MASTER_OF_THE_SWORD) ? mosSI.getValue(v, slvRS) : siRS.getValue(indieDamR, slvRS);
                o4.tStart = (int) System.currentTimeMillis();
                o4.tTerm = 0;
                tsm.putCharacterStatValue(IndieDamR, o4);

                o5.nOption = chr.hasSkill(MASTER_OF_THE_SWORD) ? -2 : siRS.getValue(indieBooster, slvRS);
                o5.rOption = EQUINOX_CYCLE_SUN;
                o5.tOption = siRS.getValue(time, slvRS);
                tsm.putCharacterStatValue(HayatoBooster, o5);
            }
            tsm.sendSetStatPacket();
        } else {
            if(tsm.getOptByCTSAndSkill(BuckShot, EQUINOX_CYCLE_MOON) != null) {
                tsm.removeStatsBySkill(EQUINOX_CYCLE_MOON);
            }
            if(tsm.getOptByCTSAndSkill(HayatoBooster, EQUINOX_CYCLE_SUN) != null) {
                tsm.removeStatsBySkill(EQUINOX_CYCLE_SUN);
            }
        }
    }

    private void applySoulElementOnMob(AttackInfo attackInfo, byte slv) {
        Option o1 = new Option();
        SkillInfo si = SkillData.getSkillInfoById(SOUL_ELEMENT);
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            if (Util.succeedProp(si.getValue(prop, slv))) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                MobTemporaryStat mts = mob.getTemporaryStat();
                if(!mob.isBoss()) {
                    o1.nOption = 1;
                    o1.rOption = SOUL_ELEMENT;
                    o1.tOption = si.getValue(subTime, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }
            }
        }
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }



    // Skill related methods -------------------------------------------------------------------------------------------

    @Override
    public void handleSkill(Client c, int skillID, byte slv, InPacket inPacket) {
        super.handleSkill(c, skillID, slv, inPacket);
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
            Option o2 = new Option();
            Option o3 = new Option();
            switch(skillID) {
                case TRUE_SIGHT:
                    // Mob Def = v
                    // Final Dmg on mob = s
                    Rect rect = new Rect(inPacket.decodeShort(), inPacket.decodeShort()
                            , inPacket.decodeShort(), inPacket.decodeShort());

                    if(!chr.isLeft()) {
                        rect = rect.moveRight();
                    }
                    for(Life life : chr.getField().getLifesInRect(rect)) {
                        if(life instanceof Mob && ((Mob) life).getHp() > 0) {
                            Mob mob = (Mob) life;
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            if(Util.succeedProp(si.getValue(prop, slv))) {
                                o1.nOption = -(si.getValue(v, slv));
                                o1.rOption = skillID;
                                o1.tOption = si.getValue(time, slv);
                                mts.addStatOptionsAndBroadcast(MobStat.PDR, o1);
                                mts.addStatOptionsAndBroadcast(MobStat.MDR, o1);
                            }
                        }
                    }
                    break;
                case IMPERIAL_RECALL:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = chr.getOrCreateFieldByCurrentInstanceType(o1.nValue);
                    chr.warp(toField);
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
