package net.swordie.ms.client.jobs;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.client.character.ExtendSP;
import net.swordie.ms.client.character.SPSet;
import net.swordie.ms.client.character.avatar.AvatarLook;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.SkillStat;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.User;
import net.swordie.ms.connection.packet.UserRemote;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.enums.Stat;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Zero extends Job {

    public static final int DUAL_COMBAT = 100001270;
    public static final int DUAL_COMBAT_2 = 100000282;
    public static final int TEMPLE_RECALL = 100001262;
    public static final int RESOLUTION_TIME = 100000279;
    public static final int BURST_JUMP = 100001265;
    public static final int BURST_STEP = 100001266;
    public static final int BURST_LEAP = 100001269;


    public static final int DIVINE_FORCE = 100001263; //Aura (Unlimited Duration)
    public static final int DIVINE_SPEED = 100001264; //Aura (Unlimited Duration)
    public static final int RHINNES_PROTECTION = 100001268; //Buff

    public static final int TIME_HOLDING = 100001274;
    public static final int TIME_HOLDING_2 = 100001281;
    public static final int TIME_DISTORTION = 100001261;
    public static final int REWIND = 100001272;
    public static final int FOCUSED_TIME = 100001005;
    public static final int DOUBLE_TIME = 100000267;
    public static final int DOUBLE_TIME_ALPHA = 100000276;
    public static final int DOUBLE_TIME_BETA = 100000277;

    public static final int AIR_RIOT = 101000101; //Special Attack (Stun Debuff)
    public static final int THROWING_WEAPON = 101100100; //Special Attack (Throw Sword)
    public static final int ADVANCED_THROWING_WEAPON = 101100101; //Special Attack (Throw Sword)
    public static final int STORM_BREAK = 101120202;
    public static final int STORM_BREAK_INIT = 101120203;
    public static final int ADV_EARTH_BREAK = 101120104;
    public static final int ADV_STORM_BREAK = 101120204;
    public static final int ADV_EARTH_BREAK_SHOCK_INIT = 101120105; //Attack to initialise the Shockwave
    public static final int ADV_STORM_BREAK_SHOCK_INIT = 101120205; //Attack to initialise the Shockwave
    public static final int ADV_EARTH_BREAK_SHOCKWAVE = 101120106; //Tile Skill
    public static final int ADV_STORM_BREAK_SHOCKWAVE = 101120206; //Tile Skill
    public static final int DIVINE_LEER = 101120207;
    public static final int CRITICAL_BIND = 101120110;
    public static final int IMMUNE_BARRIER = 101120109;
    public static final int ARMOR_SPLIT = 101110103;

    private int[] addedSkills = new int[] {
            DUAL_COMBAT,
            DUAL_COMBAT_2,
            TEMPLE_RECALL,
            RESOLUTION_TIME,
            BURST_STEP,
            BURST_JUMP,
            BURST_LEAP,
    };

    private int[] buffs = new int[] {
            DIVINE_FORCE,
            DIVINE_SPEED,
            RHINNES_PROTECTION,
            TIME_HOLDING,
            REWIND,
            FOCUSED_TIME,
    };

    private int doubleTimePrevSkill = 0;

    public static int getAlphaOrBetaSkill(int skillID) {
        switch(skillID) {
            case 101001200: //Moon Strike
            case 101000200: //Piercing Thrust
            case 101000201: //Shadow Strike
            case 101000202: //Shadow Strike

            case 101101200: //Flash Assault
            case 101100200: //Spin Cutter
            case 101100201: //Adv Spin Cutter
            case 101100202: //Adv Blade Ring

            case 101110200: //Grand Rolling Cross
            case 101110201: //Grand Rolling Cross
            case 101111200: //Rolling Cross
            case 101110202: //Rolling Assault
            case 101110203: //Advanced Rolling Assault
            case 101110204: //Advanced Rolling Assault

            case 101120200: //Wind Cutter
            case 101120201: //Wind Striker
            case 101120202: //Storm Break
            case 101120203: //Storm Break
            case 101120204: //Advanced Storm Break
            case 101120205: //Severe Storm Break (Tile)
            case 101120206: //Severe Storm Break
            case 101121101: //Hurricane Wind
            case 101121200: //Wind Cutter:
                return 1; //Alpha skills

            case 101001100: //Rising Slash
            case 101000100: //Air Raid
            case 101000101: //Air Riot
            case 101000102: //Air Riot

            case 101101100: //Flash Cut
            case 101100100: //Throwing Weapon
            case 101100101: //Adv. Throwing Weapon

            case 101111100: //Spin Driver
            case 101110101: //Wheel Wind
            case 101110102: //Adv Wheel Wind
            case 101110104: //Adv Blade Tempest

            case 101121100: //Giga Crash
            case 101120100: //Falling Star
            case 101120101: //Falling Star
            case 101120102: //Earth Break
            case 101120103: //Groundbreaker
            case 101120104: //Adv Earth Break
            case 101120105: //Mega Groundbreaker (Tile)
                return 2; //Beta skills

        }
        return skillID; // no original skill linked with this one
    }

    public Zero(Char chr) {
        super(chr);
        if(chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
            if (chr.getZeroInfo() == null) {
                chr.initZeroInfo();
            }
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isZero(id);
    }

    private boolean isBeta() {
        return chr.getZeroInfo().isZeroBetaState();
    }

    @Override
    public void handleLevelUp() {
        short level = chr.getLevel();
        chr.addStat(Stat.mhp, 500);
        chr.addStat(Stat.ap, 5);
        int sp = 3;
        if (level > 100 && (level % 10) % 3 == 0) {
            sp = 6; // double sp on levels ending in 3/6/9
        }
        ExtendSP esp = chr.getAvatarData().getCharacterStat().getExtendSP();
        SPSet alphaSpSet = esp.getSpSet().get(0);
        SPSet betaSpSet = esp.getSpSet().get(1);
        alphaSpSet.addSp(sp);
        betaSpSet.addSp(sp);
        Map<Stat, Object> stats = new HashMap<>();
        stats.put(Stat.mhp, chr.getStat(Stat.mhp));
        stats.put(Stat.mmp, chr.getStat(Stat.mmp));
        stats.put(Stat.ap, (short) chr.getStat(Stat.ap));
        stats.put(Stat.sp, chr.getAvatarData().getCharacterStat().getExtendSP());
        chr.write(WvsContext.statChanged(stats));
        byte linkSkillLevel = (byte) SkillConstants.getLinkSkillLevelByCharLevel(level);
        int linkSkillID = SkillConstants.getOriginalOfLinkedSkill(SkillConstants.getLinkSkillByJob(chr.getJob()));
        if (linkSkillID != 0 && linkSkillLevel > 0) {
            Skill skill = chr.getSkill(linkSkillID, true);
            if (skill.getCurrentLevel() != linkSkillLevel) {
                chr.addSkill(linkSkillID, linkSkillLevel, 3);
            }
        }
    }

    @Override
    public void setCharCreationStats(Char chr) {
        AvatarLook mainLook = chr.getAvatarData().getAvatarLook();
        chr.getAvatarData().setZeroAvatarLook(mainLook.deepCopy());
        AvatarLook zeroLook = chr.getAvatarData().getZeroAvatarLook();
        mainLook.getHairEquips().remove(new Integer(1562000));
        zeroLook.getHairEquips().remove(new Integer(1572000));
        zeroLook.setWeaponId(1562000);
        zeroLook.setGender(1);
        zeroLook.setSkin(chr.getAvatarData().getAvatarLook().getSkin());
        zeroLook.setFace(21290);
        zeroLook.setHair(37623);
        zeroLook.setZeroBetaLook(true);
        CharacterStat cs = chr.getAvatarData().getCharacterStat();
        cs.setLevel(100);
        cs.setStr(518);
        cs.setHp(5000);
        cs.setMaxHp(5000);
        cs.setMp(100);
        cs.setMaxMp(100);
        cs.setJob(10112);
        cs.setPosMap(100000000);
        ExtendSP esp = chr.getAvatarData().getCharacterStat().getExtendSP();
        SPSet alphaSpSet = esp.getSpSet().get(0);
        SPSet betaSpSet = esp.getSpSet().get(1);
        alphaSpSet.addSp(6);
        betaSpSet.addSp(6);
        Map<Stat, Object> stats = new HashMap<>();
        stats.put(Stat.mhp, chr.getStat(Stat.mhp));
        stats.put(Stat.mmp, chr.getStat(Stat.mmp));
        stats.put(Stat.ap, (short) chr.getStat(Stat.ap));
        stats.put(Stat.sp, chr.getAvatarData().getCharacterStat().getExtendSP());
        chr.write(WvsContext.statChanged(stats));
    }



    // Buff related methods --------------------------------------------------------------------------------------------

    public void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        Option o5 = new Option();
        Option o6 = new Option();
        Option o7 = new Option();
        switch (skillID) {
            case DIVINE_FORCE:
                if(tsm.hasStatBySkillId(skillID)) {
                    tsm.removeStatsBySkill(skillID);
                } else {
                    o1.nReason = skillID;
                    o1.nValue = si.getValue(indieAsrR, slv);
                    o1.tStart = (int) System.currentTimeMillis();
                    o1.tTerm = 0;
                    tsm.putCharacterStatValue(IndieAsrR, o1); //Indie
                    o2.nReason = skillID;
                    o2.nValue = si.getValue(indieMad, slv);
                    o2.tStart = (int) System.currentTimeMillis();
                    o2.tTerm = 0;
                    tsm.putCharacterStatValue(IndieMAD, o2); //Indie
                    o3.nReason = skillID;
                    o3.nValue = si.getValue(indiePad, slv);
                    o3.tStart = (int) System.currentTimeMillis();
                    o3.tTerm = 0;
                    tsm.putCharacterStatValue(IndiePAD, o3); //Indie
                    o4.nReason = skillID;
                    o4.nValue = si.getValue(indieMdd, slv);
                    o4.tStart = (int) System.currentTimeMillis();
                    o4.tTerm = 0;
                    tsm.putCharacterStatValue(IndieMDD, o4); //Indie
                    o5.nReason = skillID;
                    o5.nValue = si.getValue(indiePdd, slv);
                    o5.tStart = (int) System.currentTimeMillis();
                    o5.tTerm = 0;
                    tsm.putCharacterStatValue(IndiePDD, o5); //Indie
                    o6.nReason = skillID;
                    o6.nValue = si.getValue(indieTerR, slv);
                    o6.tStart = (int) System.currentTimeMillis();
                    o6.tTerm = 0;
                    tsm.putCharacterStatValue(IndieTerR, o6); //Indie
                    o7.nOption = 1;
                    o7.rOption = skillID;
                    o7.tOption = 0;
                    tsm.putCharacterStatValue(ZeroAuraStr, o7);
                }
                break;
            case DIVINE_SPEED:
                if(tsm.hasStatBySkillId(skillID)) {
                    tsm.removeStatsBySkill(skillID);
                } else {
                    o1.nReason = skillID;
                    o1.nValue = si.getValue(indieAcc, slv);
                    o1.tStart = (int) System.currentTimeMillis();
                    o1.tTerm = si.getValue(time, slv);
                    tsm.putCharacterStatValue(IndieACC, o1); //Indie
                    o2.nReason = skillID;
                    o2.nValue = si.getValue(indieBooster, slv);
                    o2.tStart = (int) System.currentTimeMillis();
                    o2.tTerm = si.getValue(time, slv);
                    tsm.putCharacterStatValue(IndieBooster, o2); //Indie
                    o3.nReason = skillID;
                    o3.nValue = si.getValue(indieEva, slv);
                    o3.tStart = (int) System.currentTimeMillis();
                    o3.tTerm = si.getValue(time, slv);
                    tsm.putCharacterStatValue(IndieEVA, o3); //Indie
                    o4.nReason = skillID;
                    o4.nValue = si.getValue(indieJump, slv);
                    o4.tStart = (int) System.currentTimeMillis();
                    o4.tTerm = si.getValue(time, slv);
                    tsm.putCharacterStatValue(IndieJump, o4); //Indie
                    o5.nReason = skillID;
                    o5.nValue = si.getValue(indieSpeed, slv);
                    o5.tStart = (int) System.currentTimeMillis();
                    o5.tTerm = si.getValue(time, slv);
                    tsm.putCharacterStatValue(IndieSpeed, o5); //Indie
                    o6.nOption = 1;
                    o6.rOption = skillID;
                    o6.tOption = 0;
                    tsm.putCharacterStatValue(ZeroAuraSpd, o6);
                }
                break;
            case RHINNES_PROTECTION:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;

            case TIME_HOLDING:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NotDamaged, o1);

                if(chr.getStat(Stat.level) >= 200) {
                    o2.nOption = si.getValue(y, slv);
                    o2.rOption = TIME_HOLDING_2;
                    o2.tOption = si.getValue(x, slv);
                    tsm.putCharacterStatValue(DamR, o2);
                    o3.nReason = TIME_HOLDING_2;
                    o3.nValue = si.getValue(z, slv);
                    o3.tStart = (int) System.currentTimeMillis();
                    o3.tTerm = si.getValue(x, slv);
                    tsm.putCharacterStatValue(IndieMaxDamageOverR, o3);
                }

                for (int skillId : chr.getSkillCoolTimes().keySet()) {
                    if (!SkillData.getSkillInfoById(skillId).isNotCooltimeReset()) {
                        chr.resetSkillCoolTime(skillId);
                    }
                }
                break;
            case REWIND:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ReviveOnce, o1);
                break;
            case FOCUSED_TIME:
                o1.nReason = skillID;
                o1.nValue = 4;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = 2400;
                tsm.putCharacterStatValue(IndiePADR, o1); //Indie
                o2.nReason = skillID;
                o2.nValue = 4;
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = 2400;
                tsm.putCharacterStatValue(IndieMADR, o2); //Indie
                break;
        }
        tsm.sendSetStatPacket();
    }

    public boolean isBuff(int skillID) {
        return super.isBuff(skillID) || Arrays.stream(buffs).anyMatch(b -> b == skillID);
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

        if(getAlphaOrBetaSkill(skillID) == 1) {
            if(hasHitMobs) {
                incrementDoubleTimeAlpha(skillID);
            }

            applyDivineLeerOnMob(attackInfo, skillID);
        }

        if(getAlphaOrBetaSkill(skillID) == 2) {
            if(hasHitMobs) {
                incrementDoubleTimeBeta(skillID);
            }

            applyCriticalBindOnMob(attackInfo, skillID);
            applyArmorSplitOnMob(attackInfo, skillID);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case AIR_RIOT:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skillID;
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case ADV_EARTH_BREAK_SHOCK_INIT:
                slv = (byte) chr.getSkill(ADV_EARTH_BREAK).getCurrentLevel();
                SkillInfo fci = SkillData.getSkillInfoById(ADV_EARTH_BREAK);
                AffectedArea aa = AffectedArea.getPassiveAA(chr, ADV_EARTH_BREAK, slv);
                aa.setMobOrigin((byte) 0);
                aa.setPosition(chr.getPosition());
                aa.setSkillID(ADV_EARTH_BREAK);
                aa.setRect(aa.getPosition().getRectAround(fci.getRects().get(0)));
                aa.setDuration(fci.getValue(v, slv) * 1000);
                chr.getField().spawnAffectedArea(aa);
                break;

        }
        super.handleAttack(c, attackInfo);
    }

    private void applyDivineLeerOnMob(AttackInfo ai, int skillID) {
        Skill skill = chr.getSkill(DIVINE_LEER);
        if (skill == null) {
            return;
        }
        byte slv = (byte) skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(DIVINE_LEER);
        for (MobAttackInfo mai : ai.mobAttackInfo) {
            if (Util.succeedProp(si.getValue(prop, slv))) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                mob.getTemporaryStat().createAndAddBurnedInfo(chr, skill);
            }
        }
    }

    private void applyCriticalBindOnMob(AttackInfo ai, int skillID) {
        Skill skill = chr.getSkill(CRITICAL_BIND);
        if (skill == null) {
            return;
        }
        byte slv = (byte) skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(CRITICAL_BIND);
        for (MobAttackInfo mai : ai.mobAttackInfo) {
            if (Util.succeedProp(si.getValue(prop, slv))) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                MobTemporaryStat mts = mob.getTemporaryStat();
                Option o = new Option();
                Option o1 = new Option();
                o.nOption = 1;
                o.rOption = CRITICAL_BIND;
                o.tOption = 4;
                mts.addStatOptionsAndBroadcast(MobStat.Freeze, o);
                o1.nOption = si.getValue(SkillStat.x, slv);
                o1.rOption = skillID;
                o1.tOption = 4;//   si.getValue(time, slv);
                mts.addStatOptionsAndBroadcast(MobStat.AddDamParty, o1);
            }
        }
    }

    private void applyArmorSplitOnMob(AttackInfo ai, int skillID) {
        Skill skill = chr.getSkill(ARMOR_SPLIT);
        if (skill == null) {
            return;
        }
        byte slv = (byte) skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(ARMOR_SPLIT);
        int amount = 1;
        for (MobAttackInfo mai : ai.mobAttackInfo) {
            if (Util.succeedProp(si.getValue(prop, slv))) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                MobTemporaryStat mts = mob.getTemporaryStat();
                Option o = new Option();
                if (mts.hasCurrentMobStat(MobStat.MultiPMDR)) {
                    amount = mts.getCurrentOptionsByMobStat(MobStat.MultiPMDR).cOption;
                    if (amount < si.getValue(x, slv)) {
                        amount++;
                    }
                }
                o.nOption = si.getValue(y, slv) * amount;
                o.rOption = ARMOR_SPLIT;
                o.tOption = si.getValue(time, slv);
                o.cOption = amount;
                mts.addStatOptionsAndBroadcast(MobStat.MultiPMDR, o);
            }
        }
    }

    private void incrementDoubleTimeAlpha(int skillID) {
        if (chr.hasSkill(DOUBLE_TIME)) {
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            Option o = new Option();
            Option o1 = new Option();
            SkillInfo si = SkillData.getSkillInfoById(DOUBLE_TIME_ALPHA);
            int amount = 1;
            if (tsm.hasStat(TimeFastABuff)) {
                if (doubleTimePrevSkill == skillID) {
                    return;
                }
                amount = tsm.getOption(TimeFastABuff).nOption;
                if (amount < 10) {
                    amount++;
                }
            }
            doubleTimePrevSkill = skillID;
            o.nOption = amount;
            o.rOption = DOUBLE_TIME_ALPHA;
            o.tOption = 20;
            tsm.putCharacterStatValue(TimeFastABuff, o);
            tsm.sendSetStatPacket();
        }
    }

    private void incrementDoubleTimeBeta(int skillID) {
        if (chr.hasSkill(DOUBLE_TIME)) {
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            Option o = new Option();
            Option o1 = new Option();
            SkillInfo si = SkillData.getSkillInfoById(DOUBLE_TIME_BETA);
            int amount = 1;
            if (tsm.hasStat(TimeFastBBuff)) {
                if (doubleTimePrevSkill == skillID) {
                    return;
                }
                amount = tsm.getOption(TimeFastBBuff).nOption;
                if (amount < 10) {
                    amount++;
                }
            }
            doubleTimePrevSkill = skillID;
            o.nOption = amount;
            o.rOption = DOUBLE_TIME_BETA;
            o.tOption = 20;
            tsm.putCharacterStatValue(TimeFastBBuff, o);
            tsm.sendSetStatPacket();
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
                case THROWING_WEAPON:
                case ADVANCED_THROWING_WEAPON:
                    Summon summon = Summon.getSummonBy(chr, skillID, slv);
                    summon.setFlyMob(true);
                    summon.setMoveAbility(MoveAbility.FixVMove);
                    chr.getField().spawnSummon(summon);
                    break;
                case TEMPLE_RECALL:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = chr.getOrCreateFieldByCurrentInstanceType(o1.nValue);
                    chr.warp(toField);
                    break;
                case TIME_DISTORTION:
                    AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                    aa.setMobOrigin((byte) 0);
                    aa.setPosition(chr.getPosition());
                    aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                    aa.setDelay((short) 5);
                    chr.getField().spawnAffectedArea(aa);
                    break;
            }
        }
    }



    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill immuneBarrier = chr.getSkill(IMMUNE_BARRIER);
        if(immuneBarrier == null) {
            return;
        }
        byte slv = (byte) immuneBarrier.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(IMMUNE_BARRIER);
        if(Util.succeedProp(si.getValue(prop, slv))) {
            Option o = new Option(IMMUNE_BARRIER, slv);
            int max = (int) (chr.getStat(Stat.mhp) * (si.getValue(x, slv) / 100D));
            o.nOption = max;
            o.xOption = max;
            chr.getTemporaryStatManager().putCharacterStatValue(ImmuneBarrier, o);
        }
        if(tsm.hasStat(ImmuneBarrier)) {
            Option o = tsm.getOption(ImmuneBarrier);
            int maxSoakDamage = o.nOption;
            int newDamage = hitInfo.hpDamage - maxSoakDamage < 0 ? 0 : hitInfo.hpDamage - maxSoakDamage;
            o.nOption = maxSoakDamage - (hitInfo.hpDamage - newDamage); // update soak value
            hitInfo.hpDamage = newDamage;
            o.tOption = si.getValue(time, slv); //added duration
            tsm.putCharacterStatValue(ImmuneBarrier, o);
            tsm.sendSetStatPacket();
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    public static void reviveByRewind(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        chr.heal(chr.getMaxHP());
        tsm.removeStatsBySkill(REWIND);
        tsm.sendResetStatPacket();
        chr.write(User.effect(Effect.skillSpecial(REWIND)));
        chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillSpecial(REWIND)));
    }
}
