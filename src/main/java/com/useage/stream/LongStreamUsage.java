package com.useage.stream;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class LongStreamUsage {

    public static void main(String[] args) {
        Object[] longStream = LongStream.range(1, 20).boxed().toArray();
        Object[] longStream1 = LongStream.rangeClosed(10, 20).boxed().toArray();
        ConcurrentHashMap<Object, Long> concurrentHashMap = LongStream.range(1, 20).boxed().collect(Collectors.toConcurrentMap(i-> UUID.randomUUID(),
                Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        System.out.println(longStream);
        System.out.println(longStream1);
    }
}
