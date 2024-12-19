package com.dj.basic.outerClass;

/**
 * 内部类
 * @author ydj
 * @date 2024/11/16 14:22
 **/
public class OuterClass {
    private int outerData = 10;

    // 成员内部类
    public class InnerClass {
        public void innerMethod() {
            // 内部类可以访问外部类的私有成员
            System.out.println("Outer data: " + outerData);
        }
    }

    // 外部类的方法，创建并使用内部类的实例
    public void outerMethod() {
        InnerClass inner = new InnerClass();
        inner.innerMethod();
    }

    public static void main(String[] args) {
        // 创建外部类的实例
        OuterClass outer = new OuterClass();
        // 通过外部类实例创建内部类实例
        OuterClass.InnerClass inner = outer.new InnerClass();
        inner.innerMethod();

        // 直接从外部类的方法访问内部类
        outer.outerMethod();
    }
}
