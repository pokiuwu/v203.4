# Jenn (9020005) | Spiegelmann's Guest House

pqItems = [

]

if sm.isPartyLeader():
    sm.sendNext("Can you help me escape?#b\r\n"
                "\r\n"
                "#L0#Enter the Escape Party Quest#l")

else:
    sm.sendSayOkay("Please have your party leader talk to me.")
    sm.dispose()

if sm.checkParty():

    # check for items
    for item in pqItems:
        if sm.hasItem(item):
            quantity = sm.getQuantityOfItem(item)
            sm.consumeItem(item, quantity)

    sm.warpPartyIn(921160100) # Escape! - PQ  first map
