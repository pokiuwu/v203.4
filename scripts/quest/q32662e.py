# Created by MechAviv
# Quest ID :: 32662
# [Grand Athenaeum] Visit the Grand Athenaeum

sm.setSpeakerID(2500002)
sm.sendNext("Come on over! I am #b#p2500002##k. Surprised? I'll have you know my simian parts are my best parts.")


sm.setSpeakerID(2500002)
sm.sendSay("What is this place, you ask? This is the #bGrand Athenaeum#k. All the knowledge and records of this world are enshrined here. Many people don't know it, but Maple World's stories write themselves into their own book when they happen. No pens or ink required!")


sm.setSpeakerID(2500002)
sm.sendSay("Ha ha, you think I'm lying? You can see for yourself! You can #benter the stories themselves#k, and experience them as a character.")


sm.setSpeakerID(2500002)
sm.sendSay("#b#p2500000##k gave me books that all touch on key events in Maple World's history.")


sm.setSpeakerID(2500002)
if sm.sendAskAccept("Let's see... Five is too many, so how about you clear #bthree, regardless of the order#k, and I'll give you a #bpretty neat gift#k. You up for the challenge?\r\n\r\n                    - Bonus Pendant Slot #e#b(21 Day)#k#n\r\n\r\n- #i1122263# #b#t1122263##k"):
    sm.completeQuest(parentID)

    sm.startQuest(32663)# [Grand Athenaeum] Read The White Mage
    sm.startQuest(32664)# [Grand Athenaeum] Read Empress In Training
    sm.startQuest(32665)# [Grand Athenaeum] Read The Black Witch
    sm.startQuest(32698)# [Grand Athenaeum] Winter Bard
    sm.startQuest(32990)# [Grand Athenaeum] Episode V: Shadow Alchemist
    sm.startQuest(32666)# [Grand Athenaeum] Food for the Soul
    sm.setSpeakerID(2500002)
    sm.sendSayOkay("You can read the books containing stories in any order you'd like. Talk to #b#p2500000##k.")
else:
    sm.setSpeakerID(2500002)
    sm.sendNext("Busy? You're always welcome here.")