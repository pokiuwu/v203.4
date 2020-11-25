package net.swordie.ms.client.character;

import net.swordie.ms.enums.Stat;

/**
 * Created by MechAviv on 12/21/2018.
 */
public class StatPair {
    private Stat statFlag;
    private int value;

    public StatPair() {}

    public StatPair(Stat statFlag, int value) {
        this.statFlag = statFlag;
        this.value = value;
    }

    public Stat getStatFlag() {
        return statFlag;
    }

    public int getValue() {
        return value;
    }
}
