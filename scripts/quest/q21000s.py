# Created by MechAviv
# Quest ID :: 21000
# Find the Missing Kid 1

sm.setSpeakerID(1209000)
if sm.sendAskAccept("Oh, no! I think there's still a child in the forest! Aran, I'm very sorry, but could you rescue the child? I know you're injured, but I don't have anyone else to ask!"):
    sm.startQuest(21000)
    sm.setSpeakerID(1209000)
    sm.sendNext("#bThe child is probably lost deep inside the forest!#k We have to escape before the Black Mage finds us. You must rush into the forest and bring the child back with you!")


    sm.setSpeakerID(1209000)
    sm.sendSay("Don't panic, Aran. If you wish to check the status of the quest, press #bQ#k and view the Quest window.")


    sm.setSpeakerID(1209000)
    sm.sendSay("Please, Aran! I'm begging you. I can't bear to lose another person to the Black Mage!")


    sm.avatarOriented("Effect/OnUserEff.img/guideEffect/aranTutorial/tutorialArrow1")
else:
    sm.setSpeakerID(1209000)
    sm.sendNext("No, Aran... We can't leave a kid behind. I know it's a lot to ask, but please reconsider. Please!")