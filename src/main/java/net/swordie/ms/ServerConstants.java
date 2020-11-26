package net.swordie.ms;

import net.swordie.ms.constants.JobConstants;

/**
 * Created on 2/18/2017.
 */
public class ServerConstants {
    public static final String DIR = System.getProperty("user.dir");
    public static final byte LOCALE = 8;
    public static final String WZ_DIR = DIR + "/wz";
    public static final String DAT_DIR = DIR + "/dat";
    public static final int MAX_CHARACTERS = JobConstants.LoginJob.values().length * 3;
    public static final String SCRIPT_DIR = DIR + "/scripts";
    public static final String RESOURCES_DIR = DIR + "/resources";
    public static final short VERSION = 203;
    public static final String MINOR_VERSION = "4";
    public static final int LOGIN_PORT = 8484;
    public static final short CHAT_PORT = 8483;
    public static final int BCRYPT_ITERATIONS = 10;
}
