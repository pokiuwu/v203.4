package net.swordie.ms.util;

import net.swordie.ms.ServerConstants;
import org.apache.log4j.LogManager;

import java.io.*;
import java.util.Scanner;

/**
 * Created on 1/3/2018.
 */
public class MetaProgramming {
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    public static void makeStateless() {
        String dir = ServerConstants.SCRIPT_DIR;
        String[] subDirs = new String[]{"field", "item", "npc", "portal", "quest", "reactor"};
        for (String subDir : subDirs) {
            File exactDir = new File(dir + "/" + subDir);
            for (File file : exactDir.listFiles()) {
                makeStateless(file);
            }
        }
    }

    public static void makeStateless(File file) {
        File outFile = new File(file.getAbsolutePath().replace("Swordie\\scripts", "Swordie\\stateless"));
        boolean fromRemoved = true;
        StringBuilder sb = new StringBuilder();
        try(Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                int tabs = 0;
                String line = scanner.nextLine();
                char[] chars = line.toCharArray();
                int spaceCount = 0;
                for (char c : chars) {
                    if (c == '#') {
                        break;
                    }
                    if (c == '\t') {
                        tabs++;
                    } else if (c == ' ') {
                        spaceCount++;
                        if (spaceCount == 4) {
                            spaceCount = 0;
                            tabs++;
                        }
                    } else {
                        break;
                    }
                }
                if (line.contains("status")) {
                } else if (line.contains("def init") || line.contains("def action(response")) {
                    fromRemoved = true;
                }  else {
                    if (line.contains("def")) {
                        fromRemoved = false;
                    }
                    line = line.replace("\t", "").replace("    ", "");
                    String newLine = "";
                    for (int i = 0; i < (fromRemoved ? tabs - 1 : tabs); i++) {
                        newLine += "    ";
                    }
                    newLine += line;
                    newLine = newLine.replace("sm.sendAskYesNo", "response = sm.sendAskYesNo");
                    newLine = newLine.replace("sm.sendAskAccept", "response = sm.sendAskAccept");
//                    System.out.println(newLine + "              (neededtabs = " + neededTabs + ", tabs = " + tabs + ", changedLast = " + changedLast + ", fromRemoved = " + fromRemoved + ")");
                    sb.append(newLine).append("\r\n");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (PrintWriter fos = new PrintWriter(outFile)){
            fos.write(sb.toString());
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void fixTempStats() {
//        Invincible(0x10000000, 2),
        int plus = 5;
        File file = new File("D:/SwordieMS/Swordie/info txts/tempstats.txt");
        try (Scanner scanner = new Scanner(file)) {

            String s = "";
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() > 4) {
                    String[] split = line.split("[(,]");
                    String name = split[0];
                    int num;
                    if (split[1].contains("x")) {
                        if (split[1].equalsIgnoreCase("0x80000000")) {
                            num = 0x80000000;
                        } else {
                            num = Integer.parseInt(split[1].replace("0x", ""), 16);
                        }
                    } else {
                        num = Integer.parseInt(split[1]);
                    }
                    int pos = Integer.parseInt(split[2].replace(" ", "").replace(")", ""));
                    if (num / plus <= 1) {
                        pos++;
                        num = num << (32 - plus);
                    } else {
                        num = num >>> plus;
                        if (num == 0) {
                            num = 0x80000000;
                            pos++;
                        }
                    }
                    log.debug(name + "(" + String.format("0x%X", num) + ", " + pos + "),");
                    if (num == 0x1 || num == 0x100 || num == 0x10000 || num == 0x1000000) {
                        log.debug("");
                    }
                }
            }
           log.debug(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void makeHeaders() {

        File file = new File("D:/SwordieMS/Swordie/src/headerText.txt");
        try (Scanner scanner = new Scanner(file)) {
            String s = "";
            int num = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("case") && line.contains(":")) {
                    String s2 = line.replace(":", "");
                    String s3 = s2.replaceAll(" ", "");
                    String s4 = s3.replace("case", "");

                    num = Integer.parseInt(s4);
                } else if (line.contains("::")) {
                    log.debug(line.replace("LOBYTE(v3) = ", ""));
                    String op = line.replace("LOBYTE(v3) = ", "").split("[(|:]")[2].replace(":", "");
                    StringBuilder newOp = new StringBuilder();
                    for (char c : op.toCharArray()) {
                        if (Character.isUpperCase(c)) {
                            newOp.append("_").append(c);
                        } else {
                            newOp.append(c);
                        }
                    }
                    String fin = newOp.toString().toUpperCase().substring(1).replace("ON_", "");
                    s += fin + "(" + num + "), \r\n";
                }
            }
            log.debug(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void makeCTS() {
        File file = new File("D:/SwordieMS/Swordie/src/decodeforlocal.txt");
        try {
            Scanner scanner = new Scanner(file);
            StringBuilder s = new StringBuilder();
            int num = 53;
            String lastSeen = "";
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("&CTS_")) {
                    String[] split = line.split("&CTS_");
                    String s2 = split[1];
                    String s3 = s2.replaceAll(" ", "");
                    String s4 = s3.replaceAll("[);]", "");
                    if (!s4.equalsIgnoreCase(lastSeen)) {
//                        s.append("if(hasNewStat(").append(s4).append(")) {\r\n");
//                        s.append("\toutPacket.encodeShort(getOption(").append(s4).append(").nOption);\r\n");
//                        s.append("\toutPacket.encodeInt(getOption(").append(s4).append(").rOption);\r\n");
//                        s.append("\toutPacket.encodeInt(getOption(").append(s4).append(").tOption);\r\n");
//                        s.append("}\r\n");
                        s.append(s4).append("(").append(num % 32).append(", ").append(num / 32).append("),\r\n");
                        lastSeen = s4;
                        num++;
                    }
                }
            }
            log.debug(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void makeInHeaders() {
        File file = new File("D:/SwordieMS/Swordie/src/ins.txt");
        int change = 0;
        try {
            Scanner scanner = new Scanner(file);
            String s = "";
            int num = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.endsWith("h")) {
                    line = line.substring(0, line.length() - 1);
                }
                String[] split = line.split(" = ");
                String op = split[0].replace(" ", "");
                log.debug(op);
                if (op.contains("DropEnterField")) {
                    change = 152;
                }
                num = Integer.parseInt(split[1], 16) + change;
                StringBuilder newOp = new StringBuilder();
                for (char c : op.toCharArray()) {
                    if (Character.isUpperCase(c)) {
                        newOp.append("_").append(c);
                    } else {
                        newOp.append(c);
                    }
                }
                String fin = newOp.toString().toUpperCase().substring(1).replaceFirst("ON_", "");
                s += fin + "(" + num + "), \r\n";
            }
            log.debug(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        makeStateless(new File("D:\\SwordieMS\\Swordie\\old_scripts\\portal\\Resi_tutor11.py"));
        makeStateless();
    }
}
