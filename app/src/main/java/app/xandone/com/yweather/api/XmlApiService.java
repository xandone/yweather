package app.xandone.com.yweather.api;

import app.xandone.com.yweather.bean.WeatherXml;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xandone on 2017/1/6.
 */
public interface XmlApiService {

    @GET("xml.php")
    Observable<WeatherXml> getWData(
            @Header("Cache-Control") String cacheControl,
            @Query(value = "city", encoded = true) String myCity,
            @Query("password") String myPassword,
            @Query("day") String myDay);
}
