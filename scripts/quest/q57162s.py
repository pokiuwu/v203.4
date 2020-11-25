# Hayato 2nd job adv | 57162
sm.setPlayerAsSpeaker()
sm.sendNext("This battle is not over, yet I feel a sense of peace. This world is not so different from my own.")
sm.sendNext("I feel like my old self, yet stronger. I believe this world holds many new challenges for me.")
sm.sendNext("I have not yet regained all of my Battoujutsu skills, but one day they will return. One day, the falcon will take flight once again.")
if sm.canHold(1142491):
    sm.giveItem(1142491)
    sm.startQuest(parentID)
    sm.completeQuest(parentID)
    sm.setJob(4110)
    sm.addSP(5)
    sm.dispose()
else:
    sm.sendNext("Please make space in your Equip inventory.")
    sm.dispose()