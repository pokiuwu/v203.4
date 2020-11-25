package net.swordie.ms.world.field.fieldeffect;

/**
 * Created on 3/26/2018.
 */
public enum FieldEffectType {
    Summon(0),
    Tremble(1),
    ObjectStateByString(2),
    DisableEffectObject(3),
    Screen(4),
    PlaySound(5),
    MobHPTag(6),
    ChangeBGM(7),
    BGMVolumeOnly(8),
    SetBGMVolume(9),
    // 10
    // 11
    // 12
    // 13
    // 14
    RewardRoulette(15),
    TopScreen(16),
    BackScreen(17),
    TopScreenEffect(18),
    ScreenEffect(19),
    ScreenFloatingEffect(20),
    Blind(21),
    SetGrey(22),
    OnOffLayer(23),
    OverlapScreen(24),
    OverlapScreenDetail(25),
    RemoveOverlapScreen(26),
    ChangeColor(27),
    StageClearExpOnly(28),
    TopScreen_WithOrigin(29),
    SpineScreen(30),
    OffSpineScreen(31),


    ;

    private byte val;

    FieldEffectType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
