package app.xandone.com.yweather;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.xandone.com.yweather.ui.base.BaseActivity;
import app.xandone.com.yweather.ui.fragment.MainWeatherFragment;
import app.xandone.com.yweather.ui.fragment.MainJokeFragment;
import app.xandone.com.yweather.ui.fragment.MainGirlFragment;
import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.main_foot_rg)
    RadioGroup main_foot_rg;

    private int mFragIndex;
    private Fragment mCurrentFrag;
    private List<Fragment> fragList;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
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
}
