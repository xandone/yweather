package app.xandone.com.yweather.ui.activity;

import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import app.xandone.com.yweather.MainActivity;
import app.xandone.com.yweather.R;
import app.xandone.com.yweather.ui.base.BaseActivity;
import butterknife.BindView;

/**
 * Created by Administrator on 2017/1/13.
 */
public class LaunchActivity extends BaseActivity {
    @BindView(R.id.launch_iv)
    ImageView launch_iv;

    @Override
    public int setLayout() {
        return R.layout.activity_launch;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        launcAnim(launch_iv);
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
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
