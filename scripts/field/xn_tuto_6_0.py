# Created by MechAviv
# Map ID :: 931050940
# Classified Lab : Silo


sm.hideUser(True)
OBJECT_1 = sm.sendNpcController(2159386, 350, 30)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
OBJECT_2 = sm.sendNpcController(2159382, 250, 30)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
OBJECT_3 = sm.sendNpcController(2159385, 480, 30)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
OBJECT_4 = sm.sendNpcController(2159427, 50, 30)
sm.showNpcSpecialActionByObjectId(OBJECT_4, "summon", 0)
OBJECT_5 = sm.sendNpcController(2159427, 0, 30)
sm.showNpcSpecialActionByObjectId(OBJECT_5, "summon", 0)
sm.moveCamera(False, 150, 200, 22)

sm.showNpcSpecialActionByObjectId(OBJECT_4, "die", 0)
sm.sendDelay(120)


sm.showNpcSpecialActionByObjectId(OBJECT_5, "die", 0)
sm.sendDelay(1500)


sm.sendDelay(120)


sm.setSpeakerID(2159386)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("They just keep coming!")


sm.setSpeakerID(2159382)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("We heard there was a hidden lab here, but this is something big!")


sm.setSpeakerID(2159385)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("With defenses like these, they must be hiding something big. And I'm going to find out what.")


sm.setSpeakerID(2159382)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("You're cool as can be, aren't you Belle? Nothing phases you.")


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg1/2", 900, 0, -120, 0, OBJECT_2, False, 0)
sm.sendDelay(900)


sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg2/12", 1200, 0, -120, 0, OBJECT_1, False, 0)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "catched", 0)
sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg1/1", 900, 0, -120, 0, OBJECT_1, False, 0)
sm.moveNpcByObjectId(OBJECT_1, False, 30, 100)
sm.sendDelay(2160)


sm.moveNpcByObjectId(OBJECT_1, True, 2, 100)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
sm.hideUser(False)
sm.forcedInput(2)
sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg2/13", 900, 0, -120, 0, OBJECT_3, False, 0)
sm.sendDelay(300)


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg2/13", 900, 0, -120, 0, OBJECT_3, False, 0)
sm.sendDelay(900)


sm.sendSessionValue("bell", OBJECT_3)
sm.sendSessionValue("hen", OBJECT_1)
sm.sendSessionValue("sig", OBJECT_2)
