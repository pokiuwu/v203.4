# q25712s - Kaiser 4th job advancement

if chr.getJob() == 6111:
    sm.setJob(6112)
    sm.addSP(5)
    sm.addAP(5)
    sm.completeQuest(25712)
else:
    sm.sendSayOkay("You're currently not a third job Kaiser.")
sm.dispose()
