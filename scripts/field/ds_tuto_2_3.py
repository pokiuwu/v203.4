# Created by MechAviv
# ID :: [927000070]
# Hidden Street : Black Mage's Main Corridor 2

sm.showFieldEffect("demonSlayer/whiteOut", 0)
sm.sendDelay(1950)

sm.completeQuestNoRewards(23203)
sm.deleteQuest(23203)
sm.curNodeEventEnd(True)
sm.warpInstanceIn(931050300, 0)