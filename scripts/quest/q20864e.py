# Created by MechAviv
# Quest ID :: 20864
# The Path of a Night Walker

sm.setSpeakerID(1101006)
if sm.sendAskYesNo("Hmph, are you sure? Once you become a Night Walker, you will be a Night Walker for life."):
    sm.setSpeakerID(1101006)
    sm.sendNext("... You are now a Night Walker. I have granted you some abilities.")

    sm.giveItem(1472061)
    sm.giveItem(2070015, 1000)
    sm.giveItem(2070015, 1000)
    sm.giveItem(2070015, 1000)
    sm.giveItem(1142066)
    # [HIRE_TUTOR] [00 ]
    sm.completeQuest(20864)
    sm.removeSkill(10000258)
    sm.setJob(1400)
    sm.resetStats()
    sm.addSP(4, True)
    # Unhandled Message [INC_SP_MESSAGE] Packet: 05 90 01 04
    # Unhandled Stat Changed [MHP] Packet: 00 00 00 08 00 00 00 00 00 00 58 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MMP] Packet: 00 00 00 20 00 00 00 00 00 00 A3 00 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 58 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 A3 00 00 00 FF 00 00 00 00
    sm.giveSkill(10001245, 1, 1)
    sm.giveSkill(10000249, 1, 1)
    # [INVENTORY_GROW] [01 1C ]
    # [INVENTORY_GROW] [02 1C ]
    # [INVENTORY_GROW] [03 1C ]
    # [INVENTORY_GROW] [04 1C ]
    sm.setSpeakerID(1101006)
    sm.sendSay("To become more powerful as a Night walker, increase your stats in your Stats Window (S). If distributing stats is too difficult, use #bAuto-Assign#.")


    sm.startQuest(29906)
    sm.completeQuest(29906)
    # [QUEST_RESULT] [0B D2 74 00 00 C8 CC 10 00 00 00 00 00 00 ]
    sm.setSpeakerID(1101006)
    sm.sendSay("I've expanded your Equip and Etc slots. Do not waste this gift.")


    sm.setSpeakerID(1101006)
    sm.sendSay("I have granted you some #bSP#k. Open your #bSkill menu#k to learn a skill. You can learn more later, though you may have to master one before another opens to you.")


    sm.setSpeakerID(1101006)
    sm.sendSay("Remember, death can now touch you as a Night Walker. If you die, you will lose EXP.")


    sm.setSpeakerID(1101006)
    sm.sendSay("Now, may the shadows keep you safe, young Cygnus Knight.")