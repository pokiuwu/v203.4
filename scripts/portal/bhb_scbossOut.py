# 105200310 (+ other RA bosses)
response = sm.sendAskYesNo("Would you like to leave?")

if response:
    if sm.getParty() is None:
        sm.warpInstanceOut(350060000) # Entrance Core
    else:
        sm.warpPartyOut(350060000) # Entrace Core
sm.dispose()
