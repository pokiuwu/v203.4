#todo instance out warp
PIGLET = 4032449

if sm.hasItem(PIGLET) and sm.hasQuestCompleted(22015):
    sm.warpInstanceOut(100030300, 2)
    sm.dispose()
else:
    sm.systemMessage("You have to rescue the Piglet first.")
