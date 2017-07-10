package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wsj on 2017/7/5.
 */
public class ValidatUtil {

    public boolean isusername(String str) {
        String regEx = "^[\\u4e00-\\u9fa5A-Za-z0-9-_]*$";
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        // 查找字符串中是否有匹配正则表达式的字符/字符串
        boolean rs = matcher.find();
        return rs;

    }

    public boolean ispassword(String str) {
        String regEx = "^[A-Za-z0-9]\\w{2,17}$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        boolean rs = matcher.find();
        return rs;
    }
}
