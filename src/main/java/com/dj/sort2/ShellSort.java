package com.dj.sort2;

import java.util.Arrays;

/**
 * 希尔排序，一分二
 *
 * @author ydj
 * @date 2024/09/10 12:14
 **/
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        sort(arr);

    }

    /**
     * 移位法--插入
     *
     * @param arr
     */
    private static void sort(int[] arr) {
        int d = 1;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int insertIndex = i;
                int insertNum = arr[i];
                while (insertIndex - gap >= 0 && arr[insertIndex-gap] > insertNum) {
                    arr[insertIndex] = arr[insertIndex - gap];
                    insertIndex -= gap;
                }
                arr[insertIndex] = insertNum;
            }
            System.out.println("希尔排序交换法第" + d + "次排序后结果：" + Arrays.toString(arr));
            d++;
        }
    }

    /**
     * 交换法
     *
     * @param arr
     */
    private static void sort2(int[] arr) {
        //每一轮
        int d = 1;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 第一轮 gap=5/2=2
            for (int i = gap; i < arr.length; i++) {
                // 和之前的所有值做交换.保证每次交换前都是有序的
                for (int j = i; j - gap >= 0; j -= gap) {
                    if (arr[j] < arr[j - gap]) {
                        int tem = arr[j - gap];
                        arr[j - gap] = arr[j];
                        arr[j] = tem;
                    }
                }
            }
            System.out.println("希尔排序交换法第" + d + "次排序后结果：" + Arrays.toString(arr));
            d++;

        }

    }

}
