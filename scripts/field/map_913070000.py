# Limbert's General Store (913070000)
sm.lockInGameUI(True)
sm.chatScript("Mr. Limbert's General Store")
sm.sendDelay(500)

sm.chatScript("Month 3, Day 4")
sm.sendDelay(1000)

sm.showBalloonMsg("Effect/Direction7.img/effect/tuto/step0/0", 2000)
sm.localEmotion(6, 10000, False)
sm.sendDelay(2000)

sm.showBalloonMsg("Effect/Direction7.img/effect/tuto/step0/1", 2000)
sm.sendDelay(2000)

sm.showBalloonMsg("Effect/Direction7.img/effect/tuto/step0/2", 3000)
sm.localEmotion(4, 8000, False)
sm.sendDelay(3000)

sm.forcedInput(1)
# continue in portal_000.py