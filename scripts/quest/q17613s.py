# Created by MechAviv
# Quest ID :: 17613
# [Commerci Republic] The Minister's Son

sm.setNpcOverrideBoxChat(9390201)
sm.sendNext("As it happens, you's not the only visitor to Berry today. There's that fancy-lookin' fella. The Prime Minister's boy, calls himself #bLeon Daniella#k. He's got a head like an ice cream cone with all that goop in his hair, but he seems like a good enough kid. Maybe you two's'll get along!")


sm.setSpeakerID(9390201)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("Thanks, Mayor Berry. I owe you one.")


sm.setNpcOverrideBoxChat(9390201)
sm.sendSay("See? Just as I thought, you's as honest as a pig at a barbecue! And such a good'n too. Well, you should go 'n get youself acquainted with that Daniella boy if you's lookin' to talk to his pappy!")


sm.setSpeakerID(9390201)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("#b(It seems like there's more to the Commerci Republic after all.)#k")


sm.setSpeakerID(9390201)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("#b(According to Mayor Berry, San Commerci is a huge, busy city. It's hard to imagine that after landing in the quiet little village of Berry... #eYou should hurry and meet with the son of Commerci's Prime Minister.#n)#k")


sm.startQuest(17613)