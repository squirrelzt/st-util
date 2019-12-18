package com.useage.threadlocal;

public class ThreadLocalDemo1 {
    private static int a = 10;
    private static ThreadLocal<Integer> threadLocal;

    static class ThreadA implements Runnable {

        @Override
        public void run() {
            threadLocal = new ThreadLocal<>();
            threadLocal.set(a + 10);
            System.out.println(threadLocal.get() + Thread.currentThread().getName());
            threadLocal.remove();
            System.out.println(threadLocal.get() + Thread.currentThread().getName());
        }
    }

    static class ThreadB extends Thread {

        @Override
        public void run() {
            System.out.println(threadLocal.get() + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        Thread A = new Thread(new ThreadA());
        A.start();

        ThreadB B = new ThreadB();
        B.start();
    }
}
