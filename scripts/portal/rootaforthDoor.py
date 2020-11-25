sm.setSpeakerID(1064015) # Fourth Seal Door
response = sm.sendAskYesNo("Would you like to fight Vellum?")

if response:
    if sm.getParty() is None:
        sm.sendSayOkay("Please create a party before going in.")
    elif not sm.isPartyLeader():
        sm.sendSayOkay("Please have your party leader enter if you wish to face Vellum.")
    elif sm.checkParty():
        sm.warpPartyIn(105200400) # North Garden
sm.dispose()
