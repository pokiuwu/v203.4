# 22007 |   Collecting Eggs (Evan intro)
sm.setSpeakerID(1013101)
sm.sendNext("Oh, did you bring the #t4032451#? Here, give it to me. I'll give you the Incubator then.")
if sm.sendAskYesNo("Alright, here you go. I have no idea how you use it, but it's yours...\r\n\r\n#fUI/UIWindow2.img/QuestIcon/8/0# 360 exp"):
    sm.giveExp(360)
    sm.completeQuest(parentID)
    sm.consumeItem(4032451, 1)
    sm.dispose()
else:
    sm.sendNext("Hm? That's strange. The Incubator wasn't installed properly. Try again.")
    sm.dispose()
