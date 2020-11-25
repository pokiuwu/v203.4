# Created by MechAviv
# ID :: [927000081]
# Hidden Street : Scene Change 2

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(1)
sm.sendDelay(30)

sm.forcedInput(0)
sm.showFieldEffect("demonSlayer/text13", 0)
sm.sendDelay(500)

sm.showFieldEffect("demonSlayer/text14", 0)
sm.sendDelay(4000)

sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
# [FORCED_STAT_RESET] []
sm.warp(927000020, 0)

