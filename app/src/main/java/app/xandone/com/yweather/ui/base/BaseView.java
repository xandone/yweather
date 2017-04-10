package app.xandone.com.yweather.ui.base;

/**
 * Created by xandone on 2016/12/23.
 */
public interface BaseView {
    void showLoading(String title);

    void stopLoading();

    void showErrorTip(int msg);
}
