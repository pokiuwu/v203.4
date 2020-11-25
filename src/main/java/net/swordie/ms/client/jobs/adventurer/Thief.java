package net.swordie.ms.client.jobs.adventurer;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.quest.QuestManager;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.ForceAtomInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.AssistType;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.drop.Drop;
import net.swordie.ms.life.drop.DropInfo;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.*;
import java.util.concurrent.ScheduledFuture;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Thief extends Beginner {
    public static final int MAPLE_RETURN = 1281;

    // Rogue
    public static final int HASTE = 4001005; //Buff
    public static final int DARK_SIGHT = 4001003; //Buff


    // Night Lord
    public static final int ASSASSINS_MARK = 4101011; //Buff (ON/OFF)
    public static final int ASSASSIN_MARK_ATOM = 4100012;
    public static final int NIGHTLORD_MARK_ATOM = 4120019;
    public static final int CLAW_BOOSTER = 4101003; //Buff

    public static final int SHADOW_PARTNER_NL = 4111002; //Buff
    public static final int EXPERT_THROWING_STAR_HANDLING = 4110012;
    public static final int SHADOW_STARS = 4111009; //Buff
    public static final int DARK_FLARE_NL = 4111007; //Summon
    public static final int SHADOW_WEB = 4111003; //Special Attack (Dot + Bind)
    public static final int VENOM_NL = 4110011; //Passive DoT

    public static final int MAPLE_WARRIOR_NL = 4121000; //Buff
    public static final int SHOWDOWN = 4121017; //Special Attack
    public static final int SUDDEN_RAID_NL = 4121016; //Special Attack
    public static final int FRAILTY_CURSE = 4121015; //AoE
    public static final int NIGHT_LORD_MARK = 4120018;
    public static final int TOXIC_VENOM_NL = 4120011; //Passive DoT
    public static final int HEROS_WILL_NL = 4121009;

    // Shadower
    public static final int STEAL = 4201004; //Special Attack (Steal Debuff)?
    public static final int DAGGER_BOOSTER = 4201002; //Buff
    public static final int MESOGUARD = 4201011; //Buff
    public static final int CRITICAL_GROWTH = 4200013; //Passive Crit increasing buff

    public static final int SHADOW_PARTNER_SHAD = 4211008; //Buff
    public static final int DARK_FLARE_SHAD = 4211007; //Summon
    public static final int PICK_POCKET = 4211003; //Buff
    public static final int MESO_EXPLOSION = 4211006; //CreateForceAtom Attack
    public static final int MESO_EXPLOSION_ATOM = 4210014; // ?
    public static final int VENOM_SHAD = 4210010; //Passive DoT

    public static final int BOOMERANG_STAB = 4221007; //Special Attack (Stun Debuff)
    public static final int MAPLE_WARRIOR_SHAD = 4221000; //Buff
    public static final int SHADOWER_INSTINCT = 4221013; //Buff //Stacks (Body Count)
    public static final int SUDDEN_RAID_SHAD = 4221010; //Special Attack
    public static final int SMOKE_SCREEN = 4221006; //Affected Area
    public static final int PRIME_CRITICAL = 4220015; //Passive Buff
    public static final int TOXIC_VENOM_SHAD = 4220011; //Passive DoT
    public static final int HEROS_WILL_SHAD = 4221008;


    // Dual Blade
    public static final int SELF_HASTE = 4301003; //Buff

    public static final int KATARA_BOOSTER = 4311009; //Buff

    public static final int FLYING_ASSAULTER = 4321006; //Special Attack (Stun Debuff)
    public static final int FLASHBANG = 4321002; //Special Attack

    public static final int CHAINS_OF_HELL = 4331006; //Special Attack (Stun Debuff)
    public static final int MIRROR_IMAGE = 4331002; //Buff
    public static final int SHADOW_MELD = 4330009;
    public static final int VENOM_DB = 4320005;
    public static final int LIFE_DRAIN = 4330007;

    public static final int FINAL_CUT = 4341002; //Special Attack
    public static final int SUDDEN_RAID_DB = 4341011; //Special Attack
    public static final int MAPLE_WARRIOR_DB = 4341000; //Buff
    public static final int MIRRORED_TARGET = 4341006; //Summon
    public static final int TOXIC_VENOM_DB = 4340012;
    public static final int HEROS_WILL_DB = 4341008;

    //Hyper skills
    public static final int EPIC_ADVENTURE_NL = 4121053;
    public static final int EPIC_ADVENTURE_SHAD = 4221053;
    public static final int EPIC_ADVENTURE_DB = 4341053;
    public static final int BLEED_DART = 4121054;
    public static final int FLIP_THE_COIN = 4221054;
    public static final int BLADE_CLONE = 4341054;
    public static final int ASURAS_ANGER = 4341052;


    //public AffectedArea aa;

    private int critAmount;
    private int supposedCrit;
    private final int MAX_CRIT = 100;
    private ScheduledFuture critGrowthTimer;
    public static long lastShadowMeld = Long.MIN_VALUE;

    private int[] addedSkills = new int[] {
            MAPLE_RETURN,
    };

    private int[] buffs = new int[]{
            HASTE,
            DARK_SIGHT,
            ASSASSINS_MARK,
            CLAW_BOOSTER,
            SHADOW_PARTNER_NL,
            SHADOW_STARS,
            DARK_FLARE_NL,
            MAPLE_WARRIOR_NL,

            DAGGER_BOOSTER,
            MESOGUARD,
            SHADOW_PARTNER_SHAD,
            DARK_FLARE_SHAD,
            PICK_POCKET,
            SHADOWER_INSTINCT,
            MAPLE_WARRIOR_SHAD,

            SELF_HASTE,
            KATARA_BOOSTER,
            MIRROR_IMAGE,
            FINAL_CUT,
            MIRRORED_TARGET,
            MAPLE_WARRIOR_DB,

            EPIC_ADVENTURE_NL,
            EPIC_ADVENTURE_SHAD,
            EPIC_ADVENTURE_DB,
            BLEED_DART,
            FLIP_THE_COIN,
            BLADE_CLONE,
            ASURAS_ANGER,
    };

    public Thief(Char chr) {
        super(chr);

        if(chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
            if (JobConstants.isShadower(chr.getJob())) {
                if(critGrowthTimer != null && !critGrowthTimer.isDone()) {
                    critGrowthTimer.cancel(true);
                }
                critGrowthTimer = EventManager.addFixedRateEvent(this::incrementCritGrowing, 2000, 2000);
            }
        }

    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isAdventurerThief(id);
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
        Field field;
        switch (skillID) {
            case HASTE:
            case SELF_HASTE:
                o1.nOption = si.getValue(speed, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Speed, o1);
                o2.nOption = si.getValue(jump, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Jump, o2);
                // SpeedMax?
                break;
            case DARK_SIGHT:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DarkSight, o1);
                break;
            case ASSASSINS_MARK:
                if (tsm.hasStat(NightLordMark)) {
                    tsm.removeStatsBySkill(skillID);
                    tsm.sendResetStatPacket();
                } else {
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    o1.tOption = 0;
                    tsm.putCharacterStatValue(NightLordMark, o1);
                }
                break;
            case CLAW_BOOSTER:
            case DAGGER_BOOSTER:
            case KATARA_BOOSTER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case SHADOW_PARTNER_NL:
            case SHADOW_PARTNER_SHAD:
            case MIRROR_IMAGE:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ShadowPartner, o1);
                break;
            case MAPLE_WARRIOR_DB:
            case MAPLE_WARRIOR_NL:
            case MAPLE_WARRIOR_SHAD:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;
            case SHADOW_STARS:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NoBulletConsume, o1);
                break;
            case MESOGUARD:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(MesoGuard, o1);
                break;
            case PICK_POCKET:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(PickPocket, o1);
                break;
            case SHADOWER_INSTINCT:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(PAD, o1);
                o2.nOption = si.getValue(ignoreMobpdpR, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(IgnoreMobpdpR, o2);
                break;
            case MIRRORED_TARGET:
                if(tsm.getOptByCTSAndSkill(ShadowPartner, MIRROR_IMAGE) != null) {
                    summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                    field = c.getChr().getField();
                    summon.setFlyMob(false);
                    summon.setMoveAction((byte) 0);
                    summon.setMoveAbility(MoveAbility.Stop);
                    summon.setAssistType(AssistType.None);
                    summon.setAttackActive(false);
                    summon.setAvatarLook(chr.getAvatarData().getAvatarLook());
                    summon.setMaxHP(si.getValue(x, slv));
                    summon.setHp(summon.getMaxHP());
                    field.spawnSummon(summon);

                    tsm.removeStatsBySkill(MIRROR_IMAGE);
                }
                break;
            case DARK_FLARE_NL:
            case DARK_FLARE_SHAD:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(false);
                summon.setMoveAction((byte) 0);
                summon.setMoveAbility(MoveAbility.Stop);
                field.spawnSummon(summon);
                break;

            case EPIC_ADVENTURE_DB:
            case EPIC_ADVENTURE_NL:
            case EPIC_ADVENTURE_SHAD:
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
            case BLEED_DART:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(BleedingToxin, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePad, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o2);
                break;
            case FLIP_THE_COIN:
                incrementFlipTheCoinStack(tsm, c);
                c.write(WvsContext.flipTheCoinEnabled((byte) 0));
                break;
            case BLADE_CLONE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(WindBreakerFinal, o1);
                o2.nOption = 10;
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamR, o2);
                break;
            case ASURAS_ANGER:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 10;
                tsm.putCharacterStatValue(Asura, o1);
                break;
        }
        tsm.sendSetStatPacket();
    }

    public boolean isBuff(int skillID) {
        return super.isBuff(skillID) || Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    private void incrementFlipTheCoinStack(TemporaryStatManager tsm, Client c) {
        Option o = new Option();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        SkillInfo FlipTheCoinInfo = SkillData.getSkillInfoById(FLIP_THE_COIN);
        int amount = 1;
        if(tsm.hasStat(FlipTheCoin)) {
            amount = tsm.getOption(FlipTheCoin).nOption;
            if(amount < FlipTheCoinInfo.getValue(y, 1)) {
                amount++;
            }
        }
        o.nOption = amount;
        o.rOption = FLIP_THE_COIN;
        o.tOption = FlipTheCoinInfo.getValue(time, 1);
        tsm.putCharacterStatValue(FlipTheCoin, o);

        //Stats
        o1.nOption = (amount * FlipTheCoinInfo.getValue(x, 1));
        o1.rOption = FLIP_THE_COIN;
        o1.tOption = FlipTheCoinInfo.getValue(time, 1);
        tsm.putCharacterStatValue(CriticalBuff, o1);
        o2.nReason = FLIP_THE_COIN;
        o2.nValue = (amount * FlipTheCoinInfo.getValue(indieDamR, 1));
        o2.tStart = (int) System.currentTimeMillis();
        o2.tTerm = FlipTheCoinInfo.getValue(time, 1);
        tsm.putCharacterStatValue(IndieDamR, o2);
        o3.nReason = FLIP_THE_COIN;
        o3.nValue = (amount * FlipTheCoinInfo.getValue(indieMaxDamageOver, 1));
        o3.tStart = (int) System.currentTimeMillis();
        o3.tTerm = FlipTheCoinInfo.getValue(time, 1);
        tsm.putCharacterStatValue(IndieMaxDamageOver, o3);
        tsm.sendSetStatPacket();
    }

    private void activateFlipTheCoin(TemporaryStatManager tsm) {    //TODO  Change to proc on Critical Hits
        if(tsm.getOption(FlipTheCoin).nOption < 5) {
            if (Util.succeedProp(50)) { //Proc on Crit<<<
                c.write(WvsContext.flipTheCoinEnabled((byte) 1));
            }
        }
    }

    private void updatecrit() {
        if(chr.hasSkill(PRIME_CRITICAL)) {
            supposedCrit = supposedCrit + 4;
        } else {
            supposedCrit = supposedCrit + 2;
        }
        Option o = new Option();
        int critGrowth = critAmount;
        o.nOption = (getPrimeCritMulti() * critGrowth);
        o.rOption = getCritGrowIcon();
        chr.getTemporaryStatManager().putCharacterStatValue(CriticalGrowing, o);
        chr.getTemporaryStatManager().sendSetStatPacket();
    }

    private void incrementCritGrowth(int stackIncrease) {
        if(supposedCrit > 100) {
            critAmount = 1; //TODO returns to starting crit% even if another crit buff is active
            supposedCrit = 0;
        } else {
            critAmount += stackIncrease;
        }
        critAmount = Math.min(MAX_CRIT, critAmount);
        updatecrit();
    }

    public void incrementCritGrowing() {
        incrementCritGrowth(1);   //Crit Growing
    }

    private int getCritGrowIcon() {
        if(chr.hasSkill(PRIME_CRITICAL)) {
            return PRIME_CRITICAL;
        } else {
            return CRITICAL_GROWTH;
        }
    }

    private int getPrimeCritMulti() {
        int multiplier = 2;
        if(chr.hasSkill(PRIME_CRITICAL)) {
            multiplier = 4;
        }
        return multiplier;
    }

    @Override
    public void handleCancelTimer(Char chr) {
        if (critGrowthTimer != null) {
            critGrowthTimer.cancel(true);
        }
    }

    private void incrementShadowInstinct(int skillId, TemporaryStatManager tsm, Client c) {
        Option o = new Option();
        Option o1 = new Option();
        SkillInfo InstinctInfo = SkillData.getSkillInfoById(SHADOWER_INSTINCT);
        Skill skill = chr.getSkill(SHADOWER_INSTINCT);
        byte slv = (byte) skill.getCurrentLevel();
        int amount = 1;
        if (tsm.hasStat(KillingPoint)) {
            if (chr.hasSkill(SHADOWER_INSTINCT)) {
                amount = tsm.getOption(KillingPoint).nOption;
                if (amount < 5) {
                    amount++;
                }
            }
        }
        o.nOption = amount;
        tsm.putCharacterStatValue(KillingPoint, o);
        o1.nOption = (amount * InstinctInfo.getValue(kp, slv));
        o1.rOption = SHADOWER_INSTINCT;
        o1.tOption = InstinctInfo.getValue(time, slv);
        tsm.putCharacterStatValue(PAD, o1);
        tsm.sendSetStatPacket();
    }

    public void giveShadowMeld() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(chr.hasSkill(SHADOW_MELD)) {
            if(tsm.getOptByCTSAndSkill(IndiePAD, SHADOW_MELD) == null) {
                Skill skill = chr.getSkill(SHADOW_MELD);
                byte slv = (byte) skill.getCurrentLevel();
                SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());

                if(lastShadowMeld + 5000 < System.currentTimeMillis()) {
                    Option o1 = new Option();
                    Option o2 = new Option();
                    o1.nOption = 100;
                    o1.rOption = skill.getSkillId();
                    o1.tOption = si.getValue(time, slv);
                    tsm.putCharacterStatValue(CriticalBuff, o1);
                    o2.nReason = skill.getSkillId();
                    o2.nValue = si.getValue(indiePad, slv);
                    o2.tStart = (int) System.currentTimeMillis();
                    o2.tTerm = si.getValue(time, slv);
                    tsm.putCharacterStatValue(IndiePAD, o2); //Indie
                    tsm.sendSetStatPacket();
                    lastShadowMeld = System.currentTimeMillis();
                }
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
            skillID = SkillConstants.getActualSkillIDfromSkillID(skill.getSkillId());
        }
        if(hasHitMobs) {
            // Venom & Toxic Venom Passives
            applyPassiveDoTSkillsOnMob(attackInfo);

            // Shadower Hyper
            if(chr.hasSkill(FLIP_THE_COIN)) {
                activateFlipTheCoin(tsm);
            }

            // Night Lord Hyper
            applyBleedDartOnMob(attackInfo);
        }

        if (JobConstants.isNightLord(chr.getJob())) {
            if(hasHitMobs) {
                // NightLord's Mark & ForceAtom
                if(chr.hasSkill(ASSASSINS_MARK)) {
                    handleMark(attackInfo);
                    setMarkonMob(attackInfo);
                }

                // Expert Throwing Star Handling
                if(attackInfo.skillId != NIGHTLORD_MARK_ATOM && attackInfo.skillId != ASSASSIN_MARK_ATOM) {
                    procExpertThrowingStar(skillID);
                }
            }
        }


        if (JobConstants.isShadower(chr.getJob())) {
            if(hasHitMobs) {
                // Critical Growth & Prime Critical
                if(chr.hasSkill(CRITICAL_GROWTH)) {
                    incrementCritGrowing();
                }

                // Pick Pocket
                if(attackInfo.skillId != MESO_EXPLOSION_ATOM) {
                    dropFromPickPocket(attackInfo);
                }

                // Shadower Instinct
                if (chr.hasSkill(SHADOWER_INSTINCT)) {
                    if (tsm.hasStat(IgnoreMobpdpR)) {
                        incrementShadowInstinct(skillID, tsm, c);
                    }
                }

            }
        }

        if (JobConstants.isDualBlade(chr.getJob())) {
            if(hasHitMobs) {
                // Life Drain
                recoverHPByLifeDrain();
            }
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case STEAL:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        Field field = chr.getField();
                        int itemId = 2431835;
                        if(mob.isBoss()) {
                            itemId = 2431850;
                        }
                        Item item = ItemData.getItemDeepCopy(itemId);
                        Drop drop = new Drop(item.getItemId(), item);
                        field.drop(drop, mob.getPosition());

                        if(!mob.isBoss()) {
                            o1.nOption = 1;
                            o1.rOption = skill.getSkillId();
                            o1.tOption = si.getValue(time, slv);
                            mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                        }
                    }
                }
                break;
            case SHADOW_WEB:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (Util.succeedProp(si.getValue(prop, slv))) {
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
                    }else{
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.createAndAddBurnedInfo(chr, skill);
                    }
                }
                break;
            case SHOWDOWN:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    if(!mob.isBoss()) {
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Showdown, o1);
                    }
                    o2.nOption = 1;
                    o2.rOption = skillID;
                    o2.tOption = si.getValue(time, slv);
                    o2.xOption = (mob.isBoss() ? si.getValue(x, slv)/2 : si.getValue(x, slv)); // Exp
                    o2.yOption = (mob.isBoss() ? si.getValue(x, slv)/2 : si.getValue(x, slv)); // Item Drop
                    mts.addStatOptionsAndBroadcast(MobStat.Treasure, o2);
                }
                break;
            case SUDDEN_RAID_DB:
                chr.reduceSkillCoolTime(FINAL_CUT, (long) (chr.getRemainingCoolTime(FINAL_CUT) * 0.2F));
                // Fallthrough intended
            case SUDDEN_RAID_SHAD:
            case SUDDEN_RAID_NL:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr, skill);
                }
                break;
            case FLASHBANG:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = -si.getValue(x, slv);
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.ACC, o1);
                    }
                }
                break;
            case BOOMERANG_STAB:
            case FLYING_ASSAULTER:
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
                break;
            case CHAINS_OF_HELL:
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
                o2.nOption = 1;
                o2.tOption = 3;
                tsm.putCharacterStatValue(NotDamaged, o2);
                tsm.sendSetStatPacket();
                break;
            case FINAL_CUT:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(FinalCut, o1);
                o2.nOption = 1;
                o2.rOption = skillID;
                o2.tOption = 3;
                tsm.putCharacterStatValue(NotDamaged, o2);
                o3.nOption = si.getValue(w, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamR, o3);
                tsm.sendSetStatPacket();
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    private void createMesoExplosionForceAtom(Drop drop) {
        if(!chr.hasSkill(MESO_EXPLOSION)) {
            return;
        }
        Field field = chr.getField();
        Rect rect = new Rect(
                new Position(
                        chr.getPosition().getX() - 500,
                        chr.getPosition().getY() - 500),
                new Position(
                        chr.getPosition().getX() + 500,
                        chr.getPosition().getY() + 500)
        );
        List<Mob> mobs = field.getMobsInRect(rect);
        if(mobs.size() <= 0) {
            return;
        }
        Mob mob = Util.getRandomFromCollection(mobs);
        int inc = ForceAtomEnum.FLYING_MESO.getInc();
        int type = ForceAtomEnum.FLYING_MESO.getForceAtomType();
        int mobId = mob.getObjectId();

        ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 2, 3,
                0, 0, (int) System.currentTimeMillis(), 1, 0,
                new Position());
        chr.getField().broadcastPacket(CField.createForceAtom(false, 0, chr.getId(), type,
                true, mobId, MESO_EXPLOSION_ATOM, forceAtomInfo, new Rect(), 0, 300,
                mob.getPosition(), MESO_EXPLOSION_ATOM, mob.getPosition()));
    }

    private void procExpertThrowingStar(int skillId) {
        if(!chr.hasSkill(EXPERT_THROWING_STAR_HANDLING)) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();

        Skill skill = chr.getSkill(EXPERT_THROWING_STAR_HANDLING);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();
        int hideIconSkillId = skill.getSkillId() + 100; // there's no Buff Icon

        if(tsm.getOptByCTSAndSkill(IndieDamR, hideIconSkillId) == null) {
            if(tsm.hasStatBySkillId(hideIconSkillId)) {
                tsm.removeStatsBySkill(hideIconSkillId);
            }
            if(Util.succeedProp(si.getValue(prop, slv))) {
                o.nReason = hideIconSkillId;
                o.nValue = si.getValue(pdR, slv);
                o.tStart = (int) System.currentTimeMillis();
                o.tTerm = 5;
                tsm.putCharacterStatValue(IndieDamR, o);
                tsm.sendSetStatPacket();
            }
        } else {
            tsm.removeStatsBySkill(hideIconSkillId);
            o.nOption = 100;
            o.rOption = hideIconSkillId;
            o.tOption = 5;
            tsm.putCharacterStatValue(CriticalGrowing, o);
            tsm.sendSetStatPacket();

            if(SkillData.getSkillInfoById(skillId) != null) {
                chr.healMP(SkillData.getSkillInfoById(skillId).getValue(mpCon, slv));
            }

            chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillAffected(skill.getSkillId(), slv, 0)));
            chr.write(User.effect(Effect.skillAffected(skill.getSkillId(), slv, 0)));
        }
    }

    private void handleMark(AttackInfo attackInfo) {
        if(getMarkSkill() == null) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = getMarkSkill();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();

        if(tsm.hasStat(NightLordMark)) {
            if (attackInfo.skillId != NIGHTLORD_MARK_ATOM && attackInfo.skillId != ASSASSIN_MARK_ATOM) {
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    int randomInt = new Random().nextInt((360/getAssassinsMarkStarCount())-1);
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if(mob == null) {
                        continue;
                    }
                    Rect rect = new Rect(
                            new Position(
                                    mob.getPosition().getX() - 800,
                                    mob.getPosition().getY() - 800),
                            new Position(
                                    mob.getPosition().getX() + 800,
                                    mob.getPosition().getY() + 800)
                    );
                    MobTemporaryStat mts = mob.getTemporaryStat();

                    List<Mob> lifes = chr.getField().getMobsInRect(rect);
                    if(lifes.size() <= 0) {
                        return;
                    }
                    List<Mob> bossLifes = chr.getField().getBossMobsInRect(rect);
                    if(mts.hasBurnFromSkillAndOwner(getCurMarkLv(), chr.getId())) {
                        for (int i = 0; i < getAssassinsMarkStarCount(); i++) {

                            Mob life = Util.getRandomFromCollection(lifes);
                            if(bossLifes.size() > 0 && Util.succeedProp(65)) {
                                life = Util.getRandomFromCollection(bossLifes);
                            }

                            int anglez = (360 / getAssassinsMarkStarCount()) * i;

                            int inc = ForceAtomEnum.ASSASSIN_MARK.getInc();
                            int type = ForceAtomEnum.ASSASSIN_MARK.getForceAtomType();
                            int atom = ASSASSIN_MARK_ATOM;

                            if(chr.hasSkill(NIGHT_LORD_MARK)) {
                                inc = ForceAtomEnum.NIGHTLORD_MARK.getInc();
                                type = ForceAtomEnum.NIGHTLORD_MARK.getForceAtomType();
                                atom = NIGHTLORD_MARK_ATOM;
                            }
                            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 45, 4,
                                    randomInt+anglez, 170, (int) System.currentTimeMillis(), 1, 0,
                                    new Position());
                            chr.getField().broadcastPacket(CField.createForceAtom(true, chr.getId(), life.getObjectId(), type,
                                    true, life.getObjectId(), atom, forceAtomInfo, rect, 0, 300,
                                    life.getPosition(), chr.getBulletIDForAttack(), life.getPosition()));
                        }
                    }
                }
            }
        }
    }

    private int getAssassinsMarkStarCount() {
        if(getMarkSkill() != null) {
            Skill skill = getMarkSkill();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            byte slv = (byte) skill.getCurrentLevel();

            return si.getValue(bulletCount, slv);
        }
        return 0;
    }

    private Skill getMarkSkill() {
        Skill skill = null;
        if(chr.hasSkill(ASSASSINS_MARK)) {
            skill = chr.getSkill(ASSASSINS_MARK);
        }
        if(chr.hasSkill(NIGHT_LORD_MARK)) {
            skill = chr.getSkill(NIGHT_LORD_MARK);
        }
        return skill;
    }



    private void setMarkonMob(AttackInfo attackInfo) {
        Skill skill = chr.getSkill(getCurMarkLv());
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(getCurMarkLv());
        byte slv = (byte) skill.getCurrentLevel();
        int markprop = si.getValue(prop, slv);
        if(tsm.hasStat(NightLordMark)) {
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                if (Util.succeedProp(markprop)) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if(mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr, skill);
                }
            }
        }
    }

    private int getCurMarkLv() {
        int supgrade = 0;
        if(chr.hasSkill(ASSASSINS_MARK)) {
            supgrade = ASSASSINS_MARK;
        }
        if(chr.hasSkill(NIGHT_LORD_MARK)) {
            supgrade = NIGHT_LORD_MARK;
        }
        return supgrade;
    }

    private void applyPassiveDoTSkillsOnMob(AttackInfo attackInfo) {

        //Night Lord
        if(chr.hasSkill(TOXIC_VENOM_NL)) {
            Skill skill = chr.getSkill(TOXIC_VENOM_NL);
            byte slv = (byte) skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int proc = si.getValue(prop, slv);
            for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                if(Util.succeedProp(proc)) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if(mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr, skill);
                }
            }
        } else if(chr.hasSkill(VENOM_NL)) {
            Skill skill = chr.getSkill(VENOM_NL);
            byte slv = (byte) skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int proc = si.getValue(prop, slv);
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                if (Util.succeedProp(proc)) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if(mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr, skill);
                }
            }
        }

        //Shadower
        if(chr.hasSkill(TOXIC_VENOM_SHAD)) {
            Skill skill = chr.getSkill(TOXIC_VENOM_SHAD);
            byte slv = (byte) skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int proc = si.getValue(prop, slv);
            for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                if(Util.succeedProp(proc)) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if(mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr, skill);
                }
            }
        } else if(chr.hasSkill(VENOM_SHAD)) {
            Skill skill = chr.getSkill(VENOM_SHAD);
            byte slv = (byte) skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int proc = si.getValue(prop, slv);
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                if (Util.succeedProp(proc)) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if(mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr, skill);
                }
            }
        }

        //Dual Blade
        if(chr.hasSkill(TOXIC_VENOM_DB)) {
            Skill skill = chr.getSkill(TOXIC_VENOM_DB);
            byte slv = (byte) skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int proc = si.getValue(prop, slv);
            for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                if(Util.succeedProp(proc)) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if(mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr, skill);
                }
            }
        } else if(chr.hasSkill(VENOM_DB)) {
            Skill skill = chr.getSkill(VENOM_DB);
            byte slv = (byte) skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int proc = si.getValue(prop, slv);
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                if (Util.succeedProp(proc)) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if(mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr, skill);
                }
            }
        }
    }

    private void applyBleedDartOnMob(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(tsm.hasStat(BleedingToxin)) {
            Skill skill = chr.getSkill(BLEED_DART);
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if(mob == null) {
                    continue;
                }
                MobTemporaryStat mts = mob.getTemporaryStat();
                mts.createAndAddBurnedInfo(chr, skill);
            }
        }
    }

    private void recoverHPByLifeDrain() {
        if(chr.hasSkill(LIFE_DRAIN)) {
            Skill skill = chr.getSkill(LIFE_DRAIN);
            byte slv = (byte) skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int proc = si.getValue(prop, slv);
            int amounthealed = si.getValue(x, slv);
            if(Util.succeedProp(proc)) {
                int healamount = (int) ((chr.getMaxHP()) / ((double)100 / amounthealed));
                chr.heal(healamount);
            }
        }
    }

    private void dropFromPickPocket(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Field field = chr.getField();
        if(!chr.hasSkill(PICK_POCKET)) {
            return;
        }
        if(tsm.getOptByCTSAndSkill(PickPocket, PICK_POCKET) != null) {
            for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) field.getLifeByObjectID(mai.mobId);
                if(mob == null) {
                    continue;
                }
                Skill skill = chr.getSkill(PICK_POCKET);
                SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                byte slv = (byte) skill.getCurrentLevel();
                Set<DropInfo> dropInfoSet = new HashSet<>();
                for (int i = 0; i < slv; i++) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        dropInfoSet.add(new DropInfo(GameConstants.MAX_DROP_CHANCE, 50, 150)); // min 50; max 150;
                    }
                }
                if (dropInfoSet.size() > 0) {
                    field.drop(dropInfoSet, mob.getPosition(), chr.getId(), false);
                }
            }
        }
    }

    @Override
    public int getFinalAttackSkill() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(tsm.getOptByCTSAndSkill(WindBreakerFinal, BLADE_CLONE) != null) {
            return BLADE_CLONE;
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
            switch (skillID) {
                case MAPLE_RETURN:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = chr.getOrCreateFieldByCurrentInstanceType(o1.nValue);
                    chr.warp(toField);
                    break;
                case SMOKE_SCREEN:
                    AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                    aa.setMobOrigin((byte) 0);
                    aa.setPosition(chr.getPosition());
                    aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                    aa.setDelay((short) 4);
                    chr.getField().spawnAffectedArea(aa);
                    break;
                case FRAILTY_CURSE:
                    SkillInfo fci = SkillData.getSkillInfoById(skillID);
                    int lt1 = si.getValue(lt, slv);
                    int rb1 = si.getValue(rb, slv);
                    AffectedArea aa2 = AffectedArea.getPassiveAA(chr, skillID, slv);
                    aa2.setMobOrigin((byte) 0);
                    aa2.setPosition(chr.getPosition());
                    aa2.setRect(aa2.getPosition().getRectAround(fci.getRects().get(0)));
                    aa2.setFlip(!chr.isLeft());
                    aa2.setDelay((short) 9);
                    chr.getField().spawnAffectedArea(aa2);
                    break;
                case MESO_EXPLOSION:
                    int i = 0;
                    Field field = chr.getField();
                    int rectRange = si.getValue(range, slv);
                    Rect rect = new Rect(
                            new Position(
                                    chr.getPosition().getX() - rectRange,
                                    chr.getPosition().getY() - rectRange),
                            new Position(
                                    chr.getPosition().getX() + rectRange,
                                    chr.getPosition().getY() + rectRange)
                    );
                    List<Drop> dropList = field.getDropsInRect(rect);
                    for(Drop drop : dropList) {
                        if(drop.isMoney() && (drop.getOwnerID() == chr.getId()) && i < si.getValue(bulletCount, slv)) {
                            createMesoExplosionForceAtom(drop);
                            field.broadcastPacket(DropPool.dropExplodeField(drop.getObjectId()));
                            i++;
                        }
                    }
                    break;
                case HEROS_WILL_NL:
                case HEROS_WILL_SHAD:
                case HEROS_WILL_DB:
                    tsm.removeAllDebuffs();
                    break;
            }
        }
    }



    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        if(hitInfo.hpDamage <= 0) {
            giveShadowMeld();
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
        // hacks to bypass the quest glitch (accept but no packet)
        short level = chr.getLevel();
        QuestManager qm = chr.getQuestManager();
        if (level == 30) {
            qm.completeQuest(1421);
        } else if (level == 60) {
            qm.completeQuest(1441);
        } else if (level == 100) {
            qm.completeQuest(1456);
        }
    }
}