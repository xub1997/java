package utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/*
* 字符串工具类
* author：xub
* */
public class StringUtil {

    /**
     * 判断对象是否为空<br>
     * 1,字符串(null或者"")都返回true<br>
     * 2,数字类型(null或者0)都返回true<br>
     * 3,集合类型(null或者不包含元素都返回true)<br>
     * 4,数组类型不包含元素返回true(包含null元素返回false)<br>
     * 5,其他对象仅null返回true
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof Number) {
            Number num = (Number) obj;
            if (num.intValue() == 0) {
                return true;
            } else {
                return false;
            }
        } else if (obj instanceof String) {
            String str = (String) obj;
            if ((str == null) || str.equals("")) {
                return true;
            } else {
                return false;
            }
        } else if (obj instanceof Collection<?>) {
            Collection<?> c = (Collection<?>) obj;
            return c.isEmpty();
        } else if (obj instanceof Map<?, ?>) {
            Map<?, ?> m = (Map<?, ?>) obj;
            return m.isEmpty();
        } else if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            return length == 0 ? true : false;
        } else {
            return false;
        }
    }

    /**
     * 字符编码自定义转换类型
     *
     * @param str
     * @param oldEncode
     * @param newEncode
     * @return
     */
    public static String convertEncode(String str, String oldEncode, String newEncode) {
        if (str == null) {
            return str;
        } else {
            try {
                return new String(str.getBytes(oldEncode), newEncode);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}
