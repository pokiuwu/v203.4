# Created by MechAviv
# Kinesis Introduction
# Map ID :: 101020400
# East Forest :: Magician Association
from net.swordie.ms.enums import TextEffectType
if "1" not in sm.getQuestEx(22740, "view1"):
    sm.lockForIntro()
    sm.blind(1, 255, 0, 0)
    sm.sendDelay(1000)

    sm.zoomCamera(0, 2000, 0, 34, -923)
    sm.sendDelay(500)

    sm.zoomCamera(3000, 2000, 3000, 14, -933)
    sm.blind(1, 180, 0, 1500)
    sm.sendDelay(1500)

    sm.blind(1, 255, 0, 1500)
    sm.sendDelay(1500)

    sm.sayMonologue("What the... Was that a dream?", True)

    sm.zoomCamera(0, 2000, 0, -126, -350)
    sm.sendDelay(500)

    sm.zoomCamera(3000, 2000, 3000, -100, -373)
    sm.blind(1, 100, 0, 1500)
    sm.sendDelay(1500)

    sm.blind(1, 255, 1500)
    sm.sendDelay(1500)

    sm.sayMonologue("Ugh, my head... Where am I?", True)

    sm.zoomCamera(0, 1000, 0, -137, -1000)
    sm.sendDelay(500)

    sm.blind(0, 0, 0, 1000)
    sm.sendDelay(1500)

    sm.createFieldTextEffect("#fn������� ExtraBold##fs26#Ellinia, Maple World\r\n#fs14#- Magician Association -", 100, 2500, 4, 0, 0, 1, 4, TextEffectType.KinesisIntro, 0, 0)
    sm.sendDelay(2500)

    sm.zoomCamera(4000, 1000, 4000, -137, -100)
    sm.sendDelay(sm.getAnswerVal())

    sm.showFadeTransition(0, 1000, 3000)
    sm.zoomCamera(0, 1000, 2147483647, 2147483647, 2147483647)

    sm.moveCamera(True, 0, 0, 0)
    sm.sendDelay(300)

    sm.removeOverlapScreen(1000)
    sm.unlockForIntro()
    sm.setQuestEx(22740, "view1", "1")
