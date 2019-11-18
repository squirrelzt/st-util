package com.useage.threadlocal;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatDemo {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<>();

    public DateFormat getDateFormat() {
        DateFormat dateFormat = threadLocal.get();
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat(DATE_FORMAT);
            threadLocal.set(dateFormat);
        }
        return dateFormat;
    }

    public static class MyRunnable implements Runnable {

        private SimpleDateFormatDemo dateFormatDemo;

        public MyRunnable(SimpleDateFormatDemo dateFormatDemo) {
            this.dateFormatDemo = dateFormatDemo;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 当前时间: "+ dateFormatDemo.getDateFormat().format(new Date()));
        }
    }
    public static void main(String[] args) {
        SimpleDateFormatDemo dateFormatDemo = new SimpleDateFormatDemo();

        MyRunnable myRunnable1 = new MyRunnable(dateFormatDemo);
        MyRunnable myRunnable2 = new MyRunnable(dateFormatDemo);
        MyRunnable myRunnable3 = new MyRunnable(dateFormatDemo);

        Thread thread1 = new Thread(myRunnable1);
        Thread thread2 = new Thread(myRunnable2);
        Thread thread3 = new Thread(myRunnable3);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
