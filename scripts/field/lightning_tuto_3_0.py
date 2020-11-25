# Created by MechAviv
# ID :: [927020050]
# Hidden Street : Temple of Time Corridor

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(0)
OBJECT_1 = sm.sendNpcController(2159356, 1500, 50)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
OBJECT_2 = sm.sendNpcController(2159360, 1350, 50)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
OBJECT_3 = sm.sendNpcController(2159361, 1300, 50)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
sm.sendSessionValue("aran", 2159356)
sm.showEffect("Effect/OnUserEff.img/normalEffect/demonSlayer/chatBalloon0", 1000, 0, 0, -2, -2, False, 0)
sm.sendDelay(1200)


sm.showNpcSpecialActionByObjectId(OBJECT_1, "attack", 0)
sm.playSound("LuminousTuto/Use2", 100)
sm.sendDelay(450)


sm.showEffect("Effect/Direction8.img/effect/tuto/BalloonMsg0/5", 0, 0, -120, -2, -2, False, 0)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "hit", 0)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "hit", 0)
sm.showEffect("Skill/2111.img/skill/21111021/hit/0", 0, -5, -50, 0, OBJECT_2, False, 0)
sm.showEffect("Skill/2111.img/skill/21111021/hit/0", 0, -5, -50, 0, OBJECT_3, False, 0)
sm.playSound("LuminousTuto/Hit2", 100)
sm.sendDelay(270)


sm.showEffect("Skill/2111.img/skill/21111021/hit/0", 0, -5, -50, 0, OBJECT_2, False, 0)
sm.showEffect("Skill/2111.img/skill/21111021/hit/0", 0, -5, -50, 0, OBJECT_3, False, 0)
sm.playSound("LuminousTuto/Hit2", 100)
sm.sendDelay(900)


sm.playSound("LuminousTuto/Use3", 100)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "die", 0)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "die", 0)
sm.showEffect("Skill/2111.img/skill/21111021/hit/0", 0, -5, -50, 0, OBJECT_2, False, 0)
sm.showEffect("Skill/2111.img/skill/21111021/hit/0", 0, -5, -50, 0, OBJECT_3, False, 0)
sm.playSound("LuminousTuto/Hit3", 100)
sm.sendDelay(2200)


sm.forcedInput(2)
sm.sendNpcController(OBJECT_2, False)
sm.sendNpcController(OBJECT_3, False)
# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
