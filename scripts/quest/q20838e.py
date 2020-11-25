# Created by MechAviv
# Quest ID :: 20838
# Certified Knight

sm.setSpeakerID(1102104)
sm.removeEscapeButton()
sm.sendNext("Do you have the Proof of Exam items?")


sm.setSpeakerID(1102104)
sm.removeEscapeButton()
sm.sendSay("Yay! I'm so happy! You're every bit as amazing as I knew you'd be! Here, take this chair. I made it for you! Sit on it when you're tired, and you'll get your HP back faster! I slipped it into your Set-up inventory!")


sm.giveItem(3010060)
sm.completeQuest(20838)