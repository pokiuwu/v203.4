# Created by MechAviv
# ID :: [4000011]
# Maple Road : Maple Tree Hill

if sm.hasQuest(32202) or "1" in sm.getQRValue(32217):
    sm.dispose()
else:
    sm.createQuestWithQRValue(32217, "1")
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, True, False, False)
    sm.showFieldEffect("maplemap/enter/10000", 0)
    sm.sendDelay(1000)


    OBJECT_1 = sm.sendNpcController(10300, -240, 220)
    sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
    sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg1/1", 900, 0, -120, 0, OBJECT_1, False, 0)
    sm.sendDelay(1800)


    sm.moveNpcByObjectId(OBJECT_1, False, 1000, 100)
    sm.moveCamera(False, 200, 200, 200)


    sm.sendDelay(4542)


    sm.moveCamera(True, 0, 0, 0)


    sm.sendDelay(0)


    sm.sendDelay(900)


    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendNext("Who was that girl? Why did she run away when she saw me?")


    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendSay("Maybe I'll follow her..")


    sm.sendNpcController(OBJECT_1, False)
    sm.startQuest(32202)
    sm.completeQuest(32202)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)
