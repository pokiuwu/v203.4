package net.swordie.ms.enums;

/**
 * Created on 1/6/2018.
 */
public enum AssistType {
    None(0),
    Attack(1),
    Heal(2),
    AttackEx(3),
    AttackEx2(4),
    Summon(5),
    AttackManual(6),
    AttackCounter(7),
    CreateArea(8),
    Bodyguard(9),
    Jaguar(10),
    ;
    private byte val;

    AssistType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
