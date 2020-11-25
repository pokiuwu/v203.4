package net.swordie.ms.life.npc;

/**
 * Created on 2/19/2018.
 */
public enum NpcMessageType {
    SayOk(0, false, false, ResponseType.Response),// v202.3
    SayNext(0, false, true, ResponseType.Response),// v202.3
    SayPrev(0, true, false, ResponseType.Response),// v202.3
    Say(0, true, true, ResponseType.Response),// v202.3
    SayOk_2(1, false, false, ResponseType.Response),// v202.3
    SayNext_2(1, false, true, ResponseType.Response),// v202.3
    SayPrev_2(1, true, false, ResponseType.Response),// v202.3
    Say_2(1, true, true, ResponseType.Response),// v202.3
    SayImage(2, ResponseType.Response),// v202.3
    AskYesNo(3, ResponseType.Response),// v202.3
    AskText(4, ResponseType.Text),// v202.3
    AskNumber(5, ResponseType.Answer),// v202.3
    AskMenu(6, ResponseType.Answer),// v202.3
    InitialQuiz(7, ResponseType.Answer),// v202.3
    InitialSpeedQuiz(8, ResponseType.Answer),// v202.3
    ICQuiz(9, ResponseType.Answer),// v202.3
    AskAvatar(10, ResponseType.Answer),// v202.3
    AskAndroid(11, ResponseType.Answer),// v202.3
    // 12
    AskPet(13, ResponseType.Answer),// v202.3
    AskPetAll(14, ResponseType.Answer),// v202.3
    AskActionPetEvolution(15, ResponseType.Answer),// v202.3
    Script(16, ResponseType.Answer),
    AskAccept(17, ResponseType.Response),// v202.3
    // 18
    AskBoxtext(19, ResponseType.Answer),// v202.3
    AskSlideMenu(20, ResponseType.Answer),// v202.3
    AskIngameDirection(21, ResponseType.Response),// v202.3
    // 22
    PlayMovieClip(23, ResponseType.Response),// v202.3
    AskCenter(24, ResponseType.Answer),// v202.3
    AskAvatar2(25, ResponseType.Answer),// v202.3
    AskSelectMenu(26, ResponseType.Answer),// v202.3
    AskAngelicBuster(27, ResponseType.Answer),// v202.3
    SayIllustration(28, true, true, ResponseType.Response),// v202.3
    SayIllustrationOk(28, false, false, ResponseType.Response),// v202.3
    SayIllustrationNext(28, false, true, ResponseType.Response),// v202.3
    SayIllustrationPrev(28, true, false, ResponseType.Response),// v202.3
    SayDualIllustration(29, ResponseType.Answer),// v202.3
    AskYesNoIllustration(30, ResponseType.Answer),// v202.3
    AskAcceptIllustration(31, ResponseType.Answer),// v202.3
    AskMenuIllustration(32, ResponseType.Answer),// v202.3
    AskYesNoDualIllustration(33, ResponseType.Answer),// v202.3
    AskAcceptDualIllustration(34, ResponseType.Answer),// v202.3
    AskMenuDualIllustration(35, ResponseType.Answer),// v202.3
    AskSNN2(36, ResponseType.Answer),// v202.3
    AskAvatarZero(37, ResponseType.Answer),// v202.3
    // 38
    // 39
    Monologue(40, ResponseType.Response),// v202.3
    AskWeaponBox(41, ResponseType.Answer),// v202.3
    AskBoxTextBgImg(42, ResponseType.Answer),// v202.3
    AskUserSurvey(43, ResponseType.Answer),// v202.3
    SuccessCamera(44, ResponseType.Answer),// v202.3
    AskMixHair(45, ResponseType.Answer),// v202.3
    AskMixHairExZero(46, ResponseType.Answer),// v202.3
    OnAskCustomMixHair(47, ResponseType.Answer),// v202.3
    OnAskCustomMixHairAndProb(48, ResponseType.Answer),// v202.3
    OnAskMixHairNew(49, ResponseType.Answer),// v202.3
    OnAskMixHairNewExZero(50, ResponseType.Answer),// v202.3
    NpcAction(51, ResponseType.Answer),// v202.3
    OnAskScreenShinningStarMsg(52, ResponseType.Answer),// v202.3
    InputUI(53, ResponseType.Answer),
    // 54 ~ or inputui :P
    OnAskNumberUseKeyPad(55, ResponseType.Answer),// v202.3
    OnSpinOffGuitarRhythmGame(56, ResponseType.Answer),// v202.3
    OnGhostParkEnter(57, ResponseType.Answer),// v202.3
    // 64
    // 65
    // 66
    None(-1, ResponseType.Answer),
    ;

    private byte val;
    private boolean prevPossible, nextPossible;
    private int delay;
    private ResponseType responseType;

    NpcMessageType(int val, ResponseType responseType) {
        this.val = (byte) val;
        prevPossible = false;
        nextPossible = false;
        this.responseType = responseType;
    }

    NpcMessageType(int val, boolean prev, boolean next, ResponseType responseType) {
        this.val = (byte) val;
        prevPossible = prev;
        nextPossible = next;
        this.responseType = responseType;
    }

    public byte getVal() {
        return val;
    }

    public boolean isPrevPossible() {
        return prevPossible;
    }

    public boolean isNextPossible() {
        return nextPossible;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }


    public ResponseType getResponseType() {
        return responseType;
    }

    public enum ResponseType {
        Response, Answer, Text
    }
}
