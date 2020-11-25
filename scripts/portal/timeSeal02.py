SEAL_CHECKER = 9300535
SEAL_OF_TIME = 2159367

if not sm.hasQuest(25672):
    sm.createQuestWithQRValue(25672, "1")
    sm.showFieldEffect("lightning/screenMsg/6", 0)