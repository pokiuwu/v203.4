package net.swordie.ms.world.shop.result;

import net.swordie.ms.connection.OutPacket;

/**
 * Created on 3/29/2018.
 */
public class MsgShopResult implements ShopResult {

    private ShopResultType type;

    public MsgShopResult(ShopResultType type) {
        this.type = type;
    }

    @Override
    public ShopResultType getType() {
        return type;
    }

    @Override
    public void encode(OutPacket outPacket) {}
}
