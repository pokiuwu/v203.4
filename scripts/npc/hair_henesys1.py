# Natalie - Henesys Hair Salon
# Male: Aran cut, Catalyst, Evan hair (m), gaga hair, shaggy wax, the coco, the mo rawk
# Female: Dual blade, front braid, grace, hime, laguna beach, lively wave, long with bangs, wavy bob, wavy ponytail

options = []

al = chr.getAvatarData().getAvatarLook()
hairColour = al.getHair() % 10
if al.getGender() == 0: # Male
    options = [33040, 30060, 32350, 33170, 30210, 33100, 30610]
else: # Female
    options = [48830, 48760, 48740, 48730, 48650, 48640, 48630, 48610, 48600, 48550, 48540, 48530, 48520, 48510, 48470, 48390, 48370, 48360, 48350, 48330, 48070, 48050, 48020, 47540, 47530,
    47520, 47400, 47390, 47350, 47280, 47270, 47170, 47160, 48100, 47090, 47080, 47050, 47040, 47030, 47020, 47010, 44950, 44940, 44930, 44900, 44830, 44790, 44610, 44510, 44500,
44490, 44480, 44470, 44430, 44400, 44330, 44310, 44120, 44010, 44000, 41950, 41890, 41860, 41850, 41770, 41750, 41720, 41700, 41640, 41520, 41340, 41160, 39360, 39350, 39340,
39190]
options = list(map(lambda x: x + hairColour, options))
answer = sm.sendAskAvatar("Choose your new hairstyle!", False, False, options)

if answer < len(options):
    sm.changeCharacterLook(options[answer])
    #sm.changeChannelAndWarp(chr.getClient().getChannelInstance().getChannelId(), chr.getFieldID());
