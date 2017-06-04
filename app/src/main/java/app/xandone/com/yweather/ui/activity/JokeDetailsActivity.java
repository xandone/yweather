package app.xandone.com.yweather.ui.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.Random;

import app.xandone.com.yweather.BaseApplication;
import app.xandone.com.yweather.R;
import app.xandone.com.yweather.adapter.JokeRecycleAdapter;
import app.xandone.com.yweather.bean.JokerBean;
import app.xandone.com.yweather.config.StringRes;
import app.xandone.com.yweather.ui.base.BaseActivity;
import app.xandone.com.yweather.utils.ImageLoader;
import app.xandone.com.yweather.utils.Utils;
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
    @BindView(R.id.joke_details_content_date)
    TextView joke_details_content_date;
    @BindView(R.id.toolBar)
    Toolbar mToolbar;
    @BindView(R.id.joke_details_title_sv)
    ScrollView joke_details_title_sv;
    @BindView(R.id.joke_details_title_img)
    ImageView joke_details_title_img;

    private JokerBean mJokerBean;
    private String[] mComeOnStringList;
    private Random mRandom;
    public static final float SCALE_VALUE = 0.6f;
    private int img_w, img_h;

    private DisplayMetrics metric;
    private float mFirstPosition = 0;
    private Boolean mScaling = false;
    private RequestManager requestManager;

    @Override
    public int setLayout() {
        return R.layout.activity_joke_details;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        requestManager = Glide.with(BaseApplication.sContext);
        mComeOnStringList = StringRes.getStrArrays(R.array.come_on_string);
        mRandom = new Random();
        mToolbar.setTitle(StringRes.getStr(R.string.toolbar_title_joke));
        joke_details_title_img.post(new Runnable() {
            @Override
            public void run() {
                img_w = joke_details_title_img.getWidth();
                img_h = joke_details_title_img.getHeight();
            }
        });
        getDetailData();

        metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);

        initEvent();
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
        joke_details_content_date.setText(mJokerBean.getMydate());
        ImageLoader.loadImage(requestManager, joke_details_title_img, mJokerBean.getUrl());
    }

    public void initEvent() {
        joke_details_title_sv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ViewGroup.LayoutParams lp = joke_details_title_img.getLayoutParams();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        mScaling = false;
                        animImg();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (!mScaling) {
                            if (joke_details_title_sv.getScrollY() == 0) {
                                mFirstPosition = event.getY();
                            } else {
                                break;
                            }
                        }
                        int distance = (int) ((event.getY() - mFirstPosition) * SCALE_VALUE);
                        if (distance < 0) {
                            break;
                        }
                        mScaling = true;
                        lp.width = img_w + distance;
                        lp.height = img_h + distance;
                        joke_details_title_img.setLayoutParams(lp);
                        return true;
                }
                return false;
            }
        });
    }

    public void animImg() {
        final ViewGroup.LayoutParams lp = joke_details_title_img.getLayoutParams();
        final float w = lp.width;
        final float h = lp.height;
        final ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0.0f, 1.0f).setDuration(200);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                lp.width = (int) (w - (w - img_w) * value);
                lp.height = (int) (h - (h - img_h) * value);
                joke_details_title_img.setLayoutParams(lp);
            }
        });
        valueAnimator.start();
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
