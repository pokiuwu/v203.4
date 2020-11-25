package net.swordie.ms.constants;

import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.jobs.Zero;
import net.swordie.ms.client.jobs.adventurer.BeastTamer;
import net.swordie.ms.client.jobs.adventurer.Kinesis;
import net.swordie.ms.client.jobs.adventurer.Magician;
import net.swordie.ms.client.jobs.adventurer.Warrior;
import net.swordie.ms.client.jobs.cygnus.DawnWarrior;
import net.swordie.ms.client.jobs.cygnus.ThunderBreaker;
import net.swordie.ms.client.jobs.legend.Aran;
import net.swordie.ms.client.jobs.legend.Evan;
import net.swordie.ms.client.jobs.legend.Phantom;
import net.swordie.ms.client.jobs.nova.AngelicBuster;
import net.swordie.ms.client.jobs.resistance.Demon;
import net.swordie.ms.loaders.SkillData;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static net.swordie.ms.client.jobs.legend.Aran.*;
import static net.swordie.ms.client.jobs.legend.Mercedes.STAGGERING_STRIKES;
import static net.swordie.ms.client.jobs.legend.Mercedes.STUNNING_STRIKES;
import static net.swordie.ms.client.jobs.nova.AngelicBuster.*;
import static net.swordie.ms.client.jobs.nova.Kaiser.*;
import static net.swordie.ms.client.jobs.resistance.Blaster.*;

/**
 * Created on 12/18/2017.
 */
public class SkillConstants {

    public static final short PASSIVE_HYPER_MIN_LEVEL = 140;
    public static final List<Short> ACTIVE_HYPER_LEVELS = Arrays.asList((short) 150, (short) 170, (short) 200);

    private static final Logger log = Logger.getLogger(SkillConstants.class);

    public static final short LINK_SKILL_1_LEVEL = 70;
    public static final short LINK_SKILL_2_LEVEL = 120;
    public static final short LINK_SKILL_3_LEVEL = 210;

    public static final byte PASSIVE_HYPER_JOB_LEVEL = 6;
    public static final byte ACTIVE_HYPER_JOB_LEVEL = 7;

    public static final int MAKING_SKILL_EXPERT_LEVEL = 10;
    public static final int MAKING_SKILL_MASTER_LEVEL = 11;
    public static final int MAKING_SKILL_MEISTER_LEVEL = 12;


    public static boolean isSkillNeedMasterLevel(int skillId) {
        if (isIgnoreMasterLevel(skillId)
                || (skillId / 1000000 == 92 && (skillId % 10000 == 0))
                || isMakingSkillRecipe(skillId)
                || isCommonSkill(skillId)
                || isNoviceSkill(skillId)
                || isFieldAttackObjSkill(skillId)) {
            return false;
        }
        int job = getSkillRootFromSkill(skillId);
        return skillId != 42120024 && !JobConstants.isBeastTamer((short) job)
                && (isAddedSpDualAndZeroSkill(skillId) || (JobConstants.getJobLevel((short) job) == 4 && !JobConstants.isZero((short) job)));
    }

    public static boolean isAddedSpDualAndZeroSkill(int skillId) {
        if (skillId > 101100101) {
            if (skillId > 101110203) {
                if (skillId == 101120104)
                    return true;
                return skillId == 101120204;
            } else {
                if (skillId == 101110203 || skillId == 101100201 || skillId == 101110102)
                    return true;
                return skillId == 101110200;
            }
        } else {
            if (skillId == 101100101)
                return true;
            if (skillId > 4331002) {
                if (skillId == 4340007 || skillId == 4341004)
                    return true;
                return skillId == 101000101;
            } else {
                if (skillId == 4331002 || skillId == 4311003 || skillId == 4321006)
                    return true;
                return skillId == 4330009;
            }
        }
    }

    public static int getSkillRootFromSkill(int skillId) {
        int prefix = skillId / 10000;
        if (prefix == 8000) {
            prefix = skillId / 100;
        }
        return prefix;
    }

    public static boolean isFieldAttackObjSkill(int skillId) {
        if (skillId <= 0) {
            return false;
        }
        int prefix = skillId / 10000;
        if (skillId / 10000 == 8000) {
            prefix = skillId / 100;
        }
        return prefix == 9500;
    }

    private static boolean isNoviceSkill(int skillId) {
        int prefix  = skillId / 10000;
        if (skillId / 10000 == 8000) {
            prefix = skillId / 100;
        }
        return JobConstants.isBeginnerJob((short) prefix);
    }

    private static boolean isCommonSkill(int skillId) {
        int prefix = skillId / 10000;
        if (skillId / 10000 == 8000) {
            prefix = skillId / 100;
        }
        return (prefix >= 800000 && prefix <= 800099) || prefix == 8001;
    }

    public static boolean isMakingSkillRecipe(int recipeId) {
        boolean result = false;
        if (recipeId / 1000000 != 92 || recipeId % 10000 == 1) {
            int v1 = 10000 * (recipeId / 10000);
            if (v1 / 1000000 == 92 && (v1 % 10000 == 0))
                result = true;
        }
        return result;
    }

    public static boolean isIgnoreMasterLevel(int skillId) {
        switch (skillId) {
            case 1120012:
            case 1320011:
            case 2121009:
            case 2221009:
            case 2321010:
            case 3210015:
            case 4110012:
            case 4210012:
            case 4340009:
            case 5120011:
            case 5120012:
            case 5220012:
            case 5220014:
            case 5320007:
            case 5321004:
            case 5321006:
            case 21120011:
            case 21120014:
            case 21120020:
            case 21120021:
            case 22171069:
            case 23120011:
            case 23120012:
            case 23120013:
            case 23121008:
            case 33120010:
            case 35120014:
            case 80001913:
                return true;
            default:
                return false;
        }
    }

    // updated to v200
    public static boolean isKeyDownSkill(int skillID) {
        return skillID == 1311011 || skillID == 2221011 || skillID == 2221052 || skillID == 2321001 || skillID == 3101008 || skillID == 3111013 || skillID == 3121020 || skillID == 4341002 ||
               skillID == 5081002 || skillID == 5081010 || skillID == 5221004 || skillID == 5311002 || skillID == 5700010 || skillID == 5711021 || skillID == 5721001 || skillID == 5721061 ||
               skillID == 11121052 || skillID == 11121055 || skillID == 12121054 || skillID == 13111020 || skillID == 13121001 || skillID == 14111006 || skillID == 14121004 || skillID == 20041226 ||
               skillID == 21120018 || skillID == 22171083 || skillID == 23121000 || skillID == 24121000 || skillID == 24121005 || skillID == 25111005 || skillID == 25121030 || skillID == 27101202 ||
               skillID == 27111100 || skillID == 30021238 || skillID == 31001000 || skillID == 31101000 || skillID == 31111005 || skillID == 31211001 || skillID == 33121009 || skillID == 33121114 ||
               skillID == 33121214 || skillID == 35121015 || skillID == 36101001 || skillID == 36121000 || skillID == 37121003 || skillID == 37121052 || skillID == 41121001 || skillID == 42121000 ||
               skillID == 60011216 || skillID == 64001000 || skillID == 64001007 || skillID == 64001008 || skillID == 64121002 || skillID == 65121003 || skillID == 80001392 || skillID == 80001587 ||
               skillID == 80001629 || skillID == 80001836 || skillID == 80001887 || skillID == 80002458 || skillID == 80011051 || skillID == 80011362 || skillID == 80011366 || skillID == 80011371 ||
               skillID == 80011381 || skillID == 80011382 || skillID == 80011387 || skillID == 95001001 || skillID == 101110101 || skillID == 101110102 || skillID == 112001008 || skillID == 112110003 ||
               skillID == 112111016 || skillID == 131001004 || skillID == 131001008 || skillID == 131001020 || skillID == 142111010 || skillID == 155121341;
    }

    // updated to v202
    public static final boolean isEvanForceSkill(int skillID) { // is_evan_force_skill
        switch (skillID) {
            case 400021046:
            case 400021012:
            case 80001894:
            case 22141011:
            case 22140022:
            case 22111017:
            case 22111011:
            case 22111012:
            case 22110022:
            case 22110023:
                return true;
        }
        if (skillID > 22141012 && skillID <= 22171063) {
            return true;
        }
        return false;
    }

    // updated to v200
    public static boolean isSuperNovaSkill(int skillID) {
        return skillID == 4221052 || skillID == 65121052;
    }

    // updated to v200
    public static boolean isRushBombSkill(int skillID) {
        switch (skillID) {
            case 42120003:
            case 61111100:
            case 40021186:
            case 22140024:
            case 27121201:
            case 31201001:
            case 5301001:
            case 2221012:
            case 5101012:
            case 5101014:
            case 12121001:
            case 14111022:
            case 80011380:
            case 80011386:
            case 80002300:
            case 61111218:
            case 64101002:
            case 80002247:
            case 400001018:
            case 400031003:
            case 400031004:
            case 101120205:
            case 101120200:
            case 101120203:
                return true;

        }
        return false;
    }

    public static boolean isZeroSkill(int skillID) {
        int prefix = skillID / 10000;
        if(prefix == 8000) {
            prefix = skillID / 100;
        }
        return prefix == 10000 || prefix == 10100 || prefix == 10110 || prefix == 10111 || prefix == 10112;
    }

    // Updated to v203.2
    public static boolean isUsercloneSummonedAbleSkill(int skillID) {
        switch (skillID) {
            case 11101120:
            case 11101121:
            case 11101220:
            case 11101221:
            case 11111120:
            case 11111121:
            case 11111220:
            case 11111221:
            case 11121101:
            case 11121102:
            case 11121103:
            case 14001020:
            case 14101020:
            case 14101021:
            case 14111020:
            case 14111021:
            case 14111022:
            case 14111023:
            case 14121001:
            case 14121002:
            case 14120045:
            case 23001000:
            case 23100004:
            case 23101000:
            case 23101001:
            case 23101007:
            case 23110006:
            case 23111000:
            case 23111001:
            case 23111002:
            case 23111003:
            case 23121000:
            case 23121002:
            case 23121003:
            case 23120013:
            case 23121052:
            case 131001000:
            case 131001001:
            case 131001002:
            case 131001003:
            case 131001004:
            case 131001005:
            case 131001008:
            case 131001010:
            case 131001011:
            case 131001012:
            case 131001013:
            case 131001101:
            case 131001102:
            case 131001103:
            case 131001104:
            case 131001108:
            case 131001113:
            case 131001201:
            case 131001202:
            case 131001203:
            case 131001208:
            case 131001213:
            case 131001313:
            case 131002010:
            case 400031024:
                return true;
        }
        return false;
    }

    public static boolean isNoconsumeUsebulletMeleeAttack(int skillID) {
        return skillID == 14121052 || skillID == 14121003 || skillID == 14000028 || skillID == 14000029;
    }

    // v202.3
    public static boolean isScreenCenterAttackSkill(int skillID) {
        switch (skillID) {
            case 80001431:
            case 80011562:
            case 100001283:
            case 21121057:
            case 13121052:
            case 14121052:
            case 15121052:
                return true;
        }
        return false;
    }

    // v202.3
    public static boolean isSupportAttackSkill(int skillID) {
        return skillID == 80011561 || skillID == 80002463 || skillID == 80001762 || skillID == 80002212;
    }

    // v202.3
    public static boolean isWingedJavelin(int skillID) {
        // Winged Javelin
        // Winged Javelin
        // Abyssal Cast
        return skillID == 152110004 || skillID == 152120016 || skillID == 155121003;
    }

    public static boolean isAranFallingStopSkill(int skillID) {
        switch(skillID) {
            case 21110028:
            case 21120025:
            case 21110026:
            case 21001010:
            case 21000006:
            case 21000007:
            case 21110022:
            case 21110023:
            case 80001925:
            case 80001926:
            case 80001927:
            case 80001936:
            case 80001937:
            case 80001938:
                return true;
            default:
                return false;
        }
    }

    public static boolean isFlipAffectAreaSkill(int skillID) {
        return skillID == 33111013 || skillID == 33121016 || skillID == 33121012 || skillID == 131001207 ||
                skillID == 131001107 || skillID == 4121015 || skillID == 51120057 || skillID == 400001017 ||
                skillID == 400021039 || skillID == 400041041 || skillID == 152121041|| skillID == 400020046 ||
                skillID == 400020051 || skillID == 35121052 ;
    }

    public static boolean isShootSkillNotConsumingBullets(int skillID) {
        int job = skillID / 10000;
        if (skillID / 10000 == 8000) {
            job = skillID / 100;
        }
        switch (skillID) {
            case 80001279:
            case 80001914:
            case 80001915:
            case 80001880:
            case 80001629:
            case 33121052:
            case 33101002:
            case 14101006:
            case 13101020:
            case 1078:
                return true;
            default:
                return getDummyBulletItemIDForJob(job, 0, 0) > 0
                        || isShootSkillNotUsingShootingWeapon(skillID, false)
                        || isFieldAttackObjSkill(skillID);

        }
    }

    private static boolean isShootSkillNotUsingShootingWeapon(int skillID, boolean bySteal) {
        if(bySteal || (skillID >= 80001848 && skillID <= 80001850)) {
            return true;
        }
        switch (skillID) {
            case 80001863:
            case 80001880:
            case 80001914:
            case 80001915:
            case 80001939:
            case 101110204:
            case 101110201:
            case 101000202:
            case 101100202:
            case 80001858:
            case 80001629:
            case 80001829:
            case 80001838:
            case 80001856:
            case 80001587:
            case 80001418:
            case 80001387:
            case 61111215:
            case 80001279:
            case 61001101:
            case 51121008:
            case 51111007:
            case 51001004:
            case 36111010:
            case 36101009:
            case 31111005:
            case 31111006: // ? was 26803624, guessing it's just a +1
            case 31101000:
            case 22110024:
            case 22110014:
            case 21120006:
            case 21100007:
            case 21110027:
            case 21001009:
            case 21000004:
            case 5121013:
            case 1078:
            case 1079:
                return true;
            default:
                return false;

        }
    }

    private static int getDummyBulletItemIDForJob(int job, int subJob, int skillID) {
        if ( job / 100 == 35 )
            return 2333000;
        if ( job / 10 == 53 || job == 501 || (job / 1000) == 0 && subJob == 2 )
            return 2333001;
        if ( JobConstants.isMercedes((short) job) )
            return 2061010;
        if ( JobConstants.isAngelicBuster(job) )
            return 2333001;
        // TODO:
//        if ( !JobConstants.isPhantom((short) job)
//                || !is_useable_stealedskill(skillID)
//                || (result = get_vari_dummy_bullet_by_cane(skillID), result <= 0) )
//        {
//            result = 0;
//        }
        return 0;
    }

    // v202.3
    public static boolean isKeydownSkillRectMoveXY(int skillID) {
        return skillID == 13111020 || skillID == 112111016;
    }

    // v202.3
    public static boolean isShadowAssault(int skillID) {
        return skillID == 400041002 || skillID == 400041003 || skillID == 400041004 || skillID == 400041005;
    }

    // v202.3 - not sure about the name
    public static boolean isPoisonAttackSkill(int skillID) {
        // Ultimate - Mind Over Matter
        // Poison Nova
        // Summon Decoy Bomb
        // Psychic Tornado
        return skillID == 400021053 || skillID == 400021029 || skillID == 64111012 || skillID == 400020009 || skillID == 400020010 || skillID == 400020011;
    }

    // v202.3
    public static boolean isUNKAttackSkill(int skillID) {
        switch (skillID) {
            case 400051003:// Meltdown
            case 400051008:// Cannon of Mass Destruction
            case 400051016:// Shark Torpedo
            case 400041034:// Apocalypse Cannon
            case 400041020:// Shurrikane
            case 400041016:// Lucky Seven
            case 400041017:// Triple Throw
            case 400021078:// Liberated Spirit Circle
            case 400021080:// Liberated Spirit Circle
            case 400021009:// Psychic Tornado
            case 400021010:// Psychic Tornado
            case 400021011:// Psychic Tornado
            case 400021028:// Poison Nova
            case 400021047:// Altar of Annihilation
            case 400021048:// Ultimate - Mind Over Matter
            case 400011004:// Spear of Darkness
            case 152121004:// Longinus Spear
            case 152001002:// Radiant Orb
            case 152120003:// Radiant Orb II
                return true;
        }
        return false;
    }

    public static int getOriginalOfLinkedSkill(int skillID) {
        int result = 0;
        switch(skillID) {
            case 80001040:
                result = 20021110;
                break;
            case 80001140:
                result = 50001214;
                break;
            case 80001155:
                result = 60011219;
                break;
            case 80000378:
                result = 30000077;
                break;
            case 80000334:
                result = 30000075;
                break;
            case 80000335:
                result = 30000076;
                break;
            case 80000369:
                result = 20010294;
                break;
            case 80000370:
                result = 20000297;
                break;
            case 80000333:
                result = 30000074;
                break;
            case 80000000:
                result = 110;
                break;
            case 80000001:
                result = 30010112;
                break;
            case 80000002:
                result = 20030204;
                break;
            case 80000005:
                result = 20040218;
                break;
            case 80000006:
                result = 60000222;
                break;
            case 80000047:
                result = 30020233;
                break;
            case 80000050:
                result = 30010241;
                break;
            case 80000066:
                result = 10000255;
                break;
            case 80000067:
                result = 10000256;
                break;
            case 80000068:
                result = 10000257;
                break;
            case 80000069:
                result = 10000258;
                break;
            case 80000070:
                result = 10000259;
                break;
            case 80000110:
                result = 100000271;
                break;
            case 80000169:
                result = 20050286;
                break;
            case 80000188:
                result = 140000292;
                break;
            case 80000004:
                result = 40020002;
                break;
            case 0:
                result = 0;
                break;
            default:
                log.error("Unknown corresponding link skill for link skill id " + skillID);
        }
        return result;
    }

    public static boolean isZeroAlphaSkill(int skillID) {
        return isZeroSkill(skillID) && skillID % 1000 / 100 == 2;
    }

    public static boolean isZeroBetaSkill(int skillID) {
        return isZeroSkill(skillID) && skillID % 1000 / 100 == 1;
    }

    public static boolean isEquilibriumSkill(int skillID) {
        return skillID >= 20040219 && skillID <= 20040220;
    }

    public static int getAdvancedCountHyperSkill(int skillId) {
        switch(skillId) {
            case 4121013:
                return 4120051;
            case 5321012:
                return 5320051;
            default:
                return 0;
        }
    }

    public static int getAdvancedAttackCountHyperSkill(int skillId) {
        switch(skillId) {
            case 25121005:
                return 25120148;
            case 31121001:
                return 31120050;
            case 31111005:
                return 31120044;
            case 22140023:
                return 22170086;
            case 21120022:
            case 21121015:
            case 21121016:
            case 21121017:
                return 21120066;
            case 21120006:
                return 21120049;
            case 21110020:
            case 21111021:
                return 21120047;
            case 15121002:
                return 15120048;
            case 14121002:
                return 14120045;
            case 15111022:
            case 15120003:
                return 15120045;
            case 51121008:
                return 51120048;
            case 32111003:
                return 32120047;
            case 35121016:
                return 35120051;
            case 37110002:
                return 37120045;
            case 51120057:
                return 51120058;
            case 51121007:
                return 51120051;
            case 65121007:
            case 65121008:
            case 65121101:
                return 65120051;
            case 61121201:
            case 61121100:
                return 61120045;
            case 51121009:
                return 51120058;
            case 13121002:
                return 13120048;
            case 5121016:
            case 5121017:
                return 5120051;
            case 3121015:
                return 3120048;
            case 2121006:
                return 2120048;
            case 2221006:
                return 2220048;
            case 1221011:
                return 1220050;
            case 1120017:
            case 1121008:
                return 1120051;
            case 1221009:
                return 1220048;
            case 4331000:
                return 4340045;
            case 3121020:
                return 3120051;
            case 3221017:
                return 3220048;
            case 4221007:
                return 4220048;
            case 4341009:
                return 4340048;
            case 5121007:
                return 5120048;
            case 5321004:
                return 5320043;
            // if ( nSkillID != &loc_A9B1CF ) nothing done with line 172?
            case 12110028:
            case 12000026:
            case 12100028:
                return 12120045;
            case 12120010:
                return 12120045;
            case 12120011:
                return 12120046;
            default:
                return 0;
        }
    }

    public static boolean isKinesisPsychicLockSkill(int skillId) {
        switch(skillId) {
            case 142120000:
            case 142120001:
            case 142120002:
            case 142120014:
            case 142111002:
            case 142100010:
            case 142110003:
            case 142110015:
                return true;
            default:
                return false;
        }
    }

    public static int getActualSkillIDfromSkillID(int skillID) {
        switch (skillID) {
            case 101120206: //Zero - Severe Storm Break (Tile)
                return 101120204; //Zero - Adv Storm Break

            case 4221016: //Shadower - Assassinate 2
                return 4221014; //Shadower - Assassinate 1

            case 41121020: //Hayato - Tornado Blade-Battoujutsu Link
                return 41121017; //Tornado Blade

            case 41121021: //Hayato - Sudden Strike-Battoujutsu Link
                return 41121018; //Sudden Strike

            case 5121017: //Bucc - Double Blast
                return 5121016; //Bucc - Buccaneer Blast

            case 5101014: //Bucc - Energy Vortex
                return 5101012; //Bucc - Tornado Uppercut

            case 5121020: //Bucc - Octopunch (Max Charge)
                return 5121007; //Bucc - Octopunch

            case 5111013: //Bucc - Hedgehog Buster
                return 5111002; //Bucc - Energy Burst

            case 5111015: //Bucc - Static Thumper
                return 5111012; //Bucc - Static Thumper

            case 31011004: //DA - Exceed Double Slash 2
            case 31011005: //DA - Exceed Double Slash 3
            case 31011006: //DA - Exceed Double Slash 4
            case 31011007: //DA - Exceed Double Slash Purple
                return 31011000; //DA - Exceed Double Slash 1

            case 31201007: //DA - Exceed Demon Strike 2
            case 31201008: //DA - Exceed Demon Strike 3
            case 31201009: //DA - Exceed Demon Strike 4
            case 31201010: //DA - Exceed Demon Strike Purple
                return 31201000; //DA - Exceed Demon Strike 1

            case 31211007: //DA - Exceed Lunar Slash 2
            case 31211008: //DA - Exceed Lunar Slash 3
            case 31211009: //DA - Exceed Lunar Slash 4
            case 31211010: //DA - Exceed Lunar Slash Purple
                return 31211000; //DA - Exceed Lunar Slash 1

            case 31221009: //DA - Exceed Execution 2
            case 31221010: //DA - Exceed Execution 3
            case 31221011: //DA - Exceed Execution 4
            case 31221012: //DA - Exceed Execution Purple
                return 31221000; //DA - Exceed Execution 1

            case 31211002: //DA - Shield Charge (Spikes)
                return 31211011; //DA - Shield Charge (Rush)

            case 61120219: //Kaiser - Dragon Slash (Final Form)
                return 61001000; //Kaiser - Dragon Slash 1

            case 61111215: //Kaiser - Flame Surge (Final Form)
                return 61001101; //Kaiser - Flame Surge

            case 61111216: //Kaiser - Impact Wave (Final Form)
                return 61101100; //Kaiser - Impact Wave

            case 61111217: //Kaiser - Piercing Blaze (Final Form)
                return 61101101; //Kaiser - Piercing Blaze

            case 61111111: //Kaiser - Wing Beat (Final Form)
                return 61111100; //Kaiser - Wing Beat

            case 61111219: //Kaiser - Pressure Chain (Final Form)
                return 61111101; //Kaiser - Pressure Chain

            case 61121201: //Kaiser - Gigas Wave (Final Form)
                return 61121100; //Kaiser - Gigas Wave

            case 61121222: //Kaiser - Inferno Breath (Final Form)
                return 61121105; //Kaiser - Inferno Breath

            case 61121203: //Kaiser - Dragon Barrage (Final Form)
                return 61121102; //Kaiser - Dragon Barrage

            case 61121221: //Kaiser - Blade Burst (Final Form)
                return 61121104; //Kaiser - Blade Burst

            case 14101021: //NW - Quint. Throw Finisher
                return 14101020; //NW - Quint. Throw

            case 14111021: //NW - Quad Throw Finisher
                return 14111020; //NW - Quad Throw

            case 14121002: //NW - Triple Throw Finisher
                return 14121001; //NW - Triple Throw

            case STAGGERING_STRIKES:
                return STUNNING_STRIKES;

            case SMASH_WAVE_COMBO:
                return SMASH_WAVE;

            case FINAL_BLOW_COMBO:
            case FINAL_BLOW_SMASH_SWING_COMBO:
                return FINAL_BLOW;

            case SOUL_SEEKER_ATOM:
                return SOUL_SEEKER;

            case 65101006: //AB - Lovely Sting Explosion
                return LOVELY_STING;

            case 65121007:
            case 65121008:
                return TRINITY;

            case REVOLVING_CANNON_2:
            case REVOLVING_CANNON_3:
                return REVOLVING_CANNON;
            default:
                return skillID;
        }
    }

    public static int getKaiserGaugeIncrementBySkill(int skillID) {
        HashMap<Integer, Integer> hashMapIncrement = new HashMap<>();
        hashMapIncrement.put(DRAGON_SLASH_1, 1);
        hashMapIncrement.put(DRAGON_SLASH_2, 3);
        hashMapIncrement.put(DRAGON_SLASH_3, 4);
        hashMapIncrement.put(DRAGON_SLASH_1_FINAL_FORM, 1);

        hashMapIncrement.put(FLAME_SURGE, 2);
        hashMapIncrement.put(FLAME_SURGE_FINAL_FORM, 2);

        hashMapIncrement.put(IMPACT_WAVE, 5);
        hashMapIncrement.put(IMPACT_WAVE_FINAL_FORM, 0);

        hashMapIncrement.put(PIERCING_BLAZE, 5);
        hashMapIncrement.put(PIERCING_BLAZE_FINAL_FORM, 0);

        hashMapIncrement.put(WING_BEAT, 2);
        hashMapIncrement.put(WING_BEAT_FINAL_FORM, 1);

        hashMapIncrement.put(PRESSURE_CHAIN, 8);
        hashMapIncrement.put(PRESSURE_CHAIN_FINAL_FORM, 0);

        hashMapIncrement.put(GIGA_WAVE, 8);
        hashMapIncrement.put(GIGA_WAVE_FINAL_FORM, 0);

        hashMapIncrement.put(INFERNO_BREATH, 14);
        hashMapIncrement.put(INFERNO_BREATH_FINAL_FORM, 0);

        hashMapIncrement.put(DRAGON_BARRAGE, 6);
        hashMapIncrement.put(DRAGON_BARRAGE_FINAL_FORM, 0);

        hashMapIncrement.put(BLADE_BURST, 6);
        hashMapIncrement.put(BLADE_BURST_FINAL_FORM, 0);

        hashMapIncrement.put(TEMPEST_BLADES_FIVE, 15);
        hashMapIncrement.put(TEMPEST_BLADES_FIVE_FF, 0);

        hashMapIncrement.put(TEMPEST_BLADES_THREE, 15);
        hashMapIncrement.put(TEMPEST_BLADES_THREE_FF, 0);

        return hashMapIncrement.getOrDefault(skillID, 0);
    }

    public static boolean isEvanFusionSkill(int skillID) {
        switch (skillID) {
            case 22110014:
            case 22110025:
            case 22140014:
            case 22140015:
            case 22140024:
            case 22140023:
            case 22170065:
            case 22170066:
            case 22170067:
            case 22170094:
                return true;
            default:
                return false;
        }
    }

    public static boolean isShikigamiHauntingSkill(int skillID) {
        switch(skillID) {
            case 80001850:
            case 42001000:
            case 42001005:
            case 42001006:
            case 40021185:
            case 80011067:
                return true;
            default:
                return false;
        }
    }

    public static boolean isStealableSkill(int skillID) {
        // TODO
        return false;
    }

    public static int getStealSkillManagerTabFromSkill(int skillID) {
        int smJobID;

        //Hyper Skills
        if(skillID % 100 == 54) {
            return 5;
        }
        switch (skillID / 10000) {

            // 1st Job Tab
            case 100:
            case 200:
            case 300:
            case 400:
            case 430:
            case 500:
            case 501:
                return 1;

            // 2nd Job Tab
            case 110:
            case 120:
            case 130:

            case 210:
            case 220:
            case 230:


            case 310:
            case 320:

            case 410:
            case 420:
            case 431:
            case 432:

            case 510:
            case 520:
            case 530:
                return 2;

            // 3rd Job Tab
            case 111:
            case 121:
            case 131:

            case 211:
            case 221:
            case 231:

            case 311:
            case 321:

            case 411:
            case 421:
            case 433:

            case 511:
            case 521:
            case 531:
                return 3;

            // 4th job Tab
            case 112:
            case 122:
            case 132:

            case 212:
            case 222:
            case 232:

            case 312:
            case 322:

            case 412:
            case 422:
            case 434:

            case 512:
            case 522:
            case 532:
                return 4;
        }
        return -1;
    }

    public static int getMaxPosBysmJobID(int smJobID) {
        int maxPos = 0;
        switch (smJobID) {
            case 1:
            case 2:
                maxPos = 3;
                break;
            case 3:
                maxPos = 2;
                break;
            case 4:
            case 5:
                maxPos = 1;
                break;
        }
        return maxPos;
    }

    public static int getStartPosBysmJobID(int smJobID) {
        int startPos = 0;
        switch (smJobID) {
            case 1:
                startPos = 0;
                break;
            case 2:
                startPos = 4;
                break;
            case 3:
                startPos = 8;
                break;
            case 4:
                startPos = 11;
                break;
            case 5:
                startPos = 13;
                break;
        }
        return startPos;
    }

    public static int getImpecSkillIDBysmJobID(int smJobID) {
        int impecSkillID = 0;
        switch (smJobID) {
            case 1:
                impecSkillID = 24001001;
                break;
            case 2:
                impecSkillID = 24101001;
                break;
            case 3:
                impecSkillID = 24111001;
                break;
            case 4:
                impecSkillID = 24121001;
                break;
            case 5:
                impecSkillID = 24121054;
                break;
        }
        return impecSkillID;
    }

    public static int getSMJobIdByImpecSkillId(int impecSkillId) {
        switch (impecSkillId) {
            case 24001001:  // 1st Job
                return 1;
            case 24101001:  // 2nd Job
                return 2;
            case 24111001:  // 3rd job
                return 3;
            case 24121001:  // 4th Job
                return 4;
            case 24121054:  // Hyper Skill
                return 5;
        }
        return -1;
    }

    public static boolean isIceSkill(int skillID) {
        switch (skillID) {
            case Magician.CHILLING_STEP:
            case Magician.COLD_BEAM:
            case Magician.ICE_STRIKE:
            case Magician.GLACIER_CHAIN:
            case Magician.FREEZING_BREATH:
            case Magician.BLIZZARD:
            case Magician.FROZEN_ORB:
            case Magician.ELQUINES:
                return true;

            default:
                return false;
        }
    }

    public static int getLinkSkillByJob(short job) {
        if (JobConstants.isCannonShooter(job)) { // Pirate Blessing
            return 80000000;
        } else if (JobConstants.isCygnusKnight(job)) { // Cygnus Blessing
            return 80000070;
        } else if (JobConstants.isMercedes(job)) { // Elven Blessing
            return 80001040;
        } else if (JobConstants.isDemonSlayer(job)) { // Fury Unleashed
            return 80000001;
        } else if (JobConstants.isDemonAvenger(job)) { // Wild Rage
            return 80000050;
        } else if (JobConstants.isJett(job)) { // Core Aura
            return 80001151;
        } else if (JobConstants.isPhantom(job)) { // Phantom Instinct
            return 80000002;
        } else if (JobConstants.isMihile(job)) { // Knight's Watch
            return 80001140;
        } else if (JobConstants.isLuminous(job)) { // Light Wash
            return 80000005;
        } else if (JobConstants.isAngelicBuster(job)) { // Terms and Conditions
            return 80001155;
        } else if (JobConstants.isHayato(job)) { // Keen Edge
            return 80000003;
        } else if (JobConstants.isKanna(job)) { // Elementalism
            return 80000004;
        } else if (JobConstants.isKaiser(job)) { // Iron Will
            return 80000006;
        } else if (JobConstants.isXenon(job)) { // Hybrid Logic
            return 80000047;
        } else if (JobConstants.isBeastTamer(job)) { // Focus Spirit
            return 80010006;
        }
        return 0;
    }

    public static int getLinkSkillLevelByCharLevel(short level) {
        int res = 0;
        if (level >= LINK_SKILL_3_LEVEL) {
            res = 3;
        } else if (level >= LINK_SKILL_2_LEVEL) {
            res = 2;
        } else if (level >= LINK_SKILL_1_LEVEL) {
            res = 1;
        }
        return res;
    }

    public static int getLinkedSkill(int skillID) {
        switch(skillID) {
            case Zero.STORM_BREAK_INIT:
                return Zero.STORM_BREAK;
            case Zero.ADV_STORM_BREAK_SHOCK_INIT:
                return Zero.ADV_STORM_BREAK;
        }
        return skillID;
    }

    public static boolean isPassiveSkill_NoPsdSkillsCheck(int skillId) {
        SkillInfo si = SkillData.getSkillInfoById(skillId);
        return si != null && si.isPsd();
    }

    public static boolean isPassiveSkill(int skillId) {
        SkillInfo si = SkillData.getSkillInfoById(skillId);
        return si != null && si.isPsd() && si.getPsdSkills().size() == 0;
    }

    public static boolean isHyperstatSkill(int skillID) {
        return skillID >= 80000400 && skillID <= 80000418;
    }

    public static int getTotalHyperStatSpByLevel(short level) {
        int sp = 0;
        for (int i = 140; i <= level; i++) {
            sp += getHyperStatSpByLv(level);
        }
        return sp;
    }

    public static int getHyperStatSpByLv(short level) {
        return 3 + ((level - 140) / 10);
    }

    public static int getNeededSpForHyperStatSkill(int lv) {
        switch (lv) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 8;
            case 5:
                return 10;
            case 6:
                return 15;
            case 7:
                return 20;
            case 8:
                return 25;
            case 9:
                return 30;
            case 10:
                return 35;
            default:
                return 0;
        }
    }

    public static int getTotalNeededSpForHyperStatSkill(int lv) {
        switch (lv) {
            case 1:
                return 1;
            case 2:
                return 3;
            case 3:
                return 7;
            case 4:
                return 15;
            case 5:
                return 25;
            case 6:
                return 40;
            case 7:
                return 60;
            case 8:
                return 85;
            case 9:
                return 115;
            case 10:
                return 150;
            default:
                return 0;
        }
    }

    public static boolean isUnregisteredSkill(int skillID) {
        int prefix = skillID / 10000;
        if (prefix == 8000) {
            prefix = skillID / 100;
        }
        return prefix != 9500 && skillID / 10000000 == 9;
    }

    public static boolean isHomeTeleportSkill(int skillId) {
        switch (skillId) {
            case Warrior.MAPLE_RETURN: // All Adventurers
            case BeastTamer.HOMEWARD_BOUND:
            case Kinesis.RETURN_KINESIS:
            case DawnWarrior.IMPERIAL_RECALL: // All KoC
            case Aran.RETURN_TO_RIEN:
            case Evan.BACK_TO_NATURE:
                // Mercedes
                // Luminous
            case Phantom.TO_THE_SKIES:
                // Shade
            case AngelicBuster.DAY_DREAMER:
                // Kaiser
            case Demon.SECRET_ASSEMBLY: // All Resistance
                // Hayato
                // Kanna
            case Zero.TEMPLE_RECALL:

                return true;
            default:
                return false;
        }
    }

    public static boolean isArmorPiercingSkill(int skillId) {
        switch (skillId) {
            case 3120017:
            case 95001000:
            case 3120008:
            case 3100001:
            case 3100010:
                return false;

            default:
                return true;
        }
    }

    public static int getBaseSpByLevel(short level) {
        return level > 140 ? 0
                : level > 130 ? 6
                : level > 120 ? 5
                : level > 110 ? 4
                : 3;
    }

    public static int getTotalPassiveHyperSpByLevel(short level) {
        return level < 140 ? 0 : (level - 130) / 10;
    }

    public static int getTotalActiveHyperSpByLevel(short level) {
        return level < 140 ? 0 : level < 170 ? 1 : level < 200 ? 2 : 3;
    }

    public static boolean isGuildSkill(int skillID) {
        int prefix = skillID / 10000;
        if (prefix == 8000) {
            prefix = skillID / 100;
        }
        return prefix == 9100;
    }

    public static boolean isGuildContentSkill(int skillID) {
        return (skillID >= 91000007 && skillID <= 91000015) || (skillID >= 91001016 && skillID <= 91001021);
    }

    public static boolean isGuildNoblesseSkill(int skillID) {
        return skillID >= 91001022 && skillID <= 91001024;
    }

    public static boolean isMultiAttackCooldownSkill(int skillID) {
        switch (skillID) {
            case 5311010:
            case 5711021:
            case 22171063:
            case 22141012:
            case ThunderBreaker.GALE:
            case ThunderBreaker.TYPHOON:
            case Demon.DEMON_CRY:
                return true;
        }
        return false;
    }

    public static boolean isMatching(int rootId, int job) {
        boolean matchingStart = job / 100 == rootId / 100;
        boolean matching = matchingStart;
        if (matchingStart && rootId % 100 != 0) {
            // job path must match
            matching = (rootId % 100) / 10 == (job % 100) / 10;
        }
        return matching;
    }

    // is_skill_from_item(signed int nSkillID)
    public static boolean isSkillFromItem(int skillID) {
        switch (skillID) {
            case 80011123: // New Destiny
            case 80011247: // Dawn Shield
            case 80011248: // Dawn Shield
            case 80011249: // Divine Guardian
            case 80011250: // Divine Shield
            case 80011251: // Divine Brilliance
            case 80011261: // Monolith
            case 80011295: // Scouter
            case 80011346: // Ribbit Ring
            case 80011347: // Krrr Ring
            case 80011348: // Rawr Ring
            case 80011349: // Pew Pew Ring
            case 80011475: // Elunarium Power (ATT & M. ATT)
            case 80011476: // Elunarium Power (Skill EXP)
            case 80011477: // Elunarium Power (Boss Damage)
            case 80011478: // Elunarium Power (Ignore Enemy DEF)
            case 80011479: // Elunarium Power (Crit Rate)
            case 80011480: // Elunarium Power (Crit Damage)
            case 80011481: // Elunarium Power (Status Resistance)
            case 80011482: // Elunarium Power (All Stats)
            case 80011492: // Firestarter Ring
            case 80001768: // Rope Lift
            case 80001705: // Rope Lift
            case 80001941: // Scouter
            case 80010040: // Altered Fate
                return true;
        }
        // Tower of Oz skill rings
        return (skillID >= 80001455 && skillID <= 80001479);
    }

    public static int getHyperPassiveSkillSpByLv(int level) {
        // 1 sp per 10 levels, starting at 140, ending at 220
        return level >= 140 && level <= 220 && level % 10 == 0 ? 1 : 0;
    }

    public static int getHyperActiveSkillSpByLv(int level) {
        return level == 150 || level == 170 || level == 200 ? 1 : 0;
    }

    public static int getNoviceSkillRoot(short job) {
        if (job / 100 == 22 || job == 2001) {
            return JobConstants.JobEnum.EVAN_NOOB.getJobId();
        }
        if (job / 100 == 23 || job == 2002) {
            return JobConstants.JobEnum.MERCEDES.getJobId();
        }
        if (job / 100 == 24 || job == 2003) {
            return JobConstants.JobEnum.PHANTOM.getJobId();
        }
        if (JobConstants.isDemon(job)) {
            return JobConstants.JobEnum.DEMON_SLAYER.getJobId();
        }
        if (JobConstants.isMihile(job)) {
            return JobConstants.JobEnum.NAMELESS_WARDEN.getJobId();
        }
        if (JobConstants.isLuminous(job)) {
            return JobConstants.JobEnum.LUMINOUS.getJobId();
        }
        if (JobConstants.isAngelicBuster(job)) {
            return JobConstants.JobEnum.ANGELIC_BUSTER.getJobId();
        }
        if (JobConstants.isXenon(job)) {
            return JobConstants.JobEnum.XENON.getJobId();
        }
        if (JobConstants.isShade(job)) {
            return JobConstants.JobEnum.SHADE.getJobId();
        }
        if (JobConstants.isKinesis(job)) {
            return JobConstants.JobEnum.KINESIS_0.getJobId();
        }
        if (JobConstants.isBlaster(job)) {
            return JobConstants.JobEnum.CITIZEN.getJobId();
        }
        if (JobConstants.isHayato(job)) {
            return JobConstants.JobEnum.HAYATO.getJobId();
        }
        if (JobConstants.isKanna(job)) {
            return JobConstants.JobEnum.KANNA.getJobId();
        }
        return 1000 * (job / 1000);
    }

    public static int getNoviceSkillFromRace(int skillID) {
        if (skillID == 50001215 || skillID == 10001215) {
            return 1005;
        }
        if (isCommonSkill(skillID) || (skillID >= 110001500 && skillID <= 110001504)) {
            return skillID;
        }
        if (isNoviceSkill(skillID)) {
            return skillID % 10000;
        }
        return 0;
    }

    public static int getBuffSkillItem(int buffSkillID) {
        int novice = getNoviceSkillFromRace(buffSkillID);
        switch (novice) {
            // Angelic Blessing
            case 86:
                return 2022746;
            // Dark Angelic Blessing
            case 88:
                return 2022747;
            // Angelic Blessing
            case 91:
                return 2022764;
            // White Angelic Blessing
            case 180:
                return 2022823;
            // Lightning God's Blessing
            case 80000086:
                return 2023189;
            // White Angelic Blessing
            case 80000155:
                return 2022823;
            // Lightning God's Blessing
            case 80010065:
                return 2023189;
            // Goddess' Guard
            case 80011150:
                return 1112932;
        }
        return 0;
    }

    public static String getMakingSkillName(int skillID) {
        switch (skillID) {
            case 92000000:
                return "Herbalism";
            case 92010000:
                return "Mining";
            case 92020000:
                return "Smithing";
            case 92030000:
                return "Accessory Crafting";
            case 92040000:
                return "Alchemy";
        }
        return null;
    }

    public static int recipeCodeToMakingSkillCode(int skillID) {
        return 10000 * (skillID / 10000);
    }

    public static int getNeededProficiency(int level) {
        if (level <= 0 || level >= MAKING_SKILL_EXPERT_LEVEL) {
            return 0;
        }
        return ((100 * level * level) + (level * 400)) / 2;
    }

    public static boolean isSynthesizeRecipe(int recipeID) {
        return isMakingSkillRecipe(recipeID) && recipeID % 10000 == 9001;
    }

    public static boolean isDecompositionRecipeScroll(int recipeID) {
        return isMakingSkillRecipe(recipeID)
                && recipeCodeToMakingSkillCode(recipeID) == 92040000
                && recipeID - 92040000 >= 9003
                && recipeID - 92040000 <= 9006;
    }

    public static boolean isDecompositionRecipeCube(int recipeID) {
        return isMakingSkillRecipe(recipeID) && recipeCodeToMakingSkillCode(recipeID) == 92040000 && recipeID == 92049002;
    }

    public static boolean isDecompositionRecipe(int recipeID) {
        if (isMakingSkillRecipe(recipeID) && recipeCodeToMakingSkillCode(recipeID) == 92040000 && recipeID == 92049000
         || isDecompositionRecipeScroll(recipeID)
         || isDecompositionRecipeScroll(recipeID)) {
            return true;
        }
        return false;
    }

    public static int getFairyBlessingByJob(short job) {
        short beginJob = JobConstants.JobEnum.getJobById(job).getBeginnerJobId();
        // xxxx0012, where xxxx is the "0th" job
        return beginJob * 10000 + 12;
    }

    public static int getEmpressBlessingByJob(short job) {
        short beginJob = JobConstants.JobEnum.getJobById(job).getBeginnerJobId();
        // xxxx0073, where xxxx is the "0th" job
        return beginJob * 10000 + 73;
    }

    public static boolean isLightMageSkill(int skillID) {
        int skillRoot = getSkillRootFromSkill(skillID);
        return skillRoot / 100 == 27 || skillRoot == 2004;
    }

    public static boolean isLarknessDarkSkill(int skillID) {
        return skillID != 20041222 && isLightMageSkill(skillID) && skillID / 100 % 10 == 2;
    }

    public static boolean isLarknessLightSkill(int skillID) {
        return skillID != 20041226 && isLightMageSkill(skillID) && skillID / 100 % 10 == 1;
    }
}
