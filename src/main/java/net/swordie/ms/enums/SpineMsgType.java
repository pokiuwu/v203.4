package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created by MechAviv on 3/2/2019.
 */
public enum SpineMsgType {
    ALPHA(1),
    PLAY(2),
    ADD_PLAY(3),
    CLEAR_TRACKS(4),
    PLAYRATE(5),
    STOP(6);

    private int val;

    SpineMsgType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static SpineMsgType getTypeByVal(int val) {
        return Arrays.stream(values()).filter(uet -> uet.getVal() == val).findAny().orElse(null);
    }
}
