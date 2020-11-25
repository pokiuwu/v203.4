package net.swordie.ms.enums;

import net.swordie.ms.util.Util;

import java.util.Arrays;

/**
 * Created by MechAviv on 3/16/2019.
 */
public enum LarknessSkillType {
    NON_LARKNESS_SKILL(-1),
    NON_TYPE(0),
    LIGHT(1),
    DARK(2),
    MIX(3);

    private final int type;

    LarknessSkillType(int type) {
        this.type = type;
    }

    public int getType(){
        return type;
    }
    public static LarknessSkillType getByVal(int val) {
        return Util.findWithPred(Arrays.asList(values()), stat -> stat.getType() == val);
    }
}
