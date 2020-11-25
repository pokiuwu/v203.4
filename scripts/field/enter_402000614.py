# Created by MechAviv
# Map ID :: 402000614
# Refuge Outskirts : Shiny Rock Spot

# if has quest 34924:
#sm.spawnNpc(3001510, 360, 76)
#sm.showNpcSpecialActionByTemplateId(3001510, "summon", 0)

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, False, False, False)
sm.setStandAloneMode(True)
sm.zoomCamera(0, 1000, 0, -357, 60)


sm.teleportInField(-740, 99)
sm.spawnNpc(3001511, -311, 76)
sm.showNpcSpecialActionByTemplateId(3001511, "summon", 0)
sm.sendDelay(500)


sm.moveNpcByTemplateId(3001511, False, 400, 180)
sm.sendDelay(500)


sm.zoomCamera(7000, 1000, 7000, 268, 60)


sm.forcedMove(False, 750)
sm.sendDelay(3000)


sm.showNpcSpecialActionByTemplateId(3001511, "falldown", 0)
sm.sendDelay(2000)


sm.moveNpcByTemplateId(3001511, False, 250, 180)
sm.sendDelay(1000)


sm.forcedMove(False, 250)
sm.sendDelay(1000)


sm.setSpeakerID(3001511)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendNext("#face0#This is where I keep all my shiny things. Isn't it pretty?")


# Unhandled Message [47] Packet: 2F 02 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00 BD 22 11 00 00 00 00 00 63 04 00 00 05 02 F0 30 82 62 89 D6 D4 01 0D 00 66 69 65 6C 64 5F 65 6E 74 65 72 3D 31
sm.zoomCamera(2000, 1000, 2000, 650, 60)


sm.sendDelay(2000)


sm.setSpeakerID(3001511)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendNext("#face1#It's almost as pretty as the night sky.")


sm.setSpeakerID(3001511)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendSay("#face2#It's my favorite, favorite thing.")


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
sm.sendSay("#face2#This looks very familiar...")


sm.OnOffLayer_On(500, "0", 0, -10, 0, "Effect/Direction17.img/effect/ark/noise/1366/0", 4, 1, -1, 1)
sm.playSound("Sound/SoundEff.img/radionoise", 100)
sm.blind(1, 255, 0, 0, 0, 500, 0)
sm.sendDelay(500)


sm.sendDelay(300)


sm.OnOffLayer_Off(300, "0", 0)
sm.OnOffLayer_On(500, "2", 0, -10, 0, "Effect/Direction17.img/effect/ark/noise/1366/2", 4, 1, -1, 1)
sm.OnOffLayer_On(500, "3", 0, -10, 0, "Effect/Direction17.img/effect/ark/noise/1366/3", 4, 1, -1, 1)
sm.setBGMVolume(0, 1000)
sm.sendDelay(1500)


sm.setBGMVolume(80, 300)
sm.changeBGM("Bgm43.img/Unknown Part Of City", 0, 0)
sm.OnOffLayer_On(1500, "d0", 0, -80, -1, "Effect/Direction17.img/effect/ark/illust/1/0", 4, 1, -1, 0)
sm.sendDelay(2000)


sm.OnOffLayer_On(500, "d1", 0, -80, -1, "Effect/Direction17.img/effect/ark/illust/1/1", 4, 1, -1, 0)
sm.sendDelay(500)


sm.playSound("Sound/Voice4.img/ArkNpc/Back/B/1", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#Now they're sending rookies like us into the field...", 50, 2500, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(3100)


sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#They must really be hurting on the front lines.", 50, 2600, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(3000)

sm.playSound("Sound/Voice4.img/ArkNpc/Back/B/2", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#I guess you can't be picky about troops when you lose.", 50, 3650, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(4150)

sm.playSound("Sound/Voice4.img/ArkNpc/Back/B/3", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#But I'm not going to complain. It got us to an elite base that much faster!", 50, 4260, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(4760)

sm.playSound("Sound/Voice4.img/Ark/Back/B/Male/4", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#Yeah, we're finally taking a big step forward.", 50, 6600, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(7100)

sm.playSound("Sound/Voice4.img/Ark/Back/B/Male/5", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#Now we'll be able to make a difference.", 50, 5100, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(5600)

sm.playSound("Sound/Voice4.img/ArkNpc/Back/B/6", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#Soldiers forever sworn to bring peace to Grandis.", 50, 4500, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(5000)


sm.sendDelay(500)


sm.OnOffLayer_On(500, "d2", 0, -80, -1, "Effect/Direction17.img/effect/ark/illust/1/2", 4, 1, -1, 0)
sm.sendDelay(500)


sm.playSound("Sound/Voice4.img/ArkNpc/Back/B/7", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#We're ready. Let's get moving.", 50, 3000, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(3500)

sm.playSound("Sound/Voice4.img/ArkNpc/Back/B/8", 100)
sm.playSound("Sound/Voice4.img/Ark/Back/B/Male/8", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#Yes, Brigadier General, sir!", 50, 1400, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(1900)

# Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 8C 01 00 00 FF 00 00 00 00
sm.playSound("Sound/Voice4.img/ArkNpc/Back/B/12", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#See you at the base.", 50, 2000, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(2500)

sm.playSound("Sound/Voice4.img/Ark/Back/B/Male/11", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#Any advice, sir?", 50, 1900, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(2400)

sm.playSound("Sound/Voice4.img/ArkNpc/Back/B/10", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#You're good soldiers. I'm sure you'll be fine.", 50, 3200, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(3700)

sm.OnOffLayer_Off(500, "d2", 0)
sm.OnOffLayer_Off(500, "d1", 0)
sm.OnOffLayer_Off(500, "d0", 0)
sm.sendDelay(500)


sm.playSound("Sound/Voice4.img/GLAddVoice/Back/B/1", 100)
sm.playSound("Sound/Voice4.img/GLAdd/Back/B/1", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs55#For victory!", 50, 2000, 4, 0, 0, 0, 4, 300, 3, 300)
sm.sendDelay(2500)

sm.playSound("Sound/Voice4.img/GLAdd/Back/B/2", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs55#Glory to the Flora!", 50, 2850, 4, 0, 0, 0, 4, 300, 3, 300)
sm.sendDelay(3350)

sm.OnOffLayer_On(500, "d5", 0, -80, -1, "Effect/Direction17.img/effect/ark/illust/1/0", 4, 1, -1, 0)
sm.OnOffLayer_On(500, "d6", 0, -80, -1, "Effect/Direction17.img/effect/ark/illust/1/1", 4, 1, -1, 0)
sm.sendDelay(1000)


sm.playSound("Sound/Voice4.img/Ark/Back/B/Male/14", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#Brigadier General Limbo... He's done so much good.", 50, 3800, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(4300)

sm.playSound("Sound/Voice4.img/Ark/Back/B/Male/15", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#That could be me one day.", 50, 3100, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(3600)

sm.playSound("Sound/Voice4.img/ArkNpc/Back/B/16", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#Ha! Sucking up to the boss already, huh?", 50, 4200, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(5700)


sm.playSound("Sound/Voice4.img/Ark/Back/B/Male/17", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#What!? I'm serious! Don't you want to be that great too?", 50, 1600, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(3600)

sm.playSound("Sound/Voice4.img/ArkNpc/Back/B/18", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#One step at a time. We're going to be late. Let's go.", 50, 3000, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(3500)

sm.OnOffLayer_Off(500, "d6", 0)
sm.playSound("Sound/Voice4.img/Ark/Back/B/Male/19", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#Albaire!", 50, 3000, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(3500)

sm.sendDelay(1500)


sm.playSound("Sound/Voice4.img/Ark/Back/B/Male/22", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#I guess it's really time for us to go.", 50, 1900, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(2400)

sm.playSound("Sound/Voice4.img/Ark/Back/B/Male/23", 100)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs35#I can't believe we've already been assigned to such an important base.", 50, 4500, 7, 0, -50, 0, 4, 300, 3, 300)
sm.sendDelay(5000)

sm.sendDelay(1500)


sm.OnOffLayer_On(500, "d3", 0, -80, -1, "Effect/Direction17.img/effect/ark/illust/1/3", 4, 1, -1, 0)
sm.sendDelay(1500)


sm.OnOffLayer_Off(500, "d3", 0)
sm.OnOffLayer_Off(500, "d5", 0)
sm.playSound("Sound/SoundEff.img/radionoise", 100)
sm.OnOffLayer_On(500, "0", 0, -10, 0, "Effect/Direction17.img/effect/ark/noise/1366/0", 4, 1, -1, 1)
sm.blind(1, 200, 0, 0, 0, 1300, 0)
sm.sendDelay(1600)


sm.OnOffLayer_Off(300, "0", 0)
sm.OnOffLayer_Off(300, "2", 0)
sm.OnOffLayer_Off(300, "3", 0)
sm.sendDelay(500)


# Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 96 01 00 00 FF 00 00 00 00
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
sm.sendNext("#face3#(I have so many memories of my life as a soldier... and the one who fought alongside me...)")


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
sm.sendSay("#face3#(The man who attacked me... We were friends before...)")


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
sm.sendSay("#face5#(We're wearing the same uniform, so we must have fought on the same side, but then... why would he attack me?)")


sm.blind(0, 0, 0, 0, 0, 1300, 0)
sm.sendDelay(1600)


sm.flipNpcByTemplateId(3001511, True)
sm.sendDelay(1000)


sm.setSpeakerID(3001511)
sm.setSpeakerType(3)
sm.removeEscapeButton()
sm.flipDialogue()
sm.setBoxChat()
sm.boxChatPlayerAsSpeaker()
sm.setBoxOverrideSpeaker()
sm.flipBoxChat()
sm.flipBoxChatPlayerAsSpeaker()
sm.setColor(1)
sm.sendNext("#face0#Are you okay?")


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
sm.sendSay("#face0#Fine. Let's get back to the refuge.")


sm.removeNpc(3001511)
sm.blind(1, 255, 0, 0, 0, 500, 0)
sm.sendDelay(500)


sm.sendDelay(1000)

sm.playExclSoundWithDownBGM("Voice4.img/Ark/Mono/C/Male/1")
sm.sayMonologue("#fs22##fnArial#I returned there several times after that.", False)

sm.playExclSoundWithDownBGM("Voice4.img/Ark/Mono/C/Male/2")
sm.sayMonologue("#fs22##fnArial#But I recalled nothing more from my past.", False)

sm.playExclSoundWithDownBGM("Voice4.img/Ark/Mono/C/Male/3")
sm.sayMonologue("#fs22##fnArial#\r\nI'm sure of one thing though.", False)


sm.playExclSoundWithDownBGM("Voice4.img/Ark/Mono/C/Male/4")
sm.sayMonologue("#fs22##fnArial#There's still hope for us.", False)

sm.playExclSoundWithDownBGM("Voice4.img/Ark/Mono/C/Male/5")
sm.sayMonologue("#fs22##fnArial#A way to escape this planet.", False)

sm.playExclSoundWithDownBGM("Voice4.img/Ark/Mono/C/Male/6")
sm.sayMonologue("#fs22##fnArial#\r\n\r\nThe power that broken crystal contains is our best chance. #fn바탕#", False)

sm.playExclSoundWithDownBGM("Voice4.img/Ark/Mono/C/Male/7")
sm.sayMonologue("#fs22##fnArial#All we have to do is find the rest of it.", True)


sm.sendDelay(1000)


# Update Quest Record EX | Quest ID: [34924] | Data: e1=1
sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)


sm.moveCamera(True, 0, 0, 0)


sm.sendDelay(300)


sm.removeOverlapScreen(1000)
sm.setStandAloneMode(False)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
# Unhandled Message [47] Packet: 2F 02 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00 BD 22 11 00 00 00 00 00 63 04 00 00 05 02 F0 30 82 62 89 D6 D4 01 0D 00 66 69 65 6C 64 5F 65 6E 74 65 72 3D 31
sm.warp(402000600, 3)
