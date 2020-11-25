package net.swordie.ms.enums;

import net.swordie.ms.util.Util;

import java.util.Arrays;

/**
 * Created on 28/8/2018.
 */
public enum FlameStat {
    STR(0),
    DEX(1),
    INT(2),
    LUK(3),
    STRDEX(4),
    STRINT(5),
    STRLUK(6),
    DEXINT(7),
    DEXLUK(8),
    INTLUK(9),
    Attack(10),
    MagicAttack(11),
    Defense(12),
    MaxHP(13),
    MaxMP(14),
    Speed(15),
    Jump(16),
    AllStats(17),
    BossDamage(18),
    Damage(19),
    LevelReduction(20);

    private int val;

    FlameStat(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static FlameStat getByVal(int val) {
        return Util.findWithPred(Arrays.asList(values()), stat -> stat.getVal() == val);
    }
}
