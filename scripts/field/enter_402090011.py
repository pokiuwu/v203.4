# Created by MechAviv
# Map ID :: 402090011
# Unknown : Unknown

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, False, False, False)
sm.setStandAloneMode(True)
sm.removeAdditionalEffect()
sm.forcedFlip(True)
sm.spawnNpc(3001511, -1100, -17)
sm.showNpcSpecialActionByTemplateId(3001511, "summon", 0)
sm.zoomCamera(0, 2000, 0, -1100, 50)


sm.sendDelay(1000)


sm.moveNpcByTemplateId(3001511, False, 20, 80)
sm.zoomCamera(500, 2000, 500, -970, 150)


sm.sendDelay(500)

sm.moveNpcByTemplateId(3001511, False, 10, 80)
sm.showNpcSpecialActionByTemplateId(3001511, "falldown", 1700)
sm.sendDelay(1700)


# [NPC_RESET_SPECIAL_ACTION] [A8 CE 30 00 ]
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
sm.sendNext("#face0#Where did everyone go...?")


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
sm.sendSay("#face0#Who are you?")


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
sm.sendSay("#face0#My name is Ark. We need to hurry.")


# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
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
sm.sendSay("#face5#It's dangerous here. I'll take you to your people.")


sm.sendDelay(1000)


sm.zoomCamera(500, 2000, 500, -1170, 150)


sm.sendDelay(500)


sm.showNpcSpecialActionByTemplateId(3001527, "summon", 0)
sm.showNpcSpecialActionByTemplateId(3001528, "summon", 0)
sm.showNpcSpecialActionByTemplateId(3001527, "summon", 0)
sm.sendDelay(500)


sm.showFadeTransition(0, 300, 300)
sm.showNpcSpecialActionByTemplateId(3001527, "summon", 0)
sm.showNpcSpecialActionByTemplateId(3001528, "summon", 0)
sm.showNpcSpecialActionByTemplateId(3001527, "summon", 0)
sm.showNpcSpecialActionByTemplateId(3001528, "summon", 0)
sm.showNpcSpecialActionByTemplateId(3001527, "summon", 0)
sm.showNpcSpecialActionByTemplateId(3001528, "summon", 0)
sm.showNpcSpecialActionByTemplateId(3001527, "summon", 0)
sm.showNpcSpecialActionByTemplateId(3001528, "summon", 0)
sm.showNpcSpecialActionByTemplateId(3001527, "summon", 0)
sm.showNpcSpecialActionByTemplateId(3001528, "summon", 0)
sm.showNpcSpecialActionByTemplateId(3001527, "summon", 0)
sm.showNpcSpecialActionByTemplateId(3001528, "summon", 0)
sm.showNpcSpecialActionByTemplateId(3001527, "summon", 0)
sm.showNpcSpecialActionByTemplateId(3001528, "summon", 0)
sm.showNpcSpecialActionByTemplateId(3001527, "summon", 0)
sm.showNpcSpecialActionByTemplateId(3001528, "summon", 0)
sm.moveNpcByTemplateId(3001527, True, 100, 80)
sm.moveNpcByTemplateId(3001528, True, 300, 80)
sm.moveNpcByTemplateId(3001527, True, 200, 80)
sm.moveNpcByTemplateId(3001528, True, 100, 100)
sm.moveNpcByTemplateId(3001527, True, 200, 80)
sm.moveNpcByTemplateId(3001528, True, 100, 80)
sm.moveNpcByTemplateId(3001527, True, 100, 80)
sm.moveNpcByTemplateId(3001528, True, 200, 100)
sm.moveNpcByTemplateId(3001527, True, 250, 80)
sm.moveNpcByTemplateId(3001528, True, 200, 80)
sm.moveNpcByTemplateId(3001527, True, 200, 80)
sm.moveNpcByTemplateId(3001528, True, 80, 80)
sm.moveNpcByTemplateId(3001527, True, 80, 100)
sm.moveNpcByTemplateId(3001528, True, 200, 80)
sm.moveNpcByTemplateId(3001527, True, 150, 80)
sm.moveNpcByTemplateId(3001528, True, 200, 80)
sm.showNpcSpecialActionByTemplateId(3001527, "move", -1)
sm.showNpcSpecialActionByTemplateId(3001528, "move", -1)
sm.showNpcSpecialActionByTemplateId(3001527, "move", -1)
sm.showNpcSpecialActionByTemplateId(3001528, "move", -1)
sm.showNpcSpecialActionByTemplateId(3001527, "move", -1)
sm.showNpcSpecialActionByTemplateId(3001528, "move", -1)
sm.showNpcSpecialActionByTemplateId(3001527, "move", -1)
sm.showNpcSpecialActionByTemplateId(3001528, "move", -1)
sm.showNpcSpecialActionByTemplateId(3001527, "move", -1)
sm.showNpcSpecialActionByTemplateId(3001528, "move", -1)
sm.showNpcSpecialActionByTemplateId(3001527, "move", -1)
sm.showNpcSpecialActionByTemplateId(3001528, "move", -1)
sm.showNpcSpecialActionByTemplateId(3001527, "move", -1)
sm.showNpcSpecialActionByTemplateId(3001528, "move", -1)
sm.showNpcSpecialActionByTemplateId(3001527, "move", -1)
sm.showNpcSpecialActionByTemplateId(3001528, "move", -1)
sm.zoomCamera(0, 2000, 0, -250, 150)


sm.removeOverlapScreen(300)
sm.sendDelay(500)


sm.zoomCamera(3000, 2000, 3000, -470, 150)


sm.sendDelay(3000)


sm.showFadeTransition(0, 300, 300)
sm.removeOverlapScreen(500)
sm.zoomCamera(0, 2000, 0, -1100, 0)


sm.showNpcSpecialActionByTemplateId(3001511, "summon", 0)
sm.teleportInField(-1100, -17)
sm.sendDelay(500)


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
sm.sendNext("#face0#Wait right there for me. I'll be back.")


sm.zoomCamera(2000, 2000, 2000, -970, 50)


sm.forcedFlip(False)
sm.forcedMove(False, 200)
sm.sendDelay(2000)


sm.moveNpcByTemplateId(3001527, True, 130, 80)
sm.moveNpcByTemplateId(3001528, True, 100, 80)
sm.moveNpcByTemplateId(3001527, True, 20, 80)
sm.moveNpcByTemplateId(3001528, True, 10, 100)
sm.moveNpcByTemplateId(3001527, True, 20, 80)
sm.moveNpcByTemplateId(3001528, True, 10, 80)
sm.moveNpcByTemplateId(3001527, True, 50, 80)
sm.moveNpcByTemplateId(3001528, True, 20, 100)
sm.moveNpcByTemplateId(3001527, True, 25, 80)
sm.moveNpcByTemplateId(3001528, True, 20, 80)
sm.moveNpcByTemplateId(3001527, True, 20, 80)
sm.moveNpcByTemplateId(3001528, True, 80, 80)
sm.moveNpcByTemplateId(3001527, True, 80, 100)
sm.moveNpcByTemplateId(3001528, True, 22, 80)
sm.moveNpcByTemplateId(3001527, True, 15, 80)
sm.moveNpcByTemplateId(3001528, True, 20, 80)
sm.zoomCamera(3000, 1000, 3000, -730, 50)


sm.sendDelay(3500)


sm.showFadeTransition(0, 1000, 3000)
sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)


sm.moveCamera(True, 0, 0, 0)


sm.sendDelay(300)

sm.removeOverlapScreen(1000)
sm.moveCamera(True, 0, 0, 0)


sm.setStandAloneMode(False)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
# [CLEAR_ANNOUNCED_QUEST] []
sm.systemMessage("If I'm going to get Mar out safely, I'll have to thin the monsters's numbers.")
sm.chatScript("If I'm going to get Mar out safely, I'll have to thin the monsters's numbers.")
# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
sm.warp(940205000, 0)
