package net.swordie.ms.util.tools;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.StringData;
import net.swordie.ms.loaders.containerclasses.ItemInfo;
import net.swordie.ms.util.Util;
import net.swordie.ms.util.XMLApi;
import org.w3c.dom.Node;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by MechAviv on 3/21/2019.
 */
public class DamageSkinCreator {
    private static final boolean printCases = true;// Prints switch cases for ItemConstants.
    private static final boolean printItemID = false;// Prints item id to find which item id cause an error.

    // Creating damage skin script for damage skins with item id in wz.
    public static void main(String[] args) {
        StringData.load();
        Node damageSkinNode = XMLApi.getNodeByPath("Effect.wz/BasicEff.img", "damageSkin");
        for (Node damageSkin : XMLApi.getAllChildren(damageSkinNode)) {
            int damageSkinID = Integer.parseInt(XMLApi.getNamedAttribute(damageSkin, "name"));
            for (Node data : XMLApi.getAllChildren(damageSkin)) {
                String name = XMLApi.getNamedAttribute(data, "name");
                String strValue = XMLApi.getNamedAttribute(data, "value");
                if (strValue == null || strValue.isEmpty() || !Util.isNumber(strValue)) {
                    continue;
                }
                int value = Integer.parseInt(strValue);
                switch (name) {
                    case "ItemID":
                        if (value / 10000 == 243 && !isIgnored(value)) {
                            if (printCases) System.out.println(String.format("case %d:// %s\r\nreturn %d;", value, StringData.getItemStringById(value), damageSkinID));
                            createScript(value);
                        }
                        break;
                }
            }
        }
    }

    private static boolean isIgnored(int itemID) {
        switch (itemID) {
            case 2436986:
                return true;
        }
        return false;
    }

    private static void createScript(int itemID) {
        ItemInfo ii = ItemData.getItemInfoByID(itemID);
        String script;
        if (printItemID) System.out.println("[DEBUG] Item ID " + itemID);
        if (ii.getScript() == null || ii.getScript().isEmpty()) {
            script = String.valueOf(itemID);
        } else {
            script = ii.getScript();
        }
        File file = new File(ServerConstants.DIR + "/scripts/item/" + script + ".py");
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            writer.write("# Created by MechAviv\r\n");
            writer.write(String.format("# %s | (%d)\n", StringData.getItemStringById(itemID), itemID));
            writer.write(String.format("if sm.addDamageSkin(%d):\n", itemID));
            writer.write(String.format("    sm.chat(%s)\r\n", Util.quotes(String.format("'%s'  Damage Skin has been added to your account's damage skin collection.", StringData.getItemStringById(itemID)))));
            writer.write("    sm.consumeItem()");
        }
        catch (IOException ex) {}
    }
}
