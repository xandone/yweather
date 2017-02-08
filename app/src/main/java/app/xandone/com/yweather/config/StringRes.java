package app.xandone.com.yweather.config;

import app.xandone.com.yweather.BaseApplication;
import app.xandone.com.yweather.R;

/**
 * Created by xandone on 2017/1/9.
 */
public class StringRes {
    public static final String[] WEATHER_STATUS = {"晴", "多云", "雷阵雨", "阵雨", "阴", "中雨", "小雨", "大雨", "雨夹雪"};
    public static final int[] WEATHER_STATUS_ICON = {
            R.drawable.sun,
            R.drawable.cloudy,
            R.drawable.thunder_shower,
            R.drawable.shower,
            R.drawable.overcast,
            R.drawable.light_rain,
            R.drawable.light_rain,
            R.drawable.heavy_rain,
            R.drawable.sleet
    };

    public static final String PASSWORD = "DJOYnieT8234jlsK";
    public static final String CITY = "%CE%E4%BA%BA";

    public static String getStr(int str) {
        return BaseApplication.getAppResources().getString(str);
    }

    public static String[] getStrArrays(int str) {
        return BaseApplication.getAppResources().getStringArray(str);
    }

}
