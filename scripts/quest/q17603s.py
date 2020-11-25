# Created by MechAviv
# Quest ID :: 17603
# [Commerci Republic] Parbell, World's 'Greatest' Explorer

sm.setSpeakerID(9390200)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendNext("Excuse me, are you Parbell the Explorer?")


sm.setNpcOverrideBoxChat(9390200)
sm.sendSay("What d'yuh means, don't yuh recognize me? Gaze 'pon this hansom visage! Haven't ya heard about ol' Parbell, the Greatest 'Splorer in the whole o' Maple World!? Sheesh, young'uns these days!")
# Unhandled User Effect [SkillAffected] Packet: 04 BF 9A 98 00 01


sm.setSpeakerID(9390200)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("(He seems a little upset.)")


sm.setNpcOverrideBoxChat(9390200)
sm.sendSay("So, I hears yuhs seekin' passage to the good ol' 'Public o' Commerci... That about right?")


sm.setSpeakerID(9390200)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("Y-Yessir, I's am... I mean, that's right. Why do you ask?")


sm.setNpcOverrideBoxChat(9390200)
sm.sendSay("Hows yuh plannin' to get there?")


sm.setSpeakerID(9390200)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("E-Excuse me...? I was... Neinheart, said you would have a ship ready for me, uh, Mr. Great Explorer, sir...")


sm.setNpcOverrideBoxChat(9390200)
sm.sendSay("Oh, Parbell the Great, done readied a ship for you. There's no doubtin' that. Question is, how yuhs plan on getting on that ship?")


sm.setSpeakerID(9390200)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("I... uh... By walking? With my feet?")


sm.setNpcOverrideBoxChat(9390200)
sm.sendSay("Y'uns about as smart as a bag of hammers, ain't yuhs? Let ol' Parbell make it all simple-like for y'un. I gots a ship. You wants to gets on that ship. I done readied mah ship like I's told. #eTraditionally at this point some form o' currency get s'changed.#n")


sm.setSpeakerID(9390200)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("(Are you kidding me? Neinheart expects me to pay for the trip myself? He's in for a stern talking-to...)")


sm.setSpeakerID(9390200)
sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()
sm.setColor(1)
sm.sendSay("I, uhh... I'm afraid I don't have any sort of payment ready, Mr. Great Explorer, sir,  but I'll definitely repay you when I can. Whatever you think is appropriate. I swear on the Empress's name.")


sm.setNpcOverrideBoxChat(9390200)
sm.sendSay("Empress? She ain't MAH Empress. Shucks, this here's why I never deal with no greenhorns... You gots your way with words, I gives yuh that. Well, get on mah ship, a promise is a promise. I'll deliver you to Commerci all safe 'n cozy-like, but remember yer offer. Words carry weight.")


sm.setNpcOverrideBoxChat(9390200)
if sm.sendAskYesNo("I'll be collectin' on that promise, 'fore long. When all's said 'n done, y'uns shold feel plum tickled that I, Parbell the Great, am showin' yuhs the way! Ready to set sail?\r\n#b(You will be moved to Commerci if you accept.)"):
    # Unhandled Message [INC_COMMITMENT_MESSAGE] Packet: 09 01 00 00 00 00
    sm.completeQuest(17603)
    sm.giveExp(556227)
    sm.warp(865090003, 0)

else:
    sm.setNpcOverrideBoxChat(9390200)
    sm.sendSayOkay("What? Yuh can't backs out now! Yuh know how long it tooks me to gets mah ship ready!?")