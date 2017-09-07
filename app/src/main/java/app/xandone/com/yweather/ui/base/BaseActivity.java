package app.xandone.com.yweather.ui.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import app.xandone.com.yweather.AppManager;
import app.xandone.com.yweather.BuildConfig;
import app.xandone.com.yweather.bean.event.Event;
import app.xandone.com.yweather.utils.EventBusUtils;
import app.xandone.com.yweather.utils.LoadingDialog;
import app.xandone.com.yweather.utils.TUtils;
import app.xandone.com.yweather.utils.ToastUtils;
import butterknife.ButterKnife;

/**
 * Created by xandone on 2016/12/21.
 */
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity {
    public T mPresenter;
    public E mModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBefore();
        setContentView(setLayout());
        PushAgent.getInstance(getApplicationContext()).onAppStart();
        ButterKnife.bind(this);
        if (isRegisterEventBus()) {
            EventBusUtils.register(this);
        }
        mPresenter = TUtils.getT(this, 0);
        mModel = TUtils.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = this;
        }
        initPresenter();
        initView();
    }

    /**
     * 配置layout之前的操作
     */
    public void doBefore() {
        initTheme();
        AppManager.newInstance().addActivivty(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 切换白天晚上模式
     */
    public void initTheme() {

    }

    /**
     * 跳转界面
     *
     * @param clazz
     */
    public void startActivity(Class<?> clazz) {
        startActivity(clazz, null);
    }

    /**
     * 含bundle跳转界面
     *
     * @param clazz
     * @param bundle
     */
    public void startActivity(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void startLoadingDialog() {
        LoadingDialog.showDialogForLoading(this);
    }

    public void startLoadingDialog(String str) {
        LoadingDialog.showDialogForLoading(this, str, false);
    }

    public void stopLoadingDialog() {
        LoadingDialog.cancelDialogForLoading();
    }

    public void showShortToast(String msg) {
        ToastUtils.showShort(msg);
    }

    public void showShortToast(int strId) {
        ToastUtils.showShort(strId);
    }

    public void showLongToast(String msg) {
        ToastUtils.showShort(msg);
    }

    public void showLongToast(int strId) {
        ToastUtils.showShort(strId);
    }

    public void showImgToast(String msg, int imgId) {
        ToastUtils.showToastWithImg(msg, imgId);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!BuildConfig.DEBUG) {
            MobclickAgent.onResume(this);//友盟
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!BuildConfig.DEBUG) {
            MobclickAgent.onResume(this);//友盟
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.newInstance().removectivity(this);
        if (isRegisterEventBus()) {
            EventBusUtils.unregister(this);
        }
    }

    public abstract int setLayout();

    public abstract void initPresenter();

    public abstract void initView();

    protected boolean isRegisterEventBus() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(Event event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBusCome(Event event) {
        if (event != null) {
            receiveStickyEvent(event);
        }
    }

    /**
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected void receiveEvent(Event event) {

    }

    /**
     * 接受到分发的粘性事件
     *
     * @param event 粘性事件
     */
    protected void receiveStickyEvent(Event event) {

    }


}
