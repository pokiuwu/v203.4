# 914000220
if not "o" in sm.getQuestEx(21002, "cmd"):
    sm.showFieldEffect("aran/tutorialGuide3")
    sm.systemMessage("You can use a Command Attack by pressing both the arrow key and the attack key after a Consecutive Attack.")
    sm.setQuestEx(21002, "cmd", "o")