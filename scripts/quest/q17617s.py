# Created by MechAviv
# Quest ID :: 17617
# [Commerci Republic] Missing Goods

# Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 78 24 00 00 FF 00 00 00 00
sm.setSpeakerID(9390220)
sm.sendNext("The only odd thing about it was...")


sm.setSpeakerID(9390220)
sm.setPlayerAsSpeaker()
if sm.sendAskYesNo("(Aha! I knew there had to be something!)"):
    sm.setSpeakerID(9390220)
    sm.setPlayerAsSpeaker()
    sm.sendNext("Yes?")


    sm.setSpeakerID(9390220)
    sm.sendSay("The items Tepes deposited were obviously packed for sea, but Tepes brought a cart with him when he picked them up. Usually, the merchants move them straight to the ships...")


    sm.setSpeakerID(9390220)
    sm.setPlayerAsSpeaker()
    sm.sendSay("Did you see which way he went?")


    sm.setSpeakerID(9390220)
    sm.sendSay("Listen, kid, I don't keep track of every sailor who walks by. But there was a fish cart accident between here and Berry, so the western path is blocked.")


    sm.setSpeakerID(9390220)
    sm.sendSay("And if he didn't go west, he probably went east, toward the canals.")


    sm.setSpeakerID(9390220)
    sm.setPlayerAsSpeaker()
    sm.sendSay("Thank you. That was very helpful.")


    sm.setSpeakerID(9390220)
    sm.sendSay("Whatever, kid. Just don't mess with my trading post.")


    # Unhandled Message [INC_COMMITMENT_MESSAGE] Packet: 09 01 00 00 00 00
    sm.startQuest(17617)
    sm.completeQuest(17617)
    # Unhandled Stat Changed [EXP] Packet: 00 00 00 00 01 00 00 00 00 00 21 33 8B 01 00 00 00 00 FF 00 00 00 00
    sm.giveExp(530255)
    # Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 82 24 00 00 FF 00 00 00 00
    # [GUILD_RESULT] [74 F9 DA 01 00 88 02 00 00 03 00 00 00 BA 00 00 00 ]
    # [GUILD_RESULT] [6D F9 DA 01 00 1E 4F 85 00 88 02 00 00 13 00 00 00 B2 01 00 00 A0 19 4A D3 37 ED D4 01 ]
    # Update Quest Record EX | Quest ID: [18418] | Data: B=1341
else:
    sm.setSpeakerID(9390220)
    sm.setPlayerAsSpeaker()
    sm.sendSayOkay("Talk to me in a bit, okay?")


