# Created by MechAviv
# Quest ID :: 17608
# [Commerci Republic] After a Pleasant Voyage

sm.setSpeakerID(9390201)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendNext("Excuse me... Hi. Could I ask you a question?")


sm.setNpcOverrideBoxChat(9390201)
sm.sendSay("Dear me, you look about as healthy as a gutted guppy! Oh, I don't mean no insult. You run into a a bit of weather out there? You should thank your lucky stars you landed here in one piece!")


sm.setSpeakerID(9390201)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("Yeah...  I got on a ship headed to Commerci, but ended up getting lost in a storm. Could you please tell me where I am, and how to get to Commerci from here?")


sm.setNpcOverrideBoxChat(9390201)
sm.sendSay("Haha, well lucky dog! Please allow me to welcome you... to Commerci! That's right, this whole land yer standin' on here is part of Commerci. This here is Berry village, the happiest place in Commerci! Uh-hehehe!")


sm.setSpeakerID(9390201)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("Oh... really? I'm glad to hear that I made it here after all.")


sm.setSpeakerID(9390201)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("#b(This little fishing village is the Commerci Republic? I guess there's no truth to the rumors.)#k")


sm.setNpcOverrideBoxChat(9390201)
sm.sendSay("Oh, I haven't introduced myself! My name is Berry, and I am the mayor of this here village. I carry on a great tradition of mayoring just like my pappy, my grandpappy before him, his pappy, and so on. All of us were Berries, and that's how-come our village is called as such. Uh-hehehe!")


sm.setNpcOverrideBoxChat(9390201)
sm.sendSay("If you's here lookin' for Commerci, consider youself my honored guest! Say, what brings you to Commerci, anyhow?")


sm.setSpeakerID(9390201)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("Well you see, I'm...")


sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, False, False, False)
sm.blind(1, 120, 0, 0, 0, 500, 0)
sm.setIntroBoxChat(1540451)
sm.sendSay("#face1##b(...To ensure there are no misunderstandings, we must approach this matter with the utmost care. We should gain their trust before we relay the Empress' proposal...)#k")


sm.blind(0, 0, 0, 0, 0, 500, 0)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
sm.setSpeakerID(9390201)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("#b(Neinheart said I shouldn't reveal my mission right away. I should start by earning their trust... I mean, this is no 'republic,' but I made the Empress a promise.)#k")


sm.setSpeakerID(9390201)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("I'm a... uh, tourist. I booked a vacation cruise to Commerci, but I think our ship may have been capsized by that storm. You know, before it arrived in this... uhh... amazing and, and uh, beautiful land. I'm glad to be alive, although my festive shirts were sadly lost at sea...")


sm.setNpcOverrideBoxChat(9390201)
sm.sendSay("Wow! You really done canceled lunch with the reaper! Well, any tourist is a friend of mine! I'll do what I can to make sure you have a pleasurable stay. Seein' as you been through so much, you should rest up! Please, make youself at home.")


sm.setSpeakerID(9390201)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("Really? But we've just met... you're going to trust me just like that?")


sm.setNpcOverrideBoxChat(9390201)
sm.sendSay("With age comes wisdom, youngster. I can tell you's a good person just by usin' these old peepers. Now, enjoy yourself! N' let me know if'n there's anything you need.")


sm.setSpeakerID(9390201)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("#b(...He seems nice enough. This person claims to be the Mayor, so I guess I could deliver the Empress's message to him. I'll wait to make sure he really trusts me before I bring up the peace treaty.)#k")


# Unhandled Message [INC_COMMITMENT_MESSAGE] Packet: 09 01 00 00 00 00
sm.completeQuest(17608)
sm.giveExp(630724)
# [GUILD_RESULT] [74 F9 DA 01 00 7B 02 00 00 03 00 00 00 BA 00 00 00 ]
# [GUILD_RESULT] [6D F9 DA 01 00 1E 4F 85 00 7B 02 00 00 06 00 00 00 B2 01 00 00 C0 98 E0 D6 35 ED D4 01 ]
# Update Quest Record EX | Quest ID: [18418] | Data: B=1328