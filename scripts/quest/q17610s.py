# Created by MechAviv
# Quest ID :: 17610
# [Commerci Republic] Berry Concerned 1

sm.setSpeakerID(9390201)
sm.sendNext("Hm... ")


sm.setSpeakerID(9390201)
sm.setPlayerAsSpeaker()
sm.sendSay("Mayor Berry, are you all right?")


sm.setSpeakerID(9390201)
sm.sendSay("Ah... you feelin' good? Don't mind me, I'm just dealin' with a pest...")


sm.setSpeakerID(9390201)
sm.setPlayerAsSpeaker()
sm.sendSay("What's wrong? I'll help you out if I can.")


sm.setSpeakerID(9390201)
if sm.sendAskYesNo("Really? You'd take the time to help an old fisherman?"):
    sm.setSpeakerID(9390201)
    sm.sendNext("Well, if you really wanna help, I've got me a cat problem.")

    sm.setSpeakerID(9390201)
    sm.sendSay("There's a group of them, wanderin' around, stealin' fish and gold and whatever else they can get their mangy paws on.")

    sm.setSpeakerID(9390201)
    sm.sendSay("I wanna see those #b#o9390807#s#k get a whippin'! Could ya get 100 of them rounded up for me?")

    sm.startQuest(17610)
else:
    sm.setSpeakerID(9390201)
    sm.sendSayOkay("Oh, I know you young folk've got lots to do. I'll just wait around here until all my livelihood's been stolen.")