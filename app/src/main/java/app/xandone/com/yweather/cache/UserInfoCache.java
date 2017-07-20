package app.xandone.com.yweather.cache;

import app.xandone.com.yweather.bean.UserBean;

/**
 * author: xandone
 * created on: 2017/7/20 11:33
 */

public class UserInfoCache {
    private static UserBean mUserBean;
    private static boolean mIsLogin;

    public static UserBean getmUserBean() {
        return mUserBean;
    }

    public static void setmUserBean(UserBean mUserBean) {
        UserInfoCache.mUserBean = mUserBean;
    }

    public static boolean ismIsLogin() {
        return mIsLogin;
    }

    public static void setmIsLogin(boolean mIsLogin) {
        UserInfoCache.mIsLogin = mIsLogin;
    }
}
