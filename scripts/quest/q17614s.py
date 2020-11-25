# Created by MechAviv
# ID :: [865010200]
# Commerci Republic : Berry

sm.setSpeakerID(9390241)
sm.removeEscapeButton()
sm.sendNext("Good! Now I leave. I just came to Berry to get fish from Berry. Haha! They have the same name! Berry, Berry, Berry, Berry.")


sm.setSpeakerID(9390241)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.sendSay("Do you have to leave right this second? I wanted to ask my new best pal some questions.")


sm.setSpeakerID(9390241)
sm.removeEscapeButton()
sm.sendSay("Oh, shoot. I have to catch a ship. Shoot! Hey! Why don't you come to #bSan Commerci#k? You can meet my dad! He's really cool.")


sm.setSpeakerID(9390241)
sm.removeEscapeButton()
sm.setPlayerAsSpeaker()
sm.sendSay("Berry did suggest I visit...")


sm.setSpeakerID(9390241)
if sm.sendAskAccept("Yeah! Just go to San Commerci and wait for me. I have a couple of voyages to make, but don't leave until I get back. Promise?"):
    sm.startQuest(17614)
    sm.createQuestWithQRValue(17614, "Incomplete")
    sm.setSpeakerID(9390241)
    sm.sendSayOkay("Okay, go east from the town, follow the coastal road, and you'll get to San Commerci. It's a single road, but I get lost on it all the time.\r\nSee you in #e#bSan Commerci#k#n. Remember, you promised!")
    sm.warpInstanceOut(865010200, 0)
else:
    sm.setSpeakerID(9390241)
    sm.sendSayOkay("Yes? Do you have business here?")