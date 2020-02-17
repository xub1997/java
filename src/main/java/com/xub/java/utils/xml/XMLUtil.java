package com.xub.java.utils.xml;

import com.xub.java.utils.AssertUtil;
import com.xub.java.utils.ReflectionUtil;
import com.xub.java.utils.date.DateUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author xub
 * @Name: XMLUtil
 * @Description: TODO
 * @date 2020/2/16  16:58
 */
public class XMLUtil {

    private static final String BYTE = "java.lang.Byte";

    private static final String SHORT = "java.lang.Short";

    private static final String INTEGER = "java.lang.Integer";

    private static final String LONG = "java.lang.Long";

    private static final String FLOAT = "java.lang.Float";

    private static final String DOUBLE = "java.lang.Double";

//    private static final String CHARACTER = "java.lang.Character";

    private static final String STRING = "java.lang.String";

    private static final String BOOLEAN = "java.lang.Boolean";

    private static final String DATE = "java.util.Date";

    private static final String BIGDECIMAL = "java.math.BigDecimal";


    /**
     * 赋值
     *
     * @param obj
     * @param fieldName
     * @param fieldValue
     * @param fieldMap
     * @param fieldTypeMap
     * @param fieldDateFormatMap
     * @param <T>
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    protected static <T> void invoke(T obj,
                                       String fieldName,
                                       String fieldValue,
                                       Map<String, Method> fieldMap,
                                       Map<String, String> fieldTypeMap,
                                       Map<String, String> fieldDateFormatMap) {
        Object realValue = null;
        Method method = fieldMap.get(fieldName);
        String fieldType = fieldTypeMap.get(fieldName);
        if (AssertUtil.isNotEmpty(method) && AssertUtil.isNotEmpty(fieldType) && AssertUtil.isNotEmpty(fieldValue) ) {

            switch (fieldType) {
                case BYTE: {
                    realValue = new Byte(fieldValue);
                    break;
                }
                case SHORT: {
                    realValue = new Short(fieldValue);
                    break;
                }
                case INTEGER: {
                    realValue = new Integer(fieldValue);
                    break;
                }
                case LONG: {
                    realValue = new Long(fieldValue);
                    break;
                }
                case FLOAT: {
                    realValue = new Float(fieldValue);
                    break;
                }
                case DOUBLE: {
                    realValue = new Double(fieldValue);
                    break;
                }
                case STRING: {
                    realValue = fieldValue;
                    break;
                }
                case BOOLEAN: {
                    realValue = new Boolean(fieldValue);
                    break;
                }
                case BIGDECIMAL: {
                    realValue = new BigDecimal(fieldValue);
                    break;
                }
                case DATE: {
                    String dateFormatString = fieldDateFormatMap.get(fieldName);
                    realValue = DateUtil.parse(fieldValue, dateFormatString);
                    break;
                }
            }
            ReflectionUtil.invoke(obj, method, realValue);
        }
    }

    /**
     * 设值
     *
     * @param obj
     * @param fieldName
     * @param fieldMap
     * @param fieldTypeMap
     * @param fieldDateFormatMap
     * @param <T>
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    protected static <T> String reinvoke(T obj,
                                         String fieldName,
                                         Map<String, Method> fieldMap,
                                         Map<String, String> fieldTypeMap,
                                         Map<String, String> fieldDateFormatMap) {
        String realValue = null;
        Method method = fieldMap.get(fieldName);
        String fieldType = fieldTypeMap.get(fieldName);
        if (AssertUtil.isNotEmpty(method) && AssertUtil.isNotEmpty(fieldType)) {
            Object invoke = ReflectionUtil.invoke(obj, method);
            switch (fieldType) {
                case BYTE:
                case SHORT:
                case INTEGER:
                case LONG:
                case FLOAT:
                case DOUBLE:
                case STRING:
                case BOOLEAN: {
                    if (AssertUtil.isNotEmpty(invoke)) {
                        realValue = String.valueOf(invoke);
                    }
                    break;
                }
                case BIGDECIMAL: {
                    if (AssertUtil.isNotEmpty(invoke)) {
                        BigDecimal value = (BigDecimal) invoke;
                        realValue = value.toString();
                    }
                    break;
                }
                case DATE: {
                    if (AssertUtil.isNotEmpty(invoke)) {
                        Date date = (Date) invoke;
                        String dateFormatString = fieldDateFormatMap.get(fieldName);
                        realValue = DateUtil.format(date, dateFormatString);
                    }
                    break;
                }
            }
        }
        return realValue;
    }
}
