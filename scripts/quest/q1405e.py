# Created by MechAviv
# Quest ID :: 1405
# Pirates of the Nautilus

sm.setSpeakerID(1090000)
if sm.sendAskYesNo("It's nice to see you face to face... Why are you so surprised? Is it because I look so young? I'm actually much older than I look, so don't think of me as a pushover... All right, I'll make you into a Pirate right this second."):
    # Inventory Operation with 6 operations.
    sm.giveItem(1482014)
    sm.giveItem(1492014)
    sm.giveItem(2330006, 600)
    sm.giveItem(2330006, 600)
    sm.giveItem(2330006, 600)
    sm.giveItem(1142107)
    
    sm.startQuest(1405)
    sm.completeQuest(1405)
    sm.startQuest(29900)
    sm.completeQuest(29900)
    sm.setJob(500)
    sm.addSP(5, True)
    sm.removeSkill(109)
    sm.removeSkill(111)
    sm.resetStats()
    # Unhandled Stat Changed [MHP] Packet: 00 00 00 08 00 00 00 00 00 00 58 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MMP] Packet: 00 00 00 20 00 00 00 00 00 00 A3 00 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 58 01 00 00 FF 00 00 00 00
    # Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 A3 00 00 00 FF 00 00 00 00
    sm.createQuestWithQRValue(17903, "1")
    sm.completeQuest(17903)
    sm.systemMessage("You cleared the Adventure Journal mission.")
    # [INVENTORY_GROW] [01 24 ]
    # [INVENTORY_GROW] [02 24 ]
    # [INVENTORY_GROW] [03 24 ]
    # [INVENTORY_GROW] [04 24 ]
    sm.setSpeakerID(1090000)
    sm.sendNext("Well, you are truly one of us now. Open up your Skill window and check out your new Pirate abilities. I also gave you a few extra SP, so you can go ahead and boost some of your new skills. You'll get more skills at higher levels, so I suggest you have a plan for your training.")
    sm.avatarOriented("Effect/OnUserEff.img/RecordClear_BT/clear")


    sm.setSpeakerID(1090000)
    sm.sendSay("Skills alone do not make you a great pirate. You have to distribute your stats like a pirate, too! If you're hoping to become a Brawler, invest heavily in STR. If you're more the Gunslinger type, then go for DEX. And if you just have no idea, use the #bAuto-Assign#k option. Simple, and effective.")


    sm.setSpeakerID(1090000)
    sm.sendSay("Here's another gift for you. I expanded a few slots in your Equip, Use, Set-up, and Etc tabs in your Inventory, so you should have plenty of room.")


    sm.setSpeakerID(1090000)
    sm.sendSay("Now, there is one last thing that you need to remember. More than anything else, you need to keep your HP up. If you fall in battle, you'll lose some of your EXP. And I'm SURE you don't want that, right?")


    sm.setSpeakerID(1090000)
    sm.sendSay("Well, that's it! I have taught you everything you need to know. I also gave you a few decent weapons, so make good use of them. Now, go forth, grow stronger, and kick around the Black Mage's minions, if you get the chance!")
else:
    sm.setSpeakerID(1090000)
    sm.sendNext("Your heart isn't in this? That's too bad.")