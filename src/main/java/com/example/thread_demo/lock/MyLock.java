package com.example.thread_demo.lock;

public class MyLock {
    synchronized public void speedPrintString(){
        System.out.println("speedPrintString getLock time="+System.currentTimeMillis()+"threadName="+Thread.currentThread().getName());
        System.out.println("--------------");
        System.out.println("speedPrintString releaseLock time="+System.currentTimeMillis()+"threadName="+Thread.currentThread().getName());

    }
}
