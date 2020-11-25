package net.swordie.ms.connection.packet;

import net.swordie.ms.client.character.avatar.AvatarLook;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.enums.DelayEffect;
import net.swordie.ms.life.pet.Pet;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.guild.Guild;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.TSIndex;
import net.swordie.ms.handlers.header.OutHeader;

/**
 * Created on 3/18/2018.
 */
public class UserPool {
    public static OutPacket userEnterField(Char chr) {
        CharacterStat cs = chr.getAvatarData().getCharacterStat();
        AvatarLook al = chr.getAvatarData().getAvatarLook();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        OutPacket outPacket = new OutPacket(OutHeader.USER_ENTER_FIELD);

        outPacket.encodeInt(chr.getId());

        outPacket.encodeInt(chr.getLevel());
        outPacket.encodeString(chr.getName());
        outPacket.encodeString(""); // parent name, deprecated
        if(chr.getGuild() != null) {
            chr.getGuild().encodeForRemote(outPacket);
        } else {
            Guild.defaultEncodeForRemote(outPacket);
        }
        outPacket.encodeByte(cs.getGender());

        outPacket.encodeInt(cs.getPop());
        outPacket.encodeInt(10); // nFarmLevel
        outPacket.encodeInt(0); // nNameTagMark
        outPacket.encodeByte(0); //idk
        outPacket.encodeInt(0);// Unknown
        tsm.encodeForRemote(outPacket, tsm.getCurrentStats());
        outPacket.encodeShort(chr.getJob());
        outPacket.encodeShort(cs.getSubJob());
        outPacket.encodeInt(chr.getTotalChuc());
        outPacket.encodeInt(0);
        al.encode(outPacket);
        if(JobConstants.isZero(chr.getJob())) {
            chr.getAvatarData().getZeroAvatarLook().encode(outPacket);
        }
        outPacket.encodeInt(chr.getDriverID());
        outPacket.encodeInt(chr.getPassengerID()); // dwPassenserID
        // new 176: sub_191E2D0
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        int size = 0;
        outPacket.encodeInt(size);
        for (int i = 0; i < size; i++) {
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
        }
        // end sub_191E2D0
        outPacket.encodeInt(chr.getChocoCount());
        outPacket.encodeInt(chr.getActiveEffectItemID());
        outPacket.encodeInt(chr.getMonkeyEffectItemID());
        outPacket.encodeInt(chr.getActiveNickItemID());
        boolean hasString = false;
        outPacket.encodeByte(hasString);
        if (hasString) {
            outPacket.encodeString("");
        }
        outPacket.encodeInt(chr.getDamageSkin().getDamageSkinID());
        outPacket.encodeInt(0); // ptPos.x?
        outPacket.encodeInt(al.getDemonWingID());
        outPacket.encodeInt(al.getKaiserWingID());
        outPacket.encodeInt(al.getKaiserTailID());
        outPacket.encodeInt(chr.getCompletedSetItemID());
        outPacket.encodeShort(chr.getFieldSeatID());
        outPacket.encodeInt(chr.getPortableChairID());
        boolean hasPortableChairMsg = chr.getPortableChairMsg() != null;
        outPacket.encodeByte(hasPortableChairMsg);
        if(hasPortableChairMsg) {
            outPacket.encodeString(chr.getPortableChairMsg());
        }
        outPacket.encodeByte(0);// boolean => long
        if (false) {
            outPacket.encodeLong(0);
        }
        int towerIDSize = 0;
        outPacket.encodeInt(towerIDSize);
        for (int i = 0; i < towerIDSize; i++) {
            outPacket.encodeInt(0); // towerChairID
        }
        outPacket.encodeInt(0); // some other position? new
        outPacket.encodeInt(0); // some other position? new
        outPacket.encodeByte(0);
        outPacket.encodeByte(0);

        outPacket.encodePosition(chr.getPosition());
        outPacket.encodeByte(chr.getMoveAction());
        outPacket.encodeShort(chr.getFoothold());

        outPacket.encodeByte(1); // ? new

        outPacket.encodeByte(0); //if true encode something virtualized

        for(Pet pet : chr.getPets()) {
            if(pet.getId() == 0) {
                continue;
            }
            outPacket.encodeByte(1);
            outPacket.encodeInt(pet.getIdx());
            pet.encode(outPacket);
        }
        outPacket.encodeByte(0); // indicating that pets are no longer being encoded

        outPacket.encodeByte(0); // if true, encode something. idk what (v4->vfptr[35].Update)(v4, iPacket);
        outPacket.encodeByte(0); // loop size, encode byte each iteration



        //outPacket.encodeByte(chr.getMechanicHue());
        outPacket.encodeInt(chr.getTamingMobLevel());
        outPacket.encodeInt(chr.getTamingMobExp());
        outPacket.encodeInt(chr.getTamingMobFatigue());

        outPacket.encodeByte(0);//unknown

        byte miniRoomType = chr.getMiniRoom() != null ? chr.getMiniRoom().getType() : 0;
        outPacket.encodeByte(miniRoomType);
        if(miniRoomType > 0) {
            chr.getMiniRoom().encode(outPacket);
        }

        outPacket.encodeByte(chr.getADBoardRemoteMsg() != null);
        if (chr.getADBoardRemoteMsg() != null) {
            outPacket.encodeString(chr.getADBoardRemoteMsg());
        }

        outPacket.encodeByte(chr.isInCouple());
        if(chr.isInCouple()) {
            chr.getCouple().encodeForRemote(outPacket);
        }

        outPacket.encodeByte(chr.hasFriendshipItem());
        if(chr.hasFriendshipItem()) {
            chr.getFriendshipRingRecord().encode(outPacket);
        }

        outPacket.encodeByte(chr.isMarried());
        if(chr.isMarried()) {
            chr.getMarriageRecord().encodeForRemote(outPacket);
        }

        DelayEffect delayEffect = chr.getDelayEffect();
        outPacket.encodeByte(delayEffect.getVal());
        switch (delayEffect) {
            case PVP_RAGE:
            case PVP_CHAMPION:
            case UNK_20:
                int delayedTime = 0;
                outPacket.encodeInt(delayedTime);
                break;
        }
        outPacket.encodeInt(chr.getEvanDragonGlide());
        if(JobConstants.isKaiser(chr.getJob())) {
            outPacket.encodeInt(chr.getKaiserMorphRotateHueExtern());
            outPacket.encodeInt(chr.getKaiserMorphPrimiumBlack());
            outPacket.encodeByte(chr.getKaiserMorphRotateHueInnner());
        }
        outPacket.encodeInt(chr.getMakingMeisterSkillEff());
        chr.getFarmUserInfo().encode(outPacket);
        for (int i = 0; i < 5; i++) {
            outPacket.encodeByte(-1); // activeEventNameTag
        }
        outPacket.encodeInt(chr.getCustomizeEffect());
        if(chr.getCustomizeEffect() > 0) {
            outPacket.encodeString(chr.getCustomizeEffectMsg());
        }
        outPacket.encodeByte(chr.getSoulEffect());
        if(tsm.hasStat(CharacterTemporaryStat.RideVehicle)) {
            int vehicleID = tsm.getTSBByTSIndex(TSIndex.RideVehicle).getNOption();
            if(vehicleID == 1932249) { // is_mix_vehicle
                size = 0;
                outPacket.encodeInt(size); // ???
                for (int i = 0; i < size; i++) {
                    outPacket.encodeInt(0);
                }
            }
        }
        /*
         Flashfire (12101025) info
         not really interested in encoding this
         structure is:
         if(bool)
            if(bool)
                slv = int
                notused = int
                x = short
                y = short
         */
        outPacket.encodeByte(0);
        outPacket.encodeByte(0); // StarPlanetRank::Decode
        // CUser::DecodeStarPlanetTrendShopLook not interesting, will break REMOTE_AVATAR_MODIFIED if 1st int is != 0
        outPacket.encodeInt(0);
        // ~CUser::DecodeStarPlanetTrendShopLook
        //outPacket.encodeInt(0); // CUser::DecodeTextEquipInfo
        outPacket.encodeInt(0);
        chr.getFreezeHotEventInfo().encode(outPacket);
        outPacket.encodeInt(chr.getEventBestFriendAID());
        outPacket.encodeByte(tsm.hasStat(CharacterTemporaryStat.KinesisPsychicEnergeShield));
        outPacket.encodeByte(1);
        outPacket.encodeByte(1);
        outPacket.encodeByte(0);
        outPacket.encodeByte(chr.isBeastFormWingOn());
        outPacket.encodeInt(chr.getMesoChairCount());
        // end kmst
        outPacket.encodeInt(0);
        outPacket.encodeByte(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeString("");
        outPacket.encodeInt(0);
        boolean bool = false;
        outPacket.encodeByte(bool);
        if(bool) {
            size = 0;
            outPacket.encodeInt(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeInt(0);
            }
        }
        int someID = 0;
        outPacket.encodeInt(someID);
        if(someID > 0) {
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeShort(0);
            outPacket.encodeShort(0);
        }
        outPacket.encodeInt(0);
        // start sub_16D99C0
        size = 0;
        outPacket.encodeInt(size);
        for (int i = 0; i < size; i++) {
            outPacket.encodeInt(0);
        }
        // end sub_16D99C0
        return outPacket;
    }

    public static OutPacket userLeaveField(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.USER_LEAVE_FIELD);

        outPacket.encodeInt(chr.getId());

        return outPacket;
    }
}
