# Created by MechAviv
# ID :: [910141020]
# Hidden Street : Lania's Front Yard

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
OBJECT_1 = sm.sendNpcController(1032201, 340, 0)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.sendSessionValue("lucia", 1032201)
sm.forcedInput(2)
sm.sendDelay(30)


sm.forcedInput(0)
sm.setSpeakerID(1032201)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("The weather's beautiful! We should have a picnic before winter comes! Would you like that?")


sm.setSpeakerID(1032201)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Let's buy some ingredients from the market. I feel like cooking!")


sm.setSpeakerID(1032201)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Yes! I'll make a cream cheese spread and we can make fancy sandwiches!")


sm.setSpeakerID(1032201)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("(Yes, a home cooked meal is what I need to calm this turmoil inside.)")


# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00 
sm.moveNpcByObjectId(OBJECT_1, True, 400, 100)
sm.sendDelay(1500)


sm.forcedInput(1)
