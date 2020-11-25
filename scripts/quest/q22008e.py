# 22008 |   Chasing away the foxes (Evan intro)
sm.setSpeakerID(1013101)
sm.sendNext("Did you defeat the #o9300385#es?")

sm.setPlayerAsSpeaker()
sm.sendSay("#bWhat happened to slaying the Foxes left behind?")

sm.setSpeakerID(1013101)
sm.sendSay("Oh, that? Haha. I did chase them, sort of, but I wanted to make sure that they catch up to you. I wouldn't want you eaten by a #o9300385# or anything. So I just let them be.")

sm.setPlayerAsSpeaker()
sm.sendSay("#bAre you sure you weren't just hiding because you were scared of the Foxes?")

sm.setSpeakerID(1013101)
sm.sendSay("What? No way! Sheesh, I fear nothing!")

sm.setPlayerAsSpeaker()
sm.sendSay("#bWatch out! There's a #o9300385# right behind you!")

sm.setSpeakerID(1013101)
sm.sendSay("Eeeek! Mommy!")

sm.setPlayerAsSpeaker()
sm.sendSay("#b...")

sm.setSpeakerID(1013101)
sm.sendSay("...")
sm.sendSay("You little brat! I'm your older brother. Don't you mess with me! Your brother has a weak heart, you know. Don't surprise me like that!")

sm.setPlayerAsSpeaker()
sm.sendSay("#b(This is why I don't want to call you Older Brother...)")

sm.setSpeakerID(1013101)
sm.sendSay("Hmph! Anyway, I'm glad you were able to defeat the #o9300385#es. As a reward, I'll give you something an adventurer gave me a long time ago. Here you are.\r\n\r\n#fUI/UIWindow2.img/QuestIcon/4/0# \r\n#i1372043# 1 #t1372043# \r\n#i2022621# 25 #t2022621# \r\n#i2022622# 25 #t2022622#s \r\n\r\n#fUI/UIWindow2.img/QuestIcon/8/0# 910 exp")

if sm.canHold(1372107) and sm.canHold(2022621) and sm.canHold(2022622):
    if not sm.hasQuestCompleted(parentID):
        sm.giveItem(1372107)
        sm.giveItem(2022621, 25)
        sm.giveItem(2022622, 25)
        sm.giveExp(910)
        sm.completeQuest(parentID)
    sm.sendSay("#bThis is a weapon that Magicians use. It's a Wand#k. You probably won't really need it, but it'll make you look important if you carry it around. Hahahahaha.")
    sm.sendSay("Anyway, the Foxes have increased, right? How weird is that? Why are they growing day by day? We should really look into it and get to the bottom of this.")
    sm.dispose()
else:
    sm.sendNext("Please make room in your Equip Inventory.")
    sm.dispose()



