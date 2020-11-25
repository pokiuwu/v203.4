# Created by MechAviv
# ID :: [927000020]
# Hidden Street : Black Mage's Main Corridor 1
ARKARIUM = 2159309

SKILLS = [30010166, 30011167, 30011168, 30011169, 30011170]

OBJECT_1 = sm.sendNpcController(2159309, 550, 50)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.sendSessionValue("arkyrim0", 2159309)
sm.completeQuestNoRewards(23205)
sm.deleteQuest(23205)
sm.startQuestNoCheck(23204)
for i in range(5):
    if not sm.hasSkill(SKILLS[i]):
        sm.giveSkill(SKILLS[i])