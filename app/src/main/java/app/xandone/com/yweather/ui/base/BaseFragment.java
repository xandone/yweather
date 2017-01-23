package app.xandone.com.yweather.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.xandone.com.yweather.utils.LoadingDialog;
import app.xandone.com.yweather.utils.TUtils;
import app.xandone.com.yweather.utils.ToastUtils;
import butterknife.ButterKnife;

/**
 * Created by xandone on 2016/12/22.
 */
public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel> extends Fragment {
    public T mPresenter;
    public E mModel;
    protected View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("xandone", getClass().getName());
        if (mRootView == null) {
            mRootView = inflater.inflate(setLayout(), container, false);
        }
        ButterKnife.bind(this, mRootView);
        mPresenter = TUtils.getT(this, 0);
        mModel = TUtils.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = this.getActivity();
        }
        initPresenter();
        initView();
        return mRootView;
    }

    public void startLoadingDialog() {
        LoadingDialog.showDialogForLoading(getActivity());
    }

    public void startLoadingDialog(String str) {
        LoadingDialog.showDialogForLoading(getActivity(), str, false);
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

    protected abstract int setLayout();

    public abstract void initPresenter();

    protected abstract void initView();
}
