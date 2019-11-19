package com.datastructure.v4.sort;

public class DirectInsertSort {

    public static void directInsertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i],j;
            for (j=i-1; j>=0 && temp<array[j]; j--) {
                array[j+1] = array[j];
            }
            array[j+1] = temp;
            System.out.print("第"+i+"趟 temp ="+temp+"\t");
            for (int t : array) {
                System.out.print(t + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] array = {30, 20, 60, 40, 90, 30};
        directInsertSort(array);
    }
}
