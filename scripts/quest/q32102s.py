# [Ellinel Fairy Academy] You Can Do It
FANZY = 1500010

sm.setSpeakerID(FANZY)
sm.sendNext("Are you asking where we are? Did you follow me without knowing where I was going? This is the forest path to the #bEllinel Fairy Academy#k.")
sm.sendSay("#bEllinel#k is an academy where fairy children learn magic. It was built across the lake from the more... conservative fairies, forming a buffer between the children and outsiders.")
sm.sendSay("#bCootie the Really Small#k has been captured by those Ellinel fairies. #bGrendel the Really Old#k and I have tried to contact them on several occasions, but they have no patience for humans. We're kind of stuck now.")

sm.setPlayerAsSpeaker()
sm.sendSay("What about the #bmissing#k people?")

sm.setSpeakerID(FANZY)
sm.sendSay("I believe they're deep within Ellinel. I don't think they've been captured by the fairies, so that's a relief. The Ellinel fairies really don't like strangers, though.")
sm.sendSay("If we want to look into the #bCootie the Really Small#k incident, we're going to have to go to Ellinel and look around. And when that happens...")

sm.setPlayerAsSpeaker()
sm.sendSay("We'll be able to find out what happened to the #bmissing people#k?")

sm.setSpeakerID(FANZY)
sm.sendSay("Exactly. Its a sensitive situation and I don't think the hostile fairies will be hospitable to the missing people. So make sure not to mention the #bmissing people#k while you are there.")

if sm.sendAskAccept("Now then, #b#h0##k, can you swim?"):
    sm.sendNext("Why don't you go for a swim! Show us how brave you are, meow...\r\n#b(Cross the lake to the right.)#k")
    sm.startQuest(parentID)
else:
    sm.sendNext("Do you want to jump in the water to amuse me anyway?")
