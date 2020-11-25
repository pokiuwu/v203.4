# Created by MechAviv
# Quest ID :: 23600
# Not coded yet
OBJECT_6 = sm.getIntroNpcObjectID(2159377)

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, True, False, False)
sm.sendDelay(900)


sm.moveCamera(False, 100, -307, -41)


sm.sendDelay(2604)


sm.setSpeakerID(2159377)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Good, very good! I am very satisfied with these results. Just a few more fine adjustments and...")


sm.startQuest(23724)
sm.completeQuest(23600)
sm.changeBGM("Bgm30.img/fromUnderToUpper", 0, 0)
sm.showEffect("Effect/Direction12.img/effect/tuto/BalloonMsg1/0", 1200, 0, -120, 0, OBJECT_6, False, 0)
sm.moveNpcByObjectId(OBJECT_6, True, 1, 100)
sm.sendDelay(90)


sm.setSpeakerID(2159377)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("An intruder?! It could be Orchid. Turn on the monitor!")


sm.startQuest(23725)
sm.sendDelay(2100)


sm.completeQuest(23725)
sm.sendDelay(1200)


sm.setSpeakerID(2159377)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendNext("Is it the Resistance? I suppose that would be better than Orchid, but... this is the worst possible time!")


sm.setSpeakerID(2159377)
sm.removeEscapeButton()
sm.setSpeakerType(3)
sm.sendSay("Wait, wait, wait. Maybe this will work. One more test, yes... they will be perfect... Hahaha... MWAHAHAHA!")


sm.warp(931050940, 0)
