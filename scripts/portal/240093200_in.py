if sm.checkParty():
    if sm.hasQuest(31351): # [Stone Colossus] Colossal Clean Up 7
        sm.warpPartyIn(240093310)
    elif sm.hasQuestCompleted(31351):
        sm.warpPartyIn(240093300)
