# q25710s - Kaiser 2nd job advancement

if chr.getJob() == 6100:
    sm.setJob(6110)
    sm.addSP(4)
    sm.addAP(5)
    sm.completeQuest(25710)
else:
    sm.sendSayOkay("You're currently not a first job Kaiser.")
sm.dispose()
