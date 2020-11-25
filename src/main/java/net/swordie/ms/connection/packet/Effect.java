package net.swordie.ms.connection.packet;


import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.jobs.adventurer.Thief;
import net.swordie.ms.client.jobs.adventurer.Warrior;
import net.swordie.ms.client.jobs.legend.Evan;
import net.swordie.ms.client.jobs.legend.Luminous;
import net.swordie.ms.client.jobs.legend.Shade;
import net.swordie.ms.client.jobs.nova.AngelicBuster;
import net.swordie.ms.client.jobs.nova.Kaiser;
import net.swordie.ms.client.jobs.resistance.BattleMage;
import net.swordie.ms.client.jobs.resistance.Demon;
import net.swordie.ms.client.jobs.resistance.WildHunter;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.TextEffectType;
import net.swordie.ms.enums.UserEffectType;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.container.Tuple;
import net.swordie.ms.world.field.Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.swordie.ms.enums.UserEffectType.*;

public class Effect {

    private UserEffectType userEffectType;
    private String string;
    private List<Tuple<Integer, Integer>> list = new ArrayList<>();

    private int arg1;
    private int arg2;
    private int arg3;
    private int arg4;
    private int arg5;
    private int arg6;
    private int arg7;
    private int arg8;
    private int arg9;
    private int arg10;
    private int arg11;

    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(getUserEffectType().getVal());

        switch (getUserEffectType()) {
            case SkillUse:
            case SkillUseBySummoned:
                encodeSkillUse(outPacket); // too long to put here
                break;
            case SkillAffected:
                int skillID = getArg1();
                outPacket.encodeInt(getArg1()); // skill id
                outPacket.encodeByte(getArg2()); // slv
                if (skillID == Demon.RAVEN_STORM || skillID == Shade.DEATH_MARK) {
                    outPacket.encodeInt(getArg3()); // nDelta
                }
                break;
            case SkillAffected_Select:
                outPacket.encodeInt(getArg1());
                outPacket.encodeInt(getArg2());
                outPacket.encodeInt(getArg3());
                outPacket.encodeByte(getArg4());
                outPacket.encodeByte(getArg5());
                break;
            case SkillSpecial:
                outPacket.encodeInt(getArg1()); // skill id
                if (getArg1() == 36110005 || getArg1() == 65101006 || getArg1() == 13121009 || getArg1() == 11121013 || getArg1() == 12100029) {
                    outPacket.encodeInt(0);
                    outPacket.encodeInt(0);
                    outPacket.encodeInt(0);
                } else if(getArg1() == BattleMage.DARK_SHOCK) {
                    outPacket.encodeInt(getArg2()); // char lvl
                    outPacket.encodeByte(getArg3()); // slv
                    outPacket.encodePositionInt(new Position(getArg4(), getArg5())); // Origin Position -  arg4 = x, arg5 = y
                    outPacket.encodePositionInt(new Position(getArg6(), getArg7())); // Destination Position -  arg6 = x, arg7 = y
                } else if (getArg1() == 80002206|| getArg1() == 80000257 || getArg1() == 80000260 || getArg1() == 80002599) {
                    outPacket.encodeInt(0);
                    outPacket.encodeInt(0);
                    outPacket.encodeInt(0);
                }
                break;
            case SkillSpecialAffected:
                outPacket.encodeInt(getArg1()); // skill id
                outPacket.encodeByte(getArg2());// slv
                break;
            case Quest:
                outPacket.encodeByte(getList().size());
                for (Tuple<Integer, Integer> item : getList()) {
                    outPacket.encodeInt(item.getLeft()); // Item ID
                    outPacket.encodeInt(item.getRight()); // Quantity
                }
                if (getList().size() <= 0) outPacket.encodeString("");
                break;
            case TextEffect:
                outPacket.encodeString(getString());
                outPacket.encodeInt(getArg1()); // letter delay
                outPacket.encodeInt(getArg2()); // box duration
                outPacket.encodeInt(getArg3()); // Positioning on Client  ( 4 = middle )

                outPacket.encodeInt(getArg4()); // xPos
                outPacket.encodeInt(getArg5()); // yPos

                outPacket.encodeInt(getArg6()); // Align
                outPacket.encodeInt(getArg7()); // Line space
                outPacket.encodeInt(getArg8()); // Enter type (0 = fade in)
                outPacket.encodeInt(getArg9()); // Leave type?
                outPacket.encodeInt(getArg10()); // Type
                outPacket.encodeString("");
                break;
            case FieldExpItemConsumed:
                outPacket.encodeInt(getArg1()); // Exp Gained
                break;
            case SkillMode:
                outPacket.encodeInt(getArg1()); // Skill ID
                outPacket.encodeInt(getArg2()); // Rotate (?)
                outPacket.encodeInt(getArg3()); // Skip Frame (?)
                break;
            case RobbinsBomb:
                outPacket.encodeByte(getArg1()); // Reset/Delete
                outPacket.encodeInt(getArg2()); // BombCount
                outPacket.encodeByte(getArg3()); // number (unknown)
                break;
            case PetBuff:
            case ResetOnStateForOnOffSkill:
            case SoulStoneUse:
            case ItemLevelUp:
            case ExpItemConsumed:
            case QuestComplete:
            case JobChanged:
            case BuffItemEffect:
            case Resist:
            case LevelUp:
                break;
            case EffectUOL:
                outPacket.encodeString(getString());
                outPacket.encodeByte(getArg1());
                outPacket.encodeInt(getArg2());
                outPacket.encodeInt(getArg3());
                if (getArg3() == 2) { // item sound
                    outPacket.encodeInt(getArg4()); // nItemID
                }
                break;
            case FadeInOut:
                outPacket.encodeInt(getArg1());// tFadeIn
                outPacket.encodeInt(getArg2());// tDelay
                outPacket.encodeInt(getArg3());// tFadeOut
                outPacket.encodeByte(getArg4()); // nAlpha
                break;
            case LeftMonsterNumber:
                outPacket.encodeInt(getArg1()); // Number on Arrow
                break;
            case JewelCraft:
                outPacket.encodeByte(getArg1()); // Result  0,2 = Success,     5 = Unk error,      Other = Fail
                outPacket.encodeInt(getArg2()); // Item ID
                break;
            case AswanSiegeAttack:
                outPacket.encodeByte(getArg1()); // 0 = Red Colour,     1 = Orange Colour
                break;
            case BlindEffect:
                outPacket.encodeByte(getArg1()); //  true/false
                break;
            case ItemMaker:
                outPacket.encodeInt(getArg1());  // success or failure      0 = Success,  1 = Failure
                break;
            case IncDecHPEffect:
                outPacket.encodeByte(getArg1()); // amount being healed     0 = Miss
                break;
            case AvatarOriented:
                outPacket.encodeString(getString());
                break;
            case ReservedEffect:
                outPacket.encodeByte(getArg1());
                outPacket.encodeInt(getArg2());
                outPacket.encodeInt(getArg3());
                outPacket.encodeString(getString());
                break;
            case PlayExclSoundWithDownBGM:
                outPacket.encodeString(getString());
                outPacket.encodeInt(getArg1());
                break;
            case ReservedEffectRepeat:
                outPacket.encodeString(getString());// effect
                outPacket.encodeByte(1);
                outPacket.encodeByte(getArg1());
                if (getArg1() == 1) {
                    outPacket.encodeByte(1);
                    outPacket.encodeInt(0);
                    outPacket.encodeInt(0);
                }
                break;
            case SpeechBalloon:
                outPacket.encodeByte(getArg1());// bNormal
                outPacket.encodeInt(getArg2());// nRange
                outPacket.encodeInt(getArg3());// nNameHeight
                outPacket.encodeString(getString());// sSpeech
                outPacket.encodeInt(getArg4());// tTime
                outPacket.encodeInt(getArg5());// pOrigin
                outPacket.encodeInt(getArg6());// x
                outPacket.encodeInt(getArg7());// y
                outPacket.encodeInt(getArg8());// z
                outPacket.encodeInt(getArg9());// nLineSpace
                if (getString() != null && !getString().isEmpty()) {
                    outPacket.encodeInt(getArg10());// nTemplateID
                }
                outPacket.encodeInt(getArg11());// nCharID
                break;
        }
    }

    private void encodeSkillUse(OutPacket outPacket) {
        // Arg 1 => skillID
        // Arg 2 => slv
        // Arg 3 => slv
        // Arg 4 => bySummonedID
        // Arg 5 => show/left boolean
        // Arg 6 => mobId
        // Arg 7 => mobPosX
        // Arg 8 => mobPosY
        // Arg 9 => posX
        // Arg 10 => posY
        int skillID = getArg1();
        if (getUserEffectType() == SkillUseBySummoned) {
            outPacket.encodeInt(getArg4()); // Summon ID
        }
        outPacket.encodeInt(skillID); // Skill id
        outPacket.encodeInt(getArg2()); // chr level
        outPacket.encodeByte(getArg3()); // slv
        if (skillID == Evan.DRAGON_FURY) { // Dragon Fury
            outPacket.encodeByte(getArg5()); // bCreate
        } else if (skillID == Warrior.FINAL_PACT) {
            outPacket.encodeByte(getArg5()); // bLoadReincarnationEffect
        } else if (skillID == Thief.CHAINS_OF_HELL) {
            outPacket.encodeByte(getArg5()); // bLeft
            outPacket.encodeInt(getArg6()); // dwMobID
        } else if (skillID == 3211010 || skillID == 3111010 || skillID == 1100012) { // Hooks (Warrior combo fury/archer skills)
            outPacket.encodeByte(getArg5()); // bLeft
            outPacket.encodeInt(getArg6()); // dwMobID
            outPacket.encodeInt(getArg7()); // nMobPosX
            outPacket.encodeInt(getArg8()); // nMobPosY
        } else if (skillID == 64001000 || skillID == 64001007 || skillID == 64001008) {
            outPacket.encodeByte(getArg5());
        } else if (skillID == 64001009 || skillID == 64001010 || skillID == 64001011 || skillID == 64001012) {
            outPacket.encodeByte(getArg5()); // bLeft
            outPacket.encodeInt(getArg6()); // dwMobID
            outPacket.encodeInt(getArg7()); // UNK
            outPacket.encodeInt(getArg8()); // UNK
        } else if (skillID == WildHunter.CALL_OF_THE_HUNTER) {
            outPacket.encodeByte(getArg5()); // bLeft
            outPacket.encodeShort(getArg7()); // nPosX
            outPacket.encodeShort(getArg8()); // nPosY
        } else if (skillID == WildHunter.CAPTURE) {
            outPacket.encodeByte(getArg5()); // nType: 0 = Success, 1 = mob hp too high, 2 = mob cannot be captured
        } else if (skillID == Kaiser.VERTICAL_GRAPPLE || skillID == AngelicBuster.GRAPPLING_HEART || skillID == 400001000) {
            outPacket.encodeInt(getArg5()); // nStartPosY
            outPacket.encodeInt(getArg7()); // ptRopeConnectDest.x
            outPacket.encodeInt(getArg8()); // ptRopeConnectDest.y
        } else if (skillID == Luminous.FLASH_BLINK || skillID == 15001021 || skillID == Shade.FOX_TROT || skillID == 4211016 || skillID == 5081021 || skillID == 400041026 || skillID == 152001004) { // Flash
            outPacket.encodeInt(getArg7()); // ptBlinkLightOrigin.x
            outPacket.encodeInt(getArg8()); // ptBlinkLightOrigin.y
            outPacket.encodeInt(getArg9()); // ptBlinkLightDest.x
            outPacket.encodeInt(getArg10()); // ptBlinkLightDest.y
        } else if (SkillConstants.isSuperNovaSkill(skillID)) {
            outPacket.encodeInt(getArg7()); // ptStartX
            outPacket.encodeInt(getArg8()); // ptStartY
        } else if (skillID == 37110004|| skillID == 37111000 || skillID == 37111003 || skillID == 37110001 || skillID == 37101001 || skillID == 37100002 || skillID == 37000010 || skillID == 37000985) {
            outPacket.encodeInt(getArg5());// unk
        } else if (skillID == 400041019) {
            outPacket.encodeInt(getArg7()); // ptStartX
            outPacket.encodeInt(getArg8()); // ptStartY
        } else if (skillID == 400041009) {
            outPacket.encodeInt(getArg5());// unk
        } else if (skillID == 400041011|| skillID == 400041012 || skillID == 400041013 || skillID == 400041014 || skillID == 400041015) {
            outPacket.encodeInt(getArg5());// unk
        } else if (skillID == 400041036) {
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
        } else if (skillID == 80002393|| skillID == 80002394 || skillID == 80002395 || skillID == 80002421) {
            outPacket.encodeInt(getArg5());// unk
        } else if (skillID == 80001132) {
            outPacket.encodeByte(getArg5());// 0 = sucessfuly catch 1 = failed too high hp 2 = cannot be captured
        } else if (SkillConstants.isUnregisteredSkill(skillID)) {
            outPacket.encodeByte(getArg5()); // bLeft
        }
    }


    public static Effect createFieldTextEffect(String msg, int letterDelay, int showTime, int clientPosition,
                                               Position boxPos, int align, int lineSpace, int type,
                                               int enterType, int leaveType) {
        Effect effect = new Effect();
        effect.setUserEffectType(TextEffect);

        effect.setString(msg);
        effect.setArg1(letterDelay);
        effect.setArg2(showTime);
        effect.setArg3(clientPosition);
        effect.setArg4(boxPos.getX());
        effect.setArg5(boxPos.getY());
        effect.setArg6(align);
        effect.setArg7(lineSpace);
        effect.setArg8(type);
        effect.setArg9(enterType);
        effect.setArg10(leaveType);

        return effect;
    }

    public static Effect createFieldTextEffect(String msg, int letterDelay, int showTime, int clientPosition,
                                               Position boxPos, int align, int lineSpace, TextEffectType type,
                                               int enterType, int leaveType) {
        Effect effect = new Effect();
        effect.setUserEffectType(TextEffect);

        effect.setString(msg);
        effect.setArg1(letterDelay);
        effect.setArg2(showTime);
        effect.setArg3(clientPosition);
        effect.setArg4(boxPos.getX());
        effect.setArg5(boxPos.getY());
        effect.setArg6(align);
        effect.setArg7(lineSpace);
        effect.setArg8(type.getVal());
        effect.setArg9(enterType);
        effect.setArg10(leaveType);

        return effect;
    }

    public static Effect createABRechargeEffect() { // Angelic Buster's Recharge userEffect
        Effect effect = new Effect();
        effect.setUserEffectType(ResetOnStateForOnOffSkill);

        return effect;
    }

    public static Effect fieldItemConsumed(int expGained) { // [exp]EXP+  effect from looting ExpOrbs
        Effect effect = new Effect();
        effect.setUserEffectType(FieldExpItemConsumed);

        effect.setArg1(expGained);

        return effect;
    }

    public static Effect skillModeEffect(int skillID, int mode, int modeStatus) { // Unknown Effect
        Effect effect = new Effect();
        effect.setUserEffectType(SkillMode);

        effect.setArg1(skillID);
        effect.setArg2(mode); //rotate
        effect.setArg3(modeStatus); //skip frame

        return effect;
    }

    public static Effect robbinsBombEffect(boolean reset, int bombCount, byte number) { //Displays bomb with [bombCount] on the Bomb, above user,   Unsure what '[int] number' does
        Effect effect = new Effect();
        effect.setUserEffectType(RobbinsBomb);

        effect.setArg1(reset ? 1 : 0);  // if true, resets/delets bombs
        effect.setArg2(bombCount);      // Number displayed on the Bomb
        effect.setArg3(number);         // unknown

        return effect;
    }

    public static Effect lefMonsterNumberEffect(int count) { //Displays arrow pointing towards the user, with [count] on the arrow,  Maximum count is 5
        Effect effect = new Effect();
        effect.setUserEffectType(LeftMonsterNumber);

        effect.setArg1(count); // number/counter on the arrow

        return effect;
    }

    public static Effect petBuffEffect() { //Displays the PetBuff Effect onto the user
        Effect effect = new Effect();
        effect.setUserEffectType(PetBuff);

        return effect;
    }

    public static Effect jewelCraftResultEffect(byte result, int itemID) { // result = Displays Success/Fail effect above player head with smile/frown respectively, itemID will show itemName in chat
        Effect effect = new Effect();
        effect.setUserEffectType(JewelCraft);

        effect.setArg1(result); // 0, 2  = Success/Smile,       5 = Request denied due to unk error,        Everything else = Fail/Frown
        effect.setArg2(itemID);

        return effect;
    }

    public static Effect azwanSpearEffect(byte colour) { // 0 = red,    1 = orange
        Effect effect = new Effect();
        effect.setUserEffectType(AswanSiegeAttack);

        effect.setArg1(colour);

        return effect;
    }

    public static Effect blindEffect(boolean active) {  // gives User the Blind Status Effect
        Effect effect = new Effect();
        effect.setUserEffectType(BlindEffect);

        effect.setArg1(active ? 1 : 0);

        return effect;
    }

    public static Effect soulStoneUseEffect() { // gives the SoulStone Used Chat
        Effect effect = new Effect();
        effect.setUserEffectType(SoulStoneUse);

        return effect;
    }

    public static Effect expItemConsumedEffect() { // gives exp item consumption effect
        Effect effect = new Effect();
        effect.setUserEffectType(ExpItemConsumed);

        return effect;
    }

    public static Effect itemMakerEffect(boolean success) { // displays itemMaker result
        Effect effect = new Effect();
        effect.setUserEffectType(ItemMaker);

        effect.setArg1(success ? 0 : 1); // 0 = Success,    1 = Failure

        return effect;
    }

    public static Effect itemLevelUpEffect() { //displays the Equipment Level Up effect
        Effect effect = new Effect();
        effect.setUserEffectType(ItemLevelUp);

        return effect;
    }

    public static Effect questCompleteEffect() { //displays the Quest Complete effect
        Effect effect = new Effect();
        effect.setUserEffectType(QuestComplete);

        return effect;
    }

    public static Effect incDecHPEffect(byte amount) { //displays the HP healing  effect
        Effect effect = new Effect();
        effect.setUserEffectType(ItemLevelUp);

        effect.setArg1(amount); // amount shown being healed,  0 = miss

        return effect;
    }

    public static Effect changeJobEffect() { //displays the JobAdvancement Effect
        Effect effect = new Effect();
        effect.setUserEffectType(JobChanged);

        return effect;
    }

    public static Effect buffItemEffect() { //displays the Buff Item Effect
        Effect effect = new Effect();
        effect.setUserEffectType(BuffItemEffect);

        return effect;
    }

    public static Effect resistDamageEffect() { //displays the "Resist" Hit
        Effect effect = new Effect();
        effect.setUserEffectType(Resist);

        return effect;
    }

    public static Effect levelUpEffect() { //displays the Level Up  Effect
        Effect effect = new Effect();
        effect.setUserEffectType(LevelUp);

        return effect;
    }

    public static void showFameGradeUp(Char chr) {
        Field field = chr.getField();
        chr.write(User.effect(Effect.avatarOriented("Effect/BasicEff.img/FameGradeUp/front")));
        chr.write(User.effect(Effect.avatarOriented("Effect/BasicEff.img/FameGradeUp/back")));
        field.broadcastPacket(UserRemote.effect(chr.getId(), Effect.avatarOriented("Effect/BasicEff.img/FameGradeUp/front")));
        field.broadcastPacket(UserRemote.effect(chr.getId(), Effect.avatarOriented("Effect/BasicEff.img/FameGradeUp/back")));
    }

    public static Effect skillUse(int skillID, byte slv, int bySummonedID) {
        Effect effect = new Effect();

        effect.setUserEffectType(bySummonedID == 0 ? SkillUse : SkillUseBySummoned);
        effect.setArg4(bySummonedID);
        effect.setArg1(skillID);
        effect.setArg2(slv);
        effect.setArg3(slv);

        return effect;
    }

    public static Effect skillAffected(int skillID, int slv, int hpGain) {
        Effect effect = new Effect();

        effect.setUserEffectType(SkillAffected);
        effect.setArg1(skillID);
        effect.setArg2(slv);
        effect.setArg3(hpGain);

        return effect;
    }

    public static Effect skillAffectedSelect(int skillID, byte slv, int select, boolean special) {
        Effect effect = new Effect();

        effect.setUserEffectType(SkillAffected_Select);
        effect.setArg1(select);
        effect.setArg2(-1); // root Select  -  (?)
        effect.setArg3(skillID);
        effect.setArg4(slv);
        effect.setArg5(special ? 1 : 0);

        return effect;
    }

    public static Effect skillSpecial(int skillID) {
        Effect effect = new Effect();

        effect.setUserEffectType(SkillSpecial);
        effect.setArg1(skillID);

        return effect;
    }

    public static Effect skillSpecialAffected(int skillID, byte slv) {
        Effect effect = new Effect();

        effect.setUserEffectType(SkillSpecialAffected);
        effect.setArg1(skillID);
        effect.setArg2(slv);

        return effect;
    }

    public static Effect gainQuestItem(int itemID, int quantity) {
        return gainQuestItem(Collections.singletonList(new Tuple<>(itemID, quantity)));
    }

    public static Effect gainQuestItem(List<Tuple<Integer, Integer>> items) {
        Effect effect = new Effect();

        effect.setUserEffectType(Quest);
        effect.setList(items);

        return effect;
    }

    public static Effect showSkillUseEffect(int skillID, byte slv, int bySummonedID, int boolFlag, int mobId, int mobPosX, int mobPosY, int posX, int posY) {
        Effect effect = new Effect();

        effect.setUserEffectType(bySummonedID == 0 ? SkillUse : SkillUseBySummoned);
        effect.setArg4(bySummonedID);
        effect.setArg1(skillID);
        effect.setArg2(slv);
        effect.setArg3(slv);
        effect.setArg5(boolFlag);
        effect.setArg6(mobId);
        effect.setArg7(mobPosX);
        effect.setArg8(mobPosY);
        effect.setArg9(posX);
        effect.setArg10(posY);

        return effect;
    }

    public static Effect showDragonFuryEffect(int skillID, byte slv, int bySummonedID, boolean show) {
        return showSkillUseEffect(skillID, slv, bySummonedID, show ? 1 : 0, 0, 0, 0, 0, 0);
    }

    public static Effect showFinalPactEffect(int skillID, byte slv, int bySummonedID, boolean show) {
        return showSkillUseEffect(skillID, slv, bySummonedID, show ? 1 : 0, 0, 0, 0, 0, 0);
    }

    public static Effect showChainsOfHellEffect(int skillID, byte slv, int bySummonedID, boolean left, int mobId) {
        return showSkillUseEffect(skillID, slv, bySummonedID, left ? 1 : 0, mobId, 0, 0, 0, 0);
    }

    public static Effect showHookEffect(int skillID, byte slv, int bySummonedID, boolean left, int mobId, int mobPosX, int mobPosY) {
        return showSkillUseEffect(skillID, slv, bySummonedID, left ? 1 : 0, mobId, mobPosX, mobPosY, 0, 0);
    }

    public static Effect showCallOfTheHunterEffect(int skillID, byte slv, int bySummonedID, boolean left, int mobPosX, int mobPosY) {
        return showSkillUseEffect(skillID, slv, bySummonedID, left ? 1 : 0, 0, mobPosX, mobPosY, 0, 0);
    }

    public static Effect showCaptureEffect(int skillID, byte slv, int bySummonedID, int type) {
        return showSkillUseEffect(skillID, slv, bySummonedID, type, 0, 0, 0, 0, 0);
    }

    public static Effect showVerticalGrappleEffect(int skillID, byte slv, int bySummonedID, int startPosY, int ropeConnectDestX, int ropeConnectDestY) {
        return showSkillUseEffect(skillID, slv, bySummonedID, startPosY, 0, ropeConnectDestX, ropeConnectDestY, 0, 0);
    }

    public static Effect showFlashBlinkEffect(int skillID, byte slv, int bySummonedID, int blinkOriginX, int blinkOriginY, int blinkDestX, int blinkDestY) {
        return showSkillUseEffect(skillID, slv, bySummonedID, 0, 0, blinkOriginX, blinkOriginY, blinkDestX, blinkDestY);
    }

    public static Effect showSuperNovaEffect(int skillID, byte slv, int bySummonedID, int x, int y) {
        return showSkillUseEffect(skillID, slv, bySummonedID, 0, 0, x, y, 0, 0);
    }

    public static Effect showUnregisteredSkill(int skillID, byte slv, int bySummonedID, boolean left) {
        return showSkillUseEffect(skillID, slv, bySummonedID, left ? 1 : 0, 0, 0, 0, 0, 0);
    }

    public static Effect showDarkShockSkill(int skillId, byte slv, Position origin, Position dest) {
        Effect effect = new Effect();

        effect.setUserEffectType(SkillSpecial);
        effect.setArg1(skillId);
        effect.setArg2(slv);
        effect.setArg3(0); // show effect
        effect.setArg4(origin.getX());
        effect.setArg5(origin.getY());
        effect.setArg6(dest.getX());
        effect.setArg7(dest.getY());

        return effect;
    }

    public static Effect effectFromWZ(String effectPath, boolean flip, int duration, int type, int itemID) {
        Effect effect = new Effect();

        effect.setUserEffectType(EffectUOL);
        effect.setString(effectPath);
        effect.setArg1(flip ? 1 : 0);
        effect.setArg2(duration);
        effect.setArg3(type);
        if (type == 2) {
            effect.setArg4(itemID);
        }

        return effect;
    }

    public static Effect fadeInOut(int fadeIn, int delay, int fadeOut, int alpha) {
        Effect effect = new Effect();

        effect.setUserEffectType(FadeInOut);
        effect.setArg1(fadeIn);
        effect.setArg2(delay);
        effect.setArg3(fadeOut);
        effect.setArg4(alpha);
        return effect;
    }

    public static Effect avatarOriented(String effectPath) {
        Effect effect = new Effect();
        
        effect.setUserEffectType(AvatarOriented);
        effect.setString(effectPath);
        
        return effect;
    }

    public static Effect reservedEffect(String effectPath) {
        Effect effect = new Effect();

        effect.setUserEffectType(ReservedEffect);
        effect.setArg1(0);// bShow
        effect.setArg2(0);
        effect.setArg3(0);
        effect.setString(effectPath);

        return effect;
    }

    public static Effect reservedEffectRepeat(String effectPath, boolean start) {
        Effect effect = new Effect();

        effect.setUserEffectType(ReservedEffectRepeat);
        effect.setString(effectPath);
        effect.setArg1(start ? 1 : 0);
        return effect;
    }

    public static Effect playExclSoundWithDownBGM(String soundPath, int volume) {
        Effect effect = new Effect();

        effect.setUserEffectType(PlayExclSoundWithDownBGM);
        effect.setString(soundPath);
        effect.setArg1(volume);

        return effect;
    }

    public static Effect speechBalloon(boolean normal, int range, int nameHeight, String speech, int time, int origin, int x, int y, int z, int lineSpace, int templateID, int charID) {
        Effect effect = new Effect();
        effect.setUserEffectType(SpeechBalloon);

        effect.setString(speech);
        effect.setArg1(normal ? 1 : 0);
        effect.setArg2(range);
        effect.setArg3(nameHeight);

        effect.setArg4(time);
        effect.setArg5(origin);
        effect.setArg6(x);
        effect.setArg7(y);
        effect.setArg8(z);
        effect.setArg9(lineSpace);
        effect.setArg10(templateID);
        effect.setArg11(charID);
        return effect;
    }

    public void setUserEffectType(UserEffectType userEffectType) {
        this.userEffectType = userEffectType;
    }

    public UserEffectType getUserEffectType() {
        return userEffectType;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setList(List<Tuple<Integer, Integer>> list) { this.list = list; }

    public List<Tuple<Integer, Integer>> getList() { return list; }

    public int getArg1() {
        return arg1;
    }

    public void setArg1(int arg1) {
        this.arg1 = arg1;
    }

    public int getArg2() {
        return arg2;
    }

    public void setArg2(int arg2) {
        this.arg2 = arg2;
    }

    public int getArg3() {
        return arg3;
    }

    public void setArg3(int arg3) {
        this.arg3 = arg3;
    }

    public int getArg4() {
        return arg4;
    }

    public void setArg4(int arg4) {
        this.arg4 = arg4;
    }

    public int getArg5() {
        return arg5;
    }

    public void setArg5(int arg5) {
        this.arg5 = arg5;
    }

    public int getArg6() {
        return arg6;
    }

    public void setArg6(int arg6) {
        this.arg6 = arg6;
    }

    public int getArg7() {
        return arg7;
    }

    public void setArg7(int arg7) {
        this.arg7 = arg7;
    }

    public int getArg8() {
        return arg8;
    }

    public void setArg8(int arg8) {
        this.arg8 = arg8;
    }

    public int getArg9() {
        return arg9;
    }

    public void setArg9(int arg9) {
        this.arg9 = arg9;
    }

    public int getArg10() {
        return arg10;
    }

    public void setArg10(int arg10) {
        this.arg10 = arg10;
    }

    public int getArg11() {
        return arg11;
    }

    public void setArg11(int arg11) {
        this.arg11 = arg11;
    }
}
