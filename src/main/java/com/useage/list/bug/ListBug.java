package com.useage.list.bug;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

public class ListBug {
    public static void main(String[] args) {

        int[] arr = {1, 2, 3};
        List list = Arrays.asList(arr);
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.get(0).getClass());
    }
}
