# Created by MechAviv
# Map ID :: 402000635
# Desert Cavern : Armor Graveyard

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, False, False, False)
sm.setStandAloneMode(True)
sm.removeAdditionalEffect()
sm.blind(1, 255, 0, 0, 0, 0, 0)
sm.forcedFlip(False)
sm.teleportInField(-610, 56)
sm.zoomCamera(2000, 2000, 2000, -600, 120)


sm.sendDelay(2000)


sm.spawnNpc(3001512, -980, 20)
sm.showNpcSpecialActionByTemplateId(3001512, "summon", 0)
sm.spawnNpc(3001513, -900, 20)
sm.showNpcSpecialActionByTemplateId(3001513, "summon", 0)
sm.spawnNpc(3001510, -762, 20)
sm.showNpcSpecialActionByTemplateId(3001510, "summon", 0)
sm.moveNpcByTemplateId(3001510, False, 150, 120)
sm.moveNpcByTemplateId(3001512, False, 150, 120)
sm.moveNpcByTemplateId(3001513, False, 150, 120)
sm.forcedMove(False, 150)
sm.sendDelay(500)


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
sm.sendNext("#face3#Salvo? Where are you?")


sm.blind(0, 0, 0, 0, 0, 500, 0)
sm.zoomCamera(2000, 1000, 2000, -240, -10)


sm.sendDelay(2000)


sm.spawnNpc(3001509, -250, 20)
sm.showNpcSpecialActionByTemplateId(3001509, "summon", 0)
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
sm.sendNext("#face2#I'm right here!")


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
sm.sendSay("#face3#Good! You're safe... What were you thinking?!")


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
sm.sendSay("#face3#Follow us and be quick about it. We've found a way out.")


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
sm.sendSay("#face2#Aw, you do care! Okay!")


# Unhandled Message [47] Packet: 2F 02 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00 CA 22 11 00 00 00 00 00 63 04 00 00 12 02 30 A0 2F 31 8B D6 D4 01 0D 00 66 69 65 6C 64 5F 65 6E 74 65 72 3D 31
sm.flipNpcByTemplateId(3001509, False)
sm.moveNpcByTemplateId(3001510, False, 550, 150)
sm.moveNpcByTemplateId(3001512, False, 550, 150)
sm.moveNpcByTemplateId(3001513, False, 550, 150)
sm.forcedMove(False, 600)
sm.zoomCamera(4000, 1000, 4000, 280, -20)


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
sm.sendNext("#face0#Ark! Where are you going?")


sm.sendDelay(4000)


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
sm.sendNext("#face2#Something about this signal isn't right...")


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
sm.sendSay("#face0#This place is really scary.")


sm.setSpeakerID(3001513)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face0#We need to get out of here!")


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
sm.sendSay("#face7#Something's wrong... what... augh...")


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
sm.sendSay("#face2#All these resonating waves make me want to sing!")


sm.OnOffLayer_On(500, "0", 0, -10, 0, "Effect/Direction17.img/effect/ark/noise/1366/0", 4, 1, -1, 1)
sm.playSound("Sound/SoundEff.img/radionoise", 100)
sm.localEmotion(1, 999999, False)
sm.forcedAction(4, 999999)
sm.sendDelay(300)


sm.OnOffLayer_Off(300, "0", 0)
sm.sendDelay(300)


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
sm.sendNext("#face0#Let's get going.")


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
sm.sendSay("#face7#Augh!")


sm.OnOffLayer_On(500, "0", 0, -10, 0, "Effect/Direction17.img/effect/ark/noise/1366/0", 4, 1, -1, 1)
sm.sendDelay(300)


sm.OnOffLayer_Off(300, "0", 0)
sm.sendDelay(300)


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
sm.sendNext("#face2#Ark doesn't look so good!")


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
sm.sendSay("#face0#Hey, Ark!")


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
sm.sendSay("#face0#Hang on!")


sm.OnOffLayer_On(500, "0", 0, -10, 0, "Effect/Direction17.img/effect/ark/noise/1366/0", 4, 1, -1, 1)
sm.playSound("Sound/SoundEff.img/radionoise", 100)
sm.blind(1, 255, 0, 0, 0, 500, 0)
sm.sendDelay(500)


sm.sendDelay(300)


sm.OnOffLayer_On(500, "2", 0, -10, 0, "Effect/Direction17.img/effect/ark/noise/1366/2", 4, 1, -1, 1)
sm.OnOffLayer_On(500, "3", 0, -10, 0, "Effect/Direction17.img/effect/ark/noise/1366/3", 4, 1, -1, 1)
sm.OnOffLayer_Off(300, "0", 0)
sm.OnOffLayer_On(1000, "d0", 0, -80, -1, "Effect/Direction17.img/effect/ark/illust/2/0", 4, 1, -1, 0)
sm.sendDelay(1000)


sm.playSound("Sound/Voice4.img/Ark/Back/C/Male/1", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#They're no match for us at all.", 50, 4650, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(5150)

sm.sendDelay(1000)


sm.OnOffLayer_Off(1000, "d0", 0)
sm.OnOffLayer_On(1000, "d1", 0, -80, -1, "Effect/Direction17.img/effect/ark/illust/2/1", 4, 1, -1, 0)
sm.sendDelay(1000)


sm.playSound("Sound/Voice4.img/Ark/Back/C/Male/2", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#They call this a war for peace?", 50, 4650, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(5150)

sm.playSound("Sound/Voice4.img/Ark/Back/C/Male/3", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#This is no war... It's a massacre.", 50, 5100, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(5600)

sm.OnOffLayer_Off(500, "d1", 0)
sm.playSound("Sound/Voice4.img/ArkNpc/Back/C/4", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs65#Ark! What are you doing?", 50, 3600, 4, 0, 0, 0, 4, 300, 3, 300)
sm.sendDelay(4100)

sm.OnOffLayer_On(1000, "d2", 0, -80, -1, "Effect/Direction17.img/effect/ark/illust/2/2", 4, 1, -1, 0)
sm.playSound("Sound/Voice4.img/ArkNpc/Back/C/5", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#Are you out of your mind?!", 50, 3900, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(4400)

sm.playSound("Sound/Voice4.img/Ark/Back/C/Male/6", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#Albaire, I can't do this any more.", 50, 5100, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(5600)

sm.playSound("Sound/Voice4.img/Ark/Back/C/Male/7", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#It's not right. It's not right at all.", 50, 5700, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(6200)

sm.playSound("Sound/Voice4.img/ArkNpc/Back/C/8", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#Don't be so naive.", 50, 2700, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(3200)

sm.playSound("Sound/Voice4.img/ArkNpc/Back/C/9", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#Nothing this great comes without sacrifice.", 50, 6450, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(6950)

sm.playSound("Sound/Voice4.img/ArkNpc/Back/C/10", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#And no one said it would be pretty.", 50, 5250, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(5750)

sm.playSound("Sound/Voice4.img/ArkNpc/Back/C/11", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#Now get it together. This is for the greater good.", 50, 7500, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(8000)

sm.OnOffLayer_Off(500, "d2", 0)
sm.sendDelay(500)


sm.OnOffLayer_On(500, "0", 0, -10, 0, "Effect/Direction17.img/effect/ark/noise/1366/0", 4, 1, -1, 1)
sm.playSound("Sound/SoundEff.img/radionoise", 100)
sm.OnOffLayer_Off(300, "2", 0)
sm.OnOffLayer_Off(300, "3", 0)
sm.sendDelay(300)


sm.OnOffLayer_Off(300, "0", 0)
sm.blind(1, 255, 0, 0, 0, 0, 0)
sm.sendDelay(1200)


sm.blind(0, 0, 0, 0, 0, 1000, 0)
sm.sendDelay(1400)


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
sm.sendNext("#face0#Ark! Hello?")


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
sm.sendSay("#face2#Ark's signal is being disrupted somehow.")


# Update Quest Record EX | Quest ID: [34995] | Data: 00=h1;10=h0;01=h0;11=h0;02=h0;12=h0;13=h0;04=h0;23=h0;14=h1;05=h0;24=h0;15=h0;06=h0;16=h0;07=h0;17=h0;09=h0
# Update Quest Record EX | Quest ID: [34934] | Data: dir=1;exp=1
sm.removeNpc(3001510)
sm.removeNpc(3001509)
sm.removeNpc(3001512)
sm.removeNpc(3001513)
sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)


sm.moveCamera(True, 0, 0, 0)


sm.sendDelay(300)


sm.removeOverlapScreen(1000)
sm.moveCamera(True, 0, 0, 0)


sm.setStandAloneMode(False)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
# [SET_PARTNER] [01 A5 CC 2D 00 5C BD C4 04 00 ]
# [SET_PARTNER] [01 A8 CC 2D 00 5E BD C4 04 00 ]
# [SET_PARTNER] [01 A9 CC 2D 00 5F BD C4 04 00 ]
