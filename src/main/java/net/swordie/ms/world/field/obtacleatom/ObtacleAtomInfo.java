package net.swordie.ms.world.field.obtacleatom;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.util.Position;

/**
 * Created on 5/30/2018.
 */
public class ObtacleAtomInfo {
    private int atomType;
    private int key;
    private Position startPos;
    private Position endPos;
    private int hitBoxRange;
    private int trueDamR;
    private int mobDamR;
    private int createDelay;
    private int height;
    private int vPerSec;
    private int maxP;
    private int length;
    private int angle;
    private ObtacleDiagonalInfo obtacleDiagonalInfo;

    public ObtacleAtomInfo() {
    }

    public ObtacleAtomInfo(int atomType, int key, Position startPos, Position endPos, int hitBoxRange, int trueDamR,
                           int mobDamR, int createDelay, int height, int vPerSec, int maxP, int length, int angle) {
        this.atomType = atomType;
        this.key = key;
        this.startPos = startPos;
        this.endPos = endPos;
        this.hitBoxRange = hitBoxRange;
        this.trueDamR = trueDamR;
        this.mobDamR = mobDamR;
        this.createDelay = createDelay;
        this.height = height;
        this.vPerSec = vPerSec;
        this.maxP = maxP;
        this.length = length;
        this.angle = angle;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getAtomType());
        outPacket.encodeInt(getKey());
        outPacket.encodeInt(getStartPos().getX());
        outPacket.encodeInt(getStartPos().getY());
        outPacket.encodeInt(getEndPos().getX());
        outPacket.encodeInt(getEndPos().getY());
        outPacket.encodeInt(getHitBoxRange());
        outPacket.encodeInt(getTrueDamR());
        outPacket.encodeInt(getMobDamR());
        outPacket.encodeInt(getCreateDelay());
        outPacket.encodeInt(getHeight());
        outPacket.encodeInt(getvPerSec());
        outPacket.encodeInt(getMaxP());
        outPacket.encodeInt(getLength());
        outPacket.encodeInt(getAngle());
    }

    public int getAtomType() {
        return atomType;
    }

    public void setAtomType(int atomType) {
        this.atomType = atomType;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Position getStartPos() {
        return startPos;
    }

    public void setStartPos(Position startPos) {
        this.startPos = startPos;
    }

    public Position getEndPos() {
        return endPos;
    }

    public void setEndPos(Position endPos) {
        this.endPos = endPos;
    }

    public int getHitBoxRange() {
        return hitBoxRange;
    }

    public void setHitBoxRange(int hitBoxRange) {
        this.hitBoxRange = hitBoxRange;
    }

    public int getTrueDamR() {
        return trueDamR;
    }

    public void setTrueDamR(int trueDamR) {
        this.trueDamR = trueDamR;
    }

    public int getMobDamR() {
        return mobDamR;
    }

    public void setMobDamR(int mobDamR) {
        this.mobDamR = mobDamR;
    }

    public int getCreateDelay() {
        return createDelay;
    }

    public void setCreateDelay(int createDelay) {
        this.createDelay = createDelay;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getvPerSec() {
        return vPerSec;
    }

    public void setvPerSec(int vPerSec) {
        this.vPerSec = vPerSec;
    }

    public int getMaxP() {
        return maxP;
    }

    public void setMaxP(int maxP) {
        this.maxP = maxP;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public ObtacleDiagonalInfo getObtacleDiagonalInfo() {
        return obtacleDiagonalInfo;
    }

    public void setObtacleDiagonalInfo(ObtacleDiagonalInfo obtacleDiagonalInfo) {
        this.obtacleDiagonalInfo = obtacleDiagonalInfo;
    }
}
