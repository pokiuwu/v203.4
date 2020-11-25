# Alicia's Soul | Tower of Oz (992000000)
CLEVER_LADY_HOTLINE = 2432461

answer = sm.sendNext("How may I help you, little one?\r\n\r\n#b"
                    "#L0#I want to expand my Stupid Pill slots.#l\r\n"
                    "#L1#I lost my Clever Lady Hotline.#l\r\n"
                    "#L2#I don't need any more guidance from you.#l")

if answer == 0:
    sm.sendSayOkay("Will be done in the future")

elif answer == 1:
    if sm.hasItem(CLEVER_LADY_HOTLINE):
        sm.sendSayOkay("Sorry, you already have a #bClever Lady Hotline.")

    elif not sm.canHold(CLEVER_LADY_HOTLINE):
        sm.sendSayOkay("Please make sure you have enough inventory space")

    else:
        sm.giveItem(CLEVER_LADY_HOTLINE)
        sm.sendSayOkay("I've given you a new #bClever Lady Hotline#k. Please try not to lose it again.")

else:
    sm.sendSayOkay("Do come to me again if you need any guidance.")