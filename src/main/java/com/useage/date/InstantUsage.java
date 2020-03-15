package com.useage.date;

import java.time.Instant;

public class InstantUsage {
    public static void main(String[] args) {
        System.out.println("时间线上的原点");
        System.out.println(Instant.EPOCH);

        System.out.println("时间线的最大值");
        System.out.println(Instant.MAX);

        System.out.println("时间线的最小值");
        System.out.println(Instant.MIN);

        System.out.println("当前时间点");
        Instant now = Instant.now();
        System.out.println(now);
        System.out.println(now.getEpochSecond());
        System.out.println(now.getNano());

        System.out.println("根据距离原点的秒构造时间点");
        System.out.println("①距离原点1秒");
        Instant nowAddOneSecond = Instant.ofEpochSecond(1L);
        System.out.println(nowAddOneSecond);
        System.out.println(nowAddOneSecond.getEpochSecond());
        System.out.println(nowAddOneSecond.getNano());
        System.out.println(nowAddOneSecond.toEpochMilli());

        System.out.println("①距离原点-1秒");
        Instant nowPlusOneSecond = Instant.ofEpochSecond(-1L);
        System.out.println(nowPlusOneSecond);
        System.out.println(nowPlusOneSecond.getEpochSecond());
        System.out.println(nowPlusOneSecond.getNano());
        System.out.println(nowPlusOneSecond.toEpochMilli());

        System.out.println("构造到纳秒级的时间点");
        Instant nowNano = Instant.ofEpochSecond(5L, 500L);
        System.out.println(nowNano);
        System.out.println(nowNano.getEpochSecond());
        System.out.println(nowNano.getNano());
        System.out.println(nowNano.toEpochMilli());

        System.out.println("构造到毫秒级的时间点");
        Instant nowMili = Instant.ofEpochMilli(5500L);
        System.out.println(nowMili);
        System.out.println(nowMili.getEpochSecond());
        System.out.println(nowMili.getNano());
        System.out.println(nowMili.toEpochMilli());

        System.out.println("根据文本构造时间点");
        Instant nowText = Instant.parse("1970-01-01T00:00:00Z");
        System.out.println(nowText);
        System.out.println(nowText.getEpochSecond());

        System.out.println("时间点的比较");
        Instant datetime1 = Instant.parse("1970-01-01T00:00:00Z");
        Instant datetime2 = Instant.parse("1970-01-01T00:00:00Z");
        Instant datetime3 = Instant.parse("1970-01-01T00:00:00Z");
        System.out.println(datetime1.compareTo(datetime2));
        System.out.println(datetime1.compareTo(datetime3));
        System.out.println(datetime2.compareTo(datetime3));
    }
}
