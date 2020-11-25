package net.swordie.ms.client.jobs.legend;

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
import net.swordie.ms.connection.packet.Summoned;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.enums.LeaveType;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.enums.Stat;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.*;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Mercedes extends Job {
    //Link Skill = return skill

    public static final int ELVEN_GRACE = 20020112;
    public static final int UPDRAFT = 20020111;
    public static final int ELVEN_HEALING = 20020109;

    public static final int DUAL_BOWGUN_BOOSTER = 23101002; //Buff

    public static final int STUNNING_STRIKES = 23111000; //Special Attack
    public static final int UNICORN_SPIKE = 23111002; //Special Attack
    public static final int IGNIS_ROAR = 23111004; //Buff
    public static final int WATER_SHIELD = 23111005; //Buff
    public static final int ELEMENTAL_KNIGHTS_BLUE = 23111008; //Summon
    public static final int ELEMENTAL_KNIGHTS_RED = 23111009; //Summon
    public static final int ELEMENTAL_KNIGHTS_PURPLE = 23111010; //Summon

    public static final int SPIKES_ROYALE = 23121002;  //Special Attack
    public static final int STAGGERING_STRIKES = 23120013; //Special Attack
    public static final int ANCIENT_WARDING = 23121004; //Buff
    public static final int MAPLE_WARRIOR_MERC = 23121005; //Buff
    public static final int LIGHTNING_EDGE = 23121003; //Debuff mobs
    public static final int HEROS_WILL_MERC = 23121008;

    public static final int HEROIC_MEMORIES_MERC = 23121053;
    public static final int ELVISH_BLESSING = 23121054;
    public static final int WRATH_OF_ENLIL = 23121052;

    //Final Attack
    public static final int FINAL_ATTACK_DBG = 23100006;
    public static final int ADVANCED_FINAL_ATTACK = 23120012;

    private int[] addedSkills = new int[] {
            ELVEN_GRACE,
            UPDRAFT,
            ELVEN_HEALING,
    };

    private final int[] buffs = new int[]{
            DUAL_BOWGUN_BOOSTER,
            IGNIS_ROAR,
            WATER_SHIELD,
            ELEMENTAL_KNIGHTS_BLUE, //Summon
            ELEMENTAL_KNIGHTS_RED, //Summon
            ELEMENTAL_KNIGHTS_PURPLE, //Summon
            ANCIENT_WARDING,
            MAPLE_WARRIOR_MERC,
            HEROIC_MEMORIES_MERC,
            ELVISH_BLESSING,
    };

    private final int[] summonAttacks = new int[] {
            ELEMENTAL_KNIGHTS_BLUE,
            ELEMENTAL_KNIGHTS_RED,
            ELEMENTAL_KNIGHTS_PURPLE,
    };

    private int eleKnightSummonID = 1;
    private int lastAttackSkill = 0;
    private List<Summon> summonList = new ArrayList<>();

    public Mercedes(Char chr) {
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
        return JobConstants.isMercedes(id);
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        chr.getAvatarData().getAvatarLook().setDrawElfEar(true);
        /*Item item = ItemData.getItemDeepCopy(1352000); // Secondary
        chr.addItemToInventory(item);*/

        CharacterStat cs = chr.getAvatarData().getCharacterStat();
        cs.setPosMap(910150000);
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
        Summon summon;
        Field field;
        switch (skillID) {
            case DUAL_BOWGUN_BOOSTER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case IGNIS_ROAR:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(IgnisRore, o1);
                o2.nValue = si.getValue(indiePad, slv);
                o2.nReason = skillID;
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o2);
                break;
            case WATER_SHIELD:
                o1.nOption = si.getValue(asrR, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AsrR, o1);
                o2.nOption = si.getValue(terR, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(TerR, o2);
                o3.nOption = si.getValue(x, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamAbsorbShield, o3);   //IgnoreMobDamR
                break;
            case ANCIENT_WARDING:
                o1.nOption = si.getValue(emhp, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EMHP, o1);
                o2.nValue = si.getValue(indiePadR, slv);
                o2.nReason = skillID;
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePADR, o2);
                break;
            case MAPLE_WARRIOR_MERC:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case HEROIC_MEMORIES_MERC:
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
            case ELVISH_BLESSING:
                o1.nValue = si.getValue(indiePad, slv);
                o1.nReason = skillID;
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1);
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Stance, o2);
                break;
            case ELEMENTAL_KNIGHTS_BLUE:
                summonEleKnights();
                break;
        }
        tsm.sendSetStatPacket();
    }

    public boolean isBuff(int skillID) {
        return super.isBuff(skillID) || Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    private void summonEleKnights() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        List<Integer> set = new ArrayList<>();
        set.add(ELEMENTAL_KNIGHTS_BLUE);
        set.add(ELEMENTAL_KNIGHTS_RED);
        set.add(ELEMENTAL_KNIGHTS_PURPLE);

        if(eleKnightSummonID != 0) {
            set.remove((Integer) eleKnightSummonID);
        }
        int random = Util.getRandomFromCollection(set);
        eleKnightSummonID = random;
        Summon summon = Summon.getSummonBy(chr, random, (byte) 1);
        Field field = chr.getField();
        summon.setMoveAbility(MoveAbility.Fly);
        summon.setSummonTerm(0);

        summonList.add(summon);
        if(summonList.size() > 2) {
            c.write(Summoned.summonedRemoved(summonList.get(0), LeaveType.ANIMATION));
            tsm.removeStatsBySkill(summonList.get(0).getSkillID());
            summonList.remove(0);
        }

        field.spawnSummon(summon);

        SkillInfo si = SkillData.getSkillInfoById(ELEMENTAL_KNIGHTS_BLUE);
        byte slv = (byte) chr.getSkill(ELEMENTAL_KNIGHTS_BLUE).getCurrentLevel();
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
        if (hasHitMobs) {
            incrementIgnisRoarStackCount(tsm, attackInfo);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case STUNNING_STRIKES:
                SkillInfo stunningStrikes = SkillData.getSkillInfoById(STUNNING_STRIKES);
                int proc = stunningStrikes.getValue(prop, slv);
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if(Util.succeedProp(proc)) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if(mob == null) {
                            continue;
                        }
                        if(!mob.isBoss()) {
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            o1.nOption = 1;
                            o1.rOption = STUNNING_STRIKES;
                            o1.tOption = si.getValue(time, slv);
                            mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                        }
                    }
                }
                break;
            case STAGGERING_STRIKES:
                SkillInfo staggeringStrikes = SkillData.getSkillInfoById(STAGGERING_STRIKES);
                int procc = staggeringStrikes.getValue(prop, slv);
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if(Util.succeedProp(procc)) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        if(!mob.isBoss()) {
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            o1.nOption = 1;
                            o1.rOption = STAGGERING_STRIKES;
                            o1.tOption = si.getValue(time, slv);
                            mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                        }
                    }
                }
                break;
            case UNICORN_SPIKE:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if(Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = si.getValue(x, slv);
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.AddDamParty, o1); // ?
                    }
                }
                break;
            case SPIKES_ROYALE:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = - si.getValue(x, slv);
                    o1.rOption = skill.getSkillId();
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.PDR, o1);
                    mts.addStatOptionsAndBroadcast(MobStat.MDR, o1);
                }
                break;
            case LIGHTNING_EDGE:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = si.getValue(x, slv);
                    o1.rOption = skill.getSkillId();
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.AddDamSkill, o1);
                }
                break;
            case ELEMENTAL_KNIGHTS_BLUE:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if(Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = si.getValue(subTime, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Freeze, o1);
                    }
                }
                break;
            case ELEMENTAL_KNIGHTS_RED:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr, skill);
                }
                break;
        }
        super.handleAttack(c, attackInfo);
    }

    private void incrementIgnisRoarStackCount(TemporaryStatManager tsm, AttackInfo attackInfo) {
        if (Arrays.asList(summonAttacks).contains(attackInfo.skillId)
                || attackInfo.skillId == getFinalAtkSkill().getSkillId()
                || attackInfo.skillId == lastAttackSkill) {
            return;
        }
        Option o = new Option();
        Option o1 = new Option();
        Skill skill = chr.getSkill(IGNIS_ROAR);
        if (skill == null) {
            return;
        }
        SkillInfo ignisRoarInfo = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();
        int amount = 1;
        SkillInfo si = SkillData.getSkillInfoById(lastAttackSkill);
        if(si != null && si.getAddAttackSkills().stream().noneMatch(aas -> aas == attackInfo.skillId)) {
            return;
        }
        if(attackInfo.skillId != SPIKES_ROYALE) {
            chr.reduceSkillCoolTime(SPIKES_ROYALE, 1000);
        }
        if(attackInfo.skillId != UNICORN_SPIKE) {
            chr.reduceSkillCoolTime(UNICORN_SPIKE, 1000);
        }
        if(attackInfo.skillId != WRATH_OF_ENLIL) {
            chr.reduceSkillCoolTime(WRATH_OF_ENLIL, 1000);
        }
        if (tsm.hasStat(IgnisRore)) {
            if (tsm.hasStat(AddAttackCount)) {
                amount = tsm.getOption(AddAttackCount).nOption;
                if (amount < ignisRoarInfo.getValue(y, slv)) {
                    amount++;
                }
            }
            lastAttackSkill = attackInfo.skillId;
            o.nOption = amount;
            o.rOption = IGNIS_ROAR;
            o.tOption = ignisRoarInfo.getValue(subTime, slv);
            tsm.putCharacterStatValue(AddAttackCount, o);
            o1.nOption = (amount * ignisRoarInfo.getValue(x, slv));
            o1.rOption = IGNIS_ROAR;
            o1.tOption = ignisRoarInfo.getValue(subTime, slv);
            tsm.putCharacterStatValue(DamR, o1);
            tsm.sendSetStatPacket();
        }
    }

    @Override
    public int getFinalAttackSkill() {
        Skill faSkill = getFinalAtkSkill();
        if(faSkill != null) {
            SkillInfo si = SkillData.getSkillInfoById(faSkill.getSkillId());
            byte slv = (byte) faSkill.getCurrentLevel();
            int proc = si.getValue(prop, slv);
            if (Util.succeedProp(proc)) {
                return faSkill.getSkillId();
            }
        }
        return 0;
    }

    private Skill getFinalAtkSkill() {
        Skill skill = null;
        if(chr.hasSkill(FINAL_ATTACK_DBG)) {
            skill = chr.getSkill(FINAL_ATTACK_DBG);
        }
        if(chr.hasSkill(ADVANCED_FINAL_ATTACK)) {
            skill = chr.getSkill(ADVANCED_FINAL_ATTACK);
        }
        return skill;
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
                case HEROS_WILL_MERC:
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
