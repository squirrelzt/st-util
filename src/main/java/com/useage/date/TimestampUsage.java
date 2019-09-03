package com.useage.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampUsage {
    public static void main(String[] args) throws ParseException {
////        String time = "2019-08-01 00:00:00";
//        String time = "2019-06-10";
////        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = sdf.parse(time);
//        long timestamp = date.getTime();
//        System.out.println(timestamp);
//
//        Date date1 = new Date(timestamp);
//        System.out.println(sdf.format(date1));

        long t1 = 1564882290512L;
        long t2 = 1567560689512L;
        long t3 = 1564848000000L;
        long t4 = 1567440000000L;
//        long t1 = 1564882290L;
//        long t2 = 1567560689L;
//        long t3 = 1564848000L;
//        long t4 = 1567440000L;
        test10(t1);
        test10(t2);
        test10(t3);
        test10(t4);
    }

    private static void test10(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        System.out.println(dateStr);
    }
}
