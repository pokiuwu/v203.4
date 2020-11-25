# Created by MechAviv
# Map ID :: 402000633
# Desert Cavern : Desert Cavern 3

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, False, False, False)
sm.setStandAloneMode(True)
sm.removeAdditionalEffect()
sm.blind(1, 255, 0, 0, 0, 0, 0)
sm.teleportInField(-1033, -40)
sm.spawnNpc(3001512, -745, -150)
sm.showNpcSpecialActionByTemplateId(3001512, "summon", 0)
sm.spawnNpc(3001513, -657, -150)
sm.showNpcSpecialActionByTemplateId(3001513, "summon", 0)
sm.blind(1, 255, 0, 0, 0, 0, 0)
sm.sendDelay(1200)


sm.blind(0, 0, 0, 0, 0, 1000, 0)
sm.sendDelay(1400)


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
sm.sendNext("#face0#Ferret, Ark!! You're okay!")


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
sm.sendSay("#face0#I don't remember anything after the fall. I just woke up here.")


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
sm.sendSay("#face0#The exit's straight above us.")


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
sm.sendSay("#face0#We've got to find Salvo before we can leave.")


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
sm.sendSay("#face4#Of course he's the one that's missing.")


# Unhandled Message [47] Packet: 2F 02 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00 C8 22 11 00 00 00 00 00 63 04 00 00 10 02 50 58 5D EF 8A D6 D4 01 0D 00 66 69 65 6C 64 5F 65 6E 74 65 72 3D 31
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
sm.sendSay("#face4#Calm down. We'll find him.")


# Update Quest Record EX | Quest ID: [34933] | Data: dir=1;exp=1
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
# [SET_PARTNER] [01 A8 CC 2D 00 5E BD C4 04 00 ]
# [SET_PARTNER] [01 A9 CC 2D 00 5F BD C4 04 00 ]
# Unhandled Stat Changed [HP] Packet: 00 00 00 04 00 00 00 00 00 00 19 03 00 00 FF 00 00 00 00
#sm.warp(402000631, 2)
