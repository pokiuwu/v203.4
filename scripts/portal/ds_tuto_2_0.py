# Created by MechAviv
# ID :: [927000020]
# Hidden Street : Black Mage's Main Corridor 1

OBJECT_1 = sm.getIntroNpcObjectID(2159309)

sm.completeQuest(23204)
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(2)
sm.sendDelay(10)


sm.forcedInput(0)
sm.setSpeakerID(2159308)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Oh, look, it's #h0#? How was your trip? I hope it was worth disobeying your orders. And how was your family? Are they looking well? Heh heh heh...")


sm.setSpeakerID(2159308)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("...I don't have time for you, #r#p2159309##k. Move, or I will MAKE you move.")


sm.setSpeakerID(2159308)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Tsk, tsk... Leaving without approval, disobeying orders... And that rebellious look... No, I don't think I'll allow you to see the Black Mage.")


sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/14", 2000, 0, -100, -2, -2, False, 0)
sm.forcedAction(332, 0)
sm.playSound("demonSlayer/31111003", 100)
sm.showEffect("Skill/3111.img/skill/31111003/effect", 0, 0, 0, -2, -2, False, 0)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "teleportation", 0)
sm.sendDelay(570)


sm.sendNpcController(OBJECT_1, False)
sm.sendDelay(1200)


OBJECT_1 = sm.sendNpcController(2159309, 180, 50)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.sendSessionValue("arkyrim1", 2159309)
sm.sendDelay(360)


sm.setSpeakerID(2159308)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Really? This is treason, you know! Are you really so weak that losing your family drives you to this? Pathetic!")


sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/15", 2000, 0, -100, -2, -2, False, 0)
sm.forcedInput(1)
