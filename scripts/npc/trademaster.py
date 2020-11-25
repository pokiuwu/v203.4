# Created by MechAviv
# [Maestra Fiametta]  |  [9390220]
# Commerci Republic : San Commerci
if sm.hasItem(4310100, 1):
    sm.setSpeakerID(9390220)
    sm.sendSayOkay("You can't start your voyage until you finish the tutorial quest!")
else:
    sm.setSpeakerID(9390220)
    sm.sendNext("What? You threw away the coins without finishing the tutorial? (Sighs) I suppose I can give you some more coins so that you can complete the tutorial.")


    sm.setSpeakerID(9390220)
    sm.sendSay("Just remember, you can't trade without gold!")


    sm.giveItem(4310100, 10)
    sm.setSpeakerID(9390220)
    sm.sendPrev("Check to make sure there you have coins in your inventory.")