# Created by MechAviv
# Quest ID :: 17616
# [Commerci Republic] Stolen Items

sm.setSpeakerID(9390217)
sm.sendNext("What to do, what to do? They'll blame me for sure!")


sm.setSpeakerID(9390217)
sm.setPlayerAsSpeaker()
sm.sendSay("Huh? What's wrong?")


sm.setSpeakerID(9390217)
sm.sendSay("You again? I don't have time right now. The items I left at the Trading Post have disappeared!")


sm.setSpeakerID(9390217)
sm.setPlayerAsSpeaker()
sm.sendSay("They were stolen?")


sm.setSpeakerID(9390217)
sm.sendSay("Yes! And do you know who the prime suspect is? It's me!")


# Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 A0 0C 00 00 FF 00 00 00 00
sm.setSpeakerID(9390217)
sm.setPlayerAsSpeaker()
sm.sendSay("Wait, your items were stolen... and you're the main suspect? How does that make sense?")


# Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 46 24 00 00 FF 00 00 00 00
sm.setSpeakerID(9390217)
sm.sendSay("Someone must've copied my fabulous fashion sense and then taken the goods, posing as me. But who's going to believe that? I'm going to lose my job, and I can't give up my sailor's wardrobe, I just can't.")


sm.setSpeakerID(9390217)
sm.setPlayerAsSpeaker()
if sm.sendAskYesNo("#b(This seems like my chance to get in good with the Daniella Merchant Union!)#k"):
    sm.startQuest(17616)
    sm.setSpeakerID(9390217)
    sm.setPlayerAsSpeaker()
    sm.sendNext("#b(I'll head to the Trading Post to investigate further.)#k")
else:
    sm.setSpeakerID(9390217)
    sm.setPlayerAsSpeaker()
    sm.sendSayOkay("(Maybe that's not such a good idea. I'll just sit here and hope a miracle happens.)")


