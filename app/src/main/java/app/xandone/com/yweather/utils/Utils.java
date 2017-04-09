package app.xandone.com.yweather.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import app.xandone.com.yweather.BaseApplication;

public class Utils {

    /**
     * 获取手机屏幕宽高
     *
     * @return
     */
    public static int[] getWH() {
        int[] wh = null;
        if (wh == null) {
            wh = new int[2];
            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager wm = null;
            wm = (WindowManager) BaseApplication.sContext.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(metrics);
            wh[0] = metrics.widthPixels;
            wh[1] = metrics.heightPixels;
        }
        return wh;
    }

    /**
     * 格式化时间
     *
     * @param millis
     * @param formatString
     * @return
     */
    public static String formatDate(long millis, String formatString) {
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return format.format(calendar.getTime());
    }

    /**
     * 图片名称
     *
     * @return
     */
    public static String getDefualtImageName() {
        return "img_" + formatDate(System.currentTimeMillis(), "yyyyMMdd_HHmmss") + ".jpg";
    }

    /**
     * 密码Md5加密
     *
     * @param str
     * @return
     */
    public static String md5(String str) {
        byte[] hash = null;
        try {
            hash = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (hash == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) sb.append("0");
            sb.append(Integer.toHexString(b & 0xFF));
        }
        return sb.toString();
    }

    /**
     * dp转化为px
     *
     * @param context
     * @param dpValue
     * @return
     */

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转化为dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 字符数组转化为Bitmap
     *
     * @param bytes
     * @return
     */
    public static Bitmap bytes2Bitmap(byte[] bytes) {
        if (0 == bytes.length) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * Bitmap转化为字符数组
     *
     * @param bitmap
     * @return
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap) {
        if (null == bitmap) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}