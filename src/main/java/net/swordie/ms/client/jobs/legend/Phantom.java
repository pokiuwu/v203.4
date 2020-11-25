package net.swordie.ms.client.jobs.legend;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.ForceAtomInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.client.jobs.adventurer.*;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.enums.Stat;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.*;
import java.util.stream.Collectors;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;
import static net.swordie.ms.life.mob.MobStat.*;

/**
 * Created on 12/14/2017.
 */
public class Phantom extends Job {

    public static final int JUDGMENT_DRAW_1 = 20031209;
    public static final int JUDGMENT_DRAW_2 = 20031210;

    public static final int SKILL_SWIPE = 20031207;
    public static final int LOADOUT = 20031208;
    public static final int TO_THE_SKIES = 20031203;
    public static final int DEXTEROUS_TRAINING = 20030206;
    public static final int GHOSTWALK = 20031211;

    public static final int IMPECCABLE_MEMORY_I = 24001001;

    public static final int IMPECCABLE_MEMORY_II = 24101001;
    public static final int CANE_BOOSTER = 24101005; //Buff
    public static final int CARTE_BLANCHE = 24100003;

    public static final int IMPECCABLE_MEMORY_III = 24111001;
    public static final int FINAL_FEINT = 24111002; //Buff (Unlimited Duration) Gone upon Death
    public static final int BAD_LUCK_WARD = 24111003; //Buff
    public static final int CLAIR_DE_LUNE = 24111005; //Buff

    public static final int IMPECCABLE_MEMORY_IV = 24121001;
    public static final int PRIERE_DARIA = 24121004; //Buff
    public static final int VOL_DAME = 24121007; // Special Buff
    public static final int MAPLE_WARRIOR_PH = 24121008; //Buff
    public static final int CARTE_NOIR = 24120002;              //80001890
    public static final int HEROS_WILL_PH = 24121009;

    public static final int HEROIC_MEMORIES_PH = 24121053;
    public static final int CARTE_ROSE_FINALE = 24121052;

    public static final int CARTE_ATOM = 80001890;

    private int[] addedSkills = new int[]{
            JUDGMENT_DRAW_2,
            SKILL_SWIPE,
            LOADOUT,
            TO_THE_SKIES,
            DEXTEROUS_TRAINING,
    };

    private final int[] buffs = new int[]{
            IMPECCABLE_MEMORY_I,
            IMPECCABLE_MEMORY_II,
            CANE_BOOSTER,
            IMPECCABLE_MEMORY_III,
            FINAL_FEINT,
            BAD_LUCK_WARD,
            CLAIR_DE_LUNE,
            IMPECCABLE_MEMORY_IV,
            PRIERE_DARIA,
            MAPLE_WARRIOR_PH,
            HEROIC_MEMORIES_PH,
    };

    private byte cardAmount;
    private Set<Job> stealJobHandlers = new HashSet<>();

    public Phantom(Char chr) {
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
        stealJobHandlers.add(new Warrior(chr));
        stealJobHandlers.add(new Magician(chr));
        stealJobHandlers.add(new Archer(chr));
        stealJobHandlers.add(new Thief(chr));
        stealJobHandlers.add(new Pirate(chr));
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isPhantom(id);
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        chr.setStolenSkills(new HashSet<>());
        chr.setChosenSkills(new HashSet<>());
        chr.getAvatarData().getCharacterStat().setPosMap(915000000);
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
            case GHOSTWALK:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DarkSight, o1);
                break;
            case CANE_BOOSTER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case FINAL_FEINT:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ReviveOnce, o1);
                break;
            case BAD_LUCK_WARD:
                o1.nValue = si.getValue(indieMhpR, slv);
                o1.nReason = skillID;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMHPR, o1);
                o2.nValue = si.getValue(indieMmpR, slv);
                o2.nReason = skillID;
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMMPR, o2);
                o3.nOption = si.getValue(x, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AsrR, o3);
                o4.nOption = si.getValue(y, slv);
                o4.rOption = skillID;
                o4.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(TerR, o4);
                break;
            case CLAIR_DE_LUNE:
                o1.nValue = si.getValue(indiePad, slv);
                o1.nReason = skillID;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1);
                o2.nValue = si.getValue(indieAcc, slv);
                o2.nReason = skillID;
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieACC, o2);
                break;
            case PRIERE_DARIA:
                o1.nOption = si.getValue(damR, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamR, o1);
                o2.nOption = si.getValue(ignoreMobpdpR, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(IgnoreMobpdpR, o2);
                break;
            case MAPLE_WARRIOR_PH:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case HEROIC_MEMORIES_PH:
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

    private void giveJudgmentDrawBuff(int skillId) {

        Skill skill = chr.getSkill(skillId);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();

        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        int randomInt = new Random().nextInt((skillId == JUDGMENT_DRAW_1 ? 2 : 5))+1;
        int xOpt = 0;
        switch (randomInt) {
            case 1: // Crit Rate
                xOpt = si.getValue(v, slv);
                break;
            case 2: // Item Drop Rate
                xOpt = si.getValue(w, slv);
                break;
            case 3: // AsrR & TerR
                xOpt = si.getValue(x, slv);
                break;
            case 4: // Defense %
                xOpt = 10;
                break;
            case 5: // Life Drain
                xOpt = 1;
                break;
        }
        chr.write(User.effect(Effect.avatarOriented("Skill/2003.img/skill/20031210/affected/"+ (randomInt-1))));
        chr.write(UserRemote.effect(chr.getId(), Effect.avatarOriented("Skill/2003.img/skill/20031210/affected/"+ (randomInt-1))));

        o.nOption = randomInt;
        o.rOption = skill.getSkillId();
        o.tOption = si.getValue(time, slv);
        o.xOption = xOpt;
        tsm.putCharacterStatValue(Judgement, o);
        tsm.sendSetStatPacket();
    }



    // Attack related methods ------------------------------------------------------------------------------------------

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {
        for(Job jobHandler : stealJobHandlers) {
            jobHandler.handleAttack(c, attackInfo);
        }
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
        if (hasHitMobs && attackInfo.skillId != CARTE_NOIR && attackInfo.skillId != CARTE_BLANCHE) {
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                cartDeck();
                createCarteForceAtom(attackInfo);
            }
            drainLifeByJudgmentDraw();
        }

        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case CARTE_ROSE_FINALE:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    AffectedArea aa = AffectedArea.getAffectedArea(chr, attackInfo);
                    aa.setMobOrigin((byte) 1);
                    aa.setPosition(mob.getPosition());
                    aa.setDelay((short) 13);
                    aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                    chr.getField().spawnAffectedArea(aa);
                }
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    private void createCarteForceAtom(AttackInfo attackInfo) {
        if (chr.hasSkill(CARTE_BLANCHE)) {
            SkillInfo si = SkillData.getSkillInfoById(CARTE_BLANCHE);
            int anglenum = new Random().nextInt(30) + 295;
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if(mob == null) {
                    continue;
                }
                int TW1prop = 100;
                if (Util.succeedProp(TW1prop)) {
                    if (chr.hasSkill(CARTE_NOIR)) {
                        int mobID = mai.mobId;
                        int inc = ForceAtomEnum.PHANTOM_CARD_2.getInc();
                        int type = ForceAtomEnum.PHANTOM_CARD_2.getForceAtomType();
                        ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 20, 35,
                                anglenum, 0, (int) System.currentTimeMillis(), 1, 0,
                                new Position()); //Slightly behind the player
                        chr.getField().broadcastPacket(CField.createForceAtom(false, 0, chr.getId(), type,
                                true, mobID, CARTE_NOIR, forceAtomInfo, new Rect(), 0, 300,
                                mob.getPosition(), CARTE_NOIR, mob.getPosition()));
                    } else if (chr.hasSkill(CARTE_BLANCHE)) {
                        int mobID = mai.mobId;
                        int inc = ForceAtomEnum.PHANTOM_CARD_1.getInc();
                        int type = ForceAtomEnum.PHANTOM_CARD_1.getForceAtomType();
                        ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 20, 40,
                                anglenum, 0, (int) System.currentTimeMillis(), 1, 0,
                                new Position()); //Slightly behind the player
                        chr.getField().broadcastPacket(CField.createForceAtom(false, 0, chr.getId(), type,
                                true, mobID, CARTE_BLANCHE, forceAtomInfo, new Rect(), 0, 300,
                                mob.getPosition(), CARTE_BLANCHE, mob.getPosition()));
                    }
                }
            }
        }
    }

    private void createCarteForceAtomByJudgmentDraw() {
        if (chr.hasSkill(CARTE_BLANCHE)) {
            SkillInfo si = SkillData.getSkillInfoById(CARTE_BLANCHE);
            Rect rect = new Rect(
                    new Position(
                            chr.getPosition().getX() - 450,
                            chr.getPosition().getY() - 450),
                    new Position(
                            chr.getPosition().getX() + 450,
                            chr.getPosition().getY() + 450)
            );
            List<Mob> mobs = chr.getField().getMobsInRect(rect);
            if (mobs.size() <= 0) {
                chr.dispose();
                return;
            }
            Mob mob = Util.getRandomFromCollection(mobs);

            for (int i = 0; i < 10; i++) {
                if (chr.hasSkill(CARTE_NOIR)) {
                    int mobID = mob.getObjectId();
                    int inc = ForceAtomEnum.PHANTOM_CARD_2.getInc();
                    int type = ForceAtomEnum.PHANTOM_CARD_2.getForceAtomType();
                    ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 20, 35,
                            350 - (2 * i), i * 5, (int) System.currentTimeMillis(), 1, 0,
                            new Position()); //Slightly behind the player
                    chr.getField().broadcastPacket(CField.createForceAtom(false, 0, chr.getId(), type,
                            true, mobID, CARTE_NOIR, forceAtomInfo, new Rect(), 0, 300,
                            mob.getPosition(), CARTE_NOIR, mob.getPosition()));
                } else if (chr.hasSkill(CARTE_BLANCHE)) {
                    int mobID = mob.getObjectId();
                    int inc = ForceAtomEnum.PHANTOM_CARD_1.getInc();
                    int type = ForceAtomEnum.PHANTOM_CARD_1.getForceAtomType();
                    ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 20, 40,
                            350 - (2 * i), i * 5, (int) System.currentTimeMillis(), 1, 0,
                            new Position()); //Slightly behind the player
                    chr.getField().broadcastPacket(CField.createForceAtom(false, 0, chr.getId(), type,
                            true, mobID, CARTE_BLANCHE, forceAtomInfo, new Rect(), 0, 300,
                            mob.getPosition(), CARTE_BLANCHE, mob.getPosition()));
                }
            }
        }
    }

    private void drainLifeByJudgmentDraw() {
        if(!chr.hasSkill(JUDGMENT_DRAW_2)) {
            return;
        }
        Skill skill = chr.getSkill(JUDGMENT_DRAW_2);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(tsm.hasStat(Judgement) && tsm.getOption(Judgement).nOption == 5) {
            int healrate = si.getValue(z, slv);
            chr.heal((int) (chr.getMaxHP() * ((double) healrate / 100)));
        }
    }

    private int getMaxCards() {
        int num = 0;
        if (chr.hasSkill(JUDGMENT_DRAW_1)) {
            num = 20;
        }
        if (chr.hasSkill(JUDGMENT_DRAW_2)) {
            num = 40;
        }
        return num;
    }

    private void resetCardStack() {
        setCardAmount((byte) 0);
    }

    public byte getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(byte cardAmount) {
        this.cardAmount = cardAmount;
        c.write(UserLocal.incJudgementStack(getCardAmount()));
    }

    private void cartDeck() {
        if (getCardAmount() < getMaxCards()) {
            setCardAmount((byte) (getCardAmount() + 1));
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
        for(Job jobHandler : stealJobHandlers) {
            jobHandler.handleSkill(c, skillID, slv, inPacket);
        }
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
                case VOL_DAME:
                    stealBuffVolDame();
                    break;
                case TO_THE_SKIES:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = chr.getOrCreateFieldByCurrentInstanceType(o1.nValue);
                    chr.warp(toField);
                    break;
                case JUDGMENT_DRAW_1:
                case JUDGMENT_DRAW_2:
                    createCarteForceAtomByJudgmentDraw();
                    giveJudgmentDrawBuff(skillID);
                    resetCardStack();
                    break;
                case HEROS_WILL_PH:
                    tsm.removeAllDebuffs();
                    break;
            }
        }
    }

    private void stealBuffVolDame() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();

        if(!chr.hasSkill(VOL_DAME)) {
            return;
        }

        Skill skill = chr.getSkill(VOL_DAME);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();

        Rect rect = new Rect(   //NPE when using the skill's rect
                new Position(
                        chr.getPosition().getX() - 250,
                        chr.getPosition().getY() - 250),
                new Position(
                        chr.getPosition().getX() + 250,
                        chr.getPosition().getY() + 250)
        );
        List<Mob> mobs = chr.getField().getMobsInRect(rect);
        if(mobs.size() <= 0) {
            return;
        }
        MobStat buffFromMobStat = MobStat.Mystery; //Needs to be initialised
        MobStat[] mobStats = new MobStat[]{ //Ordered from Weakest to Strongest, since  the for loop will save the last MobsStat
                PCounter,           //Dmg Reflect 600%
                MCounter,           //Dmg Reflect 600%
                PImmune,            //Dmg Recv -40%
                MImmune,            //Dmg Recv -40%
                PowerUp,            //Attack +40
                MagicUp,            //Attack +40
                MobStat.Invincible, //Invincible for short time
        };
        for (Mob mob : mobs) {
            MobTemporaryStat mts = mob.getTemporaryStat();
            List<MobStat> currentMobStats = Arrays.stream(mobStats).filter(mts::hasCurrentMobStat).collect(Collectors.toList());
            for (MobStat currentMobStat : currentMobStats) {
                if (mts.hasCurrentMobStat(currentMobStat)) {
                    mts.removeMobStat(currentMobStat, true);
                    buffFromMobStat = currentMobStat;
                }
            }
        }
        switch (buffFromMobStat) {
            case PCounter:
            case MCounter:
                o1.nOption = si.getValue(y, slv);
                o1.rOption = skill.getSkillId();
                o1.tOption = 30;
                tsm.putCharacterStatValue(PowerGuard, o1);
                break;
            case PImmune:
            case MImmune:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skill.getSkillId();
                o1.tOption = 30;
                tsm.putCharacterStatValue(CharacterTemporaryStat.EVA, o1); //as a check to allow for DmgReduction in the Hit Handler
                break;
            case PowerUp:
            case MagicUp:
                o1.nOption = si.getValue(epad, slv);
                o1.rOption = skill.getSkillId();
                o1.tOption = 30;
                tsm.putCharacterStatValue(CharacterTemporaryStat.PAD, o1);
                break;
            case Invincible:
                o1.nOption = 1;
                o1.rOption = skill.getSkillId();
                o1.tOption = 5;
                tsm.putCharacterStatValue(NotDamaged, o1);
                break;
        }
        tsm.sendSetStatPacket();
    }



    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        for(Job jobHandler : stealJobHandlers) {
            jobHandler.handleHit(c, inPacket, hitInfo);
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!chr.hasSkill(VOL_DAME)) {
            return;
        }
        if (tsm.getOptByCTSAndSkill(CharacterTemporaryStat.EVA, VOL_DAME) != null) {
            Skill skill = chr.getSkill(VOL_DAME);
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int dmgPerc = si.getValue(x, skill.getCurrentLevel());
            int dmg = hitInfo.hpDamage;
            hitInfo.hpDamage = dmg - (dmg * (dmgPerc / 100));
        }

        super.handleHit(c, inPacket, hitInfo);
    }

    public static void reviveByFinalFeint(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        Skill skill = chr.getSkill(FINAL_FEINT);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();

        chr.heal(chr.getMaxHP());
        tsm.removeStatsBySkill(skill.getSkillId());
        tsm.sendResetStatPacket();
        chr.chatMessage("You have been revived by Final Feint.");
        chr.write(User.effect(Effect.skillSpecial(skill.getSkillId())));
        chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillSpecial(skill.getSkillId())));

        o.nOption = 1;
        o.rOption = skill.getSkillId();
        o.tStart = si.getValue(y, slv);
        tsm.putCharacterStatValue(NotDamaged, o);
        tsm.sendSetStatPacket();
    }
}