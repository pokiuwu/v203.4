# Created by MechAviv
# ID :: [914000000]
# Black Road : Wounded Soldier's Camp
if not "o" in sm.getQuestEx(21002, "mo1"):
    sm.avatarOriented("Effect/OnUserEff.img/guideEffect/aranTutorial/legendBalloon1")
    sm.setQuestEx(21002, "mo1", "o")