# Created by MechAviv
# Map ID :: 402000615
# Refuge Outskirts : Beyond the Refuge
if "1" not in sm.getQuestEx(34915, "e1"):
    sm.setMapTaggedObjectVisible("core0", False, 0, 0)
    sm.setMapTaggedObjectVisible("core1", False, 0, 0)
    sm.changeBGM("Bgm28.img/helisiumWarcry", 0, 0)
    OBJECT_3 = sm.sendNpcController(3001509, 500, 90)
    sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
    OBJECT_4 = sm.sendNpcController(3001510, 600, 90)
    sm.showNpcSpecialActionByObjectId(OBJECT_4, "summon", 0)
    OBJECT_5 = sm.sendNpcController(3001512, 200, 90)
    sm.showNpcSpecialActionByObjectId(OBJECT_5, "summon", 0)
    OBJECT_6 = sm.sendNpcController(3001513, 130, 90)
    sm.showNpcSpecialActionByObjectId(OBJECT_6, "summon", 0)
    OBJECT_7 = sm.sendNpcController(3001516, 750, 90)
    sm.showNpcSpecialActionByObjectId(OBJECT_7, "summon", 0)
    OBJECT_8 = sm.sendNpcController(3001515, 680, 90)
    sm.showNpcSpecialActionByObjectId(OBJECT_8, "summon", 0)
    OBJECT_9 = sm.sendNpcController(3001521, -100, 90)
    sm.showNpcSpecialActionByObjectId(OBJECT_9, "summon", 0)
    OBJECT_10 = sm.sendNpcController(3001522, -190, 90)
    sm.showNpcSpecialActionByObjectId(OBJECT_10, "summon", 0)
    OBJECT_11 = sm.sendNpcController(3001514, -300, 90)
    sm.showNpcSpecialActionByObjectId(OBJECT_11, "summon", 0)
    OBJECT_12 = sm.sendNpcController(3001520, 165, -250)
    sm.showNpcSpecialActionByObjectId(OBJECT_12, "summon", 0)
    OBJECT_13 = sm.sendNpcController(3001519, 225, -250)
    sm.showNpcSpecialActionByObjectId(OBJECT_13, "summon", 0)
    OBJECT_14 = sm.sendNpcController(3001517, 95, -250)
    sm.showNpcSpecialActionByObjectId(OBJECT_14, "summon", 0)
    OBJECT_15 = sm.sendNpcController(3001518, 300, -250)
    sm.showNpcSpecialActionByObjectId(OBJECT_15, "summon", 0)
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, False, False, False)
    sm.setStandAloneMode(True)
    sm.removeAdditionalEffect()
    sm.zoomCamera(0, 3000, 0, 200, -130)


    sm.sendDelay(500)


    sm.sendDelay(500)


    sm.speechBalloon(True, 0, 0, "Our refuge isn't safe anymore.", 1000, 0, 0, 0, 0, 4, 3001517)
    sm.sendDelay(1100)
    sm.speechBalloon(True, 0, 0, "I'm so scared.", 1000, 0, 0, 0, 0, 4, 3001518)


    # Unhandled Message [47] Packet: 2F 02 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00 BE 22 11 00 00 00 00 00 63 04 00 00 06 02 C0 28 22 8D 79 D6 D4 01 0D 00 66 69 65 6C 64 5F 65 6E 74 65 72 3D 31
    sm.zoomCamera(3000, 2000, 3000, 440, 150)


    sm.sendDelay(3500)


    sm.setIntroBoxChat(3001508)
    sm.setSpeakerType(3)
    sm.sendNext("#face2#It's okay. It might be tough, but we can rebuild our home if we have to. At least we're all safe. That's what matters.")


    sm.setIntroBoxChat(3001510)
    sm.setSpeakerType(3)
    sm.sendSay("#face2#I'm detecting monster signals everywhere. We need to be ready to move.")


    sm.setIntroBoxChat(3001508)
    sm.setSpeakerType(3)
    sm.sendSay("#face2#So, Ark, was it? What do you plan to do?")


    sm.zoomCamera(3000, 2000, 3000, 150, 150)


    sm.sendDelay(1000)


    sm.setIntroBoxChat(3001500)
    sm.setSpeakerType(3)
    sm.sendNext("#face0#Don't worry, I won't bother you. Wouldn't want you to take me prisoner again... Thanks for letting me go, by the way.")


    sm.sendDelay(2000)


    sm.setIntroBoxChat(3001508)
    sm.setSpeakerType(3)
    sm.sendNext("#face2#Oh, that's not what I meant. I don't think you'll hurt us. I just think we'd be safer if we all stick together.")


    sm.setIntroBoxChat(3001510)
    sm.setSpeakerType(3)
    sm.sendSay("#face0#It's up to you. But since you're not an enemy, we've got no problem with you staying.")


    sm.blind(1, 150, 0, 0, 0, 3000, 0)
    sm.sendDelay(500)


    sm.setIntroBoxChat(3001509)
    sm.setSpeakerType(3)
    sm.sendNext("#face3#Won't get eaten by monsters! No! No! No!\r\nCause we're gonna stop talking! Go! Go! Go!")


    sm.setIntroBoxChat(3001512)
    sm.setSpeakerType(3)
    sm.sendSay("#face0#He's right. We don't have time to chat. Let's do a headcount. Zippy, Salvo, Ferret, myself, Digs, and Grit... One, two, three, four...")


    sm.setIntroBoxChat(3001513)
    sm.setSpeakerType(3)
    sm.sendSay("#face0#Bull, Slink, all the kids... Wait a minute. Are we missing a kid?!")


    sm.sendDelay(1100)


    sm.setIntroBoxChat(3001500)
    sm.setSpeakerType(3)
    sm.sendNext("#face0#(Hm, they seem nice enough. And they don't think I'm a monster anymore. Maybe traveling with them wouldn't be such a bad idea.)")


    sm.setIntroBoxChat(3001500)
    sm.setSpeakerType(3)
    sm.sendSay("#face0#(At least for a little while. Once I get more information and figure out who I am... Ark... I can move on.)")


    sm.sendDelay(300)


    sm.blind(0, 0, 0, 0, 0, 300, 0)
    sm.sendDelay(300)


    sm.showFadeTransition(0, 1000, 3000)
    sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)


    sm.moveCamera(True, 0, 0, 0)


    sm.sendDelay(300)


    sm.removeOverlapScreen(1000)
    sm.zoomCamera(0, 2000, 0, 410, 150)


    sm.setIntroBoxChat(3001508)
    sm.setSpeakerType(3)
    sm.sendNext("#face3# Mar?!")


    sm.zoomCamera(500, 3000, 500, 410, 150)


    sm.setIntroBoxChat(3001508)
    sm.setSpeakerType(3)
    sm.sendNext("#face1#Mar is missing! Oh no. He always takes a nap in the refuge this time of day! He must still be there!")


    sm.blind(0, 0, 0, 0, 0, 500, 0)
    sm.sendDelay(500)


    sm.showFadeTransition(0, 1000, 3000)
    sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)


    sm.moveCamera(True, 0, 0, 0)


    sm.sendDelay(300)


    sm.removeOverlapScreen(1000)
    sm.zoomCamera(0, 2000, 0, 150, 150)


    sm.blind(1, 150, 0, 0, 0, 2000, 0)
    sm.sendDelay(500)


    sm.setIntroBoxChat(3001500)
    sm.setSpeakerType(3)
    sm.sendNext("#face5#(The caravan is distraught over leaving someone behind, but the monsters are closing in fast. They need to get out of here.)")


    sm.setIntroBoxChat(3001500)
    sm.setSpeakerType(3)
    sm.sendSay("#face5#...")


    sm.setIntroBoxChat(3001500)
    sm.setSpeakerType(3)
    sm.sendSay("#face5#(If I send the others on and go after the kid myself, they might all make it.)")


    sm.sendDelay(300)


    sm.blind(0, 0, 0, 0, 0, 300, 0)
    sm.zoomCamera(3000, 2000, 3000, 380, 150)


    sm.setIntroBoxChat(3001508)
    sm.setSpeakerType(3)
    sm.sendNext("#face3#We can't leave Mar behind, but there are so many monsters! What do we do?")


    sm.forcedMove(False, 270)
    sm.sendDelay(3000)


    sm.setIntroBoxChat(3001500)
    sm.setSpeakerType(3)
    sm.sendNext("#face0#You all need to hide now. I'll get Mar. Where will I find him?")


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
    sm.setQuestEx(34915, "e1", "1")
    sm.systemMessage("Please complete your 1st Job Advancement before entering battle.")
    sm.chatScript("Please complete your 1st Job Advancement before entering battle.")
if sm.hasQuest(34928) and "1" not in sm.getQuestEx(34928, "e1"):
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, False, False, False)
    sm.setStandAloneMode(True)
    sm.setMapTaggedObjectVisible("core0", False, 0, 0)
    sm.setMapTaggedObjectVisible("core1", False, 0, 0)
    sm.zoomCamera(0, 1000, 0, 267, 60)


    sm.teleportInField(0, 95)
    sm.spawnNpc(3001510, 280, 95)
    sm.showNpcSpecialActionByTemplateId(3001510, "summon", 0)
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
    sm.sendNext("#face0#I'm picking up a signal somewhere near here.")


    sm.sendDelay(1000)


    sm.zoomCamera(3000, 2000, 3000, 765, 150)


    sm.moveNpcByTemplateId(3001510, False, 600, 100)
    sm.sendDelay(1000)


    sm.forcedMove(False, 520)
    sm.sendDelay(2000)


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
    sm.sendNext("#face4#This way, I think. Just below here.")


    sm.zoomCamera(2000, 2000, 2000, 765, -10)


    sm.sendDelay(2000)


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
    sm.sendNext("#face3#(There's something nagging at the back of my mind, but I can't put my finger on what.)")


    sm.zoomCamera(500, 2000, 500, 765, 150)


    sm.flipNpcByTemplateId(3001510, True)
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
    sm.sendNext("#face0#Right here, Ark.")


    sm.sendDelay(1000)


    sm.sendDelay(1000)


    sm.showEffect("Effect/Direction17.img/effect/ark/sand/wind", 0, -100, 0, 0, 3001510, False, 0)
    sm.sendDelay(300)


    sm.showEffect("Effect/Direction17.img/effect/ark/sand/wind", 0, -100, 0, 0, 3001510, False, 0)
    sm.showEffect("Effect/Direction17.img/effect/ark/sand/wind", 0, -100, 0, 0, 3001510, False, 0)
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
    sm.setQuestEx(34928, "e1", "1")
    # Update Quest Record EX | Quest ID: [34928] | Data: e1=1
    # Update Quest Record EX | Quest ID: [34995] | Data: 00=h1;10=h0;01=h1;11=h0;02=h1;12=h0;13=h0;04=h0;23=h0;14=h0;05=h0;24=h0;15=h0;06=h0;16=h0;07=h1;17=h0;09=h0
    sm.setMapTaggedObjectVisible("core0", True, 0, 0)
    sm.setMapTaggedObjectVisible("core1", True, 0, 0)
else:
    sm.setMapTaggedObjectVisible("core0", False, 0, 0)
    sm.setMapTaggedObjectVisible("core1", False, 0, 0)