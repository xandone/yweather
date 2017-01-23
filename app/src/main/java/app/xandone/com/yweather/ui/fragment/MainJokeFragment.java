package app.xandone.com.yweather.ui.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.xandone.com.yweather.R;
import app.xandone.com.yweather.adapter.JokeViewPagerAdapter;
import app.xandone.com.yweather.config.StringRes;
import app.xandone.com.yweather.ui.base.BaseFragment;
import butterknife.BindView;

/**
 * Created by xandone on 2016/12/22.
 */
public class MainJokeFragment extends BaseFragment {
    @BindView(R.id.joke_viewPager)
    ViewPager joke_viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.tab_toolBar)
    Toolbar tab_toolBar;

    private JokeViewPagerAdapter mJokeViewPagerAdapter;
    private List<String> titleList = new ArrayList<>(Arrays.asList("文字", "图片"));
    private List<Fragment> fragList = new ArrayList<Fragment>(Arrays.asList(new JokeWordsFragment(), new JokeImageFragment()));

    @Override
    protected int setLayout() {
        return R.layout.frag_main_joke;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        tab_toolBar.setTitle(StringRes.getStr(R.string.toolbar_title_joke));

        mJokeViewPagerAdapter = new JokeViewPagerAdapter(getActivity().getSupportFragmentManager(), fragList, titleList);
        joke_viewPager.setAdapter(mJokeViewPagerAdapter);
        tabLayout.setupWithViewPager(joke_viewPager);
        joke_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
