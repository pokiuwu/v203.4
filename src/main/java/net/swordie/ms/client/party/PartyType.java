package net.swordie.ms.client.party;

import java.util.Arrays;

/**
 * Created on 3/19/2018.
 */
public enum PartyType {
    PartyReq_LoadParty(0),
    PartyReq_CreateNewParty(1),
    PartyReq_WithdrawParty(2),
    PartyReq_JoinParty(3),
    PartyReq_InviteParty(4),
    PartyReq_InviteIntrusion(5), // member -> party request
    PartyReq_KickParty(6), // party -> member request
    PartyReq_ChangePartyBoss(7),
    PartyReq_ApplyParty(8),
    PartyReq_SetAppliable(9),
    PartyReq_ClearIntrusion(10),
    PartyReq_CreateNewParty_Group(11),
    PartyReq_JoinParty_Group(12),
    PartyReq_PartySetting(13),
    PartyReq_LoadStarPlanetPoint(14),

    PartyRes_LoadParty_Done(19),// v202.3
    PartyRes_CreateNewParty_Done(20),// v202.3
    PartyRes_CreateNewParty_AlreayJoined(21),// v202.3
    PartyRes_CreateNewParty_Beginner(22),// v202.3
    PartyRes_CreateNewParty_Unknown(19),
    PartyRes_CreateNewParty_byNonBoss(20),

    PartyRes_WithdrawParty_Done(25),// v202.3
    PartyRes_WithdrawParty_NotJoined(26),// v202.3
    PartyRes_WithdrawParty_Unknown(23),

    PartyRes_JoinParty_Done(28),// v202.3
    PartyRes_JoinParty_Done2(29),// v202.3 // join msg

    PartyRes_JoinParty_AlreadyJoined(30),// v202.3
    PartyRes_JoinParty_AlreadyFull(31),// v202.3
    PartyRes_JoinParty_OverDesiredSize(32),// v202.3
    PartyRes_JoinParty_UnknownUser(33),
    PartyRes_JoinParty_Unknown(34),
    PartyRes_JoinParty_FieldLimit(35),// v202.3

    PartyRes_JoinIntrusion_Done(36),// v202.3
    PartyRes_JoinIntrusion_UnknownParty(37),// v202.3

    PartyRes_InviteParty_Sent(38),// v202.3
    PartyRes_InviteParty_BlockedUser(39),// v202.3
    PartyRes_InviteParty_AlreadyInvited(40),// v202.3
    PartyRes_InviteParty_AlreadyInvitedByInviter(41),// v202.3
    PartyRes_InviteParty_Rejected(42),// v202.3
    PartyRes_InviteParty_Accepted(43),// v202.3
    PartyRes_InviteParty_FieldLimit(44),// v202.3

    PartyRes_InviteIntrusion_Sent(45),// v202.3
    PartyRes_InviteIntrusion_BlockedUser(46),// v202.3
    PartyRes_InviteIntrusion_AlreadyInvited(47),// v202.3
    PartyRes_InviteIntrusion_AlreadyInvitedByInviter(48),// v202.3
    PartyRes_InviteIntrusion_Rejected(49),// v202.3
    PartyRes_InviteIntrusion_Accepted(50),// v202.3

    PartyRes_KickParty_Done(51),// v202.3
    PartyRes_KickParty_FieldLimit(52),// v202.3
    PartyRes_KickParty_Unknown(53),// v202.3
    PartyRes_KickParty_TimeLimit(54),// v202.3

    PartyRes_ChangePartyBoss_Done(55),// v202.3
    PartyRes_ChangePartyBoss_NotSameField(56),// v202.3
    PartyRes_ChangePartyBoss_NoMemberInSameField(57),// v202.3
    PartyRes_ChangePartyBoss_NotSameChannel(58),// v202.3
    PartyRes_ChangePartyBoss_Unknown(59),// v202.3

    PartyRes_AdminCannotCreate(60),// v202.3
    PartyRes_AdminCannotInvite(61),// v202.3

    PartyRes_InAnotherWorld(62),// v202.3
    PartyRes_InAnotherChanelBlockedUser(63),// v202.3

    PartyRes_UserMigration(64),// v202.3
    PartyRes_ChangeLevelOrJob(65),// v202.3
    PartyRes_UpdateShutdownStatus(66),// v202.3
    PartyRes_Unknown(67),// v202.3
    PartyRes_SetAppliable(68),// v202.3
    PartyRes_SetAppliableFailed(69),// v202.3
    PartyRes_SuccessToSelectPQReward(70),// v202.3
    PartyRes_FailToSelectPQReward(71),// v202.3
    PartyRes_ReceivePQReward(72),// v202.3
    PartyRes_FailToRequestPQReward(73),// v202.3
    PartyRes_CanNotInThisField(74),// v202.3

    PartyRes_ApplyParty_Sent(75),// v202.3
    PartyRes_ApplyParty_UnknownParty(76),// v202.3
    PartyRes_ApplyParty_BlockedUser(77),// v202.3
    PartyRes_ApplyParty_AlreadyApplied(78),// v202.3
    PartyRes_ApplyParty_AlreadyAppliedByApplier(79),// v202.3
    PartyRes_ApplyParty_AlreadyFull(80),// v202.3
    PartyRes_ApplyParty_Rejected(81),// v202.3
    PartyRes_ApplyParty_Accepted(82),// v202.3

    PartyRes_FoundPossibleMember(83),// v202.3
    PartyRes_FoundPossibleParty(84),// v202.3

    PartyRes_PartySettingDone(85),// v202.3
    PartyRes_Load_StarGrade_Result(86),// v202.3
    PartyRes_Load_StarGrade_Result2(87),// v202.3
    PartyRes_Member_Rename(88),// v202.3
    PartyRes_Unknown_1(89),// v202.3
    PartyRes_Unknown_2(90),// v202.3
    PartyRes_BFFLimit(91),// v202.3
    PartyRes_Unknown_3(92),// v202.3
    PartyRes_Unknown_4(93),// v202.3
    PartyInfo_TownPortalChanged(94),// v202.3
    PartyInfo_OpenGate(95),// v202.3

    ExpeditionReq_Load(96),// v202.3
    ExpeditionReq_CreateNew(97),// v202.3
    ExpeditionReq_Invite(98),// v202.3
    ExpeditionReq_ResponseInvite(99),// v202.3
    ExpeditionReq_Withdraw(100),// v202.3
    ExpeditionReq_Kick(101),// v202.3
    ExpeditionReq_ChangeMaster(102),// v202.3
    ExpeditionReq_ChangePartyBoss(103),// v202.3
    ExpeditionReq_RelocateMember(104),// v202.3

    ExpeditionNoti_Load_Done(105),// v202.3
    ExpeditionNoti_Load_Fail(106),// v202.3
    ExpeditionNoti_CreateNew_Done(107),// v202.3
    ExpeditionNoti_Join_Done(108),// v202.3
    ExpeditionNoti_You_Joined(109),// v202.3
    ExpeditionNoti_You_Joined2(110),// v202.3
    ExpeditionNoti_Join_Fail(111),// v202.3
    ExpeditionNoti_Withdraw_Done(112),// v202.3
    ExpeditionNoti_You_Withdrew(113),// v202.3
    ExpeditionNoti_Kick_Done(114),// v202.3
    ExpeditionNoti_You_Kicked(115),// v202.3
    ExpeditionNoti_Removed(116),// v202.3
    ExpeditionNoti_MasterChanged(117),// v202.3
    ExpeditionNoti_Modified(118),// v202.3
    ExpeditionNoti_Modified2(119),// v202.3
    ExpeditionNoti_Invite(120),// v202.3
    ExpeditionNoti_ResponseInvite(121),// v202.3
    ExpeditionNoti_Create_Fail_By_Over_Weekly_Counter(122),// v202.3
    ExpeditionNoti_Invite_Fail_By_Over_Weekly_Counter(123),// v202.3
    ExpeditionNoti_Apply_Fail_By_Over_Weekly_Counter(124),// v202.3
    ExpeditionNoti_Invite_Fail_By_Blocked_Behavior(125),

    AdverNoti_LoadDone(126),// v202.3
    AdverNoti_Change(127),// v202.3
    AdverNoti_Remove(128),// v202.3
    AdverNoti_GetAll(129),// v202.3
    AdverNoti_Apply(130),// v202.3
    AdverNoti_ResultApply(131),// v202.3
    AdverNoti_AddFail(132),// v202.3
    AdverReq_Add(133),// v202.3
    AdverReq_Remove(134),// v202.3
    AdverReq_GetAll(135),// v202.3
    AdverReq_RemoveUserFromNotiList(136),// v202.3
    AdverReq_Apply(137),// v202.3
    AdverReq_ResultApply(138),// v202.3

    ;

    private byte val;

    PartyType(int val) {
        this.val = (byte) val;
    }

    public static PartyType getByVal(byte type) {
        return Arrays.stream(values()).filter(i -> i.getVal() == type).findFirst().orElse(null);
    }

    public byte getVal() {
        return val;
    }
}
