# Created by MechAviv
# Quest ID :: 20824
# Knight's Cavalier

FEATHERED_NOBLESSE_HAT = 1003769

sm.setSpeakerID(1102101)
sm.removeEscapeButton()
sm.sendNext("I like to give all the new recruits a little gift when they come to Ereve. It's important that the recruits look up to snuff, you know? Hit the #b#eI key#k#n to open up your inventory when we're done talking. Double click on that hat I gave you! ")

if not sm.canHold(FEATHERED_NOBLESSE_HAT):
	sm.sendSayOkay("Please free 1 equipment tab slot.")
	sm.dispose()

if not sm.hasItem(FEATHERED_NOBLESSE_HAT):
	sm.giveItem(FEATHERED_NOBLESSE_HAT)

sm.startQuest(20824)
sm.avatarOriented("UI/tutorial.img/6")