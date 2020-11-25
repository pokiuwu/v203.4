# horntail - Cave of Life - Cave of trial 1
from net.swordie.ms.constants import GameConstants

if sm.getFieldID() == 240060000:
    if int(sm.getQRValue(GameConstants.EASY_HORNTAIL_QUEST)) == 2:
        if sm.getChr().getField().getMobs().size() == 0:
            sm.invokeForParty("warp", 240060100, 0)
            sm.spawnNpc(2083002, 300, 0)
            sm.setQRValue(GameConstants.EASY_HORNTAIL_QUEST, "3")
            sm.dispose()
        else:
            sm.chat("Please eliminate all monsters")

elif sm.getFieldID() == 240060100:
    if int(sm.getQRValue(GameConstants.EASY_HORNTAIL_QUEST)) == 4:
        if sm.getChr().getField().getMobs().size() == 0:
            sm.invokeForParty("warp", 240060300, 0)
            sm.spawnNpc(2083002, -500, 0)
            sm.setQRValue(GameConstants.EASY_HORNTAIL_QUEST, "5")
            sm.dispose()
        else:
            sm.chat("Please eliminate all monsters")
elif sm.getFieldID() == 240060002:
    if int(sm.getQRValue(GameConstants.EASY_HORNTAIL_QUEST)) == 2:
        if sm.getChr().getField().getMobs().size() == 0:
            sm.invokeForParty("warp", 240060102, 0)
            sm.spawnNpc(2083002, 300, 0)
            sm.setQRValue(GameConstants.EASY_HORNTAIL_QUEST, "3")
            sm.dispose()
        else:
            sm.chat("Please eliminate all monsters")
elif sm.getFieldID() == 240060102:
    if int(sm.getQRValue(GameConstants.EASY_HORNTAIL_QUEST)) == 4:
        if sm.getChr().getField().getMobs().size() == 0:
            sm.invokeForParty("warp", 240060200, 0)
            sm.spawnNpc(2083002, -500, 0)
            sm.setQRValue(GameConstants.EASY_HORNTAIL_QUEST, "5")
            sm.dispose()
        else:
            sm.chat("Please eliminate all monsters")
elif sm.getFieldID() == 240060001:
    if int(sm.getQRValue(GameConstants.EASY_HORNTAIL_QUEST)) == 2:
        if sm.getChr().getField().getMobs().size() == 0:
            #sm.clearPartyInfo(240060101)
            sm.invokeForParty("warp", 240060101, 0)
            sm.spawnNpc(2083002, 300, 0)
            sm.setQRValue(GameConstants.EASY_HORNTAIL_QUEST, "3")
            sm.dispose()
        else:
            sm.chat("Please eliminate all monsters")

elif sm.getFieldID() == 240060101:
    if int(sm.getQRValue(GameConstants.EASY_HORNTAIL_QUEST)) == 4:
        if sm.getChr().getField().getMobs().size() == 0:
            #sm.clearPartyInfo(240060201)
            sm.invokeForParty("warp", 240060201, 0)
            sm.spawnNpc(2083002, -500, 0)
            sm.setQRValue(GameConstants.EASY_HORNTAIL_QUEST, "5")
            sm.dispose()
        else:
            sm.chat("Please eliminate all monsters")
