package app.xandone.com.yweather;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import app.xandone.com.yweather.ui.base.BaseActivity;
import app.xandone.com.yweather.ui.fragment.LeftSlideFragment;
import app.xandone.com.yweather.ui.fragment.MainGirlFragment;
import app.xandone.com.yweather.ui.fragment.MainJokeFragment;
import app.xandone.com.yweather.ui.fragment.MainWeatherFragment;
import app.xandone.com.yweather.utils.ToastUtils;
import butterknife.BindView;

public class MainActivity extends BaseActivity implements LeftSlideFragment.OnCloseDrawerLayout {
    @BindView(R.id.main_foot_rg)
    RadioGroup main_foot_rg;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    @BindView(R.id.main_frame)
    FrameLayout main_frame;

    private int mFragIndex;
    private Fragment mCurrentFrag;
    private List<Fragment> fragList;
    private boolean isState = true;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        main_frame.post(new Runnable() {
            @Override
            public void run() {

            }
        });
        mFragIndex = 0;
        fragList = new ArrayList<Fragment>(Arrays.asList(new MainWeatherFragment(), new MainGirlFragment(), new MainJokeFragment()));
        turnToFrag();

        main_foot_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.mian_footer_care_rb:
                        mFragIndex = 0;
                        break;
                    case R.id.mian_footer_video_rb:
                        mFragIndex = 1;
                        break;
                    case R.id.mian_footer_home_rb:
                        mFragIndex = 2;
                        break;
                }
                turnToFrag();
            }
        });
    }

    public void turnToFrag() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment toFragment = fragList.get(mFragIndex);
        if (mCurrentFrag != null) {
            ft.hide(mCurrentFrag);
        }
        if (toFragment.isAdded()) {
            ft.show(toFragment);
        } else {
            ft.add(R.id.main_frame, toFragment);
        }
        ft.commitAllowingStateLoss();
        mCurrentFrag = toFragment;
    }

    @Override
    public void OnClose() {
        drawerlayout.closeDrawers();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerlayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (isState) {
            isState = false;
            ToastUtils.showShort("再按一次退出");
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isState = true;
                }
            }, 2000);
        } else {
            AppManager.newInstance().finishAllActivity();
        }
    }
}
