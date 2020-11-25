
time = 15 *60

if "clear" in sm.getQRValue(62038):
    sm.warp(701220400)

elif "1" in sm.getQRValue(62007):
    sm.warpInstanceIn(701220410)
    sm.createClockForMultiple(time, 701220410, 701220510)
    sm.invokeAfterDelay(time*1000, "warpInstanceOut", 701220300, 3)
