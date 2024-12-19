package com.dj.basic;

/**
 * @author ydj
 * @date 2024/11/16 12:45
 **/
public class Parent {
    public void show() {
        System.out.println("show fu");
    }
}

class Child extends Parent {
    public void show() {
        System.out.println("show zi");
    }

    public void method() {
        System.out.println("method zi");
    }

}
