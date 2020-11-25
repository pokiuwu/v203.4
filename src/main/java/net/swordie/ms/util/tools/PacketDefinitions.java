package net.swordie.ms.util.tools;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.handlers.header.OutHeader;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Created by MechAviv on 2/3/2019.
 */
public class PacketDefinitions {

    private static final int BUILD = ServerConstants.VERSION;
    private static final int LOCALE = 8;
    private static final boolean out = true, in = true;
    public static void main(String[] args) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("PacketDefinitions.xml"));
            writer.println("<ArrayOfDefinition xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">");
            printDefinition(writer, (1 << 16) - 1, "Maple Handshake", false, false);
            if (out) printSendDefinitions(writer);
            if (in) printRecvDefinitions(writer);
            writer.print("</ArrayOfDefinition>");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printSendDefinitions(PrintWriter writer) {
        for (OutHeader opcode : OutHeader.values()) {
            printDefinition(writer, opcode.getValue(), opcode.toString(), false, isIgnored(opcode));
        }
    }

    private static void printRecvDefinitions(PrintWriter writer) {
        for (InHeader opcode : InHeader.values()) {
            printDefinition(writer, opcode.getValue(), opcode.toString(), true, isIgnored(opcode));
        }
    }

    private static void printDefinition(PrintWriter writer, int op, String name, boolean outbound, boolean ignore){
        if (op < 0) {
            return;
        }
        writer.println("  <Definition>");
        writer.println("    <Build>" + BUILD + "</Build>");
        writer.println("    <Locale>" + LOCALE + "</Locale>");
        writer.println("    <Outbound>" + (outbound ? "true" : "false") + "</Outbound>");
        writer.println("    <Opcode>" + op + "</Opcode>");
        writer.println("    <Name>" + name + "</Name>");
        writer.println("    <Ignore>" + (ignore ? "true" : "false") + "</Ignore>");
        writer.println("  </Definition>");
    }

    private static boolean isIgnored(OutHeader header) {
        switch (header) {
            //case PING:
            //    return true;
            default:
                return false;
        }
    }

    private static boolean isIgnored(InHeader header) {
        switch (header) {
            //case PONG:
            //    return true;
            default:
                return false;
        }
    }
}
