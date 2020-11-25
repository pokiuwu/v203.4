package net.swordie.ms.enums;

/**
 * Created on 1/2/2018.
 */
public enum ChatType {
    Normal(0),
    Whisper(1),
    GroupParty(2),
    GroupFriend(3),
    GroupGuild(4),
    GroupAlliance(5),
    GameDesc(6),
    Tip(7),
    Notice(8),
    Notice2(9),
    AdminChat(10),
    SystemNotice(11),
    SpeakerChannel(12),
    SpeakerWorld(13),
    SpeakerWorldGuildSkill(14),
    ItemSpeaker(15),
    ItemSpeakerItem(16),
    SpeakerBridge(17),
    SpeakerWorldExPreview(18),
    Mob(19),
    Expedition(20),
    ItemMessage(21),
    MiracleTime(22),
    LotteryItemSpeaker(23),
    LotteryItemSpeakerWorld(24),
    AvatarMegaphone(25),
    PickupSpeakerWorld(26),
    WorldName(27),
    BossArenaNotice(28),
    Claim(29),
    AfreecaTv(30),
    // non kmst from here
    GachaReward(31),
    GachaRed(32),
    GachaRed2(33), // same as GachaRed(32)
    DarkBlue2(34), // same as ItemSpeakerItem(16)
    ItemNoItemSmegaDarkText(35),
    WhiteOnGreen(36),
    CakeSpeaker(37),
    PieSpeaker(38),
    BlackOnWhite(39),
    ;
    private short val;

    ChatType(short val) {
        this.val = val;
    }

    ChatType(int i) {
        this((short) i);
    }

    public short getVal() {
        return val;
    }
}
