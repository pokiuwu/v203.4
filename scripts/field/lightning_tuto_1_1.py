# Created by MechAviv
# ID :: [927020000]
# Hidden Street : Destroyed Temple of Time Entrance

sm.sendDelay(120)


sm.setSpeakerID(2159353)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("A little over-dramatic, don't you think?")

OBJECT_1 = sm.sendNpcController(2159353, 1210, 10)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.forcedInput(2)
sm.sendDelay(30)


sm.forcedInput(0)
sm.setSpeakerID(2159353)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("You're late. Typical. One would think the greatest thief in the world could steal a watch, at least.")


sm.setSpeakerID(2159353)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("There's such a thing as showing up fashionably late, you know. Besides, you're the big hero. I'm just along for the ride.")


sm.setSpeakerID(2159353)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Call me what you will. We must all stand together, or perish.")


sm.setSpeakerID(2159353)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I knew I wasn't going to like you from the start. You're too stuffy.")


sm.setSpeakerID(2159353)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Sure. Right back at you.")


sm.setSpeakerID(2159353)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I'm glad we're on the same page. And yet we were sent here together to wait for the end... Maybe Freud has a better sense of humor than I thought.")


sm.setSpeakerID(2159353)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I still don't understand why I must stand idly by here with YOU. Perhaps he thought the situation would be enough to make us set aside our differences.")


sm.setSpeakerID(2159353)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("That's the kind of good-hearted nonsense that gets people killed...")


sm.setSpeakerID(2159353)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Enough chatter. I sense a dark presence.")


sm.setSpeakerID(2159353)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Don't waste your time moping around up here, staring into the distance. It's not as romantic as it seems...")


sm.sendDelay(300)


sm.showNpcSpecialActionByObjectId(OBJECT_1, "teleportation", 0)
sm.sendDelay(840)

sm.sendNpcController(OBJECT_1, False)
sm.sendDelay(1000)


sm.setSpeakerID(2159353)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Just one more step...")


sm.forcedInput(1)
sm.curNodeEventEnd(True)