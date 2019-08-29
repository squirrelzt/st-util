package com.useage.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampUsage {
    public static void main(String[] args) throws ParseException {
        String time = "2019-08-01 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(time);
        long timestamp = date.getTime();
        System.out.println(timestamp);

        Date date1 = new Date(timestamp);
        System.out.println(sdf.format(date1));
    }
}
