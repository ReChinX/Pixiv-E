package com.rechinx.pixiv_e.api;

import android.content.Context;
import android.util.Log;

import com.rechinx.pixiv_e.Constants;
import com.rechinx.pixiv_e.support.HttpHelper;
import com.rechinx.pixiv_e.support.SettingProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;

/**
 * Created by Chin on 2016/7/3.
 */
public class BaseApi {

    public static void login(String user_name, String user_passwd, Context context) throws IOException {

        SettingProvider settingProvider = SettingProvider.getInstance(context);

        String result = auth(user_name, user_passwd);
        try {

            JSONObject jsonObject = new JSONObject(result);
            JSONObject jsonObject1 = jsonObject.getJSONObject("response");
            JSONObject jsonObject2 = jsonObject1.getJSONObject("user");
            String access_token = jsonObject1.getString("access_token");
            String id = jsonObject2.getString("id");
            if(settingProvider.getString("access_token", null) == null || settingProvider.getString("id", null) == null) {
                settingProvider.putString("access_token", access_token);
                settingProvider.putString("id", id);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private static String auth(String user_name, String user_passwd) throws IOException{

        String result = null;
        String referer = "http://www.pixiv.net/";
        String client_id = "bYGKuGVw91e0NMfPGp44euvGt59s";
        String client_secret = "HP3RmkgAmEGro0gn1x9ioawQE8WMfvLXDz3ZqxpK";


        FormBody.Builder builder = new FormBody.Builder();
        builder.add("client_id", client_id);
        builder.add("client_secret", client_secret);

        if(user_name != null && user_passwd != null) {
            builder.add("grant_type", "password");
            builder.add("username", user_name);
            builder.add("password", user_passwd);
        }else throw new IOException("[ERROR] auth() but no password or refresh_token is set.");

        result = HttpHelper.post(Constants.auth, "PixivIOSApp/5.8.3", referer, builder);

        return result;
    }

    public static String getAccessToken(Context context){

        SettingProvider settingProvider = SettingProvider.getInstance(context);
        return settingProvider.getString("access_token", null);
    }

    public static String getId(Context context) {
        SettingProvider settingProvider = SettingProvider.getInstance(context);
        return settingProvider.getString("id", null);
    }
}
