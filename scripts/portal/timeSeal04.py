# Created by MechAviv
# ID :: [927020071]
# Unknown : Unknown

OBJECT_1 = sm.getIntroNpcObjectID(2159367)
OBJECT_4 = sm.getIntroNpcObjectID(2159363)
OBJECT_5 = sm.getIntroNpcObjectID(2159364)
OBJECT_6 = sm.getIntroNpcObjectID(2159365)
OBJECT_7 = sm.getIntroNpcObjectID(2159366)

if not sm.hasQuest(25674) and sm.hasQuest(25670) and sm.hasQuest(25671) and sm.hasQuest(25672) and sm.hasQuest(25673):
    sm.createQuestWithQRValue(25674, "1")
    sm.showFieldEffect("lightning/screenMsg/7", 0)

    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendNext("All the seals should be active now.")

    sm.setFuncKeyByScript(False, 20041222, 0)
    sm.sendNpcController(OBJECT_4, False)
    sm.sendNpcController(OBJECT_5, False)
    sm.sendNpcController(OBJECT_6, False)
    sm.sendNpcController(OBJECT_7, False)
    sm.sendNpcController(OBJECT_1, False)
    sm.killMobs()
    # [FORCED_STAT_RESET] []
    sm.warp(927020072, 0)