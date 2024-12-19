package com.dj.recursion;

/**
 * 八皇后问题 递归
 *
 * @author ydj
 * @date 2024/09/05 12:14
 **/
public class Queen {
    int max = 8;
    // arr[8] ={0,4,7,5,2,6,1,3} arr下标表示第几行，即第几个皇后，arr[i]=val,val表示第i+1个皇后，放在第i+1行第val+1列
    int[] arr = new int[max];
    static int count =0;
    public static void main(String[] args) {
        Queen queen = new Queen();
        queen.check(0);
        System.out.printf("一共有%d种解法", count);
    }


    private void check(int n) {
        if (n == max) {
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            arr[n] =i;
            if (judge(n)) {
                check(n+1);
            }
        }
    }

    private void print() {
        count++;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 判断第n个皇后和前面的皇后是否冲突，同一行同一列同一斜线
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // 同一列
            boolean commonRow = arr[i] == arr[n];
            // 同一斜线
            boolean commonSlash = Math.abs(n - i) == Math.abs(arr[n] - arr[i]);
            if (commonSlash || commonRow) {
                return false;
            }
        }
        return true;
    }
}
