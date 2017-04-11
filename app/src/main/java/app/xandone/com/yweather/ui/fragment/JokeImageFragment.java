package app.xandone.com.yweather.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.wilddog.client.DataSnapshot;
import com.wilddog.client.SyncError;
import com.wilddog.client.SyncReference;
import com.wilddog.client.ValueEventListener;
import com.wilddog.client.WilddogSync;
import com.wilddog.wilddogcore.WilddogApp;
import com.wilddog.wilddogcore.WilddogOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.xandone.com.yweather.BaseApplication;
import app.xandone.com.yweather.R;
import app.xandone.com.yweather.adapter.JokeImageRecyclerAdapter;
import app.xandone.com.yweather.api.ApiConstants;
import app.xandone.com.yweather.bean.JokeImageBean;
import app.xandone.com.yweather.ui.base.BaseFragment;
import app.xandone.com.yweather.utils.StringUtils;
import butterknife.BindView;

/**
 * Created by xandone on 2017/1/22.
 */
public class JokeImageFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.joke_img_swipe)
    SwipeRefreshLayout joke_img_swipe;
    @BindView(R.id.joke_img_recycle)
    RecyclerView joke_img_recycle;

    private List<JokeImageBean> picList;
    private GridLayoutManager mGridLayoutManager;
    private JokeImageRecyclerAdapter mJokeImageRecyclerAdapter;

    private WilddogOptions mWilddogOptions;
    private SyncReference mRef;

    @Override
    protected int setLayout() {
        return R.layout.frag_main_jork_image;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        initWilddog();
        initPic();
    }

    public void initWilddog() {
        mWilddogOptions = new WilddogOptions.Builder().setSyncUrl(ApiConstants.WD_URL).build();
        WilddogApp.initializeApp(BaseApplication.sContext, mWilddogOptions);
        mRef = WilddogSync.getInstance().getReference();
    }

    public void initPic() {
        picList = new ArrayList<>();

        joke_img_swipe.setColorSchemeResources(R.color.refresh_progress_3, R.color.refresh_progress_2, R.color.refresh_progress_1);
        joke_img_swipe.setOnRefreshListener(this);

        mGridLayoutManager = new GridLayoutManager(BaseApplication.sContext, 3);
        mJokeImageRecyclerAdapter = new JokeImageRecyclerAdapter(getActivity(), picList);
        mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position == mJokeImageRecyclerAdapter.getItemCount() - 1) ? mGridLayoutManager.getSpanCount() : 1;
            }
        });

        joke_img_recycle.setLayoutManager(mGridLayoutManager);
        joke_img_recycle.setAdapter(mJokeImageRecyclerAdapter);

        getDataFromWdog(mRef);
    }

    public void getDataFromWdog(SyncReference ref) {
        if (ref == null) {
            return;
        }
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> value = (Map<String, Object>) dataSnapshot.getValue();
                List<Object> list = (List<Object>) value.get("results");
                picList.clear();
                for (Object obj : list) {
                    Map<String, String> o = (Map<String, String>) obj;
                    if (!StringUtils.isEmpty(o.get("joke_img"))) {
                        picList.add(new JokeImageBean(o.get("joke_img")));
                    }
                }
                mJokeImageRecyclerAdapter.notifyDataSetChanged();
                closeRefresh();
            }

            @Override
            public void onCancelled(SyncError syncError) {
                Log.d("xandone", syncError.toString());
                closeRefresh();
            }
        });
    }

    @Override
    public void onRefresh() {
        getDataFromWdog(mRef);
    }

    /**
     * 关闭刷新
     */
    public void closeRefresh() {
        joke_img_swipe.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (joke_img_swipe != null) {
                    joke_img_swipe.setRefreshing(false);
                }
            }
        }, 1200);
    }
}
