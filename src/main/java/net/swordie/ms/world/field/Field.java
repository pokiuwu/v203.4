package net.swordie.ms.world.field;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.runestones.RuneStone;
import net.swordie.ms.client.character.skills.TownPortal;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.adventurer.Archer;
import net.swordie.ms.client.jobs.resistance.OpenGate;
import net.swordie.ms.client.party.Party;
import net.swordie.ms.client.party.PartyMember;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.*;
import net.swordie.ms.life.drop.Drop;
import net.swordie.ms.life.drop.DropInfo;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.npc.Npc;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.containerclasses.ItemInfo;
import net.swordie.ms.loaders.MobData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.scripts.ScriptManager;
import net.swordie.ms.scripts.ScriptManagerImpl;
import net.swordie.ms.scripts.ScriptType;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static net.swordie.ms.client.character.skills.SkillStat.time;

/**
 * Created on 12/14/2017.
 */
public class Field {
    private static final Logger log = Logger.getLogger(Field.class);
    private Rect rect;
    private int vrTop, vrLeft, vrBottom, vrRight;
    private double mobRate;
    private int id;
    private FieldType fieldType;
    private long fieldLimit;
    private int returnMap, forcedReturn, createMobInterval, timeOut, timeLimit, lvLimit, lvForceMove;
    private int consumeItemCoolTime, link;
    private boolean town, swim, fly, reactorShuffle, expeditionOnly, partyOnly, needSkillForFly;
    private Set<Portal> portals;
    private Set<Foothold> footholds;
    private Map<Integer, Life> lifes;
    private List<Char> chars;
    private Map<Life, Char> lifeToControllers;
    private Map<Life, ScheduledFuture> lifeSchedules;
    private String onFirstUserEnter = "", onUserEnter = "";
    private int fixedMobCapacity;
    private int objectIDCounter = 1000000;
    private boolean userFirstEnter = false;
    private String fieldScript = "";
    private ScriptManagerImpl scriptManagerImpl = new ScriptManagerImpl(this);
    private RuneStone runeStone;
    private ScheduledFuture runeStoneHordesTimer;
    private int burningFieldLevel;
    private long nextEliteSpawnTime = System.currentTimeMillis();
    private int killedElites;
    private EliteState eliteState;
    private int bossMobID;
    private boolean kishin;
    private List<OpenGate> openGateList = new ArrayList<>();
    private List<TownPortal> townPortalList = new ArrayList<>();
    private boolean isChannelField;
    private Map<Integer, List<String>> directionInfo;
    private Clock clock;
    private int channel;

    public Field(int fieldID) {
        this.id = fieldID;
        this.rect = new Rect();
        this.portals = new HashSet<>();
        this.footholds = new HashSet<>();
        this.lifes = new ConcurrentHashMap<>();
        this.chars = new CopyOnWriteArrayList<>();
        this.lifeToControllers = new HashMap<>();
        this.lifeSchedules = new HashMap<>();
        this.directionInfo = new HashMap<>();
        this.fixedMobCapacity = GameConstants.DEFAULT_FIELD_MOB_CAPACITY; // default
    }

    public void startFieldScript() {
        String script = getFieldScript();
        if(!"".equalsIgnoreCase(script)) {
            log.debug(String.format("Starting field script %s.", script));
            scriptManagerImpl.startScript(getId(), script, ScriptType.Field);
        }
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public int getVrTop() {
        return vrTop;
    }

    public void setVrTop(int vrTop) {
        this.vrTop = vrTop;
    }

    public int getVrLeft() {
        return vrLeft;
    }

    public void setVrLeft(int vrLeft) {
        this.vrLeft = vrLeft;
    }

    public int getVrBottom() {
        return vrBottom;
    }

    public void setVrBottom(int vrBottom) {
        this.vrBottom = vrBottom;
    }

    public int getVrRight() {
        return vrRight;
    }

    public void setVrRight(int vrRight) {
        this.vrRight = vrRight;
    }

    public int getHeight() {return getVrTop() - getVrBottom();}

    public int getWidth() {return getVrRight() - getVrLeft();}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FieldType getFieldType() { return fieldType; }

    public void setFieldType(FieldType fieldType) { this.fieldType = fieldType; }

    public long getFieldLimit() { return fieldLimit; }

    public void setFieldLimit(long fieldLimit) { this.fieldLimit = fieldLimit; }
    
    public Set<Portal> getPortals() {
        return portals;
    }

    public void setPortals(Set<Portal> portals) {
        this.portals = portals;
    }

    public void addPortal(Portal portal) {
        getPortals().add(portal);
    }

    public int getReturnMap() {
        return returnMap;
    }

    public void setReturnMap(int returnMap) {
        this.returnMap = returnMap;
    }

    public int getForcedReturn() {
        return forcedReturn;
    }

    public void setForcedReturn(int forcedReturn) {
        this.forcedReturn = forcedReturn;
    }

    public double getMobRate() {
        return mobRate;
    }

    public void setMobRate(double mobRate) {
        this.mobRate = mobRate;
    }

    public int getCreateMobInterval() {
        return createMobInterval;
    }

    public void setCreateMobInterval(int createMobInterval) {
        this.createMobInterval = createMobInterval;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getLvLimit() {
        return lvLimit;
    }

    public void setLvLimit(int lvLimit) {
        this.lvLimit = lvLimit;
    }

    public int getLvForceMove() {
        return lvForceMove;
    }

    public void setLvForceMove(int lvForceMove) {
        this.lvForceMove = lvForceMove;
    }

    public int getConsumeItemCoolTime() {
        return consumeItemCoolTime;
    }

    public void setConsumeItemCoolTime(int consumeItemCoolTime) {
        this.consumeItemCoolTime = consumeItemCoolTime;
    }

    public int getLink() {
        return link;
    }

    public void setLink(int link) {
        this.link = link;
    }

    public boolean isTown() {
        return town;
    }

    public void setTown(boolean town) {
        this.town = town;
    }

    public boolean isSwim() {
        return swim;
    }

    public void setSwim(boolean swim) {
        this.swim = swim;
    }

    public boolean isFly() {
        return fly;
    }

    public void setFly(boolean fly) {
        this.fly = fly;
    }

    public boolean isReactorShuffle() {
        return reactorShuffle;
    }

    public void setReactorShuffle(boolean reactorShuffle) {
        this.reactorShuffle = reactorShuffle;
    }

    public boolean isExpeditionOnly() {
        return expeditionOnly;
    }

    public void setExpeditionOnly(boolean expeditionONly) {
        this.expeditionOnly = expeditionONly;
    }

    public boolean isPartyOnly() {
        return partyOnly;
    }

    public void setPartyOnly(boolean partyOnly) {
        this.partyOnly = partyOnly;
    }

    public boolean isNeedSkillForFly() {
        return needSkillForFly;
    }

    public void setNeedSkillForFly(boolean needSkillForFly) {
        this.needSkillForFly = needSkillForFly;
    }

    public String getOnFirstUserEnter() {
        return onFirstUserEnter;
    }

    public void setOnFirstUserEnter(String onFirstUserEnter) {
        this.onFirstUserEnter = onFirstUserEnter;
    }

    public String getOnUserEnter() {
        return onUserEnter;
    }

    public void setOnUserEnter(String onUserEnter) {
        this.onUserEnter = onUserEnter;
    }

    public Portal getPortalByName(String name) {
        return Util.findWithPred(getPortals(), portal -> portal.getName().equals(name));
    }

    public Portal getPortalByID(int id) {
        return Util.findWithPred(getPortals(), portal -> portal.getId() == id);
    }

    public RuneStone getRuneStone() {
        return runeStone;
    }

    public void setRuneStone(RuneStone runeStone) {
        this.runeStone = runeStone;
    }

    public int getBurningFieldLevel() {
        return burningFieldLevel;
    }

    public void setBurningFieldLevel(int burningFieldLevel) {
        this.burningFieldLevel = burningFieldLevel;
    }

    public Foothold findFootHoldBelow(Position pos) {
        Set<Foothold> footholds = getFootholds().stream().filter(fh -> fh.getX1() <= pos.getX() && fh.getX2() >= pos.getX()).collect(Collectors.toSet());
        Foothold res = null;
        int lastY = Integer.MAX_VALUE;
        for (Foothold fh : footholds) {
            int y = fh.getYFromX(pos.getX());
            if (res == null && y >= pos.getY()) {
                res = fh;
                lastY = y;
            } else {
                if (y < lastY && y >= pos.getY()) {
                    res = fh;
                    lastY = y;
                }
            }
        }
        return res;
    }

    public Set<Foothold> getFootholds() {
        return footholds;
    }

    public void setFootholds(Set<Foothold> footholds) {
        this.footholds = footholds;
    }

    public void addFoothold(Foothold foothold) {
        getFootholds().add(foothold);
    }

    public void setFixedMobCapacity(int fixedMobCapacity) {
        this.fixedMobCapacity = fixedMobCapacity;
    }

    public int getFixedMobCapacity() {
        return fixedMobCapacity;
    }

    public Map<Integer, Life> getLifes() {
        return lifes;
    }

    public void addLife(Life life) {
        if (life.getObjectId() < 0) {
            life.setObjectId(getNewObjectID());
        }
        if (!getLifes().values().contains(life)) {
            getLifes().put(life.getObjectId(), life);
            life.setField(this);
            if (life instanceof Mob) {
                if (getScriptManager() != null) {
                    life.addObserver(getScriptManager());
                }
                for (Char chr : getChars()) {
                    life.addObserver(chr.getScriptManager());
                }
            }
        }
    }

    public void removeLife(int id) {
        Life life = getLifeByObjectID(id);
        if (life == null) {
            return;
        }
        getLifes().remove(life.getObjectId());
    }

    public void spawnSummon(Summon summon) {
        Summon oldSummon = (Summon) getLifes().values().stream()
                .filter(s -> s instanceof Summon &&
                        ((Summon) s).getChr() == summon.getChr() &&
                        ((Summon) s).getSkillID() == summon.getSkillID())
                .findFirst().orElse(null);
        if (oldSummon != null) {
            removeLife(oldSummon.getObjectId(), false);
        }
        spawnLife(summon, null);
    }

    public void spawnAddSummon(Summon summon) { //Test
        spawnLife(summon, null);
    }

    public void removeSummon(int skillID, int chrID) {
        Summon summon = (Summon) getLifes().values().stream()
                .filter(s -> s instanceof Summon &&
                        ((Summon) s).getChr().getId() == chrID &&
                        ((Summon) s).getSkillID() == skillID)
                .findFirst().orElse(null);
        if (summon != null) {
            removeLife(summon.getObjectId(), false);
        }
    }

    public void spawnLife(Life life, Char onlyChar) {
        addLife(life);
        if (getChars().size() > 0) {
            Char controller = null;
            if (getLifeToControllers().containsKey(life)) {
                controller = getLifeToControllers().get(life);
            }
            if (controller == null) {
                setRandomController(life);
            }
            life.broadcastSpawnPacket(onlyChar);
        }
    }

    private void setRandomController(Life life) {
        // No chars -> set controller to null, so a controller will be assigned next time someone enters this field
        Char controller = null;
        if (getChars().size() > 0) {
            controller = Util.getRandomFromCollection(getChars());
            life.notifyControllerChange(controller);
        }
        putLifeController(life, controller);
    }

    public void removeLife(Life life) {
        removeLife(life.getObjectId(), false);
    }

    public Foothold getFootholdById(int fh) {
        return getFootholds().stream().filter(f -> f.getId() == fh).findFirst().orElse(null);
    }

    public List<Char> getChars() {
        return chars;
    }

    public Char getCharByID(int id) {
        return getChars().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public void addChar(Char chr) {
        if (!getChars().contains(chr)) {
            getChars().add(chr);
            if (!isUserFirstEnter()) {
                if (hasUserFirstEnterScript()) {
                    chr.chatMessage("First enter script!");
                    chr.getScriptManager().startScript(getId(), getOnFirstUserEnter(), ScriptType.FirstEnterField);
                    setUserFirstEnter(true);
                } else if (CustomFUEFieldScripts.getByVal(getId()) != null) {
                    chr.chatMessage("Custom First enter script!");
                    String feFieldScript = CustomFUEFieldScripts.getByVal(getId()).toString();
                    chr.getScriptManager().startScript(getId(), feFieldScript, ScriptType.FirstEnterField);
                    setUserFirstEnter(true);
                }
            }
        }
        broadcastPacket(UserPool.userEnterField(chr), chr);
    }

    private boolean hasUserFirstEnterScript() {
        return getOnFirstUserEnter() != null && !getOnFirstUserEnter().equalsIgnoreCase("");
    }

    public void broadcastPacket(OutPacket outPacket, Char exceptChr) {
        getChars().stream().filter(chr -> !chr.equals(exceptChr)).forEach(
                chr -> chr.write(outPacket)
        );
    }

    public void removeChar(Char chr) {
        getChars().remove(chr);
        broadcastPacket(UserPool.userLeaveField(chr), chr);
        // change controllers for which the chr was the controller of
        for (Map.Entry<Life, Char> entry : getLifeToControllers().entrySet()) {
            if (entry.getValue() != null && entry.getValue().equals(chr)) {
                setRandomController(entry.getKey());
            }
        }
        // remove summons of that char & remove field attacks of that char
        List<Integer> removedList = new ArrayList<>();
        for (Life life : getLifes().values()) {
            if (life instanceof Summon && ((Summon) life).getChr() == chr) {
                removedList.add(life.getObjectId());
            } else if (life instanceof FieldAttackObj) {
                FieldAttackObj fao = (FieldAttackObj) life;
                if (fao.getOwnerID() == chr.getId() && fao.getTemplateId() == Archer.ARROW_PLATTER) {
                    removedList.add(life.getObjectId());
                }
            }
        }
        for (int id : removedList) {
            removeLife(id, false);
        }
    }

    public Map<Life, Char> getLifeToControllers() {
        return lifeToControllers;
    }

    public void setLifeToControllers(Map<Life, Char> lifeToControllers) {
        this.lifeToControllers = lifeToControllers;
    }

    public void putLifeController(Life life, Char chr) {
        getLifeToControllers().put(life, chr);
    }

    public Life getLifeByObjectID(int objectId) {
        return getLifes().getOrDefault(objectId, null);
    }

    public Life getLifeByTemplateId(int templateId) {
        return getLifes().values().stream().filter(l -> l.getTemplateId() == templateId).findFirst().orElse(null);
    }

    public void spawnLifesForChar(Char chr) {
        for (Life life : getLifes().values()) {
            spawnLife(life, chr);
        }
        if (getRuneStone() != null && getMobs().size() > 0 && getBossMobID() == 0 && isChannelField() && !isTown()) {
            chr.write(CField.runeStoneAppear(getRuneStone()));
        }
        if (getOpenGates() != null && getOpenGates().size() > 0) {
            for (OpenGate openGate : getOpenGates()) {
                openGate.showOpenGate(this);
            }
        }
        if (getTownPortalList() != null && getTownPortalList().size() > 0) {
            for (TownPortal townPortal : getTownPortalList()) {
                townPortal.showTownPortal(this);
            }
        }
        if (getClock() != null) {
            getClock().showClock(chr);
        }
        for (Char c : getChars()) {
            if (!c.equals(chr)) {
                chr.write(UserPool.userEnterField(c));
                Dragon dragon = c.getDragon();
                if (dragon != null) {
                    chr.write(CField.createDragon(dragon));
                }
            }
        }
    }

    @Override
    public String toString() {
        return "" + getId();
    }

    public void respawn(Mob mob) {
        mob.setHp(mob.getMaxHp());
        mob.setMp(mob.getMaxMp());
        mob.setPosition(mob.getHomePosition().deepCopy());
        spawnLife(mob, null);
    }

    public void broadcastPacket(OutPacket outPacket) {
        for (Char c : getChars()) {
            c.getClient().write(outPacket);
        }
    }

    private void broadcastPacket(OutPacket outPacket, Predicate<? super Char> predicate) {
        getChars().stream().filter(predicate).forEach(chr -> chr.write(outPacket));
    }

    public void spawnAffectedArea(AffectedArea aa) {
        addLife(aa);
        SkillInfo si = SkillData.getSkillInfoById(aa.getSkillID());
        if (si != null) {
            int duration = aa.getDuration() == 0 ? si.getValue(time, aa.getSlv()) * 1000 : aa.getDuration();
            if (duration > 0) {
                ScheduledFuture sf = EventManager.addEvent(() -> removeLife(aa.getObjectId(), true), duration);
                addLifeSchedule(aa, sf);
            }
        }
        broadcastPacket(CField.affectedAreaCreated(aa));
        getChars().forEach(chr -> aa.getField().checkCharInAffectedAreas(chr));
        getMobs().forEach(mob -> aa.getField().checkMobInAffectedAreas(mob));
    }

    public void spawnAffectedAreaAndRemoveOld(AffectedArea aa) {
        AffectedArea oldAA = (AffectedArea) getLifes().values().stream()
                .filter(l -> l instanceof AffectedArea &&
                        ((AffectedArea) l).getCharID() == aa.getCharID() &&
                        ((AffectedArea) l).getSkillID() == aa.getSkillID())
                .findFirst().orElse(null);
        if (oldAA != null) {
            removeLife(oldAA.getObjectId(), false);
        }
        spawnAffectedArea(aa);
    }

    private <T> Set<T> getLifesByClass(Class clazz) {
        return (Set<T>) getLifes().values().stream()
                .filter(l -> l.getClass().equals(clazz))
                .collect(Collectors.toSet());
    }

    public Set<Mob> getMobs() {
        return getLifesByClass(Mob.class);
    }

    public Set<Summon> getSummons() {
        return getLifesByClass(Summon.class);
    }

    public Set<Drop> getDrops() {
        return getLifesByClass(Drop.class);
    }

    public Set<MobGen> getMobGens() {
        return getLifesByClass(MobGen.class);
    }

    public Set<AffectedArea> getAffectedAreas() {
        return getLifesByClass(AffectedArea.class);
    }

    public Set<Reactor> getReactors() {
        return getLifesByClass(Reactor.class);
    }

    public Set<Npc> getNpcs() {
        return getLifesByClass(Npc.class);
    }

    public Set<FieldAttackObj> getFieldAttackObjects() {
        return getLifesByClass(FieldAttackObj.class);
    }

    public void setObjectIDCounter(int idCounter) {
        objectIDCounter = idCounter;
    }

    public int getNewObjectID() {
        return objectIDCounter++;
    }

    public List<Life> getLifesInRect(Rect rect) {
        List<Life> lifes = new ArrayList<>();
        for (Life life : getLifes().values()) {
            Position position = life.getPosition();
            int x = position.getX();
            int y = position.getY();
            if (x >= rect.getLeft() && y >= rect.getTop()
                    && x <= rect.getRight() && y <= rect.getBottom()) {
                lifes.add(life);
            }
        }
        return lifes;
    }

    public List<Char> getCharsInRect(Rect rect) {
        List<Char> chars = new ArrayList<>();
        for (Char chr : getChars()) {
            Position position = chr.getPosition();
            int x = position.getX();
            int y = position.getY();
            if (x >= rect.getLeft() && y >= rect.getTop()
                    && x <= rect.getRight() && y <= rect.getBottom()) {
                chars.add(chr);
            }
        }
        return chars;
    }

    public List<PartyMember> getPartyMembersInRect(Char chr, Rect rect) {
        Party party = chr.getParty();
        List<PartyMember> partyMembers = new ArrayList<>();
        for (PartyMember partyMember : party.getOnlineMembers()) {
            Position position = partyMember.getChr().getPosition();
            int x = position.getX();
            int y = position.getY();
            if (x >= rect.getLeft() && y >= rect.getTop()
                    && x <= rect.getRight() && y <= rect.getBottom()) {
                partyMembers.add(partyMember);
            }
        }
        return partyMembers;
    }

    public List<Mob> getMobsInRect(Rect rect) {
        List<Mob> mobs = new ArrayList<>();
        for (Mob mob : getMobs()) {
            Position position = mob.getPosition();
            int x = position.getX();
            int y = position.getY();
            if (x >= rect.getLeft() && y >= rect.getTop()
                    && x <= rect.getRight() && y <= rect.getBottom()) {
                mobs.add(mob);
            }
        }
        return mobs;
    }

    public List<Mob> getBossMobsInRect(Rect rect) {
        List<Mob> mobs = new ArrayList<>();
        for (Mob mob : getMobs()) {
            if(mob.isBoss()) {
                Position position = mob.getPosition();
                int x = position.getX();
                int y = position.getY();
                if (x >= rect.getLeft() && y >= rect.getTop()
                        && x <= rect.getRight() && y <= rect.getBottom()) {
                    mobs.add(mob);
                }
            }
        }
        return mobs;
    }

    public List<Drop> getDropsInRect(Rect rect) {
        List<Drop> drops = new ArrayList<>();
        for (Drop drop : getDrops()) {
            Position position = drop.getPosition();
            int x = position.getX();
            int y = position.getY();
            if (x >= rect.getLeft() && y >= rect.getTop()
                    && x <= rect.getRight() && y <= rect.getBottom()) {
                drops.add(drop);
            }
        }
        return drops;
    }

    public synchronized void removeLife(int id, boolean fromSchedule) {
        Life life = getLifeByObjectID(id);
        if (life == null) {
            return;
        }
        removeLife(id);
        removeSchedule(life, fromSchedule);
        life.broadcastLeavePacket();
    }

    public synchronized void removeDrop(int dropID, int pickupUserID, boolean fromSchedule, int petID) {
        Life life = getLifeByObjectID(dropID);
        if (life instanceof Drop) {
            if (petID >= 0) {
                broadcastPacket(DropPool.dropLeaveField(DropLeaveType.PetPickup, pickupUserID, life.getObjectId(),
                        (short) 0, petID, 0));
            } else if (pickupUserID != 0) {
                broadcastPacket(DropPool.dropLeaveField(dropID, pickupUserID));
            } else {
                broadcastPacket(DropPool.dropLeaveField(DropLeaveType.Fade, pickupUserID, life.getObjectId(),
                        (short) 0, 0, 0));
            }
            removeLife(dropID, fromSchedule);
        }
    }

    public Map<Life, ScheduledFuture> getLifeSchedules() {
        return lifeSchedules;
    }

    public void addLifeSchedule(Life life, ScheduledFuture scheduledFuture) {
        getLifeSchedules().put(life, scheduledFuture);
    }

    public void removeSchedule(Life life, boolean fromSchedule) {
        if (!getLifeSchedules().containsKey(life)) {
            return;
        }
        if (!fromSchedule) {
            getLifeSchedules().get(life).cancel(false);
        }
        getLifeSchedules().remove(life);
    }

    public void checkMobInAffectedAreas(Mob mob) {
        for (AffectedArea aa : getAffectedAreas()) {
            if (aa.getRect().hasPositionInside(mob.getPosition())) {
                aa.handleMobInside(mob);
            }
        }
    }

    public void checkCharInAffectedAreas(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        for (AffectedArea aa : getAffectedAreas()) {
            boolean isInsideAA = aa.getRect().hasPositionInside(chr.getPosition());
            if (isInsideAA) {
                aa.handleCharInside(chr);
            } else if (tsm.hasAffectedArea(aa) && !isInsideAA) {
                tsm.removeAffectedArea(aa);
            }
        }
    }
    public void drop(Drop drop, Position posFrom, Position posTo) {
        drop(drop, posFrom, posTo, false);
    }
    
    /**
     * Drops an item to this map, given a {@link Drop}, a starting Position and an ending Position.
     * Immediately broadcasts the drop packet.
     *
     * @param drop    The Drop to drop.
     * @param posFrom The Position that the drop starts off from.
     * @param posTo   The Position where the drop lands.
     * @param ignoreTradability if the drop should ignore tradability (i.e., untradable items won't disappear)
     */
    public void drop(Drop drop, Position posFrom, Position posTo, boolean ignoreTradability) {
        boolean isTradable = true;
        Item item = drop.getItem();
        if (item != null) {
            ItemInfo itemInfo = ItemData.getItemInfoByID(item.getItemId());
            // must be tradable, and if not an equip, not a quest item
            isTradable = ignoreTradability ||
                    (item.isTradable() && (ItemConstants.isEquip(item.getItemId()) || itemInfo != null
                    && !itemInfo.isQuest()));
        }
        drop.setPosition(posTo);
        if (isTradable) {
            addLife(drop);
            getLifeSchedules().put(drop,
                    EventManager.addEvent(() -> removeDrop(drop.getObjectId(), 0, true, -1),
                            GameConstants.DROP_REMAIN_ON_GROUND_TIME, TimeUnit.SECONDS));
        } else {
            drop.setObjectId(getNewObjectID()); // just so the client sees the drop
        }
        // Check for collision items such as exp orbs from combo kills
        if (!isTradable) {
            broadcastPacket(DropPool.dropEnterField(drop, posFrom, 0, DropEnterType.FadeAway));
        } else if(drop.getItem() != null && ItemConstants.isCollisionLootItem(drop.getItem().getItemId())) {
            broadcastPacket(DropPool.dropEnterFieldCollisionPickUp(drop, posFrom, 0));
        } else {
            for (Char chr : getChars()) {
                if (!chr.getClient().getWorld().isReboot() || drop.canBePickedUpBy(chr)) {
                    broadcastPacket(DropPool.dropEnterField(drop, posFrom, posTo, 0, drop.canBePickedUpBy(chr)));
                }
            }
        }

    }

    /**
     * Drops a {@link Drop} according to a given {@link DropInfo DropInfo}'s specification.
     *
     * @param dropInfo The
     * @param posFrom  The Position that hte drop starts off from.
     * @param posTo    The Position where the drop lands.
     * @param ownerID  The owner's character ID. Will not be able to be picked up by Chars that are not the owner.
     */
    public void drop(DropInfo dropInfo, Position posFrom, Position posTo, int ownerID) {
        int itemID = dropInfo.getItemID();
        Item item;
        Drop drop = new Drop(-1);
        drop.setPosition(posTo);
        drop.setOwnerID(ownerID);
        Set<Integer> quests = new HashSet<>();
        if (itemID != 0) {
            item = ItemData.getItemDeepCopy(itemID, true);
            if (item != null) {
                item.setQuantity(dropInfo.getQuantity());
                drop.setItem(item);
                ItemInfo ii = ItemData.getItemInfoByID(itemID);
                if (ii != null && ii.isQuest()) {
                    quests = ii.getQuestIDs();
                }
            } else {
                log.error("Was not able to find the item to drop! id = " + itemID);
                return;
            }
        } else {
            drop.setMoney(dropInfo.getMoney());
        }
        addLife(drop);
        drop.setExpireTime(FileTime.fromDate(LocalDateTime.now().plusSeconds(GameConstants.DROP_REMOVE_OWNERSHIP_TIME)));
        getLifeSchedules().put(drop,
                EventManager.addEvent(() -> removeDrop(drop.getObjectId(), 0, true, -1),
                        GameConstants.DROP_REMAIN_ON_GROUND_TIME, TimeUnit.SECONDS));
        EventManager.addEvent(() -> drop.setOwnerID(0), GameConstants.DROP_REMOVE_OWNERSHIP_TIME, TimeUnit.SECONDS);
        for (Char chr : getChars()) {
            if (chr.hasAnyQuestsInProgress(quests)) {
                broadcastPacket(DropPool.dropEnterField(drop, posFrom, posTo, ownerID, drop.canBePickedUpBy(chr)));
            }
        }
    }

    /**
     * Drops a Set of {@link DropInfo}s from a base Position.
     *
     * @param dropInfos The Set of DropInfos.
     * @param position  The Position the initial Drop comes from.
     * @param ownerID   The owner's character ID.
     */
    public void drop(Set<DropInfo> dropInfos, Position position, int ownerID, boolean isElite) {
        drop(dropInfos, findFootHoldBelow(position), position, ownerID, 0, 0, isElite);
    }

    public void drop(Drop drop, Position position) {
        drop(drop, position, false);
    }
    
    /**
     * Drops a {@link Drop} at a given Position. Calculates the Position that the Drop should land at.
     *
     * @param drop     The Drop that should be dropped.
     * @param position The Position it is dropped from.
     * @param fromReactor if it quest item the item will disapear
     */
    public void drop(Drop drop, Position position, boolean fromReactor) {
        int x = position.getX();
        Position posTo = new Position(x, findFootHoldBelow(position).getYFromX(x));
        drop(drop, position, posTo, fromReactor);
    }

    /**
     * Drops a Set of {@link DropInfo}s, locked to a specific {@link Foothold}.
     * Not all drops are guaranteed to be dropped, as this method calculates whether or not a Drop should drop, according
     * to the DropInfo's prop chance.
     *
     * @param dropInfos The Set of DropInfos that should be dropped.
     * @param fh        The Foothold this Set of DropInfos is bound to.
     * @param position  The Position the Drops originate from.
     * @param ownerID   The ID of the owner of all drops.
     * @param mesoRate  The added meso rate of the character.
     * @param dropRate  The added drop rate of the character.
     */
    public void drop(Set<DropInfo> dropInfos, Foothold fh, Position position, int ownerID, int mesoRate, int dropRate, boolean isElite) {
        int x = position.getX();
        int minX = fh == null ? position.getX() : fh.getX1();
        int maxX = fh == null ? position.getX() : fh.getX2();
        int diff = 0;
        for (DropInfo dropInfo : dropInfos) {
            if (dropInfo.willDrop(dropRate)) {
                if (!isElite && dropInfo.getItemID() / 10000 == 271) continue;
                x = (x + diff) > maxX ? maxX - 10 : (x + diff) < minX ? minX + 10 : x + diff;
                Position posTo;
                if (fh == null) {
                    posTo = position.deepCopy();
                } else {
                    posTo = new Position(x, fh.getYFromX(x));
                }
                // Copy the drop info for money, as we chance the amount that's in there.
                // Not copying -> original dropinfo will keep increasing in mesos
                DropInfo copy = null;
                if (dropInfo.isMoney()) {
                    copy = dropInfo.deepCopy();
                    copy.setMoney((int) (dropInfo.getMoney() * ((100 + mesoRate) / 100D)));
                }
                drop(copy != null ? copy : dropInfo, position, posTo, ownerID);
                diff = diff < 0 ? Math.abs(diff - GameConstants.DROP_DIFF) : -(diff + GameConstants.DROP_DIFF);
                dropInfo.generateNextDrop();
            }
        }
    }

    public List<Portal> getClosestPortal(Rect rect) {
        List<Portal> portals = new ArrayList<>();
        for (Portal portals2 : getPortals()) {
            int x = portals2.getX();
            int y = portals2.getY();
            if (x >= rect.getLeft() && y >= rect.getTop()
                    && x <= rect.getRight() && y <= rect.getBottom()) {
                portals.add(portals2);
            }
        }
        return portals;
    }

    public Char getCharByName(String name) {
        return getChars().stream().filter(chr -> chr.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public void execUserEnterScript(Char chr) {
        chr.clearCurrentDirectionNode();
        if(getOnUserEnter() == null) {
            return;
        }
        if (!getOnUserEnter().equalsIgnoreCase("")) {
            String script = getOnUserEnter();
            chr.getScriptManager().startScript(getId(), script, ScriptType.Field);
        } else if (CustomFieldScripts.getByVal(getId()) != null) {
            String customFieldScriptName = CustomFieldScripts.getByVal(getId()).toString();
            chr.chatMessage("Custom Field Script:");
            chr.getScriptManager().startScript(getId(), customFieldScriptName, ScriptType.Field);
        }
    }

    public boolean isUserFirstEnter() {
        return userFirstEnter;
    }

    public void setUserFirstEnter(boolean userFirstEnter) {
        this.userFirstEnter = userFirstEnter;
    }

    public int getAliveMobCount() {
        // not using getMobs() to only have to iterate `lifes' once
        return getLifes().values().stream()
                .filter(life -> life instanceof Mob && ((Mob) life).isAlive())
                .collect(Collectors.toList())
                .size();
    }

    public int getAliveMobCount(int mobID) {
        // not using getMobs() to only have to iterate `lifes' once
        return getLifes().values().stream()
                .filter(life -> life instanceof Mob && life.getTemplateId() == mobID && ((Mob) life).isAlive())
                .collect(Collectors.toList())
                .size();
    }

    public String getFieldScript() {
        return fieldScript;
    }

    public void setFieldScript(String fieldScript) {
        this.fieldScript = fieldScript;
    }

    public Mob spawnMobWithAppearType(int id, int x, int y, int appearType, int option) {
        Mob mob = MobData.getMobDeepCopyById(id);
        Position pos = new Position(x, y);
        mob.setPosition(pos.deepCopy());
        mob.setPrevPos(pos.deepCopy());
        mob.setPosition(pos.deepCopy());
        mob.setNotRespawnable(true);
        mob.setAppearType((byte) appearType);
        mob.setOption(option);
        if (mob.getField() == null) {
            mob.setField(this);
        }
        spawnLife(mob, null);
        return mob;
    }
    
    public Mob spawnMob(int id, int x, int y, boolean respawnable, long hp) {
        Mob mob = MobData.getMobDeepCopyById(id);
        Position pos = new Position(x, y);
        mob.setPosition(pos.deepCopy());
        mob.setPrevPos(pos.deepCopy());
        mob.setPosition(pos.deepCopy());
        mob.setNotRespawnable(!respawnable);
        if (hp > 0) {
            mob.setHp(hp);
            mob.setMaxHp(hp);
        }
        if (mob.getField() == null) {
            mob.setField(this);
        }
        spawnLife(mob, null);
        return mob;
    }

    public void spawnRuneStone() {
        if(getMobs().size() <= 0 || getBossMobID() != 0 || !isChannelField()) {
            return;
        }
        if(getRuneStone() == null) {
            RuneStone runeStone = new RuneStone().getRandomRuneStone(this);
            setRuneStone(runeStone);
            broadcastPacket(CField.runeStoneAppear(runeStone));
        }
    }

    public void useRuneStone(Client c, RuneStone runeStone) {
        broadcastPacket(CField.completeRune(c.getChr()));
        broadcastPacket(CField.runeStoneDisappear());
        c.write(CField.runeStoneSkillAck(runeStone.getRuneType()));

        setRuneStone(null);

        EventManager.addEvent(this::spawnRuneStone, GameConstants.RUNE_RESPAWN_TIME, TimeUnit.MINUTES);
    }

    public void runeStoneHordeEffect(int mobRateMultiplier, int duration) {
        double prevMobRate = getMobRate();
        setMobRate(getMobRate() * mobRateMultiplier); //Temporary increase in mob Spawn
        if(runeStoneHordesTimer != null && !runeStoneHordesTimer.isDone()) {
            runeStoneHordesTimer.cancel(true);
        }
        runeStoneHordesTimer = EventManager.addEvent(() -> setMobRate(prevMobRate), duration, TimeUnit.SECONDS);
    }

    public int getBonusExpByBurningFieldLevel() {
        return burningFieldLevel * GameConstants.BURNING_FIELD_BONUS_EXP_MULTIPLIER_PER_LEVEL; //Burning Field Level * The GameConstant
    }

    public void showBurningLevel() {
        String string = "#fn ExtraBold##fs26#          Burning Field has been destroyed.          ";
        if(getBurningFieldLevel() > 0) {
            string = "#fn ExtraBold##fs26#          Burning Stage " + getBurningFieldLevel() + ": " + getBonusExpByBurningFieldLevel() + "% Bonus EXP!          ";
        }
        Effect effect = Effect.createFieldTextEffect(string, 50, 2000, 4,
                new Position(0, -200), 1, 4 , TextEffectType.BurningField, 0, 0);
        broadcastPacket(User.effect(effect));
    }

    public void increaseBurningLevel() {
        setBurningFieldLevel(getBurningFieldLevel() + 1);
    }

    public void decreaseBurningLevel() {
        setBurningFieldLevel(getBurningFieldLevel() - 1);
    }

    public void startBurningFieldTimer() {
        if(getMobGens().size() > 0
                && getMobs().stream().mapToInt(m -> m.getForcedMobStat().getLevel()).min().orElse(0) >= GameConstants.BURNING_FIELD_MIN_MOB_LEVEL) {
            setBurningFieldLevel(GameConstants.BURNING_FIELD_LEVEL_ON_START);
            EventManager.addFixedRateEvent(this::changeBurningLevel, 0, GameConstants.BURNING_FIELD_TIMER, TimeUnit.MINUTES); //Every X minutes runs 'changeBurningLevel()'
        }
    }

    public void changeBurningLevel() {
        boolean showMessage = true;

        if(getBurningFieldLevel() <= 0) {
            showMessage = false;
        }

        //If there are players on the map,  decrease the level  else  increase the level
        if(getChars().size() > 0 && getBurningFieldLevel() > 0) {
            decreaseBurningLevel();

        } else if(getChars().size() <= 0 && getBurningFieldLevel() < GameConstants.BURNING_FIELD_MAX_LEVEL){
            increaseBurningLevel();
            showMessage = true;
        }

        if(showMessage) {
            showBurningLevel();
        }
    }

    public void setNextEliteSpawnTime(long nextEliteSpawnTime) {
        this.nextEliteSpawnTime = nextEliteSpawnTime;
    }

    public long getNextEliteSpawnTime() {
        return nextEliteSpawnTime;
    }

    public boolean canSpawnElite() {
        return isChannelField()
                && (getEliteState() == null || getEliteState() == EliteState.None)
                && getNextEliteSpawnTime() < System.currentTimeMillis();
    }

    public int getKilledElites() {
        return killedElites;
    }

    public void setKilledElites(int killedElites) {
        this.killedElites = killedElites;
    }

    public void incrementEliteKillCount() {
        setKilledElites(getKilledElites() + 1);
    }

    public void setEliteState(EliteState eliteState) {
        this.eliteState = eliteState;
    }

    public EliteState getEliteState() {
        return eliteState;
    }

    public List<Foothold> getNonWallFootholds() {
        return getFootholds().stream().filter(fh -> !fh.isWall()).collect(Collectors.toList());
    }

    public void setBossMobID(int bossMobID) {
        this.bossMobID = bossMobID;
    }

    public int getBossMobID() {
        return bossMobID;
    }

    public Portal getDefaultPortal() {
        Portal p = getPortalByName("sp");
        return p == null ? getPortalByID(0) : p;
    }

    private ScriptManager getScriptManager() {
        return scriptManagerImpl;
    }

    /**
     * Goes through all MobGens, and spawns a Mob from it if allowed to do so. Only generates when there are Chars
     * on this Field, or if the field is being initialized.
     * @param init if this is the first time that this method is called.
     */
    public void generateMobs(boolean init) {
        if (init || getChars().size() > 0) {
            boolean buffed = !isChannelField()
                    && getChannel() > GameConstants.CHANNELS_PER_WORLD - GameConstants.BUFFED_CHANNELS;
            int currentMobs = getMobs().size();
            for (MobGen mg : getMobGens()) {
                if (mg.canSpawnOnField(this)) {
                    mg.spawnMob(this, buffed);
                    currentMobs++;
                    if ((getFieldLimit() & FieldOption.NoMobCapacityLimit.getVal()) == 0
                            && currentMobs > getFixedMobCapacity()) {
                        break;
                    }
                }
            }
        }
        // No fixed rate to ensure kishin-ness keeps being checked
        double kishinMultiplier = hasKishin() ? GameConstants.KISHIN_MOB_RATE_MULTIPLIER : 1;
        EventManager.addEvent(() -> generateMobs(false),
                (long) (GameConstants.BASE_MOB_RESPAWN_RATE / (getMobRate() * kishinMultiplier)));
    }

    public int getMobCapacity() {
        return (int) (getFixedMobCapacity() * (hasKishin() ? GameConstants.KISHIN_MOB_MULTIPLIER : 1));
    }

    public boolean hasKishin() {
        return kishin;
    }

    public void setKishin(boolean kishin) {
        this.kishin = kishin;
    }

    public List<OpenGate> getOpenGates() {
        return openGateList;
    }

    public void setOpenGates(List<OpenGate> openGateList) {
        this.openGateList = openGateList;
    }

    public void addOpenGate(OpenGate openGate) {
        getOpenGates().add(openGate);
    }

    public void removeOpenGate(OpenGate openGate) {
        getOpenGates().remove(openGate);
    }

    public boolean isChannelField() {
        return isChannelField;
    }

    public void setChannelField(boolean channelField) {
        this.isChannelField = channelField;
    }

    public List<TownPortal> getTownPortalList() {
        return townPortalList;
    }

    public void setTownPortalList(List<TownPortal> townPortalList) {
        this.townPortalList = townPortalList;
    }

    public void addTownPortal(TownPortal townPortal) {
        getTownPortalList().add(townPortal);
    }

    public void removeTownPortal(TownPortal townPortal) {
        getTownPortalList().remove(townPortal);
    }

    public TownPortal getTownPortalByChrId(int chrId) {
        return getTownPortalList().stream().filter(tp -> tp.getChr().getId() == chrId).findAny().orElse(null);
    }
    
    public void increaseReactorState(Char chr, int templateId, int stateLength) {
        Life life = getLifeByTemplateId(templateId);
        if (life instanceof Reactor) {
            Reactor reactor = (Reactor) life;
            reactor.increaseState();
            chr.write(ReactorPool.reactorChangeState(reactor, (short) 0, (byte) stateLength));
        }
    }

    public Map<Integer, List<String>> getDirectionInfo() {
        return directionInfo;
    }

    public void setDirectionInfo(Map<Integer, List<String>> directionInfo) {
        this.directionInfo = directionInfo;
    }

    public List<String> getDirectionNode(int node) {
        return directionInfo.getOrDefault(node, null);
    }

    public void addDirectionInfo(int node, List<String> scripts) {
        directionInfo.put(node, scripts);
    }

    public Clock getClock() {
        return clock;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }
}
