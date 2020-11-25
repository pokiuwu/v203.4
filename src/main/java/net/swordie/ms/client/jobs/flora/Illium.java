package net.swordie.ms.client.jobs.flora;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.constants.JobConstants;

/**
 * Created by MechAviv on 1/19/2019.
 */
public class Illium extends Job {

    private int[] buffs = new int[]{};

    public Illium(Char chr) {
        super(chr);
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isIllium(id);
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        chr.getAvatarData().getAvatarLook().setDrawElfEar(true);
        chr.getAvatarData().getCharacterStat().setPosMap(100000000);
    }
}
