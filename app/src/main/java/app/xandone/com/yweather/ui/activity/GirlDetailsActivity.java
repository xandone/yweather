package app.xandone.com.yweather.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import app.xandone.com.yweather.R;
import app.xandone.com.yweather.adapter.GirlRecyclerAdapter;
import app.xandone.com.yweather.config.StringRes;
import app.xandone.com.yweather.interf.DownLoadImgInterf;
import app.xandone.com.yweather.ui.base.BaseActivity;
import app.xandone.com.yweather.utils.DownLoadImg;
import app.xandone.com.yweather.utils.ImageLoader;
import app.xandone.com.yweather.utils.StringUtils;
import app.xandone.com.yweather.utils.ToastUtils;
import butterknife.BindView;
import butterknife.OnClick;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by xandone on 2017/1/18.
 */
public class GirlDetailsActivity extends BaseActivity {
    @BindView(R.id.girl_details_photoView)
    PhotoView girl_details_photoView;

    public static final int GIRLDETAILSACTIVITY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    private PhotoViewAttacher mAttacher;
    private RequestManager mRequestManager;
    private String img_url;

    @Override
    public int setLayout() {
        return R.layout.activity_gril_details;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        img_url = getIntent().getStringExtra(GirlRecyclerAdapter.GIRLRECYCLERADAPTER_URL);
        if (StringUtils.isEmpty(img_url)) {
            return;
        }
        mRequestManager = Glide.with(this);
        mAttacher = new PhotoViewAttacher(girl_details_photoView);
        ImageLoader.loadImage(mRequestManager, girl_details_photoView, img_url);
        mAttacher.update();
    }

    @OnClick({R.id.girl_details_back_rl, R.id.girl_details_down_ll})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.girl_details_back_rl:
                finish();
                break;
            case R.id.girl_details_down_ll:
                if (ContextCompat.checkSelfPermission(GirlDetailsActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(GirlDetailsActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            GIRLDETAILSACTIVITY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                } else {
                    saveImg();
                }
                break;
        }
    }

    public void saveImg() {
        new Thread(new DownLoadImg(img_url, new DownLoadImgInterf() {
            @Override
            public void saveSuccess(String name) {
                Message msg = Message.obtain();
                msg.what = 1;
                mHandle.sendMessage(msg);
            }

            @Override
            public void saveFail() {
                Message msg = Message.obtain();
                msg.what = 2;
                mHandle.sendMessage(msg);
            }
        })).start();
    }

    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                ToastUtils.showShort(StringRes.getStr(R.string.download_success));
            } else if (msg.what == 2) {
                ToastUtils.showShort(StringRes.getStr(R.string.download_error));
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == GIRLDETAILSACTIVITY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveImg();
            } else {
                ToastUtils.showShort(StringRes.getStr(R.string.download_forbid));
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
