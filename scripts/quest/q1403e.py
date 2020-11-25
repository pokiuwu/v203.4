# Created by MechAviv
# Quest ID :: 1403
# Bowmen of Henesys

sm.setSpeakerID(1012100)
if sm.sendAskYesNo("Welcome to the Bowman Instructional School. It's good to meet face-to-face. Now then, I'll make you into a proper Bowman."):
    # Inventory Operation with 5 operations.
    sm.giveItem(1452051)
    sm.giveItem(2060000, 2000)
    sm.giveItem(2060001, 2000)
    sm.giveItem(1024000)
    sm.giveItem(1142107)
    sm.startQuest(1403)
    sm.completeQuest(1403)
    sm.startQuest(29900)
    sm.completeQuest(29900)
    # [INVENTORY_GROW] [01 1C ]
    # [INVENTORY_GROW] [02 1C ]
    sm.setJob(300)
    sm.addSP(5)
    sm.resetStats()
    # Unhandled Stat Changed [MHP] Packet: 00 00 00 08 00 00 00 00 00 00 58 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MMP] Packet: 00 00 00 20 00 00 00 00 00 00 A3 00 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 58 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 A3 00 00 00 FF 00 00 00 00
    sm.createQuestWithQRValue(17903, "1")
    sm.completeQuest(17903)
    sm.systemMessage("You cleared the Adventure Journal mission.")
    sm.setSpeakerID(1012100)
    sm.sendNext("Congratulations, you are now a Bowman. If you wish you to learn new skills as a Bowman, open the Skill window. I gave you a bit of #bSP#k to play around with, so try learning a skill.")
    sm.avatarOriented("Effect/OnUserEff.img/RecordClear_BT/clear")


    sm.setSpeakerID(1012100)
    sm.sendSay("But skills aren't everything, are they? Your stats should reflect your job as a Bowman. A Bowman uses DEX as the main stat and STR as the secondary stat. If you don't know how much of which stat to raise, try using #bAuto-Assign#k.")


    sm.setSpeakerID(1012100)
    sm.sendSay("Oh, and I gave you a small gift. I increased the space on your Equip and Use tabs. Use that space wisely!")


    sm.setSpeakerID(1012100)
    sm.sendSay("There's one more thing I must warn you about. Now that you have become a Bowman, if you fall in battle, you will lose some of the EXP you gained. Please don't forget.")


    sm.setSpeakerID(1012100)
    sm.sendSay("This is the extent of what I can teach you. Since I gave you a proper weapon, please train yourself as you travel the world.")
else:
    sm.setSpeakerID(1012100)
    sm.sendNext("You need to prepare some more? Please, take your time.")

