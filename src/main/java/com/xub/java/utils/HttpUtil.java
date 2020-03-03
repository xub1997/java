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
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLDecoder;
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
    public static JSONObject doGet(String url, Map<String, String> params) {
        JSONObject jsonObject = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
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
            response = client.execute(request);
            HttpEntity responseEntity = response.getEntity();
            //读取服务器返回过来的json字符串数据并解决乱码问题
            String strResult = EntityUtils.toString(responseEntity, "UTF-8");
            //请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                EntityUtils.consume(responseEntity);//关闭资源
                //把json字符串转换成json对象
                jsonObject = JSONObject.parseObject(strResult);
                log.info("get请求提交成功:{},返回结果：{}", url, strResult);
            } else {
                log.error("get请求提交失败:{},原因：{}", url, strResult);
            }
        } catch (Exception e) {
            log.error("get请求提交失败，链接: {}", url, e);
        }finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(client != null){
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
    public static JSONObject doPost(String url, Map<String,String> params) {
        JSONObject jsonObject = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        try {
            client = HttpClientBuilder.create().build();
            url = URLDecoder.decode(url, "UTF-8");
            URIBuilder uri = new URIBuilder(url);
            if(params != null && params.size()>0){
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

            httpPost.setHeader("Content-Type", "application/json;charset=utf8");

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
            response = client.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            //读取服务器返回过来的json字符串数据并解决乱码问题
            String strResult = EntityUtils.toString(responseEntity, "UTF-8");
            //请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                EntityUtils.consume(responseEntity);//关闭资源
                //把json字符串转换成json对象
                jsonObject = JSONObject.parseObject(strResult);
            } else {
                log.error("post请求提交失败:{},原因：{}", url, strResult);
            }
            ((CloseableHttpClient) client).close();
        } catch (Exception e) {
            log.error("post请求提交失败，链接: {}", url, e);
        }finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(client != null){
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonObject;
    }


    public static void main(String[] args) {
        Map<String, String> params = new HashMap<>();
        params.put("allowComment", "0");
        params.put("content", "213123123");
        params.put("coverUrl", "http://www.dsfds.com/sadas.jpg");
        params.put("remark", "测试备注");
        params.put("summary","测试摘要");
        params.put("title","测试标题");
        params.put("userId","1");
        System.out.println(HttpUtil.doPost("https://www.zhangaishan.com.cn/wxApp/portal/article", params));

    }

}
