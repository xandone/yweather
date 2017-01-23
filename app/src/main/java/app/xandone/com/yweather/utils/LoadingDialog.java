package app.xandone.com.yweather.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.xandone.com.yweather.R;


/**
 * Created by xandone on 2016/12/21.
 */
public class LoadingDialog {
    private static Dialog mLoadingDialog;

    public static Dialog showDialogForLoading(Context act) {
        View view = LayoutInflater.from(act).inflate(R.layout.dialog_loading, null);
        TextView tv_loading = (TextView) view.findViewById(R.id.id_tv_loading_dialog_text);
        tv_loading.setText("加载中..");
        mLoadingDialog = new Dialog(act, R.style.CustomProgressDialog);
        mLoadingDialog.setCancelable(true);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        mLoadingDialog.show();
        return mLoadingDialog;
    }

    public static Dialog showDialogForLoading(Context act, String str, boolean cancelacble) {
        View view = LayoutInflater.from(act).inflate(R.layout.dialog_loading, null);
        TextView tv_laoding = (TextView) view.findViewById(R.id.id_tv_loading_dialog_text);
        tv_laoding.setText(str);

        mLoadingDialog = new Dialog(act, R.style.CustomProgressDialog);
        mLoadingDialog.setCancelable(cancelacble);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        mLoadingDialog.show();
        return mLoadingDialog;
    }

    public static void cancelDialogForLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.cancel();
        }
    }
}
