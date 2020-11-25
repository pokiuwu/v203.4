package net.swordie.ms.connection.packet;

import net.swordie.ms.client.character.avatar.AvatarLook;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.guild.Guild;
import net.swordie.ms.enums.BaseStat;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.AvatarModifiedMask;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.life.movement.MovementInfo;
import net.swordie.ms.util.Position;


/**
 * Created on 2/3/2018.
 */
public class UserRemote {
    public static OutPacket setActiveNickItem(Char chr, String idk) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_SET_ACTIVE_NICK_ITEM);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeInt(chr.getNickItem());
        outPacket.encodeByte(!idk.isEmpty());
        if (!idk.isEmpty()) {
            outPacket.encodeString(idk);
        }

        return outPacket;
    }

    public static OutPacket move(Char chr, MovementInfo movementInfo) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_MOVE);

        outPacket.encodeInt(chr.getId());
        outPacket.encode(movementInfo);

        return outPacket;
    }

    public static OutPacket emotion(int id, int emotion, int duration, boolean byItemOption) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_EMOTION);

        outPacket.encodeInt(id);
        outPacket.encodeInt(emotion);
        outPacket.encodeInt(duration);
        outPacket.encodeByte(byItemOption);

        return outPacket;
    }



    public static OutPacket attack(Char chr, AttackInfo ai) {
        OutHeader attackType = ai.attackHeader;
        /*
        REMOTE_MELEE_ATTACK(753),
        REMOTE_SHOOT_ATTACK(754),
        REMOTE_MAGIC_ATTACK(755),
         */
        OutPacket outPacket = new OutPacket(attackType);
        outPacket.encodeInt(chr.getId());

        outPacket.encodeByte(ai.fieldKey);
        outPacket.encodeByte(ai.mobCount << 4 | ai.hits);
        outPacket.encodeInt(chr.getLevel());
        outPacket.encodeByte(ai.slv);
        if(ai.slv > 0) {
            outPacket.encodeInt(ai.skillId);
        }
        if(SkillConstants.isZeroSkill(ai.skillId)) {
            outPacket.encodeByte(ai.zero);
            if(ai.zero != 0) {
                outPacket.encodePosition(chr.getPosition());
            }
        }
        if (ai.skillId == 101110104) {
            return outPacket;
        }
        if(attackType == OutHeader.REMOTE_SHOOT_ATTACK &&
                (SkillConstants.getAdvancedCountHyperSkill(ai.skillId) != 0  ||
                SkillConstants.getAdvancedAttackCountHyperSkill(ai.skillId) != 0 ||
                SkillConstants.isShikigamiHauntingSkill(ai.skillId))){
            outPacket.encodeByte(ai.passiveSLV);
            if(ai.passiveSLV > 0) {
                outPacket.encodeInt(ai.passiveSkillID);
            }
        }
        outPacket.encodeByte(ai.someMask);
        outPacket.encodeByte(ai.buckShot);
        outPacket.encodeInt(ai.option3);
        outPacket.encodeInt(ai.bySummonedID);
        if ((ai.buckShot & 2) != 0) {
            outPacket.encodeInt(ai.buckShotSkillID);
            outPacket.encodeInt(ai.buckShotSlv);
        }
        if ((ai.buckShot & 8) != 0) {
            outPacket.encodeByte(ai.psdTargetPlus);
        }
        byte left = (byte) (ai.left ? 1 : 0);
        outPacket.encodeShort((left << 15) | ai.attackAction);
        if(ai.attackAction <= 1895) {
            outPacket.encodeByte(ai.attackActionType);
            outPacket.encodeShort(ai.x);
            outPacket.encodeShort(ai.y);
            outPacket.encodeByte(ai.showFixedDamage);
            outPacket.encodeByte(!ai.isDragonAttack);
            outPacket.encodeByte(ai.actionSpeed);
            outPacket.encodeByte(ai.mastery);
            outPacket.encodeInt(ai.bulletID);
            for(MobAttackInfo mai : ai.mobAttackInfo) {
                outPacket.encodeInt(mai.mobId);
                if(mai.mobId > 0) {
                    outPacket.encodeByte(mai.byteIdk1);
                    outPacket.encodeByte(mai.byteIdk2);
                    outPacket.encodeByte(mai.byteIdk3);
                    outPacket.encodeShort(mai.byteIdk4);
                    outPacket.encodeInt(0);
                    outPacket.encodeInt(0);
                    if(ai.skillId == 80001835 || ai.skillId == 42111002 || ai.skillId == 80011050) {
                        // Soul Shear
                        outPacket.encodeByte(ai.hits);
                        outPacket.encodeInt(0); // not exactly sure
                    }
                    for(long dmg : mai.damages) {
                        outPacket.encodeByte(0); // isCrit
                        outPacket.encodeLong(dmg);
                    }
                    if(SkillConstants.isKinesisPsychicLockSkill(ai.skillId)) {
                        outPacket.encodeInt(mai.psychicLockInfo);
                    }
                    if(ai.skillId == 37111005) {
                        outPacket.encodeByte(mai.rocketRushInfo);
                    }
                }
            }
            if(ai.skillId == 2321001 || ai.skillId == 2221052 || ai.skillId == 11121052 || ai.skillId == 12121054) {
                outPacket.encodeInt(ai.keyDown);
            } else if(SkillConstants.isSuperNovaSkill(ai.skillId) || SkillConstants.isScreenCenterAttackSkill(ai.skillId) ||
                      ai.skillId == 101000202 || ai.skillId == 101000102 ||
                      SkillConstants.isSupportAttackSkill(ai.skillId) ||
                      ai.skillId == 400041019 ||ai.skillId == 400031016 ||ai.skillId == 400041024 ||
                      SkillConstants.isWingedJavelin(ai.skillId) || ai.skillId == 400021075 || ai.skillId == 80002452) {
                outPacket.encodePositionInt(ai.ptAttackRefPoint);
            }
            if(SkillConstants.isKeydownSkillRectMoveXY(ai.skillId)) {
                outPacket.encodePosition(ai.keyDownRectMoveXY);
            }
            if(ai.skillId == 51121009) {
                outPacket.encodeByte(ai.showFixedDamage);
            }
            if(ai.skillId == 112110003) { // formation attack
                outPacket.encodeInt(0);
            }
            if(ai.skillId == 42100007) { // Soul Bomb
                outPacket.encodeShort(0);
                byte size = 0;
                outPacket.encodeByte(size);
                for (int i = 0; i < size; i++) {
                    outPacket.encodePosition(new Position());
                }
            }
            if(ai.skillId == 21120019 || ai.skillId == 37121052 || SkillConstants.isShadowAssault(ai.skillId) ||
               ai.skillId == 11121014 || ai.skillId == 5101004) {
                outPacket.encodeByte(0);
                outPacket.encodePositionInt(ai.teleportPt);
            }
            if (SkillConstants.isPoisonAttackSkill(ai.skillId)) {
                outPacket.encodePosition(new Position());
            }
            if (SkillConstants.isUNKAttackSkill(ai.skillId)) {
                outPacket.encodeInt(0);
                outPacket.encodeByte(0);
            }
//            if(ai.attackHeader == )
            outPacket.encodeArr(new byte[50]);
        }

        return outPacket;
    }

    public static OutPacket avatarModified(Char chr, byte mask, byte carryItemEffect) {
        AvatarLook al = chr.getAvatarData().getAvatarLook();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();

        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_AVATAR_MODIFIED);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeByte(mask);
        if((mask & AvatarModifiedMask.AvatarLook.getVal()) != 0) {
            al.encode(outPacket);
        }
        if((mask & AvatarModifiedMask.SubAvatarLook.getVal()) != 0) {
            al.encode(outPacket); // subAvatarLook
        }
        if((mask & AvatarModifiedMask.Speed.getVal()) != 0) {
            outPacket.encodeByte(tsm.getOption(CharacterTemporaryStat.Speed).nOption);
        }
        if((mask & AvatarModifiedMask.CarryItemEffect.getVal()) != 0) {
            outPacket.encodeByte(carryItemEffect);
        }
        boolean hasCouple = chr.getCouple() != null;
        outPacket.encodeByte(hasCouple);
        if(hasCouple) {
            chr.getCouple().encodeForRemote(outPacket);
        }
        boolean hasFriendShip = chr.getFriendshipRingRecord() != null;
        outPacket.encodeByte(hasFriendShip);
        if(hasFriendShip) {
            chr.getFriendshipRingRecord().encode(outPacket);
        }
        boolean hasWedding = chr.getMarriageRecord() != null;
        outPacket.encodeByte(hasWedding);
        if(hasWedding) {
            chr.getMarriageRecord().encode(outPacket);
        }
        outPacket.encodeInt(chr.getCompletedSetItemID());
        outPacket.encodeInt(chr.getTotalChuc());
        outPacket.encodeInt(0);

        return outPacket;
    }

    public static OutPacket throwGrenade(int charID, int grenadeID, Position pos, int keyDown, int skillID, int bySummonedID,
                                         int slv, boolean left, int attackSpeed) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_THROW_GRENADE);

        outPacket.encodeInt(charID);

        outPacket.encodeInt(grenadeID);
        outPacket.encodePositionInt(pos);
        outPacket.encodeInt(keyDown);
        outPacket.encodeInt(skillID);
        outPacket.encodeInt(bySummonedID);
        outPacket.encodeInt(slv);
        outPacket.encodeByte(left);
        outPacket.encodeInt(attackSpeed);

        return outPacket;
    }

    public static OutPacket destroyGrenade(int charID, int grenadeID) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_DESTROY_GRENADE);

        outPacket.encodeInt(charID);
        outPacket.encodeInt(grenadeID);

        return outPacket;
    }

    public static OutPacket receiveHP(Char chr) {
        return receiveHP(chr.getId(), chr.getHP(), chr.getTotalStat(BaseStat.mhp));
    }

    public static OutPacket receiveHP(int charID, int curHP, int maxHP) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_RECEIVE_HP);

        outPacket.encodeInt(charID);
        outPacket.encodeInt(curHP);
        outPacket.encodeInt(maxHP);

        return outPacket;
    }

    public static OutPacket hit(Char chr, HitInfo hitInfo) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_HIT);

        outPacket.encodeInt(chr.getId());

        outPacket.encodeByte(hitInfo.type);
        outPacket.encodeInt(hitInfo.hpDamage);
        outPacket.encodeByte(hitInfo.isCrit);
        outPacket.encodeByte(hitInfo.hpDamage == 0);
        if (hitInfo.type == -8) {
            outPacket.encodeInt(hitInfo.skillID);
            outPacket.encodeInt(0); // ignored
            outPacket.encodeInt(hitInfo.otherUserID);
        } else {
            outPacket.encodeInt(hitInfo.mobID);
            outPacket.encodeByte(hitInfo.action);
            outPacket.encodeInt(hitInfo.templateID);

            outPacket.encodeInt(0); // ignored
            outPacket.encodeInt(hitInfo.reflectDamage);
            outPacket.encodeByte(hitInfo.hpDamage == 0); // bGuard
            if (hitInfo.reflectDamage > 0) {
                outPacket.encodeByte(hitInfo.isGuard);
                outPacket.encodeInt(hitInfo.mobID);

                outPacket.encodeByte(hitInfo.hitAction);
                outPacket.encodePosition(chr.getPosition());
            }
            outPacket.encodeByte(hitInfo.specialEffectSkill);
            if ((hitInfo.specialEffectSkill & 1) != 0) {
                outPacket.encodeInt(hitInfo.curStanceSkill);
            }
        }
        outPacket.encodeInt(hitInfo.hpDamage);
        if (hitInfo.hpDamage == -1) {
            outPacket.encodeInt(hitInfo.userSkillID);
        }

        return outPacket;
    }

    public static OutPacket guildNameChanged(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_GUILD_NAME_CHANGED);

        outPacket.encodeInt(chr.getId());

        Guild guild = chr.getGuild();
        if (guild == null) {
            outPacket.encodeString("");
        } else {
            outPacket.encodeString(guild.getName());
        }

        return outPacket;
    }

    public static OutPacket guildMarkChanged(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_GUILD_MARK_CHANGED);

        outPacket.encodeInt(chr.getId());

        Guild guild = chr.getGuild();
        if (guild == null) {
            outPacket.encodeShort(0);
            outPacket.encodeByte(0);
            outPacket.encodeShort(0);
            outPacket.encodeByte(0);
        } else {
            outPacket.encodeShort(guild.getMarkBg());
            outPacket.encodeByte(guild.getMarkBgColor());
            outPacket.encodeShort(guild.getMark());
            outPacket.encodeByte(guild.getMarkColor());
        }

        return outPacket;
    }

    public static OutPacket effect(int id, Effect effect) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_EFFECT);

        outPacket.encodeInt(id);
        effect.encode(outPacket);

        return outPacket;
    }

    public static OutPacket setDefaultWingItem(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_SET_DEFAULT_WING_ITEM);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeInt(chr.getAvatarData().getCharacterStat().getWingItem());

        return outPacket;
    }

    public static OutPacket setTemporaryStat(Char chr, short delay) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_SET_TEMPORARY_STAT);

        outPacket.encodeInt(chr.getId());
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        tsm.encodeForRemote(outPacket, tsm.getNewStats());
        outPacket.encodeShort(delay);
        outPacket.encodeByte(0);
        return outPacket;
    }

    public static OutPacket resetTemporaryStat(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_RESET_TEMPORARY_STAT);

        TemporaryStatManager tsm = chr.getTemporaryStatManager();

        outPacket.encodeInt(chr.getId());
        int[] mask = tsm.getMaskByCollection(tsm.getRemovedStats());
        for (int maskElem : mask) {
            outPacket.encodeInt(maskElem);
        }
        int poseType = 0;
        if (tsm.hasStat(CharacterTemporaryStat.PoseType)) {
            poseType = tsm.getOption(CharacterTemporaryStat.PoseType).bOption;
        }
        outPacket.encodeByte(poseType);
        outPacket.encodeByte(false); // if true, show a ride vehicle effect. Why should this be called on reset tho?

        return outPacket;
    }

    public static OutPacket remoteSetActivePortableChair(int chrId, int itemId, boolean textChair, String text) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_SET_ACTIVE_PORTABLE_CHAIR);

        outPacket.encodeInt(chrId);

        outPacket.encodeInt(itemId);
        outPacket.encodeInt(textChair ? 1 : 0);
        if (textChair) {
            outPacket.encodeString(text);
        }

        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeByte(0);
        int size = 0;
        outPacket.encodeInt(size);
        for (int i = 0; i < size; i++) {
            outPacket.encodeInt(0);
        }
        outPacket.encodeByte(0);
        outPacket.encodeLong(0);
        outPacket.encodeByte(0);


        return outPacket;
    }

    public static OutPacket skillPrepare(Char chr, int skillId, byte slv) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_SKILL_PREPARE);

        outPacket.encodeInt(chr.getId());

        outPacket.encodeInt(skillId);
        outPacket.encodeByte(slv);

        outPacket.encodeShort(0); // unknown

        outPacket.encodeByte(7); // action Speed
        outPacket.encodePosition(chr.getPosition());

        return outPacket;
    }

    public static OutPacket skillCancel(int chrId, int skillId) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_SKILL_CANCEL);

        outPacket.encodeInt(chrId);
        outPacket.encodeInt(skillId);

        return outPacket;
    }
}
