package com.datastructure.algorithm.problem;

/**
 *
 * 题目描述：有二维数组，数据是整数，每行从左到右递增，每列从上到下递增，
 * 有整数K，判断K是否在数组中
 * int array[][]=[1, 5, 8, 10, 15
 *                2, 7, 16,19, 20
 *                6, 14,25,28, 30
 *                21,32,36,40, 45
 *                26,35,42,48, 50]
 */
public class TwoDimensionalArraySearch {

    public static void main(String[] args) {
        int k = 31;
//        int k = 20;
        int array[][] = {{1, 5, 8, 10, 15},{2, 7, 16,19, 20},{6, 14,25,28, 30},{21,32,36,40, 45},{26,35,42,48, 50}};

        search(array, k);
    }

    /**
     *
     * @param array 二维数组
     * @param k 要查找的值
     * @return 是否存在
     * 二分查找的思想，右上角（或左下角）就是类似中心节点，左侧都是小于的数，下侧都是大于的数
     *
     */
    public static boolean search(int array[][], int k) {
        // 数组行数
        int rowLength = array.length;
        // 数组列数
        int colLength = array[array.length-1].length;

        int rowIndex = 0;
        int colIndex = colLength - 1;
        boolean exist = false;
        boolean outOfBounds = false;
        while (!exist && !outOfBounds) {
            if (array[rowIndex][colIndex] < k) {
                if (rowIndex == rowLength - 1) {
                    outOfBounds = true;
                } else {
                    rowIndex++;
                }
            } else if (array[rowIndex][colIndex] > k) {
                if (colIndex == 0) {
                    outOfBounds = true;
                } else {
                    colIndex--;
                }
            } else {
                exist = true;
            }
        }
        System.out.println("rowIndex: " + rowIndex);
        System.out.println("colIndex: " + colIndex);
        if (exist) {
            System.out.println("k = " + k + "存在，位置是: array[" + rowIndex + "][" + colIndex + "]");
        } else {
            System.out.println("k = " + k + "不存在");
        }
        return exist;
    }
}

