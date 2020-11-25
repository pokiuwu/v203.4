# Created by MechAviv
# Map ID :: 807100001
# NPC ID :: 9131009
# Honnou-ji Eastern Door

if not sm.hasMobsInField():
    sm.removeSkill(40011183)
    sm.removeSkill(40011184)
    sm.removeSkill(40011185)
    sm.warpInstanceOut(807100002, 1) # Honnou-ji Eastern Grounds
else:
    sm.setSpeakerID(9131009)
    sm.setPlayerAsSpeaker()
    sm.boxChatPlayerAsSpeaker()
    sm.sendSayOkay("Take down all enemies! Open the Eastern Door!")