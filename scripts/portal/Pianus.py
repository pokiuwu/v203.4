if sm.hasQuest(1666):
    sm.warpInstanceIn(931050429)
    sm.createClock(6*60)
    sm.invokeAfterDelay(6*60*1000, "warpInstanceOut", 230040410, 0)

else:
    map = 230040420
    portal = 2
    sm.warp(map, portal)
