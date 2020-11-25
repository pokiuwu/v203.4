package net.swordie.ms.loaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 11/17/2017.
 */
public class DataClasses {
    public static List<Class> dataClasses = new ArrayList<>();
    public static List<Class> datCreators = new ArrayList<>();
    static {
        dataClasses.addAll(Arrays.asList(
                ItemData.class,
                SkillData.class,
                FieldData.class,
                VCore.class
                )
        );
        datCreators.addAll(Arrays.asList(
                FieldData.class,
                ItemData.class,
                MobData.class,
                StringData.class,
                NpcData.class,
                QuestData.class,
                ReactorData.class,
                SkillData.class,
                StringData.class
                )
        );
    }
}

