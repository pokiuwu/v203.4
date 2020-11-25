package net.swordie.ms.client.jobs.cygnus;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.ForceAtomInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.CField;
import net.swordie.ms.connection.packet.Summoned;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.enums.LeaveType;
import net.swordie.ms.enums.MoveAbility;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class NightWalker extends Noblesse {

    public static final int IMPERIAL_RECALL = 10001245;
    public static final int ELEMENTAL_EXPERT = 10000250;
    public static final int ELEMENTAL_SLASH = 10001244;
    public static final int NOBLE_MIND = 10000202;
    public static final int ELEMENTAL_SHIFT = 10001254;
    public static final int ELEMENTAL_SHIFT2 = 10000252;
    public static final int ELEMENTAL_HARMONY_LUK = 10000249;

    public static final int LUCKY_SEVEN = 14001020;
    public static final int DARK_ELEMENTAL = 14001021; //Buff (Mark of Darkness)
    public static final int HASTE = 14001022; //Buff
    public static final int DARK_SIGHT = 14001023; //Buff

    public static final int THROWING_BOOSTER = 14101022; //Buff


    public static final int DARK_SERVANT = 14111024; //Buff
    public static final int SPIRIT_PROJECTION = 14111025; //Buff
    public static final int DARKNESS_ASCENDING = 14110030; //Special Buff
    public static final int SHADOW_SPARK = 14111023;

    public static final int DARK_OMEN = 14121003; //Summon
    public static final int SHADOW_STITCH = 14121004; //Special Attack (Bind Debuff)
    public static final int CALL_OF_CYGNUS_NW = 14121000; //Buff
    public static final int VITALITY_SIPHON = 14120009;

    public static final int GLORY_OF_THE_GUARDIANS_NW = 14121053;
    public static final int SHADOW_ILLUSION = 14121054;
    public static final int DOMINION = 14121052;

    //Bats
    public static final int SHADOW_BAT = 14001027; //Buff (Shadow Bats) (ON/OFF)
    public static final int SHADOW_BAT_ATOM = 14000028;
    public static final int BAT_AFFINITY = 14100027; //Summon Upgrade
    public static final int BAT_AFFINITY_II = 14110029; //Summon Upgrade
    public static final int BAT_AFFINITY_III = 14120008; //Summon Upgrade

    //Dark Elemental
    public static final int ADAPTIVE_DARKNESS = 14100026;
    public static final int ADAPTIVE_DARKNESS_II = 14110028;
    public static final int ADAPTIVE_DARKNESS_III = 14120007;

    //Attacks
    public static final int QUINTUPLE_STAR = 14121001;
    public static final int QUINTUPLE_STAR_FINISHER = 14121002;

    public static final int QUAD_STAR = 14111020;
    public static final int QUAD_STAR_FINISHER = 14111021;

    public static final int TRIPLE_THROW = 14101020;
    public static final int TRIPLE_THROW_FINISHER = 14101021;

    private int[] addedSkills = new int[] {
            ELEMENTAL_HARMONY_LUK,
            IMPERIAL_RECALL,
            ELEMENTAL_EXPERT,
            ELEMENTAL_SLASH,
            NOBLE_MIND,
            ELEMENTAL_SHIFT,
            ELEMENTAL_SHIFT2,
    };

    private int[] buffs = new int[] {
            DARK_ELEMENTAL,
            HASTE,
            DARK_SIGHT,
            SHADOW_BAT,
            THROWING_BOOSTER,
            DARK_SERVANT,
            SPIRIT_PROJECTION,
            DARKNESS_ASCENDING,
            CALL_OF_CYGNUS_NW,
            DARK_OMEN,
            GLORY_OF_THE_GUARDIANS_NW,
            SHADOW_ILLUSION,
    };

    private int[] darkEleSkills = new int[] {
            DARK_ELEMENTAL,
            ADAPTIVE_DARKNESS,
            ADAPTIVE_DARKNESS_II,
            ADAPTIVE_DARKNESS_III,
    };

    private int[] batSkills = new int[] {
            SHADOW_BAT,
            BAT_AFFINITY,
            BAT_AFFINITY_II,
            BAT_AFFINITY_III,
    };

    private List<Summon> bats = new ArrayList<>();
    private Summon darkServant;

    public NightWalker(Char chr) {
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
        return JobConstants.isNightWalker(id);
    }



    // Buff related methods --------------------------------------------------------------------------------------------

    public void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Summon summon;
        Field field = chr.getField();
        switch (skillID) {
            case DARK_ELEMENTAL:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ElementDarkness, o1);
                break;
            case HASTE:
                o1.nOption = si.getValue(speed, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Speed, o1);
                o2.nOption = si.getValue(jump, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Jump, o2);
                o3.nOption = si.getValue(er, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EVAR, o3);
                break;
            case DARK_SIGHT:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DarkSight, o1);
                break;
            case THROWING_BOOSTER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case DARK_SERVANT:
                applyDarkServant();
                break;
            case SPIRIT_PROJECTION:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NoBulletConsume, o1);
                break;
            case DARKNESS_ASCENDING:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ReviveOnce, o1);
                break;
            case CALL_OF_CYGNUS_NW:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;
            case SHADOW_BAT:
                if(tsm.hasStatBySkillId(skillID)) {
                    tsm.removeStatsBySkill(skillID);
                    tsm.sendResetStatPacket();
                } else {
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    o1.tOption = 0;
                    tsm.putCharacterStatValue(NightWalkerBat, o1);
                }
                break;
            case GLORY_OF_THE_GUARDIANS_NW:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case SHADOW_ILLUSION:
                if(chr.getField().getSummons().stream()
                        .anyMatch(l -> l.getChr() == chr &&
                                l.getSkillID() == DARK_SERVANT)
                        ) {
                    tsm.removeStatsBySkill(DARK_SERVANT);
                    c.getChr().getField().broadcastPacket(Summoned.summonedRemoved(darkServant, LeaveType.ANIMATION));
                }
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ShadowIllusion, o1);
                for(int i = skillID; i < skillID+3; i++) {
                    summon = Summon.getSummonBy(c.getChr(), i, slv);
                    summon.setFlyMob(false);
                    summon.setAvatarLook(chr.getAvatarData().getAvatarLook());
                    darkServant.setAvatarLook(chr.getAvatarData().getAvatarLook());
                    darkServant.setMoveAbility(MoveAbility.WalkClone);
                    darkServant.setAssistType(AssistType.None);
                    field.spawnSummon(summon);
                }
                if(chr.hasSkill(DARK_SERVANT)) {
                    EventManager.addEvent(this::applyDarkServant, si.getValue(time, slv) * 1001, TimeUnit.MILLISECONDS);
                }
                break;
            case DARK_OMEN:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(false);
                summon.setMoveAbility(MoveAbility.Stop);
                field.spawnSummon(summon);
                break;
        }
        tsm.sendSetStatPacket();
    }

    public boolean isBuff(int skillID) {
        return super.isBuff(skillID) || Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    private void applyDarkServant() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(DARK_SERVANT);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();
        Option o1 = new Option();


        o1.nOption = si.getValue(x, slv);
        o1.rOption = skill.getSkillId();
        o1.tOption = si.getValue(time, slv);
        tsm.putCharacterStatValue(ShadowServant, o1);
        tsm.sendSetStatPacket();

        darkServant = Summon.getSummonBy(c.getChr(), skill.getSkillId(), slv);
        Field field = c.getChr().getField();
        darkServant.setFlyMob(false);
        darkServant.setAvatarLook(chr.getAvatarData().getAvatarLook());
        darkServant.setMoveAbility(MoveAbility.WalkClone);
        field.spawnSummon(darkServant);
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
        if(hasHitMobs) {
            // Handling Shadow Bats
            if(attackInfo.skillId != SHADOW_BAT_ATOM) {
                shadowBats(attackInfo);
            } else {
                recreateShadowBatForceAtom();
            }

            // Handling Dark Elemental
            if(tsm.hasStat(ElementDarkness)) {
                applyDarkElementalOnMob(attackInfo, slv);
            }
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        switch (attackInfo.skillId) {
            case SHADOW_STITCH:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if(mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 1;
                    o1.rOption = skill.getSkillId();
                    o1.tOption = (si.getValue(time, slv) + attackInfo.mobAttackInfo.size() > 25 ? 25 : si.getValue(time, slv) + attackInfo.mobAttackInfo.size());
                    mts.addStatOptionsAndBroadcast(MobStat.Freeze, o1);
                }
                break;
            case DOMINION:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NotDamaged, o1);
                o2.nOption = 100;
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CriticalBuff, o2);
                o3.nOption = 100;
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Stance, o3);
                o4.nReason = skillID;
                o4.nValue = 20;
                o4.tStart = (int) System.currentTimeMillis();
                o4.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o4);
                tsm.sendSetStatPacket();
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    // handling Shadow Bats
    private void shadowBats(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(!tsm.hasStat(NightWalkerBat)) {
            return;
        }
        for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
            if (mob == null) {
                continue;
            }
            MobTemporaryStat mts = mob.getTemporaryStat();
            // Remove Bats & Create ForceAtom
            if (bats.size() > 0) {
                for(Iterator<Summon> it = bats.iterator(); it.hasNext();) {
                    Summon bat = it.next();
                    if (Util.succeedProp((mts.hasCurrentMobStat(MobStat.ElementDarkness) ? 2*getBatAttackProp() : getBatAttackProp()))) {
                        it.remove();
                        chr.getField().broadcastPacket(Summoned.summonedRemoved(bat, LeaveType.ANIMATION));
                        createShadowBatForceAtom(attackInfo);
                    }
                }
            }

            // Spawn Bat
            if (bats.size() < getMaxBats() && Util.succeedProp(33)) {
                summonBatAndRegister();
            }
        }
    }

    private void createShadowBatForceAtom(AttackInfo attackInfo) {
        SkillInfo si = SkillData.getSkillInfoById(getBatSkill().getSkillId());
        Mob mob = (Mob) chr.getField().getLifeByObjectID(Util.getRandomFromCollection(attackInfo.mobAttackInfo).mobId);

        if(mob == null) {
            Rect rect = new Rect( // Skill itself doesn't hold any rect info
                    new Position(
                            chr.getPosition().getX() - 500,
                            chr.getPosition().getY() - 500),
                    new Position(
                            chr.getPosition().getX() + 500,
                            chr.getPosition().getY() + 500)
            );
            if(chr.getField().getMobsInRect(rect).size() <= 0) {
                return;
            }
            mob = Util.getRandomFromCollection(chr.getField().getMobsInRect(rect));
        }
        int mobId = mob.getObjectId();

        int inc = ForceAtomEnum.NIGHT_WALKER_FROM_MOB.getInc();
        int type = ForceAtomEnum.NIGHT_WALKER_FROM_MOB.getForceAtomType();

        if(getBatSkill().getSkillId() == BAT_AFFINITY_III) {
            inc = ForceAtomEnum.NIGHT_WALKER_FROM_MOB_4.getInc();
            type = ForceAtomEnum.NIGHT_WALKER_FROM_MOB_4.getForceAtomType();
        }

        ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 2, 1,
                ((chr.getPosition().getX() > mob.getPosition().getX()) ? 90 : 270), 0, (int) System.currentTimeMillis(), 1, 0,
                new Position());

        chr.getField().broadcastPacket(CField.createForceAtom(false, 0, chr.getId(), type,
                true, mobId, SHADOW_BAT_ATOM, forceAtomInfo, new Rect(), ((chr.getPosition().getX() < mob.getPosition().getX()) ? 90 : 270), 30,
                mob.getPosition(), SHADOW_BAT_ATOM, mob.getPosition()));
    }

    private void recreateShadowBatForceAtom() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(!tsm.hasStat(NightWalkerBat)) {
            return;
        }
        if(Util.succeedProp(45)) {
            Rect rect = new Rect( // Skill itself doesn't hold any rect info
                    new Position(
                            chr.getPosition().getX() - 500,
                            chr.getPosition().getY() - 500),
                    new Position(
                            chr.getPosition().getX() + 500,
                            chr.getPosition().getY() + 500)
            );
            List<Mob> mobs = chr.getField().getMobsInRect(rect);
            if (mobs.size() <= 0) {
                return;
            }
            Mob mob = Util.getRandomFromCollection(mobs);
            int mobId = mob.getObjectId();

            int inc = ForceAtomEnum.NIGHT_WALKER_FROM_MOB.getInc();
            int type = ForceAtomEnum.NIGHT_WALKER_FROM_MOB.getForceAtomType();

            if (getBatSkill().getSkillId() == BAT_AFFINITY_III) {
                inc = ForceAtomEnum.NIGHT_WALKER_FROM_MOB_4.getInc();
                type = ForceAtomEnum.NIGHT_WALKER_FROM_MOB_4.getForceAtomType();
            }

            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 2, 1,
                    ((chr.getPosition().getX() > mob.getPosition().getX()) ? 90 : 270), 0, (int) System.currentTimeMillis(), 1, 0,
                    new Position());

            chr.getField().broadcastPacket(CField.createForceAtom(true, mobId, 0, type,
                    true, mobId, SHADOW_BAT_ATOM, forceAtomInfo, new Rect(), ((chr.getPosition().getX() < mob.getPosition().getX()) ? 90 : 270), 30,
                    mob.getPosition(), SHADOW_BAT_ATOM, mob.getPosition()));
        }
    }

    private int getMaxBats() {
        int maxBats = 0;
        for(int batSkill : batSkills) {
            Skill skill = chr.getSkill(batSkill);
            if(skill == null) {
                continue;
            }
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            byte slv = (byte) skill.getCurrentLevel();
            maxBats += si.getValue(y, slv);
        }
        return maxBats;
    }

    private int getBatAttackProp() {
        int batAttackProp = 0;
        for(int batSkill : batSkills) {
            Skill skill = chr.getSkill(batSkill);
            if(skill == null) {
                continue;
            }
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            byte slv = (byte) skill.getCurrentLevel();
            batAttackProp += si.getValue(prop, slv);
        }
        return batAttackProp;
    }

    private Skill getBatSkill() {
        Skill skill = null;
        for(int batSkill : batSkills) {
            if(chr.hasSkill(batSkill)) {
                skill = chr.getSkill(batSkill);
            }
        }
        return skill;
    }

    private Summon summonBatAndRegister() {
        Summon bat = Summon.getSummonBy(chr, getBatSkill().getSkillId(), (byte) getBatSkill().getCurrentLevel());
        bat.setFlyMob(true);
        bat.setMoveAbility(MoveAbility.Fly);
        bat.setAttackActive(false);
        chr.getField().spawnAddSummon(bat);
        bats.add(bat);

        return bat;
    }

    private void applyDarkElementalOnMob(AttackInfo attackInfo, byte slv) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(tsm.hasStat(ElementDarkness)) {
            Option o1 = new Option();
            Option o2 = new Option();
            int amount = 1;
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Skill skill = chr.getSkill(DARK_ELEMENTAL);
                if (Util.succeedProp(getDarkEleProp())) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if(mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();

                    if (mts.hasCurrentMobStat(MobStat.ElementDarkness)) {
                        amount = mts.getCurrentOptionsByMobStat(MobStat.ElementDarkness).nOption;
                        if (amount < getMaxDarkEleStack()) {
                            amount++;
                        }
                    }

                    // if dominion is active, instantly gain max stack
                    if(tsm.getOptByCTSAndSkill(Stance, DOMINION) != null) {
                        amount = getMaxDarkEleStack();
                    }

                    o1.nOption = amount;
                    o1.rOption = DARK_ELEMENTAL;
                    o1.tOption = 15;
                    mts.addStatOptionsAndBroadcast(MobStat.ElementDarkness, o1);

                    o2.nOption = 1;
                    o2.rOption = DARK_ELEMENTAL;
                    o2.tOption = 15; //si.getValue(subTime, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.Blind, o2);  //To Show the Stack Effect

                    mts.createAndAddBurnedInfo(chr, skill);

                    //handle Vitality Siphon
                    if (chr.hasSkill(VITALITY_SIPHON)) {
                        incrementSiphonVitality(tsm);
                    }
                }
            }
        }
    }

    private int getMaxDarkEleStack() {
        int maxStack = 0;
        for(int darkEleSkill : darkEleSkills) {
            if(chr.hasSkill(darkEleSkill)) {
                Skill skill = chr.getSkill(darkEleSkill);
                SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                byte slv = (byte) skill.getCurrentLevel();
                maxStack += si.getValue(x, slv);
            }
        }
        return maxStack;
    }

    private Skill getDarkElementalSkill() {
        Skill skill = null;
        for(int darkEleSkill : darkEleSkills) {
            if(chr.hasSkill(darkEleSkill)) {
                skill = chr.getSkill(darkEleSkill);
            }
        }
        return skill;
    }

    private int getDarkEleProp() {
        int proc = 0;
        for(int darkEleSkill : darkEleSkills) {
            if(chr.hasSkill(darkEleSkill)) {
                Skill skill = chr.getSkill(darkEleSkill);
                SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                byte slv = (byte) skill.getCurrentLevel();
                proc += si.getValue(prop, slv);
            }
        }
        return proc;
    }

    private void incrementSiphonVitality(TemporaryStatManager tsm) {
        Option o = new Option();
        Option o1 = new Option();
        SkillInfo siphonInfo = SkillData.getSkillInfoById(VITALITY_SIPHON);
        Skill skill = chr.getSkill(VITALITY_SIPHON);
        byte slv = (byte) skill.getCurrentLevel();
        int amount = 1;
        if(tsm.hasStat(ElementDarkness)) {
            if (tsm.hasStat(SiphonVitality)) {
                amount = tsm.getOption(SiphonVitality).nOption;
                if (amount < getMaxSiphon()) {
                    amount++;
                }
            }

            // if dominion is active, instantly gain max stack
            if(tsm.getOptByCTSAndSkill(Stance, DOMINION) != null) {
                amount = getMaxSiphon();
            }

            o.nOption = amount;
            o.rOption = VITALITY_SIPHON;
            o.tOption = siphonInfo.getValue(time, slv);
            tsm.putCharacterStatValue(SiphonVitality, o);
            o1.nOption = (amount * siphonInfo.getValue(y, slv));
            o1.rOption = VITALITY_SIPHON;
            o1.tOption = siphonInfo.getValue(time, slv);
            tsm.putCharacterStatValue(IncMaxHP, o1);
            tsm.sendSetStatPacket();
        }
    }

    private int getMaxSiphon() {
        Skill skill = null;
        if (chr.hasSkill(14120009)) {
            skill = chr.getSkill(14120009);
        }
        return skill == null ? 0 : SkillData.getSkillInfoById(skill.getSkillId()).getValue(x, skill.getCurrentLevel());
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

    public static void reviveByDarknessAscending(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        chr.heal(chr.getMaxHP());
        tsm.removeStatsBySkill(DARKNESS_ASCENDING);
        tsm.sendResetStatPacket();
        chr.chatMessage("You have been revived by Darkness Ascending.");
    }
}
