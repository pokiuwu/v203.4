package net.swordie.ms.life.mob;

public enum MobStat {
    PAD(0),
    PDR(1),
    MAD(2),
    MDR(3),
    ACC(4),
    EVA(5),
    Speed(6),
    Stun(7),

    Freeze(8),
    Poison(9),
    Seal(10),
    Darkness(11),
    PowerUp(12),
    MagicUp(13),
    PGuardUp(14),
    MGuardUp(15),

    PImmune(16),
    MImmune(17),
    Web(18),
    HardSkin(19),
    Ambush(20),
    Venom(21),
    Blind(22),
    SealSkill(23),

    Dazzle(24),
    PCounter(25), // nOption = % of dmg, mOption = % chance
    MCounter(26),
    RiseByToss(27),
    BodyPressure(28),
    Weakness(29),
    Showdown(30),
    MagicCrash(31),

    DamagedElemAttr(32),
    Dark(33),
    Mystery(34),
    AddDamParty(35),
    HitCriDamR(36),
    Fatality(37),
    Lifting(38),
    DeadlyCharge(39),

    Smite(40),
    AddDamSkill(41),
    Incizing(42),
    DodgeBodyAttack(43),
    DebuffHealing(44),
    AddDamSkill2(45),
    BodyAttack(46),
    TempMoveAbility(47),

    FixDamRBuff(48),
    ElementDarkness(49),
    AreaInstallByHit(50),
    BMageDebuff(51),
    JaguarProvoke(52),
    JaguarBleeding(53),
    DarkLightning(54),
    PinkBeanFlowerPot(55),

    BattlePvPHelenaMark(56),
    PsychicLock(57),
    PsychicLockCoolTime(58),
    PsychicGroundMark(59),

    PowerImmune(56),
    PsychicForce(61),
    MultiPMDR(62),
    ElementResetBySummon(63),

    BahamutLightElemAddDam(64),
    BossPropPlus(65),
    MultiDamSkill(66),
    RWLiftPress(67),
    RWChoppingHammer(68),
    TimeBomb(69),
    Treasure(70),
    AddEffect(71),

    Unknown1(72),
    Unknown2(73),
    Invincible(74),
    Explosion(75),
    HangOver(76),
    BurnedInfo(86),// v202.3
    InvincibleBalog(78),
    ExchangeAttack(79),

    ExtraBuffStat(89),// v200.3
    LinkTeam(81),
    SoulExplosion(82),
    SeperateSoulP(83),
    SeperateSoulC(84),
    Ember(85),
    TrueSight(86),
    Laser(87),
    ;

    private int val, pos, bitPos;

    MobStat(int val, int pos) {
        this.val = val;
        this.pos = pos;
    }

    MobStat(int bitPos) {
        this.bitPos = bitPos;
        this.val = 1 << (31 - bitPos % 32);
        this.pos = bitPos / 32;
    }

    public int getPos() {
        return pos;
    }

    public int getVal() {
        if(this == BurnedInfo) {
            return 0x40000;
        }
        return val;
    }

    public boolean isMovementAffectingStat() {
        switch(this) {
            case Speed:
            case Stun:
            case Freeze:
            case RiseByToss:
            case Lifting:
            case Smite:
            case TempMoveAbility:
            case RWLiftPress:
                return true;
            default:
                return false;
        }
    }

    public int getBitPos() {
        return bitPos;
    }
}
