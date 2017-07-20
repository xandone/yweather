package app.xandone.com.yweather.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * author: xandone
 * created on: 2017/7/20 11:36
 */

public class UserBean implements Serializable, Parcelable {
    private String userName;
    private String uid;
    private String pwd;

    protected UserBean(Parcel in) {
        userName = in.readString();
        uid = in.readString();
        pwd = in.readString();
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel in) {
            return new UserBean(in);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.uid);
        dest.writeString(this.pwd);
    }

}
