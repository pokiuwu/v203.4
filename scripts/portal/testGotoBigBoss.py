
# 863010000 - Gollux
response = sm.sendAskYesNo("Would you like to battle Gollux?")

if response:
    if sm.getParty() is None:
        sm.sendSayOkay("Please create a party before going in.")
    elif not sm.isPartyLeader():
        sm.sendSayOkay("Please have your party leader enter if you wish to face Gollux.")
    elif sm.checkParty():
        sm.warpPartyIn(863010100)
sm.dispose()
