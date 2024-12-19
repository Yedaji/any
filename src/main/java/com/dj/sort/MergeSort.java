package com.dj.sort;

import java.util.Arrays;

/**
 * 6归并排序 -- 大化小
 *
 * @author ydj
 * @date 2024/09/09 21:39
 **/
public class MergeSort {
    public static void main(String[] args) {
//        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
//        int[] tem = new int[arr.length];
//        sort(arr, 0, arr.length - 1, tem);
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[800000];
        for (int i = 0; i < 800000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        int[] tem = new int[arr.length];
        long start = System.currentTimeMillis();
        sort(arr, 0, arr.length - 1, tem);
        System.out.println("时间消耗= " + (System.currentTimeMillis() - start));
    }

    public static void sort(int[] arr, int left, int right, int[] tem) {

        if (right > left) {
            int mid = (right + left) / 2;
            // 左递归
            sort(arr, left, mid, tem);
            // 右递归
            sort(arr, mid + 1, right, tem);
            merge(arr, left, mid, right, tem);
        }

    }

    public static void merge(int[] arr, int left, int mid, int right, int[] tem) {
        int curL = left;
        int curR = mid + 1;
        int temCur = 0;
        // 合并
        while (curL <= mid && curR <= right) {
            if (arr[curL] > arr[curR]) {
                tem[temCur] = arr[curR];
                curR++;
                temCur++;
            } else {
                tem[temCur] = arr[curL];
                curL++;
                temCur++;
            }
        }
        // 补充
        while (curL <= mid) {
            tem[temCur] = arr[curL];
            temCur++;
            curL++;
        }
        while (curR <= right) {
            tem[temCur] = arr[curR];
            temCur++;
            curR++;
        }
        // 拷贝tem到arr
        int t = left; //
        for (int i = 0; i < temCur; i++) {
            arr[t] = tem[i];
            t++;
        }
//        temCur = 0;
//        int temLeft = left;
//        while (temLeft <= right) {
//            arr[temLeft] = tem[temCur];
//            temLeft++;
//            temCur++;
//        }
    }


}
