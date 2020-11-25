# Created by MechAviv
# Map ID :: 931060070
# Edelstein : Secret Square

sm.forcedInput(0)
sm.sendDelay(30)


sm.forcedInput(1)
sm.sendDelay(30)


sm.forcedInput(0)
sm.setSpeakerID(2159385)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Claudine! We were worried sick.")


sm.setSpeakerID(2159384)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Thanks Belle.")


sm.setSpeakerID(2159388)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("But... what's with Commander Glowstick over here? Looks friendly enough, I guess...")


sm.setSpeakerID(2159386)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("I bet it's one of Gelimer's agents. They were going after you at first, Claudine. We can't trust anything that came out of that lab now...")


sm.setSpeakerID(2159384)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("These... robots saved my life. I heard the little one talking about memories being wiped. I think Gelimer was controlling them...")


sm.setSpeakerID(2159387)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("You KNOW Gelimer had control. You just saw it! We can't risk the safety of Secret Plaza because you're feeling charitable. That could be what Gelimer wants.")


sm.showEffect("Effect/Direction12.img/effect/story/BalloonMsg0/1", 1200, 0, -120, -2, -2, False, 0)
sm.sendDelay(1200)


sm.setSpeakerID(2159380)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("We can remove any control devices...")


sm.showEffect("Effect/Direction12.img/effect/story/BalloonMsg1/1", 1200, -250, -60, 0, -2, True, 0)
sm.showEffect("Effect/Direction12.img/effect/story/BalloonMsg1/1", 1200, 50, -90, 0, -2, True, 0)
sm.showEffect("Effect/Direction12.img/effect/story/BalloonMsg1/1", 1200, -60, -50, 0, -2, True, 0)
sm.showEffect("Effect/Direction12.img/effect/story/BalloonMsg1/1", 1200, 130, -50, 0, -2, True, 0)
sm.showEffect("Effect/Direction12.img/effect/story/BalloonMsg1/1", 1200, -340, -100, 0, -2, True, 0)
sm.showEffect("Effect/Direction12.img/effect/story/BalloonMsg1/1", 1200, 0, -120, -2, -2, False, 0)
sm.sendDelay(1200)


sm.setSpeakerID(2159388)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Then remove it immediately. It's dangerous.")


sm.setSpeakerID(2159380)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("It's not that simple. If I remove this, #h0# may be weakened to the point of near-death. The risks are too great to move ahead without careful consideration.")


sm.setSpeakerID(2159380)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I'm willing to take the risk. Roo-D, do it if you can.")


sm.setSpeakerID(2159380)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Really? Are you sure?")


sm.setSpeakerID(2159380)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("If Gelimer were to take control, I would be a danger to everyone around me. I can't let that happen. I will never be controlled again.")


sm.setSpeakerID(2159380)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Okay, I'll remove it... ")


sm.setSpeakerID(2159380)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("You might feel a little dizzy at first... but I'm almost done.")
if "1" not in sm.getQRValue(23711):
    sm.levelUntil(10)
    sm.removeSkill(30020233)
    sm.setJob(3600)
    sm.removeSkill(30021238)
    sm.giveSkill(30020232, 1, 1)
    sm.giveSkill(30020234, 1, 1)
    sm.giveSkill(36000004, 1, 1)
    sm.giveSkill(30021237, 1, 1)
    sm.giveSkill(30020240, 1, 1)
    sm.resetStats()
    # Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 C2 00 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MHP] Packet: 00 00 00 08 00 00 00 00 00 00 C2 00 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 71 00 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MMP] Packet: 00 00 00 20 00 00 00 00 00 00 71 00 00 00 FF 00 00 00 00
    sm.addSP(4, True)
    # Unhandled Stat Changed [MHP] Packet: 00 00 00 08 00 00 00 00 00 00 58 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MMP] Packet: 00 00 00 20 00 00 00 00 00 00 A3 00 00 00 FF 00 00 00 00
    # [INVENTORY_GROW] [01 1C ]
    # [INVENTORY_GROW] [02 1C ]
    # [INVENTORY_GROW] [03 1C ]
    # [INVENTORY_GROW] [04 1C ]
    sm.createQuestWithQRValue(23711, "1")
    sm.giveAndEquip(1353001)
    sm.giveAndEquip(1242001)
    # Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 84 02 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 07 01 00 00 FF 00 00 00 00
    sm.giveItem(2000018, 15)
    sm.giveItem(1142575)
    sm.giveItem(3010585, 1)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.warp(310010000, 0)
