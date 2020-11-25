# Created by MechAviv
# ID :: [927020060]
# Hidden Street : Black Mage's Antechamber

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Looks like Freud and Mercedes are already inside. I hope I'm not too late.")


sm.sendDelay(750)


sm.forcedInput(2)