package net.swordie.ms.enums;

import net.swordie.ms.util.Util;

import java.util.Arrays;

/**
 * Created by MechAviv on 30/11/2018.
 */
public enum AdminCommandType {
    // not server handled commands or unknown types:
    /**
     *
     * /setmobact - [Mob ID], [Action] (Client Side) TODO: check actions for this
     * /honorinfo - [No Args] (Client Side)
     * /pg - no idea what it does
     * /scriptrun - only log
     * /mrh - only log
     * /setGaugeMax - only log
     * /pos - sending GET_CHARACTER_POSITION to server
     * /setheadnotice - only log
     * /attackboss - only log
     * /uitest - [SHORT UI ID]
     * /m, /map - USER_TRANSFER_FIELD_REQUEST with specific map [01 00 E1 F5 05 B4 E2 EE 05 00 00 00 00 00]
     * /worldnotice - only log
     * /showcodeinfo - client side shows id for items/maps/quests
     * /getservertime - sends GET_SERVER_TIME to server
     * /flag - only log
     * /ch - change the channel for u.
     * /c - whishper ?
     * /alertall - sending BROADCAST_MSG to server.
     * /slidebrg - sending BROADCAST_MSG to server.
     * /slidebx - sending BROADCAST_MSG to server.
     * /slideall - sending BROADCAST_MSG to server.
     * /slideallx - sending BROADCAST_MSG to server.
     * /slidech - sending BROADCAST_MSG to server.
     * /slidechx - sending BROADCAST_MSG to server.
     * /slidemap - sending BROADCAST_MSG to server.
     * /slidemapx - sending BROADCAST_MSG to server.
     * /slideax - sending BROADCAST_MSG+SLIDE_REQUEST to server.
     * /sethitavoidperiod - client side god mod :P
     * /releaseMemory
     * /reloadMemory
     * /showmobattack
     * /showmobskill
     * /showpolygon
     * /showboundingbox
     * /noticeall - sending BROADCAST_MSG to server.
     * /alertch - sending BROADCAST_MSG to server.
     * /noticech - sending BROADCAST_MSG to server.
     * /alertmap - sending BROADCAST_MSG to server.
     * /alertm - sending BROADCAST_MSG to server.
     * /noticemap - sending BROADCAST_MSG to server.
     * /level1 - giving to you level 1 monster sack.
     * /level2 - giving to you level 2 monster sack.
     * /level3 - giving to you level 3 monster sack.
     * /level4 - giving to you level 4 monster sack.
     * /level5 - giving to you level 5 monster sack.
     * /level6 - giving to you level 6 monster sack.
     * /level7 - giving to you level 7 monster sack.
     * /level8 - giving to you level 8 monster sack.
     * /package1 - giving to you level 1~3 monster sack.
     * /package2 - giving to you level 4~7 monster sack.
     * /noticehe - sending BROADCAST_MSG to server.(ATTENTION! This is Athena Pierce! A horde of monsters is headed for Henesys! All brave warriors are ordered to Henesys immediately! Let the battle begin!)
     * /noticeel - sending BROADCAST_MSG to server.(ATTENTION! This is Grendel the Really Old! A horde of monsters is headed for Ellinia! All brave warriors are ordered to Ellinia immediately! We shall prevail!)
     * /noticepe - sending BROADCAST_MSG to server.(ATTENTION! This is Dances with Balrog! A horde of monsters is headed for Perion! All brave warriors are ordered to Perion immediately! May your sword be as strong as your will!)
     * /noticekc - sending BROADCAST_MSG to server.(ATTENTION! This is the Dark Lord! A horde of monsters is headed for Kerning City! All brave warriors are ordered to Kerning City immediately! Let us teach our enemies the folly of their actions!)
     * /noticelh - sending BROADCAST_MSG to server.(ATTENTION! This is Vikin! A horde of monsters is headed for Lith Harbor! All brave warriors are ordered to Lith Harbor immediately! It's battle time!)
     * /noticeor - sending BROADCAST_MSG to server.(ATTENTION! This is Ericson! A horde of monsters is headed for Orbis! All brave warriors are ordered to Orbis immediately! Fight hard, fight well!)
     * /noticelu - sending BROADCAST_MSG to server.(ATTENTION! This is Marcel! A horde of monsters is headed for Ludibrium! All brave warriors are ordered to Ludibrium immediately! Show these punks we don't toy around!)
     * /noticefo - sending BROADCAST_MSG to server.(ATTENTION! This is a distress call! A horde of monsters is headed for Folk Town! All brave warriors are ordered to Folk Town immediately! May the spirit of the Maple Tree guide your weapon!)
     * /startnotice - sending BROADCAST_MSG to server.(The monsters have begun a ruthless assault! Stand your ground, hold the line and protect the town!)
     * /endnotice - sending BROADCAST_MSG to server.(Excellent work! The town has withstood the invasion of the monsters unscathed, thanks to your superior battle skills. If you're the person that collected "The Force of Darkness," please head to the Maple Administrator located near you. We thank you again!)
     * /start - sending COCONUT_HIT ?
     * /open - opening event ? (enabling portal join00).
     * /showtooltip - showing item info
     * /setheadnotice - not sure what it does, but it client side.
     * /attackboss - not sure what it does, but it client side.
     * /grayfield - client side
     * /resetgrayfield - client side
     * /usertimertest - ?
     * /cleartemplayer - client side
     * /lightness - ?
     * /lightnessoff - ?
     * /torchtest - ?
     * /torchtest2 - ?
     * /worldtransfer - sending WORLD_TRANSFER_REQUEST to server.
     * /worldtransferS - sending WORLD_TRANSFER_REQUEST to server.
     * /mobphase - showing mob phase
     * /rwgetbarrier
     * /demiantree - sends mob skill command
     * /setobjvisiblelevel - ?
     * /incobjvisiblelevel - ?
     * /decobjvisiblelevel - ?
     * /cpsview - ?
     * /backspeed - ?
     * /mvpgrade - showing your mvp grade.
     * /mvpgrade_yes - ?
     * /mvp_clear_dp - ?
     * /mvpinfo - showing extra mvp info.
     * /reqMCreward - sending MONSTER_COLLECTION_COMPLETE_REWARD_REQ to server.
     * /yoyolog
     * /loadcmd
     * /tenthInvite
     * /mYutInvite
     * /smYutInvite
     * /bingomulti
     * /texturememory
     * /getnxrecord
     * /saveJournal - sending JOURNAL_AVATAR_REQUEST to server.
     * /loadJournal - sending JOURNAL_AVATAR_REQUEST to server.
     * /cheatRPS - sends G_S_R_P_S_FORCE_SELECT to server
     * /rotateCamera - be careful with that it can crash your MS.
     * /setCamera
     * /changeBGM
     * /sound
     * /showMeso
     * /getnextexp
     * /hackSummon
     * /jaguarHack
     * /getskilllevel
     */
    Create(0, "/create", "Creates for you an item."),
    Remove(1, "/d", "Destroy all items in specific inventory (1~5)."),
    RemoveAll(2, "/dall", "Destroy all items at all."),
    RemoveAll2(3, "/destroyall2"),
    SetMake(4, "/setmake"),// [[01 00 00 00] [02 00 00 00] [03 00 00 00]]
    ViewMake(5, "/viewmake"),// [01 00 00 00]
    SetFatigue(6, "/setfatigue"),// [XX XX XX XX] amount
    ViewFatigue(7, "/viewfatigue"),// no data
    ViewLimitReactor(8, "/vlr"),// [[01 00 00 00] [01 00] [31]]
    IncExp(9, "/exp", "Increasing your exp."),
    CharismaExp(10, "/charismaexp"),// [XX XX XX XX] amount
    InsightExp(11, "/insightexp"),// [XX XX XX XX] amount
    WillExp(12, "/willexp"),// [XX XX XX XX] amount
    CraftExp(13, "/craftexp"),// [XX XX XX XX] amount
    SenseExp(14, "/senseexp"),// [XX XX XX XX] amount
    CharmExp(15, "/charmexp"),// [XX XX XX XX] amount
    IncSchoolExp(16, "/schoolexp"),
    Logout(17, "/ban"),// [String] character name
    Portal(18, "/pton, /ptoff", "Enable/Disable portal."),// status [01/00], portal name[03 00] [31 20 31]
    NpcVar(19, "/varset, /varget"),
    NpcVarSet(20),// type inside npc var
    NpcVarGet(20),// type inside npc var
    // 21
    BanishAll(22, "/expel"),// 24 79 5C 24
    Timer(23, "/tmset, /tmon"),// [00 84 D7 17]-idk(seems static) [01 00 00 00]-seconds i guess
    Desc(24, "/explain"),// [01]
    EventStart(25),
    Hide(26, "/h"),
    UserList(27, "/u, /uclip"),
    SendUser(28),
    EntrustedShop(29, "/findhm"),// [01 00] [31]
    AdminEvent(30),
    ScreenShot(31, "/ss"),// no data
    MobClearLog(32, "/setclearlog, /resetclearlog"),
    KillMob(33, "/kill"),// [04 87 01 00] [0A 00 00 00]
    QuestReset(34, "/questreset"),
    Quest(35, "/quest"),
    QuestLoad(36, "/loadquest"),
    Summon(37, "/summon"),// [[7B F9 90 00]- summon id [64 00 00 00]-2nd arg unk [08 00] [73 67 73 64 67 73 64 67]-describe message]
    MobHP(38, "/mobhp"),// [01 00 00 00]-mob id ?
    MobHPRate(39, "/mobhpR"),// [04 87 01 00]-mob id ?
    MobNotDeadRecoverMobHP(40, "/setnotdeadrecovermobhp"),
    MobHPView(41, "/mobhpview"),// no data
    LevelSet(42, "/levelset"),// [01] - the level you desired.
    JobSet(43, "/job"),// [9A 01 00 00]-jobid
    JobFreeSwap(44, "/jobfreeswap"),// [9A 01 00 00], not sure what it does.
    NoCoolTime(45, "/nocooltime"),// no data, probably bypass the cooldown
    ApGet(46, "/apget"),// [05 00 00 00]
    MasterAllSkill(47, "/masterallskill"),// no data just skill max
    SpGet(48, "/spget"),// [01 00 00 00], shows the sp value for the skill book you want
    STR(49, "/str"),// [01 00 00 00] - amount
    DEX(50, "/dex"),// [01 00 00 00] - amount
    INT(51, "/int"),// [01 00 00 00] - amount
    LUK(52, "/luk"),// [01 00 00 00] - amount
    Skill(53, "/skill"),// [A0 8F 3E 00] [01 00 00 00]
    SkillSteal(54, "/skillsteal"),// no response
    StealSkillReset(55, "/stealskillreset"),// no response
    MMON(56, "/mmon"),// [01 00] [31]
    MMOFF(57, "/mmoff"),// [01 00] [31]
    ShowRank(58, "/Artifact Ranking"),
    MobControl(59, "/mobcontrol"),// [21]
    PassiveSkillValueView(60, "/psdview", "Showing all your passive data"),
    PassiveSkillSet(61, "/psdset", "Setting passive skill data (client side I guess)."),
    PassiveSkillUpdate(62, "/psdupdate", "Clear the passive data (should send data for client)"),
    SecondaryStatView(63, "/ssview", "Showing information about secondary stats."),
    GetCooltime(64, "/getcooltime"),// [01 00 00 00] skill id
    SetPVPTeam(65, "/setpvpteam"),// [01 00 00 00]
    SetPVPTeamDebug(66),
    SetPVPMode(67, "/setpvpmode"),// [01 00 00 00] [02 00 00 00]
    GivePVPExp(68, "/givepvpexp"),// [64 00 00 00]
    SetItemPot(69, "/setitempot"),// [01 00 00 00]
    ViewItemPot(70, "/viewitempot"),// [01 00 00 00]
    RemoveItemPot(71, "/removeitempot"),// [01 00 00 00]
    IncItemPotSatiety(72, "/incitempotsatiety"),// [01 00 00 00] [02 00 00 00]
    CureItemPot(73, "/cureitempot"),// [01 00 00 00]
    SelfInjury(74, "/selfinjury"),// [01 00 00 00]
    TempactSet(75, "/play"),// no data
    TempactReset(77, "/restore"),// no data
    ForceEvolMob(77, "/evolmob"),// [01 00 00 00]
    ScriptRun(78, "/scriptrun"),// only logging
    LoadFSScript(79),
    FSScriptRun(80),
    SetPVPOpenTime(81, "/setpvptime"),// [01 02 03 04]
    CreateBadge(82, "/badge"),// [01 02 03]
    CreateEX(83, "/createex"),// itemID - [98 53 14 00] [01 00 00 00] [01 00 00 00] [01 00 00 00] [01 00 00 00] [01 00 00 00] Describe msg: [09 00] [61 73 64 61 73 64 61 73 64]
    CreateEXItem(84, "createexitem"),// only logging
    CreateAddiotionalEX(85, "/createaddex"),// itemID - [98 53 14 00] [01 00 00 00] [01 00 00 00] [01 00 00 00] [01 00 00 00] [01 00 00 00] Describe msg: [0B 00] [73 61 64 61 73 64 61 73 64 73 61]
    CreateBySN(86, "createbysn"),// do nothing.
    ForceAtom(87),
    ForceAtomStart(88),
    SummonStat(89, "/summonstat"),
    SummonChangeable(90, "/summoncmob"),// [01 00 00 00] [01 00] [32] [03 00 00 00] [04 00 00 00 [05 00 00 00]
    GetMobStat(91, "/getmobstat"),
    SetServerVal(92, "/setserverval"),// [01 00 00 00] [02 00 00 00]
    SetCenterVal(93, "/setcenterval"),// [01 00 00 00] [01 00] [32]
    // 94
    GetQr(95, "/getqr", "Showing you quest qr value."),// [7F 0C 00 00]
    SetQr(96, "/setqr", "Setting quest qr value."),// [7F 0C 00 00] [01 00] [31]
    GetQrEX(97, "/getqrex", "Showing you quest ex qr value."),// [7F 0C 00 00]
    SetQrEX(98, "/setqrex", "Setting quest ex qr value"),// [7F 0C 00 00] [01 00] [31] [01 00] [31]
    GetWSR(99, "/getwsr"),// [01 00 00 00]
    SetWSR(100, "/setwsr, /mvp_open_sp, /mvp_close_sp", "/mvp_clear_enter, /mvp_clear_dp, /mvp_set_enter"),// [01 00 00 00] [01 00] [32] [01 00] [33]
    ResetWSR(101, "/resetwsr"),// [01 00 00 00]
    // 102
    // 103
    // 104
    // 105
    // 106
    // 107
    // 108
    ResetQrEX(109, "/resetqrex"),
    ResetQrEXKey(110, "/ResetQrEXKey"),
    GetER(111),
    SetER(112),
    SummonCopyChar(113, "/summoncopychar"),// [63 31 87 00]
    MobSkill(114),
    MCPartyMax(115, "/setmcparty"),// [01 00 00 00] [02 00 00 00]
    MCTargetMap(116, "/setmcmap"),// [01 00 00 00]
    DefPoint(117),
    DefWave(118),
    DefLive(119),
    RegisterDojang(120, "/registerdojang"),
    RelaxExp(121),
    LastLogout(122),
    SubtractLogoutDate(123),
    Shutdown(124, "/shutdown"),// [01 00] [31] [02 00 00 00]
    SetInstantLogTraceCount(125, "/logmobmove, /logusermove"),
    CreateCashItem(126, "/createcash"),// [01 00 00 00]
    CreateMultiCash(127, "/createMultiCash"),
    MobGenCategory(127, "/setmobgencategory"),
    ViewMobGenCategory(128, "/viewmobgencategory"),
    GiveMeHeal(121, "/givemeheal"),
    CheckMobZone(122, "/checkmobzone"),
    ObtacleAtom(123, "/obtacleatom"),
    DebuffObj(124, "/debuffobj"),
    ForceAtomMod(125),
    ForceAtomLoad(126),
    ForceAtomView(127),
    CharacterPotentialSet(139, "/cpsset"),// [01 00] [02 00 00 00] [03 00]
    CharacterPotentialReset(129, "/cpsresetbypos, /cpsresetbyskill, /cpsresetall"),
    CharacterPotentialSetByItem(130, "/cpsrandset"),
    HonorExp(142, "/honorexp"),// [01 00 00 00]
    AlbaRewardTest(143, "/albatest"),// [01 00 00 00] [02 00 00 00] [03 00 00 00] [04 00 00 00]
    ShowCharCard(144, "/showcharcard", "Showing affected card skill list."),
    SetCharCard(145, "/setcharcard"),
    BlockCharCardUpdate(146, "/blockcharcard"),// [01 00 00 00]
    AlbaReset(147, "/albareset"),
    AlbaStat(148, "/albastat"),
    SeedFloorLimit(149, "/setseedfloorlimit"),// [01 00 00 00] [01 00] [32]
    UpdateRandomShop(150, "/updaterandomshop"),
    UpdateRandomNpc(151, "/updaterandomnpc"),
    LoadMobDifficulty(152, "/loadmd"),
    SetMobDifficulty(153, "/setmd"),// [04 87 01 00] [64 00 00 00] [64 00 00 00] [64 00 00 00] [64 00 00 00] [6C 07 00 00] [01 00 00 00]
    SetEventPoint(154, "/eventpoint"),// [64 00 00 00]
    SetHardCoreLevel(155, "/sethclevel"),// [01 00 00 00]
    SetMechanicHue(156, "/sethue"),// [64 00 00 00] should be <=360
    ResetSkillAndSP(157, "/resetskillsp"),// no data
    KillCMob(158, "/killcmob"),// [01 00 00 00] [02 00 00 00] [03 00 00 00]
    VIEWEXEPTEXTERNALINDUN(156, "/viewexceptexternalindun"),// no data
    // 157
    // 158
    ChangeMobZone(159, "/changemobzone"),
    SetFixDam(162, "/setDam"),
    SetAsr(163, "/setAsr"),
    SetPDD(164, "/setPdd"),
    AvatarPackTest(165, "/avatarpacktest"),
    // 166
    // 167
    PetRepleteness(168, "/petrepleteness"),// [XX]- pet idx 1~3 [XX]-Repleteness 1~100
    PetLevel(169, "/petlevel"),// [XX]- pet idx 1~3 [XX]-Level
    EvolvingSystem(170),
    ResetHyperSkill(171, "/resethyperskill"),
    CreateRune(172, "/runeCreate", "/runeCreate2"),
    InitRune(173, "/runeInit"),
    GetRuneDebuff(174, "/getrunedebuff"),
    SetRuneDebuff(175, "/setrunedebuff"),
    GetUserDebuff(176, "/getuserdebuff"),
    YutDice(177, "/yut"),
    MYutDice(178, "/mYut"),
    KillServer(179, "/killserver"),
    TenthCard(180, "/tenthCard"),
    W32TimerOn(181, "/w32timeron, /w32timeroff"),
    SetServerTime(182, "/setservertime"),
    ResetServerTime(183, "/resetservertime"),
    SessionValue(184, "/setsessionval, /getsessionval"),// 1 set 2 get
    GetHalloweenRank(185),
    GoToBonusStage(186, "/gotoBonus"),
    PetActiveSkillCoolTime(187, "/petActiveSkillCoolTime"),
    FieldValue(188, "/setfieldval, /getfieldval"),
    MultiStageBonusOpen(189, "/openBonus"),
    MultiStageBonusClose(190, "/closeBonus"),
    MultiStageBonusCurrentStatus(191, "/viewBonusState"),
    SetBaseDamage(192, "/setbasedam"),
    SetPremiumUser(193, "/setpremiumuser"),
    MesoMarket(194, "/mesomarket"),// [01]
    MesoMarketList(195, "/mesomarketbuylist", "/mesomarketselllist"),// [01/00]
    SlideNotice(196, "/slidenotice"),// [03 00] [6C 6F 6C]
    LotteryItemIterate(197),
    MesoMarketInvigoRate(198, "mesomarketsetting"),// [01 00 00 00] [02 00 00 00] [03 00 00 00] [04 00 00 00] [05 00 00 00]
    CreateRandomShop(199, "/createrandomshop"),
    CheckProcess(200, "/checkprocess"),// [01 00] [31]
    // from here -2
    SoulMP(201, "/soulMP"),// [01 00 00 00]
    SetLvPenalty(202, "/setlvpenalty"),// only log
    BroadCMD(203),// should look for that
    ViewStaticInfo(204, "/viewStaticInfo"),
    ViewStaticInit(205, "/initStaticInfo"),
    WPGet(206, "/wp"),
    // 207
    SetStreamingURL(208, "/setstreamingurl"),// [01 00] [31]
    FreeWearMode(209, "/letterbox"),
    SummonUserControl(210),
    FieldSetValue(211, "/setfsval, /getfsval"),
    AddSoul(212, "/addsoul"),// [01 00 00 00]
    AddCrystal(213, "/addcrystal"),
    AddCrystalSkill(214, "/addcrystalskill"),
    FootholdSetOFF(215),
    WorldLimitReward(216),
    WorldLimitRewardCountMod(217, "/limitrewardmodcount"),// [01 00 00 00] [02 00 00 00]
    WorldLimitRewardInit(218, "/limitrewardinit"),// no data
    CreateFieldAttackObj(219, "/createfao"),// CFieldAttackObjTemplate::GetFieldAttackObjTemplate(v569) intresting
    RemoveFieldAttackObj(220, "/removefao"),
    SummonNpc(221, "/summonNpc"),// [B7 FD FF FF] [66 00 00 00] [2A 67 8C 00]
    AttackWorldBoss(222),
    SummonReactor(223, "/summonReactor, /summonOnlyViewReactor"),
    SetPacketDump(224, "/setdump"),
    // 225
    // 226
    // 227
    // 228
    SpecterGauge(229, "/spectergauge"),
    RandomMazeInfo(230, "/showRandomMazeInfo"),
    MoveMazeByPos(231, "/moveMazeByPos"),
    RandomMazeMission(232, "/setRandomMazeMission"),
    ShowRandomMazeMission(233, "/showRandomMazeMission"),
    LoadRandomMazeData(234, "/loadRandomMazeData"),
    AcrivateRandomMazeSoul(235, "/activateRandomMazeSoul"),
    SummonFarmReleaseMob(236, "/summonFarmReleaseMob"),
    // 237
    StartUniverseBoss(238, "/startuniboss"),
    ToHub(239, "/movetohub"),
    FromHub(240, "/movefromhub"),
    KillUniverseBoss(241, "/killuniboss"),
    BuyLimit(242),
    // 243
    BAMatchSimulate(244, "/bamatchsimulate"),// [01 00 00 00]
    BAMatchRequest(245, "/bamatchrequest"),// [01]
    BARatingSet(246, "/baratingset"),// [01 00 00 00]
    ELOSimulate(247, "/elosimulate"),// [01 00 00 00] [02 00 00 00]
    BAJustResult(248, "/bajustresult"),// [01] [01]
    RemovePQRank(249, "/removepqrank"),// [01 00 00 00] [02 00 00 00]
    SetHubValue(250, "/sethubval"),// [[01 00 00 00] [01 00] [32] [03 00 00 00]]
    Guild(251, "/incCommitment, /setGP, /setIGP"),
    GuildSkillSet(252, "/setguildskill"),
    GuildBattleSkillReset(253, "/guildbattleskillreset"),
    CoordiLoad(254, "/coordiload"),// [01]
    CoordiList(255, "/coordilist"),// no data
    DreamBreakerLoad(256, "/dreambreakerload"),
    DreamBreakerStagePoint(257, "/dreambreakerstagepoint"),
    DreamBreakerStage(258, "/dreambreakerstage"),
    DreamBreakerTimeHold(259, "/dreambreakertimehold"),
    DreamBreakerGaugeHold(260, "/dreambreakergaugehold"),
    SpiritSaviorTimer(261, "/spiritsaviortimer"),
    PapulatusLaser(262, "/papulatuslaser"),
    PapulatusCrane(263, "/papulatuscrane"),
    SetNaverMember(264, "/navermember"),
    RunFS(265, "/runfs"),// no data
    WCopyCharacter(266, "wcopycharacter"),
    CopyCharacter(267, "/copycharacter"),
    EliteStage(268, "/elitestage"),// [01 00 00 00]
    EliteStageFix(269, "/elitestagefix"),
    RndPortal(270, "/randomPortal"),
    HotTimeReload(271),
    IronBoxReset(272),
    IronBoxInfo(273, "/ironboxinfo"),
    TypingGameClear(274, "/cleartyping"),
    TypingGameMaxHP(275, "/maxtyping"),
    SetItemLevel(276, "/setitemlevel"),
    SetSurveyProb(277, "/survey"),
    SetMapleTVShowTime(278, "/setmapletvtime"),
    BossWaitingBuff(279, "/selfbuff"),
    MetaDataReload(280),
    SadSimulateCharEXP(281),
    SadSetNextTime(282, "/friendskip"),
    AlwaysDrop(283, "/dropRate100"),
    SetGod(284, "/setgod"),
    OpenUITest(285, "/uitest, /Test Window"),
    BlacklistStateON(286, "/blacklistview"),
    TheMeSeaONOFF(287, "/themeseasonoff"),
    SetScrollFeverState(288, "/scrollfeverstate"),// [01]
    StartPlanetMiniGameRPSForcedWin(289, "/spMiniGameRpsWin"),
    StarPlanetWorldEventRPSForcedWin(290, "/spWorldEventRpsWin"),
    StarPlanetWorldEventSet(291, "/spWorldEvent"),
    StarPlanetTestMatching(292, "/spMiniGameMatching"),
    GetStarPlanetInfo(293, "/getstarplanetinfo"),// [01 00 00 00] [01 00] [32]
    StarPlanetWorldEventDanceForcedWin(294, "/spWorldEventDanceWin"),
    TabgSpecialCard(295, "/marbleSC"),
    TabgSetHP(296, "/marbleHP"),
    BattlePVPExpUP(297, "/battlepvpexp"),
    BattlePVPPointUP(298, "/battlepvppoint"),
    BattlePVPSkipDropOutWin(299, "/battlepvptest"),
    BattlePVPCooltimeReset(300, "/battlepvpresetskill"),
    SuperMultiYutSkill(301, "/smYutSkill"),
    GachaponRewardTest(302),
    MasterPieceRewardTest(303),
    CashGachaponRewardTest(304),
    BeautyCouponRewardTest(305),
    MomentAreaONOFF(306),
    // 307
    // 308
    // 309
    PublicShareState(310, "/setpss, /removepss, /getpss"),
    WaveGenReload(311),
    // 312
    // 313
    SetFlowCamera(314, "/setflowcamera"),
    TowerSeedInit(315, "/towerinit"),
    SwimWaterTime(316, "/swimwatertime"),
    SwimWaterGhost(317, "/swimwaterghost"),
    ItemCollectionAll(318, "/itemcollectionall"),
    ItemCollectionReset(319, "/resetitemcollection"),
    FullPsychicPoint(320, "/fullpp"),// no data
    SendMail(321),
    // 322
    MobAttack(323, "/mobattack"),
    MobSkill2(324, "/mobuseskill"),
    SetAboutEntrustedShopDBQueerFailCount(325, "/setaboutentrustedshopdbqueryfailcount"),// [01 00 00 00] [02 00 00 00]
    TestWeather(326, "/weathertest"),// [01 00 00 00]
    SetRolePlaying(327, "/roleplay"),// [01 00 00 00]
    ResetRolePlaying(328, "/roleplayreset"),// no data
    ReloadSwitchRolePlayingData(329, "/reloadswrp"),// no data
    AranBoost(330, "/aranboost"),// no data
    CreateComboKillBonus(331, "/createComboKillBonus"),
    GetCR(332, "/getcr"),
    SetCR(333, "/setcr"),
    ResetCR(334, "resetcr"),
    MonsterCollectionComplete(335, "/setMCcomplete"),
    SetNXRecord(336, "/setnxrecord"),
    BlockBlizzard(337, "/blizzardblock"),
    RWSetCylinder(338, "/rwcylinder"),// [01 00 00 00] [02 00 00 00]
    RWSetCombination(339, "/rwcombination"),// [01 00 00 00]
    RWSetBarrier(340, "/rwbarrier"),// [01 00 00 00]
    RenameCharacter(341),
    HiddenRect(342, "/hiddenrect"),// no data
    JewelCraftProb(343, "/jewelcraftprob"),
    ReloadScriptWhitelist(344, "/reload_scriptlist"),// v202.3
    SetMP(345, "/setmp"),// v202.3
    DemianNextPhase(346, "/demianNextPhase"),
    DemianCreateSword(347, "/demianCreateSword"),
    DemianSetStigma(348, "/demianSitigma"),
    AsyncLiveApi(349, "/asyncliveapi"),
    LiveApi(350, "/liveapi"),
    IncArcCount(351, "/incArcCount"),
    VCoreEffect(352, "/vcoreeffect"),
    CreateVCore(353, "/createVCore"),
    SetBurningFieldPhase(354, "/setBurningFieldPhase, /burningstage"),
    TestBait(355, "/baittest"),
    LucidButterfly(356, "/lucidButterfly"),
    LucidHurdle(357 ,"/lucidHurdle"),
    LucidDragonBreath(358, "/lucidDragonBreath"),
    LucidFoothold(359, "/lucidFoothold"),
    SetHornCount(360, "/SetHornCount"),
    SetHornAble(361, "/SetHornAble"),
    LoadClientCRC(362, "/loadclientcrc"), SharedStat(492, "/sharedstat"),// [64 00 00 00]
    FindNPC(366, "/findnpc"),
    AddSkillStack(368, "/addskillstack"),
    Unstable(370, "/unstable"),
    Pop(371, "/pop"),
    Log(374, "/blockskill, /blocklog, /allowlog"),
    TestLog(375, "/logtest"),
    SetComboKillCount(376, "/setComboKillCount"),
    StartRM(377, "/rmStart"),
    ResetRM(378, "/rmReset"),
    ViperEnerge(379, "/viperenerge"),
    GetEliteBossCount(380, "/getElitebossCount"),
    MultiKill(381, "/multikill"),
    MapMobClear(382, "/mapmobclear"),
    // 383
    ResetLinkSkill(384, "/resetlinkskill"),
    DeleteCashPurchaseRecord(387, "/deletecashpurchaserecord"),
    SetExtractorRewardSet(388, "/extractorRewardSet, /ExtractorObtainSoulShard"),
    CaniMapleM(389, "/canimaplem"),
    CaniMapleBlitz(390, "/canimapleblitz"),
    AdminLog(393, "/adminlog"),
    Decmp(391,"/decmp"),
    SetFB(396, "/setfb"),
    FieldMobHuntUser(398, "/fieldmobhuntuser"),
    FieldMobHuntUserLit(399, "/fieldmobhuntuserlist"),
    Accountreliability(400, "/accountreliability"),
    ViewMobReward(402, "/viewmobreward"),
    MesoRiding(403, "/mesoRiding, /mesoRidingReload"),
    CreateMesoWorthCoin(405, "/createMesoWorthCoin"),
    LoadMesoWorthCoin(406, "/loadMesoWorthCoin"),
    GetHandsPlusLoginDate(407, "/gethandspluslogindate, /sethandspluslogindate"),
    BuffControl(408, "/buffcontrol"),
    BuffControlTime(409, "/buffcontroltime"),
    AlarmInterval(410, "/alarminterval"),
    GdAnswer(411, "/gdanswer"),
    ViewDamage(412, "/damageview"),
    SetMobTeam(414, "/setmobteam"),
    SetMobTeamUser(415, "/setmobteamuser"),
    SummonApcMob(417, "/summonapcmob"),
    SummonApcMob2(418, "/summonapcmob2"),
    GetUserChangableMobID(419, "/getuserchangablemobid"),
    GetApcAccountInfo(421, "/getApcAccountInfo"),
    VmesReset(422, "/vmesReset"),
    ReloadExpItemCharge(423, "/reloadExpItemCharge, /reloadEIC"),
    ModifyApcMMR(427, "/modifyApcMMR"),
    SetLunarPower(428, "/setlunarpower"),
    WillCMD(429, "/willcmd"),
    ClearApcRank(430, "/clearApcRank"),
    ReloadApcUser(431, "/reloadApcUser"),
    FieldGimmick(437, "/fieldgimmick"),
    GimmickInfo(438, "/gimmickinfo"),
    Gimmick(439, "/gimmick"),
    FieldGimmickReload(440, "/fieldgimmickreload"),
    IndividualMobPoolCMD(441, "/individualMobPoolcmd"),
    IncTempExp(442, "incptexp"),
    ResetPtRecord(443, "/resetptrecord"),
    PtSystem(444, "/ptsystem"),
    FieldSkill(445, "/fieldskill, /resetfieldskill, /flushfieldskill"),
    LoadTimeLimitField(449, "/loadTimeLimitField, /changeTimeLimitField"),
    ReservedSkill(450, "/reservedskill"),
    AddHillahSoul(451, "/addhillahsoul"),
    RemoveHillahSoul(452, "/removehillahsoul"),
    BlackMageReload(454, "/blackmagereload"),
    QuestSet(460, "/questset"),// [01 00 00 00]
    W(462, "/w"),// [01 00] [31] [03 00] [32 20 33]
    FieldSetScriptTimer(486, "/fieldsetscripttimer"),// [01 00 00 00]
    PartyCheck(500, "/partycheck"),// [01 00] [31]
    ReloadDisallowedDeliveryQuest(501, "/reloaddisalloweddeliveryquest"),// no data
    HpSet(502, "/hpset"),// [01 00 00 00]
    MpSet(503, "/mpset"),// [01 00 00 00]
    RandomStageFixedOn(521, "/randomstagefixedon"),// [01 00 00 00] [02 00 00 00]
    RandomStageFixedOff(522, "/randomstagefixedoff"),// no data
    FPSet(523, "/fpset"),// [04 00 00 00] [04]
    BossRewardExceptionReload(526, "/bossrewardexceptionreload"),// no data
    KillMobAll(538, "/killmoball"),
    ExportCharacter(551, "/exportcharacter"),
    ImportCharacter(552, "/importcharacter"),
    Umdh(555, "/umdh"),
    SetLadder(557, "/setladder"),
    SetGunmanRound(558, "/setgunmanround"),
    CreateEx2(559, "/createex2"),
    WebEventCheck(560, "/webeventcheck"),
    ReloadUIEvent(563, "/reloadUEvent");

    private int val;
    private String command = "/unknown", desc = "?";

    AdminCommandType(int val) {
        this.val = val;
    }

    AdminCommandType(int val, String command) {
        this.val = val;
        this.command = command;
    }

    AdminCommandType(int val, String command, String desc) {
        this.val = val;
        this.command = command;
        this.desc = desc;
    }

    public int getVal() {
        return val;
    }

    public static AdminCommandType getByVal(int val) {
        return Util.findWithPred(Arrays.asList(values()), csat -> csat.getVal() == val);
    }
}
