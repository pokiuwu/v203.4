# So Gong (2091011) | Mu Lung Dojo Hall

dojoHall = 925020001

if sm.getFieldID() == dojoHall:
    selection = sm.sendNext("My master is the strongest person in Mu Lung, and YOU wish to challenge HIM? I have a feeling you'll regret this.\r\n#b"
                "#L0#I want to challenge Mu Lung Dojo.#l\r\n"
                "#L1#What is Mu Lung Dojo?#l\r\n"
                "#L2#What rewards can I get from Mu Lung Dojo?#l\r\n"
                "#L3#How many attempts do I have left today?#l\r\n"
                "#L4#I'd like to enter the Unity Training Center.#l\r\n")

    if selection == 0: # I want to challenge Mu Lung Dojo
        if not sm.getParty() is None:
            sm.sendSayOkay("Please leave your party before attempting the Mu Lung Dojo")
        else:
            sm.warpInstanceIn(925070100) # Dojo Floor 1

    elif selection == 1: # What is Mu Lung Dojo?
        sm.sendSayOkay("#r//TODO")
    elif selection == 2: # What rewards can I get from Mu Lung Dojo?
        sm.sendSayOkay("#r//TODO")
    elif selection == 3: # How many attempts do I have left today?
        sm.sendSayOkay("#r//TODO")
    elif selection == 4: # I'd like to enter the Unity Training Center.
        sm.sendSayOkay("#r//TODO")

else:
    response = sm.sendAskYesNo("Are you giving up already?")

    if response:
        sm.warpInstanceOut(dojoHall)
