# Created by MechAviv
# Quest ID :: 1402
# Magicians of Ellinia

sm.setSpeakerID(1032001)
sm.sendNext("Ah, so you are the one that Mai was talking about. How do you do? I heard that you are interested in the path of a Magician. If that's the case, I will help guide you. I am Grendel the Really Old, the Magician Job Instructor.")


sm.setSpeakerID(1032001)
sm.sendSay("I'm sure you already know a little bit about Magicians. With high intelligence as our foundation, we learn all manner of magic spells to wield in battle. Range is not a concern for us, but our low HP is our weakness. We've come up with many ways around that, though, so don't worry too much.")


sm.setSpeakerID(1032001)
if sm.sendAskAccept("I see that you are more than qualified to become a Magician... would you like to become a Magician? If you accept, I will use my power as the Job Instructor to bring you to the #bMagic Library in Ellinia#k. I'll perform the Job Advancement once we meet in person. #rThere are still other paths open to you if you change your mind, and I will help you find them if you do.#k"):
    sm.startQuest(1402)
    # Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
    sm.warp(101000003, 6)
else:
    sm.setSpeakerID(1032001)
    selection = sm.sendNext("You are not content with the path of a Magician? That is unfortunate, but I will respect your decision. Which path will you choose now?\r\n#b#L0#Warrior#l \r\n#b#L1#Bowman#l \r\n#b#L2#Thief#l \r\n#b#L3#Pirate#l")
    if selection == 0:
        sm.createQuestWithQRValue(1406, "1")
        sm.setSpeakerID(1032001)
        sm.sendNext("You wish to be a Warrior? If that is what you wish, so be it. Dances with Balrog will be contacting you soon. Click on the #bQuest Notifier on the left#k.")
    elif selection == 1:
        sm.createQuestWithQRValue(1406, "3")
        sm.setSpeakerID(1032001)
        sm.sendNext("You wish to be a Bowman? If that is what you wish, so be it. Athena Pierce will be contacting you soon. Click on the #bQuest Notifier on the left#k.")
    elif selection == 2:
        sm.createQuestWithQRValue(1406, "4")
        sm.setSpeakerID(1032001)
        sm.sendNext("You wish to be a Thief? If that is what you wish, so be it. Dark Lord will be contacting you soon. Click on the #bQuest Notifier on the left#k.")
    elif selection == 3:
        sm.createQuestWithQRValue(1406, "5")
        sm.setSpeakerID(1032001)
        sm.sendNext("You wish to be a Pirate? If that is what you wish, so be it. Kyrin will be contacting you soon. Click on the #bQuest Notifier on the left#k.")