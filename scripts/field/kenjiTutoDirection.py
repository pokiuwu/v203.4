# Created by MechAviv

# Unknown : Unknown
if sm.getFieldID() == 807100010:
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, False, False, False)
    sm.setStandAloneMode(True)
    sm.showFieldEffect("JPKenji/text0", 0)
    sm.sendDelay(7000)


    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)
    sm.warp(807100000, 1)
# Honnou-ji : Honnou-ji Eastern Wall
elif sm.getFieldID() == 807100000:
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, False, False, False)
    sm.forcedInput(1)
    sm.sendDelay(4300)


    sm.forcedInput(0)
    sm.setIntroBoxChat(9130000)
    sm.setSpeakerType(3)
    sm.sendNext("Finally, the time has come! Today, we will put an end to the so-called Demon King. Today we wipe Oda Nobunaga from history!")


    sm.setSpeakerID(9131007)
    sm.removeEscapeButton()
    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.setColor(1)
    sm.setSpeakerType(3)
    sm.sendSay("My hands shake with anticipation. The disgrace of the Matsuyama clan will haunt me no more.")


    sm.setIntroBoxChat(9130000)
    sm.setSpeakerType(3)
    sm.sendSay("Clear your head, falcon. You get all emotional on me, and I'll shave your head bald.")


    sm.setSpeakerID(9131007)
    sm.removeEscapeButton()
    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.setColor(1)
    sm.setSpeakerType(3)
    sm.sendSay("The Mist Cutter yearns for vengeance. That is the only emotion I feel.")


    sm.setIntroBoxChat(9130000)
    sm.setSpeakerType(3)
    sm.sendSay("Hahahaha! You're just as serious as I'd heard you were. I like that. How about you start the attack on Honnou-ji?")


    sm.setSpeakerID(9131007)
    sm.removeEscapeButton()
    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.setColor(1)
    sm.setSpeakerType(3)
    sm.sendSay("The Eastern Door?")


    sm.setIntroBoxChat(9130000)
    sm.setSpeakerType(3)
    sm.sendSay("Get over the wall and open up the eastern gate. My cavalry will be waiting for you on the other side, ready to trample the enemy into submission. ")


    sm.setSpeakerID(9131007)
    sm.removeEscapeButton()
    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.setColor(1)
    sm.setSpeakerType(3)
    sm.sendSay("All right, but I hope that your horsemen will forgive me when there are no enemies left to be trampled.")


    sm.setIntroBoxChat(9130000)
    sm.setSpeakerType(3)
    sm.sendSay("Haha. I wish I could adopt you! Good luck out there, soldier. Try not to beat them all!")


    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)
    sm.objectStateByString("guide1")
    sm.objectStateByString("guide2")
    sm.objectStateByString("guide3")
    sm.objectStateByString("guide4")
# Unknown : Unknown
elif sm.getFieldID() == 807100011:
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, False, False, False)
    sm.showFieldEffect("JPKenji/text1", 0)
    sm.sendDelay(7000)


    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)
    sm.warp(807100001, 1)
# Honnou-ji : Honnou-ji Eastern Grounds
elif sm.getFieldID() == 807100001:
    sm.startQuest(57101)
    sm.objectStateByString("guide1")
    sm.objectStateByString("guide2")
    sm.objectStateByString("guide3")
    sm.setIntroBoxChat(9131007)
    sm.sendNext("You did all right, samurai. I'll let you join my side for now.")


    sm.setSpeakerID(9131007)
    sm.removeEscapeButton()
    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.setColor(1)
    sm.sendSay("The battle goes well. I fear the Uesugi troops barged in too early. They may require assistance.")


    sm.setIntroBoxChat(9131007)
    sm.sendSay("Wouldn't surprise me. Kenshin couldn't keep her men under control if she had a stack of gold for each of them. It's not like her to be early though...")


    sm.setIntroBoxChat(9131007)
    sm.sendSay("It's not important. We're doing well so far and I hate to break good momentum. Men, prepare for the final charge! TO THE TEMPLE!")


    sm.setSpeakerID(9131007)
    sm.removeEscapeButton()
    sm.flipSpeaker()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setBoxChat()
    sm.setColor(1)
    sm.sendPrev("We shall meet again, Tiger of Kai!")


    sm.completeQuest(57101)
    sm.warp(807100012, 0)
# Unknown : Unknown
elif sm.getFieldID() == 807100012:
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, True, False, False)
    sm.showFieldEffect("JPKenji/text2", 0)
    sm.sendDelay(7000)


    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)
    sm.warp(807100003, 1)

# Honnou-ji : Honnou-ji Courtyard
elif sm.getFieldID() == 807100003:
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, True, False, False)
    sm.forcedInput(1)
    sm.sendDelay(2200)


    sm.forcedInput(0)
    sm.setSpeakerID(9131000)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSayNextIllustration("A little deer comes to meet the tiger? You cannot possibly belong to the Oda clan. You are far too unkempt. What brings you to Honnou-ji?", 9131000, True)


    sm.setSpeakerID(9131000)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendSay("(This woman appears delicate, but her voice is deep and rough with callous intent. Could she be one of Oda's?)")


    sm.setSpeakerID(9131000)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendSay("My name is Anegasaki Kenji, eldest son of Anegasaki Tomonobu, retainer to the Matsuyama clan. I have come to avenge my family and rescue the princess. Who are you, fair maiden?")


    sm.setSpeakerID(9131000)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSayIllustration("Hehe, am I truly so fair that you would mistake me for a maiden? I vaguely recall an unimportant family from the southwest named Matsuyama, though I thought I had put it from my memory long ago.", 9131000, True)


    sm.setSpeakerID(9131000)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendSay("Only Nobunaga's followers would be so sharp of tongue and empty of mind. I do not relish harming one so beautiful, but my katana is less discerning.")


    sm.setSpeakerID(9131000)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendSay("I will give you one last chance to tell me your name. Bear in mind, it will be the last thing you say on this plane of existence.")


    sm.setSpeakerID(9130000)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSayIllustration("No need to get caught up dealing with this louse, Hayato.", 9130000, True)


    OBJECT_2 = sm.sendNpcController(9131007, 135, 30)
    sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
    sm.setSpeakerID(9131007)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendSay("Master Shingen!")


    sm.setSpeakerID(9130000)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSayIllustration("Honnou-ji has fallen on worse times than I'd hoped. Though I can't say I'm surprised, now that I see one of Oda's Four Heavenly Kings standing before me. Wouldn't you agree, Akechi Mitsuhide?!", 9130000, True)


    sm.showEffect("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/0", 0, 0, -120, -2, -2, False, 0)
    sm.sendDelay(2000)


    sm.showEffect("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/3", 0, 0, -120, -2, -2, False, 0)
    sm.sendDelay(2000)


    sm.showEffect("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/4", 0, 0, -120, -2, -2, False, 0)
    sm.sendDelay(2000)


    sm.setSpeakerID(9131000)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSayNextIllustration("You are slightly less idiotic than your portrait made you out to be, Takeda Shingen. Yet you figured out what is going on here, and you figured out that I was the one who started this rebellion. Bravo, you goonish oaf. Bravo!", 9131000, True)


    sm.setSpeakerID(9130000)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSayIllustration("I've been told you're the kind of guy that'd stab his mother in the back for the right price. How about you and I team up, turn the tables on the Demon King?", 9130000, True)


    sm.setSpeakerID(9131007)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendSay("You would count yourself in league with this trickster?! This scoundrel who destroyed my family?! I will not let that happen! Prepare yourself, Akechi Mitsuhide!")


    sm.showEffect("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/5", 0, 0, -120, -2, -2, False, 0)
    sm.sendDelay(1000)


    sm.showEffect("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/6", 0, -100, -120, -2, -2, False, 0)
    sm.sendDelay(2000)


    sm.showEffect("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/7", 0, -100, -120, -2, -2, False, 0)
    sm.sendDelay(2000)


    sm.showEffect("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/8", 0, 0, -120, -2, -2, False, 0)
    sm.sendDelay(2000)


    sm.showEffect("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/9", 0, -100, -120, -2, -2, False, 0)
    sm.sendDelay(2000)


    sm.showEffect("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/10", 0, 0, -120, -2, -2, False, 0)
    sm.sendDelay(2000)


    sm.forcedInput(1)
    sm.sendDelay(1300)


    sm.forcedInput(0)
    sm.forcedAction(1752, 0)
    sm.showEffect("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/11", 0, -100, -120, -2, -2, False, 0)
    sm.sendDelay(2000)
    # Unhandled User Effect [PlayPortalSE] Packet: 0D


    sm.showEffect("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/12", 0, -100, -120, -2, -2, False, 0)
    sm.sendDelay(2000)


    sm.showEffect("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/13", 0, 150, -120, -2, -2, False, 0)
    sm.sendDelay(1400)


    sm.showNpcSpecialActionByObjectId(OBJECT_2, "attack", 0)
    sm.sendDelay(300)


    sm.showEffect("Effect/DirectionJP3.img/effect/kenjiTuto/shingenAttack/0", 0, 0, 0, -2, -2, False, 0)
    sm.sendDelay(400)


    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)
    sm.sendNpcController(OBJECT_2, False)
    sm.warp(807100004, 1)
elif sm.getFieldID() == 807100004:
    OBJECT_3 = sm.sendNpcEnterField(9131005, 187, 51)
    # [SET_ACTIVE_EMOTICON_ITEM] [F7 AE A9 00 00 00 00 00 00 00 00 00 ]
    sm.sendNpcController(OBJECT_3, True)
    # [FORCED_STAT_RESET] []
    # [MOMENT_AREA_OFF_ALL] [00 00 00 ]
    # [ENTER_FIELD_PSYCHIC_INFO] [00 ]
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, True, False, False)
    sm.sendDelay(1000)


    sm.setSpeakerID(9131005)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendNext("Please step aside, madam. I have no quarrel with you.")


    sm.setSpeakerID(9131005)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSayIllustration("How dare you speak to me in such a familiar tongue. I am Kichou, daughter of the Saitou Dousan, lawful wife of the Demon King!", 9131005, True)


    sm.setSpeakerID(9131005)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendSay("The tales of your beauty are not exaggerations, Lady No, but you must stand aside. Your husband is my sworn enemy.")


    sm.setSpeakerID(9131005)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSayIllustration("Cocky little brat! You will beg me for death before I am done!", 9131005, True)


    sm.showNpcSpecialActionByObjectId(OBJECT_3, "step", 0)
    sm.sendDelay(1080)


    sm.showEffect("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/14", 0, -100, -100, -2, -2, False, 0)
    sm.sendDelay(1000)


    sm.showEffect("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/15", 0, -150, -150, -2, -2, False, 0)
    sm.sendDelay(2000)


    sm.showEffect("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/16", 0, -150, -150, -2, -2, False, 0)
    sm.sendDelay(2000)


    sm.showEffect("Effect/DirectionJP3.img/effect/kenjiTuto/balloonMsg/17", 0, 0, -120, -2, -2, False, 0)
    sm.sendDelay(2000)


    sm.forcedInput(1)
    sm.sendDelay(1800)


    sm.forcedInput(5)
    sm.sendDelay(1000)


    sm.forcedInput(0)
    sm.sendDelay(500)


    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)
    sm.warp(807100005, 0)
# Unknown : Unknown
elif sm.getFieldID() == 807100005:
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, True, False, False)
    # Inventory Operation with 1 operations.
    sm.playURLVideoByScript("http://nxcache.nexon.net/maplestory/video/yt/JPHayato.html")


    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)
    sm.setStandAloneMode(False)
    sm.warp(807040000, 0)
