package com.example.thread_demo.extend_syc;

public class Child extends Parent{

    synchronized public void childMethod(){
        try {
            while (i>0){
                i--;
                System.out.println("sub print i="+i);
                Thread.sleep(100);
                super.parentMethod();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
