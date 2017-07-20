package app.xandone.com.yweather.ui.activity;

import android.support.v7.widget.Toolbar;

import app.xandone.com.yweather.R;
import app.xandone.com.yweather.config.StringRes;
import app.xandone.com.yweather.ui.base.BaseActivity;
import butterknife.BindView;

/**
 * author: xandone
 * created on: 2017/7/20 13:54
 */

public class SetActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    Toolbar mToolbar;

    @Override
    public int setLayout() {
        return R.layout.act_set_layout;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        mToolbar.setTitle(StringRes.getStr(R.string.toolbar_title_set));
    }
}
