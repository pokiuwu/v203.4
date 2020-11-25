# Created by MechAviv
# ID :: [931050030]
# Hidden Street : Treatment Room

sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/3", 2000, 0, -100, -2, -2, False, 0)
sm.sendDelay(1000)


sm.setSpeakerID(2159344)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("(I hear something...)")


sm.setSpeakerID(2159344)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("...I was going to come back after I discovered the Energy Conducting Device. It was like the one at the Power Plant, but this one was connected to an egg. While I was examining it, that person broke out of the egg, and defeated all the Black Wings. It was crazy.")


sm.setSpeakerID(2159315)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("You know, J... If it were anyone else telling me this, I would laugh in their face. But this... What were the Black Wings doing? And who is this person?")


sm.setSpeakerID(2159315)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("(Are they talking about me?)")


sm.setSpeakerID(2159344)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("And those skills... I've never seen skills like that. So powerful... I think our guest is out of juice, but we should be careful.")


sm.setSpeakerID(2159312)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Maybe this is one of their experiments? Think about Vita... And nobody really knows what the Black Wings are doing deep in the mines, right?")


sm.setSpeakerID(2159314)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("That blasted madman Gelimer... We have to stop him!")


sm.setSpeakerID(2159344)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("...Hold on. I'll see if our new friend is awake yet.")


sm.forcedInput(2)
sm.sendDelay(2000)


sm.forcedInput(1)
OBJECT_1 = sm.sendNpcController(2159344, -600, -20)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.sendDelay(30)


sm.forcedInput(0)
sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/3", 1500, 0, -100, 0, OBJECT_1, True, 0)
sm.sendDelay(1000)


sm.setSpeakerID(2159344)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Ah, you're awake. How do you feel? Still tired?")


sm.setSpeakerID(2159344)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Did...you save me?")


sm.setSpeakerID(2159344)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Yeah. You were badly wounded... I couldn't just leave you with the Black Wings. Considering the circumstances, I think we're on the same side. We have plenty to talk about, so how about you take a walk with me?")


sm.setSpeakerID(2159344)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("(Interrogation...? Not sure yet... They're friendlier than those Black Wings, anyway.) Very well.")


sm.curNodeEventEnd(True)
# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00 
sm.sendNpcController(OBJECT_1, False)
sm.warpInstanceIn(931050010, 0)
