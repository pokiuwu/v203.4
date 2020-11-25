# Created by MechAviv
# Quest ID :: 21011
# The Missing Weapon

sm.setSpeakerID(1202003)
sm.sendNext("Wait, are you... No way.... Are you the hero that #p1201000# has been talking about all this time?! #p1201000#! Don't just nod... Tell me! Is this the hero you've been waiting for?! ")


sm.setSpeakerID(1202003)
sm.sendSay("   #i4001171#")


sm.setSpeakerID(1202003)
sm.sendSay("I'm sorry. I'm just so overcome with emotions... *Sniff sniff* My goodness, I'm starting to tear up. You must be so happy, #p1201000#.")


sm.setSpeakerID(1202003)
sm.sendSay("Wait a minute... You're not carrying any weapons. From what I've heard, each of the heroes had a special weapon. Oh, you must have lost it during the battle against the Black Mage.")


sm.setSpeakerID(1202003)
if sm.sendAskYesNo("This isn't good enough to replace your weapon, but #bcarry this sword with you for now#k. It's my gift to you. A hero can't be walking around empty-handed.\r\n\r\n#fUI/UIWindow2.img/QuestIcon/4/0# \r\n#i1302000# 1 #t1302000# \r\n\r\n#fUI/UIWindow2.img/QuestIcon/8/0# 35 exp"):
    sm.giveItem(1302000)
    sm.startQuest(21011)
    sm.completeQuest(21011)
    sm.giveExp(35)
    sm.setSpeakerID(1202003)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.sendNext("#b(Your skills are nowhere close to being hero-like... But a sword? Have you ever even held a sword in your lifetime? You can't remember... How do you even equip it?)#k")
    sm.tutorAutomatedMsg(16)
    # [START_NAVIGATION] [BC 9B 59 08 00 00 00 00 00 00 ]
    # Unhandled Message [47] Packet: 2F 01 00 00 00 40 9C 00 00 00 00 00 00 28 00 00 00 00 00 00 80 05 BB 46 E6 17 02 0B 00 75 73 65 72 5F 6C 76 75 70 3D 33
    #sm.warp(140090300, 1)
else:
    sm.setSpeakerID(1202003)
    sm.sendNext("*sniff sniff* Isn't this sword good enough for you, just for now? I'd be so honored...")