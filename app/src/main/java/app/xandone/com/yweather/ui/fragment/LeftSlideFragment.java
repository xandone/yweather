package app.xandone.com.yweather.ui.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import app.xandone.com.yweather.AppManager;
import app.xandone.com.yweather.MainActivity;
import app.xandone.com.yweather.R;
import app.xandone.com.yweather.ui.activity.ChooseCityActivity;
import app.xandone.com.yweather.ui.base.BaseFragment;
import app.xandone.com.yweather.utils.ToastUtils;
import butterknife.BindView;
import butterknife.OnClick;

public class LeftSlideFragment extends BaseFragment {
    @BindView(R.id.menu_1)
    LinearLayout menu_1;

    private MainActivity mActivity;

    @Override
    protected int setLayout() {
        return R.layout.frag_leftslide_layout;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        mActivity = (MainActivity) getActivity();
    }

    @OnClick({R.id.menu_1, R.id.menu_2, R.id.foot_menu_set, R.id.foot_menu_exit})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.menu_1:
                mActivity.startActivity(new Intent(mActivity, ChooseCityActivity.class));
                break;
            case R.id.menu_2:
                ToastUtils.showShort("关于...");
                break;
            case R.id.foot_menu_set:
                ToastUtils.showShort("设置...");
                break;
            case R.id.foot_menu_exit:
                AppManager.newInstance().finishAllActivity();
                break;
        }
        setOnCloseDrawerLayout(mActivity);
    }

    public interface OnCloseDrawerLayout {
        void OnClose();
    }

    public void setOnCloseDrawerLayout(OnCloseDrawerLayout onCloseDrawerLayout) {
        onCloseDrawerLayout.OnClose();
    }

}