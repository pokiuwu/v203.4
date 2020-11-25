# Created by MechAviv
# Map ID :: 402090004
# Sandstorm Zone : Sandstorm Zone Entrance

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, False, False, False)
sm.setStandAloneMode(True)
sm.removeAdditionalEffect()
sm.blind(1, 255, 0, 0, 0, 0, 0)
sm.zoomCamera(0, 1000, 0, -1500, -200)


sm.spawnNpc(3001509, -1838, 250)
sm.showNpcSpecialActionByTemplateId(3001509, "summon", 0)
sm.spawnNpc(3001512, -1960, 250)
sm.showNpcSpecialActionByTemplateId(3001512, "summon", 0)
sm.spawnNpc(3001513, -1900, 250)
sm.showNpcSpecialActionByTemplateId(3001513, "summon", 0)
sm.spawnNpc(3001510, -1260, 250)
sm.showNpcSpecialActionByTemplateId(3001510, "summon", 0)
sm.blind(1, 255, 0, 0, 0, 0, 0)
sm.sendDelay(1200)


sm.blind(0, 0, 0, 0, 0, 1000, 0)
sm.sendDelay(1400)


sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs18#A While Later, Far from the Caravan Refuge", 100, 1000, 6, -50, -50, 1, 4, 0, 0, 0)
sm.sendDelay(500)

sm.zoomCamera(4000, 1000, 4000, -1500, 250)


sm.sendDelay(4000)


sm.zoomCamera(3000, 1000, 3000, -1150, 250)


sm.forcedFlip(False)
sm.moveNpcByTemplateId(3001509, False, 350, 200)
sm.moveNpcByTemplateId(3001512, False, 350, 200)
sm.moveNpcByTemplateId(3001513, False, 350, 200)
sm.moveNpcByTemplateId(3001510, False, 350, 200)
sm.sendDelay(500)


sm.forcedMove(False, 350)
sm.sendDelay(2500)


sm.setSpeakerID(3001512)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendNext("#face0#The wind is strange. Are you sure we're headed the right direction?")


sm.sendDelay(500)


sm.playSound("Sound/SoundEff.img/ark/wind", 100)
sm.spineScreen(False, False, True, 0, "Effect/Direction17.img/effect/ark/dust/0/ark_dust", "01_dust A", "01")
sm.sendDelay(2500)


sm.showEffect("Effect/OnUserEff.img/emotion/oh", 0, 0, 0, 0, 3001509, False, 0)
sm.showEffect("Effect/OnUserEff.img/emotion/oh", 0, 0, 0, 0, 3001513, False, 0)
sm.showEffect("Effect/OnUserEff.img/emotion/oh", 0, 0, 0, 0, 3001512, False, 0)
sm.showEffect("Effect/OnUserEff.img/emotion/oh", 0, 0, 0, 0, 3001510, False, 0)
sm.showEffect("Effect/OnUserEff.img/emotion/oh", 0, 0, 0, 0, -2, False, 0)
sm.sendDelay(1000)


sm.playSound("Sound/SoundEff.img/ark/wind", 100)
sm.spineScreen(False, True, True, 0, "Effect/Direction17.img/effect/ark/dust/0/ark_dust", "02_dust B", "02")
sm.moveNpcByTemplateId(3001509, False, 150, 120)
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
sm.sendNext("#face2#How can we be stuck in a sandstorm?! Ferret, I'm really glad you're so good at this, but... are your signals still working?")


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
sm.sendSay("#face0#...")


sm.zoomCamera(2000, 1000, 2000, -850, 250)


sm.moveNpcByTemplateId(3001512, False, 150, 120)
sm.moveNpcByTemplateId(3001513, False, 150, 120)
sm.playSound("Sound/SoundEff.img/ark/wind", 100)
sm.blind(0, 0, 0, 0, 0, 2000, 0)
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
sm.sendNext("#face0#This is bad.")


sm.zoomCamera(1000, 1500, 1000, -850, 300)


sm.playSound("Sound/SoundEff.img/ark/wind", 100)
sm.blind(0, 0, 0, 0, 0, 1000, 0)
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
sm.sendNext("#face2#The sandstorm is messing with my devices.")


sm.zoomCamera(1000, 2000, 1000, -850, 320)


sm.playSound("Sound/SoundEff.img/ark/wind", 100)
sm.blind(0, 0, 0, 0, 0, 1000, 0)
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
sm.sendNext("#face3#I can't get any kind of signal.")


sm.spawnNpc(3001524, -750, 233)
sm.showNpcSpecialActionByTemplateId(3001524, "summon", 0)
sm.spawnNpc(3001524, -700, 233)
sm.showNpcSpecialActionByTemplateId(3001524, "summon", 0)
sm.spawnNpc(3001524, -650, 233)
sm.showNpcSpecialActionByTemplateId(3001524, "summon", 0)
sm.spawnNpc(3001524, -600, 233)
sm.showNpcSpecialActionByTemplateId(3001524, "summon", 0)
sm.spawnNpc(3001524, -560, 233)
sm.showNpcSpecialActionByTemplateId(3001524, "summon", 0)
sm.spawnNpc(3001524, -500, 233)
sm.showNpcSpecialActionByTemplateId(3001524, "summon", 0)
sm.spawnNpc(3001524, -420, 233)
sm.showNpcSpecialActionByTemplateId(3001524, "summon", 0)
sm.spawnNpc(3001524, -300, 233)
sm.showNpcSpecialActionByTemplateId(3001524, "summon", 0)
sm.showNpcSpecialActionByTemplateId(3001524, "attack", -1)
sm.showNpcSpecialActionByTemplateId(3001524, "attack", -1)
sm.showNpcSpecialActionByTemplateId(3001524, "attack", -1)
sm.showNpcSpecialActionByTemplateId(3001524, "attack", -1)
sm.showNpcSpecialActionByTemplateId(3001524, "attack", -1)
sm.showNpcSpecialActionByTemplateId(3001524, "attack", -1)
sm.playSound("Sound/SoundEff.img/ark/wind", 100)
sm.sendDelay(500)


sm.playSound("Sound/SoundEff.img/ark/wind", 100)
sm.forcedMove(False, 150)
sm.zoomCamera(2000, 1000, 2000, -1000, 250)


sm.setSpeakerID(3001500)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendNext("#face4#That's definitely not good for us.")


sm.sendDelay(2500)


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
sm.removeNpc(3001524)
sm.removeNpc(3001524)
sm.removeNpc(3001524)
sm.removeNpc(3001524)
sm.removeNpc(3001524)
sm.removeNpc(3001524)
sm.removeNpc(3001524)
sm.removeNpc(3001524)
sm.warp(402000621, 0)
