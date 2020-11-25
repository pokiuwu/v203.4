# Created by MechAviv
# Quest ID :: 21016
# Basic Fitness Training 2

sm.setSpeakerID(1201000)
if sm.sendAskAccept("Shall we continue with your Basic Training? Before accepting, please make sure you have properly equipped your sword and your skills and potions are readily accessible."):
    sm.startQuest(21016)
    sm.setSpeakerID(1201000)
    sm.removeEscapeButton()
    sm.sendNext("Alright. This time, let's have you defeat #r#o0100132#s#k, which are slightly more powerful than #o0100131#s. Head over to #b#m140020100##k and defeat #r15#k of them. That should help you build your strength. Alright! Let's do this!")


    # [START_NAVIGATION] [84 89 58 08 00 00 00 00 00 00 ]
else:
    sm.setSpeakerID(1201000)
    sm.sendNext("Are you not ready to hunt the #o0100132#s yet? Always proceed if and only if you are fully ready. There's nothing worse than engaging in battles without sufficient preparation.")