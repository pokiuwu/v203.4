package net.swordie.ms.client.character.skills.temp;

import org.apache.log4j.LogManager;
import org.python.antlr.ast.Return;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created on 1/2/2018.
 */
public enum CharacterTemporaryStat implements Comparator<CharacterTemporaryStat> {
    IndiePAD(0),
    IndieMAD(1),
    IndiePDD(2),
    IndieMDD(-1),
    IndieMHP(3),
    IndieMHPR(4),// v202.3
    IndieMMP(5),
    IndieMMPR(6),

    IndieACC(7),
    IndieEVA(8),
    IndieJump(9),
    IndieSpeed(10),// v202.3
    IndieAllStat(11),// v202.3
    IndieDodgeCriticalTime(12),
    IndieEXP(13),
    IndieBooster(15),// v202.3

    IndieFixedDamageR(16),
    PyramidStunBuff(17),
    PyramidFrozenBuff(18),
    PyramidFireBuff(19),
    PyramidBonusDamageBuff(20),
    IndieRelaxEXP(21),
    IndieSTR(22),
    IndieDEX(23),

    IndieINT(24),
    IndieLUK(25),
    IndieDamR(26),// v202.3
    IndieScriptBuff(27),
    IndieMDF(28),
    IndieMaxDamageOver(-1),
    IndieAsrR(29),
    IndieTerR(30),

    IndieCr(31),
    IndiePDDR(32),
    IndieCrDam(33),
    IndieBDR(34),// v202.3
    IndieStatR(35),
    IndieStance(36),
    IndieIgnoreMobpdpR(37),
    IndieEmpty(38),

    IndiePADR(39),// v202.3
    IndieMADR(40),// v202.3
    IndieCrDamR(41),
    IndieEVAR(42),
    IndieMDDR(-1),
    IndieDrainHP(43),
    IndiePMdR(-1),
    IndieMaxDamageOverR(-1),

    IndieForceJump(44),
    IndieForceSpeed(45),
    IndieQrPointTerm(46),
    IndieUnk1(47),
    IndieUnk2(48),
    IndieUnk3(49),
    IndieUnk4(50),
    IndieUnk5(51),
    IndieUnk6(52),
    IndieUnk7(53),
    IndieUnk8(54),
    IndieUnk9(55),
    IndieUnk10(56),
    IndieUnk11(57),
    IndieUnk12(58),
    IndieUnk13(59),
    IndieUnk14(60),
    IndieUnk15(61),
    IndieUnk16(62),
    IndieUnk17(63),
    IndieStatCount(64),

    PAD(65),// v202.3
    PDD(66),// v202.3
    MAD(67),// v202.3
    MDD(-1),// not exists anymore
    ACC(68),// v202.3
    EVA(69),// v202.3
    Craft(70),// v202.3

    Speed(71),// v202.3
    Jump(72),// v202.3
    MagicGuard(73),// v202.3
    DarkSight(74),// v202.3
    Booster(75),// v202.3
    PowerGuard(76),// v202.3
    MaxHP(77),// v202.3
    MaxMP(78),// v202.3

    Invincible(79),// v202.3
    SoulArrow(80),// v202.3
    Stun(81),// v202.3
    Unk82(82),// v202.3
    Unk83(83),// v202.3
    Unk84(84),// v202.3
    Poison(85),// v202.3
    Seal(86),// v202.3
    Darkness(87),// v202.3
    ComboCounter(88),// v202.3
    WeaponCharge(89),// v202.3

    HolySymbol(90),// v202.3
    MesoUp(91),// v202.3
    ShadowPartner(92),// v202.3
    PickPocket(93),// v202.3
    MesoGuard(94),// v202.3
    Thaw(95),// v202.3
    Weakness(96),// v202.3
    Curse(97),// v202.3

    Slow(98),// v202.3
    Morph(99),// v202.3
    Regen(100),// v202.3
    BasicStatUp(101),// v202.3
    Stance(102),// v202.3
    SharpEyes(103),// v202.3
    ManaReflection(104),// v202.3
    Attract(105),// v202.3

    NoBulletConsume(106),// v202.3
    Infinity(107),// v202.3
    AdvancedBless(108),// v202.3
    IllusionStep(109),// v202.3
    Blind(110),// v202.3
    Concentration(111),// v202.3
    BanMap(112),// v202.3
    MaxLevelBuff(113),// v202.3
    Unk114(114),// v202.3
    Unk115(115),// v202.3
    MesoUpByItem(116),// v202.3
    Ghost(117),// v202.3
    Barrier(118),// v202.3
    ReverseInput(119),// v202.3
    ItemUpByItem(120),// v202.3
    RespectPImmune(121),// v202.3
    RespectMImmune(122),// v202.3
    DefenseAtt(123),// v202.3

    DefenseState(124),// v202.3
    DojangBerserk(125),// v202.3
    DojangInvincible(126),// v202.3
    DojangShield(127),// v202.3
    SoulMasterFinal(128),// v202.3
    WindBreakerFinal(129),// v202.3
    ElementalReset(130),// v202.3
    HideAttack(131),// v202.3

    EventRate(132),// v202.3
    ComboAbilityBuff(133),// v202.3
    ComboDrain(134),// v202.3
    ComboBarrier(135),// v202.3
    BodyPressure(136),// v202.3
    RepeatEffect(137),// v202.3
    ExpBuffRate(138),// v202.3 [Used for 2450156]
    StopPortion(139),// v202.3

    StopMotion(140),// v202.3
    Fear(141),// v202.3
    HiddenPieceOn(142),// v202.3
    MagicShield(143),// v202.3
    MagicResistance(144),// v202.3
    SoulStone(145),// v202.3
    Flying(146),// v202.3
    Frozen(147),// v202.3

    AssistCharge(148),// v202.3
    Enrage(149),// v202.3
    DrawBack(150),// v202.3
    NotDamaged(151),// v202.3
    FinalCut(152),// v202.3
    HowlingAttackDamage(153),// v202.3
    BeastFormDamageUp(154),// v202.3
    Dance(155),// v202.3

    EMHP(156),// v202.3
    EMMP(157),// v202.3
    EPAD(158),// v202.3
    EMAD(159),// v202.3
    EPDD(160),// v202.3
    EMDD(-1),// not exists anymore

    Guard(161),// v202.3
    Unk162(162),// v202.3
    Unk163(163),// v202.3
    Cyclone(164),// v202.3
    Unk165(165),// v202.3
    HowlingCritical(-1),
    HowlingMaxMP(-1),
    HowlingDefence(-1),
    HowlingEvasion(-1),
    Conversion(166),// v202.3
    Revive(167),// v202.3
    PinkbeanMinibeenMove(168),// v202.3
    Sneak(169),// v202.3

    Mechanic(170),// v202.3
    BeastFormMaxHP(171),// v202.3
    Dice(172),// v202.3
    BlessingArmor(173),// v202.3
    DamR(174),// v202.3
    TeleportMasteryOn(175),// v202.3
    CombatOrders(176),// v202.3
    Beholder(177),// v202.3

    DispelItemOption(178),// v202.3
    Inflation(179),// v202.3
    OnixDivineProtection(180),// v202.3
    Web(181),// v202.3
    Bless(182),// v202.3
    TimeBomb(183),// v202.3
    DisOrder(184),// v202.3
    //Thread(185),// v202.3

    Team(186),// v202.3
    Explosion(187),// v202.3
    BuffLimit(188),// v202.3
    STR(189),// v202.3
    INT(190),// v202.3
    DEX(191),// v202.3
    LUK(192),// v202.3
    DispelItemOptionByField(193),// v202.3

    DarkTornado(194), // Cygnus Attack
    PVPDamage(195),// v202.3
    PvPScoreBonus(196),// v202.3
    PvPInvincible(197),// v202.3
    PvPRaceEffect(198),// v202.3
    WeaknessMdamage(199),// v202.3
    Frozen2(200),// v202.3
    PVPDamageSkill(201),// v202.3

    AmplifyDamage(202),// v202.3
    IceKnight(-1),
    Shock(203),// v202.3
    InfinityForce(204),// v202.3
    IncMaxHP(205),// v202.3
    IncMaxMP(206),// v202.3
    HolyMagicShell(207),// v202.3
    KeyDownTimeIgnore(208),// v202.3

    ArcaneAim(209),// v202.3
    MasterMagicOn(210),// v202.3
    AsrR(211),// v202.3
    TerR(212),// v202.3
    DamAbsorbShield(213),// v202.3
    DevilishPower(214),// v202.3
    Roulette(215),// v202.3
    SpiritLink(216),// v202.3

    AsrRByItem(217),// v202.3
    Event(218),// v202.3
    CriticalBuff(219),// v202.3
    DropRate(220),// v202.3
    PlusExpRate(221),// v202.3
    ItemInvincible(222),// v202.3
    Awake(223),
    ItemCritical(224),// v202.3

    ItemEvade(225),// v202.3
    Event2(226),// v202.3
    VampiricTouch(227),// v202.3
    DDR(228),// v202.3
    IncCriticalDamMin(-1),
    IncCriticalDamMax(-1),
    IncTerR(229),// v202.3
    IncAsrR(230),// v202.3

    DeathMark(231),// v202.3
    UsefulAdvancedBless(232),// v202.3
    Lapidification(233),// v202.3
    VenomSnake(234),// v202.3
    CarnivalAttack(235),// v202.3
    CarnivalDefence(236),// v202.3
    CarnivalExp(237),// v202.3
    SlowAttack(238),// v202.3

    PyramidEffect(239),// v202.3
    KillingPoint(240),// v202.3
    HollowPointBullet(241),// v202.3
    KeyDownMoving(242),// v202.3
    IgnoreTargetDEF(243),// v202.3
    ReviveOnce(244),// v202.3
    Invisible(245),// v202.3
    EnrageCr(246),// v202.3

    EnrageCrDam(247),// v202.3
    Judgement(248),// v202.3
    DojangLuckyBonus(249),// v202.3
    PainMark(250),// v202.3
    Magnet(251),// v202.3
    MagnetArea(252),// v202.3
    Unk253(253),// v202.3
    Unk254(254),// v202.3
    Unk255(255),// v202.3
    Unk256(256),// v202.3
    Unk257(257),// v202.3
    TideOfBattle(258),// v202.3
    GrandGuardian(259),// v203.2 Paladin VSkills
    DropPer(260),// v202.3 [Used for 2023145]
    VampDeath(261),// v202.3
    BlessingArmorIncPAD(262),// v202.3
    KeyDownAreaMoving(263),// v202.3
    Larkness(264),// v202.3
    StackBuff(265),// v202.3
    BlessOfDarkness(266),// v202.3
    AntiMagicShell(267),// v202.3
    AntiMagicShellBool(267),// v202.3
    LifeTidal(268),// v202.3
    HitCriDamR(269),// v202.3
    SmashStack(270),// v202.3

    PartyBarrier(271),// v202.3
    ReshuffleSwitch(272),// v202.3
    SpecialAction(273),// v202.3
    VampDeathSummon(274),// v202.3
    StopForceAtomInfo(275),// v202.3
    SoulGazeCriDamR(276),// v202.3
    SoulRageCount(277),// v202.3
    PowerTransferGauge(278),// v202.3

    AffinitySlug(279),// v202.3
    Trinity(280),// v202.3
    IncMaxDamage(281),// v202.3
    BossShield(282),// v202.3
    MobZoneState(283),// v202.3
    GiveMeHeal(284),// v202.3
    TouchMe(285),// v202.3
    Contagion(286),// v202.3

    ComboUnlimited(287),// v202.3
    SoulExalt(288),// v202.3
    IgnorePCounter(289),// v202.3
    IgnoreAllCounter(290),// v202.3
    IgnorePImmune(291),// v202.3
    IgnoreAllImmune(292),// v202.3
    Unk293(293),// v202.3
    FinalJudgement(294),// v202.3
    IceAura(295),// v202.3

    FireAura(296),// v202.3
    VengeanceOfAngel(297),// v202.3
    HeavensDoor(298),// v202.3
    Preparation(299),// v202.3
    BullsEye(300),// v202.3
    IncEffectHPPotion(301),// v202.3
    IncEffectMPPotion(302),// v202.3
    BleedingToxin(303),// v202.3

    IgnoreMobDamR(304),// v202.3
    Asura(305),// v202.3
    Unk306(306),// v202.3
    FlipTheCoin(307),// v202.3
    UnityOfPower(308),// v202.3
    Stimulate(309),// v202.3
    ReturnTeleport(310),// v202.3
    DropRIncrease(311),// v202.3
    IgnoreMobpdpR(312),// v202.3

    BdR(313),// v202.3
    CapDebuff(314),// v202.3
    Exceed(315),// v202.3
    DiabolikRecovery(316),// v202.3
    FinalAttackProp(317),// v202.3
    ExceedOverload(318),// v202.3
    OverloadCount(319),// v202.3
    BuckShot(320),// v202.3

    FireBomb(321),// v202.3
    HalfstatByDebuff(322),// v202.3
    SurplusSupply(323),// v202.3
    SetBaseDamage(324),// v202.3
    EVAR(325),// v202.3
    NewFlying(326),// v202.3
    AmaranthGenerator(327),// v202.3
    OnCapsule(328),// v202.3

    CygnusElementSkill(329),// v202.3
    StrikerHyperElectric(330),// v202.3
    EventPointAbsorb(331),// v202.3
    EventAssemble(332),// v202.3
    StormBringer(333),// v202.3
    ACCR(334),// v202.3
    DEXR(335),// v202.3
    Albatross(336),// v202.3

    Translucence(337),// v202.3
    PoseType(338),// v202.3
    PoseTypeBool(338),// v202.3
    LightOfSpirit(339),// v202.3
    ElementSoul(340),// v202.3
    GlimmeringTime(341),// v202.3
    TrueSight(342),// v202.3
    SoulExplosion(343),// v202.3
    SoulMP(344),// v202.3

    FullSoulMP(345),// v202.3
    SoulSkillDamageUp(346),// v202.3
    ElementalCharge(347),// v202.3
    Restoration(348),// v202.3
    CrossOverChain(349),// v202.3
    ChargeBuff(350),// v202.3
    Reincarnation(351),// v202.3
    KnightsAura(352),// v202.3

    ChillingStep(353),// v202.3
    DotBasedBuff(354),// v202.3
    BlessEnsenble(355),// v202.3
    ComboCostInc(356),// v202.3
    ExtremeArchery(357),// v202.3
    NaviFlying(358),// v202.3
    QuiverCatridge(359),// v202.3
    AdvancedQuiver(360),// v202.3

    UserControlMob(361),// v202.3
    ImmuneBarrier(362),// v202.3
    ArmorPiercing(363),// v202.3
    ZeroAuraStr(364),// v202.3
    ZeroAuraSpd(365),// v202.3
    CriticalGrowing(366),// v202.3
    QuickDraw(367),// v202.3
    BowMasterConcentration(368),// v202.3

    TimeFastABuff(369),// v202.3
    TimeFastBBuff(370),// v202.3
    GatherDropR(371),// v202.3
    AimBox2D(372),// v202.3
    IncMonsterBattleCaptureRate(373),// v202.3
    CursorSniping(374),// v202.3
    DebuffTolerance(375),// v202.3
    Unk376(376),// v202.3
    DotHealHPPerSecond(377),// v202.3

    SpiritGuard(378),// v202.3
    Unk379(379),// v202.3
    PreReviveOnce(380),// v202.3
    SetBaseDamageByBuff(381),// v202.3
    LimitMP(382),// v202.3
    ReflectDamR(383),// v202.3
    ComboTempest(384),// v202.3
    MHPCutR(385),// v202.3
    MMPCutR(386),// v202.3

    SelfWeakness(387),// v202.3
    ElementDarkness(388),// v202.3
    FlareTrick(389),// v202.3
    Ember(390),// v202.3
    Dominion(391),// v202.3
    SiphonVitality(392),// v202.3
    DarknessAscension(393),// v202.3
    BossWaitingLinesBuff(394),// v202.3

    DamageReduce(395),// v202.3
    ShadowServant(396),// v202.3
    ShadowIllusion(397),// v202.3
    KnockBack(398),// v202.3
    AddAttackCount(399),// v202.3
    ComplusionSlant(400),// v202.3
    JaguarSummoned(401),// v202.3
    JaguarCount(402),// v202.3

    SSFShootingAttack(403),// v202.3
    DevilCry(404),// v202.3
    ShieldAttack(405),// v202.3
    BMageAura(406),// v202.3
    DarkLighting(407),// v202.3
    AttackCountX(408),// v202.3
    BMageDeath(409),// v202.3
    BombTime(410),// v202.3
    NoDebuff(411),// v202.3
    BattlePvPMikeShield(412),// v202.3
    BattlePvPMikeBugle(413),// v202.3
    XenonAegisSystem(414),// v202.3
    AngelicBursterSoulSeeker(415),// v202.3
    HiddenPossession(416),// v202.3
    NightWalkerBat(417),// v202.3
    NightLordMark(418),// v202.3
    WizardIgnite(419),// v202.3
    FireBarrier(420),// v202.3
    ChangeFoxMan(421),// v202.3
    DivineEcho(422),// v203.2 (Paladin V Buff).
    Unk423(423),
    Unk424(424),
    Unk425(425),
    RIFT_OF_DAMNATION(426),// v202.3
    Unk427(427),
    Unk428(428),
    Unk429(429),
    Unk430(430),
    Unk431(431),
    BattlePvPHelenaMark(432),// v202.3
    BattlePvPHelenaWindSpirit(433),// v202.3
    BattlePvPLangEProtection(434),// v202.3
    BattlePvPLeeMalNyunScaleUp(435),// v202.3
    BattlePvPRevive(436),// v202.3
    PinkbeanAttackBuff(437),// v202.3
    PinkbeanRelax(438),// v202.3
    PinkbeanRollingGrade(439),// v202.3
    PinkbeanYoYoStack(440),// v202.3
    RandAreaAttack(441),// v202.3
    Unk442(442),
    NextAttackEnhance(443),
    AranBeyonderDamAbsorb(444),
    AranCombotempastOption(445),
    NautilusFinalAttack(446),
    ViperTimeLeap(447),
    RoyalGuardState(448),// v202.3
    RoyalGuardPrepare(449),// v202.3
    MichaelSoulLink(450),// v202.3
    MichaelStanceLink(451),// v202.3
    TriflingWhimOnOff(452),// v202.3
    AddRangeOnOff(453),// v202.3

    KinesisPsychicPoint(454),// v202.3
    KinesisPsychicOver(455),// v202.3
    KinesisPsychicShield(456),// v202.3
    KinesisIncMastery(457),// v202.3
    KinesisPsychicEnergeShield(458),// v202.3
    BladeStance(459),// v202.3
    DebuffActiveSkillHPCon(460),// v202.3
    DebuffIncHP(461),// v202.3

    BowMasterMortalBlow(462),// v202.3
    AngelicBursterSoulResonance(463),// v202.3
    Fever(-1),
    IgnisRore(464),// v202.3
    RpSiksin(465),// v202.3
    TeleportMasteryRange(466),// v202.3
    FixCoolTime(467),// v202.3
    IncMobRateDummy(468),// v202.3

    AdrenalinBoost(469),// v202.3
    AranSmashSwing(470),// v202.3
    AranDrain(471),// v202.3
    AranBoostEndHunt(472),// v202.3
    HiddenHyperLinkMaximization(473),// v202.3
    RWCylinder(474),// v202.3
    RWCombination(475),// v202.3
    Unk476(476),// v202.3
    RWMagnumBlow(477),// v202.3

    RWBarrier(478),// v202.3
    RWBarrierHeal(479),// v202.3
    RWMaximizeCannon(480),// v202.3
    RWOverHeat(481),// v202.3
    UsingScouter(482),// v202.3
    RWMovingEvar(483),// v202.3
    Stigma(484),// v202.3
    Unk485(485),
    Unk486(486),
    Unk487(487),
    Unk488(488),
    Unk489(489),
    Unk490(490),
    Unk491(491),
    Unk492(492),
    LightningCascade(493),// v202.3
    BulletBarrage(494),// v202.3
    Unk495(495),
    AuraScythe(496),// v202.3
    Unk497(497),
    Unk498(498),
    Unk499(499),
    Unk500(500),
    Unk501(501),
    ManaOverload(502),// v202.3
    Unk503(503),
    Unk504(504),
    SpreadThrow(505),
    WindEnergy(506),
    MassDestructionRockets(507),// Used for:   Cannonneer 1st V  |  Battle Mage 2nd V  |  Buccaneer 3rd V
    ShadowAssault(508),
    Unk509(509),
    Unk510(510),
    Unk511(511),
    BlitzShield(512),
    Unk513(513),
    FreudWisdom(514),// v202.3 or SKILL_COOLTIME_REDUCE_R
    CoreOverload(515),// v202.3
    Spotlight(516),// angelic buster v skill
    Unk517(517),// v202.3
    Unk518(518),
    CrystallineWings(519),// v203.2
    Unk520(520),
    Unk521(521),
    Overdrive(522),// v202.3 or attack power
    EtherealForm(523),// v202.3
    LastResort(524),// v202.3
    Unk525(525),
    Unk526(526),// v202.3
    Unk527(527),// v202.3
    Unk528(528),
    Unk529(529),// v202.3
    Unk530(530),
    Unk531(531),
    Unk532(532),
    Unk533(533),
    Unk534(534),
    Unk535(535),
    Unk536(536),
    Unk537(537),
    Unk538(538),// v202.3
    Unk539(539),// v202.3
    Unk540(540),
    Unk541(541),
    SpecterEnergy(542),// v202.3
    SpecterState(543),// v202.3
    BasicCast(544),// v202.3
    ScarletCast(545),// v202.3
    GustCast(546),// v202.3
    AbyssalCast(547),// v202.3
    ImpendingDeath(548),// v202.3
    AbyssalRecall(549),// v202.3
    ChargeSpellAmplifier(550),// v202.3
    InfinitySpell(551),// v202.3
    ConversionOverdrive(552),// v202.3
    Unk553(553),
    Unk554(554),
    Unk555(555),
    Unk556(556),
    Unk557(557),
    Unk558(558),
    Unk559(559),
    Unk560(560),
    Unk561(561),
    Unk562(562),
    Unk563(563),
    Unk564(564),
    Unk565(565),
    Unk566,
    Unk567,
    Unk568,
    Unk569,
    Unk570,
    Unk571,
    Unk572,
    Unk573,
    Unk574,
    Unk575,
    Unk576,
    Unk577,
    Unk578,
    Unk579,
    HayatoStance(580),// v202.3
    HayatoStanceBonus(581),// v202.3
    Unk582,
    Unk583,
    Unk584,
    Unk585,
    Unk586,
    Unk587,
    EyeForEye(588),// v202.3
    WillowDodge(589),// v202.3
    Unk465(590),// v202.3
    HayatoPAD(591),// v202.3
    HayatoHPR(592),// v202.3
    HayatoMPR(593),// v202.3
    HayatoBooster(594),// v202.3
    Unk595,// v202.3
    Unk596,// v202.3
    Jinsoku(597),// v202.3
    HayatoCr(598),// v202.3
    HakuBlessing(599),// v202.3
    HayatoBoss(600),// v202.3
    BattoujutsuAdvance(601),// v202.3
    Unk602,// v202.3
    Unk603,// v202.3
    BlackHeartedCurse(604),// v202.3
    BeastMode(605),// v202.3
    TeamRoar(606),// v202.3
    Unk607(607),// v202.3
    Unk608(608),// v202.3
    Unk609(609),// v202.3
    Unk610(610),// v202.3
    Unk611(611),// v202.3
    Unk612(612),// v202.3
    Unk613(613),// v202.3
    Unk614(614),// v202.3
    Unk615(615),// v202.3
    Unk616(616),// v202.3
    Unk617(617),// v202.3
    Unk618(618),// v202.3
    Unk619(619),// v202.3
    Unk620,
    Unk621,
    Unk622,
    Unk623,
    Unk624,
    Unk625,
    Unk626,
    EnergyCharged(627),// v202.3
    DashSpeed(628),// v202.3
    DashJump(629),// v202.3
    RideVehicle(630),// v202.3
    PartyBooster(631),// v202.3 - or 631 according to my sniffs
    GuidedBullet(632),// v202.3
    Undead(633),// v202.3
    RideVehicleExpire(634),// v202.3
    ;

    private int bitPos;
    private int val;
    private int pos;
    public static final int length = 32;
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    private static final List<CharacterTemporaryStat> ORDER = Arrays.asList(
            STR, INT, DEX, LUK,
            PAD, PDD, MAD, ACC, EVA, EVAR,
            Craft, Speed, Jump,
            EMHP, EMMP, EPAD, EMAD, EPDD,
            MagicGuard, DarkSight, Booster, PowerGuard, Guard,
            MaxHP, MaxMP, Invincible, SoulArrow, Stun, Shock,
            Unk82, Unk83, Unk84, Poison, Seal, Darkness, ComboCounter, WeaponCharge, ElementalCharge,
            HolySymbol, MesoUp, ShadowPartner, PickPocket, MesoGuard, Thaw, Weakness, WeaknessMdamage,
            Curse, Slow, TimeBomb, BuffLimit, Team, DisOrder, //Thread,
            Morph, Ghost,
            Regen, BasicStatUp, Stance, SharpEyes, ManaReflection, Attract, NoBulletConsume, StackBuff, Trinity,
            Infinity, AdvancedBless, IllusionStep, Blind, Concentration, BanMap, MaxLevelBuff, Barrier, DojangShield, ReverseInput,
            Unk114, Unk115, MesoUpByItem, ItemUpByItem, RespectPImmune, RespectMImmune, DefenseAtt, DefenseState, DojangBerserk, DojangInvincible, SoulMasterFinal,
            WindBreakerFinal, ElementalReset, HideAttack, EventRate, ComboAbilityBuff, ComboDrain, ComboBarrier, PartyBarrier,
            BodyPressure, RepeatEffect, ExpBuffRate, StopPortion, StopMotion, Fear, MagicShield, MagicResistance, SoulStone,
            Flying, NewFlying, NaviFlying, Frozen, Frozen2, Web,
            Enrage, NotDamaged, FinalCut, HowlingAttackDamage, BeastFormDamageUp, Dance, OnCapsule,
            Cyclone, Unk165, Conversion, Revive, PinkbeanMinibeenMove, Sneak, Mechanic, DrawBack,
            BeastFormMaxHP, Dice, BlessingArmor, BlessingArmorIncPAD,
            DamR, TeleportMasteryOn, CombatOrders, Beholder, DispelItemOption, DispelItemOptionByField,
            Inflation, OnixDivineProtection, Bless, Explosion, DarkTornado, IncMaxHP, IncMaxMP,
            PVPDamage, PVPDamageSkill, PvPScoreBonus, PvPInvincible, PvPRaceEffect,
            HolyMagicShell, InfinityForce, AmplifyDamage, KeyDownTimeIgnore, MasterMagicOn,
            AsrR, AsrRByItem, TerR, DamAbsorbShield, Roulette, Event, SpiritLink,
            CriticalBuff, DropRate, PlusExpRate, ItemInvincible, ItemCritical, ItemEvade,
            Event2, VampiricTouch, DDR, IncTerR, IncAsrR, DeathMark, PainMark,
            UsefulAdvancedBless, Lapidification, VampDeath, VampDeathSummon,
            VenomSnake, CarnivalAttack, CarnivalDefence, CarnivalExp, SlowAttack, PyramidEffect, HollowPointBullet, KeyDownMoving, KeyDownAreaMoving, CygnusElementSkill,
            IgnoreTargetDEF, Invisible, ReviveOnce, AntiMagicShell,
            EnrageCr, EnrageCrDam, BlessOfDarkness, LifeTidal,
            Judgement, DojangLuckyBonus, HitCriDamR, Larkness, SmashStack, ReshuffleSwitch, SpecialAction, ArcaneAim,
            StopForceAtomInfo, SoulGazeCriDamR, SoulRageCount, PowerTransferGauge, AffinitySlug, SoulExalt, HiddenPieceOn,
            BossShield, MobZoneState, GiveMeHeal, TouchMe, Contagion, ComboUnlimited,
            IgnorePCounter, IgnoreAllCounter, IgnorePImmune, IgnoreAllImmune, Unk293,
            FinalJudgement, KnightsAura, IceAura, FireAura, VengeanceOfAngel, HeavensDoor, Preparation, BullsEye, IncEffectHPPotion, IncEffectMPPotion,
            SoulMP, FullSoulMP, SoulSkillDamageUp,
            BleedingToxin, IgnoreMobDamR, Asura, Unk306, FlipTheCoin, UnityOfPower, Stimulate, ReturnTeleport, CapDebuff,
            DropRIncrease, IgnoreMobpdpR, BdR,
            Exceed, DiabolikRecovery, FinalAttackProp, ExceedOverload, DevilishPower,
            OverloadCount, BuckShot, FireBomb, HalfstatByDebuff, SurplusSupply, SetBaseDamage, AmaranthGenerator,
            StrikerHyperElectric, EventPointAbsorb, EventAssemble, StormBringer, ACCR, DEXR, Albatross, Translucence,
            PoseType, LightOfSpirit, ElementSoul, GlimmeringTime, Restoration, ComboCostInc, ChargeBuff,
            TrueSight, CrossOverChain, ChillingStep, Reincarnation, DotBasedBuff, BlessEnsenble, ExtremeArchery,
            QuiverCatridge, AdvancedQuiver, UserControlMob, ImmuneBarrier, ArmorPiercing, ZeroAuraStr, ZeroAuraSpd,
            CriticalGrowing, QuickDraw, BowMasterConcentration, TimeFastABuff, TimeFastBBuff, GatherDropR, AimBox2D,
            IncMonsterBattleCaptureRate, CursorSniping, DebuffTolerance, Unk376, DotHealHPPerSecond, SpiritGuard, Unk379,
            PreReviveOnce, SetBaseDamageByBuff, LimitMP, ReflectDamR, ComboTempest, MHPCutR, MMPCutR, SelfWeakness,
            ElementDarkness, FlareTrick, Ember, Dominion, SiphonVitality, DarknessAscension, BossWaitingLinesBuff,
            DamageReduce, ShadowServant, ShadowIllusion,
            AddAttackCount, ComplusionSlant, JaguarSummoned, JaguarCount, SSFShootingAttack, DevilCry, ShieldAttack, BMageAura,
            DarkLighting, AttackCountX, BMageDeath, BombTime, NoDebuff,
            XenonAegisSystem, AngelicBursterSoulSeeker, HiddenPossession, NightWalkerBat, NightLordMark, WizardIgnite,
            Unk423, Unk424,
            BattlePvPHelenaMark, BattlePvPHelenaWindSpirit, BattlePvPLangEProtection, BattlePvPLeeMalNyunScaleUp, BattlePvPRevive, PinkbeanAttackBuff,
            RandAreaAttack, Unk442, BattlePvPMikeShield, BattlePvPMikeBugle,
            PinkbeanRelax, PinkbeanYoYoStack, WindEnergy,
            NextAttackEnhance, AranBeyonderDamAbsorb, AranCombotempastOption, NautilusFinalAttack, ViperTimeLeap,
            RoyalGuardState, RoyalGuardPrepare, MichaelSoulLink, MichaelStanceLink, TriflingWhimOnOff, AddRangeOnOff,
            KinesisPsychicPoint, KinesisPsychicOver, KinesisPsychicShield, KinesisIncMastery, KinesisPsychicEnergeShield,
            BladeStance, DebuffActiveSkillHPCon, DebuffIncHP, BowMasterMortalBlow, AngelicBursterSoulResonance, Fever,
            IgnisRore, RpSiksin, TeleportMasteryRange, FireBarrier, ChangeFoxMan,
            FixCoolTime, IncMobRateDummy, AdrenalinBoost, AranSmashSwing, AranDrain, AranBoostEndHunt, HiddenHyperLinkMaximization,
            RWCylinder, RWCombination, Unk476, RWMagnumBlow, RWBarrier, RWBarrierHeal, RWMaximizeCannon, RWOverHeat,
            RWMovingEvar, Stigma, Unk485, Unk486, Unk487, Unk488, Unk489, Unk490, Unk491, Unk492,
            LightningCascade, BulletBarrage, AuraScythe, Unk497, Unk498, Unk499, Unk500, DivineEcho,
            Unk501, ManaOverload, Unk503, Unk504, SpreadThrow,
            MassDestructionRockets, ShadowAssault, Unk509, Unk510, Unk511, BlitzShield, Unk513, FreudWisdom, CoreOverload, Unk425,
            CrystallineWings, Unk520, RIFT_OF_DAMNATION,
            Unk517, Spotlight, Unk162, Unk518,
            Unk521, Overdrive, EtherealForm, LastResort, Unk525, Unk526, Unk527, Unk528, Unk529, Unk163,
            Unk530, Unk531, Unk532, Unk534, Unk535, Unk536, Unk537, Unk538, Unk539, Unk540, Unk541,
            SpecterEnergy, SpecterState, BasicCast, ScarletCast, GustCast, AbyssalCast, ImpendingDeath, AbyssalRecall, ChargeSpellAmplifier,
            InfinitySpell, ConversionOverdrive, Unk553, Unk554, Unk555, Unk556, Unk557,
            Unk559, Unk560, Unk561, Unk562, Unk427,
            Unk563, Unk428,
            Unk564, Unk565, Unk566, Unk567, Unk429, Unk430,
            Unk568, Unk569, DropPer, Unk431,
            Unk570, Unk571, Unk572, Unk573, Unk574, IncMaxDamage,
            Unk575, Unk576, Unk578, Unk579, IndieUnk13,
            HayatoStance, EyeForEye, HayatoStanceBonus,
            Unk583, Unk584, Unk585, Unk586, Unk587,
            HayatoPAD, HayatoHPR, HayatoMPR, HayatoBooster, Unk595, Unk596, Jinsoku, HayatoCr, Unk582,
            HakuBlessing, HayatoBoss, BattoujutsuAdvance, Unk602,
            TeamRoar, Unk607, Unk608, Unk609, Unk610, Unk611, Unk612, Unk613,
            Unk615, Unk616, Unk617, Unk618, Unk619
    );

    private static final List<CharacterTemporaryStat> REMOTE_ORDER = Arrays.asList(
            Speed, Poison, Seal, Darkness, WeaponCharge, ElementalCharge, Stun, Shock, Unk84, Unk83, Weakness, WeaknessMdamage,
            Curse, Slow, PvPRaceEffect, TimeBomb, Team, DisOrder, /*Thread*/ Unk82, ShadowPartner,  Morph, Ghost, Attract, Magnet, MagnetArea,
            NoBulletConsume, BanMap, Barrier, DojangShield, ReverseInput, RespectPImmune, RespectMImmune, DefenseAtt, DefenseState,
            DojangBerserk, RepeatEffect, Unk602, StopPortion, StopMotion, Fear, MagicShield, Frozen, Frozen2, Web, DrawBack, FinalCut,
            OnCapsule, Mechanic, Inflation, Explosion, DarkTornado, AmplifyDamage, HideAttack, DevilishPower, SpiritLink, Event, Event2,
            DeathMark, PainMark, Lapidification, VampDeath, VampDeathSummon, VenomSnake, PyramidEffect, KillingPoint, PinkbeanRollingGrade,
            IgnoreTargetDEF, Invisible, Judgement, KeyDownAreaMoving, StackBuff, BlessOfDarkness, Larkness, ReshuffleSwitch, SpecialAction,
            StopForceAtomInfo, SoulGazeCriDamR, PowerTransferGauge, BlitzShield, AffinitySlug, SoulExalt, HiddenPieceOn, SmashStack, MobZoneState,
            GiveMeHeal, TouchMe, Contagion, ComboUnlimited, IgnorePCounter, IgnoreAllCounter, IgnorePImmune, IgnoreAllImmune, Unk293,
            FinalJudgement, KnightsAura, IceAura, FireAura, HeavensDoor, DamAbsorbShield, NotDamaged, BleedingToxin,
            WindBreakerFinal, IgnoreMobDamR, Asura, Unk306, UnityOfPower, Stimulate, ReturnTeleport, CapDebuff, OverloadCount, FireBomb,
            SurplusSupply, NewFlying, NaviFlying, AmaranthGenerator, CygnusElementSkill, StrikerHyperElectric, EventPointAbsorb, EventAssemble,
            Albatross, Translucence, PoseTypeBool, LightOfSpirit, ElementSoul, GlimmeringTime, Reincarnation, Beholder, QuiverCatridge, ArmorPiercing,
            ZeroAuraStr, ZeroAuraSpd, ImmuneBarrier, FullSoulMP, AntiMagicShellBool, Dance, Unk379, Unk425, ComboTempest, HalfstatByDebuff,
            ComplusionSlant, JaguarSummoned, BMageAura, BombTime, Unk491, Unk492, LightningCascade, BulletBarrage, Unk495, AuraScythe,
            Unk497, DarkLighting, AttackCountX, FireBarrier, KeyDownMoving, MichaelSoulLink, KinesisPsychicEnergeShield, BladeStance,
            IgnisRore, AdrenalinBoost, RWBarrier, Unk476, RWMagnumBlow, Unk253, Unk254, Unk255, Unk256, Unk257, Stigma, DivineEcho, Unk503,
            Unk504, Unk485, ManaOverload, CursorSniping, Unk517, Spotlight, CoreOverload, FreudWisdom, ComboCounter, Overdrive, EtherealForm,
            LastResort, Unk525, Unk526, Unk527, Unk528, Unk529, Unk520, Unk530, Unk531, Unk532, Unk533,
            SpecterState, ImpendingDeath, Unk556, Unk531, Unk565, GrandGuardian, HayatoStanceBonus, BeastMode, TeamRoar, Unk586,
            HayatoBooster, Unk587, HayatoPAD, HayatoHPR, HayatoMPR, HayatoCr, HayatoBoss, Stance, BattoujutsuAdvance, Unk603,
            BlackHeartedCurse, EyeForEye, Unk608, Unk612, Unk613, Unk614, Unk616, Unk617, Unk618, Unk585, Unk513
    );

    CharacterTemporaryStat(int bitPos) {
        this.bitPos = bitPos;
        this.val = 1 << (31 - bitPos % 32);
        this.pos = bitPos / 32;
    }

    // for Unks only
    CharacterTemporaryStat() {
        int bitPos = -1;
        if (name().contains("Unk")) {
            bitPos = Integer.parseInt(name().replace("Unk", ""));
        }
        this.bitPos = bitPos;
        this.val = 1 << (31 - bitPos % 32);
        this.pos = bitPos / 32;
    }

    public boolean isEncodeInt() {
        switch (this) {
            case CarnivalDefence:
            case SpiritLink:
            case DojangLuckyBonus:
            case SoulGazeCriDamR:
            case PowerTransferGauge:
            case ReturnTeleport:
            case ShadowPartner:
            case AranSmashSwing:
            case SetBaseDamage:
            case QuiverCatridge:
            case ImmuneBarrier:
            case NaviFlying:
            case Dance:
            case SetBaseDamageByBuff:
            case DotHealHPPerSecond:
            case SpiritGuard:
            case IncMaxDamage:
            case Unk612:
            case MagnetArea:
            case DivineEcho:
            case Unk306:
            case VampDeath:
            case BlitzShield:
            case Unk162:
            case RWBarrier:
                return true;
            default:
                return false;
        }
    }

    public boolean isIndie() {
        return getBitPos() < IndieStatCount.getBitPos();
    }

    public boolean isMovingEffectingStat() {
        switch (this) {
            case Speed:
            case Jump:
            case Stun:
            case Weakness:
            case Slow:
            case Morph:
            case Ghost:
            case BasicStatUp:
            case Attract:
            case DashSpeed:
            case DashJump:
            case Flying:
            case Frozen:
            case Frozen2:
            case Lapidification:
            case IndieSpeed:
            case IndieJump:
            case KeyDownMoving:
            case EnergyCharged:
            case Mechanic:
            case Magnet:
            case MagnetArea:
            case VampDeath:
            case VampDeathSummon:
            case GiveMeHeal:
            case DarkTornado:
            case NewFlying:
            case NaviFlying:
            case UserControlMob:
            case Dance:
            case SelfWeakness:
            case BattlePvPHelenaWindSpirit:
            case IndieUnk10:
            case BattlePvPLeeMalNyunScaleUp:
            case TouchMe:
            case IndieForceSpeed:
            case IndieForceJump:
            case RideVehicle:
            case RideVehicleExpire:
            case Unk538:
            case Unk539:
                return true;
            default:
                return false;
        }
    }

    public int getVal() {
        return val;
    }

    public int getPos() {
        return pos;
    }

    public int getOrder() {
        return ORDER.indexOf(this);
    }

    public int getRemoteOrder() {
        return REMOTE_ORDER.indexOf(this);
    }

    public boolean isRemoteEncode4() {
        switch (this) {
            case NoBulletConsume:// v202.3
            case RespectPImmune:// v202.3
            case RespectMImmune:// v202.3
            case DefenseAtt:// v202.3
            case DefenseState:// v202.3
            case MagicShield:// v202.3
            case PyramidEffect:// v202.3
            case BlessOfDarkness:// v202.3
            case Unk306:// v202.3
            case ImmuneBarrier:// v202.3
            case Dance:// v202.3
            case Unk379:// v202.3
            case Unk425:// v202.3
            case SpiritGuard:
            case KinesisPsychicEnergeShield:// v202.3
            case AdrenalinBoost:// v202.3
            case RWBarrier:// v202.3
            case Unk476:// v202.3
            case RWMagnumBlow:// v202.3
            case DivineEcho:// v202.3
            case Unk503:// v202.3
            case Unk531:// v202.3
            case Unk612:// v202.3
            case Unk613:// v202.3
            case Unk614:// v202.3
            case Unk617:// v202.3
            case Unk618:// v202.3
            case HayatoStance:
            //case Unk487:
            case Unk488:
            case Unk489:
                return true;
            default:
                return false;
        }
    }

    public boolean isRemoteEncode1() {
        switch (this) {
            case Speed:// v202.3
            case Poison:// v202.3
            case Seal:// v202.3
            case Shock:// v202.3
            case Team:// v202.3
            case Cyclone:
            case OnCapsule:// v202.3
            case KillingPoint:// v202.3
            case PinkbeanRollingGrade:// v202.3
            case ReturnTeleport:// v202.3
            case FireBomb:// v202.3
            case SurplusSupply:// v202.3
            case Unk585:
                return true;
            default:
                return false;
        }
    }

    public boolean isNotEncodeReason() {
        switch (this) {
            case Speed:// v202.3
            case Poison:// v202.3
            case Seal:// v202.3
            case ElementalCharge:// v202.3
            case Shock:// v202.3
            case Team:// v202.3
            case Ghost:// v202.3
            case NoBulletConsume:// v202.3
            case RespectPImmune:// v202.3
            case RespectMImmune:// v202.3
            case DefenseAtt:// v202.3
            case DefenseState:// v202.3
            case MagicShield:// v202.3
            case Cyclone:
            case OnCapsule:// v202.3
            case PyramidEffect:// v202.3
            case KillingPoint:// v202.3
            case PinkbeanRollingGrade:// v202.3
            case StackBuff:// v202.3
            case BlessOfDarkness:// v202.3
            case SurplusSupply:// v202.3
            case ImmuneBarrier:// v202.3
            case AdrenalinBoost:// v202.3
            case RWBarrier:// v202.3
            case Unk476:// v202.3
            case RWMagnumBlow:// v202.3
            case Unk504:// v202.3
            case Unk485:// v202.3
            case ManaOverload:// v202.3
            case Unk530:// v202.3
            case Unk613:// v202.3
            case Unk614:// v202.3
            case Unk617:// v202.3
            case Unk618:// v202.3
            case HayatoStance:
            case Unk488:
            case Unk489:
            //case Unk460:
                return true;
            default:
                return false;
        }
    }

    public boolean isNotEncodeAnything() {
        switch (this) {
            // not encoded in client
            case DarkSight:
            case SoulArrow:
            case DojangInvincible:
            case Flying:
            case Sneak:
            case BeastFormDamageUp:
            case BlessingArmor:
            case BlessingArmorIncPAD:
            case HolyMagicShell:
            case VengeanceOfAngel:
            case UserControlMob:
            case Unk565:
            // Special encoding
            case FullSoulMP:// v202.3
            case AntiMagicShellBool:// v202.3
            case PoseTypeBool:
                return true;
            default:
                return false;
        }
    }

    public static void main(String[] args) {
        int a = Stigma.bitPos;
//        int val = 1 << (31 - (a & 0x1f));
//        int pos = a >> 5;
        int val = 0x1000;
        int pos = 0;
        log.debug(String.format("value 0x%04x, pos %d", val, pos));
        for(CharacterTemporaryStat cts : values()) {
            if(cts.getVal() == val && cts.getPos() == pos) {
                log.debug("Corresponds to " + cts);
            }
        }
//        for (CharacterTemporaryStat cts : values()) {
//            val = cts.getVal();
//            for (int i = 0; i < 32; i++) {
//                if (1 << i == val) {
//                    val = 31 - i;
//                }
//            }
//            if (val % 8 == 0) {
//                System.out.println();
//            }
//            System.out.println(String.format("%s(%d),", cts.toString(), (cts.getPos() * 32) + val));
//        }
    }

    @Override
    public int compare(CharacterTemporaryStat o1, CharacterTemporaryStat o2) {
        if (o1.getPos() < o2.getPos()) {
            return -1;
        } else if (o1.getPos() > o2.getPos()) {
            return 1;
        }
        // hacky way to circumvent java not having unsigned ints
        int o1Val = o1.getVal();
        if (o1Val == 0x8000_0000) {
            o1Val = Integer.MAX_VALUE;
        }
        int o2Val = o2.getVal();
        if (o2Val == 0x8000_0000) {
            o2Val = Integer.MAX_VALUE;
        }

        if (o1Val > o2Val) {
            // bigger value = earlier in the int => smaller
            return -1;
        } else if (o1Val < o2Val) {
            return 1;
        }
        return 0;
    }

    public int getBitPos() {
        return bitPos;
    }
}
