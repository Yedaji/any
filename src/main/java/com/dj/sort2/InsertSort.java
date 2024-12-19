package com.dj.sort2;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author ydj
 * @date 2024/09/10 11:48
 **/
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {17, 3, 25, 14, 20, 9};
        sort(arr);
    }

    public static void sort(int[] arr) {
        // 假设第一个值有序，后面的其他值插入其中
        // 第1轮   (3),17, 25, 14, 20, 9
        // 第2轮  （3，17），25，14，20，9
        // 第3轮  （3，17，25），14，20，9
        // 第4轮  （3，14，17，25），20，9
        for (int i = 1; i < arr.length; i++) {
            int insertIndex = i;
            int insertNum = arr[i];
            // 插入条件
            while ((insertIndex-1) >= 0 && arr[insertIndex-1] > insertNum) {
                arr[insertIndex] = arr[insertIndex-1];
                insertIndex--;
            }
            arr[insertIndex] = insertNum;
            System.out.println("插入排序第 " + i + "轮结果 = " + Arrays.toString(arr));
        }
    }
}
