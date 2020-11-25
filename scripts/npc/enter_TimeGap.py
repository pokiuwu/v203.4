# Mastema (2450017) | Demon 4th job advancement
if sm.getChr().getLevel() >= 100 and sm.getChr().getJob() == 3111:
    sm.setSpeakerID(parentID)
    if sm.sendAskYesNo("Are you ready, #h #? If you are, I'll send you to the past through the Crack in Time. You were powerful in the past, #h #, so be careful."):
        sm.sendNext("Good luck, #h #")
        sm.warpInstanceIn(927000100)