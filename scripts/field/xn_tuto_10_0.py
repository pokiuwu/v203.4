# Created by MechAviv
# Map ID :: 931050980
# Classified Lab : Silo

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, False, False, False)
sm.forcedInput(0)
sm.sendDelay(30)


sm.forcedInput(2)
sm.sendDelay(30)


sm.forcedInput(0)
sm.sendDelay(30)


sm.forcedInput(2)
sm.showEffect("Effect/Direction12.img/effect/tuto/sig", 0, 0, 0, -2, -2, False, 0)
sm.showEffect("Effect/Direction12.img/effect/tuto/luti", 0, 0, 0, -2, -2, False, 0)
sm.sendDelay(2100)


sm.moveCamera(False, 1000, -681, 43)


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg1/0", 900, 0, -120, -2, -2, False, 0)
sm.sendDelay(900)


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg2/15", 1200, 0, -120, -2, -2, False, 0)
sm.forcedInput(0)
sm.sendDelay(900)


sm.showEffect("Effect/Direction12.img/effect/tuto/laser", 0, 0, 0, -2, -2, False, 0)
sm.sendDelay(1800)


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg2/16", 1200, -110, -120, -2, -2, False, 0)
sm.sendDelay(1200)


sm.warp(931050990, 0)
