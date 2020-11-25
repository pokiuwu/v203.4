# Created by MechAviv
# ID :: [910150000]
# Black Road : After the Battle
sm.giveSkill(20021166)
sm.giveSkill(20021181)
sm.giveSkill(20020111)

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.playURLVideoByScript("http://nxcache.nexon.net/maplestory/video/yt/Mercedes.html")

sm.setStandAloneMode(True)
sm.forcedInput(4)
sm.sendDelay(2000)


sm.showEffect("Effect/Direction5.img/effect/mercedesInIce/merBalloon/0", 2000, 0, -100, 0, -2, False, 0)
sm.sendDelay(3000)


sm.forcedInput(2)