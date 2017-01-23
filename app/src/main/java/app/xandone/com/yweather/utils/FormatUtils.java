package app.xandone.com.yweather.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xandone on 2016/12/22.
 */
public class FormatUtils {


    /**
     * 是否为纯数字
     * @param num
     * @return
     */
    public static boolean isAllNum(String num) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(num);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断email格式是否正确
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    /**
     * 判断身份证格式
     */
    public static boolean isIdCardNo(String idNum){
        //定义判别用户身份证号的正则表达式（要么是15位或18位，最后一位可以为字母）
        Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
        //通过Pattern获得Matcher
        Matcher idNumMatcher = idNumPattern.matcher(idNum);
        if(!idNumMatcher.matches()){
            return false;
        }
        return true;
    }

}
