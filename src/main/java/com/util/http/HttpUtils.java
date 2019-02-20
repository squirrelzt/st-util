package com.util.http;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * 类名称: HttpUtils
 * 类描述: http工具类
 * @author squirrel
 * @date 2019-02-20
 */
public class HttpUtils {
    private static final String JSON_UTF8_MIME_TYPE = "application/json;charset=utf-8";
    private static final int SUCCESS = 200;
    private static final String ENCODING = "UTF-8";
    private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
    private static final String HEADER_ACCEPT = "application/json";

    /**
     * HttpPost请求工具方法
     *
     * @param headerParam 请求头参数
     * @param bodyParam   请求体参数
     * @param url 请求地址
     * @return 响应结果, 在发生异常时将返回一个空对象.
     */
    public static String sendByPost(Map<String, String> headerParam, Map<String, String> bodyParam, String url) {
        String body = null;
        if (bodyParam != null) {
            body = JSON.toJSONString(bodyParam);
        }
        // 创建httpClient连接
        String result = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            // 设置请求和传输超时时间
            httpPost.setConfig(requestConfig);
            httpPost.setHeader("Content-type", JSON_UTF8_MIME_TYPE);
            httpPost.setHeader("Accept", HEADER_ACCEPT);
            if (headerParam != null) {
                // 为HttpPost设置Header参数
                for (Map.Entry<String, String> entry : headerParam.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            // 为HttpPost设置请求RequestBody参数（可为空）
            if (body != null) {
                // 解决中文乱码问题
                StringEntity stringEntity = new StringEntity(body, ENCODING);
                stringEntity.setContentType("application/json; charset=utf-8");
                httpPost.setEntity(stringEntity);
            }
            // HttpClient 发送Post请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == SUCCESS) {
                result = EntityUtils.toString(httpResponse.getEntity(), ENCODING);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * HttpGet请求工具方法
     *
     * @param headerParam 请求头参数
     * @param bodyParam 请求体参数
     * @param url 请求地址
     * @return 响应结果, 在发生异常时将返回一个空对象.
     */
    public static String sendByGet(Map<String, String> headerParam, Map<String, String> bodyParam, String url) {
        String result = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            URIBuilder uriBuilder = new URIBuilder(url);
            if (bodyParam != null) {
                for (Map.Entry<String, String> entry : bodyParam.entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            URI uri = uriBuilder.build();
            // 利用URL生成一个HttpGet请求
            HttpGet httpGet = new HttpGet(uri);
            if (headerParam != null) {
                for (Map.Entry<String, String> entry : headerParam.entrySet()) {
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }
            }
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == SUCCESS) {
                result = EntityUtils.toString(httpResponse.getEntity(), ENCODING);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    public static void main(String[] args) {
        getTest();
        postTest();
    }

    public static void getTest() {
        Map<String, String> header = new HashMap<>(1);
        Map<String, String> body = new HashMap<>(2);
        body.put("name", "squirrel");
        body.put("age", "20");
        String url = "http://localhost:3000/query";
        String result = HttpUtils.sendByGet(header, body, url);
        System.out.println(result);
    }
    public static void postTest() {
        Map<String, String> header = new HashMap<>(1);
        Map<String, String> body = new HashMap<>(2);
        body.put("username", "squirrel");
        body.put("password", "123456");
        String url = "http://localhost:3000/login";
        String result = HttpUtils.sendByPost(header, body, url);
        System.out.println(result);
    }
}
