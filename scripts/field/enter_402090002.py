# Created by MechAviv
# Map ID :: 402090002
# Sandstorm Zone : Refuge Border

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, False, False, False)
sm.setStandAloneMode(True)
sm.removeAdditionalEffect()
sm.blind(1, 255, 0, 0, 0, 0, 0)
sm.forcedFlip(True)
sm.zoomCamera(0, 1000, 0, 100, 250)


sm.sendDelay(1000)


sm.spawnNpc(3001508, -54, 237)
sm.showNpcSpecialActionByTemplateId(3001508, "summon", 0)
sm.spawnNpc(3001509, 298, 200)
sm.showNpcSpecialActionByTemplateId(3001509, "summon", 0)
sm.spawnNpc(3001512, 374, 200)
sm.showNpcSpecialActionByTemplateId(3001512, "summon", 0)
sm.spawnNpc(3001513, 431, 200)
sm.showNpcSpecialActionByTemplateId(3001513, "summon", 0)
sm.spawnNpc(3001510, 550, 200)
sm.showNpcSpecialActionByTemplateId(3001510, "summon", 0)
sm.spawnNpc(3001514, -181, 200)
sm.showNpcSpecialActionByTemplateId(3001514, "summon", 0)
sm.spawnNpc(3001515, -330, 200)
sm.showNpcSpecialActionByTemplateId(3001515, "summon", 0)
sm.spawnNpc(3001516, -275, 200)
sm.showNpcSpecialActionByTemplateId(3001516, "summon", 0)
sm.spawnNpc(3001517, -487, -5)
sm.showNpcSpecialActionByTemplateId(3001517, "summon", 0)
sm.spawnNpc(3001518, -330, -5)
sm.showNpcSpecialActionByTemplateId(3001518, "summon", 0)
sm.spawnNpc(3001519, -435, -5)
sm.showNpcSpecialActionByTemplateId(3001519, "summon", 0)
sm.spawnNpc(3001520, -380, -5)
sm.showNpcSpecialActionByTemplateId(3001520, "summon", 0)
sm.spawnNpc(3001521, -331, 132)
sm.showNpcSpecialActionByTemplateId(3001521, "summon", 0)
sm.spawnNpc(3001522, -439, 93)
sm.showNpcSpecialActionByTemplateId(3001522, "summon", 0)
sm.spawnNpc(3001511, -830, 200)
sm.showNpcSpecialActionByTemplateId(3001511, "summon", 0)
sm.sendDelay(1000)


sm.blind(0, 0, 0, 0, 0, 1000, 0)
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
sm.sendNext("#face0#Ferret, Salvo, Digs, Grit, and Ark... All of you, come home in one piece.")


# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
sm.moveNpcByTemplateId(3001511, False, 350, 200)
sm.sendDelay(3500)


sm.showNpcSpecialActionByTemplateId(3001511, "falldown", 0)
sm.sendDelay(1500)


# [NPC_RESET_SPECIAL_ACTION] [16 9E 4A 00 ]
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
sm.sendNext("#face2#Be brave!!")


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
sm.sendSay("#face0#Zippy, if anything happens, we'll contact you through one of the signal devices.")


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
sm.sendSay("#face2#Good. Ring once for retreat, twice if you find the crystals, three times if you're heading back home.")


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
sm.sendSay("#face3#And I hope you don't need it, but... four if you're in danger.")


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
sm.sendSay("#face2#Don't worry. We'll succeed.")


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
sm.sendSay("#face0#Okay, let's head out.")


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
sm.sendSay("#face2#For those staying in the refuge, return to your stations and stay alert for attacks.")


sm.flipNpcByTemplateId(3001514, True)
sm.flipNpcByTemplateId(3001515, True)
sm.flipNpcByTemplateId(3001516, True)
sm.flipNpcByTemplateId(3001517, True)
sm.flipNpcByTemplateId(3001518, True)
sm.flipNpcByTemplateId(3001519, True)
sm.flipNpcByTemplateId(3001520, True)
sm.flipNpcByTemplateId(3001521, True)
sm.flipNpcByTemplateId(3001522, True)
sm.flipNpcByTemplateId(3001511, True)
sm.sendDelay(500)


sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)


sm.moveCamera(True, 0, 0, 0)


sm.sendDelay(300)


sm.removeOverlapScreen(1000)
sm.removeNpc(3001514)
sm.removeNpc(3001515)
sm.removeNpc(3001516)
sm.removeNpc(3001517)
sm.removeNpc(3001518)
sm.removeNpc(3001519)
sm.removeNpc(3001520)
sm.removeNpc(3001521)
sm.removeNpc(3001522)
sm.removeNpc(3001511)
sm.zoomCamera(0, 1000, 0, 100, 250)


sm.sendDelay(1000)


sm.zoomCamera(1000, 1000, 1000, 260, 250)


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
sm.sendNext("#face0#Zippy, we need to know what signals the refuge will use.")


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
sm.sendSay("#face3#We don't need signals!\r\nWe can use music!")


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
sm.sendSay("#face4#We'll listen with our hearts!")


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
sm.sendSay("#face2#Salvo, we really need to be more practical than that right now.")


sm.blind(1, 150, 0, 0, 0, 2000, 0)
sm.sendDelay(1000)


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
sm.sendNext("#face0#Oh, right, uh... what about... um...")


sm.blind(1, 255, 0, 0, 0, 3000, 0)
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
sm.sendNext("#face0#Ah, don't worry about it. I'm sure it'll be fine. Just stick to the plan.")


sm.sendDelay(1000)


sm.OnOffLayer_On(500, "d0", 0, -80, -1, "Effect/Direction17.img/effect/ark/illust/8/0", 4, 1, -1, 0)
sm.sendDelay(1000)


sm.OnOffLayer_On(500, "d1", -200, 30, -1, "Effect/Direction17.img/effect/ark/illust/8/1", 4, 1, -1, 0)
sm.sendDelay(500)


sm.OnOffLayer_Move(1000, "d1", 0, -150)
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
sm.sendNext("#face0#Based on the signals, there are two crystal fragments left to find.")


sm.sendDelay(1000)


sm.OnOffLayer_Move(1000, "d1", 140, 130)
sm.sendDelay(1000)


sm.OnOffLayer_Move(1000, "d1", 140, 0)
sm.sendDelay(1000)


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
sm.sendNext("#face0#We'll head to the closer one first.")


sm.OnOffLayer_Move(1000, "d1", 100, 0)
sm.sendDelay(1000)


sm.OnOffLayer_Move(1000, "d1", 0, -140)
sm.sendDelay(1000)


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
sm.sendNext("#face4#Then we'll find the third and make our way to the transport.")


sm.OnOffLayer_Move(1000, "d1", -200, 140)
sm.sendDelay(1000)


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
sm.sendNext("#face1#Well, check out Mr. Bossy taking over as leader!")


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
sm.sendSay("#face0#It's not that far, and we've got a good signal, so I doubt we'll get lost. I'm only worried about one thing.")


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
sm.sendSay("#face2#We have no idea if and when more of those monsters will show up.")


sm.sendDelay(1000)


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
sm.sendNext("#face2#There's not much we can do about that, so we'll just have to be careful. Let's hurry and get moving.")


sm.OnOffLayer_Off(300, "d0", 0)
sm.OnOffLayer_Off(300, "d1", 0)
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
# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
sm.removeNpc(3001508)
sm.removeNpc(3001509)
sm.removeNpc(3001512)
sm.removeNpc(3001513)
sm.removeNpc(3001510)
sm.warp(402090004, 0)
