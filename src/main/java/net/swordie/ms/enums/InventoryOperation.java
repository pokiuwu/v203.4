package net.swordie.ms.enums;

/**
 * Created on 2/28/2018.
 */
public enum InventoryOperation {
    ADD(0),
    UPDATE_QUANTITY(1),
    MOVE(2),
    REMOVE(3),
    ITEM_EXP(4),
    UPDATE_BAG_POS(5),
    UPDATE_BAG_QUANTITY(6),
    UNK_1(7),
    UPDATE_ITEM_INFO(9),
    UNK_2(9),
    UNK_3(10),
    ;

    private byte val;

    InventoryOperation(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
