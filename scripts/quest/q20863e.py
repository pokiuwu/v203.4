# Created by MechAviv
# Quest ID :: 20863
# The Path of a Wind Archer

sm.setSpeakerID(1101005)
if sm.sendAskYesNo("Are you certain of your choice? Once you've tasted the wind, there is no going back."):
    sm.setSpeakerID(1101005)
    sm.sendNext("You have become a Wind Archer. I will give you your abilities now. Work tirelessly, and you will be worthy of them.")

    sm.giveItem(1452051)
    sm.giveItem(2060000, 9999)
    sm.giveItem(2060000, 9999)
    sm.giveItem(2060000, 9999)
    sm.giveItem(1142066)
    # [HIRE_TUTOR] [00]
    sm.completeQuest(20863)
    sm.removeSkill(10000257)
    # Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
    # [VMATRIX_UPDATE] [00 00 00 00 13 00 00 00 FF FF FF FF 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 FF FF FF FF 02 00 00 00 00 00 00 00 00 FF FF FF FF 03 00 00 00 00 00 00 00 00 FF FF FF FF 04 00 00 00 00 00 00 00 00 FF FF FF FF 05 00 00 00 00 00 00 00 00 FF FF FF FF 06 00 00 00 00 00 00 00 00 FF FF FF FF 07 00 00 00 00 00 00 00 00 FF FF FF FF 08 00 00 00 00 00 00 00 00 FF FF FF FF 09 00 00 00 00 00 00 00 00 FF FF FF FF 0A 00 00 00 00 00 00 00 00 FF FF FF FF 0B 00 00 00 00 00 00 00 00 FF FF FF FF 0C 00 00 00 00 00 00 00 00 FF FF FF FF 0D 00 00 00 00 00 00 00 00 FF FF FF FF 0E 00 00 00 00 00 00 00 00 FF FF FF FF 0F 00 00 00 00 00 00 00 00 FF FF FF FF 10 00 00 00 00 00 00 00 00 FF FF FF FF 11 00 00 00 00 00 00 00 00 FF FF FF FF 12 00 00 00 00 00 00 00 00 00 ]
    sm.setJob(1300)
    sm.addSP(4, True)
    sm.resetStats()
    # Unhandled Stat Changed [MHP] Packet: 00 00 00 08 00 00 00 00 00 00 58 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MMP] Packet: 00 00 00 20 00 00 00 00 00 00 A3 00 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 58 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 A3 00 00 00 FF 00 00 00 00
    sm.giveSkill(10001245, 1, 1)
    sm.giveSkill(10000247, 1, 1)
    # [INVENTORY_GROW] [01 1C ]
    # [INVENTORY_GROW] [02 1C ]
    # [INVENTORY_GROW] [03 1C ]
    # [INVENTORY_GROW] [04 1C ]
    sm.setSpeakerID(1101005)
    sm.sendSay("You will work with all of your might from now on, and though doing so, you will gain points to distribute to your stats in the Stats Window (S). If you're unsure what stats you need, try the #bAuto-Assign#k function.")


    sm.setSpeakerID(1101005)
    sm.sendSay("A Wind Archer requires many items, so I have expanded your Etc storage.")


    sm.setSpeakerID(1101005)
    sm.sendSay("I've given you some #bSP#k. Open your  #bSkill Menu#k and learn some skills. There will be times you're unable to train one skill until you've mastered another, but nothing is earned without hard work.")


    sm.setSpeakerID(1101005)
    sm.sendSay("You are a Wind Archer now, so you'll lose EXP if you die. Make wise choices accordingly.")


    sm.setSpeakerID(1101005)
    sm.sendSay("Now, go and be one with the wind. Never stop striving.")

else:
    sm.setSpeakerID(1101005)
    sm.sendNext("Yes, think it through.")


