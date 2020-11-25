package net.swordie.ms.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.swordie.ms.api.data.UserInfo;
import net.swordie.ms.client.Account;
import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.BroadcastMsg;
import net.swordie.ms.connection.packet.Login;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.enums.LoginType;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by MechAviv on 1/18/2019.
 */
public class ApiFactory {
    private static ApiFactory instance;
    private final OkHttpClient client;
    private final Gson gson;
    private String apiToken;
    private long tokenExpireTime;
    public final static MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private ApiFactory() {
        client = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();

        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Used to get the ApiFactory singleton.
     *
     * @return Singleton ApiFactory Instance.
     */
    public static ApiFactory getFactory() {
        if (instance == null) {
            instance = new ApiFactory();
        }

        return instance;
    }

    /**
     * Checks to see if the server token is expired.
     *
     * @return true if the token is expired, false if it's active.
     */
    public boolean isServerTokenExpired() {
        return System.currentTimeMillis() > tokenExpireTime;
    }

    /**
     * Returns the instance of OkHttpClient. This is an external library used to make and handle all API calls and call backs.
     *
     * @return OkHttpClient instance
     */
    public OkHttpClient getHttpClient() {
        return client;
    }

    /**
     * Returns the instance of Gson. Gson is an external library used to convert java objects to and from JSON.
     *
     * @return
     */
    public Gson getGson() {
        return gson;
    }

    public String getAccountByToken(Client c, String token) {
        RequestBody body = new FormBody.Builder()
                .add("token", token)
                .build();

        Request request = new Request.Builder()
                .header("Accept", "application/json")
                .header("Authorization", "Elli9767")
                .url(ApiConstants.AUTH_URL)
                .post(body)
                .build();
        try {
            Response response = ApiFactory.getFactory().getHttpClient().newCall(request).execute();
            if (response.isSuccessful()) {
                String result = response.body().string();
                String message = result.substring(2, result.length());
                if (result.substring(0, 1).equals("1")) {
                    return message;
                }
                c.write(WvsContext.broadcastMsg(BroadcastMsg.popUpMessage(message)));
               // c.write(Login.checkPasswordResult(false, LoginType.Blocked, null));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*public String getAccountNameByToken(String token) throws IOException {
        Request request = new Request.Builder()
                .header("Accept", "application/json")
                .header("Authorization", "Elli " + token)
                .url("http://185.158.251.179/login")
                .build();

        Response response = ApiFactory.getFactory().getHttpClient().newCall(request).execute();

        if (response.isSuccessful()) {
            UserInfo userData = getGson().fromJson(response.body().string(), UserInfo.class);
            response.body().close();
            /*if ("unauthorized".equals(userData.getError())) {
                c.SendPacket(WvsContext.broadcastMsg(1, "Your login has either expired or is invalid. Please exit the game and login again through the launcher."));
                return 8;
            }
            if (!userData.isVerified() || userData.isRequireReverification()) {
                return 16;
            }
            return c.AuthLogin(userData, data);
            return 0;
        } else {
            response.body().close();
            return 8;
        }
    }*/

    public static void main(String[] args) throws IOException {
    }
}
