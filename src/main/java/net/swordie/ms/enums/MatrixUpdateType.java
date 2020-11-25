package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created by MechAviv on 2/16/2019.
 */
public enum MatrixUpdateType {
    ENABLE(0),
    DISABLE(1),
    MOVE(2),
    //3
    ENHANCE(4),
    DISASSEMBLE_SINGLE(5),
    DISASSEMBLE_MULTIPLE(6),
    CRAFT_NODE(7),
    //8
    CRAFT_NODESTONE(9),
    SLOT_ENHANCEMENT(10),
    //1
    RESET_SLOT_ENHANCEMENT(12);

    private int val;

    MatrixUpdateType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static MatrixUpdateType getUpdateTypeByVal(int val) {
        return Arrays.stream(values()).filter(vut -> vut.getVal() == val).findAny().orElse(null);
    }
}
