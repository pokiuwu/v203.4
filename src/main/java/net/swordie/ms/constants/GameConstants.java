package net.swordie.ms.constants;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.Equip;
import net.swordie.ms.connection.packet.QuickMoveInfo;
import net.swordie.ms.enums.BaseStat;
import net.swordie.ms.enums.EnchantStat;
import net.swordie.ms.enums.ItemJob;
import net.swordie.ms.enums.QuickMoveType;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.util.container.Triple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 1/23/2018.
 */
public class GameConstants {
    public static final int CHANNELS_PER_WORLD = 3;
    public static final int BUFFED_CHANNELS = 0;
    public static final int MOB_EXP_RATE = 10;
    public static final long MAX_MONEY = 9_999_999_999L;
    public static final short DAMAGE_SKIN_MAX_SIZE = 100;
    public static final int MAX_PET_AMOUNT = 3;
    public static final int MAX_HP_MP = 500000;
    public static final long DAMAGE_CAP = 50_000_000;
    public static final int BEGINNER_SP_MAX_LV = 7;
    public static final int RESISTANCE_SP_MAX_LV = 10;
    public static final int QUICKSLOT_LENGTH = 28;

    // Field
    public static final int NO_MAP_ID = 999999999;
    public static final int VIDEO_FIELD = 931050990; // Used for Effects and/or Videos
    public static final int ARDENTMILL = 910001000;
    public static final int FOREST_OF_TENACITY = 993001000;
    public static final int DEFAULT_FIELD_MOB_CAPACITY = 25;
    public static final double DEFAULT_FIELD_MOB_RATE_BY_MOBGEN_COUNT = 1.5;
    public static final int BASE_MOB_RESPAWN_RATE = 5000; // In milliseconds
    public static final double KISHIN_MOB_MULTIPLIER = 1.7;
    public static final double KISHIN_MOB_RATE_MULTIPLIER = 1.7;
    public static final Rect MOB_CHECK_RECT = new Rect(-100, -100, 100, 100);

    // Drop
    public static final int DROP_HEIGHT = 100; // was 20
    public static final int DROP_DIFF = 25;
    public static final int DROP_REMAIN_ON_GROUND_TIME = 120; // 2 minutes
    public static final int DROP_REMOVE_OWNERSHIP_TIME = 30; // 30 sec
    public static final int MIN_MONEY_MULT = 6;
    public static final int MAX_MONEY_MULT = 9;
    public static final int MAX_DROP_CHANCE = 10000;

    // Combo Kill
    public static final int COMBO_KILL_RESET_TIMER = 5; // 5 sec
    public static final int COMBO_KILL_REWARD_BLUE = 50; // Combo kills
    public static final int COMBO_KILL_REWARD_PURPLE = 350; // Combo kills
    public static final int COMBO_KILL_REWARD_RED = 750; // Combo kills

    // Multi Kill
    public static final float MULTI_KILL_BONUS_EXP_MULTIPLIER = 0.01f; // Multi Kill Bonus Exp given  =  mobEXP * (( multi Kill Amount - 2 ) * 5) * BONUS_EXP_FOR_MULTI_KILL

    // Inner Ability
    public static final int CHAR_POT_BASE_ID = 70000000;
    public static final int CHAR_POT_END_ID = 70000062;
    public static final int BASE_CHAR_POT_UP_RATE = 10; // 10%
    public static final int BASE_CHAR_POT_DOWN_RATE = 10; // 10%
    public static final int CHAR_POT_RESET_COST = 100;
    public static final int CHAR_POT_GRADE_LOCK_COST = 10000;
    public static final int CHAR_POT_LOCK_1_COST = 3000;
    public static final int CHAR_POT_LOCK_2_COST = 5000;

    // Potential Chance on Drop Equips
    public static final int RANDOM_EQUIP_UNIQUE_CHANCE = 1; // out of a 100
    public static final int RANDOM_EQUIP_EPIC_CHANCE = 3; // out of a 100
    public static final int RANDOM_EQUIP_RARE_CHANCE = 8; // out of a 100

    // Random Portal
    public static final int RANDOM_PORTAL_SPAWN_CHANCE = 500; // out of a 1000 (50%)
    public static final int RANDOM_PORTAL_COOLTIME = 15 * 60 * 1000; // 15 minutes

    // Rune
    public static final int RUNE_RESPAWN_TIME = 5; // minutes
    public static final int RUNE_COOLDOWN_TIME = 4; // minutes
    public static final int THUNDER_RUNE_ATTACK_DELAY = 4; // seconds
    public static final int DARKNESS_RUNE_NUMBER_OF_ELITE_MOBS_SPAWNED = 3; // number of elites spawned when activating Rune of Darkness

    // BurningField
    public static final int BURNING_FIELD_MAX_LEVEL = 10; //Maximum Burning Field Level
    public static final int BURNING_FIELD_LEVEL_ON_START = BURNING_FIELD_MAX_LEVEL; //Starts Burning Maps at BurningLevel 10
    public static final int BURNING_FIELD_TIMER = 10; // minutes
    public static final int BURNING_FIELD_MIN_MOB_LEVEL = 0; //Minimum Mob Level for the Field to become a Burning Field
    public static final int BURNING_FIELD_BONUS_EXP_MULTIPLIER_PER_LEVEL = 10; // multiplied by the BurningField Level  =  Bonus Exp% given

    // Exp Orb
    public static final int BLUE_EXP_ORB_ID = 2023484;
    public static final double BLUE_EXP_ORB_MULT = 2;
    public static final int PURPLE_EXP_ORB_ID = 2023494;
    public static final double PURPLE_EXP_ORB_MULT = 3.5;
    public static final int RED_EXP_ORB_ID = 2023495;
    public static final double RED_EXP_ORB_MULT = 5;

    // Mob
    public static final int MOB_SKILL_CHANCE = 20;
    public static final int NX_DROP_CHANCE = 70;

    // Elite mob
    public static final int ELITE_MOB_SKILL_COUNT = 2;
    public static final int ELITE_MOB_RESPAWN_TIME = 120; // seconds
    public static final int ELITE_MOB_SPAWN_CHANCE = 5; // out of a 1000
    public static final int ELITE_MOB_DARK_NOTIFICATION = 17;
    public static final int ELITE_BOSS_REQUIRED_KILLS = 20;
    public static final Integer[] ELITE_BOSS_TEMPLATES = new Integer[]{9303130, 9303131, 9303132, 9303133, 9303134, // 2 types, easy/hard I think
            9303135, 9303136, 9303137, 9303138, 9303139};
    public static final String ELITE_BOSS_BGM = "Bgm45/Anthem For Heroes";
    public static final long ELITE_BOSS_HP_RATE = 500; // multiplier for boss' hp compared to the mobs on the map

    // Familiar
    public static final short FAMILIAR_ORB_VITALITY = 300;

    // Party
    public static final int MAX_PARTY_MOB_LEVEL_DIFF = 5; // x levels lower than mob level
    public static final int MAX_PARTY_CHR_LEVEL_DIFF = 5; // x levels lower than mob level

    // Hyper stat
    public static final long HYPER_STAT_RESET_COST = 10000000;

    // Cash Shop
    public static final int MAX_CS_ITEMS_PER_PAGE = 12;
    public static final int MAX_LOCKER_SIZE = 9999;

    // START OF Party Quests
    public static final long PARTY_QUEST_GLOBAL_EXP = 30000000; // The minimum amount of Exp given from a PQ.

    public static final long PARTY_QUEST_EXP_FORMULA(Char chr) {
        return PARTY_QUEST_GLOBAL_EXP * (1+(chr.getParty().getPartyMembers().length*100 / chr.getParty().getAvgLevel()));
    } // Exp formula for giving Exp from Party Quests

    // Dojo
    public static final int DOJO_DUMMY_DURATION = 10; // Dummy will stay alive for [] minutes, after which it will be removed.
    public static final int DOJO_SPAWM_BOSS_DELAY = 3; // Spawn delay, in seconds, per boss on the Dojo Floors

    // Monster Park
    public static final byte MAX_MONSTER_PARK_RUNS = 7; // Max Monster Park runs per character
    public static final int MONSTER_PARK_EXP_QUEST = 99999; // Quest where the Exp for MP runs gets stored.
    public static final int MONSTER_PARK_ENTRANCE_CHECK_QUEST = 99997; // Quest where the Number of MP runs are stored
    public static final int MONSTER_PARK_TIME = 10  *60; // 10minutes

    // Lord Pirate Party Quest
    public static final int LORD_PIRATE_QUEST = 99998; // Quest where the NPC state is stored, to close/open portals

    // END OF Party Quests

    //Boss QR Values
    public static final int EASY_HORNTAIL_QUEST = 99996; // Quest where the Spawn state of horntail's heads is stored
    public static final int EASY_HILLA_QUEST = 99995; //Quest where the state of hilla portals is stored
    public static final int ARKARIUM_QUEST = 99994; //Quest wehre difficulty of arkarium is stored
    // Trading
    public static final int MAX_TRADE_ITEMS = 9;

    // Faming
    public static final int MIN_LEVEL_TO_FAME = 15;
    public static final int FAME_COOLDOWN = 24; // in hours

    // Guild
    public static final int MAX_DAY_COMMITMENT = 50000;
    public static final int SP_PER_GUILD_LEVEL = 2;
    public static final double GGP_PER_CONTRIBUTION = 0.3;
    public static final double IGP_PER_CONTRIBUTION = 0.7;
    public static final int GUILD_BBS_RECORDS_PER_PAGE = 10;
    public static final int GGP_FOR_SKILL_RESET = 50000;
    public static final int MAX_GUILD_LV = 25;
    public static final int MAX_GUILD_MEMBERS = 200;

    // Monster Collection
    public static final int MOBS_PER_PAGE = 25;
    public static final int MOBS_PER_GROUP = 5;

    public static long[] charExp = new long[276];
    private static int[][] enchantSuccessRates = new int[25][2];
    private static int[][] enchantSuccessRatesSuperior = new int[15][2];
    private static int[] guildExp = new int[MAX_GUILD_LV];

    // Skills
    public static final int TIME_LEAP_QR_KEY = 99996; // Quest where personal Time Leap CDs get stored

    // Starforce
    private static final int STARFORCE_LEVELS[][] = {
            { Integer.MAX_VALUE, -1 }, // per equip
            { 137, (ServerConstants.VERSION >= 197 ? 20 : 13) },
            { 127, (ServerConstants.VERSION >= 197 ? 15 : 12) },
            { 117, 10 },
            { 107, 8 },
            { 95, 5 },
    };

    private static final int STARFORCE_LEVELS_SUPERIOR[][] = {
            { Integer.MAX_VALUE, 15 },
            { 137, 12 },
            { 127, 10 },
            { 117, 8 },
            { 107, 5 },
            { 95, 3 },
    };

    private static List<QuickMoveInfo> quickMoveInfos;

    public static int[][][] INC_HP_MP = {
            // first array = per job
            // then a list of tuples (minHP, maxHP, minMP, maxMP, randMP)
            // 1st value is for levelup, 2nd for assigning sp
                    {{12, 16}, {0, 10}, {12, 0}, {8, 12}, {0, 6}, {8, 15}},// 0
                    {{64, 68}, {0, 4}, {6, 0}, {50, 54}, {0, 2}, {4, 15}},// 1
                    {{10, 14}, {0, 22}, {24, 0}, {6, 10}, {0, 18}, {20, 15}},// 2
                    {{20, 24}, {0, 14}, {16, 0}, {16, 20}, {0, 10}, {12, 15}},// 3
                    {{20, 24}, {0, 14}, {16, 0}, {16, 20}, {0, 10}, {12, 15}},// 4
                    {{22, 26}, {0, 18}, {22, 0}, {18, 20}, {0, 14}, {16, 15}},// 5
                    {{25, 29}, {0, 18}, {22, 0}, {28, 30}, {0, 14}, {16, 15}},// 6
                    {{20, 24}, {0, 14}, {16, 20}, {16, 20}, {0, 10}, {12, 15}},// 7
                    {{44, 48}, {0, 4}, {8, 0}, {30, 34}, {0, 2}, {4, 15}},// 8 - Aran
                    {{16, 20}, {0, 35}, {39, 0}, {12, 16}, {0, 21}, {25, 15}},// 9 - Evan
                    {{20, 24}, {0, 14}, {16, 0}, {16, 20}, {0, 10}, {12, 15}},// 10 - Mercedes
                    {{16, 20}, {0, 198}, {200, 0}, {12, 16}, {0, 21}, {25, 15}},// 11 - Luminous
                    {{34, 38}, {0, 22}, {24, 0}, {20, 24}, {0, 18}, {20, 15}},// 12 - Kinesis/BAM
                    {{20, 24}, {0, 14}, {16, 0}, {16, 20}, {0, 10}, {12, 15}},// 13 - Phantom
                    {{22, 26}, {0, 18}, {22, 0}, {18, 20}, {0, 14}, {16, 15}},// 14 - Mechanic
                    {{52, 56}, {0, 0}, {0, 0}, {38, 40}, {0, 0}, {0, 0}},// 15 - Demon Slayer
                    {{28, 32}, {0, 0}, {0, 0}, {24, 26}, {0, 0}, {0, 0}},// 16 - Angelic Buster
                    {{30, 30}, {0, 0}, {0, 0}, {30, 30}, {0, 0}, {0, 0}},// 17 - Demon Avanger.
                    {{20, 24}, {0, 14}, {16, 0}, {16, 20}, {0, 10}, {12, 15}},// 18 - Xenon
                    {{64, 68}, {0, 0}, {0, 0}, {50, 54}, {0, 0}, {0, 0}},// 19 - Zero
                    {{44, 48}, {0, 18}, {22, 0}, {30, 34}, {0, 14}, {16, 15}},// 20 - Jett
                    {{37, 41}, {0, 22}, {24, 0}, {28, 30}, {0, 18}, {20, 0}},// 21 - Cannon
                    {{44, 48}, {0, 4}, {8, 20}, {34, 38}, {0, 2}, {4, 15}},// 22 - Hayato
                    {{40, 44}, {0, 0}, {0, 0}, {28, 32}, {0, 0}, {0, 0}},// 23 - Kanna
            };
    static {
        initCharExp();
        initEnchantRates();
        initEnchantRatesSuperior();
        initQuickMove();
        initGuildExp();
    }

    private static void initQuickMove() {
        quickMoveInfos = new ArrayList<>();
        quickMoveInfos.add(new QuickMoveInfo(0, 9072302, QuickMoveType.Boat, 1, "Warping", false,
                FileTime.fromType(FileTime.Type.ZERO_TIME), FileTime.fromType(FileTime.Type.MAX_TIME)));
        quickMoveInfos.add(new QuickMoveInfo(0, 9010022, QuickMoveType.DimensionalPortal, 1, "Dimensional Portal", false,
                FileTime.fromType(FileTime.Type.ZERO_TIME), FileTime.fromType(FileTime.Type.MAX_TIME)));
        quickMoveInfos.add(new QuickMoveInfo(0, 9071003, QuickMoveType.MonsterPark, 1, "Monster Park", false,
                FileTime.fromType(FileTime.Type.ZERO_TIME), FileTime.fromType(FileTime.Type.MAX_TIME)));
        quickMoveInfos.add(new QuickMoveInfo(0, 1012124, QuickMoveType.FreeMarket, 1, "Free Market", false,
                FileTime.fromType(FileTime.Type.ZERO_TIME), FileTime.fromType(FileTime.Type.MAX_TIME)));
        quickMoveInfos.add(new QuickMoveInfo(0, 9400302, QuickMoveType.BigHeadward, 1, "Change Hair", false,
                FileTime.fromType(FileTime.Type.ZERO_TIME), FileTime.fromType(FileTime.Type.MAX_TIME)));

        quickMoveInfos.add(new QuickMoveInfo(0, 1012124, QuickMoveType.Cat, 1, "Cat", false,
                FileTime.fromType(FileTime.Type.ZERO_TIME), FileTime.fromType(FileTime.Type.MAX_TIME)));
        quickMoveInfos.add(new QuickMoveInfo(0, 1012124, QuickMoveType.Bunny, 1, "Bunny", false,
                FileTime.fromType(FileTime.Type.ZERO_TIME), FileTime.fromType(FileTime.Type.MAX_TIME)));
        quickMoveInfos.add(new QuickMoveInfo(0, 1012124, QuickMoveType.Girl, 1, "Girl", false,
                FileTime.fromType(FileTime.Type.ZERO_TIME), FileTime.fromType(FileTime.Type.MAX_TIME)));
        quickMoveInfos.add(new QuickMoveInfo(0, 1012124, QuickMoveType.Announcer, 1, "Announcer", false,
                FileTime.fromType(FileTime.Type.ZERO_TIME), FileTime.fromType(FileTime.Type.MAX_TIME)));
        quickMoveInfos.add(new QuickMoveInfo(0, 1012124, QuickMoveType.MobileWoman, 1, "Mobile Woman", false,
                FileTime.fromType(FileTime.Type.ZERO_TIME), FileTime.fromType(FileTime.Type.MAX_TIME)));
        quickMoveInfos.add(new QuickMoveInfo(0, 1012124, QuickMoveType.Waitress, 1, "Waitress", false,
                FileTime.fromType(FileTime.Type.ZERO_TIME), FileTime.fromType(FileTime.Type.MAX_TIME)));
        quickMoveInfos.add(new QuickMoveInfo(0, 1012124, QuickMoveType.Baret, 1, "Baret", false,
                FileTime.fromType(FileTime.Type.ZERO_TIME), FileTime.fromType(FileTime.Type.MAX_TIME)));
    }

    public static List<QuickMoveInfo> getQuickMoveInfos() {
        return quickMoveInfos;
    }

    private static void initCharExp() {
        // NEXTLEVEL::NEXTLEVEL
        charExp[1] = 15;
        charExp[2] = 34;
        charExp[3] = 57;
        charExp[4] = 92;
        charExp[5] = 135;
        charExp[6] = 372;
        charExp[7] = 560;
        charExp[8] = 840;
        charExp[9] = 1242;
        for (int i = 10; i <= 14; i++) {
            charExp[i] = charExp[i-1];
        }
        for(int i = 15; i <= 29; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.2);
        }
        for(int i = 30; i <= 34; i++) {
            charExp[i] = charExp[i-1];
        }
        for(int i = 35; i <= 39; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.2);
        }
        for(int i = 40; i <= 59; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.08);
        }
        for(int i = 60; i <= 64; i++) {
            charExp[i] = charExp[i-1];
        }
        for(int i = 65; i <= 74; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.075);
        }
        for(int i = 75; i <= 89; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.07);
        }
        for(int i = 90; i <= 99; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.065);
        }
        for(int i = 100; i <= 104; i++) {
            charExp[i] = charExp[i-1];
        }
        for(int i = 105; i <= 139; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.065);
        }
        for(int i = 140; i <= 179; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.0625);
        }
        for(int i = 180; i <= 199; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.06);
        }
        // level 200
        charExp[200] = 2207026470L;
        for(int i = 201; i <= 209; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.12);
        }

        // level 210
        charExp[210] = (long) (charExp[209] * 1.375 * 2);
        for(int i = 211; i <= 219; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.08);
        }

        // level 220
        charExp[220] = 84838062013L;
        for(int i = 221; i <= 229; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.04);
        }

        // level 230
        charExp[230] = (long) (charExp[229] * 1.02 * 2);
        for(int i = 231; i <= 239; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.02);
        }

        // level 240
        charExp[240] = (long) (charExp[239] * 1.01 * 2);
        for(int i = 241; i <= 249; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.01);
        }

        // level 250
        charExp[250] = (long) (charExp[249] * 1.01 * 2);
        for(int i = 251; i <= 259; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.01);
        }

        // level 260
        charExp[260] = (long) (charExp[259] * 1.01 * 2);
        for(int i = 261; i <= 269; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.01);
        }

        // level 260
        charExp[270] = (long) (charExp[269] * 1.01 * 2);
        for(int i = 271; i <= 274; i++) {
            charExp[i] = (long) (charExp[i-1] * 1.01);
        }
    }

    public static int getMaxStars(Equip equip) {
        int level = equip.getrLevel() + equip.getiIncReq();
        int stars = Arrays.stream(equip.isSuperiorEqp() ? STARFORCE_LEVELS_SUPERIOR : STARFORCE_LEVELS)
                .filter(lv -> level <= lv[0]).findFirst().get()[1];
        return stars != -1 ? stars : ItemConstants.getItemStarLimit(equip.getItemId());
    }

    private static void initEnchantRates() {
        // kms rates: success / destroy
        // out of 1000
        enchantSuccessRates = new int[][]{
                {950, 0},
                {900, 0},
                {850, 0},
                {850, 0},
                {800, 0},

                {750, 0},
                {700, 0},
                {650, 0},
                {600, 0},
                {550, 0},

                {450, 0},
                {350, 0},
                {300, 7},
                {300, 14},
                {300, 14},

                {300, 21},
                {300, 21},
                {300, 21},
                {300, 28},
                {300, 28},

                {300, 70},
                {300, 70},
                {30, 194},
                {20, 294},
                {10, 396},
        };
    }

    private static void initEnchantRatesSuperior() {
        enchantSuccessRatesSuperior = new int[][]{
                {500, 0},
                {500, 0},
                {450, 0},
                {400, 0},
                {400, 0},

                {400, 18},
                {400, 30},
                {400, 42},
                {400, 60},
                {370, 95},

                {350, 130},
                {350, 162},
                {30, 485},
                {20, 490},
                {10, 500},
        };
    }

    public static void initGuildExp() {
        guildExp[1] = 15000;
        for (int i = 2; i < guildExp.length; i++) {
            guildExp[i] = guildExp[i - 1] + 30000;
        }
    }

    public static long getEnchantmentMesoCost(int reqLevel, int chuc, boolean superior) {
        if (superior) {
            return (long) Math.pow(reqLevel, 3.56);
        } if (chuc < 10) {
            return (long) (1000 + Math.pow(reqLevel, 3) * (chuc + 1) / 25);
        } else if (chuc < 15) {
            return (long) (1000 + Math.pow(reqLevel, 3) * Math.pow(chuc + 1, 2.7) / 400);
        } else {
            return (long) (1000 + Math.pow(reqLevel, 3) * Math.pow(chuc + 1, 2.7) / 200);
        }
    }

    public static int getEnchantmentSuccessRate(Equip equip) {
        if (equip.getDropStreak() >= 2) {
            return 1000;
        }
        int chuc = equip.getChuc();
        if(chuc < 0 || chuc > 24) {
            return 0;
        } else if (equip.isSuperiorEqp()) {
            return enchantSuccessRatesSuperior[chuc][0];
        } else {
            return enchantSuccessRates[chuc][0];
        }
    }

    public static int getEnchantmentDestroyRate(Equip equip) {
        if (equip.getDropStreak() >= 2) {
            return 0;
        }
        int chuc = equip.getChuc();
        if(chuc < 0 || chuc > 24) {
            return 0;
        } else if (equip.isSuperiorEqp()) {
            return enchantSuccessRatesSuperior[chuc][1];
        } else {
            return enchantSuccessRates[chuc][1];
        }
    }

    public static int getStatForSuperiorEnhancement(int reqLevel, short chuc) {
        if (chuc == 0) {
            return reqLevel < 110 ? 2 : reqLevel < 149 ? 9 : 19;
        } else if (chuc == 1) {
            return reqLevel < 110 ? 3 : reqLevel < 149 ? 10 : 20;
        } else if (chuc == 2) {
            return reqLevel < 110 ? 5 : reqLevel < 149 ? 12 : 22;
        } else if (chuc == 3) {
            return reqLevel < 149 ? 15 : 25;
        } else if (chuc == 4) {
            return reqLevel < 149 ? 19 : 29;
        }
        return 0;
    }

    public static int getAttForSuperiorEnhancement(int reqLevel, short chuc) {
        if (chuc == 5) {
            return reqLevel < 150 ? 5 : 9;
        } else if (chuc == 6) {
            return reqLevel < 150 ? 6 : 10;
        } else if (chuc == 7) {
            return reqLevel < 150 ? 7 : 11;
        } else {
            return chuc == 8 ? 12 : chuc == 9 ? 13 : chuc == 10 ? 15 : chuc == 11 ? 17 : chuc == 12 ? 19 : chuc == 13 ? 21 : chuc == 14 ? 23 : 0;
        }
    }

    // ugliest function in swordie
    public static int getEquipStatBoost(Equip equip, EnchantStat es, short chuc) {
        int stat = 0;
        // hp/mp
        if (es == EnchantStat.MHP || es == EnchantStat.MMP) {
            stat += chuc <= 2 ? 5 : chuc <= 4 ? 10 : chuc <= 6 ? 15 : chuc <= 8 ? 20 : chuc <= 14 ? 25 : 0;
        }
        int reqLevel = equip.getrLevel() + equip.getiIncReq();
        // all stat
        if (es == EnchantStat.STR || es == EnchantStat.DEX || es == EnchantStat.INT || es == EnchantStat.LUK) {
            if (chuc <= 4) {
                stat += 2;
            } else if (chuc <= 14) {
                stat += 3;
            } else if (chuc <= 21) {
                stat += reqLevel <= 137 ? 7 : reqLevel <= 149 ? 9 : reqLevel <= 159 ? 11 : reqLevel <= 199 ? 13 : 15;
            }
        }
        // att for all equips
        if ((es == EnchantStat.PAD || es == EnchantStat.MAD) && chuc >= 15) {
            if (chuc == 15) {
                stat += reqLevel <= 137 ? 6 : reqLevel <= 149 ? 7 : reqLevel <= 159 ? 8 : reqLevel <= 199 ? 9 : 12;
            } else if (chuc == 16) {
                stat += reqLevel <= 137 ? 7 : reqLevel <= 149 ? 8 : reqLevel <= 159 ? 9 : reqLevel <= 199 ? 9 : 13;
            } else if (chuc == 17) {
                stat += reqLevel <= 137 ? 7 : reqLevel <= 149 ? 8 : reqLevel <= 159 ? 9 : reqLevel <= 199 ? 10 : 14;
            } else if (chuc == 18) {
                stat += reqLevel <= 137 ? 8 : reqLevel <= 149 ? 9 : reqLevel <= 159 ? 10 : reqLevel <= 199 ? 11 : 14;
            } else if (chuc == 19) {
                stat += reqLevel <= 137 ? 9 : reqLevel <= 149 ? 10 : reqLevel <= 159 ? 11 : reqLevel <= 199 ? 12 : 15;
            } else if (chuc == 20) {
                stat += reqLevel <= 149 ? 11 : reqLevel <= 159 ? 12 : reqLevel <= 199 ? 13 : 16;
            } else if (chuc == 21) {
                stat += reqLevel <= 149 ? 12 : reqLevel <= 159 ? 13 : reqLevel <= 199 ? 14 : 17;
            } else if (chuc == 22) {
                stat += reqLevel <= 149 ? 17 : reqLevel <= 159 ? 18 : reqLevel <= 199 ? 19 : 21;
            } else if (chuc == 23) {
                stat += reqLevel <= 149 ? 19 : reqLevel <= 159 ? 20 : reqLevel <= 199 ? 21 : 23;
            } else if (chuc == 24) {
                stat += reqLevel <= 149 ? 21 : reqLevel <= 159 ? 22 : reqLevel <= 199 ? 23 : 25;
            }
        }
        // att gains for weapons
        if (ItemConstants.isWeapon(equip.getItemId()) && !ItemConstants.isSecondary(equip.getItemId())) {
            if (chuc <= 14) {
                if (es == EnchantStat.PAD) {
                    stat += equip.getiPad() * 0.02;
                } else if (es == EnchantStat.MAD) {
                    stat += equip.getiMad() * 0.02;
                }
            } else if (es == EnchantStat.PAD || es == EnchantStat.MAD) {
                stat += chuc == 22 ? 13 : chuc == 23 ? 12 : chuc == 24 ? 11 : 0;
                if (reqLevel == 200 && chuc == 15) {
                    stat += 1;
                }
            }
        }
        // att gain for gloves, enhancements 4/6/8/10 and 12-14
        if (ItemConstants.isGlove(equip.getItemId()) && (es == EnchantStat.PAD || es == EnchantStat.MAD)) {
            if ((chuc <= 10 && chuc % 2 == 0) || (chuc >= 12 && chuc <= 14)) {
                stat += 1;
            }
        }
        // speed/jump for shoes
        if (ItemConstants.isShoe(equip.getItemId()) && (es == EnchantStat.SPEED || es == EnchantStat.JUMP) && chuc <= 4) {
            stat += 1;
        }
        return stat;
    }

    public static int getEnchantmentValByChuc(Equip equip, EnchantStat es, short chuc, int curAmount) {
        if (equip.isCash() || (ItemData.getEquipById(equip.getItemId()).getTuc() <= 0 && !ItemConstants.isTucIgnoreItem(equip.getItemId()))) {
            return 0;
        }
        if (es == EnchantStat.PDD) {
            return (int) (equip.getiPDD() * (ItemConstants.isOverall(equip.getItemId()) ? 0.10 : 0.05));
        }
        if (es == EnchantStat.MDD) {
            return (int) (equip.getiMDD() * (ItemConstants.isOverall(equip.getItemId()) ? 0.10 : 0.05));
        }
        if (!equip.isSuperiorEqp()) {
            return getEquipStatBoost(equip, es, chuc);
        } else {
            if (es == EnchantStat.STR || es == EnchantStat.DEX || es == EnchantStat.INT || es == EnchantStat.LUK) {
                return getStatForSuperiorEnhancement(equip.getrLevel() + equip.getiIncReq(), chuc);
            }
            if (es == EnchantStat.PAD || es == EnchantStat.MAD) {
                return getAttForSuperiorEnhancement(equip.getrLevel() + equip.getiIncReq(), chuc);
            }
        }
        return 0;
    }

    public static BaseStat getMainStatForJob(short job) {
        if (JobConstants.isBeginnerJob(job) || JobConstants.isBuccaneer(job) || JobConstants.isAdventurerPirate(job)
                || JobConstants.isPinkBean(job) || JobConstants.isDawnWarrior(job) || JobConstants.isKaiser(job)
                || JobConstants.isZero(job) || JobConstants.isDemon(job)
                || JobConstants.isDemonSlayer(job) || JobConstants.isAran(job) || JobConstants.isCannonShooter(job)
            || JobConstants.isDarkKnight(job) || JobConstants.isHero(job) || JobConstants.isPage(job)
            || JobConstants.isBlaster(job) || JobConstants.isHayato(job) || JobConstants.isMihile(job)
            || JobConstants.isShade(job) || JobConstants.isThunderBreaker(job) || JobConstants.isAdventurerWarrior(job)) {
            return BaseStat.str;
        } else if (JobConstants.isJett(job) || JobConstants.isCorsair(job) || JobConstants.isWildHunter(job)
                || JobConstants.isMercedes(job) || JobConstants.isAngelicBuster(job) || JobConstants.isWindArcher(job)
                || JobConstants.isAdventurerArcher(job)) {
            return BaseStat.dex;
        } else if (JobConstants.isBeastTamer(job) || JobConstants.isBlazeWizard(job) || JobConstants.isCleric(job)
                || JobConstants.isEvan(job) || JobConstants.isIceLightning(job) || JobConstants.isFirePoison(job)
                || JobConstants.isAdventurerMage(job) || JobConstants.isKanna(job) || JobConstants.isKinesis(job)
                || JobConstants.isLuminous(job)) {
            return BaseStat.inte;
        } else if (JobConstants.isAdventurerThief(job) || JobConstants.isNightLord(job) || JobConstants.isShadower(job)
                || JobConstants.isPhantom(job) || JobConstants.isNightWalker(job) || JobConstants.isDualBlade(job)) {
            return BaseStat.luk;
        } else if (JobConstants.isDemonAvenger(job)) {
            return BaseStat.mhp;
        }
        return null;
    }

    public static ItemJob getItemJobByJob(int jobArg) {
        short job = (short) jobArg;
        if (JobConstants.isPinkBean(job) || JobConstants.isDawnWarrior(job) || JobConstants.isKaiser(job)
                || JobConstants.isZero(job) || JobConstants.isDemon(job) || JobConstants.isDemonSlayer(job)
                || JobConstants.isAran(job) || JobConstants.isDarkKnight(job) || JobConstants.isHero(job)
                || JobConstants.isPage(job) || JobConstants.isBlaster(job) || JobConstants.isHayato(job)
                || JobConstants.isMihile(job) || JobConstants.isAdventurerWarrior(job)) {
            return ItemJob.WARRIOR;
        }
        if (JobConstants.isWildHunter(job) || JobConstants.isMercedes(job) || JobConstants.isWindArcher(job) ||
                JobConstants.isAdventurerArcher(job)) {
            return ItemJob.BOWMAN;
        }
        if (JobConstants.isBeastTamer(job) || JobConstants.isBlazeWizard(job) || JobConstants.isCleric(job)
                || JobConstants.isEvan(job) || JobConstants.isIceLightning(job) || JobConstants.isFirePoison(job)
                || JobConstants.isAdventurerMage(job) || JobConstants.isKanna(job) || JobConstants.isBlazeWizard(job)
                || JobConstants.isKinesis(job) || JobConstants.isLuminous(job)) {
            return ItemJob.MAGICIAN;
        }
        if (JobConstants.isAdventurerThief(job) || JobConstants.isNightLord(job) || JobConstants.isShadower(job)
                || JobConstants.isPhantom(job) || JobConstants.isNightWalker(job) || JobConstants.isDualBlade(job)) {
            return ItemJob.THIEF;
        }
        if (JobConstants.isBuccaneer(job) || JobConstants.isAdventurerPirate(job) || JobConstants.isCannonShooter(job)
                || JobConstants.isShade(job) || JobConstants.isThunderBreaker(job) || JobConstants.isCorsair(job)
                || JobConstants.isAngelicBuster(job) || JobConstants.isJett(job)) {
            return ItemJob.PIRATE;
        } else {
            return ItemJob.BEGINNER;
        }
    }

    public static BaseStat getSecStatByMainStat(BaseStat mainStat) {
        if(mainStat == null) {
            return null;
        }
        switch(mainStat) {
            case str:
                return BaseStat.dex;
            case dex:
                return BaseStat.str;
            case inte:
                return BaseStat.luk;
            case luk:
                return BaseStat.dex;
        }
        return null;
    }

    public static double getExpOrbExpModifierById(int itemID) {
        switch (itemID) {
            case BLUE_EXP_ORB_ID:
                return BLUE_EXP_ORB_MULT;
            case PURPLE_EXP_ORB_ID:
                return PURPLE_EXP_ORB_MULT;
            case RED_EXP_ORB_ID:
                return RED_EXP_ORB_MULT;
        }
        return 0;
    }

    /**
     * Gets a list of possible elite stats by mob level.
     * @param level the level of the mob
     * @return list of Triples, each triple indicating the level (left), extra hp rate (mid) and the extra exp/meso drop rate (right).
     */
    public static List<Triple<Integer, Double, Double>> getEliteInfoByMobLevel(int level) {
        List<Triple<Integer, Double, Double>> list = new ArrayList<>();
        if (level < 100) {
            list.add(new Triple<>(0, 21D, 10.5));
            list.add(new Triple<>(1, 29D, 14.5));
            list.add(new Triple<>(2, 38D, 19D));
        } else if (level < 200) {
            list.add(new Triple<>(0, 30D, 15D));
            list.add(new Triple<>(1, 45D, 22.5));
            list.add(new Triple<>(2, 60D, 30D));
        } else {
            // level >= 200
            list.add(new Triple<>(0, 35D, 17.5));
            list.add(new Triple<>(1, 52.5, 26.25));
            list.add(new Triple<>(2, 70D, 35D));
        }
        return list;
    }

    public static double getPartyExpRateByAttackersAndLeechers(int attackers, int leechers) {
        if (leechers == 1) {
            return 0; // Just 1 attacker
        }
        if (attackers >= 3) {
            switch (leechers) {
                case 6:
                    return 1.95;
                case 5:
                    return 1.5;
                case 4:
                    return 1.1;
                default:
                    return 0.75;
            }
        } else {
            switch (leechers) {
                case 6:
                    return 1.65 + attackers * 0.1;
                case 5:
                    return 1.2 + attackers * 0.1;
                case 4:
                    return 0.8 + attackers * 0.1;
                case 3:
                    return 0.4 + attackers * 0.1;
                default:
                    return 0.15 + attackers * 0.1;
            }
        }
    }

    public static long applyTax(long money) {
        // 5% global tax starting from v180ish
        return Math.round(money * 0.95);
    }

    public static int getExpRequiredForNextGuildLevel(int curLevel) {
        if (curLevel >= 25 || curLevel < 0) {
            return 0;
        }
        return guildExp[curLevel];
    }

    // -1 if not in Maplerunner
    // 0 for lobby
    // N (1-50) for Maplerunner stages
    public static int getMaplerunnerField(int fieldId) {
        // Forest of Tenacity prefix
        if (fieldId / 1000 != 993001) {
            return -1;
        }
        return fieldId % 1000 / 10;
    }

    public static int[][] getIncValArray(int job) {
        int jobRace = job / 1000;
        int jobCategory = JobConstants.getJobCategory((short) job);
        if (jobCategory <= 9) {
            if (job / 10 == 53 || job == 501) {
                return INC_HP_MP[21];
            }
            if (JobConstants.isLuminous((short) job)) {
                return INC_HP_MP[11];
            }
            if (jobRace == 2) {
                switch (jobCategory) {
                    case 1:// Aran
                        return INC_HP_MP[8];
                    case 2:// Evan
                        return INC_HP_MP[9];
                    case 3:// Mercedes
                        return INC_HP_MP[10];
                    case 4:// Phantom
                        return INC_HP_MP[13];
                }
            }
            if (JobConstants.isBattleMage((short) job) || JobConstants.isKinesis((short) job)) {
                return INC_HP_MP[12];
            } else if (JobConstants.isWildHunter((short) job)) {
                return INC_HP_MP[3];// can use default ? :/
            } else if (JobConstants.isMechanic((short) job)) {
                return INC_HP_MP[14];
            } else if (JobConstants.isDemonSlayer((short) job)) {
                return INC_HP_MP[15];
            } else if (JobConstants.isAngelicBuster((short) job)) {
                return INC_HP_MP[16];
            } else if (JobConstants.isDemonAvenger((short) job)) {
                return INC_HP_MP[17];
            } else if (JobConstants.isXenon((short) job)) {
                return INC_HP_MP[18];
            } else if (JobConstants.isZero((short) job) || JobConstants.isBlaster((short) job)) {
                return INC_HP_MP[19];
            } else if (JobConstants.isJett((short) job)) {
                return INC_HP_MP[20];
            } else if (JobConstants.isBeastTamer((short) job)) {
                return INC_HP_MP[21];
            } else if (JobConstants.isHayato((short) job)) {
                return INC_HP_MP[22];
            } else if (JobConstants.isKanna((short) job)) {
                return INC_HP_MP[23];
            }
            return INC_HP_MP[jobCategory];
        }
        return null;// something wrong.
    }

    public static boolean isValidName(String name) {
        return name.length() >= 4 && name.length() <= 13 && Util.isDigitLetterString(name);
    }

    public static boolean isValidEmotion(int emotion) {
        return emotion >= 0 && emotion <= 45;
    }

    public static boolean isFreeMarketField(int id) {
        // room 1~22
        return id > 910000000 && id < 910001000;
    }
}
