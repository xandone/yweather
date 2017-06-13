package app.xandone.com.yweather.ui.activity;

import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.umeng.message.inapp.InAppMessageManager;
import com.umeng.message.inapp.UmengSplashMessageActivity;

import java.io.File;

import app.xandone.com.yweather.BaseApplication;
import app.xandone.com.yweather.MainActivity;
import app.xandone.com.yweather.R;
import app.xandone.com.yweather.service.CheckAdService;
import app.xandone.com.yweather.utils.SpUtils;
import butterknife.BindView;

/**
 * Created by Administrator on 2017/1/13.
 */
public class LaunchActivity extends UmengSplashMessageActivity {
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

    //    @Override
//    public int setLayout() {
//        return R.layout.activity_launch;
//    }
//
//    @Override
//    public void initPresenter() {
//
//    }
//
//    @Override
    public void initView() {
        requestManager = Glide.with(BaseApplication.sContext);
        initAd();
        launcAnim(launch_iv);
    }

    public void initService() {
        Intent intent = new Intent(this, CheckAdService.class);
        startService(intent);
    }

    public void initAd() {
        isDownLoad = SpUtils.getSpBooleanData(CheckAdService.AD_ISDOWN_KEY);
        if (isDownLoad) {
            File file = new File(SpUtils.getSpStringData(CheckAdService.AD_IMG_KEY));
            if (!file.exists()) {
                return;
            }
            requestManager.load(file).into(launch_iv);
        }
    }

    public void launcAnim(View v) {
        Animation anim = new ScaleAnimation(1, 1.2f, 1, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(2000);
        anim.setFillAfter(true);
        v.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                initService();
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
