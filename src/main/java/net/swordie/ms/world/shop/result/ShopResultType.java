package net.swordie.ms.world.shop.result;

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
    FullInvMsg(7),
    RechargeSuccess(8),
    PlayerNotEnoughInStockMsg(9),
    GeneralErrorMsg(10),
    TooManyMesosMsg(11),
    MesoCapPerTransaction2mMsg(12),
    CannotHoldMoreMesosMsg(13),
    Success(14),
    NotEnoughInStockMsg2(15),
    NotEnoughMesosMsg2(16),
    NeedMoreItemsMsg(19),
    NotEnoughStarCoinsMsg(20),
    MustBeUnderLevelMsgInt(21),
    MustBeOverLevelMsgInt(22),
    ItemPurchaseDateExpiredMsg(23),
    ItemHasBeenOutstockedMsgInt(24),
    CanOnlyBeBoughtOneByOneMsg(25),
    CannotBeMovedMsg(26),
    ShopRestockedMsgInt(27),
    CanOnlyPurchaseXMoreMsgInt(28),
    InactiveIPThereforeNoTradeMsg1Int(30),
    DifferentIPSoNoTradeAfterXMinMsgInt(31),
    CannotBeUsedMsg(32),
    CannotBeDoneOnThisWorldMsgByte(33),
    ItemDetailsChangedMsg(34),
    Below15LimitMsg(35),
    ;

    private int val;

    ShopResultType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
