package net.swordie.ms.client.friend.result;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.client.friend.FriendType;

/**
 * Created on 3/31/2018.
 */
public interface FriendResult {

    FriendType getType();

    void encode(OutPacket outPacket);
}
