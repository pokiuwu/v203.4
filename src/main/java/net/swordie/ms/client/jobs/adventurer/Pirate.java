package net.swordie.ms.client.jobs.adventurer;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.quest.QuestManager;
import net.swordie.ms.client.character.skills.GuidedBullet;
import net.swordie.ms.client.character.skills.*;
import net.swordie.ms.client.character.skills.PartyBooster;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatBase;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.User;
import net.swordie.ms.connection.packet.UserRemote;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.enums.TSIndex;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Pirate extends Beginner {
    public static final int MAPLE_RETURN = 1281;

    //Pirate
    public static final int DASH = 5001005; //Buff


    //Buccaneer
    public static final int TORNADO_UPPERCUT = 5101012; //Special Attack
    public static final int KNUCKLE_BOOSTER = 5101006; //Buff
    public static final int ENERGY_CHARGE = 5100015; //Energy Gauge

    public static final int ROLL_OF_THE_DICE_BUCC = 5111007; //Buff
    public static final int ENERGY_BURST = 5111002; //Special Attack
    public static final int STATIC_THUMPER = 5111012; //Special Attack
    public static final int STUN_MASTERY = 5110000;
    public static final int SUPERCHARGE = 5110014;

    public static final int OCTOPUNCH = 5121007; //Special Attack
    public static final int NAUTILUS_STRIKE_BUCC = 5121013; //Special Attack
    public static final int NAUTILUS_STRIKE_BUCC_FA = 5120021; // Final Attack
    public static final int DRAGON_STRIKE = 5121001; //Special Attack
    public static final int BUCCANEER_BLAST = 5121016; //Special Attack
    public static final int CROSSBONES = 5121015; //Buff
    public static final int SPEED_INFUSION = 5121009; //Buff
    public static final int TIME_LEAP = 5121010; //Special Move / Buff
    public static final int MAPLE_WARRIOR_BUCC = 5121000; //Buff
    public static final int PIRATE_REVENGE_BUCC = 5120011;
    public static final int ULTRA_CHARGE = 5120018;
    public static final int ROLL_OF_THE_DICE_BUCC_DD = 5120012;
    public static final int HEROS_WILL_BUCC = 5121008;


    //Corsair
    public static final int SCURVY_SUMMONS = 5201012; //Summon
    public static final int INFINITY_BLAST = 5201008; //Buff
    public static final int GUN_BOOSTER = 5201003; //Buff

    public static final int ALL_ABOARD = 5210015; //Summon
    public static final int ROLL_OF_THE_DICE_SAIR = 5211007; //Buff
    public static final int OCTO_CANNON = 5211014; //Summon

    public static final int QUICKDRAW = 5221021; //Buff
    public static final int PARROTARGETTING = 5221015; //Special Attack
    public static final int NAUTILUS_STRIKE_SAIR = 5221013; //Special Attack
    public static final int MAPLE_WARRIOR_SAIR = 5221000; //Buff
    public static final int JOLLY_ROGER = 5221018; //Buff
    public static final int PIRATE_REVENGE_SAIR = 5220012;
    public static final int ROLL_OF_THE_DICE_SAIR_DD = 5220014;
    public static final int HEROS_WILL_SAIR = 5221010;
    public static final int MAJESTIC_PRESENCE = 5220020;
    public static final int AHOY_MATEYS = 5220019;


    //Cannoneer
    public static final int BLAST_BACK = 5011002; //Special Attack

    public static final int MONKEY_MAGIC = 5301003; //Buff
    public static final int CANNON_BOOSTER = 5301002; //Buff

    public static final int MONKEY_WAVE = 5311002; //Special Attack
    public static final int BARREL_ROULETTE = 5311004; //Buff
    public static final int LUCK_OF_THE_DIE = 5311005; //Buff

    public static final int LUCK_OF_THE_DIE_DD = 5320007;
    public static final int ANCHOR_AWEIGH = 5321003; //Summon
    public static final int MONKEY_MALITIA = 5321004; //Summon
    public static final int NAUTILUS_STRIKE_CANNON = 5321001; //Special Attack / Buff
    public static final int PIRATE_SPIRIT = 5321010; //Buff
    public static final int MAPLE_WARRIOR_CANNON = 5321005; //Buff
    public static final int HEROS_WILL_CANNON = 5321006;
    public static final int MEGA_MONKEY_MAGIC = 5320008;


    //Hyper
    public static final int EPIC_ADVENTURER_BUCC = 5121053;
    public static final int EPIC_ADVENTURER_SAIR = 5221053;
    public static final int EPIC_ADVENTURER_CANNON = 5321053;

    public static final int STIMULATING_CONVERSATION = 5121054;
    public static final int WHALERS_POTION = 5221054;
    public static final int BUCKSHOT = 5321054;
    public static final int BIONIC_MAXIMIZER = 5721054;

    public static final int ROLLING_RAINBOW = 5321052;
    public static final int POWER_UNITY = 5121052;

    private int[] addedSkills = new int[] {
            MAPLE_RETURN,
    };

    private int[] buffs = new int[]{
            DASH,
            KNUCKLE_BOOSTER,
            ROLL_OF_THE_DICE_BUCC,
            ROLL_OF_THE_DICE_BUCC_DD,
            CROSSBONES,
            SPEED_INFUSION,
            TIME_LEAP,
            MAPLE_WARRIOR_BUCC,

            SCURVY_SUMMONS,
            ALL_ABOARD,
            INFINITY_BLAST,
            GUN_BOOSTER,
            ROLL_OF_THE_DICE_SAIR,
            ROLL_OF_THE_DICE_SAIR_DD,
            OCTO_CANNON,
            QUICKDRAW,
            PARROTARGETTING,
            MAPLE_WARRIOR_SAIR,
            JOLLY_ROGER,

            MONKEY_MAGIC,
            CANNON_BOOSTER,
            BARREL_ROULETTE, //TODO
            LUCK_OF_THE_DIE,
            LUCK_OF_THE_DIE_DD,
            ANCHOR_AWEIGH,
            MONKEY_MALITIA,
            PIRATE_SPIRIT,
            MAPLE_WARRIOR_CANNON,
            MEGA_MONKEY_MAGIC,

            //Hyper
            EPIC_ADVENTURER_BUCC,
            EPIC_ADVENTURER_CANNON,
            EPIC_ADVENTURER_SAIR,
            STIMULATING_CONVERSATION,
            WHALERS_POTION,
            BUCKSHOT,
            BIONIC_MAXIMIZER,
            ROLLING_RAINBOW,
    };

    private int corsairSummonID = 0;
    private ScheduledFuture stimulatingConversationTimer;


    public Pirate(Char chr) {
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
        return JobConstants.isAdventurerPirate(id);
    }



    // Buff related methods --------------------------------------------------------------------------------------------

    public void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();

        Summon summon;
        Field field;
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        Option o5 = new Option();
        Option o6 = new Option();
        Option o7 = new Option();
        switch (skillID) {
            case DASH:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Speed, o1);
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Jump, o2);
                break;
            case KNUCKLE_BOOSTER:
            case GUN_BOOSTER:
            case CANNON_BOOSTER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case TIME_LEAP:
                long nextAvailableTime = System.currentTimeMillis() + (si.getValue(time, slv)*1000);
                chr.getScriptManager().createQuestWithQRValue(chr, GameConstants.TIME_LEAP_QR_KEY, String.valueOf(nextAvailableTime));
                if (chr.getQuestManager().getQuestById(GameConstants.TIME_LEAP_QR_KEY).getQRValue() == null
                        || Long.parseLong(chr.getQuestManager().getQuestById(GameConstants.TIME_LEAP_QR_KEY).getQRValue()) < System.currentTimeMillis()) {
                    for (int skillId : chr.getSkillCoolTimes().keySet()) {
                        if (!SkillData.getSkillInfoById(skillId).isNotCooltimeReset() && SkillData.getSkillInfoById(skillId).getHyper() == 0) {
                            chr.resetSkillCoolTime(skillId);
                        }
                    }
                }
                break;
            case SPEED_INFUSION:
                PartyBooster pb = (PartyBooster) tsm.getTSBByTSIndex(TSIndex.PartyBooster);
                pb.setNOption(-1);
                pb.setROption(skillID);
                pb.setCurrentTime((int) System.currentTimeMillis());
                pb.setExpireTerm(1);
                tsm.putCharacterStatValue(PartyBooster, pb.getOption());
                break;
            case INFINITY_BLAST:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NoBulletConsume, o1);
                break;
            case MAPLE_WARRIOR_BUCC:
            case MAPLE_WARRIOR_CANNON:
            case MAPLE_WARRIOR_SAIR:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case ROLL_OF_THE_DICE_BUCC:
            case ROLL_OF_THE_DICE_SAIR:
            case LUCK_OF_THE_DIE:
                int upbound = 6;
                if((chr.hasSkill(ROLL_OF_THE_DICE_BUCC_DD) && chr.hasSkill(5120044)) ||
                        (chr.hasSkill(ROLL_OF_THE_DICE_SAIR_DD) && chr.hasSkill(5220044))) {
                    upbound = 7;
                }
                int random = new Random().nextInt(upbound)+1;

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
                chr.reduceSkillCoolTime(NAUTILUS_STRIKE_SAIR, (long) (chr.getRemainingCoolTime(NAUTILUS_STRIKE_SAIR) * 0.5F));
                chr.reduceSkillCoolTime(NAUTILUS_STRIKE_CANNON, (long) (chr.getRemainingCoolTime(NAUTILUS_STRIKE_CANNON) * 0.5F));
                break;
            case ROLL_OF_THE_DICE_BUCC_DD:
            case ROLL_OF_THE_DICE_SAIR_DD:
            case LUCK_OF_THE_DIE_DD:
                boolean isCharged = tsm.getViperEnergyCharge() > 0;
                upbound = 6;
                if((chr.hasSkill(ROLL_OF_THE_DICE_BUCC_DD) && chr.hasSkill(5120044)) ||
                        (chr.hasSkill(ROLL_OF_THE_DICE_SAIR_DD) && chr.hasSkill(5220044))) {
                    upbound = 7;
                }

                random = new Random().nextInt(upbound)+1;
                int randomDD = new Random().nextInt(upbound)+1;

                chr.write(User.effect(Effect.skillAffectedSelect(skillID, slv, random, false)));
                chr.write(User.effect(Effect.skillAffectedSelect(skillID, slv, randomDD, true)));
                chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillAffectedSelect(skillID, slv, random, false)));
                chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillAffectedSelect(skillID, slv, randomDD, true)));

                if(random < 2 && randomDD < 2) {
                    return;
                }

                o1.nOption = (random * 10) + randomDD; // if rolled: 3 and 5, the DoubleDown nOption = 35
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);

                tsm.throwDice(random, randomDD);
                tsm.putCharacterStatValue(Dice, o1);
                if(isCharged) {
                    updateViperEnergy(tsm.getOption(EnergyCharged).nOption);
                }
                chr.reduceSkillCoolTime(NAUTILUS_STRIKE_SAIR, (long) (chr.getRemainingCoolTime(NAUTILUS_STRIKE_SAIR) * 0.5F));
                chr.reduceSkillCoolTime(NAUTILUS_STRIKE_CANNON, (long) (chr.getRemainingCoolTime(NAUTILUS_STRIKE_CANNON) * 0.5F));
                break;
            case MONKEY_MAGIC:
            case MEGA_MONKEY_MAGIC:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieAcc, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieACC, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieAllStat, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieAllStat, o2);
                o3.nReason = skillID;
                o3.nValue = si.getValue(indieEva, slv);
                o3.tStart = (int) System.currentTimeMillis();
                o3.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieEVA, o3);
                o4.nReason = skillID;
                o4.nValue = si.getValue(indieJump, slv);
                o4.tStart = (int) System.currentTimeMillis();
                o4.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieJump, o4);
                o5.nReason = skillID;
                o5.nValue = si.getValue(indieMhp, slv);
                o5.tStart = (int) System.currentTimeMillis();
                o5.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMHP, o5);
                o6.nReason = skillID;
                o6.nValue = si.getValue(indieMmp, slv);
                o6.tStart = (int) System.currentTimeMillis();
                o6.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMMP, o6);
                o7.nReason = skillID;
                o7.nValue = si.getValue(indieSpeed, slv);
                o7.tStart = (int) System.currentTimeMillis();
                o7.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieSpeed, o7);
                break;
            case BARREL_ROULETTE:
                int roulette = new Random().nextInt(4)+1;
                o1.nOption = roulette;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Roulette, o1);
                giveBarrelRouletteBuff(roulette);
                chr.reduceSkillCoolTime(NAUTILUS_STRIKE_CANNON, (long) (chr.getRemainingCoolTime(NAUTILUS_STRIKE_CANNON) * 0.5F));
                break;
            case QUICKDRAW:
                o1.nOption = 2;
                o1.rOption = skillID;
                o1.tOption = 10;
                tsm.putCharacterStatValue(QuickDraw, o1);
                o2.nOption = si.getValue(damR, slv);
                o2.rOption = skillID;
                o2.tOption = 10;
                tsm.putCharacterStatValue(DamR, o2);
                break;
            case PIRATE_SPIRIT:
                o1.nOption = si.getValue(prop, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Stance, o1);
                break;
            case JOLLY_ROGER:
                o1.nOption = si.getValue(eva, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EVA, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePadR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePADR, o2);
                o3.nOption = si.getValue(z, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Stance, o3);
                o4.nOption = si.getValue(x, slv);
                o4.rOption = skillID;
                o4.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AsrR, o4);
                o5.nOption = si.getValue(x, slv);
                o5.rOption = skillID;
                o5.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(TerR, o5);
                break;
            case CROSSBONES:
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePadR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePADR, o2);
                break;

                //Hyper
            case EPIC_ADVENTURER_BUCC:
            case EPIC_ADVENTURER_CANNON:
            case EPIC_ADVENTURER_SAIR:
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
            case STIMULATING_CONVERSATION:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Stimulate, o1);
                o2.nOption = si.getValue(indieDamR, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamR, o2);
                if(stimulatingConversationTimer != null && !stimulatingConversationTimer.isDone()) {
                    stimulatingConversationTimer.cancel(true);
                }
                incrementStimulatingConversation();
                break;
            case BIONIC_MAXIMIZER:
            case WHALERS_POTION:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieMhpR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMHPR, o1);
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AsrR, o2);
                tsm.putCharacterStatValue(TerR, o2);
                o3.nOption = si.getValue(w, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamageReduce, o3);
                break;

            case BUCKSHOT:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(BuckShot, o1);
                break;

            case ROLLING_RAINBOW: //Stationary, Attacks
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(false);
                summon.setMoveAction((byte) 0);
                summon.setMoveAbility(MoveAbility.Stop);
                field.spawnSummon(summon);
                break;
            case OCTO_CANNON: //Stationary, Attacks
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(false);
                summon.setMoveAction((byte) 0);
                summon.setMoveAbility(MoveAbility.Stop);
                field.spawnAddSummon(summon);
                break;
            case MONKEY_MALITIA: //Stationary, Attacks
                int[] summons = new int[] {
                        5320011,
                        5321004
                };
                for(int summonZ : summons) {
                    summon = Summon.getSummonBy(c.getChr(), summonZ, slv);
                    field = c.getChr().getField();
                    summon.setFlyMob(false);
                    summon.setMoveAction((byte) 0);
                    summon.setMoveAbility(MoveAbility.Stop);
                    field.spawnSummon(summon);
                }
                break;
            case ALL_ABOARD: //Moves, Attacks
                tsm.removeStatsBySkill(AHOY_MATEYS);
                corsairSummons();
                // Fallthrough intended
            case SCURVY_SUMMONS: //Moves, Attacks
                corsairSummons();
                chr.reduceSkillCoolTime(NAUTILUS_STRIKE_SAIR, (long) (chr.getRemainingCoolTime(NAUTILUS_STRIKE_SAIR) * 0.5F));
                break;
            case ANCHOR_AWEIGH: //Stationary, Pulls mobs
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(false);
                summon.setMoveAbility(MoveAbility.Stop);
                Position position = new Position(chr.isLeft() ? chr.getPosition().getX() - 250 : chr.getPosition().getX() + 250, chr.getPosition().getY());
                summon.setCurFoothold((short) chr.getField().findFootHoldBelow(position).getId());
                summon.setPosition(position);
                summon.setSummonTerm(20);
                field.spawnSummon(summon);
                break;
        }
        tsm.sendSetStatPacket();
    }

    public boolean isBuff(int skillID) {
        return super.isBuff(skillID) || Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    private void corsairSummons() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o3 = new Option();
        List<Integer> set = new ArrayList<>();
        if(chr.hasSkill(ALL_ABOARD)) {
            set.add(5210015);
            set.add(5210016);
            set.add(5210017);
            set.add(5210018);
        } else {
            set.add(5201012);
            set.add(5201013);
            set.add(5201014);
        }

        if(corsairSummonID != 0) {
            set.remove((Integer) corsairSummonID);
        }
        if(chr.hasSkill(AHOY_MATEYS)) {
            Skill skill = chr.getSkill(AHOY_MATEYS);
            byte slv = (byte) skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int random = Util.getRandomFromCollection(set);
            corsairSummonID = random;
            Summon summon = Summon.getSummonBy(chr, random, (byte) 1);
            Field field = chr.getField();
            summon.setFlyMob(false);
            summon.setMoveAbility(MoveAbility.WalkRandom);
            field.spawnSummon(summon);

            switch (random) {
                case 5210015:
                    o1.nOption = si.getValue(z, slv);
                    o1.rOption = skill.getSkillId();
                    o1.tOption = 120;
                    tsm.putCharacterStatValue(IncCriticalDamMin, o1);
                    tsm.putCharacterStatValue(IncCriticalDamMax, o1);
                    break;
                case 5210016:
                    o1.nOption = si.getValue(s, slv);
                    o1.rOption = skill.getSkillId();
                    o1.tOption = 120;
                    tsm.putCharacterStatValue(CriticalBuff, o1);
                    break;
                case 5210017:
                    o1.nOption = si.getValue(x, slv);
                    o1.rOption = skill.getSkillId();
                    o1.tOption = 120;
                    tsm.putCharacterStatValue(IndieMHPR, o1);
                    tsm.putCharacterStatValue(IndieMMPR, o1);
                    tsm.putCharacterStatValue(IndieSpeed, o1);
                    break;
                case 5210018:
                    o1.nOption = si.getValue(y, slv);
                    o1.rOption = skill.getSkillId();
                    o1.tOption = 120;
                    tsm.putCharacterStatValue(DamageReduce, o1);
                    break;
            }
            tsm.sendSetStatPacket();
        }
    }

    private void powerUnity() {
        if(!chr.hasSkill(POWER_UNITY)) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Skill skill = chr.getSkill(POWER_UNITY);
        byte slv = (byte) skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int amount = 1;
        if(tsm.hasStat(UnityOfPower)) {
            amount = tsm.getOption(UnityOfPower).nOption;
            if(amount < 4) {
                amount++;
            }
        }
        o1.nOption = amount;
        o1.rOption = skill.getSkillId();
        o1.tOption = si.getValue(time, slv);
        tsm.putCharacterStatValue(UnityOfPower, o1);
        tsm.sendSetStatPacket();
    }

    private void incrementStimulatingConversation() {
        if(!chr.hasSkill(STIMULATING_CONVERSATION)) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(Stimulate)) {
            Skill skill = chr.getSkill(STIMULATING_CONVERSATION);
            byte slv = (byte) skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            updateViperEnergy(tsm.getOption(EnergyCharged).nOption + si.getValue(x, slv));
            stimulatingConversationTimer = EventManager.addEvent(this::incrementStimulatingConversation, 4, TimeUnit.SECONDS);
        }
    }


    private void giveBarrelRouletteBuff(int roulette) {   //TODO
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        Skill skill = chr.getSkill(BARREL_ROULETTE);
        byte slv = (byte) skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        chr.write(User.effect(Effect.skillAffectedSelect(BARREL_ROULETTE, slv, roulette, false)));
        switch (roulette) {
            case 1: // Extra Attack (Final Attack)
                //Handled, See Final Attack Handler
                break;
            case 2: // Max CritDmg
                o.nOption = si.getValue(s, slv);
                o.rOption = 0;
                o.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(IncCriticalDamMin, o);
                tsm.sendSetStatPacket();
                break;
            case 3: // Slow Debuff
                //Handled, See Attack Handler
                break;
            case 4: // DoT
                //Handled, See Attack Handler
                break;
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
        if (JobConstants.isBuccaneer(chr.getJob())) {
            if(hasHitMobs && attackInfo.skillId != 0) {
                // Stun Mastery
                applyStunMasteryOnMob(attackInfo);

                // Viper Energy
                changeViperEnergy(attackInfo.skillId);
            }
        }

        if (JobConstants.isCorsair(chr.getJob())) {
            if(hasHitMobs) {
                // Quickdraw
                activateQuickdraw(attackInfo, tsm);
            }
        }

        if (JobConstants.isCannonShooter(chr.getJob())) {
            if(hasHitMobs) {
                // Barrel Roulette
                applyBarrelRouletteDebuffOnMob(attackInfo);

                // Monkey Wave Ignore KeyDown Time
                if(chr.hasSkill(MONKEY_WAVE)) {
                    Skill mwskill = chr.getSkill(MONKEY_WAVE);
                    SkillInfo mwsi = SkillData.getSkillInfoById(MONKEY_WAVE);
                    byte mwslv = (byte) mwskill.getCurrentLevel();
                    if (Util.succeedProp(mwsi.getValue(w, mwslv)) && !(tsm.getOption(KeyDownTimeIgnore).nOption > 0) && attackInfo.skillId != 5310008) {
                        o1.nOption = 1;
                        o1.rOption = 5310008;
                        o1.tOption = 15; // doesn't have an assigned skillStat
                        tsm.putCharacterStatValue(KeyDownTimeIgnore, o1);
                        tsm.sendSetStatPacket();
                    }
                }
            }
        }

        switch (attackInfo.skillId) {
            case BLAST_BACK:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = si.getValue(z, slv);
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                    }
                }
                break;
            case MONKEY_WAVE:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
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
                }
                if(tsm.hasStat(KeyDownTimeIgnore) && tsm.getOption(KeyDownTimeIgnore).nOption > 0) {
                    tsm.removeStatsBySkill(5310008);
                    tsm.removeStat(KeyDownTimeIgnore, true);
                    tsm.sendResetStatPacket();
                }
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(subTime, slv);
                tsm.putCharacterStatValue(IncCriticalDamMax, o2);
                tsm.sendSetStatPacket();
                break;
            case POWER_UNITY:
                powerUnity();
                break;
            case PARROTARGETTING:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {

                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = si.getValue(x, slv);
                    o1.rOption = skillID;
                    mts.addStatOptionsAndBroadcast(MobStat.AddDamParty, o1);

                    net.swordie.ms.client.character.skills.GuidedBullet gb = (GuidedBullet) tsm.getTSBByTSIndex(TSIndex.GuidedBullet);

                    if(gb.getMobID() != 0) {
                        Mob gbMob = (Mob) chr.getField().getLifeByObjectID(gb.getMobID());
                        if(gbMob != null) {
                            MobTemporaryStat mobTemporaryStat = gbMob.getTemporaryStat();
                            if(mobTemporaryStat.hasCurrentMobStatBySkillId(skillID)) {
                                mobTemporaryStat.removeMobStat(MobStat.AddDamParty, false);
                            }
                        }
                    }

                    gb.setNOption(1);
                    gb.setROption(skillID);
                    gb.setMobID(mai.mobId);
                    gb.setUserID(chr.getId());
                    tsm.putCharacterStatValue(GuidedBullet, gb.getOption());
                    tsm.sendSetStatPacket();
                }
                break;
            case DRAGON_STRIKE:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 10;
                    o1.rOption = skillID;
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.AddDamParty, o1);
                }
                break;
            case NAUTILUS_STRIKE_BUCC:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(VenomSnake, o1);
                tsm.sendSetStatPacket();
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    private void activateQuickdraw(AttackInfo attackInfo, TemporaryStatManager tsm) {
        Option o = new Option();
        boolean hasHitMobs = attackInfo.mobAttackInfo.size() > 0;
        SkillInfo quickdrawInfo = SkillData.getSkillInfoById(QUICKDRAW);
        if (tsm.getOption(QuickDraw).nOption == 2) {
            if(hasHitMobs) {
                tsm.removeStatsBySkill(QUICKDRAW);
            }
        } else {
            if (Util.succeedProp(quickdrawInfo.getValue(prop, quickdrawInfo.getCurrentLevel()))) {
                o.nOption = 1;
                o.rOption = QUICKDRAW;
                tsm.putCharacterStatValue(QuickDraw, o);
                tsm.sendSetStatPacket();
            }
        }
    }

    private void applyStunMasteryOnMob(AttackInfo attackInfo) {
        Option o1 = new Option();
        SkillInfo si = SkillData.getSkillInfoById(STUN_MASTERY);
        int slv = si.getCurrentLevel();
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            if (Util.succeedProp(si.getValue(subProp, slv))) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                if(!mob.isBoss()) {
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 1;
                    o1.rOption = STUN_MASTERY;
                    o1.tOption = 3;
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }
            }
        }
    }

    private void changeViperEnergy(int skillId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int energy = getEnergyIncrement();
        if(tsm.getViperEnergyCharge() == 0) {
            if (tsm.hasStat(EnergyCharged)) {
                energy = tsm.getOption(EnergyCharged).nOption;
                if (energy < getMaximumEnergy()) {
                    energy += getEnergyIncrement();
                }
                if (energy > getMaximumEnergy()) {
                    energy = getMaximumEnergy();
                }
            }
            chr.write(User.effect(Effect.skillAffected(getViperEnergySkill().getSkillId(), (byte) 1, 0)));
            chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillAffected(getViperEnergySkill().getSkillId(), (byte) 1, 0)));
        } else {
            energy = deductViperEnergyCost(skillId);
        }
        updateViperEnergy(energy);
    }

    private int deductViperEnergyCost(int skillId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int energy = tsm.getOption(EnergyCharged).nOption;
        SkillInfo si = SkillData.getSkillInfoById(skillId);
        energy = energy - si.getValue(forceCon, 1);

        return energy;
    }

    private void updateViperEnergy(int energy) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        TemporaryStatBase tsb = tsm.getTSBByTSIndex(TSIndex.EnergyCharged);
        tsb.setNOption(energy < 0 ? 0 : (energy > getMaximumEnergy() ? getMaximumEnergy() : energy));
        tsb.setROption(0);
        tsm.putCharacterStatValue(EnergyCharged, tsb.getOption());
        if(energy >= getMaximumEnergy()) {
            tsm.setViperEnergyCharge(getViperEnergySkill().getSkillId());
        } else if (energy <= 0) {
            tsm.setViperEnergyCharge(0);
        }
        tsm.sendSetStatPacket();
    }

    private int getEnergyIncrement() {
        Skill skill = getViperEnergySkill();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();

        return si.getValue(x, slv);
    }

    private int getMaximumEnergy() {
        Skill skill = getViperEnergySkill();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();

        return si.getValue(z, slv);
    }

    private Skill getViperEnergySkill() {
        Skill skill = null;
        if(chr.hasSkill(ENERGY_CHARGE)) {
            skill = chr.getSkill(ENERGY_CHARGE);
        }
        if(chr.hasSkill(SUPERCHARGE)) {
            skill = chr.getSkill(SUPERCHARGE);
        }
        if(chr.hasSkill(ULTRA_CHARGE)) {
            skill = chr.getSkill(ULTRA_CHARGE);
        }
        return skill;
    }

    private void applyBarrelRouletteDebuffOnMob(AttackInfo attackInfo) {
        if(chr.hasSkill(BARREL_ROULETTE)) {
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            Option o = new Option();
            Skill skill = chr.getSkill(BARREL_ROULETTE);
            byte slv = (byte) skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            if(tsm.hasStat(Roulette)) {
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    if (tsm.hasStat(Roulette) && tsm.getOption(Roulette).nOption == 4) {  //DoT Debuff
                        mts.createAndAddBurnedInfo(chr, skill);
                    } else if (tsm.hasStat(Roulette) && tsm.getOption(Roulette).nOption == 3) {  //Slow Debuff
                        int slowProc = si.getValue(w, slv);
                        if (Util.succeedProp(slowProc)) {
                            o.nOption = -20;
                            o.rOption = skill.getSkillId();
                            o.tOption = si.getValue(v, slv);
                            mts.addStatOptionsAndBroadcast(MobStat.Speed, o);
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getFinalAttackSkill() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(BARREL_ROULETTE);
        if(tsm.hasStat(Roulette) && tsm.getOption(Roulette).nOption == 1 && Util.succeedProp(si.getValue(z, chr.getSkill(BARREL_ROULETTE).getCurrentLevel()))) {
            return 5310008;
        }
        else if(chr.hasSkill(MAJESTIC_PRESENCE)) {
            return MAJESTIC_PRESENCE;
        } else if (chr.hasSkill(NAUTILUS_STRIKE_BUCC) && tsm.hasStatBySkillId(NAUTILUS_STRIKE_BUCC)) {
            return NAUTILUS_STRIKE_BUCC_FA;
        }
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
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (isBuff(skillID)) {
            handleBuff(c, inPacket, skillID, slv);
        } else {
            Option o1 = new Option();
            Option o2 = new Option();
            Option o3 = new Option();
            switch (skillID) {
                case TIME_LEAP:
                    //TODO
                    break;
                case MAPLE_RETURN:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = chr.getOrCreateFieldByCurrentInstanceType(o1.nValue);
                    chr.warp(toField);
                    break;
                case HEROS_WILL_BUCC:
                case HEROS_WILL_SAIR:
                case HEROS_WILL_CANNON:
                    tsm.removeAllDebuffs();
                    break;
            }
        }
    }



    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        if(chr.hasSkill(PIRATE_REVENGE_BUCC) || chr.hasSkill(PIRATE_REVENGE_SAIR)) {
            applyPirateRevenge();
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    private void applyPirateRevenge() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = getPirateRevenge();
        if (skill == null) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();
        Option o1 = new Option();
        Option o2 = new Option();
        int prop = si.getValue(SkillStat.prop, slv);
        if(Util.succeedProp(prop)) {
            o1.nOption = si.getValue(y, slv);
            o1.rOption = getPirateRevenge().getSkillId();
            o1.tOption = si.getValue(time, slv);
            tsm.putCharacterStatValue(DamageReduce, o1);
            o2.nReason = getPirateRevenge().getSkillId();
            o2.nValue = si.getValue(indieDamR, slv);
            o2.tStart = (int) System.currentTimeMillis();
            o2.tTerm = si.getValue(time, slv);
            tsm.putCharacterStatValue(IndieDamR, o2);
            tsm.sendSetStatPacket();
        }
    }

    private Skill getPirateRevenge() {
        Skill skill = null;
        if(chr.hasSkill(PIRATE_REVENGE_SAIR)) {
            skill = chr.getSkill(PIRATE_REVENGE_SAIR);
        }
        if(chr.hasSkill(PIRATE_REVENGE_BUCC)) {
            skill = chr.getSkill(PIRATE_REVENGE_BUCC);
        }
        return skill;
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
        // hacks to bypass the quest glitch (accept but no packet)
        short level = chr.getLevel();
        QuestManager qm = chr.getQuestManager();
        if (level == 30) {
            qm.completeQuest(1424);
        } else if (level == 60) {
            qm.completeQuest(1444);
        } else if (level == 100) {
            qm.completeQuest(1458);
        }
    }
}
