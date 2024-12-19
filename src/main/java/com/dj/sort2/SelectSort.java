package com.dj.sort2;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @author ydj
 * @date 2024/09/10 11:48
 **/
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 7, 3, 1, 8, 4};
        sort(arr);
    }

    public static void sort(int[] arr) {
        // 没一轮选出最小的或最大的值出来
        for (int i = 0; i < arr.length - 1; i++) {
            int minNum = arr[i];
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (minNum > arr[j]) {
//                    int tem = arr[i];
                    //这样不好，交换太多，影响效率，只需找出本轮最小的值和该值所在的索引
//                    arr[i] = arr[j];
//                    arr[j] = tem;
                    minNum = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = minNum;

            }
            System.out.println("选择排序第" + (i + 1) + "轮后结果= " + Arrays.toString(arr));
        }

    }


}
