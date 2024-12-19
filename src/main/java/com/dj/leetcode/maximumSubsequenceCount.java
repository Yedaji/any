package com.dj.leetcode;

/**
 * 2207
 *
 * @author ydj
 * @date 2024/09/24 04:05
 **/
public class maximumSubsequenceCount {
    public static void main(String[] args) {
        String text = "iekbksdsmuuzwxbpmcngsfkjvpzuknqguzvzik";
        String pattern = "mp"; // 5
        System.out.println("maximumSubsequenceCount() = " + maximumSubsequenceCount(text,pattern));
    }

    public static long maximumSubsequenceCount(String text, String pattern) {

        int anum = 0;
        int bnum = 0;
        for (int i = 0; i < text.length(); i++) {
            if (pattern.charAt(0) == (text.charAt(i))) {
                anum++;
            }
            if (pattern.charAt(1) == (text.charAt(i))) {
                bnum++;
            }

        }
        System.out.println(anum);
        System.out.println(bnum);
        return Math.max(anum * (bnum + 1), (anum + 1) * bnum);
    }
}
