# Baby Pig  (1013200) |
PIGLET = 4032449

if sm.hasItem(PIGLET) and sm.hasQuestCompleted(22015):
    sm.sendSayOkay("#b(You already have Piglet.)")
    sm.dispose()
elif not sm.hasQuestCompleted(22011) or not sm.getFieldID() == 900020110 or sm.hasItem(PIGLET):
    sm.sendSayOkay("#b(You are too far from the Piglet. Go closer to grab it.)")
    sm.dispose()
else:
    if sm.canHold(PIGLET):
        sm.giveItem(PIGLET)
        sm.startQuest(22015)
        sm.completeQuest(22015)
        sm.systemMessage("You have rescued the Piglet.")
        sm.dispose()
    else:
        sm.sendSay("Please make room in your Etc Inventory.")
        sm.dispose()