package com.dj.search;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * 二分查找 需要在有序序列中查找
 *
 * @author ydj
 * @date 2024/09/11 11:42
 **/
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {4, 5, 7, 8, 9, 10, 10, 10, 17, 18, 19, 44, 54};
        List<Integer> indexList = binarySearch(arr, 10, 0, arr.length - 1);
        System.out.println("indexList = " + indexList);

    }

    private static List<Integer> binarySearch(int[] arr, int result, int left, int right) {
        if (left > right) {
            // 找不到的情况
            return null;
        }
        int mid = (left + right) / 2;
        if (result < arr[mid]) {
            // 查找的值在数组左边
            return binarySearch(arr, result, left, mid - 1);
        } else if (result > arr[mid]) {
            // 查找的值在数组右边
            return binarySearch(arr, result, mid + 1, right);
        } else {
            // 已经找到，看看左边右边是否有相同的值
            List<Integer> list = new ArrayList<>();
            int tem = mid-1;
            while (true) {
                if (left < 0 || arr[tem] != result){
                    break;
                }
                list.add(tem);
                tem--;
            }
            list.add(mid);
            tem = mid+1;
            while (true) {
                if (right>arr.length-1 || arr[tem]!=result){
                    break;
                }
                list.add(tem);
                tem++;
            }
            return list;
        }
    }

    /**
     * 插值插值，int mid =  left + (right-left)*(findVal - arr[left] / (arr[right] - arr[left]);
     *
     */
    public static int insetValSearch(int[] arr, int finalVal, int left, int right) {
        if (left>right || finalVal<arr[0] || finalVal>arr[arr.length-1]){
            return -1;
        }
        int mid = left+(right-left)*(finalVal-arr[left])/ (arr[right]-arr[left]);
        int midVal = arr[mid];
        if (finalVal>midVal){
            return insetValSearch(arr, finalVal, mid+1, right);
        }else if (finalVal< midVal){
            return insetValSearch(arr, finalVal, left, mid-1);
        }else {
            return mid;
        }
    }
}
