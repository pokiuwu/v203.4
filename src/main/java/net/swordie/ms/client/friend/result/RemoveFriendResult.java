package net.swordie.ms.client.friend.result;

import net.swordie.ms.client.friend.Friend;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.client.friend.FriendType;

/**
 * Created on 3/31/2018.
 */
public class RemoveFriendResult implements FriendResult {

    private Friend friend;

    public RemoveFriendResult(Friend friend) {
        this.friend = friend;
    }

    @Override
    public FriendType getType() {
        return FriendType.FriendRes_DeleteFriend_Done;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(friend.isAccount());
        if(friend.isAccount()) {
            outPacket.encodeInt(friend.getFriendAccountID());
        } else {
            outPacket.encodeInt(friend.getFriendID());
        }
    }
}
