package net.swordie.ms.client.jobs.flora;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.skills.ForceAtom;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.ForceAtomInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.CField;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import javax.persistence.Basic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledFuture;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created by MechAviv on 1/19/2019.
 */
public class Ark extends Job {
    // secondary 1353603
    public static final int SPECTER_STATE = 155000007;
    public static final int SPECTRA_FARIGUE = 155001008;

    // Charge Drivers
    public static final int BASIC_CHARGE_DRIVE_ATTACK = 155001100;
    public static final int BASIC_CHARGE_DRIVE_ATOM = 155001000;
    public static final int BASIC_CHARGE_DRIVE_BUFF = 155001001;

    public static final int SCARLET_CHARGE_DRIVE_ATTACK_1 = 155101100;
    public static final int SCARLET_CHARGE_DRIVE_ATTACK_2 = 155101101;
    public static final int SCARLET_CHARGE_DRIVE_ATTACK_COMBO_1 = 155101112;
    public static final int SCARLET_CHARGE_DRIVE_ATTACK_COMBO_2 = 155101013;
    public static final int SCARLET_CHARGE_DRIVE_ATTACK_3 = 155101015;
    public static final int SCARLET_CHARGE_DRIVE_ATOM = 155101002;
    public static final int SCARLET_CHARGE_DRIVE_BUFF = 155101002;

    public static final int GUST_CHARGE_DRIVE_ATTACK = 155111102;
    public static final int GUST_CHARGE_DRIVE_ATTACK_COMBO_1 = 155111002;
    public static final int GUST_CHARGE_DRIVE_ATTACK__COMBO_2 = 155111111;
    public static final int GUST_CHARGE_DRIVE_ATOM_1 = 155111003;
    public static final int GUST_CHARGE_DRIVE_ATOM_2 = 155111004;
    public static final int GUST_CHARGE_DRIVE_BUFF = 155111005;

    public static final int ABYSSAL_CHARGE_DRIVE_ATTACK_1 = 155121102;
    public static final int ABYSSAL_CHARGE_DRIVE_ATTACK_2 = 155121002;
    public static final int ABYSSAL_CHARGE_DRIVE_ATOM_1 = 155121003;
    public static final int ABYSSAL_CHARGE_DRIVE_ATOM_2 = 155121004;
    public static final int ABYSSAL_CHARGE_DRIVE_BUFF = 155121005;

    // 1st job
    public static final int OMINOUS_NIGHTMARE = 155001102;
    public static final int SPELL_BULLETS = 155001103;

    // 2nd job
    public static final int VIVID_NIGHTMARE = 155110000;
    public static final int KNUCKLE_BOOSTER = 155101005;
    public static final int MASTER_CORRUPTION = 155101006;
    public static final int IMPENDING_DEATH_ATOM = 155100009;
    public static final int IMPENDING_DEATH = 155101008;

    // 3rd job
    public static final int CREEPING_TERROR = 155111006;
    public static final int CREEPING_TERROR_HELD_DOWN = 155111306;
    public static final int VENGEFUL_HATE = 155111207;

    // 4th job
    public static final int ENDLESS_NIGHTMARE = 155120000;
    public static final int HERO_OF_THE_FLORA = 155121008;
    public static final int FLORAN_HERO_WILL = 155121009;
    public static final int BLISSFUL_RESTRAINT_TILE = 155121006;
    public static final int BLISSFUL_RESTRAINT_ATTACK = 155121306;
    public static final int ENDLESS_DREAM = 155120001;
    public static final int ENHANCED_SPECTRA = 155120034;

    // Hyper Skills
    public static final int ENDLESS_AGONY = 155121341;
    public static final int DIVINE_WRATH =  155121042;
    public static final int CHARGE_SPELL_AMPLIFIER =  155121043;

    // V skills
    public static final int ABYSSAL_RECALL = 400051334;
    public static final int INFINITY_SPELL = 400051036;
    public static final int NIGHTMARE_ESCAPE = 400051047;
    public static final int DREAM_ESCAPE = 400051048;

    private List<SpellChargeType> spellCharges = new ArrayList<>();
    private long lastEnergyUpdate = 0L;

    private int[] addedSkills = new int[] {
           // SPECTER_STATE,
          //  SPECTRA_FARIGUE,
          //  MASTER_CORRUPTION
    };

    public enum SpellChargeType {
        Basic(15500),
        Scarlet(15510),
        Gust(15511),
        Abyssal(15512);
        private int val;

        SpellChargeType(int val) { this.val = val; }

        public int getVal() { return val; }

        public static SpellChargeType getByVal(int val) {
            return Arrays.stream(values()).filter(sct -> sct.getVal() == val).findAny().orElse(null);
        }
    }
    private int[] buffs = new int[]{
            // Basic Charge Drive
            BASIC_CHARGE_DRIVE_ATTACK,
            BASIC_CHARGE_DRIVE_ATOM,
            BASIC_CHARGE_DRIVE_BUFF,

            // Scarlet Charge Drive
            SCARLET_CHARGE_DRIVE_ATTACK_1,
            SCARLET_CHARGE_DRIVE_ATTACK_2,
            SCARLET_CHARGE_DRIVE_ATTACK_COMBO_1,
            SCARLET_CHARGE_DRIVE_ATTACK_COMBO_2,
            SCARLET_CHARGE_DRIVE_ATOM,
            SCARLET_CHARGE_DRIVE_BUFF,

            GUST_CHARGE_DRIVE_ATTACK,
            GUST_CHARGE_DRIVE_ATTACK_COMBO_1,
            GUST_CHARGE_DRIVE_ATOM_1,
            GUST_CHARGE_DRIVE_BUFF,

            ABYSSAL_CHARGE_DRIVE_ATTACK_1,
            ABYSSAL_CHARGE_DRIVE_ATOM_1,
            ABYSSAL_CHARGE_DRIVE_ATOM_2,
            ABYSSAL_CHARGE_DRIVE_BUFF,

            CREEPING_TERROR,
            CREEPING_TERROR_HELD_DOWN,
            ENDLESS_AGONY,
            ABYSSAL_RECALL,

            MASTER_CORRUPTION,
            KNUCKLE_BOOSTER,
            IMPENDING_DEATH,

            HERO_OF_THE_FLORA,

            DIVINE_WRATH,
            CHARGE_SPELL_AMPLIFIER,
            INFINITY_SPELL,
    };

    public Ark(Char chr) {
        super(chr);
        if (chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(1);
                    chr.addSkill(skill);
                }
            }
        }
    }

    public void handleBuff(Client c, InPacket inPacket, int skillID, byte slv) {
        Char chr = c.getChr();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        TemporaryStatManager tsm = c.getChr().getTemporaryStatManager();
        Option o1 = new Option();
        int curTime = (int) System.currentTimeMillis();
        switch (skillID) {
            case MASTER_CORRUPTION:
                if (chr.hasSkill(MASTER_CORRUPTION) && tsm.hasStat(SpecterEnergy) && tsm.getOption(SpecterEnergy).xOption > 0) {
                    changeSpecterState();
                } else {
                    chr.chatMessage("Cannot enter Specter state because of Spectra Fatigue");
                }
                break;
            case KNUCKLE_BOOSTER:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tStart = curTime;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case IMPENDING_DEATH:
                if (tsm.hasStat(ImpendingDeath)) {
                    tsm.removeStatsBySkill(IMPENDING_DEATH);
                } else {
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    o1.tOption = si.getValue(time, slv);
                    tsm.putCharacterStatValue(CharacterTemporaryStat.ImpendingDeath, o1);
                }
                break;
            case HERO_OF_THE_FLORA:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tStart = curTime;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case DIVINE_WRATH:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = curTime;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case CHARGE_SPELL_AMPLIFIER:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ChargeSpellAmplifier, o1);
                break;
            case INFINITY_SPELL:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(InfinitySpell, o1);
                break;
            case ABYSSAL_RECALL:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AbyssalRecall, o1);
                break;
        }
        tsm.sendSetStatPacket();
    }

    @Override
    public boolean isBuff(int skillID) {
        return super.isBuff(skillID) || Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    private void resetCharges() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = 0;
        o.xOption = 0;
        o.cOption = 0;
        tsm.putCharacterStatValue(BasicCast, o);
        tsm.putCharacterStatValue(ScarletCast, o);
        tsm.putCharacterStatValue(GustCast, o);
        tsm.putCharacterStatValue(AbyssalCast, o);
        tsm.sendSetStatPacket();
        spellCharges.clear();
    }

    public void modifySpectraEnergy(int clientCurrentEnergyPoints) {
        if (chr == null) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(SPECTER_STATE);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();

        int currentEnergy = tsm.getOption(SpecterEnergy).xOption;
        if (currentEnergy != clientCurrentEnergyPoints || System.currentTimeMillis() - lastEnergyUpdate < 1000) {
            updateSpectraEnergy(currentEnergy);
            return;
        }

        int incEnergy = si.getValue(y, slv);
        if (chr.hasSkill(ENHANCED_SPECTRA) && chr.getSkillLevel(ENHANCED_SPECTRA) > 0) {
            incEnergy += 1;
        }
        if (tsm.hasStat(SpecterState) ) {
            incEnergy = si.getValue(x, slv);
            if (Util.succeedProp(50)) {
                incEnergy *= 2;
            }
            incEnergy *= -1;
        }
        //if (chr.hasSkillOnCooldown(MASTER_CORRUPTION) || tsm.hasStat(AbyssalRecall)) {
        //    return;
        //}
        updateSpectraEnergy(currentEnergy + incEnergy);
        lastEnergyUpdate = System.currentTimeMillis();
    }

    private void updateSpectraEnergy(int spectraEnergy) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = chr.getJob();
        o.xOption = (spectraEnergy > 1000 ? 1000 : (spectraEnergy < 0 ? 0 : spectraEnergy));
        tsm.putCharacterStatValue(SpecterEnergy, o);
        if (o.xOption <= 0) { // Spectra Fatigue
            tsm.removeStat(SpecterState, false);
            if (chr.hasSkill(MASTER_CORRUPTION)) {
                SkillInfo si = SkillData.getSkillInfoById(MASTER_CORRUPTION);
                chr.addSkillCoolTime(MASTER_CORRUPTION, si.getValue(cooltime, 1) * 1000);
            }
        } else if (o.xOption >= 1000 && !chr.hasSkill(MASTER_CORRUPTION)){
            changeSpecterState();
        }
        tsm.sendSetStatPacket();
    }

    private void changeSpecterState() {
        Skill skill = chr.getSkill(SPECTER_STATE);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        if (!tsm.hasStat(SpecterState)) {
            o1.nReason = SPECTER_STATE;
            o1.nKey = 50712000;
            o1.nValue = si.getValue(indiePad, slv);
            tsm.putCharacterStatValue(IndiePAD, o1);
            o2.nReason = SPECTER_STATE;
            o1.nKey = 50712000;
            o2.nValue = si.getValue(indieStance, slv);
            tsm.putCharacterStatValue(IndieStance, o2);
            o3.nOption = 1;
            o3.rOption = chr.getJob();
            tsm.putCharacterStatValue(SpecterState, o3);
        } else {
            tsm.removeStat(SpecterState, false);
        }
    }

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {
        Char chr = c.getChr();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(attackInfo.skillId);
        int skillID;
        SkillInfo si = null;
        boolean hasHitMobs = attackInfo.mobAttackInfo.size() > 0;
        int slv = 0;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skill.getSkillId());
            slv = skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }
        //if (!SkillConstants.isArkForceAtomAttack(attackInfo.skillId)) {
        //    createImpendingDeathForceAtom();
        //}
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Field field = chr.getField();
        int curTime = (int) System.currentTimeMillis();
        switch (attackInfo.skillId) {
            case BASIC_CHARGE_DRIVE_ATOM:
                if (hasHitMobs) {
                    Skill skill2 = chr.getSkill(BASIC_CHARGE_DRIVE_ATTACK);
                    SkillInfo si2 = SkillData.getSkillInfoById(BASIC_CHARGE_DRIVE_BUFF);
                    byte slv2 = (byte) skill2.getCurrentLevel();

                    o1.nReason = BASIC_CHARGE_DRIVE_BUFF;
                    o1.nValue = si2.getValue(speed, slv2);
                    o1.tStart = curTime;
                    o1.tTerm = si2.getValue(time, slv2);
                    tsm.putCharacterStatValue(IndieSpeed, o1);
                    o2.nReason = BASIC_CHARGE_DRIVE_BUFF;
                    o2.nValue = si2.getValue(indieStance, slv2);
                    o1.tStart = curTime;
                    o2.tTerm = si2.getValue(time, slv2);
                    tsm.putCharacterStatValue(IndieStance, o2);
                }
                break;
            case SCARLET_CHARGE_DRIVE_ATOM:
                if (hasHitMobs) {
                    Skill skill2 = chr.getSkill(SCARLET_CHARGE_DRIVE_ATTACK_1);
                    SkillInfo si2 = SkillData.getSkillInfoById(SCARLET_CHARGE_DRIVE_BUFF);
                    byte slv2 = (byte) skill2.getCurrentLevel();
                    o1.nReason = SCARLET_CHARGE_DRIVE_BUFF;
                    o1.nValue = si2.getValue(indiePad, slv2);
                    o1.tStart = curTime;
                    o1.tTerm = si2.getValue(time, slv2);
                    tsm.putCharacterStatValue(IndiePAD, o1);
                    o2.nReason = SCARLET_CHARGE_DRIVE_BUFF;
                    o2.nValue = si2.getValue(indieCr, slv2);
                    o1.tStart = curTime;
                    o2.tTerm = si2.getValue(time, slv2);
                    tsm.putCharacterStatValue(IndieCr, o2);
                }
                break;
            case GUST_CHARGE_DRIVE_ATOM_1:
                if (hasHitMobs) {
                    Skill skill2 = chr.getSkill(GUST_CHARGE_DRIVE_ATTACK);
                    SkillInfo si2 = SkillData.getSkillInfoById(GUST_CHARGE_DRIVE_BUFF);
                    byte slv2 = (byte) skill2.getCurrentLevel();
                    o1.nReason = GUST_CHARGE_DRIVE_BUFF;
                    o1.nValue = -1;
                    o1.tStart = curTime;
                    o1.tTerm = si2.getValue(time, slv2);
                    tsm.putCharacterStatValue(IndieBooster, o1);
                    o2.nReason = GUST_CHARGE_DRIVE_BUFF;
                    o2.nValue = 5 + slv2;// indieEvaR
                    o1.tStart = curTime;
                    o2.tTerm = si2.getValue(time, slv2);
                    tsm.putCharacterStatValue(IndieCr, o2);
                }
                break;
            case ABYSSAL_CHARGE_DRIVE_ATOM_1:
                if (hasHitMobs) {
                    Skill skill2 = chr.getSkill(ABYSSAL_CHARGE_DRIVE_ATTACK_1);
                    SkillInfo si2 = SkillData.getSkillInfoById(ABYSSAL_CHARGE_DRIVE_BUFF);
                    byte slv2 = (byte) skill2.getCurrentLevel();

                    o1.nReason = ABYSSAL_CHARGE_DRIVE_BUFF;
                    o1.nValue = si2.getValue(indieDamR, slv2);
                    o1.tStart = curTime;
                    o1.tTerm = si2.getValue(time, slv2);
                    tsm.putCharacterStatValue(IndieDamR, o1);

                    o2.nReason = ABYSSAL_CHARGE_DRIVE_BUFF;
                    o2.nValue = si2.getValue(indieIgnoreMobpdpR, slv2);
                    o1.tStart = curTime;
                    o2.tTerm = si2.getValue(time, slv2);
                    tsm.putCharacterStatValue(IndieIgnoreMobpdpR, o2);

                    o3.nReason = ABYSSAL_CHARGE_DRIVE_BUFF;
                    o3.nValue = si2.getValue(indieBDR, slv2);
                    o1.tStart = curTime;
                    o3.tTerm = si2.getValue(time, slv2);
                    tsm.putCharacterStatValue(IndieBDR, o3);
                }
                break;
            // Gain Energy Charge on Attack
            case BASIC_CHARGE_DRIVE_ATTACK:
            case SCARLET_CHARGE_DRIVE_ATTACK_1:
            case SCARLET_CHARGE_DRIVE_ATTACK_2:
            case SCARLET_CHARGE_DRIVE_ATTACK_COMBO_1:
            case SCARLET_CHARGE_DRIVE_ATTACK_COMBO_2:
            case GUST_CHARGE_DRIVE_ATTACK:
            case GUST_CHARGE_DRIVE_ATTACK_COMBO_1:
            case ABYSSAL_CHARGE_DRIVE_ATTACK_1:
                if (hasHitMobs && !tsm.hasStat(SpecterState)) {
                    SpellChargeType spellChargeType = SpellChargeType.getByVal(attackInfo.skillId / 10000);
                    if (spellChargeType == null) {
                        return;
                    }
                    o1.nOption = 1;
                    o1.rOption = chr.getJob();
                    switch (spellChargeType) {
                        case Basic:
                            o1.xOption = tsm.hasStat(BasicCast) ? tsm.getOption(BasicCast).xOption + 2 : 2;
                            tsm.putCharacterStatValue(BasicCast, o1);
                            break;
                        case Scarlet:
                            if (spellCharges.contains(spellChargeType)) {
                                return;
                            }
                            o1.xOption = tsm.hasStat(ScarletCast) ? tsm.getOption(ScarletCast).xOption + 1 : 1;
                            tsm.putCharacterStatValue(ScarletCast, o1);
                            break;
                        case Gust:
                            if (spellCharges.contains(spellChargeType)) {
                                return;
                            }
                            o1.xOption = tsm.hasStat(GustCast) ? tsm.getOption(GustCast).xOption + 1 : 1;
                            tsm.putCharacterStatValue(GustCast, o1);
                            break;
                        case Abyssal:
                            if (spellCharges.contains(spellChargeType)) {
                                return;
                            }
                            o1.xOption = tsm.hasStat(AbyssalCast) ? tsm.getOption(AbyssalCast).xOption + 1 : 1;
                            tsm.putCharacterStatValue(AbyssalCast, o1);
                            break;
                    }
                    if (spellCharges.size() < 5) {
                        spellCharges.add(spellChargeType);
                    }
                    if (tsm.hasStat(InfinitySpell)) {
                        for (int i = spellCharges.size(); i < 5; i++) { // spell charge max size
                            o1.xOption = tsm.hasStat(BasicCast) ? tsm.getOption(BasicCast).xOption + 2: 2;
                            tsm.putCharacterStatValue(BasicCast, o1);
                            spellCharges.add(SpellChargeType.Basic);
                        }
                    }
                }
                break;
            case OMINOUS_NIGHTMARE:
            case VIVID_NIGHTMARE:
            case ENDLESS_NIGHTMARE:
                chr.addSkillCoolTime(attackInfo.skillId, 2000);// TODO
            case ENDLESS_DREAM:
                if (chr.hasSkill(NIGHTMARE_ESCAPE)) {
                    int vSkill = NIGHTMARE_ESCAPE;
                    if (tsm.hasStat(SpecterState)) {
                        vSkill = DREAM_ESCAPE;
                    }
                    if (chr.hasSkillOnCooldown(vSkill)) {
                        return;
                    }
                    //chr.write(UserLocal.userBonusAttackRequest(vSkill, chr.getPosition()));// TODO
                }
                break;
            case NIGHTMARE_ESCAPE:
            case DREAM_ESCAPE:
                si = SkillData.getSkillInfoById(NIGHTMARE_ESCAPE);
                slv = (byte) chr.getSkillLevel(NIGHTMARE_ESCAPE);
                chr.addSkillCoolTime(attackInfo.skillId, si.getValue(cooltime, slv) * 1000);
                break;
            case CREEPING_TERROR_HELD_DOWN:
                o1.nOption = 1;
                o1.rOption = attackInfo.skillId;
                o1.tOption = 2;
                tsm.putCharacterStatValue(NotDamaged, o1);
                break;
            case CREEPING_TERROR:
                tsm.removeStatsBySkill(CREEPING_TERROR_HELD_DOWN);
                if (!tsm.hasStat(SpecterState)) {
                    changeSpecterState();
                }
                break;
            case ABYSSAL_RECALL:
                o1.nOption = 1;
                o1.rOption = attackInfo.skillId;
                o1.tOption = 30;
                tsm.putCharacterStatValue(AbyssalRecall, o1);
                break;
            case ENDLESS_AGONY:
                if (!tsm.hasStat(SpecterState)) {
                    changeSpecterState();
                }
                break;
            case BLISSFUL_RESTRAINT_ATTACK:
                if (!tsm.hasStat(SpecterState)) {
                    changeSpecterState();
                }
                SkillInfo rca = SkillData.getSkillInfoById(BLISSFUL_RESTRAINT_TILE);
                AffectedArea aa = AffectedArea.getAffectedArea(chr, attackInfo);
                aa.setSkillID(BLISSFUL_RESTRAINT_TILE);
                aa.setPosition(chr.getPosition());
                Rect rect = aa.getPosition().getRectAround(rca.getRects().get(0));
                aa.setRect(rect);
                field.spawnAffectedArea(aa);
                break;
            case IMPENDING_DEATH_ATOM:
                spawnWreckage(attackInfo);
                break;
        }
        if (isBuff(attackInfo.skillId)) {
            tsm.sendSetStatPacket();
        }
        super.handleAttack(c, attackInfo);
    }

    private void spawnWreckage(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();

        if (!chr.hasSkill(VENGEFUL_HATE) || !tsm.hasStat(SpecterState) || !tsm.hasStat(ImpendingDeath)) {
            return;
        }
        Field field = chr.getField();
        Skill skill = chr.getSkill(VENGEFUL_HATE);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            if (Util.succeedProp(si.getValue(s, slv))/* && field.getWreckageByChrId(chr.getId()).size() < si.getValue(z, slv)*/) {
                Mob mob = (Mob) field.getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                //Wreckage wreckage = Wreckage.getWreckageBy(chr, skill.getSkillId(), mob.getPosition(), si.getValue(q, slv)*1000, 0);
                //field.spawnWreckage(chr, wreckage);
            }
        }
    }

    @Override
    public int getFinalAttackSkill() { return 0; }

    @Override
    public void handleSkill(Client c, int skillID, byte slv, InPacket inPacket) {
        super.handleSkill(c, skillID, slv, inPacket);
        SkillInfo si = null;
        if(chr.getSkill(skillID) != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (isBuff(skillID)) {
            handleBuff(c, inPacket, skillID, slv);
        } else {
            switch (skillID) {
                case SPELL_BULLETS:
                    if (!chr.hasSkill(SPELL_BULLETS) || tsm.hasStat(SpecterState)) {
                        return;
                    }
                    createSpellBulletForceAtom();
                    resetCharges();
                    break;
                case FLORAN_HERO_WILL:
                    tsm.removeAllDebuffs();
                    break;
                case VENGEFUL_HATE:
                    //List<Wreckage> wreckageList = chr.getField().getWreckageByCharId(chr.getId());
                    //createVengefulHateForceAtom(wreckageList);
                    break;
            }
        }
    }

    private void createSpellBulletForceAtom() {
        if (!chr.hasSkill(SPELL_BULLETS)) {
            return;
        }
        Skill skill = chr.getSkill(SPELL_BULLETS);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        Field field = chr.getField();
        Rect rect = chr.getPosition().getRectAround(si.getFirstRect());
        if (!chr.isLeft()) {
            rect = rect.horizontalFlipAround(chr.getPosition().getX());
        }
        int i = new Random().nextBoolean() ? 900 : 700;
        ForceAtom abyssalFA = new ForceAtom(true, 0, chr.getId(), ForceAtomEnum.ABYSSAL_CHARGE,
                true, new ArrayList<>(), ABYSSAL_CHARGE_DRIVE_ATOM_1, new ArrayList<>(), new Rect(), 0, 0,
                new Position(), ABYSSAL_CHARGE_DRIVE_ATOM_1, new Position());
        ForceAtom gustFA = new ForceAtom(true, 0, chr.getId(), ForceAtomEnum.GUST_CHARGE,
                true, new ArrayList<>(), GUST_CHARGE_DRIVE_ATOM_1, new ArrayList<>(), new Rect(), 0, 0,
                new Position(), GUST_CHARGE_DRIVE_ATOM_1, new Position());
        ForceAtom scarletFA = new ForceAtom(true, 0, chr.getId(), ForceAtomEnum.SCARLET_CHARGE,
                true, new ArrayList<>(), SCARLET_CHARGE_DRIVE_ATOM, new ArrayList<>(), new Rect(), 0, 0,
                new Position(), SCARLET_CHARGE_DRIVE_ATOM, new Position());
        ForceAtom basicFA = new ForceAtom(true, 0, chr.getId(), ForceAtomEnum.BASIC_CHARGE,
                true, new ArrayList<>(), BASIC_CHARGE_DRIVE_ATOM, new ArrayList<>(), new Rect(), 0, 0,
                new Position(), BASIC_CHARGE_DRIVE_ATOM, new Position());
        for (SpellChargeType spt : spellCharges) {
            if (spt == null) {
                continue;
            }
            int firstImpact = new Random().nextInt(15) + 35;
            int secondImpact = new Random().nextInt(2) + 5;
            int delay = new Random().nextInt(400) + 500;
            int angle = new Random().nextInt(20) + 50;

            Mob mob = Util.getRandomFromCollection(field.getMobsInRect(rect));
            ForceAtomInfo fai = new ForceAtomInfo(i, 1, firstImpact, secondImpact,
                    angle, delay, Util.getCurrentTime(), 0, 0, new Position());
            switch (spt) {
                case Scarlet:
                    scarletFA.addTarget(mob != null ? mob.getObjectId() : 0);
                    scarletFA.addFaiList(fai);
                    break;
                case Gust:
                    gustFA.addTarget(mob != null ? mob.getObjectId() : 0);
                    gustFA.addFaiList(fai);
                    break;
                case Abyssal:
                    abyssalFA.addTarget(mob != null ? mob.getObjectId() : 0);
                    abyssalFA.addFaiList(fai);
                    break;
                default:
                    basicFA.addTarget(mob != null ? mob.getObjectId() : 0);
                    basicFA.addFaiList(fai);
                    i++;

                    firstImpact = new Random().nextInt(15) + 35;
                    secondImpact = new Random().nextInt(2) + 5;
                    delay = new Random().nextInt(400) + 600;
                    angle = new Random().nextInt(20) + 50;

                    mob = Util.getRandomFromCollection(field.getMobsInRect(rect));
                    fai = new ForceAtomInfo(i, 1, firstImpact, secondImpact,
                            angle, delay, Util.getCurrentTime(), 0, 0, new Position());
                    basicFA.addTarget(mob != null ? mob.getObjectId() : 0);
                    basicFA.addFaiList(fai);
                    break;
            }
            List<ForceAtom> forceAtoms = new ArrayList<>();
            forceAtoms.add(abyssalFA);
            forceAtoms.add(gustFA);
            forceAtoms.add(scarletFA);
            forceAtoms.add(basicFA);
            i++;
            field.broadcastPacket(CField.createArkForceAtom(chr.getId(), SPELL_BULLETS, forceAtoms));
        }
    }

    private void createImpendingDeathForceAtom() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!tsm.hasStatBySkillId(SPECTER_STATE) || !chr.hasSkill(IMPENDING_DEATH) || !tsm.hasStat(ImpendingDeath)) {
            return;
        }
        Field field = chr.getField();
        Skill skill = chr.getSkill(IMPENDING_DEATH);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getSkillId();

        Rect rect = chr.getPosition().getRectAround(SkillData.getSkillInfoById(IMPENDING_DEATH_ATOM).getFirstRect());
        if (!chr.isLeft()) {
            rect = rect.horizontalFlipAround(chr.getPosition().getX());
            List<ForceAtomInfo> faiList = new ArrayList<>();
            List<Integer> targets = new ArrayList<>();
            int firstImpact = new Random().nextInt(15) + 35;
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isArk(id);
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        chr.getAvatarData().getAvatarLook().setDrawElfEar(true);
        chr.getAvatarData().getCharacterStat().setPosMap(402090000);
    }
}
