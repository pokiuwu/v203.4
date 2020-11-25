# 22002 |   Sandwich for Breakfast (Evan intro)
sm.setSpeakerID(1013101)
sm.sendNext("Did you feed #p1013102#? You should have some breakfast now then, Evan. Today's breakfast is a #t2022620#. I've brought it with me. Hee hee. I was going to eat it myself if you didn't agree to feed #p1013102#.")
if sm.sendAskAccept("Here, I'll give you this #bSandwich#k, so #bgo talk to mom when you finish eating#k. She says she has something to tell you."):
    if sm.canHold(2022620):
        sm.startQuest(parentID)
        sm.giveItem(2022620)
        sm.setPlayerAsSpeaker()
        sm.sendNext("#b(Mom has something to say? Eat your #t2022620# and head back inside the house.)#k")
        sm.sendSayImage("UI/tutorial/evan/3/0")
        sm.dispose()
    else:
        sm.sendNext("Please make room in your Use inventory.")
        sm.dispose()
else:
    sm.sendNext("Oh, what? Aren't you going to have breakfast? Breakfast is the most important meal of the day! Talk to me again if you change your mind. If you don't, I'm going to eat it myself.")
    sm.dispose()

