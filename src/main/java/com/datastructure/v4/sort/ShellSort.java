package com.datastructure.v4.sort;

/**
 * 希尔排序
 * 空间复杂度O(1)
 */
public class ShellSort {

    public static void shellSort(int[] array) {
        for (int delta = array.length/2; delta>0; delta/=2) {
            for (int i = delta; i < array.length; i++) {
                int temp = array[i],j;
                for (j=i-delta; j>=0 && temp < array[j]; j-=delta) {
                    array[j+delta] = array[j];
                }
                array[j+delta] = temp;
                System.out.print("第"+i+"趟 temp ="+temp+"\t");
                for (int t : array) {
                    System.out.print(t + "\t");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {30, 20, 60, 40, 90, 30};
        shellSort(array);
    }
}
