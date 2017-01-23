package app.xandone.com.yweather.ui.base;

import android.content.Context;

/**
 * Created by xandone on 2016/12/21.
 */
public class BasePresenter<T, E> {
    public Context mContext;
    public E mModel;
    public T mView;

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
    }

}

