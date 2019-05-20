package com.useage.mlutithread;

import java.util.concurrent.*;

public class DemoCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        int a = 1;
        int b = 2;
        return Thread.currentThread().getName() + " 执行结果: " + (a + b);
    }

    public static void main(String[] args) throws Exception {

        DemoCallable demoCallable1 = new DemoCallable();
        DemoCallable demoCallable2 = new DemoCallable();
        DemoCallable demoCallable3 = new DemoCallable();

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<String> future1 = executorService.submit(demoCallable1);
        Future<String> future2 = executorService.submit(demoCallable2);
        Future<String> future3 = executorService.submit(demoCallable3);

        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println(future3.get());

        executorService.shutdown();
    }
}
