# 22003 |   Delivering the Lunch Box (Evan intro)
sm.setSpeakerID(1013100)
if sm.sendAskAccept("Your #bDad#k forgot his Lunch Box when he left for the farm this morning. Will you #bdeliver this Lunch Box#k to your Dad in #b#m100030300##k, honey?"):
    if sm.canHold(4032448):
        sm.giveItem(4032448, 1)
        sm.startQuest(parentID)

        sm.sendNext("Heehee, my Evan is such a good kid! Head #bleft after you exit the house#k. Rush over to your dad. I'm sure he's starving.")
        sm.sendSay("Come back to me if you happen to lose the Lunch Box. I'll make his lunch again.")
        sm.sendSayImage("UI/tutorial/evan/5/0")
        sm.dispose()
    else:
        sm.sendNext("Please make room in your Etc Inventory.")
        sm.dispose()
else:
    sm.sendNext("Good kids listen to their mothers. Now, Evan, be a good kid and talk to me again.")
    sm.dispose()

