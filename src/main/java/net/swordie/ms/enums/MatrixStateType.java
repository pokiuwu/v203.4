package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created by MechAviv on 2/16/2019.
 */
public enum MatrixStateType {
    DISASSEMBLED(0), INACTIVE(1), ACTIVE(2);
    private int val;

    MatrixStateType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static MatrixStateType getStateByVal(int val) {
        return Arrays.stream(values()).filter(vst -> vst.getVal() == val).findAny().orElse(null);
    }
}
