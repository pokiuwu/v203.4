package net.swordie.ms.world.shop.result;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.world.shop.NpcShopDlg;

/**
 * Created on 3/29/2018.
 */
public class RechargeShopResult implements ShopResult {

    private NpcShopDlg shop;

    public RechargeShopResult(NpcShopDlg shop) {
        this.shop = shop;
    }

    @Override
    public ShopResultType getType() {
        return ShopResultType.RechargeSuccess;
    }

    @Override
    public void encode(OutPacket outPacket) {
        shop.encode(outPacket);
    }
}
