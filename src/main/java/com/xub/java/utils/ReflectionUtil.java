package com.xub.java.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xub
 * @Name: ReflectionUtil
 * @Description: TODO
 * @date 2020/2/16  15:03
 */
@Slf4j
public class ReflectionUtil {

    /**
     * 根据class创建对象
     *
     * @param clazz 待创建对象的类
     * @param <T>   对象类型
     * @return 创建好的对象
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            log.warn("反射生成{}对象出现错误，{}", clazz.getName(), e.getMessage());
            throw new IllegalStateException(e);
        } catch (IllegalAccessException e) {
            log.warn("反射生成{}对象出现错误，{}", clazz.getName(), e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * 获取对应类的所有的公有方法
     *
     * @param clazz
     * @return
     */
    public static Method[] getPublicMethods(Class clazz) {
        Method[] declaredMethods = clazz.getDeclaredMethods();
        List<Method> methodList = new ArrayList<>();
        for (Method declaredMethod : declaredMethods) {
            if (Modifier.isPublic(declaredMethod.getModifiers())) {
                methodList.add(declaredMethod);
            }
        }
        return methodList.toArray(new Method[0]);
    }

    /**
     * 反射调用对应对象的方法
     *
     * @param obj
     * @param method
     * @return
     */
    public static Object invoke(Object obj, Method method, Object... args) {
        try {
            return method.invoke(obj, args);
        } catch (IllegalAccessException e) {
            log.warn("反射调用{}的{}方法名出现错误，{}", obj.getClass().getName(), method.getName(), e.getMessage());
            throw new IllegalStateException(e);
        } catch (InvocationTargetException e) {
            log.warn("反射调用{}的{}方法名出现错误，{}", obj.getClass().getName(), method.getName(), e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * 获取类中的属性及对应set方法
     * @param clazz
     * @return
     */
    public static Map<String, Method> getFieldSetterMap(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        int fieldLength = fields.length;
        Map<String, Method> fieldMap = new HashMap<>(fieldLength);
        for (int i = 0; i < fieldLength; i++) {
            Field f = fields[i];
            String fieldName = f.getName();
            String setMethodName = new StringBuilder()
                    .append("set")
                    .append(fieldName.substring(0, 1).toUpperCase())
                    .append(fieldName.substring(1)).toString();
            //构造setter方法
            Method setMethod = null;
            try {
                setMethod = clazz.getMethod(setMethodName, new Class[]{f.getType()});
            } catch (NoSuchMethodException e) {
                log.warn("获取{}方法名出现错误，{}", clazz.getName(), e.getMessage());
                throw new IllegalStateException(e);
            }
            fieldMap.put(fieldName, setMethod);
        }
        return fieldMap;
    }

    /**
     * 获取类中的属性及对应get方法
     * @param clazz
     * @return
     */
    public static Map<String, Method> getFieldGetterMap(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        int fieldLength = fields.length;
        Map<String, Method> fieldMap = new HashMap<>(fieldLength);
        for (int i = 0; i < fieldLength; i++) {
            Field f = fields[i];
            String fieldName = f.getName();
            String setMethodName = new StringBuilder()
                    .append("get")
                    .append(fieldName.substring(0, 1).toUpperCase())
                    .append(fieldName.substring(1)).toString();
            //构造getter方法
            Method getMethod = null;
            try {
                getMethod = clazz.getMethod(setMethodName);
            } catch (NoSuchMethodException e) {
                log.warn("获取{}方法名出现错误，{}", clazz.getName(), e.getMessage());
                throw new IllegalStateException(e);
            }
            fieldMap.put(fieldName, getMethod);
        }
        return fieldMap;
    }

    /**
     * 获取类中属性及其类型
     *
     * @param clazz
     * @return
     */
    public static Map<String, String> getFieldTypeMap(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        int fieldLength = fields.length;
        Map<String, String> fieldTypeMap = new HashMap<>(fieldLength);
        for (int i = 0; i < fieldLength; i++) {
            Field f = fields[i];
            String fieldName = f.getName();
            String fieldTypeName = f.getType().getName();
            fieldTypeMap.put(fieldName, fieldTypeName);
        }
        return fieldTypeMap;
    }

    /**
     * 获取类中属性及其时间格式
     * （默认支持com.fasterxml.jackson.annotation.JsonFormat）
     *
     * @param clazz
     * @return
     */
    public static Map<String, String> getFieldDateFormatMap(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        int fieldLength = fields.length;
        Map<String, String> dateFormatMap = new HashMap<>(fieldLength);
        for (int i = 0; i < fieldLength; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            if (field.isAnnotationPresent(JsonFormat.class)) {
                JsonFormat annotation = field.getAnnotation(JsonFormat.class);
                dateFormatMap.put(fieldName, annotation.pattern());
            }
        }
        return dateFormatMap;
    }
}
