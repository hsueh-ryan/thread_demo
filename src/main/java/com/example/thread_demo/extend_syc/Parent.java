package com.example.thread_demo.extend_syc;

public class Parent {

    public int i = 10;

    synchronized public void parentMethod() {
        try {
            i--;
            System.out.println("main print i=" + i);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
