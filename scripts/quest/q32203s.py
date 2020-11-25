# Created by MechAviv
# Quest ID :: 32203
# The New Explorer

sm.setSpeakerID(10301)
sm.removeEscapeButton()
sm.sendNext("Ohmygoodness! Hi! I'm Mai, an aspiring hero. It took me four years, but I just completed my freshman year at hero school. You must be a new #bExplorer#k!")


sm.setSpeakerID(10301)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("A new... #bExplorer?#k What's that?")


sm.setSpeakerID(10301)
sm.removeEscapeButton()
sm.sendSay("This was on that test I flunked ten times... Oh, right! Explorers are people who come to Maple World from other worlds! They start their journey right here on #bMaple Island#k.")


sm.setSpeakerID(10301)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("I'm on... #bMaple Island?#k")


sm.setSpeakerID(10301)
sm.removeEscapeButton()
sm.sendSay("You sure are! We used to be just some tiny island, but then Explorers started popping out. Now we've even got our own outhouse!")


sm.setSpeakerID(10301)
sm.removeEscapeButton()
sm.sendSay("So, your name is #h0#, right? You have two options now. You can listen to some explanations about starting out, take a few small tests, get some free gifts, and become my new best friend in the entire world...")


sm.setSpeakerID(10301)
sm.removeEscapeButton()
sm.sendSay("Or you can be teleported straight to town, but you'll miss out on my gifts... and I'll be super lonely and sad.")


sm.setSpeakerID(10301)
sm.removeEscapeButton()
selection = sm.sendNext("What do you say?\r\n#b#L0# I'll be your friend, Mai! (Go through tutorial and get free equipment.) #l\r\n#L1# I don't need you, Mai! (Skip tutorial and teleport straight to town.)#l#k")
if selection == 0:
    sm.setSpeakerID(10301)
    sm.removeEscapeButton()
    sm.sendNext("REALLY?! I'll fill you in on everything you need to know, I promise!")

    sm.giveExp(35)
    sm.startQuest(32203)
    sm.completeQuest(32203)
elif selection == 1:
    sm.setSpeakerID(10301)
    sm.removeEscapeButton()
    sm.sendNext("I knew you'd pick that. Everyone always does... I'll send you to Amherst right away and hope that the next Explorer will want to be my friend.")


    sm.setSpeakerID(10301)
    sm.removeEscapeButton()
    sm.sendSay("This is for you! You can check out the Recovery Potions I gave you in your Use tab.")


    sm.setSpeakerID(10301)
    sm.removeEscapeButton()
    sm.sendSay("Make sure you talk to Chief #bLucas#k when you get to Amherst! He's really smart and will give you some great advice.")

    sm.startQuest(32203)
    sm.completeQuest(32203)
    sm.giveItem(2000013, 50)
    sm.giveItem(2000014, 50)
    sm.startQuest(32210)
    sm.giveExp(15)
    # Unhandled Message [47] Packet: 2F 03 00 00 00 70 94 00 00 00 00 00 00 26 00 00 00 00 00 00 80 05 BB 46 E6 17 02 0B 00 75 73 65 72 5F 6C 76 75 70 3D 32 98 B7 00 00 00 00 00 00 2F 00 00 00 00 00 00 80 05 BB 46 E6 17 02 0B 00 75 73 65 72 5F 6C 76 75 70 3D 32 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
    sm.warp(4000020, 1)