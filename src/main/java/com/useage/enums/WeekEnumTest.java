package com.useage.enums;

public class WeekEnumTest {

    public static void main(String[] args) {
        for (WeekEnum weekEnum : WeekEnum.values()) {
            System.out.println(weekEnum.getDayOfWeek());
            System.out.println(weekEnum.getDesc());
        }
    }
}
