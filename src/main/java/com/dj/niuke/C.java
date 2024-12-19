package com.dj.niuke;

import java.util.Scanner;

/**
 * @author ydj
 * @date 2024/10/07 10:56
 **/
public class C {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String input = in.nextLine();
            int n = in.nextInt();
            String[] split = input.split(",");
            int[] ints = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                ints[i] = Integer.parseInt(split[i]);
            }
            System.out.println(min(ints, n));
        }
    }

    public static int min(int[] time, int m) {
        int n = time.length;
        if (m >= n) {
            return 0;
        }
        int l = 0;
        int r = 100000000;
        while (l < r) {
            int mid = (l + r) / 2;
            if (check(time, m, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private static boolean check(int[] time, int m, int limit) {
        int cur = 0;
        int sum = 0;
        int max = 0;
        int day = 1;
        while (cur < time.length) {
            sum = sum + time[cur];
            max = Math.max(max, time[cur]);

            if (sum - max > limit) {
                day++;
                if (day > m) {
                    return false;
                }
                sum = time[cur];
                max = time[cur];
            }
            cur++;
        }
        return true;
    }
}
