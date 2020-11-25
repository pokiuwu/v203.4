# Created by MechAviv
# Map ID :: 807000000
# Momijigaoka : Momijigaoka

if sm.hasQuestCompleted(58907): # has just completed Mouri's Quest
    if sm.hasQuestCompleted(58908): # has already meet Ayame
        sm.warp(811000008)
    else:
        sm.warpInstanceIn(811000007, 0) # Momiji Hills (First Meet Ayame)
else:
    sm.systemMessage("You cannot enter right now. ")
    #sm.warp(811000001) # Momiji Hills : Near Momiji Hills 1
