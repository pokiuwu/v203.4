# Created by MechAviv
# ID :: [140010000]
# Snow Island : Dangerous Forest
if sm.hasQuestCompleted(21014) or sm.getChr().getJob() != 2000:
    sm.warp(140010100, 2)
else:
    sm.systemMessage("You must complete the quest before proceeding to the next map.")