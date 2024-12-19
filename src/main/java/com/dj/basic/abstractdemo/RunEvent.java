package com.dj.basic.abstractdemo;

/**
 * @author ydj
 * @date 2024/11/16 14:07
 **/
public class RunEvent extends EventD{
    private String name;
    @Override
    public void dofirst() {
        System.out.println("run");
    }

    @Override
    public void mustdo() {
        super.mustdo();
        System.out.println("noon");
    }

    public RunEvent() {
    }

    public RunEvent(String name) {
        this.name = name;
    }
}
