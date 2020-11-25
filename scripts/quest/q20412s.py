# 20412 - [Job Adv] (Lv.100) Mihile 4rd job adv
sm.setSpeakerID(1101002)
if sm.sendAskYesNo("Are you ready, are you okay to  leave?"):
    sm.warp(913070100, 0)
    sm.createClockForMultiple(300, 913070100)
    sm.addEvent(sm.invokeAfterDelay(300 *1000, "warp", 130000000, 0))