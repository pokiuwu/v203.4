# Created by MechAviv
# Map ID :: 931050950
# Classified Lab : Laboratory

sm.forcedInput(0)
sm.sendDelay(30)


sm.forcedInput(2)
sm.sendDelay(30)


sm.forcedInput(0)
sm.sendDelay(1200)


sm.forcedInput(1)
sm.sendDelay(210)


sm.forcedInput(2)
sm.sendDelay(420)


sm.forcedInput(1)
sm.sendDelay(420)


sm.forcedInput(2)
sm.sendDelay(420)


sm.forcedInput(0)
sm.setSpeakerID(2159377)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("What was that memory? I cannot recall it... like it's covered in static...")


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg0/1", 1200, 0, -120, -2, -2, False, 0)
sm.sendDelay(1200)


sm.setSpeakerID(2159377)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("I need to talk to that woman before Professor Gelimer returns. She must be in a cell.")


sm.forcedInput(2)
