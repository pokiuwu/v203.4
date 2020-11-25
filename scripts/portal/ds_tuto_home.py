# Created by MechAviv
# ID :: [924020000]
# Hidden Street : Demon Slayer's Childhood Home
STEP =  sm.getQuestEx(23200, "step")
if "0" not in STEP and "1" not in STEP:
    sm.startQuest(23200)
    sm.setQuestEx(23200, "step", "0")
    sm.avatarOriented("Effect/OnUserEff.img/normalEffect/demonSlayer/chatBalloon1")
elif "0" in STEP and "1" not in STEP:
    sm.setQuestEx(23200, "step", "1")
    sm.avatarOriented("Effect/OnUserEff.img/normalEffect/demonSlayer/chatBalloon0")