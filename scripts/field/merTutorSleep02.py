# Fairy Forest : King's Seat

if sm.hasQuest(24005): # Cursed Slumber
    sm.completeQuest(24005) # Cursed Slumber
    sm.setJob(2300) # Mercedes
    sm.setSP(5, True)
    sm.resetStats()
    sm.giveSkill(20021166, 0) # Remove the Beginner Stunning Strike Skill
