package net.swordie.ms.world.shop.cashshop;

import net.swordie.ms.Server;
import net.swordie.ms.client.Account;
import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.Inventory;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.trunk.Trunk;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.enums.CashItemType;
import net.swordie.ms.connection.packet.CCashShop;
import net.swordie.ms.enums.CashShopActionType;
import org.apache.log4j.Logger;

/**
 * Created on 4/23/2018.
 */
public class CashShopHandler {
    private static final Logger log = Logger.getLogger(CashShopHandler.class);

    public static void handleCashShopQueryCashRequest(Client c, InPacket inPacket) {
        c.write(CCashShop.queryCashResult(c.getChr()));
    }

    public static void handleCashShopCashItemRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        Account account = chr.getAccount();
        Trunk trunk = account.getTrunk();
        byte type = inPacket.decodeByte();
        CashItemType cit = CashItemType.getRequestTypeByVal(type);
        CashShop cs = Server.getInstance().getCashShop();
        if (cit == null) {
            log.error("Unhandled cash shop cash item request " + type);
            c.write(CCashShop.error());
            return;
        }
        switch (cit) {
            case Req_Buy:
                byte idk1 = inPacket.decodeByte();
                byte paymentMethod = inPacket.decodeByte();
                int idk2 = inPacket.decodeInt();
                int itemPos = inPacket.decodeInt();
                int cost = inPacket.decodeInt();
                CashShopItem csi = cs.getItemByPosition(itemPos - 1); // client's pos starts at 1
                if (csi == null || csi.getNewPrice() != cost) {
                    c.write(CCashShop.error());
                    log.error("Requested item's cost did not match client's cost");
                    return;
                }
                boolean notEnoughMoney = false;
                switch (paymentMethod) {
                    case 1: // Credit
                        if (account.getNxCredit() >= cost) {
                            account.deductNXCredit(cost);
                        } else {
                            notEnoughMoney = true;
                        }
                        break;
                    case 2: // Maple points
                        if (account.getMaplePoints() >= cost) {
                            account.deductMaplePoints(cost);
                        } else {
                            notEnoughMoney = true;
                        }
                        break;
                    case 4: // Prepaid
                        if (account.getNxPrepaid() >= cost) {
                            account.deductNXPrepaid(cost);
                        } else {
                            notEnoughMoney = true;
                        }
                        break;
                }
                if (notEnoughMoney) {
                    c.write(CCashShop.error());
                    log.error("Character does not have enough to pay for this item (Paying with " + paymentMethod + ")");
                    return;
                }
                CashItemInfo cii = csi.toCashItemInfo(account, chr);
                account.getTrunk().addCashItem(cii);
                c.write(CCashShop.cashItemResBuyDone(cii, null, null, 0));
                c.write(CCashShop.error());
                c.write(CCashShop.queryCashResult(chr));
                break;
            case Req_MoveLtoS:
                int idk3 = inPacket.decodeInt();
                int idk4 = inPacket.decodeInt();
                byte slot = (byte) (inPacket.decodeByte() - 1);
                cii = trunk.getLockerItemBySlot(slot);
                Item item = cii.toItem();
                Inventory inventory;
                if (ItemConstants.isEquip(item.getItemId())) {
                    inventory = chr.getEquipInventory();
                } else {
                    inventory = chr.getCashInventory();
                }
                if (!inventory.canPickUp(item)) {
                    c.write(CCashShop.fullInventoryMsg());
                    return;
                }
                trunk.removeCashItemBySlot(slot);
                chr.addItemToInventory(item);
                c.write(CCashShop.resMoveLtoSDone(item));
                c.write(CCashShop.loadLockerDone(account));
                break;
//            case Req_MoveStoL:
//
//                break;
            default:
                c.write(CCashShop.error());
                log.error("Unhandled cash shop cash item request " + cit);
                chr.dispose();
                break;
        }
    }

    public static void handleCashShopAction(Char chr, InPacket inPacket) {
        CashShop cashShop = Server.getInstance().getCashShop();
        byte type = inPacket.decodeByte();
        CashShopActionType csat = CashShopActionType.getByVal(type);
        if (csat == null) {
            log.error("Unhandled cash shop cash action request " + type);
            chr.write(CCashShop.error());
            return;
        }
        switch (csat) {
            case Req_OpenCategory:
                int categoryIdx = inPacket.decodeInt();
                chr.write(CCashShop.openCategoryResult(cashShop, categoryIdx));
                break;
            case Req_Favorite:
                break;

            default:
                chr.write(CCashShop.error());
                log.error("Unhandled cash shop cash action request " + csat);
                chr.dispose();
                break;
        }
    }
}
