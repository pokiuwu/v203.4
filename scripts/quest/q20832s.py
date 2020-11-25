# Created by MechAviv
# Quest ID :: 20832
# Lesson, Interrupted

sm.setSpeakerID(1102102)
sm.removeEscapeButton()
sm.flipDialogue()
sm.sendNext("Your basic attacks no longer make me want to cry. It is time to teach you something more advanced!")


sm.createQuestWithQRValue(20832, "quit")
sm.setSpeakerID(1102100)
sm.removeEscapeButton()
sm.flipDialogue()
sm.sendSay("Hey, everyone. You having fun with your training, #h0#? Kimu, Kizan, can I speak to you for a moment?")


sm.setSpeakerID(1102102)
sm.removeEscapeButton()
sm.flipDialogue()
sm.sendSay("#h0#! Take a break! That's an order!")


sm.setSpeakerID(1102100)
sm.removeEscapeButton()
sm.flipDialogue()
sm.sendSay("(Kiku whispers to the others.)")


sm.setSpeakerID(1102102)
sm.removeEscapeButton()
sm.flipDialogue()
sm.sendSay("!!!")


sm.setSpeakerID(1102101)
sm.removeEscapeButton()
sm.flipDialogue()
sm.sendSay("...")


sm.setSpeakerID(1102102)
sm.removeEscapeButton()
sm.flipDialogue()
sm.sendSay("#h0#! Do not move from that spot! I will return shortly.")

sm.startQuest(20832)
sm.createQuestWithQRValue(20832, "quit")
sm.completeQuest(20832)
