# Created by MechAviv
# Quest ID :: 1404
# Thieves of Kerning City

sm.setSpeakerID(1052001)
sm.sendNext("So, you're the one Mai was talking about? #h0#... I guess you do have some potential. You want to become a Thief? Do you know what Thieves are all about?")


sm.setSpeakerID(1052001)
sm.sendSay("Most people think of us as petty thieves who steal things, but that's not true at all. Thieves in Maple World are those who fight with sharp daggers and throwing stars from the shadows. We don't always fight fair, but we always fight to win.")


sm.setSpeakerID(1052001)
sm.sendSay("As a job, Thieves attack enemies with swift, powerful skills. Though their HP is a bit low, they make it up with speed, so you had better learn to dodge. High luck allows them to land critical hits often, as well.")


sm.setSpeakerID(1052001)
if sm.sendAskAccept("Now, will you join us on the path of Thieves? If you decide to do so, I will bring you to the #bsecret Theives' Hideout in Kerning City#k using my power as the Job Instructor... You should feel honored. #rBut if you prefer a different job, I will help you find the other paths#k."):
    sm.startQuest(1404)
    # Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
    sm.warp(103000003, 1)
else:
    sm.setSpeakerID(1052001)
    selection = sm.sendNext("You don't wish to walk the path of a Thief? I will not force this path on someone who doesn't want it. Which job do you want?\r\n#b#L0#Warrior#l \r\n#b#L1#Magician#l \r\n#b#L2#Bowman#l\r\n#b#L3#Pirate#l")
    if selection == 0:
        sm.createQuestWithQRValue(1406, "1")
        sm.setSpeakerID(1052001)
        sm.sendNext("Warrior? Well... I guess that works. Dances with Balrog will be contacting you shortly. Keep your eyes on the #bQuest Notifier on the left#k.")
    elif selection == 1:
        sm.createQuestWithQRValue(1406, "2")
        sm.setSpeakerID(1052001)
        sm.sendNext("Magician? Well... I guess that works. Grendel the Really Old will be contacting you shortly. Keep your eyes on the #bQuest Notifier on the left#k.")
    elif selection == 2:
        sm.createQuestWithQRValue(1406, "3")
        sm.setSpeakerID(1052001)
        sm.sendNext("Bowman? Well... I guess that works. Athena Pierce will be contacting you shortly. Keep your eyes on the #bQuest Notifier on the left#k.")
    elif selection == 3:
        sm.createQuestWithQRValue(1406, "5")
        sm.setSpeakerID(1052001)
        sm.sendNext("Pirate? Well... I guess that works. Kyrin will be contacting you shortly. Keep your eyes on the #bQuest Notifier on the left#k.")

