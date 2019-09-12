package com.useage.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 类名称: LocalDateUsage
 * 类描述: 日期类
 * @author squirrel
 * @date 2019-09-12
 */
public class LocalDateUsage {

    public static void main(String[] args) {
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        LocalDateTime nowDateTime = LocalDateTime.now();


    }

    /**
     * 获取当前年与日;时分秒毫秒;年月日时分秒毫秒
     */
    private static void getNow() {
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        LocalDateTime nowDateTime = LocalDateTime.now();

        System.out.println(nowDate);
        System.out.println(nowTime);
        System.out.println(nowDateTime);

    }

    /**
     * 自定义时间格式yyyy-MM-dd hh:mm:ss
     */
    private static void format() {
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        LocalDateTime nowDateTime = LocalDateTime.now();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String nowDateFormat = nowDate.format(dateFormatter);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        String nowTimeFormat = nowTime.format(timeFormatter);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String nowDateTimeFormat = nowDateTime.format(dateTimeFormatter);
        System.out.println(nowDateFormat);
        System.out.println(nowTimeFormat);
        System.out.println(nowDateTimeFormat);
    }

    /**
     * 获取单个元素: 年、月、日、时、分、秒、毫秒、星期
     */
    private static void getItem() {
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        LocalDateTime nowDateTime = LocalDateTime.now();

        int year = nowDate.getYear();
        int month = nowDate.getMonthValue();
        int day = nowDate.getDayOfMonth();


        int hour = nowTime.getHour();
        int minute = nowTime.getMinute();
        int second = nowTime.getSecond();

        int year1 = nowDateTime.getYear();
        int month1 = nowDateTime.getMonthValue();
        int day1 = nowDateTime.getDayOfMonth();
        int hour1 = nowDateTime.getHour();
        int minute1 = nowDateTime.getMinute();
        int second1 = nowDateTime.getSecond();

        System.out.println(year);
        System.out.println(month);
        System.out.println(day);

        System.out.println(hour);
        System.out.println(minute);
        System.out.println(second);

        System.out.println(year1);
        System.out.println(month1);
        System.out.println(day1);
        System.out.println(hour1);
        System.out.println(minute1);
        System.out.println(second1);

        DayOfWeek week = nowDate.getDayOfWeek();
        System.out.println(week.toString().toLowerCase());
    }

    private static void plus() {
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        LocalDateTime nowDateTime = LocalDateTime.now();

        LocalDate lastYear = nowDate.plusYears(1);
        LocalDate lastMonth = nowDate.plusMonths(1);
        LocalDate lastDay = nowDate.plusDays(1);

        LocalDateTime lastYear1 = nowDateTime.plusYears(1);
        LocalDateTime lastMonth1 = nowDateTime.plusMonths(1);
        LocalDateTime lastDay1 = nowDateTime.plusDays(1);

        System.out.println(lastYear);
        System.out.println(lastMonth);
        System.out.println(lastDay);

        System.out.println(lastYear1);
        System.out.println(lastMonth1);
        System.out.println(lastDay1);
    }
}
