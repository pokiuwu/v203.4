# Created by MechAviv
# Quest ID :: 17620
# [Commerci Republic] Eye for an Eye

sm.setSpeakerID(9390217)
sm.sendNext("Now, what dream can I make come true for you? Remember, anything in the entire world is yours for the asking.")


sm.setSpeakerID(9390217)
sm.setPlayerAsSpeaker()
sm.sendSay("Can you introduce me to Gilberto Daniella?")


sm.setSpeakerID(9390217)
sm.sendSay("I offer to make your wildest dreams come true, and that is what you want?")


# Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 C6 0A 00 00 FF 00 00 00 00
sm.setSpeakerID(9390217)
sm.setPlayerAsSpeaker()
sm.sendSay("Yup, I really want to meet #bGILBERTO DANIELLA#k.")


sm.setSpeakerID(9390217)
sm.sendSay("I heard you the first time. It's just...")


sm.setSpeakerID(9390217)
sm.setPlayerAsSpeaker()
sm.sendSay("What?")


sm.setSpeakerID(9390217)
sm.sendSay("Well, I thought you'd ask for something difficult, like borrowing my hat.")


sm.setSpeakerID(9390217)
sm.setPlayerAsSpeaker()
sm.sendSay("That was next on my list.")


sm.setSpeakerID(9390217)
if sm.sendAskYesNo("To get to the Daniella Merchant Union office, head east from this spot, past the town fountain. It's the white building with golden ornamentation."):
    sm.setSpeakerID(9390217)
    sm.sendNext("I'll let them know you're on your way. Be polite when you talk to Gilberto. He is quite powerful in Commerci.")

    sm.startQuest(17620)
else:
    sm.setSpeakerID(9390217)
    sm.sendSayOkay("Still sorting through your deepest desires? Let me know when you know what you want.")
