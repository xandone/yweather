package app.xandone.com.yweather.ui.base;

import java.io.Serializable;


public class BaseRespose<T> implements Serializable {

    public T data;

    @Override
    public String toString() {
        return "BaseRespose{" + ", data=" + data + '}';
    }
}
