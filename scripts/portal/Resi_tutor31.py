# Introduction quest portal for resistance

status = -1

def init():
    sm.setSpeakerID(2159006)
    sm.invokeAfterDelay(7000, "sendNext", "Stay back!")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.sendSay("How did you get here? This place is prohibited!")
        sm.lockInGameUI(False)

    if status == 1:
        sm.setPlayerAsSpeaker()
        sm.sendSay("Who's talking? Where are you?!")

    if status == 2:
        sm.setSpeakerID(2159006)
        sm.sendSay("Look up.")
