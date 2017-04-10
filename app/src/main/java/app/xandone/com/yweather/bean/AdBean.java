package app.xandone.com.yweather.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/10.
 */
public class AdBean implements Serializable {
    private String adurl;

    public AdBean(String adurl) {
        this.adurl = adurl;
    }

    public String getAdurl() {
        return adurl;
    }

    public void setAdurl(String adurl) {
        this.adurl = adurl;
    }
}
