# Lotus entry NPC 

# mode, req level, map, death count
destinations = [
	["Normal", 210, 350060700, 5], # p2 350060800 | p3 350060900
	["Hard", 235, 350060400, 5], # p2 350060500 | p3 350060600
]

def is_party_eligible(reqlevel, party):
	# TODO: check prequest
	for member in party.getMembers():
		if member.getLevel() < reqlevel:
			return False

	return True

sm.flipSpeaker()
sm.flipDialoguePlayerAsSpeaker()
sm.setBoxChat()

dialog = "Do you want to head to Black Heaven Core to fight Lotus?\r\n"

for i in range(len(destinations)):
	dialog += "#L%d#Go to the Black Heaven Core (%s Mode). (Lv. %d+)#l\r\n" % (i, destinations[i][0], destinations[i][1])

dialog += "#L99#Never mind."
response = sm.sendSay(dialog)

if sm.getParty() is None:
	sm.sendSayOkay("Please create a party before going in.")

elif not sm.isPartyLeader():
	sm.sendSayOkay("Please have your party leader talk to me if you wish to face Lotus.")

elif sm.checkParty() and response != 99:
	if is_party_eligible(destinations[response][1], sm.getParty()):
		sm.setPartyDeathCount(destinations[response][3])
		sm.warpPartyIn(destinations[response][2])

	else:
		sm.sendSayOkay("One or more party members are lacking the prerequisite entry quests, or are below level %d." % destinations[response][1])