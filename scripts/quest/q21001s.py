# Created by MechAviv
# Quest ID :: 21001
# Find the Missing Kid 2

sm.setSpeakerID(1209006)
if sm.sendAskAccept("*Sniff sniff* I was so scared... Please take me to Athena Pierce."):
    sm.giveItem(4001271)
    sm.startQuest(parentID)
    sm.warp(914000500, 1)
else:
    sm.setSpeakerID(1209006)
    sm.sendNext("*Sob* Aran has declined my request!")