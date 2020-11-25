# Mark of the Squad(2083004) | Cave of Life, Entrance to Horntail's Cave
from net.swordie.ms.constants import BossConstants
from net.swordie.ms.constants import GameConstants
if sm.getFieldID() == 271040100 or sm.getFieldID() == 211070102:
    if sm.sendAskYesNo("Would you like to leave the fight?"):
        sm.warpInstanceOut(271040000, 0)
else:
    if sm.isPartyLeader():
        sm.sendNext("#e<Boss:Cygnus Expedition>#n \r\n Are you ready to fight Empress Cygnus?#b\r\n \r\n"
                    "#L0#Request to join a Cygnus Expedition.#l\r\n")
        selection = sm.sendNext("#e<Boss:Von Leon>#n \r\n Select a mode. \r\n \r\n"
                                "#L0#Easy(Level 150+) #l \r\n"
                                "#L1#Normal (Level 175+) #l \r\n")
        if selection == 0:
            sm.clearPartyInfo(271040100)
            sm.warpPartyIn(271040100)

            sm.createClockForMultiple(BossConstants.CYGNUS_TIME, 271040100)
            sm.invokeAfterDelay(BossConstants.CYGNUS_TIME * 1000, "warpPartyOut", 271040100, 0)
        elif selection == 1:
            sm.clearPartyInfo(211070102)
            sm.clearPartyInfo(211070111)
            sm.warpPartyIn(211070102)
            sm.createClockForMultiple(BossConstants.CYGNUS_TIME , 211070102)
            sm.invokeAfterDelay(BossConstants.CYGNUS_TIME * 1000, "warpPartyOut", 211070000, 0)
    else:
        sm.sendSayOkay("Please have your party leader speak to me.")
        sm.dispose()