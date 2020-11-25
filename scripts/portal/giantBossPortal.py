# Gollux portals
fields = {
# Main map id : main portal id : [to field id, to field portal]
863010100 : {
    3 : [863010400, 2],
    7 : [863010300, 2],
    9 : [863010220, 1],
},
863010210 : {
    2 : [863010240, 0],
},
863010220 : {
    2 : [863010230, 1],
},
863010230 : {
    1 : [863010220, 2],
    2 : [863010240, 0],
},
863010240 : {
    1 : [863010230, 2],
    2 : [863010210, 2],
    3 : [863010500, 0],
},
863010300 :{
    1 : [863010310, 1],
    2 : [863010100, 7],
},
863010310 : {
    1 : [863010300, 1],
},
863010320 : {
    1 : [863010500, 1],
    3 : [863010330, 0],
},
863010330 : {
    1 : [863010320, 3],
    3 : [863010500, 0],
},
863010400 : {
    1 : [863010410, 1],
    2 : [863010100, 6],
},
863010410 : {
    1 : [863010400, 1],
},
863010420 : {
    2 : [863010500, 5],
    3 : [863010430, 0],
},
863010430 : {
    1 : [863010420, 3],
    3 : [863010500, 0],
},
863010500 : {
    1 : [863010320, 1],
    2 : [863010240, 0],
    3 : [863010600, 0],
    4 : [863010320, 0],
    5 : [863010420, 2],
    6 : [863010420, 0],
},
863010600 : {
    2 : [863010700, 0],
},
}

fieldID = sm.getFieldID()
if fieldID not in fields:
    sm.chat("This portal (giantBossPortal.py) is not yet coded for this map (" + str(fieldID) + ")")
else:
    innerDict = fields[fieldID]
    if parentID not in innerDict:
        sm.chat("This portal (giantBossPortal, " + str(parentID) + ") is not yet coded for this map (" + str(fieldID) + ")")
    elif sm.hasMobsInField():
        sm.chat("Defeat all monsters first.")
    else:
        sm.warp(innerDict[parentID][0], innerDict[parentID][1])
sm.dispose()
