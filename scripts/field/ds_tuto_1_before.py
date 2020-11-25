# Created by MechAviv
# ID :: [927000080]
# Hidden Street : Scene Change 1

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.setStandAloneMode(True)

sm.forcedInput(1)
sm.sendDelay(30)

sm.forcedInput(0)
sm.showFieldEffect("demonSlayer/text8", 0)
sm.sendDelay(500)


sm.showFieldEffect("demonSlayer/text9", 0)
sm.sendDelay(3000)


# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
# [FORCED_STAT_RESET] []
sm.warp(927000010, 1)