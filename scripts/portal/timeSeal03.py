
if not sm.hasQuest(25673):
    sm.showFieldEffect("lightning/screenMsg/6")
    sm.createQuestWithQRValue(25673, "1")
    sm.spawnMob(9300535, 0, -80, False)
    OBJECT_1 = sm.sendNpcController(2159367, 0, -80)
    sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
    sm.setSpeakerID(0)
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(8)
    sm.sendSayOkay("The final seal is below the central staircase. I'm almost done.")