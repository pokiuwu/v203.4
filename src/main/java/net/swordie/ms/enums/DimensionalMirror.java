package net.swordie.ms.enums;

import net.swordie.ms.util.Util;

import java.util.Arrays;

/**
 * Created by MechAviv on 3/3/2019.
 */
public enum DimensionalMirror {
    MU_LUNG_DOJO(0, 925020000, "Mu Lung Dojo", "Challenge Mu Lung Dojo and see how strong you REALLY are.", 105, 0, 1207, new int[]{1082392, 1082393, 1082394}, false),
    EREVE_CONFERENCE_PAVILION(1, 913050010, "Ereve Conference Pavilion", "The Maple Alliance has been formed at the Continental Conference. \nBattle the Black Mage in 'Black Heaven' and 'Heroes of Maple'!", 75, 0, 7998, new int[]{1142769, 1142804, 3015030, 3700350, 3017016}, false),
    FIGHT_FOR_AZWAN(2, "Fight for Azwan", "Eliminate the remainder of Hilla's forces in the Azwan area.", 120, 0, 7892, new int[]{1162009, 2430690, 1032136}, false),
    EVOLUTION_LAB(3, "Evolution Lab", "Ready for the wonders of the Evolution System?", 105, 1801, 1819, new int[]{1162013, 4001832, 2431935, 2431936}, false),
    DIMENSION_INVASION(4, "Dimension Invasion", "Defeat the invaders before it's too late!", 140, 0, 31836, new int[]{1082488, 1082489, 1142527, 1142528, 1142529, 1142530}, false),
    CRIMSONHEART_CASTLE(5, "Crimsonheart Castle", "A message from Masteria... What's going on?", 130, 31241, 31265, new int[]{1142619, 2431935, 2431936}, false),
    GRAND_ATHENAEUM(6, 302000000, "Grand Athenaeum", "Head to the Grand Athenaeum to get a glipse of the past.", 100, 0, 32670, new int[]{1122263, 2431892}, false),
    PARTY_QUEST_ENTRANCE(7, 910002000, "Party Quest Entrance", "It's more fun when you play with friends! Create a party \nto participate in a special mission.", 50, 0, 7887, new int[]{1003762, 1022073, 1132013, 1022175, 1902048, 2432131}, true),
    TOWER_OF_OZ(8, "Tower of Oz", "Conquer the underwater tower known as the Tower of Oz to help Alicia's Soul.", 140, 42009, 7839, new int[]{2028263, 2432465}, false),
    FRIENDSTORY(9, 100000004, "FriendStory", "Experience FriendStory, the first spin-off of MapleStory!", 100, 32707, 33041, new int[]{1182079, 3015119, 3010875, 2432776, 2432788}, false),
    MONSTER_PARK_REBORN(10, "Monster Park REBORN", "Massive EXP and different daily rewards await you!", 105, 0, 7900, new int[]{2434746, 2434747, 2434748, 2434749, 2434750, 2434751, 2434745}, false),
    ROOT_ABYSS(11, "Root Abyss", "Battle the seal guardians to weaken them. \nThe future of Maple World is in your hands.", 125, 30000, 30028, new int[]{1003715, 1003716, 1003717, 1003718}, false),
    URSUS(12, "Ursus", "Think you can take on Ursus the Destroyer?", 100, 0, 33553, new int[]{3015279, 2434509, 2434389, 3700334, 1142879}, true),
    TWISTED_AQUA_ROAD(500, "Twisted Aqua Road", "Do you want to move to Twisted Aqua Road?", 200, 17100, 17132, new int[]{}, false),
    PRINCESS_NO_PARTY_QUEST(501, "Princess No Party Quest", "Do you want to challenge Princess No?", 140, 58913, 58971, new int[]{2432755, 2432754, 2432753, 3010864}, false),
    ALISHAN(502, 749080900, 1, "Alishan", "Do you want to move to Alishan?", 33, 55234, 55255, new int[]{1202160, 2434004}, false),
    EVENT_HALL(503, 820000000, 2, "Event Hall", "Do you want to move to Event Hall?", 1, 0, 5399, new int[]{}, false),
    ALIEN_VISITOR(504, "Alien Visitor", "Do you want to move to Alien Visitor?", 200, 0, 17201, new int[]{}, false),
    COMMERCI_REPUBLIC(505, "Theme Dungeon:\r\n Commerci Republic", "Do you want to move to Commerci Republic?", 140, 17600, 17699, new int[]{}, false),
    MOMIJIGAOKA(506, 807000000, 4, "Momijigaoka", "Do you want to move to Momijigaoka?", 13, 0, 7844, new int[]{}, false),
    BLACKGATE_CITY(507, "Blackgate City", "Do you want to move to Blackgate City?", 100, 0, 61132, new int[]{}, false),
    AFTERLANDS(508, "Afterlands", "Do you want to move to Afterlands?", 75, 63020, 63255, new int[]{1202237, 1202238, 1202239, 1202240}, false),
    MUSHROOM_SHRINE(509, 800000000, 3, "Mushroom Shrine", "Do you want to move to Mushroom shrine where strange stories are hidden.", 10, 0, 7017, new int[]{1102887, 2047983, 2047982, 2047981, 4001832}, false),
    ABRUP_BASE_CAMP(510, "Abrup Base Camp", "The people of Abrup are waiting for you.", 33, 64010, 64154, new int[]{1005191, 2439151, 1143117, 3018143, 1143118, 2028372, 2048752}, false);

    private final String name;
    private final String desc;
    private final int id;
    private final int mapId;
    private final int portal;
    private final int reqLevel;
    private final int reqQuest;
    private final int questToSave;
    private final int[] rewards;
    private final boolean squad;

    DimensionalMirror(int id, int mapId, int portal, String name, String desc, int reqLevel, int reqQuest, int questToSave, int[] rewards, boolean squad) {
        this.id = id;
        this.mapId = mapId;
        this.portal = portal;
        this.name = name;
        this.desc = desc;
        this.reqLevel = reqLevel;
        this.reqQuest = reqQuest;
        this.questToSave = questToSave;
        this.rewards = rewards;
        this.squad = squad;
    }

    DimensionalMirror(int id, int mapId, String name, String desc, int reqLevel, int reqQuest, int questToSave, int[] rewards, boolean squad) {
        this.id = id;
        this.mapId = mapId;
        this.portal = 0;
        this.name = name;
        this.desc = desc;
        this.reqLevel = reqLevel;
        this.reqQuest = reqQuest;
        this.questToSave = questToSave;
        this.rewards = rewards;
        this.squad = squad;
    }

    DimensionalMirror(int id, String name, String desc, int reqLevel, int reqQuest, int questToSave, int[] rewards, boolean squad) {
        this.id = id;
        this.mapId = 0;
        this.portal = 0;
        this.name = name;
        this.desc = desc;
        this.reqLevel = reqLevel;
        this.reqQuest = reqQuest;
        this.questToSave = questToSave;
        this.rewards = rewards;
        this.squad = squad;
    }

    public static DimensionalMirror getByMapId(int mapId) {
        return Util.findWithPred(Arrays.asList(values()), csat -> csat.getMapId() == mapId);
    }

    public static DimensionalMirror getByID(int id) {
        return Util.findWithPred(Arrays.asList(values()), csat -> csat.getId() == id);
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getId() {
        return id;
    }

    public int getReqLevel() {
        return reqLevel;
    }

    public int getReqQuest() {
        return reqQuest;
    }

    public int[] getRewards() {
        return rewards;
    }

    public boolean isSquad() {
        return squad;
    }

    public int getQuestToSave() {
        return questToSave;
    }

    public int getMapId() {
        return mapId;
    }

    public int getPortal() {
        return portal;
    }
}
