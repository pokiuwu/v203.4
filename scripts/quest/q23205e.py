# Created by MechAviv
# Quest ID :: 23205
# Defeat the Guards!

SKILLS = [30010166, 30011167, 30011168, 30011169, 30011170]
OBJECT_1 = sm.getIntroNpcObjectID(2159309)

sm.completeQuestNoRewards(parentID)
sm.deleteQuest(parentID)
for i in range(5):
    if not sm.hasSkill(SKILLS[i]):
        sm.giveSkill(SKILLS[i], 0)# remove the skill
sm.sendNpcController(OBJECT_1, False)
sm.warpInstanceIn(927000070, 0)