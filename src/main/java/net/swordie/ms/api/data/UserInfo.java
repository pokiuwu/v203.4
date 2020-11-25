package net.swordie.ms.api.data;

/**
 *  Class to hold information received from the MapleEllinel account server after being authenticated. Holds general info about the user account.
 *
 *  Created by MechAviv on 1/18/2019.
 */
public class UserInfo {

    private int id;
    private String name;
    private String email;
    //private String ip;
    private String error;
    private String message;
    private int status_code;

    /**
     * Returns the ID for the user on the MapleEllinel Network. This is a unique id.
     *
     * @return The account ID for the user.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the account on MapleEllinel. This is a unique string.
     *
     * @return The account name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the email address linked to this users MapleEllinel account.
     *
     * @return The users email address.
     */
    public String getEmail() {
        return email;
    }

    public String getError() {
        return error;
    }

    public String getErrorMessage() {
        return message;
    }

    public int getStatusCode() {
        return status_code;
    }
}
