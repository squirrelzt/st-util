package com.useage.forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

public class ForkJoinUsageMain {
    public static void main(String[] args) {
        Instant start = Instant.now();
        ForkJoinUsage usage = new ForkJoinUsage();
        long[] numbers = LongStream.rangeClosed(1, 10000000).toArray();
        long result = usage.sumUp(numbers);
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start, end).toMillis() + "ms");
        System.out.println(result);
    }
}
