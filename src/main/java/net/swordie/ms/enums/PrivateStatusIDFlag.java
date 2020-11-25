package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created by MechAviv on 2/22/2019.
 */
public enum PrivateStatusIDFlag {
    NONE(0x0),
    // Customs:
    ADMIN(0x14),// ADMIN_CLIENT | MANAGER_ACCOUNT
    GAME_MASTER(0x15),// PRIMARY_TRACE | ADMIN_CLIENT | MANAGER_ACCOUNT
    TESTER(0x25),// PRIMARY_TRACE | TESTER_ACCOUNT | MANAGER_ACCOUNT

    //~~~~~~~~~~~~~~~~~~~
    PRIMARY_TRACE(0x1),
    MESO_TRACE(0x2),
    ADMIN_CLIENT(0x4),
    MOB_MOVE_OBSERVE(0x8),
    MANAGER_ACCOUNT(0x10),
    TESTER_ACCOUNT(0x20),
    SECONDARY_TRACE(0x40),
    MACRO_WORKING(0x80),
    NOT_ACTIVE_ACCOUNT(0x100),
    MACRO_RECEIVER_1(0x200),
    MACRO_RECEIVER_2(0x400),
    MACRO_RECEIVER_3(0x800),
    MACRO_RECEIVER_4(0x1000),
    SUB_TESTER_ACCOUNT(0x2000),
    PROTECT_ACCOUNT(0x4000),
    STAR_PLANET_M_TRACE(0x8000);

    private int flag;

    PrivateStatusIDFlag(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public boolean hasFlag(PrivateStatusIDFlag psFlag) {
        return (getFlag() & psFlag.getFlag()) != 0;
    }

    public static PrivateStatusIDFlag getStatusByFlag(int flag) {
        return Arrays.stream(values()).filter(f -> f.getFlag() == flag).findAny().orElse(null);
    }
}
