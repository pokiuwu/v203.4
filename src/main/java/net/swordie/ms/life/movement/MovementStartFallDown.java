package net.swordie.ms.life.movement;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.life.Dragon;
import net.swordie.ms.life.Life;
import net.swordie.ms.util.Position;

/**
 * Created on 1/2/2018.
 */
public class MovementStartFallDown extends MovementBase {
    public MovementStartFallDown(InPacket inPacket, byte command) {
        super();
        this.command = command;
        this.position = new Position(0, 0);

        short xv = inPacket.decodeShort();
        short xy = inPacket.decodeShort();
        vPosition = new Position(xv, xy);

        // I'm not sure about this, it' needs testing. <- mushy
        // Should be fallStart (foothold fall start)?
        footStart = inPacket.decodeShort();

        moveAction = inPacket.decodeByte();
        elapse = inPacket.decodeShort();
        forcedStop = inPacket.decodeByte();
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(getCommand());
        outPacket.encodePosition(getVPosition());
        outPacket.encodeShort(getFootStart());
        outPacket.encodeByte(getMoveAction());
        outPacket.encodeShort(getDuration());
        outPacket.encodeByte(getForcedStop());
    }

    @Override
    public void applyTo(Char chr) {
        chr.setPosition(getPosition());
        chr.setFoothold(getFh());
        chr.setMoveAction(getMoveAction());
    }

    @Override
    public void applyTo(Life life) {
        life.setPosition(getPosition());
        life.setvPosition(getVPosition());
        life.setFh(getFh());
        life.setMoveAction(getMoveAction());
    }

    @Override
    public void applyTo(Dragon dragon) {
        dragon.setPosition(getPosition());
        dragon.setvPosition(getVPosition());
        dragon.setFh(getFh());
        dragon.setMoveAction(getMoveAction());
    }
}
