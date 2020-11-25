package net.swordie.ms.client.jobs.legend;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.User;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.connection.packet.UserRemote;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.enums.LarknessSkillType;
import net.swordie.ms.enums.Stat;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Util;

import java.util.Arrays;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;
import static net.swordie.ms.enums.LarknessSkillType.DARK;
import static net.swordie.ms.enums.LarknessSkillType.LIGHT;

/**
 * Created on 12/14/2017.
 */
public class Luminous extends Job {
    public static final int SUNFIRE = 20040216;
    public static final int ECLIPSE = 20040217;
    public static final int EQUILIBRIUM = 20040219;
    public static final int EQUILIBRIUM2 = 20040220; //test
    public static final int INNER_LIGHT = 20040221;
    public static final int FLASH_BLINK = 20041222;
    public static final int CHANGE_LIGHT_DARK = 20041239;

    public static final int MAGIC_BOOSTER = 27101004; //Buff
    public static final int BLACK_BLESSING = 27100003;
    public static final int PRESSURE_VOID = 27101202;

    public static final int SHADOW_SHELL = 27111004; //Buff
    public static final int RAY_OF_REDEMPTION = 27111101; // Attack + heals party members
    public static final int DUSK_GUARD = 27111005; //Buff
    public static final int PHOTIC_MEDITATION = 27111006; //Buff
    public static final int LUNAR_TIDE = 27110007;
    public static final int DEATH_SCYTHE = 27111303;

    public static final int DARK_CRESCENDO = 27121005; //Buff
    public static final int ARCANE_PITCH = 27121006; //Buff
    public static final int MAPLE_WARRIOR_LUMI = 27121009; //Buff
    public static final int ENDER = 27121303;
    public static final int DARKNESS_MASTERY = 27120008;
    public static final int HEROS_WILL_LUMI = 27121010;

    public static final int EQUALIZE = 27121054;
    public static final int HEROIC_MEMORIES_LUMI = 27121053;
    public static final int ARMAGEDDON = 27121052; //Stun debuff

    // Constants
    private static final int MAX_GAUGE = 10000;
    private static final int MIN_GAUGE = -1;

    private int[] addedSkills = new int[] {
            EQUILIBRIUM,
            //EQUILIBRIUM2,
            CHANGE_LIGHT_DARK
    };

    private final int[] buffs = new int[]{
            MAGIC_BOOSTER,
            SHADOW_SHELL,
            DUSK_GUARD,
            PHOTIC_MEDITATION,
            RAY_OF_REDEMPTION,
            DARK_CRESCENDO,
            ARCANE_PITCH,
            MAPLE_WARRIOR_LUMI,
            HEROIC_MEMORIES_LUMI,
            EQUALIZE,
    };

    private long darkCrescendoTimer;
    private ScheduledFuture equilibriumTimer;
    private int larkness = 5000;
    private LarknessSkillType larknessDiraction = LarknessSkillType.MIX;

    public Luminous(Char chr) {
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
        return JobConstants.isLuminous(id);
    }

    public void updateLarknessGague(int skillID) {
        if (chr.getJob() / 10 == 271) {
            TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
            boolean isLightSkill = SkillConstants.isLarknessLightSkill(skillID);
            boolean isDarkSkill = SkillConstants.isLarknessDarkSkill(skillID);
            if (larknessDiraction == LarknessSkillType.MIX) {
                int reason = 0;
                if (isLightSkill) {
                    larkness = 1;
                    larknessDiraction = LIGHT;
                    sendLarkness(tsm, ECLIPSE);
                } else if (isDarkSkill) {
                    larkness = 9999;
                    larknessDiraction = LarknessSkillType.DARK;
                    sendLarkness(tsm, SUNFIRE);
                }
                if (reason != 0) sendLarkness(tsm, reason);
            }

            if (isLightSkill && larknessDiraction == LarknessSkillType.DARK) {
                larkness -= getIncGague(skillID);
                if (larkness <= 1) {
                    larkness = 1;
                    larknessDiraction = LIGHT;
                    sendLarkness(tsm, ECLIPSE);
                }
            } else if (isDarkSkill && larknessDiraction == LIGHT) {
                larkness += getIncGague(skillID);
                if (larkness >= 9999) {
                    larkness = 9999;
                    larknessDiraction = LarknessSkillType.DARK;
                    sendLarkness(tsm, SUNFIRE);
                }
            }
            if(!tsm.hasStat(Larkness)) {
                switch (larknessDiraction) {
                    case LIGHT:
                        sendLarkness(tsm, SUNFIRE);
                        break;
                    case DARK:
                        sendLarkness(tsm, ECLIPSE);
                        break;
                }
            }
            sendIncLarknessResult();
        }
    }

    public void sendLarkness(TemporaryStatManager tsm, int reasonID) {
        Option o = new Option();
        o.nOption = 1;
        o.rOption = reasonID;
        tsm.putCharacterStatValue(Larkness, o);
        tsm.sendSetStatPacket();
    }

    public int getIncGague(int skillID) {
        // TODO: handle bonus inc in hyper skills/ vskills
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        return si.getValue(gauge, chr.getSkillLevel(skillID));
    }

    public void encodeLarkness(OutPacket outPacket) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        outPacket.encodeInt(tsm.getOption(Larkness).rOption);
        outPacket.encodeInt(200000000);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(MAX_GAUGE);
        outPacket.encodeInt(MIN_GAUGE);
    }

    public void sendIncLarknessResult() {
        chr.write(UserLocal.incLarknessReponse(larkness, larknessDiraction));
    }

    // Buff related methods --------------------------------------------------------------------------------------------

    public void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case MAGIC_BOOSTER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case SHADOW_SHELL:
                o1.nOption = 3;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                // no bOption for Luminous' AntiMagicShell
                tsm.putCharacterStatValue(AntiMagicShell, o1);
                break;
            case DUSK_GUARD:
                o1.nValue = si.getValue(indieMdd, slv);
                o1.nReason = skillID;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMDD, o1);
                o2.nValue = si.getValue(indiePdd, slv);
                o2.nReason = skillID;
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePDD, o2);
                break;
            case PHOTIC_MEDITATION:
                o1.nOption = si.getValue(emad, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EMAD, o1);
                break;
            case DARK_CRESCENDO:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                o1.mOption = 1;
                tsm.putCharacterStatValue(StackBuff, o1);
                darkCrescendoTimer = System.currentTimeMillis() + (si.getValue(time, slv) * 1000);
                break;
            case ARCANE_PITCH:
                o1.nOption = si.getValue(y, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ElementalReset, o1);
                break;
            case MAPLE_WARRIOR_LUMI:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case EQUALIZE:
                larknessDiraction = larknessDiraction == DARK ? LIGHT : DARK;
                sendIncLarknessResult();
                o1.nOption = 1;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(Larkness, o1);
                equilibriumTimer = EventManager.addEvent(this::changeMode, getMoreEquilibriumTime(), TimeUnit.SECONDS);
                chr.resetSkillCoolTime(ENDER);
                chr.resetSkillCoolTime(DEATH_SCYTHE);
                break;
            case HEROIC_MEMORIES_LUMI:
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

    private void giveLunarTideBuff() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(chr.hasSkill(LUNAR_TIDE)) {
            Option o1 = new Option();
            Option o2 = new Option();
            SkillInfo lti = SkillData.getSkillInfoById(LUNAR_TIDE);
            Skill skill = chr.getSkill(LUNAR_TIDE);
            byte slv = (byte) skill.getCurrentLevel();
            int maxMP = c.getChr().getStat(Stat.mmp);
            int curMP = c.getChr().getStat(Stat.mp);
            int maxHP = c.getChr().getStat(Stat.mhp);
            int curHP = c.getChr().getStat(Stat.hp);
            double ratioHP = ((double)curHP/maxHP);
            double ratioMP = ((double)curMP)/maxMP;

            //if (ratioHP > ratioMP) {
            if(ratioHP > ratioMP) {
                //Crit Rate      HP is Greater than MP
                o1.nOption = 2;
                o1.rOption = LUNAR_TIDE;
                o1.tOption = 0;
                tsm.putCharacterStatValue(LifeTidal, o1);
                o2.nOption = lti.getValue(prop, slv);     //only gives 10% for w/e reason but the SkillStat is correct
                o2.rOption = LUNAR_TIDE;
                o2.tOption = 0;
                tsm.putCharacterStatValue(CriticalBuff, o2);
            } else {
                //Damage         MP is Greater than HP
                o1.nOption = 1;
                o1.rOption = LUNAR_TIDE;
                o1.tOption = 0;
                tsm.putCharacterStatValue(LifeTidal, o1);
                o2.nOption = lti.getValue(x, slv);
                o2.rOption = LUNAR_TIDE;
                o2.tOption = 0;
                tsm.putCharacterStatValue(DamR, o2);
            }
            tsm.sendSetStatPacket();
        }
    }

    public static void changeBlackBlessingCount(Client c, boolean increment) {
        Char chr = c.getChr();
        Option o = new Option();
        Option o2 = new Option();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();

        int amount = 1;
        if(tsm.hasStat(BlessOfDarkness)) {
            amount = tsm.getOption(BlessOfDarkness).nOption;

            if(increment) {
                if (amount < 3) {
                    amount++;
                }
            } else {
                if (amount > 0) {
                    amount--;
                }
            }
        }

        int orbmad;
        switch (amount) {
            case 1:
                orbmad = 15;
                break;
            case 2:
                orbmad = 24;
                break;
            case 3:
                orbmad = 30;
                break;
            default:
                orbmad = 0;
                break;
        }

        if(amount > 0) {
            o.nOption = amount;
            o.rOption = BLACK_BLESSING;
            o.tOption = 0;
            tsm.putCharacterStatValue(BlessOfDarkness, o);
            o2.nOption = orbmad;
            o2.rOption = BLACK_BLESSING;
            o2.tOption = 0;
            tsm.putCharacterStatValue(MAD, o2);
            tsm.sendSetStatPacket();
        } else {
            tsm.removeStatsBySkill(BLACK_BLESSING);
            tsm.sendResetStatPacket();
        }
    }

    public void changeMode() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = 1;
        if (larknessDiraction == LarknessSkillType.DARK) {
            o.rOption = ECLIPSE;
        } else if (larknessDiraction == LIGHT) {
            o.rOption = SUNFIRE;
        }
        tsm.putCharacterStatValue(Larkness, o);
        tsm.sendSetStatPacket();
    }

    public int getMoreEquilibriumTime() {
        int eqTime = 20;
        SkillInfo eqi = SkillData.getSkillInfoById(DARKNESS_MASTERY);
        if(chr.hasSkill(DARKNESS_MASTERY)) {
            eqTime += eqi.getValue(time, eqi.getCurrentLevel());
            eqTime += 5;
        }
        return eqTime;
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
        int crescendoProp = getCrescendoProp();
        if (hasHitMobs) {
            updateLarknessGague(skillID);
            // Dark Crescendo
            if (tsm.hasStat(StackBuff)) {
                if (skill != null && Util.succeedProp(crescendoProp)) {
                    incrementDarkCrescendo(tsm);
                }
            }
        }
        giveLunarTideBuff();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case ARMAGEDDON:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 1;
                    o1.rOption = skill.getSkillId();
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }
                break;
            case RAY_OF_REDEMPTION:
                chr.heal(chr.getMaxHP()); // 800% Recovery
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    private void incrementDarkCrescendo(TemporaryStatManager tsm) {
        Option o = new Option();
        Option o1 = new Option();
        SkillInfo crescendoInfo = SkillData.getSkillInfoById(DARK_CRESCENDO);
        Skill skill = chr.getSkill(DARK_CRESCENDO);
        byte slv = (byte) skill.getCurrentLevel();
        int amount = 1;
        if(tsm.hasStat(StackBuff)) {
            amount = tsm.getOption(StackBuff).mOption;
            if(amount < getMaxDarkCrescendoStack()) {
                amount++;
            }
        }
        o.setInMillis(true);
        o.nOption = (amount * crescendoInfo.getValue(damR, slv));
        o.rOption = DARK_CRESCENDO;
        o.tOption = (int) (darkCrescendoTimer - System.currentTimeMillis());
        o.mOption = amount;
        tsm.putCharacterStatValue(StackBuff, o);
        tsm.sendSetStatPacket();
    }

    private int getCrescendoProp() {
        Skill skill = null;
        if (chr.hasSkill(DARK_CRESCENDO)) {
            skill = chr.getSkill(DARK_CRESCENDO);
        }
        return skill == null ? 0 : SkillData.getSkillInfoById(DARK_CRESCENDO).getValue(prop, skill.getCurrentLevel());
    }

    private int getMaxDarkCrescendoStack() {
        Skill skill = null;
        if (chr.hasSkill(DARK_CRESCENDO)) {
            skill = chr.getSkill(DARK_CRESCENDO);
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
        Char chr = c.getChr();
        Skill skill = chr.getSkill(skillID);
        SkillInfo si = null;
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
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
                case ECLIPSE:
                case SUNFIRE:
                case CHANGE_LIGHT_DARK:
                    larknessDiraction = larknessDiraction == DARK ? LIGHT : DARK;
                    sendIncLarknessResult();
                    o1.nOption = 1;
                    o1.rOption = EQUILIBRIUM;
//                    o1.tOption = SkillData.getSkillInfoById(EQUILIBRIUM).getValue(time, 1);
                    tsm.putCharacterStatValue(Larkness, o1);
                    equilibriumTimer = EventManager.addEvent(this::changeMode, getMoreEquilibriumTime(), TimeUnit.SECONDS);
                    chr.resetSkillCoolTime(ENDER);
                    chr.resetSkillCoolTime(DEATH_SCYTHE);
                    break;
                case HEROS_WILL_LUMI:
                    tsm.removeAllDebuffs();
                    break;
            }
            tsm.sendSetStatPacket();
        }
    }

    public int alterCooldownSkill(int skillId) {
        switch (skillId) {
            case ENDER:
            case DEATH_SCYTHE:
                if(equilibriumTimer != null && !equilibriumTimer.isDone())
                return 0;
        }
        return -1;
    }



    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        if(tsm.getOption(Larkness).rOption == EQUILIBRIUM) {
            return;
        } else {
            if (tsm.hasStat(BlessOfDarkness) && chr.hasSkill(BLACK_BLESSING) && hitInfo.hpDamage > 0) {
                Skill skill = chr.getSkill(BLACK_BLESSING);
                SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                byte slv = (byte) skill.getCurrentLevel();
                changeBlackBlessingCount(c, false); // deduct orbs as player gets hit
                int dmgAbsorbed = si.getValue(x, slv);
                hitInfo.hpDamage = (int) (hitInfo.hpDamage * ((double) dmgAbsorbed / 100));
            }
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public void handleMobDebuffSkill(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(tsm.hasStat(AntiMagicShell)) {
            tsm.removeAllDebuffs();
            deductShadowShell();
        }

    }

    private void deductShadowShell() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(!chr.hasSkill(SHADOW_SHELL)) {
            return;
        }
        Skill skill = chr.getSkill(SHADOW_SHELL);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();
        Option o = new Option();
        if (tsm.hasStat(AntiMagicShell)) {
            int shadowShellCount = tsm.getOption(AntiMagicShell).nOption;

            if(shadowShellCount > 0) {
                shadowShellCount--;
            }

            if(shadowShellCount <= 0) {
                tsm.removeStatsBySkill(skill.getSkillId());
                tsm.sendResetStatPacket();
            } else {
                o.nOption = shadowShellCount;
                o.rOption = skill.getSkillId();
                o.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AntiMagicShell, o);
                tsm.sendSetStatPacket();
            }
            chr.write(User.effect(Effect.skillSpecial(skill.getSkillId())));
            chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillSpecial(skill.getSkillId())));
        }
    }

    @Override
    public void handleSkillPrepare(Char chr, int skillID) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(skillID);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        Option o = new Option();

        byte slv = (byte) skill.getCurrentLevel();
        switch (skillID) {
            case PRESSURE_VOID:
                o.nOption = si.getValue(x, slv);
                o.rOption = skill.getSkillId();
                tsm.putCharacterStatValue(KeyDownAreaMoving, o);
                tsm.sendSetStatPacket();
                break;
            default:
                super.handleSkillPrepare(chr, skillID);
                chr.chatMessage("Unhandled skill prepare for luminous " + skillID);
                break;
        }
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        chr.getAvatarData().getCharacterStat().setPosMap(927020080);
    }
}