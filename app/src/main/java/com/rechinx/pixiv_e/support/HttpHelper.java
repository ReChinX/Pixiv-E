package com.rechinx.pixiv_e.support;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Chin on 2016/7/6.
 */
public class HttpHelper {

    private static final String TAG = HttpHelper.class.getSimpleName();

    public static String post(String url, String ua, String referer, FormBody.Builder builder) throws IOException {

        String resutl = null;
        Response response = null;
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = builder.build();
        Request request = new Request.Builder().url(url)
                                                .addHeader("User-Agent", ua)
                                                .addHeader("Referer", referer)
                                                .post(requestBody)
                                                .build();

        response = okHttpClient.newCall(request).execute();

        if(response.isSuccessful()) {
            resutl = response.body().string();
        }else{
            Log.e(TAG, " " + response.code());

        }

        return resutl;
    }

    public static String get(String url, String ua, String referer, String access_token) throws IOException {

        String resutl = null;
        Response response = null;
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder().url(url)
                                                .addHeader("User-Agent", ua)
                                                .addHeader("Referer", referer)
                                                .addHeader("Authorization", "Bearer" + " " + access_token)
                                                .build();

        response = okHttpClient.newCall(request).execute();
        if(response.isSuccessful()) {
            resutl = response.body().string();
        }else{
            Log.e(TAG, " " + response.code());
        }

        return resutl;
    }
}
