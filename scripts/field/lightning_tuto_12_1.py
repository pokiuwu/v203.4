# Created by MechAviv
# ID :: [910141020]
# Hidden Street : Lania's Front Yard
OBJECT_1 = sm.getIntroNpcObjectID(1032201)

sm.forcedInput(2)
sm.sendDelay(30)


sm.forcedInput(0)
sm.setSpeakerID(1032201)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Don't take too long, okay? I know how you like to dilly-dally in town!")


sm.setSpeakerID(1032201)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I will return as swiftly as I can, dear Lania.")


sm.setSpeakerID(1032201)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("Lania, since I've started living here, for the first time in my life I'm--ARGH!")


sm.setSpeakerID(1032201)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Luminous?")


sm.forcedAction(4, 6000)
sm.showEffect("Effect/Direction8.img/effect/tuto/floodEffect/0", 5400, 0, 0, -5, -2, False, 0)
sm.showEffect("Effect/Direction8.img/effect/tuto/BalloonMsg1/1", 1400, 0, -120, -2, -2, False, 0)
sm.moveNpcByObjectId(OBJECT_1, True, 50, 100)
sm.reservedEffect("Effect/Direction8.img/lightningTutorial2/Scene2")
# Unhandled User Effect [PlaySoundWithMuteBGM] Packet: 23 0F 00 42 67 6D 32 36 2E 69 6D 67 2F 46 6C 6F 6F 64
sm.sendDelay(500)

sm.showEffect("Effect/Direction8.img/effect/tuto/BalloonMsg1/2", 0, 0, -120, 0, OBJECT_1, False, 0)
sm.sendDelay(2000)


sm.showEffect("Effect/Direction8.img/effect/tuto/BalloonMsg1/3", 0, 0, -180, -2, -2, False, 0)
sm.sendDelay(2300)


sm.faceOff(21066)
sm.showEffect("Effect/Direction8.img/effect/tuto/floodEffect/1", 0, 0, 0, -5, -2, False, 0)
sm.showEffect("Effect/Direction8.img/effect/tuto/floodEffect/2", 0, 0, 0, -6, -2, False, 0)
sm.sendDelay(3000)


sm.curNodeEventEnd(True)
# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00 
sm.sendNpcController(OBJECT_1, False)
sm.warp(910141060, 0)
