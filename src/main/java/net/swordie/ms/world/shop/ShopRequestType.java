package net.swordie.ms.world.shop;

import java.util.Arrays;

/**
 * Created on 3/28/2018.
 */
public enum ShopRequestType {
    BUY(0),
    SELL(1),
    RECHARGE(2),
    CLOSE(3),
    ;

    private int val;

    ShopRequestType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static ShopRequestType getByVal(byte type) {
        return Arrays.stream(values()).filter(v -> v.getVal() == type).findFirst().orElse(null);
    }
}
