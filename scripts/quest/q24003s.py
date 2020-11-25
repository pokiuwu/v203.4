# Created by MechAviv
# Quest ID :: 24003
# Peaceful Music

sm.setSpeakerID(1033206)
if sm.sendAskAccept("(Activate the Music Box to play a gentle melody.)"):
    sm.startQuest(24003)
    sm.completeQuest(24003)
    sm.setSpeakerID(1033206)
    sm.sendNext("(Serene music fills the town. May your people find peace in their dreams...)")
    # Unhandled User Effect [PlaySoundWithMuteBGM] Packet: 23 0F 00 47 61 6D 65 2F 51 75 65 65 6E 4F 66 45 6C 66


    sm.progressMessageFont(3, 20, 20, 0, "You can complete the quest by clicking the NPC with a book over their head.")
else:
    sm.setSpeakerID(1033206)
    sm.sendNext("(......)")


