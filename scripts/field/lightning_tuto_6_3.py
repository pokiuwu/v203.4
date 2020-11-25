# Created by MechAviv
# ID :: [927020072]
# Unknown : Unknown

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.forcedInput(0)
OBJECT_3 = sm.sendNpcController(2159357, 300, -80)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
sm.setSpeakerID(2159357)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("#b(What happened?)#k")


sm.setSpeakerID(2159357)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("#b(I activated all 5 seals.)#k")


sm.setSpeakerID(2159357)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("#b(Now we have to force the Black Mage's hand. The only way to get him to use his full power is if you use your Light magic against him.)#k")


sm.moveCamera(False, 300, 0, -500)


sm.sendDelay(1456)


sm.showFieldEffect("lightning/screenMsg/3", 0)
sm.playExclSoundWithDownBGM("Voice.img/DarkMage/1", 100)
sm.sendDelay(4000)


sm.moveCamera(True, 0, 0, 0)


sm.sendDelay(0)


sm.forcedInput(1)
sm.sendDelay(1000)


sm.forcedInput(0)
sm.showEffect("Effect/Direction8.img/effect/tuto/BalloonMsg0/8", 0, 0, -100, -2, -2, False, 0)
sm.sendDelay(2300)


sm.showEffect("Effect/Direction8.img/effect/tuto/BalloonMsg0/9", 0, 0, -100, -2, -2, False, 0)
sm.sendDelay(1500)


sm.moveCamera(False, 300, 0, -500)


sm.sendDelay(2437)


sm.playExclSoundWithDownBGM("Voice.img/DarkMage/4", 100)
sm.showFieldEffect("demonSlayer/whiteOut", 0)
sm.sendDelay(5000)


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
sm.sendNpcController(OBJECT_3, False)
# [FORCED_STAT_RESET] []
sm.warp(927020100, 0)