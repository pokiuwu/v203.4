# Created by MechAviv
# ID :: [140010000]
# Snow Island : Dangerous Forest
if not "o" in sm.getQuestEx(21019, "arr"):
    sm.avatarOriented("Effect/OnUserEff.img/guideEffect/aranTutorial/tutorialArrow3")
    sm.setQuestEx(21019, "arr", "o")