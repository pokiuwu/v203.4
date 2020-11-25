# 927030050
sm.lockInGameUI(True, False)
sm.hideUser(True)
sm.forcedInput(0)
if "3" in sm.getQRValue(38907):
    sm.reservedEffect("Effect/Direction15.img/eunwolTutorial/Scene1")
    sm.sendDelay(16000)
    sm.warpInstanceIn(927030040, 0)
elif "2" in sm.getQRValue(38907):
    sm.reservedEffect("Effect/Direction15.img/eunwolTutorial/Scene0")
    sm.sendDelay(4000)
    sm.warpInstanceIn(927030030, 0)
elif "1" in sm.getQRValue(38900):
    sm.sendDelay(500)
    sm.showFieldEffect("Map/Effect2.img/eunwol/enter")
    sm.sendDelay(3500)
    sm.warpInstanceIn(927030020, 0)
else:
    sm.sendDelay(500)
    sm.sayMonologue("\r\n\r\nA great darkness is covering Maple World.", False)
    sm.sayMonologue("\r\nThe Black Mage wishes to dominate Maple World with overwhelming darkness.", False)
    sm.sayMonologue("\r\nMany feared the Black Mage and his incredible strength, and few dared to oppose him.", True)

    sm.sayMonologue("\r\nDespair reigned in Maple World.", False)
    sm.sayMonologue("\r\n\r\n\r\nBut then...", False)
    sm.sayMonologue("\r\nThere came heroes who were bold enough to oppose the Black Mage.", True)
    sm.sendDelay(1000)

    sm.reservedEffect("Effect/Direction8.img/lightningTutorial/Scene0")
    sm.sendDelay(3300)

    sm.warpInstanceIn(927030000, 0)