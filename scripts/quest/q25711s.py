# q25711s - Kaiser 3rd job advancement

if chr.getJob() == 6110:
    sm.setJob(6111)
    sm.addSP(4)
    sm.addAP(5)
    sm.completeQuest(25711)
else:
    sm.sendSayOkay("You're currently not a second job Kaiser.")
sm.dispose()
