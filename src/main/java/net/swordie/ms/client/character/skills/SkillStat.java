package net.swordie.ms.client.character.skills;

import net.swordie.ms.enums.BaseStat;

/**
 * Created on 12/20/2017.
 */
public enum SkillStat {
    x,
    y,
    z,
    q,
    s,
    u,
    w,
    attackCount,
    rb,
    damage,
    lt,
    mpCon,
    mobCount,
    damAbsorbShieldR,
    pddX,
    mhpR,
    psdJump,
    speedMax,
    stanceProp,
    psdSpeed,
    lv2mhp,
    forceCon,
    cooltime,
    indieAsrR,
    indiePad,
    indieMad,
    indiePdd,
    indieMdd,
    indieTerR,
    indieEva,
    indieEvaR,
    indieAcc,
    indieBooster,
    indieSpeed,
    indieJump,
    range,
    time,
    cooltimeMS,
    subTime,
    strX,
    padX,
    bdR,
    damR,
    ignoreMobpdpR,
    mobCountDamR,
    actionSpeed,
    mastery,
    rb2,
    lt2,
    v,
    terR,
    mddX,
    asrR,
    MDF,
    cr,
    prop,
    ballCount,
    t,
    dotInterval,
    dotTime,
    criticaldamageMin,
    criticaldamageMax,
    dot,
    ballDelay,
    ballDelay1,
    pdR,
    dexX,
    mmpR,
    madR,
    lukX,
    intX,
    hcProp,
    hcCooltime,
    hcTime,
    subProp,
    mp,
    hcHp,
    hp,
    indieCr,
    indieDamR,
    mhpX,
    targetPlus,
    indieMaxDamageOverR,
    indieMaxDamageOver,
    damPlus,
    ar,
    madX,
    selfDestruction,
    pddR,
    mddR,
    speed,
    evaX,
    accX,
    onActive,
    jump,
    summonCount,
    acc,
    eva,
    epdd,
    emdd,
    indieMmp,
    indieMhp,
    pdd,
    mdd,
    bulletCount,
    mdd2pdd,
    lv2mmp,
    indiePddR,
    epad,
    attackDelay,
    mdR,
    hcSubTime,
    mad,
    damageToBoss,
    coolTimeR,
    w2,
    u2,
    s2,
    q2,
    v2,
    mesoR,
    dropR,
    expR,
    indieExp,
    indiePadR,
    indieMadR,
    hcSummonHp,
    er,
    indieMhpR,
    indieBDR,
    ppRecovery,
    ballDelay0,
    ballDelay2,
    bulletConsume,
    ignoreMobDamR,
    indieStance,
    dotSuperpos,
    dotTickDamR,
    ppCon,
    ppReq,
    indiePMdR,
    bufftimeR,
    rb3,
    rb4,
    lt3,
    lt4,
    hpCon,
    areaDotCount,
    hcSubProp,
    costmpR,
    MDamageOver,
    variableRect, // null val
    attackPoint, // null val
    property, // null val
    emad,
    ballDelay3,
    emhp,
    mpConReduce,
    indieMmpR,
    indieIgnoreMobpdpR,
    gauge,
    fixdamage,
    hpRCon,
    padR,
    hcReflect,
    reduceForceR,
    timeRemainEffect,
    dex,
    killRecoveryR,
    accR,
    emmp,
    powerCon,
    mmpX,
    epCon,
    kp,
    a,
    ignoreCounter,
    action,
    evaR,
    damageTW3,
    damageTW2,
    damageTW4,
    pad,
    indieAllStat,
    bulletSpeed,
    morph,
    itemConsume,
    nbdR,
    psdIncMaxDam,
    strFX,
    dexFX,
    lukFX,
    intFX,
    pdd2mdd,
    acc2mp,
    eva2hp,
    str2dex,
    dex2str,
    int2luk,
    luk2int,
    luk2dex,
    dex2luk,
    lv2pad,
    lv2mad,
    tdR,
    minionDeathProp,
    abnormalDamR,
    acc2dam,
    pdd2dam,
    mdd2dam,
    pdd2mdx,
    mdd2pdx,
    nocoolProp,
    passivePlus,
    mpConEff,
    lv2damX,
    summonTimeR,
    expLossReduceR,
    onHitHpRecoveryR,
    onHitMpRecoveryR,
    pdr,
    mhp2damX,
    mmp2damX,
    finalAttackDamR,
    guardProp,
    mob, // null val
    extendPrice,
    priceUnit,
    period,
    price,
    reqGuildLevel,
    mileage,
    disCountR,
    pqPointR,
    mesoG,
    itemUpgradeBonusR,
    itemCursedProtectR,
    itemTUCProtectR,
    igpCon,
    gpCon,
    iceGageCon,
    PVPdamage,
    lv2str,
    lv2dex,
    lv2int,
    lv2luk,
    orbCount,
    dotHealHPPerSecondR,
    ballDelay6,
    ballDelay7,
    ballDelay4,
    ballDelay5,
    ballDamage,
    ballAttackCount,
    ballMobCount,
    delay,
    strR,
    dexR,
    intR,
    lukR,
    OnActive,
    PVPdamageX,
    indieMDF,
    soulmpCon,
    prob,
    indieMddR,
    indieDrainHP,
    trembling,
    incMobRateDummy,
    fixCoolTime,
    indieForceSpeed,
    indieForceJump,
    itemCon,
    itemConNo,
    ;

    public static SkillStat getSkillStatByString(String s) {
        for(SkillStat skillStat : SkillStat.values()) {
            if(skillStat.toString().equals(s)) {
                return skillStat;
            }
        }
        return null;
    }

    public BaseStat getBaseStat() {
        switch (this) {
            case pddX:
            case mdd2pdd:
            case pdd:
            case epdd:
            case indiePdd:
                return BaseStat.pdd;
            case pddR:
            case indiePddR:
                return BaseStat.pddR;
            case mddX:
            case pdd2mdd:
            case emdd:
            case mdd:
            case indieMdd:
                return BaseStat.mdd;
            case mddR:
            case indieMddR:
                return BaseStat.mddR;
            case lv2mhp:
                return BaseStat.mhpLv;
            case mhpX:
            case emhp:
            case indieMhp:
            case hcHp:
            case eva2hp:
                return BaseStat.mhp;
            case mhpR:
            case indieMhpR:
                return BaseStat.mhpR;
            case lv2mmp:
                return BaseStat.mmpLv;
            case mmpX:
            case indieMmp:
            case emmp:
                return BaseStat.mmp;
            case mmpR:
            case indieMmpR:
                return BaseStat.mmpR;
            case psdSpeed:
            case speed:
            case speedMax:
            case indieForceSpeed:
            case indieSpeed:
                return BaseStat.speed;
            case psdJump:
            case indieForceJump:
            case indieJump:
            case jump:
                return BaseStat.jump;
            case stanceProp:
            case indieStance:
                return BaseStat.stance;
            case asrR:
            case indieAsrR:
                return BaseStat.asr;
            case pad:
            case padX:
            case indiePad:
            case epad:
                return BaseStat.pad;
            case lv2pad:
                return BaseStat.padLv;
            case padR:
            case indiePadR:
                return BaseStat.padR;
            case indieMad:
            case mad:
            case madX:
            case emad:
                return BaseStat.mad;
            case lv2mad:
                return BaseStat.madLv;
            case madR:
            case indieMadR:
                return BaseStat.madR;
            case indieTerR:
            case terR:
                return BaseStat.ter;
            case indieEva:
            case eva:
                return BaseStat.eva;
            case indieEvaR:
            case evaR:
                return BaseStat.evaR;
            case indieBooster:
                return BaseStat.booster;
            case mastery:
                return BaseStat.mastery;
            case strFX:
            case strX:
            case dex2str:
                return BaseStat.str;
            case lv2str:
                return BaseStat.strLv;
            case strR:
                return BaseStat.strR;
            case dex:
            case dexFX:
            case dexX:
            case luk2dex:
            case str2dex:
                return BaseStat.dex;
            case lv2dex:
                return BaseStat.dexLv;
            case dexR:
                return BaseStat.dexR;
            case intFX:
            case intX:
            case luk2int:
                return BaseStat.inte;
            case lv2int:
                return BaseStat.intLv;
            case intR:
                return BaseStat.intR;
            case lukFX:
            case lukX:
            case dex2luk:
            case int2luk:
                return BaseStat.luk;
            case lv2luk:
                return BaseStat.lukLv;
            case lukR:
                return BaseStat.lukR;
            case bdR:
            case indieBDR:
            case nbdR:
                return BaseStat.bd;
            case ignoreMobpdpR:
            case indieIgnoreMobpdpR:
                return BaseStat.ied;
            case indieAllStat:
                return BaseStat.allStat;
            case criticaldamageMin:
                return BaseStat.minCd;
            case criticaldamageMax:
                return BaseStat.maxCd;
            case cr:
            case indieCr:
                return BaseStat.cr;
            case expR:
            case indieExp:
                return BaseStat.expR;
            case dropR:
                return BaseStat.dropR;
            case mesoR:
            case mesoG:
                return BaseStat.mesoR;
            case hp:
                return BaseStat.hpRecovery;
            case mp:
                return BaseStat.mpRecovery;
            case bufftimeR:
                return BaseStat.buffTimeR;
        }
        return null;
    }
}
