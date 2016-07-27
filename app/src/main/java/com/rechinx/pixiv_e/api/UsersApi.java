package com.rechinx.pixiv_e.api;

import android.content.Context;

import com.rechinx.pixiv_e.Constants;
import com.rechinx.pixiv_e.support.HttpHelper;
import com.rechinx.pixiv_e.support.Params;

import java.io.IOException;

/**
 * Created by Chin on 2016/7/27.
 */
public class UsersApi {

    private static final String referer = "http://spapi.pixiv-app.net/";
    private static final String user_agent = "PixivIOSApp/5.6.0";

    public static String users(int id, Context context) throws IOException {
        Params params = new Params();
        params.put("profile_image_sizes", "px_170x170,px_50x50");
        params.put("image_sizes", "px_128x128,small,medium,large,px_480mw");
        params.put("include_stats", "1");
        params.put("include_profile", "1");
        params.put("include_workspace", "1");
        params.put("include_contacts", "1");

        String url = String.format(Constants.users, id);
        String send = params.encode();
        url += "?" + send;

        String result = HttpHelper.get(url, user_agent, referer, BaseApi.getAccessToken(context));

        return result;
    }
}
