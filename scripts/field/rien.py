# Created by MechAviv
# ID :: [140000000]
# Snow Island : Rien
sm.setTemporarySkillSet(0)
sm.lockInGameUI(False, False)
if not "ck=1" in sm.getQRValue(21019) and sm.hasQuestCompleted(21101):
    sm.addQRValue(21019, "ck=1")