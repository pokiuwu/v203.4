# Created by MechAviv
# Map ID :: 931050960
# Classified Lab : Prison
OBJECT_2 = sm.getIntroNpcObjectID(2159380)

sm.setSpeakerID(2159380)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("#h0#, #h0#! What brings you here?")


sm.setSpeakerID(2159380)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Roo-D, I must ask that prisoner a question. I need you to keep this a secret, okay?")


sm.setSpeakerID(2159380)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("You WERE acting funny when you saw her earlier. Do you... remember anything? Maybe something from your past?")


sm.setSpeakerID(2159380)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("What are you talking about? Roo-D, what do you know about my past?")


sm.setSpeakerID(2159380)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Ummm... take this for now.")


sm.showFieldEffect("xenon/knife", 0)
sm.sendDelay(4200)


sm.setSpeakerID(2159380)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("She had this on her when we put her in the cell. I think it's an important clue to finding out who you are. You should go talk to her. I'll keep an eye out for Gelimer.")


sm.moveNpcByObjectId(OBJECT_2, False, 700, 100)
sm.sendDelay(1500)


sm.setSpeakerID(2159380)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Thanks Roo-D.")


sm.sendDelay(2100)


sm.forcedInput(2)
sm.curNodeEventEnd(True)
