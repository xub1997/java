package com.xub.java.utils.xml;

import com.xub.java.utils.ReflectionUtil;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xub
 * @Name: XMLUtilBySAX
 * @Description: TODO
 * @date 2020/2/16  17:03
 */
@Slf4j
public class XMLUtilBySAX extends XMLUtil {

    /**
     * SAXParserFactory
     */
    private static SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

    /**
     * saxTransformerFactory
     */
    private static SAXTransformerFactory saxTransformerFactory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();

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
    public static <T> List<T> read(@NonNull InputStream inputStream,@NonNull  String tagName,@NonNull  Class<T> clazz) {
        try {
            //新建SAXParser
            SAXParser saxParser = saxParserFactory.newSAXParser();
            //新建自定义xmlHandler
            SAXParserHandler saxParserHandler = new SAXParserHandler(tagName, clazz);
            //交给自定义的handler进行解析xml
            saxParser.parse(inputStream, saxParserHandler);
            //返回结果
            return saxParserHandler.getResult();
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
    public static <T> void write(@NonNull String fileURI, @NonNull List<T> datas, @NonNull String rootName,@NonNull  String tagName) {
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
            //2、通过saxTransformerFactory创建TransformerHandler对象
            TransformerHandler handler = saxTransformerFactory.newTransformerHandler();
            //3、通过handler创建transformer对象
            Transformer transformer = handler.getTransformer();
            //4、通过transformer对xml文件进行设置
            //设置是否换行
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            //设置编码方式
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            //6、创建result对象并与handler对象关联
            Result result = new StreamResult(outputStream);
            handler.setResult(result);
            //7、利用handler对象进行xml内容的编写
            //打开document
            handler.startDocument();
            handler.startElement("", "", rootName, null);
            //获取类信息
            Class clazz = datas.get(0).getClass();
            //得到目标类的所有可解析的字段及getter方法
            Map<String, Method> fieldMap = ReflectionUtil.getFieldGetterMap(clazz);
            //得到目标类的所有可解析的字段类型
            Map<String, String> fieldTypeMap = ReflectionUtil.getFieldTypeMap(clazz);
            //得到目标类的所有可解析的字段的时间格式
            Map<String, String> fieldDateFormatMap = ReflectionUtil.getFieldDateFormatMap(clazz);
            for (T data : datas) {
                handler.startElement("", "", tagName, null);
                Set<String> fieldsSet = fieldMap.keySet();
                for (String field : fieldsSet) {
                    String fieldValue = reinvoke(data, field, fieldMap, fieldTypeMap, fieldDateFormatMap);
                    if (fieldValue != null) {
                        handler.startElement("", "", field, null);
                        handler.characters(fieldValue.toCharArray(), 0, fieldValue.toCharArray().length);
                        handler.endElement("", "", field);
                    }
                }
                handler.endElement("", "", tagName);
            }
            handler.endElement("", "", rootName);
            //关闭document
            handler.endDocument();
        } catch (Exception e) {
            log.warn("写入xml出现错误，{}", e.getMessage());
            throw new IllegalStateException(e);
        }
    }

}

@Slf4j
class SAXParserHandler<T> extends DefaultHandler {

    @Getter
    private List<T> result;

    private String tagName;

    private Class<T> clazz;

    private String value;

    private T t;

    //得到目标类的所有可解析的字段
    private Map<String, Method> fieldMap;

    //得到目标类的所有可解析的字段类型
    private Map<String, String> fieldTypeMap;

    //得到目标类的所有可解析的字段的时间格式
    private Map<String, String> fieldDateFormatMap;

    public SAXParserHandler(String tagName, Class<T> clazz) {
        this.tagName = tagName;
        this.clazz = clazz;
        this.fieldMap = ReflectionUtil.getFieldSetterMap(clazz);
        this.fieldTypeMap = ReflectionUtil.getFieldTypeMap(clazz);
        this.fieldDateFormatMap = ReflectionUtil.getFieldDateFormatMap(clazz);
        result = new ArrayList<>();
    }

    /**
     * 用来标识解析开始
     *
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    /**
     * 用来标识解析结束
     *
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    /**
     * 用来遍历xml的开始标签
     *
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (tagName.equals(qName)) {
            try {
                t = clazz.newInstance();
            } catch (Exception e) {
                log.warn("解析xml出现错误，{}", e.getMessage());
                throw new IllegalStateException(e);
            }
        }
    }

    /**
     * 用来遍历xml文件的结束标签
     *
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (fieldMap.containsKey(qName)) {
            try {
                XMLUtil.invoke(t, qName, value, fieldMap, fieldTypeMap, fieldDateFormatMap);
            } catch (Exception e) {
                log.warn("解析xml出现错误，{}", e.getMessage());
                throw new IllegalStateException(e);
            }
        }
        if (tagName.equals(qName)) {
            if (t != null) {
                result.add(t);
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        value = new String(ch, start, length).trim();
    }

}
