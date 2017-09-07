package app.xandone.com.yweather.utils;

import org.greenrobot.eventbus.EventBus;

import app.xandone.com.yweather.bean.event.Event;

/**
 * author: xandone
 * created on: 2017/9/7 9:53
 */

public class EventBusUtils {
    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }

    public static void sendStickyEvent(Event event) {
        EventBus.getDefault().postSticky(event);
    }
}
