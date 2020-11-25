package net.swordie.ms.util.tools;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.loaders.VCore;
import net.swordie.ms.loaders.VCoreData;

import java.io.File;
import java.util.List;

/**
 * Created by MechAviv on 3/21/2019.
 */
public class VMatrixSkills {
    public static void main(String[] args) {
        int jobID = 212;
        VCore.loadVCoreFromFile(new File(ServerConstants.DAT_DIR,  "VCore.dat"), true);
        List<VCoreData> coreByJob = VCore.getNodesForJob(jobID);
        for (VCoreData core : coreByJob) {
            if (core.getType() == VCore.SKILL) {
                System.out.println(String.format("[%s] | [%d]", core.getName(), core.getConnectSkills().get(0)));
            }
        }
    }
}
