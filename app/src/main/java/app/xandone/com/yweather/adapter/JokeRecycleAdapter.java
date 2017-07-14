package app.xandone.com.yweather.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;

import app.xandone.com.yweather.BaseApplication;
import app.xandone.com.yweather.MainActivity;
import app.xandone.com.yweather.R;
import app.xandone.com.yweather.bean.JokerBean;
import app.xandone.com.yweather.ui.activity.JokeDetailsActivity;
import app.xandone.com.yweather.utils.ImageLoader;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xandone on 2017/1/21.
 */
public class JokeRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<JokerBean> jokerBeanList;
    private Activity mContext;
    private RequestManager requestManager;
    private Fragment mFragment;
    private int mLastPosition = -1;
    public static final String JOKERECYCLEADAPTER_POSITION = "JokeRecycleAdapter_position";

    public JokeRecycleAdapter(Fragment fragment, Activity context, List<JokerBean> list) {
        this.mFragment = fragment;
        this.mContext = context;
        this.jokerBeanList = list;
        requestManager = Glide.with(BaseApplication.sContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.joke_recycle_item, parent, false);
        return new JokeHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof JokeHolder) {
            JokeHolder jokeHolder = (JokeHolder) holder;
            jokeHolder.bindItem(jokerBeanList.get(position), position);
            showItemAnim(jokeHolder.joke_item_root, position);
        }
    }

    @Override
    public int getItemCount() {
        return jokerBeanList.size();
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

    class JokeHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.joke_item_root)
        LinearLayout joke_item_root;
        @BindView(R.id.joke_item_title)
        TextView joke_item_title;
        @BindView(R.id.joke_item_content)
        TextView joke_item_content;
        @BindView(R.id.joke_item_img)
        ImageView joke_item_img;

        public JokeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindItem(JokerBean jokerBean, int position) {
            if (jokerBean == null) {
                return;
            }
            joke_item_title.setText(jokerBean.getTitle());
            joke_item_content.setText(jokerBean.getContent());
            ImageLoader.loadImage(requestManager, joke_item_img, jokerBean.getUrl());
        }

        @OnClick({R.id.joke_item_root})
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.joke_item_root:
                    Intent intent = new Intent(mContext, JokeDetailsActivity.class);
                    intent.putExtra(JOKERECYCLEADAPTER_POSITION, jokerBeanList.get(getLayoutPosition()));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mFragment.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mContext,
                                Pair.create((View) joke_item_title, "joke_item_title_trans"),
                                Pair.create((View) joke_item_content, "joke_item_content_trans"),
                                Pair.create((View) joke_item_img, "joke_item_img_trans")).toBundle());
                    } else {
                        mFragment.startActivity(intent);
                    }
                    break;
            }
        }
    }

}
