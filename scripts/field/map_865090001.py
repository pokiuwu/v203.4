# Created by MechAviv
# ID :: [865090001]
# Commerci Republic : Berry
if sm.hasQuest(17613): # [Commerci Republic] The Minister's Son
    sm.curNodeEventEnd(True)
    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(True, True, False, False)
    sm.setStandAloneMode(True)
    sm.setSpeakerID(9390241)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendNext("Hey! Keep your paws away from my pants!")


    sm.setSpeakerID(9390242)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSay("All your fish are belong to us!")


    sm.setSpeakerID(9390241)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSay("I am the great #e#bLeon Daniella#k#n! These fish are mine! See? I wrote my name on them.")


    sm.setSpeakerID(9390242)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSay("Your permanent marker won't dissuade us. WE WANT YOUR FISH!")


    sm.showNpcSpecialActionByTemplateId(9390241, "q17613", 0)
    sm.setSpeakerID(9390241)
    sm.removeEscapeButton()
    sm.flipDialogue()
    sm.setSpeakerType(3)
    sm.sendSay("It's not fair if you attack me as a pack. Do it one by one, like a real man, er, cat. Hey! Are you listening? Ouch!")


    sm.setSpeakerID(0)
    sm.removeEscapeButton()
    sm.flipDialoguePlayerAsSpeaker()
    sm.setSpeakerType(3)
    sm.sendSay("#b(Uh oh, that guy's in real trouble. I should help him.#k\r\nHey! Coastal Cats! Over here.")


    sm.setTemporarySkillSet(0)
    sm.setInGameDirectionMode(False, True, False, False)

    i = 0
    while i < 12:
        sm.spawnMob(9390847, 996, 132, False)
        i += 1
