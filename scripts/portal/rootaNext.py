# Root abyss "next" portals
if sm.hasMobsInField():
    sm.chat("Eliminate all monster before proceeding.")
    sm.dispose()
else:
    sm.warp(sm.getFieldID() + 10)
