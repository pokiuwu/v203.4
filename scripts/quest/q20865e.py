# Created by MechAviv
# Quest ID :: 20865
# The Path of a Thunder Breaker

sm.setSpeakerID(1101007)
if sm.sendAskYesNo("Yer sure about this? Remember, ye can't change yer mind, so ye'll want to pick carefully. Ye really want to join me as a Thunder Breaker?"):
    sm.setSpeakerID(1101007)
    sm.sendNext("Just like that, yer a Thunder Breaker! Now let me give ye some abilities.")

    sm.giveItem(1482014)
    sm.giveItem(1142066)
    # [HIRE_TUTOR] [00 ]
    sm.completeQuest(20865)
    sm.removeSkill(10000259)
    sm.setJob(1500)
    sm.resetStats()
    sm.addSP(4, True)
    # Unhandled Stat Changed [MHP] Packet: 00 00 00 08 00 00 00 00 00 00 58 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MMP] Packet: 00 00 00 20 00 00 00 00 00 00 A3 00 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 58 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 A3 00 00 00 FF 00 00 00 00
    sm.giveSkill(10001245, 1, 1)
    sm.giveSkill(10000246, 1, 1)
    # [INVENTORY_GROW] [01 1C ]
    # [INVENTORY_GROW] [02 1C ]
    # [INVENTORY_GROW] [03 1C ]
    # [INVENTORY_GROW] [04 1C ]
    sm.setSpeakerID(1101007)
    sm.sendSay("To become a stronger Thunder Breaker, ye have to distribute yer stats. Just open yer Stats Window (S) to do so. If yer unsure what stats to upgrade, try usin' the ol' #bAuto-Assign#k feature.")


    sm.setSpeakerID(1101007)
    sm.sendSay("A Thunder Breaker be needin' an awful lot of items, so I've expanded yer Etc slots.")


    sm.setSpeakerID(1101007)
    sm.sendSay("I've also given ye some #bSP#k. Just open yer #bSkill Menu#k to learn some new skills. There isn't much to learn and ye can't learn them all at once, and some of them require that ye learn other skills first.")


    sm.setSpeakerID(1101007)
    sm.sendSay("As an ol' Thunder Breaker, remember that if ye die, ye'll lose some EXP. So, don't go bargin' into fights without thinkin', ye hear?")


    sm.setSpeakerID(1101007)
    sm.sendSay("Now, set sail on yer adventure as a Cygnus Knight!")
else:
    sm.setSpeakerID(1101007)
    sm.sendNext("Well, that's too bad! I would've loved to have ye on me crew.")