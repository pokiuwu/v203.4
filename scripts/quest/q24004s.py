# Created by MechAviv
# Quest ID :: 24004
# Barriers of Elluel

sm.setSpeakerID(1033205)
if sm.sendAskAccept("#b(You can see the wards to create the seal around Elluel. Speaking the magic word will finish the spell, cutting the village off from the outside world for at least 100 years. Activate the seal?)"):
    sm.startQuest(24004)
    sm.completeQuest(24004)
    sm.setSpeakerID(1033205)
    sm.sendSayOkay("#b(The seal is complete, and the town is safe.)")
else:
    sm.setSpeakerID(1033205)
    sm.setPlayerAsSpeaker()
    sm.sendNext("It's not a bad idea to get rid of the monsters outside first.")


