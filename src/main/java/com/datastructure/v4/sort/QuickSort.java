package com.datastructure.v4.sort;

import java.util.HashMap;
import java.util.Map;

public class QuickSort {

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length-1);
    }

    public static void quickSort(int[] array, int begin, int end) {
        if (begin>=0 && begin<array.length && end>=0 && end<array.length && begin<end) {
            int i = begin,j=end;
            int vot = array[i];
            while (i!=j) {
                while (i<j && array[i]>=vot) {
                    j--;
                }
                if (i<j) {
                    array[i++] = array[j];
                }
                while (i<j && array[i]<=vot) {
                    i++;
                }
                if (i<j){
                    array[j--] = array[i];
                }
            }
            array[i] = vot;
            System.out.print(begin+".."+end+",vot="+vot+" ");
            for (int t : array) {
                System.out.print(t + "\t");
            }
            System.out.println();
            quickSort(array, begin, j-1);
            quickSort(array, i+1, end);
        }
    }

    public static void main(String[] args) {
        int[] array = {38, 38, 97, 75, 61, 19, 26, 49};
        quickSort(array);
        for (int t : array) {
            System.out.print(t + "\t");
        }
        Map map = new HashMap();
    }
}
