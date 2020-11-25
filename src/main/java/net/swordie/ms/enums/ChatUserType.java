package net.swordie.ms.enums;

/**
 * Created on 1/18/2018.
 */
public enum ChatUserType {
    User(1),
    Admin(0);

    private byte val;

    ChatUserType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
