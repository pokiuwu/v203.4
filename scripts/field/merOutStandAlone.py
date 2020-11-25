# Created by MechAviv
# ID :: [910150003]
# Frozen Fairy Forest : Blooming Forest

sm.progressMessageFont(3, 20, 20, 0, "Press the Ctrl key to do a regular attack.")
SLIME = 9300422

coordinates = [
    [-2058, 1],
    [-2348, 1],
    [-2812, 1],
    [-2810, 1],
    [-3040, 1],

    [-2589, -330],
    [-2313, -330],
    [-2450, -330],

    [-2121, -612],
    [-1948, -612],
]

if sm.hasQuest(24001) and not sm.hasMobsInField(): # Danika's Request (Mercedes Intro Quest)
    sm.giveSkill(20021166, 1) # Give attacking skill
    for coordinate in coordinates:
        sm.spawnMob(SLIME, coordinate[0], coordinate[1], False, 5) # Spawn 10 slimes across the field