package net.swordie.ms.client.jobs.legend;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Aran extends Job {
    public static final int COMBO_ABILITY = 21000000;
    public static final int COMBAT_STEP = 20001295;
    public static final int REGAINED_MEMORY = 20000194;
    public static final int RETURN_TO_RIEN = 20001296;

    public static final int POLEARM_BOOSTER = 21001003; //Buff
    public static final int BODY_PRESSURE = 21001008; //Buff (ON/OFF)

    public static final int SNOW_CHARGE = 21101006; //Buff
    public static final int DRAIN = 21101005; //Special Skill (HP Recovery) (ON/OFF)

    public static final int MAHA_BLESSING = 21111012; //Buff
    public static final int ADRENALINE_RUSH = 21110016; //at 1000 combo activated
    public static final int AERO_SWING = 21110026; //Passive that activates when Combo'ing in Air TODO

    public static final int MAPLE_WARRIOR_ARAN = 21121000; //Buff
    public static final int HEROS_WILL_ARAN = 21121008;

    public static final int HEROIC_MEMORIES_ARAN = 21121053;
    public static final int ADRENALINE_BURST = 21121058;
    public static final int MAHAS_DOMAIN = 21121068; //AoE Effect

    //Final Attack
    public static final int FINAL_ATTACK = 21100010;
    public static final int ADVANCED_FINAL_ATTACK = 21120012;

    //Attacking Skills:
    public static final int SMASH_WAVE = 21001009;
    public static final int SMASH_WAVE_COMBO = 21000004;

    public static final int SMASH_SWING_1 = 21001010;
    public static final int SMASH_SWING_2 = 21000006;
    public static final int SMASH_SWING_3 = 21000007;
    public static final int SMASH_SWING_2_FINAL_BLOW = 21120025;

    public static final int FINAL_CHARGE = 21101011;
    public static final int FINAL_CHARGE_COMBO = 21100002; //Special Attack (Stun Debuff) (Special Skill from Key-Command)

    public static final int FINAL_TOSS = 21100015;
    public static final int FINAL_TOSS_COMBO = 21100012;

    public static final int ROLLING_SPIN = 21101017;
    public static final int ROLLING_SPIN_COMBO = 21100013; //Special Attack (Stun Debuff) (Special Skill from Key-Command)

    public static final int GATHERING_HOOK = 21111019;
    public static final int GATHERING_HOOK_COMBO = 21110018;

    public static final int FINAL_BLOW = 21111021;
    public static final int FINAL_BLOW_COMBO = 21110020; //Special Attack (Stun Debuff) (Special Skill from Key-Command)
    public static final int FINAL_BLOW_SMASH_SWING_COMBO = 21110028; //Special Attack (Stun Debuff) (Special Skill from Key-Command)
    public static final int FINAL_BLOW_ADRENALINE_SHOCKWAVE = 21110027; //Shockwave after final blow when in Adrenaline Rush

    public static final int JUDGEMENT_DRAW = 21111017;
    public static final int JUDGEMENT_DRAW_COMBO_DOWN = 21110011; //Special Attack (Freeze Debuff) (Special Skill from Key-Command)
    public static final int JUDGEMENT_DRAW_COMBO_LEFT = 21110024; //Special Attack (Freeze Debuff) (Special Skill from Key-Command)
    public static final int JUDGEMENT_DRAW_COMBO_RIGHT = 21110025; //Special Attack (Freeze Debuff) (Special Skill from Key-Command)

    public static final int BEYOND_BLADE_1 = 21120022;
    public static final int BEYOND_BLADE_2 = 21121016;
    public static final int BEYOND_BLADE_3 = 21121017;


    //Finisher
    public static final int FINISHER_HUNTER_PREY = 21120019;


    public static int getOriginalSkillByID(int skillID) {
        switch(skillID) {
            case SMASH_WAVE_COMBO:
                return SMASH_WAVE;

            case FINAL_BLOW_COMBO:
            case FINAL_BLOW_SMASH_SWING_COMBO:
                return FINAL_BLOW;

        }
        return skillID; // no original skill linked with this one
    }


    private int[] addedSkills = new int[] {
            REGAINED_MEMORY,
            RETURN_TO_RIEN,
    };

    private final int[] buffs = new int[] {
            POLEARM_BOOSTER,
            BODY_PRESSURE,
            SNOW_CHARGE,
            DRAIN,
            MAHA_BLESSING,
            MAPLE_WARRIOR_ARAN,
            HEROIC_MEMORIES_ARAN,
    };

    private int combo;

    public Aran(Char chr) {
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
        return JobConstants.isAran(id);
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
            case POLEARM_BOOSTER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case BODY_PRESSURE:
                if(tsm.hasStatBySkillId(skillID)) {
                    tsm.removeStatsBySkill(skillID);
                } else {
                    o1.nOption = si.getValue(x, slv);
                    o1.rOption = skillID;
                    tsm.putCharacterStatValue(BodyPressure, o1);
                }
                break;
            case DRAIN:
                if(tsm.hasStatBySkillId(skillID)) {
                    tsm.removeStatsBySkill(skillID);
                } else {
                    o1.nOption = si.getValue(x, slv);
                    o1.rOption = skillID;
                    tsm.putCharacterStatValue(AranDrain, o1);
                }
                break;
            case SNOW_CHARGE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(WeaponCharge, o1);
                break;
            case MAHA_BLESSING:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieMad, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMAD, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePad, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o2);
                break;
            case MAPLE_WARRIOR_ARAN:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;

            case HEROIC_MEMORIES_ARAN:
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

    private void giveAdrenalinRushBuff(TemporaryStatManager tsm) {
        Skill skill = chr.getSkill(ADRENALINE_RUSH);
        if(skill == null) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(ADRENALINE_RUSH);
        byte slv = (byte) skill.getCurrentLevel();
        if (chr.hasSkill(ADRENALINE_RUSH)) {
            Option o = new Option();
            o.nOption = 1;
            o.rOption = ADRENALINE_RUSH;
            o.tOption = si.getValue(time, slv);
            o.cOption = 1;
            tsm.putCharacterStatValue(AdrenalinBoost, o);
            tsm.sendSetStatPacket();
            EventManager.addEvent(this::setComboCountAfterAdrenaline, si.getValue(time, slv), TimeUnit.SECONDS);
        }
    }

    private void comboAfterAdrenalin() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        tsm.getOption(ComboAbilityBuff).nOption = 500;
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
        if (hasHitMobs) {
            if(chr.hasSkill(ADRENALINE_RUSH) && tsm.getOption(ComboAbilityBuff).nOption > 999 && !tsm.hasStat(AdrenalinBoost)) {
                giveAdrenalinRushBuff(tsm);
            }
            incrementComboAbility(tsm, attackInfo);
            aranDrain();
            snowCharge(attackInfo);
        }
        doSwingStudiesAddAttack(tsm);
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case FINISHER_HUNTER_PREY:
                int t = si.getValue(subTime, slv);
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = t;
                tsm.putCharacterStatValue(AranBoostEndHunt, o1);
                tsm.sendSetStatPacket();
                break;
            case FINAL_CHARGE_COMBO: //TODO  Leaves an ice trail behind that freezes enemies
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    int hcProp = 5; //hcProp is defined yet still gives NPEs
                    int hcTime = 10; //hcTime is defined yet still gives NPEs
                    if (Util.succeedProp(hcProp)) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        if(!mob.isBoss()) {
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            o1.nOption = 1;
                            o1.rOption = getOriginalSkillByID(skillID);
                            o1.tOption = hcTime;
                            mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                        }
                    }
                }
                break;
            case ROLLING_SPIN_COMBO:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    int prop = 30; //Prop value never given, so I decided upon 30%.
                    int time = 3; //Time value never given, so I decided upon 3 seconds.
                    if (Util.succeedProp(prop)) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        if(!mob.isBoss()) {
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            o1.nOption = 1;
                            o1.rOption = getOriginalSkillByID(skillID);
                            o1.tOption = time;
                            mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                        }
                    }
                }
                break;
            case FINAL_BLOW_COMBO:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    int prop = 30; //Prop value never given, so I decided upon 30%.
                    int time = 3; //Time value never given, so I decided upon 3 seconds.
                    if (Util.succeedProp(prop)) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        if(!mob.isBoss()) {
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            o1.nOption = 1;
                            o1.rOption = getOriginalSkillByID(skillID);
                            o1.tOption = time;
                            mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                        }
                    }
                }
                break;
            case FINAL_BLOW_SMASH_SWING_COMBO:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    int prop = 30; //Prop value never given, so I decided upon 30%.
                    int time = 3; //Time value never given, so I decided upon 3 seconds.
                    if (Util.succeedProp(prop)) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if(mob == null) {
                            continue;
                        }
                        if(!mob.isBoss()) {
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            o1.nOption = 1;
                            o1.rOption = getOriginalSkillByID(skillID);
                            o1.tOption = time;
                            mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                        }
                    }
                }
                break;
            case JUDGEMENT_DRAW_COMBO_DOWN:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    int hcProp = 5; //hcProp is defined yet still gives NPEs
                    int hcTime = 2; //hcTime is defined yet still gives NPE
                    Skill judgementDrawSkill = chr.getSkill(JUDGEMENT_DRAW);
                    if(Util.succeedProp(hcProp/*si.getValue(hcProp, slv)*/)) {
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = getOriginalSkillByID(skillID);
                        o1.tOption = hcTime;    //si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Freeze, o1);
                    } else {
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.createAndAddBurnedInfo(chr, judgementDrawSkill);
                    }
                }
                break;
            case JUDGEMENT_DRAW_COMBO_LEFT:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    int hcProp = 5; //hcProp is defined yet still gives NPE
                    int hcTime = 2; //hcTime is defined yet still gives NPE
                    Skill judgementDrawSkill = chr.getSkill(JUDGEMENT_DRAW);
                    if(Util.succeedProp(hcProp/*si.getValue(hcProp, slv)*/)) {
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = getOriginalSkillByID(skillID);
                        o1.tOption = hcTime;    //si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Freeze, o1);
                    } else {
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.createAndAddBurnedInfo(chr, judgementDrawSkill);
                    }
                }
                break;
            case JUDGEMENT_DRAW_COMBO_RIGHT:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    int hcProp = 5; //hcProp is defined yet still gives NPEs
                    int hcTime = 2; //hcTime is defined yet still gives NPE
                    Skill judgementDrawSkill = chr.getSkill(JUDGEMENT_DRAW);
                    if(Util.succeedProp(hcProp/*si.getValue(hcProp, slv)*/)) {
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = 1;
                        o1.rOption = getOriginalSkillByID(skillID);
                        o1.tOption = hcTime;    //si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.Freeze, o1);
                    } else {
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.createAndAddBurnedInfo(chr, judgementDrawSkill);
                    }
                }
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    private void incrementComboAbility(TemporaryStatManager tsm, AttackInfo attackInfo) {
        Option o = new Option();
        SkillInfo comboInfo = SkillData.getSkillInfoById(COMBO_ABILITY);
        int amount = 1;
        if(!chr.hasSkill(COMBO_ABILITY)) {
            return;
        }
        if(tsm.hasStat(ComboAbilityBuff)) {
            amount = tsm.getOption(ComboAbilityBuff).nOption;
            if (amount < comboInfo.getValue(s2, chr.getSkill(COMBO_ABILITY).getCurrentLevel())) {
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    amount++;
                }
            }

        }
        o.nOption = amount;
        o.rOption = COMBO_ABILITY;
        tsm.putCharacterStatValue(ComboAbilityBuff, o);
        setCombo(amount);
    }

    private void setComboCountAfterAdrenaline() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = 500;
        o.rOption = COMBO_ABILITY;
        tsm.putCharacterStatValue(ComboAbilityBuff, o);
        setCombo(500);
    }

    private void doSwingStudiesAddAttack(TemporaryStatManager tsm) {
        Option o = new Option();
        if (chr.hasSkill(21100015)) {
            o.nOption = 1;
            o.rOption = 21100015;
            o.tOption = 5;
            tsm.putCharacterStatValue(NextAttackEnhance, o);
            tsm.sendSetStatPacket();
        }
    }

    public int getCombo() {
        return combo;
    }

    public void setCombo(int combo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        this.combo = Math.max(0, combo);
        tsm.getOption(ComboAbilityBuff).nOption = getCombo();
        c.write(WvsContext.modComboResponse(getCombo()));

    }

    public void aranDrain() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(tsm.hasStat(AranDrain)) {
            Skill skill = chr.getSkill(DRAIN);
            byte slv = (byte) skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int healrate = si.getValue(x, slv);
            chr.heal((int) (chr.getMaxHP() / ((double)100 / healrate)));
        }
    }

    public void snowCharge(AttackInfo attackInfo) {
        if(!chr.hasSkill(SNOW_CHARGE)) {
            return;
        }
        Skill skill = chr.getSkill(SNOW_CHARGE);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) chr.getSkill(skill.getSkillId()).getCurrentLevel();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        if(tsm.getOptByCTSAndSkill(WeaponCharge, SNOW_CHARGE) != null) {
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if(mob == null) {
                    continue;
                }
                MobTemporaryStat mts = mob.getTemporaryStat();
                o1.nOption = (mob.isBoss() ? -(si.getValue(q, slv) / 2) : - si.getValue(q, slv));
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(y, slv);
                o1.mOption = 1;
                mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
            }
        }
    }

    @Override
    public int getFinalAttackSkill() {
        Skill skill = getFinalAtkSkill();
        if (skill != null) {
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            byte slv = (byte) skill.getCurrentLevel();
            int proc = si.getValue(prop, slv);

            if (Util.succeedProp(proc)) {
                int fas = 0;
                if (chr.hasSkill(FINAL_ATTACK)) {
                    fas = FINAL_ATTACK;
                }
                if (chr.hasSkill(ADVANCED_FINAL_ATTACK)) {
                    fas = ADVANCED_FINAL_ATTACK;
                }
                return fas;
            }
        }
        return 0;
    }

    private Skill getFinalAtkSkill() {
        Skill skill = null;
        if(chr.hasSkill(FINAL_ATTACK)) {
            skill = chr.getSkill(FINAL_ATTACK);
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
        Char chr = c.getChr();
        Skill skill = chr.getSkill(skillID);
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
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
                case ADRENALINE_BURST:
                    tsm.getOption(ComboAbilityBuff).nOption = 1000;
                    giveAdrenalinRushBuff(tsm);
                    tsm.sendSetStatPacket();
                    break;
                case RETURN_TO_RIEN:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = chr.getOrCreateFieldByCurrentInstanceType(o1.nValue);
                    chr.warp(toField);
                    break;
                case MAHAS_DOMAIN:
                    SkillInfo mdi = SkillData.getSkillInfoById(MAHAS_DOMAIN);
                    AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                    aa.setMobOrigin((byte) 0);
                    aa.setPosition(chr.getPosition());
                    aa.setRect(aa.getPosition().getRectAround(mdi.getRects().get(0)));
                    chr.getField().spawnAffectedArea(aa);
                    break;
                case HEROS_WILL_ARAN:
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

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        CharacterStat cs = chr.getAvatarData().getCharacterStat();
        cs.setPosMap(914000000);
    }
}
