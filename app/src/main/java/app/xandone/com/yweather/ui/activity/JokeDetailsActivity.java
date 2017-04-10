package app.xandone.com.yweather.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import app.xandone.com.yweather.R;
import app.xandone.com.yweather.adapter.JokeRecycleAdapter;
import app.xandone.com.yweather.bean.JokerBean;
import app.xandone.com.yweather.config.StringRes;
import app.xandone.com.yweather.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xandone on 2017/1/21.
 */
public class JokeDetailsActivity extends BaseActivity {
    @BindView(R.id.joke_details_title)
    TextView joke_details_title;
    @BindView(R.id.joke_details_content)
    TextView joke_details_content;
    @BindView(R.id.joke_details_comeOn_icon)
    ImageView joke_details_comeOn_icon;
    @BindView(R.id.toolBar)
    Toolbar mToolbar;

    private JokerBean mJokerBean;
    private String[] mComeOnStringList;
    private Random mRandom;

    @Override
    public int setLayout() {
        return R.layout.activity_joke_details;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        mComeOnStringList = StringRes.getStrArrays(R.array.come_on_string);
        mRandom = new Random();
        mToolbar.setTitle(StringRes.getStr(R.string.toolbar_title_joke));
        getDetailData();
    }

    public void getDetailData() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        mJokerBean = (JokerBean) intent.getSerializableExtra(JokeRecycleAdapter.JOKERECYCLEADAPTER_POSITION);
        if (mJokerBean == null) {
            return;
        }
        joke_details_title.setText(mJokerBean.getTitle());
        joke_details_content.setText(mJokerBean.getContent());
    }

    @OnClick({R.id.joke_details_comeOn_icon})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.joke_details_comeOn_icon:
                showImgToast(mComeOnStringList[mRandom.nextInt(mComeOnStringList.length)], R.drawable.come_on_icon);
                break;
        }
    }
}
