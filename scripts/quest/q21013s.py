# Created by MechAviv
# Quest ID :: 21013
# A Gift for the Hero

sm.setSpeakerID(1202005)
selection = sm.sendNext("Ah, you're the hero. I've been dying to meet you.\r\n#b#L0#(Seems a bit shy...)#l")
if selection == 0:
    sm.setSpeakerID(1202005)
    if sm.sendAskAccept("I have something I've been wanting to give you as a gift for a very long time... I know you're busy, especially since you're on your way to town, but will you accept my gift?"):
        sm.startQuest(21013)
        sm.setSpeakerID(1202005)
        sm.removeEscapeButton()
        sm.sendNext("The parts of the gift have been packed inside a box nearby. Sorry to trouble you, but could you break the box and bring me a #b#t4032309##k and some #b#t4032310##k? I'll assemble them for you right away.")

        sm.tutorAutomatedMsg(18)
    else:
        sm.setSpeakerID(1202005)
        sm.sendNext("I'm sure it will come in handy during your journey. Please, don't decline my offer.")