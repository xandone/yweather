package app.xandone.com.yweather.ui.contract;

import app.xandone.com.yweather.bean.WeatherXmlData;
import app.xandone.com.yweather.ui.base.BaseModel;
import app.xandone.com.yweather.ui.base.BasePresenter;
import app.xandone.com.yweather.ui.base.BaseView;
import rx.Observable;

/**
 * Created by xandone on 2017/1/9.
 */
public interface WeatherDataContract {
    interface Model extends BaseModel {
        Observable<WeatherXmlData> getWeatherDataList(String city, String password, String day);
    }

    interface View extends BaseView {
        void returnWeatherXmlData(WeatherXmlData weatherXmlData, String day);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void requestWeatherXmlData(String city, String password, String day);
    }
}
