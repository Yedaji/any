package com.dj.basic.outerClass;

/**
 * @author ydj
 * @date 2024/11/16 15:18
 **/
public class Test {
    public static void main(String[] args) {
        /**
         * 匿名内部类
         */

        new C() {
            @Override
            public void test() {
                System.out.println("test");
            }
        }.test();
    }
}
