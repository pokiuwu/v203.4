# Created by MechAviv
# Quest ID :: 21101
# The Polearm-Wielding Hero

sm.setSpeakerID(1201001)
if sm.sendAskYesNo("#b(Are you certain that you were the hero that wielded the #p1201001#? Yes, you're sure. You better grab the #p1201001# really tightly. Surely it will react to you.)#k"):
    sm.giveItem(1142129)
    sm.startQuest(21101)
    sm.completeQuest(21101)
    sm.removeSkill(20000297)
    #sm.giveSkill(20000297)
    sm.setJob(2100)
    sm.addSP(5, True)
    sm.resetStats()
    # Unhandled Stat Changed [MHP] Packet: 00 00 00 08 00 00 00 00 00 00 BC 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 BC 01 00 00 FF 00 00 00 00
    sm.removeSkill(20001296)
    sm.giveSkill(20001296)
    sm.chatScript("You learned the Back to Rien skill.")
    sm.createQuestWithQRValue(10335, "0")
    # [INVENTORY_GROW] [01 1C ]
    # [INVENTORY_GROW] [02 1C ]
    # [INVENTORY_GROW] [03 1C ]
    # [INVENTORY_GROW] [04 1C ]
    sm.setSpeakerID(1201001)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.sendNext("#b(You might be starting to remember something...)#k")


    sm.setTemporarySkillSet(0)
    sm.setDirectionMode(True, 0)
    sm.setStandAloneMode(True)
    sm.warp(914090100, 0)

else:
    sm.setSpeakerID(1201001)
    sm.sendNext("#b(You need to think about this for a second...)#k")