# 23033 - BaM 3rd job advancement
from net.swordie.ms.enums import Stat

sm.setSpeakerID(2151001)
if chr.getJob() == 3210 and chr.getLevel() >= 60:
    sm.setJob(3211)
    sm.addSP(5)
    sm.completeQuest(23033)
    sm.sendSayOkay("Good job on defeating the conductor device. You have advanced a job level, and I have given you some SP.")
else:
    sm.sendSayOkay("You do not meet the requirements to advance to the third job.")
sm.dispose()
