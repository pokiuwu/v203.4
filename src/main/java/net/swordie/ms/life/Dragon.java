package net.swordie.ms.life;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.util.Position;

/**
 * Created by MechAviv on 12/22/2018.
 */
public class Dragon {
    private int jobCode;
    private int charID;
    private int moveAction;
    private int fh;
    private Position position;
    private Position vposition;

    public Dragon(Char chr) {
        this.charID = chr.getAvatarData().getCharacterStat().getCharacterId();
        this.jobCode = chr.getJob();
        this.moveAction = chr.getMoveAction();
        this.fh = chr.getFoothold();
        this.position = chr.getPosition();
        this.vposition = chr.getPosition();
    }

    public int getJobCode() {
        return jobCode;
    }

    public void setJobCode(int jobCode) {
        this.jobCode = jobCode;
    }

    public int getCharID() {
        return charID;
    }

    public void setCharID(int charID) {
        this.charID = charID;
    }

    public int getMoveAction() {
        return moveAction;
    }

    public void setMoveAction(int moveAction) {
        this.moveAction = moveAction;
    }

    public int getFh() {
        return fh;
    }

    public void setFh(int fh) {
        this.fh = fh;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getvPosition() {
        return vposition;
    }

    public void setvPosition(Position vposition) {
        this.vposition = vposition;
    }
}
