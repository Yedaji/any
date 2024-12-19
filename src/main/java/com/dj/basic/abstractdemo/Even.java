package com.dj.basic.abstractdemo;

/**
 * @author ydj
 * @date 2024/11/16 14:10
 **/
public class Even{
    public static void main(String[] args) {
        RunEvent run = new RunEvent("run");
        run.dofirst();
        run.mustdo();
        RunEvent.showAdress();
    }
}
