# 101020400
if sm.hasQuest(21201):
    sm.warpInstanceIn(914021000, 0)
    sm.addQRValue(21203, "0")
    sm.createClockForMultiple(15*60, 914021000, 914021010)
    sm.invokeAfterDelay(15*60*1000, "warpInstanceOut", 140030000, 0)
if sm.hasQuest(21302):
    sm.warpInstanceIn(914022100, 0)
    sm.setQRValue(21203, "1", False)
    sm.createClockForMultiple(20*60, 914022100)
    sm.invokeAfterDelay(20*60*1000, "warpInstanceOut", 140030000, 0)
sm.dispose()
