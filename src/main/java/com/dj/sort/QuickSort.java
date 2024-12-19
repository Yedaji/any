package com.dj.sort;

import java.util.Arrays;

/**
 * 5快速排序
 *
 * @author ydj
 * @date 2024/09/09 19:37
 **/
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 5, 5,5,33,44};
        sort(arr, 0, arr.length - 1);
//        int[] arr = new int[80000];
//        for (int i = 0; i < 80000; i++) {
//            arr[i] = (int) (Math.random() * 80000);
//        }
//        long start = System.currentTimeMillis();
//        sort(arr, 0, arr.length - 1);
//        System.out.println("时间消耗= " + (System.currentTimeMillis() - start)); // 2070
        System.out.println("排序后数组："+Arrays.toString(arr));
    }

    /**
     * 快速排序 -- 左右递归成有序
     *
     * @param arr
     */
    public static void sort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
//        System.out.println("l: " + l + "，r: " + r);
        int index = (left + right) / 2;
        int pivot = arr[(left + right) / 2];
        int tem = 0;
//        System.out.println("中间索引：" + (r + l) / 2 + "，值：" + pivot);
        while (r > l) {
            // 左边找到大于中间值就退出
            // 如果左边已经全部小于中间值，则arr[l]= pivot
            // -9, 78, 0, -523, 70   l: 0 -> 1 ;  -9, -523, 0, 78, 70 l: 1->2
            while (arr[l] < pivot) {
                l++;
            }
            // 右边找到小于中间值就退出
            // 如果右边已经全部大于中间值，则arr[r]= pivot
            // -9, 78, 0, -523, 70   r: 4 -> 3 ;  -523, 0, 78, 70 r: 3->2
            while (arr[r] > pivot) {
                r--;
            }
            // 说明已经是左边全部小于中间值，右边全部大于中间值
            if (r <= l) {  // l=r=2
                break;
            }
            // 交换？
             tem = arr[l];
            arr[l] = arr[r];
            arr[r] = tem;
            // 如果交换完后，发现arr[l]==pivot，r--，前移 -9, 78, 0, 5, 5,5,33,44
            if (arr[l] == pivot) {
                r--;
            }
            // 如果交换完后，发现arr[r]==pivot，r--，后移
            if (arr[r]==pivot){
                l++;
            }
//            System.out.println("l: " + l + ",r: " + r + "，左右大小后排序为：" + Arrays.toString(arr));
        }

//        System.out.println("此时l: " + l + "，此时r: " + r);
        // r <= l
        if (r == l) { // l=r=2
            l++; // l = 3
            r--; // r = 1
        }
        // r<l
        // 左边递归
        if (left < r) {
//            System.out.println("左排序");
            sort(arr, left, r);
        }
        if (right > l) {
            // 右边递归
//            System.out.println("右排序");
            sort(arr, l, right);
        }
    }

}
