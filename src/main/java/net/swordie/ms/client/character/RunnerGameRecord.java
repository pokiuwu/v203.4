package net.swordie.ms.client.character;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.util.FileTime;

/**
 * Created on 12/20/2017.
 */
public class RunnerGameRecord {
    private FileTime lastPlayed = FileTime.fromType(FileTime.Type.ZERO_TIME);
    private int characterID;
    private int lastScore;
    private int highScore;
    private int runnerPoint;
    private int totalLeft;

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getCharacterID());
        outPacket.encodeInt(getLastScore());
        outPacket.encodeInt(getHighScore());
        outPacket.encodeInt(getRunnerPoint());
        outPacket.encodeFT(getLastPlayed());
        outPacket.encodeInt(getTotalLeft());
    }

    public FileTime getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(FileTime lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    public int getCharacterID() {
        return characterID;
    }

    public void setCharacterID(int characterID) {
        this.characterID = characterID;
    }

    public int getLastScore() {
        return lastScore;
    }

    public void setLastScore(int lastScore) {
        this.lastScore = lastScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getRunnerPoint() {
        return runnerPoint;
    }

    public void setRunnerPoint(int runnerPoint) {
        this.runnerPoint = runnerPoint;
    }

    public int getTotalLeft() {
        return totalLeft;
    }

    public void setTotalLeft(int totalLeft) {
        this.totalLeft = totalLeft;
    }
}
