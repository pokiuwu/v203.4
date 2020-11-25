package net.swordie.ms.world.shop.cashshop;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.util.Util;

import java.util.*;

/**
 * Created on 4/21/2018.
 */
public class CashShop {
    private Map<CashShopCategory, List<CashShopItem>> items;
    private List<CashShopCategory> categories;
    private List<Integer> saleItems;
    private boolean eventOn;
    private boolean lockerTransfer;
    private boolean refundAvailable;
    private boolean usingOTP;
    private boolean usingNewOTP;
    private boolean betaTest;
    private CashItemInfo cii = new CashItemInfo();
    private final String BANNER_URL = "https://i.ytimg.com/vi/aXwf3CvmEC8/hqdefault.jpg";

    public CashShop() {
        items = new TreeMap<>(Comparator.comparingInt(CashShopCategory::getIdx));
        saleItems = new ArrayList<>();
        cii.setItemID(5160013);
        cii.setAccountID(0);
        cii.setCharacterID(0);
        cii.setCashItemSN(1);
        cii.setUnsure(1);
    }

    public Map<CashShopCategory, List<CashShopItem>> getItems() {
        return items;
    }

    public List<Integer> getSaleItems() {
        return saleItems;
    }

    public void encodeSaleInfo(OutPacket outPacket) {
        short size = 0;
//        outPacket.encodeShort(getSaleItems().size());
//        for(int i = 0; i < size; i++) {
//            outPacket.encodeInt(cii.getItemID());
//            cii.encode(outPacket);
//            cii.encode(outPacket);
//            // TODO
//        }
//        size = 0;
//        outPacket.encodeShort(size);
//        for (int i = 0; i < size; i++) {
//            outPacket.encodeInt(5160013);
//            outPacket.encodeString("ayy");
//        }
        size = 1;
        outPacket.encodeInt(size); // int per size
        for (int i = 0; i < size; i++) {
            outPacket.encodeInt(0);
        }
//        size = 1;
//        outPacket.encodeShort(size);
//        for (int i = 0; i < size; i++) {
//            outPacket.encodeInt(0);
//            outPacket.encodeString("");
//        }
        size = 1;
        outPacket.encodeInt(size); // randomItemCount
        for (int i = 0; i < size; i++) {
            outPacket.encodeArr(new byte[20]);
//            int itemID = 0;
//            outPacket.encodeInt(itemID); // itemID
//            if(itemID / 1000 == 5533) {
//                outPacket.encodeInt(0);
//            }

        }
//        outPacket.encodeInt(5160013);


    }

    public void encodeMainBest(OutPacket outPacket) {
        int size = 0;
        outPacket.encodeShort(size); // was int in kmst?
        for (int i = 0; i < size; i++) {
            outPacket.encodeByte(1); // nClass
            outPacket.encodeInt(5160013); // nQuestID?
        }
    }

    public void encodeCustomizedPackage(OutPacket outPacket) {
        int size = 0;
        outPacket.encodeInt(size);
        for (int i = 0; i < size; i++) {
            outPacket.encodeByte(2); // nClass
            outPacket.encodeInt(5160013); // nQuestID?
        }
    }

    public void encodeSearchHelper(OutPacket outPacket) {
        int size = 0;
        outPacket.encodeInt(size);
        for (int i = 0; i < size; i++) {
//            outPacket.encodeString("ayy"); // sKeyword
//            outPacket.encodeString("lmao"); // sMsg
            outPacket.encodeArr(new byte[10]);
        }
    }

    public void encode(OutPacket outPacket) {
//        outPacket.encodeArr(new byte[1 + 4 + 4 + 2 + 4 /*nox*/ + 1080 + 1 + 1 + 1 + 4 + 1 + 1 + 1 + 8 + 1 + 1 + 4 + 1 + 1 + /*extra*/2]);
        // CCashShop::LoadData
        outPacket.encodeByte(!isBetaTest());
        encodeSaleInfo(outPacket);
        encodeMainBest(outPacket);
        encodeCustomizedPackage(outPacket);
//        encodeSearchHelper(outPacket); // not in gms?
//        // buffer aBest, 3 inner loops (int (category) + int (gender) + int (sn))
        outPacket.encodeArr(new byte[1080]);
        short size = 0;
//        outPacket.encodeShort(size + 1);
//        for (int i = 0; i < size; i++) {
//            outPacket.encodeInt(0); // nSN
//            outPacket.encodeInt(0); // nStockState: CS_StockState IDA
//        }
//        outPacket.encodeShort(size);
//        for (int i = 0; i < size; i++) {
//            // CS_LIMITGOODS, size 116
//            outPacket.encodeInt(0);
//            for (int j = 0; j < 10; j++) {
//                outPacket.encodeInt(0); // nSN
//            }
//            outPacket.encodeInt(0); // CS_LimitGoodsState
//            outPacket.encodeInt(0); // nOriginCount
//            outPacket.encodeInt(0); // nRemainCount
//            outPacket.encodeInt(0); // dwConditionFlag
//            outPacket.encodeInt(0); // nDateStart
//            outPacket.encodeInt(0); // nDateEnd
//            outPacket.encodeInt(0); // nHourStart
//            outPacket.encodeInt(0); // nHourEnd
//            for (int j = 0; j < 7; j++) {
//                outPacket.encodeByte(0); // abWeek
//            }
//            outPacket.encodeByte(0); // nBackgrndType
//            outPacket.encodeString(""); // sNoticeMsg
//            outPacket.encodeInt(0); // nRepeatMin
//            // TODO Incomplete
//        }
        // END CCashShop::LoadData

        outPacket.encodeShort(0); // not in idb?
        // self
        outPacket.encodeByte(true);
        outPacket.encodeByte(true);
        outPacket.encodeByte(true);

        outPacket.encodeInt(0);

        outPacket.encodeByte(0);
        outPacket.encodeByte(0);
        outPacket.encodeByte(0);

        outPacket.encodeLong(0);


        outPacket.encodeByte(0);
        boolean someBool = false;
        outPacket.encodeByte(someBool);
        if(someBool) { // ^
            outPacket.encodeString("ayy");
        }
        outPacket.encodeInt(0);
        someBool = false;
        outPacket.encodeByte(someBool);
        if(someBool) { // ^
            outPacket.encodeLong(0);
        }
        outPacket.encodeByte(0);


        // kmst
//        outPacket.encodeByte(isEventOn());
//        outPacket.encodeByte(isLockerTransfer());
//        outPacket.encodeByte(isRefundAvailable());
//        outPacket.encodeByte(isUsingOTP());
//        outPacket.encodeByte(isUsingNewOTP());
    }

    public boolean isEventOn() {
        return eventOn;
    }

    public void setEventOn(boolean eventOn) {
        this.eventOn = eventOn;
    }

    public boolean isLockerTransfer() {
        return lockerTransfer;
    }

    public void setLockerTransfer(boolean lockerTransfer) {
        this.lockerTransfer = lockerTransfer;
    }

    public boolean isRefundAvailable() {
        return refundAvailable;
    }

    public void setRefundAvailable(boolean refundAvailable) {
        this.refundAvailable = refundAvailable;
    }

    public boolean isUsingOTP() {
        return usingOTP;
    }

    public void setUsingOTP(boolean usingOTP) {
        this.usingOTP = usingOTP;
    }

    public boolean isUsingNewOTP() {
        return usingNewOTP;
    }

    public void setUsingNewOTP(boolean usingNewOTP) {
        this.usingNewOTP = usingNewOTP;
    }

    public boolean isBetaTest() {
        return betaTest;
    }

    public void setBetaTest(boolean betaTest) {
        this.betaTest = betaTest;
    }

    public String getBannerUrl() {
        return BANNER_URL;
    }

    public List<CashShopCategory> getCategories() {
        return categories;
    }

    public CashShopCategory getCategoryByIdx(int idx) {
        return Util.findWithPred(getCategories(), csi -> csi.getIdx() == idx);
    }

    public void setCategories(List<CashShopCategory> categories) {
        this.categories = categories;
    }

    public void addItem(CashShopItem csi) {
        CashShopCategory csc = Util.findWithPred(getCategories(), cat -> cat.getName().equalsIgnoreCase(csi.getCategory()));
        csi.setCashShopCategory(csc);

        if (!getItems().containsKey(csc)) {
            getItems().put(csc, new ArrayList<>());
        }
        int newSize = getItems().size() + 1;
        int page = newSize / GameConstants.MAX_CS_ITEMS_PER_PAGE;
        csi.setSubCategory(4010000 + 10000 * page);
        csi.setParent(1000000 + 70000 + page * 100 + newSize % GameConstants.MAX_CS_ITEMS_PER_PAGE);
        getItems().get(csc).add(csi);
    }

    public List<CashShopItem> getItemsByCategoryIdx(int categoryIdx) {
        CashShopCategory csc = getCategoryByIdx(categoryIdx);
        return getItems().getOrDefault(csc, null);
    }

    public CashShopItem getItemByPosition(int itemPos) {
        for (Map.Entry<CashShopCategory, List<CashShopItem>> entry : getItems().entrySet()) {
            List<CashShopItem> items = entry.getValue();
            if (items.size() <= itemPos) {
                itemPos -= items.size();
            } else {
                return items.get(itemPos);
            }
        }
        return null;
    }
}
