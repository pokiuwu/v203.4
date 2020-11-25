# Mark of the Squad(2083004) | Cave of Life, Entrance to Horntail's Cave
from net.swordie.ms.constants import BossConstants
from net.swordie.ms.constants import GameConstants
if sm.isPartyLeader():
    sm.sendNext("#e<Boss:Horntail>#n \r\n Horntail has resurrected. We must stop it before it erupts a volcano and turns all of Minar into hell on earth.#b\r\n \r\n"
            "#L0#Apply to enter <Boss:Horntail>.#l\r\n")
    selection = sm.sendNext("#e<Boss:Horntail>#n \r\n Select a mode. \r\n \r\n"
                "#L0#Easy(Level 130+) #l \r\n"
                "#L1#Normal (Level 130+) #l \r\n"
                "#L2#Chaos(Level 135+) #l \r\n")
    if selection == 0:
        sm.clearPartyInfo(240060000)
        sm.warpPartyIn(240060000)
        sm.createClockForMultiple(BossConstants.EASY_HORNTAIL_TIME * 10, 240060000, 240060100, 240060300)
        sm.invokeAfterDelay(BossConstants.EASY_HORNTAIL_TIME * 1000 * 10, "warpPartyOut", 240050400, 0)
        sm.spawnNpc(2083002, 150, 0)
        for partyMember in sm.getParty().getMembers():
            sm.createQuestWithQRValue(GameConstants.EASY_HORNTAIL_QUEST, "1")
    elif selection == 1:
        sm.clearPartyInfo(240060002)
        sm.clearPartyInfo(240060102)
        sm.clearPartyInfo(240060200)
        sm.warpPartyIn(240060002)
        sm.createClockForMultiple(BossConstants.EASY_HORNTAIL_TIME * 10, 240060002)
        sm.invokeAfterDelay(BossConstants.EASY_HORNTAIL_TIME * 1000 * 10, "warpPartyOut", 240050400, 0)
        sm.spawnNpc(2083002, 150, 0)
        for partyMember in sm.getParty().getMembers():
            sm.createQuestWithQRValue(GameConstants.EASY_HORNTAIL_QUEST, "1")
    elif selection == 2:
        sm.clearPartyInfo(240060001)
        sm.clearPartyInfo(240060101)
        sm.clearPartyInfo(240060201)
        sm.warpPartyIn(240060001)
        sm.createClockForMultiple(BossConstants.EASY_HORNTAIL_TIME * 10, 240060001, 240060101, 240060201)
        sm.invokeAfterDelay(BossConstants.EASY_HORNTAIL_TIME * 1000 * 10, "warpPartyOut", 240050400, 0)
        sm.spawnNpc(2083002, 150, 0)
        for partyMember in sm.getParty().getMembers():
            sm.createQuestWithQRValue(GameConstants.EASY_HORNTAIL_QUEST, "1")


else:
    sm.sendSayOkay("Please have your party leader speak to me.")
    sm.dispose()