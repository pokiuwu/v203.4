package net.swordie.ms.client.character.skills;

import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.db.FileTimeConverter;
import net.swordie.ms.enums.MatrixStateType;
import net.swordie.ms.util.FileTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

/**
 * Created by MechAviv on 2/16/2019.
 */
@Entity
@Table(name = "matrixskills")
public class MatrixSkill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int slot = -1;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "state")
    private MatrixStateType state = MatrixStateType.INACTIVE;
    private int coreID;
    private int skillID;
    private int skillID2;
    private int skillID3;
    private int skillID4;
    private int level = 1;
    private int masterLevel;
    private int experience;
    @Convert(converter = FileTimeConverter.class)
    private FileTime expirationDate = FileTime.fromType(FileTime.Type.MAX_TIME);

    public void encode(OutPacket outPacket) {
        outPacket.encodeLong(getId());
        outPacket.encodeInt(getCoreID());
        outPacket.encodeInt(getSkillLevel());
        outPacket.encodeInt(getExperience());
        outPacket.encodeInt(getState().getVal());
        outPacket.encodeInt(getSkillID());
        outPacket.encodeInt(getSkillID2());
        outPacket.encodeInt(getSkillID3());
        outPacket.encodeInt(getSkillID4());
        outPacket.encodeFT(getExpirationDate());
    }

    public void decode(InPacket inPacket) {
        inPacket.decodeLong();
        setCoreID(inPacket.decodeInt());
        setSkillLevel(inPacket.decodeInt());
        setExperience(inPacket.decodeInt());
        setState(MatrixStateType.getStateByVal(inPacket.decodeInt()));
        setSkillID(inPacket.decodeInt());
        setSkillID2(inPacket.decodeInt());
        setSkillID3(inPacket.decodeInt());
        setSkillID4(inPacket.decodeInt());
        setExpirationDate(new FileTime(inPacket.decodeLong()));// or default maxtime
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MatrixStateType getState() {
        return state;
    }

    public void setState(MatrixStateType state) {
        this.state = state;
    }

    public boolean isActive() { return state == MatrixStateType.ACTIVE; }

    public int getCoreID() {
        return coreID;
    }

    public void setCoreID(int coreID) {
        this.coreID = coreID;
    }

    public int getSkillID() {
        return skillID;
    }

    public void setSkillID(int skillID) {
        this.skillID = skillID;
    }

    public int getSkillID2() {
        return skillID2;
    }

    public void setSkillID2(int skillID2) {
        this.skillID2 = skillID2;
    }

    public int getSkillID3() {
        return skillID3;
    }

    public void setSkillID3(int skillID3) {
        this.skillID3 = skillID3;
    }

    public int getSkillID4() {
        return skillID4;
    }

    public void setSkillID4(int skillID4) {
        this.skillID4 = skillID4;
    }

    public int getSkillLevel() {
        return level;
    }

    public void setSkillLevel(int level) {
        this.level = level;
    }

    public int getMasterLevel() {
        return masterLevel;
    }

    public void setMasterLevel(int masterLevel) {
        this.masterLevel = masterLevel;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public FileTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(FileTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getSlot() { return slot; }

    public void setSlot(int slot) { this.slot = slot; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatrixSkill skill = (MatrixSkill) o;
        return id == skill.id && coreID == skill.coreID && skillID == skill.skillID && skillID2 == skill.skillID2 && skillID3 == skill.skillID3;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, coreID);
    }
}
