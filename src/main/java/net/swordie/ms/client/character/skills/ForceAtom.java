package net.swordie.ms.client.character.skills;

import net.swordie.ms.client.character.skills.info.ForceAtomInfo;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;

import java.util.List;

/**
 * Created by MechAviv on 2/14/2019.
 */
public class ForceAtom {
    private boolean byMob;
    private boolean toMob;
    private int userOwner;
    private int charID;
    private int skillID;
    private int arriveDir;
    private int arriveRange;
    private int bulletID;
    private ForceAtomEnum forceAtomType;
    private List<Integer> targets;
    private List<ForceAtomInfo> faiList;
    private Rect rect;
    private Position forcedTargetPos;
    private Position pos;

    public ForceAtom(boolean byMob, int userOwner, int charID, ForceAtomEnum forceAtomType, boolean toMob, List<Integer> targets, int skillID, List<ForceAtomInfo> faiList, Rect rect, int arriveDir, int arriveRange, Position forcedTargetPos, int bulletID, Position pos) {
        this.setByMob(byMob);
        this.setUserOwner(userOwner);
        this.setCharID(charID);
        this.setForceAtomType(forceAtomType);
        this.setToMob(toMob);
        this.setTargets(targets);
        this.setSkillID(skillID);
        this.setFaiList(faiList);
        this.setRect(rect);
        this.setArriveDir(arriveDir);
        this.setArriveRange(arriveRange);
        this.setForcedTargetPos(forcedTargetPos);
        this.setBulletID(bulletID);
        this.setPos(pos);
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(isByMob() ? 1 : 0);
        if(isByMob()) {
            outPacket.encodeInt(getUserOwner());
        }
        outPacket.encodeInt(getCharID());
        outPacket.encodeInt(getForceAtomType().getForceAtomType());
        if(getForceAtomType().getForceAtomType() != 0 && getForceAtomType().getForceAtomType() != 9 && getForceAtomType().getForceAtomType() != 14 && getForceAtomType().getForceAtomType() != 29 && getForceAtomType().getForceAtomType() != 35 && getForceAtomType().getForceAtomType() != 42) {
            outPacket.encodeByte(isToMob());
            switch (getForceAtomType().getForceAtomType()) {
                case 2:
                case 3:
                case 6:
                case 7:
                case 11:
                case 12:
                case 13:
                case 17:
                case 19:
                case 20:
                case 23:
                case 24:
                case 25:
                case 27:
                case 28:
                case 30:
                case 32:
                case 34:
                case 38:
                case 39:
                case 40:
                case 41:
                case 47:
                case 48:
                case 49:
                case 52:
                case 53:
                case 54:
                case 55:
                    outPacket.encodeInt(getTargets().size());
                    for (int i : getTargets()) {
                        outPacket.encodeInt(i);
                    }
                    break;
                default:
                    outPacket.encodeInt(getTargets().get(0));
                    break;
            }
            outPacket.encodeInt(getSkillID());
        }
        for(ForceAtomInfo fai : faiList) {
            outPacket.encodeByte(1);
            fai.encode(outPacket);
        }
        outPacket.encodeByte(0);
        switch (getForceAtomType().getForceAtomType()) {
            case 11:
                outPacket.encodeRectInt(getRect());
                outPacket.encodeInt(getBulletID());
                break;
            case 9:
                outPacket.encodeRectInt(getRect());
                break;
            case 15:
                outPacket.encodeRectInt(getRect());
                outPacket.encodeByte(false);
                break;
            case 29:
                outPacket.encodeRectInt(getRect());
                outPacket.encodePositionInt(getForcedTargetPos());
                break;
            case 16:
            case 4:
            case 26:
            case 33:
                outPacket.encodePositionInt(getPos());
                break;
            case 17:
                outPacket.encodeInt(getArriveDir());
                outPacket.encodeInt(getArriveRange());
                break;
            case 18:
                outPacket.encodePositionInt(getForcedTargetPos());
                break;
            case 27:
            case 28:
            case 34:
                outPacket.encodeRectInt(getRect());// 16 bytes buffer
                outPacket.encodeInt(0);// duration ? (x * 1000)
                break;
            case 36:
            case 39:
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeRectInt(getRect());// 16 bytes buffer
                if (getForceAtomType().getForceAtomType() == 36) {
                    outPacket.encodeRectInt(getRect());// 16 bytes buffer
                    outPacket.encodeInt(0);
                }
                break;
            case 37:
                outPacket.encodeInt(0);
                outPacket.encodeRectInt(getRect());// 16 bytes buffer
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                break;
            case 42:
                outPacket.encodeRectInt(getRect());// 16 bytes buffer
                break;
            case 49:// not sure
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                break;
        }
    }

    public boolean isByMob() {
        return byMob;
    }

    public void setByMob(boolean byMob) {
        this.byMob = byMob;
    }

    public boolean isToMob() {
        return toMob;
    }

    public void setToMob(boolean toMob) {
        this.toMob = toMob;
    }

    public int getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(int userOwner) {
        this.userOwner = userOwner;
    }

    public int getCharID() {
        return charID;
    }

    public void setCharID(int charID) {
        this.charID = charID;
    }

    public int getSkillID() {
        return skillID;
    }

    public void setSkillID(int skillID) {
        this.skillID = skillID;
    }

    public int getArriveDir() {
        return arriveDir;
    }

    public void setArriveDir(int arriveDir) {
        this.arriveDir = arriveDir;
    }

    public int getArriveRange() {
        return arriveRange;
    }

    public void setArriveRange(int arriveRange) {
        this.arriveRange = arriveRange;
    }

    public int getBulletID() {
        return bulletID;
    }

    public void setBulletID(int bulletID) {
        this.bulletID = bulletID;
    }

    public ForceAtomEnum getForceAtomType() {
        return forceAtomType;
    }

    public void setForceAtomType(ForceAtomEnum forceAtomType) {
        this.forceAtomType = forceAtomType;
    }

    public List<Integer> getTargets() {
        return targets;
    }

    public void addTarget(int target) {
        this.targets.add(target);
    }

    public void setTargets(List<Integer> targets) {
        this.targets = targets;
    }

    public List<ForceAtomInfo> getFaiList() {
        return faiList;
    }

    public void addFaiList(ForceAtomInfo fai) {
        this.faiList.add(fai);
    }

    public void setFaiList(List<ForceAtomInfo> faiList) {
        this.faiList = faiList;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public Position getForcedTargetPos() {
        return forcedTargetPos;
    }

    public void setForcedTargetPos(Position forcedTargetPos) {
        this.forcedTargetPos = forcedTargetPos;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }
}
