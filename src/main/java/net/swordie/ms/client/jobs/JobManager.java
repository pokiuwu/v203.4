package net.swordie.ms.client.jobs;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.jobs.adventurer.*;
import net.swordie.ms.client.jobs.cygnus.*;
import net.swordie.ms.client.jobs.flora.Ark;
import net.swordie.ms.client.jobs.flora.Illium;
import net.swordie.ms.client.jobs.legend.*;
import net.swordie.ms.client.jobs.nova.AngelicBuster;
import net.swordie.ms.client.jobs.nova.Kaiser;
import net.swordie.ms.client.jobs.resistance.*;
import net.swordie.ms.client.jobs.sengoku.Hayato;
import net.swordie.ms.client.jobs.sengoku.Kanna;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.constants.JobConstants;

import java.lang.reflect.InvocationTargetException;

/**
 * Created on 12/14/2017.
 */
public class JobManager {
    private static final Class[] jobClasses = new Class[] {
            Archer.class,
            BeastTamer.class,
            Beginner.class,
            Kinesis.class,
            Magician.class,
            PinkBean.class,
            Pirate.class,
            Thief.class,
            Warrior.class,
            Jett.class,

            BlazeWizard.class,
            DawnWarrior.class,
            Mihile.class,
            NightWalker.class,
            Noblesse.class,
            ThunderBreaker.class,
            WindArcher.class,

            Aran.class,
            Evan.class,
            Legend.class,
            Luminous.class,
            Mercedes.class,
            Phantom.class,
            Shade.class,

            AngelicBuster.class,
            Kaiser.class,

            BattleMage.class,
            Blaster.class,
            Citizen.class,
            Demon.class,
            Mechanic.class,
            WildHunter.class,
            Xenon.class,

            Hayato.class,
            Kanna.class,

            Zero.class,

            Illium.class,
            Ark.class
    };

    private short id;

    public JobConstants.JobEnum getJobEnum() {
        return JobConstants.getJobEnumById(getId());
    }
    public void setDefaultCharStatValues(CharacterStat characterStat) {
        characterStat.setLevel(1);
        characterStat.setStr(12);
        characterStat.setDex(5);
        characterStat.setInt(4);
        characterStat.setLuk(4);
        characterStat.setMaxHp(50);
        characterStat.setHp(50);
        characterStat.setMaxMp(5);
        characterStat.setMp(5);
    }

    public static void handleAtt(Client c, AttackInfo attackInfo) {
        for(Class clazz : jobClasses) {
            Job job = null;
            try {
                job = (Job) clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            if(job != null && job.isHandlerOfJob(c.getChr().getJob())) {
                job.handleAttack(c, attackInfo);
            }
        }
    }

    public static void handleSkill(Client c, InPacket inPacket) {
        for(Class clazz : jobClasses) {
            Job job = null;
            try {
                job = (Job) clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            if(job != null && job.isHandlerOfJob(c.getChr().getJob())) {
                inPacket.decodeInt(); // crc
                int skillID = inPacket.decodeInt();
                byte slv = inPacket.decodeByte();
                job.handleSkill(c, skillID, slv, inPacket);
            }
        }
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public static Job getJobById(short id, Char chr) {
        Job job = null;
        for(Class clazz : jobClasses) {
            try {
                job = (Job) clazz.getConstructor(Char.class).newInstance(chr);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
            if(job != null && job.isHandlerOfJob(id)) {
                return job;
            }
        }
        return job;
    }
}
