# Created by MechAviv
# ID :: [927020071]
# Unknown : Unknown

SEAL_CHECKER = 9300535

sm.giveSkill(20041222, 1, 1)
sm.setFuncKeyByScript(True, 20041222, 42)

sm.spawnMob(SEAL_CHECKER, 600, -298, False)
sm.spawnMob(SEAL_CHECKER, 150, -508, False)
sm.spawnMob(SEAL_CHECKER, -150, -508, False)
sm.spawnMob(SEAL_CHECKER, -600, -298, False)

OBJECT_4 = sm.sendNpcController(2159363, 600, -310)
sm.showNpcSpecialActionByObjectId(OBJECT_4, "summon", 0)
OBJECT_5 = sm.sendNpcController(2159364, 150, -520)
sm.showNpcSpecialActionByObjectId(OBJECT_5, "summon", 0)
OBJECT_6 = sm.sendNpcController(2159365, -150, -520)
sm.showNpcSpecialActionByObjectId(OBJECT_6, "summon", 0)
OBJECT_7 = sm.sendNpcController(2159366, -600, -310)
sm.showNpcSpecialActionByObjectId(OBJECT_7, "summon", 0)
sm.showFieldEffect("lightning/screenMsg/4", 0)
sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendNext("Time is frozen. I must activate the seals before the Black Mage notices.")


sm.setSpeakerID(0)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.setSpeakerType(3)
sm.sendSay("I must reach the light on the platform to the right. I can #b<Flash Blink>#k there if I press #r[Shift]#k.")


sm.showFieldEffect("lightning/screenMsg/5", 0)
# Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00

