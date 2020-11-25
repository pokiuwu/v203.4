# Created by MechAviv
# Map ID :: 931060060
# Classified Lab : Silo

sm.forcedInput(0)
sm.sendDelay(30)


sm.forcedInput(2)
sm.sendDelay(30)


sm.forcedInput(0)
OBJECT_2 = sm.sendNpcController(2159381, -1040, 20)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
OBJECT_3 = sm.sendNpcController(2159384, -990, 20)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
OBJECT_4 = sm.sendNpcController(2159379, -780, 20)
sm.showNpcSpecialActionByObjectId(OBJECT_4, "summon", 0)
OBJECT_5 = sm.sendNpcController(2159385, -470, 20)
sm.showNpcSpecialActionByObjectId(OBJECT_5, "summon", 0)
OBJECT_6 = sm.sendNpcController(2159386, -550, 20)
sm.showNpcSpecialActionByObjectId(OBJECT_6, "summon", 0)
OBJECT_7 = sm.sendNpcController(2159387, -370, 20)
sm.showNpcSpecialActionByObjectId(OBJECT_7, "summon", 0)
OBJECT_8 = sm.sendNpcController(2159388, -620, 20)
sm.showNpcSpecialActionByObjectId(OBJECT_8, "summon", 0)
sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg2/17", 2400, -500, -130, 0, -2, True, 0)
sm.sendDelay(2400)


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg2/18", 1500, 0, -145, 0, OBJECT_5, False, 0)
sm.sendDelay(1380)


sm.setSpeakerID(2159384)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("The cavalry is here!")


sm.setSpeakerID(2159387)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Save the hugs for later, Claudine. We've gotta get you out of here first!")


sm.showNpcSpecialActionByObjectId(OBJECT_7, "shoot", 0)
sm.sendDelay(720)


sm.showEffect("Effect/Direction12.img/effect/tuto/smogStart", 0, -370, 20, 0, -2, True, 0)
sm.sendDelay(1050)


sm.showEffect("Effect/Direction12.img/effect/tuto/smog", 3900, -370, 20, 0, -2, True, 0)
sm.sendDelay(1500)


sm.sendNpcController(OBJECT_2, False)
sm.sendNpcController(OBJECT_3, False)
sm.sendNpcController(OBJECT_5, False)
sm.sendNpcController(OBJECT_6, False)
sm.sendNpcController(OBJECT_7, False)
sm.sendNpcController(OBJECT_8, False)
sm.hideUser(True)
sm.sendDelay(2220)


sm.showEffect("Effect/Direction12.img/effect/tuto/smogEnd", 0, -370, 20, 0, -2, True, 0)
sm.sendDelay(420)


sm.sendDelay(600)


sm.sendDelay(600)


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg0/1", 1200, 0, -120, 0, OBJECT_4, False, 0)
sm.sendDelay(1200)


sm.sendNpcController(OBJECT_4, False)
sm.warp(931060070, 0)
