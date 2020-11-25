package net.swordie.ms.world.shop.cashshop;

import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.db.FileTimeConverter;
import net.swordie.ms.connection.db.InlinedIntArrayConverter;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.util.FileTime;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created on 4/23/2018.
 */
@Entity
@Table(name = "cashiteminfos")
public class CashItemInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long unsure;
    private int accountID;
    private int characterID;
    private int itemID;
    private int commodityID;
    private short quantity;
    private String buyCharacterID;

    @Convert(converter = FileTimeConverter.class)
    private FileTime dateExpire;
    private int paybackRate;
    private double discount;
    private int orderNo;
    private int productNo;
    private boolean refundable;
    private byte sourceFlag;
    private boolean storeBank;
    private long cashItemSN;
    private int grade;
    private int position;
    @Convert(converter = InlinedIntArrayConverter.class)
    private int[] options = new int[3];

    public void encode(OutPacket outPacket) {
        // size 102
        outPacket.encodeLong(getUnsure());
        outPacket.encodeInt(getAccountID());
        outPacket.encodeInt(getCharacterID());
        outPacket.encodeInt(getItemID());
        outPacket.encodeInt(getCommodityID());
        outPacket.encodeShort(getQuantity());
        outPacket.encodeString(getBuyCharacterID(), 13);
        outPacket.encodeFT(getDateExpire());
        outPacket.encodeInt(getPaybackRate());
        outPacket.encodeLong((long) getDiscount());
        outPacket.encodeInt(getOrderNo());
        outPacket.encodeInt(getProductNo());
        outPacket.encodeByte(isRefundable());
        outPacket.encodeByte(getSourceFlag());
        outPacket.encodeByte(isStoreBank());
        // GW_CashItemOption::Decode
        outPacket.encodeLong(getCashItemSN());
        outPacket.encodeFT(getDateExpire());
        outPacket.encodeInt(getGrade());
        for (int i : getOptions()) {
            outPacket.encodeInt(i);
        }
    }

    public long getUnsure() {
        return unsure;
    }

    public void setUnsure(long unsure) {
        this.unsure = unsure;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getCharacterID() {
        return characterID;
    }

    public void setCharacterID(int characterID) {
        this.characterID = characterID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getCommodityID() {
        return commodityID;
    }

    public void setCommodityID(int commodityID) {
        this.commodityID = commodityID;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public String getBuyCharacterID() {
        return buyCharacterID;
    }

    public void setBuyCharacterID(String buyCharacterID) {
        this.buyCharacterID = buyCharacterID;
    }

    public FileTime getDateExpire() {
        return dateExpire;
    }

    public void setDateExpire(FileTime dateExpire) {
        this.dateExpire = dateExpire;
    }

    public int getPaybackRate() {
        return paybackRate;
    }

    public void setPaybackRate(int paybackRate) {
        this.paybackRate = paybackRate;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public boolean isRefundable() {
        return refundable;
    }

    public void setRefundable(boolean refundable) {
        this.refundable = refundable;
    }

    public byte getSourceFlag() {
        return sourceFlag;
    }

    public void setSourceFlag(byte sourceFlag) {
        this.sourceFlag = sourceFlag;
    }

    public boolean isStoreBank() {
        return storeBank;
    }

    public void setStoreBank(boolean storeBank) {
        this.storeBank = storeBank;
    }

    public long getCashItemSN() {
        return cashItemSN;
    }

    public void setCashItemSN(long cashItemSN) {
        this.cashItemSN = cashItemSN;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int[] getOptions() {
        return options;
    }

    public void setOptions(int[] options) {
        this.options = options;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashItemInfo that = (CashItemInfo) o;
        return itemID == that.itemID &&
                cashItemSN == that.cashItemSN;
    }

    @Override
    public int hashCode() {

        return Objects.hash(itemID, cashItemSN);
    }

    public Item toItem() {
        Item res = ItemData.getItemDeepCopy(getItemID());
        if (getDateExpire() != null) {
            res.setDateExpire(getDateExpire().deepCopy());
        }
        return res;
    }
}
