# Created by MechAviv
# [Vasily]  |  [10305]
# Maple Road : Southperry
if sm.hasQuest(32214):
    sm.setSpeakerID(10305)
    if sm.sendAskYesNo("I'll let you on board. Go defeat the monsters rampaging my ship!"):
        sm.warp(4000033, 0)
    else:
        sm.setSpeakerID(10305)
        sm.sendNext("Let me know when you're ready to fight those monsters. They're ruining everything!")
elif sm.hasQuestCompleted(32214):
    sm.setSpeakerID(10305)
    if sm.sendAskYesNo("Thanks to you, we're ready to set sail. You ready to board?"):
        sm.warp(4000032, 0)
    else:
        sm.setSpeakerID(10305)
        sm.sendNext("Let me know when you're ready to board.")
else:
    sm.setSpeakerID(10305)
    sm.sendNext("The ship isn't ready to set sail yet.")


