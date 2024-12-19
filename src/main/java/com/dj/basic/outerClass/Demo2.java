package com.dj.basic.outerClass;

/**
 *  成员内部类
 * @author ydj
 * @date 2024/11/16 14:54
 **/
public class Demo2 {
    public static void main(String[] args) {
        A a = new A();
        A.B b = a.new B();
        b.printB();
        System.out.println(a.a);
        System.out.println(b.a);
    }
}

class A {
    public int a =10;
    private int b = 20;
    static  int c = 30;
    public void printA(){
        System.out.println("printA");
    }
    public void printab(){
        B b1 = new B();
        System.out.println(b1.a);
        System.out.println(" = ");
        b1.printB();
    }
    class B{
        public int a = 15;
        public void printB() {
//            System.out.println(A.this.a);
            System.out.println(a);
            System.out.println(b);
            System.out.println(c);
            printA();
        }
    }
}
