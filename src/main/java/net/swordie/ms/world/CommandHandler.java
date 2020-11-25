package net.swordie.ms.world;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.jobs.adventurer.Kinesis;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.AdminCommandType;

/**
 * Created by MechAviv on 1/17/2019.
 */
public class CommandHandler {
    public static void handleAdminCommand(Char chr, InPacket inPacket) {
        if (chr.getAccount().isManagerAccount()) {
            int commandType = inPacket.decodeInt();
            AdminCommandType cmd = AdminCommandType.getByVal(commandType);
            switch (cmd) {
                case FullPsychicPoint:
                    if (JobConstants.isKinesis(chr.getJob())) {
                        ((Kinesis) chr.getJobHandler()).updatePsychicPoints(Kinesis.MAX_PP);
                    }
                    break;
            }
        }
    }
}
