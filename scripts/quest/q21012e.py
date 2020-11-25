# Created by MechAviv
# Quest ID :: 21012
# Abilities Lost

sm.setSpeakerID(1202004)
if sm.sendAskYesNo("Hm... Your expression tells me that the exercise didn't jog any memories. But don't you worry. They'll come back, eventually. Here, drink this potion and power up!\r\n\r\n#fUI/UIWindow2.img/QuestIcon/4/0# \r\n#i2000022# 10 #t2000022# \r\n#i2000023# 10 #t2000023# \r\n\r\n#fUI/UIWindow2.img/QuestIcon/8/0# 57 exp"):
    sm.giveItem(2000022, 10)
    sm.giveItem(2000023, 10)
    sm.createQuestWithQRValue(21012, "003")
    sm.completeQuest(21012)
    sm.giveExp(57)

    sm.setSpeakerID(1202004)
    sm.setPlayerAsSpeaker()
    sm.sendNext("#b(Even if you're really the hero everyone says you are... What good are you without any skills?)#k")


    # [START_NAVIGATION] [20 9C 59 08 00 00 00 00 00 00 ]
else:
    sm.setSpeakerID(1202004)
    sm.sendNext("What? You don't want the potion?")