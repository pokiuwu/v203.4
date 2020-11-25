# Created by MechAviv
# [Skylark Rita]  |  [2500001]
# Ariant : Middle of the Desert

sm.setSpeakerID(2500001)
sm.removeEscapeButton()
sm.flipDialogue()
selection = sm.sendNext("Return to the Grand Athenaeum?\r\n\r\n#b#L0#Return to the Grand Athenaeum. #l\r\n#L1#Not just yet.")
if selection == 0:
    sm.setSpeakerID(2500001)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.sendNext("Remembering this location...")

    sm.warp(302000000, 0)
elif selection == 1:
    sm.setSpeakerID(2500001)
    sm.flipDialogue()
    sm.sendSayOkay("You can go back if you still have stories to experience.")