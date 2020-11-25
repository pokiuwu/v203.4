if not "hand=o" in sm.getQRValue(22013):
    sm.addQRValue(22013, "hand=o")
    sm.removeEscapeButton()
    sm.sendSayImage("UI/tutorial/evan/0/0")
    sm.avatarOriented("Effect/OnUserEff.img/guideEffect/evanTutorial/evanBalloon70")
