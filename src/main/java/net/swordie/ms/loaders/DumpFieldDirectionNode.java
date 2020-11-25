package net.swordie.ms.loaders;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.enums.FieldType;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.Reactor;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Util;
import net.swordie.ms.util.XMLApi;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.Foothold;
import net.swordie.ms.world.field.Portal;
import net.swordie.ms.world.field.PortalType;
import org.apache.log4j.LogManager;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

/**
 * Created by MechAviv on 3/5/2019.
 * for api things
 */
public class DumpFieldDirectionNode {
    private static Map<Integer, Map<Integer, String>> directionInfo = new HashMap<>();
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    private static String getScriptByDirectionKey(int fieldID, int key) {
        String fieldDir = ServerConstants.DAT_DIR + "/directionInfo/" + fieldID + ".dat";
        File file = new File(fieldDir);
        if (!file.exists()) {
            return "Unknown";
        } else {
            return readFieldFromFile(file, fieldID, key);
        }
    }

    private static String readFieldFromFile(File file, int fieldID, int key) {
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            int size = dataInputStream.readShort();
            Map<Integer, String> mp = new HashMap<>();
            for (int i = 0; i < size; i++) {
                int dKey = dataInputStream.readInt();
                String dVal = dataInputStream.readUTF();
                mp.put(dKey, dVal);
            }
            if (mp.get(key) != null) {
                return mp.get(key);
            }
            directionInfo.put(fieldID, mp);
        } catch (IOException e) {
            return "Unknown";
        }
        return "Unknown";
    }

    private static void saveFields() {
        String dir = String.format("%s/%s", ServerConstants.DAT_DIR, "directionInfo");
        Util.makeDirIfAbsent(dir);
        for (Map.Entry<Integer, Map<Integer, String>> directions : directionInfo.entrySet()) {
            File file = new File(String.format("%s/%d.dat", dir, directions.getKey()));
            try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
                dataOutputStream.writeShort(directions.getValue().size());
                for (Map.Entry<Integer, String> dInfo : directions.getValue().entrySet()) {
                    dataOutputStream.writeInt(dInfo.getKey());
                    dataOutputStream.writeUTF(dInfo.getValue());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //loadFieldInfoFromWz();
        //saveFields();
        System.out.println(getScriptByDirectionKey(910150000, 2));
    }

    private static void loadFieldInfoFromWz() {
        String wzDir = ServerConstants.WZ_DIR + "/Map.wz/Map";
        File dir = new File(wzDir);
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.listFiles() == null) {
                continue;
            }
            for (File mapFile : file.listFiles()) {
                Document doc = XMLApi.getRoot(mapFile);
                Node node = XMLApi.getAllChildren(doc).get(0);
                if (node == null) {
                    continue;
                }
                int id = Integer.parseInt(XMLApi.getAttributes(node).get("name").replace(".img", ""));

                Node directionInfoNode = XMLApi.getFirstChildByNameBF(node, "directionInfo");
                if (directionInfoNode != null) {
                    Map<Integer, String> eventsQ = new HashMap<>();
                    for (Node idNode : XMLApi.getAllChildren(directionInfoNode)) {
                        String name = XMLApi.getNamedAttribute(idNode, "name");
                        int dirKey = Integer.parseInt(name);
                        for (Node dirs : XMLApi.getAllChildren(idNode)) {
                            switch (XMLApi.getNamedAttribute(dirs, "name")) {
                                case "EventQ":
                                    for (Node events : XMLApi.getAllChildren(dirs)) {
                                        //System.out.println("Adding " + XMLApi.getNamedAttribute(events, "value") + " to key " + dirKey);
                                        eventsQ.put(dirKey, XMLApi.getNamedAttribute(events, "value"));
                                    }
                                    break;
                                case "x":
                                case "y":
                                case "forcedInput":
                                    break;
                                default:
                                    System.out.println(XMLApi.getNamedAttribute(dirs, "name"));
                                    break;
                            }
                        }
                    }
                    directionInfo.put(id, eventsQ);
                }
            }
        }
    }
}
