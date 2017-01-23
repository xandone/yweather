package app.xandone.com.yweather.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import app.xandone.com.yweather.BaseApplication;


/**
 * Created by xandone on 2016/12/22.
 */
public class
SpUtils {
    private static SharedPreferences sp;

    private static void init() {
        if (sp == null) {
            sp = PreferenceManager.getDefaultSharedPreferences(BaseApplication.sContext);
        }
    }

    /**
     * 设置int型
     *
     * @param key
     * @param data
     */
    public static void setSpIntData(String key, int data) {
        init();
        sp.edit().putInt(key, data).commit();
    }

    /**
     * 获取int型
     *
     * @param key
     * @return
     */
    public static int getSpIntData(String key) {
        init();
        return sp.getInt(key, 0);
    }

    /**
     * 设置float型
     *
     * @param key
     * @param data
     */
    public static void setSpFloatData(String key, float data) {
        init();
        sp.edit().putFloat(key, data).commit();
    }

    /**
     * 获取float型
     *
     * @param key
     * @return
     */
    public static Float getSpFloatData(String key) {
        init();
        return sp.getFloat(key, 0);
    }

    /**
     * 设置String型
     *
     * @param key
     * @param data
     */
    public static void setSpStringData(String key, String data) {
        init();
        sp.edit().putString(key, data).commit();
    }

    /**
     * 获取String型
     *
     * @param key
     * @return
     */
    public static String getSpStringData(String key) {
        init();
        return sp.getString(key, "");
    }

    /**
     * 设置boolean型
     *
     * @param key
     * @param data
     */
    public static void setSpBooleanData(String key, boolean data) {
        init();
        sp.edit().putBoolean(key, data).commit();
    }

    /**
     * 获取boolean型
     *
     * @param key
     * @return
     */
    public static boolean getSpBooleanData(String key) {
        init();
        return sp.getBoolean(key, false);
    }


}

