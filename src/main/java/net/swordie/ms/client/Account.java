package net.swordie.ms.client;

import net.swordie.ms.Server;
import net.swordie.ms.client.anticheat.OffenseManager;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.MonsterCollection;
import net.swordie.ms.client.character.damage.DamageSkinSaveData;
import net.swordie.ms.client.friend.Friend;
import net.swordie.ms.client.trunk.Trunk;
import net.swordie.ms.connection.db.FileTimeConverter;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.PicStatus;
import net.swordie.ms.enums.PrivateStatusIDFlag;
import net.swordie.ms.loaders.StringData;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Util;
import org.apache.log4j.Logger;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tim on 4/30/2017.
 */
@Entity
@Table(name = "accounts")
public class Account {

    @Transient
    private static final Logger log = Logger.getLogger(Account.class);

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private PrivateStatusIDFlag privateStatusIDFlag = PrivateStatusIDFlag.NONE;
    private int age;
    private int vipGrade;
    private int nBlockReason;
    private byte gender;
    private byte msg2;
    private byte purchaseExp;
    private byte pBlockReason;
    private byte gradeCode;
    private long chatUnblockDate;
    private boolean hasCensoredNxLoginID;
    private String censoredNxLoginID;
    private String pic;
    private int characterSlots;
    @Convert(converter = FileTimeConverter.class)
    private FileTime creationDate = FileTime.currentTime();
    @JoinColumn(name = "trunkID")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Trunk trunk;
    @JoinColumn(name = "monsterCollectionID")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private MonsterCollection monsterCollection;
    // no eager -> sometimes get a "resultset closed" when fetching friends/damage skins
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "owneraccid")
    private Set<Friend> friends;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "accID")
    private Set<DamageSkinSaveData> damageSkins = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "accID")
    private Set<Char> characters = new HashSet<>();
    @Transient
    private Char currentChr;
    private int nxCredit;
    private int maplePoints;
    private int nxPrepaid;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "accID")
    private Set<LinkSkill> linkSkills = new HashSet<>();
    @Convert(converter = FileTimeConverter.class)
    private FileTime banExpireDate = FileTime.fromType(FileTime.Type.ZERO_TIME);
    private String banReason;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "offensemanager")
    private OffenseManager offenseManager;

    public Account(String name, String password, int accountId, String pic, PrivateStatusIDFlag privateStatusIDFlag, int age, int vipGrade, int nBlockReason, byte gender, byte msg2,
                   byte purchaseExp, byte pBlockReason, long chatUnblockDate, boolean hasCensoredNxLoginID,
                   byte gradeCode, String censoredNxLoginID, int characterSlots, FileTime creationDate) {
        this.name = name;
        this.password = password;
        this.id = accountId;
        this.pic = pic;
        this.privateStatusIDFlag = privateStatusIDFlag;
        this.age = age;
        this.vipGrade = vipGrade;
        this.gender = gender;
        this.msg2 = msg2;
        this.purchaseExp = purchaseExp;
        this.nBlockReason = nBlockReason;
        this.pBlockReason = pBlockReason;
        this.chatUnblockDate = chatUnblockDate;
        this.hasCensoredNxLoginID = hasCensoredNxLoginID;
        this.gradeCode = gradeCode;
        this.censoredNxLoginID = censoredNxLoginID;
        this.characterSlots = characterSlots;
        this.creationDate = creationDate;
        this.monsterCollection = new MonsterCollection();
        this.friends = new HashSet<>();
        this.trunk = new Trunk((byte) 20);
    }

    public Account(String id, int accountId) {
        this(id, null, accountId, null, PrivateStatusIDFlag.NONE, 0, 0, 0,
                (byte) 0, (byte) 0, (byte) 0, (byte) 3, 0, false, (byte) 0,
                "", 16, FileTime.currentTime());
    }

    public Account(){
    }

    public static Account getFromDBByName(String name) {
        return (Account) DatabaseManager.getObjFromDB(Account.class, name);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public PrivateStatusIDFlag getPrivateStatusIDFlag() {
        return privateStatusIDFlag;
    }

    public int getAge() {
        return age;
    }

    public int getVipGrade() {
        return vipGrade;
    }

    public byte getGender() {
        return gender;
    }

    public byte getMsg2() {
        return msg2;
    }

    public byte getPurchaseExp() {
        return purchaseExp;
    }

    public byte getpBlockReason() {
        return pBlockReason;
    }

    public long getChatUnblockDate() {
        return chatUnblockDate;
    }

    public boolean hasCensoredNxLoginID() {
        return hasCensoredNxLoginID;
    }

    public byte getGradeCode() {
        return gradeCode;
    }

    public String getCensoredNxLoginID() {
        return censoredNxLoginID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getnBlockReason() {
        return nBlockReason;
    }

    public void setPrivateStatusIDFlag(PrivateStatusIDFlag privateStatusIDFlag) {
        this.privateStatusIDFlag = privateStatusIDFlag;
    }

    public int getCharacterSlots() {
        return characterSlots;
    }

    public FileTime getCreationDate() {
        return creationDate;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static Account getFromDBById(int accountId) {
        return (Account) DatabaseManager.getObjFromDB(Account.class, accountId);
    }

    public Set<Char> getCharacters() {
        return characters;
    }

    public void addCharacter(Char character) {
       getCharacters().add(character);
    }

    public String getPic() {
        // security is overrated
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public PicStatus getPicStatus() {
        PicStatus picStatus;
        String pic = getPic();
        if(pic == null || pic.length() == 0) {
            picStatus = PicStatus.CREATE_PIC;
        } else {
            picStatus = PicStatus.ENTER_PIC;
        }
        return picStatus;
    }

    public DamageSkinSaveData getDamageSkinBySkinID(int skinID) {
        return getDamageSkins().stream().filter(d -> d.getDamageSkinID() == skinID).findAny().orElse(null);
    }

    public void setVipGrade(int vipGrade) {
        this.vipGrade = vipGrade;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public void setMsg2(byte msg2) {
        this.msg2 = msg2;
    }

    public void setnBlockReason(int nBlockReason) {
        this.nBlockReason = nBlockReason;
    }

    public void setPurchaseExp(byte purchaseExp) {
        this.purchaseExp = purchaseExp;
    }

    public void setpBlockReason(byte pBlockReason) {
        this.pBlockReason = pBlockReason;
    }

    public void setGradeCode(byte gradeCode) {
        this.gradeCode = gradeCode;
    }

    public void setChatUnblockDate(long chatUnblockDate) {
        this.chatUnblockDate = chatUnblockDate;
    }

    public void setHasCensoredNxLoginID(boolean hasCensoredNxLoginID) {
        this.hasCensoredNxLoginID = hasCensoredNxLoginID;
    }

    public void setCensoredNxLoginID(String censoredNxLoginID) {
        this.censoredNxLoginID = censoredNxLoginID;
    }

    public void setCharacterSlots(int characterSlots) {
        this.characterSlots = characterSlots;
    }

    public void setCreationDate(FileTime creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isHasCensoredNxLoginID() {
        return hasCensoredNxLoginID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Friend> getFriends() {
        return friends;
    }

    public void setFriends(Set<Friend> friends) {
        this.friends = friends;
    }

    public void addFriend(Friend friend) {
        if(getFriendByAccID(friend.getFriendAccountID()) == null) {
            getFriends().add(friend);
        }
    }

    public Friend getFriendByAccID(int accID) {
        return getFriends().stream().filter(f -> f.getFriendAccountID() == accID).findAny().orElse(null);
    }

    public void removeFriend(int accID) {
        removeFriend(getFriendByAccID(accID));
    }

    public void removeFriend(Friend f) {
        if(f != null) {
            getFriends().remove(f);
        }
    }

    public Char getCurrentChr() {
        return currentChr;
    }

    public void setCurrentChr(Char currentChr) {
        this.currentChr = currentChr;
    }

    public Set<DamageSkinSaveData> getDamageSkins() {
        return damageSkins;
    }

    public void setDamageSkins(Set<DamageSkinSaveData> damageSkins) {
        this.damageSkins = damageSkins;
    }

    public void addDamageSkin(DamageSkinSaveData dssd) {
        if(getDamageSkinByItemID(dssd.getItemID()) == null) {
            getDamageSkins().add(dssd);
        }
    }

    public void removeDamageSkin(DamageSkinSaveData dssd) {
        if(dssd != null) {
            getDamageSkins().remove(dssd);
        }
    }

    public void removeDamageSkin(int itemID) {
        removeDamageSkin(getDamageSkinByItemID(itemID));
    }

    public void addDamageSkinByItemID(int itemID) {
        addDamageSkin(new DamageSkinSaveData(ItemConstants.getDamageSkinIDByItemID(itemID), itemID, false,
                StringData.getItemStringById(itemID)));
    }

    public DamageSkinSaveData getDamageSkinByItemID(int itemID) {
        return getDamageSkins().stream().filter(d -> d.getItemID() == itemID).findAny().orElse(null);
    }

    public Trunk getTrunk() {
        if(trunk == null) {
            trunk = new Trunk((byte) 20);
        }
        return trunk;
    }

    public void setTrunk(Trunk trunk) {
        this.trunk = trunk;
    }

    public int getNxCredit() {
        return nxCredit;
    }

    public void setNxCredit(int nxCredit) {
        this.nxCredit = nxCredit;
    }

    public int getMaplePoints() {
        return maplePoints;
    }

    public void setMaplePoints(int maplePoints) {
        this.maplePoints = maplePoints;
    }

    public int getNxPrepaid() {
        return nxPrepaid;
    }

    public void setNxPrepaid(int nxPrepaid) {
        this.nxPrepaid = nxPrepaid;
    }

    public void addLinkSkill(LinkSkill linkSkill) {
        removeLinkSkillByOwnerID(linkSkill.getOwnerID());
        getLinkSkills().add(linkSkill);
    }

    public void addLinkSkill(Char originChar, int sonID, int linkedSkill) {
        int level = SkillConstants.getLinkSkillLevelByCharLevel(originChar.getLevel());
        LinkSkill ls = new LinkSkill(originChar.getId(), linkedSkill, level);
        addLinkSkill(ls);
    }

    public void removeLinkSkillByOwnerID(int ownerID) {
        getLinkSkills().stream().filter(l -> l.getOwnerID() == ownerID).findFirst()
                .ifPresent(ls -> getLinkSkills().remove(ls));
    }

    public Set<LinkSkill> getLinkSkills() {
        return linkSkills;
    }

    public void setLinkSkills(Set<LinkSkill> linkSkills) {
        this.linkSkills = linkSkills;
    }

    public void addNXCredit(int credit) {
        int newCredit = getNxCredit() + credit;
        if (newCredit >= 0) {
            setNxCredit(newCredit);
        }
    }

    public void deductNXCredit(int credit) {
        addNXCredit(-credit);
    }

    public void addMaplePoints(int points) {
        int newPoints = getMaplePoints() + points;
        if (newPoints >= 0) {
            setMaplePoints(newPoints);
        }
    }

    public void deductMaplePoints(int points) {
        addMaplePoints(-points);
    }

    public void addNXPrepaid(int prepaid) {
        int newPrepaid = getNxPrepaid() + prepaid;
        if (newPrepaid >= 0) {
            addNXPrepaid(newPrepaid);
        }
    }

    public void deductNXPrepaid(int prepaid) {
        addNXPrepaid(-prepaid);
    }

    public MonsterCollection getMonsterCollection() {
        if (monsterCollection == null) {
            monsterCollection = new MonsterCollection();
        }
        return monsterCollection;
    }

    public void setMonsterCollection(MonsterCollection monsterCollection) {
        this.monsterCollection = monsterCollection;
    }

    public FileTime getBanExpireDate() {
        return banExpireDate;
    }

    public void setBanExpireDate(FileTime banExpireDate) {
        this.banExpireDate = banExpireDate;
    }

    public String getBanReason() {
        return banReason;
    }

    public void setBanReason(String banReason) {
        this.banReason = banReason;
    }

    public boolean hasCharacter(int charID) {
        // doing a .contains on getCharacters() does not work, even if the hashcode is just a hash of the id
        return getCharById(charID) != null;
    }

    public Char getCharById(int id) {
        return Util.findWithPred(getCharacters(), chr -> chr.getId() == id);
    }

    public Char getCharByName(String name) {
        return Util.findWithPred(getCharacters(), chr -> chr.getName().equals(name));
    }

    public void unstuck() {
        Server.getInstance().removeAccount(this);
        DatabaseManager.saveToDB(this);
    }

    public OffenseManager getOffenseManager() {
        if (offenseManager == null) {
            setOffenseManager(new OffenseManager());
        }
        return offenseManager;
    }

    public void setOffenseManager(OffenseManager offenseManager) {
        this.offenseManager = offenseManager;
    }

    public boolean isManagerAccount() {
        return privateStatusIDFlag.hasFlag(PrivateStatusIDFlag.MANAGER_ACCOUNT);
    }
}
