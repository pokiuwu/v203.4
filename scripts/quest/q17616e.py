# Created by MechAviv
# Quest ID :: 17616
# [Commerci Republic] Stolen Items

sm.setSpeakerID(9390220)
sm.sendNext("Yes? What do you want?")


sm.setSpeakerID(9390220)
sm.setPlayerAsSpeaker()
sm.sendSay("What can you tell me about the items that were stolen from the Daniella Merchant Union?")


sm.setSpeakerID(9390220)
sm.sendSay("Not much to tell. A few days ago, a Daniella merchant deposited some items. A little while ago, he picked them up.")


sm.setSpeakerID(9390220)
sm.setPlayerAsSpeaker()
sm.sendSay("Are you sure it was the same guy?")


sm.setSpeakerID(9390220)
sm.sendSay("Are you sure you have a brain in your skull? Yes, it was the same guy.")


sm.setSpeakerID(9390220)
sm.setPlayerAsSpeaker()
sm.sendSay("No need to bite my head off.")


# Unhandled Message [INC_COMMITMENT_MESSAGE] Packet: 09 01 00 00 00 00
sm.completeQuest(17616)
sm.giveExp(530255)
# [GUILD_RESULT] [74 F9 DA 01 00 87 02 00 00 03 00 00 00 BA 00 00 00 ]
# [GUILD_RESULT] [6D F9 DA 01 00 1E 4F 85 00 87 02 00 00 12 00 00 00 B2 01 00 00 B0 10 82 CB 37 ED D4 01 ]
# Update Quest Record EX | Quest ID: [18418] | Data: B=1340
# Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 AB 0C 00 00 FF 00 00 00 00
