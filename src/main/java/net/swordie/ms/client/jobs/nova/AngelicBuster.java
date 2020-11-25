package net.swordie.ms.client.jobs.nova;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.ForceAtomInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.CField;
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.User;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.enums.Stat;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class AngelicBuster extends Job {
    //AB Beginner Skills
    public static final int DRESS_UP = 60011222;
    public static final int SOUL_BUSTER = 60011216;
    public static final int HYPER_COORDINATE = 60011221;
    public static final int GRAPPLING_HEART = 60011218;
    public static final int DAY_DREAMER = 60011220;
    public static final int TRUE_HEART_INHERITANCE = 60010217;

    public static final int AB_NORMAL_ATTACK = 60011216;

    //1st Job
    public static final int STAR_BUBBLE = 65001100;
    public static final int MELODY_CROSS = 65001002; //Buff

    //2nd job
    public static final int LOVELY_STING = 65101100;
    public static final int LOVELY_STING_EXPLOSION = 65101006;
    public static final int PINK_PUMMEL = 65101001;
    public static final int POWER_TRANSFER = 65101002; //Buff

    //3rd Job
    public static final int SOUL_SEEKER = 65111100;
    public static final int SOUL_SEEKER_ATOM = 65111007;
    public static final int SHINING_STAR_BURST = 65111101;
    public static final int HEAVENLY_CRASH = 65111002;
    public static final int IRON_BLOSSOM = 65111004; //Buff

    //4th Job
    public static final int CELESTIAL_ROAR = 65121100;
    public static final int TRINITY = 65121101; //TODO Recharge Attack
                                    //65121101 - Trinity -combo count-
    public static final int FINALE_RIBBON = 65121002;
    public static final int STAR_GAZER = 65121004; //Buff
    public static final int NOVA_WARRIOR_AB = 65121009; //Buff
    public static final int SOUL_SEEKER_EXPERT = 65121011; //ON/OFF Buff
    public static final int NOVA_TEMPERANCE_AB = 65121010;


    //Hypers
    public static final int PRETTY_EXALTATION = 65121054;
    public static final int FINAL_CONTRACT = 65121053;


    //Affinity Heart Passives
    public static final int AFFINITY_HEART_I = 65000003;
    public static final int AFFINITY_HEART_II = 65100005;
    public static final int AFFINITY_HEART_III = 65110006;
    public static final int AFFINITY_HEART_IV = 65120006;


    private int[] addedSkills = new int[] {
            DRESS_UP,
            SOUL_BUSTER,
            HYPER_COORDINATE,
            GRAPPLING_HEART,
            DAY_DREAMER,
            TRUE_HEART_INHERITANCE,
    };

    private final int[] buffs = new int[]{
            MELODY_CROSS,
            POWER_TRANSFER,
            IRON_BLOSSOM,
            STAR_GAZER,
            NOVA_WARRIOR_AB,
            SOUL_SEEKER_EXPERT,
            PRETTY_EXALTATION,
            FINAL_CONTRACT,
    };

    private int affinityHeartIIcounter = 0;
    private int affinityHeartIIIcounter = 0;

    public AngelicBuster(Char chr) {
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
        return JobConstants.isAngelicBuster(id);
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        chr.getAvatarData().getCharacterStat().setPosMap(940011000);
        chr.getAvatarData().getCharacterStat().setJob(JobConstants.JobEnum.ANGELIC_BUSTER.getJobId());
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
            case MELODY_CROSS:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieBooster, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                o2.nOption = si.getValue(mhpX, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EMHP, o2);
                break;
            case POWER_TRANSFER:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(PowerTransferGauge, o1);
                break;
            case IRON_BLOSSOM:
                o1.nOption = si.getValue(prop, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Stance, o1);
                break;
            case STAR_GAZER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(SharpEyes, o1); //Changed IncCriticalDamMax to SharpEyes
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(IncCriticalDamMin, o2);
                break;
            case NOVA_WARRIOR_AB:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case SOUL_SEEKER_EXPERT:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(AngelicBursterSoulSeeker, o1);
                break;
            case PRETTY_EXALTATION:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieIgnoreMobpdpR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieIgnoreMobpdpR, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieBDR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBDR, o2);
                break;
            case FINAL_CONTRACT:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CriticalBuff, o1);
                o2.nOption = si.getValue(asrR, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AsrR, o2);
                tsm.putCharacterStatValue(TerR, o2);
                o3.nOption = si.getValue(indieStance, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Stance, o3);
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

        if(hasHitMobs) {
            //Soul Seeker Recreation
            if (attackInfo.skillId == SOUL_SEEKER_ATOM) {
                recreateSoulSeekerForceAtom(attackInfo);
            }

            //Soul Seeker Expert
            if (attackInfo.skillId != SOUL_SEEKER_ATOM) {
                soulSeekerExpert(skillID, slv, attackInfo);
            }


            //Recharging System
            if(Util.succeedProp(getRechargeProc(attackInfo))) {
                rechargeABSkills();
                affinityHeartIIIcounter = 0;
            } else {

                //Affinity Heart IV passive
                if(chr.hasSkill(AFFINITY_HEART_IV) && Util.succeedProp(getRechargeProc(attackInfo))) {
                    Skill ah4Skill = chr.getSkill(AFFINITY_HEART_IV);
                    byte ah4LV = (byte) ah4Skill.getCurrentLevel();
                    SkillInfo ah4SI = SkillData.getSkillInfoById(ah4Skill.getSkillId());
                    if(Util.succeedProp(ah4SI.getValue(x, ah4LV))) {
                        rechargeABSkills();
                        affinityHeartIIIcounter = 0;
                        affinityHeartIV(tsm, ah4LV);
                    }

                } else {

                    //Affinity Heart III passive
                    if (!chr.hasSkill(AFFINITY_HEART_III)) {
                        return;
                    }
                    affinityHeartIIIcounter++;
                    if (affinityHeartIIIcounter > 2) {
                        rechargeABSkills();
                    }
                }
            }

            affinityHeartII(attackInfo);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case AB_NORMAL_ATTACK:
                soulSeekerExpert(60011216, slv, attackInfo);
                break;
            case TRINITY:
            case 65121007:
            case 65121008:
                trinityBuff(tsm);
                break;
            case LOVELY_STING:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 1;
                    o1.rOption = skill.getSkillId();
                    o1.wOption = chr.getId();
                    mts.addStatOptionsAndBroadcast(MobStat.Explosion, o1);
                }
                break;
            case FINALE_RIBBON:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 1;
                    o1.rOption = skill.getSkillId();
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.AddDamParty, o1); //TODO Check if this is the Correct MobStat
                }
                break;
            case CELESTIAL_ROAR:    //Stun Debuff
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
            case 65101006:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {

                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.removeMobStat(MobStat.Explosion, false);
                }
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    private void soulSeekerExpert(int skillID, byte slv, AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(AngelicBursterSoulSeeker)) {
            SkillInfo si = SkillData.getSkillInfoById(SOUL_SEEKER_EXPERT);
            int anglenum;
            if (new Random().nextBoolean()) {
                anglenum = 50;
            } else {
                anglenum = 130;
            }
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                int TW1prop = si.getValue(prop, slv);
                if(attackInfo.skillId == CELESTIAL_ROAR) {
                    TW1prop += si.getValue(z, slv);
                }
                if(tsm.getOptByCTSAndSkill(IndieIgnoreMobpdpR, PRETTY_EXALTATION) != null) {
                    TW1prop += 15;
                }
                if (Util.succeedProp(TW1prop)) {
                    int mobID = mai.mobId;
                    int inc = ForceAtomEnum.AB_ORB.getInc();
                    int type = ForceAtomEnum.AB_ORB.getForceAtomType();
                    ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 20, 40,
                            anglenum, 0, (int) System.currentTimeMillis(), 1, 0,
                            new Position(5, 0)); //Slightly behind the player
                    chr.getField().broadcastPacket(CField.createForceAtom(false, 0, chr.getId(), type,
                            true, mobID, SOUL_SEEKER_ATOM, forceAtomInfo, new Rect(), 0, 300,
                            mob.getPosition(), SOUL_SEEKER_ATOM, mob.getPosition()));
                }
            }
        }
    }

    private void createSoulSeekerForceAtom() {
        Field field = chr.getField();
        SkillInfo si = SkillData.getSkillInfoById(SOUL_SEEKER);
        Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
        if(!chr.isLeft()) {
            rect = rect.moveRight();
        }
        List<Mob> lifes = field.getMobsInRect(rect);
        if(lifes.size() <= 0) {
            return;
        }
        List<Mob> bossLifes = field.getBossMobsInRect(rect);
        Life life = Util.getRandomFromCollection(lifes);
        if(bossLifes.size() > 0) {
            life = Util.getRandomFromCollection(bossLifes);
        }
        int anglenum = new Random().nextInt(10);
        int mobID = life.getObjectId();
        int inc = ForceAtomEnum.AB_ORB.getInc();
        int type = ForceAtomEnum.AB_ORB.getForceAtomType();
        ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 20, 40,
                anglenum, 250, (int) System.currentTimeMillis(), 1, 0,
                new Position(0, -100));
        chr.getField().broadcastPacket(CField.createForceAtom(false, 0, chr.getId(), type,
                true, mobID, SOUL_SEEKER_ATOM, forceAtomInfo, new Rect(), 0, 300,
                life.getPosition(), SOUL_SEEKER_ATOM, life.getPosition()));
    }

    private void recreateSoulSeekerForceAtom(AttackInfo attackInfo) {
        SkillInfo si = SkillData.getSkillInfoById(SOUL_SEEKER);
        Skill skill = chr.getSkill(SOUL_SEEKER);
        byte slv = (byte) skill.getCurrentLevel();
        int anglenum = new Random().nextInt(360);
        int firstimpact = new Random().nextInt(4)+29;
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
            if (mob == null) {
                continue;
            }
            int TW1prop = si.getValue(s, slv);
            if (Util.succeedProp(TW1prop)) {
                int mobID = mai.mobId;
                int inc = ForceAtomEnum.AB_ORB_RECREATION.getInc();
                int type = ForceAtomEnum.AB_ORB_RECREATION.getForceAtomType();
                ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, firstimpact, 2,
                        anglenum, 0, (int) System.currentTimeMillis(), 1, 0,
                        new Position());
                chr.getField().broadcastPacket(CField.createForceAtom(true, chr.getId(), mobID, type,
                        true, mobID, SOUL_SEEKER_ATOM, forceAtomInfo, new Rect(), 0, 300,
                        mob.getPosition(), SOUL_SEEKER_ATOM, mob.getPosition()));
            }
        }
    }

    private int getRechargeProc(AttackInfo attackInfo) {
        Skill skill = chr.getSkill(SkillConstants.getActualSkillIDfromSkillID(attackInfo.skillId));
        if (skill == null) {
            return 0;
        }
        byte slv = (byte) skill.getCurrentLevel();
        SkillInfo rechargeInfo = SkillData.getSkillInfoById(skill.getSkillId());
        int rechargeproc = rechargeInfo.getValue(onActive, slv);
        if(rechargeproc == 0) {
            return rechargeproc;
        }
        if (chr.hasSkill(AFFINITY_HEART_I)) {
            SkillInfo ah1 = SkillData.getSkillInfoById(AFFINITY_HEART_I);
            int extraRecharge = ah1.getValue(x, slv);
            rechargeproc += (extraRecharge - 10);
        }

        return rechargeproc;
    }

    private void affinityHeartII(AttackInfo attackInfo) {
        if (!chr.hasSkill(AFFINITY_HEART_II)) {
            return;
        }
        for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
            if (mob == null) {
                continue;
            }
            if(affinityHeartIIcounter >= 10) {
                rechargeABSkills();
                affinityHeartIIcounter = 0;
                affinityHeartIIIcounter = 0;
            } else {
                long totaldmg = Arrays.stream(mai.damages).sum();
                if (totaldmg > mob.getHp()) {
                    affinityHeartIIcounter++;
                }
            }
        }
    }

    private void affinityHeartIV(TemporaryStatManager tsm, byte slv) {
        if(!chr.hasSkill(AFFINITY_HEART_IV)) {
            return;
        }
        if(tsm.getOptByCTSAndSkill(IndieDamR, AFFINITY_HEART_IV) == null) {
            Skill skill = chr.getSkill(AFFINITY_HEART_IV);
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            Option o = new Option();
            o.nValue = si.getValue(y, slv);
            o.nReason = skill.getSkillId();
            o.tStart = (int) System.currentTimeMillis();
            o.tTerm = 5;
            tsm.putCharacterStatValue(IndieDamR, o);
            tsm.sendSetStatPacket();
        }
    }

    private void trinityBuff(TemporaryStatManager tsm) {
        Option o1 = new Option();
        Option o2 = new Option();
        int amount = 1;
        if(tsm.hasStat(Trinity)) {
            amount = tsm.getOption(Trinity).mOption;
            if(amount < 3) {
                amount++;
            }
        }
        o1.nOption = 5;
        o1.rOption = TRINITY;
        o1.tOption = 5;
        o1.mOption = amount;
        tsm.putCharacterStatValue(Trinity, o1);
        o2.nValue = (5 * amount);
        o2.nReason = TRINITY;
        o2.tStart = (int) System.currentTimeMillis();
        o2.tTerm = 5;
        tsm.putCharacterStatValue(IndieDamR, o2);
        tsm.putCharacterStatValue(IndieIgnoreMobpdpR, o2);
        tsm.sendSetStatPacket();
    }

    private void rechargeABSkills() {
        Effect effect = Effect.createABRechargeEffect();
        chr.write(User.effect(effect));
        chr.write(UserLocal.resetStateForOffSkill());
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
            Option o2 = new Option();
            Option o3 = new Option();
            switch(skillID) {
                case SOUL_SEEKER:
                    createSoulSeekerForceAtom();
                    createSoulSeekerForceAtom();
                    break;
                case DAY_DREAMER:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = chr.getOrCreateFieldByCurrentInstanceType(o1.nValue);
                    chr.warp(toField);
                    break;
                case NOVA_TEMPERANCE_AB:
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