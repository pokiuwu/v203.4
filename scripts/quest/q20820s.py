# Created by MechAviv
# Quest ID :: 20820
# The City of Ereve

sm.setSpeakerID(1102101)
sm.removeEscapeButton()
sm.sendNext("Welcome to Ereve! This is the safest and most peaceful place in all of Maple World. Empress Cygnus keeps it nice all the time!\r\nYou're #b#h0##k, right? Here to join the #p1101000# Knights. I'm your guide, #p1102004#. All the Noblesses in town come to me first!")

sm.setSpeakerID(1102101)
sm.removeEscapeButton()
sm.sendSay("You need to get over to the Knight's Orientation right away. They're getting started already. Follow me, okay?.")


sm.startQuest(20820)
sm.completeQuest(20820)
sm.giveSkill(10001244, 1, 1)
sm.giveSkill(10000252, 1, 1)
sm.giveSkill(10001254, 1, 1)
sm.warp(130030100, 0)