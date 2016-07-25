package com.rechinx.pixiv_e.api;

import android.content.Context;

import com.rechinx.pixiv_e.Constants;
import com.rechinx.pixiv_e.support.HttpHelper;
import com.rechinx.pixiv_e.support.Params;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Chin on 2016/7/24.
 */
public class WorksApi {

    private static final String referer = "http://spapi.pixiv-app.net/";
    private static final String user_agent = "PixivIOSApp/5.6.0";

    public static String Works(int illust_id, Context context) throws IOException {

        Params params = new Params();
        params.put("include_stats", "true");
        params.put("image_sizes", "px_128x128,small,medium,large,px_480mw");

        String url = String.format(Constants.works, illust_id);
        String send = params.encode();
        url += "?" + send;

        String result = HttpHelper.get(url, user_agent, referer, BaseApi.getAccessToken(context));

        return result;
    }
}
