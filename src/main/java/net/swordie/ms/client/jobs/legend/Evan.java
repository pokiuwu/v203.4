package net.swordie.ms.client.jobs.legend;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.client.character.SPSet;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.*;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.ForceAtomInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatBase;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.*;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.connection.packet.CField;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobTemporaryStat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;
import static net.swordie.ms.client.character.skills.SkillStat.*;

/**
 * Created on 12/14/2017.
 */
public class Evan extends Job {

    public static final int INHERITED_WILL = 20010194;
    public static final int BACK_TO_NATURE = 20011293;

    public static final int MAGIC_GUARD = 22001012; // Buff

    public static final int MAGIC_BOOSTER = 22111020; // Buff
    public static final int ELEMENTAL_DECREASE = 22141016; // Buff
    public static final int PARTNERS = 22110016;

    public static final int BLESSING_OF_THE_ONYX = 22181000; // Buff
    public static final int MAPLE_WARRIOR_EVAN = 22171000; // Buff
    public static final int MAGIC_DEBRIS = 22141017;

    public static final int DRAGON_MASTER = 22171080; // Mount
    public static final int DRAGON_MASTER_2 = 22171083; // Add-on
    public static final int SUMMON_ONYX_DRAGON = 22171081; // Summon
    public static final int HEROIC_MEMORIES_EVAN = 22171082;
    public static final int ENHANCED_MAGIC_DEBRIS = 22170070;
    public static final int HEROS_WILL_EVAN = 22171004;
    public static final int DRAGON_FURY = 22170074;

    //Returns
    public static final int RETURN_FLASH = 22110013; // Return after Wind Skills (Mob Debuff)
    public static final int RETURN_DIVE = 22140013; // Return Dive (Buff)
    public static final int RETURN_FLAME = 22170064; // Return Flame (Flame  AoE)
    public static final int RETURN_FLAME_TILE = 22170093; // Return Flames Tile


    //Evan Attacks
    public static final int MANA_BURST_I = 22001010;
    public static final int MANA_BURST_II = 22110010;
    public static final int MANA_BURST_III = 22140010;
    public static final int MANA_BURST_IV_1 = 22170060;
    public static final int MANA_BURST_IV_2 = 22170061;
    public static final int WIND_CIRCLE = 22111011;
    public static final int THUNDER_CIRCLE = 22141011;
    public static final int EARTH_CIRCLE = 22171062;
    public static final int DARK_FOG = 22171095;

    //Final Attack
    public static final int DRAGON_SPARK = 22000015;
    public static final int ADV_DRAGON_SPARK = 22110021;

    private int prevSkill = 0;
    public HashMap<Integer, Position> debrisPos = new HashMap<>();
    private int debrisCount = 0;
    private Field oldField;

    private int[] addedSkills = new int[] {
            INHERITED_WILL,
            BACK_TO_NATURE,
    };

    private final int[] buffs = new int[]{
            MAGIC_GUARD,
            MAGIC_BOOSTER,
            ELEMENTAL_DECREASE,
            BLESSING_OF_THE_ONYX,
            MAPLE_WARRIOR_EVAN,
            HEROIC_MEMORIES_EVAN,
            SUMMON_ONYX_DRAGON,
            DRAGON_MASTER,
            DRAGON_MASTER_2,
            RETURN_DIVE,
    };

    public Evan(Char chr) {
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

    public int getEvanSkill(int skillID) {
        switch (skillID) {
            case MANA_BURST_I:
            case MANA_BURST_II:
            case MANA_BURST_III:
            case MANA_BURST_IV_1:
            case MANA_BURST_IV_2:
            case WIND_CIRCLE:
            case THUNDER_CIRCLE:
            case EARTH_CIRCLE:
            case DARK_FOG:
                return 1;

        }
        return skillID;
    }


    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isEvan(id);
    }



    // Buff related methods --------------------------------------------------------------------------------------------

    public void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        TemporaryStatBase tsb = tsm.getTSBByTSIndex(TSIndex.RideVehicle);
        Summon summon;
        Field field;
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case MAGIC_GUARD:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(MagicGuard, o1);
                break;
            case MAGIC_BOOSTER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case RETURN_DIVE:
                o1.nReason = skillID;
                o1.nValue = 1; //si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case ELEMENTAL_DECREASE:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ElementalReset, o1);
                break;
            case BLESSING_OF_THE_ONYX:
                o1.nOption = si.getValue(emad, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EMAD, o1);
                o2.nOption = si.getValue(emdd, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EMDD, o2);
                o3.nOption = si.getValue(epdd, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EPDD, o3);
                break;
            case MAPLE_WARRIOR_EVAN:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case HEROIC_MEMORIES_EVAN:
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
            case SUMMON_ONYX_DRAGON:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(true);
                summon.setMoveAbility(MoveAbility.Stop);
                field.spawnSummon(summon);
                break;


            case DRAGON_MASTER:
                tsb.setNOption(1939007);
                tsb.setROption(skillID);
                tsm.putCharacterStatValue(RideVehicle, tsb.getOption());
                tsm.sendSetStatPacket();

                o1.nOption = 1;
                o1.rOption = DRAGON_MASTER;
                o1.tOption = 30;
                //tsm.putCharacterStatValue(NewFlying, o1);
                break;
            case DRAGON_MASTER_2:
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
        if(chr.getField() != oldField) {
            debrisCount = 0;
            c.write(CField.delWreckage(chr));
        }
        oldField = chr.getField();
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
        if(hasHitMobs) {
            // Partners
            if (getEvanSkill(skillID) != 1 && chr.hasSkill(PARTNERS)) {
                givePartnersBuff(skillID);
            }

            // Wreckage / Magic Debris
            if(SkillConstants.isEvanFusionSkill(attackInfo.skillId)) {
                if(attackInfo.skillId != MAGIC_DEBRIS && attackInfo.skillId != ENHANCED_MAGIC_DEBRIS) {
                    for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        if(debrisCount < getMaxDebris()) {
                            debrisPos.put(debrisCount, mob.getPosition());
                            debrisCount++;
                            c.write(CField.addWreckage(chr, mob, getDebrisSkill(), debrisCount));
                        }
                    }
                }
            }
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
        }

        super.handleAttack(c, attackInfo);
    }

    public void givePartnersBuff(int skillID) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(PARTNERS);
        Option o = new Option();
        Option o1 = new Option();
        if(tsm.getOptByCTSAndSkill(Stance, PARTNERS) == null) {
            prevSkill = skillID;
            o.nReason = PARTNERS;
            o.nValue = si.getValue(indieDamR, 1);
            o.tStart = (int) System.currentTimeMillis();
            o.tTerm = 3;
            tsm.putCharacterStatValue(IndieDamR, o);
            o1.nOption = si.getValue(stanceProp, 1);
            o1.rOption = PARTNERS;
            o1.tOption = 3;
            tsm.putCharacterStatValue(Stance, o1);
            tsm.sendSetStatPacket();
        }
    }


    private void createMagicDebrisForceAtom() {
        Field field = chr.getField();
        SkillInfo si = SkillData.getSkillInfoById(getDebrisSkill());
        Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
        if(!chr.isLeft()) {
            rect = rect.moveRight();
        }
        List<Mob> lifes =  field.getMobsInRect(rect);
        if(lifes.size() <= 0) {
            return;
        }
        for(int i = 0; i<debrisCount; i++) {
            c.write(CField.delWreckage(chr));
            Life life = Util.getRandomFromCollection(lifes);
            int mobID = (life).getObjectId(); //
            int inc = ForceAtomEnum.WRECKAGE.getInc();
            int type = ForceAtomEnum.WRECKAGE.getForceAtomType();
            if(chr.hasSkill(ENHANCED_MAGIC_DEBRIS)) {
                inc = ForceAtomEnum.ADV_WRECKAGE.getInc();
                type = ForceAtomEnum.ADV_WRECKAGE.getForceAtomType();
            }
            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 15, 10,
                    0, 200, (int) System.currentTimeMillis(), 1, 0,
                    debrisPos.get(i));
            chr.getField().broadcastPacket(CField.createForceAtom(false, 0, chr.getId(), type,
                    true, mobID, getDebrisSkill(), forceAtomInfo, new Rect(), 0, 300,
                    life.getPosition(), getDebrisSkill(), life.getPosition()));
        }
        debrisCount = 0;
        debrisPos.clear();
    }

    private int getMaxDebris() {
        Skill skill = null;
        if(chr.hasSkill(MAGIC_DEBRIS)) {
            skill = chr.getSkill(MAGIC_DEBRIS);
        }
        if(chr.hasSkill(ENHANCED_MAGIC_DEBRIS)) {
            skill = chr.getSkill(ENHANCED_MAGIC_DEBRIS);
        }
        if (skill == null) {
            return 0;
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();
        return si.getValue(x, slv);
    }

    private int getDebrisSkill() {
        int skill = 0;
        if(chr.hasSkill(MAGIC_DEBRIS)) {
            skill = MAGIC_DEBRIS;
        }
        if(chr.hasSkill(ENHANCED_MAGIC_DEBRIS)) {
            skill = ENHANCED_MAGIC_DEBRIS;
        }
        return skill;
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
        if(chr.hasSkill(DRAGON_SPARK)) {
            skill = chr.getSkill(DRAGON_SPARK);
        }
        if(chr.hasSkill(ADV_DRAGON_SPARK)) {
            skill = chr.getSkill(ADV_DRAGON_SPARK);
        }
        return skill;
    }



    // Skill related methods -------------------------------------------------------------------------------------------

    @Override
    public void handleSkill(Client c, int skillID, byte slv, InPacket inPacket) {
        super.handleSkill(c, skillID, slv, inPacket);
        if(chr.getField() != oldField) {
            debrisCount = 0;
            c.write(CField.delWreckage(chr));
        }
        oldField = chr.getField();
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
                case BACK_TO_NATURE:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = chr.getOrCreateFieldByCurrentInstanceType(o1.nValue);
                    chr.warp(toField);
                    break;
                case RETURN_FLAME:
                    SkillInfo rft = SkillData.getSkillInfoById(RETURN_FLAME_TILE);
                    AffectedArea aa = AffectedArea.getPassiveAA(chr, RETURN_FLAME_TILE, slv);
                    aa.setMobOrigin((byte) 0);
                    aa.setPosition(chr.getPosition());
                    aa.setRect(aa.getPosition().getRectAround(rft.getRects().get(0)));
                    chr.getField().spawnAffectedArea(aa);
                    break;
                case RETURN_FLASH:
                    SkillInfo rflash = SkillData.getSkillInfoById(RETURN_FLASH);
                    Rect rect = new Rect(       //Skill itself doesn't give a Rect
                            new Position(
                                    chr.getPosition().deepCopy().getX() - 300,
                                    chr.getPosition().deepCopy().getY() - 300),
                            new Position(
                                    chr.getPosition().deepCopy().getX() + 300,
                                    chr.getPosition().deepCopy().getY() + 300)
                    );
                    for(Life life : chr.getField().getLifesInRect(rect)) {
                        if(life instanceof Mob && ((Mob) life).getHp() > 0) {
                            Mob mob = (Mob) life;
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            o1.nOption = rflash.getValue(x, slv);
                            o1.rOption = skillID;
                            o1.tOption = rflash.getValue(time, slv);
                            mts.addStatOptionsAndBroadcast(MobStat.AddDamParty, o1);

                        }
                    }
                    break;
                case MAGIC_DEBRIS:
                case ENHANCED_MAGIC_DEBRIS:
                    createMagicDebrisForceAtom();
                    break;
                case HEROS_WILL_EVAN:
                    tsm.removeAllDebuffs();
                    break;
            }
        }
    }



    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(MagicGuard)) {
            Skill skill = chr.getSkill(MAGIC_GUARD);
            SkillInfo si = SkillData.getSkillInfoById(MAGIC_GUARD);
            int dmgPerc = si.getValue(x, skill.getCurrentLevel());
            int dmg = hitInfo.hpDamage;
            int mpDmg = (int) (dmg * (dmgPerc / 100D));
            mpDmg = chr.getStat(Stat.mp) - mpDmg < 0 ? chr.getStat(Stat.mp) : mpDmg;
            hitInfo.hpDamage = dmg - mpDmg;
            hitInfo.mpDamage = mpDmg;
        }
        super.handleHit(c, inPacket, hitInfo);
    }
    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        CharacterStat cs = chr.getAvatarData().getCharacterStat();
        cs.setPosMap(900010000);
    }
    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
    }
}
