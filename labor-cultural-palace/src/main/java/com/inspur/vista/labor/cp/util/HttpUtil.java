package com.inspur.vista.labor.cp.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * HTTP工具类
 */
public class HttpUtil {

    /**
     * 日志文件
     */
    private final static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 超时时间
     */
    private static final int TIMEOUT = 10 * 1000;

    /**
     * HTTP GET
     *
     * @param url
     * @return
     */
    public static Map get(String url) throws Exception {
        return get(url, null, null);
    }

    /**
     * HTTP GET
     *
     * @param url
     * @return
     */
    public static Map get(String url, Map<String, String> param) throws Exception {
        logger.info("The Request（GET） URL IS:{}, param:{}", url, param);

        return get(url, param, null);
    }

    /**
     * HTTP GET
     *
     * @param url
     * @return
     */
    public static Map get(String url, Map<String, String> param, Map<String, String> headerInfo) {
        logger.info("The Request（GET） URL IS:{}, param:{}", url, param);

        if (param != null && !param.isEmpty()) {
            url = url + "?" + mapToUrl(param);
        }
        HttpGet httpGet = new HttpGet(url);

        if (headerInfo != null && !headerInfo.isEmpty()) {
            for (Map.Entry<String, String> entry : headerInfo.entrySet()) {
                httpGet.addHeader(entry.getKey(), entry.getValue());
            }
        }

        // 执行HTTP请求
        Map resultMap = doHttpRequest(httpGet);

        logger.info("The Response Data is :{}", resultMap);
        return resultMap;
    }

    /**
     * HTTP DELETE
     *
     * @param url
     * @return
     */
    public static Map delete(String url, Map param, Map<String, String> headerInfo) throws Exception {
        logger.info("The Request（DELETE） URL IS:{}, param:{}", url, param);

        if (StringUtils.isNotBlank((String)param.get("userId"))) {
            //url = url + "/" + mapToUrl(param);
            url = url + "/" + param.get("userId");
            url = url + "?untyingReason="+param.get("untyingReason")+"&operator="+param.get("operator");

        }
        HttpDelete httpDelete = new HttpDelete(url);

        if (headerInfo != null && !headerInfo.isEmpty()) {
            for (Map.Entry<String, String> entry : headerInfo.entrySet()) {
                httpDelete.addHeader(entry.getKey(), entry.getValue());
            }
        }

        // 执行HTTP请求
        Map resultMap = doHttpRequest(httpDelete);

        logger.info("The Response Data is :{}", resultMap);
        return resultMap;
    }

    /**
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
     */
    public static Map post(String url, Map<String, String> params) {
        return post(url, params, null);
    }

    /**
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
     */
    public static Map post(String url, Map<String, String> params, Map<String, String> headerInfo) {
        logger.info("The Request（post） URL IS:{}, param:{},headerInfo:{}", url, params, headerInfo);

        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        // 创建参数队列
        try {
            httppost.setEntity(new UrlEncodedFormEntity(mapToNameValuePairs(params), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            logger.error("Error Occured when try to UnsupportedEncodingException (GET) ", e);
        }
        if (headerInfo != null && !headerInfo.isEmpty()) {
            for (Map.Entry<String, String> entry : headerInfo.entrySet()) {
                httppost.addHeader(entry.getKey(), entry.getValue());
            }
        }

        // 执行HTTP请求
        Map resultMap = doHttpRequest(httppost);

        logger.info("The Response Data is :{}", resultMap);
        return resultMap;
    }

    /**
     * post请求，支持请求头
     *
     * @param url
     * @param paramsJson
     * @param headerInfo
     * @return
     * @throws Exception
     */
    public static Map post(String url, String paramsJson, Map<String, String> headerInfo) {

        logger.info("The Request（post） URL IS:{}, param:{},headerInfo:{}", url, paramsJson, headerInfo);

        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        // 创建参数队列
        StringEntity entity = new StringEntity(paramsJson, "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httppost.setEntity(entity);
        if (headerInfo != null && !headerInfo.isEmpty()) {
            for (Map.Entry<String, String> entry : headerInfo.entrySet()) {
                httppost.addHeader(entry.getKey(), entry.getValue());
            }
        }

        // 执行HTTP请求
        Map resultMap = doHttpRequest(httppost);

        logger.info("The Response Data is :{}", resultMap);
        return resultMap;
    }

    /**
     * map转换成url 共通函数
     *
     * @return
     */
    public static String mapToUrl(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuffer strb = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            strb.append(entry.getKey() + "=" + entry.getValue().replaceAll(" ", "%20"));
            strb.append("&");
        }
        String url = strb.toString();
        if (url.endsWith("&")) {
            url = StringUtils.substringBeforeLast(url, "&");
        }
        return url;
    }

    /**
     * map转换成List<NameValuePair> 共通函数
     *
     * @param params
     * @return
     */
    public static List<NameValuePair> mapToNameValuePairs(Map<String, String> params) {
        List<NameValuePair> list = new ArrayList<>();
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        return list;
    }

    /**
     * 获取登录用户的IP
     */
    public static String getLocalIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * JSON 数据转为MAP
     *
     * @param jsonStr
     * @return
     */
    private static Map jsonToMap(String jsonStr) {

        JSONObject obj = JSONObject.parseObject(jsonStr);
        Set<?> set = obj.keySet();
        Map<String, Object> map = new HashMap<>(set.size());
        for (Map.Entry<String, Object> entry : obj.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    /**
     * 发送 put请求访问本地应用并根据传递参数不同返回不同结果
     */
    public static Map put(String url, String paramsJson, Map<String, String> headerInfo) throws Exception {
        logger.info("The Request（put） URL IS:{}, param:{},headerInfo:{}", url, paramsJson, headerInfo);

        // 创建httpput
        HttpPut httpput = new HttpPut(url);
        // 创建参数队列
        httpput.setEntity(new StringEntity(paramsJson));
        if (headerInfo != null && !headerInfo.isEmpty()) {
            for (Map.Entry<String, String> entry : headerInfo.entrySet()) {
                httpput.addHeader(entry.getKey(), entry.getValue());
            }
        }

        // 执行HTTP请求
        Map resultMap = doHttpRequest(httpput);

        logger.info("The Response Data is :{}", resultMap);
        return resultMap;
    }


    /**
     * 执行HTTP请求
     */
    private static Map doHttpRequest(HttpUriRequest request) {

        logger.info("Do execute -- start, Detail:{}", request);
        Map resultMap = null;
        CloseableHttpClient httpClient = HttpConnectionPoolUtil.getHttpClient(request);

        // 创建默认的httpClient实例.
        try (CloseableHttpResponse response = httpClient.execute(request)) {

            StringWriter writer = new StringWriter();
            IOUtils.copy(response.getEntity().getContent(), writer, "UTF-8");
            String responseStr = writer.toString();
            // json字符串转map
            resultMap = jsonToMap(responseStr);

        } catch (Exception e) {
            resultMap = new HashMap(2);
            logger.error("Error Occured when try to Exexute Http Request ", e);
        }

        logger.info("Do execute -- end");
        return resultMap;
    }


    public static void main(String[] args) {
        try {
            get("http://www.baidu.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
