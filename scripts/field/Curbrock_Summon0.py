# Curbrock Summon 1

CURBROCK = 9400929 # MOD ID
CURBROCKS_ESCAPE_ROUTE = 600050030 # MAP ID

sm.spawnMob(CURBROCK, 70, -208, False)
sm.createClock(60)
sm.addEvent(sm.invokeAfterDelay(60000, "warp", CURBROCKS_ESCAPE_ROUTE, 0))