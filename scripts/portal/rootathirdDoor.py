sm.setSpeakerID(1064014) # Third Seal Foor
response = sm.sendAskYesNo("Would you like to fight Crimson Queen?")

if response:
    if sm.getParty() is None:
        sm.sendSayOkay("Please create a party before going in.")
    elif not sm.isPartyLeader():
        sm.sendSayOkay("Please have your party leader enter if you wish to face Crimson Queen.")
    elif sm.checkParty():
        sm.warpPartyIn(105200300) # South Garden
sm.dispose()
