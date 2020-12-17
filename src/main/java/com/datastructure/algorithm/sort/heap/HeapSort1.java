package com.datastructure.algorithm.sort.heap;

import java.util.Arrays;

public class HeapSort1 {
    public static void main(String[] args) {
        int[] a = {30, 20, 60, 40, 90, 30};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
    private static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void sort(int[] array) {
        // 构建大顶堆
        for (int i = array.length/2-1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(array, i, array.length);
        }

        // 调整堆结构+交换堆顶元素与末尾元素
        for (int j = array.length-1;j>0;j--) {
            //将堆顶元素与末尾元素进行交换
            swap(array, 0, j);
            //重新对堆进行调整
            adjustHeap(array, 0, j);
        }
    }
    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     * @param array
     * @param i
     * @param length
     */
    public static void adjustHeap(int[] array, int i, int length) {
        // 取出当前元素
        int temp = array[i];
        // 从i结点的左子结点开始，也就是2i+1处开始,依次只取左子节点
        for (int k = i*2+1; k < length; k = k*2+1) {
            // 如果左子结点小于右子结点，k指向右子结点
            if (k+1 < length && array[k] < array[k+1]) {
                k++;
            }
            // 如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
            if (array[k] > temp) {
                array[i] = array[k];
                i = k;
            } else {
                break;
            }
        }
        array[i] = temp;
    }
}
