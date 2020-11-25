# Created by MechAviv
# ID :: [910141010]
# Hidden Street : Lania's Home
OBJECT_1 = sm.getIntroNpcObjectID(1032201)
OBJECT_2 = sm.getIntroNpcObjectID(1032202)

sm.forcedInput(0)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00 
sm.sendNpcController(OBJECT_1, False)
sm.sendNpcController(OBJECT_2, False)
sm.warp(910141020, 0)
