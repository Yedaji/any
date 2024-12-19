package com.dj.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * 3248
 *
 * @author ydj
 * @date 2024/11/21 15:30
 **/
public class Leetcode3248 {
    int x = 0;
    int y = 0;

    public static void main(String[] args) {
        Leetcode3248 leetcode3248 = new Leetcode3248();
        List<String> commands = Arrays.asList("DOWN","RIGHT","UP");
        leetcode3248.finalPositionOfSnake(3, commands);
    }

    public int finalPositionOfSnake(int n, List<String> commands) {
        /**
         * grid[i][j] = (i * n) + j
         * 10=2
         */

        int step = 0;
        for (String command : commands) {
            step = getStep(command, n);
        }
        return step;
    }

    public int getStep(String command, int n) {
        // "UP"、"RIGHT"、"DOWN" 和 "LEFT"
        switch (command) {
            case "UP":
                x--;
                break;
            case "RIGHT":
                y++;
                break;
            case "DOWN":
                x++;
                break;
            case "LEFT":
                y--;
                break;
            default:
                break;
        }
        //  (i * n) + j
        return (x * n) + y;
    }
}
