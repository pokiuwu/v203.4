# Created by MechAviv
# ID :: [910141030]
# Hidden Street : Front Yard Wreckage

MENU_TEXT = ["Light Path", "Dark Path"]

if chr.getJob() == 2004:
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, True, False, False)
    OBJECT_1 = sm.sendNpcController(1032203, 0, 0)
    sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
    sm.sendDelay(1500)


    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.playExclSoundWithDownBGM("Voice.img/Luminous_M/0", 100)
    sm.sendNext("What have I done?")


    sm.moveCamera(False, 300, 0, 27)


    sm.sendDelay(1671)


    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendNext("Lania...")


    sm.moveCamera(False, 300, 500, 27)


    sm.sendDelay(1667)


    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendNext("Our home...")


    sm.moveCamera(True, 0, 0, 0)


    sm.sendDelay(0)


    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendNext("The forest... I destroyed it all.")


    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendSay("The Black Mage has cursed me! His dark power has corrupted my heart!")


    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendSay("But why now? Has the fiend broken free of his prison?!")


    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendSay("What can I do? The power of light is lost to me... ")

    answer = sm.sendAskSelectMenu(0, 0, MENU_TEXT)
    if answer == 0:
        # Light Side
        sm.setSpeakerID(0)
        sm.removeEscapeButton()
        sm.flipDialoguePlayerAsSpeaker()
        sm.setSpeakerType(3)
        sm.playExclSoundWithDownBGM("Voice.img/Luminous_M/2", 100)
        sm.sendNext("I will not be swept away by this darkness. I will save Lania and this world... Even if it means my destruction.")
        sm.forcedInput(2)

    else:
        # Dark Side
        sm.setSpeakerID(0)
        sm.removeEscapeButton()
        sm.flipDialoguePlayerAsSpeaker()
        sm.setSpeakerType(3)
        sm.sendNext("The power of darkness is overtaking me. The primal power of fear and shadow pulses through me! Haha. Hahahahaha!")

        sm.setSpeakerID(0)
        sm.removeEscapeButton()
        sm.flipDialoguePlayerAsSpeaker()
        sm.setSpeakerType(3)
        sm.sendSay("Lania, this is it for us. I am a creature of the darkness now!")

        sm.showEffect("Effect/Direction8.img/effect/tuto/floodEffect/0", 5000, 0, 0, -5, -2, False, 0)
        sm.playExclSoundWithDownBGM("Voice.img/Luminous_M/1", 100)
        sm.sendDelay(2000)

        sm.forcedInput(1)