# Created by MechAviv
# ID :: [924020010]
# Hidden Street : Scene Change 0
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.setStandAloneMode(True)

sm.forcedInput(1)
sm.sendDelay(30)


sm.forcedInput(0)
sm.sendDelay(90)


sm.showFieldEffect("demonSlayer/text11", 0)
sm.sendDelay(4000)

sm.reservedEffect("Effect/Direction6.img/DemonTutorial/Scene2")
sm.sendDelay(1)