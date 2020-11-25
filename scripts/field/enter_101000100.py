sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, False, False, False)
sm.sendDelay(7200)
sm.createFieldTextEffect("#fn나눔고딕 ExtraBold##fs20#- Several months passed. The forest and house were barely restored... -", 100, 2500, 4, 0, 0, 1, 4, 0, 0, 0)

sm.progressMessageFont(3, 20, 20, 0, "Click light bulb to start quest. Quest Status Hotkey [Q] / Secondary Key [J]")
sm.sendDelay(1500)

sm.avatarOriented("UI/tutorial.img/34")
sm.sendDelay(1200)


sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)