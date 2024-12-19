package com.dj.sort2;

import java.util.Arrays;

/**
 * 快排
 * 思想：选取中间数，保证左边都小于中间数，右边都大于中间数，不断的分割后就是一个有序数列
 *
 * @author ydj
 * @date 2024/09/10 15:11
 **/
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 5, 5, 5, 33, 44};
        int left = 0;
        int right = arr.length - 1;
        sort(arr, left, right);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 快排，左边有序+右边有序
     *
     * @param arr
     */
    public static void sort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int mid = (l + r) / 2;
        int midNum = arr[mid];
        while (r>l) {
            while (arr[l] < midNum) {
                l++;
            }
            while (arr[r] > midNum) {
                r--;
            }
            if (l >= r) {  // l =3 r=4
                break;
            }
            // 大的放到右边，小的放到左边 -9, 78, 0, 5, 5, 5, 33, 44
            // 此时arr[i]，arr[j]可能等于midNum
            int tem = arr[l];
            arr[l] = arr[r];
            arr[r] = tem;
            //当替换的值存在和中间值相等时，需移动指针
            // -9, 5, 0, 5, 5, 78, 33, 44
            // l=1 arr[l]=5; r=5 arr[r]=78; mid=3
            // l=3 r=4
            if (arr[r] == midNum) {
                l++; // r=4 l=4
            }
            if (arr[l] == midNum) {
                r--; // l =4 r=3
            }
        }
        // l =4 r=3
        // 相等时 ?
        if (r == l) {
            l++;
            r--;
        }
        // l =4 r=3
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
