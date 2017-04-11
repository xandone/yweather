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

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;

import app.xandone.com.yweather.BaseApplication;
import app.xandone.com.yweather.R;
import app.xandone.com.yweather.bean.JokeImageBean;
import app.xandone.com.yweather.ui.activity.JokeImageDetailsActivity;
import app.xandone.com.yweather.utils.ImageLoader;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by xandone on 2016/12/23.
 */
public class JokeImageRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String JOKEIMAGERECYCLERADAPTER_URL = "JokeImageRecyclerAdapter_url";
    public static final int TYPE_FOOT = 1;
    public static final int TYPE_NORMAL = 2;

    private List<JokeImageBean> list;
    private Context mContext;
    private RequestManager requestManager;
    private int mLastPosition = -1;

    public JokeImageRecyclerAdapter(Context context, List list) {
        this.mContext = context;
        this.list = list;
        requestManager = Glide.with(BaseApplication.sContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL) {
            View root_view = LayoutInflater.from(mContext).inflate(R.layout.adapter_joke_image_item, parent, false);
            return new MyHolder(root_view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_joke_footer, parent, false);
            return new FooterHoler(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder) {
            MyHolder myHolder = (MyHolder) holder;
            myHolder.bindItem(list.get(position));
            showItemAnim(myHolder.joke_photo_item_iv, position);
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
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
        @BindView(R.id.joke_photo_item_iv)
        ImageView joke_photo_item_iv;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bindItem(JokeImageBean jokeImageBean) {
            if (jokeImageBean == null) {
                return;
            }
            ImageLoader.loadImage(requestManager, joke_photo_item_iv, jokeImageBean.getJoke_img());
        }

        @OnClick({R.id.joke_photo_item_rl})
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.joke_photo_item_rl:
                    Intent intent = new Intent(mContext, JokeImageDetailsActivity.class);
                    intent.putExtra(JOKEIMAGERECYCLERADAPTER_URL, list.get(getLayoutPosition()).getJoke_img());
                    mContext.startActivity(intent);
                    break;
            }
        }
    }

    class FooterHoler extends RecyclerView.ViewHolder {
        public FooterHoler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
