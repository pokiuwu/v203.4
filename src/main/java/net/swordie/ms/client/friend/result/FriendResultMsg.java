package net.swordie.ms.client.friend.result;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.client.friend.FriendType;

/**
 * Created on 3/31/2018.
 */
public class FriendResultMsg implements FriendResult {

    private FriendType type;
    private String name;

    public FriendResultMsg(FriendType type) {
        this.type = type;
    }

    public FriendResultMsg(FriendType type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public FriendType getType() {
        return type;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeString(name);
    }
}
