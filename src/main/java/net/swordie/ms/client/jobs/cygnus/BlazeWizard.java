package net.swordie.ms.client.jobs.cygnus;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.AssistType;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.enums.LeaveType;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class BlazeWizard extends Noblesse {

    public static final int IMPERIAL_RECALL = 10001245;
    public static final int ELEMENTAL_EXPERT = 10000250;
    public static final int ELEMENTAL_SLASH = 10001244;
    public static final int NOBLE_MIND = 10000202;
    public static final int ELEMENTAL_SHIFT = 10001254;
    public static final int ELEMENTAL_SHIFT2 = 10000252;
    public static final int ELEMENTAL_HARMONY_INT = 10000248;

    public static final int ORBITAL_FLAME = 12001020;
    public static final int GREATER_ORBITAL_FLAME = 12100020;
    public static final int GRAND_ORBITAL_FLAME = 12110020;
    public static final int FINAL_ORBITAL_FLAME = 12120006;

    public static final int ORBITAL_FLAME_ATOM = 12000026;
    public static final int GREATER_ORBITAL_FLAME_ATOM = 12100028;
    public static final int GRAND_ORBITAL_FLAME_ATOM = 12110028;
    public static final int FINAL_ORBITAL_FLAME_ATOM = 12120010;



    public static final int IGNITION = 12101024; //Buff
    public static final int IGNITION_EXPLOSION = 12100029; // Explosion Attack
    public static final int FLASHFIRE = 12101025; //Special Skill
    public static final int WORD_OF_FIRE = 12101023; //Buff
    public static final int CONTROLLED_BURN = 12101022; //Special Skill

    public static final int CINDER_MAELSTROM = 12111022; //Special Skill //TODO
    public static final int PHOENIX_RUN = 12111023; //Special Buff
    public static final int PHOENIX_RUN_EFFECTS = 12111029;

    public static final int BURNING_CONDUIT = 12121005;
    public static final int FIRES_OF_CREATION = 12121004; //only used for visual cooldown
    public static final int FIRES_OF_CREATION_FOX = 12120014; //Buff
    public static final int FIRES_OF_CREATION_LION = 12120013; //Buff
    public static final int FLAME_BARRIER = 12121003; //Buff
    public static final int CALL_OF_CYGNUS_BW = 12121000; //Buff
    public static final int ORBITAL_FLAME_RANGE = 12121043; // Buff - toggle

    public static final int GLORY_OF_THE_GUARDIANS_BW = 12121053;

    //Flame Elements
    public static final int FLAME_ELEMENT = 12000022;
    public static final int GREATER_FLAME_ELEMENT = 12100026;
    public static final int GRAND_FLAME_ELEMENT = 12110024;
    public static final int FINAL_FLAME_ELEMENT = 12120007;

    private int[] addedSkills = new int[] {
            ELEMENTAL_HARMONY_INT,
            IMPERIAL_RECALL,
            ELEMENTAL_EXPERT,
            ELEMENTAL_SLASH,
            NOBLE_MIND,
            ELEMENTAL_SHIFT,
            ELEMENTAL_SHIFT2
    };

    private int[] buffs = new int[] {
            IGNITION,
            WORD_OF_FIRE,
            PHOENIX_RUN,
            FIRES_OF_CREATION_FOX,
            FIRES_OF_CREATION_LION,
            FLAME_BARRIER,
            CALL_OF_CYGNUS_BW,
            GLORY_OF_THE_GUARDIANS_BW,
            ORBITAL_FLAME_RANGE,
    };

    boolean used;
    Position chrPos;
    int prevmap;
    private HashMap<Mob, ScheduledFuture> hashMap = new HashMap<>();
    private ScheduledFuture schFuture;
    private Summon summonFox;
    private Summon summonLion;

    public BlazeWizard(Char chr) {
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
        return JobConstants.isBlazeWizard(id);
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
        switch (skillID) {
            case WORD_OF_FIRE:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Booster, o1);
                break;
            case FLAME_BARRIER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamageReduce, o1);
                break;
            case CALL_OF_CYGNUS_BW:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;
            case IGNITION:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(WizardIgnite, o1);
                break;
            case FIRES_OF_CREATION_FOX:
            case FIRES_OF_CREATION_LION:
                if (chr.hasSkillOnCooldown(FIRES_OF_CREATION_FOX) || chr.hasSkillOnCooldown(FIRES_OF_CREATION_LION)) {
                    break;
                }

                tsm.removeStatsBySkill(skillID == FIRES_OF_CREATION_FOX ? FIRES_OF_CREATION_LION : FIRES_OF_CREATION_LION);
                Field field = c.getChr().getField();

                if (summonFox != null)
                {
                    field.broadcastPacket(Summoned.summonedRemoved(summonFox, LeaveType.ANIMATION));
                }

                if (summonLion != null)
                {
                    field.broadcastPacket(Summoned.summonedRemoved(summonLion, LeaveType.ANIMATION));
                }

                tsm.sendResetStatPacket();

                chr.setSkillCooldown(FIRES_OF_CREATION, slv); // to display cooldown in quickslot
                chr.setSkillCooldown(FIRES_OF_CREATION_FOX, slv);
                chr.setSkillCooldown(FIRES_OF_CREATION_LION, slv);

                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                summon.setFlyMob(skillID == FIRES_OF_CREATION_FOX);
                summon.setMoveAbility(MoveAbility.Walk);
                // i have to specify the summon term as the _FOX/LION skills have the time set to 0, making the summon last forever!
                summon.setSummonTerm(SkillData.getSkillInfoById(FIRES_OF_CREATION).getValue(time, slv));
                field.spawnSummon(summon);

                if (skillID == FIRES_OF_CREATION_FOX) {
                    summonFox = summon;
                } else {
                    summonLion = summon;
                }

                o1.nReason = skillID;
                o1.nValue = si.getValue(y, slv);
                o1.tStart = (int) System.currentTimeMillis();
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieIgnoreMobpdpR, o1);
                o2.nOption = si.getValue(z, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ElementalReset, o2);
                break;
            case CINDER_MAELSTROM:  //Special Summon    //TODO
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = c.getChr().getField();
                summon.setFlyMob(false);
                summon.setMoveAbility(MoveAbility.Stop);
                field.spawnSummon(summon);
                break;
            case PHOENIX_RUN:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ReviveOnce, o1);
                break;
            case GLORY_OF_THE_GUARDIANS_BW:
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

            case ORBITAL_FLAME_RANGE:
                if (tsm.hasStat(AddRangeOnOff)) {
                    tsm.removeStatsBySkill(skillID);
                    tsm.sendResetStatPacket();
                } else {
                    o1.nOption = si.getValue(range, slv);
                    o1.rOption = skillID;
                    o1.tOption = 0;
                    tsm.putCharacterStatValue(AddRangeOnOff, o1);
                }
                break;
        }
        tsm.sendSetStatPacket();
        
    }

    public boolean isBuff(int skillID) {
        return super.isBuff(skillID) || Arrays.stream(buffs).anyMatch(b -> b == skillID);
    }

    @Override
    public void handleSkillRemove(Client c, int skillID) {
        switch (skillID) {
            case FIRES_OF_CREATION_FOX:
            case FIRES_OF_CREATION_LION:
                removeFiresOfCreationSummon(c, skillID);
                break;
        }
    }

    private void removeFiresOfCreationSummon(Client c, int skillID) {
        Summon summon = skillID == FIRES_OF_CREATION_FOX ? summonFox : summonLion;

        if (summon != null)
        {
            Field field = c.getChr().getField();
            field.broadcastPacket(Summoned.summonedRemoved(summon, LeaveType.ANIMATION));
        }
    }

    private void summonFlameElement() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if(tsm.getOptByCTSAndSkill(MAD, getFlameElement()) == null) {
            Option o1 = new Option();
            Option o2 = new Option();
            Skill skill = chr.getSkill(FLAME_ELEMENT);
            SkillInfo si = SkillData.getSkillInfoById(getFlameElement());
            byte slv = (byte) chr.getSkill(getFlameElement()).getCurrentLevel();
            Summon summon;
            Field field;
            field = c.getChr().getField();
            summon = Summon.getSummonBy(chr, getFlameElement(), slv);
            summon.setFlyMob(false);
            summon.setAttackActive(false);
            summon.setAssistType(AssistType.None);
            field.spawnSummon(summon);

            o2.nReason = getFlameElement();
            o2.nValue = 1;
            o2.summon = summon;
            o2.tStart = (int) System.currentTimeMillis();
            o2.tTerm = si.getValue(time, slv);
            tsm.putCharacterStatValue(IndieEmpty, o2);

            o1.nOption = si.getValue(x, slv);
            o1.rOption = getFlameElement();
            o1.tOption = si.getValue(time, slv);
            tsm.putCharacterStatValue(MAD, o1);
            tsm.sendSetStatPacket();
        }
    }

    private int getFlameElement() {
        int skill = 0;
        if(chr.hasSkill(FLAME_ELEMENT)) {
            skill = FLAME_ELEMENT;
        }
        if(chr.hasSkill(GREATER_FLAME_ELEMENT)) {
            skill = GREATER_FLAME_ELEMENT;
        }
        if(chr.hasSkill(GRAND_FLAME_ELEMENT)) {
            skill = GRAND_FLAME_ELEMENT;
        }
        if(chr.hasSkill(FINAL_FLAME_ELEMENT)) {
            skill = FINAL_FLAME_ELEMENT;
        }
        return skill;
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
            if(attackInfo.skillId != IGNITION_EXPLOSION) {
                applyIgniteOnMob(attackInfo);
            }
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case ORBITAL_FLAME_ATOM:
            case GREATER_ORBITAL_FLAME_ATOM:
            case GRAND_ORBITAL_FLAME_ATOM:
            case FINAL_ORBITAL_FLAME_ATOM:
                summonFlameElement();
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    private void applyIgniteOnMob(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        if(tsm.hasStat(WizardIgnite)) {
            Skill skill = chr.getSkill(IGNITION);
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            byte slv = (byte) skill.getCurrentLevel();
            for(MobAttackInfo mai : attackInfo.mobAttackInfo) {
                if (Util.succeedProp(si.getValue(prop, slv))) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if(mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();

                    mts.createAndAddBurnedInfo(chr, skill);

                    if(hashMap.get(mob) != null && !hashMap.get(mob).isDone()) {
                        hashMap.get(mob).cancel(true);
                        hashMap.remove(mob);
                    }

                    schFuture = EventManager.addEvent(() ->
                            explodeIgnitionOnMob(mob), si.getValue(dotTime, slv), TimeUnit.SECONDS);

                    hashMap.put(mob, schFuture);

                    o.nOption = 1;
                    o.rOption = skill.getSkillId();
                    o.tOption = 10;
                    o.wOption = 10;
                    mts.addStatOptionsAndBroadcast(MobStat.Ember, o);
                }
            }
        }
    }

    private void explodeIgnitionOnMob(Mob mob) {
        MobTemporaryStat mts = mob.getTemporaryStat();
        hashMap.remove(mob);
        Life checkMob = chr.getField().getLifeByObjectID(mob.getObjectId());
        if(checkMob != null && checkMob instanceof Mob) {
            c.write(UserLocal.explosionAttack(IGNITION_EXPLOSION, mob.getPosition(), mob.getObjectId(), 10));
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
                case CINDER_MAELSTROM:
                    //TODO
                    break;
                case FLASHFIRE:
                    Position flamepos = chr.getPosition();
                    if(used == true) {
                        if(chr.getFieldID() != prevmap) {
                            //Set Blink
                            prevmap = chr.getFieldID();
                            c.write(WvsContext.flameWizardFlareBlink(chr, flamepos, false));
                            chrPos = chr.getPosition();
                            used = true;
                        } else {
                            //Clear Blink + Teleport
                            c.write(WvsContext.flameWizardFlareBlink(chr, chrPos, true));
                            used = false;
                        }
                    } else {
                        //Set Blink
                        prevmap = chr.getFieldID();
                        c.write(WvsContext.flameWizardFlareBlink(chr, flamepos, false));
                        chrPos = chr.getPosition();
                        used = true;
                    }
                    break;
                case CONTROLLED_BURN:
                    int healmp = si.getValue(x, slv);
                    int healpercent = (chr.getMaxMP() * healmp) / 100;
                    chr.healMP(healpercent);
                    break;
                case IMPERIAL_RECALL:
                    o1.nValue = si.getValue(x, slv);
                    Field toField = chr.getOrCreateFieldByCurrentInstanceType(o1.nValue);
                    chr.warp(toField);
                    break;
                case BURNING_CONDUIT:
                    AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                    aa.setMobOrigin((byte) 0);
                    aa.setPosition(chr.getPosition());
                    aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                    aa.setDelay((short) 15);
                    chr.getField().spawnAffectedArea(aa);
                    break;
            }
        }
    }



    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

        super.handleHit(c, inPacket, hitInfo);
    }

    public static void reviveByPhoenixRun(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        Skill skill = chr.getSkill(PHOENIX_RUN);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();

        chr.heal(chr.getMaxHP() / 2); // 50%
        tsm.removeStatsBySkill(PHOENIX_RUN);
        tsm.sendResetStatPacket();
        chr.chatMessage("You have been revived by Phoenix Run.");

        Position position = chr.getPosition();
        chr.write(CField.teleport(new Position(position.getX() + (chr.isLeft() ? + 350 : - 350), position.getY()), chr));

        // Hit effect
        chr.write(User.effect(Effect.skillUse(PHOENIX_RUN_EFFECTS, slv, 0)));
        chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillUse(PHOENIX_RUN_EFFECTS, slv, 0)));

        // Backstep effect
        chr.write(User.effect(Effect.skillAffected(PHOENIX_RUN_EFFECTS, slv, 0)));
        chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillAffected(PHOENIX_RUN_EFFECTS, slv, 0)));

        o.nOption = 1;
        o.rOption = PHOENIX_RUN;
        o.tOption = si.getValue(x, slv); // duration
        tsm.putCharacterStatValue(NotDamaged, o);
        tsm.sendSetStatPacket();
    }
}
