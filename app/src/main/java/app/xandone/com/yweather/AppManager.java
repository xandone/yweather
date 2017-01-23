package app.xandone.com.yweather;

import android.app.Activity;

import java.util.Stack;


/**
 * Created by xandone on 2016/12/21.
 */
public class AppManager {

    private static Stack<Activity> activityStack;
    private volatile static AppManager instance;

    private AppManager() {
    }

    public static AppManager newInstance() {
        if (instance == null) {
            synchronized (AppManager.class) {
                instance = new AppManager();
                activityStack = new Stack<>();
            }
        }
        return instance;
    }

    public void addActivivty(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    public void removectivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity = null;
        }
    }

    public void finishAllActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public boolean isOpenActivity(Class<?> clazz) {
        if (activityStack != null) {
            if (clazz == activityStack.peek().getClass()) {
                return true;
            }
        }
        return false;
    }

}
