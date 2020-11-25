# Created by MechAviv
# Quest ID :: 20862
# The Path of a Blaze Wizard

sm.setSpeakerID(1101004)
if sm.sendAskYesNo("Super! Now, you're really, really, really sure you want to be a Blaze Wizard, right? I won't let you change your mind!"):
    sm.setSpeakerID(1101004)
    sm.sendNext("Magic dust, fairy powder, voila! You're now a Blaze Wizard! Celebrate! Here comes the POWER!")
    sm.giveItem(1372043)
    sm.giveItem(1142066)
    sm.completeQuest(20862)
    sm.removeSkill(10000256)
    # Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
    # [VMATRIX_UPDATE] [00 00 00 00 13 00 00 00 FF FF FF FF 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 FF FF FF FF 02 00 00 00 00 00 00 00 00 FF FF FF FF 03 00 00 00 00 00 00 00 00 FF FF FF FF 04 00 00 00 00 00 00 00 00 FF FF FF FF 05 00 00 00 00 00 00 00 00 FF FF FF FF 06 00 00 00 00 00 00 00 00 FF FF FF FF 07 00 00 00 00 00 00 00 00 FF FF FF FF 08 00 00 00 00 00 00 00 00 FF FF FF FF 09 00 00 00 00 00 00 00 00 FF FF FF FF 0A 00 00 00 00 00 00 00 00 FF FF FF FF 0B 00 00 00 00 00 00 00 00 FF FF FF FF 0C 00 00 00 00 00 00 00 00 FF FF FF FF 0D 00 00 00 00 00 00 00 00 FF FF FF FF 0E 00 00 00 00 00 00 00 00 FF FF FF FF 0F 00 00 00 00 00 00 00 00 FF FF FF FF 10 00 00 00 00 00 00 00 00 FF FF FF FF 11 00 00 00 00 00 00 00 00 FF FF FF FF 12 00 00 00 00 00 00 00 00 00 ]
    sm.setJob(1200)
    sm.resetStats()
    sm.addSP(4, True)
    # Unhandled Stat Changed [MMP] Packet: 00 00 00 20 00 00 00 00 00 00 1D 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 1D 01 00 00 FF 00 00 00 00
    sm.giveSkill(10001245, 1, 1)
    sm.giveSkill(10000248, 1, 1)
    # [INVENTORY_GROW] [01 1C ]
    # [INVENTORY_GROW] [02 1C ]
    # [INVENTORY_GROW] [03 1C ]
    # [INVENTORY_GROW] [04 1C ]
    sm.setSpeakerID(1101004)
    sm.sendSay("As a Blaze Wizard, you'll be given skill points as your progress. To spend them, just open your Stats Window (S). If you're not sure what to spend them on, #bAuto-Assign#k will do it for you. Easy-peasy!")

    
    sm.setSpeakerID(1101004)
    sm.sendSay("And... I've increased your Equip and Etc slots! Yippee!")


    sm.setSpeakerID(1101004)
    sm.sendSay("I've also given you some handy-dandy #bSP#k, so go ahead and open your #bSkill menu#k to learn some skills! You can't learn them all at once, and sometimes you have to be good at one skill before you can learn another, but take your time, and you'll soon wield more power than you could ever imagine! Like me!")


    sm.setSpeakerID(1101004)
    sm.sendSay("Since you're a Blaze Wizard now, if you die, you'll lose EXP. But I don't expect you to EVER die, hehe.")


    sm.setSpeakerID(1101004)
    sm.sendSay("Now, totally get out there and kick some Black Wing booty!")
else:
    sm.setSpeakerID(1101004)
    sm.sendNext("Okie dokie, think it over!")