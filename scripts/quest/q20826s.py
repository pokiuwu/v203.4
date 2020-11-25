# Created by MechAviv
# Quest ID :: 20826
# Lesson 1 - Ereve History

sm.setSpeakerID(1102103)
sm.removeEscapeButton()
sm.sendNext("Your first lesson will be about Ereve. Ereve is a floating island, held aloft by the Empress's powers. It has remained stationary for a number of years but once floated about Maple World like a ship.")


sm.setSpeakerID(1102103)
sm.removeEscapeButton()
sm.sendSay("Right now, we're focused on gathering up information on the Black Mage and preparing our forces to face him. It's some serious business, lemme tell you.")


sm.setSpeakerID(1102103)
sm.removeEscapeButton()
sm.sendSay("The knights themselves are gathered into 5 groups, based around the Spirits of Light, Fire, Wind, Lightning, and Darkness. Each group is led by a Chief Knight and...\r\nOh, here is one now. Hello Hawkeye.")


sm.setSpeakerID(1102112)
sm.flipDialogue()
sm.sendSay("Ahoy! I wanted to welcome the new knight in person. I must give ye my apologies, for I left the muffins I baked ye on my ship.")


sm.setSpeakerID(1102103)
sm.removeEscapeButton()
sm.sendSay("Hawkeye, this is highly unorthodox!")


sm.setSpeakerID(1102112)
sm.removeEscapeButton()
sm.flipDialogue()
sm.sendSay("Yar, have a heart, Kinu. Don't ye remember yer first day with the knights? Wasn't it a mite overwhelming without a friend?")


sm.setSpeakerID(1102103)
sm.removeEscapeButton()
sm.sendSay("I suppose I could bend the rules this once. #h0#, meet Hawkeye, the Chief Knight of Lightning.")


sm.startQuest(20826)
