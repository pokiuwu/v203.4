# 23023 - 2nd job advancement Battle Mage
from net.swordie.ms.enums import Stat

sm.setSpeakerID(2151001)
if sm.hasItem(4032737, 1) and chr.getJob() == 3200:
    sm.setJob(3210)
    sm.addSP(5)
    sm.completeQuest(23023)
    sm.sendSayOkay("Good job on finding the report. I've molded you into the next level of being a Battle Mage.")
else:
    sm.sendSayOkay("I still think you're missing the report.")
sm.dispose()
