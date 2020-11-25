# Created by MechAviv
# [Skylark Rita]  |  [2500000]
# Grand Athenaeum : Grand Athenaeum
from net.swordie.ms.enums import UIType


sm.openUI(UIType.UI_DIMENSION_LIBRARY)
sm.setSpeakerID(2500000)
sm.flipDialogue()
sm.setSpeakerType(3)
if sm.sendAskYesNo("Do you want to read Ep 1. <The White Mage>?"):
    sm.setSpeakerID(2500000)
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendNext("You selected #eThe White Mage#n.")


    sm.setSpeakerID(2500000)
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSay("You will re-live the experiences from the past. Don't worry though, your actions will not change the future.")


    sm.setSpeakerID(2500000)
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSay("#h0#, you will go back in time and become a #e#bmercenary#k#n. Their identity was lost to history, but we know they existed.")


    sm.setSpeakerID(2500000)
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSay("#fNpc/2500001.img/stand/0#\r\nClick me any time you want to return to reality. I will always be somewhere in the story.")
    sm.warp(302090000, 0)