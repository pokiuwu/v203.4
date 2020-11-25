# 22001 |   Feeding Bull Dog (Evan intro)
sm.setSpeakerID(1013101)
sm.sendNext("Haha. I had a good laugh. Hahaha. But enough with that nonsense. Feed #p1013102#, would you?")
sm.setPlayerAsSpeaker()
sm.sendSay("#bWhat? That's #p1013101#'s job!")
sm.setSpeakerID(1013101)
if sm.sendAskAccept("You little brat! I told you to call me Older Brother! You know how much #p1013102# hates me. He'll bite me if I go near him. You feed him. He likes you."):
    if sm.canHold(4032447):
        sm.startQuest(parentID)
        sm.giveItem(4032447)
        sm.sendNext("Hurry up and head #bleft#k to feed #b#p1013102##k. He's been barking to be fed all morning.")
        sm.sendSay("Feed #p1013102# and come back to see me.")
        sm.dispose()
    else:
        sm.sendNext("Please make space in your Etc Inventory.")
        sm.dispose()
else:
    sm.sendNext("Stop being lazy. Do you want to see your brother bitten by a dog? Hurry up! Talk to me again and accept the quest!")
    sm.dispose()


