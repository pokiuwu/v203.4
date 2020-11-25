# Portal to leave Ellinel Fairy Academy

FANZY = 1040002 # NPC ID
MIDSUMMER_NIGHTS_FOREST_PATH_FROM_ELLINEL = 101074001 # MAP ID

sm.setSpeakerID(FANZY)
if sm.sendAskYesNo("Head back to #bNorth Forest: Giant Tree#k?"):
    sm.warp(MIDSUMMER_NIGHTS_FOREST_PATH_FROM_ELLINEL)