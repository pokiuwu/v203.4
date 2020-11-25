package net.swordie.ms.client.friend;

import net.swordie.ms.connection.OutPacket; /**
 * Created on 3/18/2018.
 */
public class FriendshipRingRecord {
    private long friendshipItemSN;
    private long friendshipPairItemSN;
    private int itemID;

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(1); // can be more of the following 3 properties
        outPacket.encodeLong(getFriendshipItemSN());
        outPacket.encodeLong(getFriendshipPairItemSN());
        outPacket.encodeInt(getItemID());
    }

    public long getFriendshipItemSN() {
        return friendshipItemSN;
    }

    public void setFriendshipItemSN(long friendshipItemSN) {
        this.friendshipItemSN = friendshipItemSN;
    }

    public long getFriendshipPairItemSN() {
        return friendshipPairItemSN;
    }

    public void setFriendshipPairItemSN(long friendshipPairItemSN) {
        this.friendshipPairItemSN = friendshipPairItemSN;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
}
