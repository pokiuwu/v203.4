# 23011 - Battle mage 1st job advancement quest
from net.swordie.ms.enums import Stat

sm.setSpeakerID(2151001)
if chr.getJob() == 3000 and chr.getLevel() >= 10:
    sm.setJob(3200)
    sm.addSP(5)
    sm.completeQuest(23011)
    sm.giveItem(1382000, 1)
    sm.sendSayOkay("Congratulations, you are now a battle mage! I have given you some SP and items to start out with, enjoy!")
else:
    sm.sendSayOkay("I don't think you are quite ready to become a Battle Mage.")
sm.dispose()
