time = 7 *60

sm.createClockForMultiple(time, 931050400, 931050402)
sm.invokeAfterDelay(time*1000, "sm.warpInstanceOut", 103020000, 2)