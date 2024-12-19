package com.dj;

import java.util.Arrays;
import java.util.Properties;
import java.util.function.Function;

public class Main {
    static int[] arrh = new int[10];
    static int[] arrl = new int[10];

    public static void main(String[] args) {

        Properties properties = System.getProperties();
        System.out.println(properties);
         final Function<String, String> FUN = (str) -> {
            // 为null或空字符传是不会进行更新表头操作的
            if (str!="" && str!=null) {
                return str;}
            else {
            return "报错了";}
        };

        System.out.println(FUN.apply("sdee"));
        char[][] board = {
                {'.','.','4','.','.','.','6','3','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'5','.','.','.','.','.','.','9','.'},
                {'.','.','.','5','6','.','.','.','.'},
                {'4','.','3','.','.','.','.','.','1'},
                {'.','.','.','7','.','.','.','.','.'},
                {'.','.','.','5','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'}
        };
        System.out.println(isValidSudoku(board));
    }

    public static boolean check3t3board(char[][] board) {
        int[] arr = new int[10];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char ch = board[i][j];
                if (ch == '.') {
                    continue;
                }
                int ij = ch - 48;

                if (arr[ij] != 0) {
                    System.out.println("3*3内有重复数据");
                    return false;
                }
                arr[ij] = ij;
            }
        }
        return true;
    }

    public static boolean isValidSudoku(char[][] board) {
        char[][] iboard = new char[3][3];
        int x = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char it = board[i][j];
                int num;
                if (it != '.') {
                     num = it - 48;
                    if (arrh[num] != 0) {
                        System.out.println("第" + (i+1) + "行数据重复" + num);
                        return false;
                    }
                    arrh[num]= num;
                }
                char lc = board[j][i];
                if (lc != '.') {
                    num = lc - 48;
                    if (arrl[num] != 0) {
                        System.out.println("第" + (i+1) + "列数据重复" + num);
                        return false;
                    }
                    arrl[num]=num;
                }

            }
//            System.out.println("arrh"+Arrays.toString(arrh));
            System.out.println("arrl"+Arrays.toString(arrl));
            arrh =new int[10];
            arrl =new int[10];
        }

        for (int b = 0; b < 3; b++) {
            for (int j = 0; j < 3; j++) {
                for (int a = 0; a < 3; a++) {
                    for (int i = 0; i < 3; i++) {
                        iboard[a][i] = board[a + b * 3][i + j * 3];
                    }
                }
                check3t3board(iboard);
//                printdoard(iboard);
            }
        }
        return true;
    }

    public static void printdoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(" ~~~~~~~ ");
    }
}