package net.swordie.ms.client.character.commands;

import net.swordie.ms.enums.PrivateStatusIDFlag;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

    String[] names();
    PrivateStatusIDFlag requiredType();

}
