package com.util.random;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 类名称: RandomUtils
 * 类描述: 随机生成数工具类
 * @author squirrel
 * @date 2019-03-11
 */
public class RandomUtils {

    private RandomUtils() {}

    /**
     * [0,1) double型数值
     * @return double型数值
     */
    private static double getMathRandom() {
        return Math.random();
    }

    /**
     * [0,1) double型数值,保留小数点后round位
     * @param round 小数点后round位
     * @return double型数值
     */
    private static double getMathRandom(int round) {
        double randomNum = Math.random();
        BigDecimal randomNumBigDecimal = new BigDecimal(randomNum).setScale(round, BigDecimal.ROUND_DOWN);
        return randomNumBigDecimal.doubleValue();
    }
    /**
     * 获取任意整型值
     * @return 整型值
     */
    private static int getIntRandom() {
        Random random = new Random();
        return random.nextInt();
    }

    /**
     * (0, max)的整型值，不包含max
     * @param max 最大值
     * @return 整型值
     */
    private static int getIntRandom(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    /**
     * 获取任意整型值(多线程使用)
     * @return 整型值
     */
    private static int getThreadLocalRandomInt() {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        return threadLocalRandom.nextInt();
    }

    /**
     * (0, max)整型值，不包含max(多线程使用)
     * @param max
     * @return
     */
    private static int getThreadLocalRandomInt(int max) {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        return threadLocalRandom.nextInt(max);
    }

    public static void main(String[] args) {
        int intRandom = RandomUtils.getIntRandom();
        System.out.println(intRandom);

        int intRandomNum = RandomUtils.getIntRandom(4);
        System.out.println(intRandomNum);

        int intThreadLocalRandomInt = RandomUtils.getThreadLocalRandomInt();
        System.out.println(intThreadLocalRandomInt);

        int intThreadLocalRandomIntNum = RandomUtils.getThreadLocalRandomInt(10);
        System.out.println(intThreadLocalRandomIntNum);

        double mathRandom = RandomUtils.getMathRandom();
        System.out.println(mathRandom);

        double mathRandomRound = RandomUtils.getMathRandom(2);
        System.out.println(mathRandomRound);
    }
}
