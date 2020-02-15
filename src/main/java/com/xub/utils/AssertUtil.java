package com.xub.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

public class AssertUtil {

    private AssertUtil() {
    }

    public static boolean isEmptyObject(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            Object value = declaredField.get(obj);
            if(isNotEmpty(value)){
                return false;
            }
        }
        return true;
    }

    public static boolean isNotEmpty(Object obj){
        return !isEmpty(obj);
    }

    /**
     * 判空
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
            return num.intValue() == 0 ? true : false;
        } else if (obj instanceof String) {
            String str = (String) obj;
            return ((str == null) || "".equals(str)) ? true : false;
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
}
