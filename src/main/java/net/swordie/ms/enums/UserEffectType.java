package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created on 6/7/2018.
 */
public enum UserEffectType {
    LevelUp(0),// v202.3
    SkillUse(1),
    SkillUseBySummoned(2),
    // 3
    SkillAffected(4),// v202.3
    SkillAffected_Ex(5),// v202.3
    SkillAffected_Select(6),// v202.3
    SkillSpecialAffected(7),// v202.3
    Quest(8),// v202.3
    Pet(9),// v202.3
    SkillSpecial(10),// v202.3
    Resist(11),// v202.3
    ProtectOnDieItemUse(12),// v202.3
    PlayPortalSE(13),// v202.3
    JobChanged(14),// v202.3
    QuestComplete(15),// v202.3
    IncDecHPEffect(16),// v202.3
    BuffItemEffect(17),// v202.3
    SquibEffect(18),// v202.3
    MonsterBookCardGet(19),// v202.3
    LotteryUse(20),// v202.3
    ItemLevelUp(21),// v202.3
    ItemMaker(22),// v202.3
    // 23
    ExpItemConsumed(24),// v202.3
    FieldExpItemConsumed(25),// v202.3
    ReservedEffect(26),// v202.3
    UnkAtm1(27),// v202.3
    UpgradeTombItemUse(28),// v202.3
    BattlefieldItemUse(29),// v202.3
    UnkAtm2(30),// v202.3
    AvatarOriented(31),// v202.3
    AvatarOrientedRepeat(32),// v202.3
    AvatarOrientedMultipleRepeat(33),// v202.3
    IncubatorUse(34),// v202.3
    PlaySoundWithMuteBGM(35),// v202.3
    PlayExclSoundWithDownBGM(36),// v202.3
    SoulStoneUse(37),// v202.3
    IncDecHPEffect_EX(38),// v202.3
    IncDecHPRegenEffect(39),// v202.3
    EffectUOL(40),// v202.3
    PvPRage(41),// v202.3
    PvPChampion(42),// v202.3
    PvPGradeUp(43),// v202.3
    PvPRevive(44),// v202.3
    JobEffect(45),// v202.3
    FadeInOut(46),// v202.3
    MobSkillHit(47),// v202.3
    AswanSiegeAttack(48),// v202.3
    BlindEffect(49),// v202.3
    BossShieldCount(50),// v202.3
    ResetOnStateForOnOffSkill(51),// v202.3
    JewelCraft(52),// v202.3
    ConsumeEffect(53),// v202.3
    PetBuff(54),// v202.3
    LotteryUIResult(55),// v202.3
    LeftMonsterNumber(56),// v202.3
    ReservedEffectRepeat(57),// v202.3
    RobbinsBomb(58),// v202.3
    SkillMode(59),// v202.3
    ActQuestComplete(60),// v202.3
    Point(61),// v202.3
    SpeechBalloon(62),// v202.3
    TextEffect(63),// v202.3
    SkillPreLoopEnd(64),
    Aiming(65),
    // 66
    PickUpItem(67),// v202.3
    BattlePvP_IncDecHp(68),// v202.3
    BiteAttack_ReceiveSuccess(69),// v202.3
    BiteAttack_ReceiveFail(70),// v202.3
    IncDecHPEffect_Delayed(71),// not sure
    Lightness(72),// not sure
    SetUsed(73),// not sure
    ;

    private byte val;

    UserEffectType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }

    public static UserEffectType getTypeByVal(int val) {
        return Arrays.stream(values()).filter(uet -> uet.getVal() == val).findAny().orElse(null);
    }
}
