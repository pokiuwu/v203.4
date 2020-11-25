# 223000000
TIME = 20

sm.warpInstanceIn(240091601, 0)
sm.createStopWatch(TIME)
sm.invokeAfterDelay(TIME*1000, "warpInstanceOut", 240091500, 2)