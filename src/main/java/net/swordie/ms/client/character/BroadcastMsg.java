package net.swordie.ms.client.character;

import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.BroadcastMsgType;

import java.util.List;

/**
 * Created by Asura on 17-6-2018.
 */
public class BroadcastMsg {
    BroadcastMsgType broadcastMsgType;
    Item item;
    String string;
    String string2;
    String string3;
    int arg1;
    int arg2;
    int arg3;
    Char chr;

    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(getBroadcastMsgType().getVal());

        outPacket.encodeString(getString());

        switch (getBroadcastMsgType()) {
            case SPEAKER_CHANNEL:
                chr.encodeChat(outPacket, getString());
                break;
            case NOTICE:
            case ALERT:
            case EVENT:
            case MONSTER_LIFE_WORLD_MSG:
            case MIRACLE_TIME:
                break;
            case SPEAKER_WORLD:
            case SPEAKER_WORLD_GUILD_SKILL:
                chr.encodeChat(outPacket, getString());
                outPacket.encodeByte(getArg1()); // Channel
                outPacket.encodeByte(getArg2()); // Mega Ear
                break;
            case ITEM_SPEAKER:
                chr.encodeChat(outPacket, getString());
                outPacket.encodeByte(getArg1()); // Channel
                outPacket.encodeByte(getArg2()); // Mega Ear
                outPacket.encodeByte(getArg3()); // Boolean  Item: Yes/No
                if(getArg3() != 0) {
                    getItem().encode(outPacket); // Item encode
                }
                break;
            case ART_SPEAKER_WORLD:
                chr.encodeChat(outPacket, getString());
                outPacket.encodeByte(getArg1()); // StringList size
                if(getArg1() > 1) {
                    outPacket.encodeString(getString2()); // String 2
                }
                if(getArg1() > 2) {
                    outPacket.encodeString(getString3()); // String 3
                }
                outPacket.encodeByte(getArg2()); // Channel
                outPacket.encodeByte(getArg3()); // Mega Ear
                break;
            case NOTICE_WITH_OUT_PREFIX:
            case LOTTERY_ITEM_SPEAKER_WORLD:
                outPacket.encodeInt(getArg1()); // item Id
                if(getArg1() != 0) {
                    getItem().encode(outPacket); // item encode
                }
                break;
            case UTIL_DLG_EX:
                outPacket.encodeInt(getArg1()); // npc Id
                break;
            case EVENT_MSG_WITH_CHANNEL:
                outPacket.encodeInt(getArg1()); //  chr Id
                // "#channel" will grab  Chr's  Channel
                break;
            case LOTTERY_ITEM_SPEAKER:
                outPacket.encodeByte(getArg1()); // Boolean  Item: Yes/No
                if(getArg1() != 0) {
                    getItem().encode(outPacket); // Item encode
                }
                break;
            case GACHAPON_MSG:
                outPacket.encodeInt(getArg1()); // item Id
                outPacket.encodeByte(getArg2()); // boolean: show item
                getItem().encode(outPacket);
                break;
            case NOTICE_WINDOW:
                outPacket.encodeInt(getArg1()); // width
                outPacket.encodeInt(getArg2()); // height
                break;
            case PICKUP_ITEM_WORLD:
            case MAKING_SKILL_MEISTER_ITEM:
                getItem().encode(outPacket); // Item encode
                break;
            case ANNOUNCED_QUEST_OPEN:
                outPacket.encodeInt(getArg1()); // Quest Id
                outPacket.encodeInt(getArg2()); // Time Out
                break;
            case ANNOUNCED_QUEST_CLOSED:
                outPacket.encodeInt(getArg1()); // Quest Id
                break;
            case SLIDE:
                outPacket.encodeByte(getArg1());
                break;
        }
    }



    public static BroadcastMsg tripleMegaphone(List<String> stringList, byte channel, boolean whisperEar, Char chr) {
        BroadcastMsg broadcastMsg = new BroadcastMsg();
        broadcastMsg.setBroadcastMsgType(BroadcastMsgType.ART_SPEAKER_WORLD);

        broadcastMsg.setArg1((byte) stringList.size());
        broadcastMsg.setString(stringList.get(0));
        if(stringList.size() > 1) {
            broadcastMsg.setString2(stringList.get(1));
        }
        if(stringList.size() > 2) {
            broadcastMsg.setString3(stringList.get(2));
        }
        broadcastMsg.setArg2((byte) (channel - 1));
        broadcastMsg.setArg3((byte) (whisperEar ? 1 : 0));
        broadcastMsg.setChr(chr);
        return broadcastMsg;
    }

    public static BroadcastMsg megaphone(String string, byte channel, boolean whisperEar, Char chr) {
        BroadcastMsg broadcastMsg = new BroadcastMsg();
        broadcastMsg.setBroadcastMsgType(BroadcastMsgType.SPEAKER_WORLD);

        broadcastMsg.setString(string);
        broadcastMsg.setArg1((byte) (channel - 1));
        broadcastMsg.setArg2((byte) (whisperEar ? 1 : 0));
        broadcastMsg.setChr(chr);
        return broadcastMsg;
    }


    public static BroadcastMsg itemMegaphone(String string, byte channel, boolean whisperEar, boolean containsItem, Item item, Char chr) {
        BroadcastMsg broadcastMsg = new BroadcastMsg();
        broadcastMsg.setBroadcastMsgType(BroadcastMsgType.ITEM_SPEAKER);

        broadcastMsg.setString(string);
        broadcastMsg.setArg1((byte) (channel - 1));
        broadcastMsg.setArg2((byte) (whisperEar ? 1 : 0));
        broadcastMsg.setArg3((byte) (containsItem ? 1 : 0));
        broadcastMsg.setItem(item);
        broadcastMsg.setChr(chr);

        return broadcastMsg;
    }

    public static BroadcastMsg yellowFilled(String string, Item item, boolean show) {
        BroadcastMsg broadcastMsg = new BroadcastMsg();
        broadcastMsg.setBroadcastMsgType(BroadcastMsgType.GACHAPON_MSG);

        broadcastMsg.setString(string);
        broadcastMsg.setItem(item);
        broadcastMsg.setArg1(show ? 1 : 0);

        return broadcastMsg;
    }


    public static BroadcastMsg notice(String string) {
        BroadcastMsg broadcastMsg = new BroadcastMsg();
        broadcastMsg.setBroadcastMsgType(BroadcastMsgType.NOTICE);

        broadcastMsg.setString(string);

        return broadcastMsg;
    }

    public static BroadcastMsg popUpMessage(String string) {
        BroadcastMsg broadcastMsg = new BroadcastMsg();
        broadcastMsg.setBroadcastMsgType(BroadcastMsgType.ALERT);

        broadcastMsg.setString(string);

        return broadcastMsg;
    }

    public static BroadcastMsg popUpNotice(String string, int width, int height) {
        BroadcastMsg broadcastMsg = new BroadcastMsg();
        broadcastMsg.setBroadcastMsgType(BroadcastMsgType.NOTICE_WINDOW);

        broadcastMsg.setString(string);
        broadcastMsg.setArg1(width);
        broadcastMsg.setArg2(height);

        return broadcastMsg;
    }

    public static BroadcastMsg blueChatWithItemInfo(String string, Item item) {
        BroadcastMsg broadcastMsg = new BroadcastMsg();
        broadcastMsg.setBroadcastMsgType(BroadcastMsgType.NOTICE_WITH_OUT_PREFIX);

        broadcastMsg.setString(string);
        broadcastMsg.setArg1(item.getItemId());
        broadcastMsg.setItem(item);

        return broadcastMsg;
    }

    public static BroadcastMsg errorMessage(String string, int npcId) {
        BroadcastMsg broadcastMsg = new BroadcastMsg();
        broadcastMsg.setBroadcastMsgType(BroadcastMsgType.UTIL_DLG_EX);

        broadcastMsg.setString(string);
        broadcastMsg.setArg1(npcId);

        return broadcastMsg;
    }


  /*
    public static BroadcastMsg slideNotice(String string, boolean show) {
        BroadcastMsg broadcastMsg = new BroadcastMsg();
        broadcastMsg.setBroadcastMsgType(BroadcastMsgType.SlideNotice);

        broadcastMsg.setString(string);
        broadcastMsg.setArg1(show ? 1 : 0);

        return broadcastMsg;
    }
*/

    public BroadcastMsgType getBroadcastMsgType() {
        return broadcastMsgType;
    }

    public void setBroadcastMsgType(BroadcastMsgType broadcastMsgType) {
        this.broadcastMsgType = broadcastMsgType;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }

    public String getString3() {
        return string3;
    }

    public void setString3(String string3) {
        this.string3 = string3;
    }

    public int getArg1() {
        return arg1;
    }

    public void setArg1(int arg1) {
        this.arg1 = arg1;
    }

    public int getArg2() {
        return arg2;
    }

    public void setArg2(int arg2) {
        this.arg2 = arg2;
    }

    public int getArg3() {
        return arg3;
    }

    public void setArg3(int arg3) {
        this.arg3 = arg3;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setChr(Char chr) {
        this.chr = chr;
    }
}
