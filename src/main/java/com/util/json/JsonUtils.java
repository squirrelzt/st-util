package com.util.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类名称: JsonUtils
 * 类描述: json工具类
 * @author squirrel
 * @date 2019-02-21
 */
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转json字符串
     * @param obj 对象
     * @return json字符串
     * @throws Exception 异常
     */
    public static String obj2json(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * json字符串转对象
     * @param jsonStr json字符串
     * @param clazz 类
     * @param <T> 泛型
     * @return 对象
     * @throws Exception 异常
     */
    public static <T> T json2pojo(String jsonStr, Class<T> clazz) throws Exception {
        return objectMapper.readValue(jsonStr, clazz);
    }

    /**
     * json字符串转map
     * @param jsonStr json字符串
     * @param <T> 泛型
     * @return Map
     * @throws Exception 异常
     */
    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) throws Exception {
//        Map<String, Map<String, Object>> map = objectMapper.readValue(jsonStr, new TypeReference<Map<String, T>>() {});
//        Map<String, T> result = new HashMap<String, T>();
//        for (Map.Entry<String, Map<String, Object>> entry: map.entrySet()) {
//            result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
////            result.put(entry.getKey(), map2pojo(map, clazz));
//        }
        Map<String, T> result = objectMapper.readValue(jsonStr, Map.class);
        return result;
    }

    /**
     * json字符串转list
     * @param jsonArrayStr json字符串
     * @param clazz 类
     * @param <T> 泛型
     * @return list
     * @throws Exception 异常
     */
    public static <T> List<T> json2list (String jsonArrayStr, Class<T> clazz) throws Exception {
        List<Map<String, Object>> list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {});
        List<T> result = new ArrayList<T>();
        for (Map<String, Object> map: list) {
            result.add(map2pojo(map, clazz));
        }
        return result;
    }

    /**
     * map转json字符串
     * @param map Map
     * @param clazz 类
     * @param <T> 泛型
     * @return 异常
     */
    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }
}
