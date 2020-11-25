answer = sm.sendSay("Where would you like to go to? \r\n#L0#Velderoth's Sitting Room#l\r\n#L1#Continue to Magnus#l\r\n#L2#Nevermind#l")

# sm.chat("Response was " + str(response) + "\r\rAnswer was " + str(answer))
if answer == 0:
    if sm.getParty() is None:
        sm.sendSay("Please create a party before going in.")
    elif not sm.isPartyLeader():
        sm.sendSay("Please have your party leader enter if you wish to face Velderoth.")
    elif sm.checkParty():
        sm.warpPartyIn(401053100)
elif answer == 1:
    sm.warp(401060000)
sm.dispose()
