# 23049 - BaM 4th job advancement
from net.swordie.ms.enums import Stat

sm.setSpeakerID(2159110)
if chr.getJob() == 3211 and chr.getLevel() >= 100:
    sm.setJob(3212)
    sm.addSP(5)
    sm.completeQuest(23049)
    sm.chat("[Briston] You... have outclassed me.")
    sm.warp(310000000)
else:
    sm.sendSayOkay("You do not meet the requirements to advance.")
sm.dispose()
