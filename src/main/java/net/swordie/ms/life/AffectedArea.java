package net.swordie.ms.life;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.skills.*;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Zero;
import net.swordie.ms.client.jobs.adventurer.Archer;
import net.swordie.ms.client.jobs.adventurer.BeastTamer;
import net.swordie.ms.client.jobs.adventurer.Magician;
import net.swordie.ms.client.jobs.adventurer.Thief;
import net.swordie.ms.client.jobs.cygnus.BlazeWizard;
import net.swordie.ms.client.jobs.legend.Aran;
import net.swordie.ms.client.jobs.legend.Shade;
import net.swordie.ms.client.jobs.resistance.Xenon;
import net.swordie.ms.client.jobs.sengoku.Kanna;
import net.swordie.ms.connection.packet.CField;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Rect;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.world.field.Field;

import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;
import static net.swordie.ms.client.character.skills.SkillStat.*;

/**
 * Created on 1/6/2018.
 */
public class AffectedArea extends Life {

    private Char owner;
    private Rect rect;
    private int skillID;
    private int force;
    private int option;
    private int elemAttr;
    private int idk;
    private int slv;
    private byte mobOrigin;
    private short delay;
    private boolean flip;
    private int duration;
    private boolean removeSkill;

    public AffectedArea(int templateId) {
        super(templateId);
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public int getCharID() {
        return owner.getId();
    }

    public void setOwner(Char owner) {
        this.owner = owner;
    }

    public Char getOwner() {
        return owner;
    }

    public int getSkillID() {
        return skillID;
    }

    public void setSkillID(int skillID) {
        this.skillID = skillID;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public int getElemAttr() {
        return elemAttr;
    }

    public void setElemAttr(int elemAttr) {
        this.elemAttr = elemAttr;
    }

    public int getIdk() {
        return idk;
    }

    public void setIdk(int idk) {
        this.idk = idk;
    }

    public int getSlv() {
        return slv;
    }

    public void setSlv(int slv) {
        this.slv = slv;
    }

    public byte getMobOrigin() {
        return mobOrigin;
    }

    public void setMobOrigin(byte mobOrigin) {
        this.mobOrigin = mobOrigin;
    }

    public short getDelay() {
        return delay;
    }

    public void setDelay(short delay) {
        this.delay = delay;
    }

    public boolean isFlip() {
        return flip;
    }

    public void setFlip(boolean flip) {
        this.flip = flip;
    }

    public boolean getRemoveSkill() {
        return removeSkill;
    }

    public void setRemoveSkill(boolean removeSkill) {
        this.removeSkill = removeSkill;
    }

    public static AffectedArea getAffectedArea(Char chr, AttackInfo attackInfo) {
        AffectedArea aa = new AffectedArea(-1);
        aa.setSkillID(attackInfo.skillId);
        aa.setSlv(attackInfo.slv);
        aa.setElemAttr(attackInfo.elemAttr);
        aa.setForce(attackInfo.force);
        aa.setOption(attackInfo.option);
        aa.setOwner(chr);
        return aa;
    }

    public static AffectedArea getPassiveAA(Char chr, int skillID, byte slv) {
        AffectedArea aa = new AffectedArea(-1);
        aa.setOwner(chr);
        aa.setSkillID(skillID);
        aa.setSlv(slv);
        aa.setRemoveSkill(true);

        return aa;
    }

    public void handleMobInside(Mob mob) {
        Char chr = getField().getCharByID(getCharID());
        if (chr == null) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int skillID = getSkillID();
        Skill skill = chr.getSkill(getSkillID());
        int slv = getSlv();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        MobTemporaryStat mts = mob.getTemporaryStat();
        Option o = new Option();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case Magician.POISON_MIST:
            case Archer.FLAME_SURGE:
            case Kanna.NIMBUS_CURSE:
                if (!mts.hasBurnFromSkillAndOwner(skillID, getCharID())) {
                    mts.createAndAddBurnedInfo(chr, skill);
                }
                break;
            case Shade.SPIRIT_TRAP:
                o.nOption = 1;
                o.rOption = skillID;
                o.tOption = si.getValue(time, slv);
                mts.addStatOptionsAndBroadcast(MobStat.Freeze, o);
                break;
            case Thief.FRAILTY_CURSE:
                o.nOption = si.getValue(SkillStat.y, slv);
                o.rOption = skillID;
                o.tOption = si.getValue(time, slv);
                mts.addStatOptionsAndBroadcast(MobStat.Speed, o);
                o1.nOption = si.getValue(SkillStat.x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                mts.addStatOptionsAndBroadcast(MobStat.PAD, o);
                mts.addStatOptionsAndBroadcast(MobStat.PDR, o);
                mts.addStatOptionsAndBroadcast(MobStat.MAD, o);
                mts.addStatOptionsAndBroadcast(MobStat.MDR, o);
                break;
            case Zero.TIME_DISTORTION:
                mts.removeBuffs();
                o.nOption = 1;
                o.rOption = skillID;
                o.tOption = 5;
                mts.addStatOptionsAndBroadcast(MobStat.Freeze, o);
                o1.nOption = si.getValue(SkillStat.x, slv);
                o1.rOption = skillID;
                o1.tOption = 5;
                mts.addStatOptionsAndBroadcast(MobStat.AddDamParty, o1);
                break;
        }
    }

    public void handleCharInside(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasAffectedArea(this)) {
            return;
        }
        tsm.addAffectedArea(this);
        int skillID = getSkillID();
        int slv = getSlv();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        Option o = new Option();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case Zero.TIME_DISTORTION:
                tsm.removeAllDebuffs();
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieBooster, slv);
                o2.tStart = (int) System.currentTimeMillis();
                tsm.putCharacterStatValue(IndieBooster, o2); // Indie
                break;
            case BlazeWizard.BURNING_CONDUIT:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                tsm.putCharacterStatValue(IndieDamR, o1); // Indie
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieBooster, slv);
                o2.tStart = (int) System.currentTimeMillis();
                tsm.putCharacterStatValue(IndieBooster, o2); // Indie
                break;
            case Kanna.BELLFLOWER_BARRIER:
                o1.nReason = skillID;
                o1.nValue = si.getValue(bdR, slv);
                o1.tStart = (int) System.currentTimeMillis();
                tsm.putCharacterStatValue(IndieBDR, o1); // Indie
                break;
            case Kanna.BLOSSOM_BARRIER:
                o1.nOption = si.getValue(SkillStat.x, slv);
                o1.rOption = skillID;
                tsm.putCharacterStatValue(DamageReduce, o1);
                o2.nOption = si.getValue(SkillStat.y, slv);
                o2.rOption = skillID;
                tsm.putCharacterStatValue(AsrR, o2);
                tsm.putCharacterStatValue(TerR, o2);
                break;
            case Aran.MAHAS_DOMAIN:
                chr.heal((int) (chr.getMaxHP() / ((double) 100 / si.getValue(w, slv))));
                chr.healMP((int) (chr.getMaxHP() / ((double) 100 / si.getValue(w, slv))));
                tsm.removeAllDebuffs();
                break;
            case Thief.SMOKE_SCREEN:
                o1.nOption = 1;
                tsm.putCharacterStatValue(Invincible, o1);
                o2.nOption = si.getValue(SkillStat.x, slv);
                tsm.putCharacterStatValue(IncCriticalDamMax, o2);
                break;
            case BeastTamer.PURR_ZONE:
                chr.heal(si.getValue(hp, slv));
                break;
            case Xenon.TEMPORAL_POD:
                o1.nOption = 2;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(OnCapsule, o1);
                Xenon.temporalPodTimer(chr);
                break;
        }
        tsm.sendSetStatPacket();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void broadcastSpawnPacket(Char onlyChar) {
        Field field = getField();
        field.broadcastPacket(CField.affectedAreaCreated(this));
        field.checkCharInAffectedAreas(onlyChar);
    }

    @Override
    public void broadcastLeavePacket() {
        Field field = getField();
        field.broadcastPacket(CField.affectedAreaRemoved(this, false));
        for (Char chr : field.getChars()) {
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            if (tsm.hasAffectedArea(this)) {
                tsm.removeStatsBySkill(getSkillID());
            }
        }
    }
}
