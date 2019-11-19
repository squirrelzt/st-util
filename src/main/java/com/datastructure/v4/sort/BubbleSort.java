package com.datastructure.v4.sort;

public class BubbleSort {

    private static void swap(int[] array, int i, int j) {
        int tmep = array[i];
        array[i] = array[j];
        array[j] = tmep;
    }

    public static void bubbleSort(int[] array) {
        bubbleSort(array, true);
    }

    public static void bubbleSort(int[] array, boolean asc) {
        System.out.println("冒泡排序 （"+ (asc?"升":"降")+"序）");
        boolean exchange = true;
        for (int i = 1; i < array.length && exchange; i++) {
            exchange = false;
            for (int j = 0; j < array.length -i; j++) {
                if (asc ? array[j] > array[j+1]: array[j] < array[j+1]) {
                    swap(array, i, j);
                    exchange = true;
                }
            }
            System.out.print("第"+i+"趟\t");
            for (int t : array) {
                System.out.print(t + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] array = {30, 20, 60, 40, 90, 30};
        bubbleSort(array);
    }
}
