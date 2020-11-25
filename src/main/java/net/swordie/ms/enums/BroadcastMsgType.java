package net.swordie.ms.enums;

/**
 * Created by Asura on 16-6-2018.
 */
public enum BroadcastMsgType {
    // Types
    TYPE_ALL(0),
    TYPE_CLONE(1),
    TYPE_MAP(2),
    // Messages
    NOTICE(0),
    ALERT(1),
    SPEAKER_CHANNEL(2),
    SPEAKER_WORLD(3),
    SLIDE(4),
    EVENT(5),
    NOTICE_WITH_OUT_PREFIX(6),
    UTIL_DLG_EX(7),
    ITEM_SPEAKER(8), // Holds item info
    SPEAKER_BRIDGE(9),
    ART_SPEAKER_WORLD(10),
    GACHAPON_MSG(11), //  item shown when clicked,  does hold Item info
    BLOW_WEATHER(12), // TODO  Contains PackedCharLook
    ANNOUNCED_QUEST_OPEN(13), // tries to auto start quests with the announcement (?) - probably the  "A quest has arrived! Please clock on the icon at the botfom of your screen."
    ANNOUNCED_QUEST_CLOSED(14), // tries to auto start quests (?)
    MIRACLE_TIME(15), // Repeats the string 3x on the same line
    EVENT_MSG_WITH_CHANNEL(16), // May be for  /find ?
    LOTTERY_ITEM_SPEAKER(17), // Holds item info
    LOTTERY_ITEM_SPEAKER_WORLD(18),
    MONSTER_LIFE_WORLD_MSG(19),
    NOTICE_WINDOW(20),
    PICKUP_ITEM_WORLD(21), // Holds item info
    MAKING_SKILL_MEISTER_ITEM(22),
    SPEAKER_WORLD_GUILD_SKILL(23),
    WEATHER_MSG(24), // TODO   Contains PackedCharLook
    UNK25(25),
    UNK26(26),
    UNK27(27),
    UNK28(28),
    UNK29(29),
    UNK30(30),
    UNK31(31),
    UNK32(32),
    UNK33(33),
    UNK34(34),
    UNK35(35),
    UNK36(36);
    private byte val;

    BroadcastMsgType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
