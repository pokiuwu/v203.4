package net.swordie.ms.enums;

import net.swordie.ms.client.jobs.flora.Ark;

import java.util.Arrays;

/**
 * Created on 1/7/2018.
 */
public enum ForceAtomEnum {
    DEMON_SLAYER_FURY_1(0,2), //Fury 1st ~ 3rd Job
    DEMON_SLAYER_FURY_2(0,4), //Fury 4th Job
    DEMON_SLAYER_FURY_1_BOSS(0,6), //Fury from Bosses (1st ~ 3rd Job)
    DEMON_SLAYER_FURY_2_BOSS(0,10), //Fury from Bosses (4th Job)
    PHANTOM_CARD_1(1, 1), //Phantom - Carte Blanch (2nd Job)
    PHANTOM_CARD_2(1, 2), //Phantom - Carte Noir (4th Job)
    KAISER_WEAPON_THROW_1(2, 1), //Kaiser 3 Swords (2nd Job)
    KAISER_WEAPON_THROW_2(2, 2), //Kaiser 5 Swords (4th Job)
    KAISER_WEAPON_THROW_MORPH_1(2, 3), //Kaiser 3 Swords Final Form
    KAISER_WEAPON_THROW_MORPH_2(2, 4), //Kaiser 5 Swords Final Form
    AB_ORB(3, 1), //AB - Soul Seeker
    DA_ORB(3, 2), //Megiddo Flame (mage FP Lv170 Hyper)
    NETHER_SHIELD(3, 3), //Nether Shield
    RABBIT_ORB(3, 4), // Shade
    FLAMING_RABBIT_ORB(3, 5), // same for 4, but insta disappear (byMob?)
    AB_ORB_RECREATION(4, 1), //AB - Soul Seeker - Recreation
    DA_ORB_RECREATION(4, 2),
    NETHER_SHIELD_RECREATION(4, 3),
    RABBIT_ORB_RECREATION(4, 4),
    FLAMING_RABBIT_ORB_RECREATION(4, 5),
    XENON_ROCKET_1(5, 1), //Xenon Aegis System Rockets
    XENON_ROCKET_2(5, 2), //Xenon Aegis System Rockets
    XENON_ROCKET_3(6, 1), //Xenon Pinpoint Salvo
    WA_ARROW_1(7, 1), //WA Green Arrow
    WA_ARROW_2(7, 2), //WA Purple Arrow
    WA_ARROW_HYPER(8, 1), //WA Hyper Arrow
    KANNA_ORB_1(9, 1), // to char       isMobStartForceAtom
    KANNA_ORB_2(9, 2), // to char       isMobStartForceAtom
    BM_ARROW(10, 1), //Magic Arrow from Quiver Cartridge
    ASSASSIN_MARK(11, 1), //Assassin's Mark         isMobStartForceAtom
    NIGHTLORD_MARK(11, 2), //Night Lord's Mark      isMobStartForceAtom
    FLYING_MESO(12, 1), //Flying Meso - Shadower's Meso Explosion
    BLUE_RABBIT_ORB(13, 1), //Shade 2nd Job
    RED_RABBIT_ORB(13, 2), //Shade 4th Job upgrade
    YELLOW_ORB_TO_SELF(14, 1), //Looks similar to Demon Slayer's Fury Orbs but it's yellow instead of blue      isMobStartForceAtom
    NIGHT_WALKER_BAT(15, 1), //Night Walker Bat  from Mob?
    NIGHT_WALKER_BAT_4(15, 2), //Night Walker Bats(4th)  from Mob?
    NIGHT_WALKER_FROM_MOB(16, 1), //Night Walker Bat                isMobStartForceAtom
    NIGHT_WALKER_FROM_MOB_4(16, 2), //Night Walker Bats (4th Job)   isMobStartForceAtom
    ORBITAL_FLAME_1(17, 1), //Blaze Wizard(1)
    ORBITAL_FLAME_3(17, 2), //Blaze Wizard(3)
    ORBITAL_FLAME_2(17, 3), //Blaze Wizard(2)
    ORBITAL_FLAME_4(17, 4), //Blaze Wizard(4)
    STAR_1(18, 1), //Star (white/blue interior) - Star Planet?
    STAR_2(18, 2), //Star (purple) - Star Planet?
    KINESIS_ORB(18, 3), // ?
    KINESIS_SMALL_ORB(18, 4), // ?
    YELLOW_BLACK_ORB(18, 6), //Looks like the Soul orbs from having a Soul Enchanted weapon but these are different colour and bigger
    PURPLE_BLACK_ORB(18, 10), //Looks like the Soul orbs from having a Soul Enchanted weapon but these are different colour and bigger
    MECH_ROCKET(19, 1),
    MECH_MEGA_ROCKET_1(20, 1),
    MECH_MEGA_ROCKET_2(20, 2),
    KINESIS_ORB_REAL(22, 1),
    WRECKAGE(23, 1), //Evan's Magic Debris
    ADV_WRECKAGE(24, 1), //Evan's Advanced Magic Debris
    TRANSPARENT_AB_ORB(25, 1), // same for 26, but that disappears
    TRANSPARENT_AB_ORB_RECREATION(26,1),
    UNK27(27, 1),
    UNK28(28, 1),
    GREEN_STAR(29, 1),
    BLUE_STAR(29, 2),
    YELLOW_STAR(29, 3),
    UNK30(30, 1),
    UNK31(31, 1),
    UNK32(32, 1),
    UNK33(33, 1),
    UNK34(34, 1),
    UNK35(35, 1),
    UNK36(36, 1),
    UNK37(37, 1),
    UNK38(38, 1),
    UNK39(39, 1),
    UNK40(40, 1),
    UNK41(41, 1),
    UNK42(42, 1),
    BASIC_CHARGE(43, 1),
    SCARLET_CHARGE(44, 1),
    GUST_CHARGE(45, 1),
    ABYSSAL_CHARGE(46, 1),
    UNK47(47, 1),
    UNK48(48, 1),
    UNK49(49, 1),
    UNK50(50, 1),
    UNK51(51, 1),
    // unks
    ;

    private int forceAtomType;
    private int inc;

    ForceAtomEnum(int forceAtomType, int inc) {
        this.forceAtomType = forceAtomType;
        this.inc = inc;
    }

    public int getForceAtomType() {
        return forceAtomType;
    }

    public void setForceAtomType(int forceAtomType) {
        this.forceAtomType = forceAtomType;
    }

    public int getInc() {
        return inc;
    }

    public void setInc(int inc) {
        this.inc = inc;
    }

    public static ForceAtomEnum getByVal(int val) {
        return Arrays.stream(values()).filter(fae -> fae.getForceAtomType() == val).findAny().orElse(null);
    }
}
