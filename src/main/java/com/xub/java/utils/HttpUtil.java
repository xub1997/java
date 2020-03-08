package com.xub.java.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

@Slf4j
public class HttpUtil {

    private HttpUtil() {
    }

    /**
     * get请求
     *
     * @param url
     * @param params
     * @return
     */
    public static JSONObject doGet(String url, Map<String, String> params) throws IOException {
        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = HttpClientBuilder.create().build();
            url = URLDecoder.decode(url, "UTF-8");
            URIBuilder uri = new URIBuilder(url);
            //get请求带参数
            if (params != null && params.size() > 0) {
                List<NameValuePair> paramList = new ArrayList<>(params.size());
                Set<String> keySet = params.keySet();
                if (keySet != null && keySet.size() > 0) {
                    for (String key : keySet) {
                        BasicNameValuePair param = new BasicNameValuePair(key, params.get(key));
                        paramList.add(param);
                    }
                }
                uri.setParameters(paramList);
            }
            //发送get请求
            HttpGet request = new HttpGet(uri.build());
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    /*// 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true)*/.build();
            request.setConfig(requestConfig);
            //执行请求
            response = client.execute(request);
            //获取返回实体
            HttpEntity responseEntity = response.getEntity();
            //读取服务器返回过来的json字符串数据并解决乱码问题
            String strResult = EntityUtils.toString(responseEntity, "UTF-8");
            //请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //关闭资源
                EntityUtils.consume(responseEntity);
                //把json字符串转换成json对象
                jsonObject = JSONObject.parseObject(strResult);
                log.info("get请求提交成功:{},返回结果：{}", url, strResult);
            } else {
                log.error("get请求提交失败:{},原因：{}", url, strResult);
            }
        } catch (Exception e) {
            log.error("get请求提交失败，链接: {}", url, e);
        } finally {
            if (response != null) {
                response.close();
            }
            if (client != null) {
                client.close();
            }
        }
        return jsonObject;
    }


    /**
     * post请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, Map<String, String> params, Map<String, String> heads, boolean isHttps) throws IOException {
        String strResult = null;
        SSLContext sslContext = null;
        SSLConnectionSocketFactory sslConnectionSocketFactory = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            if (isHttps) {
                //配置https，ssl套接层
                sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                    // 信任所有
                    @Override
                    public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        return true;
                    }
                }).build();
                sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
                client = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
            } else {
                //普通http请求
                client = HttpClients.createDefault();
            }
            url = URLDecoder.decode(url, "UTF-8");
            URIBuilder uri = new URIBuilder(url);
            //参数
            if (params != null && params.size() > 0) {
                List<NameValuePair> paramList = new ArrayList<>(params.size());
                Set<String> keySet = params.keySet();
                if (keySet != null && keySet.size() > 0) {
                    for (String key : keySet) {
                        BasicNameValuePair param = new BasicNameValuePair(key, params.get(key));
                        paramList.add(param);
                    }
                }
                uri.setParameters(paramList);
            }
            // 创建Post请求
            HttpPost httpPost = new HttpPost(uri.build());
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    /*// 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true)*/.build();
            httpPost.setConfig(requestConfig);

            //header头部信息
            if (heads != null && heads.size() > 0) {
                Set<Map.Entry<String, String>> entrySet = heads.entrySet();
                for (Map.Entry<String, String> entry : entrySet) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            httpPost.setHeader("Content-Type", "application/json;charset=utf8");

            //执行请求
            response = client.execute(httpPost);
            //获取返回实体
            HttpEntity responseEntity = response.getEntity();
            //读取服务器返回过来的json字符串数据并解决乱码问题
            strResult = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
            //获取返回状态码
            int statusCode = response.getStatusLine().getStatusCode();
            log.info("返回的状态码：{}", statusCode);
            //请求发送成功，并得到响应
            if (statusCode == HttpStatus.SC_OK) {
                //关闭资源
                EntityUtils.consume(responseEntity);
                if (params != null && params.size() > 0) {
                    log.info("post请求提交成功:{},请求参数:{},返回结果：{}", url, JSONObject.toJSONString(params), strResult);
                } else {
                    log.info("post请求提交成功:{},返回结果：{}", url, strResult);
                }
            } else {
                if (params != null && params.size() > 0) {
                    log.error("post请求提交失败:{},请求参数:{},返回结果：{}", url, JSONObject.toJSONString(params), strResult);
                } else {
                    log.error("post请求提交失败:{},返回结果：{}", url, strResult);
                }
            }
        } catch (Exception e) {
            log.error("post请求提交失败，链接: {}", url, e);
        } finally {
            if (response != null) {
                response.close();
            }
            if (client != null) {
                client.close();
            }
        }
        return strResult;
    }

    /**
     * post请求
     *
     * @param url
     * @param jsonString
     * @return
     */
    public static String doPost(String url, String jsonString, Map<String, String> heads, boolean isHttps) throws IOException {
        String strResult = null;
        SSLContext sslContext = null;
        SSLConnectionSocketFactory sslConnectionSocketFactory = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            if (isHttps) {
                //配置https，ssl套接层
                sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                    // 信任所有
                    @Override
                    public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        return true;
                    }
                }).build();
                sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
                client = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
            } else {
                //普通http请求
                client = HttpClients.createDefault();
            }
            url = URLDecoder.decode(url, "UTF-8");
            // 创建Post请求
            HttpPost httpPost = new HttpPost(url);
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    /*// 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true)*/.build();
            httpPost.setConfig(requestConfig);
            //header头部信息
            if (heads != null && heads.size() > 0) {
                Set<Map.Entry<String, String>> entrySet = heads.entrySet();
                for (Map.Entry<String, String> entry : entrySet) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            httpPost.setHeader("Content-Type", "application/json;charset=utf8");

            //json数据
            if (AssertUtil.isNotEmpty(jsonString)) {
                StringEntity entity = new StringEntity(jsonString, "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }
            //执行请求
            response = client.execute(httpPost);
            //获取返回实体
            HttpEntity responseEntity = response.getEntity();
            //读取服务器返回过来的json字符串数据并解决乱码问题
            strResult = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
            //获取返回状态码
            int statusCode = response.getStatusLine().getStatusCode();
            log.info("返回的状态码：{}", statusCode);
            //请求发送成功，并得到响应
            if (statusCode == HttpStatus.SC_OK) {
                //关闭资源
                EntityUtils.consume(responseEntity);
                if (AssertUtil.isNotEmpty(jsonString)) {
                    log.info("post请求提交成功:{},请求参数:{},返回结果：{}", url, jsonString, strResult);
                } else {
                    log.info("post请求提交成功:{},返回结果：{}", url, strResult);
                }
            } else {
                if (AssertUtil.isNotEmpty(jsonString)) {
                    log.error("post请求提交失败:{},请求参数:{},返回结果：{}", url, jsonString, strResult);
                } else {
                    log.error("post请求提交失败:{},返回结果：{}", url, strResult);
                }
            }
        } catch (Exception e) {
            log.error("post请求提交失败，链接: {}", url, e);
        } finally {
            if (response != null) {
                response.close();
            }
            if (client != null) {
                client.close();
            }
        }
        return strResult;
    }



}
