package com.dj.basic.outerClass;

/**
 * @author ydj
 * @date 2024/11/16 14:46
 **/
public class MyClass {
    private String a;

    public MyClass() {
    }
}

class Outer {
    public Outer() {

    }

    public MyClass getInner() {
        /** 局部内部类 */
        class Inner extends MyClass {
            int age = 0;
            int a = 9;
        }
        return new Inner();
    }
}
