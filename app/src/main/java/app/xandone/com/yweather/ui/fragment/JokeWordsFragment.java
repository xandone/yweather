package app.xandone.com.yweather.ui.fragment;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
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
import app.xandone.com.yweather.adapter.JokeRecycleAdapter;
import app.xandone.com.yweather.api.ApiConstants;
import app.xandone.com.yweather.bean.JokerBean;
import app.xandone.com.yweather.ui.base.BaseFragment;
import app.xandone.com.yweather.utils.StringUtils;
import butterknife.BindView;

/**
 * Created by xandone on 2016/12/22.
 */
public class JokeWordsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.joke_words_swipe)
    SwipeRefreshLayout joke_words_swipe;
    @BindView(R.id.joke_words_recycle)
    RecyclerView joke_words_recycle;

    private LinearLayoutManager mLinearLayoutManager;
    private JokeRecycleAdapter mJokeRecycleAdapter;
    private List<JokerBean> jokeList = new ArrayList<>();

    private WilddogOptions mWilddogOptions;
    private SyncReference mRef;

    @Override
    protected int setLayout() {
        return R.layout.frag_main_joke_words;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        initWilddog();
        initJoke();
    }

    public void initJoke() {
        joke_words_swipe.setColorSchemeResources(R.color.refresh_progress_3, R.color.refresh_progress_2, R.color.refresh_progress_1);
        joke_words_swipe.setOnRefreshListener(this);

        mLinearLayoutManager = new LinearLayoutManager(BaseApplication.sContext);
        mJokeRecycleAdapter = new JokeRecycleAdapter(this, getActivity(), jokeList);
        joke_words_recycle.setLayoutManager(mLinearLayoutManager);
        joke_words_recycle.setAdapter(mJokeRecycleAdapter);

        getDataFromWdog(mRef);
        startLoadingDialog();
    }

    public void initWilddog() {
        mWilddogOptions = new WilddogOptions.Builder().setSyncUrl(ApiConstants.WD_URL).build();
        WilddogApp.initializeApp(BaseApplication.sContext, mWilddogOptions);
        mRef = WilddogSync.getInstance().getReference();
    }

    public void getDataFromWdog(SyncReference ref) {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> value = (Map<String, Object>) dataSnapshot.getValue();
                List<Object> list = (List<Object>) value.get("results");
                if (jokeList != null) {
                    jokeList.clear();
                }
                for (Object obj : list) {
                    Map<String, String> o = (Map<String, String>) obj;
                    if (!StringUtils.isEmpty(o.get("title"))) {
                        jokeList.add(new JokerBean(o.get("title"), o.get("content"), o.get("url")));
                    }
                }
                mJokeRecycleAdapter.notifyDataSetChanged();
                closeRefresh();
                stopLoadingDialog();
            }

            @Override
            public void onCancelled(SyncError syncError) {
                Log.d("xandone", syncError.toString());
                closeRefresh();
                stopLoadingDialog();
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
        joke_words_swipe.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (joke_words_swipe != null) {
                    joke_words_swipe.setRefreshing(false);
                }
            }
        }, 1200);
    }

}
