# Created by MechAviv
# Quest ID :: 20833
# Tiny Bird

sm.setSpeakerID(1102113)
sm.removeEscapeButton()
sm.sendNext("(*chirp, chirp*)")


sm.setSpeakerID(1102113)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.sendSay("Look! It's a bird! Is it talking to me?")


sm.setSpeakerID(1102113)
sm.removeEscapeButton()
sm.sendSay("*chirp, chirp, chirp*")


sm.setSpeakerID(1102113)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.sendSay("OMIGOODNESS! I can understand birds! I must be some sort of superhero. It... wants me to follow it. I'm sure Kizan won't mind.")


sm.startQuest(20833)
# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00 
sm.warp(130030104, 0)