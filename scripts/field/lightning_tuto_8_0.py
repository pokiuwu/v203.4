# Created by MechAviv
# ID :: [910141040]
# Hidden Street : Black Mage's Memories

sm.reservedEffect("Effect/Direction8.img/lightningTutorial/Scene2")
sm.playExclSoundWithDownBGM("Voice.img/DarkMage/5", 100)
sm.sendDelay(7200)


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00 
sm.warp(910141000, 0)
