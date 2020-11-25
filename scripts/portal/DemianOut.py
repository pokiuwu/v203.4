# Damien leave NPC

WORLD_TREE_SUMMIT = 105300303

sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()

dialog = str()

if sm.hasMobsInField():
	dialog = "Are you sure you want to leave the battlefield and abandon your party members?"

else:
	dialog = "You have defeated Damien. Do you want to leave?"

if sm.sendAskYesNo(dialog):
	if sm.getChr().getField().getChars().size() == 1:
		sm.clearPartyInfo(WORLD_TREE_SUMMIT)

	else:
		sm.warp(WORLD_TREE_SUMMIT)
