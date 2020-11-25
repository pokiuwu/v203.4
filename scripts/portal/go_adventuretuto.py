# Created by MechAviv

if sm.hasQuestCompleted(32203) and sm.getFieldID() == 4000012:
    # Maple Road : Snail Park
    sm.warp(4000013, 1)
elif sm.hasQuestCompleted(32207) and sm.getFieldID() == 4000013:
    # Maple Road : Inside the Small Forest
    sm.warp(4000014, 1)
elif sm.hasQuest(32210) and sm.getFieldID() == 4000014:
    sm.warp(4000020, 1)
else:
    sm.setSpeakerID(0)
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendNext("I should complete Mai's quest first.")