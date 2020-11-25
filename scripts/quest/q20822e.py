# Created by MechAviv
# Quest ID :: 20822
# The Path of Bravery

sm.setSpeakerID(1102101)
sm.removeEscapeButton()
sm.sendNext("Did you meet up with Kiku? He seems tough, but he's a total softy. ")


sm.setSpeakerID(1102101)
sm.removeEscapeButton()
sm.sendSay("The orientation's almost over. You wanna go ahead and get started on your training?")


sm.completeQuest(20822)
sm.warp(130030101, 0)