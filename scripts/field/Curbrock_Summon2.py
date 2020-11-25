# Curbrock Summon 3

CURBROCK3 = 9400931 # MOD ID
CURBROCKS_ESCAPE_ROUTE_VER3 = 600050050 # MAP ID

sm.spawnMob(CURBROCK3, 320, -208, False)
sm.createClock(1800)
sm.addEvent(sm.invokeAfterDelay(1800000, "warp", CURBROCKS_ESCAPE_ROUTE_VER3, 0))
sm.waitForMobDeath(CURBROCK3)
sm.warp(CURBROCKS_ESCAPE_ROUTE_VER3)
sm.stopEvents()