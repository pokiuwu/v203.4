package net.swordie.ms.enums;

/**
 *
 * @author Five
 */
public enum DBChar {
    Character(0x1),// correct v200
    Money(0x2),// correct v200
    ItemSlotEquip(0x4),// correct v200
    ItemSlotConsume(0x8),// correct v200
    ItemSlotInstall(0x10),
    ItemSlotEtc(0x20),
    ItemSlotCash(0x40),
    InventorySize(0x80),
    SkillRecord(0x100),
    QuestRecord(0x200),
    MinigameRecord(0x400),
    ZeroInfo(0x800),
    CoupleRecord(0x800),
    MapTransfer(0x1000),
    Avatar(0x2000),
    QuestComplete(0x4000),
    SkillCooltime(0x8000),// v200
    MonsterBookCard(0x10000),
    MonsterBookCover(0x20000),
    QuestRecordEx(0x40000),
    NewYearCard(0x80000),
    AdminShopCount(0x100000),
    EquipExt(0x100000),
    WildHunterInfo(0x200000),
    QuestCompleteOld(0x400000),
    Familiar(0x800000),
    ItemPot(0x800000), // correct v200
    CoreAura(0x1000000),// correct v200
    UNK6(0x1000000),
    ExpConsumeItem(0x2000000),// correct v200
    RedLeafInfo(0x2000000),
    ShopBuyLimit(0x40000000),// correct v200
    VisitorLog4(0x8000000),
    ChosenSkills(0x10000000),
    StolenSkills(0x20000000),
    Unsure(0x40000000),
    CharacterPotentialSkill(0x80000000L),
    ReturnEffectInfo(0x400000000L),
    DressUpInfo(0x800000000L),
    CoreInfo(0x1000000000L),
    FarmPotential(0x2000000000L),
    FarmUserInfo(0x4000000000L),
    MemorialCubeInfo(0x8000000000L),
    LikePoint(0x40000000000L),
    RunnerGameRecord(0x2000000000000L),
    MonsterCollection(0x4000000000000L),
    Unk3(0x10000000000L),
    Unk4(0x100000000000L),
    Unk5(0x800000),
    Unk(0x200000000000L),
    MonsterBattleInfo(0x800000000000L),
    SoulCollection(0x1000000000000L),
    UNK7(0x8000000000000L),
    UNK8(0x10000000000000L),
    VMatrixRecord(0x20000000000000L),
    All(0xFFFFFFFFFFFFFFFFL),
    ItemSlot(0x7C);

    public long uFlag;

    DBChar(long uFlag) {
        this.uFlag = uFlag;
    }

    public long get() {
        return uFlag;
    }

    public boolean isInMask(long mask){
        return (mask & get()) != 0;
    }

    public boolean isInMask(DBChar mask){
        return (mask.get() & get()) != 0;
    }
}
