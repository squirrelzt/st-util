package com.datastructure.algorithm.sort.heap;

import java.util.Arrays;

/**
 * className:HeapSort
 * class des:big heap sort
 */
public class HeapSort {

    public static void heapSort(int[] a) {
        System.out.println("begin sort ......");
        int arrayLength = a.length;
        for (int i = 0; i < arrayLength-1; i++) {
            buildMaxHeap(a, arrayLength-1-i);

            swap(a, 0, arrayLength-1-i);
            System.out.println(Arrays.toString(a));
        }
    }

    private static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    private static void buildMaxHeap(int[] data, int lastIndex) {
        //从lastIndex处节点（最后一个节点）的父节点开始
        for (int i = (lastIndex -1)/2; i >= 0; i--) {
        //k保存正在判断的节点
            int k = i;
            //如果当前k节点的子节点存在
            while (k*2+1 <= lastIndex) {
                //k节点的左子节点的索引
                int biggerIndex = 2*k + 1;
                //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                if (biggerIndex < lastIndex) {
                    //如果右子节点的值较大
                    if (data[biggerIndex] < data[biggerIndex+1]) {
                        //biggerIndex总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }
                //如果k节点的值小于其较大的子节点的值
                if (data[k] < data[biggerIndex]) {
                    //交换
                    swap(data, k, biggerIndex);
                    //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                    k=biggerIndex;
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {30, 20, 60, 40, 90, 30};
        heapSort(a);
    }

}
