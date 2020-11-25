# Created by MechAviv
# Map ID :: 931050900
# Peacetime Edelstein : Edelstein Outskirts 1

sm.hideUser(True)
sm.forcedInput(0)
sm.forcedInput(2)
sm.sendDelay(30)


sm.forcedInput(0)
OBJECT_1 = sm.sendNpcController(2159369, 665, -20)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
OBJECT_2 = sm.sendNpcController(2159374, 380, -20)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
OBJECT_3 = sm.sendNpcController(2159372, 450, -20)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
OBJECT_4 = sm.sendNpcController(2159373, 520, -20)
sm.showNpcSpecialActionByObjectId(OBJECT_4, "summon", 0)
OBJECT_5 = sm.sendNpcController(2159375, 590, -20)
sm.showNpcSpecialActionByObjectId(OBJECT_5, "summon", 0)
sm.showFieldEffect("xenon/text0", 0)
sm.sendDelay(1900)


sm.setSpeakerID(2159373)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Okay, here we go.")


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg2/0", 900, 0, -120, 0, OBJECT_4, False, 0)
sm.sendDelay(900)


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg2/1", 900, 0, -120, 0, OBJECT_5, False, 0)
sm.sendDelay(1000)


sm.setSpeakerID(2159375)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Red M-Forcer!")


sm.setSpeakerID(2159373)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Yellow M-Forcer!")


sm.setSpeakerID(2159372)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Blue M-Forcer!")


sm.setSpeakerID(2159374)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Green M-Forcer!")


sm.setSpeakerID(2159369)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Black M-Forcer!")


sm.setSpeakerID(2159373)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("All together..")


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg2/2", 900, 0, -120, 0, OBJECT_4, False, 0)
sm.sendDelay(900)


sm.showNpcSpecialActionByObjectId(OBJECT_4, "happy", 0)
sm.sendDelay(900)


sm.setSpeakerID(2159373)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Oh! Awesome!")


sm.setSpeakerID(2159372)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Belle likes to pretend she's an M-Forcer.")


sm.setSpeakerID(2159373)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("It's so fun! They are righteous heroes who protect places like Edelstein from evil! Like me!")


sm.setSpeakerID(2159374)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Too bad there's never anybody for ME to beat up.")


sm.setSpeakerID(2159375)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("That's why we usually just yell at each other and dance around. It's super fun.")


sm.setSpeakerID(2159369)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I can be the bad guy...")


sm.setSpeakerID(2159373)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("No way, #h0#! We all have to be super righteous heroes! It's no fun if you're the bad guy.")


sm.setSpeakerID(2159369)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Yes.....")


sm.setSpeakerID(2159374)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Well, I guess as long as it's fun, it wouldn't matter. Maybe we can play more later.")


sm.setSpeakerID(2159369)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I have to head home! Talk to you later!")


sm.setSpeakerID(2159373)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("See you tomorrow!")


sm.moveNpcByObjectId(OBJECT_1, True, 550, 100)
sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg2/3", 1200, 0, -120, 0, OBJECT_5, False, 0)
sm.sendDelay(600)


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg2/4", 1200, 0, -120, 0, OBJECT_2, False, 0)
sm.sendDelay(600)


sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg2/5", 1200, 0, -120, 0, OBJECT_1, False, 0)
sm.sendDelay(3000)


sm.sendNpcController(OBJECT_1, False)
sm.sendNpcController(OBJECT_2, False)
sm.sendNpcController(OBJECT_3, False)
sm.sendNpcController(OBJECT_4, False)
sm.sendNpcController(OBJECT_5, False)
sm.warp(931050910, 0)
