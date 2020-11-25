# Created by MechAviv
# Map ID :: 402090003
# Refuge Outskirts : Destroyed Refuge

# Update Quest Record EX | Quest ID: [34997] | Data: 27=h1
# Update Quest Record EX | Quest ID: [34997] | Data: 27=h1;28=h1
sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, False, False, False)
sm.setStandAloneMode(True)
sm.removeAdditionalEffect()
sm.spawnNpc(3001527, 500, 73)
sm.showNpcSpecialActionByTemplateId(3001527, "summon", 0)
sm.spawnNpc(3001528, 460, 73)
sm.showNpcSpecialActionByTemplateId(3001528, "summon", 0)
sm.spawnNpc(3001527, 400, 73)
sm.showNpcSpecialActionByTemplateId(3001527, "summon", 0)
sm.spawnNpc(3001528, 350, 73)
sm.showNpcSpecialActionByTemplateId(3001528, "summon", 0)
sm.spawnNpc(3001527, 220, 73)
sm.showNpcSpecialActionByTemplateId(3001527, "summon", 0)
sm.spawnNpc(3001528, 80, 73)
sm.showNpcSpecialActionByTemplateId(3001528, "summon", 0)
sm.spawnNpc(3001527, 40, 73)
sm.showNpcSpecialActionByTemplateId(3001527, "summon", 0)
sm.spawnNpc(3001528, 0, 73)
sm.showNpcSpecialActionByTemplateId(3001528, "summon", 0)
sm.spawnNpc(3001527, 730, 78)
sm.showNpcSpecialActionByTemplateId(3001527, "summon", 0)
sm.spawnNpc(3001528, 800, 78)
sm.showNpcSpecialActionByTemplateId(3001528, "summon", 0)
sm.spawnNpc(3001527, 870, 78)
sm.showNpcSpecialActionByTemplateId(3001527, "summon", 0)
sm.spawnNpc(3001528, 940, 78)
sm.showNpcSpecialActionByTemplateId(3001528, "summon", 0)
sm.spawnNpc(3001527, 1010, 78)
sm.showNpcSpecialActionByTemplateId(3001527, "summon", 0)
sm.spawnNpc(3001528, 1080, 78)
sm.showNpcSpecialActionByTemplateId(3001528, "summon", 0)
sm.spawnNpc(3001527, 1140, 78)
sm.showNpcSpecialActionByTemplateId(3001527, "summon", 0)
sm.spawnNpc(3001528, 1280, 78)
sm.showNpcSpecialActionByTemplateId(3001528, "summon", 0)
sm.zoomCamera(0, 2000, 0, 598, 150)


sm.forcedAction(4, 15000)
sm.localEmotion(1, 15000, False)
sm.sendDelay(500)


# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
sm.zoomCamera(500, 2250, 500, 598, 150)


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
sm.sendNext("#face5#Ugh... What's wrong with my arm?!")


sm.sendDelay(1500)


sm.zoomCamera(500, 2500, 500, 598, 160)


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
sm.sendNext("#face6#I can't stop it! What is this?!")


sm.sendDelay(1500)


sm.zoomCamera(500, 3000, 500, 598, 165)


sm.blind(1, 255, 0, 0, 0, 5000, 0)
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
sm.sendNext("#face6#No... NO! Aaaaaagh!")


sm.zoomCamera(5000, 1000, 5000, 598, 100)


sm.sendDelay(300)


sm.showNpcSpecialActionByTemplateId(3001527, "die", 0)
sm.showNpcSpecialActionByTemplateId(3001528, "die", 0)
sm.showNpcSpecialActionByTemplateId(3001527, "die", 0)
sm.showNpcSpecialActionByTemplateId(3001528, "die", 0)
sm.showNpcSpecialActionByTemplateId(3001527, "die", 0)
sm.showNpcSpecialActionByTemplateId(3001528, "die", 0)
sm.showNpcSpecialActionByTemplateId(3001527, "die", 0)
sm.showNpcSpecialActionByTemplateId(3001528, "die", 0)
sm.showNpcSpecialActionByTemplateId(3001527, "die", 0)
sm.showNpcSpecialActionByTemplateId(3001528, "die", 0)
sm.showNpcSpecialActionByTemplateId(3001527, "die", 0)
sm.showNpcSpecialActionByTemplateId(3001528, "die", 0)
sm.showNpcSpecialActionByTemplateId(3001527, "die", 0)
sm.showNpcSpecialActionByTemplateId(3001528, "die", 0)
sm.showNpcSpecialActionByTemplateId(3001527, "die", 0)
sm.showNpcSpecialActionByTemplateId(3001528, "die", 0)
sm.sendDelay(540)


sm.removeNpc(3001527)
sm.removeNpc(3001528)
sm.removeNpc(3001527)
sm.removeNpc(3001528)
sm.removeNpc(3001527)
sm.removeNpc(3001528)
sm.removeNpc(3001527)
sm.removeNpc(3001528)
sm.removeNpc(3001527)
sm.removeNpc(3001528)
sm.removeNpc(3001527)
sm.removeNpc(3001528)
sm.removeNpc(3001527)
sm.removeNpc(3001528)
sm.removeNpc(3001527)
sm.removeNpc(3001528)
sm.playSound("Sound/Voice4.img/Ark/Back/F/Male/12", 100)
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
sm.sendNext("#face7#Ahhh!!")


sm.sendDelay(3500)


sm.sayMonologue("#fnArial##fs22#The more enemies I destroyed,", False)
sm.playExclSoundWithDownBGM("Voice4.img/Ark/Mono/B/Male/2")

sm.sayMonologue("#fnArial##fs22#the more the darkness consumed me.", False)
sm.playExclSoundWithDownBGM("Voice4.img/Ark/Mono/B/Male/3")

sm.sayMonologue("#fnArial##fs22#\r\n\r\n\r\nIt hadn't always been a part of me... Had it?", False)
sm.playExclSoundWithDownBGM("Voice4.img/Ark/Mono/B/Male/4")

sm.sayMonologue("#fnArial##fs22#\r\nIt was such a strange feeling...", True)
sm.playExclSoundWithDownBGM("Voice4.img/Ark/Mono/B/Male/5")

sm.sayMonologue("#fnArial##fs22#It was taking full control of my body.", False)
sm.playExclSoundWithDownBGM("Voice4.img/Ark/Mono/B/Male/6")

sm.sayMonologue("#fnArial##fs22#\r\nIt was terrifying. Yet...", False)
sm.playExclSoundWithDownBGM("Voice4.img/Ark/Mono/B/Male/7")

sm.sayMonologue("#fnArial##fs22#I couldn't resist such intoxicating power.", False)
sm.playExclSoundWithDownBGM("Voice4.img/Ark/Mono/B/Male/8")


sm.sayMonologue("#fnArial##fs22#\r\n\r\nI was torn between a desire to protect the innocent and a thirst for carnage.", False)
sm.playExclSoundWithDownBGM("Voice4.img/Ark/Mono/B/Male/9")

sm.sayMonologue("#fnArial##fs22#As the two warred within me, all I could do was cry out.", True)
sm.playExclSoundWithDownBGM("Voice4.img/Ark/Mono/B/Male/10")


# Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 7E 00 00 00 FF 00 00 00 00
sm.OnOffLayer_On(500, "999", 0, -5, 0, "Effect/Direction17.img/effect/ark/ArkGaugeCooltime/800", 4, 1, -1, 0)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs65#Aaaahhh#fn바탕##fn나눔고딕 ExtraBold#!!", 50, 5850, 4, 0, 0, 0, 4, 300, 3, 300)
sm.playExclSoundWithDownBGM("Voice4.img/Ark/Mono/B/Male/11")
sm.sendDelay(6350)


# Update Quest Record EX | Quest ID: [34997] | Data: 27=h0;28=h1
# Update Quest Record EX | Quest ID: [34997] | Data: 27=h0;28=h0
sm.OnOffLayer_Off(300, "999", 0)
sm.spawnNpc(3001516, -1363, 92)
sm.showNpcSpecialActionByTemplateId(3001516, "summon", 0)
sm.spawnNpc(3001515, -1419, 92)
sm.showNpcSpecialActionByTemplateId(3001515, "summon", 0)
sm.spawnNpc(3001520, -1033, 96)
sm.showNpcSpecialActionByTemplateId(3001520, "summon", 0)
sm.spawnNpc(3001519, -1104, 96)
sm.showNpcSpecialActionByTemplateId(3001519, "summon", 0)
sm.spawnNpc(3001517, -968, 96)
sm.showNpcSpecialActionByTemplateId(3001517, "summon", 0)
sm.spawnNpc(3001518, -1168, 96)
sm.showNpcSpecialActionByTemplateId(3001518, "summon", 0)
sm.spawnNpc(3001512, -755, 90)
sm.showNpcSpecialActionByTemplateId(3001512, "summon", 0)
sm.spawnNpc(3001513, -822, 90)
sm.showNpcSpecialActionByTemplateId(3001513, "summon", 0)
sm.spawnNpc(3001510, -612, -289)
sm.showNpcSpecialActionByTemplateId(3001510, "summon", 0)
sm.spawnNpc(3001514, 225, -265)
sm.showNpcSpecialActionByTemplateId(3001514, "summon", 0)
sm.spawnNpc(3001521, 513, 100)
sm.showNpcSpecialActionByTemplateId(3001521, "summon", 0)
sm.spawnNpc(3001522, 639, 90)
sm.showNpcSpecialActionByTemplateId(3001522, "summon", 0)
sm.spawnNpc(3001509, 997, -122)
sm.showNpcSpecialActionByTemplateId(3001509, "summon", 0)
sm.teleportInField(-1780, 90)
sm.spawnNpc(3001508, -1630, 60)
sm.showNpcSpecialActionByTemplateId(3001508, "summon", 0)
sm.setBGMVolume(0, 2000)
sm.sendDelay(2500)


sm.setBGMVolume(80, 300)
sm.spineScreen(False, False, True, 0, "Effect/Direction17.img/effect/ark/dust/0/ark_dust", "01_dust A", "01")
sm.playSound("Sound/SoundEff.img/ark/wind", 100)
sm.sendDelay(1000)


sm.playSound("Sound/SoundEff.img/ark/wind", 100)
sm.changeBGM("Bgm48.img/VerdelTown", 0, 0)
sm.zoomCamera(0, 1000, 0, -1257, 60)


sm.blind(1, 255, 0, 0, 0, 0, 0)
sm.sendDelay(1200)


sm.blind(0, 0, 0, 0, 0, 1000, 0)
sm.sendDelay(1400)


sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs18#Several Days Later, Caravan Refuge", 100, 2200, 6, -50, -50, 1, 4, 0, 0, 0)
sm.sendDelay(1000)

sm.moveNpcByTemplateId(3001508, False, 1346, 220)
sm.sendDelay(500)


sm.forcedMove(False, 1165)
sm.zoomCamera(7000, 1000, 7000, -430, 60)


sm.speechBalloon(True, 0, 0, "We don't have enough to eat...", 1000, 0, 0, 0, 0, 4, 3001515)
sm.sendDelay(3000)


sm.sendDelay(500)
sm.speechBalloon(True, 0, 0, "We need to focus on repairing structures...", 1000, 0, 0, 0, 0, 4, 3001516)


sm.sendDelay(2500)
sm.speechBalloon(True, 0, 0, "Thank you, Ark!", 1000, 0, 0, 0, 0, 4, 3001512)


sm.setSpeakerID(3001508)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendNext("#face0#Thank you for keeping everyone safe! We've already started rebuilding the refuge.")
sm.speechBalloon(True, 0, 0, "You saved us!", 1000, 0, 0, 0, 0, 4, 3001513)


sm.sendDelay(500)


sm.moveNpcByTemplateId(3001508, True, 40, 230)
sm.sendDelay(1000)


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
sm.sendNext("#face0#(I can't remember anything after I lost consciousness, but somehow I found my way back to the caravan.)")


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
sm.sendSay("#face0#(Mar is safe, but what was all that chaos about?)")


sm.sendDelay(2000)


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
sm.removeNpc(3001516)
sm.removeNpc(3001515)
sm.removeNpc(3001520)
sm.removeNpc(3001519)
sm.removeNpc(3001517)
sm.removeNpc(3001518)
sm.removeNpc(3001512)
sm.removeNpc(3001513)
sm.removeNpc(3001510)
sm.removeNpc(3001514)
sm.removeNpc(3001521)
sm.removeNpc(3001522)
sm.removeNpc(3001509)
sm.removeNpc(3001508)
sm.warp(402000600, 3)
