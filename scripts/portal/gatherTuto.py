# Hidden Street - Ardentmill :: 910001000
maps = [910001003, 910001004, 910001007, 910001009]
GRANT = 9031000
sm.setSpeakerID(GRANT)

if sm.hasQuestCompleted(3195) and sm.hasQuestCompleted(3196) and sm.hasQuestCompleted(3197) and sm.hasQuestCompleted(3198):
    selection = sm.sendSay("Where do you want to go?\r\n\r\n#L0##bNovice Secret Herb Patch#k (Silver Herb, Magenta Herb, Silver Vein, Magenta Vein)#b#l\r\n#L1##bIntermediate Secret Herb Patch#k (Blue Herb, Brown Herb, Blue Vein, Brown Vein)#b#l\r\n#L2#Advanced Secret Herb Patch#k(Emerald Herb, Gold Herb, Emerald Vein, Gold Vein)#b#l\r\n#L3#Expert Secret Herb Patch#k(Aquamarine Herb, Red Herb, Black Herb, Purple Herb, Aquamarine Vein, Red Vein, Black Vein, Purple Vein)#l#k")

    sm.warp(maps[selection], 1)
else:
    selection = sm.sendSay("Where do you want to go?\r\n\r\n#L0##bSaffron's Herb Field#k\r\n#L1##bCole's Mine#k")
    if selection == 0:
        sm.warp(910001001, 1)
    else:
        sm.warp(910001002, 1)