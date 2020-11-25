# Created by MechAviv
# Map ID :: 931050930
# Classified Lab : Laboratory

OBJECT_6 = sm.sendNpcController(2159377, -180, 50)
sm.showNpcSpecialActionByObjectId(OBJECT_6, "summon", 0)
sm.sendSessionValue("geli", OBJECT_6)
sm.moveCamera(False, 100, -272, -63)


sm.sendDelay(2701)


sm.setSpeakerID(2159377)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("This is the last phase of testing. I know it's been a long time... are you ready?")


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg2/11", 1200, 0, -120, 0, OBJECT_6, False, 0)
sm.sendDelay(900)


sm.moveCamera(True, 100, 0, 0)


sm.sendDelay(2381)

sm.spawnMob(9300635, 150, -301, False)
sm.startQuest(23600)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)