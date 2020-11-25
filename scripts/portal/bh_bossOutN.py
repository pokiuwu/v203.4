# Lotus leave NPC

BLACK_HEAVEN_CORE_ENTRANCE = 350060300
BLACK_HEAVEN_CORE_LAST_NORMAL = 350060900

sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()

dialog = str()

if not sm.hasMobsInField() and sm.getFieldID() == BLACK_HEAVEN_CORE_LAST_NORMAL:
	dialog = "You have defeated Lotus. Do you want to leave?"

else:
	dialog = "Are you sure you want to leave the battlefield and abandon your party members?"

if sm.sendAskYesNo(dialog):
	if sm.getChr().getField().getChars().size() == 1:
		sm.clearPartyInfo(BLACK_HEAVEN_CORE_ENTRANCE)

	else:
		sm.warp(BLACK_HEAVEN_CORE_ENTRANCE)