# Created by MechAviv
# Quest ID :: 1405
# Pirates of the Nautilus

sm.setSpeakerID(1090000)
sm.sendNext("#h0#...? Oh, right! Mai mentioned you. Yeah...you look pretty good to me. I'm Kyrin, the captain of the Nautilus and the Pirate Job Instructor. I heard you are interested in becoming a Pirate. Is that true?")


sm.setSpeakerID(1090000)
sm.sendSay("If that's the case, I should tell you a bit about myself first. I brought the pirates together to start working against the Black Mage, the great evil that threatens all of Maple World. Turns out the hero business is more profitable than looting and pillaging!")


sm.setSpeakerID(1090000)
sm.sendSay("If you become a Pirate, you can help investigate the Black Mage's plots, and assist in the defense of Maple World. Keep in mind that I won't make you do anything...I'm primarily a Job Instructor, and just guide the pirates in a general sense.")


sm.setSpeakerID(1090000)
sm.sendSay("But, I know you would help us fight the Black Mage. You have that gleam in your eye that all heroes do. Anyway, I've said my piece. That was just for your information. What's really important is coming up next.")


sm.setSpeakerID(1090000)
sm.sendSay("There are two paths you can take as a Pirate. You can fight with guns, or with your fists. Your weapons and skills will be quite different depending on what you pick, but both are still Pirates. And that means you're gonna look GOOD while you fight!")


sm.setSpeakerID(1090000)
if sm.sendAskAccept("Okay, I've said enough. So, Pirate. In, or out? If you want to become a Pirate, I'll bring you to the Nautilus right now using my power as a Job Instructor. #rAnd if you don't, I'll help you find the right job for you#k."):
    sm.startQuest(1405)
    # Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
    sm.warp(120000101, 0)
else:
    sm.setSpeakerID(1090000)
    selection = sm.sendNext("You wish to choose a different path? Hey, if that's what you want. Which job will you choose, then?\r\n#b#L0#Warrior#l \r\n#b#L1#Magician#l \r\n#b#L2#Bowman#l \r\n#b#L3#Thief#l")
    if selection == 0:
        sm.createQuestWithQRValue(1406, "1")
        sm.setSpeakerID(1090000)
        sm.sendNext("You choose the path of a Warrior? Alright, then. Dances with Balrog will be contacting you. Watch the #bQuest Notifier on the left#k.")
    elif selection == 1:
        sm.createQuestWithQRValue(1406, "2")
        sm.setSpeakerID(1090000)
        sm.sendNext("You choose the path of a Magician? Alright, then. Grendel the Really Old will be contacting you. Watch the #bQuest Notifier on the left#k.")
    elif selection == 2:
        sm.createQuestWithQRValue(1406, "3")
        sm.setSpeakerID(1090000)
        sm.sendNext("You choose the path of a Bowman? Alright, then. Athena Pierce will be contacting you. Watch the #bQuest Notifier on the left#k.")
    elif selection == 3:
        sm.createQuestWithQRValue(1406, "4")
        sm.setSpeakerID(1090000)
        sm.sendNext("You choose the path of a Theif? Alright, then. Dark Lord will be contacting you. Watch the #bQuest Notifier on the left#k.")