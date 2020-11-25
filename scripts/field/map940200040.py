MOB = 9300810

sm.spawnMob(MOB, -230, 25, False)
sm.spawnMob(MOB, 80, 25, False)
sm.spawnMob(MOB, 490, 25, False)
sm.spawnMob(MOB, 800, 25, False)
sm.spawnMob(MOB, -200, -215, False)
sm.spawnMob(MOB, 200, -215, False)
sm.spawnMob(MOB, 600, -215, False)
sm.spawnMob(MOB, -200, -455, False)
sm.spawnMob(MOB, 200, -455, False)
sm.spawnMob(MOB, 600, -455, False)

sm.flipDialoguePlayerAsSpeaker()
sm.sendNext("There really are tigers around here? Moonbeam... I gotta get rid of all the #r#o9300810##k monsters first.")
sm.startQuestNoCheck(38021)