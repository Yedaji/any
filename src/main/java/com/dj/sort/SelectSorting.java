package com.dj.sort;

import java.util.Arrays;

/**
 * 2选择排序
 *
 * @author ydj
 * @date 2024/09/05 15:53
 **/
public class SelectSorting {

    public static void main(String[] args) {
//        int[] arr = {5, 2, 7, 3, 1, 8, 4};
//        sort(arr);
//        System.out.println(Arrays.toString(arr));
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        sort(arr);
        System.out.println("时间消耗= " + (System.currentTimeMillis() - start)); // 2070
    }

    /**
     * 每次找出最小的的那个数
     */
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min= arr[j];
                    minIndex=j;
                }
            }
            if (minIndex!=i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
