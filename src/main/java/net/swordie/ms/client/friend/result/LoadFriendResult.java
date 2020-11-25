package net.swordie.ms.client.friend.result;

import net.swordie.ms.client.friend.Friend;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.client.friend.FriendType;

import java.util.Set;

/**
 * Created on 3/31/2018.
 */
public class LoadFriendResult implements FriendResult {

    private Set<Friend> friends;

    public LoadFriendResult(Set<Friend> friends) {
        this.friends = friends;
    }

    @Override
    public FriendType getType() {
        return FriendType.FriendRes_LoadFriend_Done;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(friends.size());
        for(Friend f : friends) {
            f.encode(outPacket);
        }
    }
}
