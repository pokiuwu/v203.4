sm.setSpeakerID(1064013) # Second Seal Door
response = sm.sendAskYesNo("Would you like to fight Von Bon?")

if response:
    if sm.getParty() is None:
        sm.sendSayOkay("Please create a party before going in.")
    elif not sm.isPartyLeader():
        sm.sendSayOkay("Please have your party leader enter if you wish to face Von Bon.")
    elif sm.checkParty():
        sm.warpPartyIn(105200100) # East Garden
sm.dispose()
