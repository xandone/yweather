package app.xandone.com.yweather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Map;

import app.xandone.com.yweather.R;
import app.xandone.com.yweather.bean.WeatherXmlData;
import app.xandone.com.yweather.ui.fragment.MainWeatherFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by xandone on 2016/12/23.
 */
public class FurtureRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Map<String, WeatherXmlData> map;
    //    private List<WeatherXmlData> list;
    private int mLastPosition = -1;
    private String[] date = {"今天", "明　天", "后　天", "大后天"};

    private Context mContext;
    private MainWeatherFragment mFragment;

    public static final int TYPE_TOP = 1;
    public static final int TYPE_NORMAL = 2;

    public FurtureRecyclerAdapter(MainWeatherFragment fragment, Context context, Map map) {
        this.mContext = context;
        this.mFragment = fragment;
        this.map = map;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TOP) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.weahter_day, parent, false);
            return new MyHolderTop(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.furture_weather_item, parent, false);
            return new MyHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String position_str = String.valueOf(position);
        if (holder instanceof MyHolderTop) {
            MyHolderTop myHolderTop = (MyHolderTop) holder;
            myHolderTop.bindItem(map.get(position_str), mFragment.getmTimeType());
        } else if (holder instanceof MyHolder) {
            MyHolder myHolder = (MyHolder) holder;
            myHolder.bindItem(map.get(position_str), position, mFragment.getmTimeType());
            showItemAnim(myHolder.future_weather_item_root, position);
        }
    }

    @Override
    public int getItemCount() {
        return map.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_TOP : TYPE_NORMAL;
    }

    public void showItemAnim(final View view, final int position) {
        if (position > mLastPosition) {
            view.setAlpha(0);
            view.post(new Runnable() {
                @Override
                public void run() {
                    Animation animation = AnimationUtils.loadAnimation(mContext,
                            R.anim.rv_item_anim);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            view.setAlpha(1);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    view.startAnimation(animation);
                }
            });

        }
    }

    class MyHolderTop extends RecyclerView.ViewHolder {
        @BindView(R.id.weather_day_feel_tv)
        TextView weather_day_feel_tv;
        @BindView(R.id.weather_day_clothes_tv)
        TextView weather_day_clothes_tv;
        @BindView(R.id.weather_day_wind_tv)
        TextView weather_day_wind_tv;
        @BindView(R.id.weather_day_pollution_tv)
        TextView weather_day_pollution_tv;
        @BindView(R.id.weather_day_ultraviolet_rays_tv)
        TextView weather_day_ultraviolet_rays_tv;
        @BindView(R.id.weather_day_wind_value_tv)
        TextView weather_day_wind_value_tv;
        @BindView(R.id.weather_day_ll)
        RelativeLayout weather_day_ll;
        @BindView(R.id.furture_day_icon)
        ImageView furture_day_icon;

        public MyHolderTop(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.weather_day_ll})
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.weather_day_ll:
                    setOnAnimInterf(mFragment);
                    break;
            }
        }

        public void bindItem(WeatherXmlData weatherXmlData, int timeType) {
            if (weatherXmlData == null) {
                return;
            }
            showWeatherInfo(weatherXmlData, timeType);
        }

        public void showWeatherInfo(WeatherXmlData weatherXmlData, int timeType) {
            if (weatherXmlData == null) {
                return;
            }
            if (timeType == MainWeatherFragment.TYPE_DAY) {
                weather_day_feel_tv.setText(weatherXmlData.getSsd_l());
                weather_day_clothes_tv.setText(weatherXmlData.getChy_shuoming());
                weather_day_wind_tv.setText(weatherXmlData.getDirection1());
                weather_day_wind_value_tv.setText(weatherXmlData.getPower1() + "级");
                weather_day_pollution_tv.setText(weatherXmlData.getPollution_l());
                weather_day_ultraviolet_rays_tv.setText(weatherXmlData.getZwx_l());
                furture_day_icon.setImageResource(R.drawable.day_icon);
            } else {
                weather_day_feel_tv.setText(weatherXmlData.getSsd_l());
                weather_day_clothes_tv.setText(weatherXmlData.getSsd_s());
                weather_day_wind_tv.setText(weatherXmlData.getDirection2());
                weather_day_wind_value_tv.setText(weatherXmlData.getPower2() + "级");
                weather_day_pollution_tv.setText(weatherXmlData.getPollution_s());
                weather_day_ultraviolet_rays_tv.setText(weatherXmlData.getZwx_s());
                furture_day_icon.setImageResource(R.drawable.night_icon);
            }
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.future_weather_item_root)
        RelativeLayout future_weather_item_root;
        @BindView(R.id.future_weather_item_date)
        TextView future_weather_item_date;
        @BindView(R.id.future_weather_item_weather_tv)
        TextView future_weather_item_weather_tv;
        @BindView(R.id.future_weather_item_temp_tv)
        TextView future_weather_item_temp_tv;
        @BindView(R.id.future_weather_item_wind_tv)
        TextView future_weather_item_wind_tv;
        @BindView(R.id.future_weather_item_feel_tv)
        TextView future_weather_item_feel_tv;
        @BindView(R.id.future_weather_item_day_icon)
        ImageView future_weather_item_day_icon;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bindItem(WeatherXmlData weatherXmlData, int position, int timeType) {
            if (weatherXmlData == null) {
                return;
            }
            future_weather_item_date.setText(date[position]);
            future_weather_item_weather_tv.setText(weatherXmlData.getStatus1());
            future_weather_item_temp_tv.setText(weatherXmlData.getTemperature2() + "~" + weatherXmlData.getTemperature1() + "℃");
            future_weather_item_wind_tv.setText(weatherXmlData.getDirection1() + weatherXmlData.getPower1() + "级");
            future_weather_item_feel_tv.setText(weatherXmlData.getSsd_l());
            if (timeType == MainWeatherFragment.TYPE_DAY) {
                future_weather_item_day_icon.setImageResource(R.drawable.day_icon);
            } else {
                future_weather_item_day_icon.setImageResource(R.drawable.night_icon);
            }
        }
    }

    public interface AnimInterf {
        void doSwitchAnim();
    }

    public void setOnAnimInterf(AnimInterf animInterf) {
        animInterf.doSwitchAnim();
    }

}
