package com.xub.java.utils.xml;

import com.xub.java.utils.ReflectionUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xub
 * @Name: XMLUtilByDOM
 * @Description: TODO
 * @date 2020/2/16  14:05
 */
@Slf4j
public class XMLUtilByDOM extends XMLUtil {

    /**
     * DocumentBuilderFactory对象
     */
    private static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

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
    public static <T> List<T> read(@NonNull InputStream inputStream,@NonNull  String tagName,@NonNull Class<T> clazz) {
        try {
            //创建DocumentBuilder对象
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            //解析出document对象，代表整个xml文档
            Document document = documentBuilder.parse(inputStream);
            //获取节点集合
            NodeList nodeList = document.getElementsByTagName(tagName);
            int nodeListLength = nodeList.getLength();
            //返回结果
            List<T> result = new ArrayList<>(nodeListLength);
            //得到目标类的所有可解析的字段及setter方法
            Map<String, Method> fieldMap = ReflectionUtil.getFieldSetterMap(clazz);
            //得到目标类的所有可解析的字段类型
            Map<String, String> fieldTypeMap = ReflectionUtil.getFieldTypeMap(clazz);
            //得到目标类的所有可解析的字段的时间格式
            Map<String, String> fieldDateFormatMap = ReflectionUtil.getFieldDateFormatMap(clazz);
            //逐条解析
            for (int i = 0; i < nodeListLength; i++) {
                T t = clazz.newInstance();
                //获取当前节点的所有子节点
                NodeList childNodes = nodeList.item(i).getChildNodes();
                int childNodesLength = childNodes.getLength();
                //遍历属性
                for (int j = 0; j < childNodesLength; j++) {
                    Node node = childNodes.item(j);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        if (fieldMap.containsKey(node.getNodeName())) {
                            //赋值
                            invoke(t, node.getNodeName(), node.getTextContent(), fieldMap, fieldTypeMap, fieldDateFormatMap);
                        }
                    }
                }
                result.add(t);
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
    public static <T> void write(@NonNull String fileURI,@NonNull  List<T> datas,@NonNull  String rootName,@NonNull  String tagName) {
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
    public static <T> void write(@NonNull File file,@NonNull  List<T> datas,@NonNull  String rootName,@NonNull  String tagName) {
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
    public static <T> void write(@NonNull OutputStream outputStream,@NonNull  List<T> datas,@NonNull  String rootName,@NonNull  String tagName) {
        if (datas == null || datas.size() < 1) {
            log.warn("写入xml错误，数据为空，请检验数据");
            throw new IllegalStateException("写入xml错误，数据为空，请检验数据");
        }
        try {
            //创建DocumentBuilder对象
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            //新建document文件
            Document document = documentBuilder.newDocument();
            //设置Standalone = true
            document.setXmlStandalone(true);
            Element rootElement = document.createElement(rootName);
            Class clazz = datas.get(0).getClass();
            //得到目标类的所有可解析的字段及getter方法
            Map<String, Method> fieldMap = ReflectionUtil.getFieldGetterMap(clazz);
            //得到目标类的所有可解析的字段类型
            Map<String, String> fieldTypeMap = ReflectionUtil.getFieldTypeMap(clazz);
            //得到目标类的所有可解析的字段的时间格式
            Map<String, String> fieldDateFormatMap = ReflectionUtil.getFieldDateFormatMap(clazz);
            for (T data : datas) {
                Element childElement = document.createElement(tagName);
                Set<String> fieldsSet = fieldMap.keySet();
                for (String field : fieldsSet) {
                    String fieldValue = reinvoke(data, field, fieldMap, fieldTypeMap, fieldDateFormatMap);
                    if(fieldValue != null){
                        Element element = document.createElement(field);
                        element.setTextContent(fieldValue);
                        childElement.appendChild(element);
                    }
                }
                rootElement.appendChild(childElement);
            }
            document.appendChild(rootElement);
            //创建TransformerFactory对象
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            //创建Transformer
            Transformer transformer = transformerFactory.newTransformer();
            //设置
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            //输出到文件中
            transformer.transform(new DOMSource(document), new StreamResult(outputStream));
        } catch (Exception e) {
            log.warn("写入xml出现错误，{}", e.getMessage());
            throw new IllegalStateException(e);
        }
    }


}
