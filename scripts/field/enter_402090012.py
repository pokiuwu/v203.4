# Created by MechAviv
# Map ID :: 402090012
# Unknown : Unknown

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, False, False, False)
sm.removeAdditionalEffect()
sm.setStandAloneMode(True)
sm.spawnNpc(3001512, -700, 20)
sm.showNpcSpecialActionByTemplateId(3001512, "summon", 0)
sm.spawnNpc(3001513, -640, 20)
sm.showNpcSpecialActionByTemplateId(3001513, "summon", 0)
sm.spawnNpc(3001510, -470, 20)
sm.showNpcSpecialActionByTemplateId(3001510, "summon", 0)
sm.spawnNpc(3001509, -800, 20)
sm.showNpcSpecialActionByTemplateId(3001509, "summon", 0)
sm.moveNpcByTemplateId(3001510, False, 1500, 200)
sm.moveNpcByTemplateId(3001509, False, 1500, 200)
sm.moveNpcByTemplateId(3001512, False, 1500, 200)
sm.moveNpcByTemplateId(3001513, False, 1500, 200)
sm.forcedMove(False, 1500)
sm.blind(1, 150, 0, 0, 0, 1300, 0)
sm.sendDelay(1000)


sm.sayMonologue("My memory keeps getting clearer.", False)
sm.playExclSoundWithDownBGM("Voice4.img/Ark/Mono/D/Male/1")

sm.sayMonologue("And as it does, all of my old feelings come rushing back.", False)
sm.playExclSoundWithDownBGM("Voice4.img/Ark/Mono/D/Male/2")

sm.sayMonologue("\r\nEverything I fought for was a lie.", False)
sm.playExclSoundWithDownBGM("Voice4.img/Ark/Mono/D/Male/3")

sm.sayMonologue("Was the cruelty ever going to end?", False)
sm.playExclSoundWithDownBGM("Voice4.img/Ark/Mono/D/Male/4")

sm.sayMonologue("Nothing I did seemed to make any difference.", False)
sm.playExclSoundWithDownBGM("Voice4.img/Ark/Mono/D/Male/5")

sm.sayMonologue("\r\n\r\n\r\nI had committed unforgiveable acts in the name of peace...", True)
sm.playExclSoundWithDownBGM("Voice4.img/Ark/Mono/D/Male/6")

sm.blind(0, 0, 0, 0, 0, 1300, 0)
sm.sendDelay(5000)


sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)


sm.moveCamera(True, 0, 0, 0)


sm.sendDelay(300)


sm.removeOverlapScreen(1000)
sm.zoomCamera(0, 2000, 0, 600, 120)


sm.sendDelay(600)


sm.forcedMove(True, 30)
sm.sendDelay(1000)


sm.zoomCamera(1000, 2000, 1000, 800, 120)


sm.flipNpcByTemplateId(3001510, True)
sm.flipNpcByTemplateId(3001510, False)
sm.moveNpcByTemplateId(3001510, True, 30, 200)
sm.sendDelay(600)


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
sm.sendNext("#face0#Ark, you're still not well. Do you need a break?")


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
sm.sendSay("#face1#No, I'll keep up.")


sm.zoomCamera(1000, 2000, 1000, 900, 120)


sm.forcedMove(False, 130)
sm.sendDelay(1000)


sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)


sm.moveCamera(True, 0, 0, 0)


sm.sendDelay(300)


sm.removeOverlapScreen(1000)
sm.moveCamera(True, 0, 0, 0)


sm.setStandAloneMode(False)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.removeNpc(3001512)
sm.removeNpc(3001513)
sm.removeNpc(3001510)
sm.removeNpc(3001509)
sm.warp(402000648, 0)
