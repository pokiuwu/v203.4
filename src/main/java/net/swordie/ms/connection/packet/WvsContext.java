package net.swordie.ms.connection.packet;

import net.swordie.ms.client.alliance.AllianceResult;
import net.swordie.ms.client.character.*;
import net.swordie.ms.client.character.cards.CharacterCard;
import net.swordie.ms.client.character.info.ExpIncreaseInfo;
import net.swordie.ms.client.character.info.ZeroInfo;
import net.swordie.ms.client.character.items.Equip;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.items.MemorialCubeInfo;
import net.swordie.ms.client.character.potential.CharacterPotential;
import net.swordie.ms.client.character.quest.Quest;
import net.swordie.ms.client.character.quest.QuestEx;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.TownPortal;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.friend.Friend;
import net.swordie.ms.client.friend.result.FriendResult;
import net.swordie.ms.client.guild.Guild;
import net.swordie.ms.client.guild.bbs.GuildBBSPacket;
import net.swordie.ms.client.guild.result.GuildResult;
import net.swordie.ms.client.jobs.resistance.WildHunterInfo;
import net.swordie.ms.client.party.Party;
import net.swordie.ms.client.party.PartyMember;
import net.swordie.ms.client.party.PartyResult;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.*;
import net.swordie.ms.enums.MessageType;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.life.drop.DropInfo;
import net.swordie.ms.util.AntiMacro;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.container.Tuple;
import org.apache.log4j.LogManager;

import java.util.*;

import static net.swordie.ms.enums.InvType.EQUIPPED;
import static net.swordie.ms.enums.MessageType.*;

/**
 * Created on 12/22/2017.
 */
public class WvsContext {
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    public static void dispose(Char chr) {
        chr.dispose();
    }

    public static OutPacket exclRequest() {
        return new OutPacket(OutHeader.EXCL_REQUEST);
    }

    public static OutPacket statChanged(Map<Stat, Object> stats) {
        return statChanged(stats, false, (byte) -1, (byte) 0, (byte) 0, (byte) 0, false, 0, 0);
    }

    public static OutPacket statChanged(Map<Stat, Object> stats, boolean exclRequestSent, byte mixBaseHairColor,
                                        byte mixAddHairColor, byte mixHairBaseProb, byte charmOld, boolean updateCovery,
                                        int hpRecovery, int mpRecovery) {
        OutPacket outPacket = new OutPacket(OutHeader.STAT_CHANGED);

        outPacket.encodeByte(exclRequestSent);
        outPacket.encodeByte(0);// unk
        // GW_CharacterStat::DecodeChangeStat
        int mask = 0;
        for (Stat stat : stats.keySet()) {
            mask |= stat.getVal();
        }
        outPacket.encodeLong(mask);
        Comparator statComper = Comparator.comparingInt(o -> ((Stat) o).getVal());
        TreeMap<Stat, Object> sortedStats = new TreeMap<>(statComper);
        sortedStats.putAll(stats);
        for (Map.Entry<Stat, Object> entry : sortedStats.entrySet()) {
            Stat stat = entry.getKey();
            Object value = entry.getValue();
            switch (stat) {
                case skin:
                    outPacket.encodeByte((Byte) value);
                    break;
                case face:
                case hair:
                case hp:
                case mhp:
                case mp:
                case mmp:
                case pop:
                case charismaEXP:
                case insightEXP:
                case willEXP:
                case craftEXP:
                case senseEXP:
                case charmEXP:
                case eventPoints:
                case level:
                    outPacket.encodeInt((Integer) value);
                    break;
                case str:
                case dex:
                case inte:
                case luk:
                case ap:
                case fatigue:
                    outPacket.encodeShort((Short) value);
                    break;
                case sp:
                    if (value instanceof ExtendSP) {
                        ((ExtendSP) value).encode(outPacket);
                    } else {
                        outPacket.encodeShort((Short) value);
                    }
                    break;
                case exp:
                case money:
                    outPacket.encodeLong((Long) value);
                    break;
                case dayLimit:
                    ((NonCombatStatDayLimit) value).encode(outPacket);
                    break;
                case characterCard:
                    ((CharacterCard) value).encode(outPacket);
                    break;
                case pvp1:
                case pvp2:
                    break;
                case subJob:
                    Tuple<Short, Short> subJob = (Tuple<Short, Short>) value;
                    outPacket.encodeShort(subJob.getLeft());
                    outPacket.encodeShort(subJob.getRight());
            }
        }

        outPacket.encodeByte(mixBaseHairColor);
        outPacket.encodeByte(mixAddHairColor);
        outPacket.encodeByte(mixHairBaseProb);
        outPacket.encodeByte(charmOld > 0);
        if (charmOld > 0) {
            outPacket.encodeByte(charmOld);
        }
        outPacket.encodeByte(updateCovery);
        if (updateCovery) {
            outPacket.encodeInt(hpRecovery);
            outPacket.encodeInt(mpRecovery);
        }
        return outPacket;
    }

    public static OutPacket setMaplePoint(int maplePoint) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_MAPLE_POINT);
        outPacket.encodeInt(maplePoint);
        return outPacket;
    }

    public static OutPacket inventoryOperation(boolean exclRequestSent, boolean notRemoveAddInfo, InventoryOperation type, short oldPos, short newPos,
                                               int bagPos, Item item) {
        // logic like this in packets :(
        InvType invType = item.getInvType();
        if ((oldPos > 0 && newPos < 0 && invType == EQUIPPED) || (invType == EQUIPPED && oldPos < 0)) {
            invType = InvType.EQUIP;
        }

        OutPacket outPacket = new OutPacket(OutHeader.INVENTORY_OPERATION);

        outPacket.encodeByte(exclRequestSent);
        outPacket.encodeByte(1); // size
        outPacket.encodeByte(notRemoveAddInfo);

        boolean addMovementInfo = false;
        outPacket.encodeByte(type.getVal());
        outPacket.encodeByte(invType.getVal());
        outPacket.encodeShort(oldPos);
        switch (type) {
            case ADD:
                item.encode(outPacket);
                break;
            case UPDATE_QUANTITY:
                outPacket.encodeShort(item.getQuantity());
                break;
            case MOVE:
                outPacket.encodeShort(newPos);
                if (invType == InvType.EQUIP && (oldPos < 0 || newPos < 0)) {
                    addMovementInfo = true;
                }
                break;
            case REMOVE:
                if (invType == InvType.EQUIP && (oldPos < 0 || newPos < 0)) {
                    addMovementInfo = true;
                }
                break;
            case ITEM_EXP:
                outPacket.encodeLong(((Equip) item).getExp());
                break;
            case UPDATE_BAG_POS:
                outPacket.encodeInt(bagPos);
                break;
            case UPDATE_BAG_QUANTITY:
                outPacket.encodeShort(newPos);
                break;
            case UNK_1:
                break;
            case UNK_2:
                outPacket.encodeShort(bagPos); // ?
                break;
            case UPDATE_ITEM_INFO:
                item.encode(outPacket);
                break;
            case UNK_3:
                break;
        }
        outPacket.encodeByte(0);// new
        //if (bAddMovementInfo) {
        outPacket.encodeByte(addMovementInfo);
        //}
        //if (invType == InvType.EQUIP && (oldPos < 0 || newPos < 0)) {
        //    outPacket.encodeByte(item.getCashItemSerialNumber() > 0);// must be > 0
        //}
        return outPacket;
    }

    public static OutPacket updateEventNameTag(int[] tags) {
        OutPacket outPacket = new OutPacket(OutHeader.EVENT_NAME_TAG);

        for (int i = 0; i < 5; i++) {
            outPacket.encodeString("");
            if (i >= tags.length) {
                outPacket.encodeByte(-1);
            } else {
                outPacket.encodeByte(tags[i]);
            }
        }

        return outPacket;
    }

    public static OutPacket changeSkillRecordResult(Skill skill) {
        List<Skill> skills = new ArrayList<>();
        skills.add(skill);
        return changeSkillRecordResult(skills, true, false, false, false);
    }

    public static OutPacket changeSkillRecordResult(List<Skill> skills, boolean exclRequestSent, boolean showResult,
                                                    boolean removeLinkSkill, boolean sn) {
        OutPacket outPacket = new OutPacket(OutHeader.CHANGE_SKILL_RECORD_RESULT);

        outPacket.encodeByte(exclRequestSent);
        outPacket.encodeByte(showResult);
        outPacket.encodeByte(removeLinkSkill);
        outPacket.encodeShort(skills.size());
        for (Skill skill : skills) {
            outPacket.encodeInt(skill.getSkillId());
            outPacket.encodeInt(skill.getCurrentLevel());
            outPacket.encodeInt(skill.getMasterLevel());
            outPacket.encodeFT(FileTime.fromType(FileTime.Type.PLAIN_ZERO));
        }
        outPacket.encodeByte(sn);

        return outPacket;
    }

    public static OutPacket temporaryStatSet(TemporaryStatManager tsm) {
        OutPacket outPacket = new OutPacket(OutHeader.TEMPORARY_STAT_SET);

        boolean hasMovingAffectingStat = tsm.hasNewMovingEffectingStat(); // encoding flushes new stats
        tsm.encodeForLocal(outPacket);

        outPacket.encodeShort(0);
        outPacket.encodeByte(0);
        outPacket.encodeByte(0);
        outPacket.encodeByte(1);
        outPacket.encodeInt(0);
        if (hasMovingAffectingStat) {
            outPacket.encodeByte(0);
        }

        return outPacket;
    }

    public static OutPacket temporaryStatReset(TemporaryStatManager temporaryStatManager, boolean demount) {
        OutPacket outPacket = new OutPacket(OutHeader.TEMPORARY_STAT_RESET);

        for (int i : temporaryStatManager.getRemovedMask()) {
            outPacket.encodeInt(i);
        }
//        temporaryStatManager.getRemovedStats().forEach((cts, option) -> outPacket.encodeInt(0));
        temporaryStatManager.encodeRemovedIndieTempStat(outPacket);
        if (temporaryStatManager.hasRemovedMovingEffectingStat()) {
            outPacket.encodeByte(0);
        }
        outPacket.encodeByte(0); // ?
        outPacket.encodeByte(demount);

        temporaryStatManager.getRemovedStats().clear();
        return outPacket;
    }

    public static OutPacket skillUseResult(boolean stillGoing) {
        OutPacket outPacket = new OutPacket(OutHeader.SKILL_USE_RESULT);
        // 2221011 - Frozen Breath
        outPacket.encodeByte(stillGoing);

        return outPacket;
    }

    public static OutPacket dropPickupMessage(int money, short internetCafeExtra, short smallChangeExtra) {
        return dropPickupMessage(money, (byte) 1, internetCafeExtra, smallChangeExtra, (short) 0);
    }

    public static OutPacket dropPickupMessage(Item item, short quantity) {
        return dropPickupMessage(item.getItemId(), (byte) 0, (short) 0, (short) 0, quantity);
    }

    public static OutPacket dropPickupMessage(int i, byte type, short internetCafeExtra, short smallChangeExtra, short quantity) {
        OutPacket outPacket = new OutPacket(OutHeader.MESSAGE);

        outPacket.encodeByte(DROP_PICKUP_MESSAGE.getVal());
        outPacket.encodeInt(0);
        outPacket.encodeByte(0);

        if (internetCafeExtra > 0) type = 8;
        outPacket.encodeByte(type);
        // also error (?) codes -2, ,-3, -4, -5, <default>
        switch (type) {
            case -10:
                outPacket.encodeInt(100);// nItemID
                break;
            case 0: // item
                outPacket.encodeInt(i);
                outPacket.encodeInt(quantity); // ?
                break;
            case 1: // Mesos
                outPacket.encodeByte(false); // boolean: portion was lost after falling to the ground
                outPacket.encodeInt(i); // Mesos
                outPacket.encodeShort(smallChangeExtra); // Spotting small change
                break;
            case 2: // ?
                outPacket.encodeInt(100);// nItemID
                outPacket.encodeLong(0);
                break;
            case 8:
                outPacket.encodeInt(i); // Mesos
                outPacket.encodeShort(internetCafeExtra); // Internet cafe
                break;
        }

        return outPacket;
    }

    public static OutPacket questRecordMessageAddValidCheck(int qrKey, byte state) {
        OutPacket outPacket = new OutPacket(OutHeader.MESSAGE);

        outPacket.encodeByte(QUEST_RECORD_MESSAGE_ADD_VALID_CHECK.getVal());
        outPacket.encodeInt(qrKey);
        outPacket.encodeByte(true);
        outPacket.encodeByte(state);
        switch (state) {
            case 1:
                outPacket.encodeString("");
                break;
            case 2:
                outPacket.encodeLong(0);
                break;
            case 0:
                outPacket.encodeByte(0); // If quest is completed, but should never be true?
                break;
        }

        return outPacket;
    }

    public static OutPacket questRecordMessage(Quest quest) {
        OutPacket outPacket = new OutPacket(OutHeader.MESSAGE);

        outPacket.encodeByte(QUEST_RECORD_MESSAGE.getVal());
        outPacket.encodeInt(quest.getQRKey());
        QuestStatus state = quest.getStatus();
        outPacket.encodeByte(state.getVal());

        switch(state) {
            case NotStarted:
                outPacket.encodeByte(0); // If quest is completed, but should never be true?
                break;
            case Started:
                outPacket.encodeString(quest.getQRValue());
                break;
            case Completed:
                outPacket.encodeFT(quest.getCompletedTime());
                break;
        }

        return outPacket;
    }

    public static OutPacket questRecordExMessage(QuestEx quest) {
        OutPacket outPacket = new OutPacket(OutHeader.MESSAGE);

        outPacket.encodeByte(QUEST_RECORD_EX_MESSAGE.getVal());
        outPacket.encodeInt(quest.getQuestID());
        String str = "";
        for (Map.Entry<String, String> value : quest.getValues().entrySet()) {
            if (str.isEmpty()) {
                str = String.format("%s=%s", value.getKey(), value.getValue());
            } else {
                str = String.format("%s;%s=%s", str, value.getKey(), value.getValue());
            }
        }
        outPacket.encodeString(str);

        return outPacket;
    }

    public static OutPacket incExpMessage(ExpIncreaseInfo eii) {
        OutPacket outPacket = new OutPacket(OutHeader.MESSAGE);

        outPacket.encodeByte(INC_EXP_MESSAGE.getVal());
        eii.encode(outPacket);

        return outPacket;
    }

    public static OutPacket incSpMessage(short job, byte amount) {
        OutPacket outPacket = new OutPacket(OutHeader.MESSAGE);

        outPacket.encodeByte(INC_SP_MESSAGE.getVal());
        outPacket.encodeShort(job);
        outPacket.encodeByte(amount);

        return outPacket;
    }

    public static OutPacket incMoneyMessage(int amount) {
        OutPacket outPacket = new OutPacket(OutHeader.MESSAGE);

        outPacket.encodeByte(INC_MONEY_MESSAGE.getVal());
        outPacket.encodeInt(amount);
        outPacket.encodeInt(amount > 0 ? 1 : -1);// 24 for using extractor

        return outPacket;
    }

    /**
     * Returns a net.swordie.ms.connection.packet for messages with the following {@link MessageType}:<br>
     * GENERAL_ITEM_EXPIRE_MESSAGE<br>
     * ITEM_PROTECT_EXPIRE_MESSAGE<br>
     * ITEM_ABILITY_TIME_LIMITED_EXPIRE_MESSAGE<br>
     * SKILL_EXPIRE_MESSAGE
     *
     * @param mt    The message type.
     * @param items The list of ints that should be encoded.
     * @return The message OutPacket.
     */
    public static OutPacket message(MessageType mt, List<Integer> items) {
        OutPacket outPacket = new OutPacket(OutHeader.MESSAGE);

        outPacket.encodeByte(mt.getVal());
        switch (mt) {
            case GENERAL_ITEM_EXPIRE_MESSAGE:
            case ITEM_PROTECT_EXPIRE_MESSAGE:
            case ITEM_ABILITY_TIME_LIMITED_EXPIRE_MESSAGE:
            case SKILL_EXPIRE_MESSAGE:
                outPacket.encodeByte(items.size());
                items.forEach(outPacket::encodeInt);
                break;
        }
        return outPacket;
    }

    public static OutPacket itemExpireReplaceMessage(List<String> strings) {
        OutPacket outPacket = new OutPacket(OutHeader.MESSAGE);

        outPacket.encodeByte(ITEM_EXPIRE_REPLACE_MESSAGE.getVal());
        outPacket.encodeByte(strings.size());
        strings.forEach(outPacket::encodeString);

        return outPacket;
    }

    public static OutPacket incNonCombatStatEXPMessage(Stat trait, int amount) {
        OutPacket outPacket = new OutPacket(OutHeader.MESSAGE);

        outPacket.encodeByte(INC_NON_COMBAT_STAT_EXP_MESSAGE.getVal());
        long mask = 0;
        mask |= trait.getVal();
        outPacket.encodeLong(mask);
        outPacket.encodeInt(amount);

        return outPacket;
    }
    /**
     * Returns a net.swordie.ms.connection.packet for messages with the following {@link MessageType}:<br>
     * int: <br>
     * CASH_ITEM_EXPIRE_MESSAGE<br>
     * INC_POP_MESSAGE<br>
     * INC_GP_MESSAGE<br>
     * GIVE_BUFF_MESSAGE<br><br>
     * int + byte: <br>
     * INC_COMMITMENT_MESSAGE<br><br>
     * String: <br>
     * SYSTEM_MESSAGE<br><br>
     * int + String: <br>
     * QUEST_RECORD_EX_MESSAGE<br>
     * WORLD_SHARE_RECORD_MESSAGE<br>
     *
     * @param mt     The message type.
     * @param i      The integer to encode.
     * @param string The String to encode.
     * @param type   The type (byte) to encode.
     * @return The message OutPacket.
     */
    public static OutPacket message(MessageType mt, int i, String string, byte type) {
        OutPacket outPacket = new OutPacket(OutHeader.MESSAGE);

        outPacket.encodeByte(mt.getVal());
        switch (mt) {
            case CASH_ITEM_EXPIRE_MESSAGE:
            case INC_POP_MESSAGE:
            case INC_GP_MESSAGE:
            case GIVE_BUFF_MESSAGE:
                outPacket.encodeInt(i);
                break;
            case INC_COMMITMENT_MESSAGE:
                outPacket.encodeInt(i);
                outPacket.encodeByte(i < 0 ? 1 : i == 0 ? 2 : 0); // gained = 0, lost = 1, cap = 2
                break;
            case SYSTEM_MESSAGE:
                outPacket.encodeString(string);
                break;
            case QUEST_RECORD_EX_MESSAGE:
            case WORLD_SHARE_RECORD_MESSAGE:
            case COLLECTION_RECORD_MESSAGE:
                outPacket.encodeInt(i);
                outPacket.encodeString(string);
                break;
            case INC_HARDCORE_EXP_MESSAGE:
                outPacket.encodeInt(i); //You have gained x EXP
                outPacket.encodeInt(i); //Field Bonus Exp
                break;
            case BARRIER_EFFECT_IGNORE_MESSAGE:
                outPacket.encodeByte(type); //protection/shield scroll pop-up Message
                break;
        }

        return outPacket;
    }

    public static OutPacket flipTheCoinEnabled(byte enabled) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_FLIP_THE_COIN_ENABLED);

        outPacket.encodeByte(enabled);

        return outPacket;
    }

    public static OutPacket modComboResponse(int combo) {
        OutPacket outPacket = new OutPacket(OutHeader.MOD_COMBO_RESPONSE);

        outPacket.encodeInt(combo);

        return outPacket;
    }

    public static OutPacket wildHunterInfo(WildHunterInfo whi) {
        OutPacket outPacket = new OutPacket(OutHeader.WILD_HUNTER_INFO);

        whi.encode(outPacket);

        return outPacket;
    }

    public static OutPacket zeroInfo(ZeroInfo currentInfo) {
        OutPacket outPacket = new OutPacket(OutHeader.ZERO_INFO);

        currentInfo.encode(outPacket);

        return outPacket;
    }

    public static OutPacket gatherItemResult(byte type) {
        OutPacket outPacket = new OutPacket(OutHeader.GATHER_ITEM_RESULT);

        outPacket.encodeByte(0); // doesn't get used
        outPacket.encodeByte(type);

        return outPacket;
    }

    public static OutPacket sortItemResult(byte type) {
        OutPacket outPacket = new OutPacket(OutHeader.SORT_ITEM_RESULT);

        outPacket.encodeByte(0); // doesn't get used
        outPacket.encodeByte(type);

        return outPacket;
    }

    public static OutPacket clearAnnouncedQuest() {
        return new OutPacket(OutHeader.CLEAR_ANNOUNCED_QUEST);
    }

    public static OutPacket partyResult(PartyResult pri) {
        OutPacket outPacket = new OutPacket(OutHeader.PARTY_RESULT);

        outPacket.encode(pri);

        return outPacket;
    }

    public static OutPacket partyMemberCandidateResult(Set<Char> chars) {
        OutPacket outPacket = new OutPacket(OutHeader.PARTY_MEMBER_CANDIDATE_RESULT);

        outPacket.encodeByte(chars.size());
        for(Char chr : chars) {
            outPacket.encodeInt(chr.getId());
            outPacket.encodeString(chr.getName());
            outPacket.encodeShort(chr.getJob());
            outPacket.encodeShort(chr.getAvatarData().getCharacterStat().getSubJob());
            outPacket.encodeByte(chr.getLevel());
        }

        return outPacket;
    }

    public static OutPacket partyCandidateResult(Set<Party> parties) {
        OutPacket outPacket = new OutPacket(OutHeader.PARTY_CANDIDATE_RESULT);

        outPacket.encodeByte(parties.size());
        for(Party party : parties) {
            Char leader = party.getPartyLeader().getChr();
            outPacket.encodeInt(party.getId());
            outPacket.encodeString(leader.getName());
            outPacket.encodeByte(party.getAvgLevel());
            outPacket.encodeByte(party.getMembers().size());
            outPacket.encodeString(party.getName());
            outPacket.encodeByte(party.getMembers().size());
            for(PartyMember pm : party.getMembers()) {
                outPacket.encodeInt(pm.getCharID());
                outPacket.encodeString(pm.getCharName());
                outPacket.encodeShort(pm.getJob());
                outPacket.encodeShort(pm.getSubSob());
                outPacket.encodeByte(pm.getLevel());
                outPacket.encodeByte(pm.equals(party.getPartyLeader()));
            }
        }
        outPacket.encodeArr(new byte[40]);

        return outPacket;
    }

    public static OutPacket guildResult(GuildResult gri) {
        OutPacket outPacket = new OutPacket(OutHeader.GUILD_RESULT);

        gri.encode(outPacket);

        return outPacket;
    }

    public static OutPacket guildSearchResult(Collection<Guild> guilds) {
        OutPacket outPacket = new OutPacket(OutHeader.GUILD_SEARCH_RESULT);

        outPacket.encodeInt(guilds.size());
        for (Guild g : guilds) {
            outPacket.encodeInt(g.getId());
            outPacket.encodeInt(g.getLevel());
            outPacket.encodeString(g.getName());
            outPacket.encodeString(g.getGuildLeader().getName());
            outPacket.encodeInt(g.getMembers().size());
            outPacket.encodeInt(g.getAverageMemberLevel());
        }

        return outPacket;
    }


    public static OutPacket allianceResult(AllianceResult ar) {
        OutPacket outPacket = new OutPacket(OutHeader.ALLIANCE_RESULT);

        outPacket.encode(ar);

        return outPacket;
    }

    public static OutPacket guildBBSResult(GuildBBSPacket gbp) {
        OutPacket outPacket = new OutPacket(OutHeader.GUILD_BBS_RESULT);

        outPacket.encode(gbp);

        return outPacket;
    }

    public static OutPacket flameWizardFlareBlink(Char chr, Position newPosition, boolean used) {
        OutPacket outPacket = new OutPacket(OutHeader.FLAME_WIZARD_FLARE_BLINK);

        Position zero = new Position(0,0);
        outPacket.encodeInt(chr.getId()); //chr?
        outPacket.encodeByte(used); //used?

        if (used) {

        //Blink - Clear + Teleport
            chr.write(CField.teleport(newPosition, chr));

        } else {

        //Blink - Set Position
            outPacket.encodeByte(used);
            outPacket.encodeShort(1);
            outPacket.encodePosition(newPosition); //2x encode Short (x/y)
            outPacket.encodePosition(zero); //2x encode Short (x/y)
        }
        return outPacket;
    }

    public static OutPacket friendResult(FriendResult friendResult) {
        OutPacket outPacket = new OutPacket(OutHeader.FRIEND_RESULT);

        outPacket.encodeByte(friendResult.getType().getVal());
        friendResult.encode(outPacket);

        return outPacket;
    }


    public static OutPacket loadAccountIDOfCharacterFriendResult(Set<Friend> friends) {
        OutPacket outPacket = new OutPacket(OutHeader.LOAD_ACCOUNT_ID_OF_CHARACTER_FRIEND_RESULT);

        outPacket.encodeInt(friends.size());
        for(Friend fr : friends) {
            outPacket.encodeInt(fr.getFriendID());
            outPacket.encodeInt(fr.getFriendAccountID());
        }

        return outPacket;
    }

    public static OutPacket macroSysDataInit(List<Macro> macros) {
        OutPacket outPacket = new OutPacket(OutHeader.MACRO_SYS_DATA_INIT);

        outPacket.encodeByte(macros.size());
        for(Macro macro : macros) {
            macro.encode(outPacket);
        }
        return outPacket;
    }

    public static OutPacket monsterBookSetCard(int id) {
//        OutPacket outPacket = new OutPacket(OutHeader.MONSTER_LIFE_INVITE_ITEM_RESULT);
        OutPacket outPacket = new OutPacket(OutHeader.MONSTER_BOOK_SET_CARD);

        outPacket.encodeByte(id > 0); // false -> already added msg
        if (id > 0) {
            outPacket.encodeInt(id);
            outPacket.encodeInt(1); // card count, but we're just going to stuck with 1.
        }

        return outPacket;
    }

    public static OutPacket characterPotentialReset(PotentialResetType prt, int arg) {
        OutPacket outPacket = new OutPacket(OutHeader.CHARACTER_POTENTIAL_RESET);

        outPacket.encodeByte(prt.ordinal());
        switch (prt) {
            case Pos:
                outPacket.encodeShort(arg);
                break;
            case Skill:
                outPacket.encodeInt(arg);
                break;
            case All:
                break;
        }
        return outPacket;
    }

    public static OutPacket characterPotentialSet(CharacterPotential cp) {
        return characterPotentialSet(true, true, cp.getKey(), cp.getSkillID(), cp.getSlv(), cp.getGrade(), true);
    }

    public static OutPacket characterPotentialSet(boolean exclRequest, boolean changed, short pos, int skillID,
                                                  short skillLevel, short grade, boolean updatePassive) {
        OutPacket outPacket = new OutPacket(OutHeader.CHARACTER_POTENTIAL_SET);

        outPacket.encodeByte(exclRequest);
        outPacket.encodeByte(changed);
        if (changed) {
            outPacket.encodeShort(pos);
            outPacket.encodeInt(skillID);
            outPacket.encodeShort(skillLevel);
            outPacket.encodeShort(grade);
            outPacket.encodeByte(updatePassive);
        }

        return outPacket;
    }

    public static OutPacket characterHonorExp(int exp) {
        OutPacket outPacket = new OutPacket(OutHeader.CHARACTER_HONOR_EXP);

        outPacket.encodeInt(exp);

        return outPacket;
    }

    public static OutPacket cashPetPickUpOnOffResult(boolean changed, boolean on) {
        OutPacket outPacket = new OutPacket(OutHeader.CASHPET_PICK_UP_ON_OFF_RESULT);

        outPacket.encodeByte(on);
        outPacket.encodeByte(changed);

        return outPacket;
    }

    public static OutPacket setSonOfLinkedSkillResult(LinkedSkillResultType lsrt, int sonID, String sonName,
                                                      int originalSkillID, String existingParentName) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_SON_OF_LINKED_SKILL_RESULT);

        outPacket.encodeInt(lsrt.getVal());
        outPacket.encodeInt(originalSkillID);
        switch (lsrt) {
            case SetSonOfLinkedSkillResult_Success:
                outPacket.encodeInt(sonID);
                outPacket.encodeString(sonName);
                break;
            case SetSonOfLinkedSkillResult_Fail_ParentAlreadyExist:
                outPacket.encodeString(existingParentName);
                outPacket.encodeString(sonName);
                break;
            case SetSonOfLinkedSkillResult_Fail_Unknown:
                break;
            case SetSonOfLinkedSkillResult_Fail_MaxCount:
                outPacket.encodeString(existingParentName);
                break;
            case SetSonOfLinkedSkillResult_Fail_DBRequestFail:
                break;
        }

        return outPacket;
    }

    public static OutPacket memorialCubeResult(Equip equip, MemorialCubeInfo mci) {
        OutPacket outPacket = new OutPacket(OutHeader.MEMORIAL_CUBE_RESULT);

        outPacket.encodeLong(equip.getSerialNumber());
        mci.encode(outPacket);

        return outPacket;
    }

    public static OutPacket blackCubeResult(Equip equip, MemorialCubeInfo mci) {
        OutPacket outPacket = new OutPacket(OutHeader.BLACK_CUBE_RESULT);

        outPacket.encodeLong(equip.getSerialNumber());
        mci.encode(outPacket);
        outPacket.encodeInt(equip.getBagIndex());

        return outPacket;
    }

    public static OutPacket whiteCubeResult(Equip equip, MemorialCubeInfo mci) {
        OutPacket outPacket = new OutPacket(OutHeader.WHITE_ADDTIONAL_CUBE_RESULT);

        outPacket.encodeLong(equip.getSerialNumber());
        mci.encode(outPacket);
        outPacket.encodeInt(equip.getBagIndex());

        return outPacket;
    }

    public static OutPacket broadcastMsg(BroadcastMsg broadcastMsg) {
        OutPacket outPacket = new OutPacket(OutHeader.BROADCAST_MSG);

        broadcastMsg.encode(outPacket);

        return outPacket;
    }



    public static OutPacket broadcastMessage(int type, int channel, String message, boolean megaEar) {
        OutPacket outPacket = new OutPacket();

        outPacket.encodeShort(OutHeader.BROADCAST_MSG.getValue());
        outPacket.encodeByte(type);
        if (type == 4) {
            outPacket.encodeByte(1);
        }
        if ((type != 23) && (type != 24)) {
            outPacket.encodeString(message);
        }
        switch (type) {
            case 3:
            case 22:
            case 25:
            case 26: // crashes
                outPacket.encodeByte(channel - 1);
                outPacket.encodeByte(megaEar ? 1 : 0);
                break;
            case 9: // green background
                outPacket.encodeByte(channel - 1);
                break;
            case 12: // nothing
                outPacket.encodeInt(channel);
                break;
            case 6: // blue notice
            case 11: // blue notice
            case 20: // dc
                outPacket.encodeInt((channel >= 1000000) && (channel < 6000000) ? channel : 0);
                break;
            case 24: // dc
                outPacket.encodeShort(0);
            case 4: // server slide
            case 5: // red notice
            case 7: //dc
            case 8://dc
            case 10://dc
            case 13://does nothing
            case 14: //does nothing
            case 15://idk
            case 16://dc
            case 17://dc
            case 18://dc
            case 19://yellow
            case 21://dc
            case 23://dc
        }
       return outPacket;
    }

    public static OutPacket setAvatarMegaphone(Char chr, int megaItemId, List<String> lineList, boolean whisperIcon) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_AVATAR_MEGAPHONE);

        outPacket.encodeInt(megaItemId); // Avatar Megaphone Item ID
        outPacket.encodeString(chr.getName());

        for(String line : lineList) {
            outPacket.encodeString(line);
        }

        chr.encodeChat(outPacket, lineList.get(0));
        outPacket.encodeInt(chr.getClient().getChannel() - 1);
        outPacket.encodeByte(whisperIcon);

        chr.getAvatarData().getAvatarLook().encode(outPacket); // encode AvatarLook
        outPacket.encodeByte(0);

        return outPacket;
    }

    public static OutPacket receiveHyperStatSkillResetResult(int charID, boolean exclRequest, boolean success) {
        OutPacket outPacket = new OutPacket(OutHeader.RECEIVE_HYPER_STAT_SKILL_RESET_RESULT);

        outPacket.encodeByte(exclRequest);
        outPacket.encodeInt(charID);
        outPacket.encodeByte(success);

        return outPacket;
    }

    public static OutPacket mapTransferResult(MapTransferType mapTransferType, byte itemType, int[] hyperrockfields) {
        OutPacket outPacket = new OutPacket(OutHeader.MAP_TRANSFER_RESULT);

        outPacket.encodeByte(mapTransferType.getVal()); // Map Transfer Type
        outPacket.encodeByte(itemType); // Item Type (5 = Cash)
        if (mapTransferType == MapTransferType.DeleteListSend || mapTransferType == MapTransferType.RegisterListSend) {
            for (int fieldid : hyperrockfields) {
                outPacket.encodeInt(fieldid); // Target Field ID
            }
        }

        return outPacket;
    }

    public static OutPacket monsterCollectionResult(MonsterCollectionResultType mcrt, InvType invType, int fullSlots) {
        OutPacket outPacket = new OutPacket(OutHeader.MONSTER_COLLECTION_RESULT);

        outPacket.encodeInt(mcrt.ordinal());
        if (invType != null) {
            outPacket.encodeInt(invType.getVal());
        } else {
            outPacket.encodeInt(0);
        }
        outPacket.encodeInt(fullSlots);

        return outPacket;
    }

    public static OutPacket weatherEffectNotice(WeatherEffNoticeType type, String text, int duration) {
        OutPacket outPacket = new OutPacket(OutHeader.WEATHER_EFFECT_NOTICE);

        outPacket.encodeString(text); // Text
        outPacket.encodeInt(type.getVal()); // Weather Notice Type
        outPacket.encodeInt(duration); // Duration in ms
        outPacket.encodeByte(1); // Forced Notice

        return outPacket;
    }

    public static OutPacket resultInstanceTable(String name, int type, int subType, boolean rightResult, int value) {
        OutPacket outPacket = new OutPacket(OutHeader.RESULT_INSTANCE_TABLE.getValue());

        outPacket.encodeString(name);
        outPacket.encodeInt(type); // nCount
        outPacket.encodeInt(subType);
        outPacket.encodeByte(rightResult);
        outPacket.encodeInt(value);

        return outPacket;
    }

    public static OutPacket resultInstanceTable(InstanceTableType ritt, boolean rightResult, int value) {
        return resultInstanceTable(ritt.getTableName(), ritt.getType(), ritt.getSubType(), rightResult, value);
    }

    /**
     * Creates a packet to indicate the golden hammer is finished.
     *
     * @param returnResult See below
     * @param msg
     *  when returnResult is:
     *    0 or 1:
     *      Anything: Golden hammer refinement applied
     *    2:
     *      0: Increased available upgrade by 1
     *      1: Refining using golden hammer failed
     *    3:
     *      1: Item is not upgradable
     *      2: 2 upgrade increases have been used already
     *      3: You can't vicious hammer non-horntail necklace
     * @param upgradesLeft amount of upgrades left. NOTE: ((v9 >> 8) & 0xFF) - v9 + 2) (where v9 = upgradesLeft)
     * @return the created packet
     */
    public static OutPacket goldHammerItemUpgradeResult(byte returnResult, int msg, int upgradesLeft) {
        // Could create an enum for returnResult/msg, but it's not used often enough to warrant this
        OutPacket outPacket = new OutPacket(OutHeader.GOLD_HAMMER_ITEM_UPGRADE_RESULT);

        outPacket.encodeByte(returnResult);
        if (returnResult == 0) {
            outPacket.encodeInt(msg);
            outPacket.encodeInt(upgradesLeft);
        } else if (returnResult == 1) {
            // ?
        } else if (returnResult >= 2) {
            outPacket.encodeInt(msg);
        }

        return outPacket;
    }

    public static OutPacket returnToCharacterSelect() {

        return new OutPacket(OutHeader.RETURN_TO_CHARACTER_SELECT);
    }

    public static OutPacket returnToTitle() {
        return new OutPacket(OutHeader.RETURN_TO_TITLE);
    }

    public static OutPacket townPortal(TownPortal townPortal) {
        OutPacket outPacket = new OutPacket(OutHeader.TOWN_PORTAL); // As a response to Enter_TP_Request, creates the Door in the TownField

        outPacket.encodeInt(townPortal.getTownFieldId()); // townFieldId
        outPacket.encodeInt(townPortal.getFieldFieldId()); // field FieldId
        outPacket.encodeInt(townPortal.getSkillid()); // Skill Id
        outPacket.encodePosition(new Position()); // fieldField TownPortal Position

        return outPacket;
    }

    public static OutPacket givePopularityResult(PopularityResultType prType, Char targetChr, int newFame, boolean inc) {
        OutPacket outPacket = new OutPacket(OutHeader.GIVE_POPULARITY_RESULT);

        outPacket.encodeByte(prType.getVal());

        switch (prType) {
            case Success:
                outPacket.encodeString(targetChr.getName());
                outPacket.encodeByte(inc); // true = fame  |  false = defame
                outPacket.encodeInt(newFame);
                break;

            case InvalidCharacterId:
            case LevelLow:
            case AlreadyDoneToday:
            case AlreadyDoneTarget:
                break;

            case Notify:
                outPacket.encodeString(targetChr.getName());
                outPacket.encodeByte(inc); // true = fame  |  false = defame
                break;
        }

        return outPacket;
    }

    public static OutPacket randomPortalNotice(RandomPortal randomPortal) {
        OutPacket outPacket = new OutPacket(OutHeader.RANDOM_PORTAL_NOTICE);

        outPacket.encodeByte(randomPortal.getAppearType().ordinal());
        outPacket.encodeInt(randomPortal.getField().getId());

        return outPacket;
    }

    public static OutPacket randomMissionResult(RandomMissionType type, int arg1, int arg2) {
        OutPacket outPacket = new OutPacket(OutHeader.RANDOM_MISSION_RESULT);

        outPacket.encodeInt(type.getVal());
        outPacket.encodeInt(arg1);
        outPacket.encodeInt(arg2);

        return outPacket;
    }

    public static OutPacket antiMacroResult(final byte[] image, byte notificationType, byte antiMacroType) {
        return antiMacroResult(image, notificationType, antiMacroType, (byte) 0, (byte) 1);
    }

    public static OutPacket antiMacroResult(final byte[] image, byte notificationType, byte antiMacroType, byte first, byte refreshAntiMacroCount) {
        OutPacket outPacket = new OutPacket(OutHeader.ANTI_MACRO_RESULT);

        outPacket.encodeByte(notificationType);
        outPacket.encodeByte(antiMacroType);

        if (notificationType == AntiMacro.AntiMacroResultType.AntiMacroRes.getVal()) {
            outPacket.encodeByte(first);
            outPacket.encodeByte(refreshAntiMacroCount);

            if (image == null) {
                outPacket.encodeInt(0);
            } else {
                outPacket.encodeInt(image.length);
                outPacket.encodeArr(image);
            }
        } else if (notificationType == AntiMacro.AntiMacroResultType.AntiMacroRes_Fail.getVal() ||
                notificationType == AntiMacro.AntiMacroResultType.AntiMacroRes_Success.getVal()) {
            outPacket.encodeString(""); // unused?
        }

        return outPacket;
    }

    public static OutPacket setPassenserRequest(int requestorChrId) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_PASSENSER_REQUEST);

        outPacket.encodeInt(requestorChrId);

        return outPacket;
    }

    public static OutPacket platformarEnterResult(boolean wrap) {
        OutPacket outPacket = new OutPacket(OutHeader.PLAT_FORMAR_ENTER_RESULT);

        outPacket.encodeByte(wrap);

        return outPacket;
    }

    public static OutPacket platformarOxyzen(int oxyzen) {
        OutPacket outPacket = new OutPacket(OutHeader.PLAT_FORMAR_OXYZEN);

        outPacket.encodeInt(oxyzen); // casted to long in client side

        return outPacket;
    }

    public static OutPacket updateVMatrix(Char chr, boolean update, MatrixUpdateType updateType, int pos) {
        OutPacket outPacket = new OutPacket(OutHeader.VMATRIX_UPDATE);
        chr.getMatrixInventory().encode(outPacket);
        outPacket.encodeByte(update);
        if (update) {
            outPacket.encodeInt(updateType.getVal());
            // according to IDA pos applied only for enable
            if (updateType == MatrixUpdateType.ENABLE || updateType == MatrixUpdateType.DISABLE) {
                outPacket.encodeInt(pos);
            }
        }
        return outPacket;
    }

    public static OutPacket nodeShardResult(int shard) {
        OutPacket outPacket = new OutPacket(OutHeader.NODE_SHARD_RESULT);

        outPacket.encodeInt(shard);

        return outPacket;
    }

    public static OutPacket nodeEnhanceResult(int recordID, int exp, int slv1, int slv2) {
        OutPacket outPacket = new OutPacket(OutHeader.NODE_ENHANCE_RESULT);

        outPacket.encodeInt(recordID);
        outPacket.encodeInt(exp);
        outPacket.encodeInt(slv1);
        outPacket.encodeInt(slv2);

        return outPacket;
    }

    public static OutPacket nodeCraftResult(int coreID, int skillID1, int skillID2, int skillID3) {
        OutPacket outPacket = new OutPacket(OutHeader.NODE_CRAFT_RESULT);

        outPacket.encodeInt(coreID);
        outPacket.encodeInt(1);
        outPacket.encodeInt(skillID1);
        outPacket.encodeInt(skillID2);
        outPacket.encodeInt(skillID3);

        return outPacket;
    }

    public static OutPacket nodeStoneResult(int coreID, int skillID1, int skillID2, int skillID3) {
        OutPacket outPacket = new OutPacket(OutHeader.NODE_STONE_RESULT);

        outPacket.encodeInt(coreID);
        outPacket.encodeInt(1);
        outPacket.encodeInt(skillID1);
        outPacket.encodeInt(skillID2);
        outPacket.encodeInt(skillID3);
        outPacket.encodeInt(0);// unk maybe skill4 ?
        return outPacket;
    }

    public static OutPacket sendSessionValue(String key, String value) {
        OutPacket outPacket = new OutPacket(OutHeader.SESSION_VALUE);

        outPacket.encodeString(key);
        outPacket.encodeString(value);

        return outPacket;
    }

    public static OutPacket sendMonsterBookCardData(int cardID, Set<DropInfo> drops) {
        OutPacket outPacket = new OutPacket(OutHeader.MONSTER_BOOK_CARD_DATA);

        outPacket.encodeInt(cardID);
        outPacket.encodeShort(drops.size());
        for(DropInfo drop : drops) {
            outPacket.encodeInt(drop.getItemID());
        }
        return outPacket;
    }
}
