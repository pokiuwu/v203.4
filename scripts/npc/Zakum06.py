# Amon - (Easy/Chaos) Zakum's Altar
answer = sm.sendSay("#L0#I want you to spawn Zakum for me.#l\r\n#L1#I want to leave.\r\n#l#L2#Nevermind.#l")

if answer == 0:
    fieldID = sm.getFieldID()
    if fieldID == 280030200: # Easy
        # Main Body
        sm.spawnMob(8800020, -54, 86, False)
        # Arms
        for i in range(8):
            sm.spawnMob(8800023 + i, -54, 86, False)
    elif fieldID == 280030100: # Normal
        # Main Body
        sm.spawnMob(8800000, -54, 86, False)
        # Arms
        for i in range(8):
            sm.spawnMob(8800003 + i, -54, 86, False)
    elif fieldID == 280030000: # Chaos
        # Main Body
        sm.spawnMob(8800100, -54, 86, False)
        # Arms
        for i in range(8):
            sm.spawnMob(8800103 + i, -54, 86, False)
elif answer == 1:
    sm.clearPartyInfo(211042300)
