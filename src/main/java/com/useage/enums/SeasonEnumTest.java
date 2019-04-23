package com.useage.enums;

public class SeasonEnumTest {
    public static void main(String[] args) {
        int num = 1;
        SeasonEnum seasonEnum = SeasonEnum.SPRING;
        seasonEnum.getSeq();
        System.out.println(SeasonEnum.SPRING.getSeq());
        System.out.println(SeasonEnum.SPRING);
    }
}
