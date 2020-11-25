package net.swordie.ms.client;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created on 6/7/2018.
 */
@Entity
@Table(name = "linkskills")
public class LinkSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int ownerID;
    private int linkSkillID;
    private int level;

    public LinkSkill() {
    }

    public LinkSkill(int ownerID, int linkSkillID, int level) {
        this.ownerID = ownerID;
        this.linkSkillID = linkSkillID;
        this.level = level;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public int getLinkSkillID() {
        return linkSkillID;
    }

    public void setLinkSkillID(int linkSkillID) {
        this.linkSkillID = linkSkillID;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkSkill linkSkill = (LinkSkill) o;
        return ownerID == linkSkill.ownerID &&
                linkSkillID == linkSkill.linkSkillID;
    }

    @Override
    public int hashCode() {

        return Objects.hash(ownerID, linkSkillID);
    }
}
