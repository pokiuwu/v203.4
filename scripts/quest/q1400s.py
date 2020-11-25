# Created by MechAviv
# Quest ID :: 1400
# The 5 Paths

sm.setSpeakerID(12100)
sm.sendNext("Hmm, you're making good progress with your leveling. Have you decided on which job you want to take? You could be a Warrior with great strength and high HP, a Magician with many spells, a Bowman that shoots arrows from afar, a Thief that uses quick, sneaky attacks, or a Pirate with all kinds of flashy chain skills... There are so many!")


sm.setSpeakerID(12100)
selection = sm.sendNext("If you go to Victoria Island, you can advance to the job of your choice by going to the right Job Instructor. But before that, lemme know which one you're interested in, and I'll send #bthem#k a letter of recommendation. That will make it easier for you to advance! So, which job will you choose?\r\n#b#L0#I want to be a mighty Warrior!#l \r\n#b#L1#I want to be a mystical Magician!#l \r\n#b#L2#I want to be a sharp-shooting Bowman!#l \r\n#b#L3#I want to be a sneaky Thief!#l \r\n#b#L4#I want to be a swashbuckling Pirate!#l")
if selection == 0:
    sm.createQuestWithQRValue(1406, "1")
    sm.startQuest(1400)
    sm.completeQuest(1400)
    sm.setSpeakerID(12100)
    sm.sendNext("A Warrior, huh? Boy, you're going to get really strong! They can take tons of damage, and dish plenty out, too. Okay, I'll send my recommendation to #bDances with Balrog#k, the Warrior Job Instructor.")


    sm.setSpeakerID(12100)
    sm.sendSay("He will contact when you reach Lv. 10. Become a great Warrior!")