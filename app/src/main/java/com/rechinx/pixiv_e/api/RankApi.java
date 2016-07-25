package com.rechinx.pixiv_e.api;

import android.content.Context;
import android.util.Log;

import com.rechinx.pixiv_e.Constants;
import com.rechinx.pixiv_e.support.HttpHelper;
import com.rechinx.pixiv_e.support.Params;

import java.io.IOException;

/**
 * Created by Chin on 2016/7/6.
 */
public class RankApi {

    private static final String referer = "http://spapi.pixiv.net/";
    private static final String user_agent = "PixivIOSApp/5.6.0";

    public static String RankAll(String image_sizes, String include_stats, String page, String profile_image_sizes, String mode, String include_sanity_level, String per_page, Context context) throws IOException {

        Params params = new Params();
        params.put("image_sizes", image_sizes);
        params.put("include_stats", include_stats);
        params.put("page", page);
        params.put("profile_image_sizes", profile_image_sizes);
        params.put("mode", mode);
        params.put("include_sanity_level", include_sanity_level);
        params.put("per_page", per_page);

        String send = params.encode();
        String url = Constants.rank + "?" + send;

        String result = HttpHelper.get(url, user_agent, referer, BaseApi.getAccessToken(context));

        return result;

    }

}
