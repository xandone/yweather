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

    //fire.im的token
    public static  String API_FIRE_TOKEN = "9ef5d583593b651eba49591ca564aa5e";
    //fire.im的应用id
    public static  String APP_FIRE_ID = "596c297fca87a86e680003b2";


}
