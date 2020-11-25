# Created by MechAviv
# Quest ID :: 1404
# Thieves of Kerning City

sm.setSpeakerID(1052001)
if sm.sendAskYesNo("Welcome to the Thieves' Hideout. Only those who are invited will ever find it. Try not to get lost on the way out. So, are you ready to become a Thief?"):
    # Inventory Operation with 6 operations.
    sm.giveItem(1472061)
    sm.giveItem(1332063)
    sm.giveItem(2070015, 1000)
    sm.giveItem(2070015, 1000)
    sm.giveItem(2070015, 1000)
    sm.giveItem(1142107)
    sm.startQuest(1404)
    sm.completeQuest(1404)
    sm.startQuest(29900)
    sm.completeQuest(29900)
    # [INVENTORY_GROW] [01 1C ]
    # [INVENTORY_GROW] [04 1C ]
    sm.setJob(400)
    sm.addSP(5, True)
    sm.createQuestWithQRValue(7635, "1")
    sm.resetStats()
    # Unhandled Stat Changed [MHP] Packet: 00 00 00 08 00 00 00 00 00 00 58 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MMP] Packet: 00 00 00 20 00 00 00 00 00 00 A3 00 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 58 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 A3 00 00 00 FF 00 00 00 00
    sm.createQuestWithQRValue(17903, "1")
    sm.completeQuest(17903)
    sm.systemMessage("You cleared the Adventure Journal mission.")
    sm.setSpeakerID(1052001)
    sm.sendNext("With this, you have become a Thief. Since you can use Thief skills now, open your Skill window and have a look. As you level up, you will be able to learn more skills.")
    sm.avatarOriented("Effect/OnUserEff.img/RecordClear_BT/clear")


    sm.setSpeakerID(1052001)
    sm.sendSay("But skills aren't enough, right? A true Thief must have the stats to match! A Thief uses LUK as the main stat and DEX as the secondary stat. If you don't know how to raise stats, just use #bAuto-Assign#k.")


    sm.setSpeakerID(1052001)
    sm.sendSay("Oh, I gave you a little gift, too. I expanded a few slots in your Equip and ETC Item tabs. Bigger Inventory, better life, I always say.")


    sm.setSpeakerID(1052001)
    sm.sendSay("Now a word of warning. Everyone loses some of their earned EXP when they fall in battle. Be careful. You don't want to lost anything you worked to get, eh?")


    sm.setSpeakerID(1052001)
    sm.sendSay("Right, that's it. Take the equipment I gave you, and use it to train your skills as a Thief.")
else:
    sm.setSpeakerID(1052001)
    sm.sendNext("Your heart is still not ready? Hmph. I am unimpressed.")


