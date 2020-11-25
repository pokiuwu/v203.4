package net.swordie.ms.connection.packet;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.client.character.MarriageRecord;
import net.swordie.ms.client.character.info.FarmUserInfo;
import net.swordie.ms.client.character.items.*;
import net.swordie.ms.client.character.keys.FuncKeyMap;
import net.swordie.ms.client.character.runestones.RuneStone;
import net.swordie.ms.client.character.skills.ForceAtom;
import net.swordie.ms.client.character.skills.PsychicArea;
import net.swordie.ms.client.character.skills.TownPortal;
import net.swordie.ms.client.character.skills.info.ForceAtomInfo;
import net.swordie.ms.client.jobs.resistance.OpenGate;
import net.swordie.ms.world.Timer;
import net.swordie.ms.client.trunk.TrunkDlg;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.PsychicLock;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Dragon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.movement.MovementInfo;
import net.swordie.ms.life.pet.Pet;
import net.swordie.ms.loaders.containerclasses.MakingSkillRecipe;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.container.Triple;
import net.swordie.ms.world.field.ClockPacket;
import net.swordie.ms.world.field.fieldeffect.FieldEffect;
import net.swordie.ms.world.field.obtacleatom.ObtacleAtomInfo;
import net.swordie.ms.world.field.obtacleatom.ObtacleInRowInfo;
import net.swordie.ms.world.field.obtacleatom.ObtacleRadianInfo;
import org.python.antlr.ast.For;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CField {

    public static OutPacket funcKeyMappedManInit(FuncKeyMap funcKeyMap) {
        OutPacket outPacket = new OutPacket(OutHeader.FUNC_KEY_MAPPED_MAN_INIT);

        funcKeyMap.encode(outPacket);

        return outPacket;
    }
    public static  OutPacket beastTamerFuncKeyMappedManInit(){
        OutPacket outPacket = new OutPacket();
        outPacket.encodeShort(0);
        return  outPacket;
    }

    public static OutPacket affectedAreaCreated(AffectedArea aa) {
        OutPacket outPacket = new OutPacket(OutHeader.AFFECTED_AREA_CREATED);

        outPacket.encodeInt(aa.getObjectId());
        outPacket.encodeByte(aa.getMobOrigin());
        outPacket.encodeInt(aa.getCharID());
        outPacket.encodeInt(aa.getSkillID());
        outPacket.encodeShort(aa.getSlv());
        outPacket.encodeShort(aa.getDelay());
        aa.getRect().encode(outPacket);
        outPacket.encodeInt(aa.getElemAttr());
        outPacket.encodeInt(aa.getElemAttr()); // ?
        outPacket.encodePosition(aa.getPosition());
        outPacket.encodeInt(aa.getForce());
        outPacket.encodeInt(aa.getOption());
        outPacket.encodeByte(aa.getOption() != 0);
        outPacket.encodeInt(aa.getIdk()); // ?
        if(SkillConstants.isFlipAffectAreaSkill(aa.getSkillID())) {
            outPacket.encodeByte(aa.isFlip());
        }
        outPacket.encodeInt(0); // ?
        outPacket.encodeByte(0);
        return outPacket;
    }

    public static OutPacket affectedAreaRemoved(AffectedArea aa, boolean mistEruption) {
        OutPacket outPacket = new OutPacket(OutHeader.AFFECTED_AREA_REMOVED);

        outPacket.encodeInt(aa.getObjectId());
        if(aa.getSkillID() == 2111003) {
            outPacket.encodeByte(mistEruption);
        }

        return outPacket;
    }

    public static OutPacket createForceAtom(boolean byMob, int userOwner, int targetID, int forceAtomType, boolean toMob,
                                            int targets, int skillID, ForceAtomInfo fai, Rect rect, int arriveDir, int arriveRange,
                                            Position forcedTargetPos, int bulletID, Position pos) {
        return createForceAtom(byMob, userOwner, targetID, ForceAtomEnum.getByVal(forceAtomType), toMob, targets, skillID, fai, rect, arriveDir, arriveRange, forcedTargetPos, bulletID, pos);
    }

    public static OutPacket createForceAtom(boolean byMob, int userOwner, int targetID, ForceAtomEnum forceAtomType, boolean toMob,
                                     int targets, int skillID, ForceAtomInfo fai, Rect rect, int arriveDir, int arriveRange,
                                     Position forcedTargetPos, int bulletID, Position pos) {
        List<Integer> integers = new ArrayList<>();
        integers.add(targets);
        List<ForceAtomInfo> forceAtomInfos = new ArrayList<>();
        forceAtomInfos.add(fai);
        return createForceAtom(byMob, userOwner, targetID, forceAtomType, toMob, integers, skillID, forceAtomInfos,
                rect, arriveDir, arriveRange, forcedTargetPos, bulletID, pos);
    }

    public static OutPacket curNodeEventEnd(boolean enable) {
        OutPacket outPacket = new OutPacket(OutHeader.CUR_NODE_EVENT_END);
   
        outPacket.encodeByte(enable);
        
        return outPacket;
    }

    public static OutPacket createArkForceAtom(int charID, int skillID, List<ForceAtom> chargeDriveAtoms) {
        OutPacket outPacket = new OutPacket(OutHeader.ARK_CREATE_FORCE_ATOM);

        outPacket.encodeInt(charID);
        outPacket.encodeInt(skillID);
        outPacket.encodeInt(chargeDriveAtoms.size());// abyssal, gust, scarlet, basic
        for (ForceAtom fa : chargeDriveAtoms) {
            outPacket.encodeInt(fa.getForceAtomType().getForceAtomType());
            outPacket.encodeInt(fa.getBulletID());
            outPacket.encodeInt(fa.getFaiList().size());
            for (ForceAtomInfo fai : fa.getFaiList()) {
                outPacket.encodeInt(264874399);// unk
                outPacket.encodeByte(1);
                fai.encode(outPacket);
                outPacket.encodeByte(0);
            }
        }
        return outPacket;
    }

    public static OutPacket createForceAtom(boolean byMob, int userOwner, int charID, ForceAtomEnum forceAtomType, boolean toMob,
                                     List<Integer> targets, int skillID, List<ForceAtomInfo> faiList, Rect rect, int arriveDir, int arriveRange,
                                     Position forcedTargetPos, int bulletID, Position pos) {
        return createForceAtom(new ForceAtom(byMob, userOwner, charID, forceAtomType, toMob, targets, skillID, faiList, rect, arriveDir, arriveRange, forcedTargetPos, bulletID, pos));
    }

    public static OutPacket createForceAtom(boolean byMob, int userOwner, int charID, int forceAtomType, boolean toMob,
                                            List<Integer> targets, int skillID, List<ForceAtomInfo> faiList, Rect rect, int arriveDir, int arriveRange,
                                            Position forcedTargetPos, int bulletID, Position pos) {
        return createForceAtom(new ForceAtom(byMob, userOwner, charID, ForceAtomEnum.getByVal(forceAtomType), toMob, targets, skillID, faiList, rect, arriveDir, arriveRange, forcedTargetPos, bulletID, pos));
    }
    public static OutPacket createForceAtom(ForceAtom fa) {
        OutPacket outPacket = new OutPacket(OutHeader.CREATE_FORCE_ATOM);
        fa.encode(outPacket);
        return outPacket;
    }

    public static OutPacket finalAttackRequest(Char chr, int skillID, int finalSkillID, int delay, int mobID,
                                               int userRequestTime) {
        return finalAttackRequest(chr, skillID, finalSkillID, delay, mobID, userRequestTime, false, null);
    }

    public static OutPacket finalAttackRequest(Char chr, int skillID, int finalSkillID, int delay, int mobID,
                                               int userRequestTime, boolean left, Position base) {
        OutPacket outPacket = new OutPacket(OutHeader.FINAL_ATTACK_REQUEST);

        int wt = ItemConstants.getWeaponType(chr.getEquippedItemByBodyPart(BodyPart.Weapon).getItemId());

        outPacket.encodeInt(skillID);
        outPacket.encodeInt(finalSkillID);
        outPacket.encodeInt(wt);
        outPacket.encodeInt(delay);
        outPacket.encodeInt(mobID);
        outPacket.encodeInt(userRequestTime);
        if(skillID == 101000102) { // Air Riot
            outPacket.encodeByte(left);
            outPacket.encodePosition(base);
        }

        return outPacket;
    }

    public static OutPacket setAmmo(int ammo) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_AMMO);

        outPacket.encodeInt(ammo);

        return outPacket;
    }

    public static OutPacket createPsychicArea(int charID, PsychicArea pa) {
        OutPacket outPacket = new OutPacket(OutHeader.CREATE_PSYCHIC_AREA);

        outPacket.encodeInt(charID);

        outPacket.encodeByte(pa.success);
        outPacket.encodeInt(pa.action);
        outPacket.encodeInt(pa.actionSpeed);
        outPacket.encodeInt(-pa.localPsychicAreaKey);
        outPacket.encodeInt(pa.skillID);
        outPacket.encodeShort(pa.slv);
        outPacket.encodeInt(pa.psychicAreaKey);
        outPacket.encodeInt(pa.duration);
        outPacket.encodeByte(pa.isLeft);
        outPacket.encodeShort(pa.skeletonFilePathIdx);
        outPacket.encodeShort(pa.skeletonAniIdx);
        outPacket.encodeShort(pa.skeletonLoop);
        outPacket.encodePositionInt(pa.start);
        return outPacket;
    }

    public static OutPacket releasePsychicArea(int charID, int localAreaKey) {
        OutPacket outPacket = new OutPacket(OutHeader.RELEASE_PSYCHIC_AREA);

        outPacket.encodeInt(charID);
        outPacket.encodeInt(localAreaKey);

        return outPacket;
    }

    public static OutPacket createPsychicLock(boolean approved, PsychicLock pl) {
        OutPacket outPacket = new OutPacket(OutHeader.CREATE_PSYCHIC_LOCK);

        outPacket.encodeByte(approved);
        if (approved) {
            pl.encode(outPacket);
        }

        return outPacket;
    }

    public static OutPacket releasePsychicLock(int id) {
        OutPacket outPacket = new OutPacket(OutHeader.RELEASE_PSYCHIC_LOCK);

        outPacket.encodeInt(id);

        return outPacket;
    }

    public static OutPacket releasePsychicLockMob(List<Integer> ids) {
        OutPacket outPacket = new OutPacket(OutHeader.RELEASE_PSYCHIC_LOCK_MOB);

        for(int i : ids) {
            outPacket.encodeByte(1);
            outPacket.encodeInt(i);
        }
        outPacket.encodeByte(0);

        return outPacket;
    }

    public static OutPacket characterInfo(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.CHARACTER_INFO);

        CharacterStat cs = chr.getAvatarData().getCharacterStat();
        outPacket.encodeInt(chr.getId());

        outPacket.encodeInt(chr.getStat(Stat.level));
        outPacket.encodeShort(chr.getJob());
        outPacket.encodeShort(chr.getStat(Stat.subJob));
        outPacket.encodeByte(cs.getPvpGrade());
        outPacket.encodeInt(cs.getPop()); //Fame
        MarriageRecord marriage = chr.getMarriageRecord();
        outPacket.encodeByte(marriage != null);
        if(marriage != null) {
            marriage.encode(outPacket);
        }
        List<Short> makingSkills = new ArrayList<>();
        for (short i = 9200; i <= 9204; i++) {
            if (chr.getMakingSkillLevel(i * 10000) > 0) {
                makingSkills.add(i);
            }
        }
        outPacket.encodeByte(makingSkills.size());
        for (Short makingSkill : makingSkills) {
            outPacket.encodeShort(makingSkill);
        }
        outPacket.encodeString(chr.getGuild() == null ? "-" : chr.getGuild().getName());
        outPacket.encodeString(chr.getGuild() == null || chr.getGuild().getAlliance() == null ? "-" :
                chr.getGuild().getAlliance().getName());
        outPacket.encodeByte(-1); // Forced pet IDx
        outPacket.encodeByte(0); // User state (?)
        outPacket.encodeByte(chr.getPets().size() > 0); // pet activated
        for(Pet pet : chr.getPets()) {
            PetItem pi = pet.getItem();
            outPacket.encodeByte(1);
            outPacket.encodeInt(pet.getIdx());
            outPacket.encodeInt(pi.getItemId());
            outPacket.encodeString(pet.getName());
            outPacket.encodeByte(pi.getLevel());
            outPacket.encodeShort(pi.getTameness());
            outPacket.encodeByte(pi.getRepleteness());
            outPacket.encodeShort(pi.getPetSkill());
            outPacket.encodeInt(0); // equip 1
            outPacket.encodeInt(-1); // equip 2 (pet hue)
        }
        outPacket.encodeByte(0); // CUIUserInfo::SetPetInfo end

        // MedalAchievementInfo::Decode
        Equip medal = (Equip) chr.getEquippedInventory().getItems().stream().filter(i -> ItemConstants.isMedal(i.getItemId())).findFirst().orElse(null);
        outPacket.encodeInt(medal == null ? 0 : medal.getItemId());

        Set<Integer> medals = chr.getEquipInventory().getItems().stream().filter(i -> ItemConstants.isMedal(i.getItemId())).mapToInt(Item::getItemId).boxed().collect(Collectors.toSet());
        outPacket.encodeShort(medals.size()); // medal size
        for (Integer medalId : medals) {
            outPacket.encodeInt(medalId);
            outPacket.encodeFT(FileTime.fromType(FileTime.Type.MAX_TIME));
        }
        // for each medal, encode int (itemID) and complete time (FT)
        // End MedalAchievementInfo::Decode
        chr.encodeDamageSkins(outPacket);
        outPacket.encodeByte(cs.getNonCombatStatDayLimit().getCharisma());
        outPacket.encodeByte(cs.getNonCombatStatDayLimit().getInsight());
        outPacket.encodeByte(cs.getNonCombatStatDayLimit().getWill());
        outPacket.encodeByte(cs.getNonCombatStatDayLimit().getCraft());
        outPacket.encodeByte(cs.getNonCombatStatDayLimit().getSense());
        outPacket.encodeByte(cs.getNonCombatStatDayLimit().getCharm());
        outPacket.encodeInt(chr.getAccId());
        // FarmUserInfo::Decode
        new FarmUserInfo().encode(outPacket);
        // End FarmUserInfo::Decode
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        //Chairs
        List<Integer> chairs = chr.getInstallInventory().getItems().stream().filter(i -> (i.getItemId() / 10000 == 301)).mapToInt(Item::getItemId).boxed().collect(Collectors.toList());
        outPacket.encodeInt(chairs.size()); //chair amount(size)
        for (int itemId : chairs) {
       //     chr.chatMessage(itemId + "");
            outPacket.encodeInt(itemId);
        }
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        return outPacket;
    }

    public static OutPacket showItemUpgradeEffect(int charID, boolean success, boolean enchantDlg, int uItemID, int eItemID, boolean boom) {
        OutPacket outPacket = new OutPacket(OutHeader.SHOW_ITEM_UPGRADE_EFFECT);

        outPacket.encodeInt(charID);

        outPacket.encodeByte(boom ? 2 : success ? 1 : 0);
        outPacket.encodeByte(enchantDlg);
        outPacket.encodeInt(uItemID);
        outPacket.encodeInt(eItemID);

        outPacket.encodeInt(0);
        outPacket.encodeByte(0);
        outPacket.encodeByte(0);

        return outPacket;
    }

    public static OutPacket showItemReleaseEffect(int charID, short pos, boolean bonus) {
        OutPacket outPacket = new OutPacket(OutHeader.SHOW_ITEM_RELEASE_EFFECT);

        outPacket.encodeInt(charID);

        outPacket.encodeShort(pos);
        outPacket.encodeByte(bonus);

        return outPacket;
    }

    public static OutPacket hyperUpgradeDisplay(Equip equip, boolean downgradeable, long meso, long beforeMVP, int successChance,
                                                int destroyChance, boolean chanceTime) {
        OutPacket outPacket = new OutPacket(OutHeader.EQUIPMENT_ENCHANT);

        outPacket.encodeByte(EquipmentEnchantType.HyperUpgradeDisplay.getVal());
        outPacket.encodeByte(downgradeable);
        outPacket.encodeLong(meso);
        outPacket.encodeLong(beforeMVP);
        outPacket.encodeInt(successChance);
        outPacket.encodeInt(destroyChance);
        outPacket.encodeByte(chanceTime);
        TreeMap<EnchantStat, Integer> vals =  equip.getHyperUpgradeStats();
        int mask = 0;
        for(EnchantStat es : vals.keySet()) {
            mask |= es.getVal();
        }
        outPacket.encodeInt(mask);
        vals.forEach((es, val) -> outPacket.encodeInt(val));

        return outPacket;
    }

    public static OutPacket miniGameDisplay(EquipmentEnchantType eeType) {
        OutPacket outPacket = new OutPacket(OutHeader.EQUIPMENT_ENCHANT);

        outPacket.encodeByte(eeType.getVal());
        outPacket.encodeByte(0);
        outPacket.encodeInt(2000); // TODO nSeed

        return outPacket;
    }

    public static OutPacket showUpgradeResult(Equip oldEquip, Equip equip, boolean succeed, boolean boom, boolean canDegrade) {
        OutPacket outPacket = new OutPacket(OutHeader.EQUIPMENT_ENCHANT);

        outPacket.encodeByte(EquipmentEnchantType.ShowHyperUpgradeResult.getVal());
        outPacket.encodeInt(boom ? 2 : succeed ? 1 : canDegrade ? 0 : 3);
        outPacket.encodeByte(0);
        oldEquip.encode(outPacket);
        equip.encode(outPacket);

        return outPacket;
    }

    public static OutPacket showUnknownEnchantFailResult(byte msg) {
        OutPacket outPacket = new OutPacket(OutHeader.EQUIPMENT_ENCHANT);

        outPacket.encodeByte(EquipmentEnchantType.ShowUnknownFailResult.getVal());
        outPacket.encodeByte(msg);

        return outPacket;
    }

    public static OutPacket scrollUpgradeDisplay(boolean feverTime, List<ScrollUpgradeInfo> infos) {
        OutPacket outPacket = new OutPacket(OutHeader.EQUIPMENT_ENCHANT);

        outPacket.encodeByte(EquipmentEnchantType.ScrollUpgradeDisplay.getVal());
        outPacket.encodeByte(feverTime);

        outPacket.encodeByte(infos.size());
        for (ScrollUpgradeInfo sui : infos) {
            outPacket.encode(sui);
        }

        return outPacket;
    }

    public static OutPacket showScrollUpgradeResult(boolean feverAfter, int result, String desc, Equip prevEquip,
                                                    Equip newEquip) {
        OutPacket outPacket = new OutPacket(OutHeader.EQUIPMENT_ENCHANT);

        outPacket.encodeByte(EquipmentEnchantType.ShowScrollUpgradeResult.getVal());

        outPacket.encodeByte(feverAfter);
        outPacket.encodeInt(result);
        outPacket.encodeString(desc);
        outPacket.encode(prevEquip);
        outPacket.encode(newEquip);

        return outPacket;
    }

    public static OutPacket showTranmissionResult(Equip fromEq, Equip toEq) {
        OutPacket outPacket = new OutPacket(OutHeader.EQUIPMENT_ENCHANT);

        outPacket.encodeByte(EquipmentEnchantType.ShowTransmissionResult.getVal());
        fromEq.encode(outPacket);
        toEq.encode(outPacket);

        return outPacket;
    }

    public static OutPacket redCubeResult(int charID, boolean upgrade, int cubeID, int ePos, Equip equip) {
        OutPacket outPacket = new OutPacket(OutHeader.RED_CUBE_RESULT);

        outPacket.encodeInt(charID);

        outPacket.encodeByte(upgrade);
        outPacket.encodeInt(cubeID);
        outPacket.encodeInt(ePos);
        equip.encode(outPacket);

        return outPacket;
    }

    public static OutPacket inGameCubeResult(int charID, boolean upgrade, int cubeID, int ePos, Equip equip) {
        OutPacket outPacket = new OutPacket(OutHeader.IN_GAME_CUBE_RESULT);

        outPacket.encodeInt(charID);
        outPacket.encodeByte(upgrade);
        outPacket.encodeInt(cubeID);
        outPacket.encodeInt(ePos);
        equip.encode(outPacket);

        return outPacket;
    }

    public static OutPacket sitResult(int chrId, int fieldSeatId) {
        OutPacket outPacket = new OutPacket(OutHeader.SIT_RESULT);

        outPacket.encodeInt(chrId);
        if(fieldSeatId == -1) {
            outPacket.encodeByte(0);
        } else {
            outPacket.encodeByte(1);
            outPacket.encodeShort(fieldSeatId);
        }

        return outPacket;
    }

    public static OutPacket createDragon(Dragon dragon) {
        OutPacket outPacket = new OutPacket(OutHeader.DRAGON_CREATED);

        outPacket.encodeInt(dragon.getCharID());
        outPacket.encodeInt(dragon.getPosition().getX());
        outPacket.encodeInt(dragon.getPosition().getY());
        outPacket.encodeByte(dragon.getMoveAction()); //Move Action
        outPacket.encodeShort(dragon.getFh());
        outPacket.encodeShort(dragon.getJobCode());

        return outPacket;
    }

    public static OutPacket removeDragon(Dragon dragon) {
        OutPacket outPacket = new OutPacket(OutHeader.DRAGON_REMOVE);

        outPacket.encodeInt(dragon.getCharID());

        return outPacket;
    }

    public static OutPacket moveDragon(Dragon dragon, MovementInfo movementInfo) {
        OutPacket outPacket = new OutPacket(OutHeader.DRAGON_MOVE);

        outPacket.encodeInt(dragon.getCharID());
        outPacket.encode(movementInfo);

        return outPacket;
    }

    public static OutPacket questClear(int qrKey) {
        OutPacket outPacket = new OutPacket(OutHeader.QUEST_CLEAR);

        outPacket.encodeInt(qrKey);

        return outPacket;
    }

    public static OutPacket setQuestTime(List<Triple<Integer, FileTime, FileTime>> questTimes) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_QUEST_TIME);

        outPacket.encodeByte(questTimes.size());
        for(Triple<Integer, FileTime, FileTime> times : questTimes) {
            outPacket.encodeInt(times.getLeft());
            outPacket.encodeFT(times.getMiddle());
            outPacket.encodeFT(times.getRight());
        }

        return outPacket;
    }

    public static OutPacket removeDragon(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.DRAGON_REMOVE);

        outPacket.encodeInt(chr.getId());

        return outPacket;
    }

    public static OutPacket addWreckage(Char chr, Mob mob, int skillID, int debrisCount) {
        OutPacket outPacket = new OutPacket(OutHeader.ADD_WRECKAGE);

        outPacket.encodeInt(chr.getId());  //v2
        outPacket.encodePositionInt(mob.getPosition());
        outPacket.encodeInt(chr.getFieldID());  //v4
        outPacket.encodeInt(1);  //evanWreckage.nIDx
        outPacket.encodeInt(skillID);  //nSkillID
        outPacket.encodeInt(1);  //nType

        outPacket.encodeInt(debrisCount);  //Number on Skill Icon, # of Wreckages on map

        return outPacket;
    }

    public static OutPacket delWreckage(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.DEL_WRECKAGE);

        outPacket.encodeInt(chr.getId()); //Char ID
        outPacket.encodeInt(1); //Count
        outPacket.encodeByte(true); //Unk Boolean

        outPacket.encodeInt(1); //Unk


        return outPacket;
    }

    public static OutPacket enterFieldFoxMan(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.FOX_MAN_ENTER_FIELD);

        Position position = chr.getPosition();

        outPacket.encodeInt(chr.getId());
        outPacket.encodeShort(0);   // 1 = Haku Old Form,  0 = Haku New Form
        outPacket.encodePosition(position);
        outPacket.encodeByte(4); //MoveAction
        outPacket.encodeShort(0); //Foothold
        outPacket.encodeInt(0); //nUpgrade
        outPacket.encodeInt(0); //FanID Equipped by Haku

        return outPacket;
    }

    public static OutPacket whisper(Char source, String sourceName, byte channelIdx, boolean gm, String msg, boolean notFound) {
        OutPacket outPacket = new OutPacket(OutHeader.WHISPER);

        if(notFound) {
            outPacket.encodeByte(9);
            outPacket.encodeString(sourceName);
            outPacket.encodeByte(4);
            outPacket.encodeInt(channelIdx);
        } else {
            outPacket.encodeByte(18);
            outPacket.encodeString(sourceName);
            outPacket.encodeByte(channelIdx);
            outPacket.encodeByte(gm);
            outPacket.encodeString(msg);
            source.encodeChat(outPacket, msg);
            outPacket.encodeByte(0);
        }

        return outPacket;
    }

    public static OutPacket teleport(Position position, Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.TELEPORT);
        outPacket.encodeByte(false);// excl request

        outPacket.encodeByte(6);// calling type
        /*
        TODO: import the enum
        enum USER_TELEPORT_CALLING_TYPE
        {
          TELEPORT_CALLING_TYPE_DEFAULT = 0x0,
          TELEPORT_CALLING_TYPE_GUILD = 0x1,
          TELEPORT_CALLING_TYPE_FLAMEWIZARD_FLAREBLINK = 0x2,
          TELEPORT_CALLING_TYPE_BYSCRIPT = 0x3,
        };
         */
        outPacket.encodeInt(chr.getId());
        outPacket.encodePosition(position);

        return outPacket;
    }

    public static OutPacket fieldEffect(FieldEffect fieldEffect) {
        OutPacket outPacket = new OutPacket(OutHeader.FIELD_EFFECT);

        fieldEffect.encode(outPacket);

        return outPacket;
    }

    public static OutPacket removeBlowWeather() {
        return blowWeather(0, null, 0, null);
    }

    public static OutPacket blowWeather(int itemID, String message, int seconds, byte[] packedAvatarLook) {
        OutPacket outPacket = new OutPacket(OutHeader.BLOW_WEATHER);

        outPacket.encodeInt(itemID);
        if (itemID > 0) {
            outPacket.encodeString(message);
            outPacket.encodeInt(seconds); // seconds
            outPacket.encodeByte(packedAvatarLook != null);// boolean if true send PackedCharacterLook
            if (packedAvatarLook != null) {
                outPacket.encodeArr(packedAvatarLook);
            }
        }
        return outPacket;
    }

    public static OutPacket trunkDlg(TrunkDlg trunkDlg) {
        OutPacket outPacket = new OutPacket(OutHeader.TRUNK_DLG);

        outPacket.encodeByte(trunkDlg.getType().getVal());
        trunkDlg.encode(outPacket);

        return outPacket;
    }

    public static OutPacket openUI(UIType uiType) {
        return openUI(uiType.getVal());
    }

    public static OutPacket openUI(int uiID){
        OutPacket outpacket = new OutPacket(OutHeader.OPEN_UI);
        outpacket.encodeInt(uiID);
        return outpacket;
    }

    public static OutPacket closeUI(int uiID){
        OutPacket outpacket = new OutPacket(OutHeader.CLOSE_UI);
        outpacket.encodeInt(uiID);
        return outpacket;
    }

    public static OutPacket closeUI(UIType uiType) {
        return null;//closeUI(uiType.getVal());
    }

    public static OutPacket openUIWithOption(int uiID, int option, int[] minigameOptions){
        OutPacket outPacket = new OutPacket(OutHeader.OPEN_UI_WITH_OPTION);

        outPacket.encodeInt(uiID);
        outPacket.encodeInt(option);
        outPacket.encodeInt(minigameOptions.length);
        for (int i = 0; i < minigameOptions.length; i++) {
            outPacket.encodeInt(minigameOptions[i]);
        }
        return outPacket;
    }

    public static OutPacket socketCreateResult(boolean success) {
        OutPacket outPacket = new OutPacket(OutHeader.SOCKET_CREATE_RESULT);

        outPacket.encodeByte(success ? 2 : 3);

        return outPacket;
    }

    public static OutPacket changeMobZone(int mobID, int dataType) {
        OutPacket outPacket = new OutPacket(OutHeader.CHANGE_MOB_ZONE);

        outPacket.encodeInt(mobID);
        outPacket.encodeInt(dataType);

        return outPacket;
    }

    public static OutPacket createObtacle(ObtacleAtomCreateType oact, ObtacleInRowInfo oiri, ObtacleRadianInfo ori,
                                          Set<ObtacleAtomInfo> atomInfos) {
        OutPacket outPacket = new OutPacket(OutHeader.CREATE_OBTACLE);

        outPacket.encodeInt(0); // ? gets used in 1 function, which forwards it to another, which does nothing with it
        outPacket.encodeInt(atomInfos.size());
        outPacket.encodeByte(oact.getVal());
        if (oact == ObtacleAtomCreateType.IN_ROW) {
            oiri.encode(outPacket);
        } else if (oact == ObtacleAtomCreateType.RADIAL) {
            ori.encode(outPacket);
        }
        for(ObtacleAtomInfo atomInfo : atomInfos) {
            outPacket.encodeByte(true); // false -> no encode
            atomInfo.encode(outPacket);
            if (oact == ObtacleAtomCreateType.DIAGONAL) {
                atomInfo.getObtacleDiagonalInfo().encode(outPacket);
            }
        }

        return outPacket;
    }

    public static OutPacket runeStoneAppear(RuneStone runeStone) { //Spawn in RuneStone
        OutPacket outPacket = new OutPacket(OutHeader.RUNE_STONE_APPEAR);

        outPacket.encodeInt(0); // object id ??
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(runeStone.getRuneType().getVal()); // Rune Type
        outPacket.encodePositionInt(runeStone.getPosition()); // Position
        outPacket.encodeByte(runeStone.isFlip()); // flip

        return outPacket;
    }

    public static OutPacket completeRune(Char chr) { //RuneStone Disappears
        OutPacket outPacket = new OutPacket(OutHeader.RUNE_STONE_DISAPPEAR);

        outPacket.encodeInt(0);
        outPacket.encodeInt(chr.getId());
        outPacket.encodeInt(100);// ?
        outPacket.encodeShort(0);// or 2 bytes ?
        return outPacket;
    }

    public static OutPacket runeStoneUseAck(int type) {
        OutPacket outPacket = new OutPacket(OutHeader.RUNE_STONE_USE_ACK);

        outPacket.encodeInt(type);
        switch (type) {
            case 2:// Rune Delay time
                outPacket.encodeInt(0);
                break;
            case 4://That rune is to strong for you to handle
                break;
            case 5://Shows arrows
                break;
        }

        return outPacket;
    }

    public static OutPacket runeStoneDisappear() { //RuneStone is Used
        OutPacket outPacket = new OutPacket(OutHeader.RUNE_STONE_DISAPPEAR);

        outPacket.encodeInt(0); // Has to be 0
        outPacket.encodeInt(0); // Doesn't matter what number this is
        outPacket.encodeInt(0);
        outPacket.encodeShort(0);
        return outPacket;
    }

    public static OutPacket runeActSuccess(RuneType runeType, int time) {
        OutPacket outPacket = new OutPacket(OutHeader.RUNE_ACT_SUCCESS);

        outPacket.encodeInt(runeType.getVal());
        outPacket.encodeInt(time);

        return outPacket;
    }

    public static OutPacket runeStoneSkillAck(RuneType runeType) {
        OutPacket outPacket = new OutPacket(OutHeader.RUNE_STONE_SKILL_ACK);

        outPacket.encodeInt(runeType.getVal());
        outPacket.encodeByte(0);
        return outPacket;
    }

    public static OutPacket runeStoneClearAndAllRegister() {
        OutPacket outPacket = new OutPacket(OutHeader.RUNE_STONE_CLEAR_AND_ALL_REGISTER);
        int count = 0;
        outPacket.encodeInt(count); // count
        outPacket.encodeInt(0);
        for (int i = 0; i < count; i++) {
            outPacket.encodeInt(0); // not sure, but whatever
            // sub_1967EB0
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeByte(0);
        }
        return outPacket;
    }

    /**
     * Creates a Clock on a Field.
     * @param clockPacket the clock to display
     * @return packet for the client
     */
    public static OutPacket clock(ClockPacket clockPacket) {
        OutPacket outPacket = new OutPacket(OutHeader.CLOCK);

        clockPacket.encode(outPacket);

        return outPacket;
    }

    /**
     * Creates a packet for changing the elite state of a field.
     * @param eliteState The new elite state
     * @param notShowPopup whether or not the popup should show up (warning message for boss spawn, countdown for bonus)
     * @param bgm The new bgm if the state is ELITE_BOSS
     * @param propSpecialEliteEffect special elite effect
     * @param backUOL back uol
     * @return packet for the client
     */
    public static OutPacket eliteState(EliteState eliteState, boolean notShowPopup, String bgm, String propSpecialEliteEffect,
                                      String backUOL) {

        OutPacket outPacket = new OutPacket(OutHeader.ELITE_STATE);

        outPacket.encodeInt(eliteState.getVal()); // elite state
        outPacket.encodeInt(notShowPopup ? 1 : 0); // ?
        if (eliteState == EliteState.EliteBoss) {
            outPacket.encodeString(bgm); // bgm
        } else {
            outPacket.encodeString(propSpecialEliteEffect);
            outPacket.encodeString(backUOL);
        }

        return outPacket;
    }

    public static OutPacket setQuickMoveInfo(List<QuickMoveInfo> quickMoveInfos) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_QUICK_MOVE_INFO);

        outPacket.encodeByte(quickMoveInfos.size());
        quickMoveInfos.forEach(qmi -> qmi.encode(outPacket));

        return outPacket;
    }

    public static OutPacket groupMessage(GroupMessageType gmt, Char from, String msg) {

        OutPacket outPacket = new OutPacket(OutHeader.GROUP_MESSAGE.getValue());

        outPacket.encodeByte(gmt.ordinal());
        outPacket.encodeInt(from.getId());
        outPacket.encodeInt(from.getId());
        outPacket.encodeString(from.getName());
        outPacket.encodeString(msg);
        from.encodeChat(outPacket, msg);

        return outPacket;
    }

    public static OutPacket openGateCreated(OpenGate openGate) {
        OutPacket outPacket = new OutPacket(OutHeader.OPEN_GATE_CREATED);

        outPacket.encodeByte(1); // Animation
        outPacket.encodeInt(openGate.getChr().getId()); // Character Id
        outPacket.encodePosition(openGate.getPosition()); // Position
        outPacket.encodeByte(openGate.getGateId()); // Gate Id
        outPacket.encodeInt(openGate.getParty() != null ? openGate.getParty().getId() : 0); // Party Id

        return outPacket;
    }

    public static OutPacket openGateRemoved(OpenGate openGate) {
        OutPacket outPacket = new OutPacket(OutHeader.OPEN_GATE_REMOVED);

        outPacket.encodeByte(1); // Animation
        outPacket.encodeInt(openGate.getChr().getId()); // Character Id
        outPacket.encodeByte(openGate.getGateId()); // Gate Id

        return outPacket;
    }

    public static OutPacket createMirrorImage(Position position, int alpha, int red, int green, int blue, boolean left) {
        OutPacket outPacket = new OutPacket(OutHeader.CREATE_MIRROR_IMAGE);

        outPacket.encodePositionInt(position);
        outPacket.encodeInt(alpha); // nAlpha   out of 1,000 (?)
        outPacket.encodeInt(red); // R  out of 100,000 (?)

        outPacket.encodeInt(green); // G  out of 100,000 (?)
        outPacket.encodeInt(blue); // B  out of 100,000 (?)
        outPacket.encodeInt(left ? 1 : 0); // bLeft

        return outPacket;
    }

    public static OutPacket townPortalCreated(TownPortal townPortal, boolean noAnimation) {
        OutPacket outPacket = new OutPacket(OutHeader.TOWN_PORTAL_CREATED);

        outPacket.encodeByte(noAnimation); // No Animation  (false = Animation : true = No Animation)
        outPacket.encodeInt(townPortal.getChr().getId());
        outPacket.encodePosition(townPortal.getFieldPosition()); // as this doesn't need to be initialised yet.
        outPacket.encodePosition(townPortal.getFieldPosition()); //

        return outPacket;
    }

    public static OutPacket townPortalRemoved(TownPortal townPortal, boolean animation) {
        OutPacket outPacket = new OutPacket(OutHeader.TOWN_PORTAL_REMOVED);

        outPacket.encodeByte(animation); // Animation
        outPacket.encodeInt(townPortal.getChr().getId());

        return outPacket;
    }

    public static OutPacket setOneTimeAction(int charID, int action, int duration) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_ONE_TIME_ACTION);

        outPacket.encodeInt(charID);
        outPacket.encodeInt(action);
        outPacket.encodeInt(duration);

        return outPacket;
    }

    public static OutPacket makingSkillResult(int charID, int recipeCode, MakingSkillResult result, MakingSkillRecipe.TargetElem target, int incSkillProficiency) {
        OutPacket outPacket = new OutPacket(OutHeader.MAKING_SKILL_RESULT);

        outPacket.encodeInt(charID);

        outPacket.encodeInt(recipeCode);
        outPacket.encodeInt(result.getVal());
        if (result == MakingSkillResult.SUCESS_SOSO || result == MakingSkillResult.SUCESS_GOOD || result == MakingSkillResult.SUCESS_COOL) {
            outPacket.encodeInt(target.getItemID());
            outPacket.encodeInt(target.getCount());
        }
        outPacket.encodeInt(incSkillProficiency);

        return outPacket;
    }

    public static OutPacket makingSkillResult(int charID, int recipeCode, int result, int createdItemID, int itemCount, int incSkillProficiency) {
        OutPacket outPacket = new OutPacket(OutHeader.MAKING_SKILL_RESULT);

        outPacket.encodeInt(charID);

        outPacket.encodeInt(recipeCode);
        outPacket.encodeInt(result);
        if (result == 25 || result == 26 || result == 27) {
            outPacket.encodeInt(createdItemID);
            outPacket.encodeInt(itemCount);
        }
        outPacket.encodeInt(incSkillProficiency);

        return outPacket;
    }

    public static OutPacket registerExtraSkill(Char chr, int mainSkillId, Set<Integer> extraSkillIds) {
        return registerExtraSkill(chr.getPosition(), mainSkillId, extraSkillIds, chr.isLeft());
    }

    public static OutPacket registerExtraSkill(Position position, int mainSkilId, Set<Integer> extraSkillIds, boolean isLeft) {
        OutPacket outPacket = new OutPacket(OutHeader.REGISTER_EXTRA_SKILL);

        outPacket.encodePositionInt(position);
        outPacket.encodeShort(isLeft ? -1 : 1);
        outPacket.encodeInt(mainSkilId);
        outPacket.encodeShort(extraSkillIds.size());
        for (int extraSkillId : extraSkillIds) {
            outPacket.encodeInt(extraSkillId);
        }

        return outPacket;
    }

    public static OutPacket transferChannelReqIgnored(int error) {
        OutPacket outPacket = new OutPacket(OutHeader.TRANSFER_CHANNEL_REQ_IGNORED);

        outPacket.encodeByte(error);

        return outPacket;
    }

    public static OutPacket unityPortalResult() {
        OutPacket outPacket = new OutPacket(OutHeader.UNITY_PORTAL);

        outPacket.encodeInt(DimensionalMirror.values().length);
        for (DimensionalMirror unityPortal : DimensionalMirror.values()) {
            outPacket.encodeString(unityPortal.getName());
            outPacket.encodeString(unityPortal.getDesc());
            outPacket.encodeInt(unityPortal.getReqLevel());
            outPacket.encodeInt(0);// unk
            outPacket.encodeInt(unityPortal.getId());
            outPacket.encodeInt(unityPortal.getReqQuest());
            outPacket.encodeInt(unityPortal.getQuestToSave());
            outPacket.encodeByte(unityPortal.isSquad());

            outPacket.encodeInt(unityPortal.getRewards().length);
            for (Integer reward : unityPortal.getRewards()) {
                outPacket.encodeInt(reward);
            }
        }
        return outPacket;
    }

}
