package app.xandone.com.yweather.bean;

/**
 * Created by xandone on 2017/1/12.
 */
public class PicBean {
    private String url;
    private int height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public PicBean(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
