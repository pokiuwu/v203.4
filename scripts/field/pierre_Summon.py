from net.swordie.ms.enums import WeatherEffNoticeType
PIERRE = 9400938

sm.spawnMob(PIERRE, 1155, 551, False)
sm.showFieldEffect("Map/Effect.img/rootabyss/firework") # "Welcome" effect
sm.showWeatherNotice("From the bottom of my heart, welcome to the tea party!", WeatherEffNoticeType.BossPierre, 10000)

sm.waitForMobDeath(PIERRE)
if sm.hasQuest(30010):
    sm.completeQuest(30010) #[Root Abyss] Defeat the Second Guardian
sm.spawnMob(8900103, 497, 551, False) #Spawn Pierre's Treasure Chest
