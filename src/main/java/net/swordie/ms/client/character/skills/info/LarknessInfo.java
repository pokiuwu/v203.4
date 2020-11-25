package net.swordie.ms.client.character.skills.info;

import net.swordie.ms.connection.OutPacket;

/**
 * Created on 1/3/2018.
 */
public class LarknessInfo {
    private int rLarkness;
    private int tLarkness;
    private boolean dark;

    public LarknessInfo(boolean dark) {
        this.dark = dark;
    }

    public LarknessInfo(int rLarkness, int tLarkness, boolean dark) {
        this.rLarkness = rLarkness;
        this.tLarkness = tLarkness;
        this.dark = dark;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getrLarkness());
        outPacket.encodeInt(gettLarkness());
    }

    public boolean isDark() {
        return dark;
    }

    public void setDark(boolean dark) {
        this.dark = dark;
    }

    public int getrLarkness() {
        return rLarkness;
    }

    public void setrLarkness(int rLarkness) {
        this.rLarkness = rLarkness;
    }

    public int gettLarkness() {
        return tLarkness;
    }

    public void settLarkness(int tLarkness) {
        this.tLarkness = tLarkness;
    }
}
