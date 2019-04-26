package com.useage.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUsage {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(new Date());
        System.out.println(now);
    }
}
