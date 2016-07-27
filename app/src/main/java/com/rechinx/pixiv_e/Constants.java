package com.rechinx.pixiv_e;

/**
 * Created by Chin on 2016/7/6.
 */
public class Constants {

    public static final String host = "https://public-api.secure.pixiv.net";
    public static final String auth = "https://oauth.secure.pixiv.net/auth/token";
    public static final String rank = host + "/" + "v1/ranking/all";
    public static final String works = host +"/v1/works/%d.json";
    public static final String users = host + "/v1/users/%d.json";
    public static final String me_favorite_works = host + "/v1/me/favorite_works.json";
    public static final String me_following = host + "/v1/me/following.json";

    public static final String SETTING_DEF_VALUE = "none";
}
