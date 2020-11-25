# 22008 |   Chasing away the foxes (Evan intro)
sm.setSpeakerID(1013101)
if sm.sendAskAccept("It's strange. The chickens are acting funny. They used to hatch way more #t4032451#s. Do you think the Foxes have something to do with it? If so, we better hurry up and do something."):
    sm.startQuest(parentID)

    sm.removeEscapeButton()
    sm.sendNext("Right? Let us go and defeat those Foxes. Go on ahead and defeat #r10 #o9300385#es#k in #b#m100030103##k first. I'll follow you and take care of what's left behind. Now, hurry over to #m100030103#!")
    sm.sendSayImage("UI/tutorial/evan/10/0")
    sm.dispose()
else:
    sm.sendNext("Oh what... Are you scared of the #o9300385#es? Don't tell anyone you're related to me. That's shameful.")
    sm.dispose()
