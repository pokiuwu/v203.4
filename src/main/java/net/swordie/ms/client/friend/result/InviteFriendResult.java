package net.swordie.ms.client.friend.result;

import net.swordie.ms.client.friend.Friend;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.client.friend.FriendType;

/**
 * Created on 3/31/2018.
 */
public class InviteFriendResult implements FriendResult {
    private boolean isAccountFriend;
    private int level;
    private int job;
    private int subJob;
    private Friend friend;

    public InviteFriendResult(Friend friend, boolean isAccountFriend, int level, int job, int subJob) {
        this.friend = friend;
        this.isAccountFriend = isAccountFriend;
        this.level = level;
        this.job = job;
        this.subJob = subJob;
    }

    @Override
    public FriendType getType() {
        return FriendType.FriendRes_Invite;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(isAccountFriend);
        outPacket.encodeInt(friend.getFriendID());
        outPacket.encodeInt(friend.getFriendAccountID());
        outPacket.encodeString(friend.getName());
        outPacket.encodeInt(level);
        outPacket.encodeInt(job);
        outPacket.encodeInt(subJob);
        friend.encode(outPacket);
    }
}
