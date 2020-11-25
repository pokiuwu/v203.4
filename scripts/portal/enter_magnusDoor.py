from net.swordie.ms.constants import BossConstants

response = sm.sendAskYesNo("Would you like to fight magnus?")

if response:
    if sm.getParty() is None:
        sm.sendSayOkay("Please create a party before going in.")
    elif not sm.isPartyLeader():
        sm.sendSayOkay("Please have your party leader enter if you wish to face Magnus.")
    elif sm.checkParty():
        sm.setPartyDeathCount(BossConstants.MAGNUS_DEATHCOUNT)
        sm.warpPartyIn(401060200)
sm.dispose()
