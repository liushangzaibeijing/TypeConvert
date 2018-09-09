package com.xiu.sb.TypeConvert.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * HttpClient工具类
 * @author xieqx
 */
@Component
@PropertySource("classpath:application.properties")
public class HttpClientUtil
{
    /**
     * 日志记录
     */
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private static RequestConfig requestConfig = null;

    static
    {
        // 设置请求和传输超时时间
        requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
    }

    /**
     * 生成签名 并将新的参数存放进入map中
     * @param data
     * @return
     */
    public  <T extends Object> Map<String,String>  getParamMap(T  data){
        //先获取appliction.properties中需要的相关参数 使用@Value注解

        Map<String,String> paramMap = new TreeMap<String, String>();
        //获取data的class对象
        Class clazz = data.getClass();
        getAllField(data, paramMap, clazz);
        return paramMap;
    }

    /**
     * 反射获取对象的所有属性字段
     * @param data
     * @param paramMap
     * @param clazz
     * @param <T>
     */
    private  <T> void getAllField(T data, Map<String,String> paramMap, Class clazz) {
        getField(data,paramMap,clazz);
        Class superclazz = clazz.getSuperclass();
        if(superclazz == Object.class){
            return;
        }
        getAllField(data,paramMap,superclazz);
    }

    private  <T> void getField(T data, Map<String,String> paramMap, Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        //获取到的
        for(Field field :fields){
            try {
                //设置私有的属性可以访问
                field.setAccessible(true);
                String fieldName = field.getName();
                Object objectValue = field.get(data);
                if(objectValue!=null){
                    //不同的实例转换为不同的对象
                    String fieldvalue = null;
                    if(objectValue instanceof  Integer){
                        fieldvalue = Integer.toString((Integer) objectValue);
                    }
                   else if(objectValue instanceof  Long){
                        fieldvalue = Long.toString((Long) objectValue);
                    }
                    else if(objectValue instanceof  Double){
                        fieldvalue = Double.toString((Double) objectValue);
                    }
                    else if(objectValue instanceof  Float){
                        fieldvalue = Float.toString((Float) objectValue);
                    }
                    else if(objectValue instanceof Date) {
                        fieldvalue = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) objectValue);
                    }
                    else if(objectValue instanceof String) {
                        fieldvalue = (String)objectValue;
                    }
                    else {
                        fieldvalue = JSON.toJSONString(objectValue);
                        ObjectMapper mapper = new ObjectMapper();
                        fieldvalue = mapper.writeValueAsString(objectValue);
                    }
                    paramMap.put(fieldName,fieldvalue);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    public void httpPostUrlEncoded(String url, Object data){
        httpPostUrlEncoded(url, getParamMap(data), "UTF-8");
    }


    /**
     *  post请求传输json参数 application/x-www-form-urlencoded
     * @param url url地址
     * @param data json字符串
     * @param contentEncoding 编码方式
     * @return
   */
    public void httpPostUrlEncoded(String url, Map<String,String> data, String contentEncoding)
    {
        // post请求返回结果
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        // 设置请求和传输超时时间
        httpPost.setConfig(requestConfig);
        try
        {
            if (null != data)
            {
                logger.info("request param："+JSON.toJSONString(data));
                // 解决中文乱码问题
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> entry : data.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                //设置参数到请求对象中
                logger.info("form key= value: {}", JsonUtil.obj2Str(nvps));
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, contentEncoding));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            // 请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {
                try
                {
                    // 读取服务器返回过来的json字符串数据
                    String  str = EntityUtils.toString(response.getEntity(), "utf-8");
                    logger.info("response param："+str+", url:"+url);
                }
                catch (Exception e)
                {
                    logger.error("post请求提交失败:" + url, e);
                }
            }
        }
        catch (IOException e)
        {
            logger.error("post请求提交失败:" + url, e);
        }
        finally
        {
            httpPost.releaseConnection();
        }
    }


}