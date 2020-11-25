# Created by MechAviv
# Quest ID :: 20837
# Lesson 5 - Skills

sm.setSpeakerID(1102102)
sm.removeEscapeButton()
sm.sendNext("Try attacking a monster using your skills! Drag them to a hotkey to make them more convenient!")


sm.setSpeakerID(1102102)
sm.removeEscapeButton()
sm.sendSay("Now use #rElemental Slash#k to defeat #b5 #o9300731##k monsters!")


sm.startQuest(20837)
for i in range(5):
    sm.spawnMob(9300731, -364, -6, False)
sm.avatarOriented("UI/tutorial.img/8")