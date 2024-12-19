package com.dj.niuke;

import java.util.*;

/**
 * @author ydj
 * @date 2024/10/07 10:13
 **/
public class B {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String instr = in.nextLine();
            String[] to = instr.split(",");
            List<Integer> list = new ArrayList<>();
            for (String s : to) {
                list.add(Integer.parseInt(s));
            }
            int m1 = list.get(0);
            int m2 = list.get(1);
            int m3 = list.get(2);
            int m4 = list.get(3);
            int m5 = list.get(4);
            int m6 = list.get(5);

            int[][] arr = new int[m1][m2];
            for (int[] ints : arr) {
                Arrays.fill(ints, 0);
            }
            arr[m3][m4] = 1;
            arr[m5][m6] = 1;

            int count = m1 * m2 - 2;
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{m3, m4});
            queue.offer(new int[]{m5, m6});
            int[][] offesets = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            int day = 1;
            while (!queue.isEmpty() && count > 0) {
                int[] tem = queue.poll();
                int x = tem[0];
                int y = tem[1];
                day = arr[x][y] + 1;
                for (int[] offeset : offesets) {
                    int newx = x + offeset[0];
                    int newy = y + offeset[1];
                    if (newx > +0 && newx < m1 && newy >= 0 && newy < m2 && arr[newx][newy] == 0) {
                        arr[newx][newy] = day;
                        queue.offer(new int[]{newx, newy});
                        count--;

                    }
                }
            }
            System.out.println(day - 1);
        }

    }
}
