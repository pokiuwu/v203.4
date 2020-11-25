package net.swordie.ms.util.tools;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.handlers.header.OutHeader;

import java.util.Arrays;

/**
 * Created by MechAviv on 3/7/2019.
 */
public class OpcodeUpdater {
    private static String versionInfo = String.format("v%d.%s", ServerConstants.VERSION, ServerConstants.MINOR_VERSION);
    private static int[] newOpcodes = {111, 116, 122, 369, 443, 506, 507, 624, 1083, 1084, 1675, 1676, 1677, 1678, 1679, 1854};

    public static void main(String[] args) {
        int valAdd = 0;
        for (OutHeader header : OutHeader.values()) {
            int intHeader = header.getValue();
            if (intHeader == -1) {
                String opcodeName = header.name(), unkCategory = "";
                if (opcodeName.contains("UNK")) {
                    if (opcodeName.contains("_")) {
                        unkCategory = opcodeName.split("_")[0] + "_";
                    }
                    opcodeName = String.format("%sUNK%d", unkCategory, intHeader + valAdd);
                }
                System.out.println(String.format("%s(%d),", opcodeName, -1));
                continue;
            }
            if (isNewOpcode(intHeader + valAdd)) {
                while (isNewOpcode(intHeader + valAdd)) {
                    System.out.println(String.format("UNK%d(%d),// new %s", intHeader + valAdd, intHeader + valAdd, versionInfo));
                    valAdd++;
                }

                String opcodeName = header.name();
                if (opcodeName.contains("UNK")) {
                    opcodeName = String.format("UNK%d",intHeader + valAdd);
                }
                System.out.println(String.format("%s(%d),", opcodeName, intHeader + valAdd));
            } else {
                String opcodeName = header.name();
                if (opcodeName.contains("UNK")) {
                    opcodeName = String.format("UNK%d", intHeader + valAdd);
                }
                System.out.println(String.format("%s(%d),", opcodeName, intHeader + valAdd));
            }
        }
    }

    private static boolean isNewOpcode(int header) {
        return Arrays.stream(newOpcodes).anyMatch(b -> b == header);
    }
}
