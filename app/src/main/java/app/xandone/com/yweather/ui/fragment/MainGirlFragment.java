package app.xandone.com.yweather.ui.fragment;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import app.xandone.com.yweather.adapter.GirlRecyclerAdapter;
import app.xandone.com.yweather.api.ApiConstants;
import app.xandone.com.yweather.bean.PicBean;
import app.xandone.com.yweather.config.StringRes;
import app.xandone.com.yweather.ui.base.BaseFragment;
import butterknife.BindView;

/**
 * Created by xandone on 2016/12/22.
 */
public class MainGirlFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.girl_swipe)
    SwipeRefreshLayout girl_swipe;
    @BindView(R.id.girl_recycle)
    RecyclerView girl_recycle;
    @BindView(R.id.toolBar)
    Toolbar mToolbar;

    private List<PicBean> picList;
    private List<PicBean> picNowList;
    private GridLayoutManager mGridLayoutManager;
    private GirlRecyclerAdapter mGirlRecyclerAdapter;
    private int mLastVisibleItem = 0;

    private WilddogOptions mWilddogOptions;
    private SyncReference mRef;

    private boolean isInit = true;

    @Override
    protected int setLayout() {
        return R.layout.frag_main_girl;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        initWilddog();
        initPic();
        initRecycelEvent();
    }

    public void initWilddog() {
        mWilddogOptions = new WilddogOptions.Builder().setSyncUrl(ApiConstants.WD_URL).build();
        WilddogApp.initializeApp(BaseApplication.sContext, mWilddogOptions);
        mRef = WilddogSync.getInstance().getReference();
    }

    public void initPic() {
        mToolbar.setTitle(StringRes.getStr(R.string.toolbar_title_girl));
        picList = new ArrayList<>();
        picNowList = new ArrayList<>();

        girl_swipe.setColorSchemeResources(R.color.refresh_progress_3, R.color.refresh_progress_2, R.color.refresh_progress_1);
        girl_swipe.setOnRefreshListener(this);

        mGridLayoutManager = new GridLayoutManager(BaseApplication.sContext, 2);
        mGirlRecyclerAdapter = new GirlRecyclerAdapter(getActivity(), picList, picNowList);
        mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position == mGirlRecyclerAdapter.getItemCount() - 1) ? mGridLayoutManager.getSpanCount() : 1;
            }
        });

        girl_recycle.setLayoutManager(mGridLayoutManager);
        girl_recycle.setAdapter(mGirlRecyclerAdapter);

        getDataFromWdog(mRef);
        startLoadingDialog();
    }

    public void initRecycelEvent() {
        girl_recycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mLastVisibleItem = mGridLayoutManager.findLastVisibleItemPosition();
                    if (mGridLayoutManager.getItemCount() == 1) {
                        if (mGirlRecyclerAdapter != null) {
                            mGirlRecyclerAdapter.updateLoadStatus(GirlRecyclerAdapter.LOAD_NONE);
                        }
                        return;
                    }
                    if (mLastVisibleItem == mGridLayoutManager.getItemCount() - 1) {
                        if (mGirlRecyclerAdapter != null) {
                            if (mGirlRecyclerAdapter.getmStatus() != GirlRecyclerAdapter.LOAD_NONE) {
                                mGirlRecyclerAdapter.updateLoadStatus(GirlRecyclerAdapter.LOAD_MORE);
                                mGirlRecyclerAdapter.addListPic();
                            }
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
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
                    picList.add(new PicBean(o.get("url")));
                }
                if (isInit) {
                    for (int i = 0; i < GirlRecyclerAdapter.ONECE_LOAD; i++) {
                        picNowList.add(picList.get(i));
                    }
                    isInit = false;
                }

                mGirlRecyclerAdapter.notifyDataSetChanged();
                stopLoadingDialog();
                closeRefresh();
            }

            @Override
            public void onCancelled(SyncError syncError) {
                Log.d("xandone", syncError.toString());
                stopLoadingDialog();
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
        girl_swipe.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (girl_swipe != null) {
                    girl_swipe.setRefreshing(false);
                }
            }
        }, 1200);
    }

    public void loadMoreGirl() {

    }

}