package net.swordie.ms.handlers;

import net.swordie.ms.client.character.BroadcastMsg;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.Inventory;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.enums.AdminCommandType;
import net.swordie.ms.enums.InvType;
import net.swordie.ms.loaders.ItemData;

import static net.swordie.ms.enums.ChatType.Mob;

/**
 * Created by MechAviv on 2/23/2019.
 */
public class AdminHandler {
    public static void handleAdminCommand(Char chr, InPacket inPacket) {
        if (chr == null || chr.getField() == null) {
            return;
        }
        if (chr.getAccount().isManagerAccount()) {
            int commandType = inPacket.decodeInt();

            AdminCommandType type = AdminCommandType.getByVal(commandType);
            if (type == null) {
                chr.chatMessage(Mob, String.format("[%s:%09d] %s", chr.getName(), chr.getFieldID(), "Unknown admin command type " + commandType));
                System.out.println(String.format("[%s:%09d] %s", chr.getName(), chr.getFieldID(), "Unknown admin command type " + commandType));
                return;
            }
            switch (type) {
                case Create: {
                    int itemID = inPacket.decodeInt();
                    String describe = null;
                    if (inPacket.getUnreadAmount() >= 2) {
                        describe = inPacket.decodeString();// should log it to database.
                    }
                    InvType invType = InvType.getInvTypeByVal(itemID / 1000000);
                    if (invType == null || invType.getVal() < 1 || invType.getVal() > 5) {
                        chr.write(WvsContext.broadcastMsg(BroadcastMsg.popUpMessage("Please enter valid item id")));
                        return;
                    }
                    String owner = null;
                    if (describe != null && inPacket.decodeByte() != 0) {
                        owner = inPacket.decodeString();
                    }
                    Item item = ItemData.getEquipDeepCopyFromID(itemID, true);
                    if (item == null) {
                        item = ItemData.getItemDeepCopy(itemID, true);
                        if (item == null) {
                            chr.write(WvsContext.broadcastMsg(BroadcastMsg.popUpMessage(String.format("Could not find an item with id %d", itemID))));
                            return;
                        }
                        item.setQuantity(1);
                    }
                    if (owner != null) {
                        item.setOwner(owner);
                    }
                    chr.addItemToInventory(invType, item, false);
                    System.out.println(String.format("Item (Type:Create, ID:%08d, n:%d)", itemID, 0));
                    break;
                }
                case Remove: {
                    int invType = inPacket.decodeByte();
                    if (invType < 1 || invType > 5) {
                        return;
                    }
                    clearInventory(chr, InvType.getInvTypeByVal(invType));
                    System.out.println(String.format("Item (Type:Destory, ID:%08d)", invType));
                    break;
                }
                case RemoveAll: {
                    for (InvType invType : InvType.values()) {
                        if (invType != InvType.EQUIPPED) {
                            clearInventory(chr, invType);
                        }
                    }
                    break;
                }
                case IncExp: {
                    chr.addExp(inPacket.decodeInt());
                    break;
                }

                case Portal: {
                    boolean enable = inPacket.decodeByte() == 1;
                    String portalName = inPacket.decodeString();
                    break;
                }
                case NpcVar: {
                    int varType = inPacket.decodeByte();
                    String npcName = inPacket.decodeString();
                    String varName = inPacket.decodeString();
                    if (varType == AdminCommandType.NpcVarSet.getVal()) {
                        String value = inPacket.decodeString();
                    }
                    break;
                }
                case Hide: {
                    /*
                    if ( v2->m_pField && v2->m_nGradeCode & 1 ) {
                        v71 = CInPacket::Decode1(v4);
                        CUser::SetHide(v2, v71);
                        CUser::SendCharacterHidePacket(v2);
                    }
                     */
                    break;
                }

                default:
                    chr.chatMessage(Mob, String.format("[%s:%09d] %s %s, %s", chr.getName(), chr.getFieldID(), "Unhandled admin command type ", type.name(), inPacket));
                    System.out.println(String.format("[%s:%09d] %s %s, %s", chr.getName(), chr.getFieldID(), "Unhandled admin command type ", type.name(), inPacket));
                    break;
            }
        }
    }
    public static void handleAdminLog(Char chr, InPacket inPacket) {
        System.out.println(String.format("[%s:%09d] %s", chr.getName(), chr.getFieldID(), inPacket.decodeString()));
    }

    private static void clearInventory(Char chr, InvType invType) {
        Inventory inv = chr.getInventoryByType(invType);
        if (inv != null) {
            for (int i = 0; i <= inv.getSlots(); i++) {
                Item removeItem = inv.getItemBySlot((short) i);
                if (removeItem != null) {
                    removeItem.setQuantity(0);
                    chr.consumeItem(removeItem);
                }
            }
        }
    }
}
