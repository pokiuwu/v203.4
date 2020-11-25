# Created by MechAviv
# Quest ID :: 17600
# [Commerci Republic] Neinheart's Call

sm.setSpeakerID(1101002)
if sm.sendAskAccept("Ah good, I've managed to reach you. The Empress has been asking for you. Could you come to Ereve?\r\n#b(You will be moved to Ereve if you accept.)#k"):
    sm.sendNext("I will be waiting for you.")

    sm.startQuest(17600)
    sm.warp(130090000, 0)
else:
    sm.sendSayOkay("It would be wise to listen.")