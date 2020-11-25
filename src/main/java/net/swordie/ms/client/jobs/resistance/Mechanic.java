package net.swordie.ms.client.jobs.resistance;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.ForceAtomInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatBase;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.CField;
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.User;
import net.swordie.ms.connection.packet.UserRemote;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.Mechanic;
import static net.swordie.ms.enums.ForceAtomEnum.*;

/**
 * Created on 12/14/2017.
 */
public class Mechanic extends Citizen {

    public static final int MECH_VEHICLE = 1932016;

    public static final int SECRET_ASSEMBLY = 30001281;
    public static final int MECHANIC_DASH = 30001068;
    public static final int HIDDEN_PEACE = 30000227;

    public static final int HUMANOID_MECH = 35001002; //Mech Suit
    public static final int TANK_MECH = 35111003; //Tank Mech Suit

    public static final int MECHANIC_RAGE = 35101006; //Buff
    public static final int PERFECT_ARMOR = 35101007; //Buff (ON/OFF)
    public static final int OPEN_PORTAL_GX9 = 35101005; //Special Skill
    public static final int ROBO_LAUNCHER_RM7 = 35101012; //Summon
    public static final int HOMING_BEACON = 35101002;

    public static final int ROCK_N_SHOCK = 35111002; //Special Summon
    public static final int ROLL_OF_THE_DICE = 35111013; //Special Buff
    public static final int SUPPORT_UNIT_HEX = 35111008; //Summon
    public static final int ADV_HOMING_BEACON = 35110017;

    public static final int BOTS_N_TOTS = 35121009; //Special Summon
    public static final int BOTS_N_TOTS_SUB_SUMMON = 35121011; // Summon that spawn from the main BotsNtots
    public static final int MAPLE_WARRIOR_MECH = 35121007; //Buff
    public static final int ENHANCED_SUPPORT_UNIT = 35120002;
    public static final int HEROS_WILL_MECH = 35121008;
    public static final int HOMING_BEACON_RESEARCH = 35120017;
    public static final int ROLL_OF_THE_DICE_DD = 35120014; //Special Buff

    public static final int FOR_LIBERTY_MECH = 35121053;
    public static final int FULL_SPREAD = 35121055;
    public static final int DISTORTION_BOMB = 35121052;

    private int[] addedSkills = new int[] {
            SECRET_ASSEMBLY,
            MECHANIC_DASH,
            HIDDEN_PEACE,
    };

    private int[] buffs = new int[] {
            HUMANOID_MECH,
            TANK_MECH,

            MECHANIC_RAGE,
            PERFECT_ARMOR,
            ROLL_OF_THE_DICE,
            MAPLE_WARRIOR_MECH,
            ROLL_OF_THE_DICE_DD,

            SUPPORT_UNIT_HEX, //Summon
            ENHANCED_SUPPORT_UNIT,
            ROBO_LAUNCHER_RM7, //Summon
            ROCK_N_SHOCK, //Summon
            BOTS_N_TOTS, //Summon
            FULL_SPREAD, //Summon
            FOR_LIBERTY_MECH,
    };

    private int[] homingBeacon = new int[] {
            HOMING_BEACON,
            ADV_HOMING_BEACON,
            HOMING_BEACON_RESEARCH,
    };

    private ScheduledFuture botsNTotsTimer;
    private ScheduledFuture supportUnitTimer;
    private byte gateId = 0;

    public Mechanic(Char chr) {
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
        return JobConstants.isMechanic(id);
    }



    // Buff related methods --------------------------------------------------------------------------------------------

    public void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        TemporaryStatBase tsb = tsm.getTSBByTSIndex(TSIndex.RideVehicle);
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        Option o5 = new Option();
        Summon summon;
        Field field;
        switch (skillID) {
            case HUMANOID_MECH:
                o1.nOption = 0;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(Mechanic, o1);
                tsm.sendSetStatPacket();

                tsb.setNOption(MECH_VEHICLE);
                tsb.setROption(skillID+100);
                tsm.putCharacterStatValue(RideVehicle, tsb.getOption());
                break;
            case TANK_MECH:
                o1.nOption = 1;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(Mechanic, o1);
                tsm.sendSetStatPacket();

                tsb.setNOption(MECH_VEHICLE);
                tsb.setROption(skillID + 100);
                tsm.putCharacterStatValue(RideVehicle, tsb.getOption());
                break;
            case MECHANIC_RAGE:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case PERFECT_ARMOR:
                if(tsm.hasStatBySkillId(skillID)) {
                    tsm.removeStatsBySkill(skillID);
                } else {
                    o1.nOption = si.getValue(x, slv);
                    o1.rOption = skillID;
                    tsm.putCharacterStatValue(PowerGuard, o1);
                }
                break;
            case ROLL_OF_THE_DICE:
                int random = new Random().nextInt(6)+1;

                chr.write(User.effect(Effect.avatarOriented("Skill/"+ (skillID/10000) +".img/skill/"+ skillID +"/affected/" + random)));
                chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.avatarOriented("Skill/"+ (skillID/10000) +".img/skill/"+ skillID +"/affected/" + random)));

                if(random < 2) {
                    return;
                }

                o1.nOption = random;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);

                tsm.throwDice(random);
                tsm.putCharacterStatValue(Dice, o1);
                break;
            case ROLL_OF_THE_DICE_DD:
                random = new Random().nextInt(6)+1;
                int randomDD = new Random().nextInt(6)+1;

                chr.write(User.effect(Effect.avatarOriented("Skill/"+ (skillID/10000) +".img/skill/"+ skillID +"/affected/" + random)));
                chr.write(User.effect(Effect.avatarOriented("Skill/"+ (skillID/10000) +".img/skill/"+ skillID +"/specialAffected/" + randomDD)));
                chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.avatarOriented("Skill/"+ (skillID/10000) +".img/skill/"+ skillID +"/affected/" + random)));
                chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.avatarOriented("Skill/"+ (skillID/10000) +".img/skill/"+ skillID +"/specialAffected/" + randomDD)));

                if(random < 2 && randomDD < 2) {
                    return;
                }

                o1.nOption = (random * 10) + randomDD; // if rolled: 5 and 7, the DoubleDown nOption = 57
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);

                tsm.throwDice(random, randomDD);
                tsm.putCharacterStatValue(Dice, o1);
                break;
            case MAPLE_WARRIOR_MECH:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case ENHANCED_SUPPORT_UNIT:
                o2.nReason = skillID;
                o2.nValue = si.getValue(z, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = 80;
                tsm.putCharacterStatValue(IndieDamR, o2);
                // Fallthrough intended
            case SUPPORT_UNIT_HEX:

                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(false);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setAssistType(AssistType.None);
                summon.setAttackActive(false);
                field.spawnSummon(summon);

                if(supportUnitTimer != null && !supportUnitTimer.isDone()) {
                    supportUnitTimer.cancel(true);
                }
                applySupportUnitDebuffOnMob(skillID);
                break;
            case ROBO_LAUNCHER_RM7:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(true);
                summon.setMoveAbility(MoveAbility.Stop);
                field.spawnSummon(summon);
                break;
            case ROCK_N_SHOCK:      //TODO TeslaCoil
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(true);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setAssistType(AssistType.None);
                summon.setAttackActive(false);
                //field.spawnAddSummon(summon);

                break;
            case BOTS_N_TOTS:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(false);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setAssistType(AssistType.None);
                summon.setAttackActive(false);
                field.spawnSummon(summon);

                if(botsNTotsTimer != null && !botsNTotsTimer.isDone()) {
                    botsNTotsTimer.cancel(true);
                }
                botsNTotsTimer = EventManager.addEvent(() -> spawnBotsNTotsSubSummons(summon), 3, TimeUnit.SECONDS);
                break;
            case FOR_LIBERTY_MECH:
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
            case FULL_SPREAD:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(BombTime, o1);
                break;
        }
        tsm.sendSetStatPacket();
    }

    public boolean isBuff(int skillID) {
        return super.isBuff(skillID) || Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    public void healFromSupportUnit(Summon summon) {
        Char summonOwner = summon.getChr();
        if (summonOwner.hasSkill(ENHANCED_SUPPORT_UNIT) || summonOwner.hasSkill(SUPPORT_UNIT_HEX)) {
            SkillInfo si = SkillData.getSkillInfoById(SUPPORT_UNIT_HEX);
            byte slv = (byte) summonOwner.getSkill(SUPPORT_UNIT_HEX).getCurrentLevel();
            int healrate = si.getValue(hp, slv);
            c.getChr().heal((int) (c.getChr().getMaxHP() * ((double) healrate / 100)));
        }
    }

    private void spawnBotsNTotsSubSummons(Summon summon) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Position position = summon.getPosition();

        if((tsm.getOptByCTSAndSkill(IndieEmpty, BOTS_N_TOTS) != null) && chr.hasSkill(BOTS_N_TOTS)) {
            Skill skill = chr.getSkill(BOTS_N_TOTS);
            byte slv = (byte) skill.getCurrentLevel();
            Summon subSummon = Summon.getSummonBy(chr, BOTS_N_TOTS_SUB_SUMMON, slv);
            subSummon.setCurFoothold((short) chr.getField().findFootHoldBelow(position).getId());
            subSummon.setPosition(position);
            subSummon.setAttackActive(false);
            subSummon.setMoveAbility(MoveAbility.WalkRandom);

            chr.getField().spawnAddSummon(subSummon);

            botsNTotsTimer = EventManager.addEvent(() -> spawnBotsNTotsSubSummons(summon), 3, TimeUnit.SECONDS);
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
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case DISTORTION_BOMB: // TODO  AA 38's
                /*
                AffectedArea aa = AffectedArea.getAffectedArea(chr, attackInfo);
                aa.setMobOrigin((byte) 0);
                aa.setPosition(chr.getPosition());
                aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                aa.setFlip(!attackInfo.left);
                chr.getField().spawnAffectedArea(aa);
                */
                break;
        }

        super.handleAttack(c, attackInfo);
    }


    private void createHumanoidMechRocketForceAtom() { // Humanoid Rockets are spread around
        Field field = chr.getField();
        SkillInfo si = SkillData.getSkillInfoById((chr.hasSkill(ADV_HOMING_BEACON) ? ADV_HOMING_BEACON : HOMING_BEACON));
        byte slv = (byte) getHomingBeaconSkill().getCurrentLevel();
        Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
        if(!chr.isLeft()) {
            rect = rect.moveRight();
        }
        List<Mob> mobs = field.getMobsInRect(rect);
        if(mobs.size() <= 0) {
            return;
        }
        for(int i = 0; i < getHomingBeaconBulletCount(); i++) {
            Mob mob = Util.getRandomFromCollection(mobs);
            int inc = getHomingBeaconForceAtomEnum().getInc();
            int type = getHomingBeaconForceAtomEnum().getForceAtomType();
            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 30, 25,
                    0, 200 + (i * 2), (int) System.currentTimeMillis(), 1, 0,
                    new Position());
            field.broadcastPacket(CField.createForceAtom(false, 0, chr.getId(), type,
                    true, mob.getObjectId(), HOMING_BEACON, forceAtomInfo, rect, 90, 30,
                    mob.getPosition(), 0, mob.getPosition()));
        }

    }

    private void createTankMechRocketForceAtom() { // Tank Rockets are focused on 1 enemy
        Field field = chr.getField();
        SkillInfo si = SkillData.getSkillInfoById((chr.hasSkill(ADV_HOMING_BEACON) ? ADV_HOMING_BEACON : HOMING_BEACON));
        byte slv = (byte) getHomingBeaconSkill().getCurrentLevel();
        Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
        if(!chr.isLeft()) {
            rect = rect.moveRight();
        }
        if(field.getMobsInRect(rect).size() <= 0) {
            return;
        }
        Mob mob = Util.getRandomFromCollection(field.getMobsInRect(rect));
        if(field.getBossMobsInRect(rect).size() > 0) {
            mob = Util.getRandomFromCollection(field.getBossMobsInRect(rect));
        }


        for(int i = 0; i < getHomingBeaconBulletCount(); i++) {
            int inc = getHomingBeaconForceAtomEnum().getInc();
            int type = getHomingBeaconForceAtomEnum().getForceAtomType();
            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 30, 25,
                    0, 200, (int) System.currentTimeMillis(), 1, 0,
                    new Position());
            field.broadcastPacket(CField.createForceAtom(false, 0, chr.getId(), type,
                    true, mob.getObjectId(), HOMING_BEACON, forceAtomInfo, rect, 90, 30,
                    mob.getPosition(), 0, mob.getPosition()));
        }
    }

    private ForceAtomEnum getHomingBeaconForceAtomEnum() {
        switch (getHomingBeaconSkill().getSkillId()) {
            case ADV_HOMING_BEACON:
                return MECH_MEGA_ROCKET_1;
            case HOMING_BEACON_RESEARCH:
                return MECH_MEGA_ROCKET_2;
            case HOMING_BEACON:
            default:
                return MECH_ROCKET;
        }
    }

    private Skill getHomingBeaconSkill() {
        Skill skill = null;
        for(int skillId : homingBeacon) {
            if(chr.hasSkill(skillId)) {
                skill = chr.getSkill(skillId);
            }
        }

        return skill;
    }

    private int getHomingBeaconBulletCount() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int forceAtomCount = 0;
        for(int skillId : homingBeacon) {
            if(chr.hasSkill(skillId)) {
                Skill skill = chr.getSkill(skillId);
                SkillInfo si = SkillData.getSkillInfoById(skillId);
                byte slv = (byte) skill.getCurrentLevel();
                forceAtomCount += si.getValue(bulletCount, slv);
            }
        }
        if(tsm.getOptByCTSAndSkill(BombTime, FULL_SPREAD) != null) {
            forceAtomCount += chr.hasSkill(FULL_SPREAD) ? SkillData.getSkillInfoById(FULL_SPREAD).getValue(x, chr.getSkill(FULL_SPREAD).getCurrentLevel()) : 0;
        }
        return forceAtomCount;
    }

    private void applySupportUnitDebuffOnMob(int skillId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(!chr.hasSkill(SUPPORT_UNIT_HEX) || tsm.getOptByCTSAndSkill(IndieEmpty, skillId) == null) {
            return;
        }
        Skill skill = chr.getSkill(skillId);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        SkillInfo suhInfo = SkillData.getSkillInfoById(SUPPORT_UNIT_HEX);
        byte slv = (byte) skill.getCurrentLevel();

        Option o = new Option();
        Field field = chr.getField();
        for(Mob mob : field.getMobs()) {
            MobTemporaryStat mts = mob.getTemporaryStat();
            o.nOption = -suhInfo.getValue(w, chr.getSkill(SUPPORT_UNIT_HEX).getCurrentLevel()); // enhancement doesn't contain the debuff info
            o.rOption = skill.getSkillId();
            o.tOption = 6;
            mts.addStatOptionsAndBroadcast(MobStat.PDR, o);
            mts.addStatOptionsAndBroadcast(MobStat.MDR, o);
        }

        supportUnitTimer = EventManager.addEvent(() -> applySupportUnitDebuffOnMob(skillId), si.getValue(x, slv), TimeUnit.SECONDS);
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
                case SECRET_ASSEMBLY:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = chr.getOrCreateFieldByCurrentInstanceType(o1.nValue);
                    chr.warp(toField);
                    break;
                case OPEN_PORTAL_GX9:
                    Field field = chr.getField();
                    int duration = si.getValue(time, slv);
                    OpenGate openGate = new OpenGate(chr, chr.getPosition(), chr.getParty(), gateId, duration);
                    if (gateId == 0) {
                        gateId = 1;
                    } else if (gateId == 1) {
                        gateId = 0;
                    }
                    openGate.spawnOpenGate(field);
                    break;
                case HOMING_BEACON: //4
                case ADV_HOMING_BEACON: // 4thJob upgrade +5 -> 9
                    if(tsm.hasStat(Mechanic) && tsm.getOption(Mechanic).nOption <= 0) {
                        createHumanoidMechRocketForceAtom();
                    } else if (tsm.hasStat(Mechanic) && tsm.getOption(Mechanic).nOption == 1) {
                        createTankMechRocketForceAtom();
                    }
                    break;
                case HEROS_WILL_MECH:
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
