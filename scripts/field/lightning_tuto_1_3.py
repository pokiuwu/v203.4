# Created by MechAviv
# ID :: [927020000]
# Hidden Street : Destroyed Temple of Time Entrance

OBJECT_1 = sm.getIntroNpcObjectID(2159354)

sm.setSpeakerID(2159354)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("The light you possess is like a warm ray of sunshine to the spirits. It will be painful to see it extinguished...")


sm.setSpeakerID(2159354)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("If you feel so strongly about my preservation, turn away from this insanity. Turn away from the Black Mage!")


sm.setSpeakerID(2159354)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("If doing what I believe is labeled as 'insanity,' then I will gladly bear the stigma. Though you and your kind will bear it with me...")


sm.setSpeakerID(2159354)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Enough with the sophistry, #p2159354#.")


sm.setSpeakerID(2159354)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I thought I would enjoy some pre-dinner conversation, but I will be happy to end you now.")


sm.sendDelay(500)


sm.showNpcSpecialActionByObjectId(OBJECT_1, "special", 0)
sm.playSound("LuminousTuto/Special1", 100)
sm.sendDelay(1600)


sm.avatarOriented("Effect/OnUserEff.img/normalEffect/demonSlayer/chatBalloon0")
sm.playSound("LuminousTuto/Special2", 100)
sm.sendDelay(2280)

OBJECT_2 = sm.sendNpcController(2159355, 0, 10)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
sm.sendDelay(700)

sm.sendNpcController(OBJECT_1, False)
sm.setSpeakerID(2159355)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendNext("You served that fool up on a platter for me!")


sm.setSpeakerID(2159355)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("#p2159355#! Y-you destroyed him!")


sm.forcedAction(444, 540)
sm.showEffect("Skill/2711.img/skill/27111100/prepare", 540, -40, -25, -2, -2, False, 0)
sm.playSound("LuminousTuto/Use", 100)
sm.sendDelay(90)


sm.showNpcSpecialActionByObjectId(OBJECT_2, "barrier", 0)
sm.sendDelay(450)


sm.forcedAction(445, 3000)
sm.showEffect("Skill/2711.img/skill/27111100/keydown", 3000, -40, -25, -2, -2, False, 0)
sm.playSound("LuminousTuto/Loop", 100)
sm.sendDelay(30)


sm.showEffect("Effect/OnUserEff.img/normalEffect/lightning/guard", 0, 0, 0, 0, OBJECT_2, False, 0)
sm.playSound("LuminousTuto/Hit", 100)
sm.sendDelay(270)


sm.showEffect("Effect/OnUserEff.img/normalEffect/lightning/guard", 0, 0, 0, 0, OBJECT_2, False, 0)
sm.playSound("LuminousTuto/Hit", 100)
sm.sendDelay(270)


sm.showEffect("Effect/OnUserEff.img/normalEffect/lightning/guard", 0, 0, 0, 0, OBJECT_2, False, 0)
sm.playSound("LuminousTuto/Hit", 100)
sm.sendDelay(270)


sm.showEffect("Effect/OnUserEff.img/normalEffect/lightning/guard", 0, 0, 0, 0, OBJECT_2, False, 0)
sm.playSound("LuminousTuto/Loop", 100)
sm.playSound("LuminousTuto/Hit", 100)
sm.sendDelay(270)


sm.showEffect("Effect/OnUserEff.img/normalEffect/lightning/guard", 0, 0, 0, 0, OBJECT_2, False, 0)
sm.playSound("LuminousTuto/Hit", 100)
sm.sendDelay(270)


sm.showEffect("Effect/OnUserEff.img/normalEffect/lightning/guard", 0, 0, 0, 0, OBJECT_2, False, 0)
sm.playSound("LuminousTuto/Hit", 100)
sm.sendDelay(270)


sm.showEffect("Effect/OnUserEff.img/normalEffect/lightning/guard", 0, 0, 0, 0, OBJECT_2, False, 0)
sm.playSound("LuminousTuto/Hit", 100)
sm.sendDelay(270)


sm.showEffect("Effect/OnUserEff.img/normalEffect/lightning/guard", 0, 0, 0, 0, OBJECT_2, False, 0)
sm.playSound("LuminousTuto/Loop", 100)
sm.playSound("LuminousTuto/Hit", 100)
sm.sendDelay(270)


sm.showEffect("Effect/OnUserEff.img/normalEffect/lightning/guard", 0, 0, 0, 0, OBJECT_2, False, 0)
sm.playSound("LuminousTuto/Hit", 100)
sm.sendDelay(270)


sm.showEffect("Effect/OnUserEff.img/normalEffect/lightning/guard", 0, 0, 0, 0, OBJECT_2, False, 0)
sm.playSound("LuminousTuto/Hit", 100)
sm.sendDelay(540)


sm.showEffect("Skill/2711.img/skill/27111101/keyedownend", 0, -40, -25, -2, -2, False, 0)
sm.playSound("LuminousTuto/End", 100)
sm.sendDelay(600)


sm.setSpeakerID(2159355)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendNext("How cute. Well, I've got to go kill your friends! See you!")


sm.setSpeakerID(2159355)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("What are you talking about?!")


sm.setSpeakerID(2159355)
sm.removeEscapeButton()
sm.flipSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I don't have time to play with you! Ha. I'm done with this world, anyway.")


sm.showNpcSpecialActionByObjectId(OBJECT_2, "teleportation", 0)
sm.sendDelay(450)


sm.sendNpcController(OBJECT_2, False)
sm.setSpeakerID(2159355)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("I've no time left to worry about Magnus. The Black Mage awaits!")


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
# [FORCED_STAT_RESET] []
sm.warp(927020010, 0)