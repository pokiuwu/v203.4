package net.swordie.ms.life.mob.skill;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.temp.TemporaryStatBase;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.packet.MobPool;
import net.swordie.ms.enums.BaseStat;
import net.swordie.ms.enums.TSIndex;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.containerclasses.MobSkillInfo;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.world.field.Field;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;

import java.util.List;
import java.util.Objects;

import static net.swordie.ms.life.mob.skill.MobSkillStat.*;

/**
 * Created on 2/28/2018.
 */
public class MobSkill {
    private static final Logger log = LogManager.getRootLogger();
    private int skillSN;
    private byte action;
    private int level;
    private int effectAfter;
    private int skillAfter;
    private byte priority;
    private boolean firstAttack;
    private boolean onlyFsm;
    private boolean onlyOtherSkill;
    private int skillForbid;
    private int afterDelay;
    private int fixDamR;
    private boolean doFirst;
    private int preSkillIndex;
    private int preSkillCount;
    private String info;
    private String text;
    private boolean afterDead;
    private int afterAttack = -1;
    private int afterAttackCount;
    private int castTime;
    private int coolTime;
    private int delay;
    private int useLimit;
    private String speak;
    private int skillID;

    public int getSkillSN() {
        return skillSN;
    }

    public void setSkillSN(int skillSN) {
        this.skillSN = skillSN;
    }

    public byte getAction() {
        return action;
    }

    public void setAction(byte action) {
        this.action = action;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getEffectAfter() {
        return effectAfter;
    }

    public void setEffectAfter(int effectAfter) {
        this.effectAfter = effectAfter;
    }

    public int getSkillAfter() {
        return skillAfter;
    }

    public void setSkillAfter(int skillAfter) {
        this.skillAfter = skillAfter;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public boolean getFirstAttack() {
        return firstAttack;
    }

    public void setFirstAttack(boolean firstAttack) {
        this.firstAttack = firstAttack;
    }

    public void setOnlyFsm(boolean onlyFsm) {
        this.onlyFsm = onlyFsm;
    }

    public boolean isOnlyFsm() {
        return onlyFsm;
    }

    public void setOnlyOtherSkill(boolean onlyOtherSkill) {
        this.onlyOtherSkill = onlyOtherSkill;
    }

    public boolean isOnlyOtherSkill() {
        return onlyOtherSkill;
    }

    public void setSkillForbid(int skillForbid) {
        this.skillForbid = skillForbid;
    }

    public int getSkillForbid() {
        return skillForbid;
    }

    public void setAfterDelay(int afterDelay) {
        this.afterDelay = afterDelay;
    }

    public int getAfterDelay() {
        return afterDelay;
    }

    public void setFixDamR(int fixDamR) {
        this.fixDamR = fixDamR;
    }

    public int getFixDamR() {
        return fixDamR;
    }

    public void setDoFirst(boolean doFirst) {
        this.doFirst = doFirst;
    }

    public boolean isDoFirst() {
        return doFirst;
    }

    public void setPreSkillIndex(int preSkillIndex) {
        this.preSkillIndex = preSkillIndex;
    }

    public int getPreSkillIndex() {
        return preSkillIndex;
    }

    public void setPreSkillCount(int preSkillCount) {
        this.preSkillCount = preSkillCount;
    }

    public int getPreSkillCount() {
        return preSkillCount;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info == null ? "" : info;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text == null ? "" : text;
    }

    public void setAfterDead(boolean afterDead) {
        this.afterDead = afterDead;
    }

    public boolean isAfterDead() {
        return afterDead;
    }

    public void setAfterAttack(int afterAttack) {
        this.afterAttack = afterAttack;
    }

    public int getAfterAttack() {
        return afterAttack;
    }

    public void setAfterAttackCount(int afterAttackCount) {
        this.afterAttackCount = afterAttackCount;
    }

    public int getAfterAttackCount() {
        return afterAttackCount;
    }

    public void setCastTime(int castTime) {
        this.castTime = castTime;
    }

    public int getCastTime() {
        return castTime;
    }

    public void setCoolTime(int coolTime) {
        this.coolTime = coolTime;
    }

    public int getCoolTime() {
        return coolTime;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }

    public void setUseLimit(int useLimit) {
        this.useLimit = useLimit;
    }

    public int getUseLimit() {
        return useLimit;
    }

    public void setSpeak(String speak) {
        this.speak = speak;
    }

    public String getSpeak() {
        return speak == null ? "" : speak;
    }

    public int getSkillID() {
        return skillID;
    }

    public void setSkillID(int skillID) {
        this.skillID = skillID;
    }

    public void handleEffect(Mob mob) {
        MobTemporaryStat mts = mob.getTemporaryStat();
        short skill = (short) getSkillID();
        short level = (short) getLevel();
        MobSkillInfo msi = SkillData.getMobSkillInfoByIdAndLevel(skill, level);
        MobSkillID msID = MobSkillID.getMobSkillIDByVal(skill);
        Field field = mob.getField();
        Option o = new Option(skill);
        o.slv = level;
        o.tOption = msi.getSkillStatIntValue(time);
        switch(msID) {
            case POWERUP:
            case POWERUP_M:
            case PAD:
                o.nOption = msi.getSkillStatIntValue(x);
                mts.addMobSkillOptionsAndBroadCast(MobStat.PowerUp, o);
                break;
            case PGUARDUP:
                o.nOption = msi.getSkillStatIntValue(x);
                mts.addMobSkillOptionsAndBroadCast(MobStat.PGuardUp, o);
                break;
            case MGUARDUP:
                o.nOption = msi.getSkillStatIntValue(x);
                mts.addMobSkillOptionsAndBroadCast(MobStat.MGuardUp, o);
                break;
            case SEAL:
                Rect rect = mob.getPosition().getRectAround(new Rect(msi.getLt(), msi.getRb()));
                if(!mob.isLeft()) {
                    rect = rect.horizontalFlipAround(mob.getPosition().getX());
                }
                List<Char> chars = mob.getField().getCharsInRect(rect);
                Char chr = chars.size() == 0 ? null : Util.getRandomFromCollection(chars);
                if (chr == null) {
                    return;
                }
                TemporaryStatManager tsm = chr.getTemporaryStatManager();
                if(Util.succeedProp(msi.getSkillStatIntValue(prop))
                        && Util.succeedProp(100 - chr.getTotalStat(BaseStat.asr))) {
                    o.nOption = 1;
                    o.tOption = msi.getSkillStatIntValue(time);
                    tsm.putCharacterStatValueFromMobSkill(CharacterTemporaryStat.Seal, o);
                    tsm.sendSetStatPacket();
                }
                break;
            case WEAKNESS:
                rect = mob.getPosition().getRectAround(new Rect(msi.getLt(), msi.getRb()));
                if(!mob.isLeft()) {
                    rect = rect.horizontalFlipAround(mob.getPosition().getX());
                }
                chars = mob.getField().getCharsInRect(rect);
                chr = chars.size() == 0 ? null : Util.getRandomFromCollection(chars);
                if (chr == null) {
                    return;
                }
                tsm = chr.getTemporaryStatManager();
                if(Util.succeedProp(msi.getSkillStatIntValue(prop))
                        && Util.succeedProp(100 - chr.getTotalStat(BaseStat.asr))) {
                    o.nOption = 1;
                    o.tOption = msi.getSkillStatIntValue(time);
                    tsm.putCharacterStatValueFromMobSkill(CharacterTemporaryStat.Weakness, o);
                    tsm.sendSetStatPacket();
                }
                break;
            case CURSE:
                rect = mob.getPosition().getRectAround(new Rect(msi.getLt(), msi.getRb()));
                if(!mob.isLeft()) {
                    rect = rect.horizontalFlipAround(mob.getPosition().getX());
                }
                chars = mob.getField().getCharsInRect(rect);
                chr = chars.size() == 0 ? null : Util.getRandomFromCollection(chars);
                if (chr == null) {
                    return;
                }
                tsm = chr.getTemporaryStatManager();
                if(Util.succeedProp(msi.getSkillStatIntValue(prop))
                        && Util.succeedProp(100 - chr.getTotalStat(BaseStat.asr))) {
                    o.nOption = 1;
                    o.tOption = msi.getSkillStatIntValue(time);
                    tsm.putCharacterStatValueFromMobSkill(CharacterTemporaryStat.Curse, o);
                    tsm.sendSetStatPacket();
                }
                break;
            case SLOW:
                rect = mob.getPosition().getRectAround(new Rect(msi.getLt(), msi.getRb()));
                if(!mob.isLeft()) {
                    rect = rect.horizontalFlipAround(mob.getPosition().getX());
                }
                chars = mob.getField().getCharsInRect(rect);
                chr = chars.size() == 0 ? null : Util.getRandomFromCollection(chars);
                if (chr == null) {
                    return;
                }
                tsm = chr.getTemporaryStatManager();
                if(Util.succeedProp(msi.getSkillStatIntValue(prop))
                        && Util.succeedProp(100 - chr.getTotalStat(BaseStat.asr))) {
                    o.nOption = 1;
                    o.tOption = msi.getSkillStatIntValue(time);
                    tsm.putCharacterStatValueFromMobSkill(CharacterTemporaryStat.Slow, o);
                    tsm.sendSetStatPacket();
                }
                break;
            case UNDEAD:
                rect = mob.getPosition().getRectAround(new Rect(msi.getLt(), msi.getRb()));
                if(!mob.isLeft()) {
                    rect = rect.horizontalFlipAround(mob.getPosition().getX());
                }
                chars = mob.getField().getCharsInRect(rect);
                chr = chars.size() == 0 ? null : Util.getRandomFromCollection(chars);
                if (chr == null) {
                    return;
                }
                tsm = chr.getTemporaryStatManager();
                if(Util.succeedProp(msi.getSkillStatIntValue(prop))
                        && Util.succeedProp(100 - chr.getTotalStat(BaseStat.asr))) {
                    o.nOption = 1;
                    o.tOption = msi.getSkillStatIntValue(time);
                    tsm.putCharacterStatValueFromMobSkill(CharacterTemporaryStat.Undead, o);
                    TemporaryStatBase tsb = tsm.getTSBByTSIndex(TSIndex.Undead);
                    tsb.setNOption(o.nOption);
                    tsb.setROption(skill << level | 16);
                    tsb.setExpireTerm(o.tOption);
                    tsm.sendSetStatPacket();
                }
                break;
            case PHYSICAL_IMMUNE:
                o.nOption = msi.getSkillStatIntValue(x);
                mts.addMobSkillOptionsAndBroadCast(MobStat.PImmune, o);
                break;
            case MAGIC_IMMUNE:
                o.nOption = msi.getSkillStatIntValue(x);
                mts.addMobSkillOptionsAndBroadCast(MobStat.MImmune, o);
                break;
            case TELEPORT:
                int xPos = msi.getSkillStatIntValue(x);
                int yPos = msi.getSkillStatIntValue(y);
                // probably not the right logic
                if (xPos != 0 && yPos != 0) {
                    Rect possibleRect = mob.getPosition().getRectAround(new Rect(-xPos, -yPos, xPos, yPos));
                    mob.setPosition(new Position(Util.getRandom(possibleRect.getLeft(), possibleRect.getRight()),
                            Util.getRandom(possibleRect.getTop(), possibleRect.getBottom())));
                } else {
                    // xPos == skillAfter (both x)
                    mob.getField().getLifeToControllers().get(mob).write(MobPool.teleportRequest(xPos));
                }
                break;
            case PM_COUNTER:
                o.nOption = msi.getSkillStatIntValue(x);
                o.mOption = 100;
                o.wOption = msi.getSkillStatIntValue(y);
                mts.addMobSkillOptionsAndBroadCast(MobStat.PCounter, o);
                break;
            case SUMMON:
            case SUMMON2:
                if (afterDead && mob.getHp() > 0) {
                    break;
                }
                Position spawnPos = mob.getPosition();
                if (msi.getLt() != null) {
                    rect = new Rect(msi.getLt(), msi.getRb());
                    spawnPos = rect.getRandomPositionInside();
                }
                for(int i : msi.getInts()) {
                    Mob m = mob.getField().spawnMob(i, spawnPos.getX(), spawnPos.getY(), false, 0);
                    if (msi.getSkillStatIntValue(hp) > 0) {
                        m.setMaxHp(msi.getSkillStatIntValue(hp));
                        m.setHp(msi.getSkillStatIntValue(hp));
                    }
                }
                break;
            case CASTINGBAR:
                field.broadcastPacket(MobPool.castingBarSkillStart(1,
                        msi.getSkillStatIntValue(MobSkillStat.castingTime), false, false));
                break;
            case BOUNCE_ATTACK:
                mob.getField().broadcastPacket(MobPool.createBounceAttackSkill(mob, msi, false));
                break;
            case LASER_ATTACK:
                if (!mts.hasCurrentMobStat(MobStat.Laser)) {
                    o.nOption = 1;
                    o.wOption = msi.getSkillStatIntValue(MobSkillStat.w);
                    o.uOption = msi.getSkillStatIntValue(MobSkillStat.z);
                    mts.addMobSkillOptionsAndBroadCast(MobStat.Laser, o);
                }
                break;
            case LASER_CONTROL:
                // Not needed? Automatically handled well by the controller
                break;
            case UNK:
                log.warn(String.format("Unknown mob skillID %d, slv = %d", skill, level));
                break;
            default:
                log.warn(String.format("Unhandled mob skillID %s, slv = %d", msID, getLevel()));
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MobSkill mobSkill = (MobSkill) o;
        return skillSN == mobSkill.skillSN &&
                skillID == mobSkill.skillID &&
                level == mobSkill.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillSN, skillID, level);
    }
}
