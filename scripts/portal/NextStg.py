fieldID = sm.getFieldID()
if fieldID == 811000500:
    sm.warpPartyOut(811000008)
elif not sm.hasMobsInField():
    sm.warp(fieldID + 100)
else:
    sm.chat("The portal is not opened yet.")
sm.dispose()
