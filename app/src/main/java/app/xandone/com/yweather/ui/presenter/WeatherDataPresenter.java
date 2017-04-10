package app.xandone.com.yweather.ui.presenter;

import app.xandone.com.yweather.R;
import app.xandone.com.yweather.bean.WeatherXmlData;
import app.xandone.com.yweather.ui.baserx.RxSubscriber;
import app.xandone.com.yweather.ui.contract.WeatherDataContract;

/**
 * Created by xandone on 2017/1/9.
 */
public class WeatherDataPresenter extends WeatherDataContract.Presenter {

    @Override
    public void requestWeatherXmlData(String city, String password, final String day) {
        mModel.getWeatherDataList(city, password, day)
                .subscribe(new RxSubscriber<WeatherXmlData>(mContext, false) {
                    @Override
                    public void onStart() {
                        super.onStart();
                        mView.showLoading(mContext.getString(R.string.loading));
                    }

                    @Override
                    protected void onResponse(WeatherXmlData weatherXmlData) {
                        mView.returnWeatherXmlData(weatherXmlData, day);
                        mView.stopLoading();
                    }

                    @Override
                    protected void onErrorResponse(int message) {
                        mView.showErrorTip(message);
                    }
                });
    }
}
