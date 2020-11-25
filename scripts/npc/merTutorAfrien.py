# Created by MechAviv
# [Afrien]  |  [1033201]
# Black Road : After the Battle

if "o" in sm.getQuestEx(24006, "Afrien"):
    sm.setSpeakerID(1033201)
    selection = sm.sendNext("If you have questions, you need but ask.  #b\r\n#L0#What's our current situation?#l  #b\r\n#L1#What about the others?#l  #b\r\n#L2#Are you all right?#l  #b\r\n#L3#Why must I go?#l  #b\r\n#L4# I need to get back to Elluel!#l")
    if selection == 0:
        sm.setSpeakerID(1033201)
        sm.sendNext("We managed to seal the Black Mage away, but his last spell scattered us all across the world. At least you and Freud were sent to the same place. I'm more worried about the curse the Black Mage put on you.")
    elif selection == 1:
        sm.setSpeakerID(1033201)
        sm.sendNext("We were all sent to different places. Don't worry, our friends are the strongest heroes in all of Maple World. They can take care of themselves...I hope.")
    elif selection == 2:
        sm.setSpeakerID(1033201)
        sm.sendNext("There's no need to worry about me. I'm an Onyx Dragon, after all... I'm not weak. I am worried about Freud, though... But don't worry, I'll take care of him.")
    elif selection == 3:
        sm.setSpeakerID(1033201)
        sm.sendNext("It's the Black Mage's curse. It'll freeze us all, maybe forever. You have to get back to Elluel and look after the Elves. If the curse is on you, their sovereign, then it'll spread to them, as well.")
    elif selection == 4:
        sm.setSpeakerID(1033201)
        sm.sendNext("Now, go. Go and save the Elves.")

        sm.setSpeakerID(1033201)
        sm.setPlayerAsSpeaker()
        sm.sendSay("Yes. We will meet again, Afrien.")

        sm.warpInstanceIn(910150001, 0)
else:
    sm.setSpeakerID(1033201)
    sm.setPlayerAsSpeaker()
    sm.sendNext("Afrien? Freud? Are you okay?!")


    sm.setSpeakerID(1033201)
    sm.sendSay("Mercedes... You survived.")


    sm.setSpeakerID(1033201)
    sm.setPlayerAsSpeaker()
    sm.sendSay("Of course! I managed to seal him away. I can't let myself die after that! But what about you? And the others? Where are they?")


    sm.setSpeakerID(1033201)
    sm.sendSay("We may have #bdefeated the Black Mage#k, but he sent everyone flying in different directions with that last spell. We're lucky we ended up in the same place.")


    sm.setSpeakerID(1033201)
    sm.setPlayerAsSpeaker()
    sm.sendSay("You're right... I didn't realize how far away we ended up. At least we're safe.")


    sm.setSpeakerID(1033201)
    sm.setPlayerAsSpeaker()
    sm.sendSay("Now that the fight is over, I feel so weak... Not just that, but I feel so cold...")


    sm.setSpeakerID(1033201)
    sm.setPlayerAsSpeaker()
    sm.sendSay("Come to think of it, has it always been snowy here? There's all this heat, and yet snow is falling... Strange...")


    sm.setSpeakerID(1033201)
    sm.sendSay("You can't feel it, Mercedes? The #rgreat curse#k... It's been placed upon you, Freud, and the others.")


    sm.setSpeakerID(1033201)
    sm.setPlayerAsSpeaker()
    sm.sendSay("C-curse?")


    sm.setSpeakerID(1033201)
    sm.sendSay("There's an icy cold curse clinging to you. You might have been able to shrug it off if you weren't weak from fighting the Black Mage. It looks like he's not letting us off so easily...")


    sm.setSpeakerID(1033201)
    sm.setPlayerAsSpeaker()
    sm.sendSay("You should be able to survive it, at least. But I'm worried about Freud... He's too weak.")


    sm.setSpeakerID(1033201)
    sm.sendSay("I'll take care of him. For now, I'm more worried about you, Mercedes. You're the #bruler of the Elves#k. If the curse is on you, #rwon't it be placed upon all of the Elves#k?")


    sm.setSpeakerID(1033201)
    sm.setPlayerAsSpeaker()
    sm.sendSay("...!")


    sm.setSpeakerID(1033201)
    sm.sendSay("Hurry back to #bElluel#k. If the #bBlack Mage's curse is on all of the Elves#k, then you must return to your people.")


    sm.setSpeakerID(1033201)
    sm.setPlayerAsSpeaker()
    sm.sendSay("All right! Afrien... We will meet again!")


    sm.setSpeakerID(1033201)
    sm.sendSay("...I pray you're right.")

    sm.setQuestEx(24006, "Afrien", "o")
    sm.setSpeakerID(1033201)
    sm.setPlayerAsSpeaker()
    if sm.sendAskYesNo("#b(The other heroes will make it through somehow. For now, return to town using your return skill.)"):
        sm.warpInstanceIn(910150001, 0)
    else:
        sm.setSpeakerID(1033201)
        sm.setPlayerAsSpeaker()
        sm.sendNext("I have more questions!")


