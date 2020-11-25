# Mu Lung Dojo Floors (except 1st Floor) | Used to spawn the Bosses & Display "Floor Effect & Dojo Clock"

from net.swordie.ms.constants import WzConstants
from net.swordie.ms.constants import GameConstants
from time import sleep

MuLungDojoF1 = 925070100
MuLungDojoF2 = 925070200
MuLungDojoF3 = 925070300
MuLungDojoF4 = 925070400
MuLungDojoF5 = 925070500
MuLungDojoF6 = 925070600
MuLungDojoF7 = 925070700
MuLungDojoF8 = 925070800
MuLungDojoF9 = 925070900
MuLungDojoF10 = 925071000
MuLungDojoF11 = 925071100
MuLungDojoF12 = 925071200
MuLungDojoF13 = 925071300
MuLungDojoF14 = 925071400
MuLungDojoF15 = 925071500
MuLungDojoF16 = 925071600
MuLungDojoF17 = 925071700
MuLungDojoF18 = 925071800
MuLungDojoF19 = 925071900
MuLungDojoF20 = 925072000
MuLungDojoF21 = 925072100
MuLungDojoF22 = 925072200
MuLungDojoF23 = 925072300
MuLungDojoF24 = 925072400
MuLungDojoF25 = 925072500
MuLungDojoF26 = 925072600
MuLungDojoF27 = 925072700
MuLungDojoF28 = 925072800
MuLungDojoF29 = 925072900
MuLungDojoF30 = 925073000
MuLungDojoF31 = 925073100
MuLungDojoF32 = 925073200
MuLungDojoF33 = 925073300
MuLungDojoF34 = 925073400
MuLungDojoF35 = 925073500
MuLungDojoF36 = 925073600
MuLungDojoF37 = 925073700
MuLungDojoF38 = 925073800
MuLungDojoF39 = 925073900
MuLungDojoF40 = 925074000
MuLungDojoF41 = 925074100

bossPerFloor = {
MuLungDojoF1 : 9305600, # Mano
MuLungDojoF2 : 9305601, # Mushmom
MuLungDojoF3 : 9305602, # Stumpy
MuLungDojoF4 : 9305603, # Blue Mushmom
MuLungDojoF5 : 9305604, # Zombie Mushmom
MuLungDojoF6 : 9305605, # King Slime
MuLungDojoF7 : 9305606, # Dyle
MuLungDojoF8 : 9305607, # King Clang
MuLungDojoF9 : 9305608, # Faust
MuLungDojoF10 : 9305609, # Hilla
MuLungDojoF11 : 9305610, # Metal Golem
MuLungDojoF12 : 9305611, # Eliza
MuLungDojoF13 : 9305612, # Jr. Balrog
MuLungDojoF14 : 9305613, # Nine-Tailed Fox
MuLungDojoF15 : 9305614, # Deo
MuLungDojoF16 : 9305615, # Zeno
MuLungDojoF17 : 9305616, # Timer
MuLungDojoF18 : 9305617, # Snack Bar
MuLungDojoF19 : 9305618, # Tae Roon
MuLungDojoF20 : 9305619, # Von Leon
MuLungDojoF21 : 9305620, # Papa Pixie
MuLungDojoF22 : 9305621, # Alishar
MuLungDojoF23 : 9305622, # Lord Pirate
MuLungDojoF24 : 9305623, # Deet and Roi
MuLungDojoF25 : 9305624, # Frankenroid
MuLungDojoF26 : 9305625, # Chimera
MuLungDojoF27 : 9305626, # Poison Golem
MuLungDojoF28 : 9305627, # King Sage Cat
MuLungDojoF29 : 9305628, # Crimson Balrog
MuLungDojoF30 : 9305629, # Arkarium
MuLungDojoF31 : 9305630, # Manon
MuLungDojoF32 : 9305631, # Griffey
MuLungDojoF33 : 9305632, # Snowman
MuLungDojoF34 : 9305633, # Papulatus
MuLungDojoF35 : 9305634, # Ani
MuLungDojoF36 : 9305635, # Leviathan
MuLungDojoF37 : 9305636, # Dodo
MuLungDojoF38 : 9305637, # Lilynouch
MuLungDojoF39 : 9305638, # Lyka
MuLungDojoF40 : 9305639, # Magnus
MuLungDojoF41 : 9305640 # Mu Gong
}

stage = ((sm.getFieldID() % 10000) / 100)
sm.showEffectToField(""+ WzConstants.EFFECT_DOJO_STAGE_NUMBER +""+ str(stage))
sm.showEffectToField(WzConstants.EFFECT_DOJO_STAGE)

sleep(GameConstants.DOJO_SPAWM_BOSS_DELAY)
sm.spawnMob(bossPerFloor[sm.getFieldID()], 0, 7, False) # Spawns mob based on Field ID

sm.waitForMobDeath(bossPerFloor[sm.getFieldID()])
sm.showEffectToField(WzConstants.EFFECT_DOJO_CLEAR)