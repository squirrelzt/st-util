package com.useage.escapeanalysis;

public class EscapeAnalysis {
    static class Admin {
        public String name;
    }

    public static void testEscape() {
        Admin admin = new Admin();
    }

    public static void main(String[] args) {
        System.out.println("start");
        long begin = System.currentTimeMillis();
        for (long i =0; i < 1000000; i++) {
            testEscape();
        }
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end-begin));
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
