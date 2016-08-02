package com.rechinx.pixiv_e.support;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Chin on 2016/7/6.
 */
public class SettingProvider {

    public static final String XML_NAME = "settings";

    private static SettingProvider instance;
    private SharedPreferences sharedPreferences;

    public SettingProvider(Context context) {
        sharedPreferences = context.getSharedPreferences(XML_NAME, Context.MODE_PRIVATE);
    }

    public static SettingProvider getInstance(Context context){
        if(instance == null) {
            instance = new SettingProvider(context);
        }

        return instance;
    }

    public SettingProvider putBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).commit();
        return this;
    }

    public boolean getBoolean(String key, boolean def) {
        return sharedPreferences.getBoolean(key, def);
    }

    public SettingProvider putInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).commit();
        return this;
    }

    public int getInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    public SettingProvider putString(String key, String value) {
        sharedPreferences.edit().putString(key, value).commit();
        return this;
    }

    public String getString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    public SettingProvider putLong(String key, long value) {
        sharedPreferences.edit().putLong(key, value).commit();
        return this;
    }

    public long getLong(String key, long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }
}
