# Field script for leaving Ellinel Fairy Academy
sm.lockUI()
sm.hideUser(True)
sm.moveCamera(False, 100, 600, 0)

sm.sendDelay(1000)

sm.chatScript("The forest of fairies gradually fades from view, as if waking from a dream.")
sm.sendDelay(2000)

sm.hideUser(False)
sm.unlockUI()
sm.warp(101030000, 5)