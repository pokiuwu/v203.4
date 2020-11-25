# Beginner Adventurer
# Created by MechAviv
# Quest ID :: 29900
# Not coded yet

medal = 1142107

if sm.canHold(medal):
    sm.systemMessage("You have earned the <Beginner Adventurer> title.")
    sm.startQuest(parentID)
    sm.completeQuestNoRewards(parentID, True)