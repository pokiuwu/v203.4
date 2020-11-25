import time

spawned = False
while not spawned:
    drop = sm.getDropInRect(4001796, 400)
    if drop is not None:
        field.removeDrop(drop.getObjectId(), 0, False, -1)
        sm.spawnMob(8800003, 0, 0, False)
        sm.spawnMob(8800004, 0, 0, False)
        sm.spawnMob(8800005, 0, 0, False)
        sm.spawnMob(8800006, 0, 0, False)
        sm.spawnMob(8800007, 0, 0, False)
        sm.spawnMob(8800008, 0, 0, False)
        sm.spawnMob(8800009, 0, 0, False)
        sm.spawnMob(8800010, 0, 0, False)
        sm.spawnMob(8800002, 0, 0, False)
        spawned = True
    time.sleep(4)