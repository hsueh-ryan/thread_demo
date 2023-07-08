package com.example.thread_demo.sync;


/**
 * synchronizedek關鍵字加到static靜態方法上是將class對象作為鎖
 * <p>
 * synchronizedek關鍵字加到實體方法上是將方法所在類對象作為鎖
 */
public class StaticSync {
    public static void main(String[] args) {
        Thread threadA = new Thread(Service4::printA);
        Thread threadB = new Thread(Service4::printB);

        threadA.setName("A");
        threadA.start();

        threadB.setName("B");
        threadB.start();
    }


}

class Service4 {
    synchronized public static void printA() {
        System.out.println("thread name " + Thread.currentThread().getName() + "enter printA time is " + System.currentTimeMillis());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("thread name " + Thread.currentThread().getName() + "exit printA time is " + System.currentTimeMillis());
    }

    synchronized public static void printB() {
        System.out.println("thread name " + Thread.currentThread().getName() + "enter printB time is " + System.currentTimeMillis());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("thread name " + Thread.currentThread().getName() + "exit printB time is " + System.currentTimeMillis());
    }
}
