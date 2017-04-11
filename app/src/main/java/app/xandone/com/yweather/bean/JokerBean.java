package app.xandone.com.yweather.bean;

import java.io.Serializable;

/**
 * Created by xandone on 2017/1/10.
 */
public class JokerBean implements Serializable {
    private String title;
    private String content;
    private String url;
    private String mydate;

    public JokerBean(String title, String content, String url, String mydate) {
        this.title = title;
        this.content = content;
        this.url = url;
        this.mydate = mydate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMydate() {
        return mydate;
    }

    public void setMydate(String mydate) {
        this.mydate = mydate;
    }
}
