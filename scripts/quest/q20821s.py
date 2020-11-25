# Created by MechAviv
# Quest ID :: 20821
# Knight's Orientation

sm.setSpeakerID(1102101)
sm.removeEscapeButton()
sm.sendNext("This is the ceremony where we welcome all the newbie knights. We need to find Training Instructor Kiku. He's gotta be around here somewhere...")


sm.setSpeakerID(1102101)
sm.removeEscapeButton()
sm.sendSay("Having a hard time finding Kiku? You should use that NPC button next to your map! Just click on Kiku and you'll see an arrow!\r\nHurry up and go say hi before he gets grumpy!")


sm.playSound("Aran/balloon", 100)
sm.startQuest(20821)
