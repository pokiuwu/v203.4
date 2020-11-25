# Created by MechAviv
# Quest ID :: 20825
# Well-Behaved Student

sm.setSpeakerID(1102103)
if sm.sendAskYesNo("I am Kinu. I will get you up to speed on Ereve's history. Now, go on and sit! I teach best when my students are shorter than me!"):
	sm.setSpeakerID(1102103)
	sm.removeEscapeButton()
	sm.sendNext("Press X in front of any chair to sit down. If you own one, it's the same deal. X marks the butt.")


	sm.startQuest(20825)
	sm.playSound("Aran/balloon", 100)
	sm.avatarOriented("UI/tutorial.img/10")
else:
	sm.setSpeakerID(1102103)
	sm.sendNext("Why are you still standing? That is not what a well-behaved student would do.")