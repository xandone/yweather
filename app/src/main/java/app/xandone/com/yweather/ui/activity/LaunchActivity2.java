package app.xandone.com.yweather.ui.activity;

import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.umeng.message.inapp.InAppMessageManager;
import com.umeng.message.inapp.UmengSplashMessageActivity;


import app.xandone.com.yweather.R;
import butterknife.BindView;

/**
 * Created by Administrator on 2017/1/13.
 */
public class LaunchActivity2 extends UmengSplashMessageActivity {
    @BindView(R.id.launch_iv)
    ImageView launch_iv;

    private boolean isDownLoad;
    private RequestManager requestManager;

    @Override
    public boolean onCustomPretreatment() {
        InAppMessageManager mInAppMessageManager = InAppMessageManager.getInstance(this);
        //设置应用内消息为Debug模式
        mInAppMessageManager.setInAppMsgDebugMode(true);
        mInAppMessageManager.setMainActivityPath("app.xandone.com.yweather.MainActivity");
        return super.onCustomPretreatment();
    }

}
