# 921160700 - Escape! - PQ

if sm.hasMobsInField():
    sm.chat("The portal is not opened.")
else:
    sm.warpPartyOut(910002000) # Party Quest Map
    sm.giveExp(sm.getPQExp()) #Gives player PQ exp
sm.dispose()
