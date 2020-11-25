# 921160200
# Party Quest - Escape! PQ

# Hidden Street : Aerial Prison
if fieldID == 921160600:
    warp = sm.getReactorQuantity() > 1
    if warp: # due to invisible reactor hidden on the map
        sm.chat("Unlock all the prison doors.")
        sm.dispose()
else:
    warp = sm.hasMobsInField()
    if not warp:
        sm.chat("The portal is not opened.")
        sm.dispose()
sm.warp(sm.getFieldID() + 100)
