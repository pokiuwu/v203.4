#23212 | Contract with Mastema
sm.setSpeakerID(2450017)
if sm.sendAskYesNo("Everything is ready. Let us begin the contract ritual. Focus on your mind."):
    sm.setJob(3110)
    sm.addSP(5)
    sm.completeQuest(parentID)
    sm.setPlayerAsSpeaker()
    sm.sendNext("#b(You feel a curious energy flowing into you.)")
    sm.setSpeakerID(2450017)
    sm.sendNext("There... our contract is made. Now we can communicate through our minds. Isn't that neat?")
    sm.dispose()
else:
    sm.dispose()
