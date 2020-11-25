# Created by MechAviv
# ID :: [402090001]
# Refuge Outskirts : Caravan Refuge

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, False, False, False)
sm.removeAdditionalEffect()
sm.setStandAloneMode(True)
sm.zoomCamera(0, 1000, 0, -303, -545)


sm.sendDelay(1000)


sm.localEmotion(1, 7000, False)
sm.forcedFlip(True)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs18#Several Days Later, Caravan Refuge Entrance", 100, 2200, 6, -50, -50, 1, 4, 0, 0, 0)
sm.sendDelay(2000)

sm.zoomCamera(3000, 1000, 3000, -303, 70)


sm.sendDelay(4000)


sm.setIntroBoxChat(3001500)
sm.setSpeakerType(3)
sm.sendNext("#face1#Ugh... How long have I been out?")


sm.setIntroBoxChat(3001500)
sm.setSpeakerType(3)
sm.sendSay("#face1#Why am I in the desert? And... what's wrong with my arm?")


sm.forcedAction(0, 300)
sm.sendDelay(300)


sm.forcedFlip(False)
sm.forcedAction(0, 300)
sm.sendDelay(300)


sm.forcedFlip(True)
sm.forcedAction(0, 300)
sm.sendDelay(300)


sm.forcedFlip(False)
sm.setIntroBoxChat(3001500)
sm.setSpeakerType(3)
sm.sendNext("#face2#Who tied me up? What do they want?")


sm.setIntroBoxChat(3001500)
sm.setSpeakerType(3)
sm.sendSay("#face3#Maybe they're not dangerous. Then again, the wounds on my arms look pretty fresh.")


sm.setIntroBoxChat(3001500)
sm.setSpeakerType(3)
sm.sendSay("#face0#Urgh... At least I'm alive.")


sm.setIntroBoxChat(3001500)
sm.setSpeakerType(3)
sm.sendSay("#face5#Maybe I can reason with whoever it is.")


sm.forcedFlip(True)
sm.forcedAction(0, 300)
sm.sendDelay(300)


sm.setIntroBoxChat(3001500)
sm.setSpeakerType(3)
sm.sendNext("#face0#I might be able to cut these ropes, but who knows how much time I've got.")


sm.setIntroBoxChat(3001500)
sm.setSpeakerType(3)
sm.sendSay("#face3# Looks like about 20 people over there. Too many to fight. I might have to make a run for it.")


sm.setIntroBoxChat(3001500)
sm.setSpeakerType(3)
sm.sendSay("#face0#...But I suppose I should at least try to talk to them first.")


sm.setIntroBoxChat(3001500)
sm.setSpeakerType(3)
sm.sendSay("#face2#Ah! Here comes one now.")


OBJECT_1 = sm.sendNpcController(3001509, -1250, 90)
sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
sm.sendDelay(1000)


sm.showFadeTransition(0, 300, 300)
sm.sendDelay(300)


sm.removeOverlapScreen(300)
sm.zoomCamera(0, 2000, 0, -1200, 150)


sm.sendDelay(300)


sm.moveNpcByObjectId(OBJECT_1, False, 150, 70)
sm.zoomCamera(3500, 2000, 3500, -950, 150)


sm.setIntroBoxChat(3001509)
sm.setSpeakerType(3)
sm.sendNext("#face2#Boy, I feel like singing at the top of my lungs today!")


sm.sendDelay(3000)


sm.flipNpcByObjectId(OBJECT_1, True)
sm.zoomCamera(5000, 2000, 5000, -730, 150)


sm.moveNpcByObjectId(OBJECT_1, False, 150, 70)
sm.setIntroBoxChat(3001509)
sm.setSpeakerType(3)
sm.sendNext("#face2#I might just dance too! Fancy feet! Shuffle, shuffle, shuffle! I'm doing it!")


sm.sendDelay(2000)


sm.flipNpcByObjectId(OBJECT_1, True)
sm.moveNpcByObjectId(OBJECT_1, False, 430, 100)
sm.setIntroBoxChat(3001509)
sm.setSpeakerType(3)
sm.sendNext("#face3#Salvo on duty and doing fine!\r\nMy dance demolition skill's top of the line!")


sm.sendDelay(1500)


sm.setIntroBoxChat(3001509)
sm.setSpeakerType(3)
sm.sendNext("#face4#Woo! Yeah! Woo, woo! Yea- OH!")


sm.sendDelay(2000)


sm.playSound("Sound/SoundEff.img/Falldown", 100)
sm.showEffect("Effect/OnUserEff.img/emotion/oh", 0, 0, 0, 0, OBJECT_1, False, 0)
sm.setFieldFloating(402090001, 10, 10, 50)
sm.sendDelay(300)


sm.setFieldFloating(402090001, 0, 0, 0)
sm.sendDelay(300)


sm.zoomCamera(0, 2000, 0, -430, 150)


sm.flipNpcByObjectId(OBJECT_1, False)
sm.moveNpcByObjectId(OBJECT_1, True, 60, 220)
sm.sendDelay(2000)


sm.setIntroBoxChat(3001509)
sm.setSpeakerType(3)
sm.sendNext("#face1#The monster's awaaaaake!")


sm.setFieldFloating(402090001, 20, 20, 50)
sm.sendNpcController(OBJECT_1, False)
OBJECT_2 = sm.sendNpcController(3001509, -490, 90)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
sm.sendDelay(1000)


sm.setFieldFloating(402090001, 0, 0, 0)
sm.sendDelay(300)


sm.zoomCamera(0, 2000, 0, -450, 150)


OBJECT_3 = sm.sendNpcController(3001508, -185, 76)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
OBJECT_4 = sm.sendNpcController(3001510, -122, 76)
sm.showNpcSpecialActionByObjectId(OBJECT_4, "summon", 0)
OBJECT_5 = sm.sendNpcController(3001514, -30, 76)
sm.showNpcSpecialActionByObjectId(OBJECT_5, "summon", 0)
OBJECT_6 = sm.sendNpcController(3001515, 10, 127)
sm.showNpcSpecialActionByObjectId(OBJECT_6, "summon", 0)
OBJECT_7 = sm.sendNpcController(3001516, 70, 127)
sm.showNpcSpecialActionByObjectId(OBJECT_7, "summon", 0)
OBJECT_8 = sm.sendNpcController(3001512, -570, 76)
sm.showNpcSpecialActionByObjectId(OBJECT_8, "summon", 0)
OBJECT_9 = sm.sendNpcController(3001513, -620, 76)
sm.showNpcSpecialActionByObjectId(OBJECT_9, "summon", 0)
OBJECT_10 = sm.sendNpcController(3001520, -570, 127)
sm.showNpcSpecialActionByObjectId(OBJECT_10, "summon", 0)
OBJECT_11 = sm.sendNpcController(3001519, -640, 127)
sm.showNpcSpecialActionByObjectId(OBJECT_11, "summon", 0)
OBJECT_12 = sm.sendNpcController(3001517, -670, 76)
sm.showNpcSpecialActionByObjectId(OBJECT_12, "summon", 0)
OBJECT_13 = sm.sendNpcController(3001518, -720, 76)
sm.showNpcSpecialActionByObjectId(OBJECT_13, "summon", 0)
sm.sendDelay(2000)


sm.setIntroBoxChat(3001500)
sm.setSpeakerType(3)
sm.sendNext("#face4#You think I'm a monster?")


sm.zoomCamera(1000, 1000, 1000, -300, 70)


sm.sendDelay(1000)


sm.showEffect("Effect/OnUserEff.img/emotion/oh", 0, 0, 0, 0, OBJECT_3, False, 0)
sm.showEffect("Effect/OnUserEff.img/emotion/oh", 0, 0, 0, 0, OBJECT_4, False, 0)
sm.showEffect("Effect/OnUserEff.img/emotion/oh", 0, 0, 0, 0, OBJECT_5, False, 0)
sm.showEffect("Effect/OnUserEff.img/emotion/oh", 0, 0, 0, 0, OBJECT_6, False, 0)
sm.showEffect("Effect/OnUserEff.img/emotion/oh", 0, 0, 0, 0, OBJECT_7, False, 0)
sm.showEffect("Effect/OnUserEff.img/emotion/oh", 0, 0, 0, 0, OBJECT_8, False, 0)
sm.showEffect("Effect/OnUserEff.img/emotion/oh", 0, 0, 0, 0, OBJECT_9, False, 0)
sm.showEffect("Effect/OnUserEff.img/emotion/oh", 0, 0, 0, 0, OBJECT_10, False, 0)
sm.showEffect("Effect/OnUserEff.img/emotion/oh", 0, 0, 0, 0, OBJECT_11, False, 0)
sm.showEffect("Effect/OnUserEff.img/emotion/oh", 0, 0, 0, 0, OBJECT_12, False, 0)
sm.showEffect("Effect/OnUserEff.img/emotion/oh", 0, 0, 0, 0, OBJECT_13, False, 0)
sm.showEffect("Effect/OnUserEff.img/emotion/oh", 0, 0, 0, 0, OBJECT_2, False, 0)
sm.sendDelay(1000)


sm.setIntroBoxChat(3001510)
sm.setSpeakerType(3)
sm.sendNext("#face2#It can talk? Maybe it's different from the others? But... no... something's not right... Its arm...")


sm.setIntroBoxChat(3001500)
sm.setSpeakerType(3)
sm.sendSay("#face4#Please listen. I'm not a monster. I don't want to hurt you. Would you untie me?")


sm.setIntroBoxChat(3001509)
sm.setSpeakerType(3)
sm.sendSay("#face0#That's just what a monster #ewould#n say! Identify yourself if you want to be released! Or... promise to listen to my music. That's okay too.")


sm.sendDelay(1100)
sm.speechBalloon(False, 0, 0, "The face...", 1000, 0, 0, 0, 0, 4, 3001512)


sm.sendDelay(1100)
sm.speechBalloon(False, 0, 0, "Where are you from?", 1000, 0, 0, 0, 0, 4, 3001514)


sm.sendDelay(1100)
sm.speechBalloon(False, 0, 0, "What's wrong with your arm?", 1000, 0, 0, 0, 0, 4, 3001513)


sm.setIntroBoxChat(3001500)
sm.setSpeakerType(3)
sm.sendNext("#face3#I... I don't actually know. I don't know who I am either. I wish I could tell you.")


sm.setIntroBoxChat(3001500)
sm.setSpeakerType(3)
sm.sendSay("#face3#All I can remember is one big nightmare and someone calling me... Ark. I think that's my name.")


sm.setIntroBoxChat(3001510)
sm.setSpeakerType(3)
sm.sendSay("#face4#Hm, doesn't seem like they're lying.")


sm.setIntroBoxChat(3001508)
sm.setSpeakerType(3)
sm.sendSay("#face2#Well, they smile too much to be evil, right? Maybe we should let 'em go. What does everybody think?")


sm.blind(1, 200, 0, 0, 0, 3000, 0)
sm.sendDelay(500)


sm.setIntroBoxChat(3001509)
sm.setSpeakerType(3)
sm.sendNext("#face1#No! We don't know who that is! And they haven't even listened to my music! What if they turn around and attack us the moment we untie them?!")


sm.setIntroBoxChat(3001512)
sm.setSpeakerType(3)
sm.sendSay("#face0#Salvo's got a point. Well, about them attacking us, not the music.")


sm.setIntroBoxChat(3001513)
sm.setSpeakerType(3)
sm.sendSay("#face0#Morale at the refuge is already bad. If we have another problem, it'll be devastating.")


sm.sendDelay(1100)


sm.setIntroBoxChat(3001500)
sm.setSpeakerType(3)
sm.sendNext("#face3#(This debate is clearly not leaning in my favor. Maybe I should just go with my original plan to work the rope loose and make a break for it.)")


sm.sendDelay(300)


sm.blind(0, 0, 0, 0, 0, 300, 0)
sm.setFieldFloating(402090001, 10, 10, 10)
sm.sendDelay(500)


sm.setFieldFloating(402090001, 0, 0, 0)
sm.zoomCamera(0, 1000, 0, -300, 70)


sm.sendDelay(500)


sm.setFieldFloating(402090001, 3, 3, 3)
sm.zoomCamera(0, 1000, 0, -300, 70)


sm.setIntroBoxChat(3001508)
sm.setSpeakerType(3)
sm.sendNext("#face2#The alarm?! Monsters again... Get ready, everyone! Now! We must defend the refuge!")


sm.setIntroBoxChat(3001508)
sm.setSpeakerType(3)
sm.sendSay("#face2#Anyone who can't fight needs to find a safe place to hide. Anyone who can, follow me!")


sm.setFieldFloating(402090001, 30, 30, 30)
sm.sendDelay(500)


sm.setFieldFloating(402090001, 3, 3, 3)
sm.zoomCamera(0, 1000, 0, -300, 70)


sm.sendDelay(500)


# [STOP_AMBIENT_SOUND] [14 00 41 6D 62 69 65 6E 63 65 2E 69 6D 67 2F 77 61 72 6E 69 6E 67 ]
sm.playSound("Sound/SoundEff.img/blackHeaven/secretmission3", 100)
sm.changeBGM("Bgm28.img/helisiumWarcry", 0, 0)
sm.setIntroBoxChat(3001510)
sm.setSpeakerType(3)
sm.sendNext("#face2#It's too late! Look!")


sm.sendDelay(500)


sm.setFieldFloating(402090001, 0, 0, 0)
sm.zoomCamera(1000, 2000, 1000, -1000, 70)


sm.sendDelay(1000)


sm.setFieldFloating(402090001, 3, 3, 3)
sm.sendNpcController(OBJECT_2, False)
sm.sendNpcController(OBJECT_8, False)
sm.sendNpcController(OBJECT_9, False)
sm.sendNpcController(OBJECT_10, False)
sm.sendNpcController(OBJECT_11, False)
sm.sendNpcController(OBJECT_12, False)
sm.sendNpcController(OBJECT_13, False)
OBJECT_14 = sm.sendNpcController(3001527, -1450, -273)
sm.showNpcSpecialActionByObjectId(OBJECT_14, "summon", 0)
OBJECT_15 = sm.sendNpcController(3001528, -1430, -273)
sm.showNpcSpecialActionByObjectId(OBJECT_15, "summon", 0)
OBJECT_16 = sm.sendNpcController(3001527, -1420, -273)
sm.showNpcSpecialActionByObjectId(OBJECT_16, "summon", 0)
OBJECT_17 = sm.sendNpcController(3001528, -1400, -273)
sm.showNpcSpecialActionByObjectId(OBJECT_17, "summon", 0)
OBJECT_18 = sm.sendNpcController(3001527, -1380, -273)
sm.showNpcSpecialActionByObjectId(OBJECT_18, "summon", 0)
OBJECT_19 = sm.sendNpcController(3001528, -1370, -273)
sm.showNpcSpecialActionByObjectId(OBJECT_19, "summon", 0)
OBJECT_20 = sm.sendNpcController(3001527, -1350, -273)
sm.showNpcSpecialActionByObjectId(OBJECT_20, "summon", 0)
OBJECT_21 = sm.sendNpcController(3001528, -1300, -273)
sm.showNpcSpecialActionByObjectId(OBJECT_21, "summon", 0)
OBJECT_22 = sm.sendNpcController(3001527, -1450, -78)
sm.showNpcSpecialActionByObjectId(OBJECT_22, "summon", 0)
OBJECT_23 = sm.sendNpcController(3001528, -1430, -78)
sm.showNpcSpecialActionByObjectId(OBJECT_23, "summon", 0)
OBJECT_24 = sm.sendNpcController(3001527, -1420, -78)
sm.showNpcSpecialActionByObjectId(OBJECT_24, "summon", 0)
OBJECT_25 = sm.sendNpcController(3001528, -1410, -78)
sm.showNpcSpecialActionByObjectId(OBJECT_25, "summon", 0)
OBJECT_26 = sm.sendNpcController(3001527, -1350, -78)
sm.showNpcSpecialActionByObjectId(OBJECT_26, "summon", 0)
OBJECT_27 = sm.sendNpcController(3001528, -1320, -78)
sm.showNpcSpecialActionByObjectId(OBJECT_27, "summon", 0)
OBJECT_28 = sm.sendNpcController(3001527, -1300, -78)
sm.showNpcSpecialActionByObjectId(OBJECT_28, "summon", 0)
OBJECT_29 = sm.sendNpcController(3001528, -1280, -78)
sm.showNpcSpecialActionByObjectId(OBJECT_29, "summon", 0)
OBJECT_30 = sm.sendNpcController(3001527, -1350, 150)
sm.showNpcSpecialActionByObjectId(OBJECT_30, "summon", 0)
OBJECT_31 = sm.sendNpcController(3001528, -1120, 150)
sm.showNpcSpecialActionByObjectId(OBJECT_31, "summon", 0)
OBJECT_32 = sm.sendNpcController(3001527, -1200, 150)
sm.showNpcSpecialActionByObjectId(OBJECT_32, "summon", 0)
OBJECT_33 = sm.sendNpcController(3001528, -1480, 150)
sm.showNpcSpecialActionByObjectId(OBJECT_33, "summon", 0)
sm.moveNpcByObjectId(OBJECT_14, False, 600, 300)
sm.moveNpcByObjectId(OBJECT_15, False, 400, 300)
sm.moveNpcByObjectId(OBJECT_16, False, 500, 300)
sm.moveNpcByObjectId(OBJECT_17, False, 400, 300)
sm.moveNpcByObjectId(OBJECT_18, False, 600, 300)
sm.moveNpcByObjectId(OBJECT_19, False, 400, 300)
sm.moveNpcByObjectId(OBJECT_20, False, 700, 300)
sm.moveNpcByObjectId(OBJECT_21, False, 400, 300)
sm.moveNpcByObjectId(OBJECT_22, False, 500, 300)
sm.moveNpcByObjectId(OBJECT_23, False, 400, 300)
sm.moveNpcByObjectId(OBJECT_24, False, 400, 300)
sm.moveNpcByObjectId(OBJECT_25, False, 500, 300)
sm.moveNpcByObjectId(OBJECT_26, False, 400, 300)
sm.moveNpcByObjectId(OBJECT_27, False, 600, 300)
sm.moveNpcByObjectId(OBJECT_28, False, 400, 300)
sm.moveNpcByObjectId(OBJECT_29, False, 700, 300)
sm.moveNpcByObjectId(OBJECT_30, False, 300, 300)
sm.moveNpcByObjectId(OBJECT_31, False, 200, 300)
sm.moveNpcByObjectId(OBJECT_32, False, 400, 300)
sm.moveNpcByObjectId(OBJECT_33, False, 400, 300)
sm.showNpcSpecialActionByObjectId(OBJECT_14, "move", -1)
sm.showNpcSpecialActionByObjectId(OBJECT_15, "move", -1)
sm.showNpcSpecialActionByObjectId(OBJECT_16, "move", -1)
sm.showNpcSpecialActionByObjectId(OBJECT_17, "move", -1)
sm.showNpcSpecialActionByObjectId(OBJECT_18, "move", -1)
sm.showNpcSpecialActionByObjectId(OBJECT_19, "move", -1)
sm.showNpcSpecialActionByObjectId(OBJECT_20, "move", -1)
sm.showNpcSpecialActionByObjectId(OBJECT_21, "move", -1)
sm.showNpcSpecialActionByObjectId(OBJECT_22, "move", -1)
sm.showNpcSpecialActionByObjectId(OBJECT_23, "move", -1)
sm.showNpcSpecialActionByObjectId(OBJECT_24, "move", -1)
sm.showNpcSpecialActionByObjectId(OBJECT_25, "move", -1)
sm.showNpcSpecialActionByObjectId(OBJECT_26, "move", -1)
sm.showNpcSpecialActionByObjectId(OBJECT_27, "move", -1)
sm.showNpcSpecialActionByObjectId(OBJECT_28, "move", -1)
sm.showNpcSpecialActionByObjectId(OBJECT_29, "move", -1)
sm.sendDelay(1000)


sm.setFieldFloating(402090001, 0, 0, 0)
sm.zoomCamera(1000, 2000, 1000, -750, 150)


sm.sendDelay(1000)


sm.setFieldFloating(402090001, 3, 3, 3)
sm.sendDelay(500)


sm.setIntroBoxChat(3001508)
sm.setSpeakerType(3)
sm.sendNext("#face1#Everyone, RUN!!")


sm.sendDelay(1000)


sm.blind(1, 255, 0, 0, 0, 500, 0)
sm.sendDelay(500)


sm.sendDelay(1000)


sm.setIntroBoxChat(3001508)
sm.setSpeakerType(3)
sm.sendNext("#face2#I'm sorry about the misunderstanding! I'll let you go. Hurry!")


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
sm.sendNpcController(OBJECT_3, False)
sm.sendNpcController(OBJECT_4, False)
sm.sendNpcController(OBJECT_5, False)
sm.sendNpcController(OBJECT_6, False)
sm.sendNpcController(OBJECT_7, False)
sm.sendNpcController(OBJECT_14, False)
sm.sendNpcController(OBJECT_15, False)
sm.sendNpcController(OBJECT_16, False)
sm.sendNpcController(OBJECT_17, False)
sm.sendNpcController(OBJECT_18, False)
sm.sendNpcController(OBJECT_19, False)
sm.sendNpcController(OBJECT_20, False)
sm.sendNpcController(OBJECT_21, False)
sm.sendNpcController(OBJECT_22, False)
sm.sendNpcController(OBJECT_23, False)
sm.sendNpcController(OBJECT_24, False)
sm.sendNpcController(OBJECT_25, False)
sm.sendNpcController(OBJECT_26, False)
sm.sendNpcController(OBJECT_27, False)
sm.sendNpcController(OBJECT_28, False)
sm.sendNpcController(OBJECT_29, False)
sm.sendNpcController(OBJECT_30, False)
sm.sendNpcController(OBJECT_31, False)
sm.sendNpcController(OBJECT_32, False)
sm.sendNpcController(OBJECT_33, False)
sm.warp(402000615, 0)