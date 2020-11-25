# 925100400 - Fifth Map of the Lord Pirate PQ
if sm.getReactorQuantity() > 1:
    sm.chat("The portal is not opened.")
else:
    sm.warpPartyIn(sm.getFieldID() + 100) #Boss Map of Lord Pirate PQ
sm.dispose()
