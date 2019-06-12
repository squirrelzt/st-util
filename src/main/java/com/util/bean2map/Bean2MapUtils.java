package com.util.bean2map;

import com.util.json.test.JsonDomain;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Bean2MapUtils {
    public static void main(String[] args) throws Exception {
        JsonDomain.Person person = new JsonDomain.Person();
        person.setName("tom");
        person.setAge(20);
        Map<String, String> map = objectToMap(person);
        System.out.println(map);
    }

    public static Map<String, String> objectToMap(Object obj) throws Exception {
        Map<String, String> map = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property: propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            String value = getter != null ? getter.invoke(obj).toString() : null;

            map.put(key, value);
        }
        return map;
    }
}
