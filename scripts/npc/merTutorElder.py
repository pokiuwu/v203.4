# Created by MechAviv
# [Philius]  |  [1033202]
# Frozen Fairy Forest : Elluel

sm.setSpeakerID(1033202)
selection = sm.sendNext("You have a question, Your Majesty? #b\r\n#L0#What is this place?#l  #b\r\n#L1#Who am I?#l  #b\r\n#L2#Who are you?#l  #b\r\n#L3#What happened?#l  #b\r\n#L4#What do I have to do again?#l")
if selection == 0:
    sm.setSpeakerID(1033202)
    sm.sendNext("Oh dear, has the curse taken hold on you already? This is Elluel, village of the Elves. Until recently, we were safe from the Black Mage...")
elif selection == 1:
    sm.setSpeakerID(1033202)
    sm.sendNext("You are Mercedes, ruler of the Elves, of course! I didn't realize memory loss was part of the curse...")
elif selection == 2:
    sm.setSpeakerID(1033202)
    sm.sendNext("The Elves elect three Elders to advise the sovereign. Astilda, the eldest, is the Elder of Life. Danika, the youngest, is the talented Elder of War. And I, humble though my skills may be, am the Elder of Magic.")
elif selection == 3:
    sm.setSpeakerID(1033202)
    sm.sendNext("You don't remember? A handful of heroes emerged from Victoria Island to stop the Black Mage's reign of terror. They came to Elluel, and you went them to seal the Black Mage away. And then this happened...")
elif selection == 4:
    sm.setSpeakerID(1033202)
    sm.sendNext("We cannot lift the curse, but we can seal the village away and sleep it off. You must see to the seal...")

    sm.startQuest(24000)