package com.useage.date;

public class TrimDemo {
    public static void main(String[] args) {
        String str = "aaa   bbb ccc" == null ? null : "aaa   bbb ccc".replaceAll(" ", "");
        System.out.println(str);
    }
}
