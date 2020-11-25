package net.swordie.ms.client.character.skills;

import net.swordie.ms.client.character.BroadcastMsg;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.MatrixConstants;
import net.swordie.ms.enums.MatrixStateType;
import net.swordie.ms.enums.MatrixUpdateType;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.loaders.VCore;
import net.swordie.ms.loaders.VCoreData;
import org.python.bouncycastle.pqc.math.linearalgebra.Matrix;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by MechAviv on 2/20/2019.
 */
@Entity
@Table(name = "matrixinventory")
public class MatrixInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "matrixID")
    private List<MatrixSkill> skills;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "matrixID")
    private List<MatrixSlot> slots;

    public MatrixInventory() {
        this.skills = new CopyOnWriteArrayList();
        this.slots = new CopyOnWriteArrayList();
    }

    public void enhance(Char chr, MatrixSkill toEnhance, List<MatrixSkill> enhanceSkills) {
        int incExp = 0;
        for (MatrixSkill skill : enhanceSkills) {
            if (VCore.isSkillNode(skill.getCoreID())) {
                incExp += VCore.getEnforceOption(VCore.SKILL).get(skill.getSkillLevel()).getEnforceExp();
            } else if (VCore.isBoostNode(skill.getCoreID())) {
                incExp += VCore.getEnforceOption(VCore.BOOST).get(skill.getSkillLevel()).getEnforceExp();
            }
            skills.remove(skill);
        }

        Map<Integer, VCoreData.EnforceOption> enforce = VCore.getEnforceOption(VCore.getCore(toEnhance.getCoreID()).getType());
        int incExpCopy = incExp;
        int curSLV = toEnhance.getSkillLevel();
        while (incExp > 0) {
            int nextExp = enforce.get(toEnhance.getSkillLevel()).getNextExp() - toEnhance.getExperience();
            if (incExp > nextExp) {
                toEnhance.setSkillLevel(Math.min(toEnhance.getSkillLevel() + 1, 25));
                toEnhance.setExperience(0);
                incExp -= nextExp;
            } else {
                toEnhance.setExperience(toEnhance.getExperience() + incExp);
                incExp = 0;
            }
        }
        chr.write(WvsContext.updateVMatrix(chr, true, MatrixUpdateType.ENHANCE, toEnhance.getSlot()));
        chr.write(WvsContext.nodeEnhanceResult(skills.indexOf(toEnhance), incExpCopy, curSLV, toEnhance.getSkillLevel()));
    }

    public void disassembleMultiple(Char chr, List<MatrixSkill> toDisassemble) {
        int incShard = 0;
        for (MatrixSkill skill : toDisassemble) {
            if (skill != null && !skill.isActive()) {
                if (removeSkill(skills.indexOf(skill))) {
                    incShard += MatrixConstants.getIncShard(skill.getCoreID(), skill.getSkillLevel());
                }
            }
        }
        chr.incShards(incShard);
        chr.write(WvsContext.updateVMatrix(chr, true, MatrixUpdateType.DISASSEMBLE_MULTIPLE, 0));
        chr.write(WvsContext.nodeShardResult(incShard));
    }

    public void disassemble(Char chr, int slot) {
        MatrixSkill skill = skills.get(slot);
        if (skill != null && !skill.isActive()) {
            if (removeSkill(slot)) {
                int shard = MatrixConstants.getIncShard(skill.getCoreID(), skill.getSkillLevel());
                chr.incShards(shard);
                chr.write(WvsContext.updateVMatrix(chr, true, MatrixUpdateType.DISASSEMBLE_SINGLE, slot));
                chr.write(WvsContext.nodeShardResult(shard));
            }
        }
    }

    public boolean removeSkill(int slot) {
        if (skills.remove(slot) != null) {
            List<MatrixSkill> temp = new ArrayList<>(skills);
            skills.clear();
            skills.addAll(temp);
            temp.clear();
            return true;
        }
        return false;
    }

    public void moveSkill(int skillSlot, int replaceSlot, int from, int to) {
        MatrixSkill skill = skills.get(skillSlot);
        if (skill != null) {
            MatrixSkill replaceSkill = null;
            if (replaceSlot != -1) {
                replaceSkill = skills.get(replaceSlot);
            }
            move(skill, replaceSkill, from, to, skillSlot, replaceSlot);
        }
    }

    private void move(MatrixSkill skill, MatrixSkill toReplace, int from, int to, int slot, int replaceSlot) {
        if (skill != null && skill.isActive() && skill.getSlot() == from) {
            MatrixSlot toSlot = slots.get(to);
            if (toSlot != null) {
                toSlot.setEquippedSkill(slot);
                skill.setSlot(toSlot.getSlotID());
            }
            MatrixSlot fromSlot = slots.get(from);
            if (fromSlot != null) {
                if (toReplace != null && toReplace.isActive() && toReplace.getSlot() == to) {
                    fromSlot.setEquippedSkill(replaceSlot);
                    toReplace.setSlot(fromSlot.getSlotID());
                } else {
                    fromSlot.setEquippedSkill(-1);
                }
            }
        }
    }

    public int deactivateSkill(int slotID) {
        MatrixSkill skill = skills.get(slotID);
        if (skill != null && skill.getSlot() != -1) {
            MatrixSlot slot = slots.get(skill.getSlot());
            if (slot != null) {
                slot.setEquippedSkill(-1);
                skill.setState(MatrixStateType.INACTIVE);
                skill.setSlot(0);
                return slotID;
            }
        }
        return 0;
    }

    public int activateSkill(int slotID, int toSlot) {
        MatrixSkill skill = skills.get(slotID);
        if (skill != null && skill.getSlot() == -1) {
            if (toSlot != -1) {
                MatrixSlot slot = slots.get(toSlot);
                if (slot != null && slot.getEquippedSkill() == -1) {
                    slot.setEquippedSkill(slotID);
                    skill.setState(MatrixStateType.ACTIVE);
                    skill.setSlot(slot.getSlotID());
                    return slot.getSlotID();
                }
            } else {
                for (MatrixSlot slot : slots) {
                    if (slot.getEquippedSkill() == -1) {
                        slot.setEquippedSkill(slotID);
                        skill.setState(MatrixStateType.ACTIVE);
                        skill.setSlot(slot.getSlotID());
                        return slot.getSlotID();
                    }
                }
            }
        }
        return 0;
    }

    public void addSkill(MatrixSkill skill) {
        if (skills.size() < MatrixConstants.SLOT_MAX) {
            List<MatrixSkill> temp = new ArrayList<>(skills);
            skills.clear();
            skills.add(skill);
            skills.addAll(temp);
            temp.clear();
        }
    }

    public MatrixSkill getSkill(int slotID) {
        return skills.get(slotID);
    }

    public MatrixSlot getSlotByEquippedSkill(int slotID) {
        return slots.get(slotID);
    }

    public List<MatrixSlot> getSlots() {
        return slots;
    }

    public void setSlots(List<MatrixSlot> slots) {
        this.slots = slots;
    }

    public static void reloadSkills(Char chr) {
        List<Skill> changed = new ArrayList<>();
        Skill skill;
        for (MatrixSkill skillRecord : chr.getMatrixInventory().skills) {
            int[] toRemove = {skillRecord.getSkillID(), skillRecord.getSkillID2(), skillRecord.getSkillID3()};
            for (int i = 0; i < toRemove.length; i++) {
                int skillID = toRemove[i];
                if (skillID != 0) {
                    skill = SkillData.getSkillDeepCopyById(skillID);
                    skill.setCurrentLevel(0);
                    chr.removeSkill(skillID);
                    changed.add(skill);
                }
            }
        }
        chr.write(WvsContext.changeSkillRecordResult(changed, true, false, false, false));
        changed.clear();

        for (MatrixSkill skillRecord : chr.getMatrixInventory().skills) {
            if (skillRecord.getState() == MatrixStateType.ACTIVE) {
                if (skillRecord.getSlot() == 0) {
                    continue;
                }
                int[] toAdd = {skillRecord.getSkillID(), skillRecord.getSkillID2(), skillRecord.getSkillID3()};
                for (int i = 0; i < toAdd.length; i++) {
                    int skillID = toAdd[i];
                    if (skillID != 0) {
                        int slv = Math.min(skillRecord.getSkillLevel(), skillRecord.getMasterLevel());// + chr.getSkillLevel(skillRecord.getSkillLevel());
                        skill = SkillData.getSkillDeepCopyById(skillID);
                        skill.setCurrentLevel(slv);
                        skill.setMasterLevel(skillRecord.getMasterLevel());
                        chr.addSkill(skill);
                        changed.add(skill);
                    }
                }
            }
        }
        chr.write(WvsContext.changeSkillRecordResult(changed, true, false, false, false));
        changed.clear();
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(skills.size());
        for (MatrixSkill skill : skills) {
            skill.encode(outPacket);
        }
        outPacket.encodeInt(slots.size());
        for (MatrixSlot matrixSlot : slots) {
            matrixSlot.encode(outPacket);
        }
    }

    public static MatrixInventory getDefault() {
        MatrixInventory ret = new MatrixInventory();
        for (int i = 0; i < 19; i++) {
            MatrixSlot slot = new MatrixSlot();
            slot.setSlotID(i);
            slot.setEquippedSkill(-1);
            ret.slots.add(slot);
        }
        return ret;
    }
}
