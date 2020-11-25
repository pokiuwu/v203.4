#   [Job Adv] (Lv.30)   Way of the Cleric

darkMarble = 4031013
job = "Cleric"

sm.setSpeakerID(9000025) # Grendel the Really Old
if sm.hasItem(darkMarble, 30):
    sm.sendNext("I am impressed, you surpassed the test. Only few are talented enough.\r\n"
                "You have proven yourself to be worthy, I shall mold your body into a #b"+ job +"#k.")
else:
    sm.sendSayOkay("You have not retrieved the #t"+ darkMarble+"#s yet, I will be waiting.")
    sm.dispose()


sm.consumeItem(darkMarble, 30)
sm.completeQuestNoRewards(parentID)
sm.sendNext("You are now a #b"+ job +"#k.")
sm.jobAdvance(230) # Cleric
sm.dispose()
