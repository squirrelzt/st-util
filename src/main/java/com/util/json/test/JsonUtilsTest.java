package com.util.json.test;

import com.util.json.JsonUtils;

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


    public static void main(String[] args) throws Exception {
        JsonUtilsTest.obj2jsonTest();
        JsonUtilsTest.json2pojo();
        JsonUtilsTest.json2map();
        JsonUtilsTest.map2pojoTest();
        JsonUtilsTest.json2list();
    }
}
