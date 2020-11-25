# Created by MechAviv
if sm.getFieldID() == 302090000:
    # ID :: [302090000]
    # Stormy Forest : White Mage Prologue
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, True, False, False)
    sm.blind(1, 255, 0, 0, 0, 0, 0)
    sm.sayMonologue("I am a wandering mercenary.\r\n                ", False)
    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/0", 100)


    sm.sayMonologue("Looking back, it was a life that could end anywhere, at anytime.", False)
    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/1", 100)


    sm.sayMonologue("The sun will set and the wind will blow, and someday I will be lost to history.", False)
    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/2", 100)


    sm.sayMonologue("\r\n\r\n#fnCentury Gothic#―――――――#fnArial Bold#And perhaps...today will be that day.", True)
    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/3", 100)


    sm.blind(1, 255, 0, 0, 0, 0, 0)
    sm.sendDelay(1200)


    sm.blind(0, 0, 0, 0, 0, 1000, 0)
    sm.sendDelay(1400)


    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendNext("...")


    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendSay("What happened to everyone? His followers trusted him...!")
    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/4", 100)


    sm.moveCamera(False, 300, 450, 185)


    sm.sendDelay(4520)


    sm.moveCamera(False, 300, -906, 185)


    sm.sendDelay(4520)


    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendNext("Were his words all lies?")
    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/5", 100)


    sm.blind(1, 200, 0, 0, 0, 1300, 0)
    sm.sendDelay(1600)


    sm.sayMonologue("Where shall I start...?", True)
    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/7", 100)


    sm.sayMonologue("...Yes. Let's begin when I first heard about the White Mage.", True)
    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/8", 100)


    sm.blind(1, 255, 0, 0, 0, 500, 0)
    sm.sendDelay(500)


    sm.sendDelay(1200)


    sm.sayMonologue("#fs40#Episode I: The White Mage", True)


    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)
    sm.warp(302090100, 0)
elif sm.getFieldID() == 302090100:
    # ID :: [302090100]
    # Ariant : Middle of the Desert
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, True, False, False)
    sm.moveCamera(False, 0, -205, -500)


    sm.blind(1, 255, 0, 0, 0, 0, 0)
    sm.sendDelay(1200)


    sm.blind(0, 0, 0, 0, 0, 1000, 0)
    sm.sendDelay(1400)


    sm.blind(1, 200, 0, 0, 0, 1300, 0)
    sm.sendDelay(1600)


    sm.sayMonologue("I was always alone.", False)
    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/9", 100)


    sm.sayMonologue("My weapons were the only thing I trusted. I had to put my life on the line just to eat.", False)
    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/10", 100)


    sm.sayMonologue("\r\n    I am lucky enough to be alive today, but...I can't say that about all my friends.", True)
    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/11", 100)


    sm.sayMonologue("As I learned to fight, I also learned not to fear the end.", True)
    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/12", 100)


    sm.moveCamera(False, 100, -205, 200)


    sm.sayMonologue("War and famine, plagues and disasters... The world was surely falling apart.", False)
    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/13", 100)


    sm.sayMonologue("And the rich lined their pockets with comfort as they watched it happen. The worst of them was Hatsar, the mogul of Ariant.", True)
    sm.playExclSoundWithDownBGM("Voice.img/Library/Mercenary/M/14", 100)


    sm.blind(0, 0, 0, 0, 0, 1300, 0)
    sm.sendDelay(1600)


    OBJECT_1 = sm.sendNpcController(2510001, -900, 200)
    sm.showNpcSpecialActionByObjectId(OBJECT_1, "summon", 0)
    sm.sendDelay(3000)


    sm.setSpeakerID(2510001)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendNext("*Huff huff* Hey, are you the servant that Hatsar sent? To escort me to Ariant?")


    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendSay("...What? It's mercenary, not servant.")


    sm.setSpeakerID(2510001)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSay("Hey, nothing wrong with being a servant! Anyway, good. Do you have some water? It's rough having to carry all these myself.")


    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)
    sm.startQuest(32629)
    sm.giveExp(25968)
    sm.completeQuest(32629)
    sm.sendNpcController(OBJECT_1, False)
    sm.warp(302010000, 0)
