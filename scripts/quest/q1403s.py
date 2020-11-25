# Created by MechAviv
# Quest ID :: 1403
# Bowmen of Henesys

sm.setSpeakerID(1012100)
sm.sendNext("Hello, #h0#. I've heard plenty about you from Mai. You are interested in becoming a Bowman, right? My name is Athena Pierce, Bowman Job Instructor. Nice to meet you!")


sm.setSpeakerID(1012100)
sm.sendSay("How much do you know about Bowmen? We use bows or crossbows to attack enemies at long range, mainly. We're a bit slower than others, but our arrows never miss their mark!")


sm.setSpeakerID(1012100)
if sm.sendAskAccept("If you really wish to become a Bowman, I will bring you to the #bBowman Instructional School in Henesys#k using my power as the Job Instructor. #rIf you are interested in other jobs, however, I will help you find your true path#k. Now, would you like to become a Bowman?"):
    sm.startQuest(1403)
    # Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
    sm.warp(100000201, 0)
else:
    sm.setSpeakerID(1012100)
    selection = sm.sendNext("So, you have choosen another path. That is your decision, of course. Which path will you now choose?\r\n#b#L0#Warrior#l \r\n#b#L1#Magician#l \r\n#b#L2#Thief#l \r\n#b#L3#Pirate#l")
    if selection == 0:
        sm.createQuestWithQRValue(1406, "1")
        sm.setSpeakerID(1012100)
        sm.sendNext("Warrior... If you want join them on their path of strength, wait for Dances with Balrog to contact you. Just keep on eye on the #bQuest Notifier on the left#k.")
    elif selection == 1:
        sm.createQuestWithQRValue(1406, "2")
        sm.setSpeakerID(1012100)
        sm.sendNext("Magician... If you want join them on their path of intelligent, wait for Grendel the Really Old to contact you. Just keep on eye on the #bQuest Notifier on the left#k.")
    elif selection == 2:
        sm.createQuestWithQRValue(1406, "4")
        sm.setSpeakerID(1012100)
        sm.sendNext("Theif... If you want join them on their path of speedy and sneaky, wait for Dark Lord to contact you. Just keep on eye on the #bQuest Notifier on the left#k.")
    elif selection == 3:
        sm.createQuestWithQRValue(1406, "5")
        sm.setSpeakerID(1012100)
        sm.sendNext("Pirate... If you want join them on their path of fancy and unique, wait for Kyrin to contact you. Just keep on eye on the #bQuest Notifier on the left#k.")

