package net.swordie.ms.loaders;

import net.swordie.ms.constants.JobConstants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MechAviv on 2/16/2019.
 */
public class VCoreData {
    private byte type;
    private short maxLevel;
    private int coreID;
    private int expireAfter;
    private String name = "";
    private String desc = "";
    private CoreOption option = new CoreOption();
    private List<String> jobs = new ArrayList<>();
    private List<Integer> connectSkills = new ArrayList<>();

    public void saveToDat(DataOutputStream dos) throws IOException {
        dos.writeInt(getCoreID());
        dos.writeByte(getType());
        dos.writeShort(getMaxLevel());
        dos.writeInt(getExpireAfter());
        dos.writeUTF(getName());
        dos.writeUTF(getDesc());
        option.saveToDat(dos);
        dos.writeInt(getJobs().size());
        for (String job : getJobs()) {
            dos.writeUTF(job);
        }
        dos.writeInt(getConnectSkills().size());
        for (Integer skill : getConnectSkills()) {
            dos.writeInt(skill);
        }
    }

    public void loadFromDat(DataInputStream dis) throws IOException {
        setCoreID(dis.readInt());
        setType(dis.readByte());
        setMaxLevel(dis.readShort());
        setExpireAfter(dis.readInt());
        setName(dis.readUTF());
        setDesc(dis.readUTF());
        option.loadFromDat(dis);

        int jobSize = dis.readInt();
        for (int i = 0; i < jobSize; i++) {
            addJob(dis.readUTF());
        }

        int skillSize = dis.readInt();
        for (int i = 0; i < skillSize; i++) {
            addConnectedSkill(dis.readInt());
        }
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public short getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(short maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getCoreID() {
        return coreID;
    }

    public void setCoreID(int coreID) {
        this.coreID = coreID;
    }

    public int getExpireAfter() {
        return expireAfter;
    }

    public void setExpireAfter(int expireAfter) {
        this.expireAfter = expireAfter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public CoreOption getOption() {
        return option;
    }

    public void setOption(CoreOption option) {
        this.option = option;
    }

    public List<String> getJobs() {
        return jobs;
    }

    public void setJobs(List<String> jobs) {
        this.jobs = jobs;
    }

    public void addJob(String job) {
        this.jobs.add(job);
    }
    
    public boolean isClassSkill(short job) {
        if (jobs.contains("warrior")) {
            return (job != 100 && JobConstants.isAdventurerWarrior(job)) || JobConstants.isDawnWarrior(job) || JobConstants.isDemon(job) || JobConstants.isMihile(job) || JobConstants.isKaiser(job) || JobConstants.isZero(job) || JobConstants.isAran(job) || JobConstants.isBlaster(job) || JobConstants.isHayato(job);
        } else if (jobs.contains("magician")) {
            return (job != 200 && JobConstants.isAdventurerMage(job)) || JobConstants.isLuminous(job) || JobConstants.isBattleMage(job) || JobConstants.isKinesis(job) || JobConstants.isEvan(job) || JobConstants.isBlazeWizard(job) || JobConstants.isKanna(job) || JobConstants.isBeastTamer(job);
        } else if (jobs.contains("archer")) {
            return (job != 300 && JobConstants.isAdventurerArcher(job)) || JobConstants.isWindArcher(job) || JobConstants.isWildHunter(job) || JobConstants.isMercedes(job);
        } else if (jobs.contains("rogue")) {
            return (job != 400 && JobConstants.isAdventurerThief(job)) || JobConstants.isDualBlade(job) || JobConstants.isNightWalker(job) || JobConstants.isPhantom(job);
        } else if (jobs.contains("pirate")) {
            return  (job != 500 && JobConstants.isAdventurerPirate(job)) || JobConstants.isXenon(job) || JobConstants.isThunderBreaker(job) || JobConstants.isCannonShooter(job) || JobConstants.isMechanic(job) || JobConstants.isShade(job) || JobConstants.isAngelicBuster(job) || JobConstants.isJett(job);
        }
        return false;
    }

    public boolean isJobSkill(int job) {
        if (jobs.contains("warrior") || jobs.contains("magician") || jobs.contains("archer")
                || jobs.contains("rogue") || jobs.contains("pirate") || jobs.contains("none") || jobs.contains("all")) {
            return false;
        }
        for (String sJob : jobs) {
            if (Integer.valueOf(sJob) == job) {
                return true;
            }
        }
        return false;
    }

    public List<Integer> getConnectSkills() {
        return connectSkills;
    }

    public void setConnectSkills(List<Integer> connectSkills) {
        this.connectSkills = connectSkills;
    }

    public void addConnectedSkill(int skill) {
        this.connectSkills.add(skill);
    }

    public static class CoreOption {
        private short slv;
        private int skillID;
        private int cooltime;
        private int validTime;
        private int count;
        private int healPercent;
        private int reducePercent;
        private String effectType = "";
        private String condType = "";
        private double prob;

        public void saveToDat(DataOutputStream dos) throws IOException {
            dos.writeShort(getSLV());
            dos.writeInt(getSkillID());
            dos.writeInt(getCooltime());
            dos.writeInt(getValidTime());
            dos.writeInt(getCount());
            dos.writeInt(getHealPercent());
            dos.writeInt(getReducePercent());
            dos.writeUTF(getEffectType());
            dos.writeUTF(getCondType());
            dos.writeDouble(getProb());
        }

        public void loadFromDat(DataInputStream dis) throws IOException {
            setSLV(dis.readShort());
            setSkillID(dis.readInt());
            setCooltime(dis.readInt());
            setValidTime(dis.readInt());
            setCount(dis.readInt());
            setHealPercent(dis.readInt());
            setReducePercent(dis.readInt());
            setEffectType(dis.readUTF());
            setCondType(dis.readUTF());
            setProb(dis.readDouble());
        }

        public short getSLV() {
            return slv;
        }

        public void setSLV(short slv) {
            this.slv = slv;
        }

        public int getSkillID() {
            return skillID;
        }

        public void setSkillID(int skillID) {
            this.skillID = skillID;
        }

        public int getCooltime() {
            return cooltime;
        }

        public void setCooltime(int cooltime) {
            this.cooltime = cooltime;
        }

        public int getValidTime() {
            return validTime;
        }

        public void setValidTime(int validTime) {
            this.validTime = validTime;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getHealPercent() {
            return healPercent;
        }

        public void setHealPercent(int healPercent) {
            this.healPercent = healPercent;
        }

        public int getReducePercent() {
            return reducePercent;
        }

        public void setReducePercent(int reducePercent) {
            this.reducePercent = reducePercent;
        }

        public String getEffectType() {
            return effectType;
        }

        public void setEffectType(String effectType) {
            this.effectType = effectType;
        }

        public String getCondType() {
            return condType;
        }

        public void setCondType(String condType) {
            this.condType = condType;
        }

        public double getProb() {
            return prob;
        }

        public void setProb(double prob) {
            this.prob = prob;
        }
    }

    public static class EnforceOption {
        private int enforceExp;
        private int nextExp;
        private int extract;

        public void saveToDat(DataOutputStream dos) throws IOException {
            dos.writeInt(getEnforceExp());
            dos.writeInt(getNextExp());
            dos.writeInt(getExtract());
        }

        public void loadFromDat(DataInputStream dis) throws IOException {
            setEnforceExp(dis.readInt());
            setNextExp(dis.readInt());
            setExtract(dis.readInt());
        }

        public int getEnforceExp() {
            return enforceExp;
        }

        public void setEnforceExp(int enforceExp) {
            this.enforceExp = enforceExp;
        }

        public int getNextExp() {
            return nextExp;
        }

        public void setNextExp(int nextExp) {
            this.nextExp = nextExp;
        }

        public int getExtract() {
            return extract;
        }

        public void setExtract(int extract) {
            this.extract = extract;
        }
    }
}
