package net.swordie.ms.enums;

import net.swordie.ms.util.Util;

import java.util.Arrays;

/**
 * Created by MechAviv on 2/27/2019.
 */
public enum CameraSwitchType {
    NORMAL(0),
    POSITION(1),
    BACK(2),
    POSITION_BY_CID(3);

    private int val;
    private String command = "/unknown", desc = "?";

    CameraSwitchType(int val) {
        this.val = val;
    }

    CameraSwitchType(int val, String command) {
        this.val = val;
        this.command = command;
    }

    CameraSwitchType(int val, String command, String desc) {
        this.val = val;
        this.command = command;
        this.desc = desc;
    }

    public int getVal() {
        return val;
    }

    public static CameraSwitchType getByVal(int val) {
        return Util.findWithPred(Arrays.asList(values()), csat -> csat.getVal() == val);
    }
}
