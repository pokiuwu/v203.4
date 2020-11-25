sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.sendDelay(1000)


sm.setSpeakerID(9131005)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.boxChatPlayerAsSpeaker()
sm.sendNext("Please step aside, madam. I have no quarrel with you.")


sm.setSpeakerID(9131005)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxOverrideSpeaker()
sm.sendSayIllustration("How dare you speak to me in such a familiar tongue. I am Kichou, daughter of the Saitou Dousan, lawful wife of the Demon King!", 9131005, True)


sm.setSpeakerID(9131005)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.boxChatPlayerAsSpeaker()
sm.sendSay("The tales of your beauty are not exaggerations, Lady No, but you must stand aside. Your husband is my sworn enemy.")


sm.setSpeakerID(9131005)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxOverrideSpeaker()
sm.sendSayIllustration("Cocky little brat! You will beg me for death before I am done!", 9131005, True)


sm.showNpcSpecialActionByTemplateId(9131005, "step", 0)
sm.sendDelay(1080)


sm.showEffect("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/14", 0, -100, -100, -2, -2, False, 0)
sm.sendDelay(1000)


sm.showEffect("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/15", 0, -150, -150, -2, -2, False, 0)
sm.sendDelay(2000)


sm.showEffect("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/16", 0, -150, -150, -2, -2, False, 0)
sm.sendDelay(2000)


sm.showEffect("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/17", 0, 0, -120, -2, -2, False, 0)
sm.sendDelay(2000)


sm.forcedInput(1)
sm.sendDelay(1800)


sm.forcedInput(5)
sm.sendDelay(1000)


sm.forcedInput(0)
sm.sendDelay(500)


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.warp(807100005, 0)