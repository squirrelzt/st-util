package com.util.apache.commons.bean;

import org.apache.commons.beanutils.BasicDynaBean;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.ClassConverter;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class MyBeanUtils {

    public static void test() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Person person = new Person();
        person.setName("squrirrel");
        person.setAge(20);
        School school = new School();
        school.setName("daxue");
        school.setPerson(person);
        Map<String, String> map = BeanUtils.describe(school);
        for (Map.Entry<String, String> entry: map.entrySet()) {
            if (!"class".equals(entry.getKey())) {
                System.out.println(entry.getKey() + "\t" + entry.getValue());
            }
        }
        System.out.println(map);
    }

    public static Map<String, String> beanToMap(Object bean) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Map<String, String> map = BeanUtils.describe(bean);
        if (map.containsKey("class")) {
            map.remove("class");
        }
        return map;
    }

    public static Map beanToMap1(Object bean) {
        Map map = new BeanMap(bean);
        return map;
    }

    public static void main(String[] args) throws Exception {
//        MyBeanUtils.test();
        Person person = new Person();
        person.setName("squrirrel");
        person.setAge(21);
        School school = new School();
        school.setName("daxue");
        school.setPerson(person);
        Map map = MyBeanUtils.beanToMap1(school);
        System.out.println(map);
    }
}
