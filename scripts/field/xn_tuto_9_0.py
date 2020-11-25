# Created by MechAviv
# Map ID :: 931050970
# Classified Lab : Corridor

sm.forcedInput(0)
sm.sendDelay(30)


sm.forcedInput(2)
sm.sendDelay(30)


sm.forcedInput(0)
OBJECT_1 = sm.sendNpcController(2159381, -1700, 20)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
OBJECT_2 = sm.sendNpcController(2159384, -1600, 20)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("This corridor leads to the Silo, and outside. We're going to run into a lot of Guard Robots on the way.")


sm.setSpeakerID(2159381)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I will handle them. Don't worry.")


sm.setSpeakerID(2159384)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I'm afraid I'm not going to be much use in a fight with this injured arm... Are you sure about this?")


sm.setSpeakerID(2159384)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Let's give it a try.")


sm.moveNpcByObjectId(OBJECT_1, False, 2200, 100)
sm.moveNpcByObjectId(OBJECT_2, False, 2200, 100)
sm.sendDelay(2100)

sm.spawnMob(9300682, -1000, 32, False)
sm.spawnMob(9300682, -700, 32, False)
sm.spawnMob(9300682, -400, 32, False)
sm.spawnMob(9300682, -100, 32, False)
sm.spawnMob(9300682, 200, 32, False)
sm.spawnMob(9300682, 500, 32, False)
sm.chatScript("Press the CTRL key to attack. Defeat any monsters that block your path.")
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)