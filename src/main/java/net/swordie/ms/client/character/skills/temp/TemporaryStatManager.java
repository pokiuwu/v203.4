package net.swordie.ms.client.character.skills.temp;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.BodyPart;
import net.swordie.ms.client.character.items.Equip;
import net.swordie.ms.client.character.skills.GuidedBullet;
import net.swordie.ms.client.character.skills.*;
import net.swordie.ms.client.character.skills.PartyBooster;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.client.jobs.adventurer.Warrior;
import net.swordie.ms.client.jobs.legend.Luminous;
import net.swordie.ms.client.jobs.resistance.Demon;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.packet.Summoned;
import net.swordie.ms.connection.packet.UserRemote;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.BaseStat;
import net.swordie.ms.enums.LeaveType;
import net.swordie.ms.enums.TSIndex;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Util;
import net.swordie.ms.util.container.Tuple;
import org.apache.log4j.LogManager;

import java.util.*;
import java.util.concurrent.ScheduledFuture;
import java.util.stream.Collectors;

import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 1/3/2018.
 */
public class TemporaryStatManager {
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();
    private Map<CharacterTemporaryStat, List<Option>> currentStats = new HashMap<>();
    private Map<CharacterTemporaryStat, List<Option>> newStats = new HashMap<>();
    private Map<CharacterTemporaryStat, List<Option>> removedStats = new HashMap<>();
    private Map<CharacterTemporaryStat, ScheduledFuture> schedules = new HashMap<>();
    private HashMap<Tuple<CharacterTemporaryStat, Option>, ScheduledFuture> indieSchedules = new HashMap<>();
    private int pvpDamage;
    private byte defenseState;
    private byte defenseAtt;
    private int[] diceInfo = new int[22];
    private int[] diceOption = new int[8];
    private List<Integer> mobZoneStates;
    private int viperEnergyCharge;
    private StopForceAtom stopForceAtom;
    private Char chr;
    private List<TemporaryStatBase> twoStates = new ArrayList<>();
    private Set<AffectedArea> affectedAreas = new HashSet<>();
    private Map<BaseStat, Integer> baseStats = new HashMap<>();

    public TemporaryStatManager(Char chr){
        this.chr = chr;
        for(CharacterTemporaryStat cts : TSIndex.getAllCTS()) {
            switch(cts) {
                case PartyBooster:
                    twoStates.add(new PartyBooster());
                    break;
                case GuidedBullet:
                    twoStates.add(new GuidedBullet());
                    break;
                case EnergyCharged:
                    twoStates.add(new TemporaryStatBase(true));
                    break;
                case RideVehicle:
                    twoStates.add(new TwoStateTemporaryStat(false));
                    break;
                default:
                    twoStates.add(new TwoStateTemporaryStat(true));
                    break;
            }
        }
    }

    public List<TemporaryStatBase> getTwoStates() {
        return twoStates;
    }

    public TemporaryStatBase getTSBByTSIndex(TSIndex tsi) {
        return getTwoStates().get(tsi.getIndex());
    }

    public void putCharacterStatValue(CharacterTemporaryStat cts, Option option) {
        boolean indie = cts.isIndie();
        option.setTimeToMillis();
        SkillInfo skillinfo = SkillData.getSkillInfoById(indie ? option.nReason : option.rOption);
        if(skillinfo != null && !skillinfo.isNotIncBuffDuration()) {
            int duration = (indie ? option.tTerm : option.tOption);
            long buffTimeR = getChr().getTotalStat(BaseStat.buffTimeR); // includes the 100% base
            if (indie) {
                option.tTerm = (int) ((buffTimeR * duration) / 100);
            } else {
                option.tOption = (int) ((buffTimeR * duration) / 100);
            }
        }
        if(cts == CombatOrders) {
            chr.setCombatOrders(option.nOption);
        }
        if (cts == IndieMaxDamageOver || cts == IndieMaxDamageOverR) {
            long base = getTotalNOptionOfStat(IndieMaxDamageOver) + (cts == IndieMaxDamageOver ? option.nValue : 0);
            long rate = getTotalNOptionOfStat(IndieMaxDamageOverR) + (cts == IndieMaxDamageOverR ? option.nValue : 0);
            if ((GameConstants.DAMAGE_CAP + base) * rate > Integer.MAX_VALUE) {
                chr.chatMessage("Not adding max damage over, as it would bypass the hard cap of damage.");
                return;
            }
        }
        if(!indie) {
            List<Option> optList = new ArrayList<>();
            optList.add(option);
            if (hasStat(cts)) {
                Option oldOption = getCurrentStats().get(cts).get(0);
                // remove old base stats from map
                for (Map.Entry<BaseStat, Integer> stats : BaseStat.getFromCTS(cts, oldOption).entrySet()) {
                    removeBaseStat(stats.getKey(), stats.getValue());
                }
            }
            getNewStats().put(cts, optList);
            getCurrentStats().put(cts, optList);
            for (Map.Entry<BaseStat, Integer> stats : BaseStat.getFromCTS(cts, option).entrySet()) {
                addBaseStat(stats.getKey(), stats.getValue());
            }
            if (option.tOption > 0) {
                if (getSchedules().containsKey(cts)) {
                    getSchedules().get(cts).cancel(false);
                }
                ScheduledFuture sf = EventManager.addEvent(() -> removeStat(cts, true), option.tOption);
                getSchedules().put(cts, sf);
            }
        } else {
            List<Option> optList;
            if(hasStat(cts)) {
                optList = getCurrentStats().get(cts);
            } else {
                optList = new ArrayList<>();
            }
            if(optList.contains(option)) {
                // remove old option of the same skill
                Option oldOption = getOptByCTSAndSkill(cts, option.nReason);
                for (Map.Entry<BaseStat, Integer> stats : BaseStat.getFromCTS(cts, oldOption).entrySet()) {
                    removeBaseStat(stats.getKey(), stats.getValue());
                }
                optList.remove(oldOption);
            }
            optList.add(option);
            getNewStats().put(cts, optList);
            getCurrentStats().put(cts, optList);
            // Add stats to basestat
            for (Map.Entry<BaseStat, Integer> stats : BaseStat.getFromCTS(cts, option).entrySet()) {
                addBaseStat(stats.getKey(), stats.getValue());
            }
            if (option.tTerm > 0) {
                Tuple tuple = new Tuple(cts, option);
                if (getIndieSchedules().containsKey(tuple)) {
                    getIndieSchedules().get(tuple).cancel(false);
                }
                ScheduledFuture sf = EventManager.addEvent(() -> removeIndieStat(cts, option, true), option.tTerm);
                getIndieSchedules().put(tuple, sf);
            }
        }
        if (cts != LifeTidal && JobConstants.isDemonAvenger(chr.getJob())) {
            ((Demon) chr.getJobHandler()).sendHpUpdate();
        }
    }

    public Option getOptByCTSAndSkill(CharacterTemporaryStat cts, int skillID) {
        Option res = null;
        if(getCurrentStats().containsKey(cts)) {
            for (Option o : getCurrentStats().get(cts)) {
                if(o.rOption == skillID || o.nReason == skillID) {
                    res = o;
                    break;
                }
            }
        }
        return res;
    }

    public synchronized void removeStat(CharacterTemporaryStat cts, boolean fromSchedule) {
        if(cts == CombatOrders) {
            chr.setCombatOrders(0);
        }
        Option opt = getOption(cts);
        for (Map.Entry<BaseStat, Integer> stats : BaseStat.getFromCTS(cts, opt).entrySet()) {
            removeBaseStat(stats.getKey(), stats.getValue());
        }
        getRemovedStats().put(cts, getCurrentStats().get(cts));
        getCurrentStats().remove(cts);
        sendResetStatPacket(cts == RideVehicle || cts == RideVehicleExpire);
        if (TSIndex.isTwoStat(cts)) {
            getTSBByTSIndex(TSIndex.getTSEFromCTS(cts)).reset();
        }
        if(!fromSchedule && getSchedules().containsKey(cts)) {
            getSchedules().get(cts).cancel(false);
        } else {
            getSchedules().remove(cts);
        }
        if (JobConstants.isDemonAvenger(chr.getJob())) {
            ((Demon) chr.getJobHandler()).sendHpUpdate();
        }
    }

    public synchronized void removeIndieStat(CharacterTemporaryStat cts, Option option, boolean fromSchedule) {
        List<Option> optList = new ArrayList<>();
        optList.add(option);
        getRemovedStats().put(cts, optList);
        for (Map.Entry<BaseStat, Integer> stats : BaseStat.getFromCTS(cts, option).entrySet()) {
            removeBaseStat(stats.getKey(), stats.getValue());
        }
        if(getCurrentStats().containsKey(cts)) {
            if(option.summon != null) {
                getChr().getField().broadcastPacket(Summoned.summonedRemoved(option.summon, LeaveType.ANIMATION));
                option.summon = null;
            }
            getCurrentStats().get(cts).remove(option);
            if(getCurrentStats().get(cts).size() == 0) {
                getCurrentStats().remove(cts);
            }
        }
        sendResetStatPacket();
        Tuple tuple = new Tuple(cts, option);
        if(!fromSchedule && getIndieSchedules().containsKey(tuple)) {
            getIndieSchedules().get(tuple).cancel(false);
        } else {
            getIndieSchedules().remove(tuple);
        }
    }

    public boolean hasNewStat(CharacterTemporaryStat cts) {
        return newStats.containsKey(cts);
    }

    public boolean hasStat(CharacterTemporaryStat cts) {
        return getCurrentStats().containsKey(cts);
    }

    public Option getOption(CharacterTemporaryStat cts) {
        if(hasStat(cts)) {
            return getCurrentStats().get(cts).get(0);
        }
        return new Option();
    }

    public List<Option> getOptions(CharacterTemporaryStat cts) {
        if(hasStat(cts)) {
            return getCurrentStats().get(cts);
        }
        return new ArrayList<>();
    }

    public int[] getMaskByCollection(Map<CharacterTemporaryStat, List<Option>> map) {
        int[] res = new int[CharacterTemporaryStat.length];
        for(int i = 0; i < res.length; i++) {
            for(CharacterTemporaryStat cts : map.keySet()) {
                if(cts.getPos() == i) {
                    res[i] |= cts.getVal();
                }
            }
        }
        return res;
    }

    public int[] getTotalMask() {
        return getMaskByCollection(getCurrentStats());
    }

    public int[] getNewMask() {
        return getMaskByCollection(getNewStats());
    }

    public int[] getRemovedMask() {
        return getMaskByCollection(getRemovedStats());
    }

    public void encodeForLocal(OutPacket outPacket) {
        int[] mask = getNewMask();
        for(int i = 0; i < getNewMask().length; i++) {
            outPacket.encodeInt(mask[i]);
        }
        List<CharacterTemporaryStat> orderedAndFilteredCtsList = new ArrayList<>(getNewStats().keySet()).stream()
                .filter(cts -> cts.getOrder() != -1)
                .sorted(Comparator.comparingInt(CharacterTemporaryStat::getOrder))
                .collect(Collectors.toList());
        for (CharacterTemporaryStat cts : orderedAndFilteredCtsList) {
            if (cts.getOrder() != -1) {
                Option o = getOption(cts);
                if (cts.isEncodeInt()) {
                    outPacket.encodeInt(o.nOption);
                } else {
                    outPacket.encodeShort(o.nOption);
                }
                outPacket.encodeInt(o.rOption);
                outPacket.encodeInt(o.tOption);
            }
        }

        if (hasNewStat(SoulMP)) {
            outPacket.encodeInt(getOption(SoulMP).xOption);
            outPacket.encodeInt(getOption(SoulMP).rOption);
        }
        if (hasNewStat(FullSoulMP)) {
            outPacket.encodeInt(getOption(FullSoulMP).xOption);
        }
        short size = 0;
        outPacket.encodeShort(size);
        for (int i = 0; i < size; i++) {
            outPacket.encodeInt(0); // nKey
            outPacket.encodeByte(0); // bEnable
        }
        if (hasNewStat(HayatoStance)) {
            outPacket.encodeInt(getOption(HayatoStance).xOption);
        }
        if (hasNewStat(Unk579)) {
            outPacket.encodeInt(getOption(Unk579).xOption);
        }
        outPacket.encodeByte(getDefenseAtt());
        outPacket.encodeByte(getDefenseState());
        outPacket.encodeByte(getPvpDamage());
        outPacket.encodeInt(0);//?
        if (hasNewStat(Dice)) {
            for (int i = 0; i < getDiceInfo().length; i++) {
                outPacket.encodeInt(diceInfo[i]);
            }
        }
        if (hasNewStat(Unk526)) {
            outPacket.encodeInt(getOption(Unk526).xOption);
        }
        if (hasNewStat(Unk527)) {
            outPacket.encodeInt(getOption(Unk527).xOption);
        }
        if (hasNewStat(Unk558)) {
            outPacket.encodeInt(getOption(Unk558).xOption);
        }
        if (hasNewStat(KeyDownMoving)) {
            outPacket.encodeInt(getOption(KeyDownMoving).nOption);
        }
        if (hasNewStat(KillingPoint)) {
            outPacket.encodeByte(getOption(KillingPoint).nOption);
        }
        if (hasNewStat(PinkbeanRollingGrade)) {
            outPacket.encodeByte(getOption(PinkbeanRollingGrade).nOption);
        }
        if (hasNewStat(Judgement)) {
            outPacket.encodeInt(getOption(Judgement).xOption); // byte would  err38
        }
        if (hasNewStat(StackBuff)) {
            outPacket.encodeByte(getOption(StackBuff).mOption);
        }
        if (hasNewStat(Trinity)) {
            outPacket.encodeByte(getOption(Trinity).mOption);
        }
        if (hasNewStat(ElementalCharge)) {
            outPacket.encodeByte(getOption(ElementalCharge).mOption);
            outPacket.encodeShort(getOption(ElementalCharge).wOption);
            outPacket.encodeByte(getOption(ElementalCharge).uOption);
            outPacket.encodeByte(getOption(ElementalCharge).zOption);
        }
        if (hasNewStat(LifeTidal)) {
            outPacket.encodeInt(getOption(LifeTidal).mOption);
        }
        if (hasNewStat(AntiMagicShell)) {
            outPacket.encodeByte(getOption(AntiMagicShell).bOption);
        }
        if (hasNewStat(Larkness)) {
            ((Luminous) chr.getJobHandler()).encodeLarkness(outPacket);
        }
        if (hasNewStat(IgnoreTargetDEF)) {
            outPacket.encodeInt(getOption(IgnoreTargetDEF).mOption);
        }
        if (hasNewStat(StopForceAtomInfo)) {
            getStopForceAtom().encode(outPacket);
        }
        if (hasNewStat(SmashStack)) {
            outPacket.encodeInt(getOption(SmashStack).xOption);
        }
        if (hasNewStat(MobZoneState)) {
            for (int zoneState : getMobZoneStates()) {
                outPacket.encodeInt(zoneState);
            }
            outPacket.encodeInt(0); // notify end
        }
        if (hasNewStat(Unk163)) {
            int unk = 0;
            outPacket.encodeInt(unk);
            for (int i = 0; i < unk; i++) {
                outPacket.encodeInt(0);
            }
        }
        if (hasNewStat(Slow)) {
            outPacket.encodeByte(getOption(Slow).bOption);
        }
        if (hasNewStat(IceAura)) {
            outPacket.encodeByte(getOption(IceAura).bOption);
        }
        if (hasNewStat(KnightsAura)) {
            outPacket.encodeByte(getOption(KnightsAura).bOption);
        }
        if (hasNewStat(IgnoreMobpdpR)) {
            outPacket.encodeByte(getOption(IgnoreMobpdpR).bOption);
        }
        if (hasNewStat(BdR)) {
            outPacket.encodeByte(getOption(BdR).bOption);
        }
        if (hasNewStat(DropRIncrease)) {
            outPacket.encodeInt(getOption(DropRIncrease).xOption);
            outPacket.encodeByte(getOption(DropRIncrease).bOption);
        }
        if (hasNewStat(PoseType)) {
            outPacket.encodeByte(getOption(PoseType).bOption);
        }
        if (hasNewStat(Beholder)) {
            outPacket.encodeInt(getOption(Beholder).sOption);
            outPacket.encodeInt(getOption(Beholder).ssOption);
        }
        if (hasNewStat(CrossOverChain)) {
            outPacket.encodeInt(getOption(CrossOverChain).xOption);
        }
        if (hasNewStat(Reincarnation)) {
            outPacket.encodeInt(getOption(Reincarnation).xOption);
        }
        if (hasNewStat(ExtremeArchery)) {
            outPacket.encodeInt(getOption(ExtremeArchery).bOption);
            outPacket.encodeInt(getOption(ExtremeArchery).xOption);
        }
        if (hasNewStat(QuiverCatridge)) {
            outPacket.encodeInt(getOption(QuiverCatridge).xOption);
        }
        if (hasNewStat(ImmuneBarrier)) {
            outPacket.encodeInt(getOption(ImmuneBarrier).xOption);
        }
        if (hasNewStat(ZeroAuraStr)) {
            outPacket.encodeByte(getOption(ZeroAuraStr).bOption);
        }
        if (hasNewStat(ZeroAuraSpd)) {
            outPacket.encodeByte(getOption(ZeroAuraSpd).bOption);
        }
        if (hasNewStat(ArmorPiercing)) {
            outPacket.encodeInt(getOption(ArmorPiercing).bOption);
        }
        if (hasNewStat(SharpEyes)) {
            outPacket.encodeInt(getOption(SharpEyes).mOption);
        }
        if (hasNewStat(AdvancedBless)) {
            outPacket.encodeInt(getOption(AdvancedBless).xOption);
        }
        if (hasNewStat(DotHealHPPerSecond)) {
            outPacket.encodeInt(getOption(DotHealHPPerSecond).xOption);
        }
        if (hasNewStat(SpiritGuard)) {
            outPacket.encodeInt(getOption(SpiritGuard).nOption);
        }
        if (hasNewStat(Unk379)) {
            outPacket.encodeInt(getOption(Unk379).xOption);
        }
        if (hasNewStat(Unk425)) {
            outPacket.encodeInt(getOption(Unk425).xOption);
        }
        if (hasNewStat(KnockBack)) {
            outPacket.encodeInt(getOption(KnockBack).nOption);
            outPacket.encodeInt(getOption(KnockBack).bOption);
        }
        if (hasNewStat(ShieldAttack)) {
            outPacket.encodeInt(getOption(ShieldAttack).xOption);
        }
        if (hasNewStat(SSFShootingAttack)) {
            outPacket.encodeInt(getOption(SSFShootingAttack).xOption);
        }
        if (hasNewStat(BMageAura)) {
            outPacket.encodeInt(getOption(BMageAura).xOption);
            outPacket.encodeByte(getOption(BMageAura).bOption);
        }
        if (hasNewStat(BattlePvPHelenaMark)) {
            outPacket.encodeInt(getOption(BattlePvPHelenaMark).cOption);
        }
        if (hasNewStat(PinkbeanAttackBuff)) {
            outPacket.encodeInt(getOption(PinkbeanAttackBuff).bOption);
        }
        if (hasNewStat(RoyalGuardState)) {
            outPacket.encodeInt(getOption(RoyalGuardState).bOption);
            outPacket.encodeInt(getOption(RoyalGuardState).xOption);
        }
        if (hasNewStat(MichaelSoulLink)) {
            outPacket.encodeInt(getOption(MichaelSoulLink).xOption);
            outPacket.encodeByte(getOption(MichaelSoulLink).bOption);
            outPacket.encodeInt(getOption(MichaelSoulLink).cOption);
            outPacket.encodeInt(getOption(MichaelSoulLink).yOption);
        }
        if (hasNewStat(AdrenalinBoost)) {
            outPacket.encodeByte(getOption(AdrenalinBoost).cOption);
        }
        if (hasNewStat(RWCylinder)) {
            outPacket.encodeByte(getOption(RWCylinder).bOption);
            outPacket.encodeShort(getOption(RWCylinder).cOption);
            outPacket.encodeByte(getOption(RWCylinder).bOption);
        }
        if (hasNewStat(Unk529)) {
            outPacket.encodeInt(getOption(Unk529).xOption);
        }
        if (hasNewStat(Unk476)) {
            outPacket.encodeInt(getOption(Unk476).xOption);
        }
        if (hasNewStat(RWMagnumBlow)) {
            outPacket.encodeShort(getOption(RWMagnumBlow).bOption);
            outPacket.encodeByte(getOption(RWMagnumBlow).xOption);
        }
        outPacket.encodeInt(getViperEnergyCharge());
        if (hasNewStat(BladeStance)) {
            outPacket.encodeInt(getOption(BladeStance).xOption);
        }
        if (hasNewStat(DarkSight)) {
            outPacket.encodeInt(getOption(DarkSight).cOption);
            outPacket.encodeInt(getOption(DarkSight).cOption);
        }
        if (hasNewStat(Stigma)) {
            outPacket.encodeInt(getOption(Stigma).bOption);
        }
        if (hasNewStat(Unk256)) {
            outPacket.encodeInt(getOption(Unk256).xOption);
        }
        if (hasNewStat(CriticalGrowing)) {
            outPacket.encodeInt(getOption(CriticalGrowing).xOption);
        }
        if (hasNewStat(Ember)) {
            outPacket.encodeInt(getOption(Ember).xOption);
        }
        if (hasNewStat(PickPocket)) {
            outPacket.encodeInt(getOption(PickPocket).xOption);
        }
        if (hasNewStat(DivineEcho)) {
            outPacket.encodeShort(getOption(DivineEcho).xOption);
        }
        if (hasNewStat(Unk423)) {
            outPacket.encodeShort(getOption(Unk423).xOption);
        }
        if (hasNewStat(Unk424)) {
            outPacket.encodeShort(getOption(Unk424).xOption);
        }
        if (hasNewStat(Unk503)) {
            outPacket.encodeInt(getOption(Unk503).xOption);
            outPacket.encodeInt(getOption(Unk503).bOption);
            outPacket.encodeInt(getOption(Unk503).cOption);
            outPacket.encodeInt(getOption(Unk503).yOption);
        }
        if (hasNewStat(VampDeath)) {
            outPacket.encodeInt(getOption(VampDeath).xOption);
        }
        if (hasNewStat(HolyMagicShell)) {
            outPacket.encodeInt(getOption(HolyMagicShell).xOption);
        }
        for (int i = 0; i < 7; i++) {
            if(hasNewStat(TSIndex.getCTSFromTwoStatIndex(i))) {
                getTwoStates().get(i).encode(outPacket);
            }
        }
        encodeIndieTempStat(outPacket);
        if (hasNewStat(UsingScouter)) {
            outPacket.encodeInt(getOption(UsingScouter).nOption);
            outPacket.encodeInt(getOption(UsingScouter).xOption);
        }
        if (hasNewStat(Unk517)) {
            outPacket.encodeInt(getOption(Unk517).xOption);
        }
        if (hasNewStat(Unk520)) {
            outPacket.encodeInt(getOption(Unk520).nOption);
            outPacket.encodeInt(getOption(Unk520).xOption);
        }
        if (hasNewStat(Unk255)) {
            outPacket.encodeInt(getOption(Unk255).nOption);
            outPacket.encodeInt(getOption(Unk255).xOption);
        }
        if (hasNewStat(Unk521)) {
            outPacket.encodeInt(getOption(Unk521).xOption);
        }
        if (hasNewStat(Unk518)) {
            outPacket.encodeInt(getOption(Unk518).xOption);
        }
        if (hasNewStat(CoreOverload)) {
            outPacket.encodeInt(getOption(CoreOverload).xOption);
        }
        if (hasNewStat(SpecterEnergy)) {
            outPacket.encodeInt(getOption(SpecterEnergy).xOption);
        }
        if (hasNewStat(BasicCast)) {
            outPacket.encodeInt(getOption(BasicCast).xOption);
            outPacket.encodeInt(getOption(BasicCast).xOption);
        }
        if (hasNewStat(ScarletCast)) {
            outPacket.encodeInt(getOption(ScarletCast).xOption);
            outPacket.encodeInt(getOption(ScarletCast).xOption);
        }
        if (hasNewStat(GustCast)) {
            outPacket.encodeInt(getOption(GustCast).xOption);
            outPacket.encodeInt(getOption(GustCast).xOption);
        }
        if (hasNewStat(AbyssalCast)) {
            outPacket.encodeInt(getOption(AbyssalCast).xOption);
            outPacket.encodeInt(getOption(AbyssalCast).xOption);
        }
        if (hasNewStat(Unk556)) {
            outPacket.encodeInt(getOption(Unk556).xOption);
        }
        if (hasNewStat(Unk563)) {
            outPacket.encodeInt(getOption(Unk563).xOption);
        }
        if (hasNewStat(Unk566)) {
            outPacket.encodeInt(getOption(Unk566).xOption);
        }
        if (hasNewStat(Unk567)) {
            outPacket.encodeInt(getOption(Unk567).xOption);
        }
        if (hasNewStat(Unk573)) {
            outPacket.encodeInt(getOption(Unk573).xOption);
        }
        if (hasNewStat(NewFlying)) {
            outPacket.encodeInt(getOption(NewFlying).xOption);
        }
        if (hasNewStat(BeastMode)) {
            outPacket.encodeByte(getOption(BeastMode).bOption);
        }
        if (hasNewStat(Unk612)) {
            outPacket.encodeInt(getOption(Unk612).xOption);
            outPacket.encodeInt(getOption(Unk612).cOption);
            outPacket.encodeInt(getOption(Unk612).yOption);
        }
        if (hasNewStat(Unk615)) {
            outPacket.encodeInt(getOption(Unk615).xOption);
            outPacket.encodeInt(getOption(Unk615).cOption);
            outPacket.encodeInt(getOption(Unk615).yOption);
        }
        if (hasNewStat(Unk616)) {
            outPacket.encodeInt(getOption(Unk616).xOption);
            outPacket.encodeInt(getOption(Unk616).cOption);
            outPacket.encodeInt(getOption(Unk616).yOption);
        }
        if (hasNewStat(Unk617)) {
            outPacket.encodeInt(getOption(Unk617).nOption);
            outPacket.encodeInt(getOption(Unk617).xOption);
            outPacket.encodeInt(getOption(Unk617).cOption);
            outPacket.encodeInt(getOption(Unk617).yOption);
        }
        if (hasNewStat(Unk618)) {
            outPacket.encodeInt(getOption(Unk618).xOption);
            outPacket.encodeInt(getOption(Unk618).cOption);
            outPacket.encodeInt(getOption(Unk618).yOption);
        }
        if (hasNewStat(Unk619)) {
            outPacket.encodeInt(getOption(Unk619).xOption);
        }
        getNewStats().clear();
    }



    public void encodeForRemote(OutPacket outPacket, Map<CharacterTemporaryStat, List<Option>> collection) {
        int[] mask = getMaskByCollection(collection);
        for (int maskElem : mask) {
            outPacket.encodeInt(maskElem);
        }
        List<CharacterTemporaryStat> orderedAndFilteredCtsList = new ArrayList<>(collection.keySet()).stream()
                .filter(cts -> cts.getOrder() != -1)
                .sorted(Comparator.comparingInt(CharacterTemporaryStat::getOrder))
                .collect(Collectors.toList());
        for (CharacterTemporaryStat cts : orderedAndFilteredCtsList) {
            if (cts.getRemoteOrder() != -1) {
                Option o = getOption(cts);
                switch (cts) {
                    case Unk82: // Why does this get encoded, then immediately overwritten?
                        outPacket.encodeShort(o.nOption);
                        break;
                }
                if (!cts.isNotEncodeAnything()) {
                    if (cts.isRemoteEncode1()) {
                        outPacket.encodeByte(o.nOption);
                    } else if (cts.isRemoteEncode4()) {
                        outPacket.encodeInt(o.nOption);
                    } else {
                        outPacket.encodeShort(o.nOption);
                    }
                    if (!cts.isNotEncodeReason()) {
                        outPacket.encodeInt(o.rOption);
                    }
                }
                switch (cts) {
                    case Contagion:
                        outPacket.encodeInt(o.tOption);
                        break;
                    case BladeStance:
                    case ImmuneBarrier:
                    case Unk530:
                    case Unk531:
                    case Unk586:
                        outPacket.encodeInt(o.xOption);
                        break;
                    case FullSoulMP:
                        outPacket.encodeInt(o.rOption);
                        outPacket.encodeInt(o.xOption);
                        break;
                    case AntiMagicShellBool:
                    case PoseTypeBool:
                        outPacket.encodeByte(o.bOption);
                        break;
                }
            }
        }
        outPacket.encodeByte(getDefenseAtt());
        outPacket.encodeByte(getDefenseState());
        outPacket.encodeByte(getPvpDamage());
        outPacket.encodeInt(0);// unknown
        outPacket.encodeInt(0);
        Set<CharacterTemporaryStat> ctsSet = collection.keySet();
        if (ctsSet.contains(Unk526)) {
            outPacket.encodeInt(collection.get(Unk526).get(0).xOption);
        }
        if (ctsSet.contains(Unk527)) {
            outPacket.encodeInt(collection.get(Unk527).get(0).xOption);
        }
        if (ctsSet.contains(ZeroAuraStr)) {
            outPacket.encodeByte(collection.get(ZeroAuraStr).get(0).bOption);
        }
        if (ctsSet.contains(ZeroAuraSpd)) {
            outPacket.encodeByte(collection.get(ZeroAuraSpd).get(0).bOption);
        }
        if (ctsSet.contains(BMageAura)) {
            outPacket.encodeByte(collection.get(BMageAura).get(0).bOption);
        }
        if (ctsSet.contains(BattlePvPHelenaMark)) {
            outPacket.encodeInt(collection.get(BattlePvPHelenaMark).get(0).nOption);
            outPacket.encodeInt(collection.get(BattlePvPHelenaMark).get(0).rOption);
            outPacket.encodeInt(collection.get(BattlePvPHelenaMark).get(0).cOption);
        }
        if (ctsSet.contains(BattlePvPLangEProtection)) {
            outPacket.encodeInt(collection.get(BattlePvPLangEProtection).get(0).nOption);
            outPacket.encodeInt(collection.get(BattlePvPLangEProtection).get(0).rOption);
        }
        if (ctsSet.contains(MichaelSoulLink)) {
            outPacket.encodeInt(collection.get(MichaelSoulLink).get(0).xOption);
            outPacket.encodeByte(collection.get(MichaelSoulLink).get(0).bOption);
            outPacket.encodeInt(collection.get(MichaelSoulLink).get(0).cOption);
            outPacket.encodeInt(collection.get(MichaelSoulLink).get(0).yOption);
        }
        if (ctsSet.contains(AdrenalinBoost)) {
            outPacket.encodeByte(collection.get(AdrenalinBoost).get(0).cOption);
        }
        if (ctsSet.contains(Stigma)) {
            outPacket.encodeInt(collection.get(Stigma).get(0).bOption);
        }
        if (ctsSet.contains(DivineEcho)) {
            outPacket.encodeShort(collection.get(DivineEcho).get(0).xOption);
        }
        if (ctsSet.contains(Unk423)) {
            outPacket.encodeShort(collection.get(Unk423).get(0).xOption);
        }
        if (ctsSet.contains(Unk424)) {
            outPacket.encodeShort(collection.get(Unk424).get(0).xOption);
        }
        if (ctsSet.contains(Unk503)) {
            outPacket.encodeInt(collection.get(Unk503).get(0).xOption);
            outPacket.encodeInt(collection.get(Unk503).get(0).bOption);
            outPacket.encodeInt(collection.get(Unk503).get(0).cOption);
            outPacket.encodeInt(collection.get(Unk503).get(0).yOption);
        }
        if (ctsSet.contains(VampDeath)) {
            outPacket.encodeInt(collection.get(VampDeath).get(0).xOption);
        }
        if (ctsSet.contains(Unk520)) {
            outPacket.encodeInt(collection.get(Unk520).get(0).bOption);
            outPacket.encodeInt(collection.get(Unk520).get(0).xOption);
        }
        if (ctsSet.contains(Unk255)) {
            outPacket.encodeInt(collection.get(Unk255).get(0).bOption);
            outPacket.encodeInt(collection.get(Unk255).get(0).xOption);
        }
        if (ctsSet.contains(Unk538)) {
            outPacket.encodeInt(collection.get(Unk538).get(0).xOption);
            outPacket.encodeInt(collection.get(Unk538).get(0).bOption);
            outPacket.encodeInt(collection.get(Unk538).get(0).cOption);
        }
        if (getStopForceAtom() != null) {
            getStopForceAtom().encode(outPacket);
        } else {
            new StopForceAtom().encode(outPacket);
        }
        //outPacket.encodeInt(getViperEnergyCharge());
        for (int i = 0; i < 8; i++) {// 7=>8 v202 maybe more ts index ?
            if(hasNewStat(TSIndex.getCTSFromTwoStatIndex(i))) {
                getTwoStates().get(i).encode(outPacket);
            }
        }
        encodeIndieTempStat(outPacket);
        if (ctsSet.contains(NewFlying)) {
            outPacket.encodeInt(collection.get(NewFlying).get(0).xOption);
        }
        if (ctsSet.contains(Unk517)) {
            outPacket.encodeInt(collection.get(Unk517).get(0).xOption);
        }
        if (ctsSet.contains(KeyDownMoving)) {
            outPacket.encodeInt(collection.get(KeyDownMoving).get(0).xOption);
        }
        if (ctsSet.contains(Unk556)) {
            outPacket.encodeInt(collection.get(Unk556).get(0).xOption);
        }
    }

    private void encodeIndieTempStat(OutPacket outPacket) {
        Map<CharacterTemporaryStat, List<Option>> stats = getCurrentStats().entrySet().stream()
                .filter(stat -> stat.getKey().isIndie() && getNewStats().containsKey(stat.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        TreeMap<CharacterTemporaryStat, List<Option>> sortedStats = new TreeMap<>(stats);
        for(Map.Entry<CharacterTemporaryStat, List<Option>> stat : sortedStats.entrySet()) {
            int curTime = (int) System.currentTimeMillis();
            List<Option> options = stat.getValue();
            if(options == null) {
                outPacket.encodeInt(0);
                continue;
            }
            outPacket.encodeInt(options.size());
            for(Option option : options) {
                outPacket.encodeInt(option.nReason);
                outPacket.encodeInt(option.nValue);
                outPacket.encodeInt(option.nKey);
                outPacket.encodeInt(curTime - option.tStart); // elapsedTime
                outPacket.encodeInt(option.tTerm);
                int size = 0;
                outPacket.encodeInt(size);
                for (int i = 0; i < size; i++) {
                    outPacket.encodeInt(0); // MValueKey
                    outPacket.encodeInt(0); // MValue
                }
            }
        }
    }

    public void encodeRemovedIndieTempStat(OutPacket outPacket) {
        Map<CharacterTemporaryStat, List<Option>> stats = getRemovedStats().entrySet().stream()
                .filter(stat -> stat.getKey().isIndie())
                .sorted(Comparator.comparingInt(stat -> stat.getKey().getVal()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        for(Map.Entry<CharacterTemporaryStat, List<Option>> stat : stats.entrySet()) {
            int curTime = (int) System.currentTimeMillis();
            // encode remaining stats
            CharacterTemporaryStat key = stat.getKey();
            List<Option> options = getOptions(key);
            if(options == null) {
                outPacket.encodeInt(0);
                continue;
            }
            outPacket.encodeInt(options.size());
            for(Option option : options) {
                outPacket.encodeInt(option.nReason);
                outPacket.encodeInt(option.nValue);
                outPacket.encodeInt(option.nKey); // nKey
                outPacket.encodeInt(curTime - option.tStart);
                outPacket.encodeInt(option.tTerm); // tTerm
                outPacket.encodeInt(0); // size
                // pw.writeInt(0); // nMValueKey
                // pw.writeInt(0); // nMValue
            }
        }
    }

    public boolean hasNewMovingEffectingStat() {
        return getNewStats().keySet().stream().anyMatch(CharacterTemporaryStat::isMovingEffectingStat);
    }

    public boolean hasMovingEffectingStat() {
        return getCurrentStats().keySet().stream().anyMatch(CharacterTemporaryStat::isMovingEffectingStat);
    }

    public boolean hasRemovedMovingEffectingStat() {
        return getRemovedStats().keySet().stream().anyMatch(CharacterTemporaryStat::isMovingEffectingStat);
    }

    public Map<CharacterTemporaryStat, List<Option>> getCurrentStats() {
        return currentStats;
    }

    public Map<CharacterTemporaryStat, List<Option>> getNewStats() {
        return newStats;
    }

    public Map<CharacterTemporaryStat, List<Option>> getRemovedStats() {
        return removedStats;
    }

    public int getPvpDamage() {
        return pvpDamage;
    }

    public void setPvpDamage(int pvpDamage) {
        this.pvpDamage = pvpDamage;
    }

    public byte getDefenseState() {
        return defenseState;
    }

    public void setDefenseState(byte defenseState) {
        this.defenseState = defenseState;
    }

    public byte getDefenseAtt() {
        return defenseAtt;
    }

    public void setDefenseAtt(byte defenseAtt) {
        this.defenseAtt = defenseAtt;
    }

    public int[] getDiceInfo() {
        return diceInfo;
    }

    public void setDiceInfo(int[] diceInfo) {
        this.diceInfo = diceInfo;
    }

    public void throwDice(int roll) {
        throwDice(roll, 0);
    }

    public void throwDice(int roll, int secondRoll) {
        int[] array = {0, 0, 30, 20, 15, 20, 30, 20}; // Stats for Normal Rolls
        int[] arrayDD = {0, 0, 40, 30, 25, 30, 40, 30}; // Stats for Double Down Rolls
        for(int i = 0; i < diceOption.length; i++) {
            diceOption[i] = 0;
        }
        if(roll == secondRoll) {
            diceOption[roll] = arrayDD[roll];
        } else {
            diceOption[roll] = array[roll];
            diceOption[secondRoll] = array[secondRoll];
        }
        int[] diceinfo = new int[] {
                diceOption[3],  //nOption 3 (MHPR)
                diceOption[3],  //nOption 3 (MMPR)
                diceOption[4],  //nOption 4 (Cr)
                0,  // CritDamage Min
                0,  // ???  ( CritDamage Max (?) )
                0,  // EVAR
                0,  // AR
                0,  // ER
                diceOption[2],  //nOption 2 (PDDR)
                diceOption[2],  //nOption 2 (MDDR)
                0,  // PDR
                0,  // MDR
                diceOption[5],  //nOption 5 (PIDR)
                0,  // PDamR
                0,  // MDamR
                0,  // PADR
                0,  // MADR
                diceOption[6], //nOption 6 (EXP)
                diceOption[7], //nOption 7 (IED)
                0,  // ASRR
                0,  // TERR
                0,  // MesoRate
                0,
        };
        setDiceInfo(diceinfo);
    }

    public List<Integer> getMobZoneStates() {
        return mobZoneStates;
    }

    public void setMobZoneStates(List<Integer> mobZoneStates) {
        this.mobZoneStates = mobZoneStates;
    }

    public int getViperEnergyCharge() {
        return viperEnergyCharge;
    }

    public void setViperEnergyCharge(int viperEnergyCharge) {
        this.viperEnergyCharge = viperEnergyCharge;
    }

    public StopForceAtom getStopForceAtom() {
        return stopForceAtom;
    }

    public void setStopForceAtom(StopForceAtom stopForceAtom) {
        this.stopForceAtom = stopForceAtom;
    }

    public Char getChr() {
        return chr;
    }

    public Map<CharacterTemporaryStat, ScheduledFuture> getSchedules() {
        return schedules;
    }

    public Map<Tuple<CharacterTemporaryStat, Option>, ScheduledFuture> getIndieSchedules() {
        return indieSchedules;
    }

    public void sendSetStatPacket() {
        getChr().getField().broadcastPacket(UserRemote.setTemporaryStat(getChr(), (short) 0), getChr());
        getChr().getClient().write(WvsContext.temporaryStatSet(this));
    }

    public void sendResetStatPacket() {
        sendResetStatPacket(false);
    }

    public void sendResetStatPacket(boolean demount) {
        if(getRemovedStats().containsKey(CharacterTemporaryStat.Reincarnation)) {
            Warrior.finalPactEnd(chr);
        }
        getChr().getField().broadcastPacket(UserRemote.resetTemporaryStat(getChr()), getChr());
        getChr().getClient().write(WvsContext.temporaryStatReset(this, demount));
    }

    public void removeAllDebuffs() {
        removeStat(CharacterTemporaryStat.Stun, false);
        removeStat(CharacterTemporaryStat.Poison, false);
        removeStat(CharacterTemporaryStat.Seal, false);
        removeStat(CharacterTemporaryStat.Darkness, false);
        removeStat(CharacterTemporaryStat.Thaw, false);
        removeStat(CharacterTemporaryStat.Weakness, false);
        removeStat(CharacterTemporaryStat.Curse, false);
        removeStat(CharacterTemporaryStat.Slow, false);
        removeStat(CharacterTemporaryStat.Blind, false);
        sendResetStatPacket();
    }

    public Set<AffectedArea> getAffectedAreas() {
        return affectedAreas;
    }

    public void addAffectedArea(AffectedArea aa) {
        getAffectedAreas().add(aa);
    }

    public void removeAffectedArea(AffectedArea aa) {
        getAffectedAreas().remove(aa);

        if (aa.getRemoveSkill()) {
            removeStatsBySkill(aa.getSkillID());
        }
    }

    public boolean hasAffectedArea(AffectedArea affectedArea) {
        return getAffectedAreas().contains(affectedArea);
    }

    public boolean hasStatBySkillId(int skillId) {
        for (CharacterTemporaryStat cts : getCurrentStats().keySet()) {
            if (getOption(cts).rOption == skillId || getOption(cts).nReason == skillId) {
                return true;
            }
        }
        return false;
    }

    public void removeStatsBySkill(int skillId) {
        Map<CharacterTemporaryStat, Option> removedMap = new HashMap<>();
        for (CharacterTemporaryStat cts : getCurrentStats().keySet()) {
            Option checkOpt = new Option();
            checkOpt.nReason = skillId;
            if (cts.isIndie() && getOptions(cts) != null && getOptions(cts).contains(checkOpt)) {
                Option o = Util.findWithPred(getOptions(cts), opt -> opt.equals(checkOpt));
                if (o == null) {
                    log.error("Found option null, yet the options contained it?");
                } else {
                    removedMap.put(cts, o);
                }
            } else if (getOption(cts).rOption == skillId || getOption(cts).nReason == skillId) {
                removedMap.put(cts, getOption(cts));
            }
        }
        removedMap.forEach((cts, opt) -> {
            if (cts.isIndie()) {
                removeIndieStat(cts, opt, false);
            } else {
                removeStat(cts, false);
            }
        });
    }

    public void addSoulMPFromMobDeath() {
        if(hasStat(CharacterTemporaryStat.SoulMP)) {
            Option o = getOption(SoulMP);
            o.nOption = Math.min(ItemConstants.MAX_SOUL_CAPACITY, o.nOption + ItemConstants.MOB_DEATH_SOUL_MP_COUNT);
            putCharacterStatValue(SoulMP, o);
            if (o.nOption >= ItemConstants.MAX_SOUL_CAPACITY && !hasStat(FullSoulMP)) {
                Option o2 = new Option();
                o2.rOption = ItemConstants.getSoulSkillFromSoulID(((Equip) chr.getEquippedItemByBodyPart(BodyPart.Weapon)).getSoulOptionId());
                if (o2.rOption == 0) {
                    chr.chatMessage(String.format("Unknown corresponding skill for soul socket id %d!",
                            ((Equip) chr.getEquippedItemByBodyPart(BodyPart.Weapon)).getSoulOptionId()));
                }
                o2.nOption = ItemConstants.MAX_SOUL_CAPACITY;
                o2.xOption = ItemConstants.MAX_SOUL_CAPACITY;
                putCharacterStatValue(FullSoulMP, o2);
            }
            sendSetStatPacket();
        }
    }

    public void putCharacterStatValueFromMobSkill(CharacterTemporaryStat cts, Option o) {
        o.rOption |= o.slv << 16; // mob skills are encoded differently: not an int, but short (skill ID), then short (slv).
        putCharacterStatValue(cts, o);
        Job sourceJobHandler = chr.getJobHandler();
        sourceJobHandler.handleMobDebuffSkill(chr);
    }

    public void removeAllStats() {
        Set<CharacterTemporaryStat> currentStats = new HashSet<>();
        currentStats.addAll(getNewStats().keySet());
        currentStats.addAll(getCurrentStats().keySet());
        currentStats.forEach(stat -> removeStat(stat, false));
    }

    public Map<BaseStat, Integer> getBaseStats() {
        return baseStats;
    }

    public void addBaseStat(BaseStat bs, int value) {
        getBaseStats().put(bs, getBaseStats().getOrDefault(bs, 0) + value);
    }

    public void removeBaseStat(BaseStat bs, int value) {
        addBaseStat(bs, -value);
    }

    public long getTotalNOptionOfStat(CharacterTemporaryStat cts) {
        if (cts.isIndie()) {
            return getOptions(cts).stream().mapToLong(o -> o.nValue).sum();
        } else {
            return getOptions(cts).stream().mapToLong(o -> o.nOption).sum();
        }
    }
}
