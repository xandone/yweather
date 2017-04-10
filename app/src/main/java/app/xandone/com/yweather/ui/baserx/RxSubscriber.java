package app.xandone.com.yweather.ui.baserx;

import android.content.Context;

import app.xandone.com.yweather.BaseApplication;
import app.xandone.com.yweather.R;
import app.xandone.com.yweather.exception.ServerException;
import app.xandone.com.yweather.utils.LoadingDialog;
import app.xandone.com.yweather.utils.NetWorkUtils;
import rx.Subscriber;

public abstract class RxSubscriber<T> extends Subscriber<T> {

    private Context mContext;
    private String msg;
    private boolean showDialog = true;

    public static final int ERROR_NET = 1;
    public static final int ERROR_SERVER = 2;
    public static final int ERROR_OTHER = 3;

    public RxSubscriber(Context context) {
        this(context, BaseApplication.sContext.getString(R.string.loading), true);
    }

    public RxSubscriber(Context context, boolean showDialog) {
        this(context, BaseApplication.sContext.getString(R.string.loading), showDialog);
    }

    public RxSubscriber(Context context, String msg, boolean showDialog) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog = showDialog;
    }


    @Override
    public void onCompleted() {
        if (showDialog)
            LoadingDialog.cancelDialogForLoading();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (showDialog) {
            try {
                LoadingDialog.showDialogForLoading(mContext, msg, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onNext(T t) {
        onResponse(t);
    }

    @Override
    public void onError(Throwable e) {
        if (showDialog)
            LoadingDialog.cancelDialogForLoading();
        e.printStackTrace();
        //网络
        if (!NetWorkUtils.isNetConnected(BaseApplication.sContext)) {
            onErrorResponse(ERROR_NET);
        }
//        服务器
        else if (e instanceof ServerException) {
//            onErrorResponse(e.getMessage());
            onErrorResponse(ERROR_SERVER);
        }
//        其它
        else {
            onErrorResponse(ERROR_SERVER);
        }
    }

    public void showDialog() {
        this.showDialog = true;
    }

    public void hideDialog() {
        this.showDialog = false;
    }


    protected abstract void onResponse(T t);

    protected abstract void onErrorResponse(int message);

}
