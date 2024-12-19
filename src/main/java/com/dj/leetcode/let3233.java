package com.dj.leetcode;

/**
 * @author ydj
 * @date 2024/11/22 22:11
 **/
public class let3233 {
    public static void main(String[] args) {
        System.out.println(nonSpecialCount(1, 4));
    }

    public static int nonSpecialCount(int l, int r) {
        int total = r - l + 1;
        int n = (int) Math.sqrt(r); // 最大的值
        int[] arr = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            if (arr[i] == 0) {
                int mulit = i * i;
                if (mulit >= l && mulit <= r) {
                    System.out.println(i);
                    total--;
                }
                // 去除i的倍数
                for (int j = 2 * i; j < n; j += i) {
                    arr[j] = 1;
                }
            }
        }
        return total;
    }

    /*
        1 X

     */
}
