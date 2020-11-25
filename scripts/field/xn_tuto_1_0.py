# Created by MechAviv
# Map ID :: 931050910
# Peacetime Edelstein : Edelstein Outskirts 2

sm.hideUser(True)
sm.forcedInput(0)
sm.forcedInput(2)
sm.sendDelay(30)


sm.forcedInput(0)
OBJECT_1 = sm.sendNpcController(2159369, -1050, -30)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
OBJECT_2 = sm.sendNpcController(2159376, -1800, -30)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
sm.moveNpcByObjectId(OBJECT_1, True, 300, 100)
sm.moveCamera(False, 80, -1600, -34)


sm.sendDelay(6870)


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg0/2", 900, 0, -120, 0, OBJECT_1, False, 0)
sm.sendDelay(810)


sm.setSpeakerID(2159369)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Who is that grandpa? I don't think he's from this town...")


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg2/6", 900, 0, -120, 0, OBJECT_2, False, 0)
sm.sendDelay(810)


OBJECT_3 = sm.sendNpcController(2159422, -1450, -30)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
OBJECT_4 = sm.sendNpcController(2159422, -1350, -30)
sm.showNpcSpecialActionByObjectId(OBJECT_4, "summon", 0)
sm.sendDelay(1200)


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg1/0", 1200, 0, -120, 0, OBJECT_1, False, 0)
sm.sendDelay(1200)


OBJECT_5 = sm.sendNpcController(2159371, -1400, -30)
sm.showNpcSpecialActionByObjectId(OBJECT_5, "summon", 0)
sm.sendNpcController(OBJECT_1, False)
sm.sendNpcController(OBJECT_3, False)
sm.sendNpcController(OBJECT_4, False)
sm.sendDelay(600)


sm.setSpeakerID(2159376)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Finally, I find what I'm looking for... It's a good thing I looked all over town.")


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg2/8", 1200, 0, -120, 0, OBJECT_2, False, 0)
sm.sendDelay(900)


sm.moveNpcByObjectId(OBJECT_2, True, 300, 100)
sm.moveNpcByObjectId(OBJECT_5, True, 300, 100)
sm.sendDelay(1500)


OBJECT_6 = sm.sendNpcController(2159372, -500, -30)
sm.showNpcSpecialActionByObjectId(OBJECT_6, "summon", 0)
sm.moveNpcByObjectId(OBJECT_6, True, 200, 100)
sm.moveCamera(False, 150, -950, -34)


sm.sendDelay(4335)


sm.moveNpcByObjectId(OBJECT_6, False, 1, 100)
sm.sendDelay(90)


sm.moveNpcByObjectId(OBJECT_6, True, 1, 100)
sm.sendDelay(90)


sm.moveNpcByObjectId(OBJECT_6, False, 1, 100)
sm.sendDelay(90)


sm.moveNpcByObjectId(OBJECT_6, True, 1, 100)
sm.sendDelay(90)


sm.setSpeakerID(2159372)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Did #h0# already go home? I was going to return the dagger I borrowed.")


sm.setSpeakerID(2159372)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I'll give it back tomorrow.")


sm.moveNpcByObjectId(OBJECT_6, False, 100, 100)
sm.sendDelay(150)


sm.sendNpcController(OBJECT_2, False)
sm.sendNpcController(OBJECT_5, False)
sm.sendNpcController(OBJECT_6, False)
sm.warp(931060080, 0)
