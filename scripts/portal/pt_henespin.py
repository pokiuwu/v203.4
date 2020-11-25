# Created by MechAviv
# Map ID :: 100000000
# Henesys : Henesys
if sm.hasQuest(32707) or chr.getLevel() < 100:
    sm.warp(100000004, 1)
else:
    sm.progressMessageFont(3, 20, 20, 0, "Spin-off: FriendStory is available starting at Lv. 100.")