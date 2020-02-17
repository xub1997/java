package com.xub.java.utils.xml;

import com.xub.java.utils.AssertUtil;
import com.xub.java.utils.ReflectionUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author xub
 * @Name: XMLUtilByDOM4J
 * @Description: TODO
 * @date 2020/2/16  23:25
 */
@Slf4j
public class XMLUtilByDOM4J extends XMLUtil {

    /**
     * 1、创建saxReader对象
     */
    private static SAXReader saxReader = new SAXReader();

    /**
     * 解析xml文件
     *
     * @param fileURI
     * @param tagName
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> read(@NonNull String fileURI, @NonNull String tagName, @NonNull Class<T> clazz) {
        try {
            return read(new File(fileURI), tagName, clazz);
        } catch (Exception e) {
            log.warn("解析xml出现错误，{}", e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * 解析xml文件
     *
     * @param file
     * @param tagName
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> read(@NonNull File file, @NonNull String tagName, @NonNull Class<T> clazz) {
        try {
            return read(new FileInputStream(file), tagName, clazz);
        } catch (Exception e) {
            log.warn("解析xml出现错误，{}", e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * 解析xml文件
     *
     * @param inputStream
     * @param tagName
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> read(@NonNull InputStream inputStream, @NonNull String tagName, @NonNull Class<T> clazz) {
        try {
            //防止乱码
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            //2.加载xml
            Document document = saxReader.read(reader);
            //3.获取根节点
            Element rootElement = document.getRootElement();
            Iterator childIterator = rootElement.elementIterator();
            //返回结果
            List<T> result = new ArrayList<>();
            //得到目标类的所有可解析的字段
            Map<String, Method> fieldMap = ReflectionUtil.getFieldSetterMap(clazz);
            //得到目标类的所有可解析的字段类型
            Map<String, String> fieldTypeMap = ReflectionUtil.getFieldTypeMap(clazz);
            //得到目标类的所有可解析的字段的时间格式
            Map<String, String> fieldDateFormatMap = ReflectionUtil.getFieldDateFormatMap(clazz);
            //遍历
            while (childIterator.hasNext()) {
                Element childElement = (Element) childIterator.next();
                if (tagName.equals(childElement.getName())) {
                    T t = clazz.newInstance();
                    Iterator iterator = childElement.elementIterator();
                    while (iterator.hasNext()) {
                        Element element = (Element) iterator.next();
                        //赋值
                        invoke(t, element.getName(), element.getStringValue(), fieldMap, fieldTypeMap, fieldDateFormatMap);
                    }
                    result.add(t);
                }
            }
            return result;
        } catch (Exception e) {
            log.warn("解析xml出现错误，{}", e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * 写入xml文件
     *
     * @param fileURI
     * @param datas
     * @param rootName
     * @param tagName
     * @param <T>
     */
    public static <T> void write(@NonNull String fileURI, @NonNull List<T> datas, @NonNull String rootName, @NonNull String tagName) {
        try {
            write(new File(fileURI), datas, rootName, tagName);
        } catch (Exception e) {
            log.warn("写入xml出现错误，{}", e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * 写入xml文件
     *
     * @param file
     * @param datas
     * @param rootName
     * @param tagName
     * @param <T>
     */
    public static <T> void write(@NonNull File file, @NonNull List<T> datas, @NonNull String rootName, @NonNull String tagName) {
        try {
            write(new FileOutputStream(file), datas, rootName, tagName);
        } catch (Exception e) {
            log.warn("写入xml出现错误，{}", e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * 写入xml文件
     *
     * @param outputStream
     * @param datas
     * @param rootName
     * @param tagName
     * @param <T>
     */
    public static <T> void write(@NonNull OutputStream outputStream, @NonNull List<T> datas, @NonNull String rootName, @NonNull String tagName) {
        if (datas == null || datas.size() < 1) {
            log.warn("写入xml错误，数据为空，请检验数据");
            throw new IllegalStateException("写入xml错误，数据为空，请检验数据");
        }
        try {

            //1、新建document对象，代表整个xml文件
            Document document = DocumentHelper.createDocument();
            //2、创建根节点
            Element rootElement = document.addElement(rootName);
            Class clazz = datas.get(0).getClass();
            //得到目标类的所有可解析的字段及getter方法
            Map<String, Method> fieldMap = ReflectionUtil.getFieldGetterMap(clazz);
            //得到目标类的所有可解析的字段类型
            Map<String, String> fieldTypeMap = ReflectionUtil.getFieldTypeMap(clazz);
            //得到目标类的所有可解析的字段的时间格式
            Map<String, String> fieldDateFormatMap = ReflectionUtil.getFieldDateFormatMap(clazz);
            for (T data : datas) {
                //3、生成子节点
                Element childElement = rootElement.addElement(tagName);
                Set<String> fieldsSet = fieldMap.keySet();
                for (String field : fieldsSet) {
                    String fieldValue = reinvoke(data, field, fieldMap, fieldTypeMap, fieldDateFormatMap);
                    if (AssertUtil.isNotEmpty(fieldValue)) {
                        Element element = childElement.addElement(field);
                        element.setText(fieldValue);
                    }
                }
            }
            //设置生成xml的格式
            OutputFormat format = OutputFormat.createPrettyPrint();
            //通过writer对象，输出数据到指定文件输出流中
            XMLWriter writer = new XMLWriter(outputStream, format);
            //设置是否转义输出特殊字符(默认：转义输出)
            writer.setEscapeText(false);
            writer.write(document);
            writer.close();
        } catch (Exception e) {
            log.warn("写入xml出现错误，{}", e.getMessage());
            throw new IllegalStateException(e);
        }
    }

}
