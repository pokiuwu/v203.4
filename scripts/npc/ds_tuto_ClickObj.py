# Created by MechAviv
# [Shiny Pendant]  |  [2159330]
# Hidden Street : Demon Slayer's Childhood Home

if sm.hasQuest(23200) and not sm.hasQuestCompleted(23200):
    sm.setSpeakerID(0)
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendNext("#bMother! Where are you?!#k")

    sm.startQuest(23201)
    sm.completeQuest(23200)
elif sm.hasQuest(23201) and not sm.hasQuestCompleted(23201):
    sm.setSpeakerID(0)
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendNext("#bDamien! Answer me!#k")

    sm.startQuest(23202)
    sm.completeQuest(23201)
elif sm.hasQuest(23202) and not sm.hasQuestCompleted(23202):
    sm.completeQuest(23202)
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, True, False, False)
    sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/4", 1000, 0, -100, -2, -2, False, 0)
    sm.sendDelay(1000)

    sm.setSpeakerID(2159307)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendNext("#bThis is...#k")

    sm.showFieldEffect("demonSlayer/pendant", 0)
    sm.sendDelay(4200)

    sm.setSpeakerID(2159307)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendNext("#bMother... Damien...#k")

    sm.setSpeakerID(2159307)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendSay("#b...#k")

    sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/5", 2000, 0, -100, -2, -2, False, 0)
    sm.sendDelay(2000)

    sm.showEffect("Effect/Direction6.img/effect/tuto/balloonMsg1/6", 2000, 0, -150, -2, -2, False, 0)
    sm.sendDelay(2000)

    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)
    sm.warp(927000081, 0)
