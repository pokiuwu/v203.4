if sm.hasQuest(22007):
    if not sm.hasItem(4032451):
        if sm.canHold(4032451):
            sm.giveItem(4032451)
            sm.sendNext("#b(You have obtained an Egg. Deliver it to Utah.)")
            sm.dispose()
        else:
            sm.sendNext("Please make room in your Etc Inventory.")
            sm.dispose()
    else:
        sm.sendNext("#b(You have already obtained an Egg. Take the Egg you have and give it to Utah.)")
        sm.dispose()
else:
    sm.sendSayOkay("#b(You don't need to take an egg now.)#k")
    sm.dispose()