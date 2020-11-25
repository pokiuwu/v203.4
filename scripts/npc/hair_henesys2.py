# Natalie - Henesys Hair Salon
# Male: Aran cut, Catalyst, Evan hair (m), gaga hair, shaggy wax, the coco, the mo rawk
# Female: Dual blade, front braid, grace, hime, laguna beach, lively wave, long with bangs, wavy bob, wavy ponytail

options = []

al = chr.getAvatarData().getAvatarLook()
hairColour = al.getHair() % 10
options = [0, 1, 2, 3, 4, 5, 9, 10, 11, 12, 13, 15, 16]
options = list(map(lambda x: x + hairColour, options))
answer = sm.sendAskAvatar("Choose your new hairstyle!", False, False, options)

if answer < len(options):
    sm.changeCharacterLook(options[answer])
#    sm.changeChannelAndWarp(chr.getClient().getChannelInstance().getChannelId(), chr.getFieldID());
