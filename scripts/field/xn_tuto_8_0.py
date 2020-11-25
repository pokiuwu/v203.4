# Created by MechAviv
# Map ID :: 931050960
# Classified Lab : Prison

sm.forcedInput(0)
sm.sendDelay(30)


sm.forcedInput(2)
sm.sendDelay(30)


sm.forcedInput(0)
OBJECT_2 = sm.sendNpcController(2159380, 450, 180)
sm.showNpcSpecialActionByObjectId(OBJECT_2, "summon", 0)
OBJECT_3 = sm.sendNpcController(2159384, 700, 180)
sm.showNpcSpecialActionByObjectId(OBJECT_3, "summon", 0)
sm.sendSessionValue("luti", 2159380)
sm.sendSessionValue("sig", 2159384)
sm.sendDelay(300)


sm.forcedInput(2)
