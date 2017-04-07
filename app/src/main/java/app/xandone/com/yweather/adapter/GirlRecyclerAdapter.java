package app.xandone.com.yweather.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;

import app.xandone.com.yweather.BaseApplication;
import app.xandone.com.yweather.R;
import app.xandone.com.yweather.bean.PicBean;
import app.xandone.com.yweather.ui.activity.GirlDetailsActivity;
import app.xandone.com.yweather.utils.ImageLoader;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by xandone on 2016/12/23.
 */
public class GirlRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int LOAD_MORE = 0;
    public static final int LOAD_PULL_TO = 1;
    public static final int LOAD_NONE = 2;
    public static final int LOAD_END = 3;

    public static final int TYPE_FOOT = 1;
    public static final int TYPE_NORMAL = 2;

    public static final String GIRLRECYCLERADAPTER_URL = "GirlRecyclerAdapter_url";

    private int mStatus = LOAD_PULL_TO;

    private List<PicBean> list;

    private List<PicBean> nowlist;
    private Context mContext;
    private RequestManager requestManager;

    private int mLastPosition = -1;

    public static final int ONECE_LOAD = 12;
    private int now_size = ONECE_LOAD;

    public GirlRecyclerAdapter(Context context, List list, List nowList) {
        this.mContext = context;
        this.list = list;
        this.nowlist = nowList;
        requestManager = Glide.with(BaseApplication.sContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL) {
            View root_view = LayoutInflater.from(mContext).inflate(R.layout.adapter_my_photo_item, parent, false);
            return new MyHolder(root_view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_footer, parent, false);
            return new FooterHoler(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder) {
            MyHolder myHolder = (MyHolder) holder;
            myHolder.bindItem(nowlist.get(position), position);
            showItemAnim(myHolder.girl_photo_item_iv, position);
        } else if (holder instanceof FooterHoler) {
            FooterHoler footerHoler = (FooterHoler) holder;
            footerHoler.bindItem();
        }
    }

    @Override
    public int getItemCount() {
        return nowlist.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == getItemCount() - 1) ? TYPE_FOOT : TYPE_NORMAL;
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
                            mLastPosition = position;
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

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.girl_photo_item_iv)
        ImageView girl_photo_item_iv;
        @BindView(R.id.girl_photo_item_rl)
        RelativeLayout girl_photo_item_rl;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bindItem(PicBean picBean, int position) {
            if (picBean == null) {
                return;
            }
            ImageLoader.loadImage(requestManager, girl_photo_item_iv, picBean.getUrl());
        }

        @OnClick({R.id.girl_photo_item_rl})
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.girl_photo_item_rl:
                    Intent intent = new Intent(mContext, GirlDetailsActivity.class);
                    intent.putExtra(GIRLRECYCLERADAPTER_URL, nowlist.get(getLayoutPosition()).getUrl());
                    mContext.startActivity(intent);
                    break;
            }
        }
    }

    class FooterHoler extends RecyclerView.ViewHolder {
        @BindView(R.id.view_footer_progress)
        ProgressBar view_footer_pb;
        @BindView(R.id.view_footer_tv)
        TextView view_foot_tv;

        public FooterHoler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bindItem() {
            switch (mStatus) {
                case LOAD_MORE:
                    view_footer_pb.setVisibility(View.VISIBLE);
                    view_foot_tv.setText("正在加载..");
                    itemView.setVisibility(View.VISIBLE);
                    break;
                case LOAD_PULL_TO:
                    view_footer_pb.setVisibility(View.GONE);
                    itemView.setVisibility(View.GONE);
                    break;
                case LOAD_NONE:
                    view_footer_pb.setVisibility(View.GONE);
                    view_foot_tv.setText("已无数据加载");
                    itemView.setVisibility(View.VISIBLE);
                    break;
                case LOAD_END:
                    itemView.setVisibility(View.GONE);
                    break;
            }
        }
    }

    public void updateLoadStatus(int status) {
        this.mStatus = status;
        notifyDataSetChanged();
    }

    public void addListPic() {
        now_size += ONECE_LOAD;
        if (now_size >= list.size()) {
            now_size = list.size();
        }
        for (int i = now_size - ONECE_LOAD; i < now_size; i++) {
            nowlist.add(list.get(i));
        }
        if (now_size >= list.size()) {
            updateLoadStatus(LOAD_NONE);
            return;
        }
        notifyDataSetChanged();
    }

    public int getmStatus() {
        return mStatus;
    }

    public void setmStatus(int mStatus) {
        this.mStatus = mStatus;
    }

}
