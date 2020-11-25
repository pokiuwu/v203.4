if sm.hasQuest(20320):
    sm.setSpeakerID(1101002)
    if sm.sendAskYesNo("Are you ready to enter the Test area?"):
        sm.warpInstanceIn(913070200)
        sm.createClockForMultiple(300, 913070200)
        sm.addEvent(sm.invokeAfterDelay(300 *1000, "warp", 130000000, 0))
elif sm.hasQuest(20411):
    sm.setSpeakerID(1101002)
    if sm.sendAskYesNo("Are you ready, are you okay to  leave?"):
        sm.warp(913070100, 0)
        sm.createClockForMultiple(300, 913070100)
        sm.addEvent(sm.invokeAfterDelay(300 *1000, "warp", 130000000, 0))