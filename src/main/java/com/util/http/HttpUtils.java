package com.util.http;

import com.alibaba.fastjson.JSON;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
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

    private HttpUtils(){}

    private static final String JSON_UTF8_MIME_TYPE = "application/json;charset=utf-8";
    private static final int SUCCESS = 200;
    private static final String ENCODING = "UTF-8";
    private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
    private static final String HEADER_ACCEPT = "application/json";
    private static final String HEADER_ACCEPT_ENCODING = "*";

    /**
     * HttpPost请求工具方法
     *
     * @param headerParam 请求头参数
     * @param bodyParam   请求体参数
     * @param url 请求地址
     * @return 响应结果, 在发生异常时将返回一个空对象.
     */
    private static String sendByPost(Map<String, String> headerParam, Map<String, String> bodyParam, String url) {
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
            // accept-encoding不设置默认值是gzip,deflate,返回值过长会压缩
            httpPost.setHeader("accept-encoding", HEADER_ACCEPT_ENCODING);
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
    private static String sendByGet(Map<String, String> headerParam, Map<String, String> bodyParam, String url) {
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

    /**
     * httpClient上传文件
     * @param filePath 文件路径
     * @param url 上传地址
     * @return 返回值
     */
    private static String uploadFile(String filePath, String url) {
        String result = null;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            CloseableHttpResponse httpResponse;
            File localFile = new File(filePath);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            //multipartEntityBuilder.addBinaryBody("file", file,ContentType.create("image/png"),"abc.pdf");
            //当设置了setSocketTimeout参数后，以下代码上传PDF不能成功，将setSocketTimeout参数去掉后此可以上传成功。上传图片则没有个限制
            //multipartEntityBuilder.addBinaryBody("file",file,ContentType.create("application/octet-stream"),"abd.pdf");
            multipartEntityBuilder.addBinaryBody("file", localFile);
            multipartEntityBuilder.addPart("comment", new StringBody("This is comment", ContentType.TEXT_PLAIN));
            multipartEntityBuilder.addTextBody("comment", "this is comment");
            HttpEntity httpEntity = multipartEntityBuilder.build();
            httpPost.setEntity(httpEntity);

            httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == SUCCESS) {
                result = EntityUtils.toString(httpResponse.getEntity(), ENCODING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 下载文件
     * @param url 下载地址
     * @param destFilePath 下载的文件本地存放路径
     */
    private static void downloadFile(String url, String destFilePath) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String filename = getFileName(httpResponse);
            HttpEntity httpEntity = httpResponse.getEntity();
            File file = new File(destFilePath + File.separator + filename);
            try (InputStream inputStream = httpEntity.getContent(); OutputStream outputStream = new FileOutputStream(file)) {
                int length;
                byte[] bytes = new byte[1024];
                while ((length = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, length);
                }
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取下载文件名
     * @param response {@link HttpResponse}
     * @return 文件名
     */
    private static String getFileName(HttpResponse response) {
        Header contentHeader = response.getFirstHeader("Content-Disposition");
        String filename = null;
        if (contentHeader != null) {
            HeaderElement[] values = contentHeader.getElements();
            if (values.length == 1) {
                NameValuePair param = values[0].getParameterByName("filename");
                if (param != null) {
                    try {
                        filename = param.getValue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return filename;
    }

    public static void main(String[] args) {
//        getTest();
//        postTest();
//        uploadTest();
        downloadTest();
    }

    private static void downloadTest() {
        String url = "http://localhost:8080/download/file";
        String filePath = "C:/Users/admin/Desktop/doc/server";
        downloadFile(url, filePath);
    }
    public static void uploadTest() {
        String result = uploadFile("C:/Users/admin/Desktop/doc/Java_manual.pdf","http://localhost:8080/upload");
        System.out.println(result);
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
