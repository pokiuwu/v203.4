# 140090500
if not "o" in sm.getQuestEx(21019, "miss") or sm.hasTutor():
    sm.hireTutor(False)
    sm.setQuestEx(21019, "miss", "o")