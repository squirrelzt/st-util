package com.util.json.test;

import com.util.json.JsonUtils;
import com.util.json.test.JsonDomain.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtilsTest {

    public static void obj2jsonTest() throws Exception {
        Person person = new Person();
        person.setName("squirrel");
        person.setAge(20);
        String jsonStr = JsonUtils.obj2json(person);
        System.out.println(jsonStr);
    }

    public static void json2pojo() throws Exception {
        String jsonStr = "{\"name\":\"squirrel\",\"age\":20}";
        Person person = JsonUtils.json2pojo(jsonStr, Person.class);
        System.out.println(person.getName());
        System.out.println(person.getAge());
    }

    public static void json2map() throws Exception {
        String jsonStr = "{\"name\":\"tom\",\"age\":\"22\"}";
        Map map = JsonUtils.json2map(jsonStr, Person.class);
        System.out.println(map);
    }

    public static void json2list() throws Exception {
        String jsonStr = "[{\"name\":\"tom\",\"age\":\"22\"}]";
        List<Person> list = JsonUtils.json2list(jsonStr, Person.class);
        System.out.println(list);
    }

    public static void map2pojoTest() throws Exception {
        Map map = new HashMap();
        map.put("name", "squirrel");
        map.put("age", 20);
        Person person = JsonUtils.map2pojo(map, Person.class);
        System.out.println(person);
    }

    public static void json2ObjectTest() throws Exception {
        String jsonStr = "{\"name\":\"tom\",\"age\":\"22\"}";
        Map map = JsonUtils.json2Object(jsonStr, Map.class);
        System.out.println(map);
    }

    public static void msp2JsonTest() {
        Map<String, Object> map = new HashMap<>(3);
        map.put("name", "tome");
        map.put("age", 30);
        String jsonStr = JsonUtils.map2Json(map);
        System.out.println(jsonStr);
    }

    public static void orderJsonTest() {
        String json = "{\"instReprName\":\"tom\",\"businessLife\":\"businessLife\",\"contacts\":\"tom\"}";
        String orderJson = JsonUtils.orderJson(json);
        System.out.println(orderJson);
    }
    public static void main(String[] args) throws Exception {
        obj2jsonTest();
        json2pojo();
        json2map();
        map2pojoTest();
        json2list();
        json2ObjectTest();
        msp2JsonTest();
        orderJsonTest();
    }
}
