package com.dj.basic;

/**
 * @author ydj
 * @date 2024/11/16 12:46
 **/



public class PolymorphismDemo {
    public static void main(String[] args) {
        Parent fu=new Child();
        fu.show();
//        fu.method();//不能访问子类特有功能（特有方法）

        Child zi=(Child)fu; //向下转型
        zi.show();
        zi.method();
    }
}
