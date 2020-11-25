# horntail entrace - The Cave of Trial 1
from net.swordie.ms.constants import GameConstants

if sm.getFieldID() == 240060000:
    if int(sm.getQRValue(GameConstants.EASY_HORNTAIL_QUEST)) == 1:
        sm.spawnMob(8810200, 868, 230, False)
        for partyMember in sm.getParty().getMembers():
            sm.setQRValue(GameConstants.EASY_HORNTAIL_QUEST, "2")
elif sm.getFieldID() == 240060002:
    if int(sm.getQRValue(GameConstants.EASY_HORNTAIL_QUEST)) == 1:
        sm.spawnMob(8810000, 868, 230, False)
        for partyMember in sm.getParty().getMembers():
            sm.setQRValue(GameConstants.EASY_HORNTAIL_QUEST, "2")
elif sm.getFieldID() == 240060001:
    if int(sm.getQRValue(GameConstants.EASY_HORNTAIL_QUEST)) == 1:
        sm.spawnMob(8810100, 868, 230, False)
        for partyMember in sm.getParty().getMembers():
            sm.setQRValue(GameConstants.EASY_HORNTAIL_QUEST, "2")