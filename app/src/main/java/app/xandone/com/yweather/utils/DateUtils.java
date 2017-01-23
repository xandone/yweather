package app.xandone.com.yweather.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xandone on 2016/11/18.
 */
public class DateUtils {

    public static final String DEFULT_DATE_FORMATE = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFULT_DATE_ONLY_DAY = "yyyy-MM-dd";


    public static String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat(DEFULT_DATE_FORMATE);
        return sf.format(date);
    }

    public static String getCurrentDate(String pattern) {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.format(date);
    }

    /**
     * 将毫秒数转化为某格式的日期字符串
     *
     * @param longTime
     * @param formatType
     * @return
     */
    public static String longToDateString(long longTime, String formatType) {
        Date date = new Date(longTime);
        String str = new SimpleDateFormat(formatType).format(date);
        return str;
    }

    /**
     * 将某种格式的日期字符串转化为毫秒数
     *
     * @param dateString
     * @param formatType
     * @return
     */
    public static long dateStringToLong(String dateString, String formatType) {
        SimpleDateFormat sf = new SimpleDateFormat(formatType);
        Date date = null;
        try {
            date = sf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null) {
            return 0;
        } else {
            long longTime = date.getTime();
            return longTime;
        }
    }

}
