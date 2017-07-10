package utils;

import java.io.UnsupportedEncodingException;

/**
 * Created by wsj on 2017/7/4.
 */
public class CharsetUtil {
    public String changeCharset(String s) {
        try {
            return new String(s.getBytes("ISO8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("编码转换失败！");
        }
        return null;
    }
}
