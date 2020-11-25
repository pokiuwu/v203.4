package net.swordie.ms.client.jobs.nova;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.constants.JobConstants;

/**
 * Created by MechAviv on 1/19/2019.
 */
public class Cadena extends Job {

    // Skill ID: 64001001 (Attack Skill)
    // MOB_SET_STAT | 24 7B 22 01 00 00 00 02 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 E2 FF FF FF E9 93 D0 03 06 00 00 1A 00 02 01
    // TEMPORARY_STAT_SET [NextAttackEnhance] n[SLV] r[SkillID]

    // Skill ID: 64001009 (Attack Skill)
    // MOB_SET_STAT | BE CA 29 01 00 00 00 02 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 E2 FF FF FF F1 93 D0 03 02 00 00 79 00 04 03
    // TEMPORARY_STAT_SET [DarkSight] n[SLV] r[SkillID] t[500]

    private int[] buffs = new int[]{};

    public Cadena(Char chr) {
        super(chr);
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isCadena(id);
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        chr.getAvatarData().getCharacterStat().setPosMap(100000000);
    }
}
