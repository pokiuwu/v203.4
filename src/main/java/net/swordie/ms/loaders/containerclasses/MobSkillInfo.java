package net.swordie.ms.loaders.containerclasses;

import net.swordie.ms.life.mob.skill.MobSkillStat;
import net.swordie.ms.util.Position;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created on 3/18/2018.
 */
public class MobSkillInfo {

    private short id;
    private short level;
    private Map<MobSkillStat, String> mobSkillStats = new HashMap<>();
    private Position rb;
    private Position lt;
    private Position lt2;
    private Position rb2;
    private Set<Integer> ints = new HashSet<>();
    private Position lt3;
    private Position rb3;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }

    public Map<MobSkillStat, String> getMobSkillStats() {
        return mobSkillStats;
    }

    public void setMobSkillStats(Map<MobSkillStat, String> mobSkillStats) {
        this.mobSkillStats = mobSkillStats;
    }

    public void putMobSkillStat(MobSkillStat mss, String value) {
        getMobSkillStats().put(mss, value);
    }

    public int getSkillStatIntValue(MobSkillStat mobSkillStat) {
        if(!getMobSkillStats().containsKey(mobSkillStat)) {
            return 0;
        }
        return Integer.parseInt(getSkillStat(mobSkillStat));
    }

    public String getSkillStat(MobSkillStat mobSkillStat) {
        return getMobSkillStats().get(mobSkillStat);
    }

    public void setRb(Position rb) {
        this.rb = rb;
    }

    public Position getRb() {
        return rb;
    }

    public void setLt(Position lt) {
        this.lt = lt;
    }

    public Position getLt() {
        return lt;
    }

    public void setLt2(Position lt2) {
        this.lt2 = lt2;
    }

    public Position getLt2() {
        return lt2;
    }

    public void setRb2(Position rb2) {
        this.rb2 = rb2;
    }

    public Position getRb2() {
        return rb2;
    }

    public Set<Integer> getInts() {
        return ints;
    }

    public void addIntToList(int value) {
        getInts().add(value);
    }

    public void setLt3(Position lt3) {
        this.lt3 = lt3;
    }

    public Position getLt3() {
        return lt3;
    }

    public void setRb3(Position rb3) {
        this.rb3 = rb3;
    }

    public Position getRb3() {
        return rb3;
    }
}
