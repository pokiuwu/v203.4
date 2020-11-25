# Created by MechAviv
# Map ID :: 402090005
# Desert Cavern : Below the Sinkhole

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, False, False, False)
sm.removeAdditionalEffect()
sm.setStandAloneMode(True)
sm.blind(1, 255, 0, 0, 0, 0, 0)
sm.setMapTaggedObjectVisible("back0", False, 0, 0)
sm.setMapTaggedObjectVisible("back1", False, 0, 0)
sm.teleportInField(-329, 220)
sm.forcedFlip(False)
sm.zoomCamera(0, 1000, 0, -10, 250)


sm.sendDelay(1000)


sm.spawnNpc(3001509, -150, 250)
sm.showNpcSpecialActionByTemplateId(3001509, "summon", 0)
sm.spawnNpc(3001512, 0, 250)
sm.showNpcSpecialActionByTemplateId(3001512, "summon", 0)
sm.spawnNpc(3001513, -60, 250)
sm.showNpcSpecialActionByTemplateId(3001513, "summon", 0)
sm.spawnNpc(3001510, 160, 250)
sm.showNpcSpecialActionByTemplateId(3001510, "summon", 0)
sm.moveNpcByTemplateId(3001510, False, 330, 180)
sm.moveNpcByTemplateId(3001509, False, 330, 180)
sm.moveNpcByTemplateId(3001512, False, 330, 180)
sm.moveNpcByTemplateId(3001513, False, 330, 180)
sm.blind(0, 0, 0, 0, 0, 1000, 0)
sm.forcedMove(False, 420)
sm.zoomCamera(3000, 1000, 3000, 300, 250)


sm.sendDelay(3000)


sm.setSpeakerID(3001510)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendNext("#face4#Ha! The signal's coming from directly below us.")


sm.setSpeakerID(3001509)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face2#Time for me to do my thing!")


sm.moveNpcByTemplateId(3001509, False, 450, 150)
sm.sendDelay(1000)


sm.zoomCamera(3000, 1000, 3000, 800, 250)


sm.sendDelay(4000)


sm.sendDelay(1000)


sm.showEffect("Effect/Direction19.img/effect/cadena/3", 3000, 671, 152, 3, -2, False, 0)
sm.playSound("Sound/SoundEff.img/blackHeaven/explosion1", 100)
sm.showEffect("Effect/Direction19.img/effect/cadena/3", 6000, 621, 112, 3, -2, False, 0)
sm.playSound("Sound/SoundEff.img/blackHeaven/explosion2", 100)
sm.showEffect("Effect/Direction19.img/effect/cadena/3", 0, 821, 62, 3, -2, False, 0)
sm.sendDelay(1000)


sm.showEffect("Effect/Direction19.img/effect/cadena/3", 0, 321, 112, 3, -2, False, 0)
sm.showEffect("Effect/Direction19.img/effect/cadena/3", 0, 1121, -88, 3, -2, False, 0)
sm.playSound("Sound/SoundEff.img/blackHeaven/explosion0", 100)
sm.sendDelay(1000)


sm.showEffect("Effect/Direction19.img/effect/cadena/3", 6000, 1021, 62, 3, -2, False, 0)
sm.showEffect("Effect/Direction19.img/effect/cadena/3", 0, 481, 162, 3, -2, False, 0)
sm.showEffect("Effect/Direction19.img/effect/cadena/3", 0, 841, -38, 3, -2, False, 0)
sm.showEffect("Effect/Direction19.img/effect/cadena/3", 6000, 821, -238, 3, -2, False, 0)
sm.playSound("Sound/SoundEff.img/blackHeaven/explosion1", 100)
sm.setBGMVolume(80, 500)
sm.changeBGM("Bgm45.img/Time Is Gold", 0, 0)
sm.setFieldFloating(402090005, 5, 5, 1)
sm.sendDelay(500)


sm.setSpeakerID(3001510)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendNext("#face2#Salvo! You're going to destroy everything!")


sm.sendDelay(1000)


sm.setFieldFloating(402090005, 0, 0, 0)
sm.showFadeTransition(0, 1000, 3000)
sm.sendDelay(500)


sm.zoomCamera(0, 1000, 0, 800, 250)


sm.removeNpc(3001510)
sm.removeNpc(3001512)
sm.removeNpc(3001513)
sm.teleportInField(829, 220)
sm.spawnNpc(3001512, 660, 220)
sm.showNpcSpecialActionByTemplateId(3001512, "summon", 0)
sm.spawnNpc(3001513, 600, 220)
sm.showNpcSpecialActionByTemplateId(3001513, "summon", 0)
sm.spawnNpc(3001510, 1010, 220)
sm.showNpcSpecialActionByTemplateId(3001510, "summon", 0)
sm.setMapTaggedObjectVisible("back0", True, 0, 0)
sm.setMapTaggedObjectVisible("back1", True, 0, 0)
sm.showEffect("Effect/Direction17.img/effect/ark/hole/back", 999999, 150, -50, -6, 3001509, False, 0)
sm.removeOverlapScreen(1000)
sm.setFieldFloating(402090005, 10, 10, 1)
sm.sendDelay(500)


sm.zoomCamera(0, 1000, 0, 800, 250)


sm.setSpeakerID(3001509)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendNext("#face1#Uh oh! Going down!")


sm.zoomCamera(1000, 2000, 1000, 500, 3250)


# Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 EF 00 00 00 FF 00 00 00 00
sm.sendDelay(500)


sm.blind(1, 255, 0, 0, 0, 500, 0)
sm.sendDelay(500)


sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)


sm.moveCamera(True, 0, 0, 0)


sm.sendDelay(300)


sm.removeOverlapScreen(1000)
sm.moveCamera(True, 0, 0, 0)


sm.setStandAloneMode(False)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
sm.removeNpc(3001509)
sm.removeNpc(3001512)
sm.removeNpc(3001513)
sm.removeNpc(3001510)
sm.warp(402000630, 0)
