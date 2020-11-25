# Created by MechAviv
# Quest ID :: 21001
# Find the Missing Kid 2

sm.setSpeakerID(1209007)
if sm.sendAskYesNo("You made it back safely! What about the child?! Did you bring the child with you?!"):
    sm.consumeItem(4001271)
    sm.startQuest(21001)
    sm.completeQuest(21001)
    sm.setSpeakerID(1209007)
    sm.removeEscapeButton()
    sm.flipSpeaker()
    sm.sendNext("Oh, what a relief. I'm so glad...")


    sm.setSpeakerID(1209007)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.sendSay("Hurry and board the ship! We don't have much time!")


    sm.setSpeakerID(1209007)
    sm.removeEscapeButton()
    sm.flipSpeaker()
    sm.sendSay("We don't have any time to waste. The Black Mage's forces are getting closer and closer! We're doomed if we don't leave right this moment!")


    sm.setSpeakerID(1209007)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.sendSay("Leave, now!")


    sm.setSpeakerID(1209007)
    sm.removeEscapeButton()
    sm.flipSpeaker()
    sm.sendSay("Aran, please! I know you want to stay and fight the Black Mage, but it's too late! Leave it to the others and come to Victoria Island with us! ")


    sm.setSpeakerID(1209007)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.sendSay("No, I can't!")


    sm.setSpeakerID(1209007)
    sm.removeEscapeButton()
    sm.setPlayerAsSpeaker()
    sm.sendSay("Athena Pierce, why don't you leave for Victoria Island first? I promise I'll come for you later. I'll be alright. I must fight the Black Mage with the other heroes!")


    sm.setTemporarySkillSet(0)
    sm.setDirectionMode(True, 0)
    # [SET_DIRECTION_MODE] [01 00 00 00 00 ]
    # Unhandled Message [47] Packet: 2F 01 00 00 00 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
    sm.warp(914090010, 0)
else:
    sm.setSpeakerID(1209007)
    sm.sendNext("What about the child? Please give me the child.")