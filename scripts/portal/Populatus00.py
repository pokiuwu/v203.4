if sm.hasQuest(1662):
    sm.warpInstanceIn(931050427) # Silent Crusade Papu boss map
    sm.createClock(6*60)
    sm.invokeAfterDelay(6*60*1000, "warpInstanceOut", 220080000, 0)

else:
    sm.chat("This portal needs to be scripted.")