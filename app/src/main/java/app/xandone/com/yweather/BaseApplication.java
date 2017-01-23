package app.xandone.com.yweather;

import android.app.Application;
import android.content.res.Resources;

/**
 * Created by xandone on 2016/12/21.
 */
public class BaseApplication extends Application {
    public static BaseApplication sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Resources getAppResources() {
        return sContext.getResources();
    }

}
