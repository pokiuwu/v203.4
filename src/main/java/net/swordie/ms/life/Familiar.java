package net.swordie.ms.life;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.db.FileTimeConverter;
import net.swordie.ms.util.FileTime;

import javax.persistence.*;

/**
 * @author Sjonnie
 * Created on 6/9/2018.
 */
@Entity
@Table(name = "familiars")
public class Familiar extends Life {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int idk1;
    private int familiarID;
    private String name;
    private boolean idk2;
    private short idk3;
    private int fatigue;
    private long idk4;
    private long idk5;
    @Convert(converter = FileTimeConverter.class)
    private FileTime expiration = FileTime.fromType(FileTime.Type.MAX_TIME);
    private short vitality;

    public Familiar() {
        super(0);
    }

    public Familiar(int templateId) {
        super(templateId);
    }

    public Familiar(long id, int familiarID, String name, FileTime expiration, short vitality) {
        super(0);
        this.id = id;
        this.familiarID = familiarID;
        this.name = name;
        this.expiration = expiration;
        this.vitality = vitality;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getIdk1());
        outPacket.encodeInt(getFamiliarID());
        outPacket.encodeString(getName(), 13);
        outPacket.encodeByte(isIdk2());
        outPacket.encodeShort(getIdk3());
        outPacket.encodeInt(getFatigue());
        outPacket.encodeLong(getIdk4());
        outPacket.encodeLong(getIdk5());
        outPacket.encodeFT(getExpiration());
        outPacket.encodeShort(getVitality());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIdk1() {
        return idk1;
    }

    public void setIdk1(int idk1) {
        this.idk1 = idk1;
    }

    public int getFamiliarID() {
        return familiarID;
    }

    public void setFamiliarID(int familiarID) {
        this.familiarID = familiarID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIdk2() {
        return idk2;
    }

    public void setIdk2(boolean idk2) {
        this.idk2 = idk2;
    }

    public short getIdk3() {
        return idk3;
    }

    public void setIdk3(short idk3) {
        this.idk3 = idk3;
    }

    public int getFatigue() {
        return fatigue;
    }

    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    public long getIdk4() {
        return idk4;
    }

    public void setIdk4(long idk4) {
        this.idk4 = idk4;
    }

    public long getIdk5() {
        return idk5;
    }

    public void setIdk5(long idk5) {
        this.idk5 = idk5;
    }

    public FileTime getExpiration() {
        return expiration;
    }

    public void setExpiration(FileTime expiration) {
        this.expiration = expiration;
    }

    public short getVitality() {
        return vitality;
    }

    public void setVitality(short vitality) {
        this.vitality = vitality;
    }
}
