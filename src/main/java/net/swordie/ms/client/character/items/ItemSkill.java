package net.swordie.ms.client.character.items;

public class ItemSkill {
    private int skill;
    private byte slv;

    public ItemSkill(int skill, byte slv) {
        this.skill = skill;
        this.slv = slv;
    }

    public byte getSlv() {
        return slv;
    }

    public void setSlv(byte slv) {
        this.slv = slv;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }
}
