package com.dj.sort;

import java.util.Arrays;

/**
 * 7基数排序--桶排序，先排个位数，十位数，百位数～～
 *
 * @author ydj
 * @date 2024/09/10 10:02
 **/
public class RadixSort {
    public static void main(String[] args) {
        int fornt = -1;
        int rear = -1;
        int[] arr = new int[3];
        for (int i = 0; i < 6; i++) {
            if (fornt==-1||rear==-1){
                fornt++;
                rear++;
                arr[rear]= i;
                continue;
            }
            arr[rear]= i;
            fornt = (fornt + 1) % 3;
            rear = (rear + 1) % 3;
            System.out.println("fornt = " + fornt);
            System.out.println("rear = " + rear);
            System.out.println(Arrays.toString(arr));
        }

//        int[] arr = {53, 3, 542, 748, 14, 214, -53,-153};
//        sort(arr);
    }

    public static void sort(int[] arr) {
        int maxNum = arr[0];
        int minMin = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxNum) {
                maxNum = arr[i];
            }
            if (arr[i] < minMin) {
                minMin = arr[i];
            }
        }
        // 如果有负数，所有值加上最小负数的绝对值，使其全部变为正整数
        if (minMin < 0) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] -= minMin;
            }
            maxNum -= minMin;
        }
        int maxLength = String.valueOf(maxNum).length();
        System.out.println(maxLength);
        // 1定义十个桶，每个桶后面的值放该桶数字的索引
        int[][] bucket = new int[10][arr.length];
        int[] bucketNumCount = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            int arrIndex = 0;
            for (int j = 0; j < arr.length; j++) {
                // 取对应的位数值，个位-十位-百位-千位
                int bucketIndex = arr[j] / n % 10;
                // 2 放-将对于的位数放到对于的索引桶中
                bucket[bucketIndex][bucketNumCount[bucketIndex]] = arr[j];
                // 3 一个桶加入一个数，该桶组数据量+1
                bucketNumCount[bucketIndex]++;

            }
            //3取-得到新的数组
            for (int k = 0; k < bucketNumCount.length; k++) {
                if (bucketNumCount[k] > 0) {
                    for (int l = 0; l < bucketNumCount[k]; l++) {
                        arr[arrIndex] = bucket[k][l];
                        arrIndex++;
                    }
                    bucketNumCount[k] = 0;
                }
            }
            System.out.println("基数排序第" + i + "轮后排序 = " + Arrays.toString(arr));
        }
        // 将所有值还原
        if (minMin < 0) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] += minMin;
            }
        }
        System.out.println("基数排序后结果 = " + Arrays.toString(arr));
    }
}
