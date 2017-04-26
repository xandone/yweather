package app.xandone.com.yweather.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import app.xandone.com.yweather.R;
import app.xandone.com.yweather.adapter.BasePagerAdapter;
import app.xandone.com.yweather.adapter.JokeImageRecyclerAdapter;
import app.xandone.com.yweather.bean.JokeImageBean;
import app.xandone.com.yweather.ui.base.BaseActivity;
import app.xandone.com.yweather.ui.fragment.JokePagerFragment;
import app.xandone.com.yweather.widget.YViewPager;
import butterknife.BindView;

/**
 * Created by xandone on 2017/4/26.
 */

public class JokePagerActivity extends BaseActivity {
    @BindView(R.id.joke_pager_vp)
    YViewPager joke_pager_vp;

    private List<Fragment> fragmentList;
    private List<JokeImageBean> jokeImageBeanList;
    private int mPosition;
    private BasePagerAdapter basePagerAdapter;

    @Override
    public int setLayout() {
        return R.layout.activity_joke_pager;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        jokeImageBeanList = (List<JokeImageBean>) intent.getSerializableExtra(JokeImageRecyclerAdapter.JOKEIMAGERECYCLERADAPTER_URL);
        mPosition = intent.getIntExtra(JokeImageRecyclerAdapter.JOKEIMAGERECYCLERADAPTER_NUM, 0);
        initFrags(jokeImageBeanList, mPosition);
        initPager();
    }


    public void initFrags(List<JokeImageBean> list, int position) {
        fragmentList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            JokePagerFragment jokePagerFragment = new JokePagerFragment();
            Bundle bundle = new Bundle();
            bundle.putString(JokeImageRecyclerAdapter.JOKEIMAGERECYCLERADAPTER_URL, list.get(i).getJoke_img());
            jokePagerFragment.setArguments(bundle);
            fragmentList.add(jokePagerFragment);
        }
    }

    public void initPager() {
        basePagerAdapter = new BasePagerAdapter(getSupportFragmentManager(), fragmentList);
        joke_pager_vp.setAdapter(basePagerAdapter);
        joke_pager_vp.setCurrentItem(mPosition);
    }

}
