currentMap = sm.getFieldID()
if sm.hasMobsInField():
    sm.chat("Kill all monsters first.")
elif currentMap / 10000 == 92507 and not currentMap == 925074100:
    sm.warp(currentMap+100, 0)

elif currentMap == 925074100:
    sm.warp(925020003, 1)
sm.dispose()
