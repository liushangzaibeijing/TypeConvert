package com.xiu.sb.TypeConvert.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class JsonUtil {

    static ObjectMapper mapper = new ObjectMapper();

    public static byte[] obj2byte(Object obj){
        try {
            return mapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            log.error("json 转换错误",e);
        }
        return null;
    }

    public static String obj2Str(Object obj){
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("json 转换错误",e);
        }
        return null;
    }

    public static <T> T str2Obj(byte[] bytes,Class<T> clazz){
        try {
          T t =  mapper.readValue(bytes,clazz);
          return t;
        } catch (JsonProcessingException e) {
            log.error("json 转换错误",e);
        } catch (IOException e) {
            log.error("字节读取错误",e);
        }
        return null;
    }

    public static <T> List<T> str2List(String json, Class<T> clazz){
        try {
            List<T> t =  mapper.readValue(json,new TypeReference<List<T>>(){});
            return t;
        } catch (JsonProcessingException e) {
            log.error("json 转换错误",e);
        } catch (IOException e) {
            log.error("字节读取错误",e);
        }
        return null;
    }

}
