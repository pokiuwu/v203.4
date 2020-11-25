# Created by MechAviv
# Quest ID :: 20837
# Lesson 5 - Skills

sm.setSpeakerID(1102102)
sm.removeEscapeButton()
sm.sendNext("Elemental Slash is useful, so use it often!")


sm.setSpeakerID(1102102)
sm.removeEscapeButton()
sm.sendSay("I supposed you're about ready to become a Knight-in-Training. I'll send you to the Test Site, and remember, no slouching!")


sm.completeQuest(20837)
# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
sm.warp(130030106, 0)