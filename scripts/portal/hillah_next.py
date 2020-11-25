# Azwan corridor 1 -> corridor 2 portal
from net.swordie.ms.constants import GameConstants

if sm.getChr().getField().getMobs().size() == 0:
    if int(sm.getQRValue(GameConstants.EASY_HILLA_QUEST)) < 2:
        sm.chatScript("The Bloodfang caught your scent! Prepare for battle!")
        for i in range(10):
            if sm.getFieldID() == 262030100:
                sm.spawnMob(2500201, 800, 86, False)
            elif sm.getFieldID() == 262030200:
                sm.spawnMob(2500221, 800, 86, False)
            elif sm.getFieldID() == 262031100:
                sm.spawnMob(2500203, 800, 86, False)
            elif sm.getFieldID() == 262031200:
                sm.spawnMob(2500223, 800, 86, False)

        sm.invokeForParty("setQRValue", GameConstants.EASY_HILLA_QUEST, "2")
    elif int(sm.getQRValue(GameConstants.EASY_HILLA_QUEST)) == 2:
        sm.invokeForParty("setQRValue", GameConstants.EASY_HILLA_QUEST, "1")
        if sm.getFieldID() == 262030100:
            sm.clearPartyInfo(262030200)
            sm.invokeForParty("warp", 262030200, 0)
        elif sm.getFieldID() == 262030200:
            sm.clearPartyInfo(262030300)
            sm.clearPartyInfo(262030310)
            sm.invokeForParty("warp", 262030300, 0)
        elif sm.getFieldID() == 262031100:
            sm.clearPartyInfo(262031200)
            sm.invokeForParty("warp", 262031200, 0)
        elif sm.getFieldID() == 262031200:
            sm.clearPartyInfo(262031300)
            sm.clearPartyInfo(262031310)
            sm.invokeForParty("warp", 262031300, 0)

else:
    sm.chat("Please kill all monsters to continue")