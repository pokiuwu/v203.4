# Created by MechAviv
# Quest ID :: 1401
# Warriors of Perion

sm.setSpeakerID(1022000)
if sm.sendAskYesNo("I am glad to see you here in person. If you are ready, I will declare you a Warrior. There will be no going back."):
    sm.giveItem(1302077)
    sm.giveItem(1142107)
    sm.startQuest(1401)
    sm.completeQuest(1401)
    sm.startQuest(29900)
    sm.completeQuest(29900)
    sm.setJob(100)
    sm.addSP(5, True)
    # [INVENTORY_GROW] [01 1C ]
    # [INVENTORY_GROW] [02 1C ]
    # [INVENTORY_GROW] [03 1C ]
    # [INVENTORY_GROW] [04 1C ]
    sm.resetStats()
    #  Unhandled Stat Changed [MHP] Packet: 00 00 00 08 00 00 00 00 00 00 BC 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 BC 01 00 00 FF 00 00 00 00
    sm.createQuestWithQRValue(17903, "1")
    sm.completeQuest(17903)
    sm.systemMessage("You cleared the Adventure Journal mission.")
    sm.setSpeakerID(1022000)
    sm.avatarOriented("Effect/OnUserEff.img/RecordClear_BT/clear")
    sm.sendNext("Now that you have become a Warrior, you will be much more powerful. Try out your new strength and skills. You'll be pleasantly surprised.")


    sm.setSpeakerID(1022000)
    sm.sendSay("Your stats should be more appropriate for a Warrior, also. For Warriors, STR is the main stat and DEX is the secondary stat. If you don't follow, try using the #bAuto-Assign#k function.")


    sm.setSpeakerID(1022000)
    sm.sendSay("In celebration of you becoming a Warrior, I increased your Inventory space. Gather strong weapons and equipment, and push your limits.")


    sm.setSpeakerID(1022000)
    sm.sendSay("By the way...it's important that you not fall in battle now. If you do, you'll lose a little of the EXP you've accumulated. It's not a pleasant experience.")


    sm.setSpeakerID(1022000)
    sm.sendSay("This is all I can teach you for now. Take up your weapon, and train hard.")
else:
    sm.setSpeakerID(1022000)
    sm.sendNext("Hmm... Still unsure of your path? Perhaps the way of the Warrior is not for you.")
