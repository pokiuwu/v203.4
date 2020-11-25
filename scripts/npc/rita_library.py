# Created by MechAviv
# [2500000]  |  [Skylark Rita]
# Grand Athenaeum : Grand Athenaeum

from net.swordie.ms.enums import UIType

if sm.hasQuestCompleted(32662):
    sm.openUI(UIType.UI_DIMENSION_LIBRARY)
else:
    sm.setSpeakerID(2500001)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.sendSayOkay("Chirp chirp! Talk to Thales the Librarian first.")