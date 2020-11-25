package net.swordie.ms.client.jobs.resistance;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.client.character.info.HitInfo;
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
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledFuture;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Xenon extends Job {
    public static final int SUPPLY_SURPLUS = 30020232;
    public static final int MULTILATERAL_I = 30020234;
    public static final int MODAL_SHIFT = 30021236;
    public static final int LIBERTY_BOOSTERS = 30021236;
    public static final int MIMIC_PROTOCOL = 30020240;
    public static final int PROMESSA_ESCAPE = 30021235;

    public static final int CIRCUIT_SURGE = 36001002; //Buff
    public static final int PINPOINT_SALVO = 36001005; //Special Attack

    public static final int XENON_BOOSTER = 36101004; //Buff
    public static final int EFFICIENCY_STREAMLINE = 36101003; //Buff
    public static final int ION_THRUST = 36101001; //Special Attack
    public static final int PINPOINT_SALVO_REDESIGN_A = 36100010; //Special Attack Upgrade  (Passive Upgrade)

    public static final int HYBRID_DEFENSES = 36111003; //Buff
    public static final int AEGIS_SYSTEM = 36111004; //Special Buff (ON/OFF)
    public static final int AEGIS_SYSTEM_ATOM = 36110004; //Special Buff (ON/OFF)
    public static final int MANIFEST_PROJECTOR = 36111006; //Special Buff (Special Duration)
    public static final int EMERGENCY_RESUPPLY = 36111008; //Special Skill
    public static final int PINPOINT_SALVO_REDESIGN_B = 36110012; //Special Attack Upgrade  (Passive Upgrade)
    public static final int TRIANGULATION = 36110005;

    public static final int HYPOGRAM_FIELD_FORCE_FIELD = 36121002;                  //TODO Summon
    public static final int HYPOGRAM_FIELD_PENETRATE = 36121013;
    public static final int HYPOGRAM_FIELD_SUPPORT = 36121014;                      //TODO Summon
    public static final int TEMPORAL_POD = 36121007;
    public static final int OOPARTS_CODE = 36121003; //Buff
    public static final int MAPLE_WARRIOR_XENON = 36121008; //Buff
    public static final int PINPOINT_SALVO_PERFECT_DESIGN = 36120015; //Sp. Attack Upgrade  (Passive Upgrade)
    public static final int HEROS_WILL_XENON = 36121009;

    public static final int ORBITAL_CATACLYSM = 36121052;
    public static final int AMARANTH_GENERATOR = 36121054;
    public static final int ENTANGLISH_LASH = 36121053;


    private final int MAX_SUPPLY = 20;
    private int supply;
    private int supplyProp;
    private int hybridDefenseCount;
    private ScheduledFuture supplyTimer;
    private static ScheduledFuture temporalPodTimer;

    private int[] addedSkills = new int[]{
            SUPPLY_SURPLUS,
            MULTILATERAL_I,
            MODAL_SHIFT,
            LIBERTY_BOOSTERS,
            MIMIC_PROTOCOL,
            PROMESSA_ESCAPE,
    };

    private int[] buffs = new int[]{
            CIRCUIT_SURGE,
            XENON_BOOSTER,
            EFFICIENCY_STREAMLINE,
            HYBRID_DEFENSES,
            AEGIS_SYSTEM,
            MANIFEST_PROJECTOR,
            HYPOGRAM_FIELD_FORCE_FIELD,
            HYPOGRAM_FIELD_PENETRATE,
            HYPOGRAM_FIELD_SUPPORT,
            TEMPORAL_POD,
            OOPARTS_CODE,
            MAPLE_WARRIOR_XENON,
            AMARANTH_GENERATOR,
    };

    public Xenon(Char chr) {
        super(chr);
        if(chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
            supplyProp = SkillData.getSkillInfoById(SUPPLY_SURPLUS).getValue(prop, 1);

            if(supplyTimer != null && !supplyTimer.isDone()) {
                supplyTimer.cancel(true);
            }
            supplyTimer = EventManager.addFixedRateEvent(this::incrementSupply, 2000, 4000);
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isXenon(id);
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
            case CIRCUIT_SURGE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indiePad, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1);
                break;
            case XENON_BOOSTER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case EFFICIENCY_STREAMLINE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieMhpR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMHPR, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieMmpR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMMPR, o2);
                break;
            case HYBRID_DEFENSES:
                o1.nOption = si.getValue(prop, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EVAR, o1);
                hybridDefenseCount = si.getValue(x, slv);
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.mOption = hybridDefenseCount;
                tsm.putCharacterStatValue(StackBuff, o1);
                break;
            case AEGIS_SYSTEM:
                if (tsm.hasStat(XenonAegisSystem)) {
                    tsm.removeStatsBySkill(skillID);
                    tsm.sendResetStatPacket();
                } else {
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    tsm.putCharacterStatValue(XenonAegisSystem, o1);
                }
                break;
            case MANIFEST_PROJECTOR:
                o1.nOption = si.getValue(y, slv);
                o1.rOption = skillID;
                o1.tOption = 0;
                tsm.putCharacterStatValue(ShadowPartner, o1);
                break;
            case OOPARTS_CODE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(BdR, o2);
                break;
            case MAPLE_WARRIOR_XENON:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case AMARANTH_GENERATOR:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AmaranthGenerator, o1);
                incrementSupply(20);
                break;

            case HYPOGRAM_FIELD_FORCE_FIELD:
            case HYPOGRAM_FIELD_PENETRATE:
            case HYPOGRAM_FIELD_SUPPORT:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(false);
                summon.setMoveAction((byte) 0);
                summon.setMoveAbility(MoveAbility.Stop);
                field.spawnSummon(summon);
                break;
            case TEMPORAL_POD:
                field = chr.getField();
                AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                aa.setMobOrigin((byte) 0);
                aa.setDelay((short) 0);
                aa.setElemAttr(0);
                aa.setPosition(chr.getPosition());
                aa.setForce(0);
                aa.setOption(0);
                aa.setIdk(0);
                aa.setField(chr.getField());
                aa.setRect(chr.getPosition().getRectAround(si.getFirstRect()));
                field.spawnAffectedArea(aa);
                temporalPodTimer(chr);
                break;
        }
        tsm.sendSetStatPacket();
    }

    public boolean isBuff(int skillID) {
        return super.isBuff(skillID) || Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    private void applySupplyCost(int skillID, byte slv, SkillInfo si) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(skillID == PINPOINT_SALVO || skillID == PINPOINT_SALVO_REDESIGN_A || skillID == PINPOINT_SALVO_REDESIGN_B || skillID == PINPOINT_SALVO_PERFECT_DESIGN) {
            return;
        }
        if(tsm.hasStat(AmaranthGenerator)) {
            return;
        } else {
            if (si == null) {
                return;
            }
            if (si.getValue(powerCon, slv) > 0) {
                supply -= si.getValue(powerCon, slv);
                supply = Math.max(0, supply);
            }
            updateSupply();
        }
    }

    private void incrementSupply() {
        incrementSupply(1);
    }

    private void incrementSupply(int amount) {
        if (supply < 20) {
            supply = chr.getTemporaryStatManager().getOption(SurplusSupply).nOption;
            supply += amount;
            supply = Math.min(MAX_SUPPLY, supply);
            updateSupply();
        }
    }

    private void updateSupply() {
        Option o = new Option();
        o.nOption = supply;
        chr.getTemporaryStatManager().putCharacterStatValue(SurplusSupply, o);
        chr.getTemporaryStatManager().sendSetStatPacket();
    }

    @Override
    public void handleCancelTimer(Char chr) {
        if(supplyTimer != null) {
            supplyTimer.cancel(true);
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
        int slv = 0;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skill.getSkillId());
            slv = skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }
        if (hasHitMobs) {
            // Increment Supply on attack
            if (Util.succeedProp(supplyProp) &&
                    attackInfo.skillId != 0 &&
                    attackInfo.skillId != PINPOINT_SALVO &&
                    attackInfo.skillId != PINPOINT_SALVO_REDESIGN_A &&
                    attackInfo.skillId != PINPOINT_SALVO_REDESIGN_B &&
                    attackInfo.skillId != PINPOINT_SALVO_PERFECT_DESIGN &&
                    attackInfo.skillId != TRIANGULATION) {
                incrementSupply();
            }

            // Triangulation
            applyTriangulationOnMob(attackInfo);
        }
        applySupplyCost(skillID, (byte) slv, si);
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case ENTANGLISH_LASH:
                for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 1;
                    o1.rOption = skill.getSkillId();
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    mts.addStatOptionsAndBroadcast(MobStat.MagicCrash, o1);
                }
                break;
            case ORBITAL_CATACLYSM:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        o1.nOption = - si.getValue(x, slv);
                        o1.rOption = skillID;
                        o1.tOption = si.getValue(time, slv);
                        mts.addStatOptionsAndBroadcast(MobStat.PDR, o1);
                    }
                }
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(y, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieMaxDamageOverR, slv);
                o2.tStart = (int) System.currentTimeMillis();
                o2.tTerm = si.getValue(y, slv);
                tsm.putCharacterStatValue(IndieMaxDamageOverR, o2);
                tsm.sendSetStatPacket();
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    public void applyTriangulationOnMob(AttackInfo attackInfo) {
        if(!chr.hasSkill(TRIANGULATION)) {
            return;
        }
        Skill skill = chr.getSkill(TRIANGULATION);
        int slv = skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(TRIANGULATION);
        int proc = si.getValue(prop, slv);
        Option o1 = new Option();
        Option o = new Option();
        int amount = 1;
        for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
            if(mob == null) {
                continue;
            }
            MobTemporaryStat mts = mob.getTemporaryStat();

            if(Util.succeedProp(proc)) {
                if (mts.hasCurrentMobStat(MobStat.Explosion)) {
                    amount = mts.getCurrentOptionsByMobStat(MobStat.Explosion).nOption;
                    if (amount <= 3) {
                        amount++;
                    }
                }

                mts.removeMobStat(MobStat.Explosion, false);

                o1.nOption = amount;
                o1.rOption = TRIANGULATION;
                o1.tOption = 0;
                o1.wOption = chr.getId();
                mts.addStatOptionsAndBroadcast(MobStat.Explosion, o1);
            }
        }
    }

    private void createPinPointSalvoForceAtom() {
        Field field = chr.getField();

        SkillInfo si = SkillData.getSkillInfoById(PINPOINT_SALVO);
        Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
        if(!chr.isLeft()) {
            rect = rect.moveRight();
        }
        List<Mob> mobs = field.getMobsInRect(rect);
        if(mobs.size() <= 0) {
            return;
        }
        Mob mob = Util.getRandomFromCollection(mobs);
        for(int i = 0; i<4; i++) {
            int anglenum = new Random().nextInt(160) + 20;
            int mobID = mob.getObjectId();
            int inc = ForceAtomEnum.XENON_ROCKET_3.getInc();
            int type = ForceAtomEnum.XENON_ROCKET_3.getForceAtomType();
            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, inc, 20, 40,
                    anglenum, 0, (int) System.currentTimeMillis(), 1, 0,
                    new Position());
            chr.getField().broadcastPacket(CField.createForceAtom(false, 0, chr.getId(), type,
                    true, mobID, getPinPointSkill(), forceAtomInfo, new Rect(), 0, 300,
                    mob.getPosition(), 0, mob.getPosition()));
        }
    }

    public int getPinPointSkill() {
        int skill = PINPOINT_SALVO;
        if(chr.hasSkill(PINPOINT_SALVO_REDESIGN_A)) {
            skill = PINPOINT_SALVO_REDESIGN_A;
        }
        if(chr.hasSkill(PINPOINT_SALVO_REDESIGN_B)) {
            skill = PINPOINT_SALVO_REDESIGN_B;
        }
        if(chr.hasSkill(PINPOINT_SALVO_PERFECT_DESIGN)) {
            skill = PINPOINT_SALVO_PERFECT_DESIGN;
        }
        return skill;
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
        if (skill != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        chr.chatMessage(ChatType.Mob, "SkillID: " + skillID);
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        applySupplyCost(skillID, slv, si);
        if (isBuff(skillID)) {
            handleBuff(c, inPacket, skillID, slv);
        } else {
            Option o1 = new Option();
            Option o2 = new Option();
            Option o3 = new Option();
            switch (skillID) {
                case EMERGENCY_RESUPPLY:
                    incrementSupply(si.getValue(x, slv));
                    break;
                case TEMPORAL_POD:
                    AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                    aa.setMobOrigin((byte) 0);
                    aa.setPosition(chr.getPosition());
                    aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                    aa.setDelay((short) 4);
                    chr.getField().spawnAffectedArea(aa);
                    break;
                case PROMESSA_ESCAPE:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = chr.getOrCreateFieldByCurrentInstanceType(o1.nValue);
                    chr.warp(toField);
                    break;
                case PINPOINT_SALVO:
                    o1.nOption = supply--;
                    tsm.putCharacterStatValue(SurplusSupply, o1);
                    updateSupply();
                    createPinPointSalvoForceAtom();
                    break;
                case HEROS_WILL_XENON:
                    tsm.removeAllDebuffs();
                    break;
            }
        }
    }


    public static void temporalPodTimer(Char chr) {
        if (temporalPodTimer != null && !temporalPodTimer.isDone()) {
            temporalPodTimer.cancel(true);
        }
        temporalPodTimer = EventManager.addFixedRateEvent(() -> temporalPodEffect(chr), 1000, 1000);
    }

    public static void temporalPodEffect(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStatBySkillId(TEMPORAL_POD)) {
            for(int skillId : chr.getSkillCoolTimes().keySet()) {
                chr.reduceSkillCoolTime(skillId, 1000);
            }
        } else {
            temporalPodTimer.cancel(true);
        }
    }



    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        if(chr.hasSkill(HYBRID_DEFENSES)) {
            Skill skill = chr.getSkill(HYBRID_DEFENSES);
            byte slv = (byte) skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());

            if (tsm.getOptByCTSAndSkill(StackBuff, HYBRID_DEFENSES) != null) {
                if (hitInfo.hpDamage > 0) {
                    o1.nOption = 1;
                    o1.rOption = skill.getSkillId();
                    o1.mOption = hybridDefenseCount;
                    tsm.putCharacterStatValue(StackBuff, o1);
                    o2.nOption -= si.getValue(y, slv);
                    o2.rOption = skill.getSkillId();
                    tsm.putCharacterStatValue(EVAR, o2);

                    tsm.sendSetStatPacket();
                } else {
                    hybridDefenseCount--;
                    if (hybridDefenseCount <= 0) {
                        tsm.removeStatsBySkill(HYBRID_DEFENSES);
                        tsm.sendResetStatPacket();
                        return;
                    }
                    o1.nOption = 1;
                    o1.rOption = skill.getSkillId();
                    o1.mOption = hybridDefenseCount;
                    tsm.putCharacterStatValue(StackBuff, o1);
                    o2.nOption -= 0;
                    o2.rOption = skill.getSkillId();
                    tsm.putCharacterStatValue(EVAR, o2);
                    tsm.sendSetStatPacket();
                }

            }
        }

        Skill aegis = chr.getSkill(AEGIS_SYSTEM);
        if (tsm.hasStat(XenonAegisSystem) && aegis != null) {
            SkillInfo si = SkillData.getSkillInfoById(AEGIS_SYSTEM);
            byte slv = (byte) aegis.getCurrentLevel();
            if (Util.succeedProp(si.getValue(prop, slv))) {
                int mobID = hitInfo.mobID;
                ForceAtomEnum fae = ForceAtomEnum.XENON_ROCKET_1;
                int curTime = Util.getCurrentTime();
                List<ForceAtomInfo> faiList = new ArrayList<>();
                List<Integer> mobList = new ArrayList<>();
                Random random = new Random();
                for (int i = 0; i < si.getValue(x, slv); i++) {
                    int firstImpact = 5 + random.nextInt(6);
                    int secondImpact = 5 + random.nextInt(6);
                    int angle = random.nextInt(180);
                    ForceAtomInfo fai = new ForceAtomInfo(1, fae.getInc(), firstImpact, secondImpact,
                            angle, 0, curTime, 0, AEGIS_SYSTEM_ATOM, new Position(0, 0));
                    faiList.add(fai);
                    mobList.add(mobID);
                }
                chr.getField().broadcastPacket(CField.createForceAtom(false, 0, chr.getId(), fae.getForceAtomType(), true,
                        mobList, AEGIS_SYSTEM_ATOM, faiList, null, 0, 0,
                        null, 0, null));
            }
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        CharacterStat cs = chr.getAvatarData().getCharacterStat();
        cs.setPosMap(931060089);
    }
}

