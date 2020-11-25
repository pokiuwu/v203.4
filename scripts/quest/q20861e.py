# Created by MechAviv
# Quest ID :: 20861
# The Path of a Dawn Warrior

sm.setSpeakerID(1101003)
if sm.sendAskYesNo("You sure you really want to become a Dawn Warrior? This is one choice that you can't change your mind about."):
    sm.giveItem(1302077)
    sm.giveItem(1142066)
    sm.completeQuest(20861)
    sm.removeSkill(10000255)
    sm.setJob(1100)
    sm.resetStats()
    sm.addSP(4)
    # Unhandled Stat Changed [MHP] Packet: 00 00 00 08 00 00 00 00 00 00 BC 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 BC 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 71 00 00 00 FF 00 00 00 00
    sm.giveSkill(10001245, 1, 1)
    sm.giveSkill(10000246, 1, 1)
    # [INVENTORY_GROW] [01 1C ]
    # [INVENTORY_GROW] [02 1C ]
    # [INVENTORY_GROW] [03 1C ]
    # [INVENTORY_GROW] [04 1C ]
    sm.setSpeakerID(1101003)
    sm.sendNext("All right, you're a Dawn Warrior now. As you get more powerful, don't forget to assign your stats in the Stats window (S). If you can't decide how, using #bAuto-Assign#k is a safe bet.")

    sm.setSpeakerID(1101003)
    sm.sendSay("I've increased your Equip and Etc inventory, so you can hold more knightly goodies.")

    sm.setSpeakerID(1101003)
    sm.sendSay("I've also given you some #bSP#k. Open your #bSkill menu#k to learn some new skills. You won't be able to learn everything right away, of course. And some skills require that you master other skills first...")

    sm.setSpeakerID(1101003)
    sm.sendSay("Remember, you're a Dawn Warrior now! That means, if you die, you'll lose some EXP.")

    sm.setSpeakerID(1101003)
    sm.sendSay("Now, get out there and make Cygnus proud!")
else:
    sm.setSpeakerID(1101003)
    sm.sendNext("Make your decision carefully.")