package app.xandone.com.yweather;

import android.content.res.Resources;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

/**
 * Created by xandone on 2016/12/21.
 */
public class BaseApplication extends MultiDexApplication {
    public static BaseApplication sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;

        registUmengPush();
    }

    public static Resources getAppResources() {
        return sContext.getResources();
    }

    public void registUmengPush() {
        PushAgent mPushAgent = PushAgent.getInstance(this);
//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.d("xxxnnnn", deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });
    }

}
