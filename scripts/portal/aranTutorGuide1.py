# 914000210
if not "o" in sm.getQuestEx(21002, "chain"):
    sm.showFieldEffect("aran/tutorialGuide1")
    sm.systemMessage("You can use Consecutive Attacks by pressing the Ctrl key multiple times.")
    sm.setQuestEx(21002, "chain", "o")