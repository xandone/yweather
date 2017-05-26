package app.xandone.com.yweather.ui.activity;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.xandone.com.yweather.BaseApplication;
import app.xandone.com.yweather.R;
import app.xandone.com.yweather.adapter.AddressListAdapter;
import app.xandone.com.yweather.data.db.DBHelper;
import app.xandone.com.yweather.data.entity.Area;
import app.xandone.com.yweather.ui.base.BaseActivity;
import app.xandone.com.yweather.widget.GlideListView;
import butterknife.BindView;

public class ChooseCityActivity extends BaseActivity {

    @BindView(R.id.province_listView)
    RecyclerView pListView;
    @BindView(R.id.city_listView)
    RecyclerView cListView;
    @BindView(R.id.rl)
    GlideListView glideListView;

    private AddressListAdapter pAdapter, cAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private LinearLayoutManager mLinearLayoutManager2;
    private ArrayList<String> pList;
    private ArrayList<String> cList;
    private List<Area> list;

    private OnGlideRecycler onGlideRecycler;

    @Override
    public int setLayout() {
        return R.layout.activity_choice_city;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        init();
        initData();
    }

    public void init() {
        onGlideRecycler = new OnGlideRecycler();
        pList = new ArrayList<>();
        cList = new ArrayList<>();
        pAdapter = new AddressListAdapter(pList, this, onGlideRecycler, AddressListAdapter.TYPE_P);
        cAdapter = new AddressListAdapter(cList, this, onGlideRecycler, AddressListAdapter.TYPE_C);
        mLinearLayoutManager = new LinearLayoutManager(BaseApplication.sContext);
        mLinearLayoutManager2 = new LinearLayoutManager(BaseApplication.sContext);
        pListView.setAdapter(pAdapter);
        cListView.setAdapter(cAdapter);
        pListView.setLayoutManager(mLinearLayoutManager);
        cListView.setLayoutManager(mLinearLayoutManager2);
    }

    public void initData() {
        list = DBHelper.newInstance(this).getProvince();
        for (Area name : list) {
            pList.add(name.getName());
        }
        pAdapter.notifyDataSetChanged();

        List<Area> list2 = DBHelper.newInstance(this).getCity(list.get(0).getCode());
        for (Area name : list2) {
            cList.add(name.getName());
        }
        cAdapter.notifyDataSetChanged();
    }


    public class OnGlideRecycler implements AddressListAdapter.OnGlideRecyclerListener {

        @Override
        public void OnGlide(int position) {
            cList.clear();
            for (Area name : DBHelper.newInstance(ChooseCityActivity.this).getCity(list.get(position).getCode())) {
                cList.add(name.getName());
            }
            cAdapter.notifyDataSetChanged();
            glideListView.yScroll(1000);
        }

        @Override
        public void onFinish() {
            finish();
        }
    }
}