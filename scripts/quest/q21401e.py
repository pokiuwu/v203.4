# 21401 - [Job Adv] (Lv.100)   Aran
sm.setSpeakerID(1201002)
sm.sendNext("Thank you, Aran. If it weren't for you, I would have become enraged and who knows what could have happened. Thank you NOT! It's only your duty as my mster...")
if sm.sendAskYesNo("Anyways, I just noticed how high of a level you've reached. If you were able to control me in my state of rage, I think you're ready to handle more abilities"):
    sm.giveItem(1142132)
    sm.setJob(2112)
    sm.addSP(5)
    sm.completeQuest(parentID)
    sm.sendNext("Your skills have been restored. Those skills have been dormant for so long that you'll have to re-train yourself. but you'll be as good as new once you complete your training.")
    sm.sendNext("Even with all that, however, you still have a long way to go until you return to the old you.")
    sm.dispose()
else:
    sm.dispose()
