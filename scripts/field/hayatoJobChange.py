# Created by MechAviv
# Map ID :: 807040000
# Momijigaoka : Unfamiliar Hillside
if "1" not in sm.getQRValue(57375) and sm.getChr().getJob() == 4001:
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, True, False, False)
    sm.sendDelay(1000)

    sm.levelUntil(10)
    sm.createQuestWithQRValue(57375, "1")
    sm.removeSkill(40010001)
    sm.setJob(4100)
    sm.resetStats()
    # Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 CB 00 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MHP] Packet: 00 00 00 08 00 00 00 00 00 00 C2 00 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MMP] Packet: 00 00 00 20 00 00 00 00 00 00 71 00 00 00 FF 00 00 00 00
    sm.addSP(6, True)
    # Unhandled Stat Changed [MHP] Packet: 00 00 00 08 00 00 00 00 00 00 BC 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MMP] Packet: 00 00 00 20 00 00 00 00 00 00 D5 00 00 00 FF 00 00 00 00
    # [INVENTORY_GROW] [01 1C ]
    # [INVENTORY_GROW] [02 1C ]
    # [INVENTORY_GROW] [03 1C ]
    # [INVENTORY_GROW] [04 1C ]
    sm.giveSkill(40010000, 1, 1)
    sm.giveSkill(40010067, 1, 1)
    sm.giveSkill(40011288, 1, 1)
    sm.giveSkill(40011289, 1, 1)
    sm.removeSkill(40011227)
    sm.giveSkill(40011227, 1, 1)
    # Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 D2 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 DF 00 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [WILL_EXP] Packet: 00 00 00 00 40 00 00 00 00 00 20 2B 00 00 FF 00 00 00 00
    # Unhandled Message [INC_NON_COMBAT_STAT_EXP_MESSAGE] Packet: 14 00 00 40 00 00 00 00 00 20 2B 00 00
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)