# Created by MechAviv
# Quest ID :: 21013
# A Gift for the Hero

sm.setSpeakerID(1202005)
sm.sendNext("Ah, you've brought all the components. Give me a few seconds to assemble them... Like this... And like that... and...\r\n\r\n#fUI/UIWindow2.img/QuestIcon/4/0# \r\n#i3010062# 1 #t3010062# \r\n\r\n#fUI/UIWindow2.img/QuestIcon/8/0# 95 exp")

sm.giveItem(3010062)
sm.startQuest(21013)
sm.completeQuest(21013)
sm.giveExp(95)
sm.setSpeakerID(1202005)
sm.removeEscapeButton()
sm.sendSay("Here, a fully-assembled chair, just for you! I've always wanted to give you a chair as a gift, because I know a hero can occasionally use some good rest. Tee hee. ")


sm.setSpeakerID(1202005)
sm.removeEscapeButton()
sm.sendSay("A hero is not invincible. A hero is human. I'm sure you will face challenges and even falter at times. But you are a hero because you have what it takes to overcome any obstacles you may encounter.")

sm.tutorAutomatedMsg(19)
# [START_NAVIGATION] [00 3B 58 08 01 00 00 00 07 00 31 32 30 31 30 30 30 ]