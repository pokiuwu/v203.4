if sm.getFieldID() == 4000014:
    sm.dropItem(4033915,sm.getPosition(objectID).getX(), sm.getPosition(objectID).getY())
elif sm.getFieldID() == 4000030:
    if sm.hasQuest(32213):
        if not sm.hasItem(4033914):
            sm.dropItem(4033914,sm.getPosition(objectID).getX(), sm.getPosition(objectID).getY())

else:
    sm.dropItem(4032980, sm.getPosition(objectID).getX(), sm.getPosition(objectID).getY())
sm.removeReactor()