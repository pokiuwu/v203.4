package net.swordie.ms.handlers;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.CField;
import net.swordie.ms.connection.packet.ChatSocket;
import net.swordie.ms.Server;
import net.swordie.ms.enums.GroupMessageType;
import net.swordie.ms.world.World;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 4/1/2018.
 */
public class ChatHandler {

    private static Map<Integer, Client> connectedClients = new HashMap<>();

    public static void handleConnect(Client c, InPacket inPacket) {
        int accID = inPacket.decodeInt();
        int idk = inPacket.decodeInt(); // always 1?
        long idk2 = inPacket.decodeLong();
        boolean idk3 = inPacket.decodeByte() != 0;
        int charID = inPacket.decodeInt();
        String charName = inPacket.decodeString();
        int level = inPacket.decodeInt();
        int job = inPacket.decodeInt();
        Char chr = null;
        for(World w : Server.getInstance().getWorlds()) {
            chr = w.getCharByID(charID);
            if(chr != null) {
                break;
            }
        }
        if(chr != null) {
            chr.setChatClient(c);
            c.setChr(chr);
            connectedClients.put(accID, c);
        }
        c.write(ChatSocket.loginResult(chr != null));
    }

    public static void removeClient(int accID) {
        connectedClients.remove(accID);
    }

    public static void handleFriendChat(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        int accID = inPacket.decodeInt();
        String msg = inPacket.decodeString();
        int size = inPacket.decodeInt();
        for (int i = 0; i < size; i++) {
            if(connectedClients.containsKey(i)) {
                connectedClients.get(i).write(ChatSocket.friendChatMessage(accID, chr.getId(), null, msg, false));
            }
        }
    }

    public static void handleGuildChat(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        int charID = inPacket.decodeInt();
        int guildID = inPacket.decodeInt();
        String msg = inPacket.decodeString();
        if (chr.getGuild() != null) {
            chr.getGuild().broadcast(CField.groupMessage(GroupMessageType.Guild, chr, msg));
        }
    }
}
