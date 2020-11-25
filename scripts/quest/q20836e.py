# Created by MechAviv
# Quest ID :: 20836
# Lesson, Resumed

# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
sm.setSpeakerID(1102102)
sm.removeEscapeButton()
sm.sendNext("You haven't forgotten my last lesson, have you?! Pretty Ctrl to perform a regular attack!\r\nTime for the next lesson! Ready?!")


sm.setSpeakerID(1102102)
sm.removeEscapeButton()
sm.sendSay("Skill attacks! They dish out the pain! Open your Skill window by pressing the K key to access your skills.\r\nYou'll get more skills once you're more experienced, so never stop training!")


sm.completeQuest(20836)
sm.playSound("Aran/balloon", 100)
sm.avatarOriented("UI/tutorial.img/7")