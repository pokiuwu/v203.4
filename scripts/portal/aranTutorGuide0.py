# 914000200
if not "o" in sm.getQuestEx(21002, "normal"):
    sm.showFieldEffect("aran/tutorialGuide1")
    sm.systemMessage("To use a Regular Attack on monsters, press the Ctrl key.")
    sm.setQuestEx(21002, "normal", "o")