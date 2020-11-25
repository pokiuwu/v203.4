# Created by MechAviv
# ID :: [927020060]
# Hidden Street : Black Mage's Antechamber

sm.avatarOriented("Effect/OnUserEff.img/normalEffect/demonSlayer/chatBalloon0")
sm.reservedEffect("Effect/Direction8.img/lightningTutorial2/Scene2")
sm.sendDelay(1000)


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("The air feels like black sludge in my lungs. The Black Mage is close...")


sm.sendDelay(1000)


sm.curNodeEventEnd(True)
# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00 
# [FORCED_STAT_RESET] []
sm.warp(927020090, 0)
