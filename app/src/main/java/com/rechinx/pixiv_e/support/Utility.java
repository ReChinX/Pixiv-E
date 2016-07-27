package com.rechinx.pixiv_e.support;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

/**
 * Created by Chin on 2016/7/27.
 */
public class Utility {

    public static GlideUrl constructGlideUrl(String url) {
        GlideUrl glideUrl = new GlideUrl(url,
                new LazyHeaders.Builder()
                        .addHeader("Referer", "http://spapi.pixiv.net/")
                        .addHeader("Accept-Language", "zh-cn")
                        .addHeader("Proxy-Connection", "keep-alive")
                        .addHeader("Connection", "keep-alive")
                        .addHeader("Accept-Encoding", "gzip, deflate")
                        .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0")
                        .build());
        return glideUrl;
    }

}
