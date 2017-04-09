package app.xandone.com.yweather.ui.activity;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

import app.xandone.com.yweather.R;
import app.xandone.com.yweather.adapter.AddressListAdapter;
import app.xandone.com.yweather.data.db.DBHelper;
import app.xandone.com.yweather.data.entity.Area;
import app.xandone.com.yweather.ui.base.BaseActivity;
import app.xandone.com.yweather.widget.GlideListView;
import butterknife.BindView;

public class ChooseCityActivity extends BaseActivity {

    @BindView(R.id.province_listView)
    ListView pListView;
    @BindView(R.id.city_listView)
    ListView cListView;
    @BindView(R.id.rl)
    GlideListView glideListView;

    public static final String CHOOSE_CITY = "choose_city";

    private AddressListAdapter pAdapter, cAddress;
    private ArrayList<String> pList;
    private ArrayList<String> cList;
    private List<Area> list;

    @Override
    public int setLayout() {
        return R.layout.activity_choice_city;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        pList = new ArrayList<>();
        cList = new ArrayList<>();
        pAdapter = new AddressListAdapter(pList, this);
        cAddress = new AddressListAdapter(cList, this);
        pListView.setAdapter(pAdapter);
        cListView.setAdapter(cAddress);
        initData();
        initEvent();
    }

    public void initData() {
        list = new DBHelper(this).getProvince();
        for (Area name : list) {
            pList.add(name.getName());
        }

        List<Area> list2 = new DBHelper(this).getCity(list.get(0).getCode());
        for (Area name : list2) {
            cList.add(name.getName());
        }
    }

    public void initEvent() {
        pListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cList.clear();
                for (Area name : new DBHelper(ChooseCityActivity.this).getCity(list.get(position).getCode())) {
                    cList.add(name.getName());
                }
                cAddress.notifyDataSetChanged();
                glideListView.yScroll(1000);
            }
        });

    }
}