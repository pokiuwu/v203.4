# horntail entrace - The Cave of Trial 1
from net.swordie.ms.constants import GameConstants

if sm.getFieldID() == 240060100:
    if int(sm.getQRValue(GameConstants.EASY_HORNTAIL_QUEST)) == 3:
        sm.spawnMob(8810201, -317,230, False)
        for partyMember in sm.getParty().getMembers():
            sm.setQRValue(GameConstants.EASY_HORNTAIL_QUEST, "4")
elif sm.getFieldID() == 240060102:
    if int(sm.getQRValue(GameConstants.EASY_HORNTAIL_QUEST)) == 3:
        sm.spawnMob(8810001, -317,230, False)
        for partyMember in sm.getParty().getMembers():
            sm.setQRValue(GameConstants.EASY_HORNTAIL_QUEST, "4")
elif sm.getFieldID() == 240060101:
    if int(sm.getQRValue(GameConstants.EASY_HORNTAIL_QUEST)) == 3:
        sm.spawnMob(8810101, -317,230, False)
        for partyMember in sm.getParty().getMembers():
            sm.setQRValue(GameConstants.EASY_HORNTAIL_QUEST, "4")

