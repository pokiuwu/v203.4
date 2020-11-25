package net.swordie.ms.client.character.skills;

import net.swordie.ms.connection.OutPacket;

import javax.persistence.*;

/**
 * Created by MechAviv on 2/20/2019.
 */
@Entity
@Table(name = "matrixslots")
public class MatrixSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int equippedSkill;
    private int slotID;
    private int enhanceLevel;

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getEquippedSkill());
        outPacket.encodeInt(getSlotID());
        outPacket.encodeInt(getEnhanceLevel());
        outPacket.encodeByte(0);//unk
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getEquippedSkill() {
        return equippedSkill;
    }

    public void setEquippedSkill(int equippedSkill) {
        this.equippedSkill = equippedSkill;
    }

    public int getSlotID() {
        return slotID;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    public int getEnhanceLevel() {
        return enhanceLevel;
    }

    public void setEnhanceLevel(int enhanceLevel) {
        this.enhanceLevel = enhanceLevel;
    }
}
