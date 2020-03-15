package com.useage.doublecolon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 类名称: DoubleColon
 * 类描述: 双冒号用法
 * 描述: 表达式：person -> person.getAge();使用双冒号：Person：：getAge
 * 表达式: new HashMap<>() 使用双冒号:HsahMap :: new
 */
public class DoubleColon {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c");

        for (String item: list) {
            print(item);
        }

        list.forEach(item -> DoubleColon.print(item));

        list.forEach(DoubleColon::print);

        Consumer<String> consumer = DoubleColon::print;
        list.forEach(item -> consumer.accept(item));

        List<String> list1 = list.stream().map(item -> item.toUpperCase()).collect(Collectors.toList());
        System.out.println(list1.toString());

        ArrayList<String> collect = list.stream().map(String::toUpperCase).collect(Collectors.toCollection(ArrayList::new));
        System.out.println(collect);
    }

    public static void print(String str) {
        System.out.println("print value: " + str);
    }
}
