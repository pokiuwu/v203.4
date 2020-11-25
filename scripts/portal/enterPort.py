if sm.hasQuest(21301):
    sm.warpInstanceIn(914022000)
    sm.createClockForMultiple(10*60, 914022000)
    sm.invokeAfterDelay(10*60*1000, "warpInstanceOut", 140020200, 0)
else:
    sm.warp(140020300, 1)
    sm.dispose()