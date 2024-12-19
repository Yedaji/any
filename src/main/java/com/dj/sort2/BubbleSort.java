package com.dj.sort2;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author ydj
 * @date 2024/09/10 11:24
 **/
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {6, 2, 4, 3, 8, 5};
        sort(arr);
    }

    public static void sort(int[] arr) {
        // 每轮冒出最大的值
        for (int i = 0; i < arr.length; i++) {
            boolean isturn = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tem = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tem;
                    isturn = false;
                }
            }
            if (isturn) {
                System.out.println("第" + (i + 1) + "轮排序后结果 = " + Arrays.toString(arr));
                return;
            }
            System.out.println("第" + (i + 1) + "轮排序后结果 = " + Arrays.toString(arr));
        }

        // 第一轮结果 6，2，4，3，5，8
        // 第二轮结果 2，4，3，5，6，8

    }

}
