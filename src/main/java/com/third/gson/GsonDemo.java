package com.third.gson;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

public class GsonDemo {

    public static String serial(GsonDomain.Person person) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(person);
        return jsonStr;
    }

    public static GsonDomain.Person deserial(String jsonStr) {
        Gson gson = new Gson();
        GsonDomain.Person person = gson.fromJson(jsonStr, GsonDomain.Person.class);
        return person;
    }

    public static void main(String[] args) {
        GsonDomain.Person person = new GsonDomain.Person();
        person.setName("tom");
        person.setAge(20);
        String jsonStr = GsonDemo.serial(person);
        System.out.println(jsonStr);
        GsonDomain.Person gsonPerson = GsonDemo.deserial(jsonStr);
        System.out.println(JSON.toJSON(gsonPerson));
    }
}
