package com.rechinx.pixiv_e.api;

import android.content.Context;

import com.rechinx.pixiv_e.Constants;
import com.rechinx.pixiv_e.support.HttpHelper;
import com.rechinx.pixiv_e.support.Params;

import java.io.IOException;

/**
 * Created by Chin on 2016/7/27.
 */
public class MeFavoriteWorksApi {

    private static final String referer = "http://spapi.pixiv-app.net/";
    private static final String user_agent = "PixivIOSApp/5.6.0";

    public static String meFavoriteWorks(int page, int per_pages, String publicity, Context context) throws IOException {
        Params params = new Params();
        params.put("page", page+"");
        params.put("per_page", per_pages+"");
        params.put("publicity", publicity);
        params.put("image_sizes", "px_128x128,px_480mw,large");

        String send = params.encode();
        String url = Constants.me_favorite_works + "?" + send;

        String result = HttpHelper.get(url, user_agent, referer, BaseApi.getAccessToken(context));

        return result;
    }
}
