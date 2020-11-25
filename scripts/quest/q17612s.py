# Created by MechAviv
# Quest ID :: 17612
# [Commerci Republic] The Problem with Presumptions

sm.setSpeakerID(9390201)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendNext("Excuse me, Mayor? D-do you have a moment?")


sm.setNpcOverrideBoxChat(9390201)
sm.sendSay("Well you done stopped by at a mighty fine time! Ain't it a lovely day? It's been a fish-full day, I tell you what, and that's the best kind there is, far as I's concerned!")


sm.setSpeakerID(9390201)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("Uh... great! There's something I need to tell you.")


sm.setIntroBoxChat(9390201)
selection = sm.sendNext("Well, go on an' spit it out!\r\n#b\r\n#L0# (I should rethink this.)#l\r\n#L1# (There's no better time to tell him the truth.)#l")
# if selection == 1: todo: selection 0

sm.setSpeakerID(9390201)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendNext("I uhh, lied about being a tourist. I'm really here as a representative of Empress Cygnus to extend a formal offer of friendship.")


sm.setNpcOverrideBoxChat(9390201)
sm.sendSay("Say what now? You ain't no tourist... but a representative o' that fancy Syggus lady?")


sm.setSpeakerID(9390201)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("Uh... Sure, close enough... I was worried you might mistrust me if I told you my real mission on our first meeting. But after you treated me so kindly after I was shipwrecked, I knew I couldn't keep it from you any longer.")


sm.setNpcOverrideBoxChat(9390201)
sm.sendSay("Well, that's all well an' good, and I'm tickled that you'd say that... But why is you troublin' youself with ol' Mayor Berry? After all, I's just a Mayor of this lil' village.")


sm.setSpeakerID(9390201)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("But aren't you... in charge? I was told to speak with the highest official in Commerci.")


sm.setNpcOverrideBoxChat(9390201)
sm.sendSay("Uh-hehehe! Well ain't you as confused as a toad in a bird's nest! I think you's havin' a bit of a misunderstandin'.")


sm.setSpeakerID(9390201)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("Uh... what do you mean?")


sm.setNpcOverrideBoxChat(9390201)
sm.sendSay("Sure, I's the most officialest person in this here village, but are you under the impreshin' that Berry is the only place in the whole o' Commerci? This right here is just one small fishin' village. Why, you oughtta head down to #e#bSan Commerci#n#k, the #ecapital#n of the Republic, and talk to them folks!")


sm.setSpeakerID(9390201)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("San... Commerci?")


sm.setNpcOverrideBoxChat(9390201)
sm.sendSay("That's right! The fella you's lookin to see goes by the name o' #bGilberto Daniella#k. He's the Prime Minister of the whole darn Commerci Republic. That's the one you wanna deliver Ms. Syggus' message to.")


sm.setSpeakerID(9390201)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("So... San Commerci is the biggest village in Commerci, then?")


sm.setNpcOverrideBoxChat(9390201)
sm.sendSay("A village? Youngster, I think that storm might've bumped your noggin' somethin' fierce. San Commerci is a city! A downright bustlin' metropolis, even! Now everybody knows that San Commerci is the capital of the Commerci Republic! It's the place all roads lead to.")


sm.setNpcOverrideBoxChat(9390201)
sm.sendSay("I can't understand how folks stand to live cooped up like sardines... They oughtta come here. Berry Village is the happiest place in Commerci!")


sm.setNpcOverrideBoxChat(9390201)
sm.sendSay("Hmm. You may want to have a word with the Prime Minister's boy. He's servin' as captain for one of them big ships what the #bDaniella Merchant Union#k gots docked in the city. He just so happens to be staying right here in Berry!")


sm.setNpcOverrideBoxChat(9390201)
sm.sendSay("I'd like you to have this. I know you ain't really no tourist, but consider it a souvenir of your time in Berry Village!")


sm.setNpcOverrideBoxChat(9390201)
sm.sendSay("Consider it a gift from ol' Berry. I'll be cheerin' for you youngster!")


sm.giveItem(1003984) # Commerci Hat
# Unhandled Message [INC_COMMITMENT_MESSAGE] Packet: 09 01 00 00 00 00
sm.startQuest(17612)
sm.completeQuest(17612)
sm.giveExp(630724)
# [GUILD_RESULT] [74 F9 DA 01 00 82 02 00 00 03 00 00 00 BA 00 00 00 ]
# [GUILD_RESULT] [6D F9 DA 01 00 1E 4F 85 00 82 02 00 00 0D 00 00 00 B2 01 00 00 00 AD D5 22 37 ED D4 01 ]
# Update Quest Record EX | Quest ID: [18418] | Data: B=1335