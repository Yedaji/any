package com.dj.niuke;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author ydj
 * @date 2024/10/07 08:26
 **/
public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<List<user>> lists = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            ArrayList<user> users = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                user user = new user();
                user.setIndex(i);
                user.setValue(sc.nextInt());
                users.add(user);
            }
            lists.add(users);
        }

        int total = 0;
        int peruser = -1;

        for (int i = 0; i < n; i++) {
            List<user> users = lists.get(i);

            List<user> collect = users.stream().sorted(Comparator.comparing(user::getIndex))
                    .sorted(Comparator.comparing(user::getValue))
                    .collect(Collectors.toList());
            for (user user : collect) {
                if (peruser == -1 || user.getIndex() != peruser) {
                    total = total + user.getValue();
                    peruser = user.getIndex();
                    break;
                }
            }

        }
        System.out.println(total);

    }

    private static class user {
        public int index;
        public int value;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}

