# Created by MechAviv
# ID :: [910141010]
# Hidden Street : Lania's Home

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.showFieldEffect("lightning/screenMsg/1", 0)
sm.forcedInput(0)
OBJECT_1 = sm.sendNpcController(1032201, 230, -130)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
OBJECT_2 = sm.sendNpcController(1032202, 340, -400)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
sm.sendDelay(2000)


sm.setSpeakerID(1032201)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("You remember everything I said, right?")


sm.setSpeakerID(1032201)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Fresh milk, three servings of pork, fishing bait, and...")


sm.setSpeakerID(1032201)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Thread! I need thread. We're going to be freezing if I don't have time to knit us scarves and socks.")


# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00 
sm.setSpeakerID(1032201)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Of course, of course. Red and white thread, yes. I apologize. My headaches...")


sm.setSpeakerID(1032201)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("(My head aches more with each passing day, as if a fire were burning in my mind...)")


sm.setSpeakerID(1032202)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Mrow! Need me to scratch the shopping list into your arm?")


sm.setSpeakerID(1032201)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Don't be mean, Penny! And you! Don't forget my stuff this time. I don't want my feet to freeze off!")


sm.setSpeakerID(1032201)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I will return with all you need, Lania. I swear it.")


sm.setSpeakerID(1032201)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I'll walk you out. Penny, you guard the house, okay?")


sm.setSpeakerID(1032202)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Mrow! Lania this, Lania that! What about little old me?")


sm.setSpeakerID(1032202)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("When I return, I swear I will catch you the king of all fish, Penny.")


sm.setSpeakerID(1032202)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Really?! I mean... you can't sway me that easily. Just... it better be a really fat one.")


sm.setSpeakerID(1032201)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I'm sure it'll be the biggest fish in the lake, Penny. Let's go, Luminous!")


sm.setSpeakerID(1032201)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Y-yes. Let's. (My chest... It feels so tight...)")


sm.forcedInput(1)
sm.moveNpcByObjectId(OBJECT_1, True, 400, 100)
