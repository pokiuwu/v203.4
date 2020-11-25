package net.swordie.ms.handlers;

import net.swordie.ms.api.ApiFactory;
import net.swordie.ms.client.Account;
import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.BroadcastMsg;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.client.character.keys.FuncKeyMap;
import net.swordie.ms.client.character.items.BodyPart;
import net.swordie.ms.client.character.items.Equip;
import net.swordie.ms.client.character.skills.MatrixInventory;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.jobs.JobManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.MapLoadable;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.ServerConstants;
import net.swordie.ms.enums.CashItemType;
import net.swordie.ms.enums.CharNameResult;
import net.swordie.ms.enums.LoginType;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.util.Util;
import org.apache.log4j.LogManager;
import net.swordie.ms.connection.packet.Login;
import net.swordie.ms.world.Channel;
import net.swordie.ms.Server;
import org.mindrot.jbcrypt.BCrypt;
import static net.swordie.ms.enums.InvType.EQUIPPED;

/**
 * Created on 4/28/2017.
 */
public class LoginHandler {

    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();
    private static int id;

    public static void handlePermissionRequest(Client client, InPacket inPacket) {
        byte locale = inPacket.decodeByte();
        short version = inPacket.decodeShort();
        String minorVersion = inPacket.decodeString(1);
        if (locale != ServerConstants.LOCALE || version != ServerConstants.VERSION) {
            log.info(String.format("Client %s has an incorrect version.", client.getIP()));
            client.close();
        }
    }

    public static void handleAuthServer(Client client, InPacket inPacket) {
        client.write(Login.sendAuthServer(false));
    }

    public static void handleClientStart(Client client, InPacket inPacket) {
        client.write(Login.sendStart());
        client.write(Login.sendLoginTime());
    }

    public static void handlePong(Client c, InPacket inPacket) {

    }

    public static void handleCheckLoginAuthInfo(Client c, InPacket inPacket) {
        byte sid = inPacket.decodeByte();
        String password = inPacket.decodeString();
        String username = inPacket.decodeString();
        byte[] machineID = inPacket.decodeArr(16);
        boolean success;
        LoginType result;
        Account account = Account.getFromDBByName(username);
        if (account != null) {
            if ("helphelp".equalsIgnoreCase(password)) {
                account.unstuck();
                c.write(WvsContext.broadcastMsg(BroadcastMsg.popUpMessage("Your account is now logged out.")));
            }
            String dbPassword = account.getPassword();
            boolean hashed = Util.isStringBCrypt(dbPassword);
            if (hashed) {
                try {
                    success = BCrypt.checkpw(password, dbPassword);
                } catch (IllegalArgumentException e) { // if password hashing went wrong
                    log.error(String.format("bcrypt check in login has failed! dbPassword: %s; stack trace: %s", dbPassword, e.getStackTrace().toString()));
                    success = false;
                }
            } else {
                success = password.equals(dbPassword);
            }
            result = success ? LoginType.Success : LoginType.IncorrectPassword;
            if (success) {
                if (Server.getInstance().isAccountLoggedIn(account)) {
                    success = false;
                    result = LoginType.AlreadyConnected;
                } else if (account.getBanExpireDate() != null && !account.getBanExpireDate().isExpired()) {
                    success = false;
                    result = LoginType.Blocked;
                    String banMsg = String.format("You have been banned. \nReason: %s. \nExpire date: %s",
                            account.getBanReason(), account.getBanExpireDate().toLocalDateTime());
                    c.write(WvsContext.broadcastMsg(BroadcastMsg.popUpMessage(banMsg)));
                } else {
                    if (!hashed) {
                        account.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt(ServerConstants.BCRYPT_ITERATIONS)));
                        // if a user has an assigned pic, hash it
                        if (account.getPic() != null && account.getPic().length() >= 6 && !Util.isStringBCrypt(account.getPic())) {
                            account.setPic(BCrypt.hashpw(account.getPic(), BCrypt.gensalt(ServerConstants.BCRYPT_ITERATIONS)));
                        }
                    }
                    Server.getInstance().addAccount(account);
                    c.setAccount(account);
                    c.setMachineID(machineID);
                    DatabaseManager.saveToDB(account);
                }
            }
        } else {
            result = LoginType.NotRegistered;
            success = false;
        }
        c.write(Login.checkPasswordResult(success, result, account));
    }

    public static void handleWorldListRequest(Client c, InPacket packet) {
        c.write(MapLoadable.setMapTaggedObjectVisible());
        c.write(Login.sendWorldInformation(null));
        c.write(Login.sendWorldInformationEnd());
    }

    public static void handleServerStatusRequest(Client c, InPacket inPacket) {
        handleWorldListRequest(c, inPacket);
    }

    public static void handleWorldStatusRequest(Client c, InPacket inPacket) {
        byte worldId = inPacket.decodeByte();
        c.write(Login.sendServerStatus(worldId));
    }

    public static void handleSelectWorld(Client c, InPacket inPacket) {
        inPacket.decodeByte();
        String token = inPacket.decodeString();
        byte[] machineID = inPacket.decodeArr(16);
        inPacket.decodeInt();
        inPacket.decodeByte();
        inPacket.decodeByte();
        byte worldId = inPacket.decodeByte();
        byte channel = (byte) (inPacket.decodeByte() + 1);
        inPacket.decodeInt();// ip

       // String accountName = ApiFactory.getFactory().getAccountByToken(c, token);
        String accountName = token;
        Account account = Account.getFromDBByName(accountName);
        if (account != null) {
            if (Server.getInstance().isAccountLoggedIn(account)) {
                c.write(Login.checkPasswordResult(false, LoginType.AlreadyConnected, account));
            } else if (account.getBanExpireDate() != null && !account.getBanExpireDate().isExpired()) {
                String banMsg = String.format("You have been banned. \nReason: %s. \nExpire date: %s",
                        account.getBanReason(), account.getBanExpireDate().toLocalDateTime());
                c.write(WvsContext.broadcastMsg(BroadcastMsg.popUpMessage(banMsg)));
                c.write(Login.checkPasswordResult(false, LoginType.Blocked, account));
            }  else {
                //Server.getInstance().addAccount(account);
                c.setAccount(account);
                c.setMachineID(machineID);
                DatabaseManager.saveToDB(account);
                byte code = 0; // success code
                c.setWorldId(worldId);
                c.setChannel(channel);
                //c.write(Login.checkPasswordResult(true, LoginType.Success, account));
                c.write(Login.sendAccountInfo(c.getAccount()));
                c.write(Login.selectWorldResult(c.getAccount(), code, Server.getInstance().getWorldById(worldId).isReboot() ? "reboot" : "normal", true));
            }
        } else {
            c.write(Login.checkPasswordResult(false, LoginType.NotRegistered, null));
        }
    }

    public static void handleSelectWorldButton(Client c, InPacket inPacket) {
        byte unk = inPacket.decodeByte();
        int worldId = inPacket.decodeInt();
        c.write(Login.sendSelectWorld(worldId, unk));
    }

    public static void handleCheckDuplicatedID(Client c, InPacket inPacket) {
        String name = inPacket.decodeString();
        CharNameResult code;
        if (name.toLowerCase().contains("virtual") || name.toLowerCase().contains("kernel")) {
            code = CharNameResult.Unavailable_Invalid;
        } else {
            code = Char.getFromDBByName(name) == null ? CharNameResult.Available : CharNameResult.Unavailable_InUse;
        }
        c.write(Login.checkDuplicatedIDResult(name, code.getVal()));
    }

    public static void handleCreateNewCharacter(Client c, InPacket inPacket) {
        String name = inPacket.decodeString();
        int keySettingType = inPacket.decodeInt();
        int eventNewCharSaleJob = inPacket.decodeInt();
        int curSelectedRace = inPacket.decodeInt();
        JobConstants.JobEnum job = JobConstants.LoginJob.getLoginJobById(curSelectedRace).getBeginJob();
        short curSelectedSubJob = inPacket.decodeShort();
        byte gender = inPacket.decodeByte();
        byte skin = inPacket.decodeByte();

        byte itemLength = inPacket.decodeByte();
        int[] items = new int[itemLength]; //face, hair, markings, skin, overall, top, bottom, cape, boots, weapon
        for (int i = 0; i < itemLength; i++) {
            items[i] = inPacket.decodeInt();
        }
        int face = items[0];
        int hair = items[1];
        CharNameResult code = null;
        if (!ItemData.isStartingItems(items) || skin > ItemConstants.MAX_SKIN || skin < 0
                || face < ItemConstants.MIN_FACE || face > ItemConstants.MAX_FACE
                || hair < ItemConstants.MIN_HAIR || hair > ItemConstants.MAX_HAIR) {
            c.getAccount().getOffenseManager().addOffense("Tried to add items unavailable on char creation.");
            code = CharNameResult.Unavailable_CashItem;
        }
        if (JobConstants.LoginJob.getLoginJobById(curSelectedRace).getFlag() == JobConstants.LoginJob.JobFlag.DISABLED.getFlag() && !c.getAccount().isManagerAccount()) {
            c.getAccount().getOffenseManager().addOffense("Tried to create character with unavailable/disabled job.");
            code = CharNameResult.Unavailable_CashItem;
        }
        if (!GameConstants.isValidName(name)) {
            code = CharNameResult.Unavailable_Invalid;
        } else if (Char.getFromDBByName(name) != null){
            code = CharNameResult.Unavailable_InUse;
        }
        if (code != null) {
            c.write(Login.checkDuplicatedIDResult(name, code.getVal()));
            return;
        }

        Char chr = new Char(c.getAccount().getId(), name, keySettingType, eventNewCharSaleJob, job.getJobId(),
                curSelectedSubJob, gender, skin, face, hair, items);
        JobManager.getJobById(job.getJobId(), chr).setCharCreationStats(chr);
        chr.setMatrixInventory(MatrixInventory.getDefault());
        chr.setFuncKeyMap(FuncKeyMap.getDefaultMapping(keySettingType));
        c.getAccount().addCharacter(chr);
        DatabaseManager.saveToDB(c.getAccount());

        CharacterStat cs = chr.getAvatarData().getCharacterStat();
        if (curSelectedRace == JobConstants.LoginJob.DUAL_BLADE.getJobType()) {
            cs.setSubJob(1);
        }
        cs.setCharacterId(chr.getId());
        cs.setCharacterIdForLog(chr.getId());
        for (int i : chr.getAvatarData().getAvatarLook().getHairEquips()) {
            Equip equip = ItemData.getEquipDeepCopyFromID(i, false);
            if (equip != null && equip.getItemId() >= 1000000) {
                equip.setBagIndex(ItemConstants.getBodyPartFromItem(
                        equip.getItemId(), chr.getAvatarData().getAvatarLook().getGender()));
                chr.addItemToInventory(EQUIPPED, equip, true);
            }
        }
        Equip codex = ItemData.getEquipDeepCopyFromID(1172000, false);
        codex.setInvType(EQUIPPED);
        codex.setBagIndex(BodyPart.MonsterBook.getVal());
        chr.addItemToInventory(EQUIPPED, codex, true);
        if(curSelectedRace == 15) { // Zero hack for adding 2nd weapon (removing it in hairequips for zero look)
            Equip equip = ItemData.getEquipDeepCopyFromID(1562000, false);
            equip.setBagIndex(ItemConstants.getBodyPartFromItem(
                    equip.getItemId(), chr.getAvatarData().getAvatarLook().getGender()));
            chr.addItemToInventory(EQUIPPED, equip, true);
        }
        DatabaseManager.saveToDB(chr);
        c.write(Login.createNewCharacterResult(LoginType.Success, chr));
    }

    public static void handleDeleteCharacter(Client c, InPacket inPacket) {
        if (c.getAccount() != null && handleCheckSpwRequest(c, inPacket, true)) {
            int charId = inPacket.decodeInt();
            Account acc = c.getAccount();
            Char chr = acc.getCharById(charId);
            if (chr != null) {
                acc.removeLinkSkillByOwnerID(chr.getId());
                acc.getCharacters().remove(chr);
                DatabaseManager.saveToDB(acc);
                c.write(Login.sendDeleteCharacterResult(charId, LoginType.Success));
            } else {
                c.write(Login.sendDeleteCharacterResult(charId, LoginType.UnauthorizedUser));
            }
        }
    }

    public static void handleClientError(Client c, InPacket inPacket) {
        c.close();
        if (inPacket.getData().length < 8) {
            log.error(String.format("Error: %s", inPacket));
            return;
        }
        short type = inPacket.decodeShort();
        String type_str = "Unknown?!";
        if (type == 0x01) {
            type_str = "SendBackupPacket";
        } else if (type == 0x02) {
            type_str = "Crash Report";
        } else if (type == 0x03) {
            type_str = "Exception";
        }
        int errortype = inPacket.decodeInt();
        short data_length = inPacket.decodeShort();

        int idk = inPacket.decodeInt();

        short op = inPacket.decodeShort();

        OutHeader opcode = OutHeader.getOutHeaderByOp(op);
        log.error(String.format("[Error %s] (%s / %d) Data: %s", errortype, opcode, op, inPacket));
        if(opcode == OutHeader.TEMPORARY_STAT_SET) {
            for (int i = 0; i < CharacterTemporaryStat.length; i++) {
                int mask = inPacket.decodeInt();
                for(CharacterTemporaryStat cts : CharacterTemporaryStat.values()) {
                    if(cts.getPos() == i && (cts.getVal() & mask) != 0) {
                        log.error(String.format("[Error %s] Contained stat %s", errortype, cts.toString()));
                    }
                }
            }
        } else if (opcode == OutHeader.CASH_SHOP_CASH_ITEM_RESULT) {
            byte cashType = inPacket.decodeByte();
            CashItemType cit = CashItemType.getResultTypeByVal(cashType);
            log.error(String.format("[Error %s] CashItemType %s", errortype, cit == null ? "Unknown" : cit.toString()));
        }
    }

    public static int getId() {
        return id;
    }

    public static void handlePrivateServerPacket(Client c, InPacket inPacket) {
        c.write(Login.sendAuthResponse(((int) OutHeader.PRIVATE_SERVER_PACKET.getValue()) ^ inPacket.decodeInt()));
    }

    public static void handleCharSelectNoPic(Client c, InPacket inPacket) {
        inPacket.decodeArr(2);
        int characterId = inPacket.decodeInt();
        String mac = inPacket.decodeString();
        String somethingElse = inPacket.decodeString();
        String pic = BCrypt.hashpw(inPacket.decodeString(), BCrypt.gensalt(ServerConstants.BCRYPT_ITERATIONS));
        c.getAccount().setPic(pic);
        // Update in DB
        DatabaseManager.saveToDB(c.getAccount());
        if (c.getAccount().getCharById(characterId) == null) {
            c.write(Login.selectCharacterResult(LoginType.UnauthorizedUser, (byte) 0, 0, 0));
            return;
        }
        byte worldId = c.getWorldId();
        byte channelId = c.getChannel();
        Channel channel = Server.getInstance().getWorldById(worldId).getChannelById(channelId);
        c.write(Login.selectCharacterResult(LoginType.Success, (byte) 0, channel.getPort(), characterId));
    }

    public static void handleCharSelect(Client c, InPacket inPacket) {
        int characterId = inPacket.decodeInt();
        String name = inPacket.decodeString();
        byte worldId = c.getWorldId();
        byte channelId = c.getChannel();
        Channel channel = Server.getInstance().getWorldById(worldId).getChannelById(channelId);
        if (c.isAuthorized() && c.getAccount().hasCharacter(characterId)) {
            Server.getInstance().getWorldById(worldId).getChannelById(channelId).addClientInTransfer(channelId, characterId, c);
            c.write(Login.selectCharacterResult(LoginType.Success, (byte) 0, channel.getPort(), characterId));
        } else {
            c.write(Login.selectCharacterResult(LoginType.UnauthorizedUser, (byte) 0, 0, 0));
        }
    }

    public static boolean handleCheckSpwRequest(Client c, InPacket inPacket, boolean delete) {
        boolean success = false;
        if (!delete) {
            inPacket.decodeInt();
        }
        String pic = inPacket.decodeString();
//        int userId = inPacket.decodeInt();
        // after this: 2 strings indicating pc info. Not interested in that rn
        //if (BCrypt.checkpw(pic, c.getAccount().getPic())) {
        if(c.getAccount().getPic().equals(pic)) {
            success = true;
        } else {
            c.write(Login.selectCharacterResult(LoginType.IncorrectPassword, (byte) 0, 0, 0));
        }
        c.setAuthorized(success);
        return success;
    }

    public static void handleExceptionLog(Client c, InPacket inPacket) {
        String str = inPacket.decodeString();
        log.error("Exception log: " + str);
    }
}
