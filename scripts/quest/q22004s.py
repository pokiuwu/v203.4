# 22004 |   Fixing the Fence (Evan intro)
sm.setSpeakerID(1013103)
sm.sendNext("The #o1210100#s at the farm have been acting strange these past couple days. They've been angry and irritable for no reason. I was worried so I came out to the farm early this morning and sure enough, it seems like a few of these #o1210100#s got past the fence.")
if sm.sendAskAccept("Before I go and find the #o1210100#s, I should mend the broken fence. Luckily, it wasn't damaged too badly. I just need a few #t4032498#es to fix it right up. Will you bring me #b3#k #b#t4032498#es#k, Evan?"):
    sm.startQuest(parentID)
    sm.sendNext("Oh, that's very nice of you. You'll be able to find #b#t4032498#es#k from the nearby #r#o0130100#s#k. They're not too strong, but use your skills and items when you find yourself in danger.")

    sm.sendSayImage("UI/tutorial/evan/6/0")
else:
    sm.sendNext("Hm, #p1013101# would have done it at the drop of a hat.")
    sm.dispose()
