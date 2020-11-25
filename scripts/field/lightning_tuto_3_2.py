# Created by MechAviv
# ID :: [927020050]
# Hidden Street : Temple of Time Corridor

OBJECT_1 = sm.getIntroNpcObjectID(2159356)

sm.showEffect("Effect/Direction8.img/effect/tuto/BalloonMsg0/7", 4000, 0, -100, -2, -2, False, 0)
sm.sendDelay(1500)


sm.showEffect("Effect/Direction8.img/effect/tuto/BalloonMsg0/6", 2500, 0, -100, 0, OBJECT_1, False, 0)
sm.sendDelay(1500)


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
sm.sendNpcController(OBJECT_1, False)
# [FORCED_STAT_RESET] []
sm.warp(927020060, 0)