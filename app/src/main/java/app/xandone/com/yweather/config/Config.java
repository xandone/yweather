package app.xandone.com.yweather.config;

import android.os.Environment;

import java.io.File;

/**
 * Created by xandone on 2017/1/22.
 */
public class Config {
    public static final String APP_NAME = "yweather";

    public static final String DEFAULT_SAVE_IMG_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator;

    public static final String APP_LOCATION_CITY = "app_location_city";


}
