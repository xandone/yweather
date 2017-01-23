package app.xandone.com.yweather.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by xandone on 2017/1/22.
 */
public class JokeViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragList;
    private List<String> titleList;

    public JokeViewPagerAdapter(FragmentManager fm, List<Fragment> fragList, List<String> titleList) {
        super(fm);
        this.fragList = fragList;
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }

    @Override
    public int getCount() {
        return fragList.size();
    }

    public void addToTab(Fragment fragment, String title) {
        if (fragList == null || titleList == null) {
            return;
        }
        fragList.add(fragment);
        titleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
