package com.useage.enums;

import lombok.Getter;

@Getter
public enum WeekEnum {

    /**
     * 星期一
     */
    MONDAY(1, "Monday"),

    /**
     * 星期二
     */
    TUESTDAY(2, "Tuesday"),

    /**
     * 星期三
     */
    WEDNESDAY(3, "Wednesday"),

    /**
     * 星期四
     */
    THURSDAY(4, "Thursday"),

    /**
     * 星期五
     */
    FRIDAY(5, "Friday"),

    /**
     * 星期六
     */
    SATURDAY(6, "Saturday"),

    /**
     * 星期日
     */
    SUNDAY(7, "Sunday");

    private int dayOfWeek;

    private String desc;

    WeekEnum(int dayOfWeek, String desc) {
        this.dayOfWeek = dayOfWeek;
        this.desc = desc;
    }
}
