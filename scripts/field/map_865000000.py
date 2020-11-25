# Created by MechAviv
# ID :: [865000000]
# Commerci Republic : San Commerci

if sm.hasQuest(17614): # [Commerci Republic] Ciao, Until Next Time
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, True, False, False)
    sm.setStandAloneMode(True)
    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendNext("Is this #e#bSan Commerci#k#n, the capital of Commerci?")

    sm.moveCamera(False, 400, 5000, 526)

    sm.sendDelay(14007)

    sm.moveCamera(True, 3000, 0, 0)

    sm.sendDelay(1000)

    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendNext("#b(This place is HUGE! How am I ever going to find Leon? I guess I'll head to the Daniella Merchant Union first.)#k")


    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendSay("Maybe I should ask that guy for directions.")


    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)
    sm.setStandAloneMode(False)
    sm.createQuestWithQRValue(17614, "Arrived")
    # Unhandled Message [INC_COMMITMENT_MESSAGE] Packet: 09 01 00 00 00 00
    sm.completeQuest(17614)
    # Unhandled Message [INC_COMMITMENT_MESSAGE] Packet: 09 01 00 00 00 00
    sm.completeQuest(17711)
    # Unhandled Stat Changed [EXP] Packet: 00 00 00 00 01 00 00 00 00 00 34 ED 72 01 00 00 00 00 FF 00 00 00 00
    sm.giveExp(630724)
    # [GUILD_RESULT] [74 F9 DA 01 00 84 02 00 00 03 00 00 00 BA 00 00 00 ]
    # [GUILD_RESULT] [6D F9 DA 01 00 1E 4F 85 00 84 02 00 00 0F 00 00 00 B2 01 00 00 50 24 E9 97 37 ED D4 01 ]
    # Update Quest Record EX | Quest ID: [18418] | Data: B=1337
    # [GUILD_RESULT] [74 F9 DA 01 00 85 02 00 00 03 00 00 00 BA 00 00 00 ]
    # [GUILD_RESULT] [6D F9 DA 01 00 1E 4F 85 00 85 02 00 00 10 00 00 00 B2 01 00 00 80 99 E9 97 37 ED D4 01 ]
    # Update Quest Record EX | Quest ID: [18418] | Data: B=1338
elif sm.hasQuest(17617): # [Commerci Republic] Missing Goods
    sm.chatScript("You were told the impostor was last seen heading south")
else:
    sm.showEffect("Map/EffectBT.img/dawnveil1/temaD") # San Commerci Theme Dungeon Effect