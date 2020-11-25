# 914000000
if not "o" in sm.getQuestEx(21002, "mo2"):
    sm.playSound("Aran/balloon")
    sm.avatarOriented("Effect/OnUserEff.img/guideEffect/aranTutorial/legendBalloon2")
    sm.setQuestEx(21002, "mo2", "o")