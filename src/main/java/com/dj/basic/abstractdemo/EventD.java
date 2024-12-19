package com.dj.basic.abstractdemo;

/**
 * @author ydj
 * @date 2024/11/16 14:00
 **/
public abstract class EventD {
    private String name = "event";
    public static final String ADRESS="adress";
    public static void showAdress(){
        System.out.println(ADRESS);
    }
    public abstract void dofirst();
    public void mustdo(){
        System.out.println(name);
    }
    private void doshelf(){
        System.out.println("doshelf");
    }

}
