# Created by MechAviv
# Map ID :: 931050940
# Classified Lab : Silo
OBJECT_1 = sm.sendNpcController(2159377, -700, 30)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
OBJECT_2 = sm.sendNpcController(2159378, -800, 30)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
sm.setSpeakerID(2159383)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Attack on my command.")


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg1/2", 900, 0, -120, 0, OBJECT_2, False, 0)
sm.sendDelay(810)


sm.setSpeakerID(2159385)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Get away from Claudine!")


sm.forcedAction(4, 0)
sm.sendDelay(2000)


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg1/1", 900, 0, -120, -2, -2, False, 0)
sm.changeBGM("Bgm30.img/thePhoto", 0, 0)
sm.sendDelay(810)


sm.setSpeakerID(2159385)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Argh, M... My head! It... hurts.")


sm.showEffect("Effect/Direction12.img/effect/tuto/memory/1", 3900, 0, -120, -2, -2, False, 0)
sm.sendDelay(3900)


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg0/1", 900, 0, -120, -2, -2, False, 0)
sm.sendDelay(810)


sm.setSpeakerID(2159385)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("What was that?! Like... someone else's memory! My chest... I can't catch my breath... ")


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg0/2", 900, 0, -120, 0, OBJECT_2, False, 0)
sm.sendDelay(810)


sm.moveNpcByObjectId(OBJECT_1, False, 650, 100)
sm.sendDelay(150)


sm.moveNpcByObjectId(OBJECT_2, False, 650, 100)
sm.moveCamera(False, 200, -450, 43)


sm.sendDelay(3251)


sm.moveCamera(True, 80, 0, 0)


sm.sendDelay(6705)


sm.setSpeakerID(2159377)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("What are you doing?! Capture them! Capture them all!")


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg1/2", 900, 0, -120, 0, OBJECT_1, False, 0)
sm.sendDelay(810)


sm.setSpeakerID(2159386)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Belle! Get out of here!")


sm.setSpeakerID(2159385)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("What about Claudine?")


sm.setSpeakerID(2159386)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("She'll be okay! We need to get back-up!")


sm.showEffect("Effect/Direction12.img/effect/tuto/smog", 3300, 550, 0, 0, -2, True, 0)
sm.sendDelay(1200)


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg2/14", 1200, 120, -260, 0, -2, False, 1)
sm.sendDelay(1200)


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg1/1", 1500, 0, -120, 0, OBJECT_1, False, 0)
sm.sendDelay(840)


sm.showEffect("Effect/Direction12.img/effect/tuto/smogEnd", 0, 550, 0, 0, -2, True, 0)
sm.sendDelay(1020)


sm.setSpeakerID(2159377)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Don't let them get away!")


sm.setSpeakerID(2159377)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Xenon! Watch this one! Beryl, you and I will chase down the rest of these rats!")


sm.moveNpcByObjectId(OBJECT_1, False, 600, 100)
sm.moveNpcByObjectId(OBJECT_2, False, 600, 100)
sm.sendDelay(1200)


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg0/0", 900, 0, -120, -2, -2, False, 0)
sm.setSpeakerID(2159377)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("What happened earlier?")


sm.curNodeEventEnd(True)
sm.warp(931050950, 0)
