package net.swordie.ms.client.character.items;


import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.enums.SpecStat;
import net.swordie.ms.enums.Stat;
import net.swordie.ms.loaders.ItemData;

import java.util.Map;

import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

public class ItemBuffs {
    public static void giveItemBuffsFromItemID(Char chr, TemporaryStatManager tsm, int itemID) {
        Map<SpecStat, Integer> specStats = ItemData.getItemInfoByID(itemID).getSpecStats();
        long time = specStats.getOrDefault(SpecStat.time, 0) / 1000;
        for (Map.Entry<SpecStat, Integer> entry : specStats.entrySet()) {
            SpecStat ss = entry.getKey();
            int value = entry.getValue();
            Option o = new Option(-itemID, time);
            o.nOption = value;
            o.nValue = value;
            switch (ss) {
                case hp:
                    chr.heal(value);
                    break;
                case hpR:
                    chr.heal((int) ((value / 100D) * chr.getStat(Stat.mhp)));
                    break;
                case mp:
                    chr.healMP(value);
                    break;
                case mpR:
                    chr.healMP((int) ((value / 100D) * chr.getStat(Stat.mmp)));
                    break;
                case eva:
                    tsm.putCharacterStatValue(EVA, o);
                    break;
                case speed:
                    tsm.putCharacterStatValue(Speed, o);
                    break;
                case pad:
                    tsm.putCharacterStatValue(PAD, o);
                    break;
                case mad:
                    tsm.putCharacterStatValue(MAD, o);
                    break;
                case pdd:
                    tsm.putCharacterStatValue(PDD, o);
                    break;
                case mdd:
                    tsm.putCharacterStatValue(MDD, o);
                    break;
                case acc:
                    tsm.putCharacterStatValue(ACC, o);
                    break;
                case jump:
                    tsm.putCharacterStatValue(Jump, o);
                    break;
                case imhp:
                    tsm.putCharacterStatValue(MaxHP, o);
                    break;
                case immp:
                    tsm.putCharacterStatValue(MaxMP, o);
                    break;
                case indieAllStat:
                    tsm.putCharacterStatValue(IndieAllStat, o);
                    break;
                case indieSpeed:
                    tsm.putCharacterStatValue(IndieSpeed, o);
                    break;
                case indieJump:
                    tsm.putCharacterStatValue(IndieJump, o);
                    break;
                case indieSTR:
                    tsm.putCharacterStatValue(IndieSTR, o);
                    break;
                case indieDEX:
                    tsm.putCharacterStatValue(IndieDEX, o);
                    break;
                case indieINT:
                    tsm.putCharacterStatValue(IndieINT, o);
                    break;
                case indieLUK:
                    tsm.putCharacterStatValue(IndieLUK, o);
                    break;
                case indiePad:
                    tsm.putCharacterStatValue(IndiePAD, o);
                    break;
                case indiePdd:
                    tsm.putCharacterStatValue(IndiePDD, o);
                    break;
                case indieMad:
                    tsm.putCharacterStatValue(IndieMAD, o);
                    break;
                case indieMdd:
                    tsm.putCharacterStatValue(IndieMDD, o);
                    break;
                case indieBDR:
                    tsm.putCharacterStatValue(IndieBDR, o);
                    break;
                case indieIgnoreMobpdpR:
                    tsm.putCharacterStatValue(IndieIgnoreMobpdpR, o);
                    break;
                case indieStatR:
                    tsm.putCharacterStatValue(IndieStatR, o);
                    break;
                case indieMhp:
                    tsm.putCharacterStatValue(IndieMHP, o);
                    break;
                case indieMmp:
                    tsm.putCharacterStatValue(IndieMMP, o);
                    break;
                case indieBooster:
                    tsm.putCharacterStatValue(IndieBooster, o);
                    break;
                case indieAcc:
                    tsm.putCharacterStatValue(IndieACC, o);
                    break;
                case indieEva:
                    tsm.putCharacterStatValue(IndieEVA, o);
                    break;
                case indieAllSkill:
                    tsm.putCharacterStatValue(CombatOrders, o);
                    break;
                case indieMhpR:
                    tsm.putCharacterStatValue(IndieMHPR, o);
                    break;
                case indieMmpR:
                    tsm.putCharacterStatValue(IndieMMPR, o);
                    break;
                case indieStance:
                    tsm.putCharacterStatValue(IndieStance, o);
                    break;
                case indieForceSpeed:
                    tsm.putCharacterStatValue(IndieForceSpeed, o);
                    break;
                case indieForceJump:
                    tsm.putCharacterStatValue(IndieForceJump, o);
                    break;
                case indieQrPointTerm:
                    tsm.putCharacterStatValue(IndieQrPointTerm, o);
                    break;
                case indieWaterSmashBuff:
                    tsm.putCharacterStatValue(IndieUnk1, o);
                    break;
                case padRate:
                    tsm.putCharacterStatValue(IndiePADR, o);
                    break;
                case madRate:
                    tsm.putCharacterStatValue(IndieMADR, o);
                    break;
                case pddRate:
                    tsm.putCharacterStatValue(IndiePDDR, o);
                    break;
                case mddRate:
                    tsm.putCharacterStatValue(IndieMDDR, o);
                    break;
                case accRate:
                    tsm.putCharacterStatValue(ACCR, o);
                    break;
                case evaRate:
                    tsm.putCharacterStatValue(EVAR, o);
                    break;
                case mhpR:
                case mhpRRate:
                    tsm.putCharacterStatValue(IndieMHPR, o);
                    break;
                case mmpR:
                case mmpRRate:
                    tsm.putCharacterStatValue(IndieMHPR, o);
                    break;
                case booster:
                    tsm.putCharacterStatValue(Booster, o);
                    break;
                case expinc:
                    tsm.putCharacterStatValue(ExpBuffRate, o);
                    break;
                case str:
                    tsm.putCharacterStatValue(STR, o);
                    break;
                case dex:
                    tsm.putCharacterStatValue(DEX, o);
                    break;
                case inte:
                    tsm.putCharacterStatValue(INT, o);
                    break;
                case luk:
                    tsm.putCharacterStatValue(LUK, o);
                    break;
                case asrR:
                    tsm.putCharacterStatValue(AsrRByItem, o);
                    break;
                case bdR:
                    tsm.putCharacterStatValue(BdR, o);
                    break;
                case prob:
                    tsm.putCharacterStatValue(ItemUpByItem, o);
                    tsm.putCharacterStatValue(MesoUpByItem, o);
                    break;
                case inflation:
                    tsm.putCharacterStatValue(Inflation, o);
                    break;
                case morph:
                    tsm.putCharacterStatValue(Morph, o);
                    break;
                case repeatEffect:
                    tsm.putCharacterStatValue(RepeatEffect, o);
                    break;
            }
        }
        tsm.sendSetStatPacket();
    }
}
