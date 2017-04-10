package app.xandone.com.yweather.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.xandone.com.yweather.BaseApplication;
import app.xandone.com.yweather.R;
import app.xandone.com.yweather.config.Config;
import app.xandone.com.yweather.ui.activity.ChooseCityActivity;
import app.xandone.com.yweather.ui.fragment.MainWeatherFragment;
import app.xandone.com.yweather.utils.SpUtils;
import app.xandone.com.yweather.utils.StringUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddressListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> list;
    private ChooseCityActivity mActivity;
    private int current_type;

    public static final int TYPE_P = 1;
    public static final int TYPE_C = 2;

    public AddressListAdapter(List list, Activity activity, int type) {
        this.list = list;
        this.mActivity = (ChooseCityActivity) activity;
        this.current_type = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(BaseApplication.sContext).inflate(R.layout.address_list_item, parent, false);
        return new AddressHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AddressHolder) {
            AddressHolder addressHolder = (AddressHolder) holder;
            addressHolder.bindView(position);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class AddressHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.addressItem_tv)
        TextView addressItem_tv;

        public AddressHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(int positon) {
            if (StringUtils.isEmpty(list.get(positon))) {
                return;
            }
            addressItem_tv.setText(list.get(positon));
        }

        @OnClick({R.id.addressItem_root})
        public void click(View view) {
            switch (view.getId()) {
                case R.id.addressItem_root:
                    switch (current_type) {
                        case TYPE_P:
                            setOnGlideRecyclerListener(mActivity, getLayoutPosition());
                            break;
                        case TYPE_C:
                            SpUtils.setSpStringData(Config.APP_LOCATION_CITY, list.get(getLayoutPosition()));
                            Intent intent = new Intent(MainWeatherFragment.ACTION_CAST);
                            intent.putExtra(MainWeatherFragment.ACTION_CAST_KEY, list.get(getLayoutPosition()));
                            mActivity.sendBroadcast(intent);
                            mActivity.finish();
                            break;
                    }
                    break;
            }
        }
    }

    public interface OnGlideRecyclerListener {
        void OnGlide(int position);
    }

    public void setOnGlideRecyclerListener(OnGlideRecyclerListener onGlideRecyclerListener, int position) {
        onGlideRecyclerListener.OnGlide(position);
    }
}
