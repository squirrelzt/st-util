package com.useage.mlutithread;

public class DemoThread extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        DemoThread thread1 = new DemoThread();
        DemoThread thread2 = new DemoThread();
        DemoThread thread3 = new DemoThread();

        thread1.start();
        thread2.start();
        thread3.start();

    }
}
