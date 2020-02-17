package com.xub.java.utils.xml;

import com.xub.java.utils.AssertUtil;
import com.xub.java.utils.ReflectionUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xub
 * @Name: XMLUtilByJDOM
 * @Description: TODO
 * @date 2020/2/16  20:22
 */
@Slf4j
public class XMLUtilByJDOM extends XMLUtil {

    /**
     * 1、创建saxBuilder对象
     */
    private static SAXBuilder saxBuilder = new SAXBuilder();

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
            //2、加载xml文件
            Document document = saxBuilder.build(reader);
            //3.获取根节点
            Element rootElement = document.getRootElement();
            //4.获取子节点
            List<Element> children = rootElement.getChildren();
            children = children.stream().filter(e -> tagName.equals(e.getName())).collect(Collectors.toList());
            List<T> result = null;
            if (children != null && children.size() > 0) {
                //返回结果
                result = new ArrayList<>(children.size());
                //得到目标类的所有可解析的字段
                Map<String, Method> fieldMap = ReflectionUtil.getFieldSetterMap(clazz);
                //得到目标类的所有可解析的字段类型
                Map<String, String> fieldTypeMap = ReflectionUtil.getFieldTypeMap(clazz);
                //得到目标类的所有可解析的字段的时间格式
                Map<String, String> fieldDateFormatMap = ReflectionUtil.getFieldDateFormatMap(clazz);
                for (Element child : children) {
                    List<Element> childrenList = child.getChildren();
                    T t = clazz.newInstance();
                    for (Element element : childrenList) {
                        if (fieldMap.containsKey(element.getName())) {
                            //赋值
                            invoke(t, element.getName(), element.getValue(), fieldMap, fieldTypeMap, fieldDateFormatMap);
                        }
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
            Document document = new Document();
            //2、创建根节点
            Element rootElement = new Element(rootName);
            document.setRootElement(rootElement);
            //获取类信息
            Class clazz = datas.get(0).getClass();
            //得到目标类的所有可解析的字段及getter方法
            Map<String, Method> fieldMap = ReflectionUtil.getFieldGetterMap(clazz);
            //得到目标类的所有可解析的字段类型
            Map<String, String> fieldTypeMap = ReflectionUtil.getFieldTypeMap(clazz);
            //得到目标类的所有可解析的字段的时间格式
            Map<String, String> fieldDateFormatMap = ReflectionUtil.getFieldDateFormatMap(clazz);
            for (T data : datas) {
                //3、生成子节点
                Element childElement = new Element(tagName);
                Set<String> fieldsSet = fieldMap.keySet();
                for (String field : fieldsSet) {
                    String fieldValue = reinvoke(data, field, fieldMap, fieldTypeMap, fieldDateFormatMap);
                    if (AssertUtil.isNotEmpty(fieldValue)) {
                        Element element = new Element(field);
                        element.setText(fieldValue);
                        childElement.addContent(element);
                    }
                }
                rootElement.addContent(childElement);
            }
            //设置生成xml的格式
            Format format = Format.getCompactFormat();
            //编码方式
            format.setEncoding("UTF-8");
            //设置换行
            format.setIndent("");
            format.setIgnoreTrAXEscapingPIs(false);
            //通过outputter对象，输出数据到指定文件输出流中
            XMLOutputter outputter = new XMLOutputter(format);
            outputter.output(document, outputStream);
        } catch (Exception e) {
            log.warn("写入xml出现错误，{}", e.getMessage());
            throw new IllegalStateException(e);
        }
    }

}
