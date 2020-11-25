# Created by MechAviv
# Quest ID :: 17602
# [Commerci Republic] Neinheart's Request

sm.setNpcOverrideBoxChat(1540451)
sm.sendNext("According to intelligence reports, the people of Commerci are fiercely independent. The Empress means well, but in their eyes, any outreach might be thought an attempt to draw them under our influence. This would destroy any possibility of a relationship with Commerci, and I can't allow that.")


sm.setNpcOverrideBoxChat(1540451)
sm.sendSay("To ensure there are no misunderstandings, we must approach this matter with the utmost care. We should gain their trust before we relay the Empress' proposal. This is part of why the Empress has chosen you rather than a royal messenger. Please do not fail the Empress.")


sm.setNpcOverrideBoxChat(1540451)
if sm.sendAskYesNo("I've arranged your passage to Commerci. Seek out an explorer named #b'Parbell'#k in Lith Harbor. Let me know when you are ready to depart for Lith Harbor.\r\n#b(You will be moved to Lith Harbor if you accept.)"):
    sm.startQuest(17602)
    sm.warp(104000000, 5)
else:
    sm.setNpcOverrideBoxChat(1540451)
    sm.sendSayOkay("We haven't got all day. Tell me when you're ready.")