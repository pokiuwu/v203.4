package net.swordie.ms.world.shop.result;

import net.swordie.ms.util.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created on 3/29/2018.
 */
public enum ShopResultType {
    Buy(0),
    NotEnoughInStockMsg(1),
    NotEnoughMesosMsg(2),
    NotEnoughPointsMsg(3),
    RequireFloorEnterMsg(4), // int
    DoNotMeetReqsMsg(5),
    CannotBePurchasedRnMsg(6),
    FullInvMsg(10),
    Update(11),
    VendorNotEnoughInStockMsg(12),
    GeneralErrorMsg(13),
    CannotHoldMoreMesosMsg(14),
    MesoCapPerTransaction2mMsg(15),
    TooManyMesosMsg(16),
    Success(17),
    NotEnoughInStockMsg2(18),
    // 19 doesn't exist?
    NotEnoughMesosMsg2(20),
    NeedMoreItemsMsg(21),
    NotEnoughStarCoinsMsg(22),
    MustBeUnderLevelMsgInt(23),
    MustBeOverLevelMsgInt(24),
    ItemPurchaseDateExpiredMsg(25),
    ItemHasBeenOutstockedMsgInt(26),
    CanOnlyBeBoughtOneByOneMsg(27),
    CannotBeMovedMsg(28),
    ShopRestockedMsgInt(29),
    CanOnlyPurchaseXMoreMsgInt(30),
    InactiveIPThereforeNoTradeMsg1Int(32),
    DifferentIPSoNoTradeAfterXMinMsgInt(33),
    CannotBeUsedMsg(34),
    CannotBeDoneOnThisWorldMsgByte(35),
    ItemDetailsChangedMsg(36),
    Below15LimitMsg(37),

    No(-1)
    ;

    private int val;

    ShopResultType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }




    public static void main(String[] args) {
        File file = new File("D:\\SwordieMS\\SwordieUTD\\src\\main\\java\\net\\swordie\\ms\\handlers\\header\\OutHeader.java");
        int change = 1;
        boolean check = false;
        ShopResultType checkOp = null;
        try (Scanner s = new Scanner(file)) {
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (line.contains(",") && line.contains("(")) {
                    String[] split = line.split("[()]");
                    String name = split[0];
                    if (!Util.isNumber(split[1])) {
                        System.out.println(line);
                        continue;
                    }
                    int val = Integer.parseInt(split[1]);
                    ShopResultType oh = Arrays.stream(ShopResultType.values()).filter(o -> o.toString().equals(name.trim())).findFirst().orElse(null);
                    if (oh != null) {
                        ShopResultType start = VendorNotEnoughInStockMsg;
                        if (oh.ordinal() >= start.ordinal() && oh.ordinal() < No.ordinal()) {
                            if (line.contains("*")) {
                                check = true;
                                checkOp = oh;
                            }
                            val += change;
                            System.out.println(String.format("%s(%d), %s", name, val, start == oh ? "// *" : ""));
                        } else {
                            System.out.println(line);
                        }
                    }
                } else {
                    System.out.println(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (check) {
            System.err.println(String.format("Current op (%s) contains a * (= updated). Be sure to check for overlap.", checkOp));
        }

    }
}