from net.swordie.ms.enums import WeatherEffNoticeType

reactor.incHitCount()
sm.showWeatherNotice("Please allow me to mourn over your imminent demise.", WeatherEffNoticeType.BossCrimsonQueenCrownPink, 10000)
if reactor.getHitCount() == 5:
    sm.spawnMob(8920100, 37, 135, False)
    sm.removeReactor()
    sm.dispose()
