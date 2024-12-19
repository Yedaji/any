package com.dj.sort2;

import java.util.Arrays;

/**
 * 基数排序 空间换时间
 *
 * @author ydj
 * @date 2024/09/10 17:05
 **/
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214, -53, -153};
        sort(arr);
    }

    private static void sort(int[] arr) {
        // 获取最大位数的值
        int max = arr[0];
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        // 若有负数
        if (min < 0) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] += Math.abs(min);
                max += Math.abs(min);
            }
        }
        System.out.println("max = " + max);
        // 初始化十个桶 0-9
        int[][] bucket = new int[10][arr.length];
        int[] bucketCount = new int[10];
        int maxLength = ("" + max).length();
        // 循环个位，十位，百位，千位数等
        for (int i = 0, n = 1; i < maxLength; i++, n*=10) {
            for (int j = 0; j < arr.length; j++) {
                //  获取没位数对应的桶索引
                int bucketIndex = arr[j] / n % 10;
                // 放入桶中
                bucket[bucketIndex][bucketCount[bucketIndex]] = arr[j];
                bucketCount[bucketIndex]++;
            }
            // 从桶中取出数据
            int arrIndex =0;
            for (int k = 0; k < bucketCount.length; k++) {
                if (bucketCount[k]>0){
                    for (int j = 0; j < bucketCount[k]; j++) {
                        arr[arrIndex] = bucket[k][j];
                        arrIndex++;
                    }
                    // 重置桶数据量为0，方便下次使用
                    bucketCount[k]=0;
                }
            }
        }
        // 恢复数据
        if (min<0){
            for (int i = 0; i < arr.length; i++) {
                arr[i]+=min;
            }
        }
        getPrintln(arr);
    }

    private static void getPrintln(int[] arr) {
        System.out.println("基数排序后结果 = " + Arrays.toString(arr));
    }
}
