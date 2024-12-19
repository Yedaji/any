package com.dj.leetcode.string;

import java.util.LinkedList;

/**
 * 无重复字符的最长子串 s 由英文字母、数字、符号和空格组成
 *
 * @author ydj
 * @date 2024/11/21 16:11
 **/
public class LeetCode3 {

    public static void main(String[] args) {
        String str = "abcbacbb";
        new LeetCode3().lengthOfLongestSubstring(str);
    }

    public int lengthOfLongestSubstring(String s) {
        // s 由英文字母、数字、符号和空格组成
        int start = 0;
        int res = 0;
        int[] last = new int[128];
        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res = Math.max(res, i - start + 1);
            last[index] = i;
        }
        System.out.println("res = " + res);
        return res;
    }

    public int lengthOfLongestSubstring2(String s) {
        int[] arr = new int[128];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=-1;
        }
        int maxlen=0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i);
            start = Math.max(start, arr[index]+1);
            maxlen =Math.max(maxlen, i-start+1);
            arr[index] =i;
        }
        return maxlen;
    }
}
