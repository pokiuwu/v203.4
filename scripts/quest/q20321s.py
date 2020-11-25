# 20321 - [Job Adv] (Lv.60) Mihile 3rd job adv
sm.setSpeakerID(1101002)
if sm.sendAskYesNo("Are you ready to enter the Test area?"):
    sm.warpInstanceIn(913070200)
    sm.createClockForMultiple(300, 913070200)
    sm.addEvent(sm.invokeAfterDelay(300 *1000, "warp", 130000000, 0))