package com.useage.mlutithread;

public class DemoRunnable implements Runnable {


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        DemoRunnable demoRunnable1 = new DemoRunnable();
        DemoRunnable demoRunnable2 = new DemoRunnable();
        DemoRunnable demoRunnable3 = new DemoRunnable();

        Thread thread1 = new Thread(demoRunnable1);
        Thread thread2 = new Thread(demoRunnable2);
        Thread thread3 = new Thread(demoRunnable3);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
