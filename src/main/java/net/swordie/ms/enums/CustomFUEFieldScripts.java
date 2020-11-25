package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created on 1-12-2018.
 *
 * @author Asura
 */
public enum CustomFUEFieldScripts { // Custom First User Enter Field Scripts

    ;
    private int id;

    CustomFUEFieldScripts(int val) {
        this.id = val;
    }

    public int getVal() {
        return id;
    }

    public static CustomFUEFieldScripts getByVal(int id) {
        return Arrays.stream(values()).filter(cfuefs -> cfuefs.getVal() == id).findAny().orElse(null);
    }
}
