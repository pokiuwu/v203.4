from net.swordie.ms.constants import GameConstants

# 925100100 - Second Map  of the Lord Pirate PQ
if int(sm.getQRValue(GameConstants.LORD_PIRATE_QUEST)) < 3:
    sm.chat("The portal is not opened.")
else:
    sm.warpPartyIn(sm.getFieldID() + 100)
sm.dispose()
