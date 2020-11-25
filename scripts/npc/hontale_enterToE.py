# Encrypted Slate of the Squad(2083000) | Cave of Life, Cave Entrance
if sm.isPartyLeader():
    response = sm.sendAskYesNo("The letters on the slate glitter and the backdoor opens. Do you want to go to the secret path?")

    if response:
        sm.warpPartyIn(240050400)
        sm.dispose()
    else:
        sm.dispose()
else:
    sm.sendSayOkay("Please proceed through the Party leader.")
    sm.dispose()