# 931000001
sm.setSpeakerID(2159011)
if sm.sendAskYesNo("#b(What a suspicious hole. Maybe Von is hiding inside. Peek inside?)#k"):
    sm.giveExp(35)
    sm.warp(931000010, 0)
else:
    sm.sendNext("#b(Even Von wouldn't hide here, right?)#k")
