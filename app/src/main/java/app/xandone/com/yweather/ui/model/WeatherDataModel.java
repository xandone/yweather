package app.xandone.com.yweather.ui.model;

import app.xandone.com.yweather.api.Api;
import app.xandone.com.yweather.api.HostType;
import app.xandone.com.yweather.bean.WeatherXml;
import app.xandone.com.yweather.bean.WeatherXmlData;
import app.xandone.com.yweather.ui.baserx.RxSchedulers;
import app.xandone.com.yweather.ui.contract.WeatherDataContract;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by xandone on 2017/1/9.
 */
public class WeatherDataModel implements WeatherDataContract.Model {
    @Override
    public Observable<WeatherXmlData> getWeatherDataList(String city, String password, String day) {
        return Api.getDefault(HostType.MY_WEATHER, 1)
                .getWData(Api.getCacheControl(),city, password, day)
                .flatMap(new Func1<WeatherXml, Observable<WeatherXmlData>>() {
                    @Override
                    public Observable<WeatherXmlData> call(WeatherXml weatherXml) {
                        return Observable.from(weatherXml.getList());
                    }
                })
                .compose(RxSchedulers.<WeatherXmlData>io_main());
    }
}
