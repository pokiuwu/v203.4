# Created by MechAviv
# ID :: [927020070]
# Hidden Street : Black Mage's Temple

sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
OBJECT_3 = sm.sendNpcController(2159357, 300, -80)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
sm.sendSessionValue("freed", 2159357)
sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/6", 0, 0, -160, -2, -2, False, 0)
sm.sendDelay(1200)


sm.moveCamera(False, 300, 0, -500)


sm.sendDelay(2322)


sm.showFieldEffect("lightning/screenMsg/2", 0)
sm.playExclSoundWithDownBGM("Voice.img/DarkMage/0", 100)
sm.sendDelay(4000)


sm.moveCamera(False, 300, 300, -100)


sm.sendDelay(1667)


sm.showEffect("Effect/Direction5.img/effect/mercedesInIce/merBalloon/0", 0, 0, -90, 0, OBJECT_3, False, 0)
sm.sendDelay(2100)


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Freud! Mercedes!")


sm.sendDelay(300)


sm.forcedInput(2)
sm.moveCamera(True, 180, 0, 0)