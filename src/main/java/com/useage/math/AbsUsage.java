package com.useage.math;

/**
 * 类名称: AbsUsage
 * 类描述: abs取绝对值
 * @author squirrel
 * @date 2019-09-17
 */
public class AbsUsage {
    public static void main(String[] args) {
        // 判断两个值的大小误差在[-10,10]
        int num1 = 15;
        int num2 = 5;

        int val = 10;

        boolean flag = Math.abs(num1-num2) <= val;
        System.out.println(flag);
    }
}
