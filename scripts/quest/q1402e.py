# Created by MechAviv
# Quest ID :: 1402
# Magicians of Ellinia

sm.setSpeakerID(1032001)
if sm.sendAskYesNo("Ahh, welcome. It's good to finally meet, face-to-face. I can already see that you will become a great Magician. Let's make this official right away."):
    # Inventory Operation with 2 operations.
    sm.giveItem(1372043)
    sm.giveItem(1142107)
    sm.startQuest(1402)
    sm.completeQuest(1402)
    sm.startQuest(29900)
    sm.completeQuest(29900)
    sm.setJob(200)
    sm.addSP(5, True)
    sm.resetStats()
    # Unhandled Stat Changed [MMP] Packet: 00 00 00 20 00 00 00 00 00 00 1D 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 1D 01 00 00 FF 00 00 00 00
    sm.createQuestWithQRValue(17903, "1")
    sm.completeQuest(17903)
    sm.systemMessage("You cleared the Adventure Journal mission.")
    sm.setSpeakerID(1032001)
    sm.sendNext("With this, you will be able to use a wide variety of magic skills. I gave you a bit of #bSP#k, so open the #bSkill#k window and try learning a skill. If possible, try to learn some #battack magic#k.")
    sm.avatarOriented("Effect/OnUserEff.img/RecordClear_BT/clear")


    sm.setSpeakerID(1032001)
    sm.sendSay("But remember, skills aren't everything. Your stats should support your skills as a Magician, also. Magicians use INT as their main stat, and LUK as their secondary stat. If raising stats is difficult, just use #bAuto-Assign#k.")


    sm.setSpeakerID(1032001)
    sm.sendSay("Now, one more word of warning for you. If you fall in battle from this point on, you will lose a portion of your total EXP. Be extra mindful of this, since you have less HP than most. ")


    sm.setSpeakerID(1032001)
    sm.sendSay("This is all I can teach you. You have your wand for training, so use it well. I wish you luck on your journey.")
else:
    sm.setSpeakerID(1032001)
    sm.sendNext("Are you afraid? Don't be...this is the path that's been prepared for you.")