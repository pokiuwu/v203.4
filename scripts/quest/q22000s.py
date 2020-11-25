# 22000 |   Strange dream (Evan intro)
sm.setSpeakerID(1013100)
sm.sendNext("Did you sleep well, Evan?")

sm.setPlayerAsSpeaker()
sm.sendSay("#bYes, what about you, mom?")
sm.setSpeakerID(1013100)
sm.sendSay("I did as well, but you seem so tired. Did you sleep well? Did the thunder and lightning last night keep you up?")
sm.setPlayerAsSpeaker()
sm.sendSay("#bOh, no. It's not that, Mom. I just had a strange dream last night.")
sm.setSpeakerID(1013100)
sm.sendSay("A strange dream? What kind of strange dream?")
sm.setPlayerAsSpeaker()
sm.sendSay("#bWell...")
sm.sendSay("#b(You explain that you met a Dragon in your dream.)")
sm.setSpeakerID(1013100)
if sm.sendAskAccept("Hahaha, a Dragon? That's incredible. I'm glad he didn't swallow you whole! You should tell Utah about your dream. I'm sure he'll enjoy it"):
    sm.startQuest(22000)
    sm.sendNext("#b#p1013101##k went to the #b#m100030102##k to feed the Bull Dog. You'll see him right outside.")
    sm.dispose()
else:
    sm.sendNext("Hm? Don't you want to tell #p1013101#? You have to be nice to your brother, dear.")
    sm.dispose()


