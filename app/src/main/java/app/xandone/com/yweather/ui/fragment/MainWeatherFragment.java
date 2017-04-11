package app.xandone.com.yweather.ui.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import app.xandone.com.yweather.BaseApplication;
import app.xandone.com.yweather.MainActivity;
import app.xandone.com.yweather.R;
import app.xandone.com.yweather.adapter.FurtureRecyclerAdapter;
import app.xandone.com.yweather.bean.WeatherXmlData;
import app.xandone.com.yweather.config.Config;
import app.xandone.com.yweather.config.StringRes;
import app.xandone.com.yweather.ui.base.BaseFragment;
import app.xandone.com.yweather.ui.baserx.RxSubscriber;
import app.xandone.com.yweather.ui.contract.WeatherDataContract;
import app.xandone.com.yweather.ui.model.WeatherDataModel;
import app.xandone.com.yweather.ui.presenter.WeatherDataPresenter;
import app.xandone.com.yweather.utils.SpUtils;
import app.xandone.com.yweather.utils.StringUtils;
import app.xandone.com.yweather.widget.LoadingLayout;
import app.xandone.com.yweather.widget.Rotate3dAnimation;
import butterknife.BindView;

/**
 * Created by xandone on 2016/12/22.
 */
public class MainWeatherFragment extends BaseFragment<WeatherDataPresenter, WeatherDataModel>
        implements WeatherDataContract.View, SwipeRefreshLayout.OnRefreshListener, FurtureRecyclerAdapter.AnimInterf {
    @BindView(R.id.care_refresh)
    SwipeRefreshLayout care_refresh;
    @BindView(R.id.toolBar)
    Toolbar mToolbar;
    @BindView(R.id.current_temp_tv)
    TextView current_temp_tv;
    @BindView(R.id.high_temp_tv)
    TextView high_temp_tv;
    @BindView(R.id.low_temp_tv)
    TextView low_temp_tv;
    @BindView(R.id.current_weather_icon)
    ImageView current_weather_icon;
    @BindView(R.id.weather_title_rl)
    RelativeLayout weather_title_rl;
    @BindView(R.id.weather_title_bg_iv)
    ImageView weather_title_bg_iv;
    @BindView(R.id.future_weather_recycle)
    RecyclerView future_weather_recycle;
    @BindView(R.id.current_temp_city)
    TextView current_temp_city;
    @BindView(R.id.future_weather_loading)
    LoadingLayout future_weather_loading;

    public static final int TYPE_DAY = 0;
    public static final int TYPE_NIGHT = 1;
    private int mTimeType = TYPE_DAY;

    private Rotate3dAnimation mRotation;
    private AlphaAnimation mAlphaAnimationDay;
    private AlphaAnimation mAlphaAnimationNight;

    private String current_city;
    private String current_city_code;
    private FurtureRecyclerAdapter mFurtureRecyclerAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private Map<String, WeatherXmlData> futureMap = new HashMap<>();
    private MyBroadCast myBroadCast;
    private MainActivity mActivity;

    private static final int MAX_LENGTH = 4;
    public static final String ACTION_CAST = "action_cast";
    public static final String ACTION_CAST_KEY = "action_cast_key";

    @Override
    protected int setLayout() {
        return R.layout.frag_main_weather;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        mActivity = (MainActivity) getActivity();
        care_refresh.setColorSchemeResources(R.color.refresh_progress_3, R.color.refresh_progress_2, R.color.refresh_progress_1);
        care_refresh.setOnRefreshListener(this);
        mActivity.setSupportActionBar(mToolbar);
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivity.getSupportActionBar().setHomeButtonEnabled(true);
        mToolbar.setTitle(StringRes.getStr(R.string.toolbar_title_yweather));
        mToolbar.setNavigationIcon(R.drawable.icon_more);

        mFurtureRecyclerAdapter = new FurtureRecyclerAdapter(this, BaseApplication.sContext, futureMap);
        mLinearLayoutManager = new LinearLayoutManager(BaseApplication.sContext);
        future_weather_recycle.setLayoutManager(mLinearLayoutManager);
        future_weather_recycle.setAdapter(mFurtureRecyclerAdapter);

        myBroadCast = new MyBroadCast();
        IntentFilter ifilter = new IntentFilter();
        ifilter.addAction(ACTION_CAST);
        getActivity().registerReceiver(myBroadCast, ifilter);

        future_weather_recycle.post(new Runnable() {
            @Override
            public void run() {
                mAlphaAnimationDay = new AlphaAnimation(1, 0);
                mAlphaAnimationNight = new AlphaAnimation(0, 1);
//                mScaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                mRotation = new Rotate3dAnimation(0, 180, future_weather_recycle.getWidth() / 2, future_weather_recycle.getHeight() / 2, 310.0f, true);

                mRotation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        showWeather(futureMap.get("0"), mTimeType);
                        mFurtureRecyclerAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                current_city = SpUtils.getSpStringData(Config.APP_LOCATION_CITY, StringRes.CITY_NAME);
                future_weather_loading.setLoadingTips(LoadingLayout.LoadStatus.loading);
                requestNetWeather(MAX_LENGTH);
            }
        });
    }

    /**
     * 请求最近四天的天气数据
     *
     * @param len
     */
    public void requestNetWeather(int len) {
        current_temp_city.setText(current_city);
        try {
            current_city_code = URLEncoder.encode(current_city, "gb2312").substring(0, 12);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            future_weather_loading.setLoadingTips(LoadingLayout.LoadStatus.empty);
        }
        for (int i = 0; i < len; i++) {
            mPresenter.requestWeatherXmlData(current_city_code, StringRes.PASSWORD, String.valueOf(i));
        }
    }

    /**
     * 数据结果
     *
     * @param weatherXmlData
     * @param day
     */
    @Override
    public void returnWeatherXmlData(WeatherXmlData weatherXmlData, String day) {
        futureMap.put(day, weatherXmlData);
        if (futureMap.get("0") != null) {
            future_weather_loading.setLoadingTips(LoadingLayout.LoadStatus.finish);
            future_weather_recycle.setVisibility(View.VISIBLE);
            showWeather(futureMap.get("0"), mTimeType);
        } else {
            future_weather_loading.setLoadingTips(LoadingLayout.LoadStatus.empty);
            future_weather_recycle.setVisibility(View.INVISIBLE);
        }
        mFurtureRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading(String title) {
    }

    @Override
    public void stopLoading() {
        closeRefresh();
    }

    @Override
    public void showErrorTip(int msg) {
        closeRefresh();
        switch (msg) {
            case RxSubscriber.ERROR_NET:
                future_weather_loading.setLoadingTips(LoadingLayout.LoadStatus.error);
                future_weather_recycle.setVisibility(View.INVISIBLE);
                break;
//            case RxSubscriber.ERROR_SERVER:
//                future_weather_loading.setLoadingTips(LoadingLayout.LoadStatus.serverError);
//                break;
//            case RxSubscriber.ERROR_OTHER:
//                future_weather_loading.setLoadingTips(LoadingLayout.LoadStatus.serverError);
//                break;
        }
    }

    /**
     * 当天天气数据
     *
     * @param weatherXmlData
     */
    public void showWeather(WeatherXmlData weatherXmlData, int timeType) {
        if (weatherXmlData == null) {
            return;
        }
        if (timeType == TYPE_DAY) {
            String myWeather = weatherXmlData.getStatus1();
            if (StringUtils.isEmpty(myWeather)) {
                return;
            }
            int status = StringUtils.findIndex(StringRes.WEATHER_STATUS, myWeather);
            if (status == -1) {
                current_weather_icon.setImageResource(R.drawable.ic_launcher);
            }
            current_weather_icon.setImageResource(StringRes.WEATHER_STATUS_ICON[status]);
            current_temp_tv.setText(weatherXmlData.getStatus1());
            high_temp_tv.setText(weatherXmlData.getTemperature1() + "℃");
            low_temp_tv.setText(weatherXmlData.getTemperature2() + "℃");
            high_temp_tv.setTextColor(ContextCompat.getColor(BaseApplication.sContext, R.color.light_tv_color));
            low_temp_tv.setTextColor(ContextCompat.getColor(BaseApplication.sContext, R.color.light_tv_color));
        } else {
            String myWeather = weatherXmlData.getStatus2();
            if (StringUtils.isEmpty(myWeather)) {
                return;
            }
            int status = StringUtils.findIndex(StringRes.WEATHER_STATUS, myWeather);
            if (status == -1) {
                current_weather_icon.setImageResource(R.drawable.ic_launcher);
            }
            current_weather_icon.setImageResource(StringRes.WEATHER_STATUS_ICON[status]);
            current_temp_tv.setText(weatherXmlData.getStatus2());
            high_temp_tv.setText(weatherXmlData.getTemperature1() + "℃");
            low_temp_tv.setText(weatherXmlData.getTemperature2() + "℃");
            high_temp_tv.setTextColor(ContextCompat.getColor(BaseApplication.sContext, R.color.white));
            low_temp_tv.setTextColor(ContextCompat.getColor(BaseApplication.sContext, R.color.white));
        }
    }

    /**
     * 翻转动画
     *
     * @param iv
     */
    public void doAnim(View iv) {
        mAlphaAnimationDay.setDuration(2000);
        mAlphaAnimationDay.setFillAfter(true);
        mAlphaAnimationNight.setDuration(2000);
        mAlphaAnimationNight.setFillAfter(true);
        mRotation.setDuration(500);
        mRotation.setInterpolator(new AccelerateInterpolator());
        iv.startAnimation(mRotation);
        weather_title_bg_iv.setVisibility(View.VISIBLE);
        if (mTimeType == TYPE_DAY) {
            weather_title_bg_iv.startAnimation(mAlphaAnimationDay);
        } else {
            weather_title_bg_iv.startAnimation(mAlphaAnimationNight);
        }

    }

    /**
     * 切换白天黑夜
     */
    public void switchType() {
        if (mTimeType == TYPE_DAY) {
            mTimeType = TYPE_NIGHT;
        } else {
            mTimeType = TYPE_DAY;
        }
    }

    @Override
    public void onRefresh() {
        requestNetWeather(MAX_LENGTH);
    }

    /**
     * 关闭刷新
     */
    public void closeRefresh() {
        care_refresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (care_refresh != null) {
                    care_refresh.setRefreshing(false);
                }
            }
        }, 2000);
    }

    @Override
    public void doSwitchAnim() {
        switchType();
        doAnim(future_weather_recycle);
    }

    public int getmTimeType() {
        return mTimeType;
    }

    public void setmTimeType(int mTimeType) {
        this.mTimeType = mTimeType;
    }

    class MyBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            current_city = intent.getStringExtra(MainWeatherFragment.ACTION_CAST_KEY);
            requestNetWeather(MAX_LENGTH);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(myBroadCast);
    }
}
