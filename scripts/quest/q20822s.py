# Created by MechAviv
# Quest ID :: 20822
# The Path of Bravery

sm.setSpeakerID(1102100)
if sm.sendAskYesNo("I don't know if they told you during the orientation, but we're here to fight the Black Mage. Right now, you're not fit to fight a black mop. I'm gonna fix that.\r\nYou ready for some action?"):
	sm.setSpeakerID(1102100)
	sm.removeEscapeButton()
	sm.sendNext("Your first stop is Kimu. If you get lost, hit the #e#bQ key#k#n and check your #e#rQuest Info#k#n. That's tip number one: always check your quest info before you go running your mouth!")


	sm.playSound("Aran/balloon", 100)
	sm.startQuest(20822)
	sm.avatarOriented("UI/tutorial.img/2")
else:
	sm.setSpeakerID(1102100)
	sm.sendNext("You listening? Pay attention!")


