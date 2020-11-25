# Created by MechAviv
# ID :: [865020001]
# Commerci Republic : Canal 1
sm.forcedInput(0)
sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(8)
sm.sendNext("You, there! Stop!")


sm.setSpeakerID(9390223)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setSpeakerType(8)
sm.sendSay("Hmm? Is something the matter?")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(8)
sm.sendSay("What do you think you're doing with those goods?")


sm.setSpeakerID(9390223)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setSpeakerType(8)
sm.sendSay("What's it look like? I'm taking them to trade.")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(8)
sm.sendSay("But yesterday, you promised I could go with you! How could you just leave without me?")


sm.setSpeakerID(9390223)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setSpeakerType(3)
sm.sendSay("I did? I mean, I did! But something came up, you know... I'll bring you along next time.")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("AHA! That, impostor, was a trap! Who are you, and where are you taking those trade goods?")


# [NPC_VIEW_OR_HIDE] [DC B0 01 00 00 00 ]
OBJECT_1 = sm.sendNpcEnterField(9390204, 465, 343)
sm.sendNpcController(OBJECT_1, True)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
# [NPC_VIEW_OR_HIDE] [E0 B3 53 00 01 01 ]
sm.setSpeakerID(9390204)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setSpeakerType(8)
sm.sendSay("Silly me. I can't believe I fell for that. Oh well. Good thing I spent the last of my life savings to hire these brawny goons. Come out, come out, my little minions!")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(8)
sm.sendSay("Whoa, whoa, whoa. I'm not looking for a fight. I just want those goods.")


sm.setSpeakerID(9390204)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setSpeakerType(8)
sm.sendSay("Minions... ATTACK!")


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.setStandAloneMode(False)
