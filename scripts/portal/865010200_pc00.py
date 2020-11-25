# Created by MechAviv
# ID :: [865010200]
# Commerci Republic : Berry
if sm.hasQuestCompleted(17612) and not sm.hasQuestCompleted(17613):
    sm.startQuest(17613) # [Commerci Republic] The Minister's Son
    sm.warpInstanceIn(865090001, 1)
sm.dispose()