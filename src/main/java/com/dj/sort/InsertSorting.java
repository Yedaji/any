package com.dj.sort;

import java.util.Arrays;

/**
 * 3插入排序
 *
 * @author ydj
 * @date 2024/09/06 15:29
 **/
public class InsertSorting {
    public static void main(String[] args) {
//        int[] arr = {17, 3, 25, 14, 20, 9};
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
     * 假设第一个数有序，n-1个数插入
     * @param arr
     */
    public static void sort(int[] arr) {
        // 插入条件
        for (int i = 1; i < arr.length; i++) {
            int insertIndex = i - 1;
            int insertNum = arr[i];
            while (insertIndex >= 0 && arr[insertIndex] > insertNum) {
                // 大的数往后移动
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            insertIndex++;
            arr[insertIndex] = insertNum;
        }
    }
}
