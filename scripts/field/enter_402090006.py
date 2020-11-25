# Created by MechAviv
# Map ID :: 402090006
# Ravaged Base Remnants : Outside the Desert Cavern

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, False, False, False)
sm.removeAdditionalEffect()
sm.setStandAloneMode(True)
sm.zoomCamera(0, 2000, 0, -2281, 381)


# Unhandled Ingame Direction Event [InputUI] Packet: 12 00 00 00 00
# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00


sm.forcedAction(7, 0)
sm.sendDelay(1000)


sm.forcedInput(7)
sm.sendDelay(1000)


sm.showEffect("Effect/Direction19.img/effect/cadena/3", 3000, 750, 0, 4, -2, False, 0)
sm.playSound("Sound/SoundEff.img/blackHeaven/explosion1", 100)
sm.showEffect("Effect/Direction19.img/effect/cadena/3", 3000, 500, 100, 4, -2, False, 0)
sm.playSound("Sound/SoundEff.img/blackHeaven/explosion2", 100)
sm.showEffect("Effect/Direction19.img/effect/cadena/3", 0, 2000, 57, 4, -2, False, 0)
sm.sendDelay(1000)


sm.showEffect("Effect/Direction19.img/effect/cadena/3", 0, 420, 200, 4, -2, False, 0)
sm.showEffect("Effect/Direction19.img/effect/cadena/3", 0, 100, 30, 3, -2, False, 0)
sm.playSound("Sound/SoundEff.img/blackHeaven/explosion0", 100)
sm.sendDelay(1000)


sm.showEffect("Effect/Direction19.img/effect/cadena/3", 3000, 190, 440, 3, -2, False, 0)
sm.showEffect("Effect/Direction19.img/effect/cadena/3", 0, 313, 370, 3, -2, False, 0)
sm.showEffect("Effect/Direction19.img/effect/cadena/3", 0, 172, 410, 3, -2, False, 0)
sm.showEffect("Effect/Direction19.img/effect/cadena/3", 3000, 400, 280, 3, -2, False, 0)
sm.playSound("Sound/SoundEff.img/blackHeaven/explosion1", 100)
sm.sendDelay(500)


sm.zoomCamera(500, 1000, 500, -2301, 350)


sm.sendDelay(500)


sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)


sm.moveCamera(True, 0, 0, 0)


sm.sendDelay(300)


sm.removeOverlapScreen(1000)
sm.setMapTaggedObjectVisible("sand", False, 0, 0)
sm.zoomCamera(0, 1000, 0, -2301, 350)


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
sm.sendNext("#face0#I think we found it!")


# Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 1C 02 00 00 FF 00 00 00 00
sm.blind(1, 150, 0, 0, 0, 500, 0)
sm.playSound("Sound/SoundEff.img/PinkBean/expectation", 100)
sm.OnOffLayer_On(300, "d0", 0, -80, -1, "Effect/Direction17.img/effect/ark/illust/7/2", 4, 1, -1, 0)
sm.sendDelay(1000)


sm.blind(0, 0, 0, 0, 0, 500, 0)
sm.OnOffLayer_Off(500, "d0", 0)
sm.sendDelay(500)


sm.setBGMVolume(80, 0)
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
sm.sendNext("#face1#So, we just take this to the refuge, and we can travel again?")


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
sm.sendSay("#face3#Oh yeah! Now we've made it through adversity!")


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
sm.sendSay("#face4#Through the sandy storms and the dry, dry sea!")


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
sm.sendSay("#face3#It wasn't easy? You're telling me!")


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
sm.sendSay("#face4#But soon we'll leave here, and we'll all be free!")


sm.zoomCamera(1000, 1000, 1000, -1808, 350)


sm.forcedMove(False, 500)
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
sm.sendNext("#face0#Hm, I've seen that before.")


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
sm.sendSay("#face0#I've seen this from a distance before, but never up close. This structure is massive.")


sm.sendDelay(1000)


sm.zoomCamera(4000, 950, 4000, -1308, -150)


sm.sendDelay(1000)


sm.changeBGM("Bgm45.img/Army Of Fears Theme", 0, 0)
sm.sendDelay(5500)


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
sm.sendNext("#face8#So, now we see what war in the name of peace truly looks like.")


sm.sendDelay(3500)


sm.OnOffLayer_On(500, "0", 0, -10, 0, "Effect/Direction17.img/effect/ark/noise/1366/0", 4, 1, -1, 1)
sm.playSound("Sound/SoundEff.img/radionoise", 100)
sm.blind(1, 255, 0, 0, 0, 500, 0)
sm.sendDelay(500)


sm.sendDelay(300)


sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)


sm.moveCamera(True, 0, 0, 0)


sm.sendDelay(300)


sm.removeOverlapScreen(1000)
sm.moveCamera(True, 0, 0, 0)


# Unhandled Stat Changed [MP] Packet: 00 00 00 10 00 00 00 00 00 00 1F 02 00 00 FF 00 00 00 00
sm.setStandAloneMode(False)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
sm.warp(402090010, 0)
