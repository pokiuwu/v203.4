# Created by MechAviv
# ID :: [914000000]
# Black Road : Wounded Soldier's Camp
if not "o" in sm.getQuestEx(21002, "mo3"):
    sm.avatarOriented("Effect/OnUserEff.img/guideEffect/aranTutorial/legendBalloon3")
    sm.setQuestEx(21002, "mo3", "o")