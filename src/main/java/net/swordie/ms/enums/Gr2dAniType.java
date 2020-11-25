package net.swordie.ms.enums;

/**
 * Created by MechAviv on 3/9/2019.
 */
public enum Gr2dAniType {
    GA_STOP(0x0),
    GA_WAIT(0x100),
    GA_CLEAR(0x200),
    GA_NORMAL(0x0),
    GA_FIRST(0x10),
    GA_REPEAT(0x20),
    GA_REVERSE(0x40),
    GA_REVERSE_WITH_CLEAR(0x240);
    private final int val;

    Gr2dAniType(int val) {
        this.val = val;
    }

    public static Gr2dAniType getByVal(int val) {
        if (val >= 0 && val < values().length) {
            return values()[val];
        }
        return null;
    }

    public int getVal() {
        return val;
    }
}
