# Created by MechAviv
# ID :: [140090000]
# Snow Island : Ice Cave

sm.removeSkill(20000014)
sm.removeSkill(20000015)
sm.removeSkill(20000016)
sm.removeSkill(20000017)
sm.removeSkill(20000018)

if not sm.hasSkill(20001295):
    sm.lockInGameUI(False, False)
    sm.giveSkill(20001295)
    sm.chatScript("You learned the Combat Step skill.")

sm.setTemporarySkillSet(0)
sm.setDirectionMode(False, 0)