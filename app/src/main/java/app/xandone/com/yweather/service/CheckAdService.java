package app.xandone.com.yweather.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.wilddog.client.DataSnapshot;
import com.wilddog.client.SyncError;
import com.wilddog.client.SyncReference;
import com.wilddog.client.ValueEventListener;
import com.wilddog.client.WilddogSync;
import com.wilddog.wilddogcore.WilddogApp;
import com.wilddog.wilddogcore.WilddogOptions;

import java.util.List;
import java.util.Map;

import app.xandone.com.yweather.BaseApplication;
import app.xandone.com.yweather.R;
import app.xandone.com.yweather.api.ApiConstants;
import app.xandone.com.yweather.bean.AdBean;
import app.xandone.com.yweather.bean.PicBean;
import app.xandone.com.yweather.config.StringRes;
import app.xandone.com.yweather.interf.DownLoadImgInterf;
import app.xandone.com.yweather.utils.DownLoadImg;
import app.xandone.com.yweather.utils.JsonUtils;
import app.xandone.com.yweather.utils.SpUtils;
import app.xandone.com.yweather.utils.StringUtils;
import app.xandone.com.yweather.utils.ToastUtils;

/**
 * Created by Administrator on 2017/4/10.
 */
public class CheckAdService extends Service {
    private WilddogOptions mWilddogOptions;
    private SyncReference mRef;
    private AdBean adBean;

    public static final String AD_IMG_KEY = "CheckAdService_ad_img_key ";
    public static final String AD_ISDOWN_KEY = "CheckAdService_ad_isdown_key";
    private boolean isDownLoad;

    @Override
    public void onCreate() {
        super.onCreate();
        mWilddogOptions = new WilddogOptions.Builder().setSyncUrl(ApiConstants.WD_URL).build();
        WilddogApp.initializeApp(BaseApplication.sContext, mWilddogOptions);
        mRef = WilddogSync.getInstance().getReference();

        getAdFromWdog(mRef);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void getAdFromWdog(SyncReference ref) {
        if (ref == null) {
            return;
        }
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> value = (Map<String, Object>) dataSnapshot.getValue();
                List<Object> list = (List<Object>) value.get("results");
                Map<String, String> o = (Map<String, String>) list.get(0);
                if (StringUtils.isEmpty(o.get("adurl"))) {
                    SpUtils.setSpBooleanData(AD_ISDOWN_KEY, false);
                    return;
                }
                adBean = new AdBean(o.get("adurl"));

                isDownLoad = SpUtils.getSpBooleanData(AD_ISDOWN_KEY);
                if (isDownLoad) {
                    //不再重复下载
                    return;
                }
                saveImg(adBean.getAdurl());
            }

            @Override
            public void onCancelled(SyncError syncError) {
                Log.d("xandone", syncError.toString());
            }
        });
    }

    public void saveImg(String img_url) {
        new Thread(new DownLoadImg(img_url, new DownLoadImgInterf() {
            @Override
            public void saveSuccess() {
                Message msg = Message.obtain();
                msg.what = 1;
                mHandle.sendMessage(msg);
            }

            @Override
            public void saveFail() {
                Message msg = Message.obtain();
                msg.what = 2;
                mHandle.sendMessage(msg);
            }
        })).start();
    }

    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                SpUtils.setSpBooleanData(AD_ISDOWN_KEY, true);
                ToastUtils.showShort(StringRes.getStr(R.string.download_success));
            } else if (msg.what == 2) {
                SpUtils.setSpBooleanData(AD_ISDOWN_KEY, false);
                ToastUtils.showShort(StringRes.getStr(R.string.download_error));
            }
        }
    };

}
