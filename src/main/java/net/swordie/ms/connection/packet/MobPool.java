package net.swordie.ms.connection.packet;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.life.DeathType;
import net.swordie.ms.life.mob.*;
import net.swordie.ms.life.mob.skill.BurnedInfo;
import net.swordie.ms.life.mob.skill.MobSkillID;
import net.swordie.ms.life.mob.skill.MobSkillStat;
import net.swordie.ms.life.mob.skill.ShootingMoveStat;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.life.movement.MovementInfo;
import net.swordie.ms.loaders.containerclasses.MobSkillInfo;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.container.Tuple;
import java.util.List;

/**
 * Created on 2/28/2018.
 */
public class MobPool {
    public static OutPacket enterField(Mob mob, boolean hasBeenInit) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_ENTER_FIELD);

        outPacket.encodeByte(mob.isSealedInsteadDead());
        outPacket.encodeInt(mob.getObjectId());
        outPacket.encodeByte(mob.getCalcDamageIndex());
        outPacket.encodeInt(mob.getTemplateId());
        ForcedMobStat fms = mob.getForcedMobStat();
        outPacket.encodeByte(fms != null);
        if(fms != null) {
            fms.encode(outPacket);
        }
        mob.getTemporaryStat().encode(outPacket);
        if(!hasBeenInit) {
            // CMob::Init
            mob.encodeInit(outPacket);
        }
        return outPacket;
    }

    public static OutPacket changeController(Mob mob, boolean hasBeenInit, boolean isController) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_CHANGE_CONTROLLER);
        outPacket.encodeByte(isController);
        outPacket.encodeInt(mob.getObjectId());
        if(isController) {
            outPacket.encodeByte(mob.getCalcDamageIndex());
            outPacket.encodeInt(mob.getTemplateId());
            ForcedMobStat fms = mob.getForcedMobStat();
            outPacket.encodeByte(fms != null);
            if(fms != null) {
                fms.encode(outPacket);
            }
            mob.getTemporaryStat().encode(outPacket);
            if(!hasBeenInit) {
                mob.encodeInit(outPacket);
            }
        }

        return outPacket;
    }

    public static OutPacket leaveField(int id, DeathType deadType){
        OutPacket outPacket = new OutPacket(OutHeader.MOB_LEAVE_FIELD);

        outPacket.encodeInt(id);
        outPacket.encodeByte(deadType.getVal());

        return outPacket;
    }

    public static OutPacket damaged(int mobID, long damage, int templateID, byte type, long hp, long maxHp) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_DAMAGED);

        outPacket.encodeInt(mobID);
        outPacket.encodeByte(type);
        outPacket.encodeLong(damage);
        if(templateID / 10000 == 250 || templateID / 10000 == 251) {
            outPacket.encodeLong(hp);
            outPacket.encodeLong(maxHp);
        }

        return outPacket;
    }

    public static OutPacket hpIndicator(int objectId, byte percDamage) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_HP_INDICATOR);

        outPacket.encodeInt(objectId);
        outPacket.encodeInt(percDamage);
        outPacket.encodeByte(0);

        return outPacket;
    }

    public static OutPacket ctrlAck(Mob mob, boolean nextAttackPossible, short mobCtrlSN, int skillID, byte slv, int forcedAttack) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_CONTROL_ACK);

        outPacket.encodeInt(mob.getObjectId());
        outPacket.encodeShort(mobCtrlSN);
        outPacket.encodeByte(nextAttackPossible);
        outPacket.encodeInt((int) mob.getMp());
        outPacket.encodeInt(skillID);
        outPacket.encodeShort(slv);
        outPacket.encodeInt(forcedAttack);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);// for secure
        return outPacket;
    }

    public static OutPacket ctrlChange(Char chr, Mob mob, boolean isController) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_CHANGE_CONTROLLER);

        outPacket.encodeByte(isController);
        outPacket.encodeInt(mob.getObjectId());
        if(isController) {
            outPacket.encodeByte(1); // controlling type
        }

        return outPacket;

    }

    public static OutPacket statSet(Mob mob, short delay) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_STAT_SET);
        MobTemporaryStat mts = mob.getTemporaryStat();
        boolean hasMovementStat = mts.hasNewMovementAffectingStat();
        outPacket.encodeInt(mob.getObjectId());
        mts.encode(outPacket);
        outPacket.encodeShort(delay);
        outPacket.encodeByte(1); // nCalcDamageStatIndex
        if(hasMovementStat) {
            outPacket.encodeByte(0); // ?
        }

        return outPacket;
    }

    public static OutPacket statReset(Mob mob, byte byteCalcDamageStatIndex, boolean sn) {
        return statReset(mob, byteCalcDamageStatIndex, sn, null);
    }

    public synchronized static OutPacket statReset(Mob mob, byte calcDamageStatIndex, boolean sn, List<BurnedInfo> biList) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_STAT_RESET);
        MobTemporaryStat resetStats = mob.getTemporaryStat();
        int[] mask = resetStats.getRemovedMask();
        outPacket.encodeInt(mob.getObjectId());
        for (int i = 0; i < mob.getTemporaryStat().getNewMask().length; i++) {
            outPacket.encodeInt(mask[i]);
        }
        if(resetStats.hasRemovedMobStat(MobStat.BurnedInfo)) {
            if(biList == null) {
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
            } else {
                int dotCount = biList.stream().mapToInt(BurnedInfo::getDotCount).sum();
                outPacket.encodeInt(dotCount);
                outPacket.encodeInt(biList.size());
                for(BurnedInfo bi : biList) {
                    outPacket.encodeInt(bi.getCharacterId());
                    outPacket.encodeInt(bi.getSuperPos());
                }
            }
            resetStats.getBurnedInfos().clear();
        }
        outPacket.encodeByte(calcDamageStatIndex);
        if(resetStats.hasRemovedMovementAffectingStat()) {
            outPacket.encodeByte(sn);
        }
        resetStats.getRemovedStatVals().clear();
        return outPacket;
    }

    public static OutPacket specialEffectBySkill(Mob mob, int skillID, int charId, short hit) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_SPECIAL_EFFECT_BY_SKILL);

        outPacket.encodeInt(mob.getObjectId());
        outPacket.encodeInt(skillID);
        outPacket.encodeInt(charId);
        outPacket.encodeShort(hit);

        return outPacket;
    }

    public static OutPacket affected(Mob mob, int skillID, int slv, boolean userSkill, short delay) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_AFFECTED);

        outPacket.encodeInt(mob.getObjectId());
        outPacket.encodeInt(skillID);
        outPacket.encodeShort(delay);
        outPacket.encodeByte(userSkill);
        outPacket.encodeInt(slv);

        return outPacket;
    }

    public static OutPacket move(Mob mob, MobSkillAttackInfo msai, MovementInfo movementInfo) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_MOVE);

        outPacket.encodeInt(mob.getObjectId());
        outPacket.encodeByte(msai.actionAndDirMask);
        outPacket.encodeByte(msai.action);

        outPacket.encodeLong(msai.targetInfo);
        outPacket.encodeByte(msai.multiTargetForBalls.size());
        for(Position pos : msai.multiTargetForBalls) {
            outPacket.encodePosition(pos);
        }
        outPacket.encodeByte(msai.randTimeForAreaAttacks.size());
        for(short s : msai.randTimeForAreaAttacks) {
            outPacket.encodeShort(s);
        }
        outPacket.encodeByte(0);
        outPacket.encode(movementInfo);

        return outPacket;
    }


    public static OutPacket castingBarSkillStart(int gaugeType, int castingTime, boolean reverseGauge, boolean notShowUI) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_CASTING_BAR_SKILL);

        outPacket.encodeInt(gaugeType);
        outPacket.encodeInt(castingTime);
        outPacket.encodeByte(reverseGauge);
        outPacket.encodeByte(notShowUI);

        return outPacket;
    }

    public static OutPacket createBounceAttackSkill(Mob mob, MobSkillInfo msi, boolean afterConvexSkill) {
        // 0s are the ones where the wz property to take is unknown
        OutPacket outPacket = new OutPacket(OutHeader.MOB_BOUNCE_ATTACK_SKILL);

        outPacket.encodeInt(mob.getObjectId());
        outPacket.encodeInt(msi.getId());
        outPacket.encodeInt(msi.getLevel());
        outPacket.encodeByte(afterConvexSkill);
        if (afterConvexSkill) { // save for when an afterConvexSkill is found
            int count = 0;
            outPacket.encodeInt(count); // nCount
            outPacket.encodeByte(false); // bDelayedSkill
            outPacket.encodeInt(0); // nCreateY
            outPacket.encodeInt(0); // nDensity
            outPacket.encodeInt(0); // nFriction
            outPacket.encodeInt(0); // nRestitution
            outPacket.encodeInt(0); // tDestroyDelay
            for (int i = 0; i < count; i++) {
                outPacket.encodeInt(mob.getObjectId() + i + 1);
            }

        } else {
            Position pos = mob.getPosition();
            outPacket.encodeInt(pos.getX() + msi.getSkillStatIntValue(MobSkillStat.x));
            outPacket.encodeInt(pos.getY() + msi.getSkillStatIntValue(MobSkillStat.y));
            int count = msi.getSkillStatIntValue(MobSkillStat.count);
            outPacket.encodeInt(count);
            for (int i = 0; i < count; i++) {
                outPacket.encodeInt(mob.getObjectId() + i + 1); // nObjectSN
                outPacket.encodePositionInt(msi.getLt());
            }
            outPacket.encodeInt(0); // nFriction
            outPacket.encodeInt(0); // nRestitution
            outPacket.encodeInt(0); // tDestroyDelay
            outPacket.encodeInt(msi.getSkillStatIntValue(MobSkillStat.delay)); // tStartDelay
            outPacket.encodeByte(msi.getSkillStatIntValue(MobSkillStat.noGravity)); // bNoGravity
            boolean notDestroyByCollide = msi.getSkillStatIntValue(MobSkillStat.notDestroyByCollide) != 0;
            outPacket.encodeByte(notDestroyByCollide); // bNotDestroyByCollide
            if (msi.getId() == MobSkillID.BOUNCE_ATTACK.getVal() && (msi.getLevel() == 3 || msi.getLevel() == 4)) {
                outPacket.encodePositionInt(msi.getRb2());
            }
            if (notDestroyByCollide) {
                outPacket.encodeInt(1); // nIncScale
                outPacket.encodeInt(100); // nMaxScale
                outPacket.encodeInt(100); // nDecRadius
                outPacket.encodeInt(100); // fAngle
            }
        }


        return outPacket;
    }

    public static OutPacket teleportRequest(int skillAfter) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_TELEPORT_REQUEST);

        outPacket.encodeByte(skillAfter == 0);
        if (skillAfter != 0) {
            outPacket.encodeInt(skillAfter);
            switch (skillAfter) {
                case 3:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    outPacket.encodePositionInt(new Position(0, 0)); // possible position?
                    break;
                case 4:
                    outPacket.encodeInt(0); // possible x?
                    break;
            }
        }

        return outPacket;
    }

    public static OutPacket nextAttack(int forcedAttackIdx) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_NEXT_ATTACK);

        outPacket.encodeInt(forcedAttackIdx);

        return outPacket;
    }

    public static OutPacket setAfterAttack(int mobID, short afterAttack, int serverAction, boolean left) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_SET_AFTER_ATTACK);

        outPacket.encodeInt(mobID);
        outPacket.encodeShort(afterAttack);
        outPacket.encodeInt(serverAction);
        outPacket.encodeByte(left);

        return outPacket;
    }

    public static OutPacket setSkillDelay(int mobID, int skillAfter, int skillID, int slv, int sequenceDelay, Rect rect) {
        OutPacket outPacket = new OutPacket(OutHeader.MOB_SKILL_DELAY);

        outPacket.encodeInt(mobID);
        outPacket.encodeInt(skillAfter);
        outPacket.encodeInt(skillID);
        outPacket.encodeInt(slv);
        outPacket.encodeInt(sequenceDelay);
        if (rect != null) {
            outPacket.encodeRectInt(rect);
        } else {
            outPacket.encodeArr(new byte[16]); // (0,0),(0,0)
        }

        return outPacket;
    }

    public static OutPacket escortFullPath(Mob mob, int oldAttr, boolean stopEscort) {
        OutPacket outPacket = new OutPacket(OutHeader.ESCORT_FULL_PATH);

        outPacket.encodeInt(mob.getObjectId());
        outPacket.encodeInt(mob.getEscortDest().size());
        outPacket.encodeShort(mob.getPosition().getX());
        outPacket.encodeShort(oldAttr);
        outPacket.encodeInt(mob.getPosition().getY());
        for (EscortDest escortDest : mob.getEscortDest()) {
            outPacket.encodeShort(escortDest.getDestPos().getX());
            outPacket.encodeShort(escortDest.getAttr());
            outPacket.encodeInt(escortDest.getDestPos().getY());
            outPacket.encodeInt(escortDest.getMass());
            if (escortDest.getMass() == 2) {
                outPacket.encodeInt(escortDest.getStopDuration());
            }
        }
        outPacket.encodeInt(mob.getCurrentDestIndex());
        int stopDuration = mob.getEscortStopDuration();
        outPacket.encodeByte(stopDuration > 0);
        if (stopDuration > 0) {
            outPacket.encodeInt(stopDuration);
        }
        outPacket.encodeByte(stopEscort);
        return outPacket;
    }
}
