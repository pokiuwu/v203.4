package net.swordie.ms.connection.packet;

import net.swordie.ms.Server;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.Gr2dAniType;
import net.swordie.ms.enums.SpineMsgType;
import net.swordie.ms.handlers.header.OutHeader;

/**
 * Created by MechAviv on 2/27/2019.
 */
public class MapLoadable {
    // TODO: handle it with the correct strcture&naming
    public static OutPacket setMapTaggedObjectVisible() {
        OutPacket outPacket = new OutPacket(OutHeader.SET_MAP_TAGGED_OBJECT_VISISBLE);

        outPacket.encodeByte(0);// probably count lel
        outPacket.encodeString("2018mapleTree");//(Server.getInstance().getBackground());// tag (Temporary static)
        outPacket.encodeByte(1);// visible
        outPacket.encodeInt(0);// manual
        outPacket.encodeInt(0);// delay
        return outPacket;
    }

    public static OutPacket setMapTaggedObjectVisible(String tagName, boolean visible, int manual, int delay) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_MAP_TAGGED_OBJECT_VISISBLE);

        outPacket.encodeByte(1);// count

        outPacket.encodeString(tagName);
        outPacket.encodeByte(visible);
        outPacket.encodeInt(manual);
        outPacket.encodeInt(delay);

        return outPacket;
    }

    public static OutPacket setSpineObjectEffectAlpha(boolean back, String key, int alpha, int delay) {
        OutPacket outPacket = new OutPacket(back ? OutHeader.SET_SPINE_BACK_EFFECT : OutHeader.SET_SPINE_OBJECT_EFFECT);

        outPacket.encodeString(key);
        outPacket.encodeInt(SpineMsgType.ALPHA.getVal());
        outPacket.encodeInt(alpha);
        outPacket.encodeInt(delay);

        return outPacket;
    }

    public static OutPacket setSpineObjectEffectPlay(boolean back, String key, String name, boolean loop, boolean randomStart) {
        OutPacket outPacket = new OutPacket(back ? OutHeader.SET_SPINE_BACK_EFFECT : OutHeader.SET_SPINE_OBJECT_EFFECT);

        outPacket.encodeString(key);
        outPacket.encodeInt(SpineMsgType.PLAY.getVal());
        outPacket.encodeString(name);
        outPacket.encodeByte(loop);
        outPacket.encodeByte(randomStart);

        return outPacket;
    }

    public static OutPacket setSpineObjectEffectAddPlay(boolean back, String key, String name, boolean loop) {
        OutPacket outPacket = new OutPacket(back ? OutHeader.SET_SPINE_BACK_EFFECT : OutHeader.SET_SPINE_OBJECT_EFFECT);

        outPacket.encodeString(key);
        outPacket.encodeInt(SpineMsgType.ADD_PLAY.getVal());
        outPacket.encodeString(name);
        outPacket.encodeByte(loop);

        return outPacket;
    }

    public static OutPacket setSpineObjectEffectClearTracks(boolean back, String key, boolean setupPose) {
        OutPacket outPacket = new OutPacket(back ? OutHeader.SET_SPINE_BACK_EFFECT : OutHeader.SET_SPINE_OBJECT_EFFECT);

        outPacket.encodeString(key);
        outPacket.encodeInt(SpineMsgType.CLEAR_TRACKS.getVal());
        outPacket.encodeByte(setupPose);

        return outPacket;
    }

    public static OutPacket setSpineObjectEffectPlayrate(boolean back, String key, int scale) {
        OutPacket outPacket = new OutPacket(back ? OutHeader.SET_SPINE_BACK_EFFECT : OutHeader.SET_SPINE_OBJECT_EFFECT);

        outPacket.encodeString(key);
        outPacket.encodeInt(SpineMsgType.PLAYRATE.getVal());
        outPacket.encodeInt(scale);

        return outPacket;
    }

    public static OutPacket setSpineObjectEffectStop(boolean back, String key, boolean setupPose) {
        OutPacket outPacket = new OutPacket(back ? OutHeader.SET_SPINE_BACK_EFFECT : OutHeader.SET_SPINE_OBJECT_EFFECT);

        outPacket.encodeString(key);
        outPacket.encodeInt(SpineMsgType.STOP.getVal());
        outPacket.encodeByte(setupPose);

        return outPacket;
    }

    public static OutPacket setMapTaggedObjectAnimation(String tagName, int type) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_MAP_TAGGED_OBJECT_ANIMATION);

        outPacket.encodeInt(1);
        outPacket.encodeString(tagName);
        outPacket.encodeInt(type);// Gr2dAniType

        return outPacket;
    }
}
