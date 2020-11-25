# 914000100
if not "1" in sm.getQRValue(21002):
    sm.systemMessage("You can only exit after you accept the quest from Athena Pierce, who is to your right.")
else:
    sm.removeSkill(20000017)
    sm.removeSkill(20000018)
    sm.giveSkill(20000017)
    sm.giveSkill(20000018)
    sm.warp(914000200, 1)