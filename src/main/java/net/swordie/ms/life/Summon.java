package net.swordie.ms.life;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.avatar.AvatarLook;
import net.swordie.ms.client.character.items.ItemBuffs;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.SkillStat;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.client.jobs.adventurer.Thief;
import net.swordie.ms.client.jobs.adventurer.Warrior;
import net.swordie.ms.client.jobs.cygnus.WindArcher;
import net.swordie.ms.client.jobs.resistance.Mechanic;
import net.swordie.ms.client.jobs.sengoku.Kanna;
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.Summoned;
import net.swordie.ms.connection.packet.User;
import net.swordie.ms.connection.packet.UserRemote;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.world.field.Field;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.stream.Collectors;

import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.IndieEmpty;

/**
 * Created on 1/6/2018.
 */
public class Summon extends Life {

    private static final Logger log = Logger.getLogger(Summon.class);

    private Char chr;
    private int skillID;
    private int bulletID;
    private int summonTerm;
    private byte charLevel;
    private byte slv;
    private AssistType assistType;
    private EnterType enterType;
    private byte teslaCoilState;
    private boolean flyMob;
    private boolean beforeFirstAttack;
    private boolean jaguarActive;
    private boolean attackActive;
    private short curFoothold;
    private AvatarLook avatarLook;
    private List<Position> teslaCoilPositions = new ArrayList<>();
    private MoveAbility moveAbility;
    private Position[] kishinPositions = new Position[2];
    private int maxHP;
    private int hp;

    public Summon(int templateId) {
        super(templateId);
    }

    public Char getChr() {
        return chr;
    }

    public void setChr(Char chr) {
        this.chr = chr;
    }

    public int getSkillID() {
        return skillID;
    }

    public void setSkillID(int skillID) {
        this.skillID = skillID;
    }

    public byte getCharLevel() {
        return charLevel;
    }

    public void setCharLevel(byte charLevel) {
        this.charLevel = charLevel;
    }

    public byte getSlv() {
        return slv;
    }

    public void setSlv(byte slv) {
        this.slv = slv;
    }

    public int getBulletID() {
        return bulletID;
    }

    public void setBulletID(int bulletID) {
        this.bulletID = bulletID;
    }

    public int getSummonTerm() {
        return summonTerm;
    }

    public void setSummonTerm(int summonTerm) {
        this.summonTerm = 1000 * summonTerm;
    }

    public AssistType getAssistType() {
        return assistType;
    }

    public void setAssistType(AssistType assistType) {
        this.assistType = assistType;
    }

    public EnterType getEnterType() {
        return enterType;
    }

    public void setEnterType(EnterType enterType) {
        this.enterType = enterType;
    }

    public byte getTeslaCoilState() {
        return teslaCoilState;
    }

    public void setTeslaCoilState(byte teslaCoilState) {
        this.teslaCoilState = teslaCoilState;
    }

    public boolean isFlyMob() {
        return flyMob;
    }

    public void setFlyMob(boolean flyMob) {
        this.flyMob = flyMob;
    }

    public boolean isBeforeFirstAttack() {
        return beforeFirstAttack;
    }

    public void setBeforeFirstAttack(boolean beforeFirstAttack) {
        this.beforeFirstAttack = beforeFirstAttack;
    }

    public boolean isJaguarActive() {
        return jaguarActive;
    }

    public void setJaguarActive(boolean jaguarActive) {
        this.jaguarActive = jaguarActive;
    }

    public boolean isAttackActive() {
        return attackActive;
    }

    public void setAttackActive(boolean attackActive) {
        this.attackActive = attackActive;
    }

    public short getCurFoothold() {
        return curFoothold;
    }

    public void setCurFoothold(short curFoothold) {
        this.curFoothold = curFoothold;
    }

    public AvatarLook getAvatarLook() {
        return avatarLook;
    }

    public void setAvatarLook(AvatarLook avatarLook) {
        this.avatarLook = avatarLook;
    }

    public List<Position> getTeslaCoilPositions() {
        return teslaCoilPositions;
    }

    public void setTeslaCoilPositions(List<Position> teslaCoilPositions) {
        this.teslaCoilPositions = teslaCoilPositions;
    }

    public MoveAbility getMoveAbility() {
        return moveAbility;
    }

    public void setMoveAbility(MoveAbility moveAbility) {
        this.moveAbility = moveAbility;
    }

    public static Summon getSummonBy(Char chr, int skillID, byte slv) {
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        Summon summon = new Summon(-1);
        summon.setChr(chr);
        summon.setSkillID(skillID);
        summon.setSlv(slv);
        summon.setSummonTerm(si.getValue(SkillStat.time, slv));
        summon.setCharLevel((byte) chr.getStat(Stat.level));
        summon.setPosition(chr.getPosition().deepCopy());
        summon.setMoveAction((byte) 1);
        summon.setCurFoothold((short) chr.getField().findFootHoldBelow(summon.getPosition()).getId());
        summon.setMoveAbility(MoveAbility.Walk);
        summon.setAssistType(AssistType.Attack);
        summon.setEnterType(EnterType.Animation);
        summon.setBeforeFirstAttack(false);
        summon.setTemplateId(skillID);
        summon.setAttackActive(true);

        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        o1.nReason = skillID;
        o1.nValue = 1;
        o1.summon = summon;
        o1.tStart = (int) System.currentTimeMillis();
        o1.tTerm = summon.getSummonTerm() / 1000;
        tsm.putCharacterStatValue(IndieEmpty, o1);
        tsm.sendSetStatPacket();
        return summon;
    }

    public static void summonKishin(Char chr, byte slv) {
        Field field = chr.getField();

        // Remove both Old Kishins
        List<Life> oldKishins = field.getLifes().values().stream()
                .filter(s -> s instanceof Summon &&
                        ((Summon) s).getChr() == chr &&
                        ((Summon) s).getSkillID() == Kanna.KISHIN_SHOUKAN)
                .collect(Collectors.toList());
        for(Life life : oldKishins) {
            field.removeLife(life.getObjectId(), false);
        }

        // Summon Left Kishin
        Summon kishinLeft = getSummonBy(chr, Kanna.KISHIN_SHOUKAN, slv);
        kishinLeft.setFlyMob(true);
        Position kishLeftPos = new Position(chr.getPosition().getX() - 250, chr.getPosition().getY());
        kishinLeft.setPosition(kishLeftPos);
        kishinLeft.setCurFoothold((short) field.findFootHoldBelow(kishLeftPos).getId());
        kishinLeft.setMoveAbility(MoveAbility.Stop);
        kishinLeft.setMoveAction((byte) 0);
        kishinLeft.setKishinPositions(new Position[] {
            new Position(chr.getPosition().getX() + 250, chr.getPosition().getY()),
            new Position(chr.getPosition().getX() - 250, chr.getPosition().getY())
        });
        field.spawnAddSummon(kishinLeft);

        // Summon Right Kishin
        Summon kishinRight = getSummonBy(chr, Kanna.KISHIN_SHOUKAN, slv);
        kishinRight.setFlyMob(true);
        Position kishRightPos = new Position(chr.getPosition().getX() + 250, chr.getPosition().getY());
        kishinRight.setPosition(kishRightPos);
        kishinRight.setCurFoothold((short) field.findFootHoldBelow(kishRightPos).getId());
        kishinRight.setMoveAbility(MoveAbility.Stop);
        kishinRight.setMoveAction((byte) 5);
        kishinLeft.setKishinPositions(new Position[] {
                new Position(chr.getPosition().getX() + 250, chr.getPosition().getY()),
                new Position(chr.getPosition().getX() - 250, chr.getPosition().getY())
        });
        field.spawnAddSummon(kishinRight);
    }

    public Position[] getKishinPositions() {
        return kishinPositions;
    }

    public void setKishinPositions(Position[] kishinPositions) {
        this.kishinPositions = kishinPositions;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void onSkillUse(int skillId) {
        switch (skillId) {
            case Warrior.EVIL_EYE:
                ((Warrior) chr.getJobHandler()).healByEvilEye();
                break;

            case Warrior.HEX_OF_THE_EVIL_EYE:
                ((Warrior) chr.getJobHandler()).giveHexOfTheEvilEyeBuffs();
                break;

            case Mechanic.SUPPORT_UNIT_HEX:
            case Mechanic.ENHANCED_SUPPORT_UNIT:
                ((Mechanic) chr.getJobHandler()).healFromSupportUnit(this);
                break;
            default:
                int buffItem = SkillConstants.getBuffSkillItem(skillId);
                if (buffItem != 0) {
                    ItemBuffs.giveItemBuffsFromItemID(chr, chr.getTemporaryStatManager(), buffItem);
                } else {
                    chr.chatMessage(String.format("Unhandled Summon Skill: %d, casted by Summon: %d", skillId, getSkillID()));
                }
                break;
        }
        chr.write(User.effect(Effect.skillAffected(skillID, (byte) 1, getObjectId())));
        chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillAffected(skillID, (byte) 1, getObjectId())));
    }

    public void onHit(int damage, int mobTemplateId) {
        Char chr = getChr();
        Skill skill = chr.getSkill(getSkillID());

        if(skill == null) {
            return;
        }

        int summonHP = getHp();
        int newSummonHP = summonHP - damage;

        switch (getSkillID()) {
            case Thief.MIRRORED_TARGET:
                ((Thief) chr.getJobHandler()).giveShadowMeld();
                break;

            case WindArcher.EMERALD_DUST:
                ((WindArcher) chr.getJobHandler()).applyEmeraldDustDebuffToMob(this, mobTemplateId);
                // Fallthrough intended
            case WindArcher.EMERALD_FLOWER:
                ((WindArcher) chr.getJobHandler()).applyEmeraldFlowerDebuffToMob(this, mobTemplateId);
                break;

            default:
                log.error(String.format("Unhandled HP Summon, id = %d", getSkillID()));
                break;
        }

        if(newSummonHP <= 0) {
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            chr.getField().broadcastPacket(Summoned.summonedRemoved(this, LeaveType.ANIMATION));
            tsm.removeStatsBySkill(skill.getSkillId());
        } else {
            setHp(newSummonHP);
        }
    }

    @Override
    public void broadcastSpawnPacket(Char onlyChar) {
        Field field = getField();
        if (getSummonTerm() > 0) {
            ScheduledFuture sf = EventManager.addEvent(() -> field.removeLife(getObjectId(), true), getSummonTerm());
            field.addLifeSchedule(this, sf);
        }
        field.broadcastPacket(Summoned.summonedCreated(getChr().getId(), this));
    }

    @Override
    public void broadcastLeavePacket() {
        Field field = getField();
        if (getSkillID() == Kanna.KISHIN_SHOUKAN || getSkillID() == Job.MONOLITH) {
            field.setKishin(false);
        }
        field.broadcastPacket(Summoned.summonedRemoved(this, LeaveType.ANIMATION));
    }
}
