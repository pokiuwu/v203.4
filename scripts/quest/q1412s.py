#   [Job Adv] (Lv.30)   Way of the Page

darkMarble = 4031013
job = "Page"

sm.sendNext("You wish to become a #b"+ job +"#k?\r\n"
            "A #b"+ job +"#k is specialised in short weapons such as #bHammers#k and #bAxes#k. "
            "There are many useful skills you can acquire with both weapons, but I strongly recommend that you focus on one and stick to it.")


sm.sendNext("Before I teach you the ways of the "+ job +", you will have to accomplish a very difficult test. "
            "I will warp you into a special map, in which I require you to defeat #bSkeledogs#k "
            "and return 30 #i"+ str(darkMarble) +"##z"+ str(darkMarble) +"#s to me.")

response = sm.sendAskYesNo("Once you enter the map, you #rcannot#k return without the #b#t"+ str(darkMarble) +"#s#k, if you die you will lose your experience.\r\n"
                            "Are you ready?")

if response:
    sm.warp(910230000, 0)
    sm.startQuestNoCheck(parentID)
else:
    sm.sendSayOkay("You cannot stay a Swordman. You #bwill#k have to face up to the test.\r\n"
                   "Talk to me when you are ready.")
sm.dispose()
