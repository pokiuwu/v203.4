# Created by MechAviv
# Quest ID :: 32216
# Victoria Island or Bust!

sm.setSpeakerID(10306)
sm.removeEscapeButton()
sm.sendNext("You d-defeated the monsters, and you really helped me out, too, #h #. You seem r-ready to pick a Job. Did you decide which one you want?")


sm.setSpeakerID(10306)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("#bHuh? Job?#k")


sm.setSpeakerID(10306)
sm.removeEscapeButton()
sm.sendSay("There are five different Explorer Jobs. You can advance to them on Victoria Island. Hm, I think they were... Warrior, Magician, Bowman, Thief, and Pirate.")


sm.setSpeakerID(10306)
sm.removeEscapeButton()
sm.flipDialoguePlayerAsSpeaker()
sm.sendSay("#bWhat are they like?#k")


sm.setSpeakerID(10306)
sm.removeEscapeButton()
sm.sendSay("Let's see. Warriors have great strength and defense, so they excel at close-range combat. Magicians use magic, so they f-favor intelligence over power, and they're good at long-range combat against multiple enemies.")


sm.setSpeakerID(10306)
sm.removeEscapeButton()
sm.sendSay("B-bowman are also good at long-range combat. They shoot arrows from afar and can keep enemies at a distance. And, let's see... Thieves are close-range, like warriors, but they focus on speed instead of strength.")


sm.setSpeakerID(10306)
sm.removeEscapeButton()
sm.sendSay("Finally, Pirates... are Pirates. Some use their fists in close-range combat, others shoot guns or cannons from afar. Their attacks are pretty fancy, either way.")


jobs = [["None", "None"], ["Warrior", "powerful and defensive"], ["Magician", "intelligent and magical"], ["Bowman", "long-ranged and controlled"], ["Thief", "speedy and sneaky"], ["Pirate", "fancy and unique"]]

sm.setSpeakerID(10306)
sm.removeEscapeButton()
list = "If you pick your Job right now, the captain offered to contact your new job instructor as soon as we pull into harbor. So, #h #, which Job do you want?"
i = 1
while i < len(jobs):
    list += "\r\n#b#L" + str(i) + "# " + str(jobs[i][0]) + ", " + str(jobs[i][1])
    i += 1

selection = sm.sendNext(list)

sm.createQuestWithQRValue(1406, str(selection))
sm.setSpeakerID(10306)
sm.removeEscapeButton()
sm.sendNext("Oh, t-totally! #h #, you'll make a great " + jobs[selection][0] + "!")

sm.setSpeakerID(10306)
sm.removeEscapeButton()
if selection == 1:
    sm.sendSay("#h #! Should I be a Magician if you're going to be a Warrior?  I'm weak, but maybe with magic, I can help others.")
else:
    sm.sendSay("Maybe I should become a Warrior. I want to learn to stand on my own two feet and use my strength to help others.")

sm.curNodeEventEnd(True)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(True, False, False, False)
sm.playSound("advStory/whistle", 100)
sm.reservedEffect("Effect/Direction3.img/adventureStory/Scene2")
sm.sendDelay(2000)


sm.setSpeakerID(10306)
sm.removeEscapeButton()
sm.sendNext("The ship is ready to set sail!")

sm.startQuest(32216)
sm.completeQuest(32216)
sm.levelUntil(10)
sm.setTemporarySkillSet(0)
sm.setInGameDirectionMode(False, True, False, False)
# Unhandled Message [47] Packet: 2F 03 00 00 00 70 94 00 00 00 00 00 00 26 00 00 00 00 00 00 80 05 BB 46 E6 17 02 0C 00 75 73 65 72 5F 6C 76 75 70 3D 31 30 98 B7 00 00 00 00 00 00 2F 00 00 00 00 00 00 80 05 BB 46 E6 17 02 0C 00 75 73 65 72 5F 6C 76 75 70 3D 31 30 B0 83 08 00 00 00 00 00 2E 02 00 00 00 00 00 80 05 BB 46 E6 17 02 00 00
sm.warp(4000004, 0)