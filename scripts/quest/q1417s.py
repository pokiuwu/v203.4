#   [Job Adv] (Lv.30)   Way of the Cleric

darkMarble = 4031013
job = "Cleric"
monster = "Zombie Lupin"

sm.setSpeakerID(9000025) # Grendel the Really Old
sm.sendNext("You wish to become a #b"+ job +"#k?\r\n"
                "A #b"+ job +"#k is specialised in supporting spells and use #bholy magic#k to defeat their enemies. "
                 "There are many useful skills you can acquire.")


sm.sendNext("Before I teach you the ways of the "+ job +", you will have to accomplish a very difficult test. "
             "I will warp you into a special map, in which I require you to defeat #b"+ monster +"#k "
             "and return 30 #i"+ str(darkMarble) +"##z"+ str(darkMarble) +"#s to me.")

response = sm.sendAskYesNo("Once you enter the map, you #rcannot#k return without the #b#t"+ str(darkMarble) +"#s#k, "
               "if you die you will lose your experience.\r\n"
               "Are you ready?")

if response:
    sm.warp(910140000, 0) # Magician Test Site
    sm.startQuestNoCheck(parentID)
else:
    sm.sendSayOkay("You cannot be a Magician forever. You #bwill#k have to face up to the test.\r\n"
                   "Talk to me when you are ready.")
sm.dispose()
