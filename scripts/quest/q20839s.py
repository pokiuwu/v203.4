# Created by MechAviv
# Quest ID :: 20839
# Meeting with the Empress

sm.setSpeakerID(1102100)
sm.removeEscapeButton()
sm.sendNext("Hey, you're looking pretty good, #h0#. I think I'll promote you to Knight-in-Training.\r\mJust follow the arrows, and they'll lead you straight to the Empress.")


sm.setSpeakerID(1102100)
sm.removeEscapeButton()
sm.sendSay("Prove me right, #h0#.")


sm.startQuest(20839)
sm.levelUntil(10)