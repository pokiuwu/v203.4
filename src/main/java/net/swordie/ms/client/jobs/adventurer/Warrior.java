package net.swordie.ms.client.jobs.adventurer;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.quest.QuestManager;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.party.Party;
import net.swordie.ms.client.party.PartyMember;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Warrior extends Beginner {

    public static final int MAPLE_RETURN = 1281;

    //Hero
    public static final int WEAPON_BOOSTER_FIGHTER = 1101004;
    public static final int COMBO_ATTACK = 1101013;
    public static final int RAGE = 1101006;
    public static final int FINAL_ATTACK_FIGHTER = 1100002;
    public static final int FINAL_ATTACK_PAGE = 1200002;
    public static final int FINAL_ATTACK_SPEARMAN = 1300002;
    public static final int MAPLE_WARRIOR_HERO = 1121000;
    public static final int WEAPON_BOOSTER_PAGE = 1201004;
    public static final int COMBO_FURY = 1101012;
    public static final int COMBO_FURY_DOWN = 1100012;
    public static final int PANIC = 1111003;
    public static final int SHOUT = 1111008;
    public static final int SHOUT_DOWN = 1111014;
    public static final int ADVANCED_FINAL_ATTACK = 1120013;
    public static final int ENRAGE = 1121010;
    public static final int PUNCTURE = 1121015;
    public static final int MAGIC_CRASH_HERO = 1121016;
    public static final int HEROS_WILL_HERO = 1121011;
    public static final int ADVANCED_COMBO = 1120003;

    //Paladin
    public static final int CLOSE_COMBAT = 1201013;
    public static final int ELEMENTAL_CHARGE = 1200014;
    public static final int FLAME_CHARGE = 1201011;
    public static final int BLIZZARD_CHARGE = 1201012;
    public static final int LIGHTNING_CHARGE = 1211008;
    public static final int HP_RECOVERY = 1211010;
    public static final int COMBAT_ORDERS = 1211011;
    public static final int PARASHOCK_GUARD = 1211014;
    public static final int DIVINE_CHARGE = 1221004;
    public static final int THREATEN = 1211013;
    public static final int HEAVENS_HAMMER = 1221011;
    public static final int ELEMENTAL_FORCE = 1221015;
    public static final int MAPLE_WARRIOR_PALADIN = 1221000;
    public static final int GUARDIAN = 1221016;
    public static final int BLAST = 1221009;
    public static final int DIVINE_SHIELD = 1210016;
    public static final int MAGIC_CRASH_PALLY = 1221014;
    public static final int HEROS_WILL_PALA = 1221012;

    //Dark Knight
    public static final int SPEAR_SWEEP = 1301012;
    public static final int WEAPON_BOOSTER_SPEARMAN = 1301004;
    public static final int IRON_WILL = 1301006;
    public static final int HYPER_BODY = 1301007;
    public static final int EVIL_EYE = 1301013;
    public static final int EVIL_EYE_OF_DOMINATION = 1311013;
    public static final int EVIL_EYE_SHOCK = 1311014;
    public static final int CROSS_SURGE = 1311015;
    public static final int HEX_OF_THE_EVIL_EYE = 1310016;
    public static final int LORD_OF_DARKNESS = 1310009;
    public static final int MAPLE_WARRIOR_DARK_KNIGHT = 1321000;
    public static final int FINAL_PACT_INFO = 1320016;
    public static final int FINAL_PACT = 1320019;
    public static final int MAGIC_CRASH_DRK = 1321014;
    public static final int SACRIFICE = 1321015; //Resets summon
    public static final int HEROS_WILL_DRK = 1321010;
    public static final int GUNGNIR_DESCENT = 1321013;

    //Hyper Skills
    public static final int EPIC_ADVENTURE_HERO = 1121053; //Lv200
    public static final int EPIC_ADVENTURE_PALA = 1221053; //Lv200
    public static final int EPIC_ADVENTURE_DRK = 1321053; //Lv200
    public static final int CRY_VALHALLA = 1121054; //Lv150
    public static final int SACROSANCTITY = 1221054; //Lv150
    public static final int DARK_THIRST = 1321054; //Lv150
    public static final int SMITE_SHIELD = 1221052; //Lv170


    private int[] addedSkills = new int[]{
            MAPLE_RETURN,
    };

    private final int[] buffs = new int[]{
            WEAPON_BOOSTER_FIGHTER, // Weapon Booster - Fighter
            COMBO_ATTACK, // Combo Attack
            RAGE, // Rage
            MAPLE_WARRIOR_HERO, // Maple Warrior
            MAPLE_WARRIOR_PALADIN,
            MAPLE_WARRIOR_DARK_KNIGHT,
            WEAPON_BOOSTER_PAGE, // Weapon Booster - Page
            WEAPON_BOOSTER_SPEARMAN,
            COMBAT_ORDERS,
            PARASHOCK_GUARD,
            ELEMENTAL_FORCE,
            EVIL_EYE,
            EVIL_EYE_OF_DOMINATION,
            EVIL_EYE_SHOCK,
            IRON_WILL,
            HYPER_BODY,
            CROSS_SURGE,
            BLAST,
            ENRAGE,
            SACRIFICE,

            EPIC_ADVENTURE_DRK,
            EPIC_ADVENTURE_HERO,
            EPIC_ADVENTURE_PALA,
            CRY_VALHALLA,
            SACROSANCTITY,
            DARK_THIRST,
    };

    private Summon evilEye;
    private long lastPanicHit = Long.MIN_VALUE;
    private long lastDivineShieldHit = Long.MIN_VALUE;
    private long revengeEvilEye = Long.MIN_VALUE;
    private static long lastFinalPact = Long.MIN_VALUE;
    private static long finishFinalPact;
    private static int killCount;
    private int lastCharge = 0;
    private int divShieldAmount = 0;
    private ScheduledFuture parashockGuardTimer;

    public Warrior(Char chr) {
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
        return JobConstants.isAdventurerWarrior(id);
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
            case WEAPON_BOOSTER_FIGHTER:
            case WEAPON_BOOSTER_PAGE:
            case WEAPON_BOOSTER_SPEARMAN:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case RAGE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indiePad, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1);
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(PowerGuard, o2);
                break;
            case COMBO_ATTACK:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(ComboCounter, o1);
                break;
            case ENRAGE:
                removeCombo(chr, 1);
                o1.nOption = 1;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(Enrage, o1); // max mobs hit
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                tsm.putCharacterStatValue(EnrageCrDam, o2);
                o3.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                tsm.putCharacterStatValue(EnrageCr, o3);
                break;
            case COMBAT_ORDERS:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CombatOrders, o1);
                break;
            case PARASHOCK_GUARD:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(EVA, o1); // Check for the main Buff method

                if (parashockGuardTimer != null && !parashockGuardTimer.isDone()) {
                    parashockGuardTimer.cancel(true);
                }
                giveParashockGuardBuff();

                break;
            case ELEMENTAL_FORCE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case IRON_WILL:
                o1.nOption = si.getValue(pdd, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(PDD, o1);
                o2.nOption = si.getValue(mdd, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(MDD, o2);
                break;
            case HYPER_BODY:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(MaxHP, o1);
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(MaxMP, o2);
                break;
            case CROSS_SURGE:
                int totalHP = c.getChr().getMaxHP();
                int currentHP = c.getChr().getHP();
                o1.nOption = (int) ((si.getValue(x, slv) * ((double) currentHP) / totalHP));
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamR, o1);
                o2.nOption = (int) Math.min((0.08 * totalHP - currentHP), si.getValue(z, slv));
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamageReduce, o2);
                break;
            case EVIL_EYE:
                spawnEvilEye(skillID, slv);
                break;
            case EVIL_EYE_OF_DOMINATION:
                if (tsm.hasStat(Beholder)) {
                    tsm.removeStatsBySkill(EVIL_EYE_OF_DOMINATION);
                    spawnEvilEye(EVIL_EYE, slv);
                } else {
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    o1.tOption = 0;
                    o1.sOption = skillID;
                    o1.ssOption = 0;
                    tsm.putCharacterStatValue(Beholder, o1);
                }
                break;
            case EVIL_EYE_SHOCK:
                if (tsm.getOption(Beholder).nOption > 0 && tsm.getOption(Beholder).sOption > 0) //If evil eye and domination is active
                {
                    slv = (byte) chr.getSkill(EVIL_EYE).getCurrentLevel();
                    Option o = new Option();
                    o.nOption = tsm.getOption(Beholder).nOption;
                    o.rOption = tsm.getOption(Beholder).rOption;
                    o.tOption = tsm.getOption(Beholder).tOption;
                    o.sOption = tsm.getOption(Beholder).sOption;
                    o.ssOption = EVIL_EYE_SHOCK;
                    //Remove after getting the options
                    tsm.removeStatsBySkill(EVIL_EYE_OF_DOMINATION);
                    spawnEvilEye(EVIL_EYE, slv);
                    tsm.putCharacterStatValue(Beholder, o);
                }
                break;
            case SACRIFICE:
                if (tsm.hasStatBySkillId(EVIL_EYE)) {
                    o2.nReason = skillID;
                    o2.nValue = si.getValue(x, slv);
                    o2.tStart = (int) System.currentTimeMillis();
                    o2.tTerm = si.getValue(time, slv);
                    tsm.putCharacterStatValue(IndieIgnoreMobpdpR, o2);

                    o3.nReason = skillID;
                    o3.nValue = si.getValue(indieBDR, slv);
                    o3.tStart = (int) System.currentTimeMillis();
                    o3.tTerm = si.getValue(time, slv);
                    tsm.putCharacterStatValue(IndieBDR, o3);


                    tsm.removeStatsBySkill(EVIL_EYE_OF_DOMINATION);
                    tsm.removeStatsBySkill(EVIL_EYE_SHOCK);
                    removeEvilEye(tsm, c);

                    chr.heal((int) (chr.getMaxHP() / ((double) 100 / si.getValue(y, slv))));
                    chr.write(UserLocal.skillCooltimeSetM(1321013, 0));
                }
                break;
            case MAPLE_WARRIOR_HERO:
            case MAPLE_WARRIOR_PALADIN:
            case MAPLE_WARRIOR_DARK_KNIGHT:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case BLAST:
                o1.nOption = si.getValue(cr, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CriticalBuff, o1);
                o2.nOption = si.getValue(damR, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamR, o2);
                o3.nOption = si.getValue(ignoreMobpdpR, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(IgnoreMobpdpR, o3);
                break;

            //Hypers
            case EPIC_ADVENTURE_DRK:
            case EPIC_ADVENTURE_HERO:
            case EPIC_ADVENTURE_PALA:
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
            case SACROSANCTITY:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NotDamaged, o1);
                break;
            case CRY_VALHALLA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieCr, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieCr, o1);
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AsrR, o2);
                tsm.putCharacterStatValue(TerR, o2);
                o3.nOption = 100;
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Stance, o3);
                break;
            case DARK_THIRST:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indiePad, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1);
                break;
        }
        tsm.sendSetStatPacket();
    }

    public boolean isBuff(int skillID) {
        return super.isBuff(skillID) || Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    public void spawnEvilEye(int skillID, byte slv) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        SkillInfo si = SkillData.getSkillInfoById(skillID);

        Field field;
        evilEye = Summon.getSummonBy(c.getChr(), skillID, slv);
        field = c.getChr().getField();
        evilEye.setFlyMob(true);
        evilEye.setMoveAbility(MoveAbility.Fly);
        evilEye.setAssistType(AssistType.Heal);
        evilEye.setAttackActive(true);
        field.spawnSummon(evilEye);

        o1.nReason = skillID;
        o1.nValue = 1;
        o1.summon = evilEye;
        o1.tStart = (int) System.currentTimeMillis();
        o1.tTerm = si.getValue(time, slv);
        tsm.putCharacterStatValue(IndieEmpty, o1);
        tsm.sendSetStatPacket();
    }

    public void removeEvilEye(TemporaryStatManager tsm, Client c) {
        tsm.removeStatsBySkill(EVIL_EYE);
        tsm.sendResetStatPacket();
        c.getChr().getField().broadcastPacket(Summoned.summonedRemoved(evilEye, LeaveType.ANIMATION));
    }

    private void addCombo(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int currentCount = getComboCount(chr);
        if (currentCount < 0) {
            return;
        }
        int maxCombo = getMaxCombo(chr);
        int added = 1;
        if (chr.hasSkill(ADVANCED_COMBO)) {
            int slv = chr.getSkillLevel(ADVANCED_COMBO);
            SkillInfo si = SkillData.getSkillInfoById(ADVANCED_COMBO);
            if (slv > 0 && Util.succeedProp(si.getValue(prop, slv))) {
                added = 2;
            }
        }
        Option o = new Option();
        o.nOption = Math.min(maxCombo, currentCount + added);
        o.rOption = COMBO_ATTACK;
        tsm.putCharacterStatValue(ComboCounter, o);
        tsm.sendSetStatPacket();
    }

    private void removeCombo(Char chr, int count) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int currentCount = getComboCount(chr);
        Option o = new Option();
        if (currentCount > count + 1) {
            o.nOption = currentCount - count;
        } else {
            o.nOption = 0;
        }
        o.rOption = COMBO_ATTACK;
        tsm.putCharacterStatValue(ComboCounter, o);
        tsm.sendSetStatPacket();
    }

    private int getComboProp(Char chr) {
        Skill skill = null;
        if (chr.hasSkill(1110013)) {    //Combo Synergy
            skill = chr.getSkill(1110013);
        } else if (chr.hasSkill(COMBO_ATTACK)) {
            skill = chr.getSkill(COMBO_ATTACK);
        }
        if (skill == null) {
            return 0;
        }
        return SkillData.getSkillInfoById(skill.getSkillId()).getValue(prop, skill.getCurrentLevel());
    }

    public int getComboCount(Char c) {
        TemporaryStatManager tsm = c.getTemporaryStatManager();
        if (tsm.hasStat(ComboCounter)) {
            return tsm.getOption(ComboCounter).nOption;
        }
        return -1;
    }

    private int getMaxCombo(Char chr) {
        int num = 0;
        if (chr.hasSkill(COMBO_ATTACK)) {
            num = 6;
        }
        if (chr.hasSkill(ADVANCED_COMBO)) {
            num = 11;
        }
        return num;
    }

    private void giveParashockGuardBuff() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (chr.hasSkill(PARASHOCK_GUARD) && tsm.getOptByCTSAndSkill(EVA, PARASHOCK_GUARD) != null) {
            Skill skill = chr.getSkill(PARASHOCK_GUARD);
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            byte slv = (byte) skill.getCurrentLevel();
            Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
            if (!chr.isLeft()) {
                rect = rect.moveRight();
            }
            Party party = chr.getParty();

            if (party != null) {
                for (PartyMember partyMember : party.getOnlineMembers()) {
                    Char partyChr = partyMember.getChr();
                    TemporaryStatManager partyTSM = partyChr.getTemporaryStatManager();
                    int partyChrX = partyChr.getPosition().getX();
                    int partyChrY = partyChr.getPosition().getY();

                    if (partyChr.getId() == chr.getId()) {
                        continue;
                    }

                    if (partyChrX >= rect.getLeft() && partyChrY >= rect.getTop()       // if  Party Members in Range
                            && partyChrX <= rect.getRight() && partyChrY <= rect.getBottom()) {

                        Option o4 = new Option();
                        Option o5 = new Option();
                        o4.nOption = si.getValue(x, slv);
                        o4.rOption = skill.getSkillId();
                        o4.tOption = 2;
                        partyTSM.putCharacterStatValue(Guard, o4);
                        o5.nOption = chr.getId();
                        o5.rOption = skill.getSkillId();
                        o5.tOption = 2;
                        o5.bOption = 1;
                        partyTSM.putCharacterStatValue(KnightsAura, o5);
                        partyTSM.sendSetStatPacket();
                    } else {
                        partyTSM.removeStatsBySkill(skill.getSkillId());
                        partyTSM.sendResetStatPacket();
                    }
                }
            }
            Option o = new Option();
            Option o1 = new Option();
            Option o2 = new Option();
            Option o3 = new Option();
            o.nOption = chr.getId();
            o.rOption = skill.getSkillId();
            o.bOption = 1;
            tsm.putCharacterStatValue(KnightsAura, o);
            o1.nReason = skill.getSkillId();
            o1.nValue = si.getValue(indiePad, slv);
            o1.tStart = (int) System.currentTimeMillis();
            tsm.putCharacterStatValue(IndiePAD, o1);
            o2.nReason = skill.getSkillId();
            o2.nValue = si.getValue(z, slv);
            o2.tStart = (int) System.currentTimeMillis();
            tsm.putCharacterStatValue(IndiePDDR, o2);
            o3.nOption = -si.getValue(x, slv);
            o3.rOption = skill.getSkillId();
            tsm.putCharacterStatValue(Guard, o3);

            parashockGuardTimer = EventManager.addEvent(this::giveParashockGuardBuff, 1, TimeUnit.SECONDS);
        } else {
            tsm.removeStatsBySkill(PARASHOCK_GUARD);
            tsm.sendResetStatPacket();
        }
    }

    public void healByEvilEye() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (chr.hasSkill(EVIL_EYE) && tsm.hasStatBySkillId(EVIL_EYE)) {
            Skill skill = chr.getSkill(EVIL_EYE);
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            byte slv = (byte) skill.getCurrentLevel();
            chr.heal(si.getValue(hp, slv));
        }
    }

    public void giveHexOfTheEvilEyeBuffs() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        Skill skill = chr.getSkill(HEX_OF_THE_EVIL_EYE);
        byte slv = (byte) skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        if (tsm.getOptByCTSAndSkill(EPDD, HEX_OF_THE_EVIL_EYE) == null) {
            o1.nOption = si.getValue(epad, slv);
            o1.rOption = skill.getSkillId();
            o1.tOption = si.getValue(time, slv);
            tsm.putCharacterStatValue(EPAD, o1);

            o2.nOption = si.getValue(epdd, slv);
            o2.rOption = skill.getSkillId();
            o2.tOption = si.getValue(time, slv);
            tsm.putCharacterStatValue(EPDD, o2);
            tsm.putCharacterStatValue(EMDD, o2);

            o3.nReason = skill.getSkillId();
            o3.nValue = si.getValue(indieCr, slv);
            o3.tStart = (int) System.currentTimeMillis();
            o3.tTerm = si.getValue(time, slv);
            tsm.putCharacterStatValue(IndieCr, o3);

            o4.nOption = si.getValue(acc, slv);
            o4.rOption = skill.getSkillId();
            o4.tOption = si.getValue(time, slv);
            tsm.putCharacterStatValue(ACC, o4);
            tsm.putCharacterStatValue(EVA, o4);
            tsm.sendSetStatPacket();
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
        int slv = 0;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skill.getSkillId());
            slv = skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }

        if (JobConstants.isHero(chr.getJob()) && !isComboIgnoreSkill(attackInfo.skillId)) {
            if (hasHitMobs) {
                //Combo
                int comboProp = getComboProp(chr);
                if (Util.succeedProp(comboProp)) {
                    addCombo(chr);
                }
            }
        }

        if (JobConstants.isPage(chr.getJob())) {
            if (hasHitMobs) {

            }
        }

        if (JobConstants.isDarkKnight(chr.getJob())) {
            if (hasHitMobs) {
                //Lord of Darkness
                lordOfDarkness();

                killCountFinalPactOnMob(attackInfo);

                //Dark Thirst
                darkThirst(tsm);
            }
        }

        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case COMBO_FURY:
                removeCombo(chr, 1);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    if (Util.succeedProp(si.getValue(prop, skill.getCurrentLevel()))) {
                        if (!mob.isBoss()) {
                            o1.nOption = 1;
                            o1.rOption = skill.getSkillId();
                            o1.tOption = si.getValue(time, skill.getCurrentLevel());
                            mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                        }
                        addCombo(chr);
                    }
                }
                break;
            case COMBO_FURY_DOWN:
                removeCombo(chr, 1);
                break;
            case PANIC:
                if (tsm.hasStat(ComboCostInc)) {
                    int amount = tsm.getOption(ComboCostInc).nOption;
                    removeCombo(chr, 2 + amount);
                    o3.nOption = amount + 1;
                    o3.rOption = PANIC;
                    o3.tOption = si.getValue(subTime, slv);
                    tsm.putCharacterStatValue(ComboCostInc, o3);
                    tsm.sendSetStatPacket();
                } else {
                    o3.nOption = 1;
                    o3.rOption = PANIC;
                    o3.tOption = si.getValue(subTime, slv);
                    tsm.putCharacterStatValue(ComboCostInc, o3);
                    tsm.sendSetStatPacket();
                    removeCombo(chr, 2);
                }
                if (hasHitMobs) {
                    int allowedTime = si.getValue(subTime, slv);
                    for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = si.getValue(z, slv);
                        o1.rOption = skill.getSkillId();
                        o1.tOption = 0;
                        mts.addStatOptions(MobStat.PAD, o1);
                        if (Util.succeedProp(si.getValue(prop, slv))) {
                            o2.nOption = -si.getValue(x, slv); // minus?
                            o2.rOption = skill.getSkillId();
                            o2.tOption = si.getValue(time, slv);
                            mts.addStatOptions(MobStat.ACC, o2);
                        }
                        c.write(MobPool.statSet(mob, (short) 0));
                    }
                }

                break;
            case SHOUT:
                if (hasHitMobs) {
                    removeCombo(chr, si.getValue(y, slv));
                }
                break;
            case SHOUT_DOWN:
                Skill orig = chr.getSkill(SHOUT);
                slv = orig.getCurrentLevel();
                si = SkillData.getSkillInfoById(SHOUT_DOWN);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        removeCombo(chr, 1);
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    if (mob.isBoss()) {
                        o1.nOption = si.getValue(x, slv);
                        o1.rOption = SHOUT_DOWN;
                        o1.tOption = si.getValue(time, slv);
                        o1.mOption = 1;
                        mts.addStatOptionsAndBroadcast(MobStat.Weakness, o1);
                    } else {
                        o1.nOption = 1;
                        o1.rOption = SHOUT_DOWN;
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                removeCombo(chr, 1);
                chr.write(UserLocal.skillCooltimeSetM(SHOUT, 10000));
                break;
            case PUNCTURE:
                removeCombo(chr, si.getValue(y, slv));
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = si.getValue(y, slv);
                    o1.rOption = skillID;
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptions(MobStat.AddDamParty, o1);
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        mts.createAndAddBurnedInfo(chr, skill);
                    }
                }
                break;
            case CLOSE_COMBAT:
                if (Util.succeedProp(si.getValue(prop, slv))) {
                    for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        if (!mob.isBoss()) {
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            o1.nOption = 1;
                            o1.rOption = skill.getSkillId();
                            o1.tOption = si.getValue(time, slv);
                            mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                        }
                    }
                }
                break;
            case FLAME_CHARGE:
                giveChargeBuff(skill.getSkillId(), tsm);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.createAndAddBurnedInfo(chr, skill);
                    }
                }
                break;
            case BLIZZARD_CHARGE:
                giveChargeBuff(skill.getSkillId(), tsm);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.tOption = si.getValue(time, slv);
                        o1.nOption = -20;
                        o1.rOption = skill.getSkillId();
                        mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                    }
                }
                break;
            case LIGHTNING_CHARGE:
                giveChargeBuff(skill.getSkillId(), tsm);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        if (!mob.isBoss()) {
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            o1.nOption = 1;
                            o1.rOption = skill.getSkillId();
                            o1.tOption = si.getValue(time, slv);
                            mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                        }
                    } else {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.createAndAddBurnedInfo(chr, skill);
                    }
                }
                break;
            case DIVINE_CHARGE:
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
                        mts.addStatOptionsAndBroadcast(MobStat.Seal, o1);
                    }
                }
                giveChargeBuff(skill.getSkillId(), tsm);
                break;
            case HEAVENS_HAMMER:
                break;
            case BLAST:
                int charges = tsm.getOption(ElementalCharge).mOption;
                if (charges == SkillData.getSkillInfoById(ELEMENTAL_CHARGE).getValue(z, 1)) {
                    if (tsm.getOptByCTSAndSkill(DamR, BLAST) == null) {
                        resetCharges(tsm);
                        int t = si.getValue(time, slv);
                        o1.nOption = si.getValue(cr, slv);
                        o1.rOption = skillID;
                        o1.tOption = t;
                        tsm.putCharacterStatValue(CriticalBuff, o1);
                        o2.nOption = si.getValue(ignoreMobpdpR, slv);
                        o2.rOption = skillID;
                        o2.tOption = t;
                        tsm.putCharacterStatValue(IgnoreMobpdpR, o2);
                        o3.nOption = si.getValue(damR, slv);
                        o3.rOption = skillID;
                        o3.tOption = t;
                        tsm.putCharacterStatValue(DamR, o3);
                        tsm.sendSetStatPacket();
                    }
                }
                break;
            case SMITE_SHIELD:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 1;
                    o1.rOption = skill.getSkillId();
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.Smite, o1);
                }
                break;
            case SPEAR_SWEEP:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    if (!mob.isBoss()) {
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case FINAL_ATTACK_FIGHTER:
            case FINAL_ATTACK_SPEARMAN:
            case FINAL_ATTACK_PAGE:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    long dmg = 0;
                    for (int i = 0; i < mai.damages.length; i++) {
                        dmg += mai.damages[i];
                    }
                    c.write(MobPool.damaged(mob.getObjectId(), dmg, mob.getTemplateId(), (byte) 1, mob.getHp(), mob.getMaxHp()));
                }
                break;
            case EVIL_EYE:
                if (tsm.getOption(Beholder).ssOption > 0) //If can use EVIL_EYE_SHOCK
                {
                    skill = chr.getSkill(EVIL_EYE_SHOCK);
                    si = SkillData.getSkillInfoById(skill.getSkillId());
                    slv = skill.getCurrentLevel();

                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                            if (mob == null) {
                                continue;
                            }
                            if (!mob.isBoss()) {
                                MobTemporaryStat mts = mob.getTemporaryStat();
                                o1.nOption = 1;
                                o1.rOption = skill.getSkillId();
                                o1.tOption = si.getValue(time, slv);
                                mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                            }
                            //TODO add removal of shock
                        }
                    }
                }
                break;
        }
        super.handleAttack(c, attackInfo);
    }

    private void darkThirst(TemporaryStatManager tsm) {
        if (tsm.getOptByCTSAndSkill(IndiePAD, DARK_THIRST) != null) {
            Skill skill = chr.getSkill(DARK_THIRST);
            byte slv = (byte) skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int heal = si.getValue(x, slv);
            chr.heal((int) (chr.getMaxHP() / ((double) 100 / heal)));
        }
    }

    private void giveChargeBuff(int skillId, TemporaryStatManager tsm) {
        Option o = new Option();
        SkillInfo chargeInfo = SkillData.getSkillInfoById(1200014);
        int amount = 1;
        if (tsm.hasStat(ElementalCharge)) {
            amount = tsm.getOption(ElementalCharge).mOption;
            if (lastCharge == skillId) {
                return;
            }
            if (amount < chargeInfo.getValue(z, 1)) {
                amount++;
            }
        }
        lastCharge = skillId;
        o.nOption = 1;
        o.rOption = 1200014;
        o.tOption = (10 * chargeInfo.getValue(time, 1)); // elemental charge  // 10x actual duration
        o.mOption = amount;
        o.wOption = amount * chargeInfo.getValue(w, 1); // elemental charge
        o.uOption = amount * chargeInfo.getValue(u, 1);
        o.zOption = amount * chargeInfo.getValue(z, 1);
        tsm.putCharacterStatValue(ElementalCharge, o);
        tsm.sendSetStatPacket();
    }

    private void resetCharges(TemporaryStatManager tsm) {
        tsm.removeStat(ElementalCharge, false);
        tsm.sendResetStatPacket();
    }

    public void lordOfDarkness() {
        if (chr.hasSkill(LORD_OF_DARKNESS)) {
            Skill skill = chr.getSkill(LORD_OF_DARKNESS);
            byte slv = (byte) skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int proc = si.getValue(prop, slv);
            if (Util.succeedProp(proc)) {
                int heal = si.getValue(x, slv);
                chr.heal((int) (chr.getMaxHP() / ((double) 100 / heal)));
            }
        }
    }

    private Skill getFinalAtkSkill() {
        Skill skill = null;
        if (chr.hasSkill(FINAL_ATTACK_FIGHTER)) {
            skill = chr.getSkill(FINAL_ATTACK_FIGHTER);

        } else if (chr.hasSkill(FINAL_ATTACK_PAGE)) {
            skill = chr.getSkill(FINAL_ATTACK_PAGE);

        } else if (chr.hasSkill(FINAL_ATTACK_SPEARMAN)) {
            skill = chr.getSkill(FINAL_ATTACK_SPEARMAN);
        }

        if (chr.hasSkill(ADVANCED_FINAL_ATTACK)) {
            skill = chr.getSkill(ADVANCED_FINAL_ATTACK); // Hero Adv FA
        }

        return skill;
    }

    @Override
    public int getFinalAttackSkill() {
        Skill faSkill = getFinalAtkSkill();
        if (faSkill != null) {
            SkillInfo si = SkillData.getSkillInfoById(faSkill.getSkillId());
            byte slv = (byte) faSkill.getCurrentLevel();
            int proc = si.getValue(prop, slv);

            if (Util.succeedProp(proc)) {
                return faSkill.getSkillId();
            }
        }
        return 0;
    }

    public boolean isComboIgnoreSkill(int skillID) {
        return skillID == SHOUT ||
                skillID == SHOUT_DOWN ||
                skillID == PANIC ||
                skillID == COMBO_FURY ||
                skillID == COMBO_FURY_DOWN ||
                skillID == PUNCTURE;
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
                case MAPLE_RETURN:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = chr.getOrCreateFieldByCurrentInstanceType(o1.nValue);
                    chr.warp(toField);
                    break;
                case HP_RECOVERY:
                    hpRecovery();
                    break;
                case THREATEN:
                    Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
                    if (!chr.isLeft()) {
                        rect = rect.moveRight();
                    }
                    for (Life life : chr.getField().getLifesInRect(rect)) {
                        if (life instanceof Mob && ((Mob) life).getHp() > 0) {
                            Mob mob = (Mob) life;
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            if (Util.succeedProp(si.getValue(prop, slv))) {
                                o1.nOption = -si.getValue(x, slv);
                                o1.rOption = skillID;
                                o1.tOption = si.getValue(time, slv);
                                mts.addStatOptions(MobStat.PAD, o1);
                                mts.addStatOptions(MobStat.MAD, o1);
                                mts.addStatOptions(MobStat.PDR, o1);
                                mts.addStatOptions(MobStat.MDR, o1);
                                o2.nOption = -si.getValue(z, slv);
                                o2.rOption = skillID;
                                o2.tOption = si.getValue(subTime, slv);
                                mts.addStatOptionsAndBroadcast(MobStat.Darkness, o2);
                            }
                        }
                    }
                    break;
                case GUARDIAN:
                    chr.heal(chr.getMaxHP());

                    o1.nOption = 1;
                    o1.rOption = skillID;
                    o1.tOption = si.getValue(time, slv);
                    tsm.putCharacterStatValue(NotDamaged, o1);
                    tsm.sendSetStatPacket();

                    Party party = chr.getParty();
                    if (party != null) {
                        Field field = chr.getField();
                        rect = chr.getPosition().getRectAround(si.getRects().get(0));
                        if (!chr.isLeft()) {
                            rect = rect.moveRight();
                        }
                        List<PartyMember> eligblePartyMemberList = field.getPartyMembersInRect(chr, rect).stream().
                                filter(pml -> pml.getChr().getId() != chr.getId() &&
                                        pml.getChr().getHP() <= 0).
                                collect(Collectors.toList());

                        if (eligblePartyMemberList.size() > 0) {
                            Char randomPartyChr = Util.getRandomFromCollection(eligblePartyMemberList).getChr();
                            TemporaryStatManager partyTSM = randomPartyChr.getTemporaryStatManager();
                            randomPartyChr.heal(randomPartyChr.getMaxHP());
                            partyTSM.putCharacterStatValue(NotDamaged, o1);
                            partyTSM.sendSetStatPacket();
                            randomPartyChr.write(User.effect(Effect.skillAffected(skillID, (byte) 1, 0)));
                            randomPartyChr.getField().broadcastPacket(UserRemote.effect(randomPartyChr.getId(), Effect.skillAffected(skillID, (byte) 1, 0)));
                        }
                    }

                    break;
                case MAGIC_CRASH_DRK:
                case MAGIC_CRASH_HERO:
                case MAGIC_CRASH_PALLY:
                    Rect rect2 = chr.getPosition().getRectAround(si.getRects().get(0));
                    if (!chr.isLeft()) {
                        rect2 = rect2.moveRight();
                    }
                    for (Life life : chr.getField().getLifesInRect(rect2)) {
                        if (life instanceof Mob && ((Mob) life).getHp() > 0) {
                            Mob mob = (Mob) life;
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            if (Util.succeedProp(si.getValue(prop, slv))) {
                                mts.removeBuffs();
                                o1.nOption = 1;
                                o1.rOption = skillID;
                                o1.tOption = si.getValue(time, slv);
                                mts.addStatOptionsAndBroadcast(MobStat.MagicCrash, o1);
                            }
                        }
                    }
                    break;
                case HEROS_WILL_HERO:
                case HEROS_WILL_PALA:
                case HEROS_WILL_DRK:
                    tsm.removeAllDebuffs();
                    break;
            }
        }
    }

    public void hpRecovery() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        if (chr.hasSkill(HP_RECOVERY)) {
            Skill skill = chr.getSkill(HP_RECOVERY);
            byte slv = (byte) skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int recovery = si.getValue(x, slv);
            int amount = 10;

            if (tsm.hasStat(Restoration)) {
                amount = tsm.getOption(Restoration).nOption;
                if (amount < 300) {
                    amount = amount + 10;
                }
            }

            o.nOption = amount;
            o.rOption = skill.getSkillId();
            o.tOption = si.getValue(time, slv);
            int heal = (recovery + 10) - amount > 10 ? (recovery + 10) - amount : 10;
            chr.heal((int) (chr.getMaxHP() / ((double) 100 / heal)));
            tsm.putCharacterStatValue(Restoration, o);
            tsm.sendSetStatPacket();
        }
    }

    public int alterCooldownSkill(int skillId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        switch (skillId) {
            case GUNGNIR_DESCENT:
                if (tsm.hasStatBySkillId(SACRIFICE)) {
                    return 0;
                }
        }
        return -1;
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();

        //Paladin - Divine Shield
        if (chr.hasSkill(DIVINE_SHIELD)) {
            Skill skill = chr.getSkill(DIVINE_SHIELD);
            SkillInfo si = SkillData.getSkillInfoById(DIVINE_SHIELD);
            int slv = skill.getCurrentLevel();
            int shieldprop = 50;//      si.getValue(SkillStat.prop, slv);       //TODO should be prop in WzFiles, but it's actually 0
            Option o1 = new Option();
            Option o2 = new Option();
            int divShieldCoolDown = si.getValue(cooltime, slv);
            if (tsm.hasStat(BlessingArmor)) {
                if (divShieldAmount < 10) {
                    divShieldAmount++;
                } else {
                    resetDivineShield();
                    divShieldAmount = 0;
                }
            } else {
                if (lastDivineShieldHit + (divShieldCoolDown * 1000) < System.currentTimeMillis()) {
                    if (Util.succeedProp(shieldprop)) {
                        lastDivineShieldHit = System.currentTimeMillis();
                        o1.nOption = 1;
                        o1.rOption = DIVINE_SHIELD;
                        o1.tOption = si.getValue(time, slv);
                        tsm.putCharacterStatValue(BlessingArmor, o1);
                        o2.nOption = si.getValue(epad, slv);
                        o2.rOption = DIVINE_SHIELD;
                        o2.tOption = si.getValue(time, slv);
                        tsm.putCharacterStatValue(PAD, o2);
                        tsm.sendSetStatPacket();
                        divShieldAmount = 0;
                    }
                }
            }
        }

        //Hero - Combo Synergy
        if (chr.hasSkill(1110013)) {
            SkillInfo csi = SkillData.getSkillInfoById(1110013);
            int slv = csi.getCurrentLevel();
            int comboprop = 30; //csi.getValue(subProp, slv);
            if (Util.succeedProp(comboprop)) {
                addCombo(chr);
            }
        }

        //Paladin - Shield Mastery
        if (chr.hasSkill(1210001)) { //If Wearing a Shield
            if (hitInfo.hpDamage == 0 && hitInfo.mpDamage == 0) {
                // Guarded
                int mobID = hitInfo.mobID;
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mobID);
                if (mob != null) {
                    Option o = new Option();
                    Skill skill = chr.getSkill(1210001);
                    byte slv = (byte) skill.getCurrentLevel();
                    SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                    int proc = si.getValue(subProp, slv);
                    if (Util.succeedProp(proc) && !mob.isBoss()) {
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o.nOption = 1;
                        o.rOption = skill.getSkillId();
                        o.tOption = 3;  // Value isn't given
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o);
                    }
                }
            }
        }

        //Dark Knight - Revenge of the Evil Eye
        if (chr.hasSkill(1320011)) {
            Skill skill = chr.getSkill(1320011);
            byte slv = (byte) skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int proc = si.getValue(prop, slv);
            int cd = 1000 * si.getValue(cooltime, slv);
            int heal = si.getValue(x, slv);
            if (tsm.getOptByCTSAndSkill(PDD, EVIL_EYE) != null) {
                if (cd + revengeEvilEye < System.currentTimeMillis()) {
                    if (Util.succeedProp(proc)) {
                        c.write(Summoned.summonBeholderRevengeAttack(evilEye, hitInfo.mobID));
                        chr.heal((int) (chr.getMaxHP() / ((double) 100 / heal)));
                        revengeEvilEye = System.currentTimeMillis();
                    }
                }
            }
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    private void resetDivineShield() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        tsm.removeStat(BlessingArmor, false);
        tsm.removeStat(PAD, false);
        tsm.sendResetStatPacket();
    }

    public static void reviveByFinalPact(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!chr.hasSkill(FINAL_PACT_INFO)) {
            return;
        }

        Skill skill = chr.getSkill(FINAL_PACT_INFO);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();

        if (isFinalPactAvailable(chr)) {
            Option o1 = new Option();
            Option o2 = new Option();

            chr.heal(chr.getMaxHP());
            chr.healMP(chr.getMaxMP());

            o1.setInMillis(true);
            o1.nOption = 1;
            o1.rOption = FINAL_PACT;
            o1.tOption = si.getValue(time, slv) * 1000;
            o1.xOption = si.getValue(z, slv);
            tsm.putCharacterStatValue(Reincarnation, o1);
            o2.nOption = 1;
            o2.rOption = FINAL_PACT;
            o2.tOption = si.getValue(time, slv);
            tsm.putCharacterStatValue(NotDamaged, o2);
            tsm.sendSetStatPacket();


            chr.write(User.effect(Effect.showFinalPactEffect(FINAL_PACT, (byte) 1, 0, true))); // Manually broadcasting Effect packet, as FINAL PACT isn't actually ever called.
            chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.showFinalPactEffect(FINAL_PACT, (byte) 1, 0, true)));

            lastFinalPact = System.currentTimeMillis();
            finishFinalPact = System.currentTimeMillis() + (si.getValue(time, slv) * 1000);
            chr.write(UserLocal.skillCooltimeSetM(1321013, 0));
        }
    }

    public static boolean isFinalPactAvailable(Char chr) {
        Skill skill = chr.getSkill(FINAL_PACT_INFO);
        if (skill == null) {
            return false;
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();
        return lastFinalPact + (si.getValue(cooltime, slv) * 1000) < System.currentTimeMillis();
    }

    private void lowerFinalPactKillCount() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        Skill skill = chr.getSkill(FINAL_PACT_INFO);
        if (skill == null || !tsm.hasStat(Reincarnation)) {
            return;
        }
        int duration = (int) (finishFinalPact - System.currentTimeMillis());

        killCount = tsm.getOption(Reincarnation).xOption;
        if (killCount > 0) {
            killCount--;

            if (duration > 0) {
                o.setInMillis(true);
                o.nOption = 1;
                o.rOption = FINAL_PACT;
                o.tOption = duration;
                o.xOption = killCount;
                tsm.putCharacterStatValue(Reincarnation, o);
                tsm.sendSetStatPacket();
            } else {
                tsm.removeStatsBySkill(FINAL_PACT);
                tsm.sendResetStatPacket();
            }
        }
    }

    private void killCountFinalPactOnMob(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!tsm.hasStat(Reincarnation)) {
            return;
        }
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);

            if (mob != null) {
                if (mob.isBoss()) {
                    lowerFinalPactKillCount();
                } else {
                    long totaldmg = Arrays.stream(mai.damages).sum();

                    if (totaldmg >= mob.getHp()) {
                        lowerFinalPactKillCount();
                    }
                }
            }
        }
    }

    public static void finalPactEnd(Char chr) {
        if (killCount > 0) {
            chr.setStat(Stat.hp, 0);
            Map<Stat, Object> stats = new HashMap<>();
            stats.put(Stat.hp, 0);
            chr.getClient().write(WvsContext.statChanged(stats));

            chr.write(UserLocal.openUIOnDead(true, chr.getBuffProtectorItem() != null,
                    false, false, false,
                    ReviveType.NORMAL.getVal(), 0));
        }
        killCount = 30; //Resetting
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
        // hacks to bypass the quest glitch (accept but no packet)
        short level = chr.getLevel();
        QuestManager qm = chr.getQuestManager();
        if (level == 30) {
            qm.completeQuest(1410);
        } else if (level == 60) {
            qm.completeQuest(1430);
        } else if (level == 100) {
            qm.completeQuest(1450);
        }
    }
}

