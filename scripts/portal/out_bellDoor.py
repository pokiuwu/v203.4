response = sm.sendAskYesNo("Are you sure you want to leave?")

# sm.sendSay("Response was " + str(response) + "\r\rAnswer was " + str(answer))
if response:
    sm.clearPartyInfo(401060000)
sm.dispose()
