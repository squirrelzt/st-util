package com.useage.mlutithread.state;

import java.util.concurrent.TimeUnit;

public class ThreadState {

    public static void main(String[] args) {
        // TIME_WAITING
        new Thread(() -> {
           while (true) {
               try {
                   TimeUnit.SECONDS.sleep(100);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        }, "TIME_WAITING").start();

        // WAITING,线程在 ThreadStatus 类锁上通过 wait 进行等待
        new Thread(() -> {
            while (true) {
                synchronized (ThreadState.class) {
                    try {
                        ThreadState.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "WAITING").start();

        new Thread(new BlockedDemo(), "BlockedDemo-01").start();
        new Thread(new BlockedDemo(), "BlockedDemo-02").start();
    }

    static class BlockedDemo extends Thread {

        @Override
        public void run() {
            synchronized (BlockedDemo.class) {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
