# Adobis - Door to Zakum field
response = sm.sendAskYesNo("Hey, if you want to fight Zakum, I can provide you with some sacrificial orbs.")

if response:
    if sm.canHold(4001017):
        sm.giveItem(4001017, 5)
    else:
        sm.sendSayOkay("Please make more space in your ETC inventory.")
else:
    sm.sendSayOkay("Okay, maybe another time.")
