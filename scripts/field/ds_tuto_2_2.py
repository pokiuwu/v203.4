# Created by MechAviv
# ID :: [927000070]
# Hidden Street : Black Mage's Main Corridor 2

sm.completeQuestNoRewards(23203)
sm.deleteQuest(23203)

OBJECT_1 = sm.sendNpcController(2159309, 500, 50)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(2)
sm.sendDelay(30)


sm.forcedInput(0)
sm.setSpeakerID(2159308)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("You are rather powerful, aren't you? I think it's time we settled which of us is stronger. I've always wanted to test my magic against your Demon Fury. Of course, I know who will be victorious!")


sm.chatScript("Press the Control key rapidly to block Arkarium's attack and counterattack.")
sm.showEffect("Effect/Direction6.img/effect/tuto/guide1/0", 5010, 150, -300, -2, -2, False, 0)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "alert", 0)
sm.showEffect("Effect/Direction6.img/effect/tuto/arkyrimAttack", 0, 0, -7, 0, OBJECT_1, True, 0)
sm.sendDelay(2010)


sm.playSound("demonSlayer/arkAttack0", 100)
while not sm.patternInputRequest("17#17#17#", 4, 2, 3000) and sm.getPatternInputCount() < 7:
    sm.chatScript("Press the Control key rapidly to block Arkarium's attack and counterattack.")
    sm.showEffect("Effect/Direction6.img/effect/tuto/guide1/0", 5010, 150, -300, -2, -2, False, 0)

sm.fadeInOut(600, 1500, 600, 150)
sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/9", 2000, 0, -100, -2, -2, False, 0)
sm.forcedAction(376, 0)
sm.showEffect("Skill/3112.img/skill/31121000/effect", 0, 426, 83, 0, 0, False, 0)
sm.playSound("demonSlayer/31121000", 100)
sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/9", 2000, 0, -100, -2, -2, False, 0)
sm.sendDelay(900)


sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/4", 1000, 0, -150, 0, OBJECT_1, True, 0)
sm.playSound("demonSlayer/31121000h", 100)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "teleportation", 0)
sm.sendDelay(570)


sm.sendNpcController(OBJECT_1, False)
OBJECT_2 = sm.sendNpcController(2159309, 620, 50)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
sm.sendDelay(1000)


sm.setSpeakerID(2159308)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("You're stronger than I expected! How amusing!")


sm.showNpcSpecialActionByObjectId(OBJECT_2, "resolve", 0)
sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/10", 2000, 0, -150, 0, OBJECT_2, True, 0)
sm.sendDelay(1500)


sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/11", 2000, 0, -100, -2, -2, False, 0)
sm.sendDelay(1500)


sm.showEffect("Skill/3112.img/skill/31121005/effect", 0, 426, 71, 1, 0, True, 1)
sm.showEffect("Skill/3112.img/skill/31121005/effect0", 0, 426, 71, -1, 0, True, 1)
sm.playSound("demonSlayer/31121005", 100)
sm.forcedAction(370, 0)
sm.sendDelay(1980)


sm.showEffect("Effect/Direction6.img/effect/tuto/gateOpen/0", 2100, 918, -195, 0, 0, False, 0)
sm.showEffect("Effect/Direction6.img/effect/tuto/gateLight/0", 2100, 926, -390, 0, 0, False, 0)
sm.showEffect("Effect/Direction6.img/effect/tuto/gateStair/0", 2100, 879, 30, 1, 0, False, 0)
sm.playSound("demonSlayer/openGate", 100)
sm.sendDelay(1950)


sm.startQuest(23203)
sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg0/0", 2000, 0, -150, 0, OBJECT_2, True, 0)
sm.sendDelay(1200)


sm.setSpeakerID(2159308)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Ah! It seems the Black Mage wishes to see you after all. It's a shame we cannot finish our little contest, but as always, I defer to my master. I believe I'll pay those so-called 'Heroes' a visit...")


sm.setSpeakerID(2159308)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("As for you, #h0# I don't expect I'll see you again. Enjoy the oblivion granted to you from the Black Mage himself! Ha ha ha!")


sm.showNpcSpecialActionByObjectId(OBJECT_2, "teleportation", 0)
sm.sendDelay(570)


sm.sendNpcController(OBJECT_2, False)
sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg2/2", 2000, 0, -100, -2, -2, False, 0)
sm.forcedInput(2)
