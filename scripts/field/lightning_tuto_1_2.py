# Created by MechAviv
# ID :: [927020000]
# Hidden Street : Destroyed Temple of Time Entrance

OBJECT_1 = sm.sendNpcController(2159354, 128, 10)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.sendSessionValue("guare", 2159354)
sm.setSpeakerID(2159354)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Halt! This battlefield is for you and I.")


sm.moveCamera(False, 450, -200, 18)


sm.sendDelay(1938)


sm.forcedInput(1)
sm.curNodeEventEnd(True)
