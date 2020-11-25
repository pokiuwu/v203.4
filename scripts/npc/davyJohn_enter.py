# Guon (2094000) | Spiegelmann's Guest House

from net.swordie.ms.constants import GameConstants

pqItems = [
4001117, # Old Metal Key
4001120, # Rookie Pirate Mark
4001121, # Rising Pirate Mark
4001122, # Veteran Pirate Mark
]

if sm.isPartyLeader():
    sm.sendNext("Are you willing to help me in the fight against Davy John?#b\r\n"
                "\r\n"
                "#L0#Enter the Lord Pirate Party Quest#l")
    if sm.checkParty():

        # check for items
        for item in pqItems:
            if sm.hasItem(item):
                quantity = sm.getQuantityOfItem(item)
                sm.consumeItem(item, quantity)

        # for each party member, create a LORD_PIRATE_QUEST with qrValue = "0"
        for partyMember in sm.getParty().getMembers():
            sm.createQuestWithQRValue(partyMember.getChr(), GameConstants.LORD_PIRATE_QUEST, "0")

        sm.warpPartyIn(925100000) # Lord Pirate PQ  First Map

else:
    sm.sendSayOkay("Please have your party leader talk to me.")
