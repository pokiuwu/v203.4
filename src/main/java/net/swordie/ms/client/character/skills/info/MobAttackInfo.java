package net.swordie.ms.client.character.skills.info;

import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;

public class MobAttackInfo {
    public int mobId;
    public byte hitAction;
    public byte left;
    public byte idk3;
    public byte foreAction;
    public byte frameIdx;
    public byte calcDamageStatIndex;
    public short hitX;
    public short hitY;
    public short oldPosX;
    public short oldPosY;
    public short hpPerc;
    public long[] damages;
    public int mobUpDownYRange;
    public byte type;
    public String currentAnimationName;
    public int animationDeltaL;
    public String[] hitPartRunTimes;
    public int templateID;
    public short idk6;
    public boolean isResWarriorLiftPress;
    public Position pos1;
    public Position pos2;
    public Rect rect;
    public int idkInt;
    public byte byteIdk1;
    public byte byteIdk2;
    public byte byteIdk3;
    public byte byteIdk4;
    public byte byteIdk5;
    public int psychicLockInfo;
    public byte rocketRushInfo;
    public byte forceActionAndLeft;
    public byte calcDamageStatIndexAndDoomed;
    public int hitPartRunTimesSize;
    public short magicInfo;

    public MobAttackInfo deepCopy() {
        MobAttackInfo mai = new MobAttackInfo();
        mai.mobId = mobId;
        mai.hitAction = hitAction;
        mai.left = left;
        mai.idk3 = idk3;
        mai.forceActionAndLeft = forceActionAndLeft;
        mai.frameIdx = frameIdx;
        mai.calcDamageStatIndex = calcDamageStatIndex;
        mai.hitX = hitX;
        mai.hitY = hitY;
        mai.oldPosX = oldPosX;
        mai.oldPosY = oldPosY;
        mai.hpPerc = hpPerc;
        mai.damages = new long[damages.length];
        if(damages != null && damages.length > 0) {
            System.arraycopy(damages, 0, mai.damages, 0, damages.length);
        }
        mai.mobUpDownYRange = mobUpDownYRange;
        mai.animationDeltaL = animationDeltaL;
        if(hitPartRunTimes != null && hitPartRunTimes.length > 0) {
            System.arraycopy(hitPartRunTimes, 0, mai.hitPartRunTimes, 0, hitPartRunTimes.length);
        }
        mai.templateID = templateID;
        mai.idk6 = idk6;
        mai.isResWarriorLiftPress = isResWarriorLiftPress;
        mai.pos1 = pos1 != null ? pos1.deepCopy() : null;
        mai.pos2 = pos2 != null ? pos2.deepCopy() : null;
        mai.rect = rect != null ? rect.deepCopy() : null;
        mai.idkInt = idkInt;
        mai.byteIdk1 = byteIdk1;
        mai.byteIdk2 = byteIdk2;
        mai.byteIdk3 = byteIdk3;
        mai.byteIdk4 = byteIdk4;
        mai.byteIdk5 = byteIdk5;
        mai.psychicLockInfo = psychicLockInfo;
        mai.rocketRushInfo = rocketRushInfo;
        return mai;
    }
}
