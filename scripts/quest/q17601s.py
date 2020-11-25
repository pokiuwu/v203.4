# Created by MechAviv
# Quest ID :: 17601
# [Commerci Republic] In the Name of the Empress

sm.setNpcOverrideBoxChat(1540450)
sm.sendNext("Ah, #e#b#h0##k#n you've come! My apologies for bringing you here on such short notice. Tell me, have you heard of the land of Commerci, by any chance?")


sm.setNpcOverrideBoxChat(1540450)
sm.sendSay("Commerci was once a small fishing village, and they had little in the way of dealings with the rest of the world. It seems that time has been good to Commerci, as they have begun sending large trading vessels around the globe, and have re-branded themselves the #bCommerci Republic#k.")


sm.setNpcOverrideBoxChat(1540450)
sm.sendSay("#face0#As empress, I fear that such rapid growth from Commerci may have adverse effects on the rest of our world. If we can earn their friendship, they may become a powerful ally in time. However, if Commerci were to side with Black Mage... they could become a formidable threat.")


sm.setNpcOverrideBoxChat(1540450)
if sm.sendAskYesNo("#h0#, we need your help. Please act as my envoy to Commerci, and bring them a message: The kingdom of Ereve seeks a peaceful and mutually beneficial relationship with Commerci. Will you please do this for me? Neinheart will explain the mission."):
    sm.setNpcOverrideBoxChat(1540450)
    sm.sendNext("#face2#I know you won't let me down.")

    sm.setNpcOverrideBoxChat(1540450)
    sm.sendSay("I asked Neinheart to prepare you for the journey. I will be praying for you.")

    # Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
    # Unhandled Message [INC_COMMITMENT_MESSAGE] Packet: 09 01 00 00 00 00
    sm.startQuest(17601)
    sm.completeQuest(17601)
    # Unhandled Stat Changed [EXP] Packet: 00 00 00 00 01 00 00 00 00 00 56 1B 73 00 00 00 00 00 FF 00 00 00 00
    sm.giveExp(556227)
    # [GUILD_RESULT] [74 F9 DA 01 00 77 02 00 00 03 00 00 00 BA 00 00 00 ]
    # [GUILD_RESULT] [6D F9 DA 01 00 1E 4F 85 00 77 02 00 00 02 00 00 00 B2 01 00 00 E0 51 EF 6C 35 ED D4 01 ]
    # Update Quest Record EX | Quest ID: [18418] | Data: B=1324
else:
    sm.setNpcOverrideBoxChat(1540450)
    sm.sendSayOkay("#face0#Things keep getting stranger. I fear for Maple World.")