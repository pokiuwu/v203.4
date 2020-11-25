# WA - End of Knight-in-Training - Start
sm.setSpeakerID(1101002)
response = sm.sendAskYesNo("#h #, you have done surprisingly well. Do you wish to take the #b Knighthood Exam#k? If you pass, "
                "you will become a full-fledged knight. #b\r\n(Note, if you accept, you will be ported to Ereve. Talk to your instructor there.)")

if response:
    if not sm.getFieldID() == 130000000:
        sm.warp(130000000)
    sm.completeQuestNoRewards(20870)
else:
    sm.sendSayOkay("Okay, maybe next time.")
sm.dispose()
