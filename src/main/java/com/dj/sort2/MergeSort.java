package com.dj.sort2;

import java.util.Arrays;

/**
 * 归并排序
 * 思想：不断分组不断合并
 *
 * @author ydj
 * @date 2024/09/10 16:10
 **/
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] tem = new int[arr.length];
        sort(arr, 0, arr.length-1,tem);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr, int left, int right, int[] tem) {
        // 分组
        if (right> left){
            int mid = (left+right)/2;
            sort(arr, left, mid,tem);
            sort(arr, mid+1, right,tem);
            merge(arr,left,mid,right,tem);
        }
    }

    /**
     * 把两个有序数组 组合到临时数组，使临时数组为有序，然后再复制到原数组中使该数组left到right有序
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] tem) {
        // 左边数组范围left-mid,右边数组范围 mid+1 - right
        int curL = left;
        int curR = mid + 1;
        int t = 0;
        while (curL <= mid && curR <= right) {
            if (arr[curL]> arr[curR]){
                tem[t] = arr[curR];
                curR++;
                t++;
            } else {
                tem[t] = arr[curL];
                curL++;
                t++;
            }
        }
        // 剩下的直接补到tem数组
        while (curL<=mid){
            tem[t] =arr[curL];
            curL++;
            t++;
        }
        while (curR<=right){
            tem[t] =arr[curR];
            curR++;
            t++;
        }
        // 把tem复制到arr
        int l = left;
        for (int i = 0; i < t; i++) {
            arr[l] = tem[i];
            l++;
        }
    }

}
