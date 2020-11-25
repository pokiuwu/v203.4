package net.swordie.ms.client.jobs.sengoku;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.CField;
import net.swordie.ms.connection.packet.DropPool;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.drop.Drop;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */

public class Kanna extends Job {

    public static final int HAKU = 40020109;

    public static final int RADIANT_PEACOCK = 42101003;
    public static final int NIMBUS_CURSE = 42101005;
    public static final int HAKU_REBORN = 42101002;

    public static final int KISHIN_SHOUKAN = 42111003; //summon
    public static final int BLOSSOM_BARRIER = 42111004; //AoE
    public static final int SOUL_SHEAR = 42111002; //Reactive Skill [4033270 - soul shear balls]
    public static final int SOUL_SHEAR_BOMB_ITEM_ID = 4033270;

    public static final int MONKEY_SPIRITS = 42120003; //Passive activation summon
    public static final int BELLFLOWER_BARRIER = 42121005; //AoE
    public static final int AKATUSKI_HERO_KANNA = 42121006;
    public static final int NINE_TAILED_FURY = 42121024; //Attacking Skill + Buff
    public static final int BINDING_TEMPEST = 42121004;
    public static final int BLOSSOMING_DAWN = 42121007;

    public static final int VERITABLE_PANDEMONIUM = 42121052; //Immobility Debuff
    public static final int PRINCESS_VOW_KANNA = 42121053;
    public static final int BLACKHEARTED_CURSE = 42121054;

    //Haku Buffs
    public static final int HAKUS_GIFT = 42121020;
    public static final int FOXFIRE = 42121021;
    public static final int HAKUS_BLESSING = 42121022;
    public static final int BREATH_UNSEEN = 42121023;

    private int[] buffs = new int[]{
            HAKU_REBORN,
            RADIANT_PEACOCK,
            KISHIN_SHOUKAN,
            AKATUSKI_HERO_KANNA,
            NINE_TAILED_FURY,
            PRINCESS_VOW_KANNA,
            BLACKHEARTED_CURSE,
    };

    public Kanna(Char chr) {
        super(chr);
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isKanna(id);
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
        switch (skillID) {
            case HAKU_REBORN:
                o1.nOption = 0;
                o1.rOption = skillID;
                o1.tOption = 30;
                tsm.putCharacterStatValue(ChangeFoxMan, o1);
                break;
            case RADIANT_PEACOCK:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case KISHIN_SHOUKAN: //TODO
                chr.getField().setKishin(true);

                Summon.summonKishin(chr, slv);
                break;
            case AKATUSKI_HERO_KANNA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;
            case PRINCESS_VOW_KANNA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieMaxDamageOver, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMaxDamageOver, o2);
                break;
            case BLACKHEARTED_CURSE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(BlackHeartedCurse, o1);
                break;
        }
        tsm.sendSetStatPacket();

    }

    public boolean isBuff(int skillID) {
        return super.isBuff(skillID) || Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    public void spawnHaku() {
        c.write(CField.enterFieldFoxMan(chr));
    }

    public static void hakuFoxFire(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(FOXFIRE);
        int slv = si.getCurrentLevel();
        Option o1 = new Option();

        o1.nOption = 6;
        o1.rOption = FOXFIRE;
        o1.tOption = si.getValue(time, slv);
        tsm.putCharacterStatValue(FireBarrier, o1);
        tsm.sendSetStatPacket();
    }

    public static void hakuHakuBlessing(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(HAKUS_BLESSING);
        int slv = si.getCurrentLevel();
        Option o1 = new Option();

        o1.nReason = HAKUS_BLESSING;
        o1.nValue = si.getValue(indiePdd, slv);
        o1.tStart = (int) System.currentTimeMillis();
        o1.tTerm = si.getValue(time, slv);
        tsm.putCharacterStatValue(IndiePDD, o1);
        tsm.sendSetStatPacket();
    }

    public static void hakuBreathUnseen(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(BREATH_UNSEEN);
        int slv = si.getCurrentLevel();
        Option o1 = new Option();
        Option o2 = new Option();

        o1.nOption = si.getValue(prop, slv);
        o1.rOption = BREATH_UNSEEN;
        o1.tOption = si.getValue(time, slv);
        tsm.putCharacterStatValue(Stance, o1);
        o2.nOption = si.getValue(x, slv);
        o2.rOption = BREATH_UNSEEN;
        o2.tOption = si.getValue(time, slv);
        tsm.putCharacterStatValue(IgnoreMobpdpR, o2);
        tsm.sendSetStatPacket();
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
        dropSoulShearBomb(attackInfo);
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case BINDING_TEMPEST:
            case VERITABLE_PANDEMONIUM:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
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

                break;
            case NIMBUS_CURSE:
                AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, (byte) slv);
                aa.setMobOrigin((byte) 0);
                aa.setPosition(chr.getPosition());
                aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                aa.setDelay((short) 5);
                chr.getField().spawnAffectedArea(aa);
                break;
            case SOUL_SHEAR:
                explodeSoulShearBomb();
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    private void dropSoulShearBomb(AttackInfo attackInfo) {
        if (!chr.hasSkill(SOUL_SHEAR)) {
            return;
        }
        Field field = chr.getField();
        Skill skill = chr.getSkill(SOUL_SHEAR);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();
        int proc = si.getValue(prop, slv);
        for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
            if (Util.succeedProp(proc)) {
                Mob mob = (Mob) field.getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                Item item = ItemData.getItemDeepCopy(SOUL_SHEAR_BOMB_ITEM_ID);
                Drop drop = new Drop(item.getItemId(), item);
                field.drop(drop, mob.getPosition());
            }
        }
    }

    private void explodeSoulShearBomb() {
        Set<Drop> soulShearBombSet = chr.getField().getDrops().stream().filter(d -> !d.isMoney() && d.getItem().getItemId() == SOUL_SHEAR_BOMB_ITEM_ID).collect(Collectors.toSet());
        for(Drop soulShearBomb : soulShearBombSet) {
            chr.getField().broadcastPacket(DropPool.dropExplodeField(soulShearBomb.getObjectId()));
            soulShearBomb.broadcastLeavePacket();
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
                case BLOSSOM_BARRIER:
                case BELLFLOWER_BARRIER:
                    AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                    aa.setMobOrigin((byte) 0);
                    aa.setPosition(chr.getPosition());
                    aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                    aa.setDelay((short) 3);
                    chr.getField().spawnAffectedArea(aa);
                    break;
                case NINE_TAILED_FURY:
                    o1.nReason = skillID;
                    o1.nValue = si.getValue(indieDamR, slv);
                    o1.tStart = (int) System.currentTimeMillis();
                    o1.tTerm = si.getValue(time, slv);
                    tsm.putCharacterStatValue(IndieDamR, o1); //Indie
                    tsm.sendSetStatPacket();
                    break;
                case BLOSSOMING_DAWN:
                    tsm.removeAllDebuffs();
                    break;
            }
        }
    }



    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        int foxfires = 6;
        if (tsm.hasStat(FireBarrier)) {
            if(foxfires > 1) {
                foxfires = foxfires - 1;
                }
            if(foxfires == 4 || foxfires == 3) {
                o.nOption = 2;
                tsm.putCharacterStatValue(FireBarrier, o);
                tsm.sendSetStatPacket();
            } else if(foxfires == 2) {
                o.nOption = 1;
                tsm.putCharacterStatValue(FireBarrier, o);
                tsm.sendSetStatPacket();
            } else if (foxfires == 1) {
                resetFireBarrier();
                o.nOption = 0;
                tsm.putCharacterStatValue(FireBarrier, o);
                tsm.sendSetStatPacket();
            }
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    public void resetFireBarrier() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        tsm.removeStat(FireBarrier, false);
        tsm.sendResetStatPacket();
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        CharacterStat cs = chr.getAvatarData().getCharacterStat();
        cs.setPosMap(807100110);
    }
}