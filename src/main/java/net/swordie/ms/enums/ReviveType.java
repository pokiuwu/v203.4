package net.swordie.ms.enums;

/**
 * Created on 5/17/2018.
 */
public enum ReviveType {
    NORMAL(0),
    PREMIUM(1),
    MAPLEPOINT(2),
    QUESTPOINT(3),
    UPGRADETOMB(4),
    SOULSTONE(5),
    EVENT(6),
    ;

    private byte val;

    ReviveType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
