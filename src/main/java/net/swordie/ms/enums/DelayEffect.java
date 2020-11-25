package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created by MechAviv on 12/22/2018.
 */
public enum DelayEffect {
    DEFAULT(0x0),
    REINCARNATION(0x1),
    DRAGON_FURY(0x2),
    PVP_CHAMPION(0x8),
    PVP_RAGE(0x10),
    UNK_20(0x20);

    private int val;

    DelayEffect(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
