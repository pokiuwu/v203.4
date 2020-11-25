sm.setSpeakerID(1064012) # First Seal Door
response = sm.sendAskYesNo("Would you like to fight Pierre?")


if response:
    if sm.getParty() is None:
        sm.sendSayOkay("Please create a party before going in.")
    elif not sm.isPartyLeader():
        sm.sendSayOkay("Please have your party leader enter if you wish to face Pierre.")
    elif sm.checkParty():
        sm.warpPartyIn(105200200) # West Garden
