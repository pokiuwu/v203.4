# 807300110
inBattle = sm.getFieldID() == 807300110
if inBattle and sm.sendAskYesNo("Would you like to leave?"):
    sm.clearPartyInfo()
    sm.warpPartyOut(807300100)
elif sm.sendAskYesNo("Would you like to battle Ranmaru?"):
    if sm.getParty() is None:
        sm.sendSayOkay("Please create a party before going in.")
    elif not sm.isPartyLeader():
        sm.sendSayOkay("Please have your party leader enter if you wish to face Ranmaru.")
    elif sm.checkParty():
        sm.warpPartyIn(807300110)
