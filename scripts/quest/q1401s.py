# Created by MechAviv
# Quest ID :: 1401
# Warriors of Perion

sm.setSpeakerID(1022000)
sm.sendNext("So, you are the person Mai recommended. You seek to become a Warrior, am I right? I am Dances with Balrog, the Warrior Job Instructor. I instruct newcomers in the ways of battle.")


sm.setSpeakerID(1022000)
sm.sendSay("How much do you know about Warriors? Warriors have great strength and high HP, and face their enemies up-close with powerful attacks. Sounds fun, right?")


sm.setSpeakerID(1022000)
if sm.sendAskAccept("You look like you are more than qualified. If you wish to become a Warrior, I welcome you. You wish to become a Warrior? If you accept, I will use my power as the Job Instructor to bring you to the #bWarriors' Sanctuary in Perion#k right away. #rThere are still paths for you even if you change your mind, and I will help you if you do#k."):
    sm.startQuest(1401)
    sm.warp(102000003, 1)
else:
    sm.setSpeakerID(1022000)
    selection = sm.sendNext("You do not wish to choose the path of a Warrior? Very well. There are four other paths you can choose.\r\n#b#L0#Magician#l \r\n#b#L1#Bowman#l \r\n#b#L2#Thief#l \r\n#b#L3#Pirate#l")
    if selection == 0:
        sm.createQuestWithQRValue(1406, "2")
        sm.setSpeakerID(1022000)
        sm.sendNext("You wish to be a Magician? If that is what you wish, so be it. #bGrendel the Really Old#k will contact you shortly. Check the #bQuest Notifier on the left#k.")
    elif selection == 1:
        sm.createQuestWithQRValue(1406, "3")
        sm.setSpeakerID(1022000)
        sm.sendNext("You wish to be a Bowman? If that is what you wish, so be it. #bAthena Pierce#k will guide you down that path. Check the #bQuest Notifier on the left#k.")
    elif selection == 2:
        sm.createQuestWithQRValue(1406, "4")
        sm.setSpeakerID(1022000)
        sm.sendNext("You wish to be a Thief? If that is what you wish, so be it. #bDark Lord#k will contact you soon. Check the #bQuest Notifier on the left#k.")
    elif selection == 3:
        sm.createQuestWithQRValue(1406, "5")
        sm.setSpeakerID(1022000)
        sm.sendNext("You wish to be a Pirate? If that is what you wish, so be it. #bKyrin#k will contact you soon. Check the #bQuest Notifier on the left#k.")