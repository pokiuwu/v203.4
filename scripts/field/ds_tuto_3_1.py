# Created by MechAviv
# ID :: [931050020]
# Hidden Street : Extraction Room 2

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.setStandAloneMode(True)

sm.startQuest(23207)
sm.completeQuest(23207)
OBJECT_2 = sm.sendNpcController(2159340, 175, 0)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
OBJECT_3 = sm.sendNpcController(2159341, 300, 0)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
OBJECT_4 = sm.sendNpcController(2159342, 600, 0)
sm.showNpcSpecialActionByObjectId(OBJECT_4, "summon", 0)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "panic", 0)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "panic", 0)
sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/3", 1500, 0, -100, 0, OBJECT_4, True, 0)
sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/3", 1500, 0, -100, 0, OBJECT_2, True, 0)
sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/3", 1500, 0, -100, 0, OBJECT_3, True, 0)
sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg2/0", 1500, 0, -100, -2, -2, False, 0)
sm.sendDelay(1500)


sm.forcedInput(0)
sm.setSpeakerID(2159340)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("W-what is that?")


sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg2/1", 2000, 0, -100, -2, -2, False, 0)
sm.sendDelay(900)


sm.setSpeakerID(2159340)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("(What's going on? My Fury is...nearly gone! And what is this thing? Did it take my power...?)")


sm.setSpeakerID(2159341)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("T-this can't be happening...!")


sm.setSpeakerID(2159340)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("(What's going on? My Fury is...nearly gone! And what is this thing? Did it take my power...?)")


sm.setSpeakerID(2159341)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("T-this can't be happening...!")


sm.setSpeakerID(2159341)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("What did you do to me? This energy...is it the Black Mage's energy?")


sm.setSpeakerID(2159340)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Need to capture that person to avoid interrogation...")


sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/16", 2000, 0, -100, -2, -2, False, 0)
sm.sendDelay(1500)


sm.fadeInOut(600, 1500, 600, 150)
sm.forcedAction(372, 0)
sm.showEffect("Skill/3112.img/skill/31121006/effect", 0, 0, 0, -2, -2, False, 0)
sm.playSound("demonSlayer/31121006", 100)
sm.sendDelay(900)
sm.reservedEffect("Effect/Direction6.img/DemonTutorial/Scene3")


sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/17", 2000, 0, -100, -2, -2, False, 0)
sm.sendDelay(900)


sm.playSound("demonSlayer/31121006h", 100)
sm.startQuest(23207)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "die", 0)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "die", 0)
sm.sendDelay(990)


sm.sendNpcController(OBJECT_2, False)
sm.sendNpcController(OBJECT_3, False)
sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg0/13", 2000, 0, -100, -2, -2, False, 0)
sm.setSpeakerID(2159342)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("(Who is that? I've never seen such a powerful skill...)")


sm.sendDelay(1500)


sm.setSpeakerID(2159342)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("(Ugh... I wasted too much power fighting them. Where am I? If nothing else, I know I need to get out of here.)")


sm.forcedInput(2)
sm.sendDelay(990)


sm.forcedInput(0)
sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/12", 2000, 0, -100, -2, -2, False, 0)
sm.sendDelay(990)


sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/4", 2000, 0, -100, 0, OBJECT_4, True, 0)
sm.sendDelay(1200)


sm.moveNpcByObjectId(OBJECT_4, True, 150, 100)
sm.setSpeakerID(2159342)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("(No... I'm...losing consciousness. If they find me now...!)")


sm.setSpeakerID(2159342)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Wait, calm down. I'm not your enemy. Who are you? And how did you end up in a place like this?")


sm.setSpeakerID(2159342)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("(He doesn't feel evil...)\r\nStay back!")


sm.setSpeakerID(2159342)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("C'mon... Look at you. You need help, and you need it now. Do you realized what they were doing? That machine next to you is an Energy Conducting Device... The Black Wings were draining your power.")


sm.setSpeakerID(2159342)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("(An Energy Conducting Device? This machine? And who are the Black Wings? None of this makes any sense...)")


sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg0/13", 2000, 0, -100, -2, -2, False, 0)
sm.sendDelay(1500)


sm.setSpeakerID(2159342)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Who are you? And...*cough* How do you know about these things?")


sm.setSpeakerID(2159342)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I'm J, an agent in the Resistance. We're working against the Black Wings. I don't know who you are, but I wouldn't take advantage of you in your state. Let me help you.")


sm.setSpeakerID(2159342)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("No... I have...no energy...")


sm.forcedAction(379, 0)
sm.showEffect("Effect/Direction6.img/effect/tuto/fallMale", 0, 0, 0, -2, -2, False, 0)
sm.sendDelay(600)


sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/13", 2000, 0, -100, 0, OBJECT_4, True, 0)
sm.sendDelay(1500)


sm.sendNpcController(OBJECT_4, False)
sm.warpInstanceIn(931050030, 0)
