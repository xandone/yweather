package app.xandone.com.yweather.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.SharedPreferencesCompat;

import java.util.Map;

import app.xandone.com.yweather.BaseApplication;


/**
 * Created by xandone on 2016/12/22.
 */
public class SpUtils {
    private static SharedPreferences sp;
    private static SharedPreferences mPreferences;
    private static SharedPreferences.Editor mEditor;
    public static final String FILE_NAME = "shared_data";

    private static SpUtils mInstance;

    private SpUtils(Context context) {
        mPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    public static SpUtils getInstance(Context context) {
        if (null == mInstance) {
            synchronized (SpUtils.class) {
                if (null == mInstance) {
                    mInstance = new SpUtils(context.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    /**
     * 保存数据的方法，拿到数据保存数据的基本类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    public void put(String key, Object object) {
        if (object instanceof String) {
            mEditor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            mEditor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            mEditor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            mEditor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            mEditor.putLong(key, (Long) object);
        } else {
            mEditor.putString(key, object.toString());
        }
        apply();
    }

    /**
     * 获取保存数据的方法，我们根据默认值的到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key           键的值
     * @param defaultObject 默认值
     * @return
     */

    public Object get(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return mPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return mPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return mPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return mPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return mPreferences.getLong(key, (Long) defaultObject);
        } else {
            return mPreferences.getString(key, null);
        }

    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public void remove(String key) {
        mEditor.remove(key);
        apply();
    }

    /**
     * 清除所有的数据
     */
    public void clear() {
        mEditor.clear();
        apply();
    }

    /**
     * 查询某个key是否存在
     *
     * @param key
     * @return
     */
    public boolean contains(String key) {
        return mPreferences.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @return
     */
    public Map<String, ?> getAll() {
        return mPreferences.getAll();
    }

    private void apply(){
        SharedPreferencesCompat.EditorCompat.getInstance().apply(mEditor);
    }

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

    public static String getSpStringData(String key, String defaultStr) {
        init();
        return sp.getString(key, defaultStr);
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

