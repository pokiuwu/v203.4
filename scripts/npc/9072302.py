from net.swordie.ms.enums import UIType
options = ["I want to go somewhere","I want to buy something","Vote for the server", "I want to upgrade my #eV Matrix skills#k#b.#k"]

options2 = ["Town Maps","Monster Maps","Boss Entrances"]

maps = [
[300000000, 680000000, 230000000, 910001000, 260000000, 541000000, 610050000, 540000000, 
211060010, 863100000, 105300000, 310000000, 211000000, 101072000, 101000000, 101050000, 
130000000, 820000000, 223000000, 410000000, 141000000, 120040000, 209000000, 682000000, 
310070000, 401000000, 100000000, 271010000, 251000000, 744000000, 551000000, 103000000, 
224000000, 241000000, 240000000, 104000000, 220000000, 150000000, 261000000, 701220000, 
807000000,  701210000, 250000000, 800000000, 600000000, 120000000, 200000000, 800040000, 
400000000, 102000000, 914040000, 865000000, 801000000, 105000000, 866190000, 693000020, 
270000000, 860000000, 273000000, 701100000, 320000000], # Town Maps

[240070300, 800020110, 610040000, 270030000, 860000032, 211060000, 240040500, 551030100, 
271000300, 211061000, 211041100, 240010501, 270020000, 910170100, 910160100, 610030010, 
863000100, 910180100, 272000300, 682010200, 541000300, 241000200, 220050300, 102040200, 
240010700, 241000210, 241000220, 272010000, 910028600, 706041000, 706041005, 273050000, 
231040400, 401050000, 541020000, 502010010], # Monster Maps

#[[105100100, "Balrog"], [211042300, "Zakum"], [240050400, "Horntail"], [262030000, "Hilla"],
#[105200000, "Root Abyss"], [211070000, "Von Leon"], [272020110, "Arkarium"], [401060000, "Easy Magnus"],
#[401060000, "Normal/Hard Magnus"], [270050000, "Pink Bean"], [271030600, "Cygnus"], [350060300, "Lotus"],
#[863010000, "Gollux"], [211041700, "Ranmaru"], [811000008, "Princess No"], [970000106, "Hekaton"],
#[970072200, "Ursus"], [105300303, "Damien"], [610030010, "Crimsonwood Keep"], [450004000, "Lucid"],
#[927030060, "Black Mage"]] # Boss Maps
    [[211042300, "Zakum"], [240050400, "Horntail"], [262030000, "Hilla"],[211070000, "Von Leon"], [272020110, "Arkarium"],[271040000, "Cygnus"]]
]

list = "Hello #r#h0##k! How can I help you today?"
i = 0
while i < len(options):
    list += "\r\n#b#L" +str(i)+ "#" + str(options[i])
    i += 1
i = 0
option = sm.sendNext(list)
if option == 0: # I want to go somewhere (maps)
    list = "These are your options: "
    while i < len(options2):
        list += "\r\n#b#L" +str(i)+ "#" + str(options2[i])
        i += 1
    i = 0
    ans1 = sm.sendNext(list)
    list = "These are your options: "
    if ans1 == 2: # boss maps
        while i < len(maps[ans1]):
            list += "\r\n#L" + str(i) + "##b" + str(maps[ans1][i][1])
            i += 1
    else: # town/monster maps
        while i < len(maps[ans1]):
            list += "\r\n#L" + str(i) + "##b#m" + str(maps[ans1][i]) + "#"
            i += 1
    ans2 = sm.sendNext(list)
    if ans1 == 2: # boss maps
        sm.warp(maps[ans1][ans2][0], 1)
    else:
        sm.warp(maps[ans1][ans2], 1)
elif option == 3:
    sm.openUI(UIType.UI_MATRIX_UPGRADE)
else:
    sm.sendSayOkay("This option currently is uncoded.")




        
