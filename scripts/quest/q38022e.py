MOONBEAM = 3002103

sm.lockInGameUI(True, False)
sm.forcedInput(0)

sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.sendNext("Are you okay? Are you hurt?")

sm.setSpeakerID(MOONBEAM)
sm.sendSay("Ooh, my whole body aches! Here, and here, and here!")

sm.setPlayerAsSpeaker()
sm.sendSay("You... don't have a scratch on you.")

sm.setSpeakerID(MOONBEAM)
sm.sendSay("I... I must be bleeding internally! Oh, the foxmanity! I need to get back to town for treatment! Thanks for nothing, you big jerk!")

sm.completeQuest(38020)
sm.completeQuest(parentID)
sm.giveExp(5000)

sm.setPlayerAsSpeaker()
sm.sendNext("...Did I say something wrong again? I wish she'd tell me what I did wrong. I'm glad she's okay, at least.")

sm.lockInGameUI(False)
sm.warp(410000000, 2)
