reactor.incHitCount()
if reactor.getHitCount() >= 2:
    sm.systemMessage("Look, you got a Timu for training!")
    sm.spawnMob(9300732, reactor.getX(), reactor.getY(), False)
    sm.removeReactor()
    sm.dispose()