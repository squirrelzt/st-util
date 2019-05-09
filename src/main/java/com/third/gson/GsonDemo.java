package com.third.gson;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.util.json.test.Person;

public class GsonDemo {

    public static String serial(Person person) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(person);
        return jsonStr;
    }

    public static Person deserial(String jsonStr) {
        Gson gson = new Gson();
        Person person = gson.fromJson(jsonStr, Person.class);
        return person;
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("tom");
        person.setAge(20);
        String jsonStr = GsonDemo.serial(person);
        System.out.println(jsonStr);
        Person gsonPerson = GsonDemo.deserial(jsonStr);
        System.out.println(JSON.toJSON(gsonPerson));
    }
}
