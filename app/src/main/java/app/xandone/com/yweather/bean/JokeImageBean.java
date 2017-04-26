package app.xandone.com.yweather.bean;

import java.io.Serializable;

/**
 * Created by xandone on 2017/1/12.
 */
public class JokeImageBean implements Serializable{
    private String joke_img;

    public JokeImageBean(String joke_img) {
        this.joke_img = joke_img;
    }

    public String getJoke_img() {
        return joke_img;
    }

    public void setJoke_img(String joke_img) {
        this.joke_img = joke_img;
    }
}
