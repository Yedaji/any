package com.dj.sort;

import java.util.Arrays;

/**
 * 4希尔排序--也是插入排序
 *
 * @author ydj
 * @date 2024/09/08 15:43
 **/
public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        sort(arr);
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        sort(arr);
        System.out.println("时间消耗= " + (System.currentTimeMillis() - start)); // 64
    }

    public static void sort(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 第i轮排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int tem = arr[j];
                if (arr[j - gap] > arr[j]) {
                    while (j - gap >= 0 && tem < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = tem;
                }
            }
        }
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 交换法，效率低，需要改用移位法
     *
     * @param arr
     */
    public static void sort2(int[] arr) {

        int index = 0;
        for (int i = arr.length / 2; i > 0; i /= 2) {
            index++;
            for (int j = i; j < arr.length; j += i) {
                for (int k = j - i; k >= 0; k -= i) {
                    if (arr[k] > arr[k + i]) {
                        int tem = arr[k];
                        arr[k] = arr[k + i];
                        arr[k + i] = tem;
                    }
                }
            }
            System.out.println("第" + index + "轮后结果：" + Arrays.toString(arr));
        }

    }

    private static void extracted(int[] arr) {
        // 第一轮 10/2=5组 每一组都交换大小
        for (int i = 5; i < arr.length; i++) {
            // 遍历各组中
            for (int j = i - 5; j >= 0; j -= 5) {
                if (arr[j] > arr[j + 5]) {
                    int tem = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = tem;
                }
            }
        }
        System.out.println("第一轮后数组排序为：" + Arrays.toString(arr));

        // 第二轮 将是个数分成两组
        for (int i = 2; i < arr.length; i++) {
            for (int j = i - 2; j >= 0; j -= 2) {
                if (arr[j] > arr[j + 2]) {
                    int tem = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = tem;
                }
            }
            System.out.println("第二轮第" + i + "次交换：" + Arrays.toString(arr));
        }

        // 第三轮 只有一组
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[j + 1]) {
                    int tem = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tem;
                }
            }

        }
        System.out.println("第三轮结果：" + Arrays.toString(arr));
    }
}
