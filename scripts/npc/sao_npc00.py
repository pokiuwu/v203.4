options = []

al = chr.getAvatarData().getAvatarLook()
faceColour = al.getFace() % 1
if al.getGender() == 0: # Male
    options = [33040, 30060, 32350, 33170, 30210, 33100, 30610]
else: # Female
    options = [26896, 26003, 26803, 24079]
options = list(map(lambda x: x + faceColour, options))
answer = sm.sendAskAvatar("Choose your new hairstyle!", False, False, options)

if answer < len(options):
    sm.changeCharacterLook(options[answer])
   # sm.changeChannelAndWarp(chr.getClient().getChannelInstance().getChannelId(), chr.getFieldID());