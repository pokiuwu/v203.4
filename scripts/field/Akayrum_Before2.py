# Dimensional schism : Arkarium's altar (272020210)
from net.swordie.ms.constants import GameConstants

if int(sm.getQRValue(GameConstants.ARKARIUM_QUEST)) == 2:
    sm.spawnMob(8860005, 28, -181, False)
elif int(sm.getQRValue(GameConstants.ARKARIUM_QUEST)) == 3:
    sm.spawnMob(8860000, 28, -181, False)


