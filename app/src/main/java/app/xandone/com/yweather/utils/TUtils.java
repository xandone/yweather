package app.xandone.com.yweather.utils;

import java.lang.reflect.ParameterizedType;

/**
 * Created by xandone on 2016/12/21.
 */
public class TUtils {
    public static <T> T getT(Object obj, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (obj.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
