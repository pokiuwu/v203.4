# Created by MechAviv
# ID :: [931050040]
# Unknown : Unknown
SKILLS = [30010166, 30011167, 30011168, 30011169, 30011170]
for i in range(5):
    sm.removeSkill(SKILLS[i])
if chr.getJob() == 3001 and chr.getLevel() >= 10:
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, True, False, False)
    sm.forcedInput(2)
    sm.curNodeEventEnd(True)
