# Created by MechAviv
# ID :: [927000020]
# Hidden Street : Black Mage's Main Corridor 1

MOB = 9300455

OBJECT_1 = sm.getIntroNpcObjectID(2159309)
sm.forcedAction(371, 0)
sm.playSound("demonSlayer/31121001", 100)
sm.showEffect("Skill/3112.img/skill/31121001/effect", 0, 317, 71, 0, 0, False, 0)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "teleportation", 0)
sm.sendDelay(570)


sm.sendNpcController(OBJECT_1, False)
sm.sendDelay(870)


OBJECT_1 = sm.sendNpcController(2159309, 500, 50)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.sendSessionValue("arkyrim2", 2159309)
sm.setSpeakerID(2159308)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("You disappoint me! You don't even understand the Black Mage's true goal. Guards! Eliminate the betrayer!")


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.chatScript("Eliminate all guards.")
sm.playSound("demonSlayer/summonGuard", 100)

sm.spawnMob(MOB, 450, 71, False)
sm.spawnMob(MOB, 400, 71, False)
sm.spawnMob(MOB, 350, 71, False)
sm.startQuestNoCheck(23205)
sm.reservedEffect("Effect/Direction6.img/DemonTutorial/Scene4")
